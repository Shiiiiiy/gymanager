package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.Guser;
import com.uws.model.Person;
import com.uws.model.SysRole;
import com.uws.model.UserManageParam;

/**
 * 个人用户管理service接口
 * @author hejin
 *
 */
public interface IPersonUserManageService {

	/**
	 * 查询个人用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 个人用户分页数据
	 */
	public Page queryPersonUserList(Map<String, Object> param,UserManageParam ump);
	
	
	
	/**
	 * 根据用户id查询个人用户信息
	 * @param userId 角色id
	 * @return
	 */
	public Map<String,Object> queryUserPserById(String userId);
	
	
	/**
	 * 根据用户名查询用户基础信息
	 * @param username 用户名
	 * @return
	 */
	public Map<String,Object> queryGuserByUserName(String username);

	

	/**
	 * 保存个人用户信息
	 * @param ump 个人用户信息实体
	 */
	public void addPersonUser(UserManageParam ump);
	

	/**
	 * 批量删除个人用户信息
	 * @param personIdList 要删除的个人用户基础信息id列表
	 * @param guserIdList  要删除的个人用户详细信息id列表
	 */
	public void delPersonUser(String personIdList,String guserIdList);



	/**
	 * 保存用户基本信息
	 * @param guser 个人用户基础信息对象
	 * @param person 个人用户详细信息对象
	 */
	public void savePersonUser(Guser guser, Person person);
	

	/**
	 * 根据id查询个人用户基础信息对象
	 * @param guserId 主键
	 * @return
	 */
	public Guser getGuserById(String guserId);


	/**
	 * 根据id查询个人用户详细信息对象
	 * @param personId 主键
	 * @return
	 */
	public Person getPersonById(String personId);



	/**
	 * 根查询个人用户信息列表(导出)
	 * @return
	 */
	List<Map<Integer, Object>> queryAllUserPersonList(UserManageParam ump);



	public void addPersonUsers(List<String> lendReco);
	
}
