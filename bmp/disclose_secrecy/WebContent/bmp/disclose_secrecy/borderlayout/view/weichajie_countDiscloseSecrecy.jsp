<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){
				});
			});

			//导出
			function doweichajieExport() {
				window.location.href="${ctx}/bmp/discloseSecrecy/bendanwei_exportExcel_dealResult.action?organId=${organId}&&className=${className}&&dealResult=2";
			}

		</script>
		<style type="text/css">
			.pop_button_bar{}
		</style>



		<div style="padding: 5px;">

			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						案件密级统计
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a href="###" id="doExport"  onclick="doweichajieExport()" class="pop_button"><span>导出</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table" >
					<div class="eXtremeTable" >
						<s:if test="#request.countSectionList.size>0">
							<table id="countSectionList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
								<tr>
									<td class="tableHeader" align="center">泄密案件密级</td>
									<td class="tableHeader" align="center">数量</td>
								</tr>
								<c:forEach items="${countSectionList}" var="countSection" varStatus="ifsStatus">
									<tr class="${ifsStatus.index % 2 ==0 ?'odd':'even'}">
										<td style = " width:50%;font-weight:${fn:length(countSectionList)==ifsStatus.index+1 ? 'bold;':'none;'}">
										${countSection.option_text}</td>
										<td style = "width:50%;font-weight:${fn:length(countSectionList)==ifsStatus.index+1 ? 'bold;':'none;'}">
										${countSection.fcount}</td>
									</tr>
								</c:forEach>
							</table>
						</s:if>
					</div>
				</div>
			</div>
			<s:if test="#request.countCaseKindList.size>0">
			<div class="split_line"></div>
 			 <div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						案件性质统计
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >

							<table id="countPartList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
								<tr>
									<td class="tableHeader" align="center">案件性质</td>
									<td class="tableHeader" align="center">数量</td>
								</tr>
							<c:forEach items="${countCaseKindList}" var="countCaseKind" varStatus="ifsStatus">
									<tr class="${ifsStatus.index % 2 ==0 ?'odd':'even'}">
										<td style = " width:50%;font-weight:${fn:length(countCaseKindList)==ifsStatus.index+1 ? 'bold;':'none;'}">
										${countCaseKind.option_text}</td>
										<td style = "width:50%;font-weight:${fn:length(countCaseKindList)==ifsStatus.index+1 ? 'bold;':'none;'}">
										${countCaseKind.fcount}</td>
									</tr>
								</c:forEach>
							</table>

					</div>
				</div>
			</div>
			</s:if>
			<div class="split_line"></div><!-- 分隔线 -->
 			 <div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						违规方式统计
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table"><!-- 要害部位 -->
					<div class="eXtremeTable" >
						<s:if test="#request.countCaseTypeList.size>0">
							<table id="countPartList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
								<tr>
									<td class="tableHeader" align="center">违规方式</td>
									<td class="tableHeader" align="center">数量</td>
								</tr>
								<c:forEach items="${countCaseTypeList}" var="countCaseType" varStatus="ifsStatus">
									<tr class="${ifsStatus.index % 2 ==0 ?'odd':'even'}">
										<td style = "text-align:left; width:50%;font-weight:${fn:length(countCaseTypeList)==ifsStatus.index+1 ? 'bold;':'none;'}">${countCaseType.option_text}</td>
										<td style = "width:50%;font-weight:${fn:length(countCaseTypeList)==ifsStatus.index+1 ? 'bold;':'none;'}">${countCaseType.fcount}</td>
									</tr>
								</c:forEach>
							</table>
						</s:if>
					</div>
				</div>
			</div>

			<div class="split_line"></div><!-- 分隔线 -->

 			 <div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						责任单位性质统计
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table"><!-- 要害部位 -->
					<div class="eXtremeTable" >
						<s:if test="#request.countDutyOrganKindList.size>0">
							<table id="countPartList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
								<tr>
									<td class="tableHeader" align="center">责任单位性质</td>
									<td class="tableHeader" align="center">数量</td>
								</tr>
								<c:forEach items="${countDutyOrganKindList}" var="countDutyOrganKind" varStatus="ifsStatus">
									<tr class="${ifsStatus.index % 2 ==0 ?'odd':'even'}">
										<td style = "text-align:left; width:50%;font-weight:${fn:length(countDutyOrganKindList)==ifsStatus.index+1 ? 'bold;':'none;'}">
										${countDutyOrganKind.option_text}</td>
										<td style = "width:50%;font-weight:${fn:length(countDutyOrganKindList)==ifsStatus.index+1 ? 'bold;':'none;'}">
										${countDutyOrganKind.fcount}</td>
									</tr>
								</c:forEach>
							</table>
						</s:if>
					</div>
				</div>
			</div>

		</div>
