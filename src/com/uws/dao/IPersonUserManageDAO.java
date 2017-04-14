package com.uws.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.Guser;
import com.uws.model.Person;
import com.uws.model.UserManageParam;

/**
 * 个人用户管理dao接口
 * @author hejin
 *
 */
public interface IPersonUserManageDAO {

	/**
	 * 查询个人用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 个人用户分页数据
	 */
	public Page queryPersonUserList(Map<String, Object> param,UserManageParam ump);
	
	
	/**
	 * 根据用户id查询个人用户信息
	 * @param userId 用户id
	 * @return
	 */
	public Map<String,Object> queryUserPserById(String userId);
	
	
	/**
	 * 根据用户名查询用户基础信息
	 * @param username 用户名
	 * @return
	 */
	public Map<String, Object> queryGuserByUserName(String username);

	

	/**
	 * 保存用户基础信息
	 * @param guser 用户基础信息实体
	 */
	public void saveGuser(Guser guser);

	
	/**
	 * 保存个人用户详细信息
	 * @param person 个人用户详细信息实体
	 */
	public void savePerson(Person person);
	
	/**
	 * 保存用户基础信息
	 * @param guser 用户基础信息实体
	 */
	public void updateGuser(Guser guser);

	
	/**
	 * 保存企业用户详细信息
	 * @param cm 企业用户详细信息实体
	 */
	public void updatePerson(Person person);
	/**
	 * 批量删除个人用户详细信息
	 * @param personIdList 要删除的个人用户id列表
	 */
	public void delPerson(String personIdList);
	
	
	/**
	 * 批量删除用户基础信息
	 * @param guserIdList 要删除的用户基础信息id列表
	 */
	public void delGuser(String guserIdList);


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
	 * 根据DIC表id查询省市区字典项名称
	 * @param dicId 字典表id
	 * @return
	 */
	Map<String, Object> queryDicByDicId(String dicId);


	/**
	 * 根查询个人用户信息列表(导出)
	 * @return
	 */
	List<Map<Integer, Object>> queryAllUserPersonList(UserManageParam ump);


	public void addPersonUsers(List<String> sql);
	
	
	
}
