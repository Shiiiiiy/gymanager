package com.uws.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.util.DataUtil;
import com.uws.dao.IExcellentPersonDAO;
import com.uws.model.ExcellentPerson;
/**
 * 创新人才Dao实现
 * @author 张学彪
 */
@Repository
public class ExcellentPersonDAOImpl extends BaseDAOImpl implements IExcellentPersonDAO{

	@Override
	public Page queryExcellentPersonList(Map<String, Object> param,
			ExcellentPerson person) {
		StringBuffer sql=new StringBuffer("SELECT * FROM EXCELLENT_PERSON WHERE 1=1  ");
		
		if(person != null){
			if(DataUtil.isNotNull(person.getPsName())){
				sql.append(" AND PS_NAME LIKE '%").append(person.getPsName()).append("%'");
			}
			if(DataUtil.isNotNull(person.getIsInnovate())){
				sql.append(" AND IS_INNOVATE = '").append(person.getIsInnovate()).append("'");
			}
			if(DataUtil.isNotNull(person.getIsLeader())){
				sql.append(" AND IS_LEADER = '").append(person.getIsLeader()).append("'");
			}
		}
		
		sql.append(" ORDER BY CREATE_TIME DESC");
		
		param.put("sql", sql.toString());
		
		return queryBySql(param);
	}

	@Override
	public ExcellentPerson queryExcellentPersonByPersonId(String personId) {
		return (ExcellentPerson)this.getSe(ExcellentPerson.class, personId);
	}

	@Override
	public void saveExcellentPerson(ExcellentPerson person) {
		this.save2(person);
	}

	@Override
	public void updateExcellentPerson(ExcellentPerson person) {
		this.updateSe(person);
	}

	@Override
	public void deleteExcellentPersonById(String personId) {
		StringBuffer sb = new StringBuffer("DELETE FROM EXCELLENT_PERSON WHERE ID = '").append(personId).append("'");
		this.executeSQL(sb.toString());
	}

	@Override
	public void deleteExcellentPersonsByIds(List<String> personIds) {
		StringBuffer sb = new StringBuffer("DELETE FROM EXCELLENT_PERSON WHERE ID in ('");
		for(int i=1;i<=personIds.size();i++){
			if(i == personIds.size()){
				sb.append(personIds.get(i-1));
				sb.append("')");
			}else{
				sb.append(personIds.get(i-1));
				sb.append("','");
			}
		}
		this.executeSQL(sb.toString());
	}
	
	@Override
	public void changeStatus(String personId, String value) {
		StringBuffer sb = new StringBuffer("UPDATE EXCELLENT_PERSON SET STATUS = '").append(value).append("' WHERE ID = '").append(personId).append("'");
		this.executeSQL(sb.toString());
	}
	
}
