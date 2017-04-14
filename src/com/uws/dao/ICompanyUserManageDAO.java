package com.uws.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.Company;
import com.uws.model.Guser;
import com.uws.model.UserManageParam;

/**
 * 企业用户管理dao接口
 * @author hejin
 *
 */
public interface ICompanyUserManageDAO {

	/**
	 * 查询企业用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 企业用户分页数据
	 */
	public Page queryCompanyModelUserList(Map<String, Object> param,UserManageParam ump);
	
	
	/**
	 * 根据用户id查询企业用户信息
	 * @param userId 用户id
	 * @return
	 */
	public Map<String,Object> queryUserCompanyModelById(String userId);
	
	
	/**
	 * 根据用户名查询用户基础信息
	 * @param username 用户名
	 * @return
	 */
	public Map<String, Object> queryGuserByUserName(String username);

	

	/**
	 * 保存用户基础信息
	 * @param guser 用户基础信息实体
	 */
	public void saveGuser(Guser guser);

	
	/**
	 * 保存企业用户详细信息
	 * @param cm 企业用户详细信息实体
	 */
	public void saveCompanyModel(Company cm);
	
	/**
	 * 保存用户基础信息
	 * @param guser 用户基础信息实体
	 */
	public void updateGuser(Guser guser);

	
	/**
	 * 保存企业用户详细信息
	 * @param cm 企业用户详细信息实体
	 */
	public void updateCompanyModel(Company cm);

	/**
	 * 批量删除企业用户详细信息
	 * @param companyIdList 要删除的企业用户id列表
	 */
	public void delCompanyModel(String companyIdList);
	
	
	/**
	 * 批量删除用户基础信息
	 * @param guserIdList 要删除的用户基础信息id列表
	 */
	public void delGuser(String guserIdList);


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
	 * 批量保存企业用户信息
	 * @param ump 企业用户信息实体
	 */
	public void addCompany(List<String> sql);
	
	/**
	 * 根查询企业用户信息列表(导出)
	 * @return
	 */
	List<Map<Integer, Object>> queryAllUserCompanyList(UserManageParam ump);


	public List checkCompanyUsername(String guserId, String username,String userType);
	
	public List checkCompanyUsernames(List<String> usernames,String userType);
}
