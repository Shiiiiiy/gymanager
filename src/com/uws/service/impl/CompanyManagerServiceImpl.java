package com.uws.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.uws.dao.ICompanyManagerDAO;
import com.uws.model.Company;
import com.uws.model.Product;
import com.uws.service.ICompanyManagerService;
import com.uws.util.Util;

@Service("companyManagerService")
public class CompanyManagerServiceImpl implements ICompanyManagerService{
	
	@Resource
	private ICompanyManagerDAO companyManagerDao;
	public Page search(Map<String, Object> param,Company company,Product product){
		
		if(!Util.isNull(product.getProduct_name())||
				(product.getProduct_type()!=null &&!Util.isNull(product.getProduct_type()))){
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
		
		
		return companyManagerDao.search(param,company);
	}
	
	/**
	 * 查询所有子节点
	 * @param parentCode 父编码
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getDicListByParentCode(String parentCode){
		return companyManagerDao.getDicListByParentCode(parentCode);
	}
	
	/**
	 * 查询省市区列表
	 * @param parentCode 父编码
	 * @return List<Map<String, Object>>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAreaList(String parentCode){
		return companyManagerDao.getAreaList(parentCode);
	}
	
	public String saveCompany(Company company){
		return companyManagerDao.saveCompany(company);
	}
	
	public Company getCompanyModel(String id){
		return companyManagerDao.getCompanyModel(id);
	}
	
	public void updateCompany(Company company){
		companyManagerDao.updateCompany(company);
	}
	
	public void deleteCompany(Company company){
		companyManagerDao.deleteCompany(company);
	}
	
	public List<Product> getProductList(Product product){
		return companyManagerDao.getProductList(product);
	}
	
	public void deleteProductList(Product product){
	    companyManagerDao.deleteProductList(product);
	}
	
	public String saveProduct(Product pro){
		return companyManagerDao.saveProduct(pro);
	}
	
	public Product getProductModel(String id){
		return companyManagerDao.getProductModel(id);
	}
	
	public void updateProduct(Product product){
		companyManagerDao.updateProduct(product);
	}
	
	public String getDic(String code,String name){
		return companyManagerDao.getDic(code, name);
	}

	@Override
	public Map getDicByID(String id) {
		return companyManagerDao.getDicByID(id);
	}
	/**
	 * 查询所有启用企业  公用查询组件
	 * @param param 分页参数
	 * @param 查询条件
	 */
	@Override
	public Page searchComponent(Map<String, Object> param, Company company) {
		return companyManagerDao.searchComponent(param,company);
	}
}
