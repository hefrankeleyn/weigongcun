package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.config.DataConfig;
import com.edm.edmfetchdataplatform.domain.*;
import com.edm.edmfetchdataplatform.domain.status.ExamineProgressState;
import com.edm.edmfetchdataplatform.domain.status.IncludeState;
import com.edm.edmfetchdataplatform.mapper.EdmApplyOrderMapper;
import com.edm.edmfetchdataplatform.service.*;
import com.edm.edmfetchdataplatform.tools.MyDateUtil;
import com.edm.edmfetchdataplatform.tools.MyIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 流转单 service
 *
 * @Date 2019-06-18
 * @Author lifei
 */
@Service
public class EdmApplyOrderServiceImpl implements EdmApplyOrderService {

    private Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.service.impl.EdmApplyOrderServiceImpl");

    @Autowired
    private EdmConditionService edmConditionService;

    @Autowired
    private EdmerService edmerService;

    @Autowired
    private EdmTargetDescriptionService edmTargetDescriptionService;

    @Autowired
    private DataConfig dataConfig;


    @Autowired
    private EdmApplyFileService edmApplyFileService;

    @Autowired(required = false)
    private EdmApplyOrderMapper edmApplyOrderMapper;

    /**
     * 对申请的订单进行初始化
     *
     * @param conId
     * @return
     */
    @Override
    @Transactional
    public EdmApplyOrder orderInit(Integer[] conId, String email) {
        // 获取用户
        Edmer edmer = edmerService.findEdmerByEmail(email);
        // 获取订单项
        List<EdmCondition> edmConditions = edmConditionService.findEdmConditionsByConIdsAndEid(conId, edmer.getEid());
        // 组合成订单
        EdmApplyOrder edmApplyOrder = initEdmApplyOrder(edmConditions, edmer);
        return edmApplyOrder;
    }

    /**
     * 保存流转单
     *
     * @param edmerEmail
     * @param edmFiles
     * @param edmApplyOrder
     */
    @Override
    @Transactional
    public void saveEdmApplyOrder(String edmerEmail, MultipartFile[] edmFiles, EdmApplyOrder edmApplyOrder) {
        // 获取用户
        Edmer edmer = edmerService.findEdmerByEmail(edmerEmail);
        // 创建 UUID， 流转单的主键
        String oid = MyIdGenerator.createUUID();
        // 保存流转单、保存流转单id与申请项的id
        edmApplyOrder.setOid(oid);
        // 流转单的状态， 添加准备审批的状态
        edmApplyOrder.setOrderState(ExamineProgressState.READY_EXAMINE.getStatus());
        // 流转但的时间 保存
        edmApplyOrder.setApplyDate(new Date());
        // 保存订单
        edmApplyOrderMapper.saveEdmApplyOrder(edmApplyOrder);
        // 保存订单项和订单之间的关系
        Integer[] conIds = edmApplyOrder.getConIds();
        if (conIds != null && conIds.length > 0) {
            List<EdmCondition> edmConditions = edmConditionService.findEdmConditionsByConIds(conIds);
            for (int i = 0; i < edmConditions.size(); i++) {
                edmConditions.get(i).setOid(oid);
            }
            // 保存和订单项之间的关系
            // 更新订单项
            edmConditionService.updateEdmConditions(edmConditions);
        }

        // 判断附件是否存在，并保存附件
        try {
            upLoadFileAndSaveEdmApplyFiles(edmFiles, oid);
        } catch (IOException e) {
            logger.info("文件上传失败。");
            e.printStackTrace();
        }

    }

    @Override
    public EdmApplyOrder findEdmApplyOrderByOid(String oid) {
        EdmApplyOrder edmApplyOrder = edmApplyOrderMapper.findEdmApplyOrderByOid(oid);
        return edmApplyOrder;
    }

    /**
     * 上传文件，并保存edmApplyFile
     *
     * @param edmFiles
     * @param oid
     */
    private void upLoadFileAndSaveEdmApplyFiles(MultipartFile[] edmFiles, String oid) throws IOException {
        // 上传文件的根目录
        String rootPath = dataConfig.getUpLoadPath();
        logger.info(rootPath);

        if (edmFiles != null && edmFiles.length > 0) {
            EdmApplyFile[] edmApplyFiles = new EdmApplyFile[edmFiles.length];

            for (int i = 0; i < edmFiles.length; i++) {
                String originalFilename = edmFiles[i].getOriginalFilename();
                String filePath = createUpLoadFilePath(rootPath);
                String fileName = createUpLoadFileName(originalFilename);
                edmApplyFiles[i] = new EdmApplyFile(fileName, filePath, originalFilename, oid);

                // 上传附件
                edmFiles[i].transferTo(new File(filePath + File.separator + fileName));
            }

            edmApplyFileService.saveEdmApplyFiles(edmApplyFiles);

        } else {
            logger.info("edmFiles is empty");
        }
    }

