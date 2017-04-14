package com.uws.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.uws.dao.IPersonUserManageDAO;
import com.uws.model.Guser;
import com.uws.model.Person;
import com.uws.model.UserManageParam;
import com.uws.util.Constants;
import com.uws.util.Util;
/**
 * 个人用户信息管理dao实现类
 * @author hejin
 *
 */
@Repository
public class PersonUserManageDAOImpl extends BaseDAOImpl implements IPersonUserManageDAO{

	
	/**
	 * 查询个人用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 个人用户分页数据
	 */
	@Override
	public Page queryPersonUserList(Map<String, Object> param,
			UserManageParam ump) {
        StringBuffer sql=new StringBuffer("SELECT B.*,A.ID AS GID,A.USERNAME AS "
        		+ "USERNAME,A.STATUS AS STATUS1,A.IMAGE AS IMAGE1 FROM G_USER A LEFT JOIN "
        		+ "PERSON B ON A.ID=B.USERID WHERE 1=1  ");
		
		if(ump!=null){
			if(!Util.isNull(ump.getUsername())){
				sql.append(" AND A.USERNAME LIKE '%"+ump.getUsername()+"%'  ");
				
			}
			if(!Util.isNull(ump.getName())){
				sql.append(" AND B.NAME LIKE '%"+ump.getName()+"%'  ");
				
			}
			
			if(!Util.isNull(ump.getTel())){
				sql.append(" AND B.TEL LIKE '%"+ump.getTel()+"%'  ");
				
			}
			if(!Util.isNull(ump.getUsertype())){
				sql.append(" AND A.USERTYPE='"+ump.getUsertype()+"'  ");
				
			}
			
		}
		sql.append(" ORDER BY A.CREATETIME DESC,A.USERNAME ");
		
		param.put("sql", sql.toString());
		
		return queryBySql(param);
	}

