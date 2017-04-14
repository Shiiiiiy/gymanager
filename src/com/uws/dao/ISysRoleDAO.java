package com.uws.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.SysRole;
/**
 * 系统角色dao接口
 * @author hejin
 *
 */
public interface ISysRoleDAO {

	
	/**
	 * 查询角色分页列表
	 * @param sRole 查询条件
	 * @param param 分页查询参数
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
	 * 根据用户与角色关系列表，查询该用户所属角色中已启用的角色
	 * @param list 用户与角色关系列表
	 * @return List<Long> 返回该用户已启用角色列表
	 */
	public List<Long> getUserRoleIdList(List<Map> list);
	
}
