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

        /**
         * 根据流转单的状态更新流转单的样式
         */
        updateTableTrByOrderStatue();

    }

    /**
     * 根据流转单的状态更新流转单表格的样式
     */
    function updateTableTrByOrderStatue() {
        // 获取流转单的状态值
        var orderState = $(".container #someHiddenValue input[name='orderState']").val();
        // 判断orderState的值，根据值修改table的内容
        // 等待申请组组长审核
        if (orderState == 0) {
            // 将正在进行的tr 样式设置为 table-warning
            var tr = $(".container table .applyGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-warning");
            // 设置备注的样式
            updateBeiZhuTd();
        } else if (orderState == 1) {
            // 申请组组长审核失败
            // 将tr设置为
            // 将样式设置为 table-danger
            var tr = $(".container table .applyGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-danger");

        } else if (orderState == 2) {
            // 等待能力组审核
            // 设置当前tr的样式
            var tr = $(".container table .capacityGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-warning");
            // 设置备注的样式
            updateBeiZhuTd();
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
            // 设置当前tr的样式
            var tr = $(".container table .customerServerGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-warning");
            // 设置备注的样式
            updateBeiZhuTd();
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
            var tr = $(".container table .shujuGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-warning");

            // 设置备注的样式
            updateBeiZhuTd();
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
        } else if (orderState == 7) {
            // 数据组处理完成
            // 设置当前tr的样式
            var tr = $(".container table .shujuGroupTr");
            removeTableTrClass(tr, "table-secondary");
            addTableTrClass(tr, "table-warning");

            // 设置备注的样式
            updateBeiZhuTd();
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
        }else if (orderState == 9){
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
            addTableTrClass(tr, "table-success");

            var tr = $(".container table .endTr");
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
     * 更改添加备注的单元格
     */
    function updateBeiZhuTd() {
        // 设置备注的样式
        var endTr = $(".container table .endTr");
        removeTableTrClass(endTr, "table-secondary");
        addTableTrClass(endTr, "table-warning");
    }
});
