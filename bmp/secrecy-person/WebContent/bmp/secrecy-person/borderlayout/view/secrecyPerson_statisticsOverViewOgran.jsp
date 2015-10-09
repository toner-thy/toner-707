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
function doExportCurrentPartOverView(){
		var districtCode;
		if('${param.districtCode}' == ''){
			// 查看本单位的
			districtCode = '${district.districtCode}';
		} else {
			// 查看行政区的
			districtCode = '${param.districtCode}';
		}
		window.location.href = "${ctx}/bmp/secrecyperson/secrecyPerson_exportOverviewOrganCurrent.action?districtCode=" + districtCode + "&countScope=${countScope}" + "&_ts=" + $time();
}
</script>
	<!-- 内容开始 -->
<div style="padding: 5px;">
	<div class="panel">
		<s:if test="#request.districtView.size>0">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">

					</div>
					<div class="panel_btn_bar pop_button_bar">
						<%-- <c:if test="t" > --%>
							<a class="pop_button" href="###" onclick="doExportCurrentPartOverView()"><span>导出</span></a>
						<%-- </c:if> --%>
					</div>
				</div>
				<div class="eXtremeTable" style="overflow: auto;">
					<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="30%">
						<thead>
							<tr height="36px;" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
								<td class="tableHeader" style="font-weight:bolder;color:black;" align="center">单位名称</td>
								<td class="tableHeader" style="font-weight:bolder;color:black;" align="center">核心</td>
								<td class="tableHeader" style="font-weight:bolder;color:black;" align="center">重要</td>
								<td class="tableHeader" style="font-weight:bolder;color:black;" align="center">一般</td>
								<td class="tableHeader" style="font-weight:bolder;color:black;" align="center">合计</td>
							</tr>
						</thead>
						<tbody class="tableBody">
							<c:forEach var="data" items="${districtView }">
							<tr class="odd">
								<td nowrap="nowrap">${data.key }</td>
								<c:set var="sum" value="0" />
									<c:forEach var="num" begin="1" end="3" step="1">
										<c:set var="flag" value="1" />
										<td nowrap="nowrap">
											<c:forEach var="values" items="${data.value }">
												<c:if test="${values.key == num }">
													${values.value }
													<c:set var="sum" value="${sum + values.value }" />
													<c:set var="flag" value="0" />
												</c:if>
											</c:forEach>
											<c:if test="${flag == 1 }" >
												0
											</c:if>
										</td>
								</c:forEach>
								<td>${sum }</td>
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