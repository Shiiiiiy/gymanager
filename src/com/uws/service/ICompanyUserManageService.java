package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.Company;
import com.uws.model.Guser;
import com.uws.model.Person;
import com.uws.model.SysRole;
import com.uws.model.UserManageParam;

/**
 * 企业用户管理service接口
 * @author hejin
 *
 */
public interface ICompanyUserManageService {

	/**
	 * 查询企业用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 企业用户分页数据
	 */
	public Page queryCompanyModelUserList(Map<String, Object> param,UserManageParam ump);
	
	
	
	/**
	 * 根据用户id查询企业用户信息
	 * @param userId 角色id
	 * @return
	 */
	public Map<String,Object> queryUserCompanyModelById(String userId);
	
	
	/**
	 * 根据用户名查询用户基础信息
	 * @param username 用户名
	 * @return
	 */
	public Map<String,Object> queryGuserByUserName(String username);

	

	/**
	 * 保存企业用户信息
	 * @param ump 企业用户信息实体
	 */
	public void addCompanyModelUser(UserManageParam ump);
	

	/**
	 * 批量保存企业用户信息
	 * @param ump 企业用户信息实体
	 */
	public void addCompanyModelUsers(List<String> sql);
	
	/**
	 * 批量删除企业用户信息
	 * @param companyIdList 要删除的企业用户基础信息id列表
	 * @param guserIdList  要删除的企业用户详细信息id列表
	 */
	public void delCompanyModelUser(String companyIdList,String guserIdList);



	/**
	 * 保存用户基本信息
	 * @param guser 企业用户基础信息对象
	 * @param cm 企业用户详细信息对象
	 */
	public void saveCompanyModelUser(Guser guser, Company cm);
	

	/**
	 * 根据id查询企业用户基础信息对象
	 * @param guserId 主键
	 * @return
	 */
	public Guser getGuserById(String guserId);


	/**
	 * 根据id查询企业用户详细信息对象
	 * @param companyId 主键
	 * @return
	 */
	public Company getCompanyModelById(String companyId);



	/**
	 * 根查询企业用户信息列表(导出)
	 * @return
	 */
	List<Map<Integer, Object>> queryAllUserCompanyList(UserManageParam ump);


	/**
	 * 根据用户和名称查询检查是否重复
	 * @param guserId
	 * @param username
	 * @return
	 */
	public List<Guser> checkCompanyUsername(String guserId,String username,String userType);



	public List checkCompanyUsernames(List<String> lendReco,String userType);
	
}
