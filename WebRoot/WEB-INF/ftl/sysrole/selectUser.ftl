<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>角色管理</title>
    <style type="text/css">
           .myLi{
               background:#FFF5EE;margin-bottom:3px;
           }
    </style>
</head>
<body style="overflow-x: hidden">
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>选择用户<small class="font-en caps"></small></h2></div>
        <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/sysrole/opt-query/selectUser.do">
          <div id="searchDiv">
            <div class="form-inline search_list">
                <input type="hidden" id="roleId" name="id" value="${(roleId)!'0'}"/>
                
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                   
                    <div class="input-group">
                        <span class="input-group-addon">用户名称</span>
                        <input type="text" class="form-control" value="${(myuser.name)!''}" id="name" name="name" placeholder="请输入用户名称">
                       
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">登录帐号</span>
                        <input type="text" class="form-control" value="${(myuser.user_no)!''}" id="user_no" name="user_no" placeholder="请输入登录帐号">
                       
                    </div>
                
                </div>
              
             </div>
             
             <div class="tc">
		            <a class="btn black"  href="javaScript:void(0)" onclick="formSubmit()"><i class="icon-search" ></i> 查询</a>
		            <a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
		     </div> 
             
           </div>
           <a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>
         
        <div class="row">   
        <div class="table-responsive" style="width:60%;margin-left:15px;float:left;">
         
            <table class="table table-bordered table-striped table-hover mytable" >
                <thead>
                    <tr>
                        <th width="16%"><input type="checkbox" onclick="checkAll()" id="allUser">&nbsp;&nbsp;全选</th>
                        <th width="21%">用户名称</th>
                        <th width="21%">用户帐号</th>
                        <th width="21%">手机号码</th>
                        <th width="21%">邮箱</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as res>
						
							<tr>
								<td><input type="checkbox" name="users" onclick="selectUser(this)" class="${(res.ID)!'0'}" <#if res.PWD?? && res.PWD=='ok'>checked</#if>  ></td>
								<td><div class="zjh">${(res.NAME)!""}</div></td>
								<td><div class="zjh">${(res.USER_NO)!""}</div></td>
								<td>${(res.TEL_NO)!""}</td>
								<td><div class="zjh">${(res.EMAIL)!""}</div></td>
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
            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3" style="width:35%;float:right;margin-top:0px;">
                    <div class="input-group" style="width:80%;float:left;margin-bottom:20px;">
                        <h3>已选择用户</h3><br/>
	                    <ul id="userList" style="max-height:385px;min-height:200px;border:1px gray solid;overflow:auto;">
	                      <#if selectedList??> 
	                          <#list selectedList as sl>
	                             <li class="myLi" name="${(sl.ID)!''}"><span>${(sl.NAME)!""}&nbsp;(${(sl.USER_NO)!""})</span>
	                             <a class="myDel" name="${(sl.ID)!''}" onclick="delUser(this)" href="javascript:void(0);" title="删除该用户">
	                             <font color="red" size="4" >&nbsp;&nbsp;X</font></a>
	                             </li>
	                          </#list>
	                      </#if>   
	                        
	                    </ul><br/>
	                    <div class="btn-group">
				    		<a  class="btn mini black" onclick="delAll();" target="content-box" ><i class="icon-trash"></i>全部删除</a>
				    	</div>
	                    
                    </div>
                    
            </div>
         </div>   
         
        </br>
       <div class="tc">
       			<a class="btn blue" href="javaScript:void(0)" onclick="subUser(${(roleId)!'0'});"><i class="icon-ok"></i> 确定</a>
       			<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-remove"></i> 取消</a>
	   </div>
       </br>
        <input type="hidden" id="idList" name="idList"   value="<#if selectedList??><#list selectedList as sl><#if selectedList?size gt sl_index+1>${(sl.ID)!""},<#else>${(sl.ID)!""}</#if></#list></#if>" />
        </form>
       
    </div>
  
