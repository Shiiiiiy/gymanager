package com.uws.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.dao.Page;
import com.base.model.Message;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.model.Dic;
import com.uws.model.FileInfo;
import com.uws.model.NewsAll;
import com.uws.service.IFileInfoService;
import com.uws.service.INewsAllManageService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;
import com.uws.util.Util;

@Controller
public class PolicyController extends BaseController {
	
	@Autowired
	private INewsAllManageService newsAllManageService;
	
	@Resource
	private ISysDicService sysDicService;
	
	@Resource
	private IFileInfoService fileService;
	
	@RequestMapping(value="/policynews/query/list")
	public String policyNewsIndex(Model model,NewsAll news,String moduleCode,String pageNo,String pageSize){
		model.addAttribute("news", news);
		model.addAttribute("moduleCode", moduleCode);
		model.addAttribute("pageNo", pageNo == null ? 1 : pageNo);
		model.addAttribute("pageSize", pageSize == null ? 10 : pageSize);
		return "policy/news/index";
	}
	
	@RequestMapping(value="/policynews/query/single")
	public String getPlicyNews(Model model,HttpServletRequest request, String code,NewsAll news){
		if(news != null && (news.getModuleCode() == null || "".equals(news.getModuleCode()))){
			news.setModuleCode("NEWS_E");
		}
		Map<String,Object> param = this.initParamMap(request);
		Page page = newsAllManageService.queryNewsAllListByModule(param, news);
		
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		
		model.addAttribute("news", news);
		model.addAttribute(Constants.NEWS_ALL_MODULE, news.getModuleCode());
		model.addAttribute("page", page);
		model.addAttribute("enable",enable);
		model.addAttribute("disable", disable);
		model.addAttribute("pageSearchType", "placeholder");//占位符, 无实义
		return "policy/news/common";
	}
	
	@RequestMapping(value="/policynews/opt/changestatus")
	public String changeStatus(ModelMap model,String id,String pageNo,
			String pageSize,String value,String moduleCode){
		this.newsAllManageService.changeStatus(id,value);
		
		Message msg = new Message("/policynews/query/list");
		msg.setTips("操作成功");
    	//msg.addParamForward("flag", "1");
    	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
    	msg.addParamForward("pageNo", pageNo);
    	msg.addParamForward("pageSize", pageSize);
    	msg.addParamForward("pageSearchType", pageNo);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	@RequestMapping(value="/policynews/opt/edit")
	public String edit(Model model,String id,String moduleCode,String pageNo,String pageSize){
		
		model.addAttribute(Constants.NEWS_ALL_MODULE,moduleCode);
		
		if(!Util.isNull(id)){//修改
			NewsAll newsAll = newsAllManageService.queryNewsAllByNewsId(id);
        	model.addAttribute("newsAll",newsAll);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		return "policy/news/edit";
	}
	
	@RequestMapping(value="/policynews/opt/save")
	public String save(Model model,NewsAll news,String moduleCode,String pageNo,String pageSize){
		
        if(news != null && DataUtil.isNotNull(news.getId())){//修改
        	NewsAll newsAll = newsAllManageService.queryNewsAllByNewsId(news.getId());
        	newsAll.setNewsTitle(news.getNewsTitle());
        	newsAll.setNewsSource(news.getNewsSource());
        	newsAll.setIntroduce(news.getIntroduce());
        	newsAll.setNewsContent(news.getNewsContent());
        	newsAll.setStatus(news.getStatus());
        	newsAllManageService.updateNewsAll(newsAll);
        	
        	Message msg = new Message("/policynews/query/list");
        	msg.setTips("修改成功");
        	//msg.addParamForward("flag", "1");
        	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			if(DataUtil.isNotNull(moduleCode)){
				news.setModuleCode(moduleCode);
			}
			newsAllManageService.saveNewsAll(news);
			
			Message msg = new Message("/policynews/query/list");
			msg.setTips("添加成功");
        	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	@RequestMapping(value="/policynews/query/view")
	public String view(Model model, String id){
		
		NewsAll newsAll = new NewsAll();
		
        if(DataUtil.isNotNull(id)){
        	newsAll = newsAllManageService.queryNewsAllByNewsId(id);
		}
        model.addAttribute("newsAll", newsAll);
        //启用状态字典集合
  		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
  		//启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  		model.addAttribute("statusList", statusList);
  		model.addAttribute("enable", enable);
        
        return "/policy/news/view";
	}
	
	@RequestMapping(value="/policynews/opt/delete")
	public String delete(Model model,String ids, String moduleCode){

        if(DataUtil.isNotNull(ids)){
        	newsAllManageService.deleteNewsAllsById(ids);
		}
        Message msg = new Message("/policynews/query/list");
		msg.setTips("删除成功");
    	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 ************** 资源管理, 主要是对产业政策页面第一个banner进行管理**********  
	 * @return
	 */
	@RequestMapping(value="/policyresource/query/list")
	public String queryBannerList(Model model){
		List<FileInfo> fileList = fileService.queryFileInfoListForCompany("MODULE_I", "N", "FILE_SLIDER");
		model.addAttribute("fileList", fileList);
		model.addAttribute("saveLocation", Constants.FILE_PATH);
		return "policy/resource/list";
	}
	
	@RequestMapping(value="/policyresource/opt/save")
	public @ResponseBody String editFile(FileInfo file){
		if(file != null){
			file.setFileTime(new Date());
			file.setModuleCode("MODULE_I");
			file.setFileType("FILE_SLIDER");
			file.setSort(1);
			file.setParentCode(null);
		}
		fileService.saveFileInfo(file);
		return null;
	}
	
	@RequestMapping(value="/policyresource/opt/update")
	@ResponseBody
	public String updateFile(String fileTitle,String fileId,String url,String urlType,String sort){
		fileService.updateFileName(fileId, fileTitle, url, urlType,sort);
		return null;
	}
	
	@RequestMapping(value="/policyresource/opt/delete")
	public String deleteFile(String id,Model model){
		if(id != null && !"".equals(id)){
			fileService.deleteFileById(id);
		}
		return this.queryBannerList(model);
	}
	
}
