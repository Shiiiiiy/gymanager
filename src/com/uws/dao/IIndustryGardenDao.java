package com.uws.dao;


import java.util.List;
import java.util.Map;


import com.base.dao.IBaseDAO;
import com.base.dao.Page;
import com.base.model.BaseModel2;
import com.uws.model.GardenProject;
import com.uws.model.IndustryGarden;
import com.uws.model.NewsAll;

public interface IIndustryGardenDao extends IBaseDAO{
	
	/**
	 * 查询产业园区信息分页数据
	 * @param param  分页条件
	 * @param igType 园区类型
	 * @param model 保存查询条件
	 * @return
	 */
	public Page searchIndustryGardenPage(Map<String, Object> param,String igType,String industryType,IndustryGarden model,List<Map> mapList);
	
	
	/**
	 * 查询产业园区集合
	 * @param industryType
	 * @param igType
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryIndustryGardenList(String industryType,String igType,boolean containsMainGarden);
	
	
	/**
	 * 通过id查询对象
	 * @param C
	 * @param id
	 * @return
	 */
	public BaseModel2 findById(@SuppressWarnings("rawtypes") Class C,String id);
	
	/**
	 * 更新对象
	 * @param model
	 */
	public void updateModel2(BaseModel2 model); 
	
	/**
	 * 保存对象
	 * @param model
	 */
	public void saveModel2(BaseModel2 model);
	
	/**
	 * 删除对象
	 * @param model
	 */
	public void deleteProjectByIds(String ids);
	
	/**
	 * 查询园区项目信息分页列表
	 * @param param   分页条件 
	 * @param project 查询条件
	 * @return
	 */
	public Page queryGardenProject(Map<String, Object> param,GardenProject project,List<Map> mapList);
	
	
	public Page queryGardenNews(Map<String, Object> param,NewsAll news,List<Map> mapList);
}
