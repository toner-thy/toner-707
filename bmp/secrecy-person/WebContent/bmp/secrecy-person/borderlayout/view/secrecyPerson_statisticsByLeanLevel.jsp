<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<script type="text/javascript">
$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
</script>
<script type="text/javascript">
function doExportCurrentPartLearnLevel(){
		var districtCode;
		if('${param.districtCode}' == ''){
			// 查看本单位的
			districtCode = '${district.districtCode}';
		} else {
			// 查看行政区的
			districtCode = '${param.districtCode}';
		}
		window.location.href = "${ctx}/bmp/secrecyperson/secrecyPerson_exportCurrentLearnLevel.action?districtCode=" + districtCode + "&countScope=${countScope}" + "&_ts=" + $time();
}


</script>
	<!-- 内容开始 -->
<div style="padding: 5px;">
	<div class="panel">
		<s:if test="#request.statisticsList.size>0">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">

					</div>
					<div class="panel_btn_bar pop_button_bar">
						<c:if test="${expBtnFlag eq 'currentPart' }" >
							<a class="pop_button" href="###" onclick="doExportCurrentPartLearnLevel()"><span>导出</span></a>
						</c:if>
					</div>
				</div>
				<div class="eXtremeTable" style="overflow: auto;">
					<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="30%">
						<thead>
							<tr>
								<td align="center" style="font-weight:bolder;color:black;" rowspan="2">学历</td>
								<td colspan="1" class="tableHeader" style="font-weight:bolder;color:black;">核心涉密人员</td>
								<td colspan="1" class="tableHeader" style="font-weight:bolder;color:black;">重要涉密人员</td>
								<td colspan="1" class="tableHeader" style="font-weight:bolder;color:black;">一般涉密人员</td>
								<td colspan="1" class="tableHeader" style="font-weight:bolder;color:black;">合计</td>
							</tr>
							<tr>
								<td>数量</td>
								<td>数量</td>
								<td>数量</td>
								<td>数量</td>
								<!-- <td>比例</td> -->
							</tr>
						</thead>
						<tbody class="tableBody">
						<c:forEach var="sl" items="${statisticsList }" varStatus="st">
							<c:forEach var="row" items="${sl }">
								<tr class="odd">
									<td align="center" nowrap="nowrap">${ row.key}</td>
									<td align="center" nowrap="nowrap">
										<c:set var="flag" value="1" />
										<c:forEach var="colMap" items="${row.value }" >
											  <c:if test="${colMap.key == 1 }" >
												${colMap.value}
												<c:set var="flag" value="0" />
											 </c:if>
										</c:forEach>
										<c:if test="${flag == 1 }">
											0
									 	</c:if>
									 	(
									    <c:set var="v" value="0"></c:set>
									    <c:set var="total" value="0"></c:set>
										<c:forEach var="colMap" items="${row.value }" >
											 <c:if test="${colMap.key == 1 }" >
												 <c:set var="v" value="${colMap.value}"></c:set>
											 </c:if>
											 <c:if test="${colMap.key == 4 }" >
												<c:set var="total" value="${colMap.value}"></c:set>
											 </c:if>
										</c:forEach>
										<c:if test="${empty total or total==0 }">
											0.00%
										</c:if>
										<c:if test="${not empty total and total!=0 }">
											<fmt:formatNumber value="${v/total}" pattern="#.##%" minFractionDigits="2" />
										</c:if>
										)
									</td>
									<td align="center" nowrap="nowrap">
										<c:set var="flag" value="1" />
										<c:forEach var="colMap" items="${row.value }" >
											  <c:if test="${colMap.key == 2 }" >
												${colMap.value}
												<c:set var="flag" value="0" />
											 </c:if>
										</c:forEach>
										<c:if test="${flag == 1 }">
											0
									 	</c:if>
										(
									    <c:set var="v" value="0"></c:set>
									    <c:set var="total" value="0"></c:set>
										<c:forEach var="colMap" items="${row.value }" >
											 <c:if test="${colMap.key == 2 }" >
												 <c:set var="v" value="${colMap.value}"></c:set>
											 </c:if>
											 <c:if test="${colMap.key == 4 }" >
												<c:set var="total" value="${colMap.value}"></c:set>
											 </c:if>
										</c:forEach>
										<c:if test="${empty total or total==0 }">
											0.00%
										</c:if>
										<c:if test="${not empty total and total!=0 }">
											<fmt:formatNumber value="${v/total}" pattern="#.##%" minFractionDigits="2" />
										</c:if>
										)
									</td>
									<td align="center" nowrap="nowrap">
										<c:set var="flag" value="1" />
										<c:forEach var="colMap" items="${row.value }" >
											  <c:if test="${colMap.key == 3 }" >
												${colMap.value}
												<c:set var="flag" value="0" />
											 </c:if>
										</c:forEach>
										<c:if test="${flag == 1 }">
											0
									 	</c:if>
										(
									    <c:set var="v" value="0"></c:set>
									    <c:set var="total" value="0"></c:set>
										<c:forEach var="colMap" items="${row.value }" >
											 <c:if test="${colMap.key == 3 }" >
												 <c:set var="v" value="${colMap.value}"></c:set>
											 </c:if>
											 <c:if test="${colMap.key == 4 }" >
												<c:set var="total" value="${colMap.value}"></c:set>
											 </c:if>
										</c:forEach>
										<c:if test="${empty total or total==0 }">
											0.00%
										</c:if>
										<c:if test="${not empty total and total!=0 }">
											<fmt:formatNumber value="${v/total}" pattern="#.##%" minFractionDigits="2" />
										</c:if>
										)
									</td>
									<td align="center" nowrap="nowrap">
										<c:forEach var="colMap" items="${row.value }" >
											  <c:if test="${colMap.key == 4 }" >
												${colMap.value}
											 </c:if>
										</c:forEach>
									</td>
									<!-- <td align="center" >
										&nbsp;
									</td> -->
								</tr>
							</c:forEach>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</s:if>
		<s:else>
			<u:noData text="暂无数据"/>
		</s:else>
	</div>
</div>
	<!-- 内容结束 -->
