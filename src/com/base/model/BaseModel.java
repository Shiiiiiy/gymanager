package com.base.model;

import java.io.Serializable;
import java.util.Date;

public class BaseModel implements Serializable {
	
	private static final long serialVersionUID = -8538098488548601111L;
	private long id;
	
	private Date recTime;
	
	private Date updateTime;
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Date getRecTime() {
		return recTime;
	}

	public void setRecTime(Date recTime) {
		this.recTime = recTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
