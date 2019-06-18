$(document).ready(function () {

    init();
    /**
     * 初始化函数
     */
    function init() {
        // 监听添加附件按钮按钮
        $(".form-group .btn-group #addFiles").unbind("click", addFileInput);
        $(".form-group .btn-group #addFiles").bind("click", addFileInput);
    }

    /**
     * 添加一个file input
     */
    function addFileInput() {
        var fileDiv = $("<div class='custom-file mt-1'></div>");
        var fileInput = $("<input type='file' class='custom-file-input' name='edmFiles'>");
        var fileLabel = $("<label class='custom-file-label text-black-50'></label>");
        // 为附件input添加 required
        fileInput.prop("required", true);
        // 获取input[type='file']的数量
        var fileInputs = $(".form-group .custom-file input[type='file']");
        var size = fileInputs.length + 1;
        var labelContext = "待添加：附件" + size;
        var idValue = "edmFile" + size;
        // 为 lable添加 for属性
        fileLabel.attr("for", idValue);
        // 为input file添加 id
        fileInput.attr("id", idValue);
        // 拼接file input
        fileLabel.text(labelContext);
        fileDiv.append(fileInput).append(fileLabel)
        // 将附件添加到表单中
        $(".form-group .btn-group #addFiles").parent().parent().append(fileDiv);
        // 触发添加按钮的事件
        fileButtonAddEvent();
    }

    /**
     * 改变“添加附件按钮”
     */
    function fileButtonAddEvent() {
        // 获取input[type='file']的数量
        var fileInputs = $(".form-group .btn-group #addFiles").parent().parent().children(".custom-file").children("input[type='file']");
        if (fileInputs.length > 0) {
            // 将 “添加附件” 变为 “继续添加”
            $(".form-group .btn-group #addFiles").text("继续添加附件");
            // 并添加 一个 “删除最后一个附件”
            addDeleteButton();
        }
    }

    function addDeleteButton() {
        var deleteButton = $(".form-group .btn-group #deleteLastOneFile");
        if (deleteButton.length == 0) {
            var deleteLastOneFile = $("<button type='button' class='btn btn-info ml-2' id='deleteLastOneFile'>删除最后一个附件</button>");
            $(".form-group .btn-group #addFiles").after(deleteLastOneFile);
            // 对删除按钮添加监听事件
            $(".form-group .btn-group #deleteLastOneFile").unbind("click", fileButtonRemoveEvent);
            $(".form-group .btn-group #deleteLastOneFile").bind("click", fileButtonRemoveEvent);
        }
    }
    /**
     * 改变 ““继续添加” 按钮
     */
    function fileButtonRemoveEvent() {
        var fileInputs = $(".form-group .btn-group #addFiles").parent().parent().children(".custom-file").children("input[type='file']").last();
        // 删除最后一个
        if (fileInputs.length > 0) {
            fileInputs.parent().remove();
            // var fileInputs =fileInputs.last();
            // console.log(lastInputFile.length);
        }
        var fileInputs = $(".form-group .btn-group #deleteLastOneFile").parent().parent().children(".custom-file").children("input[type='file']");
        if (fileInputs.length == 0) {
            // 将 “继续添加” 变为 “添加附件”
            $(".form-group .btn-group #addFiles").text("添加附件");
            // 去除删除附件的按钮
            $(".form-group .btn-group #deleteLastOneFile").remove();
        }
    }

});