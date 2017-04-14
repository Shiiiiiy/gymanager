package com.uws.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.util.DataUtil;
import com.uws.model.FileInfo;
import com.uws.service.IFileInfoService;
import com.uws.util.Constants;
/**
 * 资源管理Controller
 * @author 张学彪
 */
@Controller
public class SourceManageController extends BaseController{

	@Resource
	private IFileInfoService fileService;
	
	/**
	 * 企业信息-资源管理
	 */
	@RequestMapping("/f1source/opt-query/f1sourceList")
	public String f1sourceList(HttpServletRequest request,Model model){
		
		String flag = request.getParameter("flag");
		if(DataUtil.isNull(flag)){
			flag = "1";
		}
		
		List<FileInfo> fileList = fileService.queryFileInfoList(Constants.MODULE_A,Constants.FILE_SLIDER,Constants.FILE_STATISTICS);
		model.addAttribute("fileList", fileList);
		model.addAttribute("flag", flag);
		model.addAttribute("saveLocation", Constants.FILE_PATH);
		model.addAttribute("slider", Constants.FILE_SLIDER);
		model.addAttribute("statistics", Constants.FILE_STATISTICS);
	    return "file/f1sourceList";  
	}
	
	/**
	 * 企业信息-资源管理-上传图片信息保存到数据库
	 */
	@RequestMapping("/f1source/opt-add/saveF1source")
	public String saveF1source(HttpServletRequest request,String fileName,String fileType,String flag,Model model){
		FileInfo file = new FileInfo();
		file.setFileName(fileName);
		file.setFileTime(new Date());
		file.setModuleCode(Constants.MODULE_A);
		//实在是不晓得他们是咋定义的，不加上去显示不出来
		file.setParentCode("MODULE_A_MAIN");
		file.setFileType(fileType);
		file.setSort(1);
		fileService.saveFileInfo(file);
	    return "redirect:/f1source/opt-query/f1sourceList?flag="+flag;  
	}
	
	/**
	 * 企业信息-资源管理-修改图片title
	 * @throws Exception 
	 */
	@RequestMapping("/f1source/opt-update/updateFileTitle")
	public String updateFileTitle(HttpServletRequest request,String flag,String fileTitle,String fileId,String url,String urlType,String sort,Model model) throws Exception{
		fileTitle = java.net.URLDecoder.decode(fileTitle,"UTF-8");
		url = java.net.URLDecoder.decode(url,"UTF-8");
		fileService.updateFileName(fileId, fileTitle, url, urlType, sort);
	    return "redirect:/f1source/opt-query/f1sourceList?flag="+flag; 
	}
	
	/**
	 * 企业信息-资源管理-删除选中图片
	 */
	@RequestMapping("/f1source/opt-delete/deleteFile")
	public String deleteFile(HttpServletRequest request,String fileId,String flag,Model model){
		fileService.deleteFileById(fileId);
	    return "redirect:/f1source/opt-query/f1sourceList?flag="+flag;  
	}
	
	
	/**
	 * 企业信息-技术创新
	 */
	@RequestMapping("/f5source/opt-query/f5sourceList")
	public String f5sourceList(HttpServletRequest request,Model model){
		
		String flag = request.getParameter("flag");
		if(DataUtil.isNull(flag)){
			flag = "1";
		}
		
		List<FileInfo> fileList = fileService.queryFileInfoList(Constants.MODULE_G,Constants.FILE_SLIDER,Constants.FILE_STATISTICS,Constants.FILE_RESULT,Constants.FILE_STAGE,"FILE_B",Constants.FILE_COOPERA);
		model.addAttribute("fileList", fileList);
		model.addAttribute("flag", flag);
		model.addAttribute("saveLocation", Constants.FILE_PATH);
		model.addAttribute("slider", Constants.FILE_SLIDER);
		model.addAttribute("statistics", Constants.FILE_STATISTICS);
		model.addAttribute("result", Constants.FILE_RESULT);
		model.addAttribute("stage", Constants.FILE_STAGE);
	    return "file/f5sourceList";  
	}
	
