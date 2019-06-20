package com.edm.edmfetchdataplatform.domain.status;

/**
 * @Date 2019-06-18
 * @Author lifei
 */
public class QunFaTypeFactory {

    public static String fetchQunFaTypeByTypeState(Integer typeState){
        if(typeState == QunFaType.SHOU_RU.getTypState()){
            return QunFaType.SHOU_RU.getTypeDescription();
        }else if (typeState == QunFaType.LA_HUO.getTypState()){
            return QunFaType.LA_HUO.getTypeDescription();
        }else if (typeState == QunFaType.WEI_XI.getTypState()){
            return QunFaType.WEI_XI.getTypeDescription();
        }else if (typeState == QunFaType.ZHANGDAN_EMAIL.getTypState()){
            return QunFaType.ZHANGDAN_EMAIL.getTypeDescription();
        }else if (typeState == QunFaType.ZHANGDAN_DUAN.getTypState()){
            return QunFaType.ZHANGDAN_DUAN.getTypeDescription();
        }else if (typeState == QunFaType.EMAIL_AND_DUAN.getTypState()){
            return QunFaType.EMAIL_AND_DUAN.getTypeDescription();
        }else{
            return QunFaType.PUSH.getTypeDescription();
        }
    }
}
