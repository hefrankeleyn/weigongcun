package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-06-18
 * @Author lifei
 */
public class QunFaTypeFactory {

    public static String fetchQunFaTypeByTypeState(Integer typeState){
        if(typeState == 0){
            return QunFaType.SHOU_RU.getTypeDescription();
        }else if (typeState == 1){
            return QunFaType.LA_HUO.getTypeDescription();
        }else if (typeState == 2){
            return QunFaType.WEI_XI.getTypeDescription();
        }else if (typeState == 3){
            return QunFaType.ZHANGDAN_EMAIL.getTypeDescription();
        }else if (typeState == 4){
            return QunFaType.ZHANGDAN_DUAN.getTypeDescription();
        }else if (typeState == 5){
            return QunFaType.EMAIL_AND_DUAN.getTypeDescription();
        }else{
            return QunFaType.PUSH.getTypeDescription();
        }
    }
}
