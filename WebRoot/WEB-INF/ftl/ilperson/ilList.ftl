<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>新闻管理</title>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header">
        <h2>
        	<#if type?? && type=="INNOVATE">
        		创新人才列表
        	<#else>
        		领军人物列表
        	</#if>
        <small class="font-en caps"></small></h2></div>
        <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/ilperson/opt-query/ilList.do">
        	<input type="hidden" name="type" value="${type }">
          <div id="searchDiv">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                   
                    <div class="input-group">
                        <span class="input-group-addon">&nbsp;&nbsp;姓&nbsp;&nbsp;&nbsp;名 &nbsp;</span>
                        <input type="text" class="form-control" value="${(ilperson.psName)!''}" id="psName" name="psName" placeholder="请输入姓名">
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
    		<a  class="btn green" onclick="addPerson();" target="content-box" ><i class="icon-plus"></i> 新增</a>
    	</div>
    	
    	<div class="btn-group">
    		<a class="btn black" href="javaScript:void(0)" onclick="delPersons();"><i class="icon-trash"></i> 批量删除</a>
    	</div>
	    	
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable">
                <thead>
                    <tr>
                    	<th width="5%"><input type='checkbox' id="chooseall" ></input></th>
                        <th width="5%">序号</th>
                        <th width="10%">姓名</th>
                        <th width="13%">职务</th>
                        <th width="20%">所属单位</th>
                        <th width="13%">联系方式</th>
                        <th width="10%">状态</th>
                        <th width="24%">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as person>
							<tr>
								<td><input type="checkbox" name='isSelect' value="${(person.ID)!''}" ></input></td>
								<td>${person_index+1 }</td>
								<td><div class="zjh"><a onclick="personInfo('${(person.ID)!''}')" href="javaScript:void(0)">${(person.PS_NAME)!"" }</a></div></td>
								<td><div class="zjh">${(person.PS_JOB)!"" }</div></td>
								<td><div class="zjh">${(person.PS_BELONG)!"" }</div></td>
								<td><div class="zjh">${(person.PS_TEL)!"" }</div></td>
								<td>
									<#assign status = (person.STATUS!'0')?number/>
									<#if status==enable.id >
										<span class="label label-info">${enable.name}</span>
									</#if>
									<#if status==disable.id >
										<span class="label label-warning">${(disable.name)}</span>
									</#if>
								</td>
								<td>
									<a onclick="editPerson('${(person.ID)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
									<a onclick="delPerson('${(person.ID)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
									<#if status==0 || status==disable.id>
										<a class="btn little_blue" href="javaScript:void(0)" onclick="changeStatus('${person.ID}','${enable.id}')"><i class="icon-ok-sign"></i> ${enable.name}</a>
									<#else>
										<a class="btn yellow" href="javaScript:void(0)" onclick="changeStatus('${person.ID}','${disable.id}')"><i class="icon-ban-circle"></i> ${disable.name}</a>
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
        
       <div id="ilperson" style="display:none;">确认删除？</div>
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
	function changeStatus(personId,value){
		
		var pageNo = $("#pageNo").val();
		var pageSize = $("*[name='pageSize']").val(); 
		
		window.location.href="${rc.contextPath}/ilperson/opt-update/changeStatus.do?personId="+personId+"&value="+value+"&type=${(type)!''}"+"&pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	//编辑新闻
	function editPerson(personId){
		var pageNo = $("#pageNo").val();
		var pageSize = $("*[name='pageSize']").val(); 
		window.location.href="${rc.contextPath}/ilperson/opt-addOrEdit/addOrEditIlperson.do?personId="+personId+"&type=${(type)!''}"+"&pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	
	//新增新闻
	function addPerson(){
		window.location.href="${rc.contextPath}/ilperson/opt-addOrEdit/addOrEditIlperson.do?type=${(type)!''}";
	}
	
	//查看新闻
	function personInfo(personId){
		window.location.href="${rc.contextPath}/ilperson/opt-query/ilPersonInfo.do?type=${(type)!''}&personId="+personId;		
	}

	//删除新闻
	function delPerson(str){
		showDialog("系统提示","ilperson","sure('"+str+"')","cancel()","close()",100);
	}
	
	//批量删除新闻
	function delPersons(){
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
			showDialog("系统提示","ilperson","psure('"+ids+"')","cancel()","close()",100);
		}else{
			custom_alert('请选择要删除的记录',3,2000);
		}
	}
	
	
	function sure(str){
		window.location.href="${rc.contextPath}/ilperson/opt-del/deleteIlPerson.do?type=${(type)!''}&personId="+str;
	}
	
	function psure(str){
		window.location.href="${rc.contextPath}/ilperson/opt-del/pdeleteIlPerson.do?type=${(type)!''}&personIds="+str;
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
	    $("#psName").val("");
	}
	
</script>
</html>
