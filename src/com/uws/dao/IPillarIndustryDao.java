package com.uws.dao;


import java.util.List;
import java.util.Map;

import com.base.dao.IBaseDAO;
import com.base.dao.Page;
import com.uws.model.Company;

public interface IPillarIndustryDao extends IBaseDAO{

	/**
	 * 查询资源文件 列表 
	 * @param moduleCode 模块code
	 * @param parentCode 上级code （产业园区 和 支柱产业）
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryFileInfoList(String moduleCode,String parentCode);	
	
	
	public Page searchCompanyPage(Map<String, Object> param,Company company,List<Map> list);
	
	
	public List<String> queryHighQualityCompany();
	
	public List<String> queryRecommendCompany();
	
	
	public void deleteIndustryCompany(String compIds);
	
	/**
	 * 删除 企业的 优质企业属性
	 * @param compIds  企业id字符串  多个的话 由  , 拼接
	 */
	public void deleteCPbyCompids(String compIds,String propType,boolean type);
	
	
	/**
	 * 新增  企业的 优质企业属性
	 * @param compIds  企业id字符串  多个的话 由  , 拼接
	 */
	public void insertCPbyCompIds(String compIds,boolean type);
	
	public void setCompanyIndustryClass(String compIds, String industryIds);
	
	@SuppressWarnings("rawtypes")
	public List<Map> selectIndustryClass(String compIds);
	
	
	public void addCompay(String compIds);
	
}
