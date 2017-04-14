package com.uws.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: ISysRolePermissionService 
 * @Description: 角色权限service
 * @date: 2017-3-20 下午1:30:37
 */	
@SuppressWarnings("rawtypes")
public interface ISysRolePermissionService {

	
	/**
	 * 根据 角色id和类型 查询 该角色的权限集合，
	 * @param roleId 为 null 返回null  
	 * @param type   type 为null的话， 查询所有模块（园区，支柱产业，生产性服务，工业产品）的权限
	 * @return
	 */
	public List<Map> queryPerMapListByRole(String roleId,String type);	
	
	/**
	 * 新增某一角色的 权限
	 * @param roleId     角色id
	 * @param type      （园区，支柱产业，生产性服务，工业产品）四个之一
	 * @param moduleIds  上面四个模块下的分类 id字符串，可以是一个或多个，多个时通过 逗号 拼接
	 */
	public void saveRolePer(String roleId,String type,String moduleIds);
	
	/**
	 * 删除某一角色的 权限
	 * @param roleId     角色id
	 * @param type      （园区，支柱产业，生产性服务，工业产品）四个之一
	 * @param moduleIds  上面四个模块下的分类 id字符串，可以是一个或多个，多个时通过 逗号 拼接
	 */
	public void delRolePer(String roleId,String type,String moduleIds);
	
	/**
	 * 根据 用户id和类型 查询 该用户的权限集合，
	 * @param userId 为 null 返回null  
	 * @param type   type 为null的话， 查询所有模块（园区，支柱产业，生产性服务，工业产品）的权限
	 * @return
	 */
	public List<Map> queryPerMapListByUser(String userId,String type);
	
	
	public void updatePerByRole(String roleId,String type,String addstring,String delString);
	
	/**
	 * 查询 园区权限时 去掉主园区的  （园区管理下 项目管理 即使用户拥有主园区 的权限 也不应该显示主园区 ，项目是分布在各个具体园区下面的  ）
	 * @param userId 用户id   
	 * @return
	 */
	public List<Map> queryGardenPerWithoutMain(String userId);
	
	/**
	 * 查询用户 的 支柱产业模块权限
	 * @param userId    用户id
	 * @param containMain  是否包含 主模块
	 * @return
	 */
	public List<Map> queryIndustryResult(String userId,boolean containMain);
	
}
