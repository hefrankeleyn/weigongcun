package com.hef.mr.utils;

import org.junit.Test;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date 2019-09-28
 * @Author lifei
 */
public class RegularHandlerUtilTest {


    @Test
    public void proUtilTest(){
        String lineReString = DataCleaningPropertiesUtil.getValueByName("PMAIL_M_LOGREGULAREXPRESSION");
        System.out.println(lineReString);
    }

    /**
     * 测试解析一条数据
     */
    @Test
    public void reTestLine(){
        String line = "20190823000032#3855428496 pmail retr 0 10.199.82.120 user=\"18608047501@wo.cn\" levelID=0 ID=417";
        String lineReString = DataCleaningPropertiesUtil.getValueByName("PMAIL_LOGREGULAREXPRESSION");
        String splitReString = DataCleaningPropertiesUtil.getValueByName("PMAIL_SPLITREGULAREXPRESSION");
        String keyNames = DataCleaningPropertiesUtil.getValueByName("PMAIL_LOGKEYNAMES");
        String businessName = "PMAIL";
        Map<String, String> reMapResult = RegularHandlerUtil.reHandleLogWithEquality(line, businessName, keyNames, lineReString ,splitReString);
        for (Map.Entry<String, String> kv: reMapResult.entrySet()) {
            System.out.println(kv.getKey() +" : " + kv.getValue());
            System.out.println(kv.getValue());
        }
        String new_line = KeyCompareValueToLineUtil.keysCompareValuesToStr(keyNames.split(","), reMapResult);
        System.out.println(new_line);
    }

