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

		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");

</script>
		<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						密级解除详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyOthersClearList.size>0">
						<table class="content_table" width="100%">
							<c:forEach items="${secrecyOthersClearList }" var="clear">
								<tr>
									<td width="8%" align="right" nowrap="nowrap">
										原密级：
									</td>
									<td width="5%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${clear.secrecyOthers.secrecyLevel}"></dictionary:text>
									</td>
									<td width="10%" align="right">
										解除类型：
									</td>
									<td width="10%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="clear_secrecy_type" optionValue="${clear.clearType }"></dictionary:text>
									</td>
									<td width="10%" align="right">
										解除时间：
									</td>
									<td width="10%" align="left">
										<fmt:formatDate value="${clear.clearTime }" pattern="yyyy-MM-dd"/>
									</td>
									<td width="10%" align="right">
										解除原因：
									</td>
									<td width="40%" align="left">
										<div title="${clear.cleanReason}">
											<c:if test="${fn:length(clear.cleanReason) <= 30}">${clear.cleanReason}</c:if>
											<c:if test="${fn:length(clear.cleanReason) > 30}">${fn:substring(clear.cleanReason,0,30)}...</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
						</table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
