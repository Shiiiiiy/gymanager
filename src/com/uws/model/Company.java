package com.uws.model;

import java.util.Date;

import com.base.model.BaseModel2;

public class Company extends BaseModel2 {

	
	
	/**企业信息ID*/
	private String id ;
	/**企业名称*/
	private String cp_name;
	/**所属行业 --属性*/
	private Dic cp_belong;
	/**企业性质 -- 属性*/
	private String cp_type;
	/**经营产品描述*/
	private String cp_product;
	/**所在地区描述*/
	private String cp_location;
	/**官网地址*/
	private String cp_address;
	/**联系方式*/
	private String cp_phone;
	/**缩略图地址*/
	private String cp_view;
	/**官方LOGO*/
	private String cp_logo;
	/**企业简介*/
	private String cp_abstract;
	/**备注*/
	private String comments;
	/**创建时间*/
	private Date create_time;
	/**修改时间*/
	private Date update_time;
	/**法定代表人*/
	private String cp_man;
	/**市场范围*/
	private Dic cp_market;
	/**营销模式*/
	private Dic cp_model;
	/**客户类别*/
	private Dic cp_customertype;
	/**行政区划*/
	private Dic cp_belongpart;
	/**联系人*/
	private String cp_linkman;
	/**业绩规模*/
	private String cp_scale;
	/**公司创立日期*/
	private Date cp_time;
	/**联系人手机号码*/
	private String cp_linktel;
	/**联系人邮箱*/
	private String cp_linkemail;
	/**组织机构代码*/
	private String cp_orgcode;
	/**社会信用代码*/
	private String cp_socialcode;
	/**所在省*/
	private Dic cp_province;
	/**所在市*/
	private Dic cp_city;
	/**所在县/区*/
	private Dic cp_area;
	/**用户id*/
	private SysUser userid;
	/**是否展示  1作为企业展示  0不展示*/
	private String is_show;
	
	private String cp_belongstr;
	private String cp_marketstr;
	private String cp_modelstr;
	private String cp_customertypestr;
	private String cp_belongpartstr;
	private String cp_provincestr;
	private String cp_citystr;
	private String cp_areastr;
	private String useridstr;
	private String cp_timestr;
	/**
	 * 状态 2  启用 3 禁用
	 */
	private String status;
	
	/**
	 * 是否推荐 企业信息-企业列表用
	 */
	private String propg;
	
