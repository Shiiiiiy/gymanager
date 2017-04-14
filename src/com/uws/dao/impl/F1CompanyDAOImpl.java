package com.uws.dao.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.model.BaseModel2;
import com.base.util.DataUtil;
import com.uws.dao.IF1CompanyDAO;
import com.uws.model.Company;
import com.uws.model.CompanyProp;
/**
 * 企业信息企业列表Dao实现
 * @author 张学彪
 */
@Repository
public class F1CompanyDAOImpl extends BaseDAOImpl implements IF1CompanyDAO{

	public void deleteNewsAllsByIds(List<String> newsIds) {
		StringBuffer sb = new StringBuffer("DELETE FROM NEWS_ALL WHERE ID in ('");
		for(int i=1;i<=newsIds.size();i++){
			if(i == newsIds.size()){
				sb.append(newsIds.get(i-1));
				sb.append("')");
			}else{
				sb.append(newsIds.get(i-1));
				sb.append("','");
			}
		}
		this.executeSQL(sb.toString());
	}


	@Override
	public Page queryCompanyListByModule(Map<String, Object> param,
			Company company,String moduleCode) {
		StringBuffer sb =new StringBuffer("SELECT CP.ID AS CID,CP.PROP_TYPE,CI.* FROM companies_prop CP LEFT JOIN company_info CI ON CP.COMP_ID = CI.ID WHERE 1=1 ");
		if(DataUtil.isNotNull(moduleCode)){
			sb.append(" AND CP.PROP_VALUE = '").append(moduleCode).append("'");
		}
		if(DataUtil.isNotNull(company.getCp_name())){
			sb.append(" AND CI.CP_NAME LIKE '%").append(company.getCp_name()).append("%'");
		}
		if(DataUtil.isNotNull(company.getCp_product())){
			sb.append(" AND CI.CP_PRODUCT LIKE '%").append(company.getCp_product()).append("%'");
		}
		if(DataUtil.isNotNull(company.getPropg())){
			sb.append(" AND CP.PROP_TYPE = '").append(company.getPropg()).append("'");
		}
		if(company.getId() !=null){
			if(company.getId().equals("")){
				sb.append("AND CI.ID in ('')");
			}else{
				sb.append("AND CI.ID in ("+company.getId()+")");
			}
		}
		//2期必须
		sb.append("AND CI.IS_SHOW = '").append(1).append("'");
		sb.append("AND CI.STATUS = '").append(2).append("'");
		//
		param.put("sql", sb.toString());
		return queryBySql(param);
	}

	@Override
	public void chooseCompany(List<String> companyIds, String moduleCode) {
		
	}

	@Override
	public void cancelCompany(List<String> propIds) {
		StringBuffer sb = new StringBuffer("DELETE FROM COMPANIES_PROP WHERE ID IN('");
		for(int i=1;i<=propIds.size();i++){
			if(i == propIds.size()){
				sb.append(propIds.get(i-1));
				sb.append("')");
			}else{
				sb.append(propIds.get(i-1));
				sb.append("','");
			}
		}
		this.executeSQL(sb.toString());
	}

	@Override
	public void setOrCancelRecommendCompany(List<String> propIds,
			String propType) {
		StringBuffer sb = new StringBuffer("UPDATE COMPANIES_PROP SET PROP_TYPE = '").append(propType).append("' WHERE ID IN('");
		for(int i=1;i<=propIds.size();i++){
			if(i == propIds.size()){
				sb.append(propIds.get(i-1));
				sb.append("')");
			}else{
				sb.append(propIds.get(i-1));
				sb.append("','");
			}
		}
		this.executeSQL(sb.toString());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean hasF1Realation(String moduleCode) {
		StringBuffer sql=new StringBuffer("SELECT COUNT(1) FROM COMPANIES_PROP WHERE PROP_VALUE='").append(moduleCode).append("'");
		Map result = this.doSelect(sql.toString());
		return result!=null && result.size()>0;
	}

	@Override
	public void initF1Prop(List<String> companyIds, String moduleCode) {
		List<String> sqlList = new ArrayList<String>();
		if(companyIds.size()>0){
			for(String id:companyIds){
				sqlList.add(new StringBuffer().append("INSERT INTO COMPANIES_PROP VALUES('").append(id).append("','").append(moduleCode).append("','','','',NOW());").toString());
			}
			this.UpdateDataMore(sqlList);
		}
	}

	@Override
	public List<BaseModel2> queryEnableCompany(int status) {
		StringBuffer sql=new StringBuffer("FROM CompanyModel WHERE STATUS = ?");
		return this.findSe(sql.toString(),new String[]{String.valueOf(status)});
	}


	@Override
	public List<Map> queryPropByTypeAndValue(String propValue) {
		StringBuffer sql=new StringBuffer("SELECT COMP_ID FROM COMPANIES_PROP WHERE PROP_VALUE=?");
		return this.SelectAll(sql.toString(),new String[]{propValue});
	}


	@Override
	public void saveProp(CompanyProp cp) {
		this.save2(cp);
	}
	
}
