package com.uws.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.impl.BaseDAOImpl;
import com.base.util.DefaultValue;
import com.uws.dao.ISysMenuDAO;
import com.uws.dao.ISysRoleDAO;
import com.uws.dao.ISysRoleMenuDAO;
import com.uws.model.SysMenu;
import com.uws.model.SysRole;
import com.uws.model.SysRoleMenu;
import com.uws.util.Util;
/**
 * 系统角色与菜单关系dao实现类
 * @author hejin
 *
 */
@Repository
public class SysRoleMenuDAOImpl extends BaseDAOImpl implements ISysRoleMenuDAO{
    @Autowired
	private ISysMenuDAO sysMenuDAO;
	
    @Autowired
    private ISysRoleDAO sysRoleDAO;
    
	/**
	  * 根据角色id查询菜单列表
	  * @param roleId 角色id
	  * @return
	  */
	@Override
	public List<Map> getSysRoleMenuList(String roleId) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("SELECT B.* FROM REF_ROLE_MENU A LEFT JOIN SYS_MENU B ON A.MENU_ID=B.ID  WHERE 1=1  ");
		List param=new ArrayList();
		if(!Util.isNull(roleId) ){
			
		     sql.append(" AND A.ROLE_ID =? ");
		     param.add(Long.parseLong(roleId));
		}
		
		sql.append(" GROUP BY A.MENU_ID ORDER BY B.REC_TIME  ");
		
		return this.SelectAll(sql.toString(),param.toArray());
	}

	 /**
	  * 根据角色id列表查询菜单列表
	  * @param roleIdList 角色id列表
	  * @return
	  */
	@Override
	public List<Map> getSysRoleMenuListAll(List<Long> roleIdList) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("SELECT B.* FROM REF_ROLE_MENU A LEFT JOIN SYS_MENU B ON A.MENU_ID=B.ID LEFT JOIN SYS_DIC C ON B.STATUS=C.ID  WHERE 1=1  ");
		List<Map>  list=new ArrayList();
		if(roleIdList!=null && roleIdList.size()>0){
			
		     sql.append(" AND A.ROLE_ID IN (");
		     int count =roleIdList.size();
		     for(int i=0;i<count;i++){
		    	 if(i==count-1){
		    	    sql.append(roleIdList.get(i)+")");
		    	 }else{
		    		sql.append(roleIdList.get(i)+",");
		    	 }
		     }
		     sql.append(" AND  C.CODE= '"+DefaultValue.DIC_ENABLE+"'");
		     sql.append(" GROUP BY A.MENU_ID ORDER BY B.REC_TIME  ");
		     list=this.SelectAll(sql.toString(),new Object[]{});
		}
		
		
		
		return list;
	}

	/**
	 * 批量删除菜单与角色关系
	 * @param roleId 角色id
	 * @param menuIdList 菜单id列表
	 */
	@Override
	public void delRoleMenuMutil(Long roleId,List<Long> menuIdList){
		StringBuffer sql=new StringBuffer("DELETE  FROM REF_ROLE_MENU   WHERE 1=1  ");
		
		if(menuIdList!=null && menuIdList.size()>0){
			if(roleId!=null){
				
				sql.append(" AND ROLE_ID ="+roleId);
			}
		     sql.append(" AND MENU_ID IN ( ");
		     int count =menuIdList.size();
		     for(int i=0;i<count;i++){
		    	 if(i==count-1){
		    	    sql.append(menuIdList.get(i)+" ) ");
		    	 }else{
		    		sql.append(menuIdList.get(i)+",");
		    	 }
		     }
		     
		     this.executeSQL(sql.toString());
		}
		
		
		
	}
	
	
	/**
	 * 删除角色与菜单关系
	 * @param sysRoleMenu  菜单与角色关系实体
	 */
	@Override
	public void delSysRoleMenu(SysRoleMenu sysRoleMenu) {
		// TODO Auto-generated method stub
		this.delete(sysRoleMenu);
	}

	/**
	 * 保存菜单与角色关系
	 * @param sysRoleMenu 菜单与角色关系实体
	 */
	@Override
	public void saveSysRoleMenu(SysRoleMenu sysRoleMenu) {
		// TODO Auto-generated method stub
		this.save(sysRoleMenu);
	}

	
	/**
	 * 新增菜单与角色关系
	 * @param menuId 菜单id
	 * @param roleId 角色id
	 */
	@Override
	public void addSysRoleMenu(Long menuId,Long roleId) {
		StringBuffer sql=new StringBuffer("INSERT INTO  REF_ROLE_MENU (MENU_ID,ROLE_ID)  VALUES (");
		if(menuId!=null && roleId!=null ){
			sql.append(menuId+","+roleId+")");
		}
		SysRoleMenu sm=new SysRoleMenu();
		
		SysMenu smm=this.sysMenuDAO.searchById(menuId.toString());
		//smm.setId(Long.parseLong(idsAdd02[i]));
		
		SysRole sr=this.sysRoleDAO.queryRoleById(roleId);
		
		sm.setMenuId(menuId);
		sm.setRoleId(roleId);
		//sr.setId(Long.parseLong(roleId));
		System.out.println("sql.toString()========="+sql.toString());
		this.save(sm);
		// TODO Auto-generated method stub
		//this.executeSQL(sql.toString());
	}

	
	
}
