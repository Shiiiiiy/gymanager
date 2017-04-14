package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;

/**
 * 用户管理业务接口
 * @author MaXiaoDong
 *
 */
public interface IDemoService {

	/**
	 * 查询 用户信息详情
	 * */
	public Page search(Map<String, Object> param);
	
	/**
	 * 菜单树形结构
	 * */
	public List<Map> Dtree();
}
