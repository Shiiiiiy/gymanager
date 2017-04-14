package com.uws.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.uws.model.Company;
import com.uws.model.Dic;
import com.uws.model.FileInfo;
import com.uws.model.IndustryGarden;
import com.uws.model.NewsAll;
import com.uws.model.SysUser;
import com.uws.service.IFileInfoService;
import com.uws.service.IIndustryGardenService;
import com.uws.service.INewsAllManageService;
import com.uws.service.IPillarIndustryService;
import com.uws.service.ISysDicService;
import com.uws.service.ISysRolePermissionService;
import com.uws.util.Constants;
import com.uws.util.Util;

/**
 * 
 * @ClassName: PillarIndustry 支柱产业 controller
 * @Description: 
 * @date: 2017-3-13 上午9:50:08
 */
@Controller
@RequestMapping("/pillarindustry")
public class PillarIndustryController extends BaseController{

	@Resource
	private IPillarIndustryService pillarIndustryService;
	
	@Resource
	private IIndustryGardenService industryGardenService;
	
	@Resource
	private ISysDicService sysDicService;
	
	@Resource 
	private INewsAllManageService newsAllManageService;
	
	@Resource
	private IFileInfoService fileService;
	
	@Resource ISysRolePermissionService sysRolePermissionService;
	
	/***************************************** 新闻管理  ******************************/
	/**
	 * 支柱产业新闻列表页
	 * @param model     
	 * @param news     保存查询条件
	 * @param request
	 * @return
	 */
	@RequestMapping("opt-query/newslist")
	public String serachNewsPage(ModelMap model,HttpSession session,NewsAll news,HttpServletRequest request){
		
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
		String flag = request.getParameter("flag");
		
		if("1".equals(flag)){
			news = (NewsAll) session.getAttribute("news_ind");
		}
		
		Map<String,Object> param = this.initParamMap(request);
		
		//支柱产业新闻code
		if(!DataUtil.isNotNull(news.getModuleCode())){
			news.setModuleCode(Constants.MODULE_CODE.NEWS_M.toString());
		}
		
		//所有支柱产业map集合
/*		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(Constants.INDUSTRY_D, null,true);
*/		
		
		@SuppressWarnings("rawtypes")
		List<Map> mapList = sysRolePermissionService.queryIndustryResult(userId,true);
		
		Page page = this.industryGardenService.queryGardenNews(param, news,mapList);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		//禁用状态字典
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		
		session.setAttribute("news_ind", news);
		
		model.addAttribute("page",page);
		model.addAttribute("enable",enable);
		model.addAttribute("disable",disable);
		model.addAttribute("mapList",mapList);
		model.addAttribute("news",news);
		
		return "/pillarindustry/news/newslist";
	}
	
	/**
	 * 新增或编辑 新闻
	 * @param id
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value={"opt-add/addnews","opt-edit/editnews"})
	public String addIndustryNews(String id,HttpSession session,ModelMap model,String pageSize,String pageNo){
		
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
		if(!Util.isNull(id)){//修改
			NewsAll gardenNews = newsAllManageService.queryNewsAllByNewsId(id);
        	if(!DataUtil.isNotNull(gardenNews.getParentCode())){
        		gardenNews.setParentCode("MODULE_E_MAIN");
        	}
			model.addAttribute("gardenNews",gardenNews);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		//所有支柱产业map集合
/*		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(Constants.INDUSTRY_D, null,true);
*/
		List<Map> mapList = this.sysRolePermissionService.queryIndustryResult(userId, true);

		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("mapList",mapList);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("pageNo", pageNo);
		
