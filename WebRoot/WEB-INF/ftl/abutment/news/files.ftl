<form class="form-inline search_list" role="form"  id="searchForm" method="post" action="">
		<input type="hidden" name="moduleCode" value="">
      	<div id="searchDiv">
        	<div class="row">
            	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;题 &nbsp;</span>
                        <input type="text" class="form-control" value="${(file.fileTitle)!''}" id="fileTitle" name="fileTitle" placeholder="请输入标题">
                    </div>
            	</div>
            	
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group news-time">
	                    <span class="input-group-addon">发布时间</span>
	                    <div class="controls input-append date form_datetime no-margin timebox" data-date="" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
	                        <input size="" type="text" value="${((file.fileTime)?string('yyyy-MM-dd'))!''}" id="fileTime" name="fileTime" placeholder="请选择发布时间" readonly class="form-control">
	                        <span class="add-on rem"><i class="icon-remove iconfont"></i></span>
	                        <span class="add-on"><i class="icon-th iconfont"></i></span>
	                    </div>
	                </div>
	            </div>
    		</div>
    		
    		<div class="tc">
	            <a class="btn black"  href="javaScript:void(0)" onclick="formSubmit()"><i class="icon-search" ></i> 查询</a>
	            <a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
	        </div> 
	        
        </div><!--searchDiv-->
        
        <a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>
        
        <div class="btn-group">
    		<a  class="btn green" onclick="addFile();" target="content-box" ><i class="icon-plus"></i> 新增</a>
    	</div>
    	
    	<div class="btn-group">
    		<a class="btn black" href="javaScript:void(0)" onclick="fileDelete();"><i class="icon-trash"></i> 批量删除</a>
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
                    <#if page??>
						<#list page.list as file>
							<tr>
								<td><input type="checkbox" name='isSelect' value="${(file['ID'])!''}" ></input></td>
								<td>${file_index+1 }</td>
								<td><div class="zjh"><a onclick="fileView('${(file['ID'])!''}')" href="javaScript:void(0)">${(file['FILE_TITLE'])!""}</a></div></td>
								<td><div class="zjh">${(file['URL'])!"" }</div></td>
								<td><div class="zjh">${(file['STIME'])!"" }</div></td>
								<td>
									<a onclick="editFile('${(file['ID'])!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
									<a onclick="single_del('${(file['ID'])!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
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
<form style="display:none" id='initEditForm' method='post'></form>
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
		var fileTitle = $("#fileTitle").val();
		var fileTime = $("#fileTime").val();
		var pageNo = $("#pageNo").val();
		var pageSize = $("select[name='pageSize']").val();
		changeTab("1",null,null,null,fileTitle,fileTime,pageNo,pageSize);
	});
	
	$("#chooseall").click(function(){
		var isChecked = $(this).prop("checked");
		$("input[name='isSelect']").prop("checked", isChecked);
	});
});

function editFile(id){
	var pageNo = $("#pageNo").val();
	var pageSize = $("*[name='pageSize']").val(); 
	window.location.href="${rc.contextPath}/abutmentfile/opt/edit?id="+id+"&pageNo="+pageNo+"&pageSize="+pageSize;
}

function addFile(){
	var pageNo = $("#pageNo").val();
	var pageSize = $("*[name='pageSize']").val(); 
	window.location.href="${rc.contextPath}/abutmentfile/opt/edit?pageSize="+pageSize+"&pageNo="+pageNo;
}

function fileView(id){
	window.location.href="${rc.contextPath}/abutmentfile/query/view?id="+id;
}

function fileDelete(){
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

function single_del(id){
	showDialog("系统提示","newsall","psure('"+id+"')","cancel()","close()",100);
}

function psure(str){
	window.location.href="${rc.contextPath}/abutmentfile/opt/delete?ids="+str;
}
function cancel(){}

function close(){}

//点击查询
function formSubmit(){
	$("#pageNo").val(1);
	$("#searchForm").submit();
}

//清空查询条件
function cleanAll(){
    $("#fileTitle").val("");
    $("#newsSource").val(""); 
    $("#fileTime").val(""); 
}

</script>