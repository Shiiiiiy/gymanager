package com.uws.dao.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.model.BaseModel;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.dao.ISysDicDao;
import com.uws.dao.ISysRoleUserDAO;
import com.uws.dao.ISysUserDAO;
import com.uws.model.Dic;
import com.uws.model.SysMenu;
import com.uws.model.SysUser;
import com.uws.util.Util;
/**
 * 用户表数据操作
 * @author wangjun
 *
 */
@Repository
public class SysUserDAOImpl extends BaseDAOImpl implements ISysUserDAO{
	/**dic的dao层*/
	@Resource
	private  ISysDicDao dicDao;
	/**角色用户的dao层*/
	@Resource
	private ISysRoleUserDAO sysRoleUserDAO;
	/**
	 * 查询用户详情
	 * @param param sql的参数
	 * @return 
	 */
	public Page search(Map<String, Object> param){
		String sql = "SELECT * FROM SYS_USER  ";
		sql+=" ORDER BY A.REC_TIME DESC ";
		param.put("sql", sql);
		Page page = queryBySql(param);
		return page;
	}
	/**
	 * 保存一个对象
	 * @param SysUser SysUser的一个对象
	 * @return
	 */
	@Override
	public SysUser saveUser(SysUser o) {
		if(DataUtil.isNotNull(o.getPwd()) ){//密文
			o.setPwd_id( Util.encryptMD5(o.getPwd()));}
		return (SysUser) save(o) ;
	}
	/**
	 * 查询用户详情  通过用户id
	 * @param param id 用户id
	 * @return 
	 */
	@Override
	public SysUser searchById(String id) {
		return (SysUser) this.get(SysUser.class,Long.parseLong(id));
	}
	/**
	 * 查询用户List  搜索条件
	 * @param param 分页参数
	 * @param o     SysUser中的查询参数
	 * @return 
	 */
	@Override
	public Page search(Map param, SysUser o) {
		StringBuffer sql = new StringBuffer(" SELECT * FROM SYS_USER A WHERE 1=1 ");
		
		if(DataUtil.isNotNull(o.getUser_no())){//帐号
			sql.append("AND A.USER_NO LIKE '%"+o.getUser_no()+"%' ");
		}
		if(DataUtil.isNotNull(o.getName())){//用户名
			sql.append("AND A.NAME LIKE '%"+o.getName()+"%' ");
		}
		if(o.getStatus()!=null && DataUtil.isNotNull(o.getStatus()) && o.getStatus()>0){//状态
			sql.append("AND A.STATUS = "+o.getStatus()+" ");
		}
		sql.append(" ORDER BY A.REC_TIME DESC ");
		param.put("sql", sql.toString());
		Page page = queryBySql(param);
		return page;
	}
	/**
	 * 修改指定用户 启用禁用状态
	 * @param id 用户表id
	 * @return 
	 */
	@Override
	public Boolean updateStatus(String id) {
		Boolean bl=false;
		if(DataUtil.isNotNull(id)){
		SysUser O=searchById(id);
		try{
		Long tid=O.getStatus();
		String code=dicDao.findById(tid+"").getCode();
		List<Dic> Dics=dicDao.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		Dic ENABLE=null,DISABLE=null;
		for (int i = 0; i < Dics.size(); i++) {
			if("ENABLE".equals(Dics.get(i).getCode())) {ENABLE=Dics.get(i);}
			else if("DISABLE".equals(Dics.get(i).getCode())){DISABLE=Dics.get(i);}
		}
		if("DISABLE".equals(code)){
			O.setStatus( ENABLE.getId());
		}else if("ENABLE".equals(code)){
			O.setStatus( DISABLE.getId() );
		}
		this.update(O);bl=true;
		}catch(Exception e){e.printStackTrace();}}
		return bl;
	}
	/**
	 * 删除指定用户 通过用户表id
	 * @param id 用户表id
	 * @return 
	 */
	@Override
	public Boolean deleteUserById(String id) {
		try {delete(searchById(id));
		//同时删除 用户-角色表
		sysRoleUserDAO.delSysRoleUserByUserID(id);
		return true;
		} catch (Exception e) {e.printStackTrace();return false;}
	}
	/**
	 * 判断数据库是否已经存在此 帐号user_no 
	 * @param user_no 用户表的 帐号字段
     * @return
	 */
	@Override
	public Boolean checkDupliUser(String user_no) {
		List<String> parm = new ArrayList<String>();
		String hql = " FROM SysUser D WHERE D.user_no = ? ";
		parm.add(user_no);
		List<BaseModel> list = find(hql,parm.toArray());
		if(list==null || list.size()<=0){return false;}
		return true;
	}
	/**
	 * 更新一个 用户数据
	 * @param SysUser 用户表   bn是(true)否需要从数据库获取密码
     * @return
	 */
	@Override
	public void updateUser(SysUser M, boolean bn ) {
		SysUser O=searchById(M.getId()+"");
		O.setEmail(M.getEmail());
		O.setName(M.getName());
		O.setStatus(M.getStatus());
		O.setTel_no(M.getTel_no());
		O.setUser_no(M.getUser_no());
		O.setImage_name(M.getImage_name());
		O.setImage_url(M.getImage_url());
		if(!bn){
			O.setPwd(M.getPwd());
			O.setPwd_id(Util.encryptMD5(M.getPwd()));
		}
		update(O);
	}
	/**
	 * 更新一个 用户的密码
	 * @param m 用户表实体  pwd_new 新密码
     * @return
	 */
	@Override
	public String updatePwd(String id,String pwd_old,String pwd_new) {
		String msg="";SysUser O=searchById(id);
		if(!(Util.encryptMD5(pwd_old).equals(O.getPwd_id()))){
			return "原密码错误";
		}else{
			if(DataUtil.isNotNull(pwd_new)){
			O.setPwd(pwd_new);
			O.setPwd_id(Util.encryptMD5(pwd_new));
			update(O);msg="密码修改成功";}
		}
		return msg;
	}
	/**
	 * 查询单个用户详情  通过用户帐号
	 * @param param user_no 用户user_no
	 * @return 
	 */
	@Override
	public SysUser searchByUser_No(String user_no) {
		List<Object> parm = new ArrayList<Object>();
		String hql = " FROM SysUser D WHERE D.user_no = ? ";
		parm.add(user_no);
		List<BaseModel> list = this.find(hql,parm.toArray());	
		if(list==null || list.size()<=0){return null;}
		SysUser M=(SysUser) list.get(0);
		return M;
	}
	/**
	 * 重置指定 用户密码 为系统默认初始密码  DEFAULT_PASSWORD
	 * @param id 用户表id
     * @return
	 */
	@Override
	public Boolean resetUserPwd(String id) {
		try{SysUser M=this.searchById(id);
			M.setPwd(DefaultValue.DEFAULT_PASSWORD);
			M.setPwd_id(Util.encryptMD5(DefaultValue.DEFAULT_PASSWORD));
			update(M);return true;
		}catch(Exception e){e.printStackTrace();}
		return false;
	}
	/**
	 * 判断值为id的用户   密码是否输入正确 
	 * @param id 用户id  pwd 用户密码
     * @return true 密码输入正确
	 */
	@Override
	public Boolean checkPwd(String id, String pwd) {
		try{ SysUser M=this.searchById(id);
		if(Util.encryptMD5(pwd).equals(M.getPwd_id())){ return true; }
		else{return false;}
		}catch(Exception e){e.printStackTrace();}
		return false;
	}
	/**
	 * 查询用户List  搜索条件 非管理员的用户
	 * @param param 分页参数
	 * @param o  SysUser中的查询参数
	 * @return 
	 */
	@Override
	public Page searchNSyS(Map param, SysUser o) {
		StringBuffer sql = new StringBuffer(" SELECT * FROM SYS_USER A WHERE 1=1 ");
		
		if(DataUtil.isNotNull(o.getUser_no())){//帐号
			sql.append("AND A.USER_NO LIKE '%"+o.getUser_no()+"%' ");
		}
		if(DataUtil.isNotNull(o.getName())){//用户名
			sql.append("AND A.NAME LIKE '%"+o.getName()+"%' ");
		}
		if(o.getStatus()!=null && DataUtil.isNotNull(o.getStatus()) && o.getStatus()>0){//状态
			sql.append("AND A.STATUS = "+o.getStatus()+" ");
		}
		
		sql.append(" ORDER BY A.REC_TIME DESC ");
		param.put("sql", sql.toString());
		Page page = queryBySql(param);
		return page;
	}
	
	
}
