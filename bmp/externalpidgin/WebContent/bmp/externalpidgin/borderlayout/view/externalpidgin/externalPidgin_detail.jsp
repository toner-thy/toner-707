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
		<title>涉密涉外活动详细记录</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />
		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
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
			<div class="panel_header no_print">
				<div class="panel_title panel_titleListIco no_print">
					涉密涉外活动详细记录
				</div>
				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
				<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
					<tr height="36">
						<td class="tbLable fr">
							活动种类：
						</td>
						<td class="tbValue fl">
							${externalPidgin.externalPidginType}
						</td>
						<td class="tbLable fr">
							承办人员：
						</td>
						<td class="tbValue fl">
							${externalPidgin.undertaker}
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr">
							密 级：
						</td>
						<td class="tbValue fl">
							<dictionary:text fieldCode="secrecy_level" optionValue="${externalPidgin.secrecyLevel}"></dictionary:text>
						</td>

						<td class="tbLable fr">
							活动名称：
						</td>
						<td class="tbValue fl">
							${externalPidgin.eternalPidginName}
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr">
							开始时间：
						</td>
						<td class="tbValue fl">
							<s:date name="externalPidgin.startDate" format="yyyy年MM月dd日" />
						</td>
						<td class="tbLable fr">
							结束时间：
						</td>
						<td class="tbValue fl">
							<s:date name="externalPidgin.endDate" format="yyyy年MM月dd日" />
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr">
							地 点：
						</td>
						<td class="tbValue fl" colspan="3">
							<c:if test="${externalPidgin.place==''}">
								未填写
							</c:if>
							<c:if test="${externalPidgin.place!=''}">
								${externalPidgin.place}
							</c:if>
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr">
							主办单位：
						</td>
						<td class="tbValue fl" colspan="3">
							${externalPidgin.mainOrgan}
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr">
							协办单位：
						</td>
						<td class="tbValue fl" colspan="3">
							${externalPidgin.aidanceOrgan}
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr">
							关键词：
						</td>
						<td class="tbValue fl" colspan="3">
							${externalPidgin.keyWord}
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr">
							 保密责任人：
						</td>
						<td class="tbValue fl">
							<c:if test="${externalPidgin.secrecyDutier==''}">
								未填写
							</c:if>
							<c:if test="${externalPidgin.secrecyDutier!=''}">
								${externalPidgin.secrecyDutier}
							</c:if>
						</td>

						<td class="tbLable fr">
							责任人职务：
						</td>
						<td class="tbValue fl" colspan="3">
							<c:if test="${externalPidgin.dutierHeadship==''}">
								未填写
							</c:if>
							<c:if test="${externalPidgin.dutierHeadship!=''}">
								${externalPidgin.dutierHeadship}
							</c:if>
						</td>
					</tr>
					<tr height="36">
						<td class="tbLable fr" valign="top">
							内 容：
						</td>
						<td  class="tbValue fl" colspan="3" valign="top" height="100">
							<div style="word-wrap:break-word;overflow: hidden;width: 400px">
								${externalPidgin.content}
							</div>
						</td>
					</tr>
				</table>
				<table class="content_table st no_print" width="100%;">
						<tr>
							<td class="tbLable fr">
								涉密涉外活动附件列表
							</td>
							<td class="fl" colspan="3">
								<attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" hostId="${externalPidgin.externalPidginId}" showTitle="false"/>
							</td>
						</tr>
					</table>
			</div>
		</div>
		<!-- 内容panel结束 -->
	</body>
</html>