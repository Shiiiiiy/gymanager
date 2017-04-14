package com.uws.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.util.DefaultValue;
import com.uws.dao.ICompanyUserManageDAO;
import com.uws.model.Company;
import com.uws.model.Guser;
import com.uws.model.Person;
import com.uws.model.UserManageParam;
import com.uws.service.ICompanyUserManageService;
import com.uws.service.IPersonUserManageService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;
import com.uws.util.Util;
/**
 * 企业用户管理service实现类
 * @author hejin
 *
 */
@Service("companyUserManageService")
public class CompanyUserManageServiceImpl implements  ICompanyUserManageService{


	
	@Autowired
	private ICompanyUserManageDAO companyUserManageDAO;
	
	@Autowired
	private ISysDicService iSysDicService;
	
	
	
	/**
	 * 查询企业用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 企业用户分页数据
	 */
	@Override
	public Page queryCompanyModelUserList(Map<String, Object> param,
			UserManageParam ump) {
		// TODO Auto-generated method stub
		return companyUserManageDAO.queryCompanyModelUserList(param, ump);
	}

	/**
	 * 根据用户id查询企业用户信息
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public Map<String, Object> queryUserCompanyModelById(String userId) {
		// TODO Auto-generated method stub
		return companyUserManageDAO.queryUserCompanyModelById(userId);
	}
	

	/**
	 * 根据用户名查询用户基础信息
	 * @param username 用户名
	 * @return
	 */
	@Override
	public Map<String, Object> queryGuserByUserName(String username) {
		// TODO Auto-generated method stub
		return companyUserManageDAO.queryGuserByUserName(username);
	}

	/**
	 * 新增企业用户信息
	 * @param ump 企业用户信息实体
	 */
	@Override
	public void addCompanyModelUser(UserManageParam ump) {
		//iSysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_ENABLE).getId()
		if(ump!=null){
			Guser guser=new Guser();
			Company cm=new Company();
			
			String guserId=UUID.randomUUID().toString().replace("-", "");
			guser.setId(guserId);
			guser.setCreateTime(new Date());
			guser.setUpdateTime(new Date());
			if(ump.getStatus() != null && !ump.getStatus().equals("")){
				guser.setStatus(ump.getStatus());
			}else{
				guser.setStatus(this.iSysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE).getId());
			}
			guser.setUsertype(Constants.USERTYPE_COMPANY);
			guser.setImage(ump.getImage());
			
			cm.setCreate_time(new Date());
			cm.setUpdate_time(new Date());
			//cm.setStatus(ump.getStatus());
			cm.setUseridstr(guserId);
			
			
			//用户基本信息
			//用户名
			if(!Util.isNull(ump.getUsername())){
				guser.setUsername(ump.getUsername());
			}
			//用户密码
			if(!Util.isNull(ump.getPassword())){
				guser.setPassword(Util.encryptMD5(ump.getPassword()));
			}
			
			//用户头像
			if(!Util.isNull(ump.getImage())){
				guser.setImage(ump.getImage());
			}
			
			//用户备注
			if(!Util.isNull(ump.getComment())){
				guser.setComments(ump.getComment());
			}
			
			//用户详细信息----------------------------
			
			//企业名称
			if(!Util.isNull(ump.getCp_name())){
				cm.setCp_name(ump.getCp_name());
			}
			//企业联系人
			if(!Util.isNull(ump.getCpLink())){
				cm.setCp_linkman(ump.getCpLink());
			}
			
			//企业联系人邮箱
			if(!Util.isNull(ump.getCpLinkEmail())){
				cm.setCp_linkemail(ump.getCpLinkEmail());
			}
			
			
			//企业联系人手机号码
			if(!Util.isNull(ump.getCpLinkTel())){
				cm.setCp_linktel(ump.getCpLinkTel());
			}
			
			//企业座机号码
			if(!Util.isNull(ump.getCpPhone())){
				cm.setCp_phone(ump.getCpPhone());
			}
			
			//企业组织机构代码
			if(!Util.isNull(ump.getCpOrgCode())){
				cm.setCp_orgcode(ump.getCpOrgCode());
			}
			
			//企业社会信用代码
			if(!Util.isNull(ump.getCpSocialCode())){
				cm.setCp_socialcode(ump.getCpSocialCode());
			}
			
			//企业所在地
			if(!Util.isNull(ump.getCpLocation())){
				cm.setCp_address(ump.getCpLocation());
			}
			
			//企业所在省
			if(!Util.isNull(ump.getCpProvince())){
				cm.setCp_provincestr(ump.getCpProvince());
			}
			
			//企业所在市
			if(!Util.isNull(ump.getCpCity())){
				cm.setCp_citystr(ump.getCpCity());
			}
			
			//企业所在区
			if(!Util.isNull(ump.getCpArea())){
				cm.setCp_location(ump.getCpLocation());
			}
			
			
			companyUserManageDAO.saveGuser(guser);
			companyUserManageDAO.saveCompanyModel(cm);
		}
		
		
		
	}
	
	/**
	 * 保存用户基本信息
	 * @param guser 企业用户基础信息对象
	 * @param cm 企业用户详细信息对象
	 */
	@Override
	public void saveCompanyModelUser(Guser guser,Company cm){
		
		companyUserManageDAO.updateGuser(guser);
		if(cm!=null){
		    companyUserManageDAO.updateCompanyModel(cm);
		}
	}
	

	/**
	 * 批量删除企业用户信息
	 * @param companyIdList 要删除的企业用户基础信息id列表
	 * @param guserIdList  要删除的企业用户详细信息id列表
	 */
	@Override
	public void delCompanyModelUser(String companyIdList, String guserIdList) {
		// TODO Auto-generated method stub
		companyUserManageDAO.delGuser(guserIdList);
		companyUserManageDAO.delCompanyModel(companyIdList);
	}

	/**
	 * 根据id查询企业用户基础信息对象
	 * @param guserId 主键
	 * @return
	 */
	@Override
	public Guser getGuserById(String guserId) {
		// TODO Auto-generated method stub
		return companyUserManageDAO.getGuserById(guserId);
	}

	/**
	 * 根据id查询企业用户详细信息对象
	 * @param companyId 主键
	 * @return
	 */
	@Override
	public Company getCompanyModelById(String companyId) {
		// TODO Auto-generated method stub
		return companyUserManageDAO.getCompanyModelById(companyId);
	}

	
	/**
	 * 根查询企业用户信息列表(导出)
	 * @return
	 */
	@Override
	public List<Map<Integer, Object>> queryAllUserCompanyList(UserManageParam ump){
		return companyUserManageDAO.queryAllUserCompanyList(ump);
	}

	@Override
	public List checkCompanyUsername(String guserId, String username,String userType) {
		// TODO Auto-generated method stub
		return companyUserManageDAO.checkCompanyUsername(guserId,username,userType);
	}

	@Override
	public void addCompanyModelUsers(List<String> sqlAry) {
		 
		companyUserManageDAO.addCompany(sqlAry);
	}

	@Override
	public List checkCompanyUsernames(List<String> lendReco,String userType) {
		 
		return companyUserManageDAO.checkCompanyUsernames(lendReco,userType);
	}
	
}
