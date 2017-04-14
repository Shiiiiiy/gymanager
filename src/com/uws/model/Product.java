package com.uws.model;

import java.util.Date;

import com.base.model.BaseModel2;

public class Product extends BaseModel2{
	/**主键*/
	private String id ;
	/**产品所属企业*/
	private String product_comp ;
	/**产品名称*/
	private String product_name ;
	/**批准文号*/
	private String product_num ;
	/**上市时间*/
	private Date product_time ;
	/**启用状态*/
	private Dic status ;
	/**备注*/
	private String comments ;
	/**创建时间*/
	private Date create_time ;
	/**产品功能*/
	private String product_capacity ;
	/**产品分类IDs*/
	private String product_type ;
	/**产品分类名字*/
	private String product_typeName;
	private String statusstr;
	
	private String product_timestr;
	
	private String create_timestr;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct_comp() {
		return product_comp;
	}
	public void setProduct_comp(String product_comp) {
		this.product_comp = product_comp;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_num() {
		return product_num;
	}
	public void setProduct_num(String product_num) {
		this.product_num = product_num;
	}
	public Date getProduct_time() {
		return product_time;
	}
	public void setProduct_time(Date product_time) {
		this.product_time = product_time;
	}
	public Dic getStatus() {
		return status;
	}
	public void setStatus(Dic status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getProduct_capacity() {
		return product_capacity;
	}
	public void setProduct_capacity(String product_capacity) {
		this.product_capacity = product_capacity;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getProduct_typeName() {
		return product_typeName;
	}
	public void setProduct_typeName(String product_typeName) {
		this.product_typeName = product_typeName;
	}
	public String getStatusstr() {
		return statusstr;
	}
	public void setStatusstr(String statusstr) {
		this.statusstr = statusstr;
	}
	public String getProduct_timestr() {
		return product_timestr;
	}
	public void setProduct_timestr(String product_timestr) {
		this.product_timestr = product_timestr;
	}
	public String getCreate_timestr() {
		return create_timestr;
	}
	public void setCreate_timestr(String create_timestr) {
		this.create_timestr = create_timestr;
	}
	
	
}
