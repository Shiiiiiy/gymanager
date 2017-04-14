package com.uws.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;

public interface IDemoDAO {

	/**
	 * 查询 用户信息详情
	 * */
	public Page search(Map<String, Object> param);
	
	/**
	 * 菜单树形结构
	 * */
	public List<Map> Dtree();
}
