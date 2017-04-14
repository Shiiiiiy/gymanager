package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.Company;
import com.uws.model.NewsAll;
import com.uws.model.Product;

/**
 * 产业服务service
 * @author: wangjun 
 */
public interface IServiceService {
	/**
	 * 产业服务下  工业产品/生产服务的分类
	 * @param industry_type 产业类型
	 * @return
	 */
	public List<Map> getIndustryList(String industry_type);
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
	 * @parammoduleType
	 * @parentCode
	 * @return Page
	 */
	public Page queryProductServiceNews(Map<String, Object> param, NewsAll news,String moduleType,String parentCode, String userid);
	/**
	 * 删除 一个 或多个新闻
	 * @param ids 新闻ids
	 * @return
	 */
	public void deleteNews(String ids);
	/**
	 * 新增或更新 新闻
	 * @param project
	 */
	public void saveOrUpdateNews(NewsAll news);
	/**
	 *批量取消  企业列表的关系
	 *@param propIds 企业ids
	 * */
	public void cancelCompany(String propIds);
	/**
	 * 批量设置选择的企业为推荐企业或不推荐企业（包含单个操作）
	 * propIds IDs
	 * propType 关系类型（是否推荐）
	 */
	public void setOrCancelRecommendCompany(String propIds, String propType);
	/**
	 * 查询产品服务下 的企业列表
	 * param : 分页参数
	 * company ： 查询条件
	 * @param uid 
	 * @param parentCode 
	 * */
	public Page queryCompanyListByModule(Map<String, Object> param,	Company company, Product product, String mCode, String parentCode, String uid);
	/**
	 *批量 添加企业分类属性
	 *@param propIds 企业ids
	 *@param propType 企业分类属性ids
	 * */
	public void addClassCompany(String propIds, String propType);
	/**
	 *从企业库  向产品服务 批量 添加企业
	 *@param  companyIds 企业ids
	 * */
	public void saveServiceCompany(String companyIds);
	/**
	 * 查询 产品服务 轮播图FILE_SLIDER 和 统计图FILE_STATISTICS
	 * @param moduleCode 文件表moduleCode
	 * @param parentCode 文件表parentCode
	 * */
	public List<Map> queryFileInfoList(String moduleCode, String parentCode);
	/**
	 * 通过id 获取 Industry名称
	 * @param id
	 * */
	public String getIndustryNameByID(String id);
	
}
