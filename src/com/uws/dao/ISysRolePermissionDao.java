package com.uws.dao;

import java.util.List;
import java.util.Map;

public interface ISysRolePermissionDao {
	
	@SuppressWarnings("rawtypes")
	public List<Map> queryPerMapListByRole(String roleId, String type);
	
	public void saveRolePer(String roleId, String type, String moduleIds); 
	
	public void delRolePer(String roleId, String type, String moduleIds);

	public List<Map> queryPerMapListByUser(String userId, String type);

}
