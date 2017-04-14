<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>新闻管理</title>
</head>
<body>
<div class="content-box">
    <div class="tabfix">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist" >
            <li role="presentation" id="li1"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" onclick="changeTab1()">线上培训</a></li>
            <li role="presentation" id="li2"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" onclick="changeTab2()" >线下培训</a></li>
        </ul>
        <div class="page-header" style="margin-top:20px;"><h5><small class="font-en caps"></small></h5></div>
        <div class="tab-content">
        	<div role="tabpanel" id="tab1" class="tab-pane">
		        <form class="form-inline search_list" role="form"  id="searchForm1" method="post" action="${rc.contextPath}/news/query/list">
		    		<input type="hidden" name="moduleCode" value="">
		          	<div id="searchDiv">
		            	<div class="row">
		                	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			                    <div class="input-group">
			                        <span class="input-group-addon">&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;题 &nbsp;</span>
			                        <input type="text" class="form-control" value="${(file.fileTitle)!''}" id="newsTitle" name="newsTitle" placeholder="请输入标题">
			                    </div>
		                	</div>
		                	
				            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				                <div class="input-group news-time">
				                    <span class="input-group-addon">发布时间</span>
				                    <div class="controls input-append date form_datetime no-margin timebox" data-date="" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
				                        <input size="" type="text" value="${((file.fileTime)?string('yyyy-MM-dd'))!''}" id="newsTime" name="newsTime" placeholder="请选择发布时间" readonly class="form-control">
				                        <span class="add-on rem"><i class="icon-remove iconfont"></i></span>
				                        <span class="add-on"><i class="icon-th iconfont"></i></span>
				                    </div>
				                    <input type="hidden" id="dtp_input1" value="" /><br />
				                    <input type="hidden" name="tab" value="1" />
				                </div>
				            </div>
		        		</div>
		        		
		        		<div class="tc">
				            <a class="btn black"  href="javaScript:void(0)" onclick="formSubmit(1)"><i class="icon-search" ></i> 查询</a>
				            <a class="btn black"  href="javaScript:void(0)" onclick="cleanAll(1);"><i class="icon-trash" ></i> 清空</a>
				        </div> 
				        
		            </div><!--searchDiv-->
		            
			        <a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>
			        
			        <div class="btn-group">
			    		<a  class="btn green" onclick="newsAdd(1);" target="content-box" ><i class="icon-plus"></i> 新增</a>
			    	</div>
			    	
			    	<div class="btn-group">
			    		<a class="btn black" href="javaScript:void(0)" onclick="delNewses(1);"><i class="icon-trash"></i> 批量删除</a>
			    	</div>
			    	
			        <div class="table-responsive">
			            <table class="table table-bordered table-striped table-hover mytable">
			                <thead>
			                    <tr>
			                    	<th width="5%"><input type='checkbox' id="chooseall" ></input></th>
			                        <th width="5%">序号</th>
			                        <th width="20%">标题</th>
			                        <th width="20%">链接</th>
			                        <th width="20%">发布时间</th>
			                        <th width="24%">操作</th>
			                    </tr>
			                </thead>
			                <tbody id="Searchresult"></tbody>
			                <tbody id="hiddentable">
			                    <#if filePage??>
									<#list filePage.list as file>
										<tr>
											<td><input type="checkbox" name='isSelect' value="${(file['ID'])!''}" ></input></td>
											<td>${file_index+1 }</td>
											<td><div class="zjh"><a onclick="newsInfo('${(file['ID'])!''}',1)" href="javaScript:void(0)">${(file['FILE_TITLE'])!""}</a></div></td>
											<td><div class="zjh">${(file['URL'])!"" }</div></td>
											<td><div class="zjh">${(file['FILE_TIME'])!"" }</div></td>
											<td>
												<a onclick="editNews('${(file['ID'])!''}','1');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
												<a onclick="delNews('${(file['ID'])!''}','1');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
												<#--if news.status==0 || news.status==disable.id>
													<a class="btn little_blue" href="javaScript:void(0)" onclick="changeStatus('${news.ID}','${enable.id}')"><i class="icon-ok-sign"></i> ${enable.name}</a>
												<#else>
													<a class="btn yellow" href="javaScript:void(0)" onclick="changeStatus('${news.ID}','${disable.id}')"><i class="icon-ban-circle"></i> ${disable.name}</a>
												</#if-->
											</td>
										</tr>
									</#list> 
								</#if>				
			                </tbody>
			                <tfoot>
		                    <tr>
		                        <td colspan="8">
		                           <#if filePage??> ${filePage.pageTool}</#if>
								</td>
		                    </tr>
			                </tfoot>
			            </table>
			    </div>
		    </form>
	    </div>
        	
	        <div role="tabpanel" id="tab2" class="tab-pane">
		        <form class="form-inline search_list" role="form"  id="searchForm2" method="post" action="${rc.contextPath}/news/query/list">
		    		<input type="hidden" name="moduleCode" value="">
		          	<div id="searchDiv">
		            	<div class="row">
		                	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			                    <div class="input-group">
			                        <span class="input-group-addon">&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;题 &nbsp;</span>
			                        <input type="text" class="form-control" value="${(news.newsTitle)!''}" id="newsTitle" name="newsTitle" placeholder="请输入标题">
			                    </div>
		                	</div>
		                	
				            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				                <div class="input-group news-time">
				                    <span class="input-group-addon">发布时间</span>
				                    <div class="controls input-append date form_datetime no-margin timebox" data-date="" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
				                        <input size="" type="text" value="${((news.newsTime)?string('yyyy-MM-dd'))!''}" id="newsTime" name="newsTime" placeholder="请选择发布时间" readonly class="form-control">
				                        <span class="add-on rem"><i class="icon-remove iconfont"></i></span>
				                        <span class="add-on"><i class="icon-th iconfont"></i></span>
				                    </div>
				                    <input type="hidden" id="dtp_input1" value="" /><br />
				                    <input type="hidden" name="tab" value="2" />
				                </div>
				            </div>
		        		</div>
		        		
		        		<div class="tc">
				            <a class="btn black"  href="javaScript:void(0)" onclick="formSubmit(2)"><i class="icon-search" ></i> 查询</a>
				            <a class="btn black"  href="javaScript:void(0)" onclick="cleanAll(1);"><i class="icon-trash" ></i> 清空</a>
				        </div> 
				        
		            </div><!--searchDiv-->
		            
			        <a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>
			        
			        <div class="btn-group">
			    		<a  class="btn green" onclick="newsAdd(2);" target="content-box" ><i class="icon-plus"></i> 新增</a>
			    	</div>
			    	
			    	<div class="btn-group">
			    		<a class="btn black" href="javaScript:void(0)" onclick="delNewses(2);"><i class="icon-trash"></i> 批量删除</a>
			    	</div>
			    	
			        <div class="table-responsive">
			            <table class="table table-bordered table-striped table-hover mytable">
			                <thead>
			                    <tr>
			                    	<th width="5%"><input type='checkbox' id="chooseall" ></input></th>
			                        <th width="5%">序号</th>
			                        <th width="20%">标题</th>
			                        <th width="20%">链接</th>
			                        <th width="20%">创建时间</th>
			                        <th width="24%">操作</th>
			                    </tr>
			                </thead>
			                <tbody id="Searchresult"></tbody>
			                <tbody id="hiddentable">
			                    <#if page??>
									<#list page.list as news>
										<tr>
											<td><input type="checkbox" name='isSelect' value="${(news.ID)!''}" ></input></td>
											<td>${news_index+1 }</td>
											<td><div class="zjh"><a onclick="newsInfo('${(news.ID)!''}',2)" href="javaScript:void(0)">${(news.NEWS_TITLE)!"" }</a></div></td>
											<td><div class="zjh">${(news.URL)!"" }</div></td>
											<td><div class="zjh">${(news.NEWS_TIME)!"" }</div></td>
											<td>
												<a onclick="editNews('${(news.ID)!''}','2');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
												<a onclick="delNews('${(news.ID)!''}','2');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
												<#--if news.status==0 || news.status==disable.id>
													<a class="btn little_blue" href="javaScript:void(0)" onclick="changeStatus('${news.ID}','${enable.id}')"><i class="icon-ok-sign"></i> ${enable.name}</a>
												<#else>
													<a class="btn yellow" href="javaScript:void(0)" onclick="changeStatus('${news.ID}','${disable.id}')"><i class="icon-ban-circle"></i> ${disable.name}</a>
												</#if-->
											</td>
										</tr>
									</#list> 
								</#if>				
			                </tbody>
			                <tfoot>
		                    <tr>
		                        <td colspan="8">
		                           <#if page??> ${page.pageTool}</#if>
								</td>
		                    </tr>
			                </tfoot>
			            </table>
			    </div>
		    </form>
	    </div>
    </div>
   </div>
