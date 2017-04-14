package com.uws.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.util.DefaultValue;
import com.uws.dao.IInstitutionUserManageDAO;
import com.uws.model.Guser;
import com.uws.model.Institution;
import com.uws.model.UserManageParam;
import com.uws.service.IInstitutionUserManageService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;
import com.uws.util.Util;
/**
 * 机构用户管理service实现类
 * @author hejin
 *
 */
@Service("institutionUserManageService")
public class InstitutionUserManageServiceImpl implements  IInstitutionUserManageService{

	@Autowired
	private IInstitutionUserManageDAO institutionUserManageDAO;
	
	@Autowired
	private ISysDicService iSysDicService;
	
	
	/**
	 * 查询机构用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 机构用户分页数据
	 */
	@Override
	public Page queryInstitutionUserList(Map<String, Object> param,
			UserManageParam ump) {
		// TODO Auto-generated method stub
		return institutionUserManageDAO.queryInstitutionUserList(param, ump);
	}

	/**
	 * 根据用户id查询机构用户信息
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public Map<String, Object> queryUserInstitutionById(String userId) {
		// TODO Auto-generated method stub
		return institutionUserManageDAO.queryUserInstitutionById(userId);
	}
	

	/**
	 * 根据用户名查询用户基础信息
	 * @param username 用户名
	 * @return
	 */
	@Override
	public Map<String, Object> queryGuserByUserName(String username) {
		// TODO Auto-generated method stub
		return institutionUserManageDAO.queryGuserByUserName(username);
	}

	
	
	/**
	 * 保存用户基本信息
	 * @param guser 机构用户基础信息对象
	 * @param institution 机构用户详细信息对象
	 */
	@Override
	public void saveInstitutionUser(Guser guser,Institution institution){
		
		institutionUserManageDAO.updateGuser(guser);
		if(institution!=null){
			institutionUserManageDAO.updateInstitution(institution);
		}
	}
	

	/**
	 * 批量删除机构用户信息
	 * @param institutionIdList 要删除的机构用户基础信息id列表
	 * @param guserIdList  要删除的机构用户详细信息id列表
	 */
	@Override
	public void delInstitutionUser(String institutionIdList, String guserIdList) {
		// TODO Auto-generated method stub
		institutionUserManageDAO.delGuser(guserIdList);
		institutionUserManageDAO.delInstitution(institutionIdList);
	}

	/**
	 * 根据id查询机构用户基础信息对象
	 * @param guserId 主键
	 * @return
	 */
	@Override
	public Guser getGuserById(String guserId) {
		// TODO Auto-generated method stub
		return institutionUserManageDAO.getGuserById(guserId);
	}

	/**
	 * 根据id查询机构用户详细信息对象
	 * @param institutionId 主键
	 * @return
	 */
	@Override
	public Institution getInstitutionById(String institutionId) {
		// TODO Auto-generated method stub
		return institutionUserManageDAO.getInstitutionById(institutionId);
	}

	@Override
	public List<Map<Integer, Object>> queryAllUserInstitutionList(UserManageParam ump) {
		// TODO Auto-generated method stub
		return institutionUserManageDAO.queryAllUserInstitutionList(ump);
	}

	@Override
	public void addInstitutionUsers(List<String> lendReco) {
		 
		institutionUserManageDAO.addInstitutionUsers(lendReco);
	}

	/**
	 * 新增机构用户信息
	 * @param ump 机构用户信息实体
	 */
	@Override
	public void addInstitutionUser(UserManageParam ump) {
		
		if(ump!=null){
			Guser guser=new Guser();
			Institution institution=new Institution();
			
			String guserId=UUID.randomUUID().toString().replace("-", "");
			guser.setId(guserId);
			guser.setCreateTime(new Date());
			guser.setUpdateTime(new Date());
			guser.setStatus(ump.getStatus());
			guser.setUsertype(Constants.USERTYPE_INSTITUTION);
			guser.setImage(ump.getImage());
			if(ump.getStatus() != null && !ump.getStatus().equals("")){
				guser.setStatus(ump.getStatus());
			}else{
				guser.setStatus(this.iSysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE).getId());
			}
			institution.setCreateTime(new Date());
			institution.setUpdateTime(new Date());
			institution.setStatus(ump.getStatus());
			institution.setUserId(guserId);
			
			
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
			
			//用户信息备注
			if(!Util.isNull(ump.getComment())){
				guser.setComments(ump.getComment());;
			}
			
			
			//用户详细信息----------------------------
			
			//机构用户名称
			if(!Util.isNull(ump.getNameIn())){
				institution.setName(ump.getNameIn());
			}
			//机构联系人
			if(!Util.isNull(ump.getLinkMan())){
				institution.setLinkman(ump.getLinkMan());
			}
			
			//用户座机
			if(!Util.isNull(ump.getPhoneIn())){
				institution.setPhone(ump.getPhoneIn());
			}
			
			
			//机构用户手机
			if(!Util.isNull(ump.getTelIn())){
				institution.setTel(ump.getTelIn());
			}
			
			//用户邮箱
			if(!Util.isNull(ump.getEmailIn())){
				institution.setEmail(ump.getEmailIn());
			}
			
			//用户组织机构代码
			if(!Util.isNull(ump.getOrgCode())){
				institution.setOrgCode(ump.getOrgCode());
			}
			
			//用户社会信用代码
			if(!Util.isNull(ump.getSocialCode())){
				institution.setSocialCode(ump.getSocialCode());
			}
			
			//机构用户所在省
			if(!Util.isNull(ump.getProvince())){
				institution.setProvince(ump.getProvince());
			}
			//用户所在市
			if(!Util.isNull(ump.getCity())){
				institution.setCity(ump.getCity());
			}
			
			//用户所在地区
			if(!Util.isNull(ump.getArea())){
				institution.setArea(ump.getArea());
			}
			
			//用户联系地址
			if(!Util.isNull(ump.getAddress())){
				institution.setAddress(ump.getAddress());
			}
			
			
			institutionUserManageDAO.saveGuser(guser);
			institutionUserManageDAO.saveInstitution(institution);
		}
		
		
		
	}

	
	
	
}
