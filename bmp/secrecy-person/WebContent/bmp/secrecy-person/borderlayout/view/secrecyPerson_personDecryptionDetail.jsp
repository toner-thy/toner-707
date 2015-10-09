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
				<!-- <div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密人员脱密信息
					</div>
					<div class="panel_btn_bar pop_button_bar">
						右侧按钮
					</div>
				</div> -->
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyPersonDecryptionList.size>0">
						<table class="content_table" width="100%">
							<tr>
								<td colspan="14" height="45" class="formTitle" align="center">
									<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
										脱密信息
									</div>
								</td>
							</tr>
							<c:forEach items="${secrecyPersonDecryptionList }" var="clear">
								<tr>
									<td width="5%" align="right" nowrap="nowrap">
										原密级：
									</td>
									<td width="5%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${clear.secrecyPersonId.secrecyPersonLevel}"></dictionary:text>
									</td>
									<td width="8%" align="right">
										脱密类型：
									</td>
									<td width="8%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="clear_secrecy_type" optionValue="${clear.decryptionType }"></dictionary:text>
									</td>
									<td width="8%" align="right">
										脱密期限：
									</td>
									<td width="4%" align="left">
										${clear.decryptionLimit }
										<dictionary:text tableCode="bmp" fieldCode="decryption_limit_type" optionValue="${clear.limitMeasure }"></dictionary:text>
									</td>
									<td width="10%" align="right">
										脱密时间起：
									</td>
									<td width="8%" align="left">
										<fmt:formatDate value="${clear.decryptionStart }" pattern="yyyy-MM-dd"/>
									</td>
									<td width="10%" align="right">
										脱密时间止：
									</td>
									<td width="8%" align="left">
										<fmt:formatDate value="${clear.decryptionEnd }" pattern="yyyy-MM-dd"/>
									</td>
									<td width="10%" align="right">
										解除原因：
									</td>
									<td width="40%" align="left">
										<div title="${clear.decryptionReason}">
											<c:if test="${fn:length(clear.decryptionReason) <= 30}">${clear.decryptionReason}</c:if>
											<c:if test="${fn:length(clear.decryptionReason) > 30}">${fn:substring(clear.decryptionReason,0,30)}...</c:if>
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