	public String getPropg() {
		return propg;
	}
	public void setPropg(String propg) {
		this.propg = propg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCp_name() {
		return cp_name;
	}
	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}
	public Dic getCp_belong() {
		return cp_belong;
	}
	public void setCp_belong(Dic cp_belong) {
		this.cp_belong = cp_belong;
	}
	public String getCp_type() {
		return cp_type;
	}
	public void setCp_type(String cp_type) {
		this.cp_type = cp_type;
	}
	public String getCp_product() {
		return cp_product;
	}
	public void setCp_product(String cp_product) {
		this.cp_product = cp_product;
	}
	public String getCp_location() {
		return cp_location;
	}
	public void setCp_location(String cp_location) {
		this.cp_location = cp_location;
	}
	public String getCp_address() {
		return cp_address;
	}
	public void setCp_address(String cp_address) {
		this.cp_address = cp_address;
	}
	public String getCp_phone() {
		return cp_phone;
	}
	public void setCp_phone(String cp_phone) {
		this.cp_phone = cp_phone;
	}
	public String getCp_view() {
		return cp_view;
	}
	public void setCp_view(String cp_view) {
		this.cp_view = cp_view;
	}
	public String getCp_logo() {
		return cp_logo;
	}
	public void setCp_logo(String cp_logo) {
		this.cp_logo = cp_logo;
	}
	public String getCp_abstract() {
		return cp_abstract;
	}
	public void setCp_abstract(String cp_abstract) {
		this.cp_abstract = cp_abstract;
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
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getCp_man() {
		return cp_man;
	}
	public void setCp_man(String cp_man) {
		this.cp_man = cp_man;
	}
	public Dic getCp_market() {
		return cp_market;
	}
	public void setCp_market(Dic cp_market) {
		this.cp_market = cp_market;
	}
	public Dic getCp_model() {
		return cp_model;
	}
	public void setCp_model(Dic cp_model) {
		this.cp_model = cp_model;
	}
	public Dic getCp_customertype() {
		return cp_customertype;
	}
	public void setCp_customertype(Dic cp_customertype) {
		this.cp_customertype = cp_customertype;
	}
	public Dic getCp_belongpart() {
		return cp_belongpart;
	}
	public void setCp_belongpart(Dic cp_belongpart) {
		this.cp_belongpart = cp_belongpart;
	}
	public String getCp_linkman() {
		return cp_linkman;
	}
	public void setCp_linkman(String cp_linkman) {
		this.cp_linkman = cp_linkman;
	}
	public String getCp_scale() {
		return cp_scale;
	}
	public void setCp_scale(String cp_scale) {
		this.cp_scale = cp_scale;
	}
	public Date getCp_time() {
		return cp_time;
	}
	public void setCp_time(Date cp_time) {
		this.cp_time = cp_time;
	}
	public String getCp_linktel() {
		return cp_linktel;
	}
	public void setCp_linktel(String cp_linktel) {
		this.cp_linktel = cp_linktel;
	}
	public String getCp_linkemail() {
		return cp_linkemail;
	}
	public void setCp_linkemail(String cp_linkemail) {
		this.cp_linkemail = cp_linkemail;
	}
	public String getCp_orgcode() {
		return cp_orgcode;
	}
	public void setCp_orgcode(String cp_orgcode) {
		this.cp_orgcode = cp_orgcode;
	}
	public String getCp_socialcode() {
		return cp_socialcode;
	}
	public void setCp_socialcode(String cp_socialcode) {
		this.cp_socialcode = cp_socialcode;
	}
	public Dic getCp_province() {
		return cp_province;
	}
	public void setCp_province(Dic cp_province) {
		this.cp_province = cp_province;
	}
	public Dic getCp_city() {
		return cp_city;
	}
	public void setCp_city(Dic cp_city) {
		this.cp_city = cp_city;
	}
	public Dic getCp_area() {
		return cp_area;
	}
	public void setCp_area(Dic cp_area) {
		this.cp_area = cp_area;
	}
	public SysUser getUserid() {
		return userid;
	}
	public void setUserid(SysUser userid) {
		this.userid = userid;
	}
	public String getCp_belongstr() {
		return cp_belongstr;
	}
	public void setCp_belongstr(String cp_belongstr) {
		this.cp_belongstr = cp_belongstr;
	}
	public String getCp_marketstr() {
		return cp_marketstr;
	}
	public void setCp_marketstr(String cp_marketstr) {
		this.cp_marketstr = cp_marketstr;
	}
	public String getCp_modelstr() {
		return cp_modelstr;
	}
	public void setCp_modelstr(String cp_modelstr) {
		this.cp_modelstr = cp_modelstr;
	}
	public String getCp_customertypestr() {
		return cp_customertypestr;
	}
	public void setCp_customertypestr(String cp_customertypestr) {
		this.cp_customertypestr = cp_customertypestr;
	}
	public String getCp_belongpartstr() {
		return cp_belongpartstr;
	}
	public void setCp_belongpartstr(String cp_belongpartstr) {
		this.cp_belongpartstr = cp_belongpartstr;
	}
	public String getCp_provincestr() {
		return cp_provincestr;
	}
	public void setCp_provincestr(String cp_provincestr) {
		this.cp_provincestr = cp_provincestr;
	}
	public String getCp_citystr() {
		return cp_citystr;
	}
	public void setCp_citystr(String cp_citystr) {
		this.cp_citystr = cp_citystr;
	}
	public String getCp_areastr() {
		return cp_areastr;
	}
	public void setCp_areastr(String cp_areastr) {
		this.cp_areastr = cp_areastr;
	}
	public String getUseridstr() {
		return useridstr;
	}
	public void setUseridstr(String useridstr) {
		this.useridstr = useridstr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCp_timestr() {
		return cp_timestr;
	}
	public void setCp_timestr(String cp_timestr) {
		this.cp_timestr = cp_timestr;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	
	
}
