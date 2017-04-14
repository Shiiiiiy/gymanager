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
import com.uws.model.Company;
import com.uws.model.Dic;
import com.uws.model.Product;
import com.uws.service.IF1CompanyService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;

/**
 * 企业信息-企业列表Controller
 * @author 张学彪
 */
@Controller
public class F1CompanyController extends BaseController{

	@Autowired
	private IF1CompanyService companyService;
	
	@Resource
	private ISysDicService sysDicService;
	
	/**
	 * 企业信息-企业列表
	 */
	@RequestMapping("/f1company/opt-query/f1companyList")
	public String companyList(HttpServletRequest request,Company company,Product product,Model model,HttpSession session){
		Map<String, Object> param = this.initParamMap(request);
		
		//是否保存查询条件
		String flag=request.getParameter("flag");
		
		if("1".equals(flag)){
			company=(Company)session.getAttribute("scompany");
			product=(Product)session.getAttribute("sproduct");
		}
		
		//判断企业关系表中是否已经存在F1的关系
		boolean hasF1Realation = companyService.hasF1Realation(Constants.F1);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		/**
		 * 如果没有关系初始化F1关系
		 * 1.获取所有F1已启用企业信息
		 * 2.插入企业关系表(PROP_TYPE为空)
		 */
		if(!hasF1Realation){
			List<Company> companys = companyService.queryEnableCompany(enable.getId().intValue());
			companyService.initF1Prop(companys, Constants.F1);
		}
		
		Page page=companyService.queryCompanyListByModule(param, company,product,"F1");
		
		//保存查询条件
		session.setAttribute("scompany", company);
		session.setAttribute("sproduct", product);
		model.addAttribute("page", page);
		
	    return "f1company/f1companyList";  
	}
	
	/**
	 *批量删除
	 * */
	@RequestMapping("/f1company/cancelCompany" )
	public String cancelCompany(ModelMap model,HttpServletRequest request){
		String propIds=request.getParameter("propIds");
		
        if(DataUtil.isNotNull(propIds)){
        	companyService.cancelCompany(propIds);
		}
        
        //return "redirect:/f1company/opt-query/f1companyList.do?flag=1";
        Message msg = new Message("/f1company/opt-query/f1companyList.do");
    	msg.setTips("取消成功");
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 *批量推荐/批量不推荐
	 * */
	@RequestMapping("/f1company/recommendCompany" )
	public String recommendCompany(ModelMap model,String pageNo,String pageSize,HttpServletRequest request){
		String propIds=request.getParameter("propIds");
		String propType=request.getParameter("propType");
		
        if(DataUtil.isNotNull(propIds)){
        	companyService.setOrCancelRecommendCompany(propIds, propType);
		}
        
        //return "redirect:/f1company/opt-query/f1companyList.do??flag=1";
        Message msg = new Message("/f1company/opt-query/f1companyList.do");
    	msg.setTips("操作成功");
    	msg.addParamForward("flag", "1");
    	msg.addParamForward("pageNo", pageNo);
    	msg.addParamForward("pageSize", pageSize);
    	msg.addParamForward("pageSearchType", pageNo);
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 *添加企业
	 * */
	@RequestMapping("/f1company/saveCompany" )
	public String saveCompany(ModelMap model,HttpServletRequest request){
		String companyIds=request.getParameter("companyIds");
		
        if(DataUtil.isNotNull(companyIds)){
        	//得到数据库中不存在的companyId
        	List<String> ids = companyService.getNoExsitIds(companyIds,"F1");
        	companyService.saveProp(ids,null,"F1");
		}
        
        //return "redirect:/f1company/opt-query/f1companyList.do??flag=1";
        Message msg = new Message("/f1company/opt-query/f1companyList.do");
    	msg.setTips("添加成功");
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}

}
