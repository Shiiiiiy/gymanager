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
import com.uws.dao.ISysRoleDAO;
import com.uws.dao.ISysRoleUserDAO;
import com.uws.model.SysRole;
import com.uws.model.SysRoleUser;
import com.uws.util.Util;
/**
 * 系统角色与用户关系dao实现类
 * @author hejin
 *
 */
@Repository
public class SysRoleUserDAOImpl extends BaseDAOImpl implements ISysRoleUserDAO{

	/**
	 * 根据角色id或用户id查询角色与用户关系列表
	 * @param roleId 角色id
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public List<Map> queryRoleUserList(String roleId,String userId) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("SELECT * FROM REF_USER_ROLE WHERE 1=1  ");
		List param=new ArrayList();
		
		if(!Util.isNull(roleId) ){
			
		     sql.append(" AND ROLE_ID =? ");
		     param.add(Long.parseLong(roleId));
		}
		
		if(!Util.isNull(userId)){
			
		     sql.append(" AND USER_ID =? ");
		     param.add(Long.parseLong(userId));
		}
		
		
		return this.SelectAll(sql.toString(),param.toArray());
	}

	
	/**
	 * 根据角色id查询用户与角色关系列表
	 * @param roleId 角色id
	 * @return
	 */
	@Override
	public List<Map> queryUserRoleListByRoleId(String roleId) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("SELECT * FROM REF_USER_ROLE  WHERE 1=1  ");
		List param=new ArrayList();
		
		if(!Util.isNull(roleId) ){
			
		     sql.append(" AND ROLE_ID =? ");
		     param.add(Long.parseLong(roleId));
		}
		
		
		return this.SelectAll(sql.toString(),param.toArray());
	}

	
	/**
	 * 根据角色id查询用户列表
	 * @param roleId 角色id
	 * @return
	 */
	@Override
	public List<Map> queryUserListByRoleId(String roleId) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("SELECT B.* FROM REF_USER_ROLE A LEFT JOIN SYS_USER B ON A.USER_ID=B.ID  WHERE 1=1  ");
		List param=new ArrayList();
		
		if(!Util.isNull(roleId) ){
			
		     sql.append(" AND A.ROLE_ID =? ");
		     param.add(Long.parseLong(roleId));
		}
		
		
		return this.SelectAll(sql.toString(),param.toArray());
	}
	
	
	
	/**
	 * 根据记录id查询用户与角色关系
	 * @param sRoleUserId 用户与角色关系id
	 */
	@Override
	public SysRoleUser querySysRoleUserById(String sRoleUserId) {
		
		return (SysRoleUser)this.get(SysRoleUser.class, Long.parseLong(sRoleUserId));
	}
	
	

	/**
	 * 保存用户与角色关系
	 * @param sRoleUser 用户与角色关系实体
	 */
	@Override
	public void saveSysRoleUser(SysRoleUser sRoleUser) {
		// TODO Auto-generated method stub
		this.save(sRoleUser);
		
	}
	
	/**
	 * 删除角色与用户关系
	 * @param sRoleUser  用户与角色关系实体
	 */
	@Override
	public void delSysRoleUser(SysRoleUser sRoleUser){
		
		this.delete(sRoleUser);
	}

	/**
	 * 根据用户id列表查询用户列表
	 * @param userIds 用户id列表
	 * @return
	 */
	@Override
	public List<Map> getMyUserList(List<Long> userIds){
		
		StringBuffer sql=new StringBuffer("SELECT * FROM  SYS_USER   WHERE 1=1  ");
		
		
		if(userIds!=null && userIds.size()>0 ){
			
		     sql.append(" AND ID in ( ");
		     int count =userIds.size();
		     for(int i=0;i<count;i++){
		    	 if(i==count-1){
		    	    sql.append(userIds.get(i)+")");
		    	 }else{
		    		sql.append(userIds.get(i)+",");
		    	 }
		     }
		}
		
		
		return this.SelectAll(sql.toString(),new Object[]{});
	}
	
	/**
	 * 根据用户id删除用户与角色关系信息
	 * @param userId 用户id
	 */
	@Override
	public void delSysRoleUserByUserID(String userId){
		StringBuffer sql=new StringBuffer("DELETE  FROM  REF_USER_ROLE  WHERE 1=1  ");
		
		if(DataUtil.isNotNull(userId)){
			sql.append(" AND USER_ID = "+Long.parseLong(userId));
			this.executeSQL(sql.toString());
		}
		
	}
	
	
}
