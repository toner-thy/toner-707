<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
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
				$ENV.onDomReady(function(){
					// 页面加载完成，执行选择是否要害部门、部位
					selectPC('${secrecyNetwork.isReview}');
					selectSP('${secrecyNetwork.isApproval}');
				});
			});


			function selectPC(value) {
			 if(value==1) {
					$('pass11').style.display="";
				}else {
					$('pass11').style.display="none";
				}
			}

			function selectSP(value) {
				if(value==1) {
					$('pass22').style.display="";
				}else {
					$('pass22').style.display="none";
				}
			}

		</script>
	</head>

	<body>

		<div id="body_content" class="body_content">
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密网络密级变更
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
						<table class="content_table" width="100%">
								<tr>
									<td width="5%" align="right">
										原密级：
									</td>
									<td width="5%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyNetworkChange.beforeLevel}"></dictionary:text>
									</td>
									<td width="5%" align="right">
										现密级：
									</td>
									<td width="5%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyNetworkChange.afterLevel}"></dictionary:text>
									</td>
									<td width="10%" align="right">
										变更时间：
									</td>
									<td width="10%" align="left">
										<fmt:formatDate value="${secrecyNetworkChange.changeDate }" pattern="yyyy-MM-dd"/>
									</td>
									<td width="10%" align="right">
										保密期限变更：
									</td>
									<td width="10%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${secrecyNetworkChange.changeTimeState }"></dictionary:text>
									</td>
									<td width="10%" align="right">
										变更原因：
									</td>
									<td width="30%" align="left">
										<div title="${secrecyNetworkChange.changeReason}">
											<c:if test="${fn:length(secrecyNetworkChange.changeReason) <= 25}">${secrecyNetworkChange.changeReason}</c:if>
											<c:if test="${fn:length(secrecyNetworkChange.changeReason) > 25}">${fn:substring(secrecyNetworkChange.changeReason,0,25)}...</c:if>
										</div>
									</td>
								</tr>
						</table>
				</div>
			</div>

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密网络详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/secrecynetwork' action='secrecyNetwork_adding.action' includeParams='true'/>" method="post">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									涉密网络名称：
								</td>
								<td class="tbValue fl">
									${secrecyNetworkChange.secrecyNetwork.name }
								</td>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									${secrecyNetworkChange.secrecyNetwork.department.departmentName }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									网络密级：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyNetworkChange.secrecyNetwork.secrecyLevel}"></dictionary:text>
								</td>
								<td class="tbLable fr">
									网络类型：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="network_type" optionValue="${secrecyNetworkChange.secrecyNetwork.networkType}"></dictionary:text>
							</tr>
							<tr>
								<td class="tbLable fr">
									是否通过测评：
								</td>
								<td class="tbValue fl">
									<c:if test="${secrecyNetworkChange.secrecyNetwork.isReview eq 1 }">是</c:if>
									<c:if test="${secrecyNetworkChange.secrecyNetwork.isReview eq 0 }">否</c:if>
								</td>
								<td class="tbLable fr">
									是否通过审批：
								</td>
								<td class="tbValue fl">
									<c:if test="${secrecyNetworkChange.secrecyNetwork.isApproval eq 1 }">是</c:if>
									<c:if test="${secrecyNetworkChange.secrecyNetwork.isApproval eq 0 }">否</c:if>
								</td>
							</tr>

							<tr style="display: none;" id="pass11">
								<td class="tbLable fr">
								           测评通过时间：
								</td>
								<td class="tbValue fl">
									<s:date name="secrecyNetworkChange.secrecyNetwork.reviewTime" format="yyyy-MM-dd"/>
								</td>
								<td class="tbLable fr">
									测评通过机构：
								</td>
								<td class="tbValue fl">
									<c:if test="${secrecyNetworkChange.secrecyNetwork.reviewOrgan.organId ne '' }">
								    	${secrecyNetworkChange.secrecyNetwork.reviewOrgan.organName }
								    </c:if>
								</td>
							</tr>
							<tr style="display: none;" id="pass22">
								<td class="tbLable fr">
								          审批时间：
								</td>
								<td class="tbValue fl">
									<s:date name="secrecyNetworkChange.secrecyNetwork.approvalTime" format="yyyy-MM-dd"/>
								</td>
								<td class="tbLable fr">
									审批文号：
								</td>
								<td class="tbValue fl">
								    ${secrecyNetworkChange.secrecyNetwork.approvalNo }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									投入使用时间：
								</td>
								<td class="tbValue fl" colspan="3">
									<s:date name="secrecyNetworkChange.secrecyNetwork.startUseDate" format="yyyy-MM-dd"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>

		</div>
	</body>
</html>