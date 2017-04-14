package com.uws.model;

import java.io.Serializable;
import java.math.BigInteger;

import com.base.model.BaseModel;
/**
 * 系统角色与菜单关系model
 * @author hejin
 *
 */
public class SysRoleMenu extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5322065726684726935L;


	
	/**
	 * 菜单id
	 */
	private Long menuId ;
	
	
	/**
	 * 角色id
	 */
	private Long roleId ;



	public Long getMenuId() {
		return menuId;
	}


	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}


	public Long getRoleId() {
		return roleId;
	}


	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	
	
	
}
