<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>年度计划任务书</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					new SimpleUI.SimpleTab({
						el : 'main_tab',
						onActive : function(item, content, index) {

						},
						onFirstActive : function(item, content, index) {
							//alert(content.get('url'));
							new IFrame({
								src : content.get('url'),
								frameborder : 0,
								styles : {
									width : content.getSize().x,
									height: 300
								},
								events : {
									load : function() {
									}
								}
							}).inject(content);
						}
					});
				});
			});
			// 详 情
			function doDetail(year){
				$ENV.dialog.open({
					url : '${ctx}/bmp/playmanager-demo/pYearPlay/pYearPlay_detail2.jsp?t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '2014年1月计划完成情况详情'
				});
			}
			//导出
			function doExport(){
				window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_exportData.action?district.districtCode=${district.districtCode}&t_date=" + new Date().getTime();
			}
		</script>
<style type="text/css">
	.btns-zone {
	    padding: 15px 0px;
	    /* text-align: center; */
	    border-color: #CCC;
	    -moz-border-top-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    border-image: none;
	    border-width: 1px 0px;
	    line-height:5;
	    height:45px;
	}
	.btns-zone span {
	    background-color: #3ED4E5;
	    border-radius: 5px;
	    color: #FFF;
	    margin: 15px;
	    padding: 10px 10px;
	    text-transform: uppercase;
	  /*   cursor: pointer; */
	    word-break:keep-all;
	    white-space:nowrap;
	}
	.btns-zone .btns-point {
	   cursor: pointer;
	}
	.btns-zone .btn-nodata {
	    background-color: #F47837;
	}
</style>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">

					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco" style="font-size: 15px">
						<a href="##" style="font-size: 15px">2010</a>
						<a href="##" style="font-size: 15px">2011</a>
						<a href="##" style="font-size: 15px">2012</a>
						<a href="##" style="font-size: 15px">2013</a>
						<a href="##" ><font style="font-size: 15px;color: red">2014</font></a>
					</div>
					<div class="panel_btn_bar pop_button_bar">

					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="panel_title panel_titleListIco" style="font-size:25px;text-align: center;">
						【2014】年度计划完成情况
					</div>
				   <div class="btns-zone">
						<div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">1 月份</span>
							</div>

							<div onclick="doDetail('1')" class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:50px; margin-top:-12px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							</div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">2 月份</span>
							</div>
							<div class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:700px; margin-top:-46px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							 </div>
						</div>
				   </div>
				   <div class="btns-zone">
						<div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">3 月份</span>
							</div>
							<div  class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:50px; margin-top:-12px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							</div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">4 月份</span>
							</div>
							<div  class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:700px; margin-top:-46px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							 </div>
						</div>
				   </div>
				   <div class="btns-zone">
						<div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">5 月份</span>
							</div>
							<div class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:50px; margin-top:-12px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							</div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">6 月份</span>
							</div>
							<div class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:700px; margin-top:-46px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							 </div>
						</div>
				   </div>
				   <div class="btns-zone"  >
						<div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">7 月份</span>
							</div>
							<div class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:50px; margin-top:-12px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							</div>
							<div  style="float:left;width: 50px">
							   <span style="font-size:12px;">8 月份</span>
							</div>
							<div  class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:700px; margin-top:-46px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							 </div>
						</div>
				   </div>
				   <div class="btns-zone"  >
						<div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">9 月份</span>
							</div>
							<div class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:50px; margin-top:-12px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							</div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">10月份</span>
							</div>
							<div class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:700px; margin-top:-46px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							 </div>
						</div>
				   </div>
				   <div class="btns-zone"  >
						<div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">11月份</span>
							</div>
							<div class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:50px; margin-top:-12px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							</div>
							<div style="float:left;width: 50px">
							   <span style="font-size:12px;">12月份</span>
							</div>
							<div class="btns-point" style="border:1px #CCC solid;height:40px; width:500px; padding:1px;float: left;margin-left:700px; margin-top:-46px;">
									  <div style=" background:#3F9; height:40px; width:200px"></div>
									  <strong style=" position:absolute; width:500px;text-align:right;  overflow:hidden; margin-top:-30px; ">40%</strong>
							 </div>
						</div>
				   </div>

			</div>
			<!-- 内容panel结束 -->
		</div>
		</div>
	</body>
</html>