package com.uws.model;

import com.base.model.BaseModel2;

/**
 * 
 * @ClassName: IndustryGarden 
 * @Description:  产业园区实体类
 * @author: shiyan 
 * @date: 2017-3-6 上午11:37:59
 */
public class IndustryGarden extends BaseModel2{
	
	/**
	 * 简介
	 */
	private String introduce;
	
	/**
	 * 产业类型      INDUSTRY_A——工业产品，INDUSTRY_B——生产服务；
	 */
	private String industryType;
	
	/**
	 * 产业名称 
	 */
	private String name;
	
	/**
	 * 备注
	 */
	private String comments;
	
	/**
	 * 简介图片
	 */
	private String introducePic;
	
	/**
	 * logo图片
	 */
	private String logoImage;
	
	/**
	 * 产业或园区:INDUSTRY——产业；GARDEN——园区；
	 */
	private String igType;
	
	/**
	 * 英文名称
	 */
	private String translation;
	
	/**
	 * 启用禁用状态
	 */
	private Long status;

	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIntroducePic() {
		return introducePic;
	}

	public void setIntroducePic(String introducePic) {
		this.introducePic = introducePic;
	}

	public String getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(String logoImage) {
		this.logoImage = logoImage;
	}

	public String getIgType() {
		return igType;
	}

	public void setIgType(String igType) {
		this.igType = igType;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
}
