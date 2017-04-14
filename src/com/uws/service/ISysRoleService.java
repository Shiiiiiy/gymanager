package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.SysRole;
import com.uws.model.SysUser;
/**
 * 系统角色service接口
 * @author hejin
 *
 */
public interface ISysRoleService {
       
	/**
	 * 查询角色分页列表
	 * @param param 分页参数
	 * @param sRole 查询条件
	 * @return
	 */
	public Page queryRoleList(Map<String, Object> param,SysRole sRole);
	
	
	/**
	 * 根据角色id查询角色信息
	 * @param roleId 角色id
	 * @return
	 */
	public SysRole queryRoleById(Long roleId);
	
	
	/**
	 * 根据角色编码查询角色信息
	 * @param roleCode 角色编码
	 * @return
	 */
	public SysRole queryRoleByCode(String roleCode);

  
	/**
	 * 保存角色信息
	 * @param sRole 角色信息实体
	 */
	public void saveSysRole(SysRole sRole);


	/**
	 * 删除角色
	 * @param sRole 要删除的角色对象
	 */
	public void delSysRole(SysRole sRole);
	
	
	/**
	 * 根据用户id查询该用户所具有的的菜单权限
	 * @param userId 用户id
	 * @return List<Map> 菜单列表
	 */
	public List<Map> getUserPermissionByUserId(String userId);


	/**
	 * 根基角色id查询该角色的用户列表
	 * @param roleId
	 * @return
	 */
	public List<Map> getSelectedUserList(String roleId);


	/**
	 * 根据用户id列表查询用户列表
	 * @param userIdList  用户id列表
	 * @return
	 */
	public List<Map> getMyUserList(String userIdList);


	/**
	 * 将所选的用户分配给指定角色
	 * @param userList 用户id列表
	 * @param roleId 角色id
	 */
	public void allotUser(String userList, String roleId);


	/**
	 * 根据角色id查询该角色权限下的所有菜单列表
	 * @param roleId 角色id
	 * @return
	 */
	public List<Map> getMenuList(String roleId);


	/**
	 * 查询所有已启用的菜单列表
	 * @return
	 */
	public List<Map> getAllMenuList();


	/**
	 * 将所选的菜单分配给指定角色
	 * @param idsDel 要删除的菜单id列表
	 * @param idsAdd 要新增的菜单id列表
	 * @param roleId 角色id
	 */
	public void updateRoleMenu(String idsDel, String idsAdd, String roleId);


	/**
	 * 查询所有已启用的菜单列表
	 * @return
	 */
	List<Map> getAllMenusList();


	/**
	 * 查询用户分页数据
	 * @param param 分页参数
	 * @param sm 查询参数
	 * @return
	 */
	Page getUserPage(Map param, SysUser sm);
	
	
	
}