    /**
     * 测试feedback清洗
     */
    @Test
    public void reFeedBackLine(){
        String line = "20191001000002 INFO pool-3-thread-26 common.log.EventLog|NcWebHookReceiver|webHookReceiver|success|\"activityId\"=\"s4m90du414hdur97o2hdtchg2u_tankjeb5ncguhpva122crapj3d\"|\"channelId\"=\"10655462458\"|\"taskId\"=\"pRPeD3Kxm5jlNb1j5EA2Q8L9raYvwkJB\"|\"recipient\"=\"18662701357\"|\"sequenceId\"=\"2EAbASe_qLI15h\"|\"requestTime\"=\"20191001000002\"|\"senderExpandCode\"=\"10655462458\"|\"isUnicomMdn\"=\"true\"";
        line="20191001000237 INFO pool-5-thread-36 common.log.EventLog|LinkFeedback|view|success|\"taskId\"=\"kdr8mnQ3yBAzNoWl11GN4EvVJo9K6gDG\"|\"recipient\"=\"13102126182\"|\"sequenceId\"=\"R7uWNzlCODle3Y\"|\"link\"=\"null\"|\"adMailExternalId\"=\"null\"|\"emailTaskId\"=\"Xe1oARE64bMpqVbOGgGqlDK9ZOxV3nWa\"|\"emailTaskSequenceId\"=\"0DXjzzly9m7v7T:A\"";
        line="20190117193556 INFO pool-5-thread-50 common.log.EventLog|CompatibleFeedback|toMidPage|success|\"webLinkUrl\"=\"mail.wo.cn\"|\"wapLinkUrl\"=\"mail.wo.cn\"|\"date\"=\"201901171935982\"|\"taskId\"=\"YrEjRPdnze4p2w5858BqAVb3DQox6Mwa\"";
        line="20191001000133 INFO pool-5-thread-2 common.log.EventLog|ThirdSmsFeedbackApi|threeNetReport|success|\"recipient\"=\"8615649646761\"|\"smsStatus\"=\"2\"|\"sequenceNumber\"=\"F21100030P710010001270057957733Y\"|\"errorCode\"=\"21\"";
        line="20191001000003 INFO pool-5-thread-6 common.log.EventLog|SmsFeedbackApi|report|success|\"activityId\"=\"onre1m87ukj6mp726doj862di6_o7p54rit8iji0psvo8btqg8a66\"|\"tid\"=\"NC:TID:pD1aKYOwxJ3mNxWzmLM2XQyA5Bv8lLeP:18676102330:Er9S8LAeG1dv51:onre1m87ukj6mp726doj862di6_o7p54rit8iji0psvo8btqg8a66\"|\"taskId\"=\"pD1aKYOwxJ3mNxWzmLM2XQyA5Bv8lLeP\"|\"sequenceId\"=\"Er9S8LAeG1dv51\"|\"recipient\"=\"18676102330\"|\"sendDate\"=\"2019-10-01 00:00:03\"|\"status\"=\"0\"|\"requestTime\"=\"20191001000003\"";
        line="20191001000004 INFO pool-23-thread-2047485 common.log.EventLog|AbstractSendSuccessHandler|onMessage|success|\"channelId\"=\"10655462458\"|\"taskId\"=\"pRPeD3Kxm5jlNb1j5EA2Q8L9raYvwkJB\"|\"recipient\"=\"17605161165\"|\"recipientCount\"=\"1\"|\"sequenceId\"=\"kwq_Ud7cmBu77Z\"|\"taskTypeId\"=\"SMS\"|\"statusId\"=\"5\"|\"adMailExternalId\"=\"null\"|\"isUnicomMdn\"=\"true\"|\"sendDate\"=\"Tue Oct 01 00:00:02 CST 2019\"|\"activityId\"=\"4lat465ug0jv8q7a2qtal1n5kh_tankjeb5ncguhpva122crapj3d\"|\"requestTime\"=\"20191001000004\"|\"senderExpandCode\"=\"10655462458\"";
        line="20191001000002 INFO pool-25-thread-1502488 common.log.EventLog|EmailFeedbackHandler|messageReceived|processing|\"dataString\"=\"{\"id\":\"TID:5EYQvZdyDgG8NWblakY2mXozPaR1eKWO:15652067483:uY9ht95YE9f17w\",\"code\":\"OK\",\"description\":\"Mail successful sent.\"}\"|\"emailRn\"=\"RecipientNotification{taskId='5EYQvZdyDgG8NWblakY2mXozPaR1eKWO', channelId='10655462458', templateId='tankjeb5ncguhpva122crapj3d', templateContext='{\"mdn\":\"wIu2ywIKAr3YOkvV0MwgDA==\"}', recipientType='EMAIL', recipient='15652067483', statusId=2, sequenceId=uY9ht95YE9f17w, channelRequestId=null, sendDate=Tue Oct 01 00:00:01 CST 2019, createdDate=Tue Oct 01 00:00:00 CST 2019, lastUpdatedDate=Tue Oct 01 00:00:01 CST 2019, sendCountStatusId=1, adMailExternalId=null, smsAndEmailTemplateId=4lat465ug0jv8q7a2qtal1n5kh_tankjeb5ncguhpva122crapj3d}\"";
        line="20190719171204 INFO pool-5-thread-31 common.log.EventLog|ThirdEmailFeedbackApi|cn21EmailReportParams|success|\"appKey\"=\"JPwCdJiuI\"|\"paras\"=\"D3410125DD2A48219564B91A47150626F4C32C1CA3C4480D4B8220E2BF57873022B1AEB1C8F88D2F0B67D8718A1360DFBC7097035BE5CEE570604B604907699DD127EAA8543CF23EDBE6C88F719C60ADEBB10AB0502DF1516789AC62ECB6D545AFA2C13E003F13690AC9D1F7C2FC13FF964FAAF09949221492BCE131\"|\"sign\"=\"a21b84f6d0fb7dfeaceedab8f1e6d87d4663c4c8\"|\"body\"=\"sign=a21b84f6d0fb7dfeaceedab8f1e6d87d4663c4c8&appKey=JPwCdJiuI&paras=D3410125DD2A48219564B91A47150626F4C32C1CA3C4480D4B8220E2BF57873022B1AEB1C8F88D2F0B67D8718A1360DFBC7097035BE5CEE570604B604907699DD127EAA8543CF23EDBE6C88F719C60ADEBB10AB0502DF1516789AC62ECB6D545AFA2C13E003F13690AC9D1F7C2FC13FF964FAAF09949221492BCE131\"";
        line="20191002230003 INFO pool-25-thread-2301694 common.log.EventLog|EmailFeedbackHandler|messageReceived|processing|\"dataString\"=\"{\"id\":\"TID:Pj6pDkWnmXbl2Z4lKMMqx8RdezJAgKv9:18588731627:yFEI3RJY0KYOyT\",\"code\":\"ERROR_OTHERTRANSIENT\",\"description\":\"Other Transient error, try later.\"}\"|\"emailRn\"=\"RecipientNotification{taskId='Pj6pDkWnmXbl2Z4lKMMqx8RdezJAgKv9', channelId='10655462454', templateId='3l00u5kegsh53pnjotjg3ntnnr', templateContext='{\"mdn\":\"IGsEZObEUZiAl6clxcn4jA==\"}', recipientType='EMAIL', recipient='18588731627', statusId=2, sequenceId=yFEI3RJY0KYOyT, channelRequestId=null, sendDate=Wed Oct 02 23:00:02 CST 2019, createdDate=Wed Oct 02 22:54:53 CST 2019, lastUpdatedDate=Wed Oct 02 23:00:02 CST 2019, sendCountStatusId=1, adMailExternalId=null, smsAndEmailTemplateId=psssks3f7eip6pq7sp1fulc5ne_3l00u5kegsh53pnjotjg3ntnnr}\"";
        String businessName = "FEEDBACK";
        String lineReString = DataCleaningPropertiesUtil.getValueByName(businessName + "_LOGREGULAREXPRESSION");
        String splitReString = DataCleaningPropertiesUtil.getValueByName(businessName + "_SPLITREGULAREXPRESSION");
        String keyNames = DataCleaningPropertiesUtil.getValueByName(businessName + "_LOGKEYNAMES");
        Map<String, String> reMapResult = RegularHandlerUtil.reHandleLogWithEquality(line, businessName, keyNames, lineReString ,splitReString);
        for (Map.Entry<String, String> kv: reMapResult.entrySet()) {
            System.out.println(kv.getKey() +" : " + kv.getValue());
        }
        String new_line = KeyCompareValueToLineUtil.keysCompareValuesToStr(keyNames.split(","), reMapResult);
        System.out.println(new_line);
        System.out.println(line);
    }

    @Test
    public void matchTest(){
        String line = "20190823000032#3855428496 pmail retr 0 10.199.82.120 user=\"18608047501@wo.cn\" levelID=0 ID=417";
        String[] split = line.split("\\?<=\\s\\S+=");
        Pattern pattern = Pattern.compile("(\\s\\S+=)");
        String[] split1 = pattern.split(line);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()){
            System.out.println(line.substring(matcher.start(),matcher.end()));
        }

        for (String s :
                split1) {
            System.out.println(s);
        }
    }

    @Test
    public void removeSomeStrTest(){
        String st="\"aa\"";
        System.out.println(st);
        System.out.println(RegularHandlerUtil.trimStr(st,"\""));
    }
}
