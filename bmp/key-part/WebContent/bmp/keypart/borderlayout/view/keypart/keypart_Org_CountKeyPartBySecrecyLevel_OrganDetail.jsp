<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部位统计(保密局)</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

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
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){
				});
			});

			//查看明细
			function doDetail(orgId){
				$ENV.dialog.open({
					url : "${contextPath}/wc/partCount.component?organId=" + orgId + "&_ts="+new Date().getTime(),
					width : 0.8,
					height : 0.8,
					title : '要害部位明细'
				});
			}

			//导出
			function doExport2(){
				var frm = $('excel_template_form');
				frm.action = "${ctx}/bmp/part/keypart_exportExcel_PartCount_OrganDetail.action?districtCode=${districtCode}";
				frm.submit();
			}
		</script>
	</head>

	<body>
	<div style="padding:5px;">
		<!-- 内容panel开始 -->
		<div class="panel tMargin">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">
					保密要害部位统计
				</div>
				<div class="panel_btn_bar pop_button_bar">
					<a href="###" id="doExport"  onclick="doExport2()" class="pop_button"><span>导出</span></a>
				</div>
			</div>
			<div class="panel_content">
				<div class="eXtremeTable" >
					<s:if test="#request.countPartList.size>0">
						<table id="countPartList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
							<tr>
								<td class="tableHeader" align="center">单位名称</td>
								<td class="tableHeader" align="center">核心</td><!-- 1 -->
								<td class="tableHeader" align="center">重要</td><!-- 2 -->
								<td class="tableHeader" align="center">一般</td><!-- 3 -->
								<td class="tableHeader" align="center">合计</td>
							</tr>
							<c:forEach items="${countPartList}" var="countPart" varStatus="ifsStatus">
								<tr <c:if test="${ifsStatus.index % 2 ==0}">class="odd"</c:if><c:if test="${ifsStatus.index % 2 != 0}">class="even"</c:if>>

									<c:if test="${fn:length(countPartList)==ifsStatus.index+1}">
										<td style="font-weight:bold;">${countPart.organ_name}</td>
										<td style="font-weight:bold;">${countPart.a1}</td>
										<td style="font-weight:bold;">${countPart.a2}</td>
										<td style="font-weight:bold;">${countPart.a3}</td>
									</c:if>
									<c:if test="${fn:length(countPartList)!=ifsStatus.index+1}">
										<td><a href="##" onclick="doDetail('${countPart.organ_id}');">${countPart.organ_name}</a></td>
										<td>${countPart.a1}&nbsp;(${countPart.b1}%)</td>
										<td>${countPart.a2}&nbsp;(${countPart.b2}%)</td>
										<td>${countPart.a3}&nbsp;(${countPart.b3}%)</td>
									</c:if>

									<td <c:if test="${fn:length(countPartList)==ifsStatus.index+1}">style="font-weight:bold;"</c:if>>
										${countPart.total}
									</td>
								</tr>
							</c:forEach>
						</table>
					</s:if>
				</div>
			</div>
		</div>

		<form action="" name="excel_template_form" id="excel_template_form" enctype="application/x-www-form-urlencoded">
			<input type="hidden" name="districtCode" id="districtCode" value="${districtCode}" />
		</form>
	</div>
	</body>
</html>