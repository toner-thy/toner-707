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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部门统计(保密局)-统计要害部门行政区内  各个单位的明细</title>

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

			//查看明细
			function doDetail(orgId){

				$ENV.dialog.open({
					url : "${contextPath}/wc/sectionCount.component?organId=" + orgId + "&_ts="+new Date().getTime(),
					//url :"${ctx}/wc/section_organ_detail.component",
					width : 0.8,
					height : 0.8,
					title : '要害部门明细'
				});
			}

			//导出
			function doExport1(){
				var frm = $('excel_template_form');
				frm.action = "${ctx}/bmp/keySection/exportExcel_KeySectionCount_OrganDetail.action?districtCode=${districtCode}";
				frm.submit();
			}
		</script>
	</head>

	<body>
		<div style="padding:5px;">
			<div class="panel tMargin" style="overflow:auto;">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">保密要害部门统计</div>
					<div class="panel_btn_bar pop_button_bar">
						<a href="###" id="doExport"  onclick="doExport1()" class="pop_button"><span>导出</span></a>
					</div>
				</div>
				<div class="panel_content">
					<div class="eXtremeTable" >
						<s:if test="#request.countSectionList.size>0">
							<table id="countSectionList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
								<tr>
									<td class="tableHeader" align="center">单位名称</td>
									<td class="tableHeader" align="center">核心</td><!-- 1 -->
									<td class="tableHeader" align="center">重要</td><!-- 2 -->
									<td class="tableHeader" align="center">一般</td><!-- 3 -->
									<td class="tableHeader" align="center">合计</td>
								</tr>
								<c:forEach items="${countSectionList}" var="countSection" varStatus="ifsStatus">
									<tr <c:if test="${ifsStatus.index % 2 ==0}">class="odd"</c:if><c:if test="${ifsStatus.index % 2 != 0}">class="even"</c:if>>
										<c:if test="${fn:length(countSectionList)==ifsStatus.index+1}">
											<td style="font-weight:bold;">${countSection.organ_name}</td>
											<td style="font-weight:bold;">${countSection.a1}</td>
											<td style="font-weight:bold;">${countSection.a2}</td>
											<td style="font-weight:bold;">${countSection.a3}</td>
										</c:if>
										<c:if test="${fn:length(countSectionList)!=ifsStatus.index+1}">
											<td><a href="##" onclick="doDetail('${countSection.organ_id}');">${countSection.organ_name}</a></td>
											<td>${countSection.a1}&nbsp;(${countSection.b1}%)</td>
											<td>${countSection.a2}&nbsp;(${countSection.b2}%)</td>
											<td>${countSection.a3}&nbsp;(${countSection.b3}%)</td>
										</c:if>
										<td <c:if test="${fn:length(countSectionList)==ifsStatus.index+1}">style="font-weight:bold;"</c:if>>${countSection.total}</td>
									</tr>
								</c:forEach>
							</table>
						</s:if>
					</div>
				</div>
			</div>
		</div>
		<s:form action="" name="excel_template_form" id="excel_template_form" theme="simple">
			<input type="hidden" name="districtCode" id="districtCode" value="${districtCode}" />
		</s:form>
	</body>
</html>