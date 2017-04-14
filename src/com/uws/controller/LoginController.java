package com.uws.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.util.DefaultValue;
import com.uws.dto.LoginResult;
import com.uws.model.Dic;
import com.uws.model.SysMenu;
import com.uws.model.SysUser;
import com.uws.service.ISysDicService;
import com.uws.service.ISysMenuService;
import com.uws.service.ISysRoleService;
import com.uws.service.ISysUserService;
import com.uws.util.Constants;
import com.uws.util.Util;

@Controller
public class LoginController {

	@Autowired(required=true)
	private ISysUserService sysUserService;
	
	@Autowired(required=true)
	private ISysRoleService sysRoleService;
	
	@Autowired(required=true)
	private ISysMenuService sysMenuService;
	
	@Autowired(required=true)
	private ISysDicService sysDicService;
	
	/**
	 * 进入登陆页面
	 * @return
	 */
	@RequestMapping(value="/login")
	public String initLogin(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		if(user != null){
			return "redirect:/main/index";
		}
		//是否曾经输错过密码 超过三次
		Boolean isMultiLogin = (Boolean) session.getAttribute("MultiLogin");
		if(isMultiLogin != null){
			model.addAttribute("isMultiLogin", true);
		}else model.addAttribute("isMultiLogin", false);
		
		//校验cookie, 如果用户多次输错密码,然后关闭浏览器再次打开,依然会出现验证码
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if(cookie != null && "safety".equals(cookie.getName())){
					//此处不对 cookie值做任何校验
					model.addAttribute("isMultiLogin", true);
					request.getSession().setAttribute("MultiLogin", true);
				}
			}
		}
		return "login";
	}
	
	/**
	 * 验证登陆
	 * @param request
	 * @param response
	 * @param username: 用户名
	 * @param userpassword 用户密码
	 * @param verifycode 验证码(如果有)
	 * @return 登陆结果
	 */
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public @ResponseBody LoginResult doLogin(HttpServletRequest request,
						  HttpServletResponse response,
							String username, String userpassword,
							String verifycode){
		HttpSession session = request.getSession(true);
		//统计错误次数
		Integer mistakeTimes = (Integer) session.getAttribute("MISTAKE_TIMES");
		mistakeTimes = mistakeTimes == null ? 0 : mistakeTimes;
		
		//是否显示验证码 变量,默认为为显示
		boolean showVerifyCode = false; 
		
		//检查是否是多次登陆
		Boolean isMultiLogin = (Boolean) session.getAttribute("MultiLogin");
		if(isMultiLogin != null){
			showVerifyCode = true;
			//重复尝试登陆,验证码需验证
			if(verifycode == null || session.getAttribute(Constants.VERIFY_CODE) == null){
				//过期的验证码
				return new LoginResult(false,false,true,false,showVerifyCode);
			}
			String sessionVerifyCode = (String) session.getAttribute(Constants.VERIFY_CODE);
			if(!verifycode.equals(sessionVerifyCode)){
				//错误的验证码
				return new LoginResult(false,false,true,false,showVerifyCode);
			}
		}
		
		//开始验证用户名和密码
		if(StringUtils.hasText(username) && StringUtils.hasText(userpassword)){
			SysUser user = this.sysUserService.searchByUser_No(username);
			if(user == null){
				
				mistakeTimes ++; 
				session.setAttribute("MISTAKE_TIMES", mistakeTimes);
				//密码错误三次 出验证码的同时, 写入用户浏览器Cookie, 即使关掉浏览器重新打开, 依然需要验证
				if(mistakeTimes >= 3){
					session.setAttribute("MultiLogin", true);
					Cookie safetyCookie = new Cookie("safety",
							DigestUtils.md5DigestAsHex((username).getBytes()));//写入安全码
					safetyCookie.setMaxAge(7 * 24 * 60 * 60);//保存一周
					safetyCookie.setPath("/"); //所有请求都会携带此cookie
					response.addCookie(safetyCookie);
					showVerifyCode = true;
				}
				
				return new LoginResult(false,false,false,true,showVerifyCode);
			}

			if(user.getPwd_id().equals(Util.encryptMD5(userpassword))){
				
				Dic disable = sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
				
				if(user.getStatus() != null && user.getStatus().equals(disable.getId())){
					
					return new LoginResult(false,true,false,false,showVerifyCode);
				}
				
				//验证通过
				session.setAttribute(Constants.LOGIN_USER, user);
				
				//设置cookie, 如果用户浏览器没有关闭,登陆持续有效
				Cookie verifyCookie = new Cookie("verify",
						DigestUtils.md5DigestAsHex((username + username).getBytes()));//双写用户名是一种保护措施
				verifyCookie.setMaxAge(-1);//此行代码可省,默认即为 -1,设置该cookie保存到用户浏览器关闭
				verifyCookie.setPath("/"); //所有请求都会携带此cookie
				
				Cookie userNameCookie = new Cookie("userid", username);
				userNameCookie.setMaxAge(-1);//此行代码可省,默认即为 -1,设置该cookie保存到用户浏览器关闭
				userNameCookie.setPath("/"); //所有请求都会携带此cookie
				response.addCookie(verifyCookie);
				response.addCookie(userNameCookie);
				
				// 获取用户菜单
				@SuppressWarnings("rawtypes")
				List<Map> menus = this.sysRoleService.getUserPermissionByUserId(user.getId().toString());
				SysMenu rootMenu = this.sysMenuService.getRootMenu();
				//session.setAttribute("usermenus", menus);
				Map<String,List<Map<String,String>>> menusMap = Util.resortMenus(menus,rootMenu);
				session.setAttribute("usermenus", menusMap);
				
				//移除用户端可能存在的 需要验证的cookie以及session中的密码错误次数
				session.removeAttribute("MISTAKE_TIMES");
				session.removeAttribute("MultiLogin");
				Cookie[] cookies = request.getCookies();
				for (Cookie cookie : cookies) {
					if(cookie != null && "safety".equals(cookie.getName())){
						Cookie safetyCookie = new Cookie("safety",
								DigestUtils.md5DigestAsHex((username).getBytes()));//写入安全码
						safetyCookie.setMaxAge(0);//主即删除此cookie
						safetyCookie.setPath("/"); //所有请求都会携带此cookie
						response.addCookie(safetyCookie);
					}
				}
				
				// 初始化
				return new LoginResult(true,false,false,false,showVerifyCode);
			}
		}
		
		mistakeTimes ++; 
		session.setAttribute("MISTAKE_TIMES", mistakeTimes);
		//密码错误三次 出验证码的同时, 写入用户浏览器Cookie, 即使关掉浏览器重新打开, 依然需要验证
		if(mistakeTimes >= 3){
			session.setAttribute("MultiLogin", true);
			Cookie safetyCookie = new Cookie("safety",
					DigestUtils.md5DigestAsHex((username).getBytes()));//写入安全码
			safetyCookie.setMaxAge(7 * 24 * 60 * 60);//保存一周
			safetyCookie.setPath("/"); //所有请求都会携带此cookie
			response.addCookie(safetyCookie);
			showVerifyCode = true;
		}
		return new LoginResult(false,false,false,true,showVerifyCode);
	}
	
	/**
	 * 用户手动退出登陆
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		session.removeAttribute(Constants.LOGIN_USER);
		session.removeAttribute("usermenus");
		//session.removeAttribute("MultiLogin");
		
		//移除Cookie值
		Cookie verifyCookie = new Cookie("verify",null);
		verifyCookie.setMaxAge(0);//删除此cookie
		verifyCookie.setPath("/"); //设置cookie的域,就是删除"/"下面的这个cookie对象
		
		Cookie userNameCookie = new Cookie("userid", null);
		userNameCookie.setMaxAge(0);
		userNameCookie.setPath("/");

		response.addCookie(verifyCookie);
		response.addCookie(userNameCookie);
		
		return "redirect:/login";
	}
	
}
