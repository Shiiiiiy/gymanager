package com.uws.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.Guser;
import com.uws.model.Institution;
import com.uws.model.Person;
import com.uws.model.UserManageParam;

/**
 * 机构用户管理dao接口
 * @author hejin
 *
 */
public interface IInstitutionUserManageDAO {

	/**
	 * 查询机构用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 机构用户分页数据
	 */
	public Page queryInstitutionUserList(Map<String, Object> param,UserManageParam ump);
	
	
	/**
	 * 根据用户id查询机构用户信息
	 * @param userId 用户id
	 * @return
	 */
	public Map<String,Object> queryUserInstitutionById(String userId);
	
	
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
	 * 保存机构用户详细信息
	 * @param person 机构用户详细信息实体
	 */
	public void saveInstitution(Institution institution);
	
	/**
	 * 保存用户基础信息
	 * @param guser 用户基础信息实体
	 */
	public void updateGuser(Guser guser);

	
	/**
	 * 保存企业用户详细信息
	 * @param cm 企业用户详细信息实体
	 */
	public void updateInstitution(Institution institution);
	/**
	 * 批量删除机构用户详细信息
	 * @param institutionList 要删除的机构用户id列表
	 */
	public void delInstitution(String institutionList);
	
	
	/**
	 * 批量删除用户基础信息
	 * @param guserIdList 要删除的用户基础信息id列表
	 */
	public void delGuser(String guserIdList);


	/**
	 * 根据id查询机构用户基础信息对象
	 * @param guserId 主键
	 * @return
	 */
	public Guser getGuserById(String guserId);


	/**
	 * 根据id查询机构用户详细信息对象
	 * @param institutionId 主键
	 * @return
	 */
	public Institution getInstitutionById(String institutionId);


	/**
	 * 根查询机构用户信息列表(导出)
	 * @return
	 */
	List<Map<Integer, Object>> queryAllUserInstitutionList(UserManageParam ump);


	public void addInstitutionUsers(List<String> lendReco);
	
	
	
}
