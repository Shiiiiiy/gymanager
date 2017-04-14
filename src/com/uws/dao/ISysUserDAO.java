package com.uws.dao;

import java.util.Map;

import com.base.dao.Page;
import com.uws.model.SysUser;
/**
 * 用户表 数据操作
 * @author wangjun
 *
 */
public interface ISysUserDAO {

	/**
	 * 查询用户详情
	 * @param param sql的参数
	 * @return 
	 */
	public Page search(Map<String, Object> param);
	/**
	 * 保存一个对象
	 * @param SysUser SysUser的一个对象
	 * @return
	 */
	public SysUser saveUser(SysUser SysUser);
	/**
	 * 查询用户详情  通过用户id
	 * @param param id 用户id
	 * @return 
	 */
	public SysUser searchById(String id);
	/**
	 * 查询用户List  搜索条件
	 * @param param 分页参数
	 * @param o     SysUser中的查询参数
	 * @return 
	 */
	public Page search(Map param, SysUser o);
	/**
	 * 修改指定用户 启用禁用状态
	 * @param id 用户表id
	 * @return 
	 */
	public Boolean updateStatus(String id);
	/**
	 * 删除指定用户 通过用户表id
	 * @param id 用户表id
	 * @return 
	 */
	public Boolean deleteUserById(String id);
	/**
	 * 判断数据库是否已经存在此 帐号user_no 
	 * @param user_no 用户表的 帐号字段
     * @return
	 */
	public Boolean checkDupliUser(String user_no);
	/**
	 * 更新一个 用户数据
	 * @param SysUser 用户表  bn是(true)否需要从数据库获取密码
     * @return
	 */
	public void updateUser(SysUser SysUser,boolean bn);
	/**
	 * 更新一个 用户的密码
	 * @param m 用户表实体  pwd_new 新密码
     * @return
	 */
	public String updatePwd(String id,String pwd_old,String pwd_new);
	/**
	 * 查询单个用户详情  通过用户帐号
	 * @param param user_no 用户user_no
	 * @return 
	 */
	public SysUser searchByUser_No(String user_no);
	/**
	 * 重置指定 用户密码 为系统默认初始密码  DEFAULT_PASSWORD
	 * @param id 用户表id
     * @return
	 */
	public Boolean resetUserPwd(String id);
	/**
	 * 判断值为id的用户   密码是否输入正确 
	 * @param id 用户id  pwd 用户密码
     * @return true 密码输入正确
	 */
	public Boolean checkPwd(String id, String pwd);
	/**
	 * 查询用户List  搜索条件 非管理员的用户
	 * @param param 分页参数
	 * @param o     SysUser中的查询参数
	 * @return 
	 */
	public Page searchNSyS(Map param, SysUser o);
}