    /**
     * 根据真实的文件名创建 唯一的文件名
     *
     * @param realFileName
     * @return
     */
    private String createUpLoadFileName(String realFileName) {
        String datetimeStr = MyDateUtil.currentDatetimeStr();
        double random = Math.random();
        int r = (int) (random * 1000);
        String fileName = datetimeStr + "-" + r + realFileName.substring(realFileName.lastIndexOf("."));
        return fileName;
    }

    /**
     * 根据根目录创建 完整的上传目录
     *
     * @param rootPath
     * @return
     */
    private String createUpLoadFilePath(String rootPath) {
        String yearStr = MyDateUtil.currentYearStr();
        String filePath = rootPath + File.separator + yearStr;
        File file = new File(rootPath + File.separator + yearStr);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
        }
        return filePath;
    }


    /**
     * 对EdmApplyOrder 进行初始化
     *
     * @param edmConditions
     * @return
     */
    private EdmApplyOrder initEdmApplyOrder(List<EdmCondition> edmConditions, Edmer edmer) {

        if (edmConditions == null || edmConditions.size() == 0) {
            return null;
        }
        // 创建 edmApplyOrder
        EdmApplyOrder edmApplyOrder = new EdmApplyOrder();
        // 查询数据目标描述
        String[] targets = new String[edmConditions.size()];
        // 申请项的Id
        Integer[] conIds = new Integer[edmConditions.size()];
        for (int i = 0; i < edmConditions.size(); i++) {
            targets[i] = edmConditions.get(i).getDimension();
            conIds[i] = edmConditions.get(i).getConId();
        }

        // 将申请项的id 添加到 edmConditions中
        edmApplyOrder.setConIds(conIds);
        // 查询 EdmTargetDescription
        List<EdmTargetDescription> edmTargetDescriptions = edmTargetDescriptionService.findEdmTargetDescriptionsByTargets(targets);


        Map<String, String> targetDescription = new HashMap<>();
        for (int i = 0; i < edmTargetDescriptions.size(); i++) {
            targetDescription.put(edmTargetDescriptions.get(i).getTarget(), edmTargetDescriptions.get(i).getDescription());
        }

        // 拼接 eid
        edmApplyOrder.setEid(edmer.getEid());
        // 拼接组别、和用户名
        edmApplyOrder.setEdmerDepartment(edmer.getDepartment());
        edmApplyOrder.setEdmUserName(edmer.getUsername());
        // 拼接目标群发省份
        StringBuilder qunfaProvinceAndCityConditions = new StringBuilder();
        // 用户数据要求
        StringBuilder usersDataCondition = new StringBuilder();
        int sendNum = 0;
        for (int i = 0; i < edmConditions.size(); i++) {
            // 判断省份条件是否生效
            EdmCondition edmCondition = edmConditions.get(i);
            qunfaProvinceAndCityConditions.append("目标用户" + (i + 1) + ": ");

            if (excludeIf(edmCondition.getProvinceIf()) && excludeIf(edmCondition.getCityIf())) {
                qunfaProvinceAndCityConditions.append("全国。");
            } else {
                // 判断省份
                setQunFaProvinceValue(edmCondition.getProvinceOpt(), edmCondition.getProvinceNames(), qunfaProvinceAndCityConditions);
                // 判断城市
                setQunFaProvinceValue(edmCondition.getCityOpt(), edmCondition.getCityNames(), qunfaProvinceAndCityConditions);
            }
            // 添加换行符
            qunfaProvinceAndCityConditions.append("\n");

            //
            usersDataCondition.append("目标用户" + (i + 1) + ": ");

            // 根据
            usersDataCondition.append(targetDescription.get(edmCondition.getDimension()));
            usersDataCondition.append(", 提取 " + edmCondition.getLimitNum() + ";");
            usersDataCondition.append("\n");

            sendNum += edmCondition.getLimitNum();


        }
        // 群发省份
        edmApplyOrder.setTargetSendProvince(qunfaProvinceAndCityConditions.toString());
        edmApplyOrder.setUserConditions(usersDataCondition.toString());

        edmApplyOrder.setSendNum(sendNum);

        return edmApplyOrder;
    }

    /**
     * 判断是否不包含
     *
     * @param state
     * @return 如果不包含返回 true
     */
    private boolean excludeIf(Integer state) {
        return (state == null || state == IncludeState.EXCLUDE.getState());
    }

    /**
     * 拼接目标群发省份
     *
     * @param state
     * @param desc
     * @param sb
     */
    private void setQunFaProvinceValue(Integer state, String desc, StringBuilder sb) {
        if (state == IncludeState.INCLUDE.getState()) {
            sb.append("取 " + desc + "。");
        } else if (state == IncludeState.EXCLUDE.getState()) {
            sb.append("排除 " + desc + "。");
        }
    }
}
