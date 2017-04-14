package com.uws.model;

import java.io.Serializable;

import com.base.model.BaseModel;
/**
 * 系统角色与用户关系model
 * @author hejin
 *
 */
public class SysRoleUser extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1534610088134445751L;


	
	/**
	 * 用户id
	 */
	private Long userId ;
	
	
	/**
	 * 角色id
	 */
	private Long roleId ;




	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getRoleId() {
		return roleId;
	}


	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	
	
}
