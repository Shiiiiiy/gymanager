package com.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

	/**
	 * 批量自动获取分页时必须参数
	 * @param request
	 * @return
	 */
	public Map<String, Object> initParamMap(HttpServletRequest request){
		Map<String, Object> param = new HashMap<String, Object>();
		int pageNo = request.getParameter("pageNo") != null?Integer.parseInt(request.getParameter("pageNo")):1;
		int pageSize = request.getParameter("pageSize") != null?Integer.parseInt(request.getParameter("pageSize")):10;
		String pageSearchType = request.getParameter("pageSearchType");
		if(pageSearchType==null || pageSearchType.equals("")){
			pageNo = 1;
		}
		param.put("pageNo",pageNo);
		param.put("pageSize",pageSize);
		return param;
	}
}
