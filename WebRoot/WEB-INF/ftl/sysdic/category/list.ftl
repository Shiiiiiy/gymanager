<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>字典分类</title>
    
    <script type="text/javascript">
    	
    	function queryForm(){
    		$("#pageNo").val(1);
    		$("#dicCategoryForm").submit();
    	}
    	
    	<!-- 清空按钮 -->
    	function cleanAll(){
    		$("#name").val('');
    		$("#code").val('');
    	}
    	
    	<!-- 跳转至新增页面  -->
    	function addDicCategory(){
    		var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val(); 
    		location="${rc.contextPath}/dicCategory/opt-add/add.do?pageNo="+pageNo+"&pageSize="+pageSize;
    	}
    	
    	<!-- 跳转至编辑页面  -->
    	function edit(id){
    		if(id==1){
    			custom_alert("启用禁用状态是系统基础字典分类不允许编辑、禁用和删除！",3,3000);
    			return;
    		}
    		var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val();
    		location="${rc.contextPath}/dicCategory/opt-edit/edit.do?id="+id+"&pageNo="+pageNo+"&pageSize="+pageSize;
    	}
    	
    	<!-- 启用禁用操作  -->
    	function changeStatus(id,type){
    		
    		if(id==1){
    			custom_alert("启用禁用状态是系统基础字典分类不允许编辑、禁用和删除！",3,3000);
    			return;
    		}
    		
    		
    		var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val(); 
    		
    		location="${rc.contextPath}/dic/opt-update/changeStatus.do?id="+id+"&pageNo="+pageNo+"&pageSize="+pageSize+"&type="+type;
    	}
    
    	<!-- 删除操作 -->
    	function deleteDic(id){
    		
    		if(id==1){
    			custom_alert("启用禁用状态是系统基础字典分类不允许编辑、禁用和删除！",3,3000);
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
        <div class="page-header"><h2>字典分类列表</h2></div>
        <form class="form-inline search_list" role="form"  id="dicCategoryForm" method="post" action="${rc.contextPath}/dicCategory/opt-query/list.do">
        <div id="searchDiv">
        	<div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">分类名称</label></span>
		                <input type="text" class="form-control" id="name" name="name" value="${dic.name!''}" placeholder="请输入名称" style="width:200px;" />
		            </div>
		        </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">分类编码</span>
		                <input type="text" class="form-control" id="code" name="code"  value="${dic.code!''}" placeholder="请输入编码" style="width:200px;"/>
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
	    		<a  class="btn green" onclick="addDicCategory();" target="content-box" ><i class="icon-plus"></i>新增</a>
	    	</div>
        	
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable">
                <thead>
                    <tr>
                        <th width="10%">序号</th>
                        <th width="20%">分类名称</th>
                        <th width="20%">分类编码</th>
                        <th width="15%">状态</th>
                        <th width="35%">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as dic>
							<tr>
								<td><div class="zjh">${dic_index+1 }</div></td>
								<td><div class="zjh">${(dic.NAME)!""}</div></td>
								<td><div class="zjh">${(dic.CODE)!""}</div></td>
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
									<a onclick="edit('${dic.ID}');" class="btn blue mini"><i class="icon-edit"></i> 编辑</a>&nbsp;&nbsp;
									<a onclick="deleteDic('${dic.ID}');"  class="btn mini black"><i class="icon-trash"></i> 删除</a>&nbsp;&nbsp;
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
