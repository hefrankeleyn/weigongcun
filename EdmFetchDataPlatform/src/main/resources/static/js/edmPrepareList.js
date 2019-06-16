$(document).ready(function () {

    init();

    /**
     * 初始化
     */
    function init() {

        // 对表单提交按钮进行初始化
        submitButtonDisabled();
        // 为td的复选框添加添加监听事件
        $("tbody td .custom-control-input").unbind("click", addhindInputAndActiveSubmitButton);
        $("tbody td .custom-control-input").bind("click", addhindInputAndActiveSubmitButton);

        // 对全选按钮添加监听事件
        $("thead th #allCids").unbind("click", allCidsButtonEvent);
        $("thead th #allCids").bind("click", allCidsButtonEvent);
    }


    // 为全选按钮添加监听事件
    function allCidsButtonEvent() {
        // 判断是被选中还是被取消选中
        var ifChecked = $(this).prop("checked");
        if (ifChecked) {
            // 获取所有没有选中的按钮，将其选中
            var tdUnCheckeds = $("tbody td .custom-control-input:checkbox:not(:checked)");
            // 将所有
            $.each(tdUnCheckeds, function (i, tdUnChecked) {
                // 置为true
                $(tdUnChecked).prop("checked", true);
                //
                // 获取name 和 value
                var name = $(tdUnChecked).attr("name");
                var value = $(tdUnChecked).attr("id");
                addInputToForm(name, value);
            });
        } else {
            // 获取所有选中的按钮，将其不选中
            var tdCheckeds = $("tbody td .custom-control-input:checkbox:checked");
            $.each(tdCheckeds, function (i, tdChecked) {
                // 置为true
                $(tdChecked).prop("checked", false);
                var value = $(tdChecked).attr("id");
                //  从表单中将 input 删除
                removeInputFromForm(value);
            });
        }
        // 处理表单提交按钮，将按钮置为可用
        submitButtonDisabled();
    }


    /**
     * 向顶部的form表单中添加隐藏input元素，并将 submit 按钮置为可用
     */
    function addhindInputAndActiveSubmitButton() {
        // 获取id的值
        var conIdParam = $(this).attr("name");
        var conIdValue = $(this).attr("id");

        // 判断是被选中还是被取消选中
        var ifChecked = $(this).prop("checked");
        if (ifChecked) {
            //向form表单中添加input
            addInputToForm(conIdParam, conIdValue)
            // 处理表单提交按钮，将按钮置为可用
            submitButtonDisabled();
        } else {
            //  从表单中将 input 删除
            removeInputFromForm(conIdValue);
            // 判断是否还有input，将按钮置为不可用
            submitButtonDisabled();
        }

        // 判断设置全选按钮
        checkIfAllChecked();
    }

    /**
     * 添加input
     * @param {name} name
     * @param {value} value
     */
    function addInputToForm(name, value) {
        // 创建一个 input 放到表单中
        // 创建 隐藏的input
        var input = $("<input type='text'>")
            .attr("name", name)
            .attr("value", value)
            .attr("hidden", "hidden");
        // 获取form表单
        $("#edmApplyForm .btn").before(input);
    }

    /**
     * 检查是否全部的按钮都被选中
     */
    function checkIfAllChecked(){
        var checkboxs = $("tbody td .custom-control-input:checkbox");
        var checkboxsOk = $("tbody td .custom-control-input:checkbox:checked");
        if(checkboxs.length == checkboxsOk.length){
            $("thead th #allCids").prop("checked",true);
        }else{
            $("thead th #allCids").prop("checked",false);
        }

    }

    /**
     * 从from中移除input
     * @param {value} value
     */
    function removeInputFromForm(value) {
        var childInput = $("#edmApplyForm").children("input[value='" + value + "']");
        if ($(childInput).length>0) {
            $(childInput).remove();
        }
    }


    /**
     * 处理表单提交按钮可用与不可用
     */
    function submitButtonDisabled() {
        var inputs = $("#edmApplyForm input[type='text']");
        console.log(inputs.length);
        if (inputs.length > 0) {
            var disabledIfExists = $("#edmApplyForm .btn").attr("disabled");
            if (typeof disabledIfExists !== undefined && disabledIfExists !== false) {
                $("#edmApplyForm .btn").prop("disabled", false);
            }
        } else {
            $("#edmApplyForm .btn").prop("disabled", true);
        }
    }

});