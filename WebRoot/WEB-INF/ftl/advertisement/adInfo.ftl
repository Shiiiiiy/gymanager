<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "/common/common.ftl" >
		 <title>广告列表</title>
		 <style>
		 	.thumb-box .img-btn{bottom:70px;}
		 </style> 
	</head>
	<body>
		<div class="content-box">
	        <form class="form-inline" role="form"  id="editAd" method="post">
	        	<div class="infor-con clearfix">
			 		<div class-"row clearfix">
			        	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			        		<p><label>广告位置</label><span>${(adLoacName)!''}</span></p>
				        </div>
		       		</div>
	       		</div>
		        <div class="row">
					<#if list??>
						<#list list as file>
							<#if file.fileType?? >
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="${file.filePath}" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file.fileTitle)!''}">${(file.fileTitle)!"" }&nbsp;</h3>
										<h3 class="f14 tc" title="${(file.url)!''}">${(file.url)!"" }&nbsp;</h3>
										</p>
									</div>
								</div>	
							</#if>
						</#list>
					</#if>
		        </div>
	        </form>
		</div>
		
	</body>
</html>