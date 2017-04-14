package com.uws.util;

import java.io.File;
import java.util.HashMap;

public class Constants {

	public static String LOGIN_USER = "LoginUser"; //登录用户
	public final static int PAGE_SIZE = 10; //分页 一页显示的行数
	
	public static int VERIFY_CODE_TYPE = 2; //验证码类型: 0->大写字母,1->小写字母,2->数字,其他->汉字
	public static String UPLOAD_FILE_SAVE_PATH = File.separator + "uploadFile"; //上传文件保存的位置
	public static String VERIFY_CODE = "VerifyCode";//系统登陆验证码
	
	/**
	 * 产业园区类型————园区
	 */
	public final static String GARDEN_TYPE = "GARDEN";
	
	/**
	 * 产业园区类型————产业
	 */
	public final static String INDUSTRY_TYPE = "INDUSTRY";
	/**
	 * 产业服务————工业产品
	 */
	public final static String INDUSTRY_A = "INDUSTRY_A";
	/**
	 * 产业服务————生产服务
	 */
	public final static String INDUSTRY_B = "INDUSTRY_B";
	
	/**
	 * 支柱产业  标识code
	 */
	public final static String INDUSTRY_D = "INDUSTRY_D";
	
	/**
	 * 新闻类型————园区动态
	 */
	public final static String GARDEN_A = "GARDEN_A";
	
	/**
	 * 新闻类型————园区政策
	 */
	public final static String GARDEN_B = "GARDEN_B";
	
	
	//区分新闻大类的参数常量
	public static String NEWS_ALL_MODULE = "moduleCode";
	

	/**前台用户类型——个人用户*/
	public static String USERTYPE_PERSON = "PERSON";
	/**前台用户类型——机构用户*/
	public static String USERTYPE_INSTITUTION = "INSTITUTION";
	/**前台用户类型——企业用户*/
	public static String USERTYPE_COMPANY = "COMPANY";
	
	

	//区分新闻二级分类参数常量
	public static String NEWS_ALL_PARENT_CODE = "parentCode";
	
	//区分新闻三级分类参数常量
	public static String NEWS_ALL_MODULE_TYPE = "moduleType";

	//系统各大类编码
	public static enum MODULE_CODE{
		//企业风采
		NEWS_A,
		//产业服务
		NEWS_B,
		//技术创新动态
		NEWS_C,
		//创业创新动态
		NEWS_D,
		//政策动态
		NEWS_E,
		//国家政策
		NEWS_F,
		//省级政策
		NEWS_G,
		//本市政策
		NEWS_H,
		//政策解读
		NEWS_I,
		//产业成功案例
		NEWS_K,
		//产业园区
		NEWS_L,
		//支柱产业
		NEWS_M,
		//首页
		NEWS_N,
		//线下培训
		NEWS_P,
		//职位发布
		NEWS_Q,
		//简历投递
		NEWS_R
	}
	/**企业关系表 常量字段*/
	public static  enum  CP_PROP{
		//是否是创新创业示范企业   创新创业中 方形展示图片
		PROP_A,
		//**是否是产业优质企业   支柱产业中 方形展示图片
		PROP_C,
		//**是否是推荐企业   企业信息-产品服务-支柱产业  logo轮播
		PROP_G,
		//**绑定企业 与产品服务 子版块的关系
		PROP_E,
		//**绑定企业 与支柱产业 子版块的关系
		PROP_F
	}
	/**
	 * 初始化十三个产业园区 + 一个总的贵阳科技园
	 */
	public static HashMap<String,String> GARDEN_MAP = new HashMap<String, String>();
	/**
	 * 初始化9个支柱产业 
	 */
	public static HashMap<String,String> INDUSTRY_MAP = new HashMap<String, String>();
	static{
		GARDEN_MAP.put("IG_C000","贵阳科技园");
		GARDEN_MAP.put("IG_C001","高新区产业园");
		GARDEN_MAP.put("IG_C002","经开区产业园");
		GARDEN_MAP.put("IG_C003","综保区产业园");
		GARDEN_MAP.put("IG_C004","双龙航空港经济区");
		GARDEN_MAP.put("IG_C005","乌当区产业园");
		GARDEN_MAP.put("IG_C006","观山湖区产业园");
		GARDEN_MAP.put("IG_C007","云岩区产业园");
		GARDEN_MAP.put("IG_C008","白云区产业园");
		GARDEN_MAP.put("IG_C009","花溪区产业园");
		GARDEN_MAP.put("IG_C010","息烽县产业园");
		GARDEN_MAP.put("IG_C011","清镇市产业园");
		GARDEN_MAP.put("IG_C012","修文县产业园");
		GARDEN_MAP.put("IG_C013","开阳县产业园");
		
		INDUSTRY_MAP.put("IG_D001","健康医药");
		INDUSTRY_MAP.put("IG_D002","装备制造");
		INDUSTRY_MAP.put("IG_D003","军民融合");
		INDUSTRY_MAP.put("IG_D004","电子信息");
		INDUSTRY_MAP.put("IG_D005","磷煤化工");
		INDUSTRY_MAP.put("IG_D006","铝及铝加工");
		INDUSTRY_MAP.put("IG_D007","新能源新材料");
		INDUSTRY_MAP.put("IG_D008","特色食品");
		INDUSTRY_MAP.put("IG_D009","生产性服务业");
	};
	
	
	//大模块企业信息固定code
	public static final String F1 = "F1";
	//大模块产品服务固定code
	public static final String F2 = "F2";
	//大模块支柱产业固定code
	public static final String F3 = "F3";
	
