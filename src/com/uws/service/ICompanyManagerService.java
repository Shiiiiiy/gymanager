package com.uws.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.uws.model.Company;
import com.uws.model.Product;

@Service("companyManagerService")
public interface ICompanyManagerService {
	public Page search(Map<String, Object> param,Company company,Product product);
	
	/**
	 * 查询所有子节点
	 * @param parentCode 父编码
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getDicListByParentCode(String parentCode);
	
	/**
	 * 查询省市区列表
	 * @param parentCode 父编码
	 * @return List<Map<String, Object>>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAreaList(String parentCode);
	
	public String saveCompany(Company company);
	
	public Company getCompanyModel(String id);
	
	public void updateCompany(Company company);
	
	public void deleteCompany(Company company);
	
	public List<Product> getProductList(Product product);
	
	public void deleteProductList(Product product);
	
	public String saveProduct(Product pro);
	
	public Product getProductModel(String id);
	
	public void updateProduct(Product product);
	
	public String getDic(String code,String name);
	
	public Map getDicByID(String id);
	/**
	 * 查询所有启用企业 查询组件
	 * @param param 分页参数
	 * @param 查询条件
	 */
	public Page searchComponent(Map<String, Object> param, Company company);
}
