package com.uws.dao.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.util.DataUtil;
import com.uws.dao.IServiceDao;
import com.uws.dao.ISysRolePermissionDao;
import com.uws.model.Company;
import com.uws.model.NewsAll;
import com.uws.util.Constants;
import com.uws.util.Util;

@Repository
public class ServiceDaoImpl  extends BaseDAOImpl implements IServiceDao {
	/**角色权限service*/
	@Resource
	private ISysRolePermissionDao sysRolePermissionDaoImpl;
	/**
	 * 查询字典分类详细信息
	 * @param ig_type产业或园区类型  industry_type 产业类型
	 * @return
	 */
	@Override
	public List<Map> getIndustryList(String ig_type, String industry_type) {
		StringBuffer sql=new StringBuffer("SELECT * FROM INDUSTRY_GARDEN  A WHERE 1=1  ");
		List<String> param=new ArrayList();
		if(!Util.isNull(ig_type) && !Util.isNull(industry_type)){
		     sql.append(" AND A.IG_TYPE = ? ");
		     param.add(ig_type);
		     sql.append(" AND INDUSTRY_TYPE = ? ");
		     param.add(industry_type);
		     return this.SelectAll(sql.toString(),param.toArray());
		}
		return null;
	}
	/**
	 * 产业服务下  工业产品/生产服务的分类
	 * @param industry_type 产业类型
	 * @return
	 */
	@Override
	public List<Map> getIndustryList(String industry_type) {
		StringBuffer sql=new StringBuffer("SELECT A.ID AS ID ,A.NAME AS name ,A.INDUSTRY_TYPE AS PID FROM INDUSTRY_GARDEN  A WHERE 1=1  ");
		List<String> param=new ArrayList();
		if(!Util.isNull(industry_type)){
		     sql.append(" AND A.IG_TYPE = ? ");
		     param.add("INDUSTRY");
		     sql.append(" AND A.INDUSTRY_TYPE = ? ");
		     param.add(industry_type);
		     return this.SelectAll(sql.toString(),param.toArray());
		}
		return null;
	}
	/**
	 * 获取产品服务新闻
	 * @param param 分页参数
	 * @param news 新闻类实体
	 * @param moduleType
	 * @param parentCode
	 * @return Page
	 */
	@Override
	public Page queryProductServiceNews(Map<String, Object> param, NewsAll news,String mCode,String parentCode,String userid) {
		StringBuffer sql=new StringBuffer("SELECT N.*,G.NAME AS GNAME FROM NEWS_ALL N " +
				" LEFT JOIN INDUSTRY_GARDEN G ON N.PARENT_CODE = G.ID " +
				" LEFT JOIN SYS_DIC D ON N.MODULE_TYPE = D.CODE  WHERE 1=1  ");
		if(news != null){
			//新闻编码
			if(DataUtil.isNotNull(news.getModuleCode())){
				sql.append(" AND N.MODULE_CODE = '").append(news.getModuleCode()).append("'");
			}
			//新闻标题
			if(DataUtil.isNotNull(news.getNewsTitle())){
				sql.append(" AND N.NEWS_TITLE LIKE '%").append(news.getNewsTitle()).append("%'");
			}
			//新闻来源
			if(DataUtil.isNotNull(news.getNewsSource())){
				sql.append(" AND N.COMMENTS LIKE '%").append(news.getNewsSource()).append("%'");
			}
			//发布时间
			if(DataUtil.isNotNull(news.getNewsTime())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				sql.append(" AND DATE_FORMAT(N.NEWS_TIME,'%Y%m%d') = '").append(sdf.format(news.getNewsTime())).append("'");
			}
			//启用状态 未用
			if(news.getStatus()!=0){
				sql.append(" AND N.STATUS = ").append(news.getStatus());
			}
			//所属服务类型
			if(Util.isNull(mCode)){//查询权限下所有的 
				List<Map> aa=new ArrayList();
				List<Map> a1=sysRolePermissionDaoImpl.queryPerMapListByUser(userid, Constants.ROLE_PER_INDUSTRY_A);
				List<Map> a2=sysRolePermissionDaoImpl.queryPerMapListByUser(userid, Constants.ROLE_PER_INDUSTRY_B);
				List<Map> a3=sysRolePermissionDaoImpl.queryPerMapListByUser(userid, Constants.ROLE_PER_INDUSTRY_M);
				aa.addAll(a1);aa.addAll(a2);
				if(aa.size()>0 && (a3==null || a3.size()<=0)){//12有权限3没权限
					sql.append(" AND N.PARENT_CODE  IN('");
					for (int i = 0; i < aa.size(); i++) {
						if(i!=aa.size()-1){
							sql.append( aa.get(i).get("ID") ).append("','");}
						else{
							sql.append( aa.get(i).get("ID") ).append("')");}
					}
				}
				else if(aa.size()<=0 && a3!=null && a3.size()>0){//12无权限3有权限
					sql.append(" AND  N.PARENT_CODE IS NULL  ");
				}
				else if(aa.size()>0 && a3!=null && a3.size()>0 ){//12有权限3有权限
					sql.append(" AND ( N.PARENT_CODE  IN('");
					for (int i = 0; i < aa.size(); i++) {
						if(i!=aa.size()-1){
							sql.append( aa.get(i).get("ID") ).append("','");}
						else{
							sql.append( aa.get(i).get("ID") ).append("')");}
					}
					sql.append(" OR  N.PARENT_CODE IS NULL  ) ");
				}
			}else if("N".equals(mCode)){
				sql.append(" AND N.PARENT_CODE IS NULL ");
			}else{//mCode有值 parentCode有值/无值
				if(Util.isNull(parentCode)){
					List<Map> aa=sysRolePermissionDaoImpl.queryPerMapListByUser(userid, mCode);
					if(aa!=null&&aa.size()>0){
						sql.append(" AND N.PARENT_CODE IN('");
						for (int i = 0; i < aa.size(); i++) {
							if(i!=aa.size()-1){
								sql.append( aa.get(i).get("ID") ).append("','");}
							else{
								sql.append( aa.get(i).get("ID") ).append("')");}
						}
					}
				}else{
					sql.append(" AND N.PARENT_CODE = '").append( parentCode ).append("'");
				}
			}
					
		}
		
		sql.append(" ORDER BY N.CREATE_TIME DESC ");
		
//		System.out.println(news.getNewsSource()+"12");
//		System.out.println(sql);
		param.put("sql", sql.toString());
		return queryBySql(param);
	}
	/**
	 * 查询类型 产品服务 的企业列表
	 * param : 分页参数
	 * company ： 查询条件
	 * */
	@Override
	public Page queryCompanyListByModule(Map<String, Object> param,	Company company, String mCode, String parentCode, String uid) {
		StringBuffer sb =new StringBuffer("SELECT CI.* FROM COMPANY_INFO CI WHERE 1=1 ");
		sb.append(" AND CI.ID IN ( SELECT M.COMP_ID FROM COMPANIES_PROP M WHERE M.PROP_TYPE ='").append(Constants.CP_PROP.PROP_E).append("')");  
		//产品服务 的类别 PROP_TYPE =PROP_E
		if(DataUtil.isNotNull(company.getCp_name())){
			sb.append(" AND CI.CP_NAME LIKE '%").append(company.getCp_name()).append("%'");
		}
		if(DataUtil.isNotNull(company.getCp_product())){
			sb.append(" AND CI.CP_PRODUCT LIKE '%").append(company.getCp_product()).append("%'");
		}
		if(DataUtil.isNotNull(company.getPropg())){//PROP_VALUE = F2 PROP_TYPE =PROP_G 产品服务的推荐
			if(Constants.F2.toString().equals(company.getPropg())){//推荐的
				sb.append(" AND CI.ID IN ( SELECT M.COMP_ID FROM COMPANIES_PROP M WHERE M.PROP_VALUE = '").append(Constants.F2).append("'")
			  .append(" AND M.PROP_TYPE ='").append(Constants.CP_PROP.PROP_G).append("') ");
			}else if("N".equals(company.getPropg())){//不推荐的
				sb.append(" AND CI.ID NOT IN ( SELECT M.COMP_ID FROM COMPANIES_PROP M WHERE M.PROP_VALUE = '").append(Constants.F2).append("'")
				  .append(" AND M.PROP_TYPE ='").append(Constants.CP_PROP.PROP_G).append("') ");
			}
		}
		if(company.getId() !=null){
			if(company.getId().equals("")){
				sb.append("AND CI.ID in ('')");
			}else{
				sb.append("AND CI.ID in ("+company.getId()+")");
			}
		}
		if(DataUtil.isNull(mCode)){//全部查询
			List<Map> aa=new ArrayList();
			List<Map> m1=sysRolePermissionDaoImpl.queryPerMapListByUser(uid, Constants.ROLE_PER_INDUSTRY_A);
			List<Map> m2=sysRolePermissionDaoImpl.queryPerMapListByUser(uid, Constants.ROLE_PER_INDUSTRY_B);
			aa.addAll(m1);aa.addAll(m2);
			if(aa!=null && aa.size()>0){
				sb.append(" AND ( CI.ID IN( SELECT S.COMP_ID FROM COMPANIES_PROP S WHERE S.PROP_VALUE IN ('");
				for (int i = 0; i < aa.size(); i++) {
					if(i!=aa.size()-1){
						sb.append( aa.get(i).get("ID") ).append("','");}
					else{
						sb.append( aa.get(i).get("ID") ).append("') )");}
				}
				sb.append(" OR CI.ID IN( SELECT S.COMP_ID FROM COMPANIES_PROP S WHERE S.PROP_VALUE IS NULL ) )");
			}
		}else if(!DataUtil.isNull(mCode) && DataUtil.isNull(parentCode)){
			List<Map> aa=sysRolePermissionDaoImpl.queryPerMapListByUser(uid,mCode);
			if(aa!=null&&aa.size()>0){
				sb.append(" AND CI.ID IN( SELECT S.COMP_ID FROM COMPANIES_PROP S WHERE S.PROP_VALUE IN ('");
				for (int i = 0; i < aa.size(); i++) {
					if(i!=aa.size()-1){
						sb.append( aa.get(i).get("ID") ).append("','");}
					else{
						sb.append( aa.get(i).get("ID") ).append("') )");}
				}
			}
		}else if(!DataUtil.isNull(mCode) && !DataUtil.isNull(parentCode)){
			sb.append(" AND CI.ID IN( SELECT S.COMP_ID FROM COMPANIES_PROP S WHERE S.PROP_VALUE = '").append(parentCode).append("')");
		}
		sb.append("AND CI.IS_SHOW = '1' AND CI.STATUS = '2' ");
		sb.append(" ORDER BY CI.CREATE_TIME DESC ");
		param.put("sql", sb.toString());
		Page page=queryBySql(param);
		return addPropValue(addRecommend(page));
	}

