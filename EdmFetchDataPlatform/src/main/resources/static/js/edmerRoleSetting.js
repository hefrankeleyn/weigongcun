$(document).ready(function () {

    // 执行初始化操作
    init();

    /**
     * 执行初始化操作
     */
    function init() {
        // 为修改权限按钮添加监听事件
        $("#updateRole").unbind("click", showOrRemoveSelect);
        $("#updateRole").bind("click", showOrRemoveSelect);
    }

    /**
     * 展示修改权限多选下拉框，或者移除修改权限下拉多选框
     */
    function showOrRemoveSelect() {
        var roles = $("#updateRole").parent().children(".form-group").children("#roles");
        if (roles.length == 0) {
            // 添加选择权限的多选列表，添加确认修改按钮
            var url = $.projectRootUrl() + "/roleController/findAllRole";
            // 获取 token
            var token = $("input[name='_csrf']").val();
            var headers = {"X-CSRF-TOKEN": token}
            $.ajax({
                type: "post",
                url: url,
                data: null,
                headers: headers,
                success: function (response) {
                    var status = response.status;
                    // 0 代表成功， 1 代表失败
                    if (status == 0) {
                        var result = response.result;
                        addSelectElementAndButtonAndRemoveAlert(result);
                    } else {
                        console.error("Ajax Exception: " + url);
                    }
                }
            });
        } else {
            // 移除选择权限的多选列表，移除 确认修改按钮
            removeSelectAndSureButtonElement();
        }

    }

    /**
     * 移除添加权限选择列表，并移除 “确认添加” 按钮
     */
    function removeSelectAndSureButtonElement() {
        $("#updateRole").parent().children(".form-group").remove();
        $("#updateRole").siblings("#sureUpdate").remove();
        $("#updateRole").text("修改权限");
    }

    /**
     * 添加权限选择列表，
     */
    function addSelectElementAndButtonAndRemoveAlert(optionData) {
        var formGroup = $("<div class='form-group mt-2'></div>");
        var label = $("<label for='roles'></label>").text("选择权限：");
        var select = $("<select multiple id='roles' name='roles' class='form-control'></select>");
        select.prop("required", true);
        // 获取该用户的所有权限
        var edmerRoles = $(".card .card-body .edmerRoles span");
        for (var i = 0; i < optionData.length; i++) {
            var rid = optionData[i].rid;
            var optionText = optionData[i].roleDesc;
            var option = $("<option value='" + rid + "'>" + optionText + "</option>");
            // 判断并添加选中元素
            for (var j = 0; j < edmerRoles.length; j++) {
                if ($(edmerRoles[j]).attr("name") == rid) {
                    option.prop("selected", true);
                }
            }
            select.append(option);
        }
        formGroup.append(label).append(select);
        // 将 select 下拉选择列表添加
        $("#updateRole").after(formGroup);

        // 添加确认安装按钮
        var updateButton = $("<button type='button' class='btn btn-primary' id='sureUpdate'></button>").text("确认修改");
        $("#updateRole").after(updateButton);
        // 为确认修改添加事件
        $("#updateRole").siblings("#sureUpdate").unbind("click", sureUpdateSubmit);
        $("#updateRole").siblings("#sureUpdate").bind("click", sureUpdateSubmit);
        $("#updateRole").text("取消修改");

        // 如果alert 存在，移除 alert
        var alert = $("#updateRole").siblings("div[role='alert']");
        if (alert.length > 0) {
            $(alert).remove();
        }
    }

    /**
     *
     */
    function sureUpdateSubmit() {
        // 获取所有被选中的role
        var selectedOptions = $("#updateRole").parent()
            .children(".form-group")
            .children("#roles")
            .children("option:selected");
        var rids = new Array();
        for (var i = 0; i < selectedOptions.length; i++) {
            rids.push($(selectedOptions[i]).attr("value"));
        }
        var eid = $(".card .card-body .card-text #eid").val();
        var data = JSON.stringify({"eid": eid, "rids": rids});
        var url = $.projectRootUrl() + "/edmerController/updateEdmerRoles";
        // 获取 token
        var token = $("input[name='_csrf']").val();
        var headers = {"X-CSRF-TOKEN": token}
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            dataTypes: "json",
            contentType: "application/json",
            headers: headers,
            success: function (response) {
                var status = response.status;
                // 0 代表成功， 1 代表失败
                if (status == 0) {
                    // 修改 用户的权限属性
                    var roles = response.result;
                    resetEdmerRoles(roles)
                    // 添加 alert 元素
                    addAlert();
                    // 移除 select 和 确认添加按钮
                    // 移除select 和 sure button
                    removeSelectAndSureButtonElement();
                } else {
                    console.error("Ajax Exception: " + url);
                }
            }
        });
    }

    /**
     * 重新设置用户的权限
     */
    function resetEdmerRoles(roles) {
        // 删除原有的元素
        // 获取该用户的所有权限
        var cardBodys = $(".card .card-body:last");
        var edmerRoles = cardBodys.children(".edmerRoles");
        if (edmerRoles.length != 0) {
            var roleSpans = edmerRoles.children("span");
            for (var i = 0; i < roleSpans.length; i++) {
                $(roleSpans[i]).remove();
            }
        }else {
            // 创建 edmerRoles div
            var edmerRolesDiv = $("<div class='edmerRoles'></div>");
            cardBodys.append(edmerRolesDiv);
        }

        var edmerRoles = $(".card .card-body .edmerRoles");
        // 添加新的元素
        $.each(roles, function (i, role) {
            var roleSpan = $("<span class='btn btn-primary btn-secondary'></span>");
            roleSpan.attr("name", role.rid);
            roleSpan.text(role.roleDesc);
            edmerRoles.append(roleSpan);
        });

    }

    /**
     * 添加 alert element
     */
    function addAlert() {
        var alert = $("<div class='alert alert-success' role='alert'></div>").text("权限修改成功！");
        $("#updateRole").after(alert);
    }


});