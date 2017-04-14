package com.uws.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.dao.Page;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.model.Dic;
import com.uws.model.SysUser;
import com.uws.service.ISysDicService;
import com.uws.service.ISysUserService;
import com.uws.util.file.FileUtils;
import com.uws.util.file.TempDataBase;

/**
 * 系统用户 Controller
 * @author wangjun
 *
 */
@Controller
@RequestMapping("/sysuser")
public class SysUserController extends BaseController{
	/**系统字典服务*/
	@Resource
	private ISysDicService sysDicService;
	/**系统用户服务*/
	@Resource
	private ISysUserService sysUserService;
	/**
	 * 进入系统用户 List页面 out
	 * @param 
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model,
			SysUser O){
		Map param = this.initParamMap(request);//分页参数
		Page page = this.sysUserService.search(param,O);
		model.addAttribute("page", page);
		model.addAttribute("UserSearch", O);
		List<Dic> Dics=sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		//字典
		model.addAttribute("Dics", Dics);
		return "sysuser/list"; 
	}
	/**
	 * 进入系统用户 新增页面
	 * @param 
	 * @return
	 */
	@RequestMapping("/newuser")
	public String newuser(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model){
		List<Dic> Dics=sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		model.addAttribute("Dics", Dics);
	    return "sysuser/newuser";  
	}
	/**
	 * 进入系统用户 编辑页面
	 * @param 
	 * @return
	 */
	@RequestMapping("/edituser")
	public String edituser(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model,
			String id){
		SysUser O=this.sysUserService.searchById(id);
		//页面密码预 处理
		String truepwd=O.getPwd();String fakepwd="";
		for (int i = 0; i < truepwd.length(); i++) {
			fakepwd+="#";
		}O.setPwd(fakepwd);
		//
		model.addAttribute("UserOne", O);
		List<Dic> Dics=sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		model.addAttribute("Dics", Dics);
	    return "sysuser/edituser";  
	}
	/**
	 * 对 系统用户 进行保存操作
	 * @param SysUser 系统用户实体
	 * @return
	 */
	@RequestMapping("/save")
	public String save(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model
			,SysUser SysUser){
		sysUserService.save(SysUser);
		return "redirect:/sysuser/list.do";
	}
	/**
	 * 对 系统用户 进行更新操作
	 * @param SysUser 系统用户实体
	 * @return
	 */
	@RequestMapping("/update")
	public String update(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model
			,SysUser SysUser,String notchangepwd){
		if("true".equals(notchangepwd)){
			sysUserService.update(SysUser,true);//需要处理密码
		}else{
			sysUserService.update(SysUser,false);//不需要处理密码
		}
		return "redirect:/sysuser/list.do";
	}
	/**
	 * 改变指定用户 启用禁用状态
	 * @param id 用户表id
     * @return
	 */
	@RequestMapping("/changestatus")
	public String changestatus(HttpServletRequest request,HttpServletResponse response,String id){
		Boolean bl=sysUserService.updateStatus(id);
		return "redirect:/sysuser/list.do";
	}
	/**
	 * 删除指定用户 
	 * @param id 用户表id
     * @return
	 */
	@RequestMapping("/deluser")
	public String deluser(HttpServletRequest request,HttpServletResponse response,String id){
		Boolean bl=sysUserService.deleteUserById(id);
		return "redirect:/sysuser/list.do";
	}
	/**
	 * 重置指定 用户密码 为系统默认初始密码  DEFAULT_PASSWORD
	 * @param id 用户表id
     * @return
	 */
	@RequestMapping("/resetpwd")
	public String resetpwd(HttpServletRequest request,HttpServletResponse response,String id){
		Boolean bl=sysUserService.resetUserPwd(id);
		return "redirect:/sysuser/list.do";
	}
	
	/**
	 * 判断数据库是否已经存在此 帐号user_no 
	 * @param user_no 用户表的 帐号字段
     * @return true 有重名
	 */
	@RequestMapping("/checkDupliUser")
	@ResponseBody
	public String checkDupliUser(HttpServletRequest request,HttpServletResponse response,String user_no){
		boolean stat=true;
		Boolean bl=sysUserService.checkDupliUser(user_no);
		if(stat && bl){return "true";}	
		return "false";
	}
	
	/**
	 * 判断值为id的用户   密码是否输入正确 
	 * @param id 用户id  pwd 用户密码
     * @return true 密码输入正确
	 */
	@RequestMapping("/checkPwd")
	@ResponseBody
	public String checkPwd(HttpServletRequest request,HttpServletResponse response,String id,String pwd){
		boolean stat=true;
		Boolean bl=sysUserService.checkPwd(id,pwd);
		if(stat && bl){return "true";}	
		return "false";
	}

}
