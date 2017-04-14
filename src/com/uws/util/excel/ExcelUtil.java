package com.uws.util.excel;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * POI实现读写功能
 * @author zhangaw
 *
 */
public class ExcelUtil {
	/**  
	* 读取excel数据  
	* @param filePath  文件存放的路径
	 * @throws Exception 
	*/
	public static List<Map<Integer,Object>> read(String filePath) throws ValidateException, Exception {
		 
			return new POIReadExcel().readExcel(filePath);
	}
	
	/**  
	* 读取excel文件  
	* @param path  文件存放的路径   
	* @param validate验证规则 
	* @param list返回结果集
	* @throws Exception 
	*/
	public static List<Map<Integer,Object>> read(String filePath,POIReadExcel p, ValidateRule[] validate) throws ValidateException, Exception {
		 
		return p.readExcel(filePath,validate);
	}
	
	/**  
	* 读取excel文件  
	* @param path  文件存放的路径   
	* @param sheetIndex sheet页下标：从0开始  
	* @param startReadLine 开始读取的行:从0开始  
	* @param tailLine 去除最后读取的行
	* @param validate验证规则 
	* @param list返回结果集
	* @throws Exception 
	*/
	public static List<Map<Integer,Object>> read(String filePath,int sheetIndex, int startReadLine, int tailLine,POIReadExcel p,ValidateRule[] validate) throws ValidateException, Exception{
		
			return p.readExcel(filePath, sheetIndex, startReadLine, tailLine,validate);
	}
	
	/**
	 * 
	 * @param headers 标题数组
	 * @param list 结果集
	 * @param os 输出流
	 * @throws Exception
	 */
	public static void write(String[] headers,List<Map<Integer, Object>> list,OutputStream os) throws ValidateException,Exception {
		
		  	new POIWriteExcel().writeExcel(headers, list, os);;
	}
	
	/**  
	 *	   
	 * @param templatePath 带有表头的Excel文件
	 * @param list
	 * @param os
	 * @throws Exception
	 */
	public static void write(String templatePath,List<Map<Integer, Object>> list,OutputStream os) throws ValidateException, Exception {  
		 
		new POIWriteExcel().writeExcel(templatePath, list, os);
	} 
	
}
