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
import com.uws.model.Dic;
import com.uws.model.FileInfo;
import com.uws.model.GardenProject;
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
 * @ClassName: IndustryGardenController  产业园区controller
 * @Description: 
 * @date: 2017-3-13 上午9:49:21
 */
@Controller
@RequestMapping("/industrygarden")
public class IndustryGardenController extends BaseController{
	
	@Resource
	private IIndustryGardenService industryGardenService;
	
	@Resource
	private ISysDicService sysDicService;
	
	@Resource 
	private INewsAllManageService newsAllManageService;
	
	@Resource 
	public IPillarIndustryService pillarIndustryService;
	
	@Resource
	private IFileInfoService fileService;
	
	@Resource
	private ISysRolePermissionService sysRolePermissionService;

	/******************************************园区管理**********************************/
	/**
	 * 查询产业园区信息分页列表
	 * @param model
	 * @param request
	 * @param garden 保存查询条件
	 * @return
	 */
	@RequestMapping("/opt-query/gardenlist")
	public String searchGardenList(ModelMap model,HttpServletRequest request,HttpSession session,IndustryGarden garden){
		
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
		//获取分页条件
		Map<String, Object> param = this.initParamMap(request);
		
/**		//所有产业园区map集合
		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(null, Constants.GARDEN_TYPE,true);
*/
		@SuppressWarnings("rawtypes")
		List<Map> mapList = sysRolePermissionService.queryPerMapListByUser(userId, Constants.ROLE_PER_GARDEN);
		
		if(mapList.size()>=Constants.GARDEN_MAP.size()){
			model.addAttribute("per","initper");
		}
		
		//查询产业园区分页列表
		Page page =  this.industryGardenService.searchIndustryGardenPage(param,Constants.GARDEN_TYPE,null,garden,mapList);
		
		model.addAttribute("mapList",mapList);//填充下拉框
		model.addAttribute("garden",garden);//填充查询条件
		model.addAttribute("page",page);//填充表格数据
		
		return "garden/garden/gardenlist";
	}
	
	/**
	 * 进入园区简介编辑页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/opt-edit/gardenintorduce")
	public String editGardenIntroduce(ModelMap model,String id,HttpSession session,String pageSize,String pageNo){
	
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
		IndustryGarden garden = null;
		
		//判断传递过来id否为空
		if(DataUtil.isNotNull(id)){
			garden =  this.industryGardenService.findById(id);
		}
		
		//判断需要编辑的对象是否存在
		if(DataUtil.isNotNull(garden)){
			
			model.addAttribute("garden",garden);//填充查询条件
			
/**			@SuppressWarnings("rawtypes")
			List<Map> mapList = this.industryGardenService.queryIndustryGardenList(null, Constants.GARDEN_TYPE,true);		
*/
			@SuppressWarnings("rawtypes")
			List<Map> mapList = sysRolePermissionService.queryPerMapListByUser(userId, Constants.ROLE_PER_GARDEN);
			model.addAttribute("mapList",mapList);//填充下拉框
			
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageNo", pageNo);
			
