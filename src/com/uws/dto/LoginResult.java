package com.uws.dto;

/**
 * 数据传输对象,用于与前端通信
 * @author zhouchang
 *
 */
public class LoginResult {

	private boolean isSuccess; // 是否成功
	
	private boolean isValid; //是否是禁用状态
	
	private boolean invalidCode; // 验证码无效
	
	private boolean invalidNameOrPwd; //用户名或密码错误
	
	private boolean showCode; //前端是否显示 验证码

	public LoginResult(boolean isSuccess, boolean isValid, boolean invalidCode,
			boolean invalidNameOrPwd, boolean showCode) {
		super();
		this.isSuccess = isSuccess;
		this.isValid = isValid;
		this.invalidCode = invalidCode;
		this.invalidNameOrPwd = invalidNameOrPwd;
		this.showCode = showCode;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isInvalidCode() {
		return invalidCode;
	}

	public void setInvalidCode(boolean invalidCode) {
		this.invalidCode = invalidCode;
	}

	public boolean isInvalidNameOrPwd() {
		return invalidNameOrPwd;
	}

	public void setInvalidNameOrPwd(boolean invalidNameOrPwd) {
		this.invalidNameOrPwd = invalidNameOrPwd;
	}

	public boolean isShowCode() {
		return showCode;
	}

	public void setShowCode(boolean showCode) {
		this.showCode = showCode;
	}
	
	
}
