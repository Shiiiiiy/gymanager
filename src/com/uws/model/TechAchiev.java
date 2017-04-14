package com.uws.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.model.BaseModel2;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 技术创新成果model
 * @author 张学彪
 */
public class TechAchiev extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = -1680345024694081487L;

	/**
	 * 成果名称
	 */
	private String name;
	
	/**
	 * 归属
	 */
	private String belong;
	
	/**
	 * 认证等级
	 */
	private String level;
	
	/**
	 * 认证时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date certTime;
	
	/**
	 * 有效期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date effectTime;
	
	/**
	 * 发明专利/其它技术成果
	 */
	private String type;
	
	/**
	 * 启用状态
	 */
	private int status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getCertTime() {
		return certTime;
	}

	public void setCertTime(Date certTime) {
		this.certTime = certTime;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
