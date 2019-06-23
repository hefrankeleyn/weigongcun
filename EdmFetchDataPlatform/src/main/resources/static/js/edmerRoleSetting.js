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
        console.log(roles.length);
        if (roles.length == 0) {
            // 添加选择权限的多选列表，添加确认修改按钮
            // $.ajax({
            //     type: "post",
            //     url: "url",
            //     data: "data",
            //     dataType: "dataType",
            //     success: function (response) {

            //     }
            // });
            optionData = { "roleName": "ROLE_APPLY", "roleDesc": "申请组权限，对edm提数申请进行初步审核" };
            var optionDataArray = new Array();
            optionDataArray.push(optionData);
            // 添加 select 和 button
            addSelectElementAndButton(optionDataArray);

            // 如果alert 存在，移除 alert
            var alert = $("#updateRole").siblings("div[role='alert']");
            if(alert.length>0){
                $(alert).remove();
            }

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
    function addSelectElementAndButton(optionData) {
        var formGroup = $("<div class='form-group'></div>");
        var label = $("<label for='roles'></label>").text("选择权限：");
        var select = $("<select multiple id='roles' name='roles' class='form-control'></select>");
        select.prop("required", true);
        for (var i = 0; i < optionData.length; i++) {
            var roleValue = optionData[i].roleName;
            var optionText = optionData[i].roleDesc;
            var option = $("<option value='" + roleValue + "'>" + optionText + "</option>");
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
    }

    /**
     *
     */
    function sureUpdateSubmit() {
        // 获取所有被选中的role

        var selectedOptions = $("#sureUpdate").siblings(".form-group #roles option:selected");
        var optionValues = new Array();
        for (var i = 0; i < selectedOptions.length; i++) {
            optionValues.push($(selectedOptions[i]).val());
        }
        var eid = $(".card .card-body .card-text #eid").val();
        var data = { "eid": eid, "roles": optionValues }
        // $.ajax({
        //     type: "POST",
        //     url: "url",
        //     data: "data",
        //     dataType: "dataType",
        //     success: function (response) {

        //     }
        // });

        // 添加 alert 元素
        addAlert();
        // 移除 select 和 确认添加按钮
        // 移除select 和 sure button
        removeSelectAndSureButtonElement();
    }

    /**
     * 添加 alert element
     */
    function addAlert() {
        var alert = $("<div class='alert alert-success' role='alert'></div>").text("权限修改成功！");
        $("#updateRole").after(alert);
    }



});