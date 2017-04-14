package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.Dic;
/**
 * @ClassName: ISysDicService 
 * @author: 石焱 
 * @date: 2017-2-15 下午3:44:16
 */
public interface ISysDicService  {

	
	/**
	 * 查询字典分类详细信息
	 * @param param
	 * @param dic  保存查询条件
	 * @return
	 */
	public Page search(Map<String, Object> param,Dic dic);
	
	/**
	 * 保存字典
	 * @param dic
	 */
	public void save(Dic dic);
	
	/**
	 * 根据id查询数据
	 * @param dic
	 */
	public Dic findById(String id);
	
	/**
	 * 根据字典id删除字典对象
	 * @param id
	 * @return 返回true的话，删除的是字典项；返回false，删除的是字典分类
	 */
	public boolean deleteById(String id);
	
	
	/**
	 * 根据字典分类code获取字典项集合，code为空，查询所有启用的字典分类集合
	 * @param code
	 * @return
	 */
	public List<Dic> getDicListByCode(String code);
	
	
	/**
	 * 更改字典启用状态，返回的boolean表示更改的字典类型  true--字典项  false--字典分类
	 * @param id
	 * @param type 决定启用还是禁用字典 ，0 禁用，其他 启用
	 * @return
	 */
	public boolean changeStatus(String id,int type);

	/**
	 * 查询字典项分页列表
	 * @param param
	 * @param dic
	 * @return
	 */
	public Page searchItem(Map<String, Object> param,Dic dic);
	
	/**
	 * 根据字典分类code和字典code查询 字典对象
	 * @param parentCode
	 * @param code
	 * @return Dic
	 */
	public Dic getDicByCodes(String parentCode,String code);
	
	/**
	 * 校验code的唯一性
	 * @param code 字典code
	 * @param pid  上级字典分类 id  允许为null
	 * @param id   字典id  允许为null （为空时是新增时的校验，不为空是编辑时的校验）
	 * @return
	 */
	public boolean checkCode(String code,String pid,String id);
	
}
