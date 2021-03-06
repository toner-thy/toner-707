<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统升级日志</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<style type="text/css">
html,body,div,h1,h2,h3,h4,h5,h6,ul,ol,li,dl,dt,dd,p,blockquote,pre,form,input,textarea,fieldset,table,th,td
	{
	margin: 0;
	padding: 0
}

body,button,input,select,textarea,h1,h2,h3,i {
	font: normal 12px/1em 'Microsoft YaHei', '\5fae\8f6f\96c5\9ed1', arial,
		helvetica, sans-serif
}

body {
	min-width: 960px
}

ul,li {
	list-style: none
}

a {
	text-decoration: none
}

.cls {
	zoom: 1
}

.cls:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden
}

.link:hover {
	text-decoration: underline
}

hr {
	height: 0;
	border-left: none;
	border-right: 0;
	border-top: 1px dashed #2d2f34;
	border-bottom: 1px dashed #474954
}

.wrapper {
	width: 960px;
	margin: 0 auto
}

.header {
	border-bottom: 10px solid #0b84e9;
	box-shadow: 0 1px 1px #2fa0ff;
	background: #244b8c url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/t01508a114ce3a4469f.png)
		no-repeat bottom center
}

.header .top {
	height: 60px;
	background: #fff
}

.header .top h1 {
	float: left;
	padding: 6px 0 0 14px
}

.header .top h1 a {
	display: block;
	width: 153px;
	height: 0;
	padding-top: 48px;
	overflow: hidden;
	background: url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/logo.png) no-repeat
}

.header .top .nav {
	float: right;
	position: relative;
	margin-top: 16px
}

.header .top .nav li {
	padding: 8px 6px;
	margin: 0 10px;
	display: inline-block
}

.header .top .nav li a {
	color: #999
}

.header .top .nav li.current {
	border-bottom: 1px solid #63d029
}

.header .top .nav li.current a {
	color: #63d029
}

.header .top .nav li.last {
	margin-right: 0;
	padding-right: 0
}

.header .top .nav li.last:hover {
	text-decoration: underline
}

.header .top .nav li.underscore {
	position: absolute;
	width: 100%;
	left: 0;
	bottom: 0;
	margin: 0;
	padding: 0
}

.header .top .nav li.underscore i {
	position: absolute;
	display: none;
	height: 1px;
	bottom: 0;
	background: #63d029;
	-webkit-transition: .5s;
	-moz-transition: .5s;
	-ms-transition: .5s;
	-o-transition: .5s;
	transition: .5s
}

.content {
	background: #3a3c48
		url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/content-bg.png)
}

.footer {
	height: 120px;
	padding-top: 18px;
	color: #adafba;
	background: #2c2f38;
	text-align: center
}

.footer p {
	line-height: 24px
}

.ie6 .header .top .nav li,.ie7 .header .top .nav li {
	display: inline;
	zoom: 1
}

.ie6 .header .top h1 a {
	background-image: url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/logo-8.png);
	font-size: 0
}

.ie6 .header .top .nav li.underscore i {
	overflow: hidden
}
</style>
<style type="text/css">
.header {
	height: 158px;
	background: #2d8ee7
		url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/header-bg2.png) no-repeat
		bottom center
}

.content {
	padding: 50px 0;
	min-height: 720px
}

.content .wrapper {
	position: relative;
	background: url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/release-bg.png)
		no-repeat right top
}

.content .light {
	position: absolute;
	left: 55px;
	top: -50px;
	width: 152px;
	height: 191px;
	background: url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/t011df7512152150bb9.png) no-repeat
		top center
}

.content .light i {
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0;
	background: url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/t01cfdd74c38452f86c.png) no-repeat
		top center
}

.content .line-left {
	position: absolute;
	left: 0;
	top: 15px;
	width: 70px
}

.content .line-right {
	position: absolute;
	right: 0;
	top: 15px;
	width: 460px
}

.content .main {
	background: url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/line-bg.png) repeat-y
		249px 0
}

.content .main .title {
	position: absolute;
	line-height: 40px;
	padding-left: 67px;
	left: 230px;
	top: 0;
	color: #58a6fb;
	font-size: 24px;
	background: url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/clock.png) no-repeat
		left top
}

.content .main .year {
	position: relative;
	z-index: 100
}

.content .main .year h2 {
	height: 40px;
	width: 170px;
	padding-right: 30px;
	font-size: 24px;
	line-height: 40px;
	text-align: right
}

.content .main .year h2 a {
	color: #58a6fb
}

.content .main .year h2 i {
	display: block;
	position: relative;
	height: 0;
	width: 0;
	left: 190px;
	top: -20px;
	border-width: 6px;
	border-style: solid;
	border-color: #59a7fb transparent transparent transparent;
	-webkit-transition: .5s;
	-moz-transition: .5s;
	-ms-transition: .5s;
	-o-transition: .5s;
	transition: .5s;
	-webkit-transform-origin: 6px 3px;
	-moz-transform-origin: 6px 3px;
	-ms-transform-origin: 6px 3px;
	-o-transform-origin: 6px 3px;
	transform-origin: 6px 3px
}

