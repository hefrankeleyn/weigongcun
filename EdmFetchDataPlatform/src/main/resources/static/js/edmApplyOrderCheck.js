$(document).ready(function () {


    init();

    /**
     * 初始化函数
     */
    function init() {
        /**
         * 启动bootstrap的 popover功能
         */
        $('[data-toggle="popover"]').popover();

        // 根据order的状态值和当前当前用户的角色，修改表格数据内容
        updateTableTrByOrderStatueAndCurrentUserRole();

    }

    /**
     * 判断是否有特定的权限
     * @param {指定权限的名称} roleName
     */
    function judgeIfHaveSomeRole(roleName) {
        // 选择被选中的checkbox
        var roleNameCheckBoxs = $(".container #someHiddenValue input[type='checkbox']:checked");
        var continueFlag = false;
        $.each(roleNameCheckBoxs, function (i, roleNameCheckBox) {
            if ($(roleNameCheckBox).val() == roleName) {
                continueFlag = true;
            }
        });
        return continueFlag;
    }

    /**
     * 根据表格的状态修改表单数据
     * 将正在进行的表格样式设置为： table-warning；
     * 将失败的表格样式设置为： table-danger；
     * 将默认的表格样式设置为： table-secondary；
     * 将成功的设置为： table-success
     */
    function updateTableTrByOrderStatueAndCurrentUserRole() {

        // 获取流转单的状态值
        var orderState = $(".container #someHiddenValue input[name='orderState']").val();
        // 判断orderState的值，根据值修改table的内容
        // 等待申请组组长审核
        if (orderState == 0) {
            // 判断是否有申请组组长的权限
            if (judgeIfHaveSomeRole("ROLE_APPLY")) {
                // 展示申请组审核按钮
                updateApplyGroupTr(1, 2);

                // 将样式设置为 table-warning
                var tr = $(".container table .applyGroupTr");
                removeTableTrClass(tr, "table-secondary");
                addTableTrClass(tr, "table-warning");
            }
        } else if (orderState == 1) {
            // 申请组组长审核失败
            // 将tr设置为
            // 将样式设置为 table-danger
            var tr = $(".container table .applyGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-danger");

        } else if (orderState == 2) {
            // 等待能力组审核
            // 判断是否有能力组权限
            if (judgeIfHaveSomeRole("ROLE_CAPACITY")) {
                // 展示申请组审核按钮
                updateCapacityGroupTr(3, 4);
                // 设置当前tr的样式
                var tr = $(".container table .capacityGroupTr");
                removeTableTrClass(tr, "table-secondary");
                addTableTrClass(tr, "table-warning");
            }
            // 将样式设置为
            var tr = $(".container table .applyGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");

        } else if (orderState == 3) {
            // 能力组审核不通过
            // 将样式设置为
            var tr = $(".container table .applyGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");
            var tr = $(".container table .capacityGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-danger");
        } else if (orderState == 4) {
            // 等待客服组审核
            // 判断是否有客服组权限
            if (judgeIfHaveSomeRole("ROLE_CUSTOMER_SERVICE")) {
                // 展示申请组审核按钮
                updateCustomerServiceGroupTr(5, 6);
                // 设置当前tr的样式
                var tr = $(".container table .customerServerGroupTr");
                removeTableTrClass(tr, "table-secondary");
                addTableTrClass(tr, "table-warning");
            }
            // 将样式设置为
            var tr = $(".container table .applyGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");
            var tr = $(".container table .capacityGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");

        } else if (orderState == 5) {
            // 客服组审核不通过
            // 将样式设置为
            var tr = $(".container table .applyGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");
            var tr = $(".container table .capacityGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");
            var tr = $(".container table .customerServerGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-danger");
        } else if (orderState == 6) {
            // 等待数据组处理
        } else if (orderState == 7) {
            // 数据组处理完成
            // 判断是否有客服组权限
            if (judgeIfHaveSomeRole("ROLE_SHUJU")) {
                // 展示申请组审核按钮
                updateShujuGroupTr(8, 7);
                // 设置当前tr的样式
                var tr = $(".container table .shujuGroupTr");
                removeTableTrClass(tr, "table-secondary");
                addTableTrClass(tr, "table-warning");

            }
            // 将样式设置为
            var tr = $(".container table .applyGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");
            var tr = $(".container table .capacityGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");
            var tr = $(".container table .customerServerGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");
        } else if (orderState == 8) {
            var tr = $(".container table .applyGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");
            var tr = $(".container table .capacityGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");
            var tr = $(".container table .customerServerGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-success");

            var tr = $(".container table .shujuGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-danger");
        }
    }


    /**
     * 移除某一个样式
     * @param {通过jquery选中的元素} trJqElement
     * @param {class 的名字} className
     */
    function removeTableTrClass(trJqElement, className) {
        if (trJqElement.hasClass(className)) {
            trJqElement.removeClass(className);
        }
    }

    /**
     * 添加某一个样式
     * @param {通过jquery选中的元素} trJqElement
     * @param {class 的名字} className
     */
    function addTableTrClass(trJqElement, className) {
        if (!trJqElement.hasClass(className)) {
            trJqElement.addClass(className);
        }
    }

    /**
     * 更改添加备注的单元格
     */
    function updateBeiZhuTd() {
        // 备注
        var beiZhuTd = $(".container table #endTr td:nth-child(2)");
        addTextareaElement(beiZhuTd, "endBeiZhu");

        // 设置备注的样式
        var endTr = $(".container table .endTr");
        removeTableTrClass(endTr, "table-secondary");
        addTableTrClass(endTr, "table-warning");
    }


    /**
     * 修改数据组的tr
     */
    function updateShujuGroupTr(concalOrderState, successOrderState) {

        // 添加撤销流转的按钮
        var firstTd = $(".container table #shujuGroupFirstTr td:nth-child(1)");
        addConcalLiuZhuanButton(firstTd);
        // 为最后一个td添加 select 下拉选项和button按钮
        var fifthTd = $(".container table #shujuGroupFirstTr td:nth-child(5)");
        // 为最后一个td添加 select
        // 查询所有运营者权限的用户
        addSendEmailLabelAndSelectElement(fifthTd, "ROLE_OPERATION");
        // 添加隐藏input
        addOrderStateInput(firstTd, concalOrderState);
        // 添加隐藏input
        // 添加隐藏input
        addOrderStateInput(fifthTd, successOrderState);
        // 添加 button按钮
        var subButtonInnerSpan = fifthTd.children(".sendEmailSpan");
        addSubButtonForChangeOrderResult(fifthTd, subButtonInnerSpan);
        // 添加其他内容 实际用户数据属性说明
        var beiZhuTd = $(".container table #shujuGroupSecondTr td:nth-child(2)");
        addTextareaElement(beiZhuTd, "dataUsersDescription");

        // 用户数据连接
        var userDataLinkTd = $(".container table #shujuGroupSecondTr td:nth-child(1)");
        addReadOnlyInputElement(userDataLinkTd, "dataCode");

        // 实际用户数量
        var userNumTd = $(".container table #shujuGroupSecondTr td:nth-child(3)");
        addReadOnlyInputElement(userNumTd, "actualUserNum");


        // 备注
        updateBeiZhuTd();

    }

    /**
     * 修改客服组的tr
     */
    function updateCustomerServiceGroupTr(concalOrderState, successOrderState) {

        // 添加撤销流转的按钮
        var firstTd = $(".container table #customerServerGroupFirstTr td:nth-child(1)");

        // 为最后一个td添加 select 下拉选项和button按钮
        var fifthTd = $(".container table #customerServerGroupFirstTr td:nth-child(5)");
        // 为最后一个td添加 select
        addSendEmailLabelAndSelectElement(fifthTd, "ROLE_SHUJU");
        // 添加隐藏input
        addOrderStateInput(firstTd, concalOrderState);
        // 添加隐藏input
        addOrderStateInput(fifthTd, successOrderState);
        // 添加 button按钮
        var subButtonInnerSpan = fifthTd.children(".sendEmailSpan");
        var sendFailEmailSpan = fifthTd.children(".sendFailEmail");
        addSubButtonForChangeOrderResult(fifthTd, subButtonInnerSpan);
        // 将失败按钮添加到第一栏
        addLiuZhuanFailButton(firstTd, sendFailEmailSpan);
        // 移除失败按钮
        fifthTd.children(".sendFailEmail").remove();

        // 排期确认
        var pqiQueRenTd = $(".container table #customerServerGroupSecondTr td:nth-child(1)");
        addInputRadios(pqiQueRenTd, "paiQiQueRenStatue")
        // 群发方案确认
        var QunFaFangAnRenTd = $(".container table #customerServerGroupSecondTr td:nth-child(2)");
        addInputRadios(QunFaFangAnRenTd, "qunFaFangAnQueRenState")

        // 添加其他内容
        var beiZhuTd = $(".container table #customerServerGroupSecondTr td:nth-child(3)");
        addTextareaElement(beiZhuTd, "thirdCheckBeiZhu");
        // 备注
        updateBeiZhuTd();
    }

    /**
     * 修改能力组的tr
     */
    function updateCapacityGroupTr(concalOrderState, successOrderState) {
        // 添加撤销流转的按钮
        var capacityFirstTd = $(".container table #capacityGroupFirstTr td:nth-child(1)");
        addConcalLiuZhuanButton(capacityFirstTd);
        // 添加隐藏input
        addOrderStateInput(capacityFirstTd, concalOrderState);
        // 为最后一个td添加 select 下拉选项和button按钮
        var fifthTd = $(".container table #capacityGroupFirstTr td:nth-child(5)");
        // 查询具有客服组权限的用户，并将用户信息添加到option中
        addSendEmailLabelAndSelectElement(fifthTd, "ROLE_CUSTOMER_SERVICE");
        // 添加 button按钮
        var subButtonInnerSpan = fifthTd.children(".sendEmailSpan");
        addSubButtonForChangeOrderResult(fifthTd, subButtonInnerSpan);
        // 添加隐藏input
        addOrderStateInput(fifthTd, successOrderState);

        // 修改 排期结果
        // 修改内容初审的值
        var pqiQiTd = $(".container table #capacityGroupSecondTr td:nth-child(1)");
        addInputElement(pqiQiTd, "paiQiResult");

        // 修改内容复审
        var pqiQiTd = $(".container table #capacityGroupSecondTr td:nth-child(2)");
        // addInputRadios(pqiQiTd, "capacityCheckStatue");
        addInputRadiosTongGuoOrNot(pqiQiTd, "capacityCheckStatue");
        // 备注
        updateBeiZhuTd();

    }


    /**
     * 修改申请组的tr
     */
    function updateApplyGroupTr(concalOrderState, successOrderState) {
        // 添加撤销流转的按钮
        var firstTd = $(".container table #applyGroupFirstTr td:nth-child(1)");
        addConcalLiuZhuanButton(firstTd);
        // 添加隐藏input，里面存放orderState
        addOrderStateInput(firstTd, concalOrderState);

        // 为最后一个td添加 select 下拉选项和button按钮
        var fifthTd = $(".container table #applyGroupFirstTr td:nth-child(5)");
        // 查询权限为： ROLE_CAPACITY 的所有用户，并将用户信息放入到option中
        addSendEmailLabelAndSelectElement(fifthTd, "ROLE_CAPACITY");
        // 添加 button按钮
        var subButtonInnerSpan = fifthTd.children(".sendEmailSpan");
        addSubButtonForChangeOrderResult(fifthTd, subButtonInnerSpan);

        // 添加隐藏input，里面存放orderState
        addOrderStateInput(fifthTd, successOrderState);

        // 修改内容初审的值
        var neiRongChuShenTd = $(".container table #applyGroupSecondTr td:nth-child(1)");
        // 将修改名字的样式注释掉，暂时不支持修改内容初审的名字，内容初审是在提交申请时确认
        // addInputElement(neiRongChuShenTd, "firstCheckerUserName");
        // 备注
        updateBeiZhuTd();

    }

    /**
     * 添加orderState Input元素
     * @param trJqElement
     * @param orderStateValue
     */
    function addOrderStateInput(trJqElement, orderStateValue) {
        var concalInput = $("<input type='hidden' name='orderState'>").attr("value", orderStateValue);
        trJqElement.append(concalInput);
    }

    /**
     * 添加radio
     * @param {通过jquery获取到的td元素} tdJqElement
     * @param {input 元素的name属性值} inputName
     */
    function addInputRadios(tdJqElement, inputName) {
        var successIdValue = inputName + "Success";
        var failIdValue = inputName + "Fail";
        // 添加选择成功的radio
        var divOne = $("<div class='custom-control custom-radio custom-control-inline mt-2'></div>");
        var inputRadioSuccess = $("<input type='radio' class='custom-control-input success-radio'>")
            .attr("name", inputName)
            .attr("value", 2)
            .attr("id", successIdValue);
        var raidoLabelSuc = $("<label class='custom-control-label'>确认</label>").attr("for", successIdValue);
        divOne.append(inputRadioSuccess);
        divOne.append(raidoLabelSuc);
        var divTwo = $("<div class='custom-control custom-radio custom-control-inline'></div>");
        // 添加 选择失败的radio
        var inputRadioFail = $("<input type='radio' class='custom-control-input fail-radio'>")
            .attr("name", inputName)
            .attr("value", 3)
            .attr("id", failIdValue);
        var raidoLabelFail = $("<label class='custom-control-label'>取消</label>").attr("for", failIdValue);
        divTwo.append(inputRadioFail);
        divTwo.append(raidoLabelFail);
        tdJqElement.append(divOne);
        tdJqElement.append(divTwo);
        // 对该点选框添加监听事件
        tdJqElement.children("div").children("input[type='radio']").unbind("click", buttionDisableEvent);
        tdJqElement.children("div").children("input[type='radio']").bind("click", buttionDisableEvent);
    }

    /**
     * 添加radio
     * @param {通过jquery获取到的td元素} tdJqElement
     * @param {input 元素的name属性值} inputName
     */
    function addInputRadiosTongGuoOrNot(tdJqElement, inputName) {
        var successIdValue = inputName + "Success";
        var failIdValue = inputName + "Fail";
        // 添加选择成功的radio
        var divOne = $("<div class='custom-control custom-radio custom-control-inline mt-2'></div>");
        var inputRadioSuccess = $("<input type='radio' class='custom-control-input success-radio'>")
            .attr("name", inputName)
            .attr("value", 0)
            .attr("id", successIdValue);
        var raidoLabelSuc = $("<label class='custom-control-label'>通过</label>").attr("for", successIdValue);
        divOne.append(inputRadioSuccess);
        divOne.append(raidoLabelSuc);
        var divTwo = $("<div class='custom-control custom-radio custom-control-inline'></div>");
        // 添加 选择失败的radio
        var inputRadioFail = $("<input type='radio' class='custom-control-input fail-radio'>")
            .attr("name", inputName)
            .attr("value", 1)
            .attr("id", failIdValue);
        var raidoLabelFail = $("<label class='custom-control-label'>不通过</label>").attr("for", failIdValue);
        divTwo.append(inputRadioFail);
        divTwo.append(raidoLabelFail);
        tdJqElement.append(divOne);
        tdJqElement.append(divTwo);
        // 对该点选框添加监听事件
        tdJqElement.children("div").children("input[type='radio']").unbind("click", buttionDisableEvent);
        tdJqElement.children("div").children("input[type='radio']").bind("click", buttionDisableEvent);
    }


    /**
     * 按钮失效事件
     */
    function buttionDisableEvent() {
        if ($(this).hasClass("fail-radio")) {
            // 让提交按钮不可用
            $(this).parent().parent().parent("tr")
                .prev("tr").children("td").children(".subToUpdateEdmApplyOrderResult").prop("disabled", true);
        } else {
            // 判断同一个tr下，其他td下 是否取消被选中
            var sameTrRadios = $(this).parent().parent().parent("tr")
                .children("td").children("div").children("input[type='radio']:checked");
            var flag = true;
            for (var i = 0; i < sameTrRadios.length; i++) {
                if ($(sameTrRadios[i]).hasClass("fail-radio")) {
                    flag = false;
                }
            }
            if (flag) {
                // 检查按钮上的select是否为空
                var sumitbutton = $(this).parent().parent().parent("tr")
                    .prev("tr").children("td").children(".subToUpdateEdmApplyOrderResult");
                var options = sumitbutton.siblings("select").children("option");
                if (options.length > 0) {
                    $(this).parent().parent().parent("tr")
                        .prev("tr").children("td").children(".subToUpdateEdmApplyOrderResult").prop("disabled", false);
                }
            }
        }
    }

    /**
     * 添加 <input> 元素
     * @param {通过jquery获取到的td元素} tdJqElement
     * @param {input元素name值} inputName
     */
    function addInputElement(tdJqElement, inputName) {
        var tdTextValue = tdJqElement.text();
        var inputElement = $("<input class='form-control'>")
            .attr("name", inputName)
            .attr("value", tdTextValue);
        // 删除td里面的内容
        tdJqElement.text("");
        tdJqElement.append(inputElement);
    }

    /**
     * 添加只读input元素
     * @param {通过jquery获取到的td元素} tdJqElement
     * @param {input元素name值} inputName
     */
    function addReadOnlyInputElement(tdJqElement, inputName) {
        var tdTextValue = tdJqElement.text();
        var inputElement = $("<input class='form-control' readonly='true'>")
            .attr("name", inputName)
            .attr("value", tdTextValue);
        // 删除td里面的内容
        tdJqElement.text("");
        tdJqElement.append(inputElement);
    }


    /**
     * 添加 <textarea> 元素
     * @param {通过jquery获取到的td元素} tdJqElement
     * @param {textArea元素的name属性值} textAreaName
     */
    function addTextareaElement(tdJqElement, textAreaName) {
        var tdTextValue = tdJqElement.text();
        var textAreaElement = $("<textarea class='form-control' rows='2' cols='50' id='beiZhu'></textarea>")
            .attr("name", textAreaName)
            .text(tdTextValue);
        // 删除td里面的内容
        tdJqElement.text("");
        tdJqElement.append(textAreaElement);
    }

    /**
     * 添加提交改变流转单结果的按钮
     */
    function addSubButtonForChangeOrderResult(tdJqElement, childSpan) {
        // 添加button按钮
        var subToUpdateEdmApplyOrderResultButton = $("<button type='button' class='btn btn-success mt-1 subToUpdateEdmApplyOrderResult'></button>");
        // 判断是否有select元素，如果有判断是否有子元素，如果没有子元素将按钮置为不可用
        var selectElement = childSpan.siblings("select");
        if (selectElement.length > 0) {
            var optionElements = selectElement.children("option");
            if (optionElements.length == 0) {
                subToUpdateEdmApplyOrderResultButton.prop("disabled", true);
            }
        }

        childSpan.wrap(subToUpdateEdmApplyOrderResultButton);
        // 为button添加点击事件
        tdJqElement.children(".subToUpdateEdmApplyOrderResult").unbind("click", submitToUpdateOrderResult);
        tdJqElement.children(".subToUpdateEdmApplyOrderResult").bind("click", submitToUpdateOrderResult);
    }

    /**
     * 添加用户选定发送邮件对象的 label和select元素
     * @param {jquery选中的td元素} tdJqElement
     * @param {权限名称} roleName
     */
    function addSendEmailLabelAndSelectElement(tdJqElement, roleName) {
        var applyGroupLabel = $("<label for='applyGroupLeaderEmail'>邮件发送给：</label>");
        var applyGroupSelect = $("<select id='applyGroupLeaderEmail' name='applyGroupLeaderEmail' class='form-control'></select>");

        // 根据权限查询查询用户，并将用户信息添加到 select中
        // addSelectByRoleAndGroup("ROLE_CAPACITY", applyGroupSelect);
        addSelectByRoleAndGroup(roleName, applyGroupSelect);
        // 添加select
        tdJqElement.prepend(applyGroupSelect);
        // 添加lable
        tdJqElement.prepend(applyGroupLabel);
    }

    /**
     * 添加撤销流转表单
     */
    function addConcalLiuZhuanButton(tdJqElement) {
        var cancelLiuZhuanA = $("<button type='button' class='btn btn-danger cancelLiuZhuan'>撤销流转</button>");
        tdJqElement.append(cancelLiuZhuanA);

        // 绑定点击事件，当点击的时候，创建form表单，提交修改订单结果的信息
        cancelLiuZhuanA.unbind("click", submitToUpdateOrderResult);
        cancelLiuZhuanA.bind("click", submitToUpdateOrderResult);
    }

    /**
     * 添加 流转失败按钮
     * @param {jquery 选中的td元素} tdJqElement
     * @param {span元素} spanElement
     */
    function addLiuZhuanFailButton(tdJqElement, spanElement) {
        var cancelLiuZhuanA = $("<button type='button' class='btn btn-danger mt-1 cancelLiuZhuan'></button>")
            .append(spanElement);
        tdJqElement.append(cancelLiuZhuanA);

        // 绑定点击事件，当点击的时候，创建form表单，提交修改订单结果的信息
        cancelLiuZhuanA.unbind("click", submitToUpdateOrderResult);
        cancelLiuZhuanA.bind("click", submitToUpdateOrderResult);
    }

    /**
     * 根据权限和用户组获取用户
     */
    function addSelectByRoleAndGroup(roleName, selectJqElment) {
        // selectJqElment.append("<option value='liangnan@wo.cn'>梁南</option>");

        var url = $.projectRootUrl() + "/edmerController/findEdmerListByRole";
        // 获取 token
        var token = $(".container #someHiddenValue input[name='_csrf']").val();
        var headers = {"X-CSRF-TOKEN": token};
        var data = {"roleName": roleName};
        // 设置为同步
        /*
        async: false 设置ajax为同步，请求前，后面的js代码不执行
         */
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            headers: headers,
            async: false,
            success: function (response) {
                // 结果的状态
                var status = response.status;
                if (status == 0) {
                    var result = response.result;
                    for (var i = 0; i < result.length; i++) {
                        var userName = result[i].username;
                        var email = result[i].email;
                        var option = $("<option></option>").attr("value", email).text(userName);
                        selectJqElment.append(option);
                    }
                } else {
                    console.error("edmerList is empty.");
                }
            }
        });
    }


    /**
     * 创建form表单，提交信息，用于修改订单审核状态信息
     *
     * 在点击 “撤销流转” 按钮的时候，要检查备注说明是否为空，如果备注说明不为空就提交form表单，否则提醒用户填写备注
     */
    function submitToUpdateOrderResult() {

        // 获取当前流转单的id
        var oid = $(".container #someHiddenValue input[name='oid']").val();
        // 获取下一步流转单的状态
        var orderState = $(this).siblings("input:hidden[name='orderState']").val();
        var data = {"oid": oid, "orderState": orderState};
        var beiZhuTd = $(".container table #endTr td:nth-child(2)");
        // 为备注添加 is-valid  类
        var endTextarea = beiZhuTd.children("textarea[name='endBeiZhu']");
        var endTextareaText = endTextarea.val();
        // 为data添加
        data.endBeiZhu = endTextareaText;
        // 获取当前审核人的邮箱
        var currentUserEmail = $(".container #someHiddenValue input[name='currentUserEmail']").val();
        //当前审核人的姓名
        var currentUserName = $(".container #someHiddenValue input[name='currentUserName']").val();
        data.currentUserEmail = currentUserEmail;
        data.currentUserName = currentUserName;
        // 获取收件人的邮箱
        var emailToSelect = $(this).siblings("select").children("option:selected");
        if (emailToSelect.length == 1) {
            var emailTo = emailToSelect.val();
            var emailToUserName = emailToSelect.text();
            data.emailTo = emailTo;
            data.emailToUserName = emailToUserName;
        }
        // 获取当前的按钮的父元素元素
        var trElement = $(this).parent().parent("tr");
        if (trElement.hasClass("applyGroupTr")) {
            // 只有在 点击的是取消流转按钮并且，备注为空的情况下，才不让提交
            if (!($(this).hasClass("cancelLiuZhuan") && validBeiZhuIfIsEmpty())) {
                // 创建form表单并且提交form表单
                createFormAndSubmitForm(data);
            }

        } else if (trElement.hasClass("capacityGroupTr")) {
            // 只有在 点击的是取消流转按钮并且，备注为空的情况下，才不让提交
            if (!($(this).hasClass("cancelLiuZhuan") && validBeiZhuIfIsEmpty())) {
                // 点击取消流转，结束for循环
                if ($(this).hasClass("cancelLiuZhuan")) {
                    // 创建form表单并且提交form表单
                    createFormAndSubmitForm(data);
                } else {
                    // 获取兄弟节点 capacityGroupTr
                    var siblingCapacityGroupTr = trElement.siblings(".capacityGroupTr");
                    // 获取排期结果
                    var paiQiResultInput = siblingCapacityGroupTr.children("td").children("input[name='paiQiResult']");
                    var paiQiResult = paiQiResultInput.val();
                    var capacityCheckStatueRadioDiv = siblingCapacityGroupTr.children("td")
                        .children(".custom-radio");
                    var capacityCheckStatueRadio = capacityCheckStatueRadioDiv.children("input[name='capacityCheckStatue']:checked");

                    if (paiQiResult != "" && capacityCheckStatueRadio.length == 1) {
                        // 获取排期结果
                        data.paiQiResult = paiQiResult;
                        // 获取内容复审
                        data.capacityCheckStatue = capacityCheckStatueRadio.attr("value");
                        // 创建form表单并且提交form表单
                        createFormAndSubmitForm(data);
                    } else {
                        // 为 input和radio添加验证
                        if (paiQiResult == "") {
                            addIsInValidClass(paiQiResultInput);
                        }
                        // radio添加验证
                        if (capacityCheckStatueRadio.length != 1) {
                            var radios = capacityCheckStatueRadioDiv.children("input[name='capacityCheckStatue']");
                            addIsInValidClassForRadio(radios);
                            // 监听 radio，只要有选中就改变样式
                            radios.unbind("click", radioRemoveIsInvalidClass);
                            radios.bind("click", radioRemoveIsInvalidClass);
                        }
                    }
                }
            }
        } else if (trElement.hasClass("customerServerGroupTr")) {
            // 只有在 点击的是取消流转按钮并且，备注为空的情况下，才不让提交
            if (!($(this).hasClass("cancelLiuZhuan") && validBeiZhuIfIsEmpty())) {
                // 点击取消流转，结束for循环
                if ($(this).hasClass("cancelLiuZhuan")) {
                    // 创建form表单并且提交form表单
                    createFormAndSubmitForm(data);
                } else {
                    // 获取兄弟节点 customerServerGroupTr
                    var siblingTr = trElement.siblings(".customerServerGroupTr");
                    var radioParentDiv = siblingTr.children("td")
                        .children(".custom-radio");
                    // 排期确认
                    var paiQiQueRenStatueRadio = radioParentDiv.children("input[name='paiQiQueRenStatue']:checked");
                    // 群发方案确认
                    var qunFaFangAnQueRenStateRadio = radioParentDiv.children("input[name='qunFaFangAnQueRenState']:checked");
                    //  验证两个radio
                    if (paiQiQueRenStatueRadio.length == 1 && qunFaFangAnQueRenStateRadio.length == 1) {
                        // 创建form表单并且提交form表单
                        // 给data添加 排期确认
                        data.paiQiQueRenStatue = paiQiQueRenStatueRadio.attr("value");
                        data.qunFaFangAnQueRenState = qunFaFangAnQueRenStateRadio.attr("value");
                        // 添加
                        data.thirdCheckBeiZhu = siblingTr.children("td").children("textarea[name='thirdCheckBeiZhu']").val();
                        createFormAndSubmitForm(data);
                    } else {
                        // radio添加验证
                        if (paiQiQueRenStatueRadio.length != 1) {
                            var radios = radioParentDiv.children("input[name='paiQiQueRenStatue']");
                            addIsInValidClassForRadio(radios);
                            // 监听 radio，只要有选中就改变样式
                            radios.unbind("click", radioRemoveIsInvalidClass);
                            radios.bind("click", radioRemoveIsInvalidClass);
                        }
                        if (qunFaFangAnQueRenStateRadio.length != 1) {
                            var radios = radioParentDiv.children("input[name='qunFaFangAnQueRenState']");
                            addIsInValidClassForRadio(radios);
                            // 监听 radio，只要有选中就改变样式
                            radios.unbind("click", radioRemoveIsInvalidClass);
                            radios.bind("click", radioRemoveIsInvalidClass);
                        }
                    }
                }
            }
        } else if (trElement.hasClass("shujuGroupTr")) {
            // 只有在 点击的是取消流转按钮并且，备注为空的情况下，才不让提交
            if (!($(this).hasClass("cancelLiuZhuan") && validBeiZhuIfIsEmpty())) {
                // 点击取消流转，结束for循环
                if ($(this).hasClass("cancelLiuZhuan")) {
                    // 创建form表单并且提交form表单
                    createFormAndSubmitForm(data);
                } else {
                    // 获取兄弟节点 shujuGroupTr table-warning
                    var siblingTr = trElement.siblings(".shujuGroupTr");
                    // 用户数据链接
                    var dataCodeInput = siblingTr.children("td").children("input[name='dataCode']");
                    var dataCode = dataCodeInput.val();
                    // 实际用户数量
                    var actualUserNumInput = siblingTr.children("td").children("input[name='actualUserNum']");
                    var actualUserNum = actualUserNumInput.val();
                    // 用户数据连接和 世界用户数量不能为空
                    if (dataCode != "" && actualUserNum != "") {
                        // 获取 实际用户数据属性说明
                        var dataUsersDescriptionTextArea = siblingTr.children("td").children("textarea[name='dataUsersDescription']");
                        data.dataUsersDescription = dataUsersDescriptionTextArea.val();
                        createFormAndSubmitForm(data);
                    } else {
                        // 为 input
                        if (dataCode == "") {
                            addIsInValidClass(dataCodeInput);
                        }
                        if (actualUserNum == "") {
                            addIsInValidClass(actualUserNumInput);
                        }
                    }
                }
            }
        }
        // console.log("data: " + JSON.stringify(data));
    }

    /**
     * 移除radio 上 is-invalid 样式
     */
    function radioRemoveIsInvalidClass() {
        var radios = $(this).parent().parent().children("div").children("input[type='radio']");
        for (var i = 0; i < radios.length; i++) {
            if ($(radios[i]).hasClass("is-invalid")) {
                $(radios[i]).removeClass("is-invalid");
            }
        }
    }


    /**
     * 添加is-invalid class
     * @param {jquery 选中的元素} jqElement
     */
    function addIsInValidClass(jqElement) {
        if (jqElement.hasClass("is-valid")) {
            jqElement.removeClass("is-valid");
        }
        if (!jqElement.hasClass("is-invalid")) {
            // 添加 invalid-feedback
            var invalidFeedback = $("<div class='invalid-feedback'>不能为空</div>");
            if (jqElement.siblings(".invalid-feedback").length == 0) {
                jqElement.after(invalidFeedback);
            }
            // 添加验证的样式
            jqElement.addClass("is-invalid");
        }
    }

    /**
     *
     * @param {jquery 选中的元素} jqElement
     */
    function addIsInValidClassForRadio(jqElement) {
        if (jqElement.hasClass("is-valid")) {
            jqElement.removeClass("is-valid");
        }
        if (!jqElement.hasClass("is-invalid")) {
            // 添加验证的样式
            jqElement.addClass("is-invalid");
        }
    }

    /**
     * 检查备注是否为空
     * 如果为空返回 true
     * 不为空 返回false
     */
    function validBeiZhuIfIsEmpty() {
        // 检查备注是否为空，如果为空，提醒用户添加备注信息
        // 备注
        var beiZhuTd = $(".container table #endTr td:nth-child(2)");
        // 为备注添加 is-valid  类
        var endTextarea = beiZhuTd.children("textarea[name='endBeiZhu']");
        var endTextareaText = endTextarea.val();
        if (endTextareaText == "") {
            if (endTextarea.hasClass("is-valid")) {
                endTextarea.removeClass("is-valid");
            }

            if (!endTextarea.hasClass("is-invalid")) {
                // 添加 invalid-feedback
                var invalidFeedback = $("<div class='invalid-feedback'>不能为空</div>");
                if (endTextarea.siblings(".invalid-feedback").length == 0) {
                    endTextarea.after(invalidFeedback);
                }
                // 添加验证的样式
                endTextarea.addClass("is-invalid");
                // 为备注绑定一个监听事件，只要内容发生变化，就改变样式
                endTextarea.unbind("input propertychange", changeValid);
                endTextarea.bind("input propertychange", changeValid);
            }
            return true;
        } else {
            if (endTextarea.hasClass("is-invalid")) {
                endTextarea.removeClass("is-invalid");
                endTextarea.addClass("is-valid");
            }
            return false;
        }
    }

    /**
     * 改变备注textarea验证的样式
     */
    function changeValid() {
        var beiZhuTd = $(".container table #endTr td:nth-child(2)");
        // 为备注添加 is-valid  类
        var endTextarea = beiZhuTd.children("textarea[name='endBeiZhu']");
        var endTextareaText = endTextarea.val();
        var endTextareaText = endTextarea.val();
        if (endTextareaText != "") {
            if (endTextarea.hasClass("is-invalid")) {
                endTextarea.removeClass("is-invalid");
                endTextarea.addClass("is-valid");
            }
        }
    }

    /**
     * 创建form 并且提交form
     */
    function createFormAndSubmitForm(jsonData) {
        var url = $.projectRootUrl() + "/edmApplyOrderCheckResultController/updateEdmApplyOrderCheckResult";
        // 创建 form
        var updateOrderResultForm = $("<form class='updateEdmOrderResult' method='POST'></form>").attr("action", url);
        // 添加 _rsft
        var token = $(".container #someHiddenValue input[name='_csrf']")
        updateOrderResultForm.append(token);


        $.each(jsonData, function (key, value) {
            // console.log("key: " + key);
            // console.log("value: " + value);
            if (value != "") {
                // 将值添加表单中
                var inputElement = $("<input type='hidden'>").attr("name", key).attr("value", value);
                updateOrderResultForm.append(inputElement);
            }
        });

        var form = $("body .updateEdmOrderResult");
        if (form.length > 0) {
            form.remove();
        }
        // 将表单添加页面上
        $("body").append(updateOrderResultForm);

        //触发表单提交事件
        /*updateOrderResultForm.submit(function(e) {
            console.log("updateApplyOrder form submit..");
            // 阻止表单提交
            e.preventDefault();
        });*/

        // 提交表单
        updateOrderResultForm.submit();

    }

});