<div class="header w">
        <div class="logo fl">
            <img src="${rc.contextPath}/images/logo.png" alt="logo" width="100" />
            <a href="javascript:;" class="xs-but" style="display:none;"><i class="iconfont f30">&#xe600;</i></a>
            <a href="javascript:;" class="but"><i class="iconfont f30">&#xe600;</i></a>
            
        </div>
        <div class="function clearfix fr">
            <ul>
                <li class="user">
                    <div class="dropdown">
                        <a href="#" class="dropbut">
				        <#if (LoginUser.image_url)?? && (LoginUser.image_url+"")!="">
				    		<img src="${rc.contextPath+(LoginUser.image_url)!'/images/user.jpg'}" alt="user" class="mr10" />
				    	<#else>	
				    		<img src="${rc.contextPath}/images/user.jpg" alt="user" class="mr10" />
				    	</#if>
                        <span>${(LoginUser.user_no)!''}</span><b class="arrow-down"></b></a>
                        <ul id="mm" class="dropmenu-box dropmenu" style="display:none; width:190px;">
                            <li><a href="#">个人资料</a></li>
                            <li><a href="#">修改密码</a></li>
                            <li><a href="${rc.contextPath}/logout">退出登录</a></li>
                        </ul>
                    </div>
                </li>
                <li class="xs-hidden"><a href="javascript:;" class="refresh"><i class="iconfont f25">&#xe606;</i></a></li>
                <li class="xs-hidden">
                    <div class="dropdown">
                        <a href="javascript:;" class="skin dropbut last-but"><i class="iconfont f25">&#xe60a;</i></a>
                        <ul id="mm" class="dropmenu-box last skin-con clearfix" style="display: none; width: 160px;">
                            <li id="skin_0" class="red selected"><a href="#">1</a></li>
                            <li id="skin_1" class="blue"><a href="#">2</a></li>
                            <!--<li id="skin_2" class="orange"><a href="#">3</a></li>
                            <li id="skin_3" class="red"><a href="#">3</a></li>-->
                        </ul>
                    </div>
                </li>
                <li><a href="javascript:;" class="shutdown popup-but-confirm"><i class="iconfont f25">&#xe608;</i></a></li>
            </ul>
        </div>
</div>
