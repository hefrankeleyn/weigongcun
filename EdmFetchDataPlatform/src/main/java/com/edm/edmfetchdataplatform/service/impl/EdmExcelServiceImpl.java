package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.config.DataConfig;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmOrderCheckers;
import com.edm.edmfetchdataplatform.service.EdmExcelService;
import com.edm.edmfetchdataplatform.tools.MyDateUtil;
import com.edm.edmfetchdataplatform.tools.MyFileUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @Date 2019-06-24
 * @Author lifei
 */
@Service
public class EdmExcelServiceImpl implements EdmExcelService {

    // 获取logger
    private static final Logger logger = Logger.getLogger("com.edm.edmfetchdataplatform.service.impl.EdmExcelServiceImpl");

    @Autowired
    private DataConfig dataConfig;


    @Override
    public void createEdmApplyExcelOrder(EdmApplyOrder edmApplyOrder, EdmOrderCheckers edmOrderCheckers) {
        // 当前时间的年月
        String currentYearMonthDayStr = MyDateUtil.toDateStr(new Date());
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("流转单");
        // 行对象
        HSSFRow row = null;
        // 单元格对象
        HSSFCell cell = null;
        // 第一行： 邮箱事业部群发流转单 A1~E1
        // 合并单元格 A1~E1
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
        // 创建第一行
        row= sheet.createRow(0);
        // 创建一个单元格
        cell = row.createCell(0);
        //setCellValue("邮箱事业部群发流转单");

        // 第二行： 编号  日期  下一个环节
        // 合并： A1~B1
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
        // 合并： C1~D1
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
        // 创建第二行
        row = sheet.createRow(1);
        // 创建单元格, 编号
        cell = row.createCell(0);
        cell.setCellValue("编号: ");
        // 创建单元格, 日期
        cell = row.createCell(2);
        cell.setCellValue("日期: " + currentYearMonthDayStr);

          // 创建单元格, 下一个环节
        cell = row.createCell(4);
        cell.setCellValue("下一个环节");

        // 第三行： 申请人 申请人及组别
        // 合并行： A3~A10
        sheet.addMergedRegion(new CellRangeAddress(2, 9, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(2, 9, 4, 4));
        // 创建行
        row = sheet.createRow(2);
        // 创建单元格： 申请人
        cell = row.createCell(0);
        cell.setCellValue("申请人");
        // 发送给所在组组长
        cell = row.createCell(4);
        cell.setCellValue("发所在组组长");

        // 申请人及组别
        createRowThreeToTowTenAndCellOneToCellFour(row, "申请人及组别", "\"群发类型及群发方式\n" +
                "（收入/拉活/维系/账单-\n" +
                "邮/短/邮+短/PUSH）\"\n", "群发主题及短信内容");


        // 创建第四行 申请人及组别 群发类型 群发主题及短信内偶然
        createRowThreeToTowTenAndCellOneToCellFour(sheet, 3, edmApplyOrder.getEdmerDepartment() + "-" + edmApplyOrder.getEdmUserName(),
                edmApplyOrder.getQunFaTypeDescription(), edmApplyOrder.getQunFaSubjectAndContext());
        // 创建第五行
        // 排期意向	目标群发省	用户数据要求
        createRowThreeToTowTenAndCellOneToCellFour(sheet,4, "排期意向",
                "目标群发省", "用户数据要求");
        //  创建第六行
        createRowThreeToTowTenAndCellOneToCellFour(sheet,5, edmApplyOrder.getPaiQiYiXiang(),
                edmApplyOrder.getTargetSendProvince(), edmApplyOrder.getUserConditions());
        // 创建第七行  发送量	投递通道	目标用户不足时如何处理
        createRowThreeToTowTenAndCellOneToCellFour(sheet,6, "发送量",
                "投递通道", "目标用户不足时如何处理");
        // 创建第八行
        createRowThreeToTowTenAndCellOneToCellFour(sheet,7, edmApplyOrder.getSendNum() + "",
                edmApplyOrder.getChannelSends(), edmApplyOrder.getHowSupplement());

        // 创建第九行  短信内容
        row = sheet.createRow(8);
        cell = row.createCell(1);
        cell.setCellValue("短信内容");

        row = sheet.createRow(9);
        cell = row.createCell(1);
        cell.setCellValue(edmApplyOrder.getMessageContext());



        // 创建第十行 申请组组长
        createCheckAndResultRows(sheet, 10, "申请组组长", "发能力组，\r\n 抄申请人",
                "内容初审","","",
                edmOrderCheckers.getChuShenChecker(),"","");

        // 创建第十一行， "能力组 //葛兴羽"
        createCheckAndResultRows(sheet, 12,
                "能力组\r\n" + edmOrderCheckers.getCapacityChecker(), "发客服组，\r\n抄申请人",
                "排期结果","内容复审", "",
                edmApplyOrder.getPaiQiYiXiang(), "", "");

        // 创建十二行， "客服组 梁南"
        createCheckAndResultRows(sheet, 14,
                "客服组\r\n" + edmOrderCheckers.getCustomerServiceChecker(), "审核通过：\r\n" +
                        "发数据组，抄申请人\r\n" +
                        "审核不通过：\r\n" +
                        "返回申请人",
                "排期确认","群发方案确认", "",
                "", "", "请剔除黑名单用户和省分");

        // 创建第十三行， "数据组
        //数据
        //(shuju@wo.cn)"
        createCheckAndResultRows(sheet, 16,
                "数据组\r\n" + edmOrderCheckers.getShuJuGroup() + "\r\n (" + edmOrderCheckers.getShuJuGroupEmail() + ")","发申请人，由申请人执行群发任务",
                "用户数据链接\r\n" +
                        "（分省用户明细可附表）","实际用户数据属性说明", "实际用户数量",
                "", "", "");

        // 第十四行
        sheet.addMergedRegion(new CellRangeAddress(18, 18, 1, 3));
        row = sheet.createRow(18);
        createFirstCellAndLastCell(row, "备注说明", "结束");


        // 生成excel
        // 文件路径
        // 上传文件的根目录
        String originalFilename = "《"+edmApplyOrder.getOrderName()+"》群发流转单-"+ currentYearMonthDayStr + ".xlsx";
        // 获取要上传的目录
        String rootPath = dataConfig.getUpLoadPath();
        String filePath = MyFileUtil.createUpLoadFilePath(rootPath);
        // 生成一个文件名
        String fileName = MyFileUtil.createUpLoadFileName(originalFilename);
        File file = new File(filePath + File.separator + fileName);
        // 如果文件存在，就删除文件
        if (file.exists()){
            file.delete();
        }

        logger.info(file.toString());
        // 该语法保证 close 一定会关闭
        try(OutputStream outputStream = new FileOutputStream(file)){
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建第三行到到十行，每行的第2个到第三个单元格
     * @param sheet
     * @param rowNum
     * @param cellTwoValue
     * @param cellThreeValue
     * @param cellFourValue
     */
    private void createRowThreeToTowTenAndCellOneToCellFour(HSSFSheet sheet,
                                                            int rowNum,
                                                            String cellTwoValue,
                                                            String cellThreeValue,
                                                            String cellFourValue){
        HSSFRow row = sheet.createRow(rowNum);
        createRowThreeToTowTenAndCellOneToCellFour(row, cellTwoValue, cellThreeValue, cellFourValue);
    }

    /**
     * 创建第三行到到十行，每行的第2个到第三个单元格
     * @param row
     * @param cellTwoValue
     * @param cellThreeValue
     * @param cellFourValue
     */
    private void createRowThreeToTowTenAndCellOneToCellFour(HSSFRow row ,
                                                            String cellTwoValue,
                                                            String cellThreeValue,
                                                            String cellFourValue){

        HSSFCell cell = null;
        cell = row.createCell(1);
        cell.setCellValue(cellTwoValue);
        cell = row.createCell(2);
        cell.setCellValue(cellThreeValue);
        cell = row.createCell(3);
        cell.setCellValue(cellFourValue);
    }

    /**
     * 创建每一行第一个单元格，和最后一个单元格
     * @param row
     */
    private void createFirstCellAndLastCell(HSSFRow row, String zeroCellValue, String fiveCellValue){
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(zeroCellValue);
        cell = row.createCell(4);
        cell.setCellValue(fiveCellValue);
    }


    /**
     * excel中 核查结果
     * @param sheet
     * @param rowNum
     * @param firstCellValue
     * @param lastCellValue
     * @param oneRowCellOneValue
     * @param oneRowCellTwoValue
     * @param oneRowCellThreeValue
     * @param twoRowCellOneValue
     * @param twoRowCellTwoValue
     * @param twoRowCellThreeValue
     */
    private void createCheckAndResultRows(HSSFSheet sheet,
                                          int rowNum,
                                          String firstCellValue,
                                          String lastCellValue,
                                          String oneRowCellOneValue,
                                          String oneRowCellTwoValue,
                                          String oneRowCellThreeValue,
                                          String twoRowCellOneValue,
                                          String twoRowCellTwoValue,
                                          String twoRowCellThreeValue
                                          ){
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 4, 4));

        HSSFRow row = sheet.createRow(rowNum);

        createFirstCellAndLastCell(row, firstCellValue, lastCellValue);

        createRowThreeToTowTenAndCellOneToCellFour(row, oneRowCellOneValue,
                oneRowCellTwoValue, oneRowCellThreeValue);

        row = sheet.createRow(rowNum + 1);
        createRowThreeToTowTenAndCellOneToCellFour(row, twoRowCellOneValue,
                twoRowCellTwoValue, twoRowCellThreeValue);
    }



}
