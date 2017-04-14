<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>字典项</title>
    
    <script type="text/javascript">
    	
    	<!-- 选择下拉框 -->
		$(function(){
			$('#parentId').searchableSelect();
			$("div[name='searchEle']:eq(0)").width(200);
		});
    
    	function queryForm(){
    		$("#pageNo").val(1);
    		$("#dicItemForm").submit();
    	}
    
    	<!-- 清空按钮 -->
    	function cleanAll(){
    		$("#parentId").val('');
    		$("#status").val('');
    		$("div[name='searchEle']:eq(0)").find("div[class=searchable-select-holder]").html("请选择..");
    	}
    	
    	<!-- 跳转至新增页面  -->
    	function addDicItem(){
    		var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val(); 
    		location="${rc.contextPath}/dicItem/opt-add/add.do?pageNo="+pageNo+"&pageSize="+pageSize;
    	}
    	
    	<!-- 跳转至编辑页面  -->
    	function edit(id){
    		if(id==2 || id==3){
    			custom_alert("启用、禁用是系统基础字典不允许编辑、禁用和删除！",3,3000);
    			return;
    		}
    		
    		var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val();
    		location="${rc.contextPath}/dicItem/opt-edit/edit.do?id="+id+"&pageNo="+pageNo+"&pageSize="+pageSize;
    	}
    	
    	
    	<!-- 启用禁用操作  -->
    	function changeStatus(id,type){
    		
    		if(id==2 || id==3){
    			custom_alert("启用、禁用是系统基础字典不允许编辑、禁用和删除！",3,3000);
    			return;
    		}
    		
    		var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val(); 
    		
    		location="${rc.contextPath}/dic/opt-update/changeStatus.do?id="+id+"&pageNo="+pageNo+"&pageSize="+pageSize+"&type="+type;
    	}
    	
    	
    	<!-- 删除操作 -->
    	function deleteDic(id){
    		
    		if(id==2 || id==3){
    			custom_alert("启用、禁用是系统基础字典不允许编辑、禁用和删除！",3,3000);
    			return;
    		}
    		
    		showDialog("系统提示","ddd","sure("+id+")","cancle()","close()",100);
    	}
    	function sure(id){
    		//alert("确定");
    		var pageSize = $("*[name='pageSize']").val(); 
    		location="${rc.contextPath}/dic/opt-delete/delete.do?id="+id+"&pageSize="+pageSize;
    	}
    	function cancle(){
    		//alert("取消");
    	}
    	function close(){
    		//alert("关闭");
    	}
    
    </script>
</head>
<body>
	<div id="ddd" style="display:none;">
		确认删除？	
	</div>
	
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>字典项列表</h2></div>
        <form class="form-inline search_list" role="form"  id="dicItemForm" method="post" action="${rc.contextPath}/dicItem/opt-query/list.do">
        <div id="searchDiv">
        	<div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">字典分类 </label></span>
		                <select class="form-control" name="parentId" id="parentId" style="width:200px;">
	                    <option  value="">请选择..</option>
	                    <#if dicList??>
	                    	<#list dicList as d>
	                    		<#if dic?? && dic.pid ?? && dic.pid ==d.id >
	                    			<option value="${d.id}" selected>${(d.name)!""}</option>
	                    		<#else>
	                    			<option value="${d.id}">${(d.name)!""}</option>
	                    		</#if>
	                    	</#list>
	                    </#if>
	                    </select>
		            </div>
		         </div>
		        
		        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon"> &nbsp;&nbsp;状&nbsp;态 &nbsp;</label></span>
			            <select class="form-control" name="status" id="status" style="width:200px;">
		                    <option value="">请选择..</option>
		                    <#if statusList??>
		                    	<#list statusList as d>
				                    <#if dic?? && dic.status?? && dic.status==d.id >
			                			<option value="${d.id}" selected>${(d.name)!""}</option>
			                		<#else>
			                			<option value="${d.id}">${(d.name)!""}</option>
			                		</#if>
		                    	</#list>
	                        </#if>
	                    </select>
		            </div>
		        </div>
	            
		        
	            
            </div>
            
            <div class="tc">
	            <a class="btn black"  href="javaScript:void(0)" onclick="queryForm()"><i class="icon-search" ></i> 查询</a>
	            <a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
            </div>
           </div> 
            <a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>
        	<div class="btn-group">
        		<a  class="btn green" onclick="addDicItem();" target="content-box" ><i class="icon-plus"></i>新增</a>
        	</div>
        	
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable">
                <thead>
                    <tr>
                        <th width="8%">序号</th>
                        <th width="12%">字典名称</th>
                        <th width="12%">字典编码</th>
                        <th width="12%">字典分类</th>
                        <th width="12%">字典值</th>
                        <th width="10%">状态</th>
                        <th width="34%">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as dic>
							<tr>
								<td><div class="zjh">${dic_index+1 }</div></td>
								<td><div class="zjh">${(dic.NAME)!"" }</div></td>
								<td><div class="zjh">${(dic.CODE)!"" }</div></td>
								<td><div class="zjh">${(dic.PNAME)!"" }</div></td>
								<td><div class="zjh">${(dic.VAL)!""}</div></td>
								<td>
									<#assign status = (dic.STATUS!'0')?number/>
									<#if status==enable.id >
										<span class="label label-info">${enable.name}</span>
									</#if>
									<#if status==disable.id >
										<span class="label label-warning">${(disable.name)}</span>
									</#if>
								</td>
								<td>
									<a class="btn blue" onclick="edit('${dic.ID}');"><i class="icon-edit"></i> 编辑</a>&nbsp;&nbsp;
									<a class="btn black" onclick="deleteDic('${dic.ID}');" ><i class="icon-trash"></i> 删除</a>&nbsp;&nbsp;
									<#if status==enable.id >
									<a class="btn yellow" href="javaScript:void(0)" onclick="changeStatus('${dic.ID}',0)"><i class="icon-ban-circle"></i> ${disable.name}</a>
									<#else>
									<a class="btn little_blue" href="javaScript:void(0)" onclick="changeStatus('${dic.ID}',1)"><i class="icon-ok-sign"></i> ${enable.name}</a>
									</#if>
								</td>
							</tr>
						</#list> 
					</#if>
										
										
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="8">
                            ${page.pageTool}
						</td>
                    </tr>
                </tfoot>
            </table>
        </div>
        </form>
    </div>
</body>
</html>
