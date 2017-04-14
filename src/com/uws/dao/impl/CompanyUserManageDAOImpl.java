package com.uws.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.uws.dao.ICompanyUserManageDAO;
import com.uws.model.Company;
import com.uws.model.Guser;
import com.uws.model.UserManageParam;
import com.uws.util.Constants;
import com.uws.util.Util;
/**
 * 企业用户信息管理dao实现类
 * @author hejin
 *
 */
@Repository
public class CompanyUserManageDAOImpl extends BaseDAOImpl implements ICompanyUserManageDAO{

	
	/**
	 * 查询企业用户分页信息
	 * @param param 分页参数
	 * @param ump 查询条件
	 * @return Page 企业用户分页数据
	 */
	@Override
	public Page queryCompanyModelUserList(Map<String, Object> param,
			UserManageParam ump) {
        StringBuffer sql=new StringBuffer("SELECT B.*,A.ID AS GID,A.USERNAME AS "
        		+ "USERNAME,A.STATUS AS STATUS1,A.IMAGE AS IMAGE1 FROM G_USER A LEFT JOIN "
        		+ "COMPANY_INFO B ON A.ID=B.USERID WHERE 1=1  ");
		
		if(ump!=null){
			if(!Util.isNull(ump.getUsername())){
				sql.append(" AND A.USERNAME LIKE '%"+ump.getUsername()+"%'  ");
				
			}
			if(!Util.isNull(ump.getCp_name())){
				sql.append(" AND B.CP_NAME LIKE '%"+ump.getCp_name()+"%'  ");
				
			}
			
			if(!Util.isNull(ump.getCpLinkTel())){
				sql.append(" AND B.CP_LINKTEL LIKE '%"+ump.getCpLinkTel()+"%'  ");
				
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
	 * 根据用户id查询企业用户信息
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public Map<String, Object> queryUserCompanyModelById(String userId) {
		StringBuffer sql=new StringBuffer("SELECT "
				+ "B.*,A.ID AS GID,A.USERNAME AS USERNAME,A.STATUS AS STATUS1,"
				+ "A.PASSWORD AS PASSWORD,A.USERTYPE AS USERTYPE ,A.IMAGE AS IMAGE1,"
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.CP_PROVINCE) AS PROVINCE_NAME, "
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.CP_CITY) AS CITY_NAME, "
				+ " (SELECT C.NAME FROM  DIC C WHERE C.ID=B.CP_AREA) AS AREA_NAME, "
				+ "A.COMMENT AS COMMENT  FROM G_USER A LEFT JOIN COMPANY_INFO B "
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
	 * 保存企业用户详细信息
	 * @param cm 企业用户详细信息实体
	 */
	@Override
	public void saveCompanyModel(Company cm) {
		// TODO Auto-generated method stub
		cm.setIs_show("0");
		this.save2(cm);
	}

	/**
	 * 批量删除企业用户详细信息
	 * @param companyIdList 要删除的企业用户id列表
	 */
	@Override
	public void delCompanyModel(String companyIdList) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("DELETE  FROM COMPANY_INFO   WHERE 1=1  ");
		
		String[] arr =companyIdList.split(",");
		
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
	 * 根据id查询企业用户基础信息对象
	 * @param guserId 主键
	 * @return
	 */
	@Override
	public Guser getGuserById(String guserId){
		
		return (Guser)this.getSe(Guser.class, guserId);
	}
	
	/**
	 * 根据id查询企业用户详细信息对象
	 * @param companyId 主键
	 * @return
	 */
	@Override
    public Company getCompanyModelById(String companyId){
		
		return (Company)this.getSe(Company.class, companyId);
	}

	/**
	 * 根查询企业用户信息列表(导出)
	 * @return
	 */
	@Override
	public List<Map<Integer, Object>> queryAllUserCompanyList(UserManageParam ump) {
		StringBuffer sql=new StringBuffer("SELECT "
				+ "B.*,A.ID AS GID,A.USERNAME AS USERNAME,"
				+ "(SELECT D.NAME FROM  SYS_DIC D WHERE D.ID=A.STATUS) AS STATUS1,"
				+ "A.PASSWORD AS PASSWORD,A.USERTYPE AS USERTYPE ,A.IMAGE AS IMAGE1,"
				+ "A.COMMENT AS COMMENT,"
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.CP_PROVINCE) AS PROVINCE_NAME, "
				+ "(SELECT C.NAME FROM  DIC C WHERE C.ID=B.CP_CITY) AS CITY_NAME, "
				+ " (SELECT C.NAME FROM  DIC C WHERE C.ID=B.CP_AREA) AS AREA_NAME "
				+ " FROM G_USER A LEFT JOIN COMPANY_INFO B ON A.ID=B.USERID "
				+ " WHERE 1=1 AND A.USERTYPE='"+Constants.USERTYPE_COMPANY+"' ");
		
		if(ump!=null){
			if(!Util.isNull(ump.getUsername())){
				sql.append(" AND A.USERNAME LIKE '%"+ump.getUsername()+"%'  ");
				
			}
			if(!Util.isNull(ump.getCp_name())){
				sql.append(" AND B.CP_NAME LIKE '%"+ump.getCp_name()+"%'  ");
				
			}
			
			if(!Util.isNull(ump.getCpLinkTel())){
				sql.append(" AND B.CP_LINKTEL LIKE '%"+ump.getCpLinkTel()+"%'  ");
				
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
			map.put(1, "企业用户");
			//用户头像
//			map.put(2, list01.get(i).get("IMAGE1"));
			//状态
			map.put(2, list01.get(i).get("STATUS1"));
			
			//企业名称
			map.put(3, list01.get(i).get("CP_NAME"));
			//企业联系人
			map.put(4, list01.get(i).get("CP_LINKMAN"));
			
			//手机号码
			map.put(5, list01.get(i).get("CP_LINKTEL"));
			//座机号码
			map.put(6, list01.get(i).get("CP_PHONE"));
			//联系人邮箱
			map.put(7, list01.get(i).get("CP_LINKEMAIL"));
			
			//注册地(省)
			map.put(8, list01.get(i).get("PROVINCE_NAME"));
			//注册地(市)
			map.put(9, list01.get(i).get("CITY_NAME"));
			//注册地(区)
			map.put(10, list01.get(i).get("AREA_NAME"));
			//组织企业代码
			map.put(11, list01.get(i).get("CP_ORGCODE"));
			//社会信用代码
			map.put(12, list01.get(i).get("CP_SOCIALCODE"));
			//所在地
			map.put(13, list01.get(i).get("CP_LOCATION"));
			
			list.add(map);
			
		}
		
		
		return list;
	}

	@Override
	public List checkCompanyUsername(String guserId, String username,String userType) {
		 StringBuffer hql = new StringBuffer(" from Guser g where 1=1");
		 List<String> value = new ArrayList<String>();
		 if(guserId != null && !guserId.equals("")){
			 hql.append(" and g.id != ?");
			 value.add(guserId);
		 }
		 if(username != null && !username.equals("")){
			 hql.append(" and g.username = ?");
			 value.add(username);
		 }
		 if(userType!=null && !"".equals(userType)){
			 hql.append(" and g.usertype = ? ");
			 value.add(userType);
		 }
		 
		 
		return this.findSe(hql.toString(), value.toArray());
	}

	@Override
	public List checkCompanyUsernames(List<String> usernames,String userType) {
		 String sql = "SELECT G.USERNAME FROM G_USER G WHERE G.USERTYPE='"+userType+"' ";
		 sql += " AND G.USERNAME IN(";
		 if(usernames != null && usernames.size()>0){
			 for (int i = 0; i < usernames.size(); i++) {
				 sql += "'"+usernames.get(i)+"',";
			}
		 }
		 sql = sql.substring(0, sql.length() -1);
		 sql += ")";
		return this.SelectAll(sql.toString());
	}

	@Override
	public void updateGuser(Guser guser) {
		 
		this.updateSe(guser);
	}

	@Override
	public void updateCompanyModel(Company cm) {
		
		this.updateSe(cm);
	}

	@Override
	public void addCompany(List<String> sql) {
		 
		this.UpdateDataMore(sql);
	}

}
