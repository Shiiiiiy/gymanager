package com.uws.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.uws.dao.IInstitutionUserManageDAO;
import com.uws.model.Guser;
import com.uws.model.Institution;
import com.uws.model.UserManageParam;
import com.uws.util.Constants;
import com.uws.util.Util;
/**
 * 机构用户信息管理dao实现类
 * @author hejin
 *
 */
@Repository
public class InstitutionUserManageDAOImpl extends BaseDAOImpl implements IInstitutionUserManageDAO{

	
	/**
	 * 查询机构用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 机构用户分页数据
	 */
	@Override
	public Page queryInstitutionUserList(Map<String, Object> param,
			UserManageParam ump) {
        StringBuffer sql=new StringBuffer("SELECT B.*,A.ID AS GID,A.USERNAME AS USERNAME"
        		+ ",A.STATUS AS STATUS1,A.IMAGE AS IMAGE1 FROM G_USER A LEFT JOIN INSTITUTION B "
        		+ "ON A.ID=B.USERID WHERE 1=1  ");
		
		if(ump!=null){
			if(!Util.isNull(ump.getUsername())){
				sql.append(" AND A.USERNAME LIKE '%"+ump.getUsername()+"%'  ");
				
			}
			if(!Util.isNull(ump.getNameIn())){
				sql.append(" AND B.NAME LIKE '%"+ump.getNameIn()+"%'  ");
				
			}
			
			if(!Util.isNull(ump.getLinkMan())){
				sql.append(" AND B.LINKMAN LIKE '%"+ump.getLinkMan()+"%'  ");
				
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
	 * 根据用户id查询机构用户信息
	 * @param userId 角色id
	 * @return
	 */
	@Override
	public Map<String, Object> queryUserInstitutionById(String userId) {
		StringBuffer sql=new StringBuffer("SELECT B.*,A.ID AS GID,"
				+ "A.USERNAME AS USERNAME,A.STATUS AS STATUS1,A.PASSWORD AS PASSWORD,A.COMMENT AS COMMENT,"
				+ "A.USERTYPE AS USERTYPE ,A.IMAGE AS IMAGE1,"
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.PROVINCE) AS PROVINCE_NAME, "
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.CITY) AS CITY_NAME, "
				+ " (SELECT C.NAME FROM  DIC C WHERE C.ID=B.AREA) AS AREA_NAME "
				+ "  FROM G_USER A LEFT JOIN INSTITUTION B "
				+ "ON A.ID=B.USERID WHERE 1=1  ");
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
	 * 保存机构用户详细信息
	 * @param person 机构用户详细信息实体
	 */
	@Override
	public void saveInstitution(Institution institution) {
		// TODO Auto-generated method stub
		this.save2(institution);
	}

	/**
	 * 批量删除机构用户详细信息
	 * @param personIdList 要删除的机构用户id列表
	 */
	@Override
	public void delInstitution(String institutionList) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("DELETE  FROM INSTITUTION   WHERE 1=1  ");
		
		String[] arr =institutionList.split(",");
		
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
	 * 根据id查询机构用户基础信息对象
	 * @param guserId 主键
	 * @return
	 */
	@Override
	public Guser getGuserById(String guserId){
		
		return (Guser)this.getSe(Guser.class, guserId);
	}
	
	/**
	 * 根据id查询机构用户详细信息对象
	 * @param personId 主键
	 * @return
	 */
	@Override
    public Institution getInstitutionById(String institutionId){
		
		return (Institution)this.getSe(Institution.class, institutionId);
	}
	
	
	
	/**
	 * 根查询机构用户信息列表(导出)
	 * @return
	 */
	@Override
	public List<Map<Integer, Object>> queryAllUserInstitutionList(UserManageParam ump) {
		StringBuffer sql=new StringBuffer("SELECT "
				+ "B.*,A.ID AS GID,A.USERNAME AS USERNAME,"
				+ "(SELECT D.NAME FROM  SYS_DIC D WHERE D.ID=A.STATUS) AS STATUS1,"
				+ "A.PASSWORD AS PASSWORD,A.USERTYPE AS USERTYPE ,A.IMAGE AS IMAGE1,"
				+ "A.COMMENT AS COMMENT,"
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.PROVINCE) AS PROVINCE_NAME, "
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.CITY) AS CITY_NAME, "
				+ " (SELECT C.NAME FROM  DIC C WHERE C.ID=B.AREA) AS AREA_NAME "
				+ " FROM G_USER A LEFT JOIN INSTITUTION B ON A.ID=B.USERID "
				+ " WHERE 1=1 AND A.USERTYPE='"+Constants.USERTYPE_INSTITUTION+"' ");
		
		if(ump!=null){
			if(!Util.isNull(ump.getUsername())){
				sql.append(" AND A.USERNAME LIKE '%"+ump.getUsername()+"%'  ");
				
			}
			if(!Util.isNull(ump.getNameIn())){
				sql.append(" AND B.NAME LIKE '%"+ump.getNameIn()+"%'  ");
				
			}
			
			if(!Util.isNull(ump.getLinkMan())){
				sql.append(" AND B.LINKMAN LIKE '%"+ump.getLinkMan()+"%'  ");
				
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
			map.put(1, "机构用户");
			//用户头像
//			map.put(2, list01.get(i).get("IMAGE1"));
			//状态
			map.put(2, list01.get(i).get("STATUS1"));
			
			//机构名称
			map.put(3, list01.get(i).get("NAME"));
			//机构联系人
			map.put(4, list01.get(i).get("LINKMAN"));
			
			//手机号码
			map.put(5, list01.get(i).get("TEL"));
			//座机号码
			map.put(6, list01.get(i).get("PHONE"));
			//联系人邮箱
			map.put(7, list01.get(i).get("EMAIL"));
			
			//所在省
			map.put(8, list01.get(i).get("PROVINCE_NAME"));
			//所在市
			map.put(9, list01.get(i).get("CITY_NAME"));
			//所在区
			map.put(10, list01.get(i).get("AREA_NAME"));
			//组织机构代码
			map.put(11, list01.get(i).get("ORGCODE"));
			//社会信用代码
			map.put(12, list01.get(i).get("SOCIALCODE"));
			//联系地址
			map.put(13, list01.get(i).get("ADDRESS"));
			
			list.add(map);
			
		}
		
		
		return list;
	}

	@Override
	public void updateGuser(Guser guser) {
		 
		this.updateSe(guser);
	}

	@Override
	public void updateInstitution(Institution institution) {
		
		this.updateSe(institution);
	}

	@Override
	public void addInstitutionUsers(List<String> lendReco) {
		 
		this.UpdateDataMore(lendReco);
	}
	
}
