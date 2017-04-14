package com.uws.dao;

import java.util.List;
import java.util.Map;

import com.uws.model.SysRoleUser;

/**
 * 系统角色与用户关系dao接口
 * @author hejin
 *
 */
public interface ISysRoleUserDAO {

	/**
	 * 根据角色id或用户id查询角色与用户关系列表
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @return
	 */
	public List<Map> queryRoleUserList(String roleId,String userId);

	
	/**
	 * 删除角色与用户关系
	 * @param sRoleUser  用户与角色关系实体
	 */
	public void delSysRoleUser(SysRoleUser sRoleUser);


	/**
	 * 保存用户与角色关系
	 * @param sRoleUser 用户与角色关系实体
	 */
	public void saveSysRoleUser(SysRoleUser sRoleUser);


	/**
	 * 根据角色id查询用户列表
	 * @param roleId 角色id
	 * @return
	 */
	public List<Map> queryUserListByRoleId(String roleId);


	/**
	 * 根据用户id列表查询用户与角色关系列表
	 * @param userIds 用户id列表
	 * @return
	 */
	public List<Map> getMyUserList(List<Long> userIds);


	/**
	 * 根据记录id查询用户与角色关系
	 * @param sRoleUserId 用户与角色关系id
	 */
	public SysRoleUser querySysRoleUserById(String sRoleUserId);


	/**
	 * 根据角色id列表查询用户列表
	 * @param userIds 用户id列表
	 * @return
	 */
	public List<Map> queryUserRoleListByRoleId(String roleId);


	/**
	 * 根据用户id删除用户与角色关系信息
	 * @param userId 用户id
	 */
	public void delSysRoleUserByUserID(String userId);
	
	
	
	
	
}
