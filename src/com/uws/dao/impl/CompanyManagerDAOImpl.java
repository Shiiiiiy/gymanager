package com.uws.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.uws.dao.ICompanyManagerDAO;
import com.uws.model.Company;
import com.uws.model.Product;
import com.uws.util.Util;

@Repository
public class CompanyManagerDAOImpl extends BaseDAOImpl implements ICompanyManagerDAO{
	
	/**
	 * 查询企业基本信息
	 */
	public Page search(Map<String, Object> param,Company company){
		StringBuffer sql = new StringBuffer("SELECT * FROM COMPANY_INFO WHERE 1=1 ");
		if(!Util.isNull(company.getCp_name())){
			sql.append("AND cp_name LIKE '%"+company.getCp_name()+"%'");
		}
		if(company.getId()!=null){
			if(company.getId().equals("")){
				sql.append("AND id in ('')");
			}else{
				sql.append("AND id in ("+company.getId()+")");
			}
		}
		//
		sql.append("AND IS_SHOW = '"+1+"'");/**只查询展示的企业*/
		//
		sql.append(" ORDER BY CREATE_TIME DESC,CP_NAME "); 
		
		param.put("sql", sql.toString());
		Page page = queryBySql(param);
		
		return searchProductName(page);
		
	}
	/**
	 * 查询所有启用企业  公用查询组件
	 * @param param 分页参数
	 * @param 查询条件
	 */
	@Override
	public Page searchComponent(Map<String, Object> param, Company company) {
		StringBuffer sql = new StringBuffer("SELECT * FROM COMPANY_INFO WHERE 1=1 ");
		if(!Util.isNull(company.getCp_name())){
			sql.append("AND CP_NAME LIKE '%"+company.getCp_name()+"%'");
		}
		//
		sql.append("AND IS_SHOW = '"+1+"'");/**只查询企业*/
		sql.append("AND STATUS = '"+2+"'");/**启用的*/
		//
		sql.append(" ORDER BY CREATE_TIME DESC,CP_NAME "); 
		
		param.put("sql", sql.toString());
		Page page = queryBySql(param);
		
		return searchProductName(page);
	}
	/**
	 * 查询产品列表
	 */
	@SuppressWarnings("rawtypes")
	public List<Product> getProductList(Product product){
		SimpleDateFormat sm  = new SimpleDateFormat("yyyy-MM-dd");
		List<Product> listPro = new ArrayList<Product>();
		StringBuffer sql = new StringBuffer("SELECT * FROM PRODUCT_INFO N WHERE 1=1 ");
		if(!Util.isNull(product.getProduct_name())){
			sql.append(" AND N.PRODUCT_NAME LIKE '%"+product.getProduct_name()+"%'");
		}
		if(product.getProduct_type()!=null&&!Util.isNull(product.getProduct_type())){
			sql.append(" AND N.PRODUCT_TYPE = '"+product.getProduct_type()+"'");
		}
		if(!Util.isNull(product.getProduct_comp())){
			sql.append(" AND N.PRODUCT_COMP = '"+product.getProduct_comp()+"'");
		}
		if(!Util.isNull(product.getProduct_capacity())){
			sql.append(" AND N.product_capacity LIKE '%"+product.getProduct_capacity()+"%'");
		}
		List<Map> listMap = SelectAll(sql.toString());
		for(Map list : listMap){
			Product pro = new Product();
			pro.setId(list.get("ID").toString());
			pro.setProduct_name(list.get("PRODUCT_NAME")==null?"":list.get("PRODUCT_NAME").toString());
			pro.setProduct_comp(list.get("PRODUCT_COMP")==null?"":list.get("PRODUCT_COMP").toString());
			pro.setProduct_num(list.get("PRODUCT_NUM")==null?"":list.get("PRODUCT_NUM").toString());
			pro.setStatusstr(list.get("STATUS")==null?"":list.get("STATUS").toString());
			pro.setComments(list.get("COMMENTS")==null?"":list.get("COMMENTS").toString());
			pro.setProduct_capacity(list.get("PRODUCT_CAPACITY")==null?"":list.get("PRODUCT_CAPACITY").toString());
			pro.setProduct_typeName(list.get("PRODUCT_TYPE_NAME")==null?"":list.get("PRODUCT_TYPE_NAME").toString());
			pro.setProduct_type(list.get("PRODUCT_TYPE")==null?"":list.get("PRODUCT_TYPE").toString());
			try {
				if(!Util.isNull(list.get("PRODUCT_TIME")==null?"":list.get("PRODUCT_TIME").toString())){
					pro.setProduct_time(sm.parse(list.get("PRODUCT_TIME").toString()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			listPro.add(pro);
		}
		return listPro ;
	}
	/**查询 企业产品信息*/
	public Page searchProductName(Page page){
		List<Map> list=	page.getList();
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				StringBuffer sql = new StringBuffer("SELECT DISTINCT B.NAME FROM DIC B LEFT JOIN PRODUCT_INFO A ON A.PRODUCT_TYPE=B.CODE  WHERE  A.PRODUCT_COMP = '");
				String cpid=(String) list.get(i).get("ID");
				sql.append(cpid).append("'");
				try {
					List<Map> names=SelectAll(sql.toString());
					String zhi="";
					for (int j = 0; j < names.size(); j++) {
						if(j!=names.size()-1){
							zhi=zhi+(String)names.get(j).get("NAME")+",";
						}else{
							zhi=zhi+(String)names.get(j).get("NAME");
						}
					}
					list.get(i).put("COMMENTS", zhi);
				} catch (Exception e) {e.printStackTrace();}
			}
		}
		page.setList(list);
		return page;	
	}
	
	/**
	 * 查询所有子节点
	 * @param parentCode 父编码
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getDicListByParentCode(String parentCode){
		StringBuffer sql=new StringBuffer(" SELECT D.CODE as id,D.NAME as name FROM DIC D WHERE D.STATUS =  '1'");
		if(!Util.isNull(parentCode)){
			sql.append(" AND D.PARENTCODE='"+parentCode+"' ");
		}else{
			return null;
		}		
		
		return SelectAll(sql.toString());
	}
	
	
	/**
	 * 查询省市区列表
	 * @param parentCode 父编码
	 * @return List<Map<String, Object>>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAreaList(String parentCode){
		StringBuffer sql=new StringBuffer(" SELECT D.CODE,D.NAME,D.ID FROM DIC D WHERE D.STATUS =  '1' ");
		if(!Util.isNull(parentCode)){
			sql.append(" AND D.PARENTCODE= '"+parentCode+"' ");
		}else{
			return null;
		}	
		return SelectAll(sql.toString());
	}
	
	/**
	 * 保存企业基本信息
	 */
	public String saveCompany(Company company){
		company.setIs_show("1");/**区分企业表 与 企业用户*/
		company.setCreate_time(new Date());
		Company  com = (Company)save2(company);
		return com.getId();
	}
	/**
	 * 得到一个企业基本信息
	 */
	public Company getCompanyModel(String id){
		String hql = " from Company where id = '"+id+"'";
		return (Company)getSe(hql, null);
	}
	/**
	 * 修改企业基本信息
	 */
	public void updateCompany(Company company){
		company.setIs_show("1");
		updateSe(company);
	}
	
	/**
	 * 删除企业基本信息
	 */
	public void deleteCompany(Company company){
		String sql = "DELETE FROM companies_prop WHERE COMP_ID = '"+company.getId()+"'";
		String sql1 = "DELETE FROM product_info WHERE PRODUCT_COMP = '"+company.getId()+"'";
		String sql2 = "DELETE FROM file_info WHERE FILE_TYPE = 'FILE_PRO' AND parent_code = '"+company.getId()+"'";
		executeSQL(sql);
		executeSQL(sql1);
		executeSQL(sql2);
		deleteSe(company);
	}
	
	/**
	 * 保存一个产品
	 * @param pro
	 * @return
	 */
	public String saveProduct(Product pro){
		Product  com = (Product)save2(pro);
		return com.getId();
	}
	
	/**
	 * 得到一个产品
	 * @param id
	 * @return
	 */
	public Product getProductModel(String id){
		String hql = " from Product where id = '"+id+"'";
		return (Product)getSe(hql, null);
	}
	
	/**
	 * 修改一个产品
	 * @param product
	 */
	public void updateProduct(Product product){
		updateSe(product);
	}
	
	/**
	 * 删除产品信息
	 * @param product
	 */
	public void deleteProductList(Product product){
		deleteSe(product);
	}
	
	/**
	 * 得到 dic
	 * @param code
	 * @param name
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getDic(String code,String name){
		String sql = "select code from dic where parentcode = '"+code+"' AND name ='"+name+"'" ;
		List<Map> list = SelectAll(sql);
		if (list != null && list.size() > 0) {
			return list.get(0).get("code").toString();
		} else {
			return null;
		}
	}
	@Override
	public Map getDicByID(String id) {
		if(Util.isNull(id)){return null;}
		String sql = "SELECT * FROM DIC  A WHERE A.ID   = '"+id+"'" ;
		List<Map> list = SelectAll(sql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
