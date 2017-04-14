package com.uws.model;

import java.io.Serializable;
import java.util.Date;

import com.base.model.BaseModel2;

public class IndustryGuild extends BaseModel2 implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String belong;
	private String peopleNO;
	private String level;
	private Date startTime;
	private String linkman;
	private String tel;
	private String status;
	
	public IndustryGuild(){}
	
	public IndustryGuild(String id){
		this.setId(id);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPeopleNO() {
		return peopleNO;
	}

	public void setPeopleNO(String peopleNO) {
		this.peopleNO = peopleNO;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
