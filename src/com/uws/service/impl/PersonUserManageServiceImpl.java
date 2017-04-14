package com.uws.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.util.DefaultValue;
import com.uws.dao.IPersonUserManageDAO;
import com.uws.model.Guser;
import com.uws.model.Person;
import com.uws.model.UserManageParam;
import com.uws.service.IPersonUserManageService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;
import com.uws.util.Util;
/**
 * 个人用户管理service实现类
 * @author hejin
 *
 */
@Service("personUserManageService")
public class PersonUserManageServiceImpl implements  IPersonUserManageService{

	@Autowired
	private IPersonUserManageDAO personUserManageDAO;
	
	@Autowired
	private ISysDicService iSysDicService;
	
	
	/**
	 * 查询个人用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 个人用户分页数据
	 */
	@Override
	public Page queryPersonUserList(Map<String, Object> param,
			UserManageParam ump) {
		// TODO Auto-generated method stub
		return personUserManageDAO.queryPersonUserList(param, ump);
	}

	/**
	 * 根据用户id查询个人用户信息
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public Map<String, Object> queryUserPserById(String userId) {
		// TODO Auto-generated method stub
		return personUserManageDAO.queryUserPserById(userId);
	}
	

	/**
	 * 根据用户名查询用户基础信息
	 * @param username 用户名
	 * @return
	 */
	@Override
	public Map<String, Object> queryGuserByUserName(String username) {
		// TODO Auto-generated method stub
		return personUserManageDAO.queryGuserByUserName(username);
	}
	
	
	/**
	 * 根查询个人用户信息列表(导出)
	 * @return
	 */
	@Override
	public List<Map<Integer, Object>> queryAllUserPersonList(UserManageParam ump){
		
		return this.personUserManageDAO.queryAllUserPersonList(ump);
	}
	

	/**
	 * 新增个人用户信息
	 * @param ump 个人用户信息实体
	 */
	@Override
	public void addPersonUser(UserManageParam ump) {
		//iSysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_ENABLE).getId()
		if(ump!=null){
			Guser guser=new Guser();
			Person person=new Person();
			
			String guserId=UUID.randomUUID().toString().replace("-", "");
			guser.setId(guserId);
			guser.setCreateTime(new Date());
			guser.setUpdateTime(new Date());
			guser.setStatus(ump.getStatus());
			guser.setUsertype(Constants.USERTYPE_PERSON);
			guser.setImage(ump.getImage());
			if(ump.getStatus() != null && !ump.getStatus().equals("")){
				guser.setStatus(ump.getStatus());
			}else{
				guser.setStatus(this.iSysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE).getId());
			}
			person.setCreateTime(new Date());
			person.setUpdateTime(new Date());
			person.setStatus(ump.getStatus());
			person.setUserId(guserId);
			
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
			
			//个人用户真实名
			if(!Util.isNull(ump.getName())){
				person.setName(ump.getName());
			}
			//用户性别
			if(!Util.isNull(ump.getGender())){
				person.setGender(ump.getGender());
			}
			
			//用户座机
			if(!Util.isNull(ump.getPhone())){
				person.setPhone(ump.getPhone());
			}
			
			
			//个人用户手机
			if(!Util.isNull(ump.getTel())){
				person.setTel(ump.getTel());
			}
			
			//用户证件类型
			if(!Util.isNull(ump.getCardtype())){
				person.setCardtype(ump.getCardtype());
			}
			
			//用户证件号码
			if(!Util.isNull(ump.getCardnum())){
				person.setCardnum(ump.getCardnum());
			}
			
			//个人用户所在省
			if(!Util.isNull(ump.getAddProvince())){
				person.setProvince(ump.getAddProvince());
			}
			//用户所在市
			if(!Util.isNull(ump.getAddCity())){
				person.setCity(ump.getAddCity());
			}
			
			//用户所在地区
			if(!Util.isNull(ump.getAddArea())){
				person.setArea(ump.getAddArea());
			}
			
			//用户邮箱
			if(!Util.isNull(ump.getEmail())){
				person.setEmail(ump.getEmail());
			}
			
			//用户联系地址
			if(!Util.isNull(ump.getLinkAddress())){
				person.setLinkaddress(ump.getLinkAddress());
			}
			
			
			personUserManageDAO.saveGuser(guser);
			personUserManageDAO.savePerson(person);
		}
		
		
		
	}
	
	/**
	 * 保存用户基本信息
	 * @param guser 个人用户基础信息对象
	 * @param person 个人用户详细信息对象
	 */
	@Override
	public void savePersonUser(Guser guser,Person person){
		
		personUserManageDAO.updateGuser(guser);
		if(person!=null){
		    personUserManageDAO.updatePerson(person);
		}
	}
	

	/**
	 * 批量删除个人用户信息
	 * @param personIdList 要删除的个人用户基础信息id列表
	 * @param guserIdList  要删除的个人用户详细信息id列表
	 */
	@Override
	public void delPersonUser(String personIdList, String guserIdList) {
		// TODO Auto-generated method stub
		personUserManageDAO.delGuser(guserIdList);
		personUserManageDAO.delPerson(personIdList);
	}

	/**
	 * 根据id查询个人用户基础信息对象
	 * @param guserId 主键
	 * @return
	 */
	@Override
	public Guser getGuserById(String guserId) {
		// TODO Auto-generated method stub
		return personUserManageDAO.getGuserById(guserId);
	}

	/**
	 * 根据id查询个人用户详细信息对象
	 * @param personId 主键
	 * @return
	 */
	@Override
	public Person getPersonById(String personId) {
		// TODO Auto-generated method stub
		return personUserManageDAO.getPersonById(personId);
	}

	@Override
	public void addPersonUsers(List<String> sql) {
 
		personUserManageDAO.addPersonUsers(sql);
	}

	
	
	
}
