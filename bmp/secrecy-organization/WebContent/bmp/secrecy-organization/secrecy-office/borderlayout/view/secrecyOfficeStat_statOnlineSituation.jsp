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


		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
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

			function doExportOnline() {
				var districtCode;
				var flag = '${flag}';
				if('${param.districtCode}' == ''){
					districtCode = '${district.districtCode}';
				} else {
					districtCode = '${param.districtCode}';
				}
				window.location.href = "${ctx}/secrecyorganization/secrecyofficestat/secrecyOfficeStat_exportOnlineSituation.action?districtCode=" + districtCode + "&flag=" + flag + "&_ts=" + $time();
			}
		</script>
	</head>

	<body>
		<div style="padding:5px;">
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_list_ico">
						按岗位分组
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="###" onclick="doExportOnline()"><span>导 出</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" align="center" width="20%">行政管理人员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">其他人员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">专业技术人员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">工勤人员(百分比)</td>
								<td class="tableHeader" align="center" width="20%">合计</td>
							</tr>
							<tr>
							<c:set var="total_postType" value="${postTypeMap.sys_person_post_type_a + postTypeMap.sys_person_post_type_o + postTypeMap.sys_person_post_type_t + postTypeMap.sys_person_post_type_w}"></c:set>
								<td >${postTypeMap.sys_person_post_type_a} / <c:if test="${total_postType == 0 }">0.00%</td></c:if><c:if test="${total_postType != 0 }"><fmt:formatNumber value="${postTypeMap.sys_person_post_type_a / total_postType}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${postTypeMap.sys_person_post_type_o} / <c:if test="${total_postType == 0 }">0.00%</td></c:if><c:if test="${total_postType != 0 }"><fmt:formatNumber value="${postTypeMap.sys_person_post_type_o / total_postType}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${postTypeMap.sys_person_post_type_t} / <c:if test="${total_postType == 0 }">0.00%</td></c:if><c:if test="${total_postType != 0 }"><fmt:formatNumber value="${postTypeMap.sys_person_post_type_t / total_postType}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${postTypeMap.sys_person_post_type_w} / <c:if test="${total_postType == 0 }">0.00%</td></c:if><c:if test="${total_postType != 0 }"><fmt:formatNumber value="${postTypeMap.sys_person_post_type_w / total_postType}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${total_postType }</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="split_line"></div>
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_list_ico">
						按年龄结构分组
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" align="center" width="20%">56岁以上(百分比)</td>
								<td class="tableHeader" align="center" width="20%">46-55岁(百分比)</td>
								<td class="tableHeader" align="center" width="20%">36-45岁(百分比)</td>
								<td class="tableHeader" align="center" width="20%">35以下(百分比)</td>
								<td class="tableHeader" align="center" width="20%">合计</td>
							</tr>
							<tr>
							<c:set var="total_year" value="${yearMap.key56 + yearMap.key46 + yearMap.key36 + yearMap.key35}"></c:set>
								<td >${yearMap.key56} / <c:if test="${total_year == 0 }">0.00%</td></c:if><c:if test="${total_year != 0 }"><fmt:formatNumber value="${yearMap.key56 / total_year}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${yearMap.key46} / <c:if test="${total_year == 0 }">0.00%</td></c:if><c:if test="${total_year != 0 }"><fmt:formatNumber value="${yearMap.key46 / total_year}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${yearMap.key36} / <c:if test="${total_year == 0 }">0.00%</td></c:if><c:if test="${total_year != 0 }"><fmt:formatNumber value="${yearMap.key36 / total_year}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${yearMap.key35} / <c:if test="${total_year == 0 }">0.00%</td></c:if><c:if test="${total_year != 0 }"><fmt:formatNumber value="${yearMap.key35 / total_year}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${total_year }</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="split_line"></div>
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_list_ico">
						按专业分组
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" align="center" width="20%">文学(百分比)</td>
								<td class="tableHeader" align="center" width="20%">法律(百分比)</td>
								<td class="tableHeader" align="center" width="20%">管理(百分比)</td>
								<td class="tableHeader" align="center" width="20%">计算机(百分比)</td>
								<td class="tableHeader" align="center" width="15%">其他(百分比)</td>
								<td class="tableHeader" align="center" width="5%">合计</td>
							</tr>
							<tr>
								<!--大专以下：即小学、初中、高中、中专  -->
								<c:set var="total_special" value="${specialMap.sys_person_specialty_code_wx + specialMap.sys_person_specialty_code_fx + specialMap.sys_person_specialty_code_gl + specialMap.sys_person_specialty_code_jsj + specialMap.other}"></c:set>
								<td >${specialMap.sys_person_specialty_code_wx} / <c:if test="${total_special == 0 }">0.00%</td></c:if><c:if test="${total_special != 0 }"><fmt:formatNumber value="${specialMap.sys_person_specialty_code_wx / total_special}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${specialMap.sys_person_specialty_code_fx} / <c:if test="${total_special == 0 }">0.00%</td></c:if><c:if test="${total_special != 0 }"><fmt:formatNumber value="${specialMap.sys_person_specialty_code_fx / total_special}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${specialMap.sys_person_specialty_code_gl} / <c:if test="${total_special == 0 }">0.00%</td></c:if><c:if test="${total_special != 0 }"><fmt:formatNumber value="${specialMap.sys_person_specialty_code_gl / total_special}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${specialMap.sys_person_specialty_code_jsj} / <c:if test="${total_special == 0 }">0.00%</td></c:if><c:if test="${total_special != 0 }"><fmt:formatNumber value="${specialMap.sys_person_specialty_code_jsj / total_special}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${specialMap.other} / <c:if test="${total_special == 0 }">0.00%</td></c:if><c:if test="${total_special != 0 }"><fmt:formatNumber value="${specialMap.other / total_special}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${total_special }</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="split_line"></div>
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_list_ico">
						按学历分组
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="stat_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" align="center" width="20%">博士研究生(百分比)</td>
								<td class="tableHeader" align="center" width="20%">硕士研究生(百分比)</td>
								<td class="tableHeader" align="center" width="20%">大学(百分比)</td>
								<td class="tableHeader" align="center" width="20%">大专以下(百分比)</td>
								<td class="tableHeader" align="center" width="20%">合计</td>
							</tr>
							<tr>
								<!--大学：即本科和大专  -->
								<c:set var="colleage" value="${diplomaMap.sys_person_learning_level_7 + sys_person_learning_level_6}"></c:set>
								<!--大专以下：即小学、初中、高中、中专  -->
								<c:set var="other" value="${diplomaMap.sys_person_learning_level_1 + sys_person_learning_level_2 + sys_person_learning_level_3 + sys_person_learning_level_4 + sys_person_learning_level_5}"></c:set>
								<c:set var="total_diploma" value="${colleage + other + diplomaMap.sys_person_learning_level_9 + diplomaMap.sys_person_learning_level_10}"></c:set>
								<td >${diplomaMap.sys_person_learning_level_9} / <c:if test="${total_diploma == 0 }">0.00%</td></c:if><c:if test="${total_diploma != 0 }"><fmt:formatNumber value="${diplomaMap.sys_person_learning_level_9 / total_diploma}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${diplomaMap.sys_person_learning_level_10} / <c:if test="${total_diploma == 0 }">0.00%</td></c:if><c:if test="${total_diploma != 0 }"><fmt:formatNumber value="${diplomaMap.sys_person_learning_level_10 / total_diploma}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${colleage} / <c:if test="${total_diploma == 0 }">0.00%</td></c:if><c:if test="${total_diploma != 0 }"><fmt:formatNumber value="${colleage / total_diploma}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${other} / <c:if test="${total_diploma == 0 }">0.00%</td></c:if><c:if test="${total_diploma != 0 }"><fmt:formatNumber value="${other / total_diploma}" pattern="#.##%" minFractionDigits="2" /></td></c:if>
								<td >${total_diploma }</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>