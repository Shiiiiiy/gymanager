package com.uws.dao.impl;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.model.BaseModel2;
import com.base.util.DataUtil;
import com.uws.dao.IIndustryGardenDao;
import com.uws.model.GardenProject;
import com.uws.model.IndustryGarden;
import com.uws.model.NewsAll;

@Repository
public class IndustryGardenDaoImpl  extends BaseDAOImpl implements IIndustryGardenDao {

	@Override
	public Page searchIndustryGardenPage(Map<String, Object> param,String igType,String industryType,IndustryGarden model,List<Map> mapList) {

		StringBuffer sql = new StringBuffer("SELECT  G.ID AS ID,G.INTRODUCE AS INTRODUCE,G.NAME AS NAME,G.CREATE_TIME AS CREATE_TIME,D.NAME AS DNAME FROM INDUSTRY_GARDEN G LEFT JOIN SYS_DIC D ON G.USE_STATUS = D.ID  WHERE 1=1 ");
		
		if(DataUtil.isNotNull(igType)){
			sql.append(" AND G.IG_TYPE = '"+igType+"' ");
		}
		
		if(DataUtil.isNotNull(industryType)){
			sql.append(" AND G.INDUSTRY_TYPE = '"+industryType+"' ");
		}
		
		//下拉框选择的园区
		if(DataUtil.isNotNull(model) && DataUtil.isNotNull(model.getId())){
			sql.append(" AND G.ID = '"+model.getId()+"' ");
		}else{
			StringBuffer sb = getMapStr(mapList, "ID","'");
			sql.append(" AND G.ID in ("+sb.toString()+") ");
		}
		
		param.put("sql", sql.toString());
		
		Page page = this.queryBySql(param);
		
		return page;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> queryIndustryGardenList(String industryType, String igType,boolean containsMainGarden) {

		StringBuffer sql = new StringBuffer("SELECT G.ID AS ID,G.NAME AS NAME,G.IG_TYPE AS TYPE FROM INDUSTRY_GARDEN G WHERE  1=1 ");
		
		List<String> values = new ArrayList<String>();
		
		if(DataUtil.isNotNull(igType)){
			sql.append(" AND G.IG_TYPE = ? ");
			values.add(igType);
		}
		
		if(DataUtil.isNotNull(industryType)){
			sql.append(" AND G.INDUSTRY_TYPE = ? ");
			values.add(industryType);
		}
		
		if(!containsMainGarden){
			sql.append(" AND G.ID != ? ");
			values.add("IG_C000");
		}
		
		return this.SelectAll(sql.toString(),values.toArray());
	
	}

	@Override
	public BaseModel2 findById(@SuppressWarnings("rawtypes") Class c,String id) {
		return this.getSe( c, id);
	}

	@Override
	public void updateModel2(BaseModel2 model) {
		this.updateSe(model);
	}
	@Override
	public void saveModel2(BaseModel2 model) {
		this.save2(model);
	}

	@Override
	public Page queryGardenProject(Map<String, Object> param,
			GardenProject project,List<Map> mapList) {
		
		StringBuffer sql = new StringBuffer("  SELECT P.ID AS ID,P.PRO_NAME AS PRO_NAME,P.PRO_CONTENT AS PRO_CONTENT,P.PRO_TIME AS PRO_TIME,P.PRO_OVERTIME AS PRO_OVERTIME,P.PRO_INVEST AS PRO_INVEST,D1.NAME AS TYPE_NAME,D2.NAME AS STATUS_NAME,G.NAME AS GNAME FROM GARDEN_PROJECT P LEFT JOIN INDUSTRY_GARDEN G ON P.OF_GARDEN = G.ID " +
				" LEFT JOIN  SYS_DIC D1  ON P.STATUS = D1.CODE " +
				" LEFT JOIN  SYS_DIC D2  ON P.PRO_STATUS = D2.CODE WHERE 1 = 1 ");
		
		if(DataUtil.isNotNull(project.getOfGarden())){
			sql.append(" AND G.ID = '"+project.getOfGarden()+"' ");
		}else{
			StringBuffer sb = getMapStr(mapList, "ID","'");
			sql.append(" AND G.ID in ("+sb.toString()+") ");
		}
		if(DataUtil.isNotNull(project.getProjectType())){
			sql.append(" AND P.STATUS = '"+project.getProjectType()+"' ");
		}
		if(DataUtil.isNotNull(project.getProjectStatus())){
			sql.append(" AND P.PRO_STATUS = '"+project.getProjectStatus() +"' ");
		}
		if(DataUtil.isNotNull(project.getProjectName())){
			sql.append(" AND P.PRO_NAME LIKE '%"+project.getProjectName() +"%' ");
		}
		
		sql.append(" ORDER BY P.CREATE_TIME DESC ");
		
		param.put("sql",sql.toString());
		
		return this.queryBySql(param);
	}

	@Override
	public void deleteProjectByIds(String ids) {
		
		String[] idarr = ids.split(",");
		
		
		StringBuffer sql = new StringBuffer("DELETE FROM GARDEN_PROJECT WHERE ID IN ( ");
		
		for (int i=0;i<idarr.length;i++) {
			sql.append( "'"+idarr[i]+"'");
			
			if(i==idarr.length-1){
				sql.append( ")");
			}else{
				sql.append( ",");
			}
		}
		
		this.executeSQL(sql.toString());
	}

	@Override
	public Page queryGardenNews(Map<String, Object> param, NewsAll news,List<Map> mapList) {
		
		
		StringBuffer sql=new StringBuffer("SELECT N.ID AS ID,N.NEWS_TITLE AS NEWS_TITLE,N.NEWS_CONTENT AS NEWS_CONTENT,N.INTRODUCE AS INTRODUCE,N.NEWS_TIME AS NEWS_TIME,N.COMMENTS AS COMMENTS,N.STATUS AS STATUS,G.NAME AS GNAME,D.NAME AS DNAME FROM NEWS_ALL N " +
				" LEFT JOIN INDUSTRY_GARDEN G ON N.PARENT_CODE = G.ID " +
				" LEFT JOIN SYS_DIC D ON N.MODULE_TYPE = D.CODE  WHERE 1=1  ");
		
		//当没有 园区的查询条件时，就根据角色的拥有的权限来查询
		if(news ==null || DataUtil.isNull(news.getParentCode())){
			StringBuffer sb = getMapStr(mapList, "ID", "'");
			if(sb.toString().contains("IG_C000") || sb.toString().contains("MODULE_E_MAIN") ){
				sql.append(" AND ( N.PARENT_CODE IN ("+sb.toString().replace("IG_C000","").replace("MODULE_E_MAIN","")+")  OR N.PARENT_CODE IS NULL ) "); 
			}else{
				sql.append(" AND N.PARENT_CODE IN ("+sb.toString().replace("IG_C000","").replace("MODULE_E_MAIN","")+")"); 
			}
		}
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
			//启用状态
			if(news.getStatus()!=0){
				sql.append(" AND N.STATUS = ").append(news.getStatus());
			}
			//新闻类型
			if(DataUtil.isNotNull(news.getModuleType())){
				sql.append(" AND N.MODULE_TYPE = '").append(news.getModuleType()).append("'");
			}
			//所属园区
			if(DataUtil.isNotNull(news.getParentCode())  ){
				if( !news.getParentCode().equals("MODULE_E_MAIN") && !news.getParentCode().equals("IG_C000") ){
					sql.append(" AND N.PARENT_CODE = '").append(news.getParentCode()).append("'");
				}else{
					sql.append(" AND ( N.PARENT_CODE IS NULL OR N.PARENT_CODE='' ) ");
				}
			}
			
		}
		sql.append(" ORDER BY N.NEWS_TIME DESC "); 
		
		param.put("sql", sql.toString());
		
		return queryBySql(param);
		
		
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
	
	
	
	
}
