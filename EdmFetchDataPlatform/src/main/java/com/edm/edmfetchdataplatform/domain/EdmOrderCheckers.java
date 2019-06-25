package com.edm.edmfetchdataplatform.domain;

/**
 * edm 流转单 核查者
 * @Date 2019-06-25
 * @Author lifei
 */
public class EdmOrderCheckers {

    /**
     * 初审人
     */
    private String chuShenChecker;

    /**
     * 能力组 审核人
     */
    private String capacityChecker;

    /**
     * 客服组 审核人
     */
    private String customerServiceChecker;

    /**
     * 数据组
     */
    private String shuJuGroup;

    /**
     * 数据组邮箱
     */
    private String shuJuGroupEmail;

    public EdmOrderCheckers() {
        this.chuShenChecker = "建伟";
        this.capacityChecker = "葛新宇";
        this.customerServiceChecker = "梁南";
        this.shuJuGroup = "数据";
        this.shuJuGroupEmail = "shuju@wo.cn";
    }

    public EdmOrderCheckers(String chuShenChecker, String capacityChecker,
                            String customerServiceChecker, String shuJuGroup,
                            String shuJuGroupEmail) {
        this.chuShenChecker = chuShenChecker;
        this.capacityChecker = capacityChecker;
        this.customerServiceChecker = customerServiceChecker;
        this.shuJuGroup = shuJuGroup;
        this.shuJuGroupEmail = shuJuGroupEmail;
    }

    public String getChuShenChecker() {
        return chuShenChecker;
    }

    public void setChuShenChecker(String chuShenChecker) {
        this.chuShenChecker = chuShenChecker;
    }

    public String getCapacityChecker() {
        return capacityChecker;
    }

    public void setCapacityChecker(String capacityChecker) {
        this.capacityChecker = capacityChecker;
    }

    public String getCustomerServiceChecker() {
        return customerServiceChecker;
    }

    public void setCustomerServiceChecker(String customerServiceChecker) {
        this.customerServiceChecker = customerServiceChecker;
    }

    public String getShuJuGroup() {
        return shuJuGroup;
    }

    public void setShuJuGroup(String shuJuGroup) {
        this.shuJuGroup = shuJuGroup;
    }

    public String getShuJuGroupEmail() {
        return shuJuGroupEmail;
    }

    public void setShuJuGroupEmail(String shuJuGroupEmail) {
        this.shuJuGroupEmail = shuJuGroupEmail;
    }


    @Override
    public String toString() {
        return "EdmOrderCheckers{" +
                "chuShenChecker='" + chuShenChecker + '\'' +
                ", capacityChecker='" + capacityChecker + '\'' +
                ", customerServiceChecker='" + customerServiceChecker + '\'' +
                ", shuJuGroup='" + shuJuGroup + '\'' +
                ", shuJuGroupEmail='" + shuJuGroupEmail + '\'' +
                '}';
    }
}
