package com.uws.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.model.BaseModel2;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 年度新增上市企业名录model
 * @author 张学彪
 */
public class MarketCompanies extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = -924390405504977887L;

	/**
	 * 企业名称
	 */
	private String compName;
	
	/**
	 * 行业类别
	 */
	private String ofIndustry;
	
	/**
	 * 交易所
	 */
	private String bourse;
	
	/**
	 * 上市板块（新三板/主板）
	 */
	private String market;
	
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
	
	/**
	 * 上市时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date marketTime;

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

	public String getBourse() {
		return bourse;
	}

	public void setBourse(String bourse) {
		this.bourse = bourse;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
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

	public Date getMarketTime() {
		return marketTime;
	}

	public void setMarketTime(Date marketTime) {
		this.marketTime = marketTime;
	}

}
