$(document).ready(function () {


    // 调用初始化函数
    init();

    /**
     * 初始化函数
     */
    function init() {
        // 查看table是否存在，如果存在，对分页信息进行初始化
        var tableElement = $(".container table");
        if (tableElement.length > 0) {
            // 初始化分页html
            initPageHtml();
        }
        /**
         * 监听li的点击事件
         */
        liActiveInit();
        $(".container .list-group li").unbind("click", liChangeActiveEvn);
        $(".container .list-group li").bind("click", liChangeActiveEvn);
    }

    /**
     * 对li的属性进行初始化
     */
    function liActiveInit() {
        var li = $(".container .list-group #currentUserEdmOrder");
        // active属性
        if (!$(li).hasClass("active")) {
            $(li).addClass("active");
        }
        // 对值进行初始化
        var ifCurrentUser = $(".container nav #pageValue input[name='ifCurrentUser']");
        if (ifCurrentUser.length == 0) {
            var ifCurrentUserInput = $("<input type='hidden' name='ifCurrentUser' value='0'>");
            $(".container nav #pageValue").append(ifCurrentUserInput);
        }

        // 为全部全部添加初始化
        var li = $(".container .list-group #orderAll");
        // active属性
        if (!$(li).hasClass("active")) {
            $(li).addClass("active");
        }
        // 对值进行初始化
        var orderStatusElement = $(".container nav #pageValue input[name='orderStatus']");
        if (orderStatusElement.length == 0) {
            var orderStatusInput = $("<input type='hidden' name='orderStatus' value='-1'>");
            $(".container nav #pageValue").append(orderStatusInput);
        }

    }

    /**
     * 当点击的时候为li添加active属性
     */
    function liChangeActiveEvn() {
        // 获取其他的li
        var lis = $(this).siblings("li");
        // 移除其他的active属性
        $.each(lis, function (i, liElement) {
            if ($(liElement).hasClass("active")) {
                $(liElement).removeClass("active");
            }
        });
        // 是否查当前用户的条件： 0 当前用户， 1 所有用户
        var ifCurrentUser = $(".container nav #pageValue input[name='ifCurrentUser']");
        // 修改状态
        var orderStatusElement = $(".container nav #pageValue input[name='orderStatus']");
        // 当前用户
        if ($(this).attr("id") == "currentUserEdmOrder") {
            ifCurrentUser.val(0);
        }
        // 所有用户
        else if ($(this).attr("id") == "allUserEdmOrder") {
            ifCurrentUser.val(1);
        }
        // 查询所有order
        else if ($(this).attr("id") == "orderAll") {
            orderStatusElement.val(0);
        }
        // 流转中order
        else if ($(this).attr("id") == "orderDuring") {
            orderStatusElement.val(1);
        }
        // 流转成功
        else if ($(this).attr("id") == "orderSuccess") {
            orderStatusElement.val(2);
        }
        // 流转失败
        else if ($(this).attr("id") == "orderFail") {
            orderStatusElement.val(3);
        }

        // 为其添加active事件
        if (!$(this).hasClass("active")) {
            $(this).addClass("active");
            changeLiActiveToFindPageEdmApplyOrderList();
        }
    }

    /**
     * 对分页的html进行初始化
     */
    function initPageHtml() {
        // 获取总页面数
        var pageSize = $(".container nav #pageValue input[name='pageSize']").val();
        // 获取当前页面数
        var currentPageNum = $(".container nav #pageValue input[name='currentPageNum']").val();
        // 获取一页的记录条数
        var totalPageNum = $(".container nav #pageValue input[name='totalPageNum']").val();
        // 总共的记录条数
        var totalItemNum = $(".container nav #pageValue input[name='totalItemNum']").val();

        var pageUl = $("<ul class='pagination justify-content-center'></ul>");

        // 初始化： 一页显示多少条
        var pageSizeLi = $("<li class='pageSize pt-2'></li>");
        var pageSizeLabel = $("<label for='pageSize'>一页显示条数：</label>");
        pageSizeLi.append(pageSizeLabel);
        var pageSizeSelect = $("<select id='pageSize' name='pageSize'></select>");
        pageSizeSelect.append("<option value='10'>10</option>");
        pageSizeSelect.append("<option value='30'>30</option>");
        pageSizeSelect.append("<option value='50'>50</option>");
        pageSizeSelect.append("<option value='100'>100</option>");

        var options = pageSizeSelect.children("option");
        var pageSizeFlag = true;
        for (var i = 0; i < options.length; i++) {
            var optValue = $(options[i]).text();
            if (optValue == pageSize) {
                $(options[i]).prop("selected", true);
                pageSizeFlag = false;
            }
        }
        if (pageSizeFlag) {
            var defaultOpt = $("<option></option>");
            defaultOpt.attr("value", pageSize);
            defaultOpt.text(pageSize);
            defaultOpt.prop("selected", true);
            pageSizeSelect.prepend(defaultOpt);
        }

        pageSizeLi.append(pageSizeSelect);
        // // 将一页显示多少条添加到ul
        pageUl.append(pageSizeLi);
        // 上一页 设置可用或不可用
        if (currentPageNum <= 1) {
            var previousLi = $("<li class='page-item previous disabled'><span class='page-link disabled' name='lastPageNum'>&laquo;</span></li>");
            pageUl.append(previousLi);
        } else {
            var previousLi = $("<li class='page-item previous'><span class='page-link' name='lastPageNum'>&laquo;</span></li>");
            pageUl.append(previousLi);
        }


        // 中间的页码
        // 当前页
        if (currentPageNum >= 1) {
            var currentPageNumLi = $("<li class='page-item middlePageNum active'><span class='page-link' name='currentPageNum'>" + currentPageNum + "</span></li>");
            pageUl.append(currentPageNumLi);
        }
        // 中间的页码, 显示中间页码的次数
        for (var i = parseInt(currentPageNum) + 1, j = 0; i >= 1 && i <= totalPageNum && j < 2; i++, j++) {
            var currentPageNumLi = $("<li class='page-item middlePageNum'><span class='page-link'>" + i + "</span></li>");
            pageUl.append(currentPageNumLi);
        }


        // 下一页, 设置可用或不可用
        if (currentPageNum >= totalPageNum) {
            var nextLi = $("<li class='page-item next disabled'><span class='page-link disabled' name='nextPageNum'>&raquo;</span></li>");
            pageUl.append(nextLi);
        } else {
            var nextLi = $("<li class='page-item next'><span class='page-link' name='nextPageNum'>&raquo;</span></li>");
            pageUl.append(nextLi);
        }


        var currentPageValue = "总共 " + totalPageNum + "页, " + totalItemNum + " 条数据";
        var currentPageSpan = $("<span class='currentPage'></span>");
        currentPageSpan.text(currentPageValue);
        var currentPageLi = $("<li class='currentPage pt-2'></li>");
        currentPageLi.append(currentPageSpan);
        pageUl.append(currentPageLi);


        $(".container nav").append(pageUl);

        // 为分页按钮添加监听事件
        // 为一页显示多少条添加监听事件
        $(".container nav .pagination li select").unbind("change", findPageEdmApplyOrderList);
        $(".container nav .pagination li select").bind("change", findPageEdmApplyOrderList);
        // 为上一页、下一页、中间页码添加监听事件
        var pateItems = $(".container nav .pagination .page-item");
        $.each(pateItems, function (i, item) {
            if (!$(item).hasClass("disabled")) {
                $(item).unbind("click", findPageEdmApplyOrderList);
                $(item).bind("click", findPageEdmApplyOrderList);
            }
        });


    }


    /**
     * 查找一页的PageEdmApplyOrder
     */
    function findPageEdmApplyOrderList() {
        // 获取当前页码
        var currentPageNumElement = $(".container nav .pagination .middlePageNum span[name='currentPageNum']");
        var oldCurrentPageNum=1;
        var currentPageNum=1;
        if (currentPageNumElement.length >0){
            oldCurrentPageNum = currentPageNumElement.text();
            currentPageNum= oldCurrentPageNum;
        }

        // 获取 一页的条数
        var pageSize = $(".container nav .pagination .pageSize select option:selected").attr("value");
        // 获取 eid
        var eid = $(".container nav #pageValue input[name='eid']").val();
        // 用户的状态： 0 当前用户， 1 所有用户
        var ifCurrentUserValue = $(".container nav #pageValue input[name='ifCurrentUser']").val();
        // order的状态： 0 全部order 1 流转中 2 流转成功  3 流转失败
        var orderStatusValue = $(".container nav #pageValue input[name='orderStatus']").val();
        // selected 是否改变
        var selectChanged = false;
        // 判断点击的是否为上一页
        if ($(this).hasClass("previous")) {
            // 获取当前页码
            currentPageNum = parseInt(currentPageNum) - 1;
        }
        // 判断点击的是否为下一页
        else if ($(this).hasClass("next")) {
            // 获取当前页码
            currentPageNum = parseInt(currentPageNum) + 1;
        }
        // 判断是否点击为中间页码
        else if ($(this).hasClass("middlePageNum")) {
            // 获取当前页码的值
            currentPageNum = $(this).children("span").text();
        }
        // 判断点击为一页多少条
        else if ($(this)[0].tagName == "SELECT") {
            pageSize = $(this).val();
            currentPageNum = 1;
            selectChanged = true;
        }
        var dataObj = {"currentPage": currentPageNum, "pageSize": pageSize};
        // 当前用户
        if (ifCurrentUserValue == 0){
            dataObj.eid=eid;
        }
        // 流转中
        if (orderStatusValue==1){
            dataObj.orderStates=[0, 2, 4, 6];
        }
        // 流转成功
        else if (orderStatusValue == 2){
            dataObj.orderStates=[7, 9];
        }
        // 流转失败
        else if (orderStatusValue == 3){
            dataObj.orderStates=[1, 3, 5, 8];
        }

        // ajax 参数
        var data = JSON.stringify(dataObj);
        // 获取 token
        var token = $(".container nav #pageValue input[name='_csrf']").val();
        var headers = {"X-CSRF-TOKEN": token};
        // 获取项目路径
        var url = $.projectRootUrl() + "/edmApplyOrderController/findPageCurrentUserEdmApplyOrdersByQuery";
        if (parseInt(currentPageNum) == parseInt(oldCurrentPageNum) && !selectChanged) {
            console.log("CurrentPage not change");
        } else {
            ajaxFindPage(headers, data, url);
        }

    }

    /**
     * 改变了点击li的时候
     * 查找一页的PageEdmApplyOrder
     */
    function changeLiActiveToFindPageEdmApplyOrderList() {
        // 获取当前页码
        var currentPageNumElement = $(".container nav .pagination .middlePageNum span[name='currentPageNum']");
        var oldCurrentPageNum=1;
        var currentPageNum=1;
        if (currentPageNumElement.length >0){
            oldCurrentPageNum = currentPageNumElement.text();
            currentPageNum= oldCurrentPageNum;
        }
        // 获取 一页的条数
        var pageSize = $(".container nav .pagination .pageSize select option:selected").attr("value");
        // 获取 eid
        var eid = $(".container nav #pageValue input[name='eid']").val();
        // 用户的状态： 0 当前用户， 1 所有用户
        var ifCurrentUserValue = $(".container nav #pageValue input[name='ifCurrentUser']").val();
        // order的状态： 0 全部order 1 流转中 2 流转成功  3 流转失败
        var orderStatusValue = $(".container nav #pageValue input[name='orderStatus']").val();


        // selected 是否改变
        var selectChanged = false;
        // 判断点击的是否为上一页
        if ($(this).hasClass("previous")) {
            // 获取当前页码
            currentPageNum = parseInt(currentPageNum) - 1;
        }
        // 判断点击的是否为下一页
        else if ($(this).hasClass("next")) {
            // 获取当前页码
            currentPageNum = parseInt(currentPageNum) + 1;
        }
        // 判断是否点击为中间页码
        else if ($(this).hasClass("middlePageNum")) {
            // 获取当前页码的值
            currentPageNum = $(this).children("span").text();
        }
        // 判断点击为一页多少条
        else if ($(this)[0].tagName == "SELECT") {
            pageSize = $(this).val();
            currentPageNum = 1;
            selectChanged = true;
        }
        var dataObj = {"currentPage": currentPageNum, "pageSize": pageSize};
        // 当前用户
        if (ifCurrentUserValue == 0){
            dataObj.eid=eid;
        }
        // 流转中
        if (orderStatusValue==1){
            dataObj.orderStates=[0, 2, 4, 6];
        }
        // 流转成功
        else if (orderStatusValue == 2){
            dataObj.orderStates=[7, 9];
        }
        // 流转失败
        else if (orderStatusValue == 3){
            dataObj.orderStates=[1, 3, 5, 8];
        }

        // ajax 参数
        var data = JSON.stringify(dataObj);
        // 获取 token
        var token = $(".container nav #pageValue input[name='_csrf']").val();
        var headers = {"X-CSRF-TOKEN": token};
        // 获取项目路径
        var url = $.projectRootUrl() + "/edmApplyOrderController/findPageCurrentUserEdmApplyOrdersByQuery";

        ajaxFindPage(headers, data, url);

    }

    /**
     * 分页查询 ajax
     * @param headers
     * @param data
     * @param url
     */
    function ajaxFindPage(headers, data, url) {
        $.ajax({
            type: "POST",
            dataTypes: "json",
            contentType: "application/json",
            url: url,
            data: data,
            headers: headers,
            success: function (response) {
                // 结果的状态
                var status = response.status;
                if (status == 0) {
                    var result = response.result;
                    // table 的tr
                    reloadTableTrs(result.pageObjList);
                    // 分页的html
                    reloadPageHtml(result);
                } else {
                    console.error("Ajax Exception: " + url);
                }
            }
        });
    }

    /**
     * 重新加载分页的html
     */
    function reloadPageHtml(edmPage) {
        // 修改隐藏域的值
        // 修改总页面数
        $(".container nav #pageValue input[name='pageSize']").val(edmPage.pageSize);
        // 修改当前页面数
        $(".container nav #pageValue input[name='currentPageNum']").val(edmPage.currentPageNum);
        // 修改一页的记录条数
        $(".container nav #pageValue input[name='totalPageNum']").val(edmPage.totalPageNum);
        // 修改一页的记录条数
        $(".container nav #pageValue input[name='totalItemNum']").val(edmPage.totalItemNum);
        // 移除就得分页html
        $(".container nav .pagination").remove();
        // 重新添加分页的标签
        initPageHtml();
    }

    /**
     * 刷新table下面tr
     */
    function reloadTableTrs(edmApplyOrderList) {
        // 查看详情的url
        var rootDescUrl = $.projectRootUrl() + "/edmApplyOrderController/findEdmApplyOrderByOid/";
        var rootProgressUrl = $.projectRootUrl() + "/edmApplyOrderController/findEdmApplyOrderProgress/";
        // 判断table 是否存在，如果不存在创建table
        createTableIfNoteExists();
        var tbody = $(".container table tbody");
        // 删除所有的tr
        tbody.children("tr").remove();
        // 添加新的tr
        for (var i = 0; i < edmApplyOrderList.length; i++) {
            var tr = $("<tr></tr>")
            var xuhaoTh = $("<th scope='row' class='xuhao'></th>").text(i + 1);
            xuhaoTh.attr("id", edmApplyOrderList[i].edmer.eid);
            tr.append(xuhaoTh);
            // 流转单的名字
            var liuZhuanDanNameTd = $("<td class='liuzhuandan'></td>");
            var xiangqingA = $("<a></a>").attr("href", rootDescUrl + edmApplyOrderList[i].oid);
            xiangqingA.text(edmApplyOrderList[i].orderName);
            liuZhuanDanNameTd.append(xiangqingA);
            tr.append(liuZhuanDanNameTd);
            // 申请时间
            var applyDateTd = $("<td class='applyDate'></td>").text($.date(edmApplyOrderList[i].applyDate));
            tr.append(applyDateTd);
            // 申请人
            var applierTd = $("<td class='applier'></td>").text(edmApplyOrderList[i].edmer.username);
            tr.append(applierTd);
            // 状态
            var applyStatueTd = $("<td class='applyStatue'></td>");
            var orderStateValue = edmApplyOrderList[i].orderState;
            if(orderStateValue==7 || orderStateValue == 9){
                applyStatueTd.text('流转完成');
            }else{
                applyStatueTd.text('流转中');
            }
            tr.append(applyStatueTd);

            // 操作
            var optionTd = $("<td class='caozuo'></td>");
            var caoZouDiv = $("<div></div>");
            var showDescA = $("<a class='btn btn-info btn-sm mr-1 active' role='button' aria-pressed='true'>查看详情</a>");
            showDescA.attr("href", rootDescUrl + edmApplyOrderList[i].oid);
            var jinDuTiaoA = $("<a class='btn btn-info btn-sm active' role='button' aria-pressed='true'>流转进度</a>");
            jinDuTiaoA.attr("href", rootProgressUrl + edmApplyOrderList[i].oid);
            jinDuTiaoA.attr("href", "#");
            caoZouDiv.append(showDescA);
            caoZouDiv.append(jinDuTiaoA);
            optionTd.append(caoZouDiv);
            tr.append(optionTd);
            tbody.append(tr);
        }
    }

    /**
     * 创建table
     */
    function createTableIfNoteExists() {
        if($(".container table").length==0){
            // 判断 jumbotron 是否存在
            if ($(".container .jumbotron").length>0){
                $(".container .jumbotron").remove();
            }
            var hElement = $("<h2></h2>").text("群发流转单列表");
            $(".container").prepend(hElement);
            // 创建table
            var tableElement = $("<table style='table-layout:fixed' class='table table-bordered table-hover table-striped table-condensed'></table>");
            var theadElement = $("<thead></thead>");
            var theadOfTrElement = $("<tr></tr>").append("<th scope='col' class='xuhao' style='width: 8%;'>序号</th>")
                .append("<th scope='col' class='liuzhuandan'>流转单名称</th>")
                .append("<th scope='col' class='applyDate' style='width: 11%;'>申请时间</th>")
                .append("<th scope='col' class='applier' style='width: 10%;'>申请人</th>")
                .append("<th scope='col' class='applyStatue' style='width: 10%;'>状态</th>")
                .append("<th scope='col' class='caozuo' style='width: 18%;'>操作</th>");
            theadElement.append(theadOfTrElement);
            tableElement.append(theadElement);
            var tbodyElement = $("<tbody></tbody>");
            tableElement.append(tbodyElement);
            $(".container .filterUl").after(tableElement);
        }
    }

});