package com.uws.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.ExcellentPerson;

/**
 * 技术创新-创新人才Dao
 * @author 张学彪
 */
public interface IExcellentPersonDAO {

	/**
	 * 查询创新人才列表
	 * param : 分页参数
	 * company ： 查询条件
	 * */
	public Page queryExcellentPersonList(Map<String, Object> param, ExcellentPerson person);
	
	/**
	 * 通过ID查询创新人才详细信息
	 * @param personId
	 */
	public ExcellentPerson queryExcellentPersonByPersonId(String personId);
	
	/**
	 * 新增创新人才
	 * person ： 创新人才model
	 * */
	public void saveExcellentPerson(ExcellentPerson person);
	
	/**
	 * 修改创新人才
	 * person ： 创新人才model
	 * */
	public void updateExcellentPerson(ExcellentPerson person);
	
	/**
	 * 删除创新人才
	 * personId ： 创新人才ID
	 * */
	public void deleteExcellentPersonById(String personId);
	
	/**
	 * 批量删除创新人才
	 * personIds ： 创新人才ID集合
	 * */
	public void deleteExcellentPersonsByIds(List<String> personIds);
	
	/**
	 * 修改创新人才启用禁用状态
	 * @param personId ： 创新人才ID
	 * @param value 状态值
	 */
	public void changeStatus(String personId, String value);
}
