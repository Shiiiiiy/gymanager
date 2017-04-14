package com.uws.model;

import java.io.Serializable;

import com.base.model.BaseModel2;
/**
 * 年度新增创新企业名录model
 * @author 张学彪
 */
public class NewCompanies extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = 3675093861719120910L;

	/**
	 * 企业名称
	 */
	private String compName;
	
	/**
	 * 行业类别
	 */
	private String ofIndustry;
	
	/**
	 * 核心技术
	 */
	private String technology;
	
	/**
	 * 投资规模
	 */
	private String scale;
	
	/**
	 * 联系人
	 */
	private String linkman;
	
	/**
	 * 电话
	 */
	private String tel;
	
	/**
	 * 网址
	 */
	private String webSite;
	
	/**
	 * 启用状态
	 */
	private int status;

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getOfIndustry() {
		return ofIndustry;
	}

	public void setOfIndustry(String ofIndustry) {
		this.ofIndustry = ofIndustry;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
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

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