	/**
	 * 批量设置选择的企业为推荐企业或不推荐企业（包含单个操作）
	 * @param idList IDs
	 * @param propType 关系类型（是否推荐）
	 */
	@Override
	public void setOrCancelRecommendCompany(List<String> propIds, String propType) {
		if(DataUtil.isNull(propType) || propIds==null){return;}
		//删除关系
		StringBuffer sb = new StringBuffer("DELETE FROM COMPANIES_PROP  WHERE PROP_VALUE = '");
		sb.append(Constants.F2).append("'").append(" AND PROP_TYPE = '").append(Constants.CP_PROP.PROP_G).append("'");
		sb.append(" AND COMP_ID IN('");
		for(int i=0;i<propIds.size();i++){
			if(i == propIds.size()-1){
				sb.append(propIds.get(i));
				sb.append("')");
			}else{
				sb.append(propIds.get(i));
				sb.append("','");
			}
		}
		this.executeSQL(sb.toString());
		if( Constants.F2.toString().equals(propType) ){//插入关系
			for(int i=0;i < propIds.size();i++){
				StringBuffer sql=new StringBuffer(" INSERT INTO COMPANIES_PROP (ID,COMP_ID,PROP_VALUE,PROP_TYPE) VALUES ('");
				sql.append(UUID.randomUUID().toString().replace("-", "")).append("','");//ID
				sql.append(propIds.get(i)).append("','");//COMP_ID
				sql.append(Constants.F2).append("','");//PROP_VALUE
				sql.append(Constants.CP_PROP.PROP_G).append("')");//PROP_TYPE
				this.executeSQL(sql.toString());
			}
		}
	}
	
