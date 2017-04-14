<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>新闻管理</title>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>园区新闻<small class="font-en caps"></small></h2></div>
        <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/industrygarden/opt-query/newslist.do">
        <div id="searchDiv">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                   
                    <div class="input-group">
                        <span class="input-group-addon">&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;题 &nbsp;</span>
                        <input type="text" class="form-control" value="${(news.newsTitle)!''}" id="newsTitle" name="newsTitle" placeholder="请输入标题">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">&nbsp;&nbsp;来&nbsp;&nbsp;&nbsp;源&nbsp;&nbsp;</span>
                        <input type="text" class="form-control" value="${(news.newsSource)!''}" id="newsSource" name="newsSource" placeholder="请输入来源">
                    </div>
                </div>
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group news-time">
	                    <span class="input-group-addon">发布日期</span>
	                    <div class="controls input-append date form_datetime no-margin timebox" data-date="${.now}" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
	                        <input size="" type="text" value="${((news.newsTime)?string('yyyy-MM-dd'))!''}" id="newsTime" name="newsTime" placeholder="请选择发布日期" readonly class="form-control">
	                        <span class="add-on rem"><i class="icon-remove iconfont"></i></span>
	                        <span class="add-on"><i class="icon-th iconfont"></i></span>
	                    </div>
	                    <input type="hidden" id="dtp_input1" value="" /><br />
	                </div>
	            </div>
        	</div>
        	<div class="row">
	        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">园区列表</label></span>
		                <select class="form-control" name="parentCode" id="parentCode" style="width:180px;">
		                    <option value="">请选择..</option>
		                    <#if mapList??>
		                    	<#list mapList as map>
				                    <#if news?? && news.parentCode??  &&  news.parentCode ==map["ID"] >
			                			<option value="${map['ID']}" selected>${(map["NAME"])!""}</option>
			                		<#else>
			                			<option value="${map['ID']}">${(map["NAME"])!""}</option>
			                		</#if>
		                    	</#list>
		                    </#if>
		                </select>
		            </div>
		        </div>
		        
		        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon">新闻类型</span>
	                    <select class="form-control" id="moduleType" name="moduleType" style="width:180px;">
	                        <option value="">请选择..</option>
	                        <#if newsType??>
							<#list newsType as type>
								<#if news?? && news.moduleType?? && news.moduleType == type.code>
	                        	<option value="${(type.code)!'' }" selected="selected" >${(type.name)!''}</option>
	                        	<#else>
	                        	<option value="${(type.code)!'' }" >${(type.name)!''}</option>
	                        	</#if>
	                        </#list> 
							</#if>
	                    </select>
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
    		<a  class="btn green" onclick="addNews();" target="content-box" ><i class="icon-plus"></i> 新增</a>
    	</div>
    	
    	<div class="btn-group">
    		<a class="btn black" href="javaScript:void(0)" onclick="delNewses();"><i class="icon-trash"></i> 批量删除</a>
    	</div>
	    	
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable">
                <thead>
                    <tr>
                    	<th width="3%"><input type='checkbox' id="chooseall" ></input></th>
                        <th width="5%">序号</th>
                        <th width="9%">标题</th>
                        <th width="10%">所属园区</th>
                        <th width="8%">数据来源</th>
                        <th width="18%">简介</th>
                        <th width="9%">新闻类型</th>
                        <th width="9%">发布日期</th>
                        <th width="5%">状态</th>
                        <th width="24%">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as news>
							<tr>
								<td><div class="zjh"><input type="checkbox" name='isSelect' value="${(news.ID)!''}" ></input></div></td>
								<td><div class="zjh">${news_index+1 }</div></td>
								<td><div class="zjh"><a onclick="newsInfo('${(news.ID)!''}')" href="javaScript:void(0)">${(news.NEWS_TITLE)!"" }</a></div></td>
								<td><div class="zjh">${(news.GNAME)!"贵阳科技园" }</div></td>
								<td><div class="zjh">${(news.COMMENTS)!"" }</div></td>
								<td><div class="zjh">${(news.INTRODUCE)!"" }</div></td>
								<td><div class="zjh">${(news.DNAME)!"" }</div></td>
								<td><div class="zjh">${(news.NEWS_TIME?date("yyyy-MM-dd"))!"" }</div></td>
								<td>
									<#if news.STATUS ?? && news.STATUS=="">
										<#assign status = 0 />
									<#else>
										<#assign status = (news.STATUS!'0')?number/>
									</#if>
									<#if status==enable.id >
										<span class="label label-info">${enable.name}</span>
									</#if>
									<#if status==disable.id >
										<span class="label label-warning">${(disable.name)}</span>
									</#if>
								</td>
								<td>
									<a onclick="editNews('${(news.ID)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
									<a onclick="delNews('${(news.ID)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
									<#if status==0 || status==disable.id>
										<a class="btn little_blue" href="javaScript:void(0)" onclick="changeStatus('${news.ID}','${enable.id}')"><i class="icon-ok-sign"></i> ${enable.name}</a>
									<#else>
										<a class="btn yellow" href="javaScript:void(0)" onclick="changeStatus('${news.ID}','${disable.id}')"><i class="icon-ban-circle"></i> ${disable.name}</a>
									</#if>
								</td>
							</tr>
						</#list> 
					</#if>
										
										
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="10">
                           <#if page??> ${page.pageTool}</#if>
						</td>
                    </tr>
                </tfoot>
            </table>
        
       <div id="newsall" style="display:none;">确认删除？</div>
    </div>
    </form>
    </div>
