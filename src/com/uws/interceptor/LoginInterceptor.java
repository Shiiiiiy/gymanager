package com.uws.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.uws.model.SysMenu;
import com.uws.model.SysUser;
import com.uws.service.ISysMenuService;
import com.uws.service.ISysRoleService;
import com.uws.service.ISysUserService;
import com.uws.util.Constants;
import com.uws.util.Util;

/**
 * 登陆拦截器,主要是查看Session是否过期
 * @author zhouchang
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired(required=true)
	private ISysUserService sysUserService;
	
	@Autowired(required=true)
	private ISysRoleService sysRoleService;
	
	@Autowired(required=true)
	private ISysMenuService sysMenuService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		SysUser user = (SysUser) session.getAttribute(Constants.LOGIN_USER);
		Cookie[] cookies = request.getCookies();
		boolean hasLogin = false;
		String userId = null;
		String verifyCode = null;
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if("userid".equals(cookie.getName())){
					userId = cookie.getValue();
				}
				if("verify".equals(cookie.getName())){
					verifyCode = cookie.getValue();
				}
			}
		}
		
		//此处存在cookie被纂改的可能
		if(userId == null || verifyCode == null || 
				!verifyCode.equals(DigestUtils.md5DigestAsHex((userId + userId).getBytes()))){
			hasLogin = false;
		}else{
			hasLogin = true;
		}
		
		if(user == null && hasLogin == false){
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		
		if(user == null && hasLogin == true){
			user = sysUserService.searchByUser_No(userId);
			session.setAttribute(Constants.LOGIN_USER, user);
			
			// 获取用户菜单
			@SuppressWarnings("rawtypes")
			List<Map> menus = this.sysRoleService.getUserPermissionByUserId(user.getId().toString());
			SysMenu rootMenu = this.sysMenuService.getRootMenu();
			//菜单整理
			Map<String,List<Map<String,String>>> menusMap = Util.resortMenus(menus,rootMenu);
			session.setAttribute("usermenus", menusMap);
			//response.sendRedirect(request.getContextPath() + "/main/index");
		}
		
		return true;
	}
}
