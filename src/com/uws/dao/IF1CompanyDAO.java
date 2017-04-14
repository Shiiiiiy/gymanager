package com.uws.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.base.model.BaseModel2;
import com.uws.model.Company;
import com.uws.model.CompanyProp;

/**
 * 企业信息-企业列表Dao
 * @author 张学彪
 */
public interface IF1CompanyDAO {

	/**
	 * 查询类型为F1的企业列表
	 * param : 分页参数
	 * company ： 查询条件
	 * */
	public Page queryCompanyListByModule(Map<String, Object> param, Company company,String moduleCode);
	
	/**
	 * 查询所有已启用的企业信息集合
	 */
	public List<BaseModel2> queryEnableCompany(int status);
	
	/**
	 * 选择企业到企业信息企业列表
	 * companyIds 选择的企业ID集合
	 * moduleCode 所属大类
	 */
	public void chooseCompany(List<String> companyIds,String moduleCode);
	
	/**
	 * 从企业信息企业列表中去掉勾选的企业（包含单个批量操作）
	 * propIds 关系表主键ID
	 */
	public void cancelCompany(List<String> propIds);
	
	/**
	 * 批量设置选择的企业为推荐企业或不推荐企业（包含单个操作）
	 * propIds 关系表主键ID
	 * propType 关系类型（是否推荐）
	 */
	public void setOrCancelRecommendCompany(List<String> propIds,String propType);
	
	/**
	 * 查询企业关系表中是否存在大类为企业信息的数据
	 */
	public boolean hasF1Realation(String moduleCode);
	
	/**
	 * 企业信息企业列表查询时初始化关系表
	 */
	public void initF1Prop(List<String> companyIds,String moduleCode);

	/**
	 * 通过propValue获取关系表中已经存在的数据
	 * @param propType 类型
	 * @param propValue 模块编码
	 * @return
	 */
	public List<Map> queryPropByTypeAndValue(String propValue);
	
	/**
	 * 往关系表插入数据
	 */
	public void saveProp(CompanyProp cp);
}
