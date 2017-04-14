<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "/common/common.ftl" >
		<script type="text/javascript" src="${rc.contextPath}/plugin/layer/layer.js"></script>
		<title>广告列表</title>
	</head>
	<body>
		<div class="content-box">
			<div class="page-header"><h2>广告列表<small class="font-en caps"></small></h2></div>
			<form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/advertisement/list.do">
				<div id="searchDiv">
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		                    <div class="input-group">
		                        <span class="input-group-addon">广告位置</span>
		                        <select class="form-control"  name="code" id="code" onchange="getCityOrArea('A',this)">
	                                <option value=""> 请选择..</option>
	                                <#if listGarden??>
	                                       <#list listGarden as mt>
	                                       		<#if "${(codeId)!''}" == "${mt.id}" >
		                                            <option value="${(mt.id)!''}" name="" selected = "selected">${(mt.name)!""}</option>
	                                       		<#else>
		                                            <option value="${(mt.id)!''}" name="" >${(mt.name)!""}</option>
	                                       		</#if>
	                                       </#list>
	                                </#if>
	                            </select>
		                    </div>
		                </div>
		                
		                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		                    <div class="input-group">
		                        <span class="input-group-addon">标题</span>
		                        <input type="text" class="form-control" value="${(title)!''}" id="title" name="title" placeholder="请输入广告标题">
		                    </div>
		                </div>
		                
		                <div class="tc">
							<a class="btn black"  href="javaScript:void(0)" onclick="formSubmit()"><i class="icon-search" ></i> 查询</a>
							<a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
						</div>  
				</div>
				<a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>	
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-hover mytable" style="table-layout:fixed">
						<thead>
							<tr>
								<th width="4%">序号</th>
								<th width="8%">广告位置</th>
								<th width="10%">标题</th>
								<th width="10%">链接</th>
								<th width="25%">操作</th>
							</tr>
						</thead>
						<tbody id="hiddentable">
							<#if page??>
								<#list page.list as ad>
									<tr>	
										<td><div class="zjh">${ad_index+1 }</div></td>
										<td><div class="zjh">${(ad.adLoacName)!'' }</div></td>
										<td><div class="zjh">${(ad.FILE_TITLE)!'' }</div></td>
										<td><div class="zjh">${(ad.URL)!'' }</div></td>
										<td>
											<a onclick="edit('${(ad.FILE_TYPE)!''}','${(ad.MODULE_CODE)!''}','${(ad.PARENT_CODE)!''}','${(ad.adLoacName)!''}');" class="btn blue mini"><i class="icon-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;
											<a onclick="see('${(ad.FILE_TYPE)!''}','${(ad.MODULE_CODE)!''}','${(ad.PARENT_CODE)!''}','${(ad.adLoacName)!''}');" class="btn blue mini"><i class="icon-edit"></i>&nbsp;查看</a>&nbsp;&nbsp;
										</td>
									</tr>
								</#list>
							
							</#if>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5">
									${page.pageTool}
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</form>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			$('#code').searchableSelect();
			$("div[name='searchEle']:eq(0)").width(200);
		})
		function cleanAll(){
		    //$("#code").val("");
		    $("div[name='searchEle']:eq(0)").find("div[class=searchable-select-holder]").html(" 请选择..");
		    $("#code").empty();
		    $("#code").append("<option value=''> 请选择..</option>");
		    $("#title").val(""); 
		}
		
		function formSubmit(){
			$("#searchForm").submit();
		}
		function edit(file_type,module_code,parent_code,adLoacName){
			window.location.href="${rc.contextPath}/advertisement/initEditAd.do?file_type="+file_type+"&module_code="+module_code+"&parent_code="+parent_code+"&adLoacName="+encodeURI(encodeURI(adLoacName));
		}
		
		function see(file_type,module_code,parent_code,adLoacName){
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
				  content: '<div style="width:100%;height:100%;"><iframe id="myIframe" style="width:100%;height:100%;" frameborder="0" src="${rc.contextPath}/advertisement/initEditAd.do?file_type='+file_type+'&module_code='+module_code+'&parent_code='+parent_code+'&see=see"></iframe></div>'
			}); 
		}
		
	</script>
</html>