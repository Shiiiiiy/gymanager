package com.uws.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.dao.Page;
import com.base.model.BaseModel2;
import com.base.model.Message;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.model.Dic;
import com.uws.model.FileInfo;
import com.uws.model.IndustryGuild;
import com.uws.model.IntegrityAgency;
import com.uws.model.NewsAll;
import com.uws.service.IFileInfoService;
import com.uws.service.IIntegrityAgencyService;
import com.uws.service.INewsAllManageService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;
import com.uws.util.Util;

/**
 * 对接服务-新闻管理模块控制器
 * @author zhouchang
 *
 */
@Controller
public class AbutmentManageController extends BaseController {

	@Resource
	private ISysDicService sysDicService;
	@Autowired
	private INewsAllManageService newsAllManageService;
	@Resource
	private IFileInfoService fileService;
	
	@Autowired(required=true)
	private IIntegrityAgencyService integrityAgencyService;
	
	/**
	 * 进入 对接服务->新闻管理
	 * @param model
	 * @param news
	 * @param file
	 * @param tab
	 * @param moduleCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/abutment/query/index")
	public String abutmentnewsIndex(Model model,NewsAll news,FileInfo file,String tab,
			String moduleCode,String pageNo,String pageSize){
		model.addAttribute("news", news);
		model.addAttribute("file", file);
		model.addAttribute("tab", tab);
		model.addAttribute("moduleCode", moduleCode);
		model.addAttribute("pageNo", pageNo == null ? 1 : pageNo);
		model.addAttribute("pageSize", pageSize == null ? 10 : pageSize);
		return "abutment/news/index";
	}
	
	/**
	 * 选择 线下培训标签
	 * @param model
	 * @param news
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/abutmentnews/query/list")
	public String queryNewsList(Model model, NewsAll news,HttpServletRequest request){
		Map<String,Object> param = this.initParamMap(request);
		news.setModuleCode("NEWS_P");
		Page page = newsAllManageService.queryNewsAllListByModule(param, news);
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		model.addAttribute("news", news);
		model.addAttribute(Constants.NEWS_ALL_MODULE, "NEWS_P");
		model.addAttribute("page", page);
		model.addAttribute("enable",enable);
		model.addAttribute("disable", disable);
		model.addAttribute("pageSearchType", "placeholder");//占位符, 无实义
		return "abutment/news/news";
	}
	
	/**
	 * 线下培训 标签中点击 启用和禁用
	 * @param model
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @param value
	 * @return
	 */
	@RequestMapping(value="/abutmentnews/opt/changestatus")
	public String alterStatus(ModelMap model,String id,String pageNo,
			String pageSize,String value){
		this.newsAllManageService.changeStatus(id,value);
		
		Message msg = new Message("/abutment/query/index");
		msg.setTips("操作成功");
    	msg.addParamForward("tab", "2");
    	msg.addParamForward(Constants.NEWS_ALL_MODULE, "NEW_P");
    	msg.addParamForward("pageNo", pageNo);
    	msg.addParamForward("pageSize", pageSize);
    	msg.addParamForward("pageSearchType", pageNo);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 * 进入修改或新增 线下培训页面
	 * @param model
	 * @param id
	 * @param moduleCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/abutmentnews/opt/edit")
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
		return "abutment/news/edit";
	}
	
