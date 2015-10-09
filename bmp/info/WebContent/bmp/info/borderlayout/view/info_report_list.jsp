<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>上报信息列表页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
			//查看详情
			function doDetail(infoId){
				var date = new Date();
				var action = "${ctx}/bmp/info/info_info.action?infoWarn.infoWarnId="+infoId+"&date="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '信息上报详情'
				});
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<ap:operationbutton />
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="上报信息简介" ctx="${ctx}" icoPath="/bmp/info/borderlayout/skin/blue/img/.gif">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','上报信息简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','上报信息搜索');">查 询</div>
			</cp:start>
				<cp:msg show="false" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于上报信息
					</div>
					<div class="cpMsgContext">
						上报信息功能用于机关单位查看下级单位上报的文章信息。
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="true" divId="cp002">
					<s:form action="info_report_list.action" id="list_form" name="list_form" theme="simple">
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									标题：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" id="infoWarn.title" name="infoWarn.title" value="${infoWarn.title}"/>
								</td>
								<td class="tbLable fr">
									类型：
								</td>
								<td class="tbValue fl" colspan="3">
									<s:select list="#request.infoTypeList" id="infoWarn.info.infoType.infoTypeId" name="infoWarn.info.infoType.infoTypeId"
										value="infoWarn.info.infoType.infoTypeId" headerKey="" headerValue="全部" cssStyle="width:100px;" theme="simple"
										listKey="infoTypeId" listValue="name" >
 									</s:select>
								</td>
								<td class="tbLable fr">
									上报单位：
								</td>
								<td class="tbValue fl">
									<input type="text" id="infoWarn.reportOrgan" name="infoWarn.reportOrgan" value="${infoWarn.reportOrgan}"/>
								</td>
								<td class="tbLable fr">
									上报时间：
								</td>
								<td class="tbValue fl">
									<input type="text" id="infoWarn.reportBeginTime" name="infoWarn.reportBeginTime" class="Wdate data_input" readonly="readonly" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'infoWarn.reportEndTime\',{d:-1})||\'%y-%M-%d\'}'})" value="${infoWarn.reportBeginTime }"/>
									~
									<input type="text" id="infoWarn.reportEndTime" name="infoWarn.reportEndTime" class="Wdate data_input" readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'infoWarn.reportBeginTime\',{d:1})}',maxDate:'%y-%M-%d'})" value="${infoWarn.reportEndTime }"/>
								</td>
							</tr>
							<tr>
								<td class="fc" colspan="4" style="border: 0px;">
									<div class="btn_query_bar pop_button_bar">
										<a class="pop_button" href="javascript:document.getElementById('list_form').submit();"><span>查 询</span></a>&nbsp;&nbsp;
										<a class="pop_button" href="javascript:document.getElementById('list_form').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						上报信息列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.infoWarnList.size>0">
						<ec:table items="infoWarnList" var="info" tableId="infoReportList" border="0"
							action="${ctx}/bmp/info/info_report_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<%-- <ec:column property="infoWarnId" alias="feedbackId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/> --%>
								<ec:column property="title" title="标 题" cell="text" alias="size=18" width="20%"/>
								<ec:column property="info.infoType.name" title="类 型" width="10%"/>
								<ec:column property="reportOrgan" title="上报单位" width="10%"/>
								<ec:column property="reportTime" cell="date" format="yyyy-MM-dd HH:mm:ss" title="上报时间" width="25%"/>
								<ec:column property="null" title="详 情" width="5%">
									<a href="###" onclick="doDetail('${info.infoWarnId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="详细信息"/>
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>