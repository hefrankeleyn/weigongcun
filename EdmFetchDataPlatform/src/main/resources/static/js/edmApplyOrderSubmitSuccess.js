$(document).ready(function () {

    init();
    /**
     * 初始化
     */
    function init() {
        // 对响应页面的text进行初始化
        addText("邮件已经发送...", "流转单申请已经提交，邮件已经发送", "bg-warning");
        // 连接 topic/sendemailfeed 端点
        listenMessage();
    }

    /**
     * 为页面的text添加内容
     * @param headText
     * @param leadText
     */
    function addText(headText, leadText, bgColorClassName){
        $(".container .jumbotron .resultHead").text(headText);
        $(".container .jumbotron .lead").text(leadText);

        // 移除其他背景颜色
        if($(".container .jumbotron").hasClass("bg-warning")){
            $(".container .jumbotron").removeClass("bg-warning");
        }
        if ($(".container .jumbotron").hasClass("bg-danger")) {
            $(".container .jumbotron").removeClass("bg-danger");
        }
        if ($(".container .jumbotron").hasClass("bg-success")) {
            $(".container .jumbotron").removeClass("bg-success");
        }
        if (!$(".container .jumbotron").hasClass(bgColorClassName)) {
            $(".container .jumbotron").addClass(bgColorClassName);
        }
    }





    function listenMessage() {
        // 该url 与 配置类WebSocketStompConfig 方法 registerStompEndpoints 下的注册端点要一致
        var url = $.projectRootUrl() + "/websocketRabbitmq";
        var sock = new SockJS(url);
        var stomp = Stomp.over(sock);

        /**
         * 服务端使用 SimpMessagingTemplate 发送消息，消息将被路由到一个独特的目的地上
         * 注意，这个目的地使用了User作为前缀
         */
        stomp.connect($.myDataConfig.rabbitmq_username, $.myDataConfig.rabbitmq_password, function(){
            stomp.subscribe("/user/topic/sendemailfeed", changeLeadAndHeadText);
        },function (error) {
            console.error("stomp: " + error);
        });
    }

    /**
     * 修改 具有lead类和Head的内容
     */
    function changeLeadAndHeadText(incoming) {
        // console.log("incoming: " + incoming);
        var result = JSON.parse(incoming.body);
        var status = result.status;

        // 邮件发送成功
        if (status == 0){
            addText("流转单申请提交成功", "流转单申请提交成功，邮件发送成功", "bg-success");
        }else {
            // 邮件发送失败
            addText("邮件发送失败！", "流转单申请已经提交，邮件发送失败", "bg-danger");
        }
        console.log("Receive message success!");
    }

});