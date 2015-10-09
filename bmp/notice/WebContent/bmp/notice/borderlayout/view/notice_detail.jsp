<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>公告内容</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<style type="text/css" media="print">
			.no_print{display:none;}
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

		<!-- 附件上传专用css & js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});

			function download(hostId,attachId){
				window.location.href="<s:url action="externalPidgin_download" includeParams="false"/>?externalPidgin.externalPidginId=" + hostId + "&attachment.attachId=" + attachId;
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar no_print">
			<div class="left no_print">
				<div class="print_pop_button no_print">
				</div>
			</div>
			<div class="right no_print">
			</div>
		</div>
		<!-- 内容panel开始 -->
		<div class="panel tMargin" style="margin-top: -1px;">
			<div class="panel_header no_print">
				<div class="panel_title panel_titleListIco no_print">
					公告内容
				</div>
				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
				<table class="content_table" cellspacing="0" cellpadding="0" width="100%;">
					<tr height="36">
						<td class="tbLable fc" colspan="4">
							${notice.noticeName }
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr">
							发布人：
						</td>
						<td class="tbValue fl">
							${notice.noticePublisher}
						</td>
						<td class="tbLable fr">
							发布时间：
						</td>
						<td class="tbValue fl">
							<s:date name="notice.noticePublishDate" format="yyyy年MM月dd日" />
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr" valign="top">
							内 容：
						</td>
						<td  class="tbValue fl" colspan="3" valign="top" height="100">
							<div style="word-wrap:break-word;overflow: hidden;width:100%;">
								${notice.noticeContent}
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!-- 内容panel结束 -->
	</body>
</html>