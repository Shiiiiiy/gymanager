package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.Guser;
import com.uws.model.Institution;
import com.uws.model.Person;
import com.uws.model.SysRole;
import com.uws.model.UserManageParam;

/**
 * 机构用户管理service接口
 * @author hejin
 *
 */
public interface IInstitutionUserManageService {

	/**
	 * 查询机构用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 机构用户分页数据
	 */
	public Page queryInstitutionUserList(Map<String, Object> param,UserManageParam ump);
	
	
	
	/**
	 * 根据用户id查询机构用户信息
	 * @param userId 角色id
	 * @return
	 */
	public Map<String,Object> queryUserInstitutionById(String userId);
	
	
	/**
	 * 根据用户名查询用户基础信息
	 * @param username 用户名
	 * @return
	 */
	public Map<String,Object> queryGuserByUserName(String username);

	

	/**
	 * 保存机构用户信息
	 * @param ump 机构用户信息实体
	 */
	public void addInstitutionUser(UserManageParam ump);
	/**
	 * 保存机构用户信息
	 * @param ump 机构用户信息实体
	 */
	public void addInstitutionUsers(List<String> ump);

	/**
	 * 批量删除机构用户信息
	 * @param personIdList 要删除的机构用户基础信息id列表
	 * @param guserIdList  要删除的机构用户详细信息id列表
	 */
	public void delInstitutionUser(String personIdList,String guserIdList);



	/**
	 * 保存用户基本信息
	 * @param guser 机构用户基础信息对象
	 * @param institution 机构用户详细信息对象
	 */
	public void saveInstitutionUser(Guser guser, Institution institution);
	

	/**
	 * 根据id查询机构用户基础信息对象
	 * @param guserId 主键
	 * @return
	 */
	public Guser getGuserById(String guserId);


	/**
	 * 根据id查询机构用户详细信息对象
	 * @param institutionId 主键
	 * @return
	 */
	public Institution getInstitutionById(String institutionId);
	
	/**
	 * 根查询机构用户信息列表(导出)
	 * @return
	 */
	List<Map<Integer, Object>> queryAllUserInstitutionList(UserManageParam ump);
	
}
