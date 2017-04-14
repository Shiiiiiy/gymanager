package com.uws.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.uws.util.Constants;
import com.uws.util.ImageUploadUtil;
import com.uws.util.file.FileUtils;
import com.uws.util.file.pojo.UploadFileInfo;
/**
 * 文件上传 
 */
@Controller
public class FileUploadController extends BaseController{

	/**
	 * CKEditor上传图像
	 * @param request
	 * @param response
	 */
	@RequestMapping("/**/back/news/imageUpload")
    public void imageUpload(HttpServletRequest request, HttpServletResponse response) {
        String DirectoryName = Constants.UPLOAD_FILE_SAVE_PATH;
        try {
            ImageUploadUtil.ckeditor(request, response, DirectoryName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * 图片预览
	 * @param response
	 * @param request
	 * @param id
	 */
	@RequestMapping("/**/back/news/preview")
	public void preview(HttpServletResponse response,HttpServletRequest request,String fileName){
		final String path =  Constants.UPLOAD_FILE_SAVE_PATH + File.separator + fileName;
		try {
			FileUtils.picturePreview(request, response, new UploadFileInfo(path,fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/ckeditor")
	public String ckEditor(){
		
		return "demo/ckeditor";
	}
	
 
}
