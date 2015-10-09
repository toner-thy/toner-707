<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密设备详情</title>
		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){

				});
			});
		</script>
	</head>

	<body>
	 <!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar"></div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						技术培训详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table">
						<tr>
							<td align="right" height="36" width="140px">
								培训主题：
							</td>
							<td colspan="3" style="padding-left: 10px;">
								${technicTrain.trainingTitle }
							</td>
						</tr>
						<tr>
							<td align="right" height="36">
								培训时间：
							</td>
							<td style="padding-left: 10px;">
								<fmt:formatDate value='${technicTrain.trainingDate }' pattern='yyyy年MM月dd日'/>
							</td>
							<td align="right" height="36">
								参加培训人数：
							</td>
							<td style="padding-left: 10px;">
								${technicTrain.personNumber }
							</td>
						</tr>
						<tr>
							<td align="right" height="36">
								培训对象：
							</td>
							<td style="padding-left: 10px;" colspan="3">
								<c:if test="${technicTrain.trainingTarget==null}">
									未填写
								</c:if>
								<c:if test="${technicTrain.trainingTarget!=null}">
									${technicTrain.trainingTarget }
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right" height="36">
								培训地点：
							</td>
							<td style="padding-left: 10px;" colspan="3">
								<c:if test="${technicTrain.trainingPlace==null}">
									未填写
								</c:if>
								<c:if test="${technicTrain.trainingPlace!=null}">
									${technicTrain.trainingPlace}
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right" height="50">
								应到场单位：
							</td>
							<td colspan="3" valign="top">
								<c:if test="${organsName==''}">
									未填写
								</c:if>
								<c:if test="${organsName!=''}">
									${organsName}
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right" height="50">
								未到场单位：
							</td>
							<td colspan="3" valign="top">
								<c:if test="${notorgansName==''}">
									未填写
								</c:if>
								<c:if test="${notorgansName!=''}">
									${notorgansName}
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right">
								培训内容描述：
							</td>
							<td colspan="3" height="100" valign="top">
								<c:if test="${technicTrain.trainingContent==null}">
									未填写
								</c:if>
								<c:if test="${technicTrain.trainingContent!=null}">
									${technicTrain.trainingContent}
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right">
								备 注：
							</td>
							<td colspan="3" height="100" valign="top">
								<c:if test="${technicTrain.remark==null}">
									未填写
								</c:if>
								<c:if test="${technicTrain.remark!=null}">
									${technicTrain.remark}
								</c:if>
							</td>
						</tr>
						<!-- 附件显示表 -->
						<tr>
							<td colspan="4">
								<attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" attachments="${attachmentList}" showTitle="false" />
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>