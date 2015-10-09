<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密工作机构</title>

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

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
			});

			// 详 情
			function doDetail(id){
				environment.dialog.open({
					url : '${ctx}/bmp/demo/secrecyOrgan/secrecyOrgan_detail.jsp',
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					icon : '${ctx}/platform/theme/default/images/main/display.gif',
					title : '保密工作机构详情'
				});
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="secrecyOrgan_add.jsp" onclick=""><span>新 增</span></a>
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
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密工作机构简介" ctx="${ctx}" icoPath="/bmp/demo/secrecyOrgan/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密工作机构简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密工作机构
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

					<!-- 联系方式 -->
					<div class="cpMsgContactInfoTitle">
						业务指导
					</div>
					<div class="cpMsgContactInfoContext">
						联系人：四川省国家保密局督察处 XXX 联系电话：028-85229437
					</div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密工作机构
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<u:noData text="当前尚未填写保密工作机构，请点击【新增】按钮添加。"/>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>