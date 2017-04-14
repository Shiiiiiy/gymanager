package com.uws.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.dao.impl.BaseDAOImpl;
import com.base.util.DataUtil;
import com.uws.dao.ISysRolePermissionDao;

@Repository
public class SysRolePermissionDaoImpl extends BaseDAOImpl implements ISysRolePermissionDao  {

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryPerMapListByRole(String roleId, String type) {
		
		StringBuffer sql = new StringBuffer(" SELECT  DISTINCT P.VALUE AS ID,G.NAME AS NAME,P.TYPE AS PID  FROM REF_ROLE_PERMISSION P LEFT JOIN INDUSTRY_GARDEN G ON P.VALUE = G.ID  WHERE 1=1 ");
		
		if(DataUtil.isNotNull(roleId)){
			sql.append(" AND P.ROLE_ID ='"+roleId+"' ");
		}else{
			return null;
		}
		if(DataUtil.isNotNull(type)){
			sql.append(" AND P.TYPE ='"+type+"' ");
		}
		
		return this.SelectAll(sql.toString());
	}
	
	
	

	@Override
	public void saveRolePer(String roleId, String type, String moduleIds) {
		
		if(DataUtil.isNull(roleId) || DataUtil.isNull(type) || DataUtil.isNull(moduleIds)){
			return ;
		}
		List<String> sqlList = new ArrayList<String>();
		String idarr[] = moduleIds.split(",");
		for (String id : idarr) {
			if(DataUtil.isNotNull(id)){
				String sql = " INSERT INTO REF_ROLE_PERMISSION(ROLE_ID,TYPE,VALUE) VALUES ("+roleId+",'"+type+"','"+id+"') ";
				sqlList.add(sql);
			}
			
		}
		if(sqlList!=null&&sqlList.size()>0){
			this.UpdateDataMore(sqlList);
		}
	}

	@Override
	public void delRolePer(String roleId, String type, String moduleIds) {
		if(DataUtil.isNull(roleId) || DataUtil.isNull(type) || DataUtil.isNull(moduleIds)){
			return ;
		}
		
		StringBuffer sb = new StringBuffer();
		String idarr[] = moduleIds.split(",");
		for(int i=0;i<idarr.length;i++){
			if(idarr.length-1==i){
				sb.append("'"+idarr[i]+"'");
			}else{
				sb.append("'"+idarr[i]+"',");
			}
		}
		
		if(DataUtil.isNotNull(sb)){
			String sql = "DELETE FROM REF_ROLE_PERMISSION WHERE ROLE_ID = '"+roleId+"' AND TYPE = '"+type+"' AND VALUE IN ("+sb.toString()+") ";
			this.executeSQL(sql);
		}
	}




	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryPerMapListByUser(String userId, String type) {
		
		if(DataUtil.isNull(userId)){
			return null;
		}
				
		StringBuffer sql = new StringBuffer(" SELECT DISTINCT P.VALUE AS ID,G.NAME AS NAME,P.TYPE AS PID FROM REF_ROLE_PERMISSION P LEFT JOIN  INDUSTRY_GARDEN G ON P.VALUE = G.ID   WHERE ROLE_ID IN (SELECT ROLE_ID FROM REF_USER_ROLE WHERE USER_ID = '"+userId+"') ");

		if(DataUtil.isNotNull(type)){
			sql.append(" AND TYPE = '"+type+"' ");
		}
		
		sql.append(" ORDER BY G.ID ");
		
		return this.SelectAll(sql.toString());
	}

}
