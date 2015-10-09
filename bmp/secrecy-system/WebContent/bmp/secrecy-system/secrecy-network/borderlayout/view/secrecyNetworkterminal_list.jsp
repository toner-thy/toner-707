<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/district" prefix="dis"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密网络终端列表</title>

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
				});
			});

			function doExport(action){
				var frm = window.document.getElementById("queryForm");
				var oldAction = frm.action;
				frm.action = action;
				frm.submit();
				frm.action = oldAction;
			}
		</script>
	</head>

	<body>
		<c:choose>
			<c:when test="${not empty zhtj and zhtj eq '1' }">
				<div class="button_bar">
					<div class="left">
						<div class="pop_button_bar">
							<a class="pop_button" href="${ctx}/platform/stat/statFramework_organDetail.action?organId=${organ.organId}" id="dataflagfanhui" ><span>返回</span></a>
						</div>
					</div>
					<div class="right"></div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="button_bar">
					<div class="left">
						<div class="pop_button_bar">
							<ap:operationbutton/>
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
			</c:otherwise>
		</c:choose>
		<!-- 公共头部 -->
		<div class="body_content" >
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密网络简介" ctx="${ctx}" icoPath="/bmp/secrecy-system/secrecy-network/borderlayout/skin/blue/img/list_cpIco_zd.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','涉密网络简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','涉密网络查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密网络终端
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_networkterminal"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form action="<s:url action="secrecyNetworkterminal_list.action" namespace="/bmp/secrecynetworkterminal"/>" method="post" id="queryForm" theme="simple">
						<input type="hidden" id="checkLower" name="checkLower" value="${checkLower}" />
						<input type="hidden" id="fromQuery" name="fromQuery" value="${fromQuery}" />
						<input type="hidden" id="district.districtCode" name="district.districtCode" value="${district.districtCode}" />
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">涉密网络名称：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyNetworkterminal.secrecyNetwork.name" name="secrecyNetworkterminal.secrecyNetwork.name" value="${secrecyNetworkterminal.secrecyNetwork.name}" />
								</td>
								<td class="tbLable fr">责任人：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyNetworkterminal.dutyPerson.name" name="secrecyNetworkterminal.dutyPerson.name" value="${secrecyNetworkterminal.dutyPerson.name}" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">网络密级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyNetworkterminal.secrecyLevel" name="secrecyNetworkterminal.secrecyLevel"
									 title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyNetworkterminal.secrecyLevel}"></dictionary:select>
								</td>
								<td class="tbLable fr">部门名称：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyNetworkterminal.department.departmentName" name="secrecyNetworkterminal.department.departmentName" value="${secrecyNetworkterminal.department.departmentName}" />
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
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<c:if test="${fromQuery ne '1'}">
							涉密网络终端列表
						</c:if>
						<c:if test="${fromQuery eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${checkLower ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 涉密网络终端列表
							</c:if>
							<c:if test="${checkLower eq '1'}">
								${district.districtName} - 涉密网络终端列表
							</c:if>
						</c:if>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyNetworkterminalList.size>0">
						<ec:table items="secrecyNetworkterminalList" var="secrecyNetworkterminal" tableId="secrecyNetworkterminalTable" border="0"
							action="${ctx}/bmp/secrecynetworkterminal/secrecyNetworkterminal_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secrecynetworkterminalId" alias="secrecyNetworkId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="secrecyNetwork.name" title="涉密网络<br/>名称" cell="text" alias="size=10" width="10%"/>
								<ec:column property="dutyPerson.name" title="责任人" cell="text" alias="size=3" width="5%"></ec:column>
								<ec:column property="null" title="密级" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyNetworkterminal.secrecyLevel }"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="机型" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="computer_type" optionValue="${secrecyNetworkterminal.secrecyComputer.computerType }"></dictionary:text>
								</ec:column>
								<ec:column property="secrecyComputer.computerNo" title="设备编号" width="5%"></ec:column>
								<ec:column property="secrecyComputer.diskSeq" title="硬盘序列号" width="5%"></ec:column>
								<ec:column property="ipAddress" title="IP地址" width="5%"></ec:column>
								<ec:column property="macAddress" title="MAC地址" width="5%"></ec:column>
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
								<%-- <ec:column property="null"  width="5%" title="详 情">
									<a href="###" onclick="doDetail('${secrecyNetworkterminal.secrecynetworkterminalId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" alt ="详情" >
									</a>
								</ec:column> --%>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无数据"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>