	/**
	 * 图片文件信息表字段中MODULE_CODE对应的企业信息编码
	 */
	public static final String MODULE_A = "MODULE_A";
	/**
	 * 图片文件信息表字段中MODULE_CODE对应的支柱产业编码
	 */
	public static final String MODULE_D = "MODULE_D";
	/**
	 * 图片文件信息表字段中MODULE_CODE对应的支柱产业编码
	 */
	public static final String MODULE_E = "MODULE_E";
	
	/**
	 * 图片文件信息表字段中MODULE_CODE对应的产业园区编码
	 */
	public static final String MODULE_F = "MODULE_F";
	
	/**
	 * 图片文件信息表字段中MODULE_CODE对应的技术创新编码
	 */
	public static final String MODULE_G = "MODULE_G";
	
	/**
	 * 图片文件信息表字段中MODULE_CODE对应的创新创业编码
	 */
	public static final String MODULE_J = "MODULE_J";
	
	/**
	 * 图片文件信息表字段中MODULE_CODE对应的创新创业编码
	 */
	public static final String MODULE_H = "MODULE_H";
	
	/**
	 * 图片文件信息表字段中FILE_TYPE对应的企业信息下面FILE_SLIDER——轮播图
	 */
	public static final String FILE_SLIDER = "FILE_SLIDER";
	
	/**
	 * 图片文件信息表字段中FILE_TYPE对应的企业信息下面FILE_STATISTICS——统计图
	 */
	public static final String FILE_STATISTICS = "FILE_STATISTICS";
	
	/**
	 * 图片文件信息表字段中FILE_TYPE对应的企业信息下面FILE_RESULT——创新成果图
	 */
	public static final String FILE_RESULT = "FILE_RESULT";
	
	/**
	 * 图片文件信息表字段中FILE_TYPE对应的企业信息下面FILE_STAGE——创新平台图
	 */
	public static final String FILE_STAGE = "FILE_STAGE";
	
	/**
	 * 图片文件信息表字段中FILE_TYPE对应的技术创新 产学研合作配图
	 */
	public static final String FILE_COOPERA = "FILE_COOPERA";
	/**
	 * 图片文件信息表字段中FILE_TYPE对应的企业信息下面LOGO_A——创新平台图
	 */
	public static final String LOGO_A = "LOGO_A";
	
	/**
	 * 图片文件信息表字段中FILE_TYPE对应的企业信息下面LOGO_B——创新平台图
	 */
	public static final String LOGO_B = "LOGO_B";
	
	/**
	 * 图片文件信息表字段中FILE_TYPE对应的企业信息 基础信息的图片
	 */
	public static final String FILE_PRO = "FILE_PRO";
	
	/**
	 * 图片文件信息表字段中FILE_TYPE对应的龙头企业图片
	 */
	public static final String COMP_A = "COMP_A";
	
	/**
	 * 图片文件信息表字段中FILE_TYPE对应的经济运行分析报告
	 */
	public static final String FILE_A = "FILE_A";
	
	/**
	 * 文件上传固定路径
	 */
//	public static final String FILE_PATH = "/gymanager/uploadFile/images";
	public static final String FILE_PATH = "/gykjy/plugins/images/allImages";
	
	/**
	 * 角色权限控制 ————园区
	 */
	public static final String ROLE_PER_GARDEN = "GARDEN";
	
	/**
	 * 角色权限控制 ————支柱产业
	 */
	public static final String ROLE_PER_INDUSTRY = "INDUSTRY";
	/**
	 * 角色权限控制 ————工业产品
	 */
	public static final String ROLE_PER_INDUSTRY_A = "INDUSTRY_A";
	
	/**
	 * 角色权限控制 ————生产服务
	 */
	public static final String ROLE_PER_INDUSTRY_B = "INDUSTRY_B";
	/**
	 * 角色权限控制 ————产品服务主页
	 */
	public static final String ROLE_PER_INDUSTRY_M = "INDUSTRY_ABMAIN";
	
}
