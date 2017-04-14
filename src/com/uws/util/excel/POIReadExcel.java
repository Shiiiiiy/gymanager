package com.uws.util.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.base.util.DataUtil;
 
  
/**  
*  使用POI读取文件
*/  
public class POIReadExcel {  

/**
 * 返回错误信息
 */
public List<String> messagerList = new ArrayList<String>();
  
/**  
* 读取excel数据  
* @param path  文件存放的路径
 * @throws Exception 
*/  
public List<Map<Integer,Object>> readExcel(String path) throws ValidateException,Exception {  
	return readExcel(path, 0, 0, 0,null);  
} 

/**  
* 读取excel文件  
 * @param path  文件存放的路径  
 * @param validate每列的验证规则  
 * @param list返回结果集
 * @throws Exception 
 * @throws FileNotFoundException 
*/  
public List<Map<Integer,Object>> readExcel(String path,ValidateRule[] validate) throws ValidateException,Exception {  
	return readExcel(path, 0, 0, 0,validate);  
} 
/**  
* 读取excel文件  
* @param path  文件存放的路径   
* @param sheetIndex sheet页下标：从0开始  
* @param startReadLine 开始读取的行:从0开始  
* @param tailLine 去除最后读取的行
* @param list返回结果集
*  @param validate每列的验证规则  
 * @throws Exception 
 * @throws FileNotFoundException 
*/  
public List<Map<Integer,Object>> readExcel(String path,int sheetIndex, int startReadLine, int tailLine,ValidateRule[] validate) throws ValidateException, Exception {  
	List<Map<Integer,Object>> list = new ArrayList<Map<Integer,Object>>();
	Workbook wb = validateExcel(new FileInputStream(path));
	Sheet sheet = wb.getSheetAt(sheetIndex);  
	Row row = null;  
	for(int i=startReadLine; i<sheet.getLastRowNum()-tailLine+1; i++) {  
		row = sheet.getRow(i);  
		Map<Integer,Object> map = new HashMap<Integer,Object>();
		if(!DataUtil.isNotNull(row)){    //某一行全部为空的话直接跳过
			continue;
		}
		for (int j = 0; j < row.getLastCellNum(); j++) {
			boolean isMerge = isMergedRegion(sheet, i, j);
			Object obj = null ;
			//判断是否具有合并单元格  
			if(isMerge) {
				obj = getMergedRegionValue(sheet, row.getRowNum(),j);//获取合并后的单元格
			}else {  
				obj = getCellValue(row.getCell(j));//获取当前的单元格
			}
			if(row.getRowNum() != startReadLine && validate != null &&  validate.length >j && validate[j] != null ){ 
				//第一行不校验  校验为空不校验  校验规则的长度小于列的长度不校验   校验规则为空不校验
				String res = validate[j].validate(obj);
				if(res != null){
					messagerList.add(messages(row.getRowNum(),j,res));
				//	throw new ValidateException(messages(row.getRowNum(),j,res));
				}
			}
			map.put(j ,obj); 
		}  
		list.add(map); 
	} 
	
	return list;
}  

/**
 * 异常通知
 * @param row 当前行
 * @param column 当前列
 * @param info 返回信息
 * @return
 */
public String messages(int row,int column,String info){
	return "第"+(++row)+"行，第"+(++column)+"列的数据"+info;
}

/**
 * 获取Excel版本
 * @param inputStream
 * @return
 * @throws Exception
 */
public static Workbook validateExcel(InputStream inputStream)throws Exception{
	Workbook workbook = null;
		if (!inputStream.markSupported()) {
			inputStream = new PushbackInputStream(inputStream, 8);
	    }
		//下面不可以做两次判断 因为在创建完一个workbook以后  输入流就关闭了,第二次判断又会用到此输入流 会抛出异常;
		Boolean isXls = false; Boolean isXlsx = false;
        if (POIFSFileSystem.hasPOIFSHeader(inputStream)) {//office 2007以下的版本使用HSSFWorkbook
        	isXls = true;
        }
        if (POIXMLDocument.hasOOXMLHeader(inputStream)) {//2007以上的office版本 使用XSSFWorkbook操作
        	isXlsx = true;
        }
        if(isXls)workbook =  new HSSFWorkbook(inputStream);
        if(isXlsx)workbook =  new XSSFWorkbook(OPCPackage.open(inputStream)); //针对 xlsx 格式
        inputStream.close();
	return workbook;
}
  
/**   
* 获取合并单元格的值   
* @param sheet   
* @param row   
* @param column   
* @return   
*/    
private Object getMergedRegionValue(Sheet sheet ,int row , int column){    
    int sheetMergeCount = sheet.getNumMergedRegions();    
    for(int i = 0 ; i < sheetMergeCount ; i++){    
        CellRangeAddress ca = sheet.getMergedRegion(i);    
        int firstColumn = ca.getFirstColumn();    
        int lastColumn = ca.getLastColumn();    
        int firstRow = ca.getFirstRow();    
        int lastRow = ca.getLastRow();    
        if(row >= firstRow && row <= lastRow){    
            if(column >= firstColumn && column <= lastColumn){    
                Row fRow = sheet.getRow(firstRow);    
                Cell fCell = fRow.getCell(firstColumn);    
                return getCellValue(fCell) ;    
            }    
        }    
    }    
    return null ;    
}    
  
/**  
* 判断指定的单元格是否是合并单元格  
* @param sheet   
* @param row 行下标  
* @param column 列下标  
* @return  
*/  
private boolean isMergedRegion(Sheet sheet,int row ,int column) {  
  int sheetMergeCount = sheet.getNumMergedRegions();  
	  for (int i = 0; i < sheetMergeCount; i++) {  
		CellRangeAddress range = sheet.getMergedRegion(i);  
		int firstColumn = range.getFirstColumn();  
		int lastColumn = range.getLastColumn();  
		int firstRow = range.getFirstRow();  
		int lastRow = range.getLastRow();  
			if(row >= firstRow && row <= lastRow){  
				if(column >= firstColumn && column <= lastColumn){  
					return true;  
				}  
			}  
	  }  
  return false;  
}  
  
/**   
* 获取单元格的值   
* @param cell   
* @return   
*/    
public static Object getCellValue(Cell cell){    
    if(cell == null) return "";    
    if(cell.getCellType() == Cell.CELL_TYPE_STRING){    
        return cell.getStringCellValue();    
    }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){    
        return cell.getBooleanCellValue() ;    
    }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){    
        return cell.getCellFormula()  ;    
    }else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {  
        String cellValue = "";  
        if (HSSFDateUtil.isCellDateFormatted(cell)) {    //判断是日期类型  
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");  
            Date dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());//获取成DATE类型     
            cellValue = dateformat.format(dt);   
        }else{  
            DecimalFormat df = new DecimalFormat("0");    
            cellValue = df.format(cell.getNumericCellValue());  
        }  
        return cellValue;   
    }
    return "";    
}    

}  