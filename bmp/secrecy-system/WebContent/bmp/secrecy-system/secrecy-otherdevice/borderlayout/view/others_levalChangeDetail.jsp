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
						密级变更详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyOthersChangeList.size>0">
						<table class="content_table" width="100%" >
							<!-- <tr>
								<td colspan="10" height="45" class="formTitle" align="center">
									<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
										密级变更信息
									</div>
								</td>
							</tr> -->
							<c:forEach items="${secrecyOthersChangeList }" var="changes">
								<tr>
									<td width="8%" align="right" nowrap="nowrap">
										原密级：
									</td>
									<td width="6%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${changes.beforeLevel}"></dictionary:text>
									</td>
									<td width="8%" align="right">
										现密级：
									</td>
									<td width="5%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${changes.afterLevel}"></dictionary:text>
									</td>
									<td width="10%" align="right">
										变更时间：
									</td>
									<td width="10%" align="left">
										<fmt:formatDate value="${changes.changeDate }" pattern="yyyy-MM-dd"/>
									</td>
									<td width="10%" align="right">
										保密期限变更：
									</td>
									<td width="10%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${changes.changeTimeState }"></dictionary:text>
									</td>
									<td width="10%" align="right">
										变更原因：
									</td>
									<td width="30%" align="left">
										<div title="${changes.changeReason}">
											<c:if test="${fn:length(changes.changeReason) <= 25}">${changes.changeReason}</c:if>
											<c:if test="${fn:length(changes.changeReason) > 25}">${fn:substring(changes.changeReason,0,25)}...</c:if>
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
