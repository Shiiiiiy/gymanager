package com.uws.controller;

import java.util.List;
import java.util.Map;

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
import com.uws.model.Product;
import com.uws.service.IF1CompanyService;

/**
 * 创新创业-示范企业Controller
 * @author 张学彪
 */
@Controller
public class F6CompanyController extends BaseController{

	@Autowired
	private IF1CompanyService companyService;
	
	/**
	 * 示范企业列表
	 */
	@RequestMapping("/f6company/opt-query/f6companyList")
	public String companyList(HttpServletRequest request,Company company,Product product,Model model,HttpSession session){
		Map<String, Object> param = this.initParamMap(request);
		
		//是否保存查询条件
		String flag=request.getParameter("flag");
		
		if("1".equals(flag)){
			company=(Company)session.getAttribute("company");
			product=(Product)session.getAttribute("product");
		}
		
		Page page=companyService.queryCompanyListByModule(param, company,product,"F6");
		
		//保存查询条件
		session.setAttribute("company", company);
		session.setAttribute("product", product);
		model.addAttribute("page", page);
		
	    return "f1company/f6companyList";  
	}
	
	/**
	 *批量删除
	 * */
	@RequestMapping("/f6company/cancelCompany" )
	public String cancelCompany(ModelMap model,HttpServletRequest request){
		String propIds=request.getParameter("propIds");
		
        if(DataUtil.isNotNull(propIds)){
        	companyService.cancelCompany(propIds);
		}
        
        //return "redirect:/f6company/opt-query/f6companyList.do??flag=1";
        Message msg = new Message("/f6company/opt-query/f6companyList.do");
    	msg.setTips("取消成功");
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	/**
	 *添加企业
	 * */
	@RequestMapping("/f6company/saveCompany" )
	public String saveCompany(ModelMap model,HttpServletRequest request){
		String companyIds=request.getParameter("companyIds");
		
        if(DataUtil.isNotNull(companyIds)){
        	//得到数据库中不存在的companyId
        	List<String> ids = companyService.getNoExsitIds(companyIds,"F6");
        	companyService.saveProp(ids,"PROP_A","F6");
		}
        
        //return "redirect:/f6company/opt-query/f6companyList.do??flag=1";
        Message msg = new Message("/f6company/opt-query/f6companyList.do");
    	msg.setTips("添加成功");
    	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
}
