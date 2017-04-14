package com.base.dao;

import java.util.List;
import java.util.Map;

public class Page {
	
	private long pageSize = 10;//每页显示多少条数据
	
	private long page = 1;//当前页数
	
	private long maxPage = 0;//最大页数
	
	private long dataCount = 0;//数据总共有多少条
	
	private long sIndex = 1;//当前页数据开始的条数
	
	private long eIndex = 10;//当前页数据结束的条数
	
	private String pageTool;
	
	private List<Map> list = null;
	
	private String sql;
	
	/**分页构造函数
	* @param sql 包含筛选条件的sql，但不包含分页相关约束，如mysql的limit
	* @param currentPage 当前页
	* @param numPerPage 每页记录数
	* @param jTemplate JdbcTemplate实例
	*/
	public void initSQL(Map<String, Object> param){
		this.sql = (String)param.get("sql");
		this.dataCount = (Long)param.get("countData");
		//设置每页显示记录数
		this.pageSize = (Integer)param.get("pageSize");
		//设置要显示的页数
		this.page = (Integer)param.get("pageNo");
		if(page<1){
			page = 1;
		}
		
		
		
		this.sIndex = (this.page-1)*this.getPageSize()+1;
		this.eIndex = this.page*this.pageSize;
		if(eIndex>this.dataCount){
			this.eIndex = dataCount;
		}
		if((this.dataCount % this.pageSize) ==0){
			this.maxPage = this.dataCount / this.pageSize;
		}else{
			this.maxPage =  this.dataCount / this.pageSize+1;
		}
		
		StringBuffer sb = new StringBuffer("");
		

		sb.append("SELECT SEL_TEMP_TABLE.* FROM(");
		sb.append(sql);
		sb.append(") SEL_TEMP_TABLE");
		
		sb.append(" LIMIT ");
		sb.append(this.sIndex-1);
		sb.append(",");
		sb.append(this.pageSize);
		
		this.sql = sb.toString();
	}
	
	/**
	 * 设置翻页条
	 */
	public void initPageTool(){
		int[] pageSizeAry = {1,10,20,50,100};
		StringBuffer sb = new StringBuffer("");
		sb.append("		<div class=\"pagination_left\" id=\"sample_editable_1_info\">");
		sb.append("			<select name=\"pageSize\" style=\"width:80px;\" onchange=\"changePageSize();\">\n");
		for(int i=0;i<pageSizeAry.length;i++){
			if(pageSizeAry[i] == pageSize){
				sb.append("				<option selected>"+pageSizeAry[i]+"</option>\n");
			}else{
				sb.append("				<option>"+pageSizeAry[i]+"</option>\n");
			}
		}
		sb.append("			</select>\n");
		sb.append("			条/页|共 "+dataCount+" 条</div>\n");
		sb.append("			<input type=\"hidden\" name=\"pageNo\" id=\"pageNo\" value=\""+page+"\">\n");
		sb.append("			<input type=\"hidden\" name=\"pageSearchType\" id=\"pageSearchType\">\n");
		sb.append("		</div>\n");
		sb.append("		<div class=\"pagination\">\n");
		
		if(page == 1){
			sb.append("					<span class=\"current prev\">首页</span>\n");
		}else{
			sb.append("					<a class=\"prev\" href=\"javaScript:void(0);\" onclick=\"goTopages(1)\">首页</a>\n");
		}
		
		
		if(maxPage>9){
			long itemBIndex = page - 4;
			long itemEIndex = page + 4;
			if(itemBIndex<=0){
				itemBIndex = 1;
				itemEIndex = 9;
			}
			
			if(itemEIndex > maxPage){
				itemEIndex = maxPage;
			}
			for(long i = itemBIndex;i<itemEIndex + 1;i++){
				String selClass = "";
				if(i == page){
					sb.append("<span class=\"current\">"+i+"</span>\n");
				}else{
					sb.append("<a href=\"javaScript:void(0);\"  onclick=\"goTopages("+i+")\">"+i+"</a>\n");
				}
			}
			if(itemEIndex<(maxPage - 1)){
				sb.append("<a href=\"javaScript:void(0);\"  onclick=\"goTopages("+maxPage+")\">... "+maxPage+"</a>\n");
			}
		}else{
			for(int i = 1;i<maxPage + 1;i++){
				if(i == page){
					sb.append("<span class=\"current\">"+i+"</span>\n");
				}else{
					sb.append("<a href=\"javaScript:void(0);\"  onclick=\"goTopages("+i+")\">"+i+"</a>\n");
				}
			}
		}
		
		if(page == maxPage){
			sb.append("					<span class=\"current next\">末页</span>\n");
		}else{
			sb.append("<a href=\"javaScript:void(0);\"  onclick=\"goTopages("+maxPage+")\">末页</a>\n");
		}
		
		
		sb.append("</div>\n");
		
		sb.append("<script>\n");
		/**
		 * 获取当前页码条所在的Form的对象
		 */
		sb.append("	function getThisElementParentForm(){\n");
		sb.append("		var obj = $(\"#pageNo\");\n");
		sb.append("		while(obj && !obj.is(\"FORM\")){\n");
		sb.append("			obj = obj.parent();\n");
		sb.append("		}");
		sb.append("		return obj;\n");
		sb.append("	}\n");
		
		/**
		 * 改变每页显示数据条数
		 */
		sb.append("	function changePageSize(){\n");
		sb.append("     $(\"#pageNo\").val(\"1\");\n");
		sb.append("		var formObj = getThisElementParentForm();\n");
		sb.append("		formObj.submit();\n");
		sb.append("	}\n");

		
		sb.append("	function goTopages(page){\n");
		sb.append("     $(\"#pageNo\").val(page);\n");
		sb.append("     $(\"#pageSearchType\").val(page);\n");
		sb.append("		var formObj = getThisElementParentForm();\n");
		sb.append("		formObj.submit();\n");
		sb.append("	}\n");
		sb.append("</script>\n");
		
		
		this.pageTool = sb.toString();
	}
	
	
	
	public long getPageSize() {
		return pageSize;
	}


	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}


	public long getPage() {
		return page;
	}


	public void setPage(long page) {
		this.page = page;
	}


	public long getMaxPage() {
		return maxPage;
	}


	public void setMaxPage(long maxPage) {
		this.maxPage = maxPage;
	}


	public long getDataCount() {
		return dataCount;
	}


	public void setDataCount(long dataCount) {
		this.dataCount = dataCount;
	}


	public List<Map> getList() {
		return list;
	}


	public void setList(List<Map> list) {
		this.list = list;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getPageTool() {
		return pageTool;
	}

	public void setPageTool(String pageTool) {
		this.pageTool = pageTool;
	}
	
}
