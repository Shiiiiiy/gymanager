<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>新闻管理</title>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>
        		创新创业示范企业
        <small class="font-en caps"></small></h2></div>
        <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/dynamicdata/opt-query/modelList.do?modelType=${modelType}">
        <input type="hidden" name="modelType" id="modelType" value="${modelType!'' }">
        <div class="btn-group">
    		<a  class="btn green" onclick="addTechInnovation();" target="content-box" ><i class="icon-plus"></i> 新增</a>
    	</div>
    	
    	<div class="btn-group">
    		<a class="btn black" href="javaScript:void(0)" onclick="delTechInnovations();"><i class="icon-trash"></i> 批量删除</a>
    	</div>
	    	
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable">
                <thead>
                    <tr>
                    	<th width="20px"><input type='checkbox' id="chooseall" ></input></th>
                       	<th width="46px">序号</th>
                        <th width="6%">名称</th>
                        <th width="6%">类别</th>
                        <th width="7%">联系人</th>
                        <th width="6%">电话</th>
                        <th width="6%">网址</th>
                        <th width="9%">业绩规模</th>
                        <th width="9%">创立时间</th>
                        <th width="48px">状态</th>
                        <th width="235px">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as techi>
							<tr>
								<td><input type="checkbox" name='isSelect' value="${(techi.ID)!''}" ></input></td>
								<td><div class="zjh">${techi_index+1}</div></td>
								<td><div class="zjh"><a onclick="info('${(techi.ID)!''}')" href="javaScript:void(0)">${(techi.NAME)!"" }</a></div></td>
								<td><div class="zjh">${(techi.BELONG)!"" }</div></td>
								<td><div class="zjh">${(techi.LINKMAN)!"" }</div></td>
								<td><div class="zjh">${(techi.PHONE)!"" }</div></td>
								<td><div class="zjh">${(techi.URL)!"" }</div></td>
								<td><div class="zjh">${(techi.SCALE)!"" }<#if techi.SCALE?? && techi.SCALE!=''>元</#if></div></td>
								<td><div class="zjh">${(techi.START_TIME)!"" }</div></td>
								<td>
									<#assign status = (techi.STATUS!'0')?number/>
									<#if status==enable.id >
										<span class="label label-info">${enable.name}</span>
									</#if>
									<#if status==disable.id >
										<span class="label label-warning">${(disable.name)}</span>
									</#if>
								</td>
								<td>
									<a onclick="editTechInnovation('${(techi.ID)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
									<a onclick="delTechInnovation('${(techi.ID)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
									<#if status==0 || status==disable.id>
										<a class="btn little_blue" href="javaScript:void(0)" onclick="changeStatus('${techi.ID}','${enable.id}')"><i class="icon-ok-sign"></i> ${enable.name}</a>
									<#else>
										<a class="btn yellow" href="javaScript:void(0)" onclick="changeStatus('${techi.ID}','${disable.id}')"><i class="icon-ban-circle"></i> ${disable.name}</a>
									</#if>
								</td>
							</tr>
						</#list> 
					</#if>
										
										
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="11">
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
	$("#chooseall").click(function(){
   			var isChecked = $(this).prop("checked");
    		$("input[name='isSelect']").prop("checked", isChecked);
		});

	//启用禁用操作
	function changeStatus(id,value){
		var pageNo = $("#pageNo").val();
		var pageSize = $("*[name='pageSize']").val();
		window.location.href="${rc.contextPath}/dynamicdata/opt-update/changeModelStatus.do?techid="+id+"&value="+value+"&tableName=${modelType}"+"&pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	//编辑新闻
	function editTechInnovation(id){
		var pageNo = $("#pageNo").val();
		var pageSize = $("*[name='pageSize']").val();
		window.location.href="${rc.contextPath}/dynamicdata/opt-addOrEdit/addOrEditInnovBusiness.do?id="+id+"&modelType=${modelType}"+"&pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	
	//新增新闻
	function addTechInnovation(){
		window.location.href="${rc.contextPath}/dynamicdata/opt-addOrEdit/addOrEditInnovBusiness.do?modelType=${modelType}";
	}
	
	//查看新闻
	function info(id){
		window.location.href="${rc.contextPath}/dynamicdata/opt-query/innovBusinessInfo.do?id="+id;		
	}


	//删除新闻
	function delTechInnovation(str){
		showDialog("系统提示","newsall","sure('"+str+"')","cancel()","close()",100);
	}
	
	//批量删除新闻
	function delTechInnovations(){
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
			custom_alert('请选择要删除的记录',3,2000);
		}
	}
	
	
	function sure(str){
		window.location.href="${rc.contextPath}/dynamicdata/opt-del/deleteModel.do?modelId="+str+"&modelType=${modelType}";
	}
	
	function psure(str){
		window.location.href="${rc.contextPath}/dynamicdata/opt-del/pdeleteModel.do?modelIds="+str+"&modelType=${modelType}";
	}
	function cancel(){
		
	}
	function close(){
		
	}
	
</script>
</html>
