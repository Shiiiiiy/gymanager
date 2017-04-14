package com.uws.dao;


import java.util.List;
import java.util.Map;

import com.base.dao.IBaseDAO;
import com.base.dao.Page;
import com.uws.model.Company;
import com.uws.model.NewsAll;
import com.uws.model.Product;
/**
 * 产业服务 dao层 
 * @author: wangjun 
 */
public interface IServiceDao extends IBaseDAO{
	/**
	 * 查询字典分类详细信息
	 * @param ig_type产业或园区类型  industry_type 产业类型
	 * @return
	 */
	public List<Map> getIndustryList(String ig_type, String industry_type);
	/**
	 * 获取产品服务新闻
	 * @param param 分页参数
	 * @param news 新闻类实体
	 * @param userid 
	 * @return Page
	 */
	public Page queryProductServiceNews(Map<String, Object> param, NewsAll news,String moduleType,String parentCode, String userid);
	/**
	 * 查询类型 产业服务 的企业列表
	 * @param param : 分页参数
	 * @param company ： 查询条件
	 * */
	public Page queryCompanyListByModule(Map<String, Object> param,	Company company, String mCode, String parentCode, String uid);
	/**
	 * 批量设置选择的企业为推荐企业或不推荐企业（包含单个操作）
	 * @param idList IDs
	 * @param propType 关系类型（是否推荐）
	 */
	public void setOrCancelRecommendCompany(List<String> idList, String propType);
	/**
	 *批量取消  企业列表的关系
	 *@param propIds 企业ids
	 * */
	public void cancelCompany(List<String> idList);
	 /**
	 * 批量 添加企业分类属性
	 *@param propIds 企业ids
	 *@param propType 企业分类属性ids
	 * */
	public void addClassCompany(List<String> idList, List<String> typeList);
	/**
	 *从企业库  向产品服务 批量 添加企业
	 *@param  companyIds 企业ids
	 * */
	public void saveServiceCompany(List<String> idList);
	/**
	 * 查询 产品服务 轮播图FILE_SLIDER 和 统计图FILE_STATISTICS
	 * @param moduleCode 文件表moduleCode
	 * @param parentCode 文件表parentCode
	 * */
	public List<Map> queryFileInfoList(String moduleCode, String parentCode);
	/**
	 * 产业服务下  工业产品/生产服务的分类
	 * @param industry_type 产业类型
	 * @return
	 */
	public List<Map> getIndustryList(String industry_type);
	/**
	 * 通过id 获取 Industry名称
	 * @param id
	 * */
	public String getIndustryNameByID(String id);
}
