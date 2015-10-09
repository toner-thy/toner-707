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

			function doExportInfrastructure() {
				var districtCode;
				var flag = '${flag}';
				if('${param.districtCode}' == ''){
					// 查看本单位的
					districtCode = '${district.districtCode}';
				} else {
					// 查看行政区的
					districtCode = '${param.districtCode}';
				}
				window.location.href = "${ctx}/secrecyorganization/secrecyofficestat/secrecyOfficeStat_exportInfrastructure.action?districtCode=" + districtCode + "&flag=" + flag + "&_ts=" + $time();
			}
		</script>
	</head>

	<body>
		<div style="padding:5px;">
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">

					</div>
					<div class="panel_btn_bar pop_button_bar">
					<a class="pop_button" href="###" onclick="doExportInfrastructure()"><span>导 出</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" align="center" width="20%">宣传教育基地(百分比)</td>
								<td class="tableHeader" align="center" width="20%">销毁中心(百分比)</td>
								<td class="tableHeader" align="center" width="20%">测评(分)中心(百分比)</td>
								<td class="tableHeader" align="center" width="20%">保密技术科研单位(百分比)</td>
								<td class="tableHeader" align="center" width="15%">其他(百分比)</td>
								<td class="tableHeader" align="center" width="5%">合计</td>
							</tr>
							<tr>
								<c:set var="total" value="${infrastructureMap.secrecy_office_infrastructure_xc + infrastructureMap.secrecy_office_infrastructure_xh + infrastructureMap.secrecy_office_infrastructure_cp + infrastructureMap.secrecy_office_infrastructure_ky + infrastructureMap.secrecy_office_infrastructure_qt}"></c:set>
								<td >${infrastructureMap.secrecy_office_infrastructure_xc} / <c:if test="${total == 0 }">0.00%</td></c:if><c:if test="${total != 0 }"><fmt:formatNumber value="${infrastructureMap.secrecy_office_infrastructure_xc / total}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${infrastructureMap.secrecy_office_infrastructure_xh} / <c:if test="${total == 0 }">0.00%</td></c:if><c:if test="${total != 0 }"><fmt:formatNumber value="${infrastructureMap.secrecy_office_infrastructure_xh / total}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${infrastructureMap.secrecy_office_infrastructure_cp} / <c:if test="${total == 0 }">0.00%</td></c:if><c:if test="${total != 0 }"><fmt:formatNumber value="${infrastructureMap.secrecy_office_infrastructure_cp / total}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${infrastructureMap.secrecy_office_infrastructure_ky} / <c:if test="${total == 0 }">0.00%</td></c:if><c:if test="${total != 0 }"><fmt:formatNumber value="${infrastructureMap.secrecy_office_infrastructure_ky / total}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${infrastructureMap.secrecy_office_infrastructure_qt} / <c:if test="${total == 0 }">0.00%</td></c:if><c:if test="${total != 0 }"><fmt:formatNumber value="${infrastructureMap.secrecy_office_infrastructure_qt / total}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${total}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>