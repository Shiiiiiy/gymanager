package com.uws.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.uws.dao.IDemoDAO;
import com.uws.model.Demo;
/**
 * 用户表数据操作
 * @author MaXiaoDong
 *
 */
@Repository
public class DemoDAOImpl extends BaseDAOImpl implements IDemoDAO{
	
	/**
	 * 查询 用户信息详情
	 * */
	public Page search(Map<String, Object> param){
		Demo demo = new Demo();
		demo.setName("2131231231212");
		//this.save(demo);
//		demo = (Demo) this.get(Demo.class, 10l);
//		demo.setPassword("1212323");
//		this.update(demo);
		
		
//		this.delete(demo);
//		List list = this.find("FROM Demo");
		String sql = "SELECT * FROM SYS_USER";
		
		
//		String temp = "SELECT * FROM SYS_USER WHERE ID = ?";
//		List<Object> values=new ArrayList();
//		values.add(5);
//		List<Map> ff = this.SelectAll(temp, values.toArray());
//		
		
		
		param.put("sql", sql);
		Page page = queryBySql(param);
		return page;
		
	}
	
	
	/**
	 * 菜单树形结构
	 * */
	public List<Map> Dtree(){
		String sql = "SELECT ID,IFNULL(P_ID,0) P_ID,`TITLE` FROM SYS_MENU";
		return this.SelectAll(sql);
		
	}
}
