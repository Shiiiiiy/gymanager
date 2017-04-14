package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.base.model.BaseModel2;
import com.uws.model.GardenProject;
import com.uws.model.IndustryGarden;
import com.uws.model.NewsAll;

public interface IIndustryGardenService {
	
	/**
	 * 查询产业园区信息分页数据
	 * @param param  分页条件
	 * @param igType 园区类型
	 * @param industryType 产业类型 : INDUSTRY_A——工业产品，INDUSTRY_B——生产服务； INDUSTRY_D——支柱产业
	 * @param model 保存查询条件
	 * @return
	 */
	public Page searchIndustryGardenPage(Map<String, Object> param,String igType,String industryType,IndustryGarden model,List<Map> mapList);
	
	
	/**
	 * 查询产业园区信息map集合  map里只有【ID】和【NAME】 两个属性
 	 * @param industryType 产业类型 : INDUSTRY_A——工业产品，INDUSTRY_B——生产服务；
	 * @param igType    产业 或 园区
	 * @param containsMainGarden  是否包含主园区    true--包含   false--不包含
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryIndustryGardenList(String industryType,String igType,boolean containsMainGarden);
	
	
	/**
	 * 
	 * 根据id查询IndustryGarden对象
	 * @param id
	 * @return
	 */
	public IndustryGarden findById(String id);
	
	
	/**
	 * 根据id查找GardenProject 对象
	 * @param id
	 * @return
	 */
	public GardenProject findProjectById(String id);
	
	/**
	 * 更新对象
	 * @param garden
	 */
	public void updateModel2(BaseModel2 model);
	
	
	/**
	 * 保存对象
	 * @param garden
	 */
	public void saveModel2(BaseModel2 model);
	
	
	/**
	 * 获取需要初始化的产业园的id集合
	 * @return
	 */
	public List<String> getGardenInitList();
	
	
	/**
	 * 新增 多个garden对象 
	 * @param list
	 */
	public void saveGardenList(List<IndustryGarden> list);
	
	/**
	 * 初始化产业园区
	 * @param ids   需要初始化的园区id通过   , 拼接成的字符串
	 */
	public void initGarden(String ids);
	
	/**
	 * @param param   分页条件
	 * @param project 查询条件
	 * @return
	 */
	public Page queryGardenProject(Map<String, Object> param,GardenProject project,List<Map> mapList);
	
	/**
	 * 新增或更新园区项目
	 * @param project
	 */
	public void saveOrUpdateProject(GardenProject project);

	/**
	 * 通过id删除园区项目   
	 * @param ids  可以是一个id 也可以是多个id通过 , 拼接成的字符串
	 */
	public void deleteProject(String ids);
	
	/**
	 * @param param
	 * @param news
	 * @param mapList   权限集合
	 * @return
	 */
	public Page queryGardenNews(Map<String, Object> param,NewsAll news,List<Map> mapList);
	
	
	/**
	 * 新增或更新园区新闻（动态+政策）
	 * @param project
	 */
	public void saveOrUpdateNews(NewsAll news); 
	
	/**
	 * 删除园区新闻
	 * @param ids
	 */
	public void deleteNews(String ids);
	
}