</body>
<script type="text/javascript">
	$(function () {
		/*选择器*/
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
		var X = $('.news-time').offset().top;
		$('.datetimepicker').css("top", X);
	});
	$("#chooseall").click(function(){
   			var isChecked = $(this).prop("checked");
    		$("input[name='isSelect']").prop("checked", isChecked);
		});

	//启用禁用操作
	function changeStatus(newsId,value){
		
		var pageNo = $("#pageNo").val();
		var pageSize = $("*[name='pageSize']").val(); 
		
		window.location.href="${rc.contextPath}/industrygarden/opt-update/changestatus.do?id="+newsId+"&pageNo="+pageNo+"&pageSize="+pageSize+"&value="+value;
	}
	
	//编辑新闻
	function editNews(newsId){
		var pageNo = $("#pageNo").val();
		var pageSize = $("*[name='pageSize']").val(); 
		window.location.href="${rc.contextPath}/industrygarden/opt-edit/editnews.do?id="+newsId+"&pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	
	//新增新闻
	function addNews(){
		var pageSize = $("*[name='pageSize']").val(); 
		window.location.href="${rc.contextPath}/industrygarden/opt-add/addnews.do?pageSize="+pageSize;
	}
	
	//查看新闻
	function newsInfo(newsId){
		window.location.href="${rc.contextPath}/industrygarden/opt-view/newsinfo.do?id="+newsId;		
	}

	//删除新闻
	function delNews(str){
		showDialog("系统提示","newsall","sure('"+str+"')","cancel()","close()",100);
	}
	
	//批量删除新闻
	function delNewses(){
		var ids = "";
		$("[name = isSelect]:checkbox").each(function (i) {
			if(this.checked){
				if(i == 0){
					ids += $(this).val();
				}else{
					ids += "," + $(this).val()
				}
			}
			
		});
		if(ids != ""){
			showDialog("系统提示","newsall","sure('"+ids+"')","cancel()","close()",100);
		}else{
			custom_alert('请选择要删除的新闻',3,2000);
		}
	}
	
	
	function sure(str){
		var pageSize = $("*[name='pageSize']").val(); 
		window.location.href="${rc.contextPath}/industrygarden/opt-del/delnews.do?ids="+str+"&pageSize="+pageSize;
	}
	
	function cancel(){
		
	}
	function close(){
		
	}
	
	//点击查询
	function formSubmit(){
		$("#pageNo").val(1);
		$("#searchForm").submit();
	}
	
	//清空查询条件
	function cleanAll(){
	    $("#newsTitle").val("");
	    $("#newsSource").val(""); 
	    $("#newsTime").val(""); 
	    $("#parentCode").val("");
	    $("#moduleType").val("");
	}
	
</script>
</html>