</body>
<script type="text/javascript">

    //清空
    function cleanAll(){
        $("#userParam").val("");
    }


	//提交
	function subUser(str){
	    var idList=$("#idList").val();
	    window.location.href="${rc.contextPath}/sysrole/opt-update/allotUser.do?roleId="+str+"&idList="+idList;
	}
	
    //点击查询
	function formSubmit(){
	   $("#pageNo").val(1);
	   $("#searchForm").submit();
	  
	}
	
	//全部删除
	function delAll(){
	      var ids=$("#idList").val();
	      //将全选取消
	      var all=document.getElementById("allUser");
	      all.checked=false;
	      
	      var ch = document.getElementsByName("users");
	      //将各行数据的复选框全部设为未选中
	      for(var i=0;i<ch.length;i++) {
	            ch[i].checked = all.checked;
	      }
	      
	      $("#userList").html("");
	      $("#idList").val("");
	}
	
	//删除已选中用户
	function delUser(obj){
	      var userId=$(obj).attr("name");
	      var ch = document.getElementsByName("users");
	      //将对应行数据设为未选中
	      for(var i=0;i<ch.length;i++) {
	            if(userId==ch[i].getAttribute("class")){
	                ch[i].checked = false;
	                
	            }
	            
	      }
	      
	      
	      var oldIdList=$("#idList").val();
          var ids=oldIdList.split(",");
	      
	      //剔除隐藏域中对应的id
	      for(var i=0;i<ids.length;i++){
	           if(ids[i]==userId){
	               ids.splice(i,1);
	           }
	      }
	      
	      $("#idList").val(ids);
	      $(obj).parent().remove();
	      
	}
    
    function checkAll(){
           
            var all = document.getElementById("allUser");
	        var ch = document.getElementsByName("users");
	        for(var i=0;i<ch.length;i++) {
	            ch[i].checked = all.checked;
	        }
	        
	        var objs=$("input[name='users']");
	        
	        $(objs).each(function(){
				  selectUser(this);
		    });
			
    }
    

    //取消
	function goBacks(){
		window.location.href="${rc.contextPath}/sysrole/opt-query/roleList.do";
	}
	
	//清空查询条件
	function cleanAll(){
	   $("#user_no").val("");
	   $("#name").val("");    
		 
	}
	
	
	 //点击第一列中的复选框
     function selectUser(obj){
            var usId=$(obj).attr("class");
            var oldIdList=$("#idList").val();
            var ids=oldIdList.split(",");
            
            if($(obj).is(':checked')){
                var count=0;
                for(var i=0;i<ids.length;i++){
	                if(ids[i]==usId){
	                    count++;
	                    return;
	                }
                }
                if(count==0){
	                    ids.push(usId);
	                   
	                    $("#idList").val(ids);
	                    var userNo=$(obj).parent().next().next().text();
	                    var name=$(obj).parent().next().text();
	                    var str="<li class='myLi' name='"+usId+"'><span>"+name+"&nbsp;("+userNo+")</span><a class='myDel' name='"+usId+"' onclick='delUser(this)' href='javascript:void(0);' title='删除该用户'>";
	                    str=str+"<font color='red' size='4' >&nbsp;&nbsp;X</font></a></li>";
	                    //向右侧展示框中添加刚刚选中的用户
	                    $("#userList").append(str);
	                    return;
	            }
            }else{
                for(var i=0;i<ids.length;i++){
	                if(ids[i]==usId){
	                   ids.splice(i,1);
	                  
	                   $("#idList").val(ids);
	                   var objs=$("#userList li");
	                   //删除右侧展示框中与刚才去除勾选项对应的用户
	                   $(objs).each(function(){
					         if($(this).attr("name")==usId){
					             $(this).remove();
					         }
			           });
	                   
	                   return;
	                }else{
	                    
	                }
                }
            }
            
            
        }
    
	
</script>
</html>
