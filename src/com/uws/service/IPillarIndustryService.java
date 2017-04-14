package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.Company;
import com.uws.model.IndustryGarden;


public interface IPillarIndustryService {

	/**
	 * 获取需要初始化的产业的id集合
	 * @return
	 */
	public List<String> getIndustryInitList();

	
	/**
	 * 初始化 产业数据
	 * @param ids
	 */
	public void initIndustry(String ids);


	/**
	 * 保存 产业
	 * @param list
	 */
	public void saveIndustryList(List<IndustryGarden> list);
	
	
	/**
	 * 查询资源文件 列表
	 * @param moduleCode  模块类型
	 * @param parentCode  上级code  （支柱产业 和 产业园区）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryFileInfoList(String moduleCode,String parentCode);
	
	
	/**
	 * 查询  权限下的 企业分页信息
	 * @param param
	 * @param company  查询条件
	 * @param list     权限集合
	 * @return
	 */
	public Page searchCompanyPage(Map<String, Object> param,Company company,List<Map> list);

	
	/**
	 * 查询优质企业id集合
	 * @return
	 */
	public List<String> queryHighQualityCompany();
	
	/**
	 * 查询推荐企业id集合
	 * @return
	 */
	public List<String> queryRecommendCompany();
	
	/**
	 * 从企业列表中移除   可移除多个
	 * @param compIds
	 */
	public void deleteIndustryCompany(String compIds);
	
	/**
	 * 根据传入的 type 的值 推荐或 不推荐 企业
	 * @param compIds 企业id  可以为多个
	 * @param type  YES 推荐  NO 不推荐
	 */
	public void changeCompanyHq(String compIds,String changeType,String value);
	
	
	/**
	 * 设置 企业 产业分类类型（ 允许设置多个企业  多种分类）
	 * @param compIds
	 * @param industryIds
	 */
	public void setCompanyIndustryClass(String compIds,String industryIds);
	
	
	public void addCompany(String compIds);
	
}
