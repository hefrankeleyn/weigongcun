package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.config.DataConfig;
import com.edm.edmfetchdataplatform.domain.*;
import com.edm.edmfetchdataplatform.domain.status.ExamineProgressState;
import com.edm.edmfetchdataplatform.domain.status.GroupRole;
import com.edm.edmfetchdataplatform.domain.status.IncludeState;
import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;
import com.edm.edmfetchdataplatform.mapper.EdmApplyOrderMapper;
import com.edm.edmfetchdataplatform.service.*;
import com.edm.edmfetchdataplatform.tools.MyArrayUtil;
import com.edm.edmfetchdataplatform.tools.MyDateUtil;
import com.edm.edmfetchdataplatform.tools.MyFileUtil;
import com.edm.edmfetchdataplatform.tools.MyIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
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

    @Autowired
    private EdmExcelService edmExcelService;

    /**
     * 用于发邮件
     */
    @Autowired
    private EdmSendEmailService edmSendEmailService;

    // 流转单的结果
    @Autowired
    private EdmApplyOrderCheckResultService edmApplyOrderCheckResultService;

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

        try {
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


            // 创建当前流转单的目录，该流转单及附件保存在同一个目录里面
            // 上传文件的根目录
            String rootPath = dataConfig.getUpLoadPath();
            // 根据根目录创建一个唯一的目录
            String uniqueFilePath = MyFileUtil.createUniqueFilePath(rootPath);


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

            // 判断附件是否存在，并保存附件 , 将附件保存在唯一的目录下
            List<EdmApplyFile> edmApplyFileList = upLoadMultipartFile(edmFiles, oid, uniqueFilePath);


            // 保存流转单的结果
            EdmApplyOrderCheckResult edmApplyOrderCheckResult = new EdmApplyOrderCheckResult();
            Edmer applyGroupEdmer = edmerService.findEdmerByEid(edmApplyOrder.getNextEdmerId());
            // 添加一个唯一的id
            edmApplyOrderCheckResult.setOcId(MyIdGenerator.createUUID());
            // 申请组组长的名称
            edmApplyOrderCheckResult.setFirstCheckerUserName(applyGroupEdmer.getUsername());
            // 排期
            edmApplyOrderCheckResult.setPaiQiResult(edmApplyOrder.getPaiQiYiXiang());
            edmApplyOrderCheckResult.setOid(oid);
            // 保存流转单的结果
            edmApplyOrderCheckResultService.saveEdmApplyOrderCheckResult(edmApplyOrderCheckResult);



            // 将群发流转单生成excel
            EdmApplyFile edmApplyOrderExcel = edmExcelService.createEdmApplyExcelOrder(edmApplyOrder, edmApplyOrderCheckResult, uniqueFilePath);

            // 将excel插入到list的第一个元素
            edmApplyFileList.add(0, edmApplyOrderExcel);
            // 将 申请流转单excel文件及其其附件信息保存到表中
            EdmApplyFile[] saveEdmApplyFiles = new EdmApplyFile[edmApplyFileList.size()];
            edmApplyFileService.saveEdmApplyFiles(edmApplyFileList.toArray(saveEdmApplyFiles));


            // 给申请组组长发送邮件，并抄送给申请人
            EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters = new EdmLiuZhuanEmailParameters();
            edmLiuZhuanEmailParameters.setEmailTo(applyGroupEdmer.getEmail());
            //  抄送者
            String[] emailCcs = new String[]{edmer.getEmail()};
            edmLiuZhuanEmailParameters.setEmailCc(emailCcs);
            // 邮件接收者的姓名
            edmLiuZhuanEmailParameters.setEmailToUserName(applyGroupEdmer.getUsername());
            // 群发流转单的名称
            edmLiuZhuanEmailParameters.setOrderName(edmApplyOrder.getOrderName());
            // 排期意向
            edmLiuZhuanEmailParameters.setPaiQiYiXiang(edmApplyOrder.getPaiQiYiXiang());
            // 添加附件
            edmLiuZhuanEmailParameters.setEdmApplyFiles(edmApplyFileList);
            // 发邮件
            logger.info(edmLiuZhuanEmailParameters.toString());
            edmSendEmailService.sendThymeleafEmail(edmLiuZhuanEmailParameters);





        } catch (IOException e) {
            logger.info("文件上传失败。");
            throw new RuntimeException(e);
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
     * @return 上传完成的edmApplyFiles
     */
    private List<EdmApplyFile> upLoadMultipartFile(MultipartFile[] edmFiles, String oid) throws IOException {
        // 上传文件的根目录
        String rootPath = dataConfig.getUpLoadPath();
        logger.info(rootPath);
        List<EdmApplyFile> edmApplyFiles = new ArrayList<>();
        if (edmFiles != null && edmFiles.length > 0) {
            for (int i = 0; i < edmFiles.length; i++) {
                String originalFilename = edmFiles[i].getOriginalFilename();
                String filePath = MyFileUtil.createUpLoadFilePath(rootPath);
                String fileName = MyFileUtil.createUpLoadFileName(originalFilename);
                edmApplyFiles.add(new EdmApplyFile(fileName, filePath, originalFilename, 0, oid));
                // 上传附件
                edmFiles[i].transferTo(new File(filePath + File.separator + fileName));
            }
        } else {
            logger.info("edmFiles is empty");
        }
        return edmApplyFiles;
    }

    /**
     * 上传到指定目录
     * @param edmFiles
     * @param oid
     * @param filePath
     * @return
     * @throws IOException
     */
    private List<EdmApplyFile> upLoadMultipartFile(MultipartFile[] edmFiles, String oid, String filePath) throws IOException {
        // 上传文件的根目录
        String rootPath = dataConfig.getUpLoadPath();
        logger.info(rootPath);
        List<EdmApplyFile> edmApplyFiles = new ArrayList<>();
        if (edmFiles != null && edmFiles.length > 0) {
            for (int i = 0; i < edmFiles.length; i++) {
                String originalFilename = edmFiles[i].getOriginalFilename();
                edmApplyFiles.add(new EdmApplyFile(originalFilename, filePath, originalFilename, 0, oid));
                // 上传附件
                edmFiles[i].transferTo(new File(filePath + File.separator + originalFilename));
            }
        } else {
            logger.info("edmFiles is empty");
        }
        return edmApplyFiles;
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
                setQunFaCityValue(edmCondition.getCityOpt(), edmCondition.getCityNames(), qunfaProvinceAndCityConditions);
            }
            // 添加换行符
            qunfaProvinceAndCityConditions.append("\r\n");

            //
            usersDataCondition.append("目标用户" + (i + 1) + ": ");

            // 根据
            usersDataCondition.append(targetDescription.get(edmCondition.getDimension()));
            usersDataCondition.append(", 提取 " + edmCondition.getLimitNum() + ";");
            usersDataCondition.append("\r\n");

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
            sb.append("包含省份：" + desc + "，  ");
        } else if (state == IncludeState.EXCLUDE.getState()) {
            sb.append("排除城市：" + desc + "，  ");
        }
    }

    /**
     * 拼接城市
     * @param state
     * @param desc
     * @param sb
     */
    private void setQunFaCityValue(Integer state, String desc, StringBuilder sb) {
        if (state == IncludeState.INCLUDE.getState()) {
            sb.append("包含城市：" + desc + "   ");
        } else if (state == IncludeState.EXCLUDE.getState()) {
            sb.append("排除城市：" + desc + "   ");
        }
    }
}