</div>
<div id="newsall" style="display:none;">确认删除？</div>
<form style="display:none" id='initEditForm' method='post'></form>
<script type="text/javascript">
	$(function () {
		$('.form_datetime').datetimepicker({
		    language: 'zh-CN',
		    weekStart: 1,
		    todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
		});
		$('.datetimepicker').css("top", $('.news-time').offset().top);
		
		var tab = '${tab}';
		if(tab == 1){
			$("#li1").addClass("active");
			$("#tab1").addClass("active");
			$("#li2").removeClass("active");
			$("#tab2").removeClass("active");
		}else{
			$("#li2").addClass("active");
			$("#tab2").addClass("active");
			$("#li1").removeClass("active");
			$("#tab1").removeClass("active");
		}
		
		$("#chooseall").click(function(){
			var isChecked = $(this).prop("checked");
			$("input[name='isSelect']").prop("checked", isChecked);
		});
	});
	
	var newsAdd = function(i){
		window.location.href="${rc.contextPath}/news/add/init?tab="+i;
	};	

	
	//编辑新闻
	var editNews = function(newsId,i){
		var str = "<input name='id' value='" + newsId +"' />"
		str += "<input name='tab' value='"+i+"' />";
		$("#initEditForm").append(str);
		$("#initEditForm").attr("action","${rc.contextPath}/news/add/init");
		$("#initEditForm").submit();
	}
	
	//点击查询
	function formSubmit(i){
		if(i==1){
			$("#tab1 #pageNo").val(1);
			$("#searchForm1").submit();
		}else{
			$("#tab2 #pageNo").val(1);
			$("#searchForm2").submit();
		}
	}

	//清空查询条件
	function cleanAll(i){
		if(i == 1){
			$("#tab1 #newsTitle").val("");
	   		$("#tab1 #newsTime").val(""); 
		}else{
			$("#tab2 #newsTitle").val("");
	   		$("#tab2 #newsTime").val(""); 
		}
	    
	}

	function delNews(str,i){
		showDialog("系统提示","newsall","sure('"+str+"',"+i+")","cancel()","close()",100);
	}
	//批量删除新闻
	function delNewses(k){
		var ids = "";
		var selector = "#tab"+k+" input[name='isSelect']:checked";
		$(selector).each(function (i) {
			if(i == 0){
				ids += $(this).val();
			}else{
				ids += "," + $(this).val()
			}
		});
		if(ids != ""){
			showDialog("系统提示","newsall","sure('"+ids+"',"+k+")","cancel()","close()",100);
		}else{
			custom_alert('请选择要删除的条目!',3,2000);
		}
	}
	
	
	function sure(str,k){
		window.location.href="${rc.contextPath}/news/delete/delete?id="+str+"&tab="+k;
	}
	
	/*function psure(str){
		window.location.href="${rc.contextPath}/newsall/opt-del/pdeleteNewsAll.do?moduleCode=${(moduleCode)!''}&newsIds="+str;
	}*/
	function cancel(){}
	function close(){}
	
	var newsInfo = function(id,i){
		window.location.href="${rc.contextPath}/news/query/view?id="+id+"&tab="+i;
	}
</script>
</body>
</html>