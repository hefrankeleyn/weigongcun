/**
 * author: Li Fei
 * date: 2019-6-13
 */
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

        // 为城市的省份选项添加 监听事件
        $(".form-group #oneProvince").unbind("change", createCitySelect);
        $(".form-group #oneProvince").bind("change", createCitySelect);

        // 开启表单验证
        addFormValidationEvent();

        // 监听要排除的数据编码按钮
        $(".form-group .input-group .addDataCode:button").unbind("click", addDataCodeCheckBox);
        $(".form-group .input-group .addDataCode:button").bind("click", addDataCodeCheckBox);
        // 将添加要排除的数据编码按钮置为不可用
        addDataCodeButtonEnableOrDisAble(true);
        $(".form-group .input-group #addDataCode").unbind("input propertychange", listenAddDataCodeInput);
        $(".form-group .input-group #addDataCode").bind("input propertychange", listenAddDataCodeInput);
    }

    /**
     * 检查要排除的数据编码是否存在，如果存在添加checkout
     */
    function addDataCodeCheckBox() {
        // 获取addDataCode input中的内容
        var dataCodeInput = $(this).parent().siblings("#addDataCode");
        var dataCode = dataCodeInput.val();
        var parentFormGroup = $(this).parent().parent().parent();
        // 拼接data参数
        var data = {"dataCode" : dataCode};
        // 获取 token
        var token = $("input[name='_csrf']").val();
        var headers = {"X-CSRF-TOKEN": token}
        // 获取项目路径
        var url = $.projectRootUrl() + "/edmTaskResultController/judgeDataCodeIfExists";
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: data,
            headers:headers,
            success: function (response) {
                // 获取请求结果
                var status = response.status;
                if(status == 0){
                    // 进行ajax请求，查询该datacode是否存在， 如果存在添加一个checkbox
                    var label = $("<label class='form-check-label'></label>").text(dataCode);
                    var inputCheckbox = $("<input type='checkbox' class='form-check-input' name='dataCodes'>")
                        .attr("value", dataCode)
                        .prop("checked", true);
                    var dataCodeCheckBox = $("<div class='form-check-inline'></div>")
                        .append(inputCheckbox).append(label);
                    parentFormGroup.append(dataCodeCheckBox);
                    // 清除input的内容
                    dataCodeInput.val("");
                    // 添加监听checked 看是否选中
                    inputCheckbox.unbind("click", removeDataCodeCheckBox);
                    inputCheckbox.bind("click", removeDataCodeCheckBox);

                    // 移除并移除invalid-feedback
                    var invalidDiv = dataCodeInput.parent().children(".invalid-feedback");
                    if (invalidDiv.length == 1) {
                        invalidDiv.remove();
                        if (dataCodeInput.hasClass("is-invalid")) {
                            dataCodeInput.removeClass("is-invalid");
                        }
                    }
                }else {
                    // 数据编码不存在，添加错误消息
                    var invalidDataCode = $("<div class='invalid-feedback'>数据编码不存在</div>");
                    dataCodeInput.parent().append(invalidDataCode);
                    dataCodeInput.addClass("is-invalid");
                }
            }
        });


    }

    /**
     * 判断是否移除datacode的checkedbox
     */
    function removeDataCodeCheckBox() {
        var checkedIf = $(this).is(":checked");
        if (!checkedIf) {
            $(this).parent().remove();
        }
    }


    /**
     * 检查 addDataCode 的input中是否存在内容
     */
    function listenAddDataCodeInput() {
        // 将按钮置为可用
        var dataValue = $(this).val();
        if (dataValue != "") {
            // 将添加要排除的数据编码按钮置为可用
            addDataCodeButtonEnableOrDisAble(false);
        }
    }

    /**
     * 将添加排除数据编码按钮置为可用或不可用
     * true为不可用
     * false为可用
     * @param {boolean值} flag
     */
    function addDataCodeButtonEnableOrDisAble(flag) {
        var addDataCodeButton = $(".form-group .input-group .addDataCode:button");
        if (flag) {
            if (addDataCodeButton.length == 1) {
                addDataCodeButton.prop("disabled", true);
                // 如果btn-info 存在，删除 该类 添加 btn-secondary
                if (addDataCodeButton.hasClass("btn-info")) {
                    addDataCodeButton.removeClass("btn-info");
                }
                // 如果 btn-secondary 不存在 添加 btn-secondary
                if (!addDataCodeButton.hasClass("btn-secondary")) {
                    addDataCodeButton.removeClass("btn-secondary");
                }
            }
        } else {
            if (addDataCodeButton.length == 1) {
                addDataCodeButton.prop("disabled", false);
                // 如果btn-secondary 存在，删除 该类 添加 btn-info
                if (addDataCodeButton.hasClass("btn-secondary")) {
                    addDataCodeButton.removeClass("btn-secondary");
                }
                // 如果 btn-info 不存在 添加 btn-info
                if (!addDataCodeButton.hasClass("btn-info")) {
                    addDataCodeButton.addClass("btn-info");
                }
            }
        }

    }

    /**
     * 添加表单验证事件
     */
    function addFormValidationEvent() {
        // 自定义验证规则
        $(".form-group #limitNum").unbind("input propertychange",fetchDataFormValidation);
        $(".form-group #limitNum").bind("input propertychange",fetchDataFormValidation);
    }


    /**
     * 提数表单验证
     */
    function fetchDataFormValidation(){
        var limitNum = $(".form-group #limitNum").val();
        var numberIf = $.isNumeric(limitNum);
        if(!numberIf  || limitNum<=0){
            // $(".form-group #limitNum").css("border","1px solid #ced4");
            $(".form-group #limitNum").addClass("is-invalid");
            // 添加不可用标识
            $("button[type='submit']").attr("disabled","disabled");
        }else{
            // 移除不可用标识
            $("button[type='submit']").removeAttr("disabled");
            $(".form-group #limitNum").removeClass("is-invalid");
        }
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
        var url = $.projectRootUrl() + "/edmFetchDataConditionController/findCitiesByProvinceCode";
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: data,
            headers:headers,
            success: function (response) {
                // 添加城市的 multi select
                var status = response.status;
                if(status == 0){
                    var result = response.result;
                    removeAndCreateMultipleCitySelect(result);
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
        // 添加 required 元素
        $(".form-group #citycodes").attr("required","required");

        var citycodesSelect = onProvince.children("#citycodes").children("option");
        //为新添加Select绑定监听事件
        citycodesSelect.unbind("click", createCityCheckBox);
        citycodesSelect.bind("click", createCityCheckBox);

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
            $("#oneProvince").parent().show();

            // 调用获取某一个城市的函数
            createCitySelect();

            // radio 添加name属性，并将父元素显示
            $("input[id^='cityOpt'][type='radio']").attr("name", "cityOpt");
            $("input[id^='cityOpt'][type='radio']").parent().parent().show();
        } else if (!ifChecked) {
            // select 移除name属性，并且将父元素进行隐藏
            $(".form-group #oneProvince").parent().hide();
            //删除所有的城市复选框
            var formChecks = $(".form-group #oneProvince").parent().children(".form-check");
            if(formChecks.length>0){
                $(formChecks).remove();
            }
            // 移除城市下拉列表
            var citycodesSelect = $(".form-group #oneProvince").parent().children("#citycodes");
            if(citycodesSelect.length>0){
                $(citycodesSelect).remove();
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
            // 添加 required 元素
            $("#provinceCodes").attr("required","required");

            // radio 添加name属性，并将父元素显示
            $("input[id^='provinceOpt'][type='radio']").attr("name", "provinceOpt");
            $("input[id^='provinceOpt'][type='radio']").parent().parent().show();
        } else if (!ifChecked) {
            // select 移除name属性，并且将父元素进行隐藏
            $("#provinceCodes").removeAttr("name");
            $("#provinceCodes").parent().hide();
            $("#provinceCodes").removeAttr("required");

            // radio 移除name属性，并将父元素隐藏
            $("input[id^='provinceOpt'][type='radio']").removeAttr("name");
            $("input[id^='provinceOpt'][type='radio']").parent().parent().hide();
        }
    }


});