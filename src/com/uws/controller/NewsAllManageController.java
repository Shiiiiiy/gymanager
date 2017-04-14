package com.uws.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.dao.Page;
import com.base.model.Message;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.model.Dic;
import com.uws.model.NewsAll;
import com.uws.service.INewsAllManageService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;
import com.uws.util.Util;

/**
 * 新闻（资讯/动态/政策表）Controller
 * @author 张学彪
 */
@Controller
public class NewsAllManageController extends BaseController{

	@Autowired
	private INewsAllManageService newsAllManageService;
	
	@Resource
	private ISysDicService sysDicService;
	
	/**
	 * 企业信息-新闻管理-新闻列表
	 */
	@RequestMapping("/newsall/opt-query/newsAllList")
	public String newsAllList(HttpServletRequest request,NewsAll news,Model model,HttpSession session){
		Map<String, Object> param = this.initParamMap(request);
		
		//新闻大类型,区分各模块
		String moduleCode = request.getParameter(Constants.NEWS_ALL_MODULE);
		if(DataUtil.isNotNull(moduleCode)){
			news.setModuleCode(moduleCode);
		}
		
		//是否保存查询条件
		String flag=request.getParameter("flag");
		
		if("1".equals(flag)){
			news=(NewsAll)session.getAttribute("news");
		}
		
		Page page=newsAllManageService.queryNewsAllListByModule(param, news);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		//禁用状态字典
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		
		//保存查询条件
		session.setAttribute("news", news);
		model.addAttribute(Constants.NEWS_ALL_MODULE, moduleCode);
		model.addAttribute("page", page);
		model.addAttribute("enable",enable);
		model.addAttribute("disable", disable);
		
	    return "newsall/newsallList";  
	}
	
	
	/**
	 * 进入新增或修改新闻页面
	 */
	@RequestMapping("/newsall/opt-addOrEdit/addOrEditNewsAll")
	public String addOrEditNewsAll(HttpServletRequest request,String pageNo,String pageSize,Model model,HttpSession session){
		//新闻id
		String newsId=request.getParameter("newsId");
		
		/**
		 * 必带新闻大分类code
		 * 从新闻列表带过来
		 */
		String moduleCode = request.getParameter(Constants.NEWS_ALL_MODULE);
		model.addAttribute(Constants.NEWS_ALL_MODULE,moduleCode);
		
		if(!Util.isNull(newsId)){//修改
			NewsAll newsAll = newsAllManageService.queryNewsAllByNewsId(newsId);
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
		
		/**
		 * 根据自己业务带上 父编码、新闻类型
		 * 分类下拉列表数据自查
		 */
		//model.addAttribute(Constants.NEWS_ALL_MODULE,"");
		
	    return "newsall/addOrEditNewsAll";  
	}
	
	
	/**
	 *保存（新增、修改）新闻（通用）
	 * */
	@RequestMapping("/newsall/opt-save/saveNewsAll" )
	public String saveNewsAll(ModelMap model,NewsAll news,String pageNo,String pageSize, HttpServletRequest request){
		
		String moduleCode = request.getParameter(Constants.NEWS_ALL_MODULE);
		
        if(news != null && DataUtil.isNotNull(news.getId())){//修改
        	NewsAll newsAll = newsAllManageService.queryNewsAllByNewsId(news.getId());
        	newsAll.setNewsTitle(news.getNewsTitle());
        	newsAll.setNewsSource(news.getNewsSource());
        	newsAll.setIntroduce(news.getIntroduce());
        	newsAll.setNewsContent(news.getNewsContent());
        	newsAll.setStatus(news.getStatus());
        	newsAllManageService.updateNewsAll(newsAll);
        	
        	//return new StringBuffer("redirect:/newsall/opt-query/newsAllList.do??flag=1&").append(Constants.NEWS_ALL_MODULE).append("=").append(moduleCode).toString();
        	Message msg = new Message("/newsall/opt-query/newsAllList.do");
        	msg.setTips("修改成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			/**
			 * 新增
			 * moduleCode 通用新闻大类编码
			 * parentCode 父编码(产业服务和产业园区，支柱产业)
			 * moduleType 新闻类型(园区动态和园区政策);GARDEN_A——园区动态；GARDEN_B——园区政策；
			 * 根据业务需要自选参数，moduleCode为必填
			 */
			if(DataUtil.isNotNull(moduleCode)){
				news.setModuleCode(moduleCode);
			}
			newsAllManageService.saveNewsAll(news);
			//return new StringBuffer("redirect:/newsall/opt-query/newsAllList.do?").append(Constants.NEWS_ALL_MODULE).append("=").append(moduleCode).toString();
			
			Message msg = new Message("/newsall/opt-query/newsAllList.do");
			msg.setTips("添加成功");
        	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
        
        
	}
	
	/**
	 * 新闻的启用或禁用
	 * @param newsId
	 * @param value 
	 * @return
	 */
	@RequestMapping(value={"/newsall/opt-update/changeStatus"})
	public String changeStatus(ModelMap model,String newsId,String pageNo,String pageSize,String value,String moduleCode){
		
		this.newsAllManageService.changeStatus(newsId,value);
		
		//return new StringBuffer("redirect:/newsall/opt-query/newsAllList.do??flag=1&").append(Constants.NEWS_ALL_MODULE).append("=").append(moduleCode).append("&pageNo="+pageNo+"&pageSize="+pageSize).toString();
		Message msg = new Message("/newsall/opt-query/newsAllList.do");
		msg.setTips("操作成功");
    	msg.addParamForward("flag", "1");
    	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
    	msg.addParamForward("pageNo", pageNo);
    	msg.addParamForward("pageSize", pageSize);
    	msg.addParamForward("pageSearchType", pageNo);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 *查看新闻
	 * */
	@RequestMapping("/newsall/opt-query/newsAllInfo" )
	public String newsAllInfo(ModelMap model,HttpServletRequest request){
		//新闻id
		String newsId=request.getParameter("newsId");
		
		NewsAll newsAll = new NewsAll();
		
        if(DataUtil.isNotNull(newsId)){
        	newsAll = newsAllManageService.queryNewsAllByNewsId(newsId);
		}
        
        model.addAttribute("newsAll", newsAll);
        
        //启用状态字典集合
  		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
  		
  		//启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  				
  		model.addAttribute("statusList", statusList);
  		model.addAttribute("enable", enable);
        
        return "/newsall/newsInfo";
	}
	
	/**
	 *查看新闻
	 * */
	@RequestMapping("/newsall/opt-query/newsAllDetail" )
	public String newsAllDetail(ModelMap model,HttpServletRequest request){
		//新闻id
		String newsId=request.getParameter("id");
		
		NewsAll newsAll = new NewsAll();
		
        if(DataUtil.isNotNull(newsId)){
        	newsAll = newsAllManageService.queryNewsAllByNewsId(newsId);
		}
        
        model.addAttribute("newsAll", newsAll);
        
        return "/newsall/newsDetail";
	}
	
	
	/**
	 *删除新闻
	 * */
	@RequestMapping("/newsall/opt-del/deleteNewsAll" )
	public String deleteNewsAll(ModelMap model,HttpServletRequest request){
		//新闻id
		String newsId=request.getParameter("newsId");
		
		String moduleCode = request.getParameter(Constants.NEWS_ALL_MODULE);
		
        if(DataUtil.isNotNull(newsId)){
        	newsAllManageService.deleteNewsAllById(newsId);
		}
        
        //return new StringBuffer("redirect:/newsall/opt-query/newsAllList.do??flag=1&").append(Constants.NEWS_ALL_MODULE).append("=").append(moduleCode).toString();
        Message msg = new Message("/newsall/opt-query/newsAllList.do");
		msg.setTips("删除成功");
    	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 *批量删除新闻
	 * */
	@RequestMapping("/newsall/opt-del/pdeleteNewsAll" )
	public String deleteNewsAlls(ModelMap model,HttpServletRequest request){
		//新闻ids
		String newsIds=request.getParameter("newsIds");
		
		String moduleCode = request.getParameter(Constants.NEWS_ALL_MODULE);
		
        if(DataUtil.isNotNull(newsIds)){
        	newsAllManageService.deleteNewsAllsById(newsIds);
		}
        
        //return new StringBuffer("redirect:/newsall/opt-query/newsAllList.do??flag=1&").append(Constants.NEWS_ALL_MODULE).append("=").append(moduleCode).toString();
        Message msg = new Message("/newsall/opt-query/newsAllList.do");
		msg.setTips("批量删除成功");
    	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
 
}
