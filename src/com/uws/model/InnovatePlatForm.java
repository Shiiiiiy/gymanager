package com.uws.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.model.BaseModel2;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 创新创业平台model
 * @author 张学彪
 */
public class InnovatePlatForm extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = -3094826980503260501L;

	/**
	 * 平台名称
	 */
	private String platName;
	
	/**
	 * 平台类型
	 */
	private String platType;
	
	/**
	 * 投资主体
	 */
	private String investor;
	
	/**
	 * 平台级别
	 */
	private String platLevel;
	
	/**
	 * 平台成立时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date platTime;
	
	/**
	 * 启用状态
	 */
	private int status;

	public String getPlatName() {
		return platName;
	}

	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public String getInvestor() {
		return investor;
	}

	public void setInvestor(String investor) {
		this.investor = investor;
	}

	public String getPlatLevel() {
		return platLevel;
	}

	public void setPlatLevel(String platLevel) {
		this.platLevel = platLevel;
	}

	public Date getPlatTime() {
		return platTime;
	}

	public void setPlatTime(Date platTime) {
		this.platTime = platTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
