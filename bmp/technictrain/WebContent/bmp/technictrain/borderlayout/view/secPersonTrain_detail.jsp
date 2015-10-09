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
			function doClose(){
				environment.dialog.close();
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:doClose();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密人员培训详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
				<table class="content_table">
						<tr>
							<td align="right" width="20%">
								姓 名：
							</td>
							<td width="15%">
								${secPersonTrain.secPersonName}
							</td>
							<td align="right" width="15%">性 别：</td>
							<td width="15%">
								<c:if test="${secPersonTrain.sex eq 1}">男</c:if>
								<c:if test="${secPersonTrain.sex eq 0}" >女</c:if>
							</td>
							<td rowspan="3" colspan="2" align="center" width="30%">
								<c:choose>
									<c:when test="${secPersonTrain.photo.savePath=='' || secPersonTrain.photo.savePath==null}">
										<img id="secPersonTrain.photo.src" src="${ctx}/platform/theme/default/images/person_photo.jpg" width="80" height="100" border="0">
									</c:when>
									<c:otherwise>
										<img id="secPersonTrain.photo.src" src="${ctx}/${secPersonTrain.photo.savePath}" width="80" height="100" border="0">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right">出生年月：</td>
							<td>
								<s:date name="secPersonTrain.birthday" format="yyyy年MM月dd日"/>
							</td>
							<td align="right">
								职 务：
							</td>
							<td>
								${secPersonTrain.duty}
							</td>
						</tr>
						<tr>
							<td align="right">
								政治面貌：
							</td>
							<td>
								<c:if test="${secPersonTrain.politics eq 1}">党员</c:if>
								<c:if test="${secPersonTrain.politics eq 2}" >团员</c:if>
								<c:if test="${secPersonTrain.politics eq 3}" >无党派</c:if>
							</td>
							<td align="right">
								学 历：
							</td>
							<td>
								<c:if test="${secPersonTrain.studyLever eq 1}">高中</c:if>
								<c:if test="${secPersonTrain.studyLever eq 2}" >专科</c:if>
								<c:if test="${secPersonTrain.studyLever eq 3}" >本科</c:if>
								<c:if test="${secPersonTrain.studyLever eq 4}" >硕士</c:if>
								<c:if test="${secPersonTrain.studyLever eq 5}" >博士</c:if>
							</td>
						</tr>
						<tr>
							<td align="right">
								工作单位：
							</td>
							<td>
								${secPersonTrain.workPlace}
							</td>
							<td align="right">
								涉密岗位名称：
							</td>
							<td>
								${secPersonTrain.secDutyName}
							</td>
							<td align="right">
								参加工作日期：
							</td>
							<td>
								<s:date name="secPersonTrain.joinWorkDay" format="yyyy年MM月dd日"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								到涉密岗位工作日期：
							</td>
							<td>
								<s:date name="secPersonTrain.secWorkDay" format="yyyy年MM月dd日"/>
							</td>
							<td align="right">
								接触最高密级：
							</td>
							<td>
								<c:if test="${secPersonTrain.secLever eq 1}">一般</c:if>
								<c:if test="${secPersonTrain.secLever eq 2}" >重要</c:if>
								<c:if test="${secPersonTrain.secLever eq 3}" >核心</c:if>
							</td>
							<td align="right">
								是否与单位签订保密协议：
							</td>
							<td>
							 	<c:if test="${secPersonTrain.secAgreement eq 0}">否</c:if>
								<c:if test="${secPersonTrain.secAgreement eq 1}" >是</c:if>
							</td>
						</tr>
						<tr>
							<td align="right">
								培训日期：
							</td>
							<td>
								<s:date name="secPersonTrain.trainDay" format="yyyy年MM月dd日"/>
							</td>
							<td align="right">
								培训成绩：
							</td>
							<td>
								${secPersonTrain.trianGrade}
							</td>
							<td align="right">
								上岗证：
							</td>
							<td>
								<c:if test="${secPersonTrain.dutyCertificate eq 1}">A</c:if>
								<c:if test="${secPersonTrain.dutyCertificate eq 2}" >B</c:if>
								<c:if test="${secPersonTrain.dutyCertificate eq 3}" >C</c:if>
							</td>
						</tr>
						<tr>
							<td align="right">
								上岗编号：
							</td>
							<td colspan="5">
									${secPersonTrain.dutyNumber}
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

	</body>
</html>