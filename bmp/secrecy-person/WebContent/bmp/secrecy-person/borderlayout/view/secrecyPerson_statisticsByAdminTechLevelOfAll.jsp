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
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<script type="text/javascript">

$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

//导出
function doExportAdminTechDistrict(){
		var districtCode;
		if('${param.districtCode}' == ''){
			// 查看本单位的
			districtCode = '${district.districtCode}';
		} else {
			// 查看行政区的
			districtCode = '${param.districtCode}';
		}
		window.location.href = "${ctx}/bmp/secrecyperson/secrecyPerson_exportAdminTechDistrict.action?districtCode=" + districtCode + "&countScope=${countScope}" + "&_ts=" + $time();
}
</script>
	<!-- 内容开始 -->
		<s:if test="#request.statisticsList_admin.size>0">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">

					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="###" onclick="doExportAdminTechDistrict()"><span>导出</span></a>
					</div>
				</div>

				<div class="eXtremeTable" style="overflow:auto; ">
					<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="30%">
						<thead>
							<tr>
							<td rowspan="3" class="tableHeader" style="font-weight:bolder;color:black;">名称</td>
							</tr>
							<tr>
								<c:forEach var="option" items="${adminLevelOptions }" >
									<td colspan="3" class="tableHeader" style="font-weight:bolder;color:black;">${option.optionText }</td>
								</c:forEach>
								<td colspan="3" class="tableHeader" style="font-weight:bolder;color:black;">合计</td>
							</tr>
							<tr>
								<c:forEach var="leaningLevelMap" items="${adminLevelOptions }" >
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
							<!-- Map<String, List<Map<String, Map<Integer, Integer>>>> -->
							<c:forEach var="row" items="${statisticsList_admin }" varStatus="rowStatu">
							<tr class="odd">
								<td align="center" nowrap="nowrap">${row.key }</td>
								<c:set var="rowTotal" value="0" />
								<c:forEach var="layer1" items="${row.value }">
									<c:forEach var="layer2" items="${layer1 }" varStatus="layer2Statu">
										<c:forEach var="lastvalue" items="${layer2.value }" varStatus="lastvalueStatu">
											<c:if test="${lastvalue.key == 4  }">
												<c:set var="rowTotal" value="${lastvalue.value }" />
											</c:if>
										</c:forEach>
									</c:forEach>
								</c:forEach>

								<c:forEach var="layer1" items="${row.value }">
									<c:forEach var="layer2" items="${layer1 }" varStatus="layer2Statu">

												<c:forEach var="temp" begin="1" end="3" varStatus="tempNumStatu">
												<td align="center" nowrap="nowrap">
													<c:set var="dividend" value="0" />
													<c:set var="flag" value="1" />
													<c:forEach var="lastvalue" items="${layer2.value }" varStatus="lastvalueStatu">
														<c:if test="${lastvalue.key == tempNumStatu.index }">
															${lastvalue.value }
															<c:set var="dividend" value="${lastvalue.value }" />
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
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
		</s:if>
		<s:if test="#request.statisticsList_tech.size>0">
				<div class="eXtremeTable" style="overflow:auto;" >
					<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="30%">
						<thead>
							<tr>
							<td rowspan="3" class="tableHeader" style="font-weight:bolder;color:black;">名称</td>
							</tr>
							<tr>
								<c:forEach var="option" items="${technicTitleLevelOptions }" >
									<td colspan="3" class="tableHeader" style="font-weight:bolder;color:black;">${option.optionText }</td>
								</c:forEach>
								<td colspan="3" class="tableHeader" style="font-weight:bolder;color:black;">合计</td>
							</tr>
							<tr>
								<c:forEach var="leaningLevelMap" items="${technicTitleLevelOptions }" >
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
							<!-- Map<String, List<Map<String, Map<Integer, Integer>>>> -->
							<c:forEach var="row" items="${statisticsList_tech }" varStatus="rowStatu">
							<tr class="odd">
								<td align="center" nowrap="nowrap">${row.key }</td>
								<c:set var="rowTotal" value="0" />
								<c:forEach var="layer1" items="${row.value }">
									<c:forEach var="layer2" items="${layer1 }" varStatus="layer2Statu">
										<c:forEach var="lastvalue" items="${layer2.value }" varStatus="lastvalueStatu">
											<c:if test="${lastvalue.key == 4  }">
												<c:set var="rowTotal" value="${lastvalue.value }" />
											</c:if>
										</c:forEach>
									</c:forEach>
								</c:forEach>

								<c:forEach var="layer1" items="${row.value }">
									<c:forEach var="layer2" items="${layer1 }" varStatus="layer2Statu">

												<c:forEach var="temp" begin="1" end="3" varStatus="tempNumStatu">
												<td align="center" nowrap="nowrap">
													<c:set var="dividend" value="0" />
													<c:set var="flag" value="1" />
													<c:forEach var="lastvalue" items="${layer2.value }" varStatus="lastvalueStatu">
														<c:if test="${lastvalue.key == tempNumStatu.index }">
															${lastvalue.value }
															<c:set var="dividend" value="${lastvalue.value }" />
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
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
		</s:if>
		<s:if test="#request.statisticsList_admin.size==0 && #request.statisticsList_tech.size==0">
			<u:noData text="暂无数据"/>
		</s:if>
	<!-- 内容结束 -->
