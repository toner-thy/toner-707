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

function doExportCurrentPart(){
		 var districtCode;
		if('${param.districtCode}' == ''){
			// 查看本单位的
			districtCode = '${district.districtCode}';
		} else {
			// 查看行政区的
			districtCode = '${param.districtCode}';
		}
		window.location.href = "${ctx}/secrecysystem/statistics/secrecyNetwork_CurrentOrganExport.action?districtCode=" + districtCode + "&_ts=" + $time();
}


</script>
	<!-- 内容开始 -->
	<div style="padding:5px;">
		<div class="panel">
		<s:if test="#request.networkCountList.size>0">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">

					</div>
					<div class="panel_btn_bar pop_button_bar">
							<a class="pop_button" href="###" onclick="doExportCurrentPart()"><span>导出</span></a>
					</div>
				</div>
				<div class="eXtremeTable" >
					<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="30%">
						<thead>
							<c:forEach var="ncl" items="${networkCountList }" varStatus="st">
								<c:if test="${st.first }">
									<tr>
										<c:forEach var="title" items="${ncl }" varStatus="stsub">
											<td align="center" style="font-weight:bolder;color:black;">${title }</td>
										</c:forEach>
									</tr>
								</c:if>
							</c:forEach>
						</thead>
						<tbody class="tableBody">
							<c:forEach var="ncl" items="${networkCountList }" varStatus="st">
								<c:if test="${not st.first }">
									<tr class="odd">
										<c:forEach var="val" items="${ncl }" varStatus="stsub">
											<td align="center">${val }</td>
										</c:forEach>
									</tr>
								</c:if>
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
