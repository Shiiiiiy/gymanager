package com.uws.model;


import com.base.model.BaseModel;


/**
 * 系统菜单 Model
 * @author wangjun
 *
 */
public class SysMenu extends BaseModel{

	private static final long serialVersionUID = 7489353750385396963L;

	/**父id*/
	private Long p_id;
	/**菜单编码*/
	private String menu_code;
	/**菜单名称*/
	private String title;
	/**路径*/
	private String url;
	/**备注*/
	private String remark;
	/**STATUS*/
	private Long status;

 
	
	public Long getP_id() {
		return p_id;
	}
	public void setP_id(Long p_id) {
		this.p_id = p_id;
	}
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}



	
	

}
