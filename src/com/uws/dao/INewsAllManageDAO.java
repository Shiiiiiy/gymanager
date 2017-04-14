package com.uws.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.NewsAll;

/**
 * 新闻（资讯/动态/政策表）Dao
 * @author 张学彪
 */
public interface INewsAllManageDAO {

	/**
	 * 查询新闻列表
	 * param : 分页参数
	 * news ： 查询条件
	 * */
	public Page queryNewsAllListByModule(Map<String, Object> param, NewsAll news);
	
	/**
	 * 通过新闻ID查询新闻详细信息
	 * @param newsId
	 */
	public NewsAll queryNewsAllByNewsId(String newsId);
	
	/**
	 * 新增新闻
	 * news ： 新闻model
	 * */
	public void saveNewsAll(NewsAll news);
	
	/**
	 * 修改新闻
	 * news ： 新闻model
	 * */
	public void updateNewsAll(NewsAll news);
	
	/**
	 * 删除新闻
	 * newsId ： 新闻ID
	 * */
	public void deleteNewsAllById(String newsId);
	
	/**
	 * 批量删除新闻
	 * newsIds ： 新闻ID集合
	 * */
	public void deleteNewsAllsByIds(List<String> newsIds);
	
	/**
	 * 修改新闻启用禁用状态
	 * @param newsId 新闻ID
	 * @param value 状态值
	 */
	public void changeStatus(String newsId, String value);

	/**
	 * 获取诚信服务机构新闻列表
	 * @return
	 */
	public Page getIntegrityAgencyList(Map<String, Object> param,String tableName);
}
