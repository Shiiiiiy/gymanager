package com.uws.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.model.BaseModel2;
import com.base.util.DataUtil;
import com.uws.dao.IDynamicDataDAO;
import com.uws.model.FinanceEnvironment;
import com.uws.model.InnovBusiness;
import com.uws.model.InnovatePlatForm;
import com.uws.model.InvestCompanies;
import com.uws.model.MarketCompanies;
import com.uws.model.NewCompanies;
import com.uws.model.TechAchiev;
import com.uws.model.TechInnovation;
import com.uws.service.IDynamicDataService;
/**
 * 技术创新-动态数据Service实现
 * @author 张学彪
 */
@Service("dynamicDataService")
public class IDynamicDataServiceImpl implements IDynamicDataService{

	@Autowired
	private IDynamicDataDAO dynamicData;

	@Override
	public Page queryTechInnovationList(Map<String, Object> param,
			TechInnovation echInnovation) {
		return dynamicData.queryTechInnovationList(param, echInnovation);
	}

	@Override
	public TechInnovation queryTechInnovationById(String echInnovationId) {
		return dynamicData.queryTechInnovationById(echInnovationId);
	}

	@Override
	public void saveTechInnovation(TechInnovation echInnovation) {
		dynamicData.saveTechInnovation(echInnovation);
	}

	@Override
	public void updateTechInnovation(TechInnovation echInnovation) {
		dynamicData.updateTechInnovation(echInnovation);
	}

	@Override
	public void deleteTechInnovationById(String echInnovationId) {
		dynamicData.deleteTechInnovationById(echInnovationId);
	}

	@Override
	public void deleteTechInnovationsByIds(String echInnovationIds) {
		List<String> idList = new ArrayList<String>();
		if(DataUtil.isNotNull(echInnovationIds)){
			String[] idArray = echInnovationIds.split(",");
			for(String id:idArray){
				idList.add(id);
			}
		}
		if(idList.size()>0){
			dynamicData.deleteTechInnovationsByIds(idList);
		}
	}

	@Override
	public void changeStatus(String echInnovationId, String value,
			String tableName) {
		dynamicData.changeStatus(echInnovationId, value, tableName);
	}

	@Override
	public Page queryTechAchievList(Map<String, Object> param,
			TechAchiev techAchiev) {
		return dynamicData.queryTechAchievList(param, techAchiev);
	}

	@Override
	public TechAchiev queryTechAchievById(String techAchievId) {
		return dynamicData.queryTechAchievById(techAchievId);
	}

	@Override
	public void saveTechAchiev(TechAchiev techAchiev) {
		dynamicData.saveTechAchiev(techAchiev);
	}

	@Override
	public void updateTechAchiev(TechAchiev techAchiev) {
		dynamicData.updateTechAchiev(techAchiev);
	}

	@Override
	public void deleteTechAchievById(String techAchievId) {
		dynamicData.deleteTechAchievById(techAchievId);
	}

	@Override
	public void deleteTechAchievsByIds(String techAchievIds) {
		List<String> idList = new ArrayList<String>();
		if(DataUtil.isNotNull(techAchievIds)){
			String[] idArray = techAchievIds.split(",");
			for(String id:idArray){
				idList.add(id);
			}
		}
		if(idList.size()>0){
			dynamicData.deleteTechAchievsByIds(idList);
		}
	}

	@Override
	public void saveModel(BaseModel2 model) {
		dynamicData.saveModel(model);
	}

	@Override
	public void deleteModelById(String id, String tableName) {
		dynamicData.deleteModelById(id, tableName);
	}

	@Override
	public void deleteModelByIds(String ids, String tableName) {
		List<String> idList = new ArrayList<String>();
		if(DataUtil.isNotNull(ids)){
			String[] idArray = ids.split(",");
			for(String id:idArray){
				idList.add(id);
			}
		}
		if(idList.size()>0){
			dynamicData.deleteModelByIds(idList, tableName);
		}
	}

	@Override
	public void updateModel(BaseModel2 model) {
		dynamicData.updateModel(model);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BaseModel2 queryModelById(Class clazz, String id) {
		return dynamicData.queryModelById(clazz, id);
	}

	@Override
	public Page queryInnovatePlatFormList(Map<String, Object> param,
			InnovatePlatForm innovatePlatForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page queryFinanceEnvironmentList(Map<String, Object> param,
			FinanceEnvironment financeEnvironment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page queryNewCompaniesList(Map<String, Object> param,
			NewCompanies newCompanies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page queryInvestCompaniesList(Map<String, Object> param,
			InvestCompanies investCompanies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page queryMarketCompaniesList(Map<String, Object> param,
			MarketCompanies marketCompanies) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page queryInnovBusinessList(Map<String, Object> param,
			InnovBusiness innovBusiness) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page queryModelList(Map<String, Object> param, String tableName) {
		Page page = dynamicData.queryModelList(param, tableName);
		if(page != null && page.getList()!=null && page.getList().size()>0){
			for(Map map:page.getList()){
				if(DataUtil.isNotNull(map.get("PLAT_TIME"))){
					String ptime = map.get("PLAT_TIME").toString();
					map.put("PLAT_TIME", ptime.substring(0,ptime.length()-2));
				}
				if(DataUtil.isNotNull(map.get("START_TIME"))){
					String stime = map.get("START_TIME").toString();
					map.put("START_TIME", stime.substring(0,stime.length()-2));
				}
				if(DataUtil.isNotNull(map.get("MARKET_TIME"))){
					String mtime = map.get("MARKET_TIME").toString();
					map.put("MARKET_TIME", mtime.substring(0,mtime.length()-2));
				}
			}
		}
		return page;
	}
}
