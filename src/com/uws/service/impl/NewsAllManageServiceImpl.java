package com.uws.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.util.DataUtil;
import com.uws.dao.INewsAllManageDAO;
import com.uws.model.NewsAll;
import com.uws.service.INewsAllManageService;
/**
 * 新闻（资讯/动态/政策表）Service实现
 * @author 张学彪
 */
@Service("newsALLManageService")
public class NewsAllManageServiceImpl implements INewsAllManageService{

	@Autowired
	private INewsAllManageDAO newsAllManageDAO;

	@Override
	public Page queryNewsAllListByModule(Map<String, Object> param, NewsAll news) {
		return newsAllManageDAO.queryNewsAllListByModule(param, news);
	}

	@Override
	public NewsAll queryNewsAllByNewsId(String newsId) {
		return newsAllManageDAO.queryNewsAllByNewsId(newsId);
	}

	@Override
	public void saveNewsAll(NewsAll news) {
		news.setNewsTime(new Date());
		newsAllManageDAO.saveNewsAll(news);
	}

	@Override
	public void updateNewsAll(NewsAll news) {
		newsAllManageDAO.updateNewsAll(news);
	}

	@Override
	public void deleteNewsAllById(String newsId) {
		newsAllManageDAO.deleteNewsAllById(newsId);
	}

	@Override
	public void deleteNewsAllsById(String newsIds) {
		List<String> idList = new ArrayList<String>();
		if(DataUtil.isNotNull(newsIds)){
			String[] idArray = newsIds.split(",");
			for(String id:idArray){
				idList.add(id);
			}
		}
		if(idList.size()>0){
			newsAllManageDAO.deleteNewsAllsByIds(idList);
		}
	}

	@Override
	public void changeStatus(String newsId, String value) {
		newsAllManageDAO.changeStatus(newsId, value);
	}

	@Override
	public Page getIntegrityAgencyList(Map<String, Object> param,String tableName) {
		return newsAllManageDAO.getIntegrityAgencyList(param,tableName);
	}
	
}
