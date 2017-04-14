<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>产业园区</title>
    
    <script type="text/javascript">
    	
    	function queryForm(){
    		$("#pageNo").val(1);
    		$("#gardenForm").submit();
    	}
    	
    	function edit(id){
    		var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val(); 
    		location="${rc.contextPath}/industrygarden/opt-edit/gardenintorduce.do?id="+id+"&pageSize="+pageSize+"&pageNo="+pageNo;
    	}
    	
    	//查看新闻
    	function gardenInfo(gardenId){
    		window.location.href="${rc.contextPath}/industrygarden/opt-view/gardeninfo.do?id="+gardenId;		
    	}
    	
    	function cleanAll(){
    		$("#id").val('');
    	}
    	
    	function initGarden(){
    		$.ajax({
			      type:"POST",//通常会用到两种：GET,POST。默认是：GET
			      url:"${rc.contextPath}/industrygarden/opt-query/initlist.do",//(默认: 当前页地址) 发送请求的地址
			      async:false,
			      success:function(data){
			    	  if(data==""){
			    		  custom_alert('没有需要初始化的数据',3,2000);
			    	  }else{
			      		location="${rc.contextPath}/industrygarden/opt-init/initgarden.do?ids="+data;
			    	  }
			      }
			   });
    		
    	}
    	
    </script>
</head>
<body>
	
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>产业园区</h2></div>
        <form class="form-inline search_list" role="form"  id="gardenForm" method="post" action="${rc.contextPath}/industrygarden/opt-query/gardenlist.do">
        <div id="searchDiv">
        	<div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">园区列表</label></span>
		                <select class="form-control" name="id" id="id" style="width:200px;">
		                    <option value="">请选择..</option>
		                    <#if mapList??>
		                    	<#list mapList as map>
				                    <#if garden?? && garden.id??  &&  garden.id==map["ID"] >
			                			<option value="${map['ID']}" selected>${(map["NAME"])!""}</option>
			                		<#else>
			                			<option value="${map['ID']}">${(map["NAME"])!""}</option>
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
		
		<#if per?? && per =="initper">
            <div class="btn-group">
	    		<a  class="btn green" onclick="initGarden();" target="content-box" ><i class="icon-plus"></i> 初始化</a>
	    	</div>
	    </#if>
        	
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable">
                <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="10%">园区名称</th>
                        <th width="35%">简介内容</th>
              <!--      <th width="8%">状态</th>     -->
                        <th width="10%">创建时间</th>
                        <th width="15%">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as garden>
							<tr>
								<td><div class="zjh">${garden_index+1 }</div></td>
				<!--			<td><div class="zjh"><a onclick="gardenInfo('${(garden.ID)!''}')" href="javaScript:void(0)">${(garden.NAME)!"" }</a></div></td>     -->
								<td><div class="zjh">${(garden.NAME)!""}</div></td> 
								<td><div class="zjh">${(garden.INTRODUCE)!""}</div></td> 
				<!--			<td><div class="zjh">${(garden.DNAME)!""}</div></td>     -->
								<td><div class="zjh">${(garden.CREATE_TIME?date("yyyy-MM-dd"))!""}</div></td>
								<td>
									<a class="btn blue" onclick="edit('${garden.ID}');"><i class="icon-edit"></i> 编辑</a>&nbsp;&nbsp;
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
