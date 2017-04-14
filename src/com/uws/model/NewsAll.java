package com.uws.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.model.BaseModel2;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 资讯/动态/政策表model
 * @author 张学彪
 */
public class NewsAll extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = 5120309834649148961L;

	/**
	 * 标题
	 */
	private String newsTitle;
	
	/**
	 * 内容
	 */
	private String newsContent;
	
	/**
	 * 模块编码
	 * NEWS_A——企业风采；NEWS_B——产业服务；NEWS_C——技术创新动态；NEWS_D——创业创新动态；NEWS_E——政策动态；
	 *  NEWS_F——国家政策；NEWS_G——省级政策；NEWS_H——本市政策；NEWS_I——政策解读；NEWS_K——产业成功案例；
	 *  NEWS_L——产业园区；NEWS_M——支柱产业；NEWS_N——首页；NEWS_P—— 线下培训;NEWS_Q—— 职位发布；NEWS_R—— 简历投递；
	 */
	private String moduleCode;
	
	/**
	 * 父编码(产业服务和产业园区，支柱产业)
	 */
	private String parentCode;
	
	/**
	 * 新闻来源
	 */
	private String newsSource;
	
	/**
	 * 新闻类型(园区动态和园区政策);GARDEN_A——园区动态；GARDEN_B——园区政策；
	 */
	private String moduleType;
	
	/**
	 * 发布时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date newsTime;
	
	/**
	 * 路径（外链）
	 */
	private String url;
	
	/**
	 * 简介
	 */
	private String introduce;
	
	/**
	 * 启用状态
	 */
	private int status;

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getNewsSource() {
		return newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public Date getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(Date newsTime) {
		this.newsTime = newsTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
