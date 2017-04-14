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
import com.uws.model.FinanceEnvironment;
import com.uws.model.InnovBusiness;
import com.uws.model.InnovatePlatForm;
import com.uws.model.InvestCompanies;
import com.uws.model.MarketCompanies;
import com.uws.model.NewCompanies;
import com.uws.model.TechAchiev;
import com.uws.model.TechInnovation;
import com.uws.service.IDynamicDataService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;

/**
 * 技术创新-动态数据Controller
 * @author 张学彪
 */
@Controller
public class DynamicDataController extends BaseController{

	@Autowired
	private IDynamicDataService dynamicDataService;
	
	@Resource
	private ISysDicService sysDicService;
	
	/**
	 * 主页面
	 */
	@RequestMapping("/dynamicdata/opt-query/dynamicData")
	public String dynamicData(HttpServletRequest reques,Model model,HttpSession session){
		
	    return "dynamicdata/dynamicData";  
	}
	
	/**
	 * 查询技术创新平台列表
	 */
	@RequestMapping("/dynamicdata/opt-query/techInnovationList")
	public String queryTechInnovationList(HttpServletRequest request,TechInnovation techInnovation,Model model,HttpSession session){
		Map<String, Object> param = this.initParamMap(request);
		
		//手动设置每页条数方便页面显示
		//param.put("pageSize",5);
		
		//是否保存查询条件
		String flag=request.getParameter("flag");
		
		if("1".equals(flag)){
			techInnovation=(TechInnovation)session.getAttribute("techi");
		}
		
		Page page=dynamicDataService.queryTechInnovationList(param, techInnovation);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		//禁用状态字典
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		
		//保存查询条件
		session.setAttribute("techi", techInnovation);
		model.addAttribute("page", page);
		model.addAttribute("enable",enable);
		model.addAttribute("disable", disable);
		
	    return "dynamicdata/techInnovationList";  
	}
	
	
	/**
	 * 进入新增或修改页面
	 */
	@RequestMapping("/dynamicdata/opt-addOrEdit/addOrEditTechInnovation")
	public String addOrEditTechInnovation(HttpServletRequest request,String pageNo,String pageSize,Model model,HttpSession session){
		String techInnovationId=request.getParameter("techInnovationId");
		
		if(DataUtil.isNotNull(techInnovationId)){//修改
			TechInnovation techInnovation = dynamicDataService.queryTechInnovationById(techInnovationId);
        	model.addAttribute("techInnovation",techInnovation);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		
	    return "dynamicdata/addOrEditTechInnovation";  
	}
	
	
	/**
	 *保存（新增、修改）
	 * */
	@RequestMapping("/dynamicdata/opt-save/saveTechInnovation" )
	public String saveTechInnovation(ModelMap model,TechInnovation techInnovation,String pageNo,String pageSize, HttpServletRequest request){
		
        if(techInnovation != null && DataUtil.isNotNull(techInnovation.getId())){//修改
        	TechInnovation techInno = dynamicDataService.queryTechInnovationById(techInnovation.getId());
        	techInno.setName(techInnovation.getName());
        	techInno.setBelong(techInnovation.getBelong());
        	techInno.setLevel(techInnovation.getLevel());
        	techInno.setScale(techInnovation.getScale());
        	techInno.setStartTime(techInnovation.getStartTime());
        	techInno.setStatus(techInnovation.getStatus());
        	dynamicDataService.updateTechInnovation(techInno);
        	
        	//return new StringBuffer("redirect:/dynamicdata/opt-query/techInnovationList.do?flag=1").toString();
        	Message msg = new Message("/dynamicdata/opt-query/techInnovationList.do");
        	msg.setTips("修改成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			dynamicDataService.saveTechInnovation(techInnovation);
			//return new StringBuffer("redirect:/dynamicdata/opt-query/techInnovationList.do?flag=1").toString();
			Message msg = new Message("/dynamicdata/opt-query/techInnovationList.do");
        	msg.setTips("添加成功");
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
	@RequestMapping(value={"/dynamicdata/opt-update/changeStatus"})
	public String changeStatus(ModelMap model,String techid,String tableName,String type,String value,String moduleCode,HttpServletRequest request){
		String pno = request.getParameter("pageNo");
		String psize = request.getParameter("pageSize");
		dynamicDataService.changeStatus(techid, value, tableName);
		if(DataUtil.isNotNull(type)){
			//return new StringBuffer("redirect:/dynamicdata/opt-query/techAchievList.do?flag=1&type=").append(type).toString();
			Message msg = new Message("/dynamicdata/opt-query/techAchievList.do");
        	msg.setTips("操作成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("type", type);
        	msg.addParamForward("pageNo", pno);
        	msg.addParamForward("pageSize", psize);
        	msg.addParamForward("pageSearchType", pno);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			//return new StringBuffer("redirect:/dynamicdata/opt-query/techInnovationList.do?flag=1").toString();
			Message msg = new Message("/dynamicdata/opt-query/techInnovationList.do");
        	msg.setTips("操作成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("pageNo", pno);
        	msg.addParamForward("pageSize", psize);
        	msg.addParamForward("pageSearchType", pno);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	/**
	 *查看
	 * */
	@RequestMapping("/dynamicdata/opt-query/techInnovationInfo" )
	public String techInnovationInfo(ModelMap model,HttpServletRequest request){
		//id
		String techInnovationId=request.getParameter("id");
		
		TechInnovation techInnovation = new TechInnovation();
		
        if(DataUtil.isNotNull(techInnovationId)){
        	techInnovation = dynamicDataService.queryTechInnovationById(techInnovationId);
		}
        
        model.addAttribute("vo", techInnovation);
        
        //启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  		model.addAttribute("enable", enable);
        
        return "/dynamicdata/techInnovationInfo";
	}
	
	
	/**
	 *删除
	 * */
	@RequestMapping("/dynamicdata/opt-del/deleteTechInnovation" )
	public String deleteTechInnovation(ModelMap model,HttpServletRequest request){
		String techInnovationId=request.getParameter("techInnovationId");
		
        if(DataUtil.isNotNull(techInnovationId)){
        	dynamicDataService.deleteTechInnovationById(techInnovationId);
		}
        
        //return new StringBuffer("redirect:/dynamicdata/opt-query/techInnovationList.do?flag=1").toString();
        Message msg = new Message("/dynamicdata/opt-query/techInnovationList.do");
    	msg.setTips("删除成功");
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 *批量删除
	 * */
	@RequestMapping("/dynamicdata/opt-del/pdeleteTechInnovation" )
	public String pdeleteTechInnovation(ModelMap model,HttpServletRequest request){
		//ids
		String techInnovationIds=request.getParameter("techInnovationIds");
		
        if(DataUtil.isNotNull(techInnovationIds)){
        	dynamicDataService.deleteTechInnovationsByIds(techInnovationIds);
		}
        
        //return new StringBuffer("redirect:/dynamicdata/opt-query/techInnovationList.do?flag=1").toString();
        Message msg = new Message("/dynamicdata/opt-query/techInnovationList.do");
    	msg.setTips("批量删除成功");
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	
	
	
	/**
	 * 查询技术创新平台列表
	 */
	@RequestMapping("/dynamicdata/opt-query/techAchievList")
	public String queryTechAchievList(HttpServletRequest request,TechAchiev techAchiev,Model model,HttpSession session){
		Map<String, Object> param = this.initParamMap(request);
		
		//手动设置每页条数方便页面显示
		//param.put("pageSize",5);
		
		//是否保存查询条件
		String flag=request.getParameter("flag");
		
		if("1".equals(flag)){
			techAchiev=(TechAchiev)session.getAttribute("techa");
		}
		
		//TYPE_A=贵阳市专利申请明星企业,TYPE_B=贵阳市知识产权优势培育企业
		String type=request.getParameter("type");
		if(DataUtil.isNotNull(type)){
			techAchiev.setType(type);
		}
		
		Page page=dynamicDataService.queryTechAchievList(param, techAchiev);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		//禁用状态字典
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		
		//保存查询条件
		session.setAttribute("techa", techAchiev);
		model.addAttribute("page", page);
		model.addAttribute("enable",enable);
		model.addAttribute("disable", disable);
		model.addAttribute("type", type);
		
	    return "dynamicdata/techAchievList";  
	}
	
	/**
	 * 进入新增或修改页面
	 */
	@RequestMapping("/dynamicdata/opt-addOrEdit/addOrEditTechAchiev")
	public String addOrEditTechAchiev(HttpServletRequest request,String pageNo,String pageSize,Model model,HttpSession session){
		String techAchievId=request.getParameter("techAchievId");
		String type=request.getParameter("type");
		if(DataUtil.isNotNull(techAchievId)){//修改
			//TechAchiev techAchiev = dynamicDataService.queryTechAchievById(techAchievId);
			TechAchiev techAchiev = (TechAchiev)dynamicDataService.queryModelById(TechAchiev.class, techAchievId);
        	model.addAttribute("techAchiev",techAchiev);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("type", type);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
	    return "dynamicdata/addOrEditTechAchiev";  
	}
	
	
	/**
	 *保存（新增、修改）
	 * */
	@RequestMapping("/dynamicdata/opt-save/saveTechAchiev" )
	public String saveTechAchiev(ModelMap model,TechAchiev techAchiev, String pageNo,String pageSize,HttpServletRequest request){
		String type=request.getParameter("type");
        if(techAchiev != null && DataUtil.isNotNull(techAchiev.getId())){//修改
        	//TechAchiev ta = dynamicDataService.queryTechAchievById(techAchiev.getId());
        	TechAchiev ta = (TechAchiev)dynamicDataService.queryModelById(TechAchiev.class, techAchiev.getId());
        	ta.setName(techAchiev.getName());
        	ta.setBelong(techAchiev.getBelong());
        	ta.setLevel(techAchiev.getLevel());
        	ta.setCertTime(techAchiev.getCertTime());
        	ta.setEffectTime(techAchiev.getEffectTime());
        	ta.setStatus(techAchiev.getStatus());
        	//dynamicDataService.updateTechAchiev(ta);
        	dynamicDataService.updateModel(ta);
        	
        	//return new StringBuffer("redirect:/dynamicdata/opt-query/techAchievList.do?flag=1&type=").append(type).toString();
        	Message msg = new Message("/dynamicdata/opt-query/techAchievList.do");
        	msg.setTips("修改成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("type", type);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			//dynamicDataService.saveTechAchiev(techAchiev);
			dynamicDataService.saveModel(techAchiev);
			//return new StringBuffer("redirect:/dynamicdata/opt-query/techAchievList.do?flag=1&type=").append(type).toString();
			Message msg = new Message("/dynamicdata/opt-query/techAchievList.do");
        	msg.setTips("添加成功");
        	msg.addParamForward("type", type);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	/**
	 *查看
	 * */
	@RequestMapping("/dynamicdata/opt-query/techAchievInfo" )
	public String techAchievInfo(ModelMap model,HttpServletRequest request){
		//id
		String techAchievId=request.getParameter("id");
		
		TechAchiev techAchiev = new TechAchiev();
		
        if(DataUtil.isNotNull(techAchievId)){
        	techAchiev = (TechAchiev)dynamicDataService.queryModelById(TechAchiev.class, techAchievId);
		}
        
        model.addAttribute("vo", techAchiev);
        //启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  		model.addAttribute("enable", enable);
        
        return "/dynamicdata/techAchievInfo";
	}
	
	
	/**
	 *删除
	 * */
	@RequestMapping("/dynamicdata/opt-del/deleteTechAchiev" )
	public String deleteTechAchiev(ModelMap model,HttpServletRequest request){
		String techAchievId=request.getParameter("techAchievId");
		String type=request.getParameter("type");
        if(DataUtil.isNotNull(techAchievId)){
        	//dynamicDataService.deleteTechAchievById(techAchievId);
        	dynamicDataService.deleteModelById(techAchievId, "TECH_ACHIEV");
		}
        
        //return new StringBuffer("redirect:/dynamicdata/opt-query/techAchievList.do?flag=1&type=").append(type).toString();
        Message msg = new Message("/dynamicdata/opt-query/techAchievList.do");
    	msg.setTips("删除成功");
    	msg.addParamForward("type", type);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 *批量删除
	 * */
	@RequestMapping("/dynamicdata/opt-del/pdeleteTechAchiev" )
	public String pdeleteTechAchiev(ModelMap model,HttpServletRequest request){
		//ids
		String techAchievIds=request.getParameter("techAchievIds");
		String type=request.getParameter("type");
		
        if(DataUtil.isNotNull(techAchievIds)){
        	//dynamicDataService.deleteTechAchievsByIds(techAchievIds);
        	dynamicDataService.deleteModelByIds(techAchievIds, "TECH_ACHIEV");
		}
        
        //return new StringBuffer("redirect:/dynamicdata/opt-query/techAchievList.do?flag=1&type=").append(type).toString();
        Message msg = new Message("/dynamicdata/opt-query/techAchievList.do");
    	msg.setTips("批量删除成功");
    	msg.addParamForward("type", type);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 * 通用启用禁用方法
	 */
	@RequestMapping(value={"/dynamicdata/opt-update/changeModelStatus"})
	public String changeModelStatus(ModelMap model,String techid,String tableName,String value,HttpServletRequest request){
		
		dynamicDataService.changeStatus(techid, value, tableName);
		
		String pno = request.getParameter("pageNo");
		String psize = request.getParameter("pageSize");
		
		Message msg = new Message("/dynamicdata/opt-query/modelList.do");
    	msg.setTips("操作成功");
    	msg.addParamForward("flag", "1");
    	msg.addParamForward("modelType", tableName);
    	msg.addParamForward("pageNo", pno);
    	msg.addParamForward("pageSize", psize);
    	msg.addParamForward("pageSearchType", pno);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 * 创新创业主页面
	 */
	@RequestMapping("/dynamicdata/opt-query/dynamicData1")
	public String dynamicData1(HttpServletRequest reques,Model model,HttpSession session){
		
	    return "dynamicdata/dynamicData1";  
	}
	
 
	/**
	 * 创新创业动态数据列表
	 */
	@RequestMapping("/dynamicdata/opt-query/modelList")
	public String innovatePlatFormList(HttpServletRequest request,InnovatePlatForm innovatePlatForm,Model model){
		Map<String, Object> param = this.initParamMap(request);
		
		//手动设置每页条数方便页面显示
		//param.put("pageSize",5);
		
		String modelType = request.getParameter("modelType");
		
		Page page=dynamicDataService.queryModelList(param, modelType);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		//禁用状态字典
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		
		//保存查询条件
		model.addAttribute("page", page);
		model.addAttribute("enable",enable);
		model.addAttribute("disable", disable);
		model.addAttribute("modelType", modelType);
		
		String ftlUrl = "";
		if(modelType.equals("FINANCE_ENVIRONMENT")){
			ftlUrl = "dynamicdata/financeEnvironmentList";
		}else if(modelType.equals("INNOVATE_PLATFORM")){
			ftlUrl = "dynamicdata/innovatePlatformList";
		}else if(modelType.equals("INNOV_BUSINESS")){
			ftlUrl = "dynamicdata/innovBusinessList";
		}else if(modelType.equals("INVEST_COMPANIES")){
			ftlUrl = "dynamicdata/investCompaniesList";
		}else if(modelType.equals("MARKET_COMPANIES")){
			ftlUrl = "dynamicdata/marketCompaniesList";
		}else if(modelType.equals("NEW_COMPANIES")){
			ftlUrl = "dynamicdata/newCompaniesList";
		}
		
	    return ftlUrl;  
	}
	
	/**
	 *删除
	 * */
	@RequestMapping("/dynamicdata/opt-del/deleteModel" )
	public String deleteModel(ModelMap model,HttpServletRequest request){
		String id=request.getParameter("modelId");
		String modelType=request.getParameter("modelType");
        if(DataUtil.isNotNull(id)){
        	dynamicDataService.deleteModelById(id, modelType);
		}
        //return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
        Message msg = new Message("/dynamicdata/opt-query/modelList.do");
    	msg.setTips("删除成功");
    	msg.addParamForward("modelType", modelType);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 *批量删除
	 * */
	@RequestMapping("/dynamicdata/opt-del/pdeleteModel" )
	public String pdeleteModel(ModelMap model,HttpServletRequest request){
		String ids=request.getParameter("modelIds");
		String modelType=request.getParameter("modelType");
		
        if(DataUtil.isNotNull(ids)){
        	dynamicDataService.deleteModelByIds(ids, modelType);
		}
        //return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
        Message msg = new Message("/dynamicdata/opt-query/modelList.do");
    	msg.setTips("批量删除成功");
    	msg.addParamForward("modelType", modelType);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 * 进入新增或修改页面（创新创业-动态数据-创新创业平台）
	 */
	@RequestMapping("/dynamicdata/opt-addOrEdit/addOrEditInnovatePlatform")
	public String addOrEditInnovatePlatform(HttpServletRequest request,String pageNo,String pageSize,Model model,HttpSession session){
		String id=request.getParameter("id");
		String modelType=request.getParameter("modelType");
		if(DataUtil.isNotNull(id)){//修改
			InnovatePlatForm vo = (InnovatePlatForm)dynamicDataService.queryModelById(InnovatePlatForm.class, id);
        	model.addAttribute("vo",vo);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("modelType", modelType);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
	    return "dynamicdata/addOrEditInnovatePlatForm";  
	}
	
	
	/**
	 *保存（新增、修改）（创新创业-动态数据-创新创业平台）
	 * */
	@RequestMapping("/dynamicdata/opt-save/saveInnovatePlatForm" )
	public String saveInnovatePlatForm(ModelMap model,InnovatePlatForm innovatePlatForm,String pageNo,String pageSize, HttpServletRequest request){
		String modelType=request.getParameter("modelType");
        if(innovatePlatForm != null && DataUtil.isNotNull(innovatePlatForm.getId())){//修改
        	InnovatePlatForm mod = (InnovatePlatForm)dynamicDataService.queryModelById(InnovatePlatForm.class, innovatePlatForm.getId());
        	mod.setPlatName(innovatePlatForm.getPlatName());
        	mod.setPlatType(innovatePlatForm.getPlatType());
        	mod.setInvestor(innovatePlatForm.getInvestor());
        	mod.setPlatLevel(innovatePlatForm.getPlatLevel());
        	mod.setPlatTime(innovatePlatForm.getPlatTime());
        	mod.setStatus(innovatePlatForm.getStatus());
        	dynamicDataService.updateModel(mod);
        	
        	//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
        	Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("修改成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("modelType", modelType);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			dynamicDataService.saveModel(innovatePlatForm);
			//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
			Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("添加成功");
        	msg.addParamForward("modelType", modelType);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	/**
	 *查看
	 * */
	@RequestMapping("/dynamicdata/opt-query/innovatePlatFormInfo" )
	public String innovatePlatFormInfo(ModelMap model,HttpServletRequest request){
		//id
		String id=request.getParameter("id");
		
		InnovatePlatForm mod = new InnovatePlatForm();
		
        if(DataUtil.isNotNull(id)){
        	mod = (InnovatePlatForm)dynamicDataService.queryModelById(InnovatePlatForm.class, id);
		}
        
        model.addAttribute("vo", mod);
        //启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  		model.addAttribute("enable",enable);
        
        return "/dynamicdata/innovatePlatformInfo";
	}
	
	/**
	 * 进入新增或修改页面（创新创业-动态数据-产业金融环境）
	 */
	@RequestMapping("/dynamicdata/opt-addOrEdit/addOrEditFinanceEnvironment")
	public String addOrEditFinanceEnvironment(HttpServletRequest request,String pageNo,String pageSize,Model model,HttpSession session){
		String id=request.getParameter("id");
		String modelType=request.getParameter("modelType");
		if(DataUtil.isNotNull(id)){//修改
			FinanceEnvironment vo = (FinanceEnvironment)dynamicDataService.queryModelById(FinanceEnvironment.class, id);
        	model.addAttribute("vo",vo);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("modelType", modelType);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
	    return "dynamicdata/addOrEditFinanceEnvironment";  
	}
	
	
	/**
	 *保存（新增、修改）（创新创业-创新创业平台）
	 * */
	@RequestMapping("/dynamicdata/opt-save/saveFinanceEnvironment" )
	public String saveFinanceEnvironment(ModelMap model,FinanceEnvironment financeEnvironment, String pageNo,String pageSize,HttpServletRequest request){
		String modelType=request.getParameter("modelType");
        if(financeEnvironment != null && DataUtil.isNotNull(financeEnvironment.getId())){//修改
        	FinanceEnvironment mod = (FinanceEnvironment)dynamicDataService.queryModelById(FinanceEnvironment.class, financeEnvironment.getId());
        	mod.setFinanceName(financeEnvironment.getFinanceName());
        	mod.setIsBranch(financeEnvironment.getIsBranch());
        	mod.setScale(financeEnvironment.getScale());
        	mod.setLinkman(financeEnvironment.getLinkman());
        	mod.setTel(financeEnvironment.getTel());
        	mod.setStatus(financeEnvironment.getStatus());
        	dynamicDataService.updateModel(mod);
        	
        	//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
        	Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("修改成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("modelType", modelType);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			dynamicDataService.saveModel(financeEnvironment);
			//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
			Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("添加成功");
        	msg.addParamForward("modelType", modelType);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	/**
	 *查看
	 * */
	@RequestMapping("/dynamicdata/opt-query/financeEnvironmentInfo" )
	public String financeEnvironmentInfo(ModelMap model,HttpServletRequest request){
		//id
		String id=request.getParameter("id");
		
		FinanceEnvironment mod = new FinanceEnvironment();
		
        if(DataUtil.isNotNull(id)){
        	mod = (FinanceEnvironment)dynamicDataService.queryModelById(FinanceEnvironment.class, id);
		}
        
        model.addAttribute("vo", mod);
        //启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  		model.addAttribute("enable",enable);
        
        return "/dynamicdata/financeEnvironmentInfo";
	}
	
	/**
	 * 进入新增或修改页面（创新创业-动态数据-创新创业示范企业）
	 */
	@RequestMapping("/dynamicdata/opt-addOrEdit/addOrEditInnovBusiness")
	public String addOrEditInnovBusiness(HttpServletRequest request,String pageNo,String pageSize,Model model,HttpSession session){
		String id=request.getParameter("id");
		String modelType=request.getParameter("modelType");
		if(DataUtil.isNotNull(id)){//修改
			InnovBusiness vo = (InnovBusiness)dynamicDataService.queryModelById(InnovBusiness.class, id);
        	model.addAttribute("vo",vo);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("modelType", modelType);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
	    return "dynamicdata/addOrEditInnovBusiness";  
	}
	
	
	/**
	 *保存（新增、修改）（创新创业-动态数据-创新创业示范企业）
	 * */
	@RequestMapping("/dynamicdata/opt-save/saveInnovBusiness" )
	public String saveInnovBusiness(ModelMap model,InnovBusiness business, String pageNo,String pageSize,HttpServletRequest request){
		String modelType=request.getParameter("modelType");
        if(business != null && DataUtil.isNotNull(business.getId())){//修改
        	InnovBusiness mod = (InnovBusiness)dynamicDataService.queryModelById(InnovBusiness.class, business.getId());
        	mod.setName(business.getName());
        	mod.setBelong(business.getBelong());
        	mod.setScale(business.getScale());
        	
        	mod.setLinkman(business.getLinkman());
        	mod.setPhone(business.getPhone());
        	mod.setUrl(business.getUrl());
        	
        	mod.setStartTime(business.getStartTime());
        	mod.setStatus(business.getStatus());
        	dynamicDataService.updateModel(mod);
        	
        	//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
        	Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("修改成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("modelType", modelType);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			dynamicDataService.saveModel(business);
			//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
			Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("添加成功");
        	msg.addParamForward("modelType", modelType);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	/**
	 *查看
	 * */
	@RequestMapping("/dynamicdata/opt-query/innovBusinessInfo" )
	public String innovBusinessInfo(ModelMap model,HttpServletRequest request){
		//id
		String id=request.getParameter("id");
		
		InnovBusiness mod = new InnovBusiness();
		
        if(DataUtil.isNotNull(id)){
        	mod = (InnovBusiness)dynamicDataService.queryModelById(InnovBusiness.class, id);
		}
        
        model.addAttribute("vo", mod);
        //启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  		model.addAttribute("enable",enable);
        
        return "/dynamicdata/innovBusinessInfo";
	}
	
	/**
	 * 进入新增或修改页面（创新创业-动态数据-年度新增创新企业名录）
	 */
	@RequestMapping("/dynamicdata/opt-addOrEdit/addOrEditNewCompanies")
	public String addOrEditNewCompanies(HttpServletRequest request,Model model,String pageNo,String pageSize,HttpSession session){
		String id=request.getParameter("id");
		String modelType=request.getParameter("modelType");
		if(DataUtil.isNotNull(id)){//修改
			NewCompanies vo = (NewCompanies)dynamicDataService.queryModelById(NewCompanies.class, id);
        	model.addAttribute("vo",vo);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("modelType", modelType);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
	    return "dynamicdata/addOrEditNewCompanies";  
	}
	
	
	/**
	 *保存（新增、修改）（创新创业-动态数据-年度新增创新企业名录）
	 * */
	@RequestMapping("/dynamicdata/opt-save/saveNewCompanies" )
	public String saveNewCompanies(ModelMap model,NewCompanies newCompanies,String pageNo,String pageSize, HttpServletRequest request){
		String modelType=request.getParameter("modelType");
        if(newCompanies != null && DataUtil.isNotNull(newCompanies.getId())){//修改
        	NewCompanies mod = (NewCompanies)dynamicDataService.queryModelById(NewCompanies.class, newCompanies.getId());
        	mod.setCompName(newCompanies.getCompName());
        	mod.setOfIndustry(newCompanies.getOfIndustry());
        	mod.setTechnology(newCompanies.getTechnology());
        	mod.setScale(newCompanies.getScale());
        	mod.setLinkman(newCompanies.getLinkman());
        	mod.setTel(newCompanies.getTel());
        	mod.setWebSite(newCompanies.getWebSite());
        	mod.setStatus(newCompanies.getStatus());
        	dynamicDataService.updateModel(mod);
        	
        	//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
        	Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("修改成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("modelType", modelType);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			dynamicDataService.saveModel(newCompanies);
			//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
			Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("添加成功");
        	msg.addParamForward("modelType", modelType);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	/**
	 *查看
	 * */
	@RequestMapping("/dynamicdata/opt-query/newCompaniesInfo" )
	public String newCompaniesInfo(ModelMap model,HttpServletRequest request){
		//id
		String id=request.getParameter("id");
		
		NewCompanies mod = new NewCompanies();
		
        if(DataUtil.isNotNull(id)){
        	mod = (NewCompanies)dynamicDataService.queryModelById(NewCompanies.class, id);
		}
        
        model.addAttribute("vo", mod);
        //启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  		model.addAttribute("enable",enable);
        
        return "/dynamicdata/newCompaniesInfo";
	}
	
	/**
	 * 进入新增或修改页面（创新创业-动态数据-年度成功引入风险投资企业名录）
	 */
	@RequestMapping("/dynamicdata/opt-addOrEdit/addOrEditInvestCompanies")
	public String addOrEditInvestCompanies(HttpServletRequest request,String pageNo,String pageSize,Model model,HttpSession session){
		String id=request.getParameter("id");
		String modelType=request.getParameter("modelType");
		if(DataUtil.isNotNull(id)){//修改
			InvestCompanies vo = (InvestCompanies)dynamicDataService.queryModelById(InvestCompanies.class, id);
        	model.addAttribute("vo",vo);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("modelType", modelType);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
	    return "dynamicdata/addOrEditInvestCompanies";  
	}
	
	
	/**
	 *保存（新增、修改）（创新创业-动态数据-年度成功引入风险投资企业名录）
	 * */
	@RequestMapping("/dynamicdata/opt-save/saveInvestCompanies" )
	public String saveInvestCompanies(ModelMap model,InvestCompanies investCompanies,String pageNo,String pageSize, HttpServletRequest request){
		String modelType=request.getParameter("modelType");
        if(investCompanies != null && DataUtil.isNotNull(investCompanies.getId())){//修改
        	InvestCompanies mod = (InvestCompanies)dynamicDataService.queryModelById(InvestCompanies.class, investCompanies.getId());
        	mod.setCompName(investCompanies.getCompName());
        	mod.setOfIndustry(investCompanies.getOfIndustry());
        	mod.setInvestor(investCompanies.getInvestor());
        	mod.setScale(investCompanies.getScale());
        	mod.setLinkman(investCompanies.getLinkman());
        	mod.setTel(investCompanies.getTel());
        	mod.setWebSite(investCompanies.getWebSite());
        	mod.setStatus(investCompanies.getStatus());
        	dynamicDataService.updateModel(mod);
        	
        	//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
        	Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("修改成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("modelType", modelType);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			dynamicDataService.saveModel(investCompanies);
			//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
			Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("添加成功");
        	msg.addParamForward("modelType", modelType);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	/**
	 *查看
	 * */
	@RequestMapping("/dynamicdata/opt-query/investCompaniesInfo" )
	public String investCompaniesInfo(ModelMap model,HttpServletRequest request){
		//id
		String id=request.getParameter("id");
		
		InvestCompanies mod = new InvestCompanies();
		
        if(DataUtil.isNotNull(id)){
        	mod = (InvestCompanies)dynamicDataService.queryModelById(InvestCompanies.class, id);
		}
        
        model.addAttribute("vo", mod);
        //启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  		model.addAttribute("enable",enable);
        
        return "/dynamicdata/investCompaniesInfo";
	}
	
	/**
	 * 进入新增或修改页面（创新创业-动态数据-年度成功引入风险投资企业名录）
	 */
	@RequestMapping("/dynamicdata/opt-addOrEdit/addOrEditMarketCompanies")
	public String addOrEditMarketCompanies(HttpServletRequest request,String pageNo,String pageSize,Model model,HttpSession session){
		String id=request.getParameter("id");
		String modelType=request.getParameter("modelType");
		if(DataUtil.isNotNull(id)){//修改
			MarketCompanies vo = (MarketCompanies)dynamicDataService.queryModelById(MarketCompanies.class, id);
        	model.addAttribute("vo",vo);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("modelType", modelType);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
	    return "dynamicdata/addOrEditMarketCompanies";  
	}
	
	
	/**
	 *保存（新增、修改）（创新创业-动态数据-年度成功引入风险投资企业名录）
	 * */
	@RequestMapping("/dynamicdata/opt-save/saveMarketCompanies" )
	public String saveMarketCompanies(ModelMap model,MarketCompanies marketCompanies, String pageNo,String pageSize,HttpServletRequest request){
		String modelType=request.getParameter("modelType");
        if(marketCompanies != null && DataUtil.isNotNull(marketCompanies.getId())){//修改
        	MarketCompanies mod = (MarketCompanies)dynamicDataService.queryModelById(MarketCompanies.class, marketCompanies.getId());
        	mod.setCompName(marketCompanies.getCompName());
        	mod.setOfIndustry(marketCompanies.getOfIndustry());
        	mod.setLinkman(marketCompanies.getLinkman());
        	mod.setTel(marketCompanies.getTel());
        	mod.setWebSite(marketCompanies.getWebSite());
        	mod.setStatus(marketCompanies.getStatus());
        	mod.setBourse(marketCompanies.getBourse());
        	mod.setMarket(marketCompanies.getMarket());
        	mod.setMarketTime(marketCompanies.getMarketTime());
        	dynamicDataService.updateModel(mod);
        	
        	//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
        	Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("修改成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("modelType", modelType);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			dynamicDataService.saveModel(marketCompanies);
			//return new StringBuffer("redirect:/dynamicdata/opt-query/modelList.do?flag=1&modelType=").append(modelType).toString();
			Message msg = new Message("/dynamicdata/opt-query/modelList.do");
        	msg.setTips("添加成功");
        	msg.addParamForward("modelType", modelType);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}
	}
	
	/**
	 *查看
	 * */
	@RequestMapping("/dynamicdata/opt-query/marketCompaniesInfo" )
	public String marketCompaniesInfo(ModelMap model,HttpServletRequest request){
		//id
		String id=request.getParameter("id");
		
		MarketCompanies mod = new MarketCompanies();
		
        if(DataUtil.isNotNull(id)){
        	mod = (MarketCompanies)dynamicDataService.queryModelById(MarketCompanies.class, id);
		}
        
        model.addAttribute("vo", mod);
        
        //启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  		model.addAttribute("enable",enable);
        
        return "/dynamicdata/marketCompaniesInfo";
	}
}
