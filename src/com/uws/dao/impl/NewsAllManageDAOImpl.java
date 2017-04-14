package com.uws.dao.impl;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.util.DataUtil;
import com.uws.dao.INewsAllManageDAO;
import com.uws.model.NewsAll;
/**
 * 新闻管理（资讯/动态/政策表）Dao实现
 * @author 张学彪
 */
@Repository
public class NewsAllManageDAOImpl extends BaseDAOImpl implements INewsAllManageDAO{

	@Override
	public Page queryNewsAllListByModule(Map<String, Object> param, NewsAll news) {
		StringBuffer sql=new StringBuffer("SELECT *,DATE_FORMAT(NEWS_TIME,'%Y-%c-%d %h:%i:%s')AS STIME FROM NEWS_ALL WHERE 1=1  ");
		
		if(news != null){
			//新闻编码
			if(DataUtil.isNotNull(news.getModuleCode())){
				sql.append(" AND MODULE_CODE = '").append(news.getModuleCode()).append("'");
			}
			//新闻标题
			if(DataUtil.isNotNull(news.getNewsTitle())){
				sql.append(" AND NEWS_TITLE LIKE '%").append(news.getNewsTitle()).append("%'");
			}
			//新闻来源
			if(DataUtil.isNotNull(news.getNewsSource())){
				sql.append(" AND COMMENTS LIKE '%").append(news.getNewsSource()).append("%'");
			}
			//发布时间
			if(DataUtil.isNotNull(news.getNewsTime())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				sql.append(" AND DATE_FORMAT(NEWS_TIME,'%Y%m%d') = '").append(sdf.format(news.getNewsTime())).append("'");
			}
			//启用状态
			if(news.getStatus()!=0){
				sql.append(" AND STATUS = ").append(news.getStatus());
			}
			
		}
		
		sql.append(" ORDER BY CREATE_TIME DESC");
		
		param.put("sql", sql.toString());
		
		return queryBySql(param);
	}

	@Override
	public NewsAll queryNewsAllByNewsId(String newsId) {
		return (NewsAll)this.getSe(NewsAll.class, newsId);
	}

	@Override
	public void saveNewsAll(NewsAll news) {
		this.save2(news);
	}

	@Override
	public void updateNewsAll(NewsAll news) {
		this.updateSe(news);
	}

	@Override
	public void deleteNewsAllById(String newsId) {
		StringBuffer sb = new StringBuffer("DELETE FROM NEWS_ALL WHERE ID = '").append(newsId).append("'");
		this.executeSQL(sb.toString());
	}

	@Override
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
	public void changeStatus(String newsId, String value) {
		StringBuffer sb = new StringBuffer("UPDATE NEWS_ALL SET STATUS = '").append(value).append("' WHERE ID = '").append(newsId).append("'");
		this.executeSQL(sb.toString());
	}

	@Override
	public Page getIntegrityAgencyList(Map<String, Object> param,String tableName) {
		String sql = "";
		if(tableName != null && !"".equals(tableName)){
			sql = "select * from " + tableName + " order by CREATE_TIME desc";
		}
		param.put("sql",sql);
		return queryBySql(param);
	}
	
}
