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
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>年度工作计划详情</title>

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

		</script>

		<style type="text/css">
		.ko {
			padding: 12px 12px 0;
		}
		#dvContent {
			font-family: arial, verdana, sans-serif;
			font-size: 14px;
			line-height: 1.666;
			margin: 0;
			min-height: 100px;
			overflow: auto;
			padding: 0;
			white-space: normal;
			word-wrap: break-word;
			color: black;
			background-color: white;
		}
		#dvContent ul,ol{
			margin-left: 40px;
		}
		#dvContent ol li{
			list-style-type:decimal;
			display: list-item;
		}
		#dvContent ul li{
			list-style-type: disc;
			display: list-item;
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
					<a class="pop_button pop_button_close" href="###" onclick="javascript:window.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">

			<!-- 年度工作计划panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						年度工作计划详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" width="100%">
						<tr>
							<td class="tbLable fr">
								年度工作计划标题：
							</td>
							<td class="tbValue fl" >
								${annualPlan.annualPlanTitle }
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								年度：
							</td>
							<td class="tbValue fl">
								${annualPlan.annualPlanYear }
							</td>
						</tr>
						<tr>
							<td class="tbLable" colspan="2">
									<div class="ko">
										<div id="dvContent" class="nui-fClear pm">${annualPlan.annualPlanContent }</div>
									</div>
							</td>
						</tr>
					</table>
					<attach:view uploadBehavior="bmpUploadBehavior" hostId="${annualPlan.annualPlanId }"></attach:view>
				</div>
			</div>
			<!-- 年度工作计划panel结束 -->
		</div>
	</body>
</html>