<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<#include "/common/common.ftl" >
		<title>操作跳转页面</title>
	</head>
<body>
   		<#if msg??>
				<form action="${rc.contextPath}${(msg.url)!""}" method="post" id="frm">
	   				<#if msg.messageParamList??>
						<#list msg.messageParamList as m>
								<input type="hidden" name="${(m.key)!"" }" value="${(m.val)!""}"/>
		                </#list> 
					</#if>
	   			</form>
		</#if>
<div id="ddd" style="display: none;">
	${(msg.tips)!"" }
</div>
<script>
	forward_showDialogFullHD("系统提示","ddd",'${(msg.tips)!"" }',null,null,"submitForm()",100,false,null,1000,"submitForm()");
	function submitForm(){
		$("#frm").submit();
	}
</script>
</body>
</html>