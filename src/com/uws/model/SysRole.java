package com.uws.model;

import java.io.Serializable;

import com.base.model.BaseModel;
/**
 * 系统角色model
 * @author hejin
 *
 */
public class SysRole extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -249292542153869767L;
	
	
	
	/**
	 * 角色编码
	 */
	private String roleCode ;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 备注
	 */
	private String remarker;
	
	/**
	 * 启用状态
	 */
	private Long status;
	

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemarker() {
		return remarker;
	}

	public void setRemarker(String remarker) {
		this.remarker = remarker;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	

}
