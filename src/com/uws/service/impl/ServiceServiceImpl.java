package com.uws.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.util.DataUtil;
import com.uws.dao.ICompanyManagerDAO;
import com.uws.dao.IIndustryGardenDao;
import com.uws.dao.INewsAllManageDAO;
import com.uws.dao.IServiceDao;
import com.uws.dao.ISysMenuDAO;
import com.uws.model.Company;
import com.uws.model.NewsAll;
import com.uws.model.Product;
import com.uws.service.IIndustryGardenService;
import com.uws.service.IServiceService;
/**
 * @Description: 产业服务接口实现类
 * @author: wangjun 
 */
@Service
public class ServiceServiceImpl implements IServiceService {
	/**产业服务 dao层*/
	@Resource
	private IServiceDao serviceDao;
	/**新闻管理 dao层*/
	@Resource
	private INewsAllManageDAO newsAllManageDAO;
	/**产品管理 dao层*/
	@Resource
	private ICompanyManagerDAO companyManagerDao;
	/**
	 * 查询字典分类详细信息
	 * @param ig_type产业或园区类型  industry_type 产业类型
	 * @return
	 */
	@Override
	public List<Map> getIndustryList(String ig_type, String industry_type) {
		return serviceDao.getIndustryList(ig_type,industry_type);
	}
	/**
	 * 获取产品服务新闻
	 * @param param 分页参数
	 * @param news 新闻类实体
	 * @param moduleType
	 * @param parentCode
	 * @return Page
	 */
	@Override
	public Page queryProductServiceNews(Map<String, Object> param, NewsAll news,String moduleType,String parentCode,String userid) {
		return serviceDao.queryProductServiceNews(param,news,moduleType,parentCode,userid);
	}
	/**
	 * 删除 一个 或多个新闻
	 * @param ids 新闻ids
	 * @return
	 */
	@Override
	public void deleteNews(String ids) {
		
		String[] idarr = ids.split(",");
		
		List<String> idlist = new ArrayList<String>();
		
		for (String string : idarr) {
			idlist.add(string);
		}
		this.newsAllManageDAO.deleteNewsAllsByIds(idlist);
	}
	/**
	 * 新增或更新园区新闻（动态+政策）
	 * @param project
	 */
	@Override
	public void saveOrUpdateNews(NewsAll news) {
		if(DataUtil.isNotNull(news.getId())){//修改
			NewsAll newNews = newsAllManageDAO.queryNewsAllByNewsId(news.getId());
			newNews.setComments(news.getComments());    //备注
			newNews.setIntroduce(news.getIntroduce());  //简介
			if(DataUtil.isNotNull(news.getParentCode()) && news.getParentCode().equals("N") ){
				newNews.setParentCode(null);
			}else{
				newNews.setParentCode(news.getParentCode());// 所属园区 或所属产业支柱模块 
			}
			newNews.setNewsTitle(news.getNewsTitle());//标题
			newNews.setNewsContent(news.getNewsContent());//内容
			newNews.setStatus(news.getStatus());//状态
			newNews.setNewsSource(news.getNewsSource());//来源
			newNews.setModuleType(null);//新闻类型
			newsAllManageDAO.updateNewsAll(newNews);
		}else{//新增
			news.setNewsTime(new Date());
			news.setModuleType(null);//新闻类型
			if(DataUtil.isNotNull(news.getParentCode()) && news.getParentCode().equals("N") ){
				news.setParentCode(null);
			}
			newsAllManageDAO.saveNewsAll(news);
		}
	}
	/**
	 *批量取消  企业列表的关系
	 *@param propIds 企业ids
	 * */
	@Override
	public void cancelCompany(String propIds) {
		List<String> idList = new ArrayList<String>();
		if(DataUtil.isNotNull(propIds)){
			String[] idArray = propIds.split(",");
			for(String id:idArray){
				idList.add(id);
			}
		}
		if(idList.size()>0){
			serviceDao.cancelCompany(idList);
		}
	}
	/**
	 * 批量设置选择的企业为推荐企业或不推荐企业（包含单个操作）
	 * propIds IDs
	 * propType 关系类型（是否推荐）
	 */
	@Override
	public void setOrCancelRecommendCompany(String propIds, String propType) {
		List<String> idList = new ArrayList<String>();
		if(DataUtil.isNotNull(propIds)){
			String[] idArray = propIds.split(",");
			for(String id:idArray){
				idList.add(id);
			}
		}
		if(idList.size()>0){
			serviceDao.setOrCancelRecommendCompany(idList, propType);
		}
		
	}
	@Override
	public Page queryCompanyListByModule(Map<String, Object> param,	Company company, Product product, String mCode, String parentCode, String uid){
		/**
		 * 通过产品名称模糊查询经营该产品的企业
		 */
		if(product != null && DataUtil.isNotNull(product.getProduct_name())){
			List<Product> listPro = companyManagerDao.getProductList(product);
			String companyId = "";
			for(int i = 0 ; i < listPro.size() ; i ++){
				if(i == listPro.size()-1){
					companyId += "'"+listPro.get(i).getProduct_comp()+"'";
				}else{
					companyId += "'"+listPro.get(i).getProduct_comp()+"',";
				}
			}
			company.setId(companyId);
		}
		return serviceDao.queryCompanyListByModule(param,company,mCode,parentCode,uid);
	}
	 /**
	 * 批量 添加企业分类属性
	 *@param propIds 企业ids
	 *@param propType 企业分类属性ids
	 * */
	@Override
	public void addClassCompany(String propIds, String propType) {
		List<String> idList = new ArrayList<String>();
		if(DataUtil.isNotNull(propIds)){
			String[] idArray = propIds.split(",");
			for(String id:idArray){
				idList.add(id);
			}
		}
		List<String> typeList = new ArrayList<String>();
		if(DataUtil.isNotNull(propType)){
			String[] idArray = propType.split(",");
			for(String id:idArray){
				typeList.add(id);
			}
		}
		if(idList.size()>0 && typeList.size()>0){
			serviceDao.addClassCompany(idList,typeList);
		}
	}
	/**
	 *从企业库  向产品服务 批量 添加企业
	 *@param  companyIds 企业ids
	 * */
	@Override
	public void saveServiceCompany(String companyIds) {
		List<String> idList = new ArrayList<String>();
		if(DataUtil.isNotNull(companyIds)){
			String[] idArray = companyIds.split(",");
			for(String id:idArray){
				idList.add(id);
			}
		}
		if(idList.size()>0){
			serviceDao.saveServiceCompany(idList);
		}
	}
	/**
	 * 查询 产品服务 轮播图FILE_SLIDER 和 统计图FILE_STATISTICS
	 * @param moduleCode 文件表moduleCode
	 * @param parentCode 文件表parentCode
	 * */
	@Override
	public List<Map> queryFileInfoList(String moduleCode, String parentCode) {
		return serviceDao.queryFileInfoList(moduleCode,parentCode);
	}
	/**
	 * 产业服务下  工业产品/生产服务的分类
	 * @param industry_type 产业类型
	 * @return
	 */
	@Override
	public List<Map> getIndustryList(String industry_type) {
		return serviceDao.getIndustryList(industry_type);
	}
	/**
	 * 通过id 获取 Industry名称
	 * @param id
	 * */
	@Override
	public String getIndustryNameByID(String id) {
		return serviceDao.getIndustryNameByID(id);
	}
}
