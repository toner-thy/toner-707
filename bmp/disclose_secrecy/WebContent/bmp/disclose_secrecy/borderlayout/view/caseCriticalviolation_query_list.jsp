<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>严重违规案件列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">


		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){
					new SimpleUI.SimpleTab({
						el : 'main_tab',
						onActive : function(item, content, index) {

						},
						onFirstActive : function(item, content, index) {
							var frm = new IFrame({
								src : content.get('url'),
								frameborder : 0,
								styles : {
									width : content.getSize().x,
									height: 620
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

			//导出
			function doExport(action){
				var frm = window.frames[0].document.getElementById("caseCriticalviolation_list_form");
				var oldAction = frm.action;
				frm.action = action;
				frm.submit();
				frm.action = oldAction;
			}


		</script>

	</head>

	<body>

		<div class="body_content" id="body_content" >
			<div id="main_tab" class="panel tab_panel" style="height: 650px;width: 100%;">
				<div id="main_tab_header" class="panel_header" style="width: 100%;">
					<div class="tab_bar">
						<div class="panel_title">严重违规案件列表</div>
						<div class="panel_title">严重违规案件密级变更列表</div>
						<div class="panel_title">严重违规案件解除列表</div>
					</div>
					<div style="position: absolute;right: 0;top: 0;" class="btn_bar">
						<div style="text-align: right;vertical-align: middle;" >
							<ap:operationbutton />
						</div>
						<div class=""></div>
						<div class=""></div>
					</div>
				</div>

				<!-- 3个面板开始 -->
				<div class="tab_panel_content">
					<div class="panel_content" url="${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_selectListing2.action?districtCode=${districtCode}&includeChild=${includeChild}">
					</div>
					<div class="panel_content" url="${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_select_change.action?districtCode=${districtCode}&includeChild=${includeChild}">
					</div>
					<div class="panel_content" url="${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_select_clear.action?districtCode=${districtCode}&includeChild=${includeChild}">
					</div>
				</div>
				<!-- 3个面板结束 -->

			</div>
		</div>
	</body>
</html>