package com.uws.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.uws.dao.ISysDicDao;
import com.uws.dao.ISysUserDAO;
import com.uws.model.SysUser;
import com.uws.service.ISysUserService;
/**
 * 用户管理 业务接口实现类
 * @author wangjun
 *
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
	/**
	 * 用户的dao层
	 */
	@Resource
	private ISysUserDAO sysUserDAO;
	/**
	 * 查询用户详情
	 * @param param sql的参数
	 * @return 
	 */
	public Page search(Map<String, Object> param){
		return sysUserDAO.search(param);
		
	}
	/**
	 * 保存一个对象
	 * @param SysUser SysUser的一个对象
	 * @return
	 */
	@Override
	public SysUser save(SysUser SysUser) {
		return sysUserDAO.saveUser(SysUser);
	}
	/**
	 * 查询用户详情  通过用户id
	 * @param param id 用户id
	 * @return 
	 */
	@Override
	public SysUser searchById(String id) {
		return sysUserDAO.searchById(id);
	}
	/**
	 * 查询用户List  搜索条件
	 * @param param 分页参数
	 * @param o     SysUser中的查询参数
	 * @return 
	 */
	@Override
	public Page search(Map param, SysUser o) {
		return sysUserDAO.search(param,o);
	}
	/**
	 * 修改指定用户 启用禁用状态
	 * @param id 用户表id
	 * @return 
	 */
	@Override
	public Boolean updateStatus(String id) {
		return sysUserDAO.updateStatus(id);
	}
	/**
	 * 删除指定用户 通过用户表id
	 * @param id 用户表id
	 * @return 
	 */
	@Override
	public Boolean deleteUserById(String id) {
		return sysUserDAO.deleteUserById(id);
	}
	/**
	 * 判断数据库是否已经存在此 帐号user_no 
	 * @param user_no 用户表的 帐号字段
     * @return
	 */
	@Override
	public Boolean checkDupliUser(String user_no) {
		return sysUserDAO.checkDupliUser(user_no);
	}
	/**
	 * 更新一个 用户数据
	 * @param SysUser 用户表 bn是(true)否需要从数据库获取密码
     * @return
	 */
	@Override
	public void update(SysUser SysUser,boolean bn) {
		sysUserDAO.updateUser(SysUser,bn);
	}
	/**
	 * 更新一个 用户的密码
	 * @param m 用户表实体  pwd_new 新密码
     * @return
	 */
	@Override
	public String updatePwd(String id,String pwd_old,String pwd_new) {
		return sysUserDAO.updatePwd( id, pwd_old, pwd_new);
	}
	/**
	 * 查询单个用户详情  通过用户帐号
	 * @param param user_no 用户user_no
	 * @return 
	 */
	@Override
	public SysUser searchByUser_No(String user_no) {
		return sysUserDAO.searchByUser_No(user_no);
	}
	/**
	 * 重置指定 用户密码 为系统默认初始密码  DEFAULT_PASSWORD
	 * @param id 用户表id
     * @return
	 */
	@Override
	public Boolean resetUserPwd(String id) {
		return sysUserDAO.resetUserPwd(id);
	}
	/**
	 * 判断值为id的用户   密码是否输入正确 
	 * @param id 用户id  pwd 用户密码
     * @return true 密码输入正确
	 */
	@Override
	public Boolean checkPwd(String id, String pwd) {
		return sysUserDAO.checkPwd(id,pwd);
	}
	
}
