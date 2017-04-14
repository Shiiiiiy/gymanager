package com.uws.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.model.BaseModel2;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 技术创新平台model
 * @author 张学彪
 */
public class InnovBusiness extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = -1478175732479014710L;

	/**
	 * 企业名称
	 */
	private String name;
	
	/**
	 * 归属
	 */
	private String belong;
	

	
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
	 * 联系人
	 */
	private String linkman;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 网址
	 */
	private String url;
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

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
