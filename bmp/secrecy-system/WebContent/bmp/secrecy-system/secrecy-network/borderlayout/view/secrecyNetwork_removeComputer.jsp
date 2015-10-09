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
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){
					var needReload = ${needReload};
					if (needReload) {
						if (!confirm('移除成功，是否继续移除？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					$("sbm_button").addEvent('click', function(){
						var items = EcTable.getCheckedItems();
						if(items.length==0){
							alert("请选择一项。");
							return;
						}
						var action = "${ctx}/bmp/secrecynetwork/secrecyNetwork_rmcomputer.action";
						$ENV.dialog.open({
							url : action + "?secrecyNetworkterminal.secrecynetworkterminalId="+items[0].value+"&_ts="+new Date().getTime(),
							width : 0.5,
							height : 340,
							title : '移除网络'
						});
						/* if(window.confirm("确定移除？")){
							var ids = "";
							items.each(function(item){
								ids += item.value + ",";
							});
							document.getElementById("secrecyNetworkterminalIds").value = ids;
							document.getElementById("secrecyNetworkterminalDelForm").submit();
						} */
					});
				});
			});
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="###" id="sbm_button"><span>移除</span></a>
					<a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class='pop_button' href='${ctx}/platform/help/help_clientInfo.do?help.helpId=secrecy_committee_help' target='_back'><span>帮 助</span></a>
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<!-- 公共头部 -->
		<div class="body_content" >
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密计算机简介" ctx="${ctx}" icoPath="/bmp/secrecy-system/secrecy-computer/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','涉密计算机简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','涉密计算机查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密计算机
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_network"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form action="<s:url action="secrecyNetwork_removeComputer.action" namespace="/bmp/secrecynetwork"/>" method="post" id="queryForm" theme="simple">
						<input type="hidden" name="secrecyNetwork.secrecyNetworkId" id="secrecyNetwork.secrecyNetworkId" value="${secrecyNetwork.secrecyNetworkId }" />
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">责任人：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyNetworkterminal.secrecyComputer.dutyPerson.name" name="secrecyNetworkterminal.secrecyComputer.dutyPerson.name" value="${secrecyComputer.dutyPerson.name}" />
								</td>
								<td class="tbLable fr">机型：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="computer_type" id="secrecyNetworkterminal.secrecyComputer.computerType" name="secrecyNetworkterminal.secrecyComputer.computerType"
									 title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyNetworkterminal.secrecyComputer.computerType}"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">密级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyNetworkterminal.secrecyComputer.secrecyLevel" name="secrecyNetworkterminal.secrecyComputer.secrecyLevel"
									 title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyNetworkterminal.secrecyComputer.secrecyLevel}"></dictionary:select>
								</td>
								<td class="tbLable fr">是否属于要害部门、部位：</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'是','0':'否'}"
									style="width:135px;"
									theme="simple"
									name="secrecyNetworkterminal.secrecyComputer.isbelongKeydepartment"
									headerKey=""
									headerValue="请选择"
									>
									</s:select>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('queryForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('queryForm').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密计算机列表
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyNetworkterminalList.size>0">
						<ec:table items="secrecyNetworkterminalList" var="secrecyNetworkterminal" tableId="secrecyNetworkterminalListTab" border="0"
							action="${ctx}/bmp/secrecynetwork/secrecyNetwork_removeComputer.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secrecynetworkterminalId" alias="secrecycomputerId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="secrecyComputer.dutyPerson.name" title="责任人" cell="text" alias="size=10" width="10%"/>
								<ec:column property="null" title="密级 " width="5%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyNetworkterminal.secrecyComputer.secrecyLevel }"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="机型 " width="10%">
									<dictionary:text tableCode="bmp" fieldCode="computer_type" optionValue="${secrecyNetworkterminal.secrecyComputer.computerType }"></dictionary:text>
								</ec:column>
								<ec:column property="secrecyComputer.computerNo" title="编号" cell="text" alias="size=20" width="10%"/>
								<ec:column property="secrecyComputer.diskSeq" title="硬盘序列号" cell="text" alias="size=20" width="15%"/>
								<ec:column property="null" title="是否属于要害部门、部位 "  width="15%">
									<dictionary:text tableCode="bmp" fieldCode="is_section_part" optionValue="${secrecyNetworkterminal.secrecyComputer.isbelongKeydepartment}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="是否安装保密技术防护专用系统（三合一）" width="15%">
									<c:if test="${secrecyNetworkterminal.secrecyComputer.isFanghu eq 1 }">是</c:if>
									<c:if test="${secrecyNetworkterminal.secrecyComputer.isFanghu eq 0 }">否</c:if>
								</ec:column>
								<ec:column property="null" title="是否纳入违规外联监控系统" width="15%">
									<c:if test="${secrecyNetworkterminal.secrecyComputer.isWailian eq 1 }">是</c:if>
									<c:if test="${secrecyNetworkterminal.secrecyComputer.isWailian eq 0 }">否</c:if>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无涉密计算机。"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
		<!-- 删除用隐藏表单 -->
		<form action="${ctx }/bmp/secrecynetworkterminal/secrecyNetworkterminal_removeTerminals.action" method="post" id="secrecyNetworkterminalDelForm">
			<input type="hidden" name="secrecyNetworkterminalIds" id="secrecyNetworkterminalIds"/>
		</form>

	</body>
</html>