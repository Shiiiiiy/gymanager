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
import com.base.util.DefaultValue;
import com.uws.model.Dic;
import com.uws.model.SysMenu;
import com.uws.model.SysUser;
import com.uws.service.ISysDicService;
import com.uws.service.ISysMenuService;
import com.uws.service.ISysUserService;
/**
 * 系统菜单 Controller
 * @author wangjun
 *
 */
@Controller
@RequestMapping("/sysmenu")
public class SysMenuController extends BaseController{
	/**系统字典服务*/
	@Resource
	private ISysDicService sysDicService;
	/**系统菜单服务*/
	@Resource
	private ISysMenuService sysMenuService;
	/**
	 * 进入系统主布局页面   准备激活的parentid
	 * @param 
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model,
			String parentid){
		if(null==parentid || "".equals(parentid)){
			SysMenu M= sysMenuService.getRootMenu();
			parentid=M.getId()+"";}//如果是第一次 那么展开根节点
		model.addAttribute("parentid", parentid);
		List<Dic> Dics=sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		model.addAttribute("Dics", Dics);
		//初始化 dtree
		List<Map> list = sysMenuService.Dtree();
		model.addAttribute("list", list); 
		//
		return "sysmenu/list";  
	}

	/**
	 * 进入系统菜单 右侧展示页面
	 * @param id 父类菜单id
	 * @return
	 */
	@RequestMapping("/menu")
	public String menu(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model,
			String id){
		if(null==id || "root".equals(id)){
			SysMenu M= sysMenuService.getRootMenu();
			id=M.getId()+"";}//如果是第一次 那么展开根节点	
		List<SysMenu> ListMenu= sysMenuService.getMenuListById(id);
		SysMenu MenuO= sysMenuService.searchById(id);
		model.addAttribute("ListMenu", ListMenu);
		model.addAttribute("MenuO", MenuO);
		List<Dic> Dics=sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		model.addAttribute("Dics", Dics);
		Boolean bl=sysMenuService.isLastMenu(id);
		if(bl){model.addAttribute("no_next", "no_next");}
	    return "sysmenu/menu";  
	}
	/**
	 * 删除指定菜单  通过菜单id
	 * @param id 菜单表id
     * @return
	 */
	@RequestMapping("/delmenu")
	public String delmenu(HttpServletRequest request,HttpServletResponse response,String id){
		boolean stat=true;
		String pid=sysMenuService.searchById(id).getP_id()+"";
		Boolean bl=sysMenuService.deleteMenuById(id);
		return "redirect:/sysmenu/list.do?parentid="+pid;
	}
	/**
	 * 改变指定菜单 启用禁用状态
	 * @param id 菜单表id
     * @return
	 */
	@RequestMapping("/changestatus")
	public String changestatus(HttpServletRequest request,HttpServletResponse response,String id){
		boolean stat=true;
		Boolean bl=sysMenuService.updateStatus(id);
		String pid=sysMenuService.searchById(id).getP_id()+"";
		return "redirect:/sysmenu/list.do?parentid="+pid;
	}
	/**
	 * 进入系统菜单 保存修改
	 * @param 
	 * @return
	 */
	@RequestMapping("/editmenu")
	public String editmenu(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model
		,SysMenu SysMenu,long tid){
		SysMenu.setId(tid);
		sysMenuService.update(SysMenu);
		String pid=SysMenu.getP_id()+"";
		return "redirect:/sysmenu/list.do?parentid="+pid;
		
	}
	/**
	 * 对 系统菜单 进行保存操作
	 * @param SysUser 系统用户实体
	 * @return
	 */
	@RequestMapping("/savemenu")
	public String save(HttpSession session, HttpServletRequest request,HttpServletResponse response, Model model
			,SysMenu SysMenu){
		SysMenu M=sysMenuService.save(SysMenu);
		String pid=SysMenu.getP_id()+"";
		return "redirect:/sysmenu/list.do?parentid="+pid;
	}
	
	
	/**
	 * 查询菜单  节点列表 异步加载 ztree   out of date
	 * @param request
	 * @param model
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getMenuTree")
	public void getMenuTree(HttpServletRequest request,HttpServletResponse response,String ID){
		String parentCode=ID;
		List<Map> list=new ArrayList();
		//菜单节点列表
		List<Map> menuList=this.sysMenuService.getMenuListByParentCode(parentCode);
		if(menuList!=null && menuList.size()>0){
			for(int i=0;i<menuList.size();i++){
				String pid=(String) menuList.get(i).get("ID");
				list=sysMenuService.getMenuListByParentCode(pid);
				if(list!=null && list.size()>0){
					menuList.get(i).put("isParent", true);
					menuList.get(i).put("open",true);
				}else{
					menuList.get(i).put("isParent", false);
					menuList.get(i).put("open",true);

				}
				
			}
		}
		ObjectMapper mapper= new ObjectMapper();
		response.setCharacterEncoding("UTF-8");
		try {
			 response.getWriter().write(mapper.writeValueAsString(menuList));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 判断数据库是否已经存在此  MenuCode
	 * @param code 菜单表code
     * @return true 有重名
	 */
	@RequestMapping("/checkDupliMenuCode")
	@ResponseBody
	public String checkDupliMenuCode(HttpServletRequest request,HttpServletResponse response,String code){
		boolean stat=true;
		Boolean bl=sysMenuService.checkDupliMenuCode(code);
		if(stat && bl){return "true";}	
		return "false";
	}
}
