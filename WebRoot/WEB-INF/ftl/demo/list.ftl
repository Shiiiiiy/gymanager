<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>欢迎页面</title>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>表单<small class="font-en caps"> form style</small></h2></div>
        <form class="form-inline search_list" role="form"  id="ssssss" method="post" action="${rc.contextPath}/main/list.do">
        <div id="searchDiv">
            <div class="form-inline search_list">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <input type="text" class="form-control" id="name" placeholder="请输入名称">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <input type="text" class="form-control" id="address" placeholder="请输入名称">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <select class="form-control">
                            <option selected="selected">选择列表</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <input type="text" class="form-control" id="name" placeholder="请输入名称">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">地址</span>
                        <input type="text" class="form-control" id="address" placeholder="请输入名称">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">选择列表</span>
                        <select class="form-control">
                            <option selected="selected">选择列表</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <input type="text" class="form-control" id="name" placeholder="请输入名称">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <input type="text" class="form-control" id="address" placeholder="请输入名称">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <input type="text" class="form-control" id="name" placeholder="请输入名称">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <input type="text" class="form-control" id="address" placeholder="请输入名称">
                    </div>
                </div>
            </div>
	        <div class="tc">
	            <a class="btn black"  href="javaScript:void(0)"><i class="icon-search"></i> 搜索</a>
	            <a class="btn black"  href="javaScript:void(0)"  onclick="tc();"><i class="icon-search"></i> 弹出框</a>
	            <a class="btn black"  href="javaScript:void(0)"  onclick="custom_alert('123123',3,2000);"><i class="icon-search"></i> 消息框</a>
	            <a class="btn black"  href="javaScript:void(0)"  onclick="ceshi('123123');"><i class="icon-search"></i> 弹出框全屏版</a>
	            
	            

	
	
	        </div>
       	</div>
        <a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>
        
        <div class="btn-group">
			<a class="btn green" href="javaScript:void(0)"><i class="icon-plus"></i> 新增</a>
		</div>
        <div class="btn-group">
			<a class="btn blue" href="javaScript:void(0)"><i class="icon-edit"></i> 编辑</a>
		</div>
        <div class="btn-group">
			<a class="btn black" href="javaScript:void(0)"><i class="icon-trash"></i> 删除</a>
		</div>	 
        <div class="btn-group">
			<a class="btn yellow" href="javaScript:void(0)"><i class="icon-ban-circle"></i> 禁用</a>
		</div>
        <div class="btn-group">
			<a class="btn little_blue" href="javaScript:void(0)"><i class="icon-ok-sign"></i> 启用</a>
		</div>
        <div class="btn-group">
			<a class="btn purple" href="javaScript:void(0)"><i class="icon-cog"></i> 导出</a>
		</div>
        <div class="btn-group">
			<a class="btn black" href="javaScript:void(0)"><i class="icon-cloud"></i> 上传</a>
		</div>
        <div class="btn-group">
			<a class="btn black" href="${rc.contextPath}/main/dtree.do"><i class="icon-ok-sign"></i> 树形结构</a>
		</div>			
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>PASS_WORD</th>
                        <th>NAME</th>
                        <th>TEL_NO</th>
                        <th>EMAIL</th>
                        <th>REC_DATE</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as user>
							<tr>
								<td>${(user.ID)!""}</td>
								<td>${(user.PASS_WORD)!""}</td>
								<td>${(user.NAME)!""}</td>
								<td>${(user.TEL_NO)!""}</td>
								<td>${(user.EMAIL)!""}</td>
								<td>${(user.EMAIL)!""}</td>
								<td>
									<span class="label label-success">${(user.STATUS)!""}-1</span>
									<span class="label label-info">${(user.STATUS)!""}-2</span>
									<span class="label label-warning">${(user.STATUS)!""}-3</span>
								</td>
								<td>
									<a href="${rc.contextPath}/main/form.do" class="btn blue mini"><i class="icon-edit"></i> Edit</a>&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="#" class="btn mini black"><i class="icon-trash"></i> Delete</a>
								</td>
							</tr>
						</#list> 
					</#if>
										
										
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="8">
                            ${page.pageTool }
						</td>
                    </tr>
                </tfoot>
            </table>
        </div>
       
    </div>
        </form>
        
<div id="ddd" style="display:none;">

	<p>One fine body…</p>

	<p>One fine body…</p>

	<p>One fine body…</p>

	<input type="text" class="m-wrap" data-tabindex="1">

	<input type="text" class="m-wrap" data-tabindex="2">

	<a class="btn green" data-toggle="modal" href="#stack2">Launch modal</a>
</div>

<script>
	function ceshi(msg){
		showDialogFullHD("测试层","ddd",null,"a()","b()","c()",100,true);
	}
	
	function tc(){
		showDialog("测试层","ddd","a()","b()","c()",100,false,true);
	}
	function a(){
		alert("确定");
	}
	function b(){
		alert("取消");
	}
	function c(){
		alert("关闭");
	}

</script>
</body>
</html>
