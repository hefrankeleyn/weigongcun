package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.base.query.EdmApplyOrderQuery;
import com.edm.edmfetchdataplatform.config.DataConfig;
import com.edm.edmfetchdataplatform.domain.*;
import com.edm.edmfetchdataplatform.domain.status.ExamineProgressState;
import com.edm.edmfetchdataplatform.domain.status.GroupRole;
import com.edm.edmfetchdataplatform.domain.status.IncludeState;
import com.edm.edmfetchdataplatform.domain.translate.EdmLiuZhuanEmailParameters;
import com.edm.edmfetchdataplatform.base.EdmPage;
import com.edm.edmfetchdataplatform.mapper.EdmApplyOrderMapper;
import com.edm.edmfetchdataplatform.service.*;
import com.edm.edmfetchdataplatform.tools.*;
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
     * 流转单的结果
     */
    @Autowired
    private EdmApplyOrderCheckResultService edmApplyOrderCheckResultService;

    /**
     * 用于开启一个线程发送邮件，并发送STOMP消息
     */
    @Autowired
    private StompSendEmailService stompSendEmailService;

    @Autowired
    private EdmAlertService edmAlertService;


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

            Edmer edmer = null;
            if (edmApplyOrder.getEdmer() != null && edmApplyOrder.getEdmer().getEid() != null) {
                edmer = edmerService.findEdmerByEid(edmApplyOrder.getEdmer().getEid());
            } else {
                edmer = edmerService.findEdmerByEmail(edmerEmail);
            }
            // 创建 UUID， 流转单的主键
            String oid = MyIdGenerator.createUUID();
            // 保存流转单、保存流转单id与申请项的id
            edmApplyOrder.setOid(oid);
            // 流转单的状态， 添加准备审批的状态
            edmApplyOrder.setOrderState(ExamineProgressState.READY_EXAMINE.getStatus());
            // 流转但的时间 保存
            edmApplyOrder.setApplyDate(new Date());

            // 保存流转单申请者
            edmApplyOrder.setEdmer(edmer);


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


            edmApplyOrder.setEdmApplyOrderCheckResult(edmApplyOrderCheckResult);
            // 保存订单
            edmApplyOrderMapper.saveEdmApplyOrder(edmApplyOrder);


            // 将群发流转单生成excel
            // 当前时间的年月
            String currentYearMonthDayStr = MyDateUtil.toDateStr(new Date());
            String originalFilename = "《" + edmApplyOrder.getOrderName() + "》群发流转单-" + currentYearMonthDayStr + ".xlsx";
            EdmApplyFile edmApplyOrderExcel = edmExcelService.createEdmApplyExcelOrder(edmApplyOrder, edmApplyOrderCheckResult, uniqueFilePath, originalFilename);

            // 将excel插入到list的第一个元素
            edmApplyFileList.add(0, edmApplyOrderExcel);
            // 将 申请流转单excel文件及其其附件信息保存到表中
            EdmApplyFile[] saveEdmApplyFiles = new EdmApplyFile[edmApplyFileList.size()];
            edmApplyFileService.saveEdmApplyFiles(edmApplyFileList.toArray(saveEdmApplyFiles));


            // 给申请组组长发送邮件，并抄送给申请人
            EdmLiuZhuanEmailParameters edmLiuZhuanEmailParameters = new EdmLiuZhuanEmailParameters(edmApplyOrder.getOrderState());
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
            // 添加组名
            edmLiuZhuanEmailParameters.setGroupName(edmApplyOrder.getEdmer().getDepartment());
            // 启动一个线程发邮件
            stompSendEmailService.sendEmailWithRunnerAndSTOMPMessage(edmerEmail, edmLiuZhuanEmailParameters);
        } catch (IOException e) {
            logger.info("文件上传失败。");
            throw new RuntimeException(e);
        }


    }

    /**
     * 修改流转单的状态
     *
     * @param edmApplyOrder
     */
    @Override
    @Transactional
    public void updateEdmApplyOrderStatus(EdmApplyOrder edmApplyOrder) {
        edmApplyOrderMapper.updateEdmApplyOrderStateByOid(edmApplyOrder);
    }

    @Override
    public EdmApplyOrder findEdmApplyOrderByOid(String oid) {
        EdmApplyOrder edmApplyOrder = edmApplyOrderMapper.findEdmApplyOrderByOid(oid);
        return edmApplyOrder;
    }

    /**
     * 根据ocid 查询EdmApplyOrder
     *
     * @param ocid
     * @return
     */
    @Override
    public EdmApplyOrder findEdmApplyOrderByOcid(String ocid) {
        if (!MyStrUtil.isEmptyOrNull(ocid)) {
            EdmApplyOrder edmApplyOrder = edmApplyOrderMapper.findEdmApplyOrderByOcid(ocid);
            return edmApplyOrder;
        }
        return null;
    }

    /**
     * 根据用户邮箱查询该用户申请的流转单
     *
     * @param email
     * @return
     */
    @Override
    public List<EdmApplyOrder> findEdmApplyOrdersByEmail(String email) {
        Edmer edmer = edmerService.findEdmerByEmail(email);
        if (edmer != null) {
            return findEdmApplyOrdersByEid(edmer.getEid());
        }
        return null;
    }


    /**
     * 根据eid查询 EdmApplyOrders
     *
     * @param eid
     * @return
     */
    @Override
    public List<EdmApplyOrder> findEdmApplyOrdersByEid(Integer eid) {
        if (eid != null) {
            List<EdmApplyOrder> edmApplyOrders = edmApplyOrderMapper.findEdmApplyOrdersByEid(eid);
            return edmApplyOrders;
        }
        return null;
    }


    /**
     * 查询邮箱用户一页 EdmApplyOrder
     *
     * @param email
     * @return
     */
    @Override
    public EdmPage<EdmApplyOrder> findPageEdmApplyOrdersByEmail(String email) {
        Edmer edmer = edmerService.findEdmerByEmail(email);
        EdmApplyOrderQuery edmApplyOrderQuery = new EdmApplyOrderQuery();
        if (edmer != null) {
            edmApplyOrderQuery.setEid(edmer.getEid());
        }
        return findPageEdmApplyOrdersByQuery(edmApplyOrderQuery);
    }

    /**
     * 根据流转单id 重新生成excel，并返回excel的File对象
     *
     * @param oid
     * @return
     */
    @Override
    @Transactional
    public File getEdmApplyOrderExcelByOid(String oid) {
        // 根据oid 查询 EdmApplyOrder
        EdmApplyOrder edmApplyOrder = edmApplyOrderMapper.findEdmApplyOrderByOid(oid);

        // 获取excel 文件
        List<EdmApplyFile> edmApplyFiles = edmApplyOrder.getEdmApplyFiles();
        File file = null;
        for (EdmApplyFile edmApplyFile : edmApplyFiles) {
            if (edmApplyFile.getFlag() == 1) {
                file = new File(edmApplyFile.getFilepath() + File.separator + edmApplyFile.getFilename());
                // 重新生成excel
                // 删除已经存在的excel
                if (file.exists()) {
                    file.deleteOnExit();
                }

                // 重新生成excel
                // 将群发流转单生成excel
                EdmApplyFile updateApplyFile = edmExcelService.createEdmApplyExcelOrder(edmApplyOrder,
                        edmApplyOrder.getEdmApplyOrderCheckResult(),
                        edmApplyFile.getFilepath(),
                        edmApplyFile.getOriginalfilename());
                updateApplyFile.setFid(edmApplyFile.getFid());
                // 更新edmApplyFile
                edmApplyFileService.updateEdmApplyFile(updateApplyFile);
                file = new File(updateApplyFile.getFilepath() + File.separator + updateApplyFile.getFilename());
            }
        }
        return file;
    }


    @Override
    public List<Integer> findOptOrderStatusByRoles(List<Role> roles) {
        if (roles != null && !roles.isEmpty()) {
            List<Integer> optStateList = new ArrayList<>();
            for (int i = 0; i < roles.size(); i++) {
                //  申请组权限
                if (GroupRole.ROLE_APPLY.getRoleName().equals(roles.get(i).getRoleName())) {
                    // 等待申请组审核
                    optStateList.add(ExamineProgressState.READY_EXAMINE.getStatus());
                } else if (GroupRole.ROLE_CAPACITY.getRoleName().equals(roles.get(i).getRoleName())) {
                    // 等待能力组审核
                    optStateList.add(ExamineProgressState.APPLY_GROUP_EXAMINE_SUCCESS.getStatus());
                } else if (GroupRole.ROLE_CUSTOMER_SERVICE.getRoleName().equals(roles.get(i).getRoleName())) {
                    // 等待客户组审核
                    optStateList.add(ExamineProgressState.POWER_GROUP_EXAMINE_SUCCESS.getStatus());
                } else if (GroupRole.ROLE_SHUJU.getRoleName().equals(roles.get(i).getRoleName())) {
                    // 等待数据组处理
                    optStateList.add(ExamineProgressState.SERVICES_GROUP_EXAMINE_SUCCESS.getStatus());
                } else if (GroupRole.ROLE_EDM.getRoleName().equals(roles.get(i).getRoleName())) {
                    // 赋予开发者权限
                    optStateList.add(ExamineProgressState.READY_EXAMINE.getStatus());
                    optStateList.add(ExamineProgressState.APPLY_GROUP_EXAMINE_FAIL.getStatus());
                    optStateList.add(ExamineProgressState.POWER_GROUP_EXAMINE_FAIL.getStatus());
                    optStateList.add(ExamineProgressState.POWER_GROUP_EXAMINE_SUCCESS.getStatus());
                    optStateList.add(ExamineProgressState.SERVICES_GROUP_EXAMINE_FAIL.getStatus());
                    optStateList.add(ExamineProgressState.SERVICES_GROUP_EXAMINE_SUCCESS.getStatus());
                    optStateList.add(ExamineProgressState.DATA_GROUP_EXAMINE_SUCCESS.getStatus());
                }
            }
            if (optStateList.isEmpty()) {
                return null;
            } else {
                return optStateList;
            }
        }
        return null;
    }

    /**
     * 根据查询条件获取数据量
     *
     * @param edmApplyOrderQuery
     * @return
     */
    @Override
    public Integer countEdmApplyOrderByBEdmApplyOrderQuery(EdmApplyOrderQuery edmApplyOrderQuery) {

        Integer totalNum = edmApplyOrderMapper.countEdmApplyOrdersByEdmApplyOrderQuery(edmApplyOrderQuery);
        return totalNum;
    }

    /**
     * 根据查询条件类， 获取一页 EdmApplyOrder 数据
     *
     * @param edmApplyOrderQuery
     * @return
     */
    @Override
    public EdmPage<EdmApplyOrder> findPageEdmApplyOrdersByEdmApplyOrderQuery(EdmApplyOrderQuery edmApplyOrderQuery) {
        // 查询总的记录条数
        Integer totalNum = countEdmApplyOrderByBEdmApplyOrderQuery(edmApplyOrderQuery);
        // 查询一页数据
        EdmPage<EdmApplyOrder> edmPage = new EdmPage<>(totalNum, edmApplyOrderQuery.getCurrentPage(), edmApplyOrderQuery.getPageSize());
        List<EdmApplyOrder> edmApplyOrders = edmApplyOrderMapper.findPageEdmApplyOrdersByEdmApplyOrderQuery(edmApplyOrderQuery,
                edmPage.getCurrentPageFirstItemNum(), edmPage.getPageSize());
        edmPage.setPageObjList(edmApplyOrders);

        return edmPage;
    }

    /**
     * 根据eid和orderStatusArray 查询 EdmApplyOrder
     *
     * @param eid
     * @param orderStatusArray
     * @return
     */
    @Override
    public List<EdmApplyOrder> findEdmApplyOrderByEidAndOrderStatusArray(Integer eid, Integer[] orderStatusArray) {
        if (eid != null && orderStatusArray != null && orderStatusArray.length > 0) {
            List<EdmApplyOrder> edmApplyOrderList = edmApplyOrderMapper.findEdmApplyOrdersByEidAndOrderStatus(eid, orderStatusArray);
            return edmApplyOrderList;
        }
        return null;
    }

    /**
     * 执行提数操作
     *
     * @param oid
     * @description 1、根据oid查询OrderState；
     * 2、判断OrderState是否审核完成；
     * 3、如果审核完成将状态修改为提取中；
     * 4、进行提数操作
     */
    @Override
    public void executeFetchDataOption(String oid) {
        if (!MyStrUtil.isEmptyOrNull(oid)) {
            EdmApplyOrder edmApplyOrder = findEdmApplyOrderByOid(oid);
            if (edmApplyOrder.getOrderState() == ExamineProgressState.SERVICES_GROUP_EXAMINE_SUCCESS.getStatus()){
                edmApplyOrder.setOrderState(ExamineProgressState.EXECUTE_DURING.getStatus());
                updateEdmApplyOrderStatus(edmApplyOrder);
                // 将该订单发送到消息队列，进行处理
                edmAlertService.sendEdmApplyOrder(edmApplyOrder);
            }
        }
    }

    /**
     * 查询一页
     *
     * @param edmApplyOrderQuery
     * @return
     */
    @Override
    public EdmPage<EdmApplyOrder> findPageEdmApplyOrdersByQuery(EdmApplyOrderQuery edmApplyOrderQuery) {
        // 查询总的条数
        Integer totalNum = edmApplyOrderMapper.countEdmApplyOrdersByEdmApplyOrderQuery(edmApplyOrderQuery);
        EdmPage<EdmApplyOrder> edmPage = new EdmPage<>(totalNum, edmApplyOrderQuery.getCurrentPage(), edmApplyOrderQuery.getPageSize());
        List<EdmApplyOrder> edmApplyOrders = edmApplyOrderMapper.findPageEdmApplyOrdersByEdmApplyOrderQuery(edmApplyOrderQuery, edmPage.getCurrentPageFirstItemNum(), edmPage.getPageSize());
        edmPage.setPageObjList(edmApplyOrders);
        return edmPage;
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
     *
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
        // 申请项的Id
        Integer[] conIds = new Integer[edmConditions.size()];
        for (int i = 0; i < edmConditions.size(); i++) {
            conIds[i] = edmConditions.get(i).getConId();
        }

        // 将申请项的id 添加到 edmConditions中
        edmApplyOrder.setConIds(conIds);

        // 申请人
        edmApplyOrder.setEdmer(edmer);
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
                // 判断省份 和城市
                setQunFaProvinceAndCityValue(edmCondition.getProvinceOpt(), edmCondition.getCityOpt(),
                        edmCondition.getProvinceNames(), edmCondition.getCityNames(),
                        qunfaProvinceAndCityConditions);

            }
            // 添加换行符
            qunfaProvinceAndCityConditions.append(";\r\n");

            //
            usersDataCondition.append("目标用户" + (i + 1) + ": ");

            // 根据
            String[] dimensions = MyArrayUtil.strToArray(edmCondition.getDimensions());
            String[] descriptions = new String[dimensions.length];
            for (int x = 0; x < dimensions.length; x++) {
                descriptions[x] = edmTargetDescriptionService.findDescriptionByTarget(dimensions[x]);
            }
            usersDataCondition.append(MyArrayUtil.arrayToStr(descriptions));
            // 判断是否需要排除数据编码
            if (edmCondition.getDataCodes() != null && !edmCondition.getDataCodes().trim().equals("")) {
                usersDataCondition.append(",排除数据编码\"" + edmCondition.getDataCodes() + "\"");
            }
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
     * 拼接省份和城市标识
     *
     * @param provinceStatue
     * @param cityState
     * @param provinceDesc
     * @param cityDesc
     * @param sb
     */
    private void setQunFaProvinceAndCityValue(Integer provinceStatue, Integer cityState, String provinceDesc, String cityDesc, StringBuilder sb) {
        // 省份信息
        if (provinceStatue == IncludeState.INCLUDE.getState()) {
            sb.append("包含省份：" + provinceDesc);
        } else if (provinceStatue == IncludeState.EXCLUDE.getState()) {
            sb.append("排除省份：" + provinceDesc);
        }
        // 添加对城市操作的信息
        if (cityState == IncludeState.INCLUDE.getState()) {
            // 添加逗号
            ifProvinceSpilt(provinceStatue, sb);
            sb.append("包含城市：" + cityDesc);
        } else if (cityState == IncludeState.EXCLUDE.getState()) {
            // 添加逗号
            ifProvinceSpilt(provinceStatue, sb);
            sb.append("排除城市：" + cityDesc);
        }
    }

    /**
     * @param provinceStatue
     * @param sb
     */
    private void ifProvinceSpilt(Integer provinceStatue, StringBuilder sb) {
        // 添加分割号
        if (provinceStatue == IncludeState.INCLUDE.getState() || provinceStatue == IncludeState.EXCLUDE.getState()) {
            sb.append("，");
        }
    }
}