		return "/pillarindustry/news/addOrEditNewsAll";
	}
	
	/**
	 * 保存或更新新闻对象
	 * @param news
	 * @return
	 */
	@RequestMapping("/opt-save/savenews")
	public String saveNews(NewsAll news,ModelMap model,String pageSize,String pageNo){
		
		news.setModuleCode(Constants.MODULE_CODE.NEWS_M.toString());
		
		this.industryGardenService.saveOrUpdateNews(news);
		
		String url = "/pillarindustry/opt-query/newslist.do?pageSize="+pageSize;
		if(DataUtil.isNotNull(pageSize) && DataUtil.isNotNull(pageNo)){
			url+="&flag=1&pageNo="+pageNo+"&pageSearchType=c";
		}
		
		Message msg = new Message(url);
		
		msg.setTips("保存成功");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
//		return "redirect:/pillarindustry/opt-query/newslist";
		
	}
	
	/**
	 * 删除 一个 或多个 园区新闻（政策+动态）
	 * @param ids
	 * @param moduleType
	 * @return
	 */
	@RequestMapping("/opt-del/delnews")
	public String delGardenNews(ModelMap model,String ids,String pageSize,String pageNo){
		
		
		this.industryGardenService.deleteNews(ids);
		
		Message msg = new Message("/pillarindustry/opt-query/newslist.do?flag=1&pageSize="+pageSize+"&pageNo="+pageNo+"&pageSearchType=c");
		
		msg.setTips("删除成功");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
//		return "redirect:/pillarindustry/opt-query/newslist";
	}
	
	/**
	 * 改变启用禁用状态
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @param value
	 * @return
	 */
	@RequestMapping("/opt-update/changestatus")
	public String changeNewsStatus(ModelMap model,String id,String pageNo,String pageSize,String value){
		
		this.newsAllManageService.changeStatus(id,value);
		
		Message msg = new Message("/pillarindustry/opt-query/newslist.do?flag=1&pageNo="+pageNo+"&pageSize="+pageSize+"&pageSearchType=x");
		
		if(value.equals("2")){
			msg.setTips("启用成功");
		}else{
			msg.setTips("禁用成功");
		}
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	//	return "redirect:/pillarindustry/opt-query/newslist?&pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	@RequestMapping("/opt-view/newsinfo")
	public String viewNews(ModelMap model,String id){
		
		
		NewsAll gardenNews = newsAllManageService.queryNewsAllByNewsId(id);
    	if(!DataUtil.isNotNull(gardenNews.getParentCode())){
    		gardenNews.setParentCode("MODULE_E_MAIN");
    	}
    	
    	Dic status = this.sysDicService.findById(String.valueOf(gardenNews.getStatus()));

    	String industryName = null;
    	if(!"MODULE_E_MAIN".equals(gardenNews.getParentCode())){
    		industryName = Constants.INDUSTRY_MAP.get(gardenNews.getParentCode());
    	}else{
    		industryName ="主模块"; 
    	}
		model.addAttribute("gardenNews",gardenNews);
		model.addAttribute("status", status);
		model.addAttribute("industryName", industryName);
		
		return "/pillarindustry/news/newsinfo";
	}
	
	/******************************************* 新闻管理完 *************************************************/
	
	/******************************************* 产业管理  *************************************************/
	
	@RequestMapping("/opt-query/industrylist") 
	public String serachIndustryPage(HttpServletRequest request,HttpSession session,ModelMap model,IndustryGarden industry){
		 
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
		//获取分页条件
		Map<String, Object> param = this.initParamMap(request);

		//所有支柱产业map集合
/*		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(Constants.INDUSTRY_D, Constants.INDUSTRY_TYPE,true);
	*/			
		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.sysRolePermissionService.queryIndustryResult(userId, false);
		
		if(mapList.size()>=Constants.INDUSTRY_MAP.size()){
			model.addAttribute("per","initper");
		}
		
		Page page = this.industryGardenService.searchIndustryGardenPage(param, Constants.INDUSTRY_TYPE, Constants.INDUSTRY_D,industry,mapList);
		
		model.addAttribute("page",page);
		model.addAttribute("mapList", mapList);
		model.addAttribute("industry",industry);
		
		return "/pillarindustry/industry/industrylist";
	}
	
	
	@RequestMapping("/opt-edit/editindustry")
	public String editIndustry(ModelMap model,HttpSession session,String id){
		
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
		IndustryGarden industry = this.industryGardenService.findById(id);
		
		//所有支柱产业map集合
	/*	@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(Constants.INDUSTRY_D, Constants.INDUSTRY_TYPE,true);
	*/	
		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.sysRolePermissionService.queryIndustryResult(userId, false);
		
		model.addAttribute("mapList",mapList);
		model.addAttribute("industry", industry);
		
		return "/pillarindustry/industry/industryinfo";
	}
	
	
	/**
	 * 保存产业概况和概况图片信息
	 * @param garden
	 * @return
	 */
	@RequestMapping("/opt-save/saveindustry")
	public String saveIndustryIntroduce(ModelMap model,IndustryGarden industry){
		
		IndustryGarden newGarden = this.industryGardenService.findById(industry.getId());
		
		newGarden.setIntroduce(industry.getIntroduce());
		newGarden.setIntroducePic(industry.getIntroducePic());
		
		this.industryGardenService.updateModel2(newGarden);
		
		Message msg = new Message("/pillarindustry/opt-query/industrylist.do");
		
		msg.setTips("保存成功");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	//	return "redirect:/pillarindustry/opt-query/industrylist";
	}
	
	/**
	 * 获取需要初始化的产业的信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/opt-query/initlist")
	public String getInitList(){
		
		String returnstr = null;
		//获取需要初始化的园区id集合
		List<String> initIndustryId = this.pillarIndustryService.getIndustryInitList();
		
		if(initIndustryId == null || initIndustryId.size() ==0){
			returnstr = "";
		}else{
			StringBuffer ids = new StringBuffer();
			
			for (String string : initIndustryId) {
				ids.append(string+",");
			}
			
			returnstr = ids.substring(0,ids.length()-1);
		}
		return returnstr;
	}
	
	/**
	 * 初始化支柱产业
	 * @param ids 需要初始化的园区id 通过 , 拼接起来的字符串
	 * @return
	 */
	@RequestMapping("/opt-init/initindustry")
	public String initGarden(String ids,ModelMap model){
		
		this.pillarIndustryService.initIndustry(ids);
		
		Message msg = new Message("/pillarindustry/opt-query/industrylist.do");
		
		msg.setTips("初始化成功");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	//	return "redirect:/pillarindustry/opt-query/industrylist"; 
	}
	
	
	/**********************************************产业管理完*******************************/
	
	/**********************************************资源管理*********************************/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/opt-query/resource")
	public String queryResource(String flag,HttpSession session,ModelMap model,String parentCode){
		
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
		if(DataUtil.isNull(flag)){
			flag = "1";
		}
		
		//所有支柱产业map集合
/*		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(Constants.INDUSTRY_D, Constants.INDUSTRY_TYPE,true);
*/	
		List<Map> mapList = this.sysRolePermissionService.queryIndustryResult(userId, true);
		
		if(DataUtil.isNull(parentCode)){
			if(mapList.size()>0 ){
				parentCode = mapList.get(0).get("ID").toString();
			}else{
				parentCode ="1a2b3c";    //没有权限 随便给的一个 code
				Map map = new HashMap();
				map.put("NAME","没有权限");
				map.put("ID","1a2b3c");
				mapList.add(map);
			}
		}
		
		List<Map> fileMap = pillarIndustryService.queryFileInfoList(Constants.MODULE_E,parentCode);
		
		model.addAttribute("mapList",mapList);
		model.addAttribute("fileList", fileMap);
		model.addAttribute("flag", flag);
		model.addAttribute("slider", Constants.FILE_SLIDER);
		model.addAttribute("compa", Constants.COMP_A);
		model.addAttribute("statistics", Constants.FILE_STATISTICS);
		model.addAttribute("file_a", Constants.FILE_A);
		model.addAttribute("parentCode",parentCode);
		
		
		return "/pillarindustry/resource/sourceList";
	}
	/**
	 * 新增资源文件
	 * @param model
	 * @param flag
	 * @param parentCode
	 * @param fileType
	 * @param fileName
	 * @return
	 */
	@RequestMapping("/opt-save/saveresource")
	public String saveResourceFile(ModelMap model,String flag,String parentCode,String fileType,String fileName){
		
		FileInfo file = new FileInfo();
		file.setFileName(fileName);
		file.setFileTime(new Date());
		file.setModuleCode(Constants.MODULE_E);
		file.setParentCode(parentCode);
		file.setFileType(fileType);
		file.setSort(1);
		fileService.saveFileInfo(file);
		
//		Message msg = new Message("/pillarindustry/opt-query/resource.do?flag="+flag);
//		msg.setTips("保存成功");
//		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
//		return "toForward"; 
		
		return "redirect:/pillarindustry/opt-query/resource?flag="+flag+"&parentCode="+parentCode;
	}
	
	/**
	 * 支柱产业  资源管理  删除图片
	 */
	@RequestMapping("/opt-delete/deleteFile")
	public String deleteFile(HttpServletRequest request,String fileId,String parentCode,String flag,Model model){
		fileService.deleteFileById(fileId);
		
		Message msg = new Message("/pillarindustry/opt-query/resource.do?flag="+flag+"&parentCode="+parentCode);
		
		msg.setTips("删除成功");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	//    return "redirect:/pillarindustry/opt-query/resource?flag="+flag+"&parentCode="+parentCode;  
	}
	
	
	/**
	 * 支柱产业  资源管理  编辑图片
	 * @throws Exception 
	 */
	@RequestMapping("/opt-update/updateFileTitle")
	public String updateFileTitle(HttpServletRequest request,String flag,String parentCode,String fileTitle,String fileId,String url,String urlType,String sort,Model model) throws Exception{
		fileTitle = java.net.URLDecoder.decode(fileTitle,"UTF-8");
		url = java.net.URLDecoder.decode(url,"UTF-8");
		fileService.updateFileName(fileId, fileTitle, url, urlType,sort);
	    return "redirect:/pillarindustry/opt-query/resource?flag="+flag+"&parentCode="+parentCode; 
	}
	
	/*****************************************资源管理 完******************************************/
	
	/*****************************************企业列表********************************************/
	
	/**
	 * 进入列表页
	 * @param request
	 * @param model
	 * @param comp
	 * @param session
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/opt-query/companylist")
	public String queryCompanyList(HttpServletRequest request,ModelMap model,Company comp,HttpSession session){
		
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
		Map<String,Object> param = initParamMap(request);
		
		String flag = request.getParameter("flag");
		if("1".equals(flag)){
			comp = (Company) session.getAttribute("comp_");
		}
		
		//所有支柱产业map集合
//		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(Constants.INDUSTRY_D, Constants.INDUSTRY_TYPE,true);
		
		List<Map> mapList = this.sysRolePermissionService.queryIndustryResult(userId, false);
		
		Page page = this.pillarIndustryService.searchCompanyPage(param, comp,mapList);
		
		//优质企业ID集合 
		List<String> hqcList = this.pillarIndustryService.queryHighQualityCompany();
		
		//推荐企业ID集合
		List<String> recommendList =  this.pillarIndustryService.queryRecommendCompany();
		
		
		List<Map> result = page.getList();
		for (Map map : result) {
			
			if(hqcList.contains(map.get("ID").toString())){
				map.put("HQC","YES");   //如果是优质企业的话 给一个标识字段 HQC
			}
			if(recommendList.contains(map.get("ID").toString())){
				map.put("RC","YES");   //如果是推荐企业的话 给一个标识字段 RC
			}
		}
		
		
		session.setAttribute("comp_", comp);
		model.addAttribute("page",page);
		model.addAttribute("mapList",mapList);
		model.addAttribute("comp", comp);
		
		return "/pillarindustry/company/companylist";
	}
	
	/**
	 * 从 支柱产业 企业列表 中 移除（实际上是删除 CONPANIES_PROP 表上的数据）
	 * @param compIds
	 * @param session
	 * @return
	 */
	@RequestMapping("/opt-del/delcompany")
	public String deleteCompany(ModelMap model,String compIds,HttpSession session){
		
		this.pillarIndustryService.deleteIndustryCompany(compIds);
		
		Message msg = new Message("/pillarindustry/opt-query/companylist.do?flag=1");
		
		msg.setTips("删除成功");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	//	return "redirect:/pillarindustry/opt-query/companylist?flag=1";
	}
	
	/**
	 * 推荐 或 取消推荐 企业
	 * @param compIds  企业id 可以多个
	 * @param type     YES 推荐  NO 不推荐
	 * @param pageSize  
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/opt-edit/changeCompanyHQ")
	public String changeCompanyHQ (ModelMap model,String compIds,String type,String pageSize,String pageNo,String changeType){
		
		this.pillarIndustryService.changeCompanyHq(compIds, changeType,type);
		
		Message msg = new Message("/pillarindustry/opt-query/companylist.do?flag=1&pageSize="+pageSize+"&pageNo="+pageNo+"&pageSearchType=y");
		
		msg.setTips("操作成功");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	//	return "redirect:/pillarindustry/opt-query/companylist?flag=1&pageSize="+pageSize+"&pageNo="+pageNo;
		
	}
	
	/**
	 * 对企业设置 产业分类
	 * @param compIds     企业id
	 * @param industryIds 分类id
	 * @param pageSize 
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/opt-edit/setClass")
	public String setCompanyIndustryClass(ModelMap model,String compIds,String industryIds,String pageSize,String pageNo){
		
		this.pillarIndustryService.setCompanyIndustryClass(compIds, industryIds);
		
		Message msg = new Message("/pillarindustry/opt-query/companylist?flag=1&pageSize="+pageSize+"&pageNo="+pageNo+"&pageSearchType=s");
		
		msg.setTips("设置成功");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	//	return "redirect:/pillarindustry/opt-query/companylist?flag=1&pageSize="+pageSize+"&pageNo="+pageNo;
	}
	
	/**
	 * 新增 支柱产业 企业列表数据 （从企业表（company_info）中选择企业添加  到 关系表里（conpanies_prop） ）
	 */
	@RequestMapping("/opt-add/addCompany")
	public String addCompany(ModelMap model,String compIds){
		this.pillarIndustryService.addCompany(compIds);
		
		Message msg = new Message("/pillarindustry/opt-query/companylist.do");
		
		msg.setTips("选择成功");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	//	return "redirect:/pillarindustry/opt-query/companylist?flag=1";
	}
	
	
	
}
