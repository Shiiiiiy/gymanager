package com.uws.model;

import java.io.Serializable;

import com.base.model.BaseModel2;
/**
 * 关系model
 * @author 张学彪
 */
public class CompanyProp extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = 2701001079676581364L;

	/**
	 * 企业信息ID
	 */
	private String compId;
	
	/**
	 * 企业信息扩展属性值
	 */
	private String propValue;
	
	/**
	 * 企业信息扩展属性类型
	 */
	private String propType;
	
	/**
	 * 启用状态
	 */
	private int status;

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
