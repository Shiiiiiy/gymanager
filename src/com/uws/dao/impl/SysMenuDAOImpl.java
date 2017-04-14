package com.uws.dao.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.model.BaseModel;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.dao.ISysDicDao;
import com.uws.dao.ISysMenuDAO;
import com.uws.dao.ISysRoleUserDAO;
import com.uws.model.Dic;
import com.uws.model.SysMenu;

/**
 * 菜单表 数据操作
 * @author wangjun
 *
 */
@Repository
public class SysMenuDAOImpl extends BaseDAOImpl implements ISysMenuDAO{
	/**dic的dao层*/
	@Resource
	private  ISysDicDao dicDao;
	/**
	 * 删除指定菜单  通过菜单id
	 * @param id 菜单表id
     * @return
	 */
	@Override
	public Boolean deleteMenuById(String id) {
		try {
		//同时删除其下的子菜单
		deleteMenuTree(searchById(id));
		return true;
		} catch (Exception e) {e.printStackTrace();return false;}
	}
	/**
	 * 通过一个菜单 删除其下树形子菜单 递归
	 * @param Parent 系统菜单对象
     * @return
	 * */
	public void deleteMenuTree(SysMenu Parent) {
		delete(Parent);
		List<SysMenu> mm=getMenuListById(Parent.getId()+"");//自己的子菜单
		if(mm.size()>0){
			for (int i = 0; i < mm.size(); i++) {
				SysMenu Children=mm.get(i);
				deleteMenuTree(Children);
			}
		}
	}
	/**
	 * 保存一个系统菜单 实体
	 * @param M 系统菜单对象
     * @return
	 */
	@Override
	public SysMenu saveMenu(SysMenu o) {
		return (SysMenu) save(o) ;
	}
	/**
	 * 保存一个系统菜单 修改的实体
	 * @param M 系统菜单对象
     * @return
	 */
	@Override
	public void updateMenu(SysMenu m) {
		SysMenu O=searchById(m.getId()+"");
		O.setMenu_code(m.getMenu_code());
		O.setRemark(m.getRemark());
		O.setTitle(m.getTitle());
		O.setUrl(m.getUrl());
		O.setStatus(m.getStatus());
		update(O);
	}
	/**
	 * 通过自身id  获取自身实体类
	 * @param id  自身id
	 * @param 
	 */
	@Override
	public SysMenu searchById(String id) {
		return (SysMenu) this.get(SysMenu.class,Long.parseLong(id));
	}
	/**
	 * 通过复数 id  返回 菜单集合
	 * @param id  自身id
	 * @param 
	 */
	@Override
	public List<Map> searchByIds(List<Long> ids) {
		StringBuffer sql = new StringBuffer("SELECT * FROM SYS_MENU A WHERE 1 = 1 ");
		List<Object> values=new ArrayList();
		if(ids!=null && ids.size()>0){
			sql.append(" AND A.ID IN ( ?  ");
			values.add(ids.get(0));
		}
		for (int i = 1; i < ids.size(); i++) {
			sql.append(" , ? ");
			values.add(ids.get(i));
		}
		sql.append(" ) ");
		return SelectAll(sql.toString(),values.toArray());
	}
	/**
	 * 改变指定菜单 启用禁用状态
	 * @param id 菜单表id
     * @return
	 */
	@Override
	public Boolean updateStatus(String id) {
		Boolean bl=false;
		if(DataUtil.isNotNull(id)){
		SysMenu O=searchById(id);
		try{
		
		Long ids=O.getStatus();
		String code=dicDao.findById(ids+"").getCode();
		List<Dic> Dics=dicDao.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		Dic ENABLE=null,DISABLE=null;
		for (int i = 0; i < Dics.size(); i++) {
			if("ENABLE".equals(Dics.get(i).getCode())) {ENABLE=Dics.get(i);}
			else if("DISABLE".equals(Dics.get(i).getCode())){DISABLE=Dics.get(i);}
		}
		if("DISABLE".equals(code)){
			O.setStatus( ENABLE.getId());
			this.update(O);   bl=true;
			updateStatusTree(O,ENABLE);  //递归 子树同样改状态
		}else if("ENABLE".equals(code)){
			O.setStatus( DISABLE.getId() );
			this.update(O);   bl=true;
			updateStatusTree(O,DISABLE); //递归 子树同样改状态
		}
		
		}catch(Exception e){e.printStackTrace();}}
		return bl;
	}
	/**
	 * 通过一个父菜单 	  启用/禁用其下树形子菜单 递归
	 * @param Parent 系统菜单对象   dic 将要改为的状态值
     * @return
	 * */
	public void updateStatusTree(SysMenu Parent,Dic dic) {
		List<SysMenu> mm=getMenuListById(Parent.getId()+"");//自己的子菜单
		if(mm.size()>0){
			for (int i = 0; i < mm.size(); i++) {
				SysMenu Children=mm.get(i);
				Children.setStatus(dic.getId());//子树改状态
				this.update(Children);
				updateStatusTree(Children,dic);
			}
		}
	}
	/**
	 * 通过菜单父id  返回其节点列表
	 * @param id  父单菜id
	 * @param 
	 */
	@Override
	public List<SysMenu> getMenuListById(String id) {
		List<Object> parm = new ArrayList<Object>();
		String hql = " FROM SysMenu D WHERE D.p_id = ? ";
		parm.add(Long.parseLong(id));
		List<BaseModel> list = this.find(hql,parm.toArray());		
		List<SysMenu> returnList = new ArrayList<SysMenu>();
		for (BaseModel baseModel : list) {
			returnList.add((SysMenu)baseModel);
		}
		return returnList;
	}

