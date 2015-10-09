<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>

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
				$ENV.onDomReady(function(){
				});
			});

			function doExportEstablish() {
				var districtCode;
				var flag = '${flag}';
				if('${param.districtCode}' == ''){
					districtCode = '${district.districtCode}';
				} else {
					districtCode = '${param.districtCode}';
				}
				window.location.href = "${ctx}/secrecyorganization/secrecyofficestat/secrecyOfficeStat_exportEstablishSituation.action?districtCode=" + districtCode + "&flag=" + flag + "&_ts=" + $time();
			}

		</script>
	</head>

	<body>
		<div style="padding:5px;">
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编制构成情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="###" onclick="doExportEstablish()"><span>导 出</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" align="center" width="20%">公务员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">参照管理事业编制(百分比)</td>
								<td class="tableHeader" align="center" width="20%">全额拨款事业编制(百分比)</td>
								<td class="tableHeader" align="center" width="20%">自收自支事业编制(百分比)</td>
								<td class="tableHeader" align="center" width="20%">合计</td>
							</tr>
							<c:set var="total1" value="0"></c:set>
							<c:set var="total2" value="0"></c:set>
							<c:set var="total3" value="0"></c:set>
							<c:set var="total4" value="0"></c:set>
							<c:forEach items="${establishSituationList }" var="e" varStatus="eStatus">
								<c:set var="total1" value="${e.civil + e.referMamager + e.fullFunds + e.selfFunds}"></c:set>
								<tr>
									<td>${e.civil } / <c:if test="${total1 == 0 }">0.00%</td></c:if><c:if test="${total1 != 0 }"><fmt:formatNumber value="${e.civil / total1}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${e.referMamager } / <c:if test="${total1 == 0 }">0.00%</td></c:if><c:if test="${total1 != 0 }"><fmt:formatNumber value="${e.referMamager / total1}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${e.fullFunds } / <c:if test="${total1 == 0 }">0.00%</td></c:if><c:if test="${total1 != 0 }"><fmt:formatNumber value="${e.fullFunds / total1}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${e.selfFunds } / <c:if test="${total1 == 0 }">0.00%</td></c:if><c:if test="${total1 != 0 }"><fmt:formatNumber value="${e.selfFunds / total1}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${total1 }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="split_line"></div>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						领导职数
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" align="center" width="10%">局长(百分比)</td>
								<td class="tableHeader" align="center" width="20%">副局长(百分比)</td>
								<td class="tableHeader" align="center" width="20%">巡视员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">副巡视员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">处（室、科）领导职数(百分比)</td>
								<td class="tableHeader" align="center" width="10%">合计</td>
							</tr>
							<c:forEach items="${leaderStaffList }" var="l" varStatus="lStatus">
								<c:set var="total2" value="${l.secretary + l.deputySecretary + l.inspector + l.deputyInspector + l.deptStaff}"></c:set>
								<tr>
									<td>${l.secretary } / <c:if test="${total2 == 0 }">0.00%</td></c:if><c:if test="${total2 != 0 }"><fmt:formatNumber value="${l.secretary / total2}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${l.deputySecretary } / <c:if test="${total2 == 0 }">0.00%</td></c:if><c:if test="${total2 != 0 }"><fmt:formatNumber value="${l.deputySecretary / total2}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${l.inspector } / <c:if test="${total2 == 0 }">0.00%</td></c:if><c:if test="${total2 != 0 }"><fmt:formatNumber value="${l.inspector / total2}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${l.deputyInspector } / <c:if test="${total2 == 0 }">0.00%</td></c:if><c:if test="${total2 != 0 }"><fmt:formatNumber value="${l.deputyInspector / total2}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${l.deptStaff } / <c:if test="${total2 == 0 }">0.00%</td></c:if><c:if test="${total2 != 0 }"><fmt:formatNumber value="${l.deptStaff / total2}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${total2 }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
			<div class="split_line"></div>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						聘用人员
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" align="center" width="20%">领导岗位任职人员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">专业技术人员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">工勤人员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">其他人员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">合计</td>
							</tr>
							<c:forEach items="${employPersonList }" var="e" varStatus="eStatus">
								<c:set var="total3" value="${e.managerStaff + e.specialtyStaff + e.workStaff + e.other}"></c:set>
								<tr>
									<td>${e.managerStaff } / <c:if test="${total3 == 0 }">0.00%</td></c:if><c:if test="${total3 != 0 }"><fmt:formatNumber value="${e.managerStaff / total3}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${e.specialtyStaff } / <c:if test="${total3 == 0 }">0.00%</td></c:if><c:if test="${total3 != 0 }"><fmt:formatNumber value="${e.specialtyStaff / total3}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${e.workStaff } / <c:if test="${total3 == 0 }">0.00%</td></c:if><c:if test="${total3 != 0 }"><fmt:formatNumber value="${e.workStaff / total3}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${e.other } / <c:if test="${total3 == 0 }">0.00%</td></c:if><c:if test="${total3 != 0 }"><fmt:formatNumber value="${e.other / total3}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
									<td>${total3 }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>