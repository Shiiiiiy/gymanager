package com.uws.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
import com.uws.model.NewsAll;
import com.uws.model.Product;
import com.uws.model.SysUser;
import com.uws.service.IDemoService;
import com.uws.service.IFileInfoService;
import com.uws.service.INewsAllManageService;
import com.uws.service.IServiceService;
import com.uws.service.ISysDicService;
import com.uws.service.ISysRolePermissionService;
import com.uws.util.Constants;
import com.uws.util.Util;
/**
 * @author: wangjun
 * 产品服务 控制层
 */
@Controller
@RequestMapping("/service")
public class ServiceController extends BaseController {
	/**产品服务的服务层*/
	@Resource
	private IServiceService serviceService;
	/**系统字典service*/
	@Resource
	private ISysDicService sysDicService;
	/**新闻管理service*/
	@Resource 
	private INewsAllManageService newsAllManageService;
	/**文件管理service*/
	@Resource
	private IFileInfoService fileService;
	/**角色权限service*/
	@Resource
	private ISysRolePermissionService sysRolePermissionService;
	/***------------------------------------------------------------------------------------------------------------**/
	/**
	 * 进入 产品服务--企业列表  list页面 menu1
	 */
	@RequestMapping("/cplist")
	public String companyList(HttpServletRequest request,Company company,Product product,Model model,HttpSession session
			,String mCode,String parentCode){
		Map<String, Object> param = this.initParamMap(request);
		SysUser user = (SysUser)session.getAttribute("LoginUser");
		//是否保存查询条件
		String flag=request.getParameter("flag");
		if("1".equals(flag)){
			company=(Company)session.getAttribute("tcompany");
			product=(Product)session.getAttribute("tproduct");
			param=(Map<String, Object>)session.getAttribute("tparam");
			mCode=(String) session.getAttribute("tmCode");
			parentCode=(String) session.getAttribute("tparentCode");
		}
		//
		//初始化 一级菜单
		List<Map> m1=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_A);
		if(m1!=null&&m1.size()>0){
			model.addAttribute("type1", Constants.INDUSTRY_A);//
			if("INDUSTRY_A".equals(mCode)){model.addAttribute("toActive", "INDUSTRY_A");}
		}
		List<Map> m2=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_B);
		if(m2!=null&&m2.size()>0){
			model.addAttribute("type2", Constants.INDUSTRY_B);//
			if("INDUSTRY_B".equals(mCode)){model.addAttribute("toActive", "INDUSTRY_B");}
		}
		//初始化二级菜单
		if(!DataUtil.isNull(mCode)){
			List<Map> ServiceList=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", mCode);
			model.addAttribute("mapList", ServiceList);
			model.addAttribute("toActive2", parentCode);
		}
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		Page page=serviceService.queryCompanyListByModule(param, company,product,mCode,parentCode,user.getId()+"");
		//保存查询条件
		session.setAttribute("tcompany", company);
		session.setAttribute("tproduct", product);
		session.setAttribute("tparam", param);
		session.setAttribute("tmCode", mCode);
		session.setAttribute("tparentCode", parentCode);
		//
		model.addAttribute("company", company);
		model.addAttribute("product", product);
		model.addAttribute("page", page);
		//选择类别组间初始化
		model.addAttribute("ClassA", m1);//工业产品
		model.addAttribute("ClassB", m2);//生产服务
	    return "/service/cplist/list";  
	}
	/**
	 *批量取消  企业列表的关系
	 *@param propIds 企业ids
	 * */
	@RequestMapping("/cancelCompany" )
	public String cancelCompany(ModelMap model,HttpServletRequest request){
		String propIds=request.getParameter("propIds");
        if(DataUtil.isNotNull(propIds)){
        	serviceService.cancelCompany(propIds);
		}
		Message msg=new Message("/service/cplist.do");
		msg.setTips("操作成功");
		msg.addParamForward("flag", "1");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	/**
	 *批量 推荐/批量 不推荐
	 *@param propIds 企业ids
	 *@param propType 准备更新的状态  推荐/批量
	 * */
	@RequestMapping("/recommendCompany" )
	public String recommendCompany(ModelMap model,HttpServletRequest request){
		String propIds=request.getParameter("propIds");
		String propType=request.getParameter("propType");
        if(DataUtil.isNotNull(propIds)){
        	serviceService.setOrCancelRecommendCompany(propIds, propType);
		}
		Message msg=new Message("/service/cplist.do");
		msg.setTips("操作成功");
		msg.addParamForward("flag", "1");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	/**
	 *批量 添加企业分类属性
	 *@param propIds 企业ids
	 *@param propType 企业分类属性ids
	 * */
	@RequestMapping("/addClassCompany" )
	public String addClassCompany(ModelMap model,HttpServletRequest request){
		String propIds=request.getParameter("propIds");
		String propType=request.getParameter("propType");
        if(DataUtil.isNotNull(propIds)){
        	serviceService.addClassCompany(propIds, propType);
		}
		Message msg=new Message("/service/cplist.do");
		msg.setTips("操作成功");
		msg.addParamForward("flag", "1");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	/**
	 *从企业库  向产品服务 批量 添加企业
	 *@param  companyIds 企业ids
	 * */
	@RequestMapping("/saveCompany" )
	public String saveCompany(ModelMap model,HttpServletRequest request){
		String companyIds=request.getParameter("companyIds");
        if(DataUtil.isNotNull(companyIds)){
        	serviceService.saveServiceCompany(companyIds);
		}
		Message msg=new Message("/service/cplist.do");
		msg.setTips("操作成功");
		//msg.addParamForward("flag", "1");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	/***------------------------------------------------------------------------------------------------------------**/
	/**
	 * 进入 产品服务--新闻管理  list页面 menu2
	 * @param news
	 * @param model
	 * @return
	 */
	@RequestMapping("/news")
	public String news(HttpServletRequest request,NewsAll news,ModelMap model,HttpSession session,String mCode,String parentCode){
		Map<String, Object> param = this.initParamMap(request);
		news.setModuleCode(Constants.MODULE_CODE.NEWS_B.toString());//产品服务新闻 NEWS_B
		SysUser user = (SysUser)session.getAttribute("LoginUser");
		String useSession=request.getParameter("useSession");
		if(!Util.isNull(useSession)){//按照session查询条件 进行查询
			news =(NewsAll) session.getAttribute("serviceSearchNews");
			mCode =(String) session.getAttribute("serviceSearchMCode");
			parentCode =(String) session.getAttribute("serviceSearchParentCode");
			int pageNo = (Integer) session.getAttribute("serviceSearchPageNo");
			int pageSize =(Integer) session.getAttribute("serviceSearchPageSize");
			param.put("pageNo", pageNo);
			param.put("pageSize", pageSize);
		}
		
		Page page=this.serviceService.queryProductServiceNews(param, news,mCode,parentCode,user.getId()+"");
		model.addAttribute("page", page);
		//初始化 一级菜单
		List<Map> m0=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_M);
		if(m0!=null&&m0.size()>0){
			model.addAttribute("type0", "N");
			if("N".equals(mCode)){model.addAttribute("toActive", "N");}
		}
		List<Map> m1=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_A);
		if(m1!=null&&m1.size()>0){
			model.addAttribute("type1", Constants.INDUSTRY_A);
			if("INDUSTRY_A".equals(mCode)){model.addAttribute("toActive", "INDUSTRY_A");}
		}
		List<Map> m2=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_B);
		if(m2!=null&&m2.size()>0){
			model.addAttribute("type2", Constants.INDUSTRY_B);
			if("INDUSTRY_B".equals(mCode)){model.addAttribute("toActive", "INDUSTRY_B");}
		}
		//初始化二级菜单
		if("N".equals(parentCode) || ("".equals(parentCode)&&"N".equals(mCode))){
			model.addAttribute("toActive2", "N");
		}else if(!DataUtil.isNull(parentCode) || (DataUtil.isNull(parentCode)&&null!=mCode&&mCode.toUpperCase().indexOf("INDUSTRY_")!=-1) ){
			List<Map> ServiceList=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", mCode);
			model.addAttribute("mapList", ServiceList);
			model.addAttribute("toActive2", parentCode);
		}
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		model.addAttribute("enable",enable);
		//禁用状态字典
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		model.addAttribute("disable", disable);
		//返回查询条件
		model.addAttribute("news",news);
		//session中保存本次查询条件
		session.setAttribute("serviceSearchNews",news);
		session.setAttribute("serviceSearchPageNo",param.get("pageNo"));
		session.setAttribute("serviceSearchPageSize",param.get("pageSize"));
		session.setAttribute("serviceSearchMCode",mCode);
		session.setAttribute("serviceSearchParentCode",parentCode);
		return "/service/news/newslist";  
	}
	/**
	 * 删除 一个 或多个新闻
	 * @param ids 新闻ids
	 * @return
	 */
	@RequestMapping("/delnews")
	public String delGardenNews(String ids,ModelMap model){
		this.serviceService.deleteNews(ids);
		Message msg=new Message("/service/news.do");
		msg.setTips("删除成功");
		msg.addParamForward("useless", "useless");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	/**
	 * 进入新增或修改新闻页面
	 * @id 新闻id
	 */
	@RequestMapping(value = {"/opt-add/addnews","/opt-edit/editnews"})
	public String addOrEditNewsAll(ModelMap model,HttpSession session,String id){
		String mCode="",parentCode="";
		if(!Util.isNull(id)){//是修改
			NewsAll gardenNews = newsAllManageService.queryNewsAllByNewsId(id);
			model.addAttribute("gardenNews",gardenNews);
			parentCode=gardenNews.getParentCode();
			if("".equals(parentCode) || null==parentCode){mCode="N";}
			else{
				mCode=parentCode.toUpperCase().indexOf("_A")!=-1?Constants.INDUSTRY_A:mCode;
				mCode=parentCode.toUpperCase().indexOf("_B")!=-1?Constants.INDUSTRY_B:mCode;
			}
			//修改记录查询条件
			
			
		}
		SysUser user = (SysUser)session.getAttribute("LoginUser");
		//初始化 一级菜单
		List<Map> m0=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_M);
		if(m0!=null&&m0.size()>0){
			model.addAttribute("type0", "N");
			if("N".equals(mCode)){model.addAttribute("toActive", "N");}
		}
		List<Map> m1=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_A);
		if(m1!=null&&m1.size()>0){
			model.addAttribute("type1", Constants.INDUSTRY_A);
			if("INDUSTRY_A".equals(mCode)){model.addAttribute("toActive", "INDUSTRY_A");}
		}
		List<Map> m2=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_B);
		if(m2!=null&&m2.size()>0){
			model.addAttribute("type2", Constants.INDUSTRY_B);
			if("INDUSTRY_B".equals(mCode)){model.addAttribute("toActive", "INDUSTRY_B");}
		}
		//初始化二级菜单
		if("N".equals(parentCode) || "N".equals(mCode)){
			model.addAttribute("toActive2", "N");
		}else if(!DataUtil.isNull(parentCode) || (DataUtil.isNull(parentCode)&&null!=mCode&&mCode.toUpperCase().indexOf("INDUSTRY_")!=-1)){
			List<Map> ServiceList=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", mCode);
			model.addAttribute("mapList", ServiceList);
			model.addAttribute("toActive2", parentCode);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);		
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
	    return "/service/news/addOrEditNewsAll";  
	}
	
	/**
	 * 保存或更新  新闻
	 * @param news
	 * @return
	 */
	@RequestMapping("/opt-save/savenews")
	public String saveGardenNews(NewsAll news,ModelMap model){
		news.setModuleCode(Constants.MODULE_CODE.NEWS_B.toString());
		this.serviceService.saveOrUpdateNews(news);
		Message msg=new Message("/service/news.do");
		msg.setTips("保存成功");
		if(!Util.isNull(news.getId())){
			msg.addParamForward("useSession", "1");}
		else{
			msg.addParamForward("uselessmsg", "1");
		}
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	/**
	 * 修改  新闻状态
	 * @param news
	 * @return
	 */
	@RequestMapping("/opt-update/changestatus")
	public String changeNewsStatus(String id,String pageNo,String pageSize,String value,ModelMap model){
		this.newsAllManageService.changeStatus(id,value);
		Message msg=new Message("/service/news.do");
		msg.setTips("操作成功");
		msg.addParamForward("useSession", "1");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	/**
	 * 查看  新闻
	 * @param news
	 * @return
	 */
	@RequestMapping("/opt-view/newsinfo")
	public String viewNews(ModelMap model,String id){
		model.addAttribute("type1", Constants.INDUSTRY_A);
		model.addAttribute("type2", Constants.INDUSTRY_B);
		NewsAll gardenNews = newsAllManageService.queryNewsAllByNewsId(id);
    	model.addAttribute("gardenNews",gardenNews);
		String parentCode=gardenNews.getParentCode();
		if(null==parentCode || "".equals(parentCode)){
			model.addAttribute("tmCode","主页新闻");
			model.addAttribute("tparentCode","主页新闻");
		}else{
			String pcode="";
			pcode=parentCode.toUpperCase().indexOf("_A")!=-1?"工业产品":pcode;
			pcode=parentCode.toUpperCase().indexOf("_B")!=-1?"生产服务":pcode;
			model.addAttribute("tmCode",pcode);
			model.addAttribute("tparentCode",this.serviceService.getIndustryNameByID(parentCode));
		}	
		if((long)gardenNews.getStatus()==sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_ENABLE).getId()){
			model.addAttribute("tDic", "启用");
		}else{model.addAttribute("tDic", "禁用");}
    	//List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		//model.addAttribute("statusList", statusList);
		return "/service/news/newsview";  
	}
	/***------------------------------------------------------------------------------------------------------------**/
	/**
	 *进入 产品服务--资源管理  list页面 menu3
	 *@param parentCode 二级分类
	 */
	@RequestMapping("/opt-query/resource")
	public String queryResource(HttpServletRequest request,HttpServletResponse response,ModelMap model,HttpSession session){
		//分类信息
		String flag=(String) request.getParameter("flag");
		if(DataUtil.isNull(flag)){flag = "1";}
		//
		String mCode=request.getParameter("mCode");
		String parentCode=request.getParameter("parentCode");
		SysUser user = (SysUser)session.getAttribute("LoginUser");
		//初始化 一级菜单
		List<Map> m0=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_M);
		if(m0!=null&&m0.size()>0){
			model.addAttribute("type0", "N");
			if("N".equals(mCode)){model.addAttribute("toActive", "N");}
		}
		List<Map> m1=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_A);
		if(m1!=null&&m1.size()>0){
			model.addAttribute("type1", Constants.INDUSTRY_A);
			if("INDUSTRY_A".equals(mCode)){model.addAttribute("toActive", "INDUSTRY_A");}
		}
		List<Map> m2=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", Constants.ROLE_PER_INDUSTRY_B);
		if(m2!=null&&m2.size()>0){
			model.addAttribute("type2", Constants.INDUSTRY_B);
			if("INDUSTRY_B".equals(mCode)){model.addAttribute("toActive", "INDUSTRY_B");}
		}
		//初始化二级菜单
		if("N".equals(parentCode) || ("".equals(parentCode)&&"N".equals(mCode))){
			model.addAttribute("toActive2", "N");
		}else if(!DataUtil.isNull(parentCode)){
			List<Map> ServiceList=this.sysRolePermissionService.queryPerMapListByUser(user.getId()+"", mCode);
			model.addAttribute("mapList", ServiceList);
			model.addAttribute("toActive2", parentCode);
		}
		if(!(DataUtil.isNull(mCode)&&DataUtil.isNull(parentCode))){
			List<Map> fileMap = serviceService.queryFileInfoList(Constants.MODULE_D,parentCode);
			model.addAttribute("fileList", fileMap);
		}
		model.addAttribute("flag", flag);
		model.addAttribute("slider", Constants.FILE_SLIDER);
		model.addAttribute("compa", Constants.COMP_A);
		model.addAttribute("statistics", Constants.FILE_STATISTICS);

		
		return "/file/f2sourceList";
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
	public String saveResourceFile(ModelMap model,String flag,String parentCode,String mCode,String fileType,String fileName){	
		FileInfo file = new FileInfo();
		file.setFileName(fileName);
		file.setFileTime(new Date());
		file.setModuleCode(Constants.MODULE_D);
		String parentCodeSave=parentCode;
		if("".equals(parentCode) || "N".equals(parentCode) ||null==parentCode){parentCodeSave=null;}
		file.setParentCode(parentCodeSave);
		file.setFileType(fileType);
		file.setSort(1);
		fileService.saveFileInfo(file);
		return "redirect:/service/opt-query/resource.do?&parentCode="+parentCode+"&mCode="+mCode+"&flag="+flag; 
	}
	
	/**
	 * 产品服务  资源管理  删除图片 ok
	 */
	@RequestMapping("/opt-delete/deleteFile")
	public String deleteFile(HttpServletRequest request,String fileId,String parentCode,String mCode,String flag,Model model){
		fileService.deleteFileById(fileId);
		Message msg=new Message("/service/opt-query/resource.do");
		msg.setTips("删除成功");
		msg.addParamForward("parentCode", parentCode);
		msg.addParamForward("mCode", mCode);
		msg.addParamForward("flag", flag);
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
		//return "redirect:/service/opt-query/resource.do?&parentCode="+parentCode+"&mCode="+mCode+"&flag="+flag;   
	}
	
	
	/**
	 * 产品服务  资源管理  编辑图片 ok
	 * @throws Exception 
	 */
	@RequestMapping("/opt-update/updateFileTitle")
	public String updateFileTitle(HttpServletRequest request,String flag,String sort,String mCode,String parentCode,String fileTitle,String fileId,String url,String urlType,Model model) throws Exception{
		fileTitle = java.net.URLDecoder.decode(fileTitle,"UTF-8");
		url = java.net.URLDecoder.decode(url,"UTF-8");
		fileService.updateFileName(fileId, fileTitle, url, urlType,sort);
	    return "redirect:/service/opt-query/resource.do?&parentCode="+parentCode+"&mCode="+mCode+"&flag="+flag; 
	}
	/**----------------------------------------------------------------------*/
	/**
	 *通过分类[生成服务/工业产品] 返回之类list //获取权限下的所有二级菜单
	 */
	@RequestMapping("/getServiceList")
	@ResponseBody
	public void getServiceList(HttpServletRequest request,HttpServletResponse response,String type,HttpSession session){
		SysUser user = (SysUser)session.getAttribute("LoginUser");
		String uid=user.getId()+"";
		List<Map> ServiceList=this.sysRolePermissionService.queryPerMapListByUser(uid, type);
		ObjectMapper mapper= new ObjectMapper();
		response.setCharacterEncoding("UTF-8");
		try {
			 response.getWriter().write(mapper.writeValueAsString(ServiceList));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
