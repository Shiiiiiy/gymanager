<div class="left nav">
    <div class="user clearfix">
    	<#if (LoginUser.image_url)?? && (LoginUser.image_url+"")!="">
    		<img src="${rc.contextPath+(LoginUser.image_url)!'/images/user.jpg'}" alt="user" class="mr10 fl" />
    	<#else>	
    		<img src="${rc.contextPath}/images/user.jpg" alt="user" class="mr10 fl" />
    	</#if>
        
        <div class="fl mt5">${(LoginUser.user_no)!''}<p class="comefrom f12">${(LoginUser.name)!''}</p><p class="user-nav f20"><a href="#"><i class="iconfont">&#xe60c;</i></a><a href="#"><i class="iconfont">&#xe60d;</i></a></p></div>
    </div>
    <div class="div_scroll">
    	<#list menus?keys as key>
    		<div class="submenu">
	            <a href="javascript:void(0);" target="content-box" class="class_a"><i class="iconfont f20 mr10">&#xe603;</i><span class="arrow"></span>${key}</a>
	            <ul style="display:none;">
	            	<#list menus[key] as map>
	            		<li><a href="${rc.contextPath}${map["URL"]}" target="content-box"><b class="fr arrow-left"></b><i class="iconfont mr10">&#xe605;</i>${map["TITLE"]}</a></li>
	            	</#list>
	            </ul>
	        </div>
    	</#list>
    </div>
</div>