.content .main .year .list {
	margin: 10px 0;
	position: relative;
	overflow: hidden;
	-webkit-transition: height 1s cubic-bezier(0.025, 0.025, 0.000, 1.115),
		opacity 1s;
	-moz-transition: height 1s cubic-bezier(0.025, 0.025, 0.000, 1.115),
		opacity 1s;
	-ms-transition: height 1s cubic-bezier(0.025, 0.025, 0.000, 1.115),
		opacity 1s;
	-o-transition: height 1s cubic-bezier(0.025, 0.025, 0.000, 1.115),
		opacity 1s;
	transition: height 1s cubic-bezier(0.025, 0.025, 0.000, 1.115), opacity
		1s
}

.content .main .year .list ul {
	bottom: 0
}

.content .main .year .list ul li {
	background: url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/circle.png) no-repeat
		235px 31px;
	padding: 30px 0;
	color: #a1a4b8
}

.content .main .year .list ul li .date,.content .main .year .list ul li .version
	{
	float: left;
	display: block;
	clear: left;
	width: 200px;
	line-height: 24px;
	text-align: right
}

.content .main .year .list ul li .date {
	font-size: 18px;
	line-height: 32px;
	color: #bec1d5
}

.content .main .year .list ul li .intro,.content .main .year .list ul li .more
	{
	float: left;
	display: block;
	width: 400px;
	margin-left: 100px;
	line-height: 24px
}

.content .main .year .list ul li .intro {
	font-size: 18px;
	line-height: 32px;
	color: #63d029
}

.content .main .year .list ul li.highlight {
	background-image: url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/circle-h.png)
}

.content .main .year .list ul li.highlight .date,.content .main .year .list ul li.highlight .intro
	{
	color: #ec6a13
}

.content .wrapper:first-child .main .year.close h2 i {
	transform: rotate(-90deg);
	-webkit-transform: rotate(-90deg);
	-moz-transform: rotate(-90deg);
	-ms-transform: rotate(-90deg);
	-o-transform: rotate(-90deg)
}

.content .wrapper:first-child .main .year.close .list {
	opacity: 0;
	height: 0 !important
}

.ie7 .content .main .year h2 i {
	left: 40px
}

.ie6 .content .wrapper {
	background-image:
		url(${ctx}/platform/jsp/public/sysReleaseLog/release/css/image/release-bg-8.png)
}

.ie6 .content .light {
	display: none
}

.ie6 .content .line-left {
	width: 100px
}

.ie6 .content .main .year h2 {
	padding-right: 0;
	width: 200px
}

.ie6 .content .main .year h2 a {
	cursor: default
}

.ie6 .content .main .year h2 i {
	display: none
}
</style>
</head>
<body>
<div class="content" >
	<div class="wrapper">
		<div class="light"><i></i></div>
		<hr class="line-left">
		<hr class="line-right">
		<div class="main">
			<h1 class="title">系统更新日志</h1>
			<s:if test="#request.sysReleaseLogMap.size>0">
				<s:iterator var="map" value="#request.sysReleaseLogMap"	status="mapList">
					<div class="year">
						<h2><a href="#"><s:property value="key" />年<i></i></a></h2>
						<div class="list" style="height: 720px; ">
							<ul style="position: absolute; ">
								<s:iterator var="srl" value="#map.value" status="listList">
									<li class="cls">
										<p class="date"><s:property value="%{getText('{0,date,MM.dd }',{releaseDate})}" /></p>
										<p class="intro"><s:property value="releasePerson" /></p>
										<p class="version"><s:property value="releaseVersion" /></p>
										<div class="more"><pre><s:property value="releaseContent" /></pre></div>
									</li>
								</s:iterator>
							</ul>
						</div>
					</div>
				</s:iterator>
			</s:if>
		</div>
	</div>
</div>

<script src="${ctx}/platform/jsp/public/helpTree/resource/js/jquery-1.6.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		var e = $(".nav"), t = e.offset().left;
		$(window).on("resize", function() {
			t = e.offset().left
		});
		var n = e.find("li.current"), r = {
			width : n.innerWidth(),
			left : n.offset().left - t
		}, i = $(".nav .underscore i"), s;
		e.find("li:not(.underscore):not(.last)").mouseover(function() {
			var e = $(this);
			s = setTimeout(function() {
				i.css({
					width : e.innerWidth(),
					left : e.offset().left - t
				})
			}, 100)
		});
		var o = function() {
			clearTimeout(s), i.css(r)
		};
		e.mouseleave(o).find("li.last").mouseover(o), i.css(r).show(), n.css(
				"border-color", "#fff")
	})
</script>
<script>
	$(".main .year .list").each(function(e, t) {
		var n = $(t), r = n.find("ul");
		n.height(r.outerHeight()), r.css("position", "absolute")
	}), $(".main .year>h2>a").click(function(e) {
		e.preventDefault(), $(this).parents(".year").toggleClass("close")
	})
</script>
</body>
</html>