$(document).ready(function () {

    init();
    /**
     * 初始化
     */
    function init() {
        // 对响应页面的text进行初始化
        addText("正在发送邮件...", "审核已经提交，正在发送邮件");
        // 连接 topic/sendemailfeed 端点
        listenMessage();
    }

    /**
     * 为页面的text添加内容
     * @param headText
     * @param leadText
     */
    function addText(headText, leadText){
        $(".container .jumbotron .resultHead").text(headText);
        $(".container .jumbotron .lead").text(leadText);
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
        stomp.connect("guest", "guest", function(){
            stomp.subscribe("/user/queue/sendemailfeed", changeLeadAndHeadText);
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
            addText("审核提交成功", "审核已经提交，邮件发送成功");
        }else {
            // 邮件发送失败
            addText("邮件发送失败！", "审核已经提交，邮件发送失败");
        }
        console.log("Receive message success!");
    }

});