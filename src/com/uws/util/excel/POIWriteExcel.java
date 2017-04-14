package com.uws.util.excel;
 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class POIWriteExcel {



/**
 * 
 * @param headers 标题数组
 * @param list 结果集
 * @param os 输出流
 * @throws Exception
 */
public void  writeExcel(String[] headers,List<Map<Integer, Object>> list,OutputStream os) throws Exception {
	Workbook wb = createWorkbook(headers, 0, 0);
	writeDatas(wb,0,list);
	wb.write(os);
}

/**  
 *	   
 * @param templatePath 带有表头的Excel文件
 * @param list
 * @param os
 * @throws Exception
 */
public void writeExcel(String templatePath,List<Map<Integer, Object>> list,OutputStream os) throws  Exception {  
	Workbook wb = POIReadExcel.validateExcel(new FileInputStream(templatePath));
	writeDatas(wb,0,list);
	wb.write(os);
} 

/**
 * 创建一个含有标题的工作簿
 * @param sheetName
 * @param headers
 * @param startWriteRows
 * @param startWriteCols
 */
private Workbook createWorkbook(String[] headers,int startWriteRows, int startWriteCols){
	HSSFWorkbook workbook = new HSSFWorkbook(); //创建工作簿
	HSSFSheet sheet = workbook.createSheet("Sheet0"); //创建sheet时 sheet的名称应该由外部传入.
	sheet.setDefaultRowHeight((short)400);
	if(headers.length > 0 ){
	Row titleRow = sheet.createRow(startWriteRows);
		for (int i = 0; i < headers.length; i++) {
			sheet.setColumnWidth((short) i, (short) 4000);
			Cell titleCell = titleRow.createCell(startWriteCols + i);
			titleCell.setCellValue(headers[i]);
			addTitleStyle(workbook, titleCell);
		}
	}
	return workbook;
}

/**  
* 写入结果集
* @param wb   
* @param sheetIndex sheet页下标：从0开始  
* @param startReadLine 开始读取的行:从0开始  
* @param tailLine 去除最后读取的行
* @param list返回结果集
*/  
private void writeDatas(Workbook wb,int sheetIndex,List<Map<Integer, Object>> list) {  
	Sheet sheet = wb.getSheetAt(sheetIndex);  
	int lastRowNum = sheet.getLastRowNum() ;
	if(lastRowNum == 0){
		Row r = sheet.getRow(lastRowNum);
		if(r == null || r.getLastCellNum() == 0 ){
			lastRowNum--;
		}
	}
	lastRowNum++;
	Row row = null; 
	if(list != null && list.size() > 0){
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(lastRowNum+i);  
			writeExcelRow(wb,row,0,list.get(i));
		}
	}
} 
	

/**
 * 逐行写入
 * @param row
 * @param datas
 * @param style
 */
private void writeExcelRow(Workbook wb,Row row,int startWriteCols, Map<Integer, Object> datas) {
	
	CellStyle headerStyle2 = (CellStyle) wb .createCellStyle();// 创建标题样式2  
    headerStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);  
    headerStyle2.setAlignment(CellStyle.ALIGN_CENTER);  
    Font headerFont2 = (Font) wb.createFont();  
    headerFont2.setFontHeightInPoints((short) 8);  
    headerStyle2.setFont(headerFont2);  
    headerStyle2.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
    headerStyle2.setBorderLeft(CellStyle.BORDER_THIN);// 左边框  
    headerStyle2.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
    headerStyle2.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
  
	
	for (Map.Entry<Integer, Object> data : datas.entrySet()) {
		Cell cell = row.createCell(startWriteCols+data.getKey());
		Object obj = data.getValue();
	      if ((obj == null)) {
	        cell.setCellValue("");
	      } else {
	    	 setCellValue(cell,obj);
	    	 cell.setCellStyle(headerStyle2);
	      }
	 }
 }
	
/**   
* 设置单元格的值   
* @param cell   
* @return   
*/    
private void setCellValue(Cell cell, Object value){
	if(value instanceof Integer){
		Integer i = Integer.valueOf(value.toString().split("\\.")[0]);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(i);
	}else if(value instanceof Float){
		Float f = Float.valueOf(value.toString());
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(f);
	}else if(value instanceof Double){
		Double d = Double.valueOf(value.toString());
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(d);
	}else if(value instanceof String){	
		cell.setCellType(Cell.CELL_TYPE_STRING );
		cell.setCellValue(value.toString());
	}else if(value instanceof Boolean){	
		cell.setCellType(Cell.CELL_TYPE_BOOLEAN );
		cell.setCellValue(((Boolean) value).booleanValue());
	}else if(value instanceof Date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cell.setCellType(Cell.CELL_TYPE_FORMULA);
		try{
			cell.setCellValue(sdf.format(value));//对于 yyyy-MM-dd格式的日期 此处会抛异常
		}catch(Exception e){
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			cell.setCellValue(sdf.format(value));
		}
	}else{
		cell.setCellValue("");
	}
}
 
/**  
* 合并单元格  
* @param sheet   
* @param firstRow 开始行  
* @param lastRow 结束行  
* @param firstCol 开始列  
* @param lastCol 结束列  
*/  
//private void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {  
//	sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));  
//} 
 
 
//添加标题样式
 public void addTitleStyle(Workbook workbook,Cell cell){
		CellStyle cellstyle = (CellStyle) workbook.createCellStyle();// 设置表头样式  
		cellstyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);    //设置垂直居中  
		cellstyle.setAlignment(CellStyle.ALIGN_CENTER);// 设置居中  
		HSSFPalette palette = ((HSSFWorkbook) workbook).getCustomPalette(); //wb HSSFWorkbook对象
		palette.setColorAtIndex((short) 9, (byte)  (0x66), (byte) (0xcd), (byte) (0xaa));
		cellstyle.setFillPattern(HSSFCellStyle.BIG_SPOTS);;
		cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellstyle.setFillForegroundColor(HSSFColor.WHITE.index);
		cellstyle.setFillBackgroundColor(HSSFColor.DARK_BLUE.index);//背景
	    Font headerFont = (Font) workbook.createFont(); //创建字体样式  
	    headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
	    headerFont.setFontHeightInPoints((short) 10);    //设置字体大小  
	    cellstyle.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
	    cellstyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框  
	    cellstyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
	    cellstyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
	    cellstyle.setFont(headerFont);    //为标题样式设置字体样式  
	    cell.setCellStyle(cellstyle);
 }
 
 //添加文本样式1
 public void addContentStyle1(Workbook workbook,Cell cell){
	 	CellStyle headerStyle2 = (CellStyle) workbook .createCellStyle();// 创建标题样式2  
	    headerStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);  
	    headerStyle2.setAlignment(CellStyle.ALIGN_CENTER);  
	    Font headerFont2 = (Font) workbook.createFont();  
	    headerFont2.setFontHeightInPoints((short) 8);  
	    headerStyle2.setFont(headerFont2);  
	    headerStyle2.setBorderBottom(CellStyle.BORDER_THIN); // 下边框  
	    headerStyle2.setBorderLeft(CellStyle.BORDER_THIN);// 左边框  
	    headerStyle2.setBorderTop(CellStyle.BORDER_THIN);// 上边框  
	    headerStyle2.setBorderRight(CellStyle.BORDER_THIN);// 右边框  
	    cell.setCellStyle(headerStyle2);
 }
 //添加文本样式2
 public void addContentStyle2(Workbook workbook,Cell cell){
	 
 }
 
}
