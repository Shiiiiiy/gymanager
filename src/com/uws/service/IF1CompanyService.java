package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.base.model.BaseModel2;
import com.uws.model.Company;
import com.uws.model.Product;

/**
 * 企业信息企业列表Service
 * @author 张学彪
 */
public interface IF1CompanyService {

	/**
	 * 查询类型为F1的企业列表
	 * param : 分页参数
	 * company ： 查询条件
	 * */
	public Page queryCompanyListByModule(Map<String, Object> param, Company company, Product product,String moduleCode);
	
	/**
	 * 选择企业到企业信息企业列表
	 * companyIds 选择的企业ID集合
	 * moduleCode 所属大类
	 */
	public void chooseCompany(String companyIds,String moduleCode);
	
	/**
	 * 从企业信息企业列表中去掉勾选的企业（包含单个批量操作）
	 * propIds 关系表主键ID
	 */
	public void cancelCompany(String propIds);
	
	/**
	 * 批量设置选择的企业为推荐企业或不推荐企业（包含单个操作）
	 * propIds 关系表主键ID
	 * propType 关系类型（是否推荐）
	 */
	public void setOrCancelRecommendCompany(String propIds,String propType);
	
	/**
	 * 查询所有已启用的企业信息集合
	 */
	public List<Company> queryEnableCompany(int status);
	
	/**
	 * 查询企业关系表中是否存在大类为企业信息的数据
	 */
	public boolean hasF1Realation(String moduleCode);
	
	/**
	 * 企业信息企业列表查询时初始化关系表
	 */
	public void initF1Prop(List<Company> companyIds,String moduleCode);

	/**
	 * 得到选取的企业中关系表里面不存在的IDs
	 */
	public List<String> getNoExsitIds(String companyIds, String propValue);
	
	/**
	 * 往关系表插入数据
	 */
	public void saveProp(List<String> ids, String propType, String propValue);
}
