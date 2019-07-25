$(document).ready(function () {
    // 执行初始化操作
    init();

    /**
     * 执行初始化操作
     */
    function init() {
        // 为修改权限按钮添加监听事件
        $("#reSetPassword").unbind("click", showOrRemoveInputs);
        $("#reSetPassword").bind("click", showOrRemoveInputs);
    }


    /**
     * 展示修改权限多选下拉框，或者移除修改权限下拉多选框
     */
    function showOrRemoveInputs() {
        var roles = $("#reSetPassword").parent().children(".passwordInputs");
        if (roles.length == 0) {
            // 添加 select 和 button
            addInputsAndButtonAndRemoveAlert();
        } else {
            // 移除选择权限的多选列表，移除 确认修改按钮
            removeRePasswordForm();
        }

    }

    /**
     * 移除修改密码的表单
     */
    function removeRePasswordForm() {
        $("#reSetPassword").parent().children(".passwordInputs").remove();
        $("#reSetPassword").siblings("#sureReSetPassword").remove();
        $("#reSetPassword").text("修改密码");
    }

    /**
     * 添加权限选择列表，
     */
    function addInputsAndButtonAndRemoveAlert() {
        var reSetPasswordButtonParent = $("#reSetPassword").parent();
        var actionUrl = $.projectRootUrl() + "/edmerController/reSetPassword";
        var parentInputs = $("<form class='mt-2 passwordInputs'></form>")
            .attr("action", actionUrl)
            .attr("method", "POST");
        // 获取 token
        var tokenElement = $(".reSetDive input[name='_csrf']").clone();
        parentInputs.append(tokenElement);
        var eidElement = $(".container .card .card-body #eid").clone();
        parentInputs.append(eidElement);
        var oldPasswordLable = $("<label></label>").attr("for", "oldPassword").text("旧密码");
        var oldPasswordInput = $("<input type='password' class='form-control'>").attr("name", "oldPassword")
            .attr("id", "oldPassword");
        var oldPasswordDiv = $("<div class='form-group w-25'></div>")
            .append(oldPasswordLable)
            .append(oldPasswordInput);

        var newPasswordLable = $("<label></label>").attr("for", "newPassword").text("新密码");
        var newPasswordInput = $("<input type='password' class='form-control'>").attr("name", "newPassword")
            .attr("id", "newPassword");
        var newPasswordDiv = $("<div class='form-group w-25'></div>")
            .append(newPasswordLable)
            .append(newPasswordInput);

        var reNewPasswordLable = $("<label></label>").attr("for", "reNewPassword").text("旧密码");
        var reNewPasswordInput = $("<input type='password' class='form-control'>").attr("name", "reNewPassword")
            .attr("id", "reNewPassword");
        var reNewPasswordDiv = $("<div class='form-group w-25'></div>")
            .append(reNewPasswordLable)
            .append(reNewPasswordInput);

        parentInputs.append(oldPasswordDiv).append(newPasswordDiv).append(reNewPasswordDiv);
        //添加到button 下面
        reSetPasswordButtonParent.append(parentInputs);

        // 添加确认安装按钮
        var updateButton = $("<button type='button' class='btn btn-primary' id='sureReSetPassword'></button>").text("确认修改");
        $("#reSetPassword").after(updateButton);
        // 为确认修改添加事件
        $("#reSetPassword").siblings("#sureReSetPassword").unbind("click", sureReSetPasswordSubmit);
        $("#reSetPassword").siblings("#sureReSetPassword").bind("click", sureReSetPasswordSubmit);
        $("#reSetPassword").text("取消修改");
        // 如果alert 存在，移除 alert
        var alert = $("#reSetPassword").siblings("div[role='alert']");
        if (alert.length > 0) {
            $(alert).remove();
        }
    }

    /**
     * 确认更新密码
     */
    function sureReSetPasswordSubmit() {

        //获取eid
        var eidValue = $(".container .card .card-body #eid").val();
        // 获取旧密码
        var oldPassword = $(this).siblings("form").children(".form-group").children("input[name='oldPassword']");
        var oldPasswordValue = oldPassword.val();
        // 获取新密码
        var newPassword = $(this).siblings("form").children(".form-group").children("input[name='newPassword']");
        var newPasswordValue = newPassword.val();
        // 获取确认新密码
        var reNewPassword = $(this).siblings("form").children(".form-group").children("input[name='reNewPassword']");
        var reNewPasswordValue = reNewPassword.val();

        // 判断旧密码不能为空，且要原有密码进行匹配看是否一致
        if (oldPasswordValue == "") {
            changeIsInValid(oldPassword, "不能为空！");
        } // 新密码不能为空，并且不能少于6位
        else if (newPasswordValue == "" || newPasswordValue.length < 6) {
            newPassChangeIsInValid(newPassword, "新密码不能为空，且长度至少6位！");
        } else if (newPasswordValue != reNewPasswordValue) {
            newPassChangeIsInValid(reNewPassword, "两次输入的密码不一致！");
        } else {
            // 旧密码和原密码进行匹配
            var compareResult = compareoldTruePassword(eidValue, oldPasswordValue);
            console.log("compareResult: " + compareResult);
            if (!compareResult) {
                newPassChangeIsInValid(oldPassword, "和原来的密码不一致！");
            } else {
                // 新旧密码进行对比看是否一致
                var newAndOldCompareResult = compareoldTruePassword(eidValue, newPasswordValue);
                // 新密码不能和旧密码一致
                if (newAndOldCompareResult) {
                    newPassChangeIsInValid(newPassword, "新密码和原来的密码一致，请重新设置！");
                } else {
                    $(this).siblings("form").submit();
                }
            }


        }

    }


    /**
     * 和旧的密码进行匹配
     * @param oldPassword
     */
    function compareoldTruePassword(eid, oldPassword) {

        var flag = false;
        // 将值转化为sjon格式
        var data = {"eid": eid, "oldPassword": oldPassword};
        var url = $.projectRootUrl() + "/edmerController/comparePassword";
        // 获取 token
        var token = $("input[name='_csrf']").val();
        var headers = {"X-CSRF-TOKEN": token}
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            headers: headers,
            async: false,
            success: function (response) {
                var status = response.status;
                // 0 代表成功， 1 代表失败
                if (status == 0) {
                    var result = response.result;
                    flag = true;
                } else {
                    flag = false;
                }
            }
        });

        return flag;
    }


    /**
     * 旧密码认证
     * @param jqSelectElement
     * @param inValidTextValue
     */
    function changeIsInValid(jqSelectElement, inValidTextValue) {
        // 判断 invalid-feedvack div 是否存在
        var invalidDiv = jqSelectElement.siblings(".invalid-feedback");
        if (invalidDiv.length > 0) {
            invalidDiv.text(inValidTextValue)
        } else {
            var invalidDivEle = $("<div class='invalid-feedback'></div>").text(inValidTextValue);
            jqSelectElement.after(invalidDivEle);
        }
        if (!jqSelectElement.hasClass("is-invalid")) {
            jqSelectElement.addClass("is-invalid");
            jqSelectElement.unbind("input propertychange", changeCurrentIsValid);
            jqSelectElement.bind("input propertychange", changeCurrentIsValid);
        }
    }

    /**
     * 修改成验证通过
     */
    function changeCurrentIsValid() {
        if ($(this).val() != null) {
            if ($(this).hasClass("is-invalid")) {
                $(this).removeClass("is-invalid");
            }
        }
    }


    /**
     * 新密码认证
     * @param jqSelectElement
     * @param inValidTextValue
     */
    function newPassChangeIsInValid(jqSelectElement, inValidTextValue) {
        // 判断 invalid-feedvack div 是否存在
        var invalidDiv = jqSelectElement.siblings(".invalid-feedback");
        if (invalidDiv.length > 0) {
            invalidDiv.text(inValidTextValue)
        } else {
            var invalidDivEle = $("<div class='invalid-feedback'></div>").text(inValidTextValue);
            jqSelectElement.after(invalidDivEle);
        }
        if (!jqSelectElement.hasClass("is-invalid")) {
            jqSelectElement.addClass("is-invalid");
            jqSelectElement.unbind("input propertychange", newPassChangeCurrentIsValid);
            jqSelectElement.bind("input propertychange", newPassChangeCurrentIsValid);
        }
    }

    /**
     * 修改成验证通过
     */
    function newPassChangeCurrentIsValid() {
        if ($(this).val() != null && $(this).val().length >= 6) {
            if ($(this).hasClass("is-invalid")) {
                $(this).removeClass("is-invalid");
            }
        }
    }

    /**
     * 修改成验证通过
     */
    function changeSomeOneIsValid(jqSelectElement) {
        if (jqSelectElement.hasClass("is-invalid")) {
            jqSelectElement.removeClass("is-invalid");
        }
    }
});