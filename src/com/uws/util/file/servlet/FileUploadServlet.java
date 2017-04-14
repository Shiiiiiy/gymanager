package com.uws.util.file.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;









import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uws.util.Constants;
import com.uws.util.file.dto.UploadResult;



//@WebServlet({"/file/upload"})
public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ObjectMapper jsonMapper = new ObjectMapper();
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//防止中文文件名乱码
		response.setCharacterEncoding("UTF-8");
		// 系统配置的文件保存跟路径
		String path = null;
		//调用者指定的保存路径
		String userPath =  request.getParameter("location");
		try {
			path = this.getFolder(request,userPath);
		} catch (Exception e) {
			e.printStackTrace();//TODO
			logger.error("获取系统保存文件目录错误!", e);
			UploadResult result = new UploadResult(false, null,"cannot get the file path!",null);
			response.getWriter().print(jsonMapper.writeValueAsString(result));
			return;
		}

		//创建目录
		if(!(new File(path).exists())){
			new File(path).mkdirs();
		}
		
		FileItemFactory itemFactory = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(itemFactory);
        upload.setHeaderEncoding("utf-8");  
        List<FileItem> fileItems = null;
        
        try {
        	//开始解析上传请求
        	fileItems = upload.parseRequest(request);  
        } catch (FileUploadException e) {  
        	logger.error("解析上传请求时出现异常!", e);
        	e.printStackTrace();//TODO
			UploadResult result = new UploadResult(false, null,"Error occured while parsing upload request!",null);
			response.getWriter().print(jsonMapper.writeValueAsString(result));
			return; 
        }
        
        Iterator<FileItem> iterator = fileItems.iterator();
        while(iterator.hasNext()){
        	FileItem item = iterator.next();
        	if(!item.isFormField()){
        		String fileName = item.getName();
        		//去掉文件名中的空格和制表符
        		fileName = fileName.replaceAll("\\s", "");
        		//判断是否是图片文件
        		boolean isPic = this.isPicFile(fileName);
        		//取文件名后缀
        		String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());
        		//防止上传同名文件造成文件覆盖
        		String fileNameInServer = UUID.randomUUID().toString().replaceAll("-", "").concat(".") + 
        				DigestUtils.md5DigestAsHex(fileName.getBytes()).concat(".") + fileExtName;
        		
        		String finalFilePath = path + "/" + fileNameInServer;
        				
        		logger.debug("文件上传写入的绝对地址-->:{}",finalFilePath);
        		try {
        			//写入磁盘
					item.write(new File(finalFilePath));
				} catch (Exception e) {
					logger.error("文件写入磁盘时出现错误!", e);
					UploadResult result = new UploadResult(false, null,"Error occured while writing file!",null);
					response.getWriter().print(jsonMapper.writeValueAsString(result));
					return;
				}
        		
        		//写入成功,通知前端
        		UploadResult result = new UploadResult(true, finalFilePath,null,fileName);
        		result.setPictureFile(isPic);//通知前端 是否图片
        		result.setFileNameInServer(fileNameInServer);
        		
        		if(userPath != null && !userPath.equals("")){
        			String relativePath = userPath + "/" + fileNameInServer;
        			result.setFileRelativePath(relativePath);
        		}else{
        			String contextPath = request.getContextPath().substring(1);
        			String relativePath = finalFilePath.substring(finalFilePath.indexOf(contextPath))
        					.replaceAll("\\\\", "/");
        			result.setFileRelativePath("/" + relativePath);
        		}
        		
				response.getWriter().print(jsonMapper.writeValueAsString(result));
        	}
        }
        
        
	}
	
	
	/**
	 * 获取系统配置的文件保存路径,如果没有设置返回项目根路径 + 配置目录 + 年月日 
	 * 如果是指定路径,则返回指定路径的绝对地址
	 * @param request
	 * @param replaceStr 
	 * @return
	 */
	private String getFolder(HttpServletRequest request,String userPath/*, String replaceStr*/){
		if(userPath != null && !userPath.equals("")){
			//取当前项目的绝对路径
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			//取项目名
			String contextPath = request.getContextPath().substring(1);
			//从绝对路径中去掉项目名,拼接出新的路径
			rootPath = rootPath.substring(0, rootPath.length() - 1).replaceFirst(contextPath, "")
					.replaceAll("\\\\", "/");
			userPath = userPath.replaceAll("\\\\", "/").substring(1);
			return rootPath + userPath;
			
		}else{
			// 日历对象 以获取年月日数字
			Calendar cal = Calendar.getInstance();
			
	        //保存路径以 年/月/日的方式保存
			if(Constants.UPLOAD_FILE_SAVE_PATH == null || Constants.UPLOAD_FILE_SAVE_PATH.equals("")){
		        return request.getSession().getServletContext().getRealPath("/") + 
		        		File.separator + cal.get(Calendar.YEAR) +
						File.separator + cal.get(Calendar.MONTH) + 
						File.separator + cal.get(Calendar.DATE);
		    }
		    return request.getSession().getServletContext().getRealPath("/") +
		    		Constants.UPLOAD_FILE_SAVE_PATH.substring(1) + 
		    		File.separator + cal.get(Calendar.YEAR) +
					File.separator + cal.get(Calendar.MONTH) + 
					File.separator + cal.get(Calendar.DATE);
		}
	}
	

	private boolean isPicFile(String fileName){
		String[] picTypeArr = 
				new String[]{"bmp","dib","gif","jfif","jpe","jpeg",
				"jpg","png","tif","tiff","ico"};
		String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1, 
                fileName.length());
		for (String ext : picTypeArr) {
			if(ext.equals(fileExtName))
				return true;
		}
		return false;
	}
	
}
