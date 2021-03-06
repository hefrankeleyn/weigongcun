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

        // 监听所有的 executeTiShu 类
        $(".container table .executeTiShu").unbind("click", ajaxExecuteTiShu);
        $(".container table .executeTiShu").bind("click", ajaxExecuteTiShu);
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
        var oldCurrentPageNum = 1;
        var currentPageNum = 1;
        if (currentPageNumElement.length > 0) {
            oldCurrentPageNum = currentPageNumElement.text();
            currentPageNum = oldCurrentPageNum;
        }
        // 获取 一页的条数
        var pageSize = $(".container nav .pagination .pageSize select option:selected").attr("value");
        // 获取 eid
        var eid = $(".container nav #pageValue input[name='eid']").val();
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
        var orderStates = [6, 10];
        // ajax 参数
        var data = JSON.stringify({
            "currentPage": currentPageNum,
            "pageSize": pageSize,
            "eid": eid,
            "orderStates": orderStates
        });
        // 获取 token
        var token = $(".container nav #pageValue input[name='_csrf']").val();
        var headers = {"X-CSRF-TOKEN": token};
        // 获取项目路径
        var url = $.projectRootUrl() + "/edmApplyOrderController/findPageEdmPreExecutorListByQuery";
        if (parseInt(currentPageNum) == parseInt(oldCurrentPageNum) && !selectChanged) {
            console.log("CurrentPage not change");
        } else {
            ajaxFindPage(headers, data, url);
        }

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
        var rootDescUrl = $.projectRootUrl() + "/edmApplyOrderController/findCheckEdmApplyOrderByOid/";
        // 提数操作

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
            var applyDateTd = $("<td class='paiQiResult'></td>").text($.date(edmApplyOrderList[i].edmApplyOrderCheckResult.paiQiResult));
            tr.append(applyDateTd);
            // 申请人
            var applierTd = $("<td class='applier'></td>").text(edmApplyOrderList[i].edmer.username);
            tr.append(applierTd);
            // 状态
            var applyStatueTd = $("<td class='applyStatue'></td>").text(edmApplyOrderList[i].orderState == 6 ? '审批完成' : '提取中');
            tr.append(applyStatueTd);

            // 操作
            var optionTd = $("<td class='caozuo'></td>");
            var caoZouDiv = $("<div></div>");
            var oidInput = $("<input type='hidden' name='oid'>").attr("value", edmApplyOrderList[i].oid);
            caoZouDiv.append(oidInput);
            if (edmApplyOrderList[i].orderState == 6) {
                var tishuOptButton = $("<button class='btn btn-success btn-sm mr-1 executeTiShu' type='button' aria-pressed='true'>开始提数</button>");
                caoZouDiv.append(tishuOptButton);
                tishuOptButton.unbind("click", ajaxExecuteTiShu);
                tishuOptButton.bind("click", ajaxExecuteTiShu);
            }else {
                var tishuDuringButton = $("<button class='btn btn-secondary btn-sm mr-1 executeTiShu' type='button' aria-pressed='true' disabled>提取中...</button>");
                caoZouDiv.append(tishuDuringButton);
            }
            optionTd.append(caoZouDiv);
            tr.append(optionTd);
            tbody.append(tr);
        }
    }

    /**
     * 发起ajax，发起执行提数操作
     * @param oid
     */
    function ajaxExecuteTiShu() {
        var oid = $(this).siblings("input[name='oid']").val();
        var currentTr = $(this).parent("div").parent("td").parent("tr");
        // 修改text
        $(this).text("提取中...");
        // 修改颜色的类
        if ($(this).hasClass("btn-success")){
            $(this).removeClass("btn-success");
            $(this).addClass("btn-secondary")
        }
        // 将按钮值为不可用
        $(this).prop("disabled", true);
        // ajax 参数
        var data = {
            "oid": oid
        };
        // 获取 token
        var token = $(".container nav #pageValue input[name='_csrf']").val();
        var headers = {"X-CSRF-TOKEN": token};
        // 获取项目路径
        var url = $.projectRootUrl() + "/edmApplyOrderController/executeTiShuOption";
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            headers: headers,
            success: function (response) {
                // 结果的状态
                var status = response.status;
                if (status == 0) {
                    //  删除当前tr
                    currentTr.remove();
                    // 判断当前table下是否存在tr
                    judgeTrIsEmptyRemoveTheadAddDiv();
                } else {
                    console.error("Ajax Exception: " + url);
                }
            }
        });
    }

    /**
     * 判断tr是否为空，如果为空删除thead
     */
    function judgeTrIsEmptyRemoveTheadAddDiv() {
        var tBodyTrs = $(".container table tbody").children("tr");
        if (tBodyTrs.length == 0) {
            $(".container").remove();
            var jumbotronDiv = $("<div class='jumbotron'></div>").append("<h1 class='display-4'>列表为空</h1>")
                .append("<p class='lead'>流转单可能还在审核中，暂时没有待提数列表</p>");
            $("<div class='container'></div>").append(jumbotronDiv).appendTo($("body"));
        }
    }


});