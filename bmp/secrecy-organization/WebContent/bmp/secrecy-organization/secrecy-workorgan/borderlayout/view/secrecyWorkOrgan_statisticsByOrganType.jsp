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
function doExportCurrent(){
		var districtCode;
		if('${param.districtCode}' == ''){
			// 查看本单位的
			districtCode = '${district.districtCode}';
		} else {
			// 查看行政区的
			districtCode = '${param.districtCode}';
		}
		window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyOfficeStat_exportCurrent.action?districtCode=" + districtCode + "&_ts=" + $time();
}

function doExportOrganType(){
		var districtCode;
		if('${param.districtCode}' == ''){
			// 查看本单位的
			districtCode = '${district.districtCode}';
		} else {
			// 查看行政区的
			districtCode = '${param.districtCode}';
		}
		window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyOfficeStat_exportOrganTypeOrgan.action?districtCode=" + districtCode + "&_ts=" + $time();
}

function doExportDetail(){
		var organId = '${param.organId}';
		if( organId==null || organId==""){
			alert("系统错误，请联系维护人员处理。");
			return;
		}
		window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyOfficeStat_exportCurrent.action?organId=" + organId + "&_ts=" + $time();
}

</script>
	<!-- 内容开始 -->
	<div style="padding:5px;">
		<div class="panel">
		<s:if test="#request.organTypeResult.size>0">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">

					</div>
					<div class="panel_btn_bar pop_button_bar">
						<c:if test="${expBtnFlag eq 'current' }">
							<a class="pop_button" href="###" onclick="doExportCurrent()"><span>导出</span></a>
						</c:if>
						<c:if test="${expBtnFlag eq 'classifyOrgan' }">
							<a class="pop_button" href="###" onclick="doExportOrganType()"><span>导出</span></a>
						</c:if>
						<c:if test="${expBtnFlag eq 'detail' }">
							<a class="pop_button" href="###" onclick="doExportDetail()"><span>导出</span></a>
						</c:if>
					</div>
				</div>

				<div class="eXtremeTable" >
					<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="30%">
						<thead>
							<tr>
								<td align="center" style="font-weight:bolder;color:black;">单位名称</td>
								<c:forEach var="dic" items="${organCategoryDictionary }" >
									<td align="center" style="font-weight:bolder;color:black;">
										${dic.optionText }
									</td>
								</c:forEach>
							</tr>
						</thead>
						<tbody class="tableBody">
							<c:forEach var="row" items="${organTypeResult }" varStatus="st">
								<tr class="odd">
									<td align="center" >${ row.key}</td>
									<c:forEach var="dic" items="${organCategoryDictionary }" >
										<td align="center" >
											<c:forEach items="${row.value }" var="v">
												<c:if test="${v.key == dic.optionValue }">
													是
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
