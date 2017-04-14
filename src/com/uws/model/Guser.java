package com.uws.model;

import java.io.Serializable;

import com.base.model.BaseModel2;
/**
 * 前台用户基础信息model
 * @author hejin
 *
 */
public class Guser extends BaseModel2 implements Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 6745658016206764197L;
	
	
	  //用户名
      private String username;
      //密码
      private String password;
      //用户类型
      private String usertype;
      //状态：禁用/启用
      private Long status;
      
      //用户头像
      private String image;
      
      
      
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
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
      
      
      
  
}
