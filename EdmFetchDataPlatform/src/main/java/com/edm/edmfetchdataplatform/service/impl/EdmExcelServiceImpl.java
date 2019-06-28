package com.edm.edmfetchdataplatform.service.impl;

import com.edm.edmfetchdataplatform.config.DataConfig;
import com.edm.edmfetchdataplatform.domain.EdmApplyFile;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrder;
import com.edm.edmfetchdataplatform.domain.EdmApplyOrderCheckResult;
import com.edm.edmfetchdataplatform.service.EdmExcelService;
import com.edm.edmfetchdataplatform.tools.MyDateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
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


    /**
     * 生成 群发流转单excel
     * 为合并的单元格设置样式，应当在其它相关单元格内容设置完成之后设置
     * 并生成的excel放到指定的目录下
     *
     * @param edmApplyOrder
     * @param edmApplyOrderCheckResult
     * @return EdmApplyFile
     */
    @Override
    public EdmApplyFile createEdmApplyExcelOrder(EdmApplyOrder edmApplyOrder, EdmApplyOrderCheckResult edmApplyOrderCheckResult, String filePath) {
        // 当前时间的年月
        String currentYearMonthDayStr = MyDateUtil.toDateStr(new Date());
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("流转单");
        // 设置默认宽和高
        sheet.setDefaultColumnWidth(12 * 255);
        sheet.setDefaultRowHeight((short) 3000);

        // 设置单元格的宽
        setColumnWidthAndHeight(sheet);
        // 行对象
        HSSFRow row = null;
        // 单元格对象
        HSSFCell cell = null;
        // 第一行： 邮箱事业部群发流转单 A1~E1
        // 合并单元格 A1~E1
        CellRangeAddress cellRangeAddress = null;
        cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);
        sheet.addMergedRegion(cellRangeAddress);

        // 创建第一行
        row = sheet.createRow(0);
        // 设置行高
        row.setHeight((short) (23.25 * 20));
        // 创建一个单元格
        cell = row.createCell(0);
        cell.setCellValue("邮箱事业部群发流转单");

        // 设置单元格
        // 上下居中， 加粗，字号14
        cell.setCellStyle(setCellStyle(workbook, HorizontalAlignment.CENTER, true, (short) 14));
        // 设置合并单元格的样式
        addBorderForRegion(cellRangeAddress, sheet);


        // 第二行： 编号  日期  下一个环节
        // 合并： A1~B1
        CellRangeAddress cellRangeAddress1 = new CellRangeAddress(1, 1, 0, 1);
        sheet.addMergedRegion(cellRangeAddress1);
        // 合并： C1~D1
        CellRangeAddress cellRangeAddress2 = new CellRangeAddress(1, 1, 2, 3);
        sheet.addMergedRegion(cellRangeAddress2);
        // 创建第二行
        row = sheet.createRow(1);
        // 设置行高
        row.setHeight((short) (19.5 * 20));
        // 创建单元格, 编号
        cell = row.createCell(0);
        cell.setCellValue("编号: " + edmApplyOrder.getOid());
        // 设置单元格样式
        // 左右居左， 加粗，字号14
        cell.setCellStyle(setCellStyle(workbook, HorizontalAlignment.LEFT, true, (short) 11));

        // 创建单元格, 日期
        cell = row.createCell(2);
        cell.setCellValue("日期: " + currentYearMonthDayStr);
        // 左右居左， 加粗，字号14
        cell.setCellStyle(setCellStyle(workbook, HorizontalAlignment.LEFT, true, (short) 11));
        // 创建单元格, 下一个环节
        cell = row.createCell(4);
        cell.setCellValue("下一个环节");
        // 左右居中， 不加粗，字号14
        cell.setCellStyle(setCellStyle(workbook, HorizontalAlignment.CENTER, false, (short) 11));

        // 设置合并单元格的样式
        addBorderForRegion(cellRangeAddress1, sheet);
        addBorderForRegion(cellRangeAddress2, sheet);


        // 第三行： 申请人 申请人及组别
        // 合并行： A3~A10
        CellRangeAddress cellRangeAddress3 = new CellRangeAddress(2, 9, 0, 0);
        sheet.addMergedRegion(cellRangeAddress3);
        CellRangeAddress cellRangeAddress4 = new CellRangeAddress(2, 9, 4, 4);
        sheet.addMergedRegion(cellRangeAddress4);
        // 创建行
        row = sheet.createRow(2);
        // 设置行高
        row.setHeight((short) (55.5 * 20));
        // 创建单元格： 申请人
        cell = row.createCell(0);
        cell.setCellValue("申请人");
        // 左右居中， 加粗，字号11
        cell.setCellStyle(setCellStyle(workbook, HorizontalAlignment.CENTER, true, (short) 12));
        // 发送给所在组组长
        cell = row.createCell(4);
        cell.setCellValue("发所在组组长");
        // 左右居中， 不加粗，字号11
        cell.setCellStyle(setCellStyle(workbook, HorizontalAlignment.CENTER, false, (short) 11));


        // 申请人及组别
        createRowThreeToTowTenAndCellOneToCellFour(workbook, HorizontalAlignment.CENTER, true, (short) 12,
                row, "申请人及组别", "群发类型及群发方式" + "\r\n" +
                        "（收入/拉活/维系/账单-" + "\r\n" +
                        "邮/短/邮+短/PUSH）", "群发主题及短信内容");


        // 创建第四行 申请人及组别 群发类型 群发主题及短信内偶然
        createRowThreeToTowTenAndCellOneToCellFour(workbook, HorizontalAlignment.CENTER, false, (short) 11,
                sheet, 3, edmApplyOrder.getEdmer().getDepartment() + "-" + edmApplyOrder.getEdmer().getUsername(),
                edmApplyOrder.getQunFaTypeDescription(), edmApplyOrder.getQunFaSubjectAndContext());
        // 创建第五行
        // 排期意向	目标群发省	用户数据要求
        createRowThreeToTowTenAndCellOneToCellFour(workbook, HorizontalAlignment.CENTER, true, (short) 12,
                sheet, 4, "排期意向",
                "目标群发省", "用户数据要求");
        //  创建第六行
        createRowThreeToTowTenAndCellOneToCellFour(workbook, HorizontalAlignment.CENTER, false, (short) 11,
                sheet, 5, edmApplyOrder.getPaiQiYiXiang(),
                edmApplyOrder.getTargetSendProvince(), edmApplyOrder.getUserConditions());
        // 创建第七行  发送量	投递通道	目标用户不足时如何处理
        createRowThreeToTowTenAndCellOneToCellFour(workbook, HorizontalAlignment.CENTER, true, (short) 12,
                sheet, 6, "发送量",
                "投递通道", "目标用户不足时如何处理");
        // 创建第八行
        createRowThreeToTowTenAndCellOneToCellFour(workbook, HorizontalAlignment.CENTER, false, (short) 11,
                sheet, 7, edmApplyOrder.getSendNum() + "",
                edmApplyOrder.getChannelSends(), edmApplyOrder.getHowSupplement());

        // 创建第九行  短信内容
        createRowThreeToTowTenAndCellOneToCellFour(workbook, HorizontalAlignment.CENTER, true, (short) 12,
                sheet, 8, "短信内容",
                "", "");

        row = sheet.createRow(9);
        // 设置行高
        row.setHeight((short) (81.75 * 20));
        createRowThreeToTowTenAndCellOneToCellFour(workbook, HorizontalAlignment.LEFT, false, (short) 12,
                row, edmApplyOrder.getMessageContext(), "", "");

        // 设置合并单元格的样式
        addBorderForRegion(cellRangeAddress3, sheet);
        addBorderForRegion(cellRangeAddress4, sheet);


        // 创建第十行 申请组组长
        createCheckAndResultRows(workbook, HorizontalAlignment.CENTER, true, (short) 12,
                sheet, 10, "申请组组长", "发能力组，" + "\r\n" + "抄申请人",
                "内容初审", "", "",
                edmApplyOrderCheckResult.getFirstCheckerUserName(), "", "");

        // 创建第十一行， "能力组 //葛兴羽"
        createCheckAndResultRows(workbook, HorizontalAlignment.CENTER, true, (short) 12,
                sheet, 12,
                "能力组" + "\r\n" + edmApplyOrderCheckResult.getSecondCheckerUserName(), "发客服组，" + "\r\n" + "抄申请人",
                "排期结果", "内容复审", "",
                edmApplyOrderCheckResult.getPaiQiResult(), edmApplyOrderCheckResult.getSecondCheckerResult(), "");

        // 创建十二行， "客服组 梁南"
        createCheckAndResultRows(workbook, HorizontalAlignment.CENTER, true, (short) 12,
                sheet, 14,
                "客服组" + "\r\n" + edmApplyOrderCheckResult.getThirdCheckerUserName(), "审核通过：" + "\r\n" +
                        "发数据组，抄申请人" + "\r\n" +
                        "审核不通过：" + "\r\n" +
                        "返回申请人",
                "排期确认", "群发方案确认", "",
                edmApplyOrderCheckResult.getThirdCheckerPaiQiResult(), edmApplyOrderCheckResult.getThirdCheckerQunFaFangAnResult(), edmApplyOrderCheckResult.getThirdCheckBeiZhu());

        // 创建第十三行， "数据组
        //数据
        //(shuju@wo.cn)"
        createCheckAndResultRows(workbook, HorizontalAlignment.CENTER, true, (short) 12,
                sheet, 16,
                "数据组" + "\r\n" + edmApplyOrderCheckResult.getShuJuUserName() + "\r\n (" + edmApplyOrderCheckResult.getShuJuEmail() + ")", "发申请人，由申请人执行群发任务",
                "用户数据链接" + "\r\n" +
                        "（分省用户明细可附表）", "实际用户数据属性说明", "实际用户数量",
                edmApplyOrderCheckResult.getFetchResultSheetName(), edmApplyOrderCheckResult.getDataUsersDescription(), edmApplyOrderCheckResult.getActualUserNum());

        // 第十四行
        CellRangeAddress cellRangeAddress5 = new CellRangeAddress(18, 18, 1, 3);
        sheet.addMergedRegion(cellRangeAddress5);
        row = sheet.createRow(18);
        // 设置行高
        row.setHeight((short) (60 * 20));
        createFirstCellAndLastCell(workbook, HorizontalAlignment.CENTER, true, (short) 12, row, "备注说明", "结束");
        // 为合并单元格添加样式
        addBorderForRegion(cellRangeAddress5, sheet);

        // 生成excel
        // 文件路径
        // 上传文件的根目录
        String originalFilename = "《" + edmApplyOrder.getOrderName() + "》群发流转单-" + currentYearMonthDayStr + ".xlsx";
        // 获取要上传的目录
        /*  文件放到指定的目录下
        String rootPath = dataConfig.getUpLoadPath();
        String filePath = MyFileUtil.createUpLoadFilePath(rootPath);
        String fileName = MyFileUtil.createUpLoadFileName(originalFilename);
        */
        // 判断文件夹是否存放，如果不存在就创建
        File fileFolds = new File(filePath);
        if (!fileFolds.exists()){
            fileFolds.mkdirs();
        }
        // 生成一个文件名
        File file = new File(filePath + File.separator + originalFilename);
        // 如果文件存在，就删除文件
        if (file.exists()) {
            file.delete();
        }

        EdmApplyFile edmApplyFile = new EdmApplyFile(originalFilename, filePath, originalFilename, 1, edmApplyOrder.getOid());

        logger.info(file.toString());
        // 该语法保证 close 一定会关闭
        try (OutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
            return edmApplyFile;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置单元格的宽和高
     *
     * @param sheet
     */
    private void setColumnWidthAndHeight(HSSFSheet sheet) {
        // 设置第一列的宽为 12 个字
        sheet.setColumnWidth(0, 16 * 255);
        // 设置第二列的宽为
        sheet.setColumnWidth(1, 28 * 255);
        // 设置第三、四、五列的宽
        sheet.setColumnWidth(2, 28 * 255);
        sheet.setColumnWidth(3, 28 * 255);
        sheet.setColumnWidth(4, 18 * 255);

    }


    /**
     * 为合并的单元格添加边框
     *
     * @param region
     * @param sheet
     */
    private void addBorderForRegion(CellRangeAddress region, HSSFSheet sheet) {
        // 设置边框
        RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
    }


    /**
     * 设置单元格样式
     * 居中、12号字体、宋体、加粗、边框
     *
     * @param boldIf   是否加粗
     * @param fontSize 字体大小
     * @param workbook
     * @return
     */
    private HSSFCellStyle setCellStyle(HSSFWorkbook workbook, HorizontalAlignment horizontalAlignment, boolean boldIf, short fontSize) {

        HSSFCellStyle cellStyle = setCellStyle(workbook, horizontalAlignment);

        // 字体： 14号， 宋体
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        // 12号
        font.setFontHeightInPoints(fontSize);
        // 加粗
        font.setBold(boldIf);

        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 只设置边框
     *
     * @param workbook
     * @param horizontalAlignment
     * @return
     */
    private HSSFCellStyle setCellStyle(HSSFWorkbook workbook, HorizontalAlignment horizontalAlignment) {

        HSSFCellStyle cellStyle = workbook.createCellStyle();

        // 单元格的边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        // 左右居中
        cellStyle.setAlignment(horizontalAlignment);
        // 上下居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        return cellStyle;
    }


    /**
     * 创建第三行到到十行，每行的第2个到第三个单元格
     *
     * @param sheet
     * @param rowNum
     * @param cellTwoValue
     * @param cellThreeValue
     * @param cellFourValue
     */
    private void createRowThreeToTowTenAndCellOneToCellFour(HSSFWorkbook workbook,
                                                            HorizontalAlignment horizontalAlignment,
                                                            boolean blodIf,
                                                            short fontSize,
                                                            HSSFSheet sheet,
                                                            int rowNum,
                                                            String cellTwoValue,
                                                            String cellThreeValue,
                                                            String cellFourValue) {
        HSSFRow row = sheet.createRow(rowNum);
        // 设置行高
        row.setHeight((short) (34.5 * 20));
        createRowThreeToTowTenAndCellOneToCellFour(workbook, horizontalAlignment, blodIf, fontSize, row, cellTwoValue, cellThreeValue, cellFourValue);
    }

    /**
     * 创建第三行到到十行，每行的第2个到第三个单元格
     *
     * @param row
     * @param cellTwoValue
     * @param cellThreeValue
     * @param cellFourValue
     */
    private void createRowThreeToTowTenAndCellOneToCellFour(HSSFWorkbook workbook,
                                                            HorizontalAlignment horizontalAlignment,
                                                            boolean blodIf,
                                                            short fontSize,
                                                            HSSFRow row,
                                                            String cellTwoValue,
                                                            String cellThreeValue,
                                                            String cellFourValue) {
        // 添加样式： 水平位置， 是否加粗，字体
        HSSFCellStyle hssfCellStyle = setCellStyle(workbook, horizontalAlignment, blodIf, fontSize);
        hssfCellStyle.setWrapText(true); // 设置为自动换行
        HSSFCell cell = null;

        cell = row.createCell(1);
        cell.setCellStyle(hssfCellStyle);
        cell.setCellValue(new HSSFRichTextString(cellTwoValue));

        cell = row.createCell(2);
        cell.setCellStyle(hssfCellStyle);
        cell.setCellValue(new HSSFRichTextString(cellThreeValue));

        cell = row.createCell(3);
        cell.setCellStyle(hssfCellStyle);
        cell.setCellValue(new HSSFRichTextString(cellFourValue));
    }


    /**
     * 创建每一行第一个单元格，和最后一个单元格
     *
     * @param row
     */
    private void createFirstCellAndLastCell(HSSFWorkbook workbook,
                                            HorizontalAlignment horizontalAlignment,
                                            boolean blodIf,
                                            short fontSize,
                                            HSSFRow row, String zeroCellValue, String fiveCellValue) {
        // 添加样式： 水平位置， 是否加粗，字体
        HSSFCellStyle hssfCellStyle = setCellStyle(workbook, horizontalAlignment);

        hssfCellStyle.setWrapText(true);

        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(hssfCellStyle);
        // 设置一个单元格中不同的字体
        HSSFRichTextString zeroRichTextString = new HSSFRichTextString(zeroCellValue);
        String[] zeros = zeroCellValue.split("\r\n");
        logger.info(Arrays.toString(zeros));
        // 设置富文本前半段的字体样式
        zeroRichTextString.applyFont(0, zeros[0].length(), setFontStyle(workbook, (short) 11, true));
        if (zeros != null && zeros.length >= 2) {
            // 设置富文本后半段的字体样式
            zeroRichTextString.applyFont(zeros[0].length(), zeroCellValue.length(), setFontStyle(workbook, (short) 10, false));
        }
        cell.setCellValue(zeroRichTextString);

        // 添加样式： 水平位置， 是否加粗，字体
        HSSFCellStyle hssfCellStyle1 = setCellStyle(workbook, horizontalAlignment, false, (short) 11);
        hssfCellStyle1.setWrapText(true);

        cell = row.createCell(4);
        cell.setCellStyle(hssfCellStyle1);
        cell.setCellValue(new HSSFRichTextString(fiveCellValue));
    }

    /**
     * 设置字体的样式
     *
     * @param workbook
     * @param fontSize 大小
     * @param boldIf   是否加粗
     * @return
     */
    private HSSFFont setFontStyle(HSSFWorkbook workbook, short fontSize, Boolean boldIf) {
        // 字体： 14号， 宋体
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        // 12号
        font.setFontHeightInPoints(fontSize);
        // 加粗
        font.setBold(boldIf);
        return font;
    }


    /**
     * excel中 核查结果
     *
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
    private void createCheckAndResultRows(HSSFWorkbook workbook,
                                          HorizontalAlignment horizontalAlignment,
                                          boolean blodIf,
                                          short fontSize,
                                          HSSFSheet sheet,
                                          int rowNum,
                                          String firstCellValue,
                                          String lastCellValue,
                                          String oneRowCellOneValue,
                                          String oneRowCellTwoValue,
                                          String oneRowCellThreeValue,
                                          String twoRowCellOneValue,
                                          String twoRowCellTwoValue,
                                          String twoRowCellThreeValue
    ) {
        CellRangeAddress cellRangeAddress = new CellRangeAddress(rowNum, rowNum + 1, 0, 0);
        sheet.addMergedRegion(cellRangeAddress);
        CellRangeAddress cellRangeAddress1 = new CellRangeAddress(rowNum, rowNum + 1, 4, 4);
        sheet.addMergedRegion(cellRangeAddress1);

        HSSFRow row = sheet.createRow(rowNum);
        // 设置行高
        row.setHeight((short) (46.5 * 20));
        createFirstCellAndLastCell(workbook, horizontalAlignment, blodIf, fontSize, row, firstCellValue, lastCellValue);

        createRowThreeToTowTenAndCellOneToCellFour(workbook, horizontalAlignment, blodIf, fontSize,
                row, oneRowCellOneValue, oneRowCellTwoValue, oneRowCellThreeValue);

        row = sheet.createRow(rowNum + 1);
        // 设置行高
        row.setHeight((short) (38.25 * 20));
        createRowThreeToTowTenAndCellOneToCellFour(workbook, horizontalAlignment, false, (short) 11,
                row, twoRowCellOneValue, twoRowCellTwoValue, twoRowCellThreeValue);
        // 为合并单元格添加边框
        addBorderForRegion(cellRangeAddress, sheet);
        addBorderForRegion(cellRangeAddress1, sheet);
    }


}
