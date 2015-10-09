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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密计算机列表</title>

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

			// 详 情
			function doDetail(id){
				$ENV.dialog.open({
					title : '涉密计算机详情',
					url : '${ctx}/bmp/secrecycomputer/secrecyComputer_detail.action?secrecyComputer.secrecycomputerId='+id+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
			}

		</script>
	</head>

	<body >

		<div class="panel_content" >

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密计算机列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyComputerList.size>0">
						<ec:table items="secrecyComputerList" var="secrecyComputer" tableId="secrecyComputerList" border="0"
							action="${actionRequestURI}"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secrecycomputerId" alias="secrecycomputerId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="dutyPerson.name" title="责任人" cell="text" alias="size=3" width="10%"/>
								<ec:column property="null" title="密级 " width="6%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyComputer.secrecyLevel }"></dictionary:text>
									<input type="hidden" id="${secrecyComputer.secrecycomputerId}_isNet" name="${secrecyComputer.secrecycomputerId}_isNet" value="${secrecyComputer.isNetTerminal}"/>
								</ec:column>
								<ec:column property="null" title="机型 " width="6%">
									<dictionary:text tableCode="bmp" fieldCode="computer_type" optionValue="${secrecyComputer.computerType }"></dictionary:text>
								</ec:column>
								<ec:column property="computerNo" title="编号" cell="text" alias="size=20" width="10%"/>
								<ec:column property="diskSeq" title="硬盘序列号" cell="text" alias="size=20" width="15%"/>
								<ec:column property="null" title="是否属于</br>要害部门、部位 "  width="10%">
									<dictionary:text tableCode="bmp" fieldCode="is_section_part" optionValue="${secrecyComputer.isbelongKeydepartment}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="是否安装保密技术<br/>防护专用系统（三合一）" width="10%">
									<c:if test="${secrecyComputer.isFanghu eq 1 }">是</c:if>
									<c:if test="${secrecyComputer.isFanghu eq 0 }">否</c:if>
								</ec:column>
								<ec:column property="null" title="是否纳入<br/>违规外联监控系统" width="15%">
									<c:if test="${secrecyComputer.isWailian eq 1 }">是</c:if>
									<c:if test="${secrecyComputer.isWailian eq 0 }">否</c:if>
								</ec:column>
								<ec:column property="null"  width="5%" title="详 情">
									<a href="###" onclick="doDetail('${secrecyComputer.secrecycomputerId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" alt ="详情" >
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>


	</body>
</html>