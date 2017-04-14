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
import com.uws.model.ExcellentPerson;
import com.uws.service.IExcellentPersonService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;

/**
 * 创新人才/领军人物Controller
 * @author 张学彪
 */
@Controller
public class ExcellentPersonController extends BaseController{

	@Autowired
	private IExcellentPersonService excellentPersonService;
	
	@Resource
	private ISysDicService sysDicService;
	
	/**
	 * 创新人才/领军人物列表
	 */
	@RequestMapping("/ilperson/opt-query/ilList")
	public String newsAllList(HttpServletRequest request,ExcellentPerson person,String type,Model model,HttpSession session){
		Map<String, Object> param = this.initParamMap(request);
		
		//是否保存查询条件
		String flag=request.getParameter("flag");
		
		if("1".equals(flag)){
			person=(ExcellentPerson)session.getAttribute("ilperson");
		}
		/**
		 * type = "INNOVATE" 为创新人才
		 * type = "LEADER" 为领军人物
		 */
		if(DataUtil.isNotNull(type) && type.equals("INNOVATE")){
			person.setIsInnovate("1");
		}else{
			person.setIsLeader("1");
		}
		
		Page page=excellentPersonService.queryExcellentPersonList(param, person);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		//禁用状态字典
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		
		//保存查询条件
		session.setAttribute("ilperson", person);
		session.setAttribute("type", type);
		model.addAttribute("page", page);
		model.addAttribute("enable",enable);
		model.addAttribute("disable", disable);
		
	    return "/ilperson/ilList";  
	}
	
	
	/**
	 * 进入新增或修改页面
	 */
	@RequestMapping("/ilperson/opt-addOrEdit/addOrEditIlperson")
	public String addOrEditIlperson(HttpServletRequest request,String pageNo,String pageSize,Model model,HttpSession session){
		
		String personId=request.getParameter("personId");
		
		/**
		 * type = "INNOVATE" 为创新人才
		 * type = "LEADER" 为领军人物
		 */
		String type=request.getParameter("type");
		
		if(DataUtil.isNotNull(personId)){//修改
			ExcellentPerson person = excellentPersonService.queryExcellentPersonByPersonId(personId);
			model.addAttribute("person", person);
		}
		
		model.addAttribute("type", type);
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("enable", enable);
		model.addAttribute("saveLocation", Constants.FILE_PATH);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		
	    return "/ilperson/addOrEditIlperson";  
	}
	
	
	/**
	 *保存（新增、修改）
	 * */
	@RequestMapping("/ilperson/opt-save/saveIlPerson" )
	public String saveIlPerson(ModelMap model,ExcellentPerson person,String type,String pageNo,String pageSize, HttpServletRequest request){
		
        if(person != null && DataUtil.isNotNull(person.getId())){//修改
        	
        	ExcellentPerson p = excellentPersonService.queryExcellentPersonByPersonId(person.getId());
        	p.setPsName(person.getPsName());
        	p.setPsJob(person.getPsJob());
        	p.setPsTel(person.getPsTel());
        	p.setPsBelong(person.getPsBelong());
        	p.setPsIntroduce(person.getPsIntroduce());
        	p.setPsImage(person.getPsImage());
        	p.setStatus(person.getStatus());
        	p.setComments(person.getComments());
        	excellentPersonService.updateExcellentPerson(p);
        	
        	//return new StringBuffer("redirect:/ilperson/opt-query/ilList.do?type=").append(type).toString();
        	Message msg = new Message("/ilperson/opt-query/ilList.do");
        	msg.setTips("修改成功");
        	msg.addParamForward("flag", "1");
        	msg.addParamForward("type", type);
        	msg.addParamForward("pageNo", pageNo);
        	msg.addParamForward("pageSize", pageSize);
        	msg.addParamForward("pageSearchType", pageNo);
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        	return "toForward";
		}else{
			/**
			 * type = "INNOVATE" 为创新人才
			 * type = "LEADER" 为领军人物
			 */
			if(DataUtil.isNotNull(type) && type.equals("INNOVATE")){
				person.setIsInnovate("1");
			}else{
				person.setIsLeader("1");
			}
			excellentPersonService.saveExcellentPerson(person);
			//return new StringBuffer("redirect:/ilperson/opt-query/ilList.do?type=").append(type).toString();
			Message msg = new Message("/ilperson/opt-query/ilList.do");
        	msg.setTips("添加成功");
        	msg.addParamForward("type", type);
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
	@RequestMapping(value={"/ilperson/opt-update/changeStatus"})
	public String changeStatus(ModelMap model,String personId,String value,String type,String pageNo,String pageSize,String moduleCode){
		
		excellentPersonService.changeStatus(personId, value);
		
		//return new StringBuffer("redirect:/ilperson/opt-query/ilList.do?type=").append(type).toString();
		Message msg = new Message("/ilperson/opt-query/ilList.do");
    	msg.setTips("操作成功");
    	msg.addParamForward("flag", "1");
    	msg.addParamForward("type", type);
    	msg.addParamForward("pageNo", pageNo);
    	msg.addParamForward("pageSize", pageSize);
    	msg.addParamForward("pageSearchType", pageNo);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 *查看新闻
	 * */
	@RequestMapping("/ilperson/opt-query/ilPersonInfo" )
	public String ilPersonInfo(ModelMap model,HttpServletRequest request,String type){
		//新闻id
		String personId=request.getParameter("personId");
		
		ExcellentPerson person = new ExcellentPerson();
		
        if(DataUtil.isNotNull(personId)){
        	person = excellentPersonService.queryExcellentPersonByPersonId(personId);
		}
        
        model.addAttribute("person", person);
        
        //启用状态字典集合 
  		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
  		
  		//启用状态字典
  		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
  				
  		model.addAttribute("statusList", statusList);
  		model.addAttribute("enable", enable);
  		model.addAttribute("type", type);
        
        return "/ilperson/ilPersonInfo";
	}
	
	
	/**
	 *删除新闻
	 * */
	@RequestMapping("/ilperson/opt-del/deleteIlPerson" )
	public String deleteIlPerson(ModelMap model,HttpServletRequest request){
		String personId=request.getParameter("personId");
		String type=request.getParameter("type");
        if(DataUtil.isNotNull(personId)){
        	excellentPersonService.deleteExcellentPersonById(personId);
		}
        
        //return new StringBuffer("redirect:/ilperson/opt-query/ilList.do?type=").append(type).toString();
        Message msg = new Message("/ilperson/opt-query/ilList.do");
    	msg.setTips("删除成功");
    	msg.addParamForward("type", type);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 *批量删除新闻
	 * */
	@RequestMapping("/ilperson/opt-del/pdeleteIlPerson" )
	public String deleteNewsAlls(ModelMap model,HttpServletRequest request){
		String personIds=request.getParameter("personIds");
		String type=request.getParameter("type");
		
        if(DataUtil.isNotNull(personIds)){
        	excellentPersonService.deleteExcellentPersonsByIds(personIds);
		}
        
        //return new StringBuffer("redirect:/ilperson/opt-query/ilList.do?type=").append(type).toString();
        Message msg = new Message("/ilperson/opt-query/ilList.do");
    	msg.setTips("删除成功");
    	msg.addParamForward("type", type);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
 
}
