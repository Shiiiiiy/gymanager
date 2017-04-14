<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "/common/common.ftl" >
	    <title>企业列表</title>
	    <link rel="stylesheet" href="${rc.contextPath}/plugin/ztree/css/zTreeStyle.css" type="text/css">
	    <script type="text/javascript" src="${rc.contextPath}/plugin/ztree/js/jquery.ztree.core.js"></script>
    	<script type="text/javascript" src="${rc.contextPath}/plugin/layer/layer.js"></script>
    	<script type="text/javascript" src="${rc.contextPath}/js/company.js"></script>
	</head>
	<body>
		<div class="content-box" id="companyListDiv">
       	    <!--form-->
        	<form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/companyManage/list.do?param=layer">
        		<div id="searchDiv">
        			<div class="form-inline search_list">
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		                    <div class="input-group">
		                        <span class="input-group-addon">企业名称</span>
		                        <input type="text" class="form-control" value="${(company.cp_name)!''}" id="companyName" name="cp_name" placeholder="请输入企业名称">
		                    </div>
		                </div>
					</div> 
					<div class="tc">
						<a class="btn black"  href="javaScript:void(0)" onclick="formSubmit()"><i class="icon-search" ></i> 查询</a>
						<a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
					</div>  
        		</div>
				
				
        		
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-hover mytable" width="100%" style="table-layout:fixed">
						<thead>
							<tr>
								<th width="3%"><input type="checkbox" id="checkedAll" /></th>
								<th width="8%">序号</th>
								<th width="10%">企业名称</th>
								<th width="25%">经营产品描述</th>
								<th width=25%">所在地区描述</th>
								<th width="10%">官网地址</th>
								<th width="10%">操作</th>
								
							</tr>
						</thead>
						<tbody id="hiddentable"></tbody>
							<#if page??>
								<#list page.list as company>
									<tr>	
										<td><input type="checkbox" name="checkList" value="${company.ID}" /></td>
										<td><div class="zjh">${company_index+1 }</div></td>
										<td><div class="zjh">${(company.CP_NAME)!'' }</div></td>
										<td><div class="zjh">${(company.CP_PRODUCT)!'' }</div></td>
										<td><div class="zjh">${(company.CP_LOCATION)!'' }</div></td>
										<td><div class="zjh">${(company.CP_ADDRESS)!'' }</div></td>
										<td>
											<a onclick="companyInfoIframe('${company.ID}');" class="btn blue mini"><i class="icon-edit"></i>&nbsp;查看</a>&nbsp;&nbsp;
										</td> 
									</tr>
								</#list>
							
							</#if>
						<tfoot>
							<tr>
								<td colspan="7">
									${page.pageTool}
								</td>
							</tr>
						</tfoot>
		            </table>
				</div>
			    	
        		
        	</form>
        </div>
        <div id="companyInfoDiv" class="content-box" style="display:none;">
        	<iframe style="width:100%;" id="companyInfoIframe" frameborder="0" src="" name="yelu"></iframe>
        </div>
	</body>
	<script>
	
		$("#checkedAll").click(function(){
			var flag = this.checked;
			$('input[name="checkList"]').each(function(){
				this.checked = flag;
			});
		});
		
		
		function companyInfoIframe(companyId){
			$("#companyListDiv").hide();
			$("#companyInfoDiv").show();
			$("#companyInfoIframe").attr("src","${rc.contextPath}/companyManage/addCompany.do?companyId="+companyId+"&flag=see");
		}
		
		function changeDiv(){
			if($("#companyInfoDiv").is(":hidden")){
				$("#companyListDiv").hide();
				$("#companyInfoDiv").show();
			}else{
				$("#companyListDiv").show();
				$("#companyInfoDiv").hide();
			}
		}
		
		function isInfoShow(){
			return $("#companyInfoDiv").is(":hidden");
		}
		
		//清空查询条件
		function cleanAll(){
		    $("#companyName").val("");
		    $("#productName").val(""); 
			$("#productType").val("");
			$("#productType01").val("");
		}
		//查询
		function formSubmit(){
    		$("#searchForm").submit();
    	}
    	
		function getChecked(){
			var len = $('input[name="checkList"]:checked').length;
			
			var companyId = "";
			$('input[name="checkList"]:checked').each(function(i){
				if(i==len-1){
					companyId += $(this).val();
				}else{
					companyId += $(this).val() +",";
				}
			});
			
			return companyId;
		}
    	
		function show(){
			custom_alert('请选择企业',3,2000);
		}
		
	</script>
</html>