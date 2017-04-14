package com.uws.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.base.model.BaseModel2;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @ClassName: GardenProject  园区项目实体类
 * @author: shiyan
 * @date: 2017-3-8 上午10:08:45
 */
@Repository
public class GardenProject extends BaseModel2{

	/**
	 * 所属园区
	 */
	private String ofGarden;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 项目类型 BASE——基础建设施建设项目；INDUSTRY——园区产业项目；
	 */
	private String projectType;
	
	/**
	 * 备注
	 */
	private String comments;
	
	/**
	 * 项目状态:PROSTATUS_A——拟建;PROSTATUS_B——在建;PROSTATUS_C——竣工；
	 */ 
	private String projectStatus;
	
	/**
	 * 项目内容
	 */
	private String projectContent;
	
	/**
	 * 项目启动时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date projectTime;
	
	/**
	 * 项目完工时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date projectOvertime;
	
	/**
	 * 项目投资金额（万元）
	 */
	private String projectInvest;

	public String getOfGarden() {
		return ofGarden;
	}

	public void setOfGarden(String ofGarden) {
		this.ofGarden = ofGarden;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	public Date getProjectTime() {
		return projectTime;
	}

	public void setProjectTime(Date projectTime) {
		this.projectTime = projectTime;
	}

	public Date getProjectOvertime() {
		return projectOvertime;
	}

	public void setProjectOvertime(Date projectOvertime) {
		this.projectOvertime = projectOvertime;
	}

	public String getProjectInvest() {
		return projectInvest;
	}

	public void setProjectInvest(String projectInvest) {
		this.projectInvest = projectInvest;
	}
	
	
	
}