			//跳转到编辑页面
			return "garden/garden/gardenIntroduce";
		}else{
			
			//如果为空跳转到列表页面
			return "redirect:/industrygarden/opt-query/gardenlist";
		}
		
	}
	
	/**
	 * 保存产业园简介和简介图片信息
	 * @param garden
	 * @return
	 */
	@RequestMapping("/opt-save/savegarden")
	public String saveGardenIntroduce(ModelMap model,IndustryGarden garden,String pageSize,String pageNo){
		
		
		IndustryGarden newGarden = this.industryGardenService.findById(garden.getId());
		
		newGarden.setIntroduce(garden.getIntroduce());
		newGarden.setIntroducePic(garden.getIntroducePic());
		 
		this.industryGardenService.updateModel2(newGarden);
		
		String url = "/industrygarden/opt-query/gardenlist.do?pageSize="+pageSize+"&pageNo="+pageNo+"&pageSearchType=1";
		
		Message msg = new Message(url);
		
		msg.setTips("保存成功");
		msg.addParamForward("", "");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		//return "redirect:/industrygarden/opt-query/gardenlist";
		return "toForward";
	}
	
	
	/**
	 * 获取需要初始化的园区的信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/opt-query/initlist")
	public String getInitList(){
		
		String returnstr = null;
		//获取需要初始化的园区id集合
		List<String> initGardenId = this.industryGardenService.getGardenInitList();
		
		if(initGardenId == null || initGardenId.size() ==0){
			returnstr = "";
		}else{
			StringBuffer ids = new StringBuffer();
			
			for (String string : initGardenId) {
				ids.append(string+",");
			}
			
			returnstr = ids.substring(0,ids.length()-1);
		}
		return returnstr;
	}
	
	/**
	 * 初始化产业园区
	 * @param ids 需要初始化的园区id 通过 , 拼接起来的字符串
	 * @return
	 */
	@RequestMapping("/opt-init/initgarden")
	public String initGarden(String ids,ModelMap model){
		
		this.industryGardenService.initGarden(ids);
		
		Message msg = new Message("/industrygarden/opt-query/gardenlist.do");
		
		msg.setTips("初始化成功");
		msg.addParamForward("", "");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		
		return "toForward"; 
	}
	
	@RequestMapping("/opt-view/gardeninfo")
	public String viewGardenInfo(ModelMap model,String id){
		
		IndustryGarden garden = this.industryGardenService.findById(id);
		
		model.addAttribute("garden",garden);
		
		return "/garden/garden/gardeninfo";
	}
	
	
	/******************************************园区管理完**********************************/
	
	/*******************************************园区项目***********************************/
	
	/**
	 * 查询园区项目信息分页列表
	 * @param model    
	 * @param request
	 * @param project  保存查询条件
	 * @return
	 */
	@RequestMapping("/opt-query/projectlist")
	public String queryGardenProject(ModelMap model,HttpSession session,HttpServletRequest request,GardenProject project){

		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		String flag = request.getParameter("flag");
		
		if("1".equals(flag)){
			project  = (GardenProject) session.getAttribute("project_gar");
		}
		
		//获取分页条件
		Map<String, Object> param = this.initParamMap(request);
		
		//所有产业园区map集合   不包含主园区 主园区下没有项目
/*		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(null, Constants.GARDEN_TYPE,false);
*/		
		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.sysRolePermissionService.queryGardenPerWithoutMain(userId);
		
		//项目类型字典集合
		List<Dic> projectTypelist = sysDicService.getDicListByCode("PROJECT_TYPE");
		
		//项目状态字典集合
		List<Dic> projectStatuslist = sysDicService.getDicListByCode("PROJECT_STATUS");
		
		Page page = this.industryGardenService.queryGardenProject(param, project,mapList);
		
		session.setAttribute("project_gar", project);
		model.addAttribute("page",page);
		model.addAttribute("mapList",mapList);
		model.addAttribute("project",project);
		model.addAttribute("projectTypelist",projectTypelist);
		model.addAttribute("projectStatuslist",projectStatuslist);
		
		return "/garden/project/projectlist";
	}
	
	/**
	 * 跳转至编辑园区项目界面（新增+修改）
	 * @param model
	 * @param id 
	 * @return
	 */
	@RequestMapping(value={"/opt-add/addproject","/opt-edit/editproject"})
	public String addGardenProject(ModelMap model,String id,HttpSession session,String pageNo,String pageSize){
		
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
/*		//所有产业园区map集合
		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(null, Constants.GARDEN_TYPE,false);
*/
		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.sysRolePermissionService.queryGardenPerWithoutMain(userId);
		
		//项目类型字典集合
		List<Dic> projectTypelist = sysDicService.getDicListByCode("PROJECT_TYPE");
		
		//项目状态字典集合
		List<Dic> projectStatuslist = sysDicService.getDicListByCode("PROJECT_STATUS");

		if(DataUtil.isNotNull(id)){ //编辑
			GardenProject project = this.industryGardenService.findProjectById(id);
			model.addAttribute("project",project);
		}else{
			//拟建状态字典  
			Dic planStruct = this.sysDicService.getDicByCodes("PROJECT_STATUS", "PROSTATUS_A");
			model.addAttribute("planStruct",planStruct);
		}
		
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("mapList",mapList);
	
		model.addAttribute("projectTypelist",projectTypelist);
		model.addAttribute("projectStatuslist",projectStatuslist);
		
		
		return "/garden/project/addproject";
	}
	
	/**
	 * 保存或者更新园区项目对象
	 * @param request
	 * @param project
	 * @return
	 */
	@RequestMapping("/opt-save/saveproject")
	public String saveProject(ModelMap model,HttpServletRequest request,GardenProject project,String pageSize,String pageNo){
		
		//保存或者更新园区项目对象
		this.industryGardenService.saveOrUpdateProject(project);
		
		String url = "/industrygarden/opt-query/projectlist.do?pageSize="+pageSize;
		
		if(DataUtil.isNotNull(pageNo)){   // 编辑的话 跳转到 原页数，新增 跳转到首页
			url+="&flag=1&pageNo="+pageNo+"&pageSearchType=1";
		}
		
		Message msg = new Message(url);
		
		msg.setTips("保存成功");
		msg.addParamForward("", "");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		//返回到列表页面
	}
	
	/**
	 * 删除园区项目
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping("/opt-del/delproject")
	public String deleteProject(ModelMap model,HttpServletRequest request,String ids,String pageSize){
		
		//删除园区项目 ，根据传入的 ids 可删除一个或多个
		this.industryGardenService.deleteProject(ids);
		
		Message msg = new Message("/industrygarden/opt-query/projectlist.do?flag=1&pageSize="+pageSize);
		
		msg.setTips("删除成功");
		msg.addParamForward("", "");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
	}
	
	
	@RequestMapping("/opt-view/projectinfo")
	public String viewGarden(String id,ModelMap model){
		
		GardenProject project = industryGardenService.findProjectById(id);
    	
		if(!DataUtil.isNotNull(project)){
			return "redirct:/industrygarden/opt-query/projectlist.do";
		}
		
		Dic status = this.sysDicService.getDicByCodes("PROJECT_STATUS", project.getProjectStatus());
		
		Dic type = this.sysDicService.getDicByCodes("PROJECT_TYPE",project.getProjectType());
    	
		String gardenName = Constants.GARDEN_MAP.get(project.getOfGarden());
		
		model.addAttribute("project",project);
		model.addAttribute("status", status);
		model.addAttribute("type", type);
		model.addAttribute("gardenName", gardenName);
		
		return "/garden/project/projectinfo";
		
	}
	
	/*********************************************园区项目完********************************/
	
	/*******************************************新闻管理************************************/
	
	/**
	 * 园区动态和政策分页列表页面
	 * @param request
	 * @param news
	 * @param model
	 * @return
	 */
	@RequestMapping("/opt-query/newslist")
	public String newsAllList(HttpServletRequest request,HttpSession session,NewsAll news,ModelMap model){
		
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		String flag = request.getParameter("flag");
		
		if("1".equals(flag)){
			news = (NewsAll)session.getAttribute("news_gar");
		}
		
		Map<String, Object> param = this.initParamMap(request);
		
		if(!DataUtil.isNotNull(news.getModuleCode())){
			news.setModuleCode(Constants.MODULE_CODE.NEWS_L.toString());
		}
		
		//所有产业园区map集合
/*		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(null, Constants.GARDEN_TYPE,true);
*/		
		@SuppressWarnings("rawtypes")
		List<Map> mapList = sysRolePermissionService.queryPerMapListByUser(userId, Constants.ROLE_PER_GARDEN);
		
		
		Page page=industryGardenService.queryGardenNews(param, news,mapList);

		//新闻类型   （动态+政策）
		List<Dic> newsType = this.sysDicService.getDicListByCode("GARDEN_NEWS_TYPE"); 
		

		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		//禁用状态字典
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		
		session.setAttribute("news_gar", news);
		model.addAttribute("newsType",newsType);
		model.addAttribute("page", page);
		model.addAttribute("enable",enable);
		model.addAttribute("disable", disable);
		model.addAttribute("news",news);
		model.addAttribute("mapList",mapList);
		
	    return "/garden/news/newslist";  
	}
	
	
	/**
	 * 进入新增或修改新闻页面
	 */
	@RequestMapping(value = {"/opt-add/addnews","/opt-edit/editnews"})
	public String addOrEditNewsAll(HttpSession session,ModelMap model,String id,String pageSize,String pageNo){
		
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
		if(!Util.isNull(id)){//修改
			NewsAll gardenNews = newsAllManageService.queryNewsAllByNewsId(id);
        	if(!DataUtil.isNotNull(gardenNews.getParentCode())){
        		gardenNews.setParentCode("IG_C000");
        	}
			model.addAttribute("gardenNews",gardenNews);
		}
		
		//新闻类型   （动态+政策）
		List<Dic> newsType = this.sysDicService.getDicListByCode("GARDEN_NEWS_TYPE"); 
		
		//所有产业园区map集合
/*		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(null, Constants.GARDEN_TYPE,true);
*/		
		@SuppressWarnings("rawtypes")
		List<Map> mapList = sysRolePermissionService.queryPerMapListByUser(userId, Constants.ROLE_PER_GARDEN);
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("mapList",mapList);
		model.addAttribute("newsType", newsType);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("pageNo",pageNo);
		/**
		 * 根据自己业务带上 父编码、新闻类型
		 * 分类下拉列表数据自查
		 */
		//model.addAttribute(Constants.NEWS_ALL_MODULE,"");
		
	    return "/garden/news/addOrEditNewsAll";  
	}
	
	/**
	 * 保存或更新园区动态或政策
	 * @param news
	 * @return
	 */
	@RequestMapping("/opt-save/savenews")
	public String saveGardenNews(ModelMap model,NewsAll news,String pageNo,String pageSize){
		
		news.setModuleCode(Constants.MODULE_CODE.NEWS_L.toString());
		
		this.industryGardenService.saveOrUpdateNews(news);
		
		String url = "/industrygarden/opt-query/newslist.do?pageSize="+pageSize;
		if(DataUtil.isNotNull(pageNo)){
			url+="&flag=1&pageNo="+pageNo+"&pageSearchType=1";
		}
		
		Message msg = new Message(url);
		
		msg.setTips("保存成功");
		msg.addParamForward("", "");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
	}
	
	/**
	 * 删除 一个 或多个 园区新闻（政策+动态）
	 * @param ids
	 * @param moduleType
	 * @return
	 */
	@RequestMapping("/opt-del/delnews")
	public String delGardenNews(ModelMap model,String ids,String pageSize){
		
		
		this.industryGardenService.deleteNews(ids);
		
		Message msg = new Message("/industrygarden/opt-query/newslist.do?flag=1&pageSize="+pageSize);
		
		msg.setTips("删除成功");
		msg.addParamForward("", "");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	}
	
	@RequestMapping("/opt-update/changestatus")
	public String changeNewsStatus(ModelMap model,String id,String pageNo,String pageSize,String value){
		
		this.newsAllManageService.changeStatus(id,value);
		
		Message msg = new Message("/industrygarden/opt-query/newslist.do?flag=1&pageNo="+pageNo+"&pageSize="+pageSize+"&pageSearchType=1");
		if(value.equals("2")){
			msg.setTips("启用成功");
		}else{
			msg.setTips("禁用成功");
		}
		msg.addParamForward("", "");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
		
	//	return "redirect:/industrygarden/opt-query/newslist?pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	@RequestMapping("/opt-view/newsinfo")
	public String viewNews(ModelMap model,String id){
		
		NewsAll gardenNews = newsAllManageService.queryNewsAllByNewsId(id);
    	if(!DataUtil.isNotNull(gardenNews.getParentCode())){
    		gardenNews.setParentCode("IG_C000");
    	}
    	
    	//新闻类型   （动态+政策）
		Dic newsType = this.sysDicService.getDicByCodes("GARDEN_NEWS_TYPE",gardenNews.getModuleType()); 
    	//启用状态
		Dic status = this.sysDicService.findById(String.valueOf(gardenNews.getStatus()));
    	
		String gardenName = Constants.GARDEN_MAP.get(gardenNews.getParentCode());
		
		model.addAttribute("gardenNews",gardenNews);
		model.addAttribute("status", status);
		model.addAttribute("newsType", newsType);
		model.addAttribute("gardenName", gardenName);
		
		return "/garden/news/newsinfo";  
	}
	
	
	/*******************************************新闻管理完************************************/
	
	
	/********************************************资源管理************************************/
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/opt-query/resource")
	public String queryResource(HttpSession session,String flag,ModelMap model,String parentCode){
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		String userId = user.getId().toString();
		
		if(DataUtil.isNull(flag)){
			flag = "1";
		}
		
		//所有产业园区map集合
/*		@SuppressWarnings("rawtypes")
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(null, Constants.GARDEN_TYPE,true);
		
*/		
		List<Map> mapList = sysRolePermissionService.queryPerMapListByUser(userId, Constants.ROLE_PER_GARDEN);

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

		List<Map> fileMap = pillarIndustryService.queryFileInfoList(Constants.MODULE_F,parentCode);
		
		model.addAttribute("mapList",mapList);
		model.addAttribute("fileList", fileMap);
		model.addAttribute("flag", flag);
		model.addAttribute("slider", Constants.FILE_SLIDER);
		model.addAttribute("statistics", Constants.FILE_STATISTICS);
		model.addAttribute("comp_a", Constants.COMP_A);
		model.addAttribute("file_a", Constants.FILE_A);
		
		model.addAttribute("parentCode",parentCode);
		
		
		return "/garden/resource/sourceList";
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
		file.setModuleCode(Constants.MODULE_F);
		if(!parentCode.equals("IG_C000")){    //不是主园区的时候才给parentCode赋值，否则为空
			file.setParentCode(parentCode);
		}
		file.setFileType(fileType);
		file.setSort(1);
		fileService.saveFileInfo(file);
		
		Message msg = new Message("/industrygarden/opt-query/resource?flag="+flag+"&parentCode="+parentCode);
		
		msg.setTips("上传成功");
		msg.addParamForward("", "");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	}
	
	/**
	 * 产业园区  资源管理  删除图片
	 */
	@RequestMapping("/opt-delete/deleteFile")
	public String deleteFile(HttpServletRequest request,String fileId,String parentCode,String flag,Model model){
		fileService.deleteFileById(fileId);
		
		Message msg = new Message("/industrygarden/opt-query/resource?flag="+flag+"&parentCode="+parentCode);
		
		msg.setTips("删除成功");
		msg.addParamForward("", "");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
		
	  //  return "redirect:/industrygarden/opt-query/resource?flag="+flag+"&parentCode="+parentCode;  
	}
	
	
	/**
	 * 产业园区  资源管理  编辑图片
	 * @throws Exception 
	 */
	@RequestMapping("/opt-update/updateFileTitle")
	public String updateFileTitle(HttpServletRequest request,String flag,String parentCode,String fileTitle,String fileId,String url,String urlType,String sort,Model model) throws Exception{
		fileTitle = java.net.URLDecoder.decode(fileTitle,"UTF-8");
		url = java.net.URLDecoder.decode(url,"UTF-8");
		fileService.updateFileName(fileId, fileTitle, url, urlType,sort);
		
		Message msg = new Message("/industrygarden/opt-query/resource?flag="+flag+"&parentCode="+parentCode);
		
		msg.setTips("保存成功");
		msg.addParamForward("", "");
		
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		
		return "toForward"; 
		
	}
	
	
}
