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
//导出
function doExportAgeOrgan(){
		var districtCode;
		if('${param.districtCode}' == ''){
			// 查看本单位的
			districtCode = '${district.districtCode}';
		} else {
			// 查看行政区的
			districtCode = '${param.districtCode}';
		}
		window.location.href = "${ctx}/bmp/secrecyperson/secrecyPerson_exportAgeOrgan.action?districtCode=" + districtCode + "&countScope=${countScope}" + "&_ts=" + $time();
}
</script>


	<!-- 内容开始 -->
	<div style="padding: 5px;">
		<div class="panel">
			<s:if test="#request.ageStatisticsList.size>0">
					<div class="panel_header">
						<div class="panel_title panel_titleListIco">

						</div>
						<div class="panel_btn_bar pop_button_bar">
							<a class="pop_button" href="###" onclick="doExportAgeOrgan()"><span>导出</span></a>
						</div>
					</div>
					<div class="eXtremeTable" style="overflow: auto;">
						<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="30%">
							<thead>
								<tr>
								<td rowspan="3" class="tableHeader" style="font-weight:bolder;color:black;">名称</td>
								</tr>
								<tr>
									<c:forEach var="ageMap" items="${ageRange }" >
										<c:set var="start" value="" />
										<c:set var="end" value="" />
										<c:forEach var="age" items="${ageMap }" >
											<c:if test="${age.key == 'start' }" >
												<c:set var="start" value="${age.value }" />
											</c:if>
											<c:if test="${age.key == 'end' }" >
												<c:set var="end" value="${age.value }" />
											</c:if>
										</c:forEach>
										<c:set var="title" value="" />
										<c:if test="${empty start or start == 0 }" >
											<c:set var="title" value="小于${end }岁" />
										</c:if>
										<c:if test="${not empty start and start!=0 and not empty end }" >
											<c:set var="title" value="${start }岁~${end }岁" />
										</c:if>
										<c:if test="${not empty start and empty end }" >
											<c:set var="title" value="大于${start }岁" />
										</c:if>
										<td colspan="3" class="tableHeader" style="font-weight:bolder;color:black;">${title }</td>
									</c:forEach>
									<td colspan="3" class="tableHeader" style="font-weight:bolder;color:black;">合计</td>
								</tr>
								<tr>
									<c:forEach var="ageMap" items="${ageRange }" >
										<td colspan="1" class="tableHeader" style="font-weight:bolder;color:black;">核心<br />涉密人员</td>
										<td colspan="1" class="tableHeader" style="font-weight:bolder;color:black;">重要<br />涉密人员</td>
										<td colspan="1" class="tableHeader" style="font-weight:bolder;color:black;">一般<br />涉密人员</td>
									</c:forEach>
										<td colspan="1" class="tableHeader" style="font-weight:bolder;color:black;">核心<br />涉密人员</td>
										<td colspan="1" class="tableHeader" style="font-weight:bolder;color:black;">重要<br />涉密人员</td>
										<td colspan="1" class="tableHeader" style="font-weight:bolder;color:black;">一般<br />涉密人员</td>
								</tr>
							</thead>
							<tbody class="tableBody">
								<c:forEach var="row" items="${ageStatisticsList }" varStatus="rowStatu">
								<tr class="odd">
									<td align="center" nowrap="nowrap">${row.key }</td>
									<c:forEach var="layer1" items="${row.value }">
										<c:set var="rowTotal" value="0" />
										<c:forEach var="layer2" items="${layer1 }" varStatus="layer2Statu">
											<c:forEach var="ageMap" items="${layer2.value }" varStatus="ageMapStatu">
												<c:forEach var="ages" items="${ageMap }" >
													<c:if test="${ages.key == 4  }">
														<c:set var="rowTotal" value="${ages.value }" />
													</c:if>
												</c:forEach>
											</c:forEach>
										</c:forEach>
									</c:forEach>
									<c:forEach var="layer1" items="${row.value }">
										<c:forEach var="layer2" items="${layer1 }" varStatus="layer2Statu">
											<c:forEach var="ageMap" items="${layer2.value }" varStatus="ageMapStatu">
												<c:forEach var="temp" begin="1" end="3" varStatus="tempNumStatu">
													<td align="center" nowrap="nowrap">
														<c:set var="dividend" value="0" />
														<c:set var="flag" value="1" />
														<c:forEach var="ages" items="${ageMap }" >
															<c:if test="${ages.key == tempNumStatu.index }">
																${ages.value }
																<c:set var="dividend" value="${ages.value }" />
																<c:set var="flag" value="0" />
															</c:if>
														</c:forEach>
														<c:if test="${flag == 1 }">
															0
														</c:if>
														(
														<c:if test="${empty rowTotal or rowTotal==0 }">
															0.00%
														</c:if>
														<c:if test="${not empty rowTotal and rowTotal!=0 }">
															<c:if test="${flag == 1 }">
																0.00%
															</c:if>
															<c:if test="${flag == 0 }">
																<fmt:formatNumber value="${dividend/rowTotal}" pattern="#.##%" minFractionDigits="2" />
															</c:if>
														</c:if>
														)
													</td>
												</c:forEach>
											</c:forEach>
										</c:forEach>
									</c:forEach>
								</tr>
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
