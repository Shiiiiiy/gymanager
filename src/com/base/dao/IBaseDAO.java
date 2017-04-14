package com.base.dao;

import java.io.Serializable;
import java.util.List;

import com.base.model.BaseModel;

/**
 * 基础数据库操作类
 * 
 * @author bufoon
 * 
 */
public interface IBaseDAO {

	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public BaseModel save(BaseModel o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(BaseModel o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(BaseModel o);


	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List find(String hql);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List find(String hql, Object[] param);


	

	/**
	 * 获得一个对象
	 * 
	 * @param c 对象类型
	 * @param id
	 * @return Object
	 */
	public BaseModel get(Class c, Serializable id);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public BaseModel get(String hql, Object[] param);


	


}