	/**
	 * 技术创新-资源管理-上传图片信息保存到数据库
	 */
	@RequestMapping("/f5source/opt-add/saveF5source")
	public String saveF5source(HttpServletRequest request,String fileName,String fileType,String flag,String parentCode,Model model){
		FileInfo file = new FileInfo();
		file.setFileName(fileName);
		file.setFileTime(new Date());
		file.setModuleCode(Constants.MODULE_G);
		file.setParentCode(parentCode);
		file.setFileType(fileType);
		file.setSort(1);
		fileService.saveFileInfo(file);
	    return "redirect:/f5source/opt-query/f5sourceList?flag="+flag;  
	}
	
	/**
	 * 技术创新-资源管理-修改图片title
	 * @throws Exception 
	 */
	@RequestMapping("/f5source/opt-update/updateFileTitle5")
	public String updateFileTitle5(HttpServletRequest request,String flag,String fileTitle,String fileId,String url,String urlType,String sort,Model model) throws Exception{
		fileTitle = java.net.URLDecoder.decode(fileTitle,"UTF-8");
		url = java.net.URLDecoder.decode(url,"UTF-8");
		fileService.updateFileName(fileId, fileTitle, url, urlType, sort);
	    return "redirect:/f5source/opt-query/f5sourceList?flag="+flag; 
	}
	
	/**
	 * 技术创新-资源管理-删除选中图片
	 */
	@RequestMapping("/f5source/opt-delete/deleteFile5")
	public String deleteFile5(HttpServletRequest request,String fileId,String flag,Model model){
		fileService.deleteFileById(fileId);
	    return "redirect:/f5source/opt-query/f5sourceList?flag="+flag;  
	}
	
	
	/**
	 * 企业信息-创新创业
	 */
	@RequestMapping("/f6source/opt-query/f6sourceList")
	public String f6sourceList(HttpServletRequest request,Model model){
		
		String flag = request.getParameter("flag");
		if(DataUtil.isNull(flag)){
			flag = "1";
		}
		
		List<FileInfo> fileList = fileService.queryFileInfoList(Constants.MODULE_H,Constants.FILE_SLIDER,Constants.FILE_STATISTICS,Constants.LOGO_A,Constants.LOGO_B);
		model.addAttribute("fileList", fileList);
		model.addAttribute("flag", flag);
		model.addAttribute("saveLocation", Constants.FILE_PATH);
		model.addAttribute("slider", Constants.FILE_SLIDER);
		model.addAttribute("statistics", Constants.FILE_STATISTICS);
		model.addAttribute("logoa", Constants.LOGO_A);
		model.addAttribute("logob", Constants.LOGO_B);
	    return "file/f6sourceList";  
	}
	
	/**
	 * 创新创业-资源管理-上传图片信息保存到数据库
	 */
	@RequestMapping("/f6source/opt-add/saveF6source")
	public String saveF6source(HttpServletRequest request,String fileName,String fileType,String flag,Model model){
		FileInfo file = new FileInfo();
		file.setFileName(fileName);
		file.setFileTime(new Date());
		file.setModuleCode(Constants.MODULE_H);
		file.setFileType(fileType);
		file.setSort(1);
		fileService.saveFileInfo(file);
	    return "redirect:/f6source/opt-query/f6sourceList?flag="+flag;  
	}
	
	/**
	 * 创新创业-资源管理-修改图片title
	 * @throws Exception 
	 */
	@RequestMapping("/f6source/opt-update/updateFileTitle6")
	public String updateFileTitle6(HttpServletRequest request,String flag,String fileTitle,String fileId,String url,String urlType,String sort,Model model) throws Exception{
		fileTitle = java.net.URLDecoder.decode(fileTitle,"UTF-8");
		url = java.net.URLDecoder.decode(url,"UTF-8");
		fileService.updateFileName(fileId, fileTitle, url, urlType, sort);
	    return "redirect:/f6source/opt-query/f6sourceList?flag="+flag; 
	}
	
	/**
	 * 创新创业-资源管理-删除选中图片
	 */
	@RequestMapping("/f6source/opt-delete/deleteFile6")
	public String deleteFile6(HttpServletRequest request,String fileId,String flag,Model model){
		fileService.deleteFileById(fileId);
	    return "redirect:/f6source/opt-query/f6sourceList?flag="+flag;  
	}
}
