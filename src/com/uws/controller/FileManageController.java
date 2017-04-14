package com.uws.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uws.util.Util;
import com.uws.util.file.FileUtils;
import com.uws.util.file.TempDataBase;
import com.uws.util.file.pojo.UploadFileInfo;

@Controller
@RequestMapping("/file")
public class FileManageController {

	@RequestMapping("/list")
	public String fileList(){
		return "/file/list";
	}
	
	@RequestMapping("/list1")
	public String fileTest(){
		return "/login1";
	}
	
	/**
	 * 保存文件路径到数据库(本方法是模拟的数据库)
	 * @param location
	 * @return
	 */
	@RequestMapping("/savepath")
	public @ResponseBody String savepath(String location,String fileName){
		String id = UUID.randomUUID().toString();
		Map<String,String> m = new HashMap<String, String>();
		m.put("path", location);
		m.put("fileName", fileName);
		TempDataBase.getDataBase().put(id, m);
		System.out.println(TempDataBase.getDataBase());
		return "{\"id\":\"" + id +"\",\"fileName\":\"" + fileName + "\"}";
	}
	
	/**
	 * 图片预览
	 * @param response
	 * @param request
	 * @param id
	 */
	@RequestMapping("/preview")
	public void preview(HttpServletResponse response,HttpServletRequest request,String id){
		String path = TempDataBase.getDataBase().get(id).get("path");
		String name = TempDataBase.getDataBase().get(id).get("fileName");
		try {
			//弃用匿名内部类的方式
			/*FileUtils.picturePreview(request, response, new QueryFile() {
				
				@Override
				public String queryFilePath() {
					return path;
				}
			});*/
			
			FileUtils.picturePreview(request, response, new UploadFileInfo(path, name));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 文件下载
	 * @param response
	 * @param request
	 * @param id
	 */
	@RequestMapping("/download")
	public void download(HttpServletResponse response,HttpServletRequest request,String id){
		String path = TempDataBase.getDataBase().get(id).get("path");
		String name = TempDataBase.getDataBase().get(id).get("fileName");
		try {
			
			FileUtils.download(request, response, new UploadFileInfo(path, name));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5DigestAsHex("zhouchang".getBytes()));
		System.out.println(DigestUtils.md5DigestAsHex("zhouaw".getBytes()));
		
		System.out.println(Util.encryptMD5("zhouchang"));
		System.out.println(Util.encryptMD5("zhouaw"));
		
	}
}
