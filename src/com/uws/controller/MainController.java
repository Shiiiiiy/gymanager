package com.uws.controller;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.dao.Page;
import com.uws.service.IDemoService;

@Controller
public class MainController extends BaseController{
	

	@Resource
	private IDemoService userService;
	
	
	/**
	 * 进入后端首页
	 */
	@RequestMapping("/main/index")
	public String index(HttpServletRequest request,Model model){
		
		//@zhouchang 登陆后 在这里加载用户菜单
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Map<String,List<Map<String,String>>> menus = 
			(Map<String,List<Map<String,String>>>) request.getSession().getAttribute("usermenus");
		model.addAttribute("menus", menus);
	    return "main/main";  
	}
	
	/**
	 * 进入后端首页
	 */
	@RequestMapping("/main/list")
	public String list(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model){
		
		Map param = this.initParamMap(request);
		
		Page page = userService.search(param);
		model.addAttribute("page", page);
	    return "demo/list";  
	}
	
	/**
	 * 进入后端首页
	 */
	@RequestMapping("/main/form")
	public String form(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model){
	    return "demo/form";  
	}
	
	/**
	 * 进入后端首页
	 */
	@RequestMapping("/main/login")
	public String Login(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model){
	    return "demo/login";  
	}
	
	/**
	 * 进入后端首页
	 */
	@RequestMapping("/main/dtree")
	public String dtree(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model){
		List list = userService.Dtree();
		model.addAttribute("list", list);
	    return "demo/dtree";  
	}
}
