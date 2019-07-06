$(document).ready(function() {


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
        $.each(roleNameCheckBoxs, function(i, roleNameCheckBox) {
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
                updateApplyGroupTr();

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
                updateCapacityGroupTr();
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
                updateCustomerServiceGroupTr();
                console.log("ROLE_CUSTOMER_SERVICE ...");
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
                updateShujuGroupTr();
                // 设置当前tr的样式
                var tr = $(".container table .shujuGroupTr");
                var endTr = $(".container table .endTr");
                removeTableTrClass(tr, "table-secondary");
                addTableTrClass(tr, "table-warning");
                removeTableTrClass(endTr, "table-secondary");
                addTableTrClass(endTr, "table-warning");
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
     * 修改数据组的tr
     */
    function updateShujuGroupTr() {
        // 为最后一个td添加 select 下拉选项和button按钮
        var fifthTd = $(".container table #shujuGroupFirstTr td:nth-child(5)");
        // 为最后一个td添加 select
        addSendEmailLabelAndSelectElement(fifthTd);
        // 添加 button按钮
        var subButtonInnerSpan = fifthTd.children(".sendEmailSpan");
        addSubButtonForChangeOrderResult(fifthTd, subButtonInnerSpan);
        // 添加其他内容
        var beiZhuTd = $(".container table #shujuGroupSecondTr td:nth-child(2)");
        addTextareaElement(beiZhuTd, "dataUsersDescription");
        // 备注
        var beiZhuTd = $(".container table #endTr td:nth-child(2)");
        addTextareaElement(beiZhuTd, "ednBeiZhu");

    }

    /**
     * 修改可辅助组的tr
     */
    function updateCustomerServiceGroupTr() {

        // 添加撤销流转的按钮
        var firstTd = $(".container table #customerServerGroupFirstTr td:nth-child(1)");

        // 为最后一个td添加 select 下拉选项和button按钮
        var fifthTd = $(".container table #customerServerGroupFirstTr td:nth-child(5)");
        // 为最后一个td添加 select
        addSendEmailLabelAndSelectElement(fifthTd);
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
    }

    /**
     * 修改能力组的tr
     */
    function updateCapacityGroupTr() {
        // 添加撤销流转的按钮
        var capacityFirstTd = $(".container table #capacityGroupFirstTr td:nth-child(1)");
        addConcalLiuZhuanButton(capacityFirstTd);
        // 为最后一个td添加 select 下拉选项和button按钮
        var fifthTd = $(".container table #capacityGroupFirstTr td:nth-child(5)");
        addSendEmailLabelAndSelectElement(fifthTd);
        // 添加 button按钮
        var subButtonInnerSpan = fifthTd.children(".sendEmailSpan");
        addSubButtonForChangeOrderResult(fifthTd, subButtonInnerSpan);

        // 修改 排期结果
        // 修改内容初审的值
        var pqiQiTd = $(".container table #capacityGroupSecondTr td:nth-child(1)");
        addInputElement(pqiQiTd, "paiQiResult");

        // 修改内容复审
        var pqiQiTd = $(".container table #capacityGroupSecondTr td:nth-child(2)");
        addInputRadios(pqiQiTd, "capacityCheckStatue")

    }




    /**
     * 修改申请组的tr
     */
    function updateApplyGroupTr() {
        // 添加撤销流转的按钮
        var firstTd = $(".container table #applyGroupFirstTr td:nth-child(1)");
        addConcalLiuZhuanButton(firstTd);

        // 为最后一个td添加 select 下拉选项和button按钮
        var fifthTd = $(".container table #applyGroupFirstTr td:nth-child(5)");
        addSendEmailLabelAndSelectElement(fifthTd);
        // 添加 button按钮
        var subButtonInnerSpan = fifthTd.children(".sendEmailSpan");
        addSubButtonForChangeOrderResult(fifthTd, subButtonInnerSpan);

        // 修改内容初审的值
        var neiRongChuShenTd = $(".container table #applyGroupSecondTr td:nth-child(1)");
        addInputElement(neiRongChuShenTd, "firstCheckerUserName");

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
        var inputRadioSuccess = $("<input type='radio' class='custom-control-input'>")
            .attr("name", inputName)
            .attr("value", 0)
            .attr("id", successIdValue);
        var raidoLabelSuc = $("<label class='custom-control-label'>通过</label>").attr("for", successIdValue);
        divOne.append(inputRadioSuccess);
        divOne.append(raidoLabelSuc);
        var divTwo = $("<div class='custom-control custom-radio custom-control-inline'></div>");
        // 添加 选择失败的radio
        var inputRadioFail = $("<input type='radio' class='custom-control-input'>")
            .attr("name", inputName)
            .attr("value", 1)
            .attr("id", failIdValue);
        var raidoLabelFail = $("<label class='custom-control-label'>不通过</label>").attr("for", failIdValue);
        divTwo.append(inputRadioFail);
        divTwo.append(raidoLabelFail);
        tdJqElement.append(divOne);
        tdJqElement.append(divTwo);
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
        childSpan.wrap(subToUpdateEdmApplyOrderResultButton);
        // 为button添加点击事件
        tdJqElement.children(".subToUpdateEdmApplyOrderResult").unbind("click", submitToUpdateOrderResult);
        tdJqElement.children(".subToUpdateEdmApplyOrderResult").bind("click", submitToUpdateOrderResult);
    }

    /**
     * 添加用户选定发送邮件对象的 label和select元素
     * @param {jquery选中的td元素} tdJqElement
     */
    function addSendEmailLabelAndSelectElement(tdJqElement) {
        var applyGroupLabel = $("<label for='applyGroupLeaderEmail'>邮件发送给：</label>");
        var applyGroupSelect = $("<select id='applyGroupLeaderEmail' name='applyGroupLeaderEmail' class='form-control'></select>");

        // 根据权限查询查询用户，并将用户信息添加到 select中
        addSelectByRoleAndGroup("ROLE_CAPACITY", applyGroupSelect);
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
        selectJqElment.append("<option value='liangnan@wo.cn'>梁南</option>")
    }





    /**
     * 创建form表单，提交信息，用于修改订单审核状态信息
     */
    function submitToUpdateOrderResult() {
        console.log("submit update order result..");
    }

});