package com.uws.model;

import java.io.Serializable;

import com.base.model.BaseModel2;
/**
 * 前台个人用户详细信息model
 * @author hejin
 *
 */
public class Person extends BaseModel2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9076026683196268882L;
	
	
	//用户id(G_USER表)
    private String userId;
    //用户名
    private String name;
    //用户性别
    private String gender;
    
    //手机号码
    private String tel;
    
    //电话号码
    private String phone;
    
    //邮箱
    private String email;
    
    //头像
    private String image;
    
    //证件类型
    private String cardtype;
    
    //证件号码
    private String cardnum;
    
    //所在省
    private String province;

    //所在市
    private String city;
    
    //所在地区
    private String area;
    
    //联系地址
    private String linkaddress;
    
    
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
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

	public String getLinkaddress() {
		return linkaddress;
	}

	public void setLinkaddress(String linkaddress) {
		this.linkaddress = linkaddress;
	}
    
    
    
    
    
}
