package com.uws.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.base.dao.Page;
import com.base.model.BaseModel2;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.dao.IIndustryGardenDao;
import com.uws.dao.INewsAllManageDAO;
import com.uws.dao.ISysDicDao;
import com.uws.model.Dic;
import com.uws.model.GardenProject;
import com.uws.model.IndustryGarden;
import com.uws.model.NewsAll;
import com.uws.service.IIndustryGardenService;
import com.uws.util.Constants;

/**
 * @ClassName: IndustryGardenServiceImpl 
 * @Description: 产业园管理接口实现类
 * @author: shiyan 
 * @date: 2017-3-6 下午1:12:33
 */
@Service("industryGardenService")
public class IndustryGardenServiceImpl implements IIndustryGardenService {

	@Resource
	private IIndustryGardenDao industryGardenDao;
	
	@Resource
	private INewsAllManageDAO newsAllManageDAO;
	
	@Resource
	private ISysDicDao dicDao;

	@Override
	public Page searchIndustryGardenPage(Map<String, Object> param,String igType ,String industryType,IndustryGarden model,List<Map> mapList) {
		return this.industryGardenDao.searchIndustryGardenPage(param, igType,industryType,model,mapList);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryIndustryGardenList(String industryType, String igType,boolean containsMainGarden) {
		return this.industryGardenDao.queryIndustryGardenList(industryType, igType,containsMainGarden);
	}

	@Override
	public IndustryGarden findById(String id) {
		return (IndustryGarden)this.industryGardenDao.findById(IndustryGarden.class,id);
	}

	@Override
	public void updateModel2(BaseModel2 model) {
		this.industryGardenDao.updateModel2(model);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<String> getGardenInitList() {
		
		//获取需要初始化的十三个产业园区的id集合  
		List<String> gardenIdList =new ArrayList<String>(Constants.GARDEN_MAP.keySet());
		
		//获取数据库所有产业园集合
		List<Map> gardenList = this.queryIndustryGardenList(null,Constants.GARDEN_TYPE,true);
		
		//数据库所有产业园的id结合
		List<String> gardenListId = new ArrayList<String>();
		
		for (Map map : gardenList) {
			gardenListId.add(map.get("ID").toString());
		}
		
		//返回数据库中不存在的产业园的id集合
		List<String> returnList = new ArrayList<String>(); 
		for (String string : gardenIdList) {
			if(!gardenListId.contains(string)){
				returnList.add(string);
			}
		}
		
		return returnList;
	}

	@Override
	public void saveGardenList(List<IndustryGarden> list) {
		
		for (IndustryGarden industryGarden : list) {
			this.industryGardenDao.saveModel2(industryGarden);
		}
		
	}

	@Override
	public void initGarden(String ids) {
		
		String[] idarr = ids.split(",");
		List<IndustryGarden> list = new ArrayList<IndustryGarden>();
		
		Dic enable = dicDao.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		for (String string : idarr) {
			
			IndustryGarden garden = new IndustryGarden();
			garden.setId(string);
			garden.setName(Constants.GARDEN_MAP.get(string));
			garden.setCreateTime(new Date());
			garden.setIgType(Constants.GARDEN_TYPE);
			garden.setStatus(enable.getId());
			
			list.add(garden);
		}
		
		this.saveGardenList(list);
		
	}

	@Override
	public Page queryGardenProject(Map<String, Object> param,
			GardenProject project,List<Map> mapList) {
		return this.industryGardenDao.queryGardenProject(param, project,mapList);
	}

	@Override
	public void saveOrUpdateProject(GardenProject project) {
		
		//id不为空就是更新
		if(DataUtil.isNotNull(project.getId())){
			GardenProject newProject = this.findProjectById(project.getId());
			newProject.setOfGarden(project.getOfGarden());  //所属园区
			newProject.setProjectContent(project.getProjectContent()); //项目内容
			newProject.setProjectInvest(project.getProjectInvest()); //项目投资金额
			newProject.setProjectName(project.getProjectName()); //项目名称
			newProject.setProjectStatus(project.getProjectStatus());//项目状态
			newProject.setProjectType(project.getProjectType());//项目类型
			newProject.setProjectTime(project.getProjectTime());//项目启动时间
			newProject.setProjectOvertime(project.getProjectOvertime());//项目结束时间
			newProject.setComments(project.getComments());//备注
			
			this.updateModel2(newProject);
		}
		//否则即是新增
		else{
			this.industryGardenDao.saveModel2(project);
		}
		
	}

	@Override
	public void saveModel2(BaseModel2 model) {
		this.industryGardenDao.saveModel2(model);
	}

	@Override
	public GardenProject findProjectById(String id) {
		return (GardenProject)this.industryGardenDao.findById(GardenProject.class, id);
	}

	@Override
	public void deleteProject(String ids) {
		this.industryGardenDao.deleteProjectByIds(ids);
	}

	@Override
	public Page queryGardenNews(Map<String, Object> param, NewsAll news,List<Map> mapList) {
		return this.industryGardenDao.queryGardenNews(param, news, mapList);
	}

	@Override
	public void saveOrUpdateNews(NewsAll news) {
		
		if(DataUtil.isNotNull(news.getId())){
			NewsAll newNews = newsAllManageDAO.queryNewsAllByNewsId(news.getId());
			newNews.setComments(news.getComments());    //备注
			newNews.setIntroduce(news.getIntroduce());  //简介
			if(DataUtil.isNotNull(news.getParentCode()) && news.getParentCode().equals("MODULE_E_MAIN") || news.getParentCode().equals("IG_C000")){
				newNews.setParentCode(null);
			}else{
				newNews.setParentCode(news.getParentCode());// 所属园区 或所属产业支柱模块 
			}
			newNews.setNewsTitle(news.getNewsTitle());//标题
			newNews.setNewsContent(news.getNewsContent());//内容
			newNews.setStatus(news.getStatus());//状态
			newNews.setNewsSource(news.getNewsSource());//来源
			newNews.setModuleType(news.getModuleType());//新闻类型
			this.updateModel2(newNews);
			
		}else{
			news.setNewsTime(new Date());
			
			if(DataUtil.isNotNull(news.getParentCode()) && news.getParentCode().equals("MODULE_E_MAIN") || news.getParentCode().equals("IG_C000")){
				news.setParentCode(null);
			}
			
			this.saveModel2(news);
		}
		
	}

	@Override
	public void deleteNews(String ids) {
		
		String[] idarr = ids.split(",");
		
		List<String> idlist = new ArrayList<String>();
		
		for (String string : idarr) {
			idlist.add(string);
		}
		
		this.newsAllManageDAO.deleteNewsAllsByIds(idlist);
		
	}
	
}
