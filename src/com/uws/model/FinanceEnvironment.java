package com.uws.model;

import java.io.Serializable;

import com.base.model.BaseModel2;
/**
 * 产业金融环境model
 * @author 张学彪
 */
public class FinanceEnvironment extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = 2648152798433806621L;

	/**
	 * 机构名称
	 */
	private String financeName;
	
	/**
	 * 本地是否设立分支机构
	 */
	private String isBranch;
	
	/**
	 * 本地累计投资规模
	 */
	private String scale;
	
	/**
	 * 电话
	 */
	private String tel;
	
	/**
	 * 联系人
	 */
	private String linkman;
	
	/**
	 * 启用状态
	 */
	private int status;

	public String getFinanceName() {
		return financeName;
	}

	public void setFinanceName(String financeName) {
		this.financeName = financeName;
	}

	public String getIsBranch() {
		return isBranch;
	}

	public void setIsBranch(String isBranch) {
		this.isBranch = isBranch;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
