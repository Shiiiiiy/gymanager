package com.uws.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.util.DataUtil;
import com.mysql.jdbc.Util;
import com.uws.dao.ISysMenuDAO;
import com.uws.dao.ISysRoleDAO;
import com.uws.dao.ISysRoleMenuDAO;
import com.uws.dao.ISysRoleUserDAO;
import com.uws.dao.ISysUserDAO;
import com.uws.model.SysMenu;
import com.uws.model.SysRole;
import com.uws.model.SysRoleMenu;
import com.uws.model.SysRoleUser;
import com.uws.model.SysUser;
import com.uws.service.ISysRoleService;
/**
 * 系统角色service实现类
 * @author hejin
 *
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService{

	@Autowired
	private ISysRoleDAO sysRoleDAO;
	
	@Autowired
	private ISysRoleMenuDAO sysRoleMenuDAO;
	
	@Autowired
	private ISysRoleUserDAO sysRoleUserDAO;
	
	@Autowired
	private ISysUserDAO sysUserDAO;
	
	@Autowired
	private ISysMenuDAO sysMenuDAO;
	
	
	/**
	 * 查询角色分页列表
	 * @param sRole 查询条件
	 * @param param 分页查询参数
	 * @return
	 */
	@Override
	public Page queryRoleList(Map<String, Object> param,SysRole sRole) {

		return sysRoleDAO.queryRoleList(param,sRole);
	}
	
	

	/**
	 * 根据角色id查询角色信息
	 * @param roleId 角色id
	 * @return
	 */
	@Override
	public SysRole queryRoleById(Long roleId) {
		// TODO Auto-generated method stub
		return sysRoleDAO.queryRoleById(roleId);
	}

	
	/**
	 * 根据角色编码查询角色信息
	 * @param roleCode 角色编码
	 * @return
	 */
	@Override
	public SysRole queryRoleByCode(String roleCode) {
		// TODO Auto-generated method stub
		return this.sysRoleDAO.queryRoleByCode(roleCode);
	}

	
	/**
	 * 保存角色信息
	 * @param sRole 角色信息实体
	 */
	@Override
	public void saveSysRole(SysRole sRole){
		this.sysRoleDAO.saveSysRole(sRole);
	}
	
	
	/**
	 * 删除角色信息
	 * @param sRole 角色信息实体
	 */
	@Override
	public void delSysRole(SysRole sRole){
		this.sysRoleDAO.delSysRole(sRole);
	}



	/**
	 * 根据用户id查询该用户所具有的的菜单权限
	 * @param userId 用户id
	 * @return List<Map> 菜单列表
	 */
	@Override
	public List<Map> getUserPermissionByUserId(String userId) {
			//用户与角色关系列表
			List<Map>  roleUserList=sysRoleUserDAO.queryRoleUserList("", userId);
			//角色id列表
			List<Long> roleIdList=sysRoleDAO.getUserRoleIdList(roleUserList);
			
		    return sysRoleMenuDAO.getSysRoleMenuListAll(roleIdList);
		
		
	}
	
	
	/**
	 * 根基角色id查询该角色的用户列表
	 * @param roleId
	 * @return
	 */
	@Override
	public List<Map> getSelectedUserList(String roleId){
		
		
		return sysRoleUserDAO.queryUserListByRoleId(roleId);
	}
	
	
	/**
	 * 根据用户id列表查询用户列表
	 * @param userIdList  用户id列表
	 * @return
	 */
	@Override
	public List<Map> getMyUserList(String userIdList){
		String[] arr=userIdList.split(",");
		int count =arr.length;
		List<Long> list=new ArrayList();
		for(int i=0;i<count;i++){
			if(DataUtil.isNotNull(arr[i])){
			   list.add(Long.parseLong(arr[i]));
			}
		}
		
		
		return sysRoleUserDAO.getMyUserList(list);
	}
	
	/**
	 * 根据角色id查询该角色权限下的所有菜单列表
	 * @param roleId 角色id
	 * @return
	 */
	@Override
	public List<Map> getMenuList(String roleId){
		
		
		return this.sysRoleMenuDAO.getSysRoleMenuList(roleId);
	}
	
	
	/**
	 * 查询所有已启用的菜单列表
	 * @return
	 */
	@Override
	public List<Map> getAllMenuList(){
		
		return this.sysMenuDAO.getAllMenuList();
	}
	
	
	/**
	 * 查询所有已启用的菜单列表
	 * @return
	 */
	@Override
	public List<Map> getAllMenusList(){
		
		return this.sysMenuDAO.getAllMenusList();
	}
	
	
	/**
	 * 查询用户分页数据
	 * @param param 分页参数
	 * @param sm 查询参数
	 * @return
	 */
	@Override
	public Page getUserPage(Map param,SysUser sm){
		
		return this.sysUserDAO.searchNSyS(param, sm);
	}
	
	
	/**
	 * 将所选的菜单分配给指定角色
	 * @param idsDel 要删除的菜单id列表
	 * @param idsAdd 要新增的菜单id列表
	 * @param roleId 角色id
	 */
	@Override
	public void updateRoleMenu(String idsDel,String idsAdd,String roleId){
		List<Long> idsDel01=new ArrayList();
		String[] idsDel02=idsDel.split(",");
		String[] idsAdd02=idsAdd.split(",");
		//将字符串中的id都转成long型
		if(idsDel02!=null && idsDel02.length>0){
			int count =idsDel02.length;
			for(int i=0;i<count;i++){
				if(DataUtil.isNotNull(idsDel02[i])){
				   
				   idsDel01.add(Long.parseLong(idsDel02[i]));
				}
			} 
		}
		
		//将字符串中的id都转成long型
		if(idsAdd02!=null && idsAdd02.length>0){
			int count =idsAdd02.length;
			for(int i=0;i<count;i++){
				if(DataUtil.isNotNull(idsAdd02[i])){
					
					SysRoleMenu sm=new SysRoleMenu();
					
					sm.setMenuId(Long.parseLong(idsAdd02[i]));
					sm.setRoleId(Long.parseLong(roleId));
					
					//新增一条角色与菜单关系
					this.sysRoleMenuDAO.saveSysRoleMenu(sm);
					
				}
			} 
		}
		
		if(idsDel01!=null && idsDel01.size()>0){
			//批量删除角色与菜单关系
			this.sysRoleMenuDAO.delRoleMenuMutil(Long.parseLong(roleId), idsDel01);
		    
		}
	}
	
	
	
	
	
	
	/**
	 * 将所选的用户分配给指定角色
	 * @param userList 用户id列表
	 * @param roleId 角色id
	 */
	@Override
	public void allotUser(String userList,String roleId){
		//查询该角色所有的用户与角色关系信息
        List<Map> list=this.sysRoleUserDAO.queryUserRoleListByRoleId(roleId);
		
        //插入新增的用户，剔除未选中的用户
		if(list.size()>0){
		
			List<Map> list01=new ArrayList();
			list01.addAll(list);
			if(userList!=null && userList!=""){
				
				
				
				if(DataUtil.isNull(userList) && list01!=null&&list01.size()>0){
					
					for(Map ml:list01){
						SysRoleUser mu=this.sysRoleUserDAO.querySysRoleUserById(ml.get("ID").toString());
						//删除未选中的角色与用户关系
						this.sysRoleUserDAO.delSysRoleUser(mu);
					}
				}else{
					String[] userArs=userList.split(",");
				
					int t=0;
					List<Map> list03=new ArrayList();
					for(int i=0;i<userArs.length;i++){
						
						Iterator<Map> it=list01.iterator();
						Map mru=null;
						while(it.hasNext()){
							mru=new HashMap();
							
							mru=it.next();
							if(DataUtil.isNotNull(userArs[i]) && userArs[i].equals(mru.get("USER_ID").toString())){
								
								t=t+1;
								it.remove();
							}
						}
						
						if(t==0){
							if(DataUtil.isNotNull(userArs[i])){
								SysRoleUser mu=new SysRoleUser();
							
								mu.setRoleId(Long.parseLong(roleId));
								mu.setUserId(Long.parseLong(userArs[i]));
								//新增一条新选中的用户与角色关系
								this.sysRoleUserDAO.saveSysRoleUser(mu);
							}
						}
						t=0;
					}
					
					if(list01!=null&&list01.size()>0){
					
						for(Map ml:list01){
							SysRoleUser mu=this.sysRoleUserDAO.querySysRoleUserById(ml.get("ID").toString());
							//删除未选中的角色与用户关系
							this.sysRoleUserDAO.delSysRoleUser(mu);
						}
					}
			  }
			}else{
				
				if(list01!=null ){
						for(Map mle:list01){
							SysRoleUser mu=this.sysRoleUserDAO.querySysRoleUserById(mle.get("ID").toString());
							//删除未选中的角色与用户关系
							this.sysRoleUserDAO.delSysRoleUser(mu);
							
						}
				}
				
			}
		}else{
            if(userList!=null && userList!=""){
            	String[] userArs=userList.split(",");
            	
				for(int i=0;i<userArs.length;i++){
					if(DataUtil.isNotNull(userArs[i])){
						SysRoleUser mu=new SysRoleUser();
						
						mu.setRoleId(Long.parseLong(roleId));
						mu.setUserId(Long.parseLong(userArs[i]));
						//新增一条新选中的用户与角色关系
						this.sysRoleUserDAO.saveSysRoleUser(mu);
					}
				}
				
			}
         
		}
		
	}
	
}
