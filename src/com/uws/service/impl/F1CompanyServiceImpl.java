package com.uws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.model.BaseModel2;
import com.base.util.DataUtil;
import com.uws.dao.ICompanyManagerDAO;
import com.uws.dao.IF1CompanyDAO;
import com.uws.model.Company;
import com.uws.model.CompanyProp;
import com.uws.model.Product;
import com.uws.service.IF1CompanyService;
/**
 * 企业信息企业列表Service实现
 * @author 张学彪
 */
@Service("f1CompanyService")
public class F1CompanyServiceImpl implements IF1CompanyService{

	@Autowired
	private IF1CompanyDAO companyDAO;
	
	@Resource
	private ICompanyManagerDAO companyManagerDao;

	@Override
	public Page queryCompanyListByModule(Map<String, Object> param,
			Company company, Product product,String moduleCode) {
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
		return companyDAO.queryCompanyListByModule(param, company, moduleCode);
	}

	@Override
	public void chooseCompany(String companyIds, String moduleCode) {
		List<String> idList = new ArrayList<String>();
		if(DataUtil.isNotNull(companyIds)){
			String[] idArray = companyIds.split(",");
			for(String id:idArray){
				idList.add(id);
			}
		}
		if(idList.size()>0){
			companyDAO.chooseCompany(idList, moduleCode);
		}
	}

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
			companyDAO.cancelCompany(idList);
		}
	}

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
			companyDAO.setOrCancelRecommendCompany(idList, propType);
		}
	}

	@Override
	public List<Company> queryEnableCompany(int status) {
		List<Company> list= new ArrayList<Company>();
		List<BaseModel2> modelList = companyDAO.queryEnableCompany(status);
		for(BaseModel2 bm:modelList){
			list.add((Company)bm);
		}
		return list;
	}

	@Override
	public boolean hasF1Realation(String moduleCode) {
		return companyDAO.hasF1Realation(moduleCode);
	}

	@Override
	public void initF1Prop(List<Company> companys, String moduleCode) {
		List<String> list = new ArrayList<String>();
		if(companys != null && companys.size() > 0){
			for(Company cm:companys){
				list.add(cm.getId());
			}
		}
		companyDAO.initF1Prop(list, moduleCode);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<String> getNoExsitIds(String companyIds,String propValue) {
		List<String> idList = new ArrayList<String>();
		String[] arr = companyIds.split(",");
		List<Map> modelList = companyDAO.queryPropByTypeAndValue(propValue);
		for(String id:arr){
			boolean flag = true;
			for(Map m:modelList){
				if(m.get("COMP_ID").equals(id)){
					flag = false;
					break;
				}
			}
			if(flag){
				idList.add(id);
			}
		}
		return idList;
	}

	@Override
	public void saveProp(List<String> ids, String propType, String propValue) {
		CompanyProp cp = null;
		if(ids !=null && ids.size()>0){
			for(String id:ids){
				cp= new CompanyProp();
				cp.setCompId(id);
				cp.setPropType(propType);
				cp.setPropValue(propValue);
				companyDAO.saveProp(cp);
			}
		}
	}

}
