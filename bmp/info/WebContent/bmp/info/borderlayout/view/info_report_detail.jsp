<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>信息详情</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/platform/css/public/print/printPage.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
		</script>

  		<!--print 这个属性可以在打印时有效-->
		<style type="text/css" media="print">
			.no_print{display:none;}
			.PageNext{page-break-after: always;}
		</style>

		<style type="text/css">
			.content_Table {
				margin-top:1px;
				color: #446586;
				width: 98%;
				background-color: #fff;
				border-spacing:0;
				border-collapse:collapse;
			}
		</style>

	</head>

	<body>
		<!-- 内容panel开始 -->
		<div class="button_bar no_print">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content print_panel" style="border:0px;">
			<div class="print_panel_content" style="border:0px;background-color: #fff;">
				<table align="center" class="content_Table" style="border:0px;word-break:break-word;word-wrap:break-word;" cellspacing="0" cellpadding="0">
					<tr style="text-align:center;">
						<td colspan="8">
							<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
								${infoWarn.info.title}
							</div>
							<div style="width: 100%; font-size: 14px;font-weight: normal;padding-top:10px; border-top: 1px solid #B2B2B2; font-family: '楷体_gb2312';">
								上报单位：<c:forEach items="${infoWarn.info.infoWarnSet }" var="infoWarn" varStatus="iw"><c:if test="${iw.index == 0 }">${infoWarn.organ.name }</c:if><c:if test="${iw.index > 0 }">;${infoWarn.organ.name }</c:if></c:forEach>
							</div>

							<!-- 属性栏目开始 -->
							<div style="width: 100%;margin-top: 15px;float: left; font-family: Arial, 'Times New Roman' !important;">
								<div class="doc_state fr">类&nbsp;&nbsp;型：</div>
								<div class="doc_state_c fl">
									${infoWarn.info.infoType.name }
								</div>
								<div class="doc_state fr">来&nbsp;&nbsp;源：</div>
								<div class="doc_state_c fl">
									${infoWarn.info.source }
								</div>
								<div class="doc_state fr">审签领导：</div>
								<div class="doc_state_c fl">
									${infoWarn.info.leader.name }
								</div>
								<div class="doc_state fr">报送电话：</div>
								<div class="doc_state_c fl">
									${infoWarn.info.reportPhone }
								</div>
								<div class="doc_state fr">日&nbsp;&nbsp;期：</div>
								<div class="doc_state_c fl">
									<s:date name="#request.infoWarn.reportTime" format="yyyy年MM月dd日" />
								</div>
							</div>
							<!-- 属性栏目结束 -->

							<!-- 正文开始 -->
							<div style="width: 100%;margin-top: 15px;padding-top: 5px;margin-left:10px;line-height: 25px; font-family: Arial, 'Times New Roman' !important;" align="left">
								<c:out escapeXml="false" value="${infoWarn.info.content}" />
							</div>
							<!-- 正文结束 -->

						</td>
					</tr>
				</table>
				<table align="center" width="800px;" class="content_table" style="word-break:break-word;word-wrap:break-word;">
				<tr>
					<td colspan="3">
						<attach:view uploadBehavior="bmpUploadBehavior" hostId="${infoWarn.info.infoId }"></attach:view>
					</td>
				</tr>
			</table>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>