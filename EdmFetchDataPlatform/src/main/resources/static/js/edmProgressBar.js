$(document).ready(function() {

    init();
    /**
     * 初始化
     */
    function init() {
        setProgressStatus();
    }

    /**
     * 设置进度条的状态
     * active 审核完成
     * waite 等待审核
     * warn 审核失败
     */
    function setProgressStatus() {
        // 获取当前进度的状态值
        var stateVal = $(".container .jumbotron .lead").attr("value");
        console.log(stateVal);
        // 等待申请组审核
        if (stateVal == 0) {
            var applyGroupLi = $(".container .progressBar .applyGroup");
            eleAddClassAndText(applyGroupLi, "waite", "等待申请组组长审核");
        } else if (stateVal == 1) { // 申请组组长审核不通过
            var applyGroupLi = $(".container .progressBar .applyGroup");
            eleRemoveClassAndUpdateText(applyGroupLi, "waite");
            eleAddClassAndText(applyGroupLi, "warn", "申请组组长审核不通过");
        } else if (stateVal == 2) { // 等待能力组审核
            // 申请组审核通过
            var applyGroupLi = $(".container .progressBar .applyGroup");
            eleAddClassAndText(applyGroupLi, "active", "申请组组长审核通过");
            // 等待能力组审核
            var capalityGroupLi = $(".container .progressBar .capalityGroup");
            eleAddClassAndText(capalityGroupLi, "waite", "等待能力组审核");
        } else if (stateVal == 3) { // 能力组审核失败
            // 申请组审核通过
            var applyGroupLi = $(".container .progressBar .applyGroup");
            eleAddClassAndText(applyGroupLi, "active", "申请组组长审核通过");
            // 能力组审核不通过
            var capalityGroupLi = $(".container .progressBar .capalityGroup");
            eleRemoveClassAndUpdateText(capalityGroupLi, "waite");
            eleAddClassAndText(capalityGroupLi, "warn", "能力组审核不通过");
        } else if (stateVal == 4) { // 等待客服组审核
            // 申请组审核通过
            var applyGroupLi = $(".container .progressBar .applyGroup");
            eleAddClassAndText(applyGroupLi, "active", "申请组组长审核通过");
            // 能力组审核通过
            var capalityGroupLi = $(".container .progressBar .capalityGroup");
            eleAddClassAndText(capalityGroupLi, "active", "能力组审核通过");
            // 等待客服组审核
            var customerServerGroupLi = $(".container .progressBar .customerServerGroup");
            eleAddClassAndText(customerServerGroupLi, "waite", "等待客服组审核");
        } else if (stateVal == 5) { // 客服组审核失败
            // 申请组审核通过
            var applyGroupLi = $(".container .progressBar .applyGroup");
            eleAddClassAndText(applyGroupLi, "active", "申请组组长审核通过");
            // 能力组审核通过
            var capalityGroupLi = $(".container .progressBar .capalityGroup");
            eleAddClassAndText(capalityGroupLi, "active", "能力组审核通过");
            // 等待客服组审核
            var customerServerGroupLi = $(".container .progressBar .customerServerGroup");
            eleRemoveClassAndUpdateText(customerServerGroupLi, "waite");
            eleAddClassAndText(customerServerGroupLi, "warn", "客服组审核不通过");
        } else if (stateVal == 6) { // 等待数据组处理
            // 申请组审核通过
            var applyGroupLi = $(".container .progressBar .applyGroup");
            eleAddClassAndText(applyGroupLi, "active", "申请组组长审核通过");
            // 能力组审核通过
            var capalityGroupLi = $(".container .progressBar .capalityGroup");
            eleAddClassAndText(capalityGroupLi, "active", "能力组审核通过");
            // 等待客服组审核
            var customerServerGroupLi = $(".container .progressBar .customerServerGroup");
            eleAddClassAndText(customerServerGroupLi, "active", "客服组审核通过");
            // 等待数据组处理
            var shuJuGroupLi = $(".container .progressBar .shuJuGroup");
            eleAddClassAndText(shuJuGroupLi, "waite", "等待数据组处理");
        } else if (stateVal == 7) { // 等待数据组处理
            // 申请组审核通过
            var applyGroupLi = $(".container .progressBar .applyGroup");
            eleAddClassAndText(applyGroupLi, "active", "申请组组长审核通过");
            // 能力组审核通过
            var capalityGroupLi = $(".container .progressBar .capalityGroup");
            eleAddClassAndText(capalityGroupLi, "active", "能力组审核通过");
            // 等待客服组审核
            var customerServerGroupLi = $(".container .progressBar .customerServerGroup");
            eleAddClassAndText(customerServerGroupLi, "active", "客服组审核通过");
            // 等待数据组处理
            var shuJuGroupLi = $(".container .progressBar .shuJuGroup");
            eleAddClassAndText(shuJuGroupLi, "active", "数据组处理完成");
        }
    }
    /**
     * 添加class
     * @param {选中的元素} ele
     * @param {类值} classValue
     */
    function eleAddClassAndText(ele, classValue, textValue) {
        if (!$(ele).hasClass(classValue)) {
            $(ele).addClass(classValue);
            $(ele).text(textValue);
        }
    }
    /**
     * 移除存在的类值
     * @param {选中的元素} ele
     * @param {类值} classValue
     * @param {文本内容} textValue
     */
    function eleRemoveClassAndUpdateText(ele, classValue) {
        if ($(ele).hasClass(classValue)) {
            $(ele).removeClass(classValue);
        }
    }

})