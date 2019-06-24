package com.edm.edmfetchdataplatform.domain.translate;

import com.edm.edmfetchdataplatform.domain.EdmApplyFile;

import java.util.Arrays;
import java.util.List;

/**
 * @Date 2019-06-24
 * @Author lifei
 */
public class EdmLiuZhuanEmailParameters {

    /**
     * 邮件发送者
     * 不变量
     */
//    private String emailFrom;
    /**
     * 邮件接受者
     */
    private String emailTo;
    /**
     * 邮件抄送者
     */
    private String[] emailCc;

    /**
     * 邮件接受者的姓名
     */
    private String emailToUserName;

    /**
     * 群发流转单的名称
     */
    private String orderName;

    /**
     * 排期意向
     */
    private String paiQiYiXiang;

    private List<EdmApplyFile> edmApplyFiles;

    public EdmLiuZhuanEmailParameters() {
    }

    public EdmLiuZhuanEmailParameters(String emailTo, String[] emailCc, String emailToUserName,
                                      String orderName, String paiQiYiXiang) {
        this.emailTo = emailTo;
        this.emailCc = emailCc;
        this.emailToUserName = emailToUserName;
        this.orderName = orderName;
        this.paiQiYiXiang = paiQiYiXiang;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String[] getEmailCc() {
        return emailCc;
    }

    public void setEmailCc(String[] emailCc) {
        this.emailCc = emailCc;
    }

    public String getEmailToUserName() {
        return emailToUserName;
    }

    public void setEmailToUserName(String emailToUserName) {
        this.emailToUserName = emailToUserName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getPaiQiYiXiang() {
        return paiQiYiXiang;
    }

    public void setPaiQiYiXiang(String paiQiYiXiang) {
        this.paiQiYiXiang = paiQiYiXiang;
    }


    public List<EdmApplyFile> getEdmApplyFiles() {
        return edmApplyFiles;
    }

    public void setEdmApplyFiles(List<EdmApplyFile> edmApplyFiles) {
        this.edmApplyFiles = edmApplyFiles;
    }


    @Override
    public String toString() {
        return "EdmLiuZhuanEmailParameters{" +
                ", emailTo='" + emailTo + '\'' +
                ", emailCc=" + Arrays.toString(emailCc) +
                ", emailToUserName='" + emailToUserName + '\'' +
                ", orderName='" + orderName + '\'' +
                ", paiQiYiXiang='" + paiQiYiXiang + '\'' +
                ", edmApplyFiles=" + edmApplyFiles +
                '}';
    }
}
