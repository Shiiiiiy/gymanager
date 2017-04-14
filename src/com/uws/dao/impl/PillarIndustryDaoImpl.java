package com.uws.dao.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.util.DataUtil;
import com.uws.dao.IPillarIndustryDao;
import com.uws.model.Company;

@Repository
public class PillarIndustryDaoImpl extends BaseDAOImpl implements IPillarIndustryDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryFileInfoList(String moduleCode, String parentCode) {
		
		
		StringBuffer sql = new StringBuffer(" SELECT * FROM FILE_INFO WHERE 1=1 ");
		
		if(DataUtil.isNotNull(moduleCode)){
			sql.append(" AND MODULE_CODE =  '"+moduleCode+"' ");
		}
		if(DataUtil.isNotNull(parentCode)){
			
			//查询   支柱产业 和 产业园  主模块      一期的时候  当为主模块时 parnetCode 有的是 null 有的不是空
			if(parentCode.equals("MAIN") || parentCode.equals("IG_C000")) {
				sql.append(" AND ( PARENT_CODE IS NULL OR PARENT_CODE='' OR PARENT_CODE='MODULE_E_MAIN' ) ");
			}else{
			//子模块
				sql.append(" AND  PARENT_CODE = '"+parentCode+"'");
			}
		}
		
		sql.append(" ORDER BY SORT,CREATE_TIME DESC  ");
		
