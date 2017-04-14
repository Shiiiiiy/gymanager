package com.base.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.base.dao.IBaseDAO;
import com.base.dao.Page;
import com.base.model.BaseModel;
import com.base.model.BaseModel2;
import com.base.util.DefaultValue;
import com.uws.model.Demo;


@SuppressWarnings("all")
@Repository
public class BaseDAOImpl implements IBaseDAO {

	private SessionFactory sessionFactory;
	
	private DataSource dataSource;
/*******************************************************************************************Hibernate***********************************************************************************************/	
	
	/**
	 * 保存一个对象
	 * @param o
	 * @return
	 */
	public BaseModel save(BaseModel o) {
		o.setRecTime(new Date());
	    o.setUpdateTime(new Date());
	    Long id = (Long)this.getCurrentSession().save(o);
	    this.getCurrentSession().flush();
	    o.setId(id);
		return o ;
	}
	
	/**
	 * 保存一个对象
	 * @param o
	 * @return
	 */
	public BaseModel2 save2(BaseModel2 o) {
		if(o.getCreateTime() == null)
			o.setCreateTime(new Date());
	    o.setUpdateTime(new Date());
	    String id = (String)this.getCurrentSession().save(o);
	    this.getCurrentSession().flush();
	    o.setId(id);
		return o ;
	}
	
	
	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(BaseModel o) {
		this.getCurrentSession().delete(o);
		getCurrentSession().flush();
	}

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void deleteSe(BaseModel2 o) {
		this.getCurrentSession().delete(o);
		getCurrentSession().flush();
	}
	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(BaseModel o) {
	    o.setUpdateTime(new Date());
		this.getCurrentSession().update(o);
		getCurrentSession().flush();
	}


	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void updateSe(BaseModel2 o) {
	    o.setUpdateTime(new Date());
		this.getCurrentSession().update(o);
		getCurrentSession().flush();
	}

	
	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<BaseModel> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	
	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<BaseModel2> findSe(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	
	
	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<BaseModel> find(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<BaseModel2> findSe(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}
	
	
	/**
	 * 获得一个对象
	 * 
	 * @param class1 对象类型
	 * @param i
	 * @return Object
	 */
	public BaseModel get(Class class1, Serializable id) {
		return (BaseModel) this.getCurrentSession().get(class1, id);
	}



	/**
	 * 获得一个对象
	 * 
	 * @param class1 对象类型
	 * @param i
	 * @return Object
	 */
	public BaseModel2 getSe(Class class1, Serializable id) {
		return (BaseModel2) this.getCurrentSession().get(class1, id);
	}
	
	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public BaseModel get(String hql, Object[] param) {
		List<BaseModel> l =  this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}
	

	
	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public BaseModel2 getSe(String hql, Object[] param) {
		List<BaseModel2> l =  this.findSe(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}
	
/*************************************************************************************************SQL操作***************************************************************************/	
	
	
	/**
	 * 根据SQL返回SQL执行后的结果集，以List的形式返回，查询多条数据
	 */
	public List<Map> SelectAll(String sql) {
		List<Map> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		try {
			con = this.dataSource.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			
			String key = "";
			String value = "";
			if(rs!=null){
				while (rs.next()) {
					int maxFeild = rsmd.getColumnCount();
					Map map = new HashMap();
					for (int i = 0; i < maxFeild; i++) {
						key = rsmd.getColumnName(i + 1);
						value = rs.getString(i + 1);
						map.put(key, value);
					}
					list.add(map);
				}
			}
			rs.close();
			ps.close();
			con.close();

			rsmd = null;
			rs = null;
			ps = null;
			con = null;
		} catch (Exception e) {
			rsmd = null;
			if(rs!=null){
				try {
					rs.close();
				}catch (SQLException e1) {
					
				}
				rs = null;
			}
			if(ps!=null){
				try {
					ps.close();
				}catch (SQLException e1) {
					
				}
				ps = null;
			}
			if(con!=null){
				try {
					con.close();
				}catch (SQLException e1) {
					
				}
				con = null;
			}
			System.out.println("SelectImp SelectAll 查询失败："+sql);
		}
		return list;
	}
	
	/**
	 * 根据SQL返回SQL执行后的结果集，以List的形式返回，查询多条数据
	 */
	public List<Map> SelectAll(String sql,Object[] param){
		List<Map> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		try {
			con = this.dataSource.getConnection();
			ps = con.prepareStatement(sql);
			for(int i=0;i<param.length;i++){
				ps.setObject(i+1, param[i]);
			}
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			
			String key = "";
			String value = "";
			if(rs!=null){
				while (rs.next()) {
					int maxFeild = rsmd.getColumnCount();
					Map map = new HashMap();
					for (int i = 0; i < maxFeild; i++) {
						key = rsmd.getColumnName(i + 1);
						value = rs.getString(i + 1);
						map.put(key, value);
					}
					list.add(map);
				}
			}
			rs.close();
			ps.close();
			con.close();

			rsmd = null;
			rs = null;
			ps = null;
			con = null;
		} catch (Exception e) {
			rsmd = null;
			if(rs!=null){
				try {
					rs.close();
				}catch (SQLException e1) {
					
				}
				rs = null;
			}
			if(ps!=null){
				try {
					ps.close();
				}catch (SQLException e1) {
					
				}
				ps = null;
			}
			if(con!=null){
				try {
					con.close();
				}catch (SQLException e1) {
					
				}
				con = null;
			}
			System.out.println("SelectImp SelectAll 查询失败："+sql);
		}
		return list;
	}
	
	/**
	 * 根据SQL返回SQL执行后的结果集，以Map的形式返回，查询一条数据
	 */
	@SuppressWarnings("unchecked")
	public Map doSelect(String sql){
		Map map = new HashMap();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			con = this.dataSource.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			
			String key = "";
			String value = "";
			if(rs!=null){
				while (rs.next()) {
					int maxFeild = rsmd.getColumnCount();
					for (int i = 0; i < maxFeild; i++) {
						key = rsmd.getColumnName(i + 1);
						value = rs.getString(i + 1);
						map.put(key, value);
					}
					break;
				}
			}
			rs.close();
			ps.close();
			con.close();
			
			rsmd = null;
			rs = null;
			ps = null;
			con = null;
		} catch (Exception e) {
			rsmd = null;
			if(rs!=null){
				try {
					rs.close();
				}catch (SQLException e1) {
					
				}
				rs = null;
			}
			if(ps!=null){
				try {
					ps.close();
				}catch (SQLException e1) {
					
				}
				ps = null;
			}
			if(con!=null){
				try {
					con.close();
				}catch (SQLException e1) {
					
				}
				con = null;
			}
			System.out.println("SelectImp doSelect查询失败："+sql);
		}
		return map;
	}
	
	/**
	 * 统计SQL的数据量
	 * @param sql
	 * @return
	 */
	public long countData(String sql){
		long count = 0;
		String str = "SELECT COUNT(*) AS COUNTDATA FROM ("+sql+") AS TOTALTABLE";
		Map map = this.doSelect(str);
		String num = DefaultValue.getValueFromMap(map, "COUNTDATA");
		if(num==null || num.equals("")){
			num = "0";
		}
		count = Long.parseLong(num);
		return count;
	}
	


	/**
	 *	执行更新SQL语句
	 * @param sql
	 */
	public void executeSQL(String sql){
		Connection con = null;
		Statement st = null;
		try {
			con = this.dataSource.getConnection();
			st = con.createStatement();
			st.execute(sql);
			st.close();
			con.close();
			st = null;
			con = null;
		}catch (SQLException e) {
			if(st!=null){
				try {
					st.close();
				}catch (SQLException e1) {
					
				}
				st = null;
			}
			if(con!=null){
				try {
					con.close();
				}catch (SQLException e1) {
					
				}
				con = null;
			}
			System.out.println("SelectImp 执行语句失败，SQL："+sql);
		}
	}
	
	/**
	 * 批量更新SQL语句
	 * @param sqlAry SQL语句集合
	 */
	public void UpdateDataMore(List<String> sqlAry){
		Connection con = null;
		Statement st = null;
		try {
			con = this.dataSource.getConnection();
			con.setAutoCommit(false);
			
			String sql = sqlAry.get(0);
			PreparedStatement prest =con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			int size = 0;
			for(int i=0;i<sqlAry.size();i++){
				size ++;
				prest.addBatch(sqlAry.get(i));
				
				
				if(i==(sqlAry.size()-1)){
					prest.executeBatch();
				}else{
					if(size==500){
						size = 0;
						prest.executeBatch();
						prest.clearBatch();
					}
				}
			}
			con.commit();
			con.close();
			st = null;
			con = null;
		}catch (SQLException e) {
			if(st!=null){
				try {
					st.close();
				}catch (SQLException e1) {
					
				}
				st = null;
			}
			if(con!=null){
				try {
					con.close();
				}catch (SQLException e1) {
					
				}
				con = null;
			}
			System.out.println("SelectImp 批量更新SQL语句，SQL："+sqlAry);
		}
	}

	/**
	 * SQL分页查询
	 * @param sql 查询语句
	 * @param paramAry 查询条件
	 * @param pageNum 页数
	 * @param pageSize 每页大小
	 * @return
	 */
	public Page queryBySql(Map<String, Object> param){
		String sql = (String) param.get("sql");
		long countData = this.countData(sql);
		param.put("countData", countData);
		Page page = new Page();
		page.initSQL(param);
		List list = this.SelectAll(page.getSql());
		page.setList(list);
		page.initPageTool();
		return page;
	}
	

	
/*************************************************************************************************END***************************************************************************/	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


	public DataSource getDataSource() {
		return dataSource;
	}
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
