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
<%@ taglib tagdir="/WEB-INF/tags/yearStat" prefix="yearStat" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>商业秘密统计(行政区划)</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

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

			//导出
			function doExport_secrecycountryitemDistrict(){
				var frm = $('excel_secrecycountryitemDistrict_form');
				frm.action = "${ctx}/bmp/secrecycountryitem/export_CountrySecrecy_District.action";
				frm.submit();
			}

			//回调方法
			function callBackFunction_count_CountrySecrecy_district(beginDate, endDate, dateType){
				var url = "${ctx}/bmp/secrecycountryitem/count_CountrySecrecy_District.action?&districtCode=${param.districtCode}&dateType="+dateType;
				if(beginDate!="") {
					url += "&beginDate="+beginDate+"&endDate="+endDate;
				}
				$('count_CountrySecrecy_district').getParent().load(url);
			}


		</script>
		<style type="text/css">
			.pop_button_bar{}
		</style>
	</head>

	<body>

		<div style="padding:5px;" id="count_CountrySecrecy_district">
			<!-- 年度过滤器 -->
			<yearStat:yearstat_query actionUrl="${ctx}/bmp/secrecycountryitem/count_CountrySecrecy_District.action"
			callBackFunction="callBackFunction_count_CountrySecrecy_district" yearStatProvider="${yearStatProvider }"></yearStat:yearstat_query>


			<div class="panel tMargin" style="overflow-y: auto;">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						行政区划-商业秘密统计
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a href="###" id="doExport"  onclick="doExport_secrecycountryitemDistrict()" class="pop_button"><span>导出</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table" >
					<div class="eXtremeTable" >
						<s:if test="#request.countrySecrecyList.size>0">
							<table id="countrySecrecyList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
								<tr>
									<td class="tableHeader" align="center">商业秘密</td>
									<td class="tableHeader" align="center">绝密</td><!-- 1 -->
									<td class="tableHeader" align="center">机密</td><!-- 2 -->
									<td class="tableHeader" align="center">秘密</td><!-- 3 -->
									<td class="tableHeader" align="center">合计</td>
								</tr>
								<c:forEach items="${countrySecrecyList}" var="countrySecrecy" varStatus="ifsStatus">
									<tr <c:if test="${ifsStatus.index % 2 ==0}">class="odd"</c:if><c:if test="${ifsStatus.index % 2 != 0}">class="even"</c:if>>
										<c:if test="${countrySecrecy.sc_name!='合计'}">
											<td>${countrySecrecy.sc_name}</td>
											<td>${countrySecrecy.a1}&nbsp;/&nbsp;${countrySecrecy.b1}%</td>
											<td>${countrySecrecy.a2}&nbsp;/&nbsp;${countrySecrecy.b2}%</td>
											<td>${countrySecrecy.a3}&nbsp;/&nbsp;${countrySecrecy.b3}%</td>
											<td>${countrySecrecy.total}</td>
										</c:if>
										<c:if test="${countrySecrecy.sc_name=='合计'}">
											<td style="font-weight:bold;">${countrySecrecy.sc_name}</td>
											<td style="font-weight:bold;">${countrySecrecy.a1}</td>
											<td style="font-weight:bold;">${countrySecrecy.a2}</td>
											<td style="font-weight:bold;">${countrySecrecy.a3}</td>
											<td style="font-weight:bold;">${countrySecrecy.total}</td>
										</c:if>
									</tr>
								</c:forEach>
							</table>
						</s:if>
					</div>
				</div>
			</div>

			<form action="" name="excel_secrecycountryitemDistrict_form" id="excel_secrecycountryitemDistrict_form" enctype="application/x-www-form-urlencoded">
				<input type="hidden" name="dateType" id="dateType" value="${yearStatProvider.dateType }">
				<input type="hidden" name="beginDate" id="beginDate" value="${yearStatProvider.beginDate }">
				<input type="hidden" name="endDate" id="endDate" value="${yearStatProvider.endDate }">
				<input type="hidden" name="actionUrl" id="actionUrl" value="${yearStatProvider.url }">
			</form>
		</div>
	</body>
</html>