	/**
	 * 进入 线下培训 条目的查看页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/abutmentnews/query/view")
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
        return "/abutment/news/view";
	}
	
	/**
	 * 线下培训 新增/修改 后的保存
	 * @param model
	 * @param news
	 * @param moduleCode
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/abutmentnews/opt/save")
	public String save(Model model,NewsAll news,String moduleCode,String pageNo,String pageSize){
		
        if(news != null && DataUtil.isNotNull(news.getId())){//修改
        	NewsAll newsAll = newsAllManageService.queryNewsAllByNewsId(news.getId());
        	newsAll.setNewsTitle(news.getNewsTitle());
        	newsAll.setNewsSource(news.getNewsSource());
        	newsAll.setIntroduce(news.getIntroduce());
        	newsAll.setNewsContent(news.getNewsContent());
        	newsAll.setStatus(news.getStatus());
        	newsAllManageService.updateNewsAll(newsAll);
        	
        	Message msg = new Message("/abutment/query/index");
        	msg.setTips("修改成功");
        	msg.addParamForward("tab", "2");
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
			news.setModuleCode("NEWS_P");
			newsAllManageService.saveNewsAll(news);
			
			Message msg = new Message("/abutment/query/index");
			msg.setTips("添加成功");
        	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
        	msg.addParamForward("tab", "2");
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	/**
	 * 线下培训 删除
	 * @param model
	 * @param ids
	 * @param moduleCode
	 * @return
	 */
	@RequestMapping(value="/abutmentnews/opt/delete")
	public String delete(Model model,String ids, String moduleCode){

        if(DataUtil.isNotNull(ids)){
        	newsAllManageService.deleteNewsAllsById(ids);
		}
        Message msg = new Message("/abutment/query/index");
		msg.setTips("删除成功");
    	msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
    	msg.addParamForward("tab", "2");
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 * 选择 线上培训标签
	 * @param model
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/abutmentfile/query/list")
	public String queryFileList(Model model,FileInfo file,HttpServletRequest request){
		Map<String,Object> param = this.initParamMap(request);
		Page page = fileService.pageQueryFiles(param,file,"MODULE_J", "MODULE_J_MAIN", "FILE_URL");
		model.addAttribute("page", page);
		model.addAttribute("file", file);
		model.addAttribute("pageSearchType", "placeholder");//占位符, 无实义
		return "abutment/news/files";
	}
	
	/**
	 * 进入线上培训新增或修改页面
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/abutmentfile/opt/edit")
	public String editFile(Model model,String id, String pageNo,String pageSize){
		if(!Util.isNull(id)){//修改
			FileInfo file = fileService.queryFileById(id);
        	model.addAttribute("file", file);
		}

		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		return "abutment/news/editfile";
	}
	
	/**
	 * 线上培训 修改新增的保存
	 * @param model
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/abutmentfile/opt/save",method=RequestMethod.POST)
	public String saveFile(Model model,FileInfo file,String pageNo,String pageSize){
		if(file != null && DataUtil.isNotNull(file.getId())){
			FileInfo dbFile = this.fileService.queryFileById(file.getId());
			dbFile.setFileTitle(file.getFileTitle());
			dbFile.setUrl(file.getUrl());
			dbFile.setFileTime(new Date());
			this.fileService.saveFileInfo(dbFile);
			
			Message msg = new Message("/abutment/query/index");
        	msg.setTips("修改成功");
        	msg.addParamForward("tab", "1");
        	//msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			file.setModuleCode("MODULE_J");
			file.setParentCode("MODULE_J_MAIN");
			file.setFileType("FILE_URL");
			file.setFileTime(new Date());
			this.fileService.saveFileInfo(file);
			
			Message msg = new Message("/abutment/query/index");
        	msg.setTips("添加成功");
        	msg.addParamForward("tab", "1");
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	/**
	 * 线上培训 查看
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/abutmentfile/query/view")
	public String fileView(Model model,String id){
		if(id != null && !"".equals(id)){
			FileInfo file = this.fileService.queryFileById(id);
			model.addAttribute("file", file);
		}
		return "abutment/news/viewfile";
	}
	
	/**
	 * 线上培训 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/abutmentfile/opt/delete")
	public String fileDelete(Model model,String ids){
		if(DataUtil.isNotNull(ids)){
        	this.fileService.batchDeleteFileByIdString(ids);
		}
        Message msg = new Message("/abutment/query/index");
		msg.setTips("删除成功");
    	//msg.addParamForward(Constants.NEWS_ALL_MODULE, moduleCode);
    	msg.addParamForward("tab", "1");
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	@RequestMapping(value="/abutmentnews/query/single")
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
		return "abutment/news/common";
	}
	

	
	
	
	
	
	
	
	
	
	
	/**
	 * ********************资源管理***********************
	 */
	
	/**
	 * 进入 对接服务 资源管理
	 * @param model
	 * @param flag
	 * @return
	 */
	@RequestMapping(value="/resouce/query/list")
	public String resourcesList(Model model, String flag,String downloadType,String researchType){
		if(DataUtil.isNull(flag)){
			flag = "1"; 
		}
		if(researchType == null || "".equals(researchType)){
			researchType = "FILE_E1";
		}
		//产业研究
		List<FileInfo> industryReserchList = fileService.queryFileInfoListForCompany(Constants.MODULE_J, "MODULE_J_MAIN",researchType);
		//诚信机构logo
		List<FileInfo> certificationbodys = fileService.queryFileInfoListForCompany(Constants.MODULE_J, "N","FILE_CHENGXIN");
		//招聘
		List<FileInfo> fileList = fileService.queryFileInfoListForCompany(Constants.MODULE_J, "N", "FILE_ZHONGJIE");
		
		if(downloadType == null || "".equals(downloadType)){
			for (String type : DefaultValue.DOWNLOAD_TYPE.keySet()) {
				downloadType = type;
				break;
			}
		}
		//下载区
		List<FileInfo> downloads = fileService.queryFileInfoListForCompany(Constants.MODULE_J, "MODULE_J_MAIN",downloadType);//"FILE_D","FILE_E","FILE_F","FILE_G","FILE_I"
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		
		for (String type : DefaultValue.DOWNLOAD_TYPE.keySet()) {
			Map<String,String> m = new HashMap<String, String>();
			m.put("ID", type);
			m.put("NAME", DefaultValue.DOWNLOAD_TYPE.get(type));
			mapList.add(m);
		}
		
		
		model.addAttribute("industryReserchList", industryReserchList);
		model.addAttribute("researchType", researchType);
		model.addAttribute("certificationbodys", certificationbodys);
		model.addAttribute("downloads", downloads);
		model.addAttribute("flag", flag);
		model.addAttribute("saveLocation", Constants.FILE_PATH);
		model.addAttribute("downloadType", downloadType);
		model.addAttribute("mapList", mapList);
		model.addAttribute("fileList", fileList);
		
	    return "abutment/resouce/list";
	}
	
	/**
	 * 资源上传后的信息保存
	 * @return
	 */
	@RequestMapping(value="/resouce/save/saveresouce")
	@ResponseBody
	public void saveResource(Model model,FileInfo file){
		if(file != null){
			file.setFileTime(new Date());
			file.setModuleCode(Constants.MODULE_J);
			file.setParentCode(("".equals(file.getParentCode()) || 
					"FILE_ZHONGJIE".equals(file.getFileType()))
					? null : file.getParentCode());
			file.setSort(1);
		}
		fileService.saveFileInfo(file);
	}
	
	/**
	 * 资源信息修改后保存
	 * @param fileTitle
	 * @param fileId
	 * @param url
	 * @param urlType
	 */
	@RequestMapping(value="/resouce/opt/updateresource")
	public @ResponseBody void updateResource(String fileTitle,String fileId,String url,String urlType,String sort){
		fileService.updateFileName(fileId, fileTitle, url, urlType,sort);
	}
	
	@RequestMapping(value="/resouce/opt/deletefile")
	public String deleteFile(Model model ,String fileId,String flag, String downloadType, String researchType){
		fileService.deleteFileById(fileId);
		return resourcesList(model,flag,downloadType,researchType);
	}
	
	
	/**
	 * *******************动态数据******************
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/dynamic/query/list")
	public String serviceAgencyList(Model model, String flag,HttpServletRequest request,String tab){
		HttpSession session = request.getSession();
		Map<String, Object> param1 = null;
		Map<String, Object> param2 = null;
		if(tab != null){
			if("1".equals(tab)){
				param1 = this.initParamMap(request);
				param2 = (Map<String, Object>) session.getAttribute("DYNAMIC_PARAM_B");
				session.setAttribute("DYNAMIC_PARAM_A", param1);
				flag = tab;
			}else{
				param2 = this.initParamMap(request);
				param1 = (Map<String, Object>) session.getAttribute("DYNAMIC_PARAM_A");
				session.setAttribute("DYNAMIC_PARAM_B", param2);
				flag = tab;
			}
		}else{
			param1 = this.initParamMap(request);
			param2 = this.initParamMap(request);
			session.setAttribute("DYNAMIC_PARAM_A", param1);
			session.setAttribute("DYNAMIC_PARAM_B", param2);
		}
		//Map<String, Object> param = this.initParamMap(request);
		Page integrityAgencyPage = newsAllManageService.getIntegrityAgencyList(param1,"INTEGRITY_AGENCY");//INDUSTRY_GUILD
		Page industryGuildPage = newsAllManageService.getIntegrityAgencyList(param2,"INDUSTRY_GUILD");//INDUSTRY_GUILD
		List<Map> ll= industryGuildPage.getList();
		if(null!=ll){
		for (int i = 0; i < ll.size(); i++) {
			if(ll.get(i).get("START_TIME")!=null){
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        String str = ((String)ll.get(i).get("START_TIME")).substring(0,10);
		        ll.get(i).put("START_TIME", str);//替换日期
			}else{ll.get(i).put("START_TIME", "");};//替换日期}
		}}
		model.addAttribute("integrityAgencyPage", integrityAgencyPage);
		model.addAttribute("industryGuildPage", industryGuildPage);
		if(flag == null || flag.equals(""))
			flag = "1";
		model.addAttribute("flag", flag);
		return "abutment/dynamic/list";
	}
	
	/**
	 * 保存或修改
	 * @param model
	 * @param flag
	 * @param request
	 * @param ia
	 * @return
	 */
	@RequestMapping(value="/dynamic/opt/save")
	public String saveItem(Model model, String flag, HttpServletRequest request,IntegrityAgency ia){
		IntegrityAgency dbIa = null;
		if(ia != null && ia.getId() != null){
			dbIa = integrityAgencyService.getById(IntegrityAgency.class,ia.getId());
			dbIa.setAddress(ia.getAddress());
			dbIa.setLinkman(ia.getLinkman());
			dbIa.setName(ia.getName());
			dbIa.setService(ia.getService());
			dbIa.setTel(ia.getTel());
			dbIa.setWebsite(ia.getWebsite());
			ia = dbIa;
		}
		ia.setStatus("2");
		integrityAgencyService.save(ia);
		
		Message msg = new Message("/dynamic/query/list");
		msg.setTips("保存成功");
		msg.addParamForward("flag", flag);
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		return "toForward";
		//return serviceAgencyList(model, flag, request,null);
	}
	
	/**
	 * 查询单个 诚信服务机构/行业协会
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/dynamic/query/one")
	public @ResponseBody BaseModel2 getOne(String id,Integer tab){
		if(tab == 1){
			IntegrityAgency ia = integrityAgencyService.getById(IntegrityAgency.class,id);
			return ia;
		}else{
			IndustryGuild ig = integrityAgencyService.getById(IndustryGuild.class,id);
			if(ig.getStartTime()!=null){
				SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		        String str = sdf.format(ig.getStartTime());
				ig.setComments(str);//用Comments的值替换日期
			}
			return ig;
		}
	}
	
	/**
	 * 删除 诚信服务机构
	 * @param model
	 * @param flag
	 * @param request
	 * @param ia
	 * @return
	 */
	@RequestMapping(value="/dynamic/opt/deleteia")
	public String deleteIA(Model model, HttpServletRequest request,String ids){
		//integrityAgencyService.deleteIA();
		String[] aIds = null;
		if(ids != null){
			aIds = ids.split(",");
		}
		List<BaseModel2> iaList = new ArrayList<BaseModel2>();
		for (String id : aIds) {
			IntegrityAgency ia = new IntegrityAgency();
			ia.setId(id);
			iaList.add(ia);
		}
		integrityAgencyService.deleteIA(iaList);
		
		Message msg = new Message("/dynamic/query/list");
		msg.setTips("删除成功");
		msg.addParamForward("flag", "1");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		return "toForward";
	//	return serviceAgencyList(model, "1", request,null);
	}
	
	/**
	 * 行业协会 保存/修改
	 * @param model
	 * @param ig
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dynamic/opt/saveig")
	public String saveIG(Model model,IndustryGuild ig,HttpServletRequest request,String tstartTime){
		IndustryGuild dbig = null;
		if(ig != null && ig.getId() != null){
			dbig = integrityAgencyService.getById(IndustryGuild.class,ig.getId());
			dbig.setLinkman(ig.getLinkman());
			dbig.setName(ig.getName());
			dbig.setTel(ig.getTel());
			dbig.setBelong(ig.getBelong());
			dbig.setLevel(ig.getLevel());
			dbig.setPeopleNO(ig.getPeopleNO());
			ig = dbig;
		}
		//
		if(!Util.isNull(tstartTime)){
			SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
			try {
				ig.setStartTime(sdf.parse(tstartTime));
			} catch (ParseException e) {}
		}else{ig.setStartTime(null);}
		//
		ig.setStatus("2");
		integrityAgencyService.save(ig);
		
		Message msg = new Message("/dynamic/query/list");
		msg.setTips("保存成功");
		msg.addParamForward("flag", "2");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		return "toForward";
		
	//	return serviceAgencyList(model, "2", request,null);
	}
	
	@RequestMapping(value="/dynamic/opt/deleteig")
	public String deleteIG(Model model, HttpServletRequest request,String ids){
		String[] aIds = null;
		if(ids != null){
			aIds = ids.split(",");
		}
		List<BaseModel2> igList = new ArrayList<BaseModel2>();
		for (String id : aIds) {
			IndustryGuild ia = new IndustryGuild(id);
			igList.add(ia);
		}
		integrityAgencyService.deleteIA(igList);
		
		Message msg = new Message("/dynamic/query/list");
		msg.setTips("删除成功");
		msg.addParamForward("flag", "2");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		return "toForward";
		
	//	return serviceAgencyList(model, "2", request,null);
	}
}