	/**
	 * 通过菜单父id  返回其节点列表
	 * @param parentCode  菜单父id
	 */
	@Override
	public List<Map> getMenuListByParentCode(String parentCode) {
		StringBuffer sql = new StringBuffer("SELECT * FROM SYS_MENU A WHERE 1 = 1 ");
		List<Object> values=new ArrayList();
		if(!"".equals(parentCode) && parentCode!=null ){
			sql.append(" AND A.P_ID = ? ");
			values.add(parentCode);
		}else{
			sql.append(" AND A.P_ID IS NULL ");
			
		}
		return SelectAll(sql.toString(),values.toArray());
	}
	/**
	 * 获取根菜单实体
	 * @param 
	 */
	@Override
	public SysMenu getRootMenu() {
		List<Object> parm = new ArrayList<Object>();
		String hql = " FROM SysMenu D WHERE D.p_id IS NULL ";
		List<BaseModel> list = this.find(hql,parm.toArray());		
		if(list!=null && list.size()>0){
			return (SysMenu) list.get(0);
		}
		return null;
	}

	
	/**
	 * 查询所有已启用的菜单列表（菜单树）
	 * @return
	 */
	@Override
	public List<Map> getAllMenuList(){
		Dic dic=dicDao.getDicByCodes(DefaultValue.ENABLE_DISABLE, "ENABLE");
		StringBuffer sql = new StringBuffer("SELECT ID AS id,P_ID AS pId,TITLE AS name FROM SYS_MENU  WHERE 1 = 1 ");
		
		sql.append(" AND STATUS = ? ORDER BY REC_TIME  ");
		
		return this.SelectAll(sql.toString(), new Object[]{dic.getId()});
	}
	
	
	/**
	 * 查询所有已启用的菜单列表()
	 * @return
	 */
	@Override
	public List<Map> getAllMenusList(){
		Dic dic=dicDao.getDicByCodes(DefaultValue.ENABLE_DISABLE, "ENABLE");
		StringBuffer sql = new StringBuffer("SELECT * FROM SYS_MENU  WHERE 1 = 1 ");
		
		sql.append(" AND STATUS = ? ORDER BY REC_TIME  ");
		
		return this.SelectAll(sql.toString(), new Object[]{dic.getId()});
	}
	/**
	 * 判断数据库是否已经存在此  MenuCode
	 * @param code 菜单表code
     * @return true 有重名
	 */
	@Override
	public Boolean checkDupliMenuCode(String code) {
		List<String> parm = new ArrayList<String>();
		String hql = " FROM SysMenu D WHERE D.menu_code = ? ";
		parm.add(code);
		List<BaseModel> list = find(hql,parm.toArray());
		if(list==null || list.size()<=0){return false;}
		return true;
	}
	/**
	 * 从dic获取  菜单树形结构 dtree
	 * @return 
	 * */
	@Override
	public List<Map> Dtree() {
		String sql = "SELECT ID,IFNULL(P_ID,0) P_ID,`TITLE` FROM SYS_MENU ORDER BY REC_TIME  ";
		return this.SelectAll(sql);
	}
	/**
	 * 判断当前菜单是否是root下 的2级菜单
	 * @param id 菜单表id
	 * @return 
	 * */
	@Override
	public Boolean isLastMenu(String id) {
		Boolean bl=false;
		try {
			SysMenu M2=searchById(id);
			SysMenu M1=searchById( M2.getP_id()+"" );
			SysMenu Root=searchById( M1.getP_id()+"" );
			if("null".equals( Root.getP_id()+"")){ bl=true; }
		} catch (Exception e) {}		
		return bl;
	}
}
