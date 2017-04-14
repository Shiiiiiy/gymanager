package com.uws.model;


import com.base.model.BaseModel;
/**
 * 系统用户
 * @author wangjun
 */
public class SysUser  extends BaseModel  {

	private static final long serialVersionUID = 776073063334559802L;

	/**帐号*/
	private String user_no;
	/**原始密码*/
	private String pwd;
	/**加密后的密码*/
	private String pwd_id;
	/**用户名*/
	private String name;
	/**手机号*/
	private String tel_no;
	/**邮箱*/
	private String email;
	/**启用状态*/
	private Long status;
	/**用户头像名称*/
	private String image_name;
	/**用户头像地址*/
	private String image_url;
 
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPwd_id() {
		return pwd_id;
	}
	public void setPwd_id(String pwd_id) {
		this.pwd_id = pwd_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	

	
}
