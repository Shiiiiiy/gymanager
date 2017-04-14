package com.uws.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uws.dao.ISysRolePermissionDao;
import com.uws.service.ISysRolePermissionService;
import com.uws.util.Constants;
@SuppressWarnings("rawtypes")
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl implements ISysRolePermissionService{

	@Autowired
	private ISysRolePermissionDao sysRolePermissionDao;
	
	
	@Override
	public List<Map> queryPerMapListByRole(String roleId, String type) {
		return this.sysRolePermissionDao.queryPerMapListByRole(roleId, type);
	}

	@Override
	public void saveRolePer(String roleId, String type, String moduleIds) {
		this.sysRolePermissionDao.saveRolePer(roleId, type, moduleIds);
		
	}

	@Override
	public void delRolePer(String roleId, String type, String moduleIds) {
		this.sysRolePermissionDao.delRolePer(roleId, type, moduleIds);
	}

	@Override
	public List<Map> queryPerMapListByUser(String userId, String type) {
		return this.sysRolePermissionDao.queryPerMapListByUser(userId, type);
	}

	@Override
	public void updatePerByRole(String roleId, String type, String addstring,
			String delString) {
		
		this.delRolePer(roleId, type, delString);
		this.saveRolePer(roleId,type,addstring);
	}

	@Override
	public List<Map> queryGardenPerWithoutMain(String userId) {
		
		List<Map> list = this.queryPerMapListByUser(userId, Constants.ROLE_PER_GARDEN);
		
		Iterator<Map> it = list.iterator();
		
		//遍历 map集合 然后删掉 主园区
		while(it.hasNext()){
			if(it.next().get("ID").equals("IG_C000")){
				it.remove();
			}
		}
		return list;
	}

	@Override
	public List<Map> queryIndustryResult(String userId, boolean containMain) {
		
		List<Map> list = this.queryPerMapListByUser(userId, Constants.ROLE_PER_INDUSTRY);

		if(containMain){
			for (Map map : list) {
				if(map.get("ID").equals("MODULE_E_MAIN")){
					map.put("NAME","主模块");
				}
			}
		}else{
			Iterator<Map> it = list.iterator();
			while(it.hasNext()){
				if(it.next().get("ID").equals("MODULE_E_MAIN")){
					it.remove();
				}
			}
		}
		
		
		
		return list;
	}

	
}