	/**
	 * 根据用户id查询个人用户信息
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public Map<String, Object> queryUserPserById(String userId) {
		StringBuffer sql=new StringBuffer("SELECT "
				+ "B.*,A.ID AS GID,A.USERNAME AS USERNAME,A.STATUS AS STATUS1,"
				+ "A.PASSWORD AS PASSWORD,A.USERTYPE AS USERTYPE ,A.IMAGE AS IMAGE1,"
				+ "A.COMMENT AS COMMENT,"
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.ADD_PROVINCE) AS PROVINCE_NAME, "
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.ADD_CITY) AS CITY_NAME, "
				+ " (SELECT C.NAME FROM  DIC C WHERE C.ID=B.ADD_AREA) AS AREA_NAME "
				+ "  FROM G_USER A LEFT JOIN PERSON B ON A.ID=B.USERID  "
				+ " WHERE 1=1  ");
		Map<String, Object> map=new HashMap();
		
		if(!Util.isNull(userId)){
			sql.append(" AND A.ID='"+userId+"'");
		}else{
			return map;
		}
		
		List<Map> list=this.SelectAll(sql.toString());
		if(list!=null && list.size()>0){
			map=list.get(0);
		}
		
		return map;
	}

	
	/**
	 * 根查询个人用户信息列表(导出)
	 * @return
	 */
	@Override
	public List<Map<Integer, Object>> queryAllUserPersonList(UserManageParam ump) {
		StringBuffer sql=new StringBuffer("SELECT "
				+ "B.*,A.ID AS GID,A.USERNAME AS USERNAME,"
				+ "(SELECT D.NAME FROM  SYS_DIC D WHERE D.ID=A.STATUS) AS STATUS1,"
				+ "A.PASSWORD AS PASSWORD,A.USERTYPE AS USERTYPE ,A.IMAGE AS IMAGE1,"
				+ "A.COMMENT AS COMMENT,"
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.ADD_PROVINCE) AS PROVINCE_NAME, "
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.ADD_CITY) AS CITY_NAME, "
				+ " (SELECT C.NAME FROM  DIC C WHERE C.ID=B.ADD_AREA) AS AREA_NAME "
				+ " FROM G_USER A LEFT JOIN PERSON B ON A.ID=B.USERID "
				+ " WHERE 1=1 AND A.USERTYPE='"+Constants.USERTYPE_PERSON+"' ");
		
		if(ump!=null){
			if(!Util.isNull(ump.getUsername())){
				sql.append(" AND A.USERNAME LIKE '%"+ump.getUsername()+"%'  ");
				
			}
			if(!Util.isNull(ump.getName())){
				sql.append(" AND B.NAME LIKE '%"+ump.getName()+"%'  ");
				
			}
			
			if(!Util.isNull(ump.getTel())){
				sql.append(" AND B.TEL LIKE '%"+ump.getTel()+"%'  ");
				
			}
			if(!Util.isNull(ump.getUsertype())){
				sql.append(" AND A.USERTYPE='"+ump.getUsertype()+"'  ");
				
			}
		}
		sql.append(" ORDER BY A.CREATETIME DESC,A.USERNAME ");
		
		List<Map<Integer, Object>> list=new ArrayList();
		List<Map> list01=this.SelectAll(sql.toString());
		
		Map<Integer, Object> map=null;
		int count01=list01.size();
		for(int i=0;i<count01;i++){
			map=new HashMap();
			//用户名
			map.put(0, list01.get(i).get("USERNAME"));
			//用户密码
//			map.put(1, list01.get(i).get("PASSWORD"));
			//用户类型
			map.put(1, "个人用户");
			//用户头像
//			map.put(2, list01.get(i).get("IMAGE1"));
			//状态
			map.put(2, list01.get(i).get("STATUS1"));
			//真实姓名
			map.put(3, list01.get(i).get("NAME"));
			//性别
			if("1".equals(list01.get(i).get("GENDER"))){
			    map.put(4, "男");
			}else{
				map.put(4, "女");
			}
			//手机号码
			map.put(5, list01.get(i).get("TEL"));
			//座机号码
			map.put(6, list01.get(i).get("PHONE"));
			//邮箱
			map.put(7, list01.get(i).get("EMAIL"));
			//证件类型
			if("CARD_A".equals(list01.get(i).get("CARDTYPE"))){
			    map.put(8, "身份证");
			}else{
				map.put(8, "驾驶证");
			}
			
			//证件号码
			map.put(9, list01.get(i).get("CARDNUM"));
			//所在省
			map.put(10, list01.get(i).get("PROVINCE_NAME"));
			//所在市
			map.put(11, list01.get(i).get("CITY_NAME"));
			//所在区
			map.put(12, list01.get(i).get("AREA_NAME"));
			//联系地址
			map.put(13, list01.get(i).get("LINK_ADDRESS"));
			
			list.add(map);
			
		}
		
		
		return list;
	}

	
	
	
	/**
	 * 根据DIC表id查询省市区字典项名称
	 * @param dicId 字典表id
	 * @return
	 */
	@Override
	public Map<String, Object> queryDicByDicId(String dicId) {
		StringBuffer sql=new StringBuffer("SELECT * "
				+ " FROM DIC "
				+ " WHERE 1=1  ");
		Map<String, Object> map=new HashMap();
		
		if(!Util.isNull(dicId)){
			sql.append(" AND ID='"+dicId+"'");
		}else{
			return map;
		}
		
		List<Map> list=this.SelectAll(sql.toString());
		if(list!=null && list.size()>0){
			map=list.get(0);
		}
		
		return map;
	}
	
	
	/**
	 * 根据用户名查询用户基础信息
	 * @param username 用户名
	 * @return
	 */
	@Override
	public Map<String, Object> queryGuserByUserName(String username) {
		StringBuffer sql=new StringBuffer("SELECT * FROM G_USER   WHERE 1=1  ");
		Map<String, Object> map=new HashMap();
		if(!Util.isNull(username)){
			sql.append(" AND USERNAME='"+username+"'");
		}else{
			return null;
		}
		
		List<Map> list=this.SelectAll(sql.toString());
		if(list!=null && list.size()>0){
			map=list.get(0);
		}
		
		return map;
	}

	/**
	 * 保存用户基础信息
	 * @param guser 用户基础信息实体
	 */
	@Override
	public void saveGuser(Guser guser) {
		// TODO Auto-generated method stub
		this.save2(guser);
	}

	/**
	 * 保存个人用户详细信息
	 * @param person 个人用户详细信息实体
	 */
	@Override
	public void savePerson(Person person) {
		// TODO Auto-generated method stub
		this.save2(person);
	}

	/**
	 * 批量删除个人用户详细信息
	 * @param personIdList 要删除的个人用户id列表
	 */
	@Override
	public void delPerson(String personIdList) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("DELETE  FROM PERSON   WHERE 1=1  ");
		
		String[] arr =personIdList.split(",");
		
		if(arr!=null && arr.length>0){
		    int count =arr.length;
			sql.append(" AND ID IN ( '");
		    for(int i=0;i<count;i++){
		    	if(!Util.isNull(arr[i])){
			    	if(i==count-1){
			    		sql.append(arr[i]+"' ) ");
			    	}else{
			    		sql.append(arr[i]+"','");
			    	}
		    	}
		    	
		    }
		    
		    this.executeSQL(sql.toString());
		}
		
	}

	/**
	 * 批量删除用户基础信息
	 * @param guserIdList 要删除的用户基础信息id列表
	 */
	@Override
	public void delGuser(String guserIdList) {
		
			StringBuffer sql=new StringBuffer("DELETE  FROM G_USER   WHERE 1=1  ");
			
			String[] arr =guserIdList.split(",");
			
			if(arr!=null && arr.length>0){
			    int count =arr.length;
				sql.append(" AND ID IN ( '");
			    for(int i=0;i<count;i++){
			    	if(!Util.isNull(arr[i])){
				    	if(i==count-1){
				    		sql.append(arr[i]+"' ) ");
				    	}else{
				    		sql.append(arr[i]+"','");
				    	}
			    	}
			    	
			    }
			    
			    this.executeSQL(sql.toString());
			}
		
	}

	/**
	 * 根据id查询个人用户基础信息对象
	 * @param guserId 主键
	 * @return
	 */
	@Override
	public Guser getGuserById(String guserId){
		
		return (Guser)this.getSe(Guser.class, guserId);
	}
	
	/**
	 * 根据id查询个人用户详细信息对象
	 * @param personId 主键
	 * @return
	 */
	@Override
    public Person getPersonById(String personId){
		
		return (Person)this.getSe(Person.class, personId);
	}
	
	@Override
	public void updateGuser(Guser guser) {
		 
		this.updateSe(guser);
	}

	@Override
	public void updatePerson(Person person) {
 
		this.updateSe(person);
	}

	@Override
	public void addPersonUsers(List<String> sql) {
	 
		this.UpdateDataMore(sql);
	}

}
