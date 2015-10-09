<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary"
	prefix="dictionary"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 直辖单位责任人统计 -->
<link href="${ctx}/platform/css/public/table/complexTbSustain.css"
	type="text/css" rel="stylesheet" />

<script src="${ctx}/resources/js/environment/environment.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$ENV.loader
			.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
	$ENV.loader
			.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
	$ENV.loader
			.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
	$ENV.loader
			.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
	$ENV.loader
			.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

	$ENV.loader
			.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
	$ENV.loader
			.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
	$ENV.loader
			.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
	$ENV.loader
			.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
	$ENV.loader
			.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
	$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",
			function() {
				$ENV.onDomReady(function() {

				});
			});

	//导出
	function dobendanweiExportDutyPerson() {
		window.location.href = "${ctx}/bmp/discloseSecrecy/bendanwei_exportExcel_dutyPerson.action?organId=${organId}&&className=${className}";
	}
	//导出
	function dozhixiaExportDutyPerson() {
		window.location.href = "${ctx}/bmp/discloseSecrecy/baomiju_exportExcel_dutyPerson.action?dealResult=${dealResult}&&className=${className}&&districtCode=${districtCode}&&districtName=${districtName}&&zhixia=${zhixia}";
	}
</script>
<div style="width: 99%; padding: 5px;">



	<div class="panel tMargin">
		<div class="panel_header">
			<div class="panel_title panel_titleListIco">行政级别统计</div>
			<div class="panel_btn_bar pop_button_bar">
				<s:if
					test="#request.districtCode!=null&&#request.districtName!=null">
					<a href="###" id="doExport" onclick="dozhixiaExportDutyPerson()"
						class="pop_button"><span>导出</span></a>
				</s:if>
				<s:else>
					<a href="###" id="doExport" onclick="dobendanweiExportDutyPerson()"
						class="pop_button"><span>导出</span></a>
				</s:else>
			</div>
		</div>

		<div class="panel_content panel_content_table">
			<div class="eXtremeTable">
				<s:if test="#request.listShowManageLevel.size>0">
					<table id="countSectionList_table" border="0" cellspacing="0"
						cellpadding="0" class="tableRegion" width="100%">
						<c:forEach items="${listShowManageLevel}" var="countSection"
							varStatus="ifsStatus">
							<tr class="${ifsStatus.index % 2 ==0 ?'even':'odd'}">
								<c:forEach items="${countSection}" var="value"
									varStatus="ifsStatus2">
									<c:if test="${ifsStatus.index==0}">
										<td class="tableHeader" align="center">${value}</td>
									</c:if>
									<c:if test="${ifsStatus.index!=0}"><td>${value}</td></c:if>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
				</s:if>
			</div>
		</div>

	</div>
	<div class="split_line"></div>
	<div class="panel tMargin">
		<div class="panel_header">
			<div class="panel_title panel_titleListIco">政治面貌统计</div>
		</div>

		<div class="panel_content panel_content_table">
			<div class="eXtremeTable">
				<s:if test="#request.listShowPolitical.size>0">
					<table id="countSectionList_table" border="0" cellspacing="0"
						cellpadding="0" class="tableRegion" width="100%">
						<c:forEach items="${listShowPolitical}" var="countSection"
							varStatus="ifsStatus">
							<tr class="${ifsStatus.index % 2 ==0 ?'even':'odd'}">
								<c:forEach items="${countSection}" var="value"
									varStatus="ifsStatus2">
									<c:if test="${ifsStatus.index==0}">
										<td class="tableHeader" align="center">${value}</td>
									</c:if>
									<c:if test="${ifsStatus.index!=0}"><td>${value}</td></c:if>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
				</s:if>
			</div>
		</div>

	</div>
</div>

