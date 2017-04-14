package com.uws.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.model.BaseModel2;
import com.base.util.DataUtil;
import com.uws.dao.IDynamicDataDAO;
import com.uws.model.TechAchiev;
import com.uws.model.TechInnovation;
/**
 * 技术创新-动态数据Dao实现
 * @author 张学彪
 */
@Repository
public class DynamicDataDAOImpl extends BaseDAOImpl implements IDynamicDataDAO{

	@Override
	public Page queryTechInnovationList(Map<String, Object> param,
			TechInnovation echInnovation) {
		StringBuffer sql=new StringBuffer("SELECT *,DATE_FORMAT(START_TIME,'%Y-%c-%d %h:%i:%s') as STIME FROM TECH_INNOVATION WHERE 1=1  ");
		
		if(echInnovation != null){
			//启用状态
			if(echInnovation.getStatus()!=0){
				sql.append(" AND STATUS = ").append(echInnovation.getStatus());
			}
		}
		
		sql.append(" ORDER BY CREATE_TIME DESC");
		
		param.put("sql", sql.toString());
		
		return queryBySql(param);
	}

	@Override
	public TechInnovation queryTechInnovationById(String echInnovationId) {
		return (TechInnovation)this.getSe(TechInnovation.class, echInnovationId);
	}

	@Override
	public void saveTechInnovation(TechInnovation echInnovation) {
		this.save2(echInnovation);
	}

	@Override
	public void updateTechInnovation(TechInnovation echInnovation) {
		this.updateSe(echInnovation);
	}

	@Override
	public void deleteTechInnovationById(String echInnovationId) {
		StringBuffer sb = new StringBuffer("DELETE FROM TECH_INNOVATION WHERE ID = '").append(echInnovationId).append("'");
		this.executeSQL(sb.toString());
	}

	@Override
	public void deleteTechInnovationsByIds(List<String> echInnovationIds) {
		StringBuffer sb = new StringBuffer("DELETE FROM TECH_INNOVATION WHERE ID IN ('");
		for(int i=1;i<=echInnovationIds.size();i++){
			if(i == echInnovationIds.size()){
				sb.append(echInnovationIds.get(i-1));
				sb.append("')");
			}else{
				sb.append(echInnovationIds.get(i-1));
				sb.append("','");
			}
		}
		this.executeSQL(sb.toString());
	}

	@Override
	public void changeStatus(String echInnovationId, String value,
			String tableName) {
		StringBuffer sb = new StringBuffer("UPDATE ").append(tableName).append(" SET STATUS = '").append(value).append("' WHERE ID = '").append(echInnovationId).append("'");
		this.executeSQL(sb.toString());
	}

	@Override
	public Page queryTechAchievList(Map<String, Object> param,
			TechAchiev techAchiev) {
		StringBuffer sql=new StringBuffer("SELECT *,DATE_FORMAT(CERT_TIME,'%Y-%c-%d %h:%i:%s') as STIME,DATE_FORMAT(EFFECT_TIME,'%Y-%c-%d %h:%i:%s') as STIME1 FROM TECH_ACHIEV WHERE 1=1  ");
		
		if(techAchiev != null){
			//启用状态
			if(techAchiev.getStatus()!=0){
				sql.append(" AND STATUS = ").append(techAchiev.getStatus());
			}
			
			if(DataUtil.isNotNull(techAchiev.getType())){
				sql.append(" AND TYPE = '").append(techAchiev.getType()).append("'");
			}
		}
		
		sql.append(" ORDER BY CREATE_TIME DESC");
		param.put("sql", sql.toString());
		
		return queryBySql(param);
	}

	@Override
	public TechAchiev queryTechAchievById(String techAchievId) {
		return (TechAchiev)this.getSe(TechAchiev.class, techAchievId);
	}

	@Override
	public void saveTechAchiev(TechAchiev techAchiev) {
		this.save2(techAchiev);
	}

	@Override
	public void updateTechAchiev(TechAchiev techAchiev) {
		this.updateSe(techAchiev);
	}

	@Override
	public void deleteTechAchievById(String techAchievId) {
		StringBuffer sb = new StringBuffer("DELETE FROM TECH_ACHIEV WHERE ID = '").append(techAchievId).append("'");
		this.executeSQL(sb.toString());
	}

	@Override
	public void deleteTechAchievsByIds(List<String> techAchievIds) {
		StringBuffer sb = new StringBuffer("DELETE FROM TECH_ACHIEV WHERE ID IN ('");
		for(int i=1;i<=techAchievIds.size();i++){
			if(i == techAchievIds.size()){
				sb.append(techAchievIds.get(i-1));
				sb.append("')");
			}else{
				sb.append(techAchievIds.get(i-1));
				sb.append("','");
			}
		}
		this.executeSQL(sb.toString());
	}

	@Override
	public void saveModel(BaseModel2 model) {
		this.save2(model);
	}

	@Override
	public void updateModel(BaseModel2 model) {
		this.updateSe(model);
	}

	@Override
	public void deleteModelById(String id,String tableName) {
		StringBuffer sb = new StringBuffer("DELETE FROM ").append(tableName).append(" WHERE ID = '").append(id).append("'");
		this.executeSQL(sb.toString());
	}

	@Override
	public void deleteModelByIds(List<String> ids,String tableName) {
		StringBuffer sb = new StringBuffer("DELETE FROM ").append(tableName).append(" WHERE ID IN ('");
		for(int i=1;i<=ids.size();i++){
			if(i == ids.size()){
				sb.append(ids.get(i-1));
				sb.append("')");
			}else{
				sb.append(ids.get(i-1));
				sb.append("','");
			}
		}
		this.executeSQL(sb.toString());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BaseModel2 queryModelById(Class clazz,String id) {
		return this.getSe(clazz, id);
	}

	@Override
	public Page queryModelList(Map<String, Object> param,String tableName) {
		StringBuffer sql=new StringBuffer("SELECT * FROM ").append(tableName).append(" WHERE 1=1  ");
		
		sql.append(" ORDER BY CREATE_TIME DESC");
		param.put("sql", sql.toString());
		
		return queryBySql(param);
	}

}
