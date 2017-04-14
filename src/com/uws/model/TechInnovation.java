package com.uws.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.model.BaseModel2;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 技术创新平台model
 * @author 张学彪
 */
public class TechInnovation extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = -1613043247546267674L;

	/**
	 * 成果名称
	 */
	private String name;
	
	/**
	 * 归属
	 */
	private String belong;
	
	/**
	 * 级别
	 */
	private String level;
	
	/**
	 * 投资规模
	 */
	private String scale;
	
	/**
	 * 创立时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date startTime;
	
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

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
