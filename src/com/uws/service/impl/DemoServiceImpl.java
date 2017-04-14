package com.uws.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.uws.dao.IDemoDAO;
import com.uws.service.IDemoService;
/**
 * 用户管理业务接口实现类
 * @author MaXiaoDong
 *
 */
@Service("demoService")
public class DemoServiceImpl implements IDemoService {


	@Resource
	private IDemoDAO userDAO;
	
	/**
	 * 查询 用户信息详情
	 * */
	public Page search(Map<String, Object> param){
		return userDAO.search(param);
		
	}
	
	
	/**
	 * 菜单树形结构
	 * */
	public List<Map> Dtree(){
		return userDAO.Dtree();
		
	}
	
}
