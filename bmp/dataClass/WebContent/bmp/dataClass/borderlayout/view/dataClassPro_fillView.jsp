<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>上传资料</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<s:actionmessage theme="messages"/>

		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");

			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js", function(){
				$ENV.onDomReady(function(){
				});
			});

		//下载
		function download(attendMeetingId,attachId){
			window.location.href="<s:url action="am_download"  includeParams="false"/>?attendMeeting.attendMeetingId="+attendMeetingId+"&&attachment.attachId="+attachId;
		}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
			</div>
			<div class="right no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<!-- 内容panel开始 -->
		<div class="panel tMargin" style="margin-top: -1px;">
			<div class="panel_content panel_content_table">
				<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
					<tr height="36">
						<td style="text-align:right;">填报人：</td>
						<td>
							${dataClassPro.fillPerson}
						</td>
					</tr>
					<tr height="36">
						<td style="text-align:right;">审核人：</td>
						<td>
							${dataClassPro.auditPerson}
						</td>
					</tr>
					<tr height="36">
						<td style="text-align:right;">填报时间：</td>
						<td>
							 <s:date name="dataClassPro.fillTime" format="yyyy年MM月dd日" />
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr">
							备注：
						</td>
						<td class="tbValue fl">
							<textarea  style="width:99%;height:200px" readonly="readonly">${dataClassPro.remark}</textarea>
						</td>
					</tr>
				</table>
				<table class="content_table st no_print" width="100%;">
					<tr>
						<td class="tbLable fr">
							附件列表
						</td>
						<td class="fl" colspan="3">
							<attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" hostId="${dataClassPro.id}" showTitle="false"/>
						</td>
					</tr>
				</table>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>