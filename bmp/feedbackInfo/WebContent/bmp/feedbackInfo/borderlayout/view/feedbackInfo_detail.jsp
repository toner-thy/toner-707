<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>意见反馈详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/platform/css/public/print/printPage.css" type="text/css" rel="stylesheet" />

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mootools/mootools-core-1.4.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mootools/mootools-more-1.4.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/notimoo/notimoo-1.2.1.js" type="text/javascript"></script>
		<script src="${ctx}/platform/layout/borderlayout/js/TabUtils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js"></script>
		<script src="${ctx}/resources/js/SimpleUI/SimpleUI.js" type="text/javascript"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!--print 这个属性可以在打印时有效-->
		<style type="text/css" media="print">
			.no_print{display:none;}
			.PageNext{page-break-after: always;}
		</style>

		<!-- 打印功能标签 -->
		<!-- <p:print button="btnPrint"></p:print> -->

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="print_button_bar no_print">
			<div class="left">
				<div class="print_pop_button no_print">
					<a class="print_pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right no_print">
				<div class="print_pop_button no_print">
					<a class="print_pop_button print_pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_content panel_content_table">
					<table align="center" width="800px;" class="content_table" style="word-break:break-word;word-wrap:break-word;">
						<tr style="text-align:center;">
							<td colspan="8">
								<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
									${feedbackInfo.feedbackTitle}
								</div>
								<div style="width: 100%; font-size: 12px;font-weight: normal; padding-top:10px; border-top: 1px solid #B2B2B2;">
									创建人：${feedbackInfo.createPerson.userName} &nbsp;&nbsp; 创建时间：<s:date name="#attr.feedbackInfo.createTime" format="yyyy年MM月dd日" />
								</div>

								<!-- 正文开始 -->
								<div style="width: 100%;margin-top: 15px;float: left;padding-top: 5px;line-height: 25px; font-family: Arial, 'Times New Roman' !important;" align="left">
									<c:out escapeXml="false" value="${feedbackInfo.content}" />
								</div>
								<!-- 正文结束 -->
							</td>
						</tr>
					</table>
				</div>
				<br>
				<div class="panel_content no_print">
					<table align="center" width="800px;" class="content_table" style="word-break:break-word;word-wrap:break-word;">
						<tr>
							<td colspan="3">
								<attach:view uploadBehavior="bmpUploadBehavior" hostId="${feedbackInfo.feedbackInfoId }"></attach:view>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>