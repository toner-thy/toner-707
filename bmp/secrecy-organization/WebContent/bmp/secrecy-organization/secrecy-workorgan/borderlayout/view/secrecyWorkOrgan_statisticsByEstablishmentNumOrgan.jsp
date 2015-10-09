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
function doExportEstablishmentNumOrgan(){
		var districtCode;
		if('${param.districtCode}' == ''){
			// 查看本单位的
			districtCode = '${district.districtCode}';
		} else {
			// 查看行政区的
			districtCode = '${param.districtCode}';
		}
		window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyOfficeStat_doExportEstablishmentNumOrgan.action?districtCode=" + districtCode + "&_ts=" + $time();
}
</script>
	<!-- 内容开始 -->
	<div style="padding:5px;">
		<div class="panel">
		<s:if test="#request.resultMaps.size>0">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">

					</div>
					<div class="panel_btn_bar pop_button_bar">
						<c:if test="${expBtnFlag eq 'classifyOrgan' }">
							<a class="pop_button" href="###" onclick="doExportEstablishmentNumOrgan()"><span>导出</span></a>
						</c:if>
					</div>
				</div>
				<div class="eXtremeTable" >
					<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="30%">
						<thead>
							<tr>
								<td align="center" style="font-weight:bolder;color:black;">单位名称</td>
								<c:forEach var="numMap" items="${headMap }" >
									<td align="center" style="font-weight:bolder;color:black;">
										<c:set var="start" value="" />
										<c:set var="end" value="" />
										<c:forEach var="v" items="${numMap }" >
											<c:if test="${v.key eq 'start' }">
												<c:set var="start" value="${v.value }" />
											</c:if>
											<c:if test="${v.key eq 'end' }">
												<c:set var="end" value="${v.value }" />
											</c:if>
										</c:forEach>
										<c:if test="${start==0 or start==null }" >
												小于${end }人
										</c:if>
										<c:if test="${start>0 and not empty end }" >
											${start }人~${end }人
										</c:if>
										<c:if test="${start>0 and empty end }" >
											大于${start }人
										</c:if>
									</td>
								</c:forEach>
							</tr>
						</thead>
						<tbody class="tableBody">
							<c:forEach var="row" items="${resultMaps }" varStatus="st">
								<tr class="odd">
									<td align="center" >${ row.key}</td>
									<c:forEach var="numMap" items="${headMap }" varStatus="st2">
										<td align="center">
											<c:set var="start" value="" />
											<c:set var="end" value="" />
											<c:forEach var="v" items="${numMap }" >
												<c:if test="${v.key eq 'start' }">
													<c:set var="start" value="${v.value }" />
												</c:if>
												<c:if test="${v.key eq 'end' }">
													<c:set var="end" value="${v.value }" />
												</c:if>
											</c:forEach>
											<c:forEach var="cell" items="${row.value }" varStatus="st1">
												<c:if test="${ st2.index == st1.index }" >
													<c:if test="${start==0 or start==null }" >
														<c:if test="${cell.value < end }">
															是
														</c:if>
													</c:if>
													<c:if test="${start>0 and not empty end }" >
														<c:if test="${cell.value > start and cell.value < end }">
															是
														</c:if>
													</c:if>
													<c:if test="${start>0 and empty end }" >
														<c:if test="${cell.value > start}">
															是
														</c:if>
													</c:if>
												</c:if>
											</c:forEach>
										</td>
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
