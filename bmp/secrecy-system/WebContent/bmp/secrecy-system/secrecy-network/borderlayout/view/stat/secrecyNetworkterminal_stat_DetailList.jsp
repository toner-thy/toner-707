<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密网络终端列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<s:actionmessage theme="messages"/>
		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){
					new FormCheck('queryform',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

		</script>
	</head>

	<body >

		<div class="panel_content" >

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密网络终端列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table" style="overflow: auto;">
					<s:if test="#request.secrecyNetworkterminalList.size>0">
						<ec:table items="secrecyNetworkterminalList" var="secrecyNetworkterminal" tableId="secrecyNetworkterminalTable" border="0"
							action="${actionRequestURI}"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secrecynetworkterminalId" alias="secrecyNetworkId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="secrecyNetwork.name" title="涉密网络<br/>名称" cell="text" alias="size=4" width="10%"/>
								<ec:column property="dutyPerson.name" title="责任人" cell="text" alias="size=3" width="5%"></ec:column>
								<ec:column property="null" title="密级" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyNetworkterminal.secrecyLevel }"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="机型" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="computer_type" optionValue="${secrecyNetworkterminal.secrecyComputer.computerType }"></dictionary:text>
								</ec:column>
								<ec:column property="secrecyComputer.computerNo" title="设备编号" width="5%"></ec:column>
								<ec:column property="secrecyComputer.diskSeq" title="硬盘序列号" width="5%"></ec:column>
								<ec:column property="ipAddress" title="IP地址" cell="text" alias="size=3" width="5%"></ec:column>
								<ec:column property="macAddress" title="MAC地址" cell="text" alias="size=3" width="5%"></ec:column>
								<ec:column property="null" title="是否属于<br/>要害部门、部位" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="is_section_part" optionValue="${secrecyNetworkterminal.isbelongKeydepartment }"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="是否安装<br/>保密技术防护<br/>专用系统<br/>（三合一）" width="5%">
									<c:if test="${secrecyNetworkterminal.isFanghu eq '1' }">
										是
									</c:if>
									<c:if test="${secrecyNetworkterminal.isFanghu eq '0' }">
										否
									</c:if>
								</ec:column>
								<ec:column property="null" title="是否纳入违规<br/>外联监控系统">
									<c:if test="${secrecyNetworkterminal.isWailian eq '1' }">
										是
									</c:if>
									<c:if test="${secrecyNetworkterminal.isWailian eq '0' }">
										否
									</c:if>
								</ec:column>
								<ec:column property="department.departmentName" title="部门名称" cell="text" alias="size=5" width="5%"></ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无数据"/>
					</s:else>
				</div>			</div>
			<!-- 内容panel结束 -->
		</div>


	</body>
</html>