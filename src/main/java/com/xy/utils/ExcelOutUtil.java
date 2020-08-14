package com.xy.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelOutUtil {

    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb,String head) {
        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }
        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 第三步，在sheet中添加表头第1行
        HSSFRow row = sheet.createRow(1);

        // 第四步，设置样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short)11);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        cellStyle.setWrapText(true);//自动换行

        // 创建总标题头
        HSSFRow rowHead = sheet.createRow(0);
        HSSFCell cellRowHead = rowHead.createCell(0);
        cellRowHead.setCellValue(head);
        cellRowHead.setCellStyle(cellStyle);
        rowHead.setHeightInPoints(36);
        CellRangeAddress cra = new CellRangeAddress(0,0,0,6);//合并单元格
        sheet.addMergedRegion(cra);//引用设计样式

        // 声明列对象
        HSSFRow row1=sheet.createRow(1);
        HSSFCell cell = row1.createCell(1);
        // 创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(cellStyle);
        }

        // 创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 2);
            row.setHeightInPoints(20);//设置行高
            int width=10;//设置宽度
            sheet.setColumnWidth(0,256*width+384);
            sheet.setColumnWidth(1,256*width+384);
            sheet.setColumnWidth(2,256*width+384);
            sheet.setColumnWidth(3,256*width+384);
            sheet.setColumnWidth(4,256*width+384);
            sheet.setColumnWidth(5,256*width+384);
            sheet.setColumnWidth(6,256*width+484);
            for (int j = 0; j < values[i].length; j++) {
                // 将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
                row.setRowStyle(cellStyle);
            }
        }
        return wb;
    }

    // 根据参数返回一个二维数组
    public static String[][] getContent(int length) {
        return new String[length][];
    }

    /**
     * 用于时间命名
     * @return
     */
    public String getDataTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
        String dataTime = dateFormat.format(date);
        return dataTime;
    }

}