		return this.SelectAll(sql.toString());
	}

	@Override
	public Page searchCompanyPage(Map<String, Object> param,
			Company company,List<Map> mapList) {
		
//		StringBuffer sql = new StringBuffer(" SELECT CP.COMP_ID AS CID,CP.PROP_TYPE,I.NAME AS INAME,CI.* FROM  COMPANIES_PROP CP " +
//				"LEFT JOIN COMPANY_INFO CI ON  CP.COMP_ID = CI.ID " +
//				"LEFT JOIN INDUSTRY_GARDEN I ON CP.PROP_VALUE = I.ID  " +
//				"WHERE CP.PROP_TYPE ='PROP_F' ");
//		
		StringBuffer sql = new StringBuffer(" SELECT CI.ID  AS CID,CI.* FROM  COMPANIES_PROP CP  " +
				" LEFT JOIN COMPANY_INFO CI ON  CP.COMP_ID = CI.ID  WHERE CP.PROP_TYPE ='PROP_F'   ");

		
		
		if(DataUtil.isNotNull(company.getCp_name())){
			sql.append(" AND CI.CP_NAME LIKE '%"+company.getCp_name()+"%' ");
		}
		if(DataUtil.isNotNull(company.getCp_product())){
			sql.append(" AND CI.CP_PRODUCT LIKE '%"+company.getCp_product()+"%' ");
		}
		if(DataUtil.isNotNull(company.getCp_modelstr())){
			if(company.getCp_modelstr().equals("YES")){        //只查询优质企业
				sql.append(" AND CI.ID IN ( SELECT COMP_ID AS ID FROM COMPANIES_PROP WHERE PROP_TYPE = 'PROP_C' ) ");
			}else if(company.getCp_modelstr().equals("NO")){   //只查询不是优质企业的
				sql.append(" AND CI.ID NOT IN ( SELECT COMP_ID AS ID FROM COMPANIES_PROP WHERE PROP_TYPE = 'PROP_C' ) ");
			}
		}
		if(DataUtil.isNotNull(company.getPropg())){
			if(company.getPropg().equals("YES")){        //只查询推荐企业
				sql.append(" AND CI.ID IN ( SELECT COMP_ID AS ID FROM COMPANIES_PROP WHERE PROP_TYPE = 'PROP_G' AND PROP_VALUE ='F3' ) ");
			}else if(company.getPropg().equals("NO")){   //只查询不推荐的企业
				sql.append(" AND CI.ID NOT IN ( SELECT COMP_ID AS ID FROM COMPANIES_PROP WHERE PROP_TYPE = 'PROP_C'  AND PROP_VALUE ='F3' ) ");
			}
		}
		
		
		if(DataUtil.isNotNull(company.getCp_belongstr())){
			sql.append(" AND CP.PROP_VALUE = '"+company.getCp_belongstr()+"' ");
		}else{
			StringBuffer sb = getMapStr(mapList,"ID","'");
			
			sql.append(" AND CP.PROP_VALUE IN('',"+sb.toString()+")");
		}
		
		sql.append("AND CI.IS_SHOW = '1' AND CI.STATUS = '2' ");
		sql.append(" GROUP BY CI.ID  ");
		
		param.put("sql", sql.toString());
		
		return this.queryBySql(param);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<String> queryHighQualityCompany() {
		
		//查询所有优质企业
		String sql = " SELECT COMP_ID AS ID FROM COMPANIES_PROP WHERE PROP_TYPE = 'PROP_C'   ";
		
	  
		List<Map> list = this.SelectAll(sql);

	    List<String> returnList = new ArrayList<String>();
	    for (Map map : list) {
	    	returnList.add(map.get("ID").toString());
		}
	    
		return returnList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<String> queryRecommendCompany() {
		
		//查询所有推荐企业
		String sql = " SELECT COMP_ID AS ID FROM COMPANIES_PROP WHERE PROP_TYPE = 'PROP_G' AND PROP_VALUE = 'F3'  ";
		
		List<Map> list = this.SelectAll(sql);

	    List<String> returnList = new ArrayList<String>();
	    for (Map map : list) {
	    	returnList.add(map.get("ID").toString());
		}
		return returnList;
	}
	
	
	
	@Override
	public void deleteIndustryCompany(String compIds) {
		
		String str = sqlFormat(compIds);
		
		String sql = " DELETE FROM  COMPANIES_PROP WHERE COMP_ID IN ("+str+")  AND PROP_TYPE IN ('PROP_C','PROP_F') ";
		
		this.executeSQL(sql);
	}

	@Override
	public void deleteCPbyCompids(String compIds,String propType,boolean type) {

		String str = sqlFormat(compIds);
		
		String sql = " DELETE FROM  COMPANIES_PROP WHERE COMP_ID IN ("+str+")  AND PROP_TYPE = '"+propType+"' ";
		
		//推荐企业属性
		if(!type){
			sql+=" AND PROP_VALUE = 'F3' ";
		}
		
		this.executeSQL(sql);
		
	}

	@Override
	public void insertCPbyCompIds(String compIds,boolean type) {
		
		List<String> sqllist = new ArrayList<String>();
		String[] idarr = compIds.split(",");
		
		for (String cid : idarr) {
			String uuid = UUID.randomUUID().toString().trim().replaceAll("-", ""); 
			StringBuffer sql =null ;
			if(type){
				sql	= new StringBuffer(" INSERT INTO COMPANIES_PROP  VALUES('"+uuid+"','"+cid+"','','PROP_C',NULL,'',NOW())");
			}else{
				sql	= new StringBuffer(" INSERT INTO COMPANIES_PROP  VALUES('"+uuid+"','"+cid+"','F3','PROP_G',NULL,'',NOW())");
			}
			sqllist.add(sql.toString());
		}
		if(sqllist.size()>0){
			this.UpdateDataMore(sqllist);
		}
	}

	@Override
	public void setCompanyIndustryClass(String compIds, String industryIds) {
		
		this.deleteCPbyCompids(compIds, "PROP_F",true);
		
		List<String> sqllist = new ArrayList<String>();
		String[] comparr = compIds.split(",");
		String[] industryarr = industryIds.split(",");
		//遍历 企业
		for (String comp : comparr) {
			if(DataUtil.isNotNull(industryIds)){
				//遍历分类
				for (String industry : industryarr) {
					String uuid = UUID.randomUUID().toString().trim().replaceAll("-", ""); 
					StringBuffer sql = new StringBuffer(" INSERT INTO COMPANIES_PROP  VALUES('"+uuid+"','"+comp+"','"+industry+"','PROP_F',NULL,'',NOW())");
					sqllist.add(sql.toString());
				}
			}else{
				String uuid = UUID.randomUUID().toString().trim().replaceAll("-", ""); 
				StringBuffer sql = new StringBuffer(" INSERT INTO COMPANIES_PROP  VALUES('"+uuid+"','"+comp+"','','PROP_F',NULL,'',NOW())");
				sqllist.add(sql.toString());
			}
			
		}
		
		if(sqllist.size()>0){
			this.UpdateDataMore(sqllist);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> selectIndustryClass(String compIds) {
		
		String str = sqlFormat(compIds);
		
		String sql = " SELECT P.COMP_ID AS CID,G.NAME AS NAME  FROM  COMPANIES_PROP P LEFT JOIN INDUSTRY_GARDEN G ON P.PROP_VALUE = G.ID  WHERE COMP_ID IN ("+str+")  AND PROP_TYPE = 'PROP_F' ";

		return this.SelectAll(sql);
		
	}

	@Override
	public void addCompay(String compIds) {
		
		//过滤掉已经存在的企业id，只保留不存在的
		String notExistCompIds = queryNotExist(compIds);
		
		List<String> sqllist = new ArrayList<String>();
		String[] comparr = notExistCompIds.split(",");
		for (String comp : comparr) {
			if(DataUtil.isNotNull(comp)){
				String uuid = UUID.randomUUID().toString().trim().replaceAll("-", ""); 
				StringBuffer sql = new StringBuffer(" INSERT INTO COMPANIES_PROP  VALUES('"+uuid+"','"+comp+"','','PROP_F',NULL,'',NOW())");
				sqllist.add(sql.toString());
			}
		}
		if(sqllist.size()>0){
			this.UpdateDataMore(sqllist);
		}
		
	}
	
    @SuppressWarnings("rawtypes")
	private String queryNotExist(String compIds){
    	
    	String str = sqlFormat(compIds);
    	
    	//查询已经存在的企业id
    	String sql = "SELECT COMP_ID AS ID FROM COMPANIES_PROP WHERE PROP_TYPE = 'PROP_F' AND  COMP_ID IN ("+str+") ";
    	
    	List<Map> mapList = this.SelectAll(sql);
    	for (Map map : mapList) {
    		if(str.contains(map.get("ID").toString())){
    			compIds = compIds.replace(map.get("ID").toString(),"");
    		}
		}
    	
    	return compIds;
    } 
    
	
	/**
	 * 遍历map 将 键为 key的 值  通过 传入的 format 拼接成    字符串  
	 * @param mapList
	 * @param key
	 * @param format   比如  单引号 空格等
	 * @return
	 */
	private StringBuffer getMapStr(List<Map> mapList,String key,String format){
	
		StringBuffer sb = new StringBuffer("");
		
		if(DataUtil.isNotNull(mapList) && mapList.size()>0){
			for(int i=0;i<mapList.size();i++){
				if(mapList.size()-1==i){
					sb.append(format+mapList.get(i).get(key)+format);
				}else{
					sb.append(format+mapList.get(i).get(key)+format+",");
				}
			}
		}
		return sb;
	}
    
    
	//   "a,b,c,d"  ==> "'a','b','c'"   字符串转换
    private	String sqlFormat(String string){
    	if(DataUtil.isNull(string)){
    		return "''";
    	}
    	StringBuffer str = new StringBuffer();
    	String[] idarr = string.split(",");
		
		for(int i=0;i<idarr.length;i++){
			if(DataUtil.isNotNull(idarr[i])){
				if(i==idarr.length-1){
					str.append("'"+idarr[i]+"'");
				}else{
					str.append("'"+idarr[i]+"',");
				}
			}
			
		} 
    	return str.toString();
    }


}
