package com.uws.model;

import java.io.Serializable;
import java.util.Date;

import com.base.model.BaseModel2;

/**
 * 创新领军人物表model
 * @author 张学彪
 */
public class ExcellentPerson extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = -4980186178349740306L;

	/**
	 * 人物名字
	 */
	private String psName;
	
	/**
	 * 人物年纪
	 */
	private int psAge;
	
	/**
	 * 启用状态
	 */
	private int status;
	
	/**
	 * 人物性别
	 */
	private String psSex;
	
	/**
	 * 出生年月
	 */
	private Date psBrith;
	
	/**
	 * 头像
	 */
	private String psImage;
	
	/**
	 * 人物简介
	 */
	private String psIntroduce;
	
	/**
	 * 是否是创新人物
	 */
	private String isInnovate;
	
	/**
	 * 是否是领军人物
	 */
	private String isLeader;
	
	/**
	 * 所在单位
	 */
	private String psBelong;
	
	/**
	 * 职务
	 */
	private String psJob;
	
	/**
	 * 联系方式
	 */
	private String psTel;
	
	/**
	 * 头像访问地址
	 */
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public int getPsAge() {
		return psAge;
	}

	public void setPsAge(int psAge) {
		this.psAge = psAge;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPsSex() {
		return psSex;
	}

	public void setPsSex(String psSex) {
		this.psSex = psSex;
	}

	public Date getPsBrith() {
		return psBrith;
	}

	public void setPsBrith(Date psBrith) {
		this.psBrith = psBrith;
	}

	public String getPsImage() {
		return psImage;
	}

	public void setPsImage(String psImage) {
		this.psImage = psImage;
	}

	public String getPsIntroduce() {
		return psIntroduce;
	}

	public void setPsIntroduce(String psIntroduce) {
		this.psIntroduce = psIntroduce;
	}

	public String getIsInnovate() {
		return isInnovate;
	}

	public void setIsInnovate(String isInnovate) {
		this.isInnovate = isInnovate;
	}

	public String getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

	public String getPsBelong() {
		return psBelong;
	}

	public void setPsBelong(String psBelong) {
		this.psBelong = psBelong;
	}

	public String getPsJob() {
		return psJob;
	}

	public void setPsJob(String psJob) {
		this.psJob = psJob;
	}

	public String getPsTel() {
		return psTel;
	}

	public void setPsTel(String psTel) {
		this.psTel = psTel;
	}

}
