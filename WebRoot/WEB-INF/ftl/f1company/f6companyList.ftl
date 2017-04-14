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
		<div class="content-box">
			<div class="page-header"><h2>示范企业列表<small class="font-en caps"></small></h2></div>
       	    <!--form-->
        	<form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/f6company/opt-query/f6companyList.do">
        		<div id="searchDiv">
        			<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		                    <div class="input-group">
		                        <span class="input-group-addon">企业名称</span>
		                        <input type="text" class="form-control" value="${(company.cp_name)!''}" id="companyName" name="cp_name" placeholder="请输入企业名称">
		                    </div>
		                </div>
						
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		                    <div class="input-group">
		                        <span class="input-group-addon">经营产品</span>
		                        <input type="text" class="form-control" value="${(product.product_name)!''}" id="productName" name="product_name" placeholder="请输入经营产品">
		                    </div>
		                </div>
						
					</div> 
					<div class="tc">
						<a class="btn black"  href="javaScript:void(0)" onclick="formSubmit()"><i class="icon-search" ></i> 查询</a>
						<a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
					</div>  
        		</div>
				
        		<a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>	
        		<div class="btn-group">
					<a  class="btn green" id="addCompany" target="content-box" ><i class="icon-plus"></i> 选择</a>
				</div>	
		        <div class="btn-group">
					<a class="btn black" href="javaScript:void(0)" onclick="deleteAll()"><i class="icon-trash"></i> 批量取消</a>
				</div>
				
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-hover mytable" style="table-layout:fixed">
						<thead>
							<tr>
								<th width="4%"><input type="checkbox" id="checkedAll" /></th>
								<th width="5%">序号</th>
								<th width="13%">企业名称</th>
								<th width="16%">经营产品描述</th>
								<th width="16%">所在地区描述</th>
								<th width="13%">官网地址</th>
								<th width="11%">法定代表人</th>
								<th width="11%">联系方式</th>
								<th width="11%">操作</th>
							</tr>
						</thead>
						<tbody id="hiddentable"></tbody>
							<#if page??>
								<#list page.list as company>
									<tr>	
										<td><input type="checkbox" name="checkList" value="${(company.CID)!''}" /></td>
										<td><div class="zjh">${company_index+1 }</div></td>
										<td><div class="zjh"><a href="javascript:" onclick="seeCompany('${(company.ID)!''}')">${(company.CP_NAME)!'' }</a></div></td>
										<td><div class="zjh">${(company.CP_PRODUCT)!'' }</div></td>
										<td><div class="zjh">${(company.CP_LOCATION)!'' }</div></td>
										<td><div class="zjh">${(company.CP_ADDRESS)!'' }</div></td>
										<td><div class="zjh">${(company.CP_MAN)!'' }</div></td>
										<td><div class="zjh">${(company.CP_PHONE)!'' }</div></td>
										<td>
											<a onclick="deleteCompany('${(company.CID)!''}');"  class="btn mini black"><i class="icon-trash"></i> 取消</a>&nbsp;&nbsp;
										</td>
									</tr>
								</#list>
							
							</#if>
						<tfoot>
							<tr>
								<td colspan="10">
									${page.pageTool}
								</td>
							</tr>
						</tfoot>
		            </table>
				</div>
			    	
        		
        	</form>
        </div>
        <div id="tishi" style="display:none;">确认移除选中的企业？</div>
        
        <div id="tishi1" style="display:none;">确认移除该企业？</div>
        
	</body>
	<script>
	
		$("#checkedAll").click(function(){
			var flag = this.checked;
			$('input[name="checkList"]').each(function(){
				this.checked = flag;
			});
		});
		
		//批量删除
		function deleteAll(){
			var len = $('input[name="checkList"]:checked').length;
			if(len<=0){
				custom_alert('请选择要移除的企业',3,2000);
				return;
			}
			var companyId = "";
			$('input[name="checkList"]:checked').each(function(i){
				if(i==len-1){
					companyId += $(this).val();
				}else{
					companyId += $(this).val() +",";
				}
			});
			showDialog("系统提示","tishi","sure('"+companyId+"')","cancel()","close()",100);
		}
		
		function deleteCompany(propid){
			showDialog("系统提示","tishi1","sure('"+propid+"')","cancel()","close()",100);
		}
		
		function sure(str){
			window.location.href="${rc.contextPath}/f6company/cancelCompany.do?propIds="+str;
		}
		
		function cancel(){
		
		}
		//清空查询条件
		function cleanAll(){
		    $("#companyName").val("");
		    $("#productName").val(""); 
		}
		//查询
		function formSubmit(){
			$("#pageNo").val(1);
    		$("#searchForm").submit();
    	}
		
		function callback(companyIds){
			window.location.href="${rc.contextPath}/f6company/saveCompany.do?companyIds="+companyIds;	
		}
		
		function seeCompany(companyId){
			var index=layer.open({
				  title: '查看详细',
				  btn:['确定','取消'],
				  area:['900px', '100%'],
				  cancel: function(){ 
				  },
				  btn1:function(){
				      layer.close(index);
				  },
				  btn2:function(){
				  },
				  content: '<div style="width:100%;height:100%;"><iframe id="myIframe" style="width:100%;height:100%;" frameborder="0" src="${rc.contextPath}/companyManage/addCompany.do?companyId='+companyId+'&flag=see"></iframe></div>'
			});
		}
		
		$(function () {
			$("#addCompany").company();
		});
	    
	</script>
</html>