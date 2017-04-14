package com.uws.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户管理传参实体
 * @author hejin
 *
 */
public class UserManageParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3736504317369585641L;
	//id
	private String id;
	
	//个人用户id
	private String personId;
	
	//企业信息id
	private String companyId;
	
	//机构信息id
	private String institutionId;
	
	/**----------------------G_USER------------------**/
	//用户名
	private String username;
	
	//用户密码
	private String password;
	
	//用户类型
	private String usertype;
	
	//个人用户头像
    private String image;
    
    //备注
    private String comment;
	
	/**----------------------G_USER------------------**/
	
	
	/**----------------------PERSON------------------**/
	
	//个人用户真实姓名
	private String name;
	
	//个人用户性别
	private String gender;
	
	//个人用户手机
	private String tel;
	
	//个人用户座机
    private String phone;
		
    //个人用户联系邮箱
    private String email;
    
    //个人用户证件类型
    private String cardtype;
    
    
    //个人用户证件号码
    private String cardnum;
    
    //个人用户所在省
    private String addProvince;
    
    //个人用户所在市
    private String addCity;
		
    //个人用户所在县区
    private String addArea;	
		
    //个人用户通讯地址
    private String linkAddress;
    
    
    
    
    /**----------------------PERSON------------------**/
    
    /**----------------------COMPANY------------------**/
    //企业名称
    private String cp_name;
    
    //企业联系人
    private String cpLink;
    
    //企业联系人邮箱
    private String cpLinkEmail;
    
    //企业联系人手机号
    private String cpLinkTel;
    
    //企业座机号
    private String cpPhone;
    
    //企业组织机构代码
    private String cpOrgCode;
    
    //企业社会信用代码
    private String cpSocialCode;
    
    //企业所在地
    private String cpLocation;
    
    //企业注册地（省）
    private String cpProvince;
    
    //企业注册地（市）
    private String cpCity;
    
    //企业注册地（区）
    private String cpArea;
    
    
    /**----------------------COMPANY------------------**/
    
    /**----------------------INSTITUTION------------------**/
    //机构名称
    private String nameIn;
    
    //机构联系人
    private String linkMan;
    
    //机构座机
    private String phoneIn;
    
    //联系人手机号
    private String telIn;
    
    //联系人邮箱
    private String emailIn;
    
    //组织机构代码
    private String orgCode;
    
    //社会信用代码
    private String socialCode;
    
    //所在地
    private String address;
    
    //注册地（省）
    private String province;
    
    //注册地（市）
    private String city;
    
    //注册地（县/区）
    private String area;
    
    
    /**----------------------INSTITUTION------------------**/
	
	//创建时间
	private Date createTime;
	
	//修改时间
	private Date updateTime;
	
	//状态：启用/禁用
	private Long status;

	
	
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getAddProvince() {
		return addProvince;
	}

	public void setAddProvince(String addProvince) {
		this.addProvince = addProvince;
	}

	public String getAddCity() {
		return addCity;
	}

	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}

	public String getAddArea() {
		return addArea;
	}

	public void setAddArea(String addArea) {
		this.addArea = addArea;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public String getCpLink() {
		return cpLink;
	}

	public void setCpLink(String cpLink) {
		this.cpLink = cpLink;
	}

	public String getCpLinkEmail() {
		return cpLinkEmail;
	}

	public void setCpLinkEmail(String cpLinkEmail) {
		this.cpLinkEmail = cpLinkEmail;
	}

	public String getCpLinkTel() {
		return cpLinkTel;
	}

	public void setCpLinkTel(String cpLinkTel) {
		this.cpLinkTel = cpLinkTel;
	}

	public String getCpPhone() {
		return cpPhone;
	}

	public void setCpPhone(String cpPhone) {
		this.cpPhone = cpPhone;
	}

	public String getCpOrgCode() {
		return cpOrgCode;
	}

	public void setCpOrgCode(String cpOrgCode) {
		this.cpOrgCode = cpOrgCode;
	}

	public String getCpSocialCode() {
		return cpSocialCode;
	}

	public void setCpSocialCode(String cpSocialCode) {
		this.cpSocialCode = cpSocialCode;
	}

	public String getCpLocation() {
		return cpLocation;
	}

	public void setCpLocation(String cpLocation) {
		this.cpLocation = cpLocation;
	}

	public String getCpProvince() {
		return cpProvince;
	}

	public void setCpProvince(String cpProvince) {
		this.cpProvince = cpProvince;
	}

	public String getCpCity() {
		return cpCity;
	}

	public void setCpCity(String cpCity) {
		this.cpCity = cpCity;
	}

	public String getCpArea() {
		return cpArea;
	}

	public void setCpArea(String cpArea) {
		this.cpArea = cpArea;
	}

	public String getNameIn() {
		return nameIn;
	}

	public void setNameIn(String nameIn) {
		this.nameIn = nameIn;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getPhoneIn() {
		return phoneIn;
	}

	public void setPhoneIn(String phoneIn) {
		this.phoneIn = phoneIn;
	}

	public String getTelIn() {
		return telIn;
	}

	public void setTelIn(String telIn) {
		this.telIn = telIn;
	}

	public String getEmailIn() {
		return emailIn;
	}

	public void setEmailIn(String emailIn) {
		this.emailIn = emailIn;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	
	
      
}
