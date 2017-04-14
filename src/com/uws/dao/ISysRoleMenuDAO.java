package com.uws.dao;

import java.util.*;

import com.uws.model.SysRoleMenu;
import com.uws.model.SysRoleUser;

/**
 * 系统角色与菜单关系dao接口
 * @author hejin
 *
 */
public interface ISysRoleMenuDAO {

	 /**
	  * 根据角色id查询菜单列表
	  * @param roleId 角色id
	  * @return
	  */
	 public List<Map> getSysRoleMenuList(String roleId);
	 
	 
	 /**
	  * 根据角色id列表查询菜单列表
	  * @param roleIdList 角色id列表
	  * @return
	  */
	 public List<Map> getSysRoleMenuListAll(List<Long> roleIdList);
	 
	 
	 
	/**
	 * 删除角色与菜单关系
	 * @param sysRoleMenu  菜单与角色关系实体
	 */
	public void delSysRoleMenu(SysRoleMenu sysRoleMenu);


	/**
	 * 保存菜单与角色关系
	 * @param sysRoleMenu 菜单与角色关系实体
	 */
	public void saveSysRoleMenu(SysRoleMenu sysRoleMenu);


	/**
	 * 批量删除菜单与角色关系
	 * @param roleId 角色id
	 * @param menuIdList 菜单id列表
	 */
	public void delRoleMenuMutil(Long roleId, List<Long> menuIdList);


	/**
	 * 新增菜单与角色关系
	 * @param sysRoleMenu 菜单与角色关系实体
	 */
	public void addSysRoleMenu(Long menuId,Long roleId);
	
}
