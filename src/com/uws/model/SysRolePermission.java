package com.uws.model;

import com.base.model.BaseModel;

public class SysRolePermission extends BaseModel{

	private static final long serialVersionUID = -1840819284070101678L;


	private Long roleId;
	
	private String type;
	
	private String value;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