	/**
	 * 处理推荐信息
	 * page
	 * */
	private Page addRecommend(Page page) {
		if(null==page){return null;}
		List<Map> list=page.getList();
		for (int i = 0; i < list.size(); i++) {
			Map aa=list.get(i);
			StringBuffer sb =new StringBuffer("SELECT CI.PROP_VALUE FROM COMPANIES_PROP CI WHERE 1=1 AND CI.COMP_ID = '");
			sb.append(aa.get("ID")).append("'").append("AND CI.PROP_TYPE = '").append(Constants.CP_PROP.PROP_G).append("'");
			List<Map> ll=SelectAll(sb.toString());
			if(ll!=null && ll.size()!=0 && ll.get(0).get("PROP_VALUE").equals("F2")){
				list.get(i).put("COMMENTS", "F2");
			}else{
				list.get(i).put("COMMENTS", "N");
			}
		}
		page.setList(list);
		return page;
	}
	/**
	 * 处理分类
	 * page
	 * */
	private Page addPropValue(Page page) {
		if(null==page){return null;}
		List<Map> list=page.getList();
		for (int i = 0; i < list.size(); i++) {
			Map aa=list.get(i);
			StringBuffer sb =new StringBuffer("SELECT B.NAME FROM INDUSTRY_GARDEN B WHERE B.ID IN ( SELECT A.PROP_VALUE FROM COMPANIES_PROP A WHERE A.COMP_ID = '");
			sb.append(aa.get("ID")).append("' AND A.PROP_TYPE = '").append(Constants.CP_PROP.PROP_E).append("' )");
			List<Map> ll=SelectAll(sb.toString());
			String str="";
			if(ll!=null && ll.size()!=0 ){
				for (int j = 0; j < ll.size(); j++) {
					if(j!=ll.size()-1){
					str=str+ll.get(j).get("NAME")+",";
					}else{ str=str+ll.get(j).get("NAME");}
				}
			}
			list.get(i).put("CP_ABSTRACT", str);
		}
		page.setList(list);
		return page;
	}
	/**
	 *批量取消  企业列表的关系
	 *@param propIds 企业ids
	 * */
	@Override
	public void cancelCompany(List<String> propIds) {
		StringBuffer sb = new StringBuffer("DELETE FROM COMPANIES_PROP WHERE COMP_ID IN('");
		for(int i=0;i< propIds.size();i++){
			if(i == propIds.size()-1){
				sb.append(propIds.get(i));
				sb.append("')");
			}else{
				sb.append(propIds.get(i));
				sb.append("','");
			}
		}
		this.executeSQL(sb.toString());
	}
	 /**
	 * 批量 添加企业分类属性
	 *@param propIds 企业ids
	 *@param propType 企业分类属性ids
	 * */
	@Override
	public void addClassCompany(List<String> idList, List<String> typeList) {
		//移除所有企业 分类 
		StringBuffer sb = new StringBuffer("DELETE FROM COMPANIES_PROP WHERE PROP_TYPE = '"); 
		sb.append(Constants.CP_PROP.PROP_E).append("'").append(" AND COMP_ID IN('" );
		for(int i=0;i< idList.size();i++){
			if(i == idList.size()-1){
				sb.append(idList.get(i));
				sb.append("')");
			}else{
				sb.append(idList.get(i));
				sb.append("','");
			}
		}
		this.executeSQL(sb.toString());
		//添加属性
		for (int i = 0; i < idList.size(); i++) {
			for (int j = 0; j < typeList.size(); j++) {
				StringBuffer sql=new StringBuffer(" INSERT INTO COMPANIES_PROP (ID,COMP_ID,PROP_VALUE,PROP_TYPE) VALUES ('");
				sql.append(UUID.randomUUID().toString().replace("-", "")).append("','");//ID
				sql.append(idList.get(i)).append("','");//COMP_ID
				sql.append(typeList.get(j)).append("','");//PROP_VALUE
				sql.append(Constants.CP_PROP.PROP_E).append("')");//PROP_TYPE
				this.executeSQL(sql.toString());
			}
		}
	}
	/**
	 *从企业库  向产品服务 批量 添加企业
	 *@param  companyIds 企业ids
	 * */
	@Override
	public void saveServiceCompany(List<String> idList) {
		// 获取需要添加的企业id
		StringBuffer sb = new StringBuffer("SELECT A.COMP_ID FROM COMPANIES_PROP A WHERE A.PROP_TYPE = '");	
		sb.append(Constants.CP_PROP.PROP_E).append("' AND A.COMP_ID IN ('");
		for(int i=0;i< idList.size();i++){
			if(i == idList.size()-1){
				sb.append(idList.get(i));
				sb.append("')");
			}else{
				sb.append(idList.get(i));
				sb.append("','");
			}
		}
		
		List<Map> havelist=SelectAll(sb.toString());//存在已有的
		//只插入必要的
		for (int i = 0; i < idList.size(); i++) {
			boolean donext=true;
			for (int j = 0; j < havelist.size(); j++) {
				if(idList.get(i).equals(havelist.get(j).get("COMP_ID"))){
					donext=false; break;
				}
			}
			if(donext){
				StringBuffer sql=new StringBuffer(" INSERT INTO COMPANIES_PROP (ID,COMP_ID,PROP_TYPE) VALUES ('");
				sql.append(UUID.randomUUID().toString().replace("-", "")).append("','");//ID
				sql.append(idList.get(i)).append("','");//COMP_ID
				sql.append(Constants.CP_PROP.PROP_E).append("')");//PROP_TYPE
				this.executeSQL(sql.toString());
			}
		}
	}
	/**
	 * 查询 产品服务 轮播图FILE_SLIDER 和 统计图FILE_STATISTICS
	 * @param moduleCode 文件表moduleCode
	 * @param parentCode 文件表parentCode
	 * */
	@Override
	public List<Map> queryFileInfoList(String moduleCode, String parentCode) {
		StringBuffer sql = new StringBuffer(" SELECT * FROM FILE_INFO WHERE 1=1 ");
		if(DataUtil.isNotNull(moduleCode)){
			sql.append(" AND MODULE_CODE =  '"+moduleCode+"' ");
		}
		if(DataUtil.isNotNull(parentCode)){
			//查询   
			if(parentCode==null || "N".equals(parentCode) || "".equals(parentCode)) {
				sql.append(" AND  PARENT_CODE IS NULL  ");
			}else{
			//子模块
				sql.append(" AND  PARENT_CODE = '"+parentCode+"'");
			}
		}
		sql.append(" ORDER BY SORT ASC,CREATE_TIME DESC");
		return this.SelectAll(sql.toString());
	}
	/**
	 * 通过id 获取 Industry名称
	 * @param id
	 * */
	@Override
	public String getIndustryNameByID(String id) {
		StringBuffer sql=new StringBuffer("SELECT A.NAME FROM INDUSTRY_GARDEN  A WHERE 1=1  ");
		List<String> param=new ArrayList();
		if(!Util.isNull(id)){
		     sql.append(" AND A.ID = ? ");
		     param.add(id);
		     List<Map> list=SelectAll(sql.toString(),param.toArray());
		     if(null!=list && list.size()>0)
		     {return (String) list.get(0).get("NAME");}
		}
		return null;
	}


}














