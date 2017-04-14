package com.uws.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.util.DefaultValue;
import com.uws.dao.IIndustryGardenDao;
import com.uws.dao.IPillarIndustryDao;
import com.uws.dao.ISysDicDao;
import com.uws.model.Company;
import com.uws.model.Dic;
import com.uws.model.IndustryGarden;
import com.uws.service.IPillarIndustryService;
import com.uws.util.Constants;

@Service("pillarIndustryService")
public class PillarIndustryServiceImpl implements IPillarIndustryService {

		@Resource
		private IPillarIndustryDao pillarIndustryDao;
		
		@Resource 
		private IIndustryGardenDao industryGardenDao;
		
		@Resource
		private ISysDicDao dicDao;

		@SuppressWarnings("rawtypes")
		@Override
		public List<String> getIndustryInitList() {

			//获取需要初始化的十三个产业园区的id集合  
			List<String> IndustryIdList =new ArrayList<String>(Constants.INDUSTRY_MAP.keySet());
			
			//获取数据库所有产业园集合
			List<Map> industryList = this.industryGardenDao.queryIndustryGardenList(Constants.INDUSTRY_D, null,true);
			
			//数据库所有产业园的id结合
			List<String> industryListId = new ArrayList<String>();
			
			for (Map map : industryList) {
				industryListId.add(map.get("ID").toString());
			}
			
			//返回数据库中不存在的产业园的id集合
			List<String> returnList = new ArrayList<String>(); 
			for (String string : IndustryIdList) {
				if(!industryListId.contains(string)){
					returnList.add(string);
				}
			}
			return returnList;
		}

		@Override
		public void initIndustry(String ids) {
			
			String[] idarr = ids.split(",");
			List<IndustryGarden> list = new ArrayList<IndustryGarden>();
			
			Dic enable = dicDao.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
			
			for (String string : idarr) {
				IndustryGarden industry = new IndustryGarden();
				industry.setId(string);
				industry.setName(Constants.INDUSTRY_MAP.get(string));
				industry.setCreateTime(new Date());
				industry.setIgType(Constants.INDUSTRY_TYPE);
				industry.setIndustryType(Constants.INDUSTRY_D);
				industry.setStatus(enable.getId());
				list.add(industry);
			}
			
			this.saveIndustryList(list);
		}

		
		@Override
		public void saveIndustryList(List<IndustryGarden> list) {
			
			for (IndustryGarden industryGarden : list) {
				this.industryGardenDao.saveModel2(industryGarden);
			}
		}

		@SuppressWarnings("rawtypes")
		@Override
		public List<Map> queryFileInfoList(String moduleCode, String parentCode ) {
		
			return this.pillarIndustryDao.queryFileInfoList(moduleCode, parentCode);
		}

	
		@Override
		public Page searchCompanyPage(Map<String, Object> param,
				Company company,List<Map> mapList) {
			
			Page page = pillarIndustryDao.searchCompanyPage(param, company,mapList);
			
			//设置 企业  所属的  产业分类  （  填充一个字段显示在 列表页）
			this.setIndustryClass(page);
			
			return page;
		}

		@Override
		public List<String> queryHighQualityCompany() {
			return this.pillarIndustryDao.queryHighQualityCompany();
		}

		@Override
		public void deleteIndustryCompany(String compIds) {
			this.pillarIndustryDao.deleteIndustryCompany(compIds);
		}

		@Override
		public void changeCompanyHq(String compIds, String changeType,String type) {
			
			if("1".equals(changeType)){         
				if("YES".equals(type)){
					this.pillarIndustryDao.deleteCPbyCompids(compIds,"PROP_C",true);
					this.pillarIndustryDao.insertCPbyCompIds(compIds,true);
				}else if("NO".equals(type)){
					this.pillarIndustryDao.deleteCPbyCompids(compIds,"PROP_C",true);
				}
			}
			if("2".equals(changeType)){         
				if("YES".equals(type)){
					this.pillarIndustryDao.deleteCPbyCompids(compIds,"PROP_G",false);
					this.pillarIndustryDao.insertCPbyCompIds(compIds,false);
				}else if("NO".equals(type)){
					this.pillarIndustryDao.deleteCPbyCompids(compIds,"PROP_G",false);
				}
			}
		}

		@Override
		public void setCompanyIndustryClass(String compIds, String industryIds) {
			
				this.pillarIndustryDao.setCompanyIndustryClass(compIds, industryIds);
			
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void setIndustryClass(Page page){
			
			List<Map> result = page.getList();
			
			if(result!=null && result.size()>0){
				
				//获取所有企业id集合 ，通过 , 拼接成字符串
				StringBuffer compIds = new StringBuffer();
				for (Map map : result) {
					compIds.append(map.get("ID")).append(",");
				}
				String compIdstr = compIds.substring(0, compIds.length()-1);
				
				//根据上面的id集合  获取这些 企业 所属的 产业分类信息集合
				List<Map> icla = this.pillarIndustryDao.selectIndustryClass(compIdstr);
				
				// 企业id为key，分类名称为 value
				Map<String,String> newMap = new HashMap<String, String>();
				
				for (Map map : icla) { //遍历上面查询的结果 按 【 id为key，分类名称为 value】的形式保存到 newMap中
					if(newMap.containsKey(map.get("CID"))){  // 一个企业有可能有多个分类，首先获取之前保存的信息 和这次的 拼接到一起
						newMap.put(map.get("CID").toString(), newMap.get(map.get("CID"))+",<br/>"+map.get("NAME"));
					}else{
						newMap.put(map.get("CID").toString(), map.get("NAME")==null?"":map.get("NAME").toString());
					}
				}
				
				for (Map map : result) {  
					map.put("CLASSNAME", newMap.get(map.get("ID")));
				}
			}
		}

		@Override
		public void addCompany(String compIds) {
			
			this.pillarIndustryDao.addCompay(compIds);
		}

		@Override
		public List<String> queryRecommendCompany() {
			return this.pillarIndustryDao.queryRecommendCompany();
		}
		
}
