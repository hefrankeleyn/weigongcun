$(document).ready(function () {

    init();
    /**
     * 初始化操作
     */
    function init() {
        addOrRemoveProvinceCodesDiv();
        addOrRemoveCityCodesDiv();
        // 省份
        $("#provinceIf").unbind("click", addOrRemoveProvinceCodesDiv);
        $("#provinceIf").bind("click", addOrRemoveProvinceCodesDiv);

        // 城市
        $("#cityIf").unbind("click", addOrRemoveCityCodesDiv);
        $("#cityIf").bind("click", addOrRemoveCityCodesDiv);

        // 为 城市下拉复选框添加监听事件
        // $("#citycodes option").unbind("click", createCityCheckBox);
        // $("#citycodes option").bind("click", createCityCheckBox);

        // 为城市的省份选项添加 监听事件
        $(".form-group #oneProvince").unbind("change", createCitySelect);
        $(".form-group #oneProvince").bind("change", createCitySelect);
        // 调用获取某一个城市的函数
        createCitySelect();
    }

    /**
     * 通过ajax请求数据，然后创建city 选择列表
     */
    function createCitySelect() {
        // 获取 token
        var token = $("input[name='_csrf']").val();
        // 获取省份的id
        var provincecode = $(".form-group #oneProvince").children("option:checked").attr("value");
        var data = {"provincecode" : provincecode};
        var headers = {"X-CSRF-TOKEN": token}
        // 获取项目路径
        var projectUrl = createProjectUrl();
        var url = projectUrl + "/edmFetchDataConditionController/findCitiesByProvinceCode";
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: data,
            headers:headers,
            success: function (response) {
                // 添加城市的 multi select
                var status = response.status;
                if(status=="SUCCESS"){
                    var result = response.result;
                    removeAndCreateMultipleCitySelect(result)
                }else {
                    console.error("Ajax Exception: " + url);
                }
            }
        });
    }
    /**
     * 重新添加城市复选列表
     */
    function removeAndCreateMultipleCitySelect(collection){
        var onProvince = $(".form-group #oneProvince").parent();
        var multipleCitySelect = onProvince.children("#citycodes");
        if(multipleCitySelect.length>0){
            multipleCitySelect.remove();
        }
        var selectHtml = $("<select multiple id='citycodes' class='form-control'></select>");
        $.each(collection, function (i, city) {
            var optionHtml = $("<option></option>").attr("value", city.citycode);
            optionHtml.text(city.cityname);
            selectHtml.append(optionHtml);
        });
        $(".form-group #oneProvince").after(selectHtml);

        var citycodesSelect = onProvince.children("#citycodes").children("option");
        //为新添加S绑定监听事件
        citycodesSelect.unbind("click", createCityCheckBox);
        citycodesSelect.bind("click", createCityCheckBox);

    }

    /**
     * 获取项目的url
     */
    function createProjectUrl(){
        // 获取网址 http://localhost:8080/EdmFetchDataPlatform/edmFetchDataConditionController/findCitiesByProvinceCode
        var wwwPath = window.document.location.href;
        // 获取主机之后的目录， /EdmFetchDataPlatform/edmFetchDataConditionController/findCitiesByProvinceCode
        var pathname = window.document.location.pathname;
        var pathnameIndex = wwwPath.indexOf(pathname);
        // 获取主机地址 http://localhost:8080
        var hostPath = wwwPath.substr(0,pathnameIndex);
        //获取项目名称 /EdmFetchDataPlatform/
        var projectName = pathname.substr(0, pathname.substr(1).indexOf("/")+1);
        return hostPath + projectName;
    }

    /**
     * 移除复选框
     */
    function removeCityCheckBox(){
        var checkedValue = $(this).children(".form-check-input").prop("checked");
        if(!checkedValue){
            $(this).remove();
        }
    }


    /**
     * 添加或者移除checkbox 复选框
     */
    function createCityCheckBox() {
        // 获取checkbox的value值，name值， id值
        // 城市代码的值
        var cityCodeValue = $(this).val();
        // 城市中文名称
        var cityNameValue = $(this).text();
        // checkbox城市id
        var cityCheckBoxId = "citycodes" + cityCodeValue;
        // 判断要创建的city checkbox 是否存在，如果不存在就新添加一个
        // 获取所有的city checkbox
        var cityCheckBoxsInput = $(this).parent().parent().children(".form-check").children(".form-check-input");

        var create_flag = true;
        // 判断复选框是否存在
        for (var i = 0; i < cityCheckBoxsInput.length; i++) {
            var idValue = $(cityCheckBoxsInput[i]).attr("id");
            if (idValue == cityCheckBoxId) {
                create_flag = false;
                break;
            }
        }
        // 如果复选框不存在，就创建元素
        /*
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" value="1283" name="cityCodes" id="citycodes1" checked>
            <label class="form-check-label" for="citycodes1">珠海</label>
        </div>
        */
        if (create_flag) {
            var checkInputHtml = $("<input class='form-check-input' type='checkbox' name='cityCodes'>")
                .attr("id", cityCheckBoxId)
                .attr("value",cityCodeValue)
                .attr("checked","checked");
            var checkBoxLableHtml = $("<label class='form-check-label'></label>").attr("for",cityCheckBoxId).text(cityNameValue);
            var newCheckBox = $("<div class='form-check form-check-inline'></div>").append(checkInputHtml);
            newCheckBox.append(checkBoxLableHtml);
            $(this).parent().parent().append(newCheckBox);

            //为城市复选框添加监听事件,用于移除复选框
            newCheckBox.unbind("click",removeCityCheckBox);
            newCheckBox.bind("click",removeCityCheckBox);
        }
    }

    /**
     * 添加或者移除城市省份选项的内容
     */
    function addOrRemoveCityCodesDiv() {
        var ifChecked = $("#cityIf").prop("checked");
        if (ifChecked) {
            // select，将父元素显示
            $("#citycodes").parent().show();

            // radio 添加name属性，并将父元素显示
            $("input[id^='cityOpt'][type='radio']").attr("name", "cityOpt");
            $("input[id^='cityOpt'][type='radio']").parent().parent().show();
        } else if (!ifChecked) {
            // select 移除name属性，并且将父元素进行隐藏
            $("#citycodes").parent().hide();
            //删除所有的城市复选框
            var formChecks = $(".form-group #oneProvince").parent().children(".form-check");
            if(formChecks.length>0){
                $(formChecks).remove();
            }
            // radio 移除name属性，并将父元素隐藏
            $("input[id^='cityOpt'][type='radio']").removeAttr("name");
            $("input[id^='cityOpt'][type='radio']").parent().parent().hide();
        }
    }
    /**
     * 添加或者移除省份选项的div
     */
    function addOrRemoveProvinceCodesDiv() {
        var ifChecked = $("#provinceIf").prop("checked");
        if (ifChecked) {
            // select 添加name属性，并将父元素显示
            $("#provinceCodes").attr("name", "provinceCodes");
            $("#provinceCodes").parent().show();

            // radio 添加name属性，并将父元素显示
            $("input[id^='provinceOpt'][type='radio']").attr("name", "provinceOpt");
            $("input[id^='provinceOpt'][type='radio']").parent().parent().show();
        } else if (!ifChecked) {
            // select 移除name属性，并且将父元素进行隐藏
            $("#provinceCodes").removeAttr("name");
            $("#provinceCodes").parent().hide();

            // radio 移除name属性，并将父元素隐藏
            $("input[id^='provinceOpt'][type='radio']").removeAttr("name");
            $("input[id^='provinceOpt'][type='radio']").parent().parent().hide();
        }
    }


});