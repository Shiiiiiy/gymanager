package com.uws.model;

import java.io.Serializable;

import com.base.model.BaseModel2;
/**
 * 机构用户详细信息model
 * @author hejin
 *
 */
public class Institution extends BaseModel2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 684482946636890384L;
	
	//用户id
	private String userId;
	
	//用户名
	private String name;
	
	//联系人
	private String linkman;
	
	//电话号码
	private String phone;
	
	//手机号码
	private String tel;
	
	//邮箱
	private String email;
	
	//组织机构代码
	private String orgCode;
	
	//社会信用代码
	private String socialCode;
	
	//联系地址
	private String address;
	
	 //所在省
    private String province;

    //所在市
    private String city;
    
    //所在地区
    private String area;
    
    //状态：启用/禁用
    private Long status;
    
    

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSocialCode() {
		return socialCode;
	}

	public void setSocialCode(String socialCode) {
		this.socialCode = socialCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
    
    
    
	

}
