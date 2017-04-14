$(function () {
    //页面高度
    var pageHeight = $(window).height() - 60;
    $(".left, .content, #con-iframe").height(pageHeight);
    $(window).resize(function () {
        var pageHeight = $(window).height() - 60;
        $(".left, .content, #con-iframe").height(pageHeight);
    });
    //左菜单显示隐藏
    $("a.but").click(function () {
        if ($(".left").css("display") == "none") {
            $(".left").animate({ "left": "0px" }, 200);
            $(".content").animate({ "left": "250px" }, 200);
            $(".left").css("display", "block");
            $("#ascrail2000").fadeIn(300);
            var bodyWidth = $(window).width();
            if (bodyWidth < 600) {
                $(".content").width(bodyWidth);
            } else {
                $(".content").width(bodyWidth - 250);
            }
            $(window).resize(function () {
                var contentWidth = $(window).width();
                if (contentWidth < 600) {
                    $(".content").width(contentWidth);
                } else {
                    $(".content").width(contentWidth - 250);
                }
            });
        } else {
            $(".left").animate({ "left": "-250px" }, 200);
            $(".content").animate({ "left": "0px" }, 200);
            $(".left").css("display", "none");
            $("#ascrail2000").fadeOut(10);
            var bodyWidth = $(window).width();
            $(".content").width(bodyWidth);
            $(window).resize(function () {
                var contentWidth = $(window).width();
                $(".content").width(contentWidth);
            });
        }
    });
    var bodyWidths = $(window).width();
    if (bodyWidths < 600) {
        $(".left").css({ "left": "-250px" });
        $(".content").css("left", "0px");
        $(".content, .xs-nav").width(bodyWidths);
        $("a.xs-but").css("display", "inline-block");
    } else if ($(".left").css("display") == "none") {
        $(".left").css({ "left": "-250px" });
        $(".content").css("left", "0px");
    } else {
        $(".left").css({ "left": "0px" });
        $(".content").css("left", "250px");
        $(".content").width(bodyWidths - 250);
        $("a.xs-but").css("display", "none");
        $(".xs-nav").css("display", "none");
    }
    $(window).resize(function () {
        var contentWidths = $(window).width();
        if (contentWidths < 600) {
            $(".left").css({ "left": "-250px" });
            $(".content").css("left", "0px");
            $(".content, .xs-nav").width(contentWidths);
            $("a.xs-but").css("display", "inline-block");
        } else if ($(".left").css("display") == "none") {
            $(".left").css({ "left": "-250px" });
            $(".content").css("left", "0px");
            if (contentWidths < 600) {
                $("a.xs-but").css("display", "inline-block");
            } else {
                $("a.xs-but").css("display", "none");
                $(".xs-nav").css("display", "none");
            }
        } else {
            $(".left").css({ "left": "0px" });
            $(".content").css("left", "250px");
            $(".content").width(contentWidths - 250);
            $("a.xs-but").css("display", "none");
            $(".xs-nav").css("display", "none");
        }
    });
    $("a.xs-but").click(function () {
        if ($(".xs-nav").css("display") == "none") {
            $(".xs-nav").slideDown(200);
        } else {
            $(".xs-nav").slideUp(200);
        }
    });
    //折叠菜单
    $("a.class_a").click(function () {
        if ($(this).parent().children("ul").css("display") == "none") {
            $(this).addClass("active");
            $(this).find("span").addClass("expand");
            $(this).parent().siblings().children("a.class_a").removeClass("active");
            $(this).parent().siblings().children("a.class_a").children("span").removeClass("expand");
            $(this).parent().children("ul").slideDown(100);
            $(this).parent().siblings().children("ul").slideUp(100);
        } else {
            $(this).parent().children("ul").slideUp(100);
            $(this).removeClass("active");
            $(this).find("span").removeClass("expand");
        }
    });
    $(".submenu li a").click(function () {
        $(this).addClass("current");
        $(this).parents("div.submenu").siblings().children("ul").children().children().removeClass("current");
        $(this).parents("li").siblings().children().removeClass("current");
    });
    //dropdown
    $(".dropdown").hover(function () {
        $(this).find("ul").slideDown(200);
        $(this).find(".dropbut").addClass("active");
    }, function () {
        $(this).find("ul").slideUp(200);
        $(this).find(".dropbut").removeClass("active");
    });
    //header-function
    $(".refresh").click(function () {
        $('#con-iframe').attr('src', $('#con-iframe').attr('src'));
    });
    //dropdown-last
    var lastWidth = $(".dropdown .last").outerWidth();
    selfWidth = $(".dropdown .last-but").outerWidth();
    $(".dropdown .last").css("left", -(lastWidth - selfWidth - 4));
    //skin
    //<![CDATA[
    var $li = $(".skin-con li");
    $li.click(function () {
        switchSkin(this.id);
    });
    
    /***
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
    **/

  //Scrollbar//
    var bodyHeight = $(window).height();
    scroll_con = $(".div_scroll").height();
    scroll_con_xs = $(".div_scroll_xs").height();
    maxH = bodyHeight - 60 - 201;
    maxH_xs = bodyHeight - 60;
    function Scrollmove() {
        $(".div_scroll").niceScroll({
            cursorcolor: "#ffffff",
            cursoropacitymin: 0.5,
            cursoropacitymax: 0.7,
            touchbehavior: false,
            cursorwidth: "8px",
            cursorborder: "0",
            cursorborderradius: "5px",
            autohidemode: false,
        });
    }
    function ScrollmoveXs() {
        $(".div_scroll_xs").niceScroll({
            cursorcolor: "#ffffff",
            cursoropacitymin: 0.5,
            cursoropacitymax: 0.7,
            touchbehavior: false,
            cursorwidth: "8px",
            cursorborder: "0",
            cursorborderradius: "5px",
            autohidemode: false,
        });
    }
    if (scroll_con >= maxH) {
        $(".div_scroll").height(maxH);
        Scrollmove();
    } else {
        $(".div_scroll").css("height", "auto");
    }
    if (scroll_con_xs >= maxH_xs) {
        $(".div_scroll_xs").height(maxH_xs);
        ScrollmoveXs();
    } else {
        $(".div_scroll_xs").css("height", "auto");
    }

    $(window).resize(function () {
        var bodyHeight = $(window).height();
        scroll_con = $(".div_scroll").height();
        scroll_con_xs = $(".div_scroll_xs").height();
        maxH = bodyHeight - 60 - 201;
        maxH_xs = bodyHeight - 60;
        if (scroll_con >= maxH) {
            $(".div_scroll").height(maxH);
            Scrollmove();
        } else {
            $(".div_scroll").css("height", "auto");
        }
        if (scroll_con_xs >= maxH_xs) {
            $(".div_scroll_xs").height(maxH_xs);
            ScrollmoveXs();
        } else {
            $(".div_scroll_xs").css("height", "auto");
        }
    });
   
});