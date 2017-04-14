$(function () {
    //skin
    //<![CDATA[
    var $li = $(".skin-con li");
    $li.click(function () {
        switchSkin(this.id);
    });
    var cookie_skin = $.cookie("MyCssSkin");
    if (cookie_skin) {
        switchSkin(cookie_skin);
    }
    function switchSkin(skinName) {
        $("#" + skinName).addClass("selected").siblings().removeClass("selected");
        //当前<li>元素选中
        //去掉其它同辈<li>元素的选中
        $("#cssfile").attr("href", "css/skin/" + skinName + ".css");
        $("#con-iframe").contents().find("#cssfile").attr("href", "css/skin/" + skinName + ".css");
        //设置不同皮肤
        $.cookie("MyCssSkin", skinName, { path: '/', expires: 10 });
    }
    //]]>
    //分页
    var initPagination = function () {
        var num_entries = $("#hiddentable tr").length;
        // 创建分页
        $("#Pagination").pagination(num_entries, {
            num_edge_entries: 1, //边缘页数
            num_display_entries: 4, //主体页数
            callback: pageselectCallback,
            items_per_page: 3,//每页显示3项
            prev_text: "上一页",
            next_text: "下一页",
        });
    }();

    function pageselectCallback(page_index, jq) {
        var max_elem = (page_index + 1) * 3;
        $("#Searchresult").html("");
        // 获取加载元素
        for (var i = page_index * 3; i < max_elem; i++) {
            $("#Searchresult").append($("#hiddentable tr:eq(" + i + ")").clone());
        }
        return false;
    }
    //time
    function show() {
        var mydate = new Date();
        var str = "" + mydate.getFullYear() + "年";
        str += "" + mydate.getMonth() + "月";
        str += "" + mydate.getDate() + "日";
        str += "" + mydate.getHours() + ":";
        str += "" + mydate.getMinutes() + ":";
        if (mydate.getSeconds() < 10) {
            str += "" + "0" + mydate.getSeconds();
        } else {
            str += "" + mydate.getSeconds();
        }
        return str;
    }
    setInterval(function () { $(".showtime").html(show()) }, 1000);
    //弹窗
    /*function g_alert(type, cont, url) {
        //html	
        var html = '<div id="g_all"></div><div id="g_box"><div id="g_cont"><i class="iconfont"></i><span id="g_msg">' + cont + '</span></div><div id="g_button"><a class="butt" id="ok">确定</a><a class="butt" id="false">取消</a></div></div>';
        $('body').append(html);

        //类型为alert
        if (type == 'success') {
            $('#g_cont i').html('&#xe612;');
            $('#false').hide();
            $('#g_box').css("background", "#3aaf00");
        }

        if (type == 'error') {
            $('#g_cont i').html('&#xe614;');
            $('#false').hide();
        }

        //类型为confirm
        if (type == 'warn') {
            $('#g_cont i').html('456');
            $('#false').hide();
        }

        //类型为confirm
        if (type == 'confirm') {
            $('#g_cont i').html('&#xe613;');
        }

        //点击OK
        $('#ok').click(function () {
            $('#g_all').remove();
            $('#g_box').remove();
            $('#g_css').remove();
            if (url) {
                window.location = url;
            }
            return true;
        });

        //点击false
        $('#false').click(function () {
            $('#g_all').remove();
            $('#g_box').remove();
            $('#g_css').remove();
            if (type != 'confirm') {
                if (url) {
                    window.location = url;
                }
            }
            return false;
        });

        //居中
        var boxWidth = $("#g_box").width();
        var boxHeight = $("#g_box").height();
        $("#g_box").css({ "margin-top": -(boxHeight) / 2 + "px" });
    }
    function success() {
        g_alert('success', '操作成功', '#');
    }
    function error() {
        g_alert('error', '操作失败'); //url可写可不写
    }
    function confirm() {
        g_alert('confirm', '你确定要退出登录？', '#');
    }
    function warn() {
        g_alert('warn', '用户名不能为空');
    }
    $(".popup-but-confirm").click(function () {
        confirm();
    });*/
    //验证提示
    $("div.error input").parent().append("<p>123123123</p>");
    $("div.error input").focus(function () {
        $(this).parent().children("p").remove();
    });
    $("div.error input").blur(function () {
        $(this).parent().append("<p>123123123</p>");
    });
    //搜索条件伸缩
    var formH = $("form.search_list").height();
    if (formH > 100) {
        $("form.search_list").css("height", "100px");
    } else {
        $("form.search_list").css("height", "auto");
    }
    $("a.expend").click(function () {
        if ($("form.search_list").css("height") == "100px") {
            $("form.search_list").css("height", "auto");
            $(this).html("<span class='triangle-up'></span>收缩");
        } else {
            $("form.search_list").css("height", "100px");
            $(this).html("<span class='triangle-down'></span>展开");
        }
    });
});