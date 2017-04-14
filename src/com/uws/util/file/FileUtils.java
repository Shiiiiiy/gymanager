package com.uws.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uws.util.file.pojo.UploadFileInfo;




public class FileUtils {

	/**
	 * 文件下载的静态方法
	 * @param request
	 * @param response
	 * @param query -> 重点对象,此对象的类型是个接口,调用者应的调用时 通过 new关键字创建对象,
	 * 					并实现唯一一个方法:queryFilePath,返回保存在数据库中的文件绝对地址
	 * @throws IOException
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response,
			UploadFileInfo fileInfo) throws IOException{
		
		String validate = request.getParameter("validate");
		if(validate != null){ //不为空则为文件校验
			String filePath = fileInfo.getFileLocation();
			if(filePath != null && !filePath.equals("")){
				File file = new File(filePath);
				if(file.exists()){
					//通知客户端,文件存在准备下载
					response.getWriter().print("{\"exists\":true}");
					return;
				}
			}
			response.getWriter().print("{\"exists\":false}");
		
		}else{	//下载文件
			
			String filePath = fileInfo.getFileLocation(); //文件的绝对路径
			String fileName = fileInfo.getFileOriginalName();//filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());
			//获取文件的上传时的文件名
			//fileName = fileName.substring(33);
			fileName = fileName == null || "".equals(fileName) ? "unnamed_file" : fileName;
			
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-disposition", "attachment;" + encodeFileName(request, fileName));
			long fileLength = new File(filePath).length();
			response.setHeader("Content-Length", String.valueOf(fileLength));
			
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bis.close();
			bos.close();
		}
		
	}
	
	/**
	 * 图片预览的静态方法
	 * @param request
	 * @param response
	 * @param query-> 重点对象,此对象的类型是个接口,调用者应的调用时 通过 new关键字创建对象,
	 * 					并实现唯一一个方法:queryFilePath,返回保存在数据库中的文件绝对地址
	 * @throws IOException
	 */
	public static void picturePreview(HttpServletRequest request, HttpServletResponse response,
			UploadFileInfo fileInfo) throws IOException{
		
		String filePath = fileInfo.getFileLocation();//query.queryFilePath();
		
		if(filePath != null && !"".equals(filePath)){
			response.setContentType("image/jpeg;charset=UTF-8");
			
			FileInputStream fis = new FileInputStream(filePath);
			OutputStream os = response.getOutputStream();
			
			try{
				int count = 0;
				byte[] buffer = new byte[1024 * 1024];
				while ((count = fis.read(buffer)) != -1){
					os.write(buffer, 0, count);
				}
				os.flush();
			}catch (IOException e){
				e.printStackTrace();
			}finally{
				if (os != null)
				os.close();
				if (fis != null)
				fis.close();
			}
		}
		
	}
	
	/**
	 * 获取客户端浏览器类型、编码下载文件名
	 * 
	 * @param request
	 * @param fileName
	 * @return String
	 */
	public static String encodeFileName(HttpServletRequest request, String fileName) {
		String userAgent = request.getHeader("User-Agent");
		String resultStr = "";
		try {
			String encodedFileName = URLEncoder.encode(fileName, "UTF8");
			// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
			resultStr = "filename=\"" + encodedFileName + "\"";
			if (userAgent != null) {
				userAgent = userAgent.toLowerCase();
				
				if (userAgent.indexOf("msie") != -1) { // IE浏览器，只能采用URLEncoder编码
					resultStr = "filename=\"" + encodedFileName + "\"";
					
				}else if (userAgent.indexOf("opera") != -1) {  // Opera浏览器只能采用filename*
					resultStr = "filename*=UTF-8''" + encodedFileName;
					
				}else if (userAgent.indexOf("safari") != -1) {	// Safari浏览器，只能采用ISO编码的中文输出
					resultStr = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
					
				}else if (userAgent.indexOf("applewebkit") != -1) {	// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
					//new_filename = MimeUtility.encodeText(fileName, "UTF8", "B");
					//rtn = "filename=\"" + new_filename + "\"";
					resultStr = "filename*=UTF-8''" + encodedFileName;
					
				}else if (userAgent.indexOf("mozilla") != -1) {	// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
					resultStr = "filename*=UTF-8''" + encodedFileName;
					
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return resultStr;
	}
	
}
