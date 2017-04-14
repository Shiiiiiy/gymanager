package com.uws.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.model.BaseModel;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.dao.ISysRoleDAO;
import com.uws.model.SysRole;
import com.uws.util.Util;
/**
 * 系统角色dao实现类
 * @author hejin
 *
 */
@Repository
public class SysRoleDAOImpl extends BaseDAOImpl implements ISysRoleDAO{

	/**
	 * 查询角色分页列表
	 * @param sRole 分页查询参数
	 * @return
	 */
	@Override
	public Page queryRoleList(Map<String, Object> param,SysRole sRole) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("SELECT * FROM SYS_ROLE WHERE 1=1  ");
		
		if(sRole!=null){
			if(!Util.isNull(sRole.getRoleName())){
				sql.append(" AND ROLE_NAME LIKE '%"+sRole.getRoleName()+"%'  ");
				
			}
			if(!Util.isNull(sRole.getRoleCode())){
				sql.append(" AND ROLE_CODE LIKE '%"+sRole.getRoleCode()+"%'  ");
				
			}
			
			if(sRole.getStatus()!=null && sRole.getStatus()!=0){
				sql.append(" AND STATUS ="+sRole.getStatus()+"  ");
			}
			
		}
		sql.append(" ORDER BY REC_TIME DESC ");
		
		param.put("sql", sql.toString());
		
		return queryBySql(param);
	}

	/**
	 * 根据角色id查询角色信息
	 * @param roleId 角色id
	 * @return
	 */
	@Override
	public SysRole queryRoleById(Long roleId) {
		SysRole role=(SysRole)this.get(SysRole.class, roleId);

		return role;
	}

	/**
	 * 根据角色编码查询角色信息
	 * @param roleCode 角色编码
	 * @return
	 */
	@Override
	public SysRole queryRoleByCode(String roleCode) {
		List<String> param = new ArrayList<String>();
		String hql = " FROM SysRole s WHERE s.roleCode = ? ";
		
		param.add(roleCode);
		SysRole sysRole = (SysRole)this.get(hql, param.toArray()); 
		
		return sysRole;
	}

	/**
	 * 保存角色信息
	 * @param sRole 角色信息实体
	 */
	@Override
	public void saveSysRole(SysRole sRole) {
		// TODO Auto-generated method stub
		if(DataUtil.isNotNull(sRole.getId()) && sRole.getId()!=0){
			this.update(sRole);
		}else{
			this.save(sRole);
		}
		
	}
	
	/**
	 * 删除角色信息
	 * @param sRole 角色信息实体
	 */
	@Override
	public void delSysRole(SysRole sRole){
		
		this.delete(sRole);
	}

	/**
	 * 根据用户与角色关系列表，查询该用户所属角色中已启用的角色
	 * @param list 用户与角色关系列表
	 * @return List<Long> 返回该用户已启用角色列表
	 */
	@Override
	public List<Long> getUserRoleIdList(List<Map> list){
		StringBuffer sql=new StringBuffer("SELECT A.ID AS ID FROM SYS_ROLE A LEFT JOIN SYS_DIC B ON A.STATUS=B.ID WHERE 1=1  ");
		//角色id列表
		List<Long> roleIdList=new ArrayList();
		
		int count =list.size();
		if(list!=null && count>0){
			sql.append(" AND A.ID IN ( ");
		    for(int i=0;i<count;i++){
		    	if(i==count-1){
		    		sql.append(Long.valueOf(list.get(i).get("ROLE_ID").toString())+" ) ");
		    	}else{
		    		sql.append(Long.valueOf(list.get(i).get("ROLE_ID").toString())+",");
		    	}
		    	
		    }
		    sql.append(" AND B.CODE= '"+DefaultValue.DIC_ENABLE+"'");
		    
		    List<Map> roleList=this.SelectAll(sql.toString());
		    
		    int total = roleList.size();
		    
		    for(int i=0;i<total;i++){
		    	roleIdList.add(new Long(roleList.get(i).get("ID").toString()));
		    }
		}
		
		
		return roleIdList;
	}
	
}
