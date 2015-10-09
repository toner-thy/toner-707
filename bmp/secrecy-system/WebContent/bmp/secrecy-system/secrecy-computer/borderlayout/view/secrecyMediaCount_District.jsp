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

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

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
			function doExportMobileStorageMediaDistrict(){
				var frm = $('excel_MobileStorageMediaDistrict_form');
				frm.action = "${ctx}/secrecysystem/statistics/export_MobileStorageMediaDistrict.action";
				frm.submit();
			}
		</script>
		<style type="text/css">
			.pop_button_bar{}
		</style>
		<div style="padding:5px">
			<div class="panel" style="overflow-y: auto;">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco" >
						涉密移动存储介质-行政区划
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a href="###" id="doExport"  onclick="doExportMobileStorageMediaDistrict()" class="pop_button"><span>导出</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table" >
					<div class="eXtremeTable" >
						<s:if test="#request.mobileStorageMediaList.size>0">
							<table id="mobileStorageMediaList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
								<tr>
									<td class="tableHeader" align="center">介质类型</td>
									<td class="tableHeader" align="center">绝密</td><!-- 1 -->
									<td class="tableHeader" align="center">机密</td><!-- 2 -->
									<td class="tableHeader" align="center">秘密</td><!-- 3 -->
									<td class="tableHeader" align="center">合计</td>
								</tr>
								<c:forEach items="${mobileStorageMediaList}" var="mobileStorageMedia" varStatus="ifsStatus">
									<tr <c:if test="${ifsStatus.index % 2 ==0}">class="odd"</c:if><c:if test="${ifsStatus.index % 2 != 0}">class="even"</c:if>>
										<c:if test="${mobileStorageMedia.media_name!='合计'}">
											<td>${mobileStorageMedia.media_name}</td>
											<td>${mobileStorageMedia.a1}&nbsp;/&nbsp;${mobileStorageMedia.b1}%</td>
											<td>${mobileStorageMedia.a2}&nbsp;/&nbsp;${mobileStorageMedia.b2}%</td>
											<td>${mobileStorageMedia.a3}&nbsp;/&nbsp;${mobileStorageMedia.b3}%</td>
											<td>${mobileStorageMedia.total}</td>
										</c:if>
										<c:if test="${mobileStorageMedia.media_name=='合计'}">
											<td style="font-weight:bold;">${mobileStorageMedia.media_name}</td>
											<td style="font-weight:bold;">${mobileStorageMedia.a1}</td>
											<td style="font-weight:bold;">${mobileStorageMedia.a2}</td>
											<td style="font-weight:bold;">${mobileStorageMedia.a3}</td>
											<td style="font-weight:bold;">${mobileStorageMedia.total}</td>
										</c:if>
									</tr>
								</c:forEach>
							</table>
						</s:if>
					</div>
				</div>
			</div>

			<form action="" name="excel_MobileStorageMediaDistrict_form" id="excel_MobileStorageMediaDistrict_form" enctype="application/x-www-form-urlencoded">
			</form>
		</div>
