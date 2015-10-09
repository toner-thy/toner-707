<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密计算机列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){
					<%-- 为点击进行选择的按钮加入事件--%>
					$('btn_select').addEvent('click', function(){
						var value = EcTable.getRadioItem();
						var arg = window.getParams();
						if (arg.select) {
							arg.select({
								"text" : $('name_' + value).value,
								"value" : value
							});
						} else {
							var arg = window.getParams();
							var dc = window.getOpener().document;
							dc.getElementById(arg.hidden).value = value;
							dc.getElementById(arg.text).value = $('name_' + value).value;
						}
						window.close();
					});
					<%-- 选中已经选择的数据 --%>
					window.onDialogReady = function(){
						var arg = window.getParams();
						if (arg.value) {
							EcTable.getAllRadioItems().each(function(item){
								if (item.value == arg.value) item.checked = true;
							});
						}
					};

					$('btn_query').addEvent('click', function(){
						$('query_form').submit();
					});
				});
			});

		</script>
	</head>

	<body>
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" id="btn_select" ><span>选择</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a href="javascript:window.close();" class="pop_button pop_button_close"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div class="body_content" style="overflow: hidden;">
		    <div class="panel">
		    	<div class="panel_header">
		    		涉密计算机查询
		    	</div>
		    	<div class="panel_content" >
		    	<form id="query_form" action="<s:url action="singelSelect" namespace="/bmp/secrecycomputer"/>" method="post"  >
						<table width="100%">
							<tr>
								<td class="tbLable fr">
									姓名：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyComputer.dutyPerson.name" id="secrecyComputer.dutyPerson.name" value="${secrecyComputer.dutyPerson.name}" />
								</td>
								<td colspan="1" class="fl" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="###" id="btn_query"><span>查 询</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
		    	</div>
		    </div>
			<div class="panel_content">
				<!-- 内容panel开始 -->
				<div class="panel tMargin">
					<div class="panel_content panel_content_table">
						<s:if test="#request.secrecyComputerList.size>0">
							<ec:table items="secrecyComputerList" var="s" tableId="secrecyComputerList" border="0"
								action="${ctx}/bmp/secrecycomputer/singelSelect.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<ec:row>
									<ec:column property="secrecycomputerId" alias="选择" cell="radio" width="5%" />
									<ec:column property="null" title="责任人" width="10%">
										${s.dutyPerson.name }
										<input type="hidden" name="name_${s.secrecycomputerId }" id="name_${s.secrecycomputerId }" value="${s.computerNo}" />
									</ec:column>
									<ec:column property="null" title="密级 " width="5%">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${s.secrecyLevel }"></dictionary:text>
									</ec:column>
									<ec:column property="null" title="机型 " width="5%">
										<dictionary:text tableCode="bmp" fieldCode="computer_type" optionValue="${s.computerType }"></dictionary:text>
									</ec:column>
									<ec:column property="computerNo" title="编号" cell="text" alias="size=20" width="10%"/>
									<ec:column property="diskSeq" title="硬盘序列号" cell="text" alias="size=20" width="15%"/>
									<ec:column property="null" title="是否属于要害部门、部位 "  width="15%">
										<dictionary:text tableCode="bmp" fieldCode="is_section_part" optionValue="${s.isbelongKeydepartment}"></dictionary:text>
									</ec:column>
									<ec:column property="null" title="是否安装保密技术防护专用系统（三合一）" width="15%">
										<c:if test="${s.isFanghu eq 1 }">是</c:if>
										<c:if test="${s.isFanghu eq 0 }">否</c:if>
									</ec:column>
									<ec:column property="null" title="是否纳入违规外联监控系统" width="15%">
										<c:if test="${s.isWailian eq 1 }">是</c:if>
										<c:if test="${s.isWailian eq 0 }">否</c:if>
									</ec:column>
								</ec:row>
							</ec:table>
						</s:if>
						<s:else>
							<u:noData text="当前暂无涉密计算机，请点击【新增】按钮添加"/>
						</s:else>
					</div>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>