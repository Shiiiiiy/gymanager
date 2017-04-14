package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.base.model.BaseModel2;
import com.uws.model.FinanceEnvironment;
import com.uws.model.InnovBusiness;
import com.uws.model.InnovatePlatForm;
import com.uws.model.InvestCompanies;
import com.uws.model.MarketCompanies;
import com.uws.model.NewCompanies;
import com.uws.model.TechAchiev;
import com.uws.model.TechInnovation;

/**
 * 技术创新-动态数据Service
 * @author 张学彪
 */
public interface IDynamicDataService {
	/**
	 * 查询技术创新平台列表
	 * param : 分页参数
	 * echInnovation ： 查询条件
	 * */
	public Page queryTechInnovationList(Map<String, Object> param, TechInnovation echInnovation);
	
	/**
	 * 通过ID查询技术创新平台详细信息
	 * @param echInnovationId
	 */
	public TechInnovation queryTechInnovationById(String echInnovationId);
	
	/**
	 * 新增技术创新平台
	 * echInnovation ： 技术创新平台model
	 * */
	public void saveTechInnovation(TechInnovation echInnovation);
	
	/**
	 * 修改技术创新平台
	 * echInnovation ： 技术创新平台model
	 * */
	public void updateTechInnovation(TechInnovation echInnovation);
	
	/**
	 * 删除技术创新平台
	 * newsId ： 技术创新平台ID
	 * */
	public void deleteTechInnovationById(String echInnovationId);
	
	/**
	 * 批量删除技术创新平台
	 * echInnovationIds ： 技术创新平台ID集合
	 * */
	public void deleteTechInnovationsByIds(String echInnovationIds);
	
	/**
	 * 修改新闻启用禁用状态
	 * @param echInnovationId ID
	 * @param value 状态值
	 * @param tableName 数据库表名称
	 */
	public void changeStatus(String echInnovationId, String value, String tableName);
	
	
	/**
	 * 查询技术创新成果列表
	 * param : 分页参数
	 * techAchiev ： 查询条件
	 * */
	public Page queryTechAchievList(Map<String, Object> param, TechAchiev techAchiev);
	
	/**
	 * 通过ID查询技术创新成果详细信息
	 * @param techAchievId
	 */
	public TechAchiev queryTechAchievById(String techAchievId);
	
	/**
	 * 新增技术创新成果
	 * techAchiev ： 技术创新成果model
	 * */
	public void saveTechAchiev(TechAchiev techAchiev);
	
	/**
	 * 修改技术创新成果
	 * techAchiev ： 技术创新成果model
	 * */
	public void updateTechAchiev(TechAchiev techAchiev);
	
	/**
	 * 删除技术创新成果
	 * techAchievId ： 技术创新成果ID
	 * */
	public void deleteTechAchievById(String techAchievId);
	
	/**
	 * 批量删除技术创新成果
	 * techAchievIds ： 技术创新成果ID集合
	 * */
	
	public void deleteTechAchievsByIds(String techAchievIds);
	
	/**
	 * 同样的操作实在是太多了，偷个懒 ~-~！！
	 */
	
	@SuppressWarnings("rawtypes")
	public BaseModel2 queryModelById(Class clazz,String id);

	public void saveModel(BaseModel2 model);
	
	public void updateModel(BaseModel2 model);
	
	public void deleteModelById(String id,String tableName);
	
	public void deleteModelByIds(String ids,String tableName);
	
	/**
	 * 创新创业平台列表
	 * */
	public Page queryInnovatePlatFormList(Map<String, Object> param, InnovatePlatForm innovatePlatForm);
	
	/**
	 * 产业金融环境列表
	 * */
	public Page queryFinanceEnvironmentList(Map<String, Object> param, FinanceEnvironment financeEnvironment);
	
	/**
	 * 年度新增创新企业名录列表
	 * */
	public Page queryNewCompaniesList(Map<String, Object> param, NewCompanies newCompanies);
	
	/**
	 * 年度成功引入风险投资企业名录表
	 * */
	public Page queryInvestCompaniesList(Map<String, Object> param, InvestCompanies investCompanies);
	
	/**
	 * 年度新增上市企业名录表
	 * */
	public Page queryMarketCompaniesList(Map<String, Object> param, MarketCompanies marketCompanies);
	
	/**
	 * 创新创业示范企业列表
	 * */
	public Page queryInnovBusinessList(Map<String, Object> param, InnovBusiness innovBusiness);
	
	public Page queryModelList(Map<String, Object> param,String tableName);
}
