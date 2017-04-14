<form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/policynews/query/single">
	<input type="hidden" name="moduleCode" id="moduleCode" value="${moduleCode }">
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
		            <span class="input-group-addon">&nbsp;&nbsp;来&nbsp;&nbsp;&nbsp;源&nbsp;</span>
		            <input type="text" class="form-control" value="${(news.newsSource)!''}" id="newsSource" name="newsSource" placeholder="请输入来源">
		        </div>
		    </div>
		    
		    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		        <div class="input-group news-time">
		            <span class="input-group-addon">发布日期</span>
		            <div class="controls input-append date form_datetime no-margin timebox" data-date="${.now}" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
		                <input size="" type="text" value="${((news.newsTime)?string('yyyy-MM-dd'))!''}" id="newsTime" name="newsTime" placeholder="请选择发布时间" readonly class="form-control">
		                <span class="add-on rem"><i class="icon-remove iconfont"></i></span>
		                <span class="add-on"><i class="icon-th iconfont"></i></span>
		            </div>
		            <input type="hidden" id="dtp_input1" value="" /><br />
		        </div>
		    </div>
		</div>
	</div>
        
    <div class="tc">
        <a class="btn black"  href="javaScript:void(0)" onclick="formSubmit()"><i class="icon-search" ></i> 查询</a>
        <a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
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
                	<th width="5%"><input type='checkbox' id="chooseall" ></input></th>
                    <th width="5%">序号</th>
                    <th width="20%">标题</th>
                    <th width="10%">数据来源</th>
                    <th width="20%">简介</th>
                    <th width="9%">发布时间</th>
                    <th width="7%">状态</th>
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
							<td><div class="zjh"><a onclick="newsInfo('${(news.ID)!''}')" href="javaScript:void(0)">${(news.NEWS_TITLE)!"" }</a></div></td>
							<td><div class="zjh">${(news.COMMENTS)!"" }</div></td>
							<td><div class="zjh">${(news.INTRODUCE)!"" }</div></td>
							<td><div class="zjh">${(news.STIME)!''}</div></td>
							<td>
								<#assign status = (news.STATUS!'0')?number/>
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
                    <td colspan="8">
                       <#if page??> ${page.pageTool}</#if>
					</td>
                </tr>
            </tfoot>
        </table>
	   <div id="newsall" style="display:none;">确认删除？</div>
	</div>
</form>
<script>
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

$(function(){

	$("#searchForm").submit(function(event){
		event.preventDefault();
		var code = $("#moduleCode").val();
		var newsTitle = $("#newsTitle").val();
		var newsSource = $("#newsSource").val();
		var newsTime = $("#newsTime").val();
		var pageNo = $("#pageNo").val();
		var pageSize = $("select[name='pageSize']").val();
		changeTab(code,newsTitle,newsSource,newsTime,pageNo,pageSize);
	});
	
	$("#chooseall").click(function(){
		var isChecked = $(this).prop("checked");
		$("input[name='isSelect']").prop("checked", isChecked);
	});
});

//启用禁用操作
function changeStatus(id,value){
	var pageNo = $("#pageNo").val();
	var pageSize = $("*[name='pageSize']").val(); 
	window.location.href="${rc.contextPath}/policynews/opt/changestatus?id="+id+"&pageNo="
	+pageNo+"&pageSize="+pageSize+"&value="+value+"&moduleCode=${(moduleCode)!''}"
}

//编辑新闻
function editNews(newsId){
	var pageNo = $("#pageNo").val();
	var pageSize = $("*[name='pageSize']").val(); 
	var moduleCode = $("#moduleCode").val();
	window.location.href="${rc.contextPath}/policynews/opt/edit.do?id="+newsId+"&pageNo="+pageNo+"&pageSize="+pageSize+"&moduleCode="+moduleCode;
}


//新增新闻
function addNews(){
	window.location.href="${rc.contextPath}/policynews/opt/edit.do?moduleCode=${(moduleCode)!''}";
}

//查看新闻
function newsInfo(newsId){
	window.location.href="${rc.contextPath}/policynews/query/view?moduleCode=${(moduleCode)!''}&id="+newsId;		
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
		showDialog("系统提示","newsall","psure('"+ids+"')","cancel()","close()",100);
	}else{
		custom_alert('请选择要删除的新闻',3,2000);
	}
}


function sure(str){
	window.location.href="${rc.contextPath}/policynews/opt/delete?moduleCode=${(moduleCode)!''}&ids="+str;
}

function psure(str){
	window.location.href="${rc.contextPath}/policynews/opt/delete?moduleCode=${(moduleCode)!''}&ids="+str;
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
}
</script>