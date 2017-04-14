<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>园区项目</title>
    
    <script type="text/javascript">
    	
    	function queryForm(){
    		$("#pageNo").val(1);
    		$("#projectForm").submit();
    	}
    	<!-- 新增 -->
    	function addProject(){
    		var pageSize = $("*[name='pageSize']").val(); 
    		location="${rc.contextPath}/industrygarden/opt-add/addproject.do?pageSize="+pageSize;
    	}
    	<!-- 编辑 -->
    	function editPro(id){
    		var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val(); 
    		location="${rc.contextPath}/industrygarden/opt-edit/editproject?id="+id+"&pageSize="+pageSize+"&pageNo="+pageNo;
    	}
    	
    	<!-- 查看 -->
    	function projectInfo(gardenId){
    		window.location.href="${rc.contextPath}/industrygarden/opt-view/projectinfo.do?id="+gardenId;		
    	}
    	
    	function cleanAll(){
    		$("#ofGarden").val('');
    		$("#projectType").val('');
    		$("#projectStatus").val('');
    		$("#projectName").val('');
    	}
    	
    	//删除
    	function deletePro(id){
    		showDialog("系统提示","deleteDiv","sure('"+id+"')","cancel()","close()",100);
    	}
    	
    	//批量删除新闻
    	function deleteAll(){
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
    			showDialog("系统提示","deleteDiv","sure('"+ids+"')","cancel()","close()",100);
    		}else{
    			custom_alert('请选择要删除的园区项目',3,2000);
    		}
    	}
    	
    	
    	function sure(ids){
    		var pageSize = $("*[name='pageSize']").val(); 
    		window.location.href="${rc.contextPath}/industrygarden/opt-del/delproject.do?ids="+ids+"&pageSize="+pageSize;
    	}
    	function cancel(){
    	}
    	function close(){
    	}
    	
    	
    	$(function(){
    	  	$("#chooseall").click(function(){
    	  		var isChecked = $(this).prop("checked");
    	  		$("input[name='isSelect']").prop("checked", isChecked);
    	  	});
    	});	
  
    		
        	
    	
    
    </script>
</head>
<body>
	<div id="deleteDiv" style="display:none;">确认删除？</div>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>园区项目</h2></div>
        <form class="form-inline search_list" role="form"  id="projectForm" method="post" action="${rc.contextPath}/industrygarden/opt-query/projectlist.do">
        <div id="searchDiv">
        	<div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">园区列表</label></span>
		                <select class="form-control" name="ofGarden" id="ofGarden" style="width:200px;">
		                    <option value="">请选择..</option>
		                    <#if mapList??>
		                    	<#list mapList as map>
				                    <#if project?? && project.ofGarden??  &&  project.ofGarden ==map["ID"] >
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
	                    <span class="input-group-addon">项目类型</span>
	                    <select class="form-control" id="projectType" name="projectType" style="width:200px;">
	                        <option value="">请选择..</option>
	                        <#if projectTypelist??>
							<#list projectTypelist as type>
								<#if project?? && project.projectType?? && project.projectType == type.code>
	                        	<option value="${(type.code)!'' }" selected="selected" >${(type.name)!''}</option>
	                        	<#else>
	                        	<option value="${(type.code)!'' }" >${(type.name)!''}</option>
	                        	</#if>
	                        </#list> 
							</#if>
	                    </select>
	                </div>
	            </div>
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon">项目状态</span>
	                    <select class="form-control" id="projectStatus" name="projectStatus" style="width:200px;">
	                        <option value="">请选择..</option>
	                        <#if projectStatuslist??>
							<#list projectStatuslist as status>
								<#if project?? && project.projectStatus?? && project.projectStatus == status.code>
	                        	<option value="${(status.code)!'' }" selected="selected" >${(status.name)!''}</option>
	                        	<#else>
	                        	<option value="${(status.code)!'' }" >${(status.name)!''}</option>
	                        	</#if>
	                        </#list> 
							</#if>
	                    </select>
	                </div>
	            </div>
            </div>
            <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">项目名称</label></span>
		                <input type="text" class="form-control" id="projectName" name="projectName" value="${project.projectName!''}" placeholder="请输入项目名称" style="width:200px;" />
		            </div>
		        </div>
            </div>
            
            
            <div class="tc">
	            <a class="btn black"  href="javaScript:void(0)" onclick="queryForm()"><i class="icon-search" ></i> 查询</a>
	            <a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
	        </div>
	      </div>
			<a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>

            <div class="btn-group">
	    		<a  class="btn green" onclick="addProject();" target="content-box" ><i class="icon-plus"></i> 新增</a>
	    	</div>
	    	<div class="btn-group">
	    		<a  class="btn black"  onclick="deleteAll();" target="content-box" ><i class="icon-trash"></i> 批量删除</a>
        	</div>
	    	
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable">
                <thead>
                    <tr>
                		<th width="3%"><input type='checkbox' id="chooseall" ></input></th>
                        <th width="5%">序号</th>
                        <th width="9%">项目名称</th>
                        <th width="10%">所属园区</th>
                        <th width="16%">项目内容</th>
                        <th width="7%">项目类型</th>
                        <th width="7%">项目状态</th>
                        <th width="7%">项目金额</th>
                        <th width="9%">开始时间</th>
                        <th width="9%">结束时间</th>
                        <th width="18%">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as project>
							<tr>
								<td><input type="checkbox" name='isSelect' value="${(project.ID)!''}" ></input></td>
								<td><div class="zjh">${project_index+1 }</div></td>
								<td><div class="zjh"><a onclick="projectInfo('${(project.ID)!''}')" href="javaScript:void(0)">${(project.PRO_NAME)!"" }</a></div></td>
								<td><div class="zjh">${(project.GNAME)!""}</div></td> 
								<td><div class="zjh">${(project.PRO_CONTENT)!""}</div></td> 
								<td><div class="zjh">${(project.TYPE_NAME)!""}</div></td> 
								<td><div class="zjh">${(project.STATUS_NAME)!""}</div></td> 
								<td><div class="zjh">${((project.PRO_INVEST?number!"0")?string("0.00"))!"0"}万元</div></td>
								<td><div class="zjh">${(project.PRO_TIME?date("yyyy-MM-dd"))!""}</div></td>
								<td><div class="zjh">${(project.PRO_OVERTIME?date("yyyy-MM-dd"))!""}</div></td>
								<td>
									<a class="btn blue" onclick="editPro('${project.ID}');"><i class="icon-edit"></i> 编辑</a>&nbsp;&nbsp;
									<a class="btn black" onclick="deletePro('${(project.ID)!''}');"  ><i class="icon-trash"></i> 删除</a>&nbsp;
								</td>
							</tr>
						</#list> 
					</#if>
										
										
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="12">
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
