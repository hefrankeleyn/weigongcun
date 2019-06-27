/**
 * 控制导航栏active属性
 */
$(document).ready(function () {

    init();

    /**
     * 初始化
     */
    function init() {

        // 监听nav 下面的list
        $(".navbar .collapse .navbar-nav li").unbind("click", addOrRemoveActiveClass);
        $(".navbar .collapse .navbar-nav li").bind("click", addOrRemoveActiveClass);

        // 监听dropdown 下面所有的a dropdown-item
        $(".navbar .collapse .navbar-nav .dropdown .dropdown-menu .dropdown-item").unbind("click", addOrRemoveActiveForDropDown);
        $(".navbar .collapse .navbar-nav .dropdown .dropdown-menu .dropdown-item").bind("click", addOrRemoveActiveForDropDown);

        // 遍历所有head 上的所有a标签，判断是否与当前href
        // 在thymeleaf 模板下，在当前的js文件中只有这一个函数有起作用的
        loopAaddActiveClass();
    }

    /**
     * 遍历所有的a，判断a的href值是否与当前local的值相同
     */
    function loopAaddActiveClass() {

        // 判断当前的href
        var pathName = window.document.location.pathname;
        // 获取所有的a元素
        var navItemAs = $(".navbar .navbar-nav .nav-item a[href]");
        $.each(navItemAs, function (i, navItemA) {
            var hrefValue = $(navItemA).attr("href");
            if (hrefValue == pathName) {
                addActiveForA(navItemA);
            }
        })

    }

    /**
     * 为a标签添加active 的 class 属性
     */
    function addActiveForA(item) {
        // 为父类的li添加active
        // 判断当前class包含 dropdown-item 不
        console.log($(item).attr("class"));
        if($(item).hasClass("dropdown-item")){
            // 为父元素添加 active
            $(item).parent().parent(".nav-item").addClass("active");
            // 为自己添加active
            $(item).addClass("active");
        }else if ($(item).hasClass("nav-link")) {
            // 为父元素添加active
            $(item).parent(".nav-item").addClass("active");
        }
    }

    /**
     * 添加或移除active class
     */
    function addOrRemoveActiveClass() {
        var classValue = $(this).attr("class");

        // 判断class里面是否同样包含 dropdown
        if (!$(this).hasClass("dropdown")) {
            // 判断里面是否包含class： active
            if (!$(this).hasClass("active")) {
                // 如果不包含active 的class ，添加active，并移除其他元素中的active
                var siblingsList = $(this).siblings("li");
                $.each(siblingsList, function (i, item) {
                    removeActiveForLi(item);
                });
                // 添加 active
                $(this).addClass("active");

            }
        }
    }

    /**
     * 为dropdown 添加或移除active class属性
     */
    function addOrRemoveActiveForDropDown() {
        var classValue = $(this).attr("class");
        console.log(classValue);
        // 判断是否有 active class 属性
        if (!$(this).hasClass("active")) {
            // 如果不存在 active 属性，就添加active属性，同时移除其他兄弟节点的子元素
            var siblingsA = $(this).siblings("a");
            // 遍历兄弟元素节点
            $.each(siblingsA, function (i, item) {
                // 判断兄弟节点是否有active的 class 属性，如果存在就移除
                if ($(item).hasClass("active")) {
                    $(item).removeClass("active");
                }
            });

            // 判断当前父元素 li 的兄弟元素上是否有 active class
            // 获取当前父元素的兄弟元素
            var otherNavItems = $(this).parent().parent().siblings(".nav-item");
            // 移除其他li及li drowdown上的active class属性
            $.each(otherNavItems, function (i, item) {
                removeActiveForLi(item);
            });

            // 在当前父标签上添加 active
            $(this).parent().parent(".nav-item").addClass("active");
            // 在当前a标签上添加active class
            $(this).addClass("active");

        }
    }

    /**
     *  为 li 移除active
     */
    function removeActiveForLi(item) {
        // 判断是否有 active 的 class
        if ($(item).hasClass("active")) {
            // 移除active 的class 属性
            $(item).removeClass("active");
            // 判断item是否有dropdown class
            if ($(item).hasClass("dropdown")) {
                // 如果是dropdown， 遍历下面的 a
                var dropdownItems = $(item).children(".dropdown-menu").children(".dropdown-item");
                $.each(dropdownItems, function (i, dropdownItem) {
                    if ($(dropdownItem).hasClass("active")) {
                        $(dropdownItem).removeClass("active");
                    }
                });
            }
        }
    }
});

