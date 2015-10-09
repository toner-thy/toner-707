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
		<title>信息上报列表页面</title>

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
			// 上报
			function doReport(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择需要上报的信息。");
					return;
				}
				// 只有草稿和已驳回的信息能够报审
				var status = new Array();
				items.each(function(item) {
					var v = $(item.value+"_status").value;
					if (v != 1 && v != 4) {
						status.push(item);
					}
				});
				if (status.length > 0) {
					alert("您勾选的数据中包含非“草稿”和“驳回”状态的信息，只有“草稿”和“驳回状态的信息才能上报”。");
					return;
				}
				if(window.confirm("确定要上报吗？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}
			//删除
			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择需要删除的信息。");
					return;
				}
				var status = new Array();
				items.each(function(item) {
					var v = $(item.value+"_status").value;
					if (v && v != 1) {
						status.push(item);
					}
				});
				if (status.length > 0) {
					alert("您勾选的数据中包含非“草稿”状态的信息，只有“草稿”状态的信息才能删除”。");
					return;
				}
				if(window.confirm("确定要删除吗？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}
			//报审
			function doSubmit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择需要报审的信息。");
					return;
				}
				// 只有草稿和已驳回的信息能够报审
				var status = new Array();
				items.each(function(item) {
					var v = $(item.value+"_status").value;
					if (v != 1 && v != 4) {
						status.push(item);
					}
				});
				if (status.length > 0) {
					alert("您勾选的数据中包含非“草稿”和“驳回”状态的信息，只有“草稿”和“驳回状态的信息才能报审”。");
					return;
				}
				if(window.confirm("确定要报审吗？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}
			//删除
			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择需要删除的信息。");
					return;
				}
				var status = new Array();
				items.each(function(item) {
					var v = $(item.value+"_status").value;
					if (v && v != 1) {
						status.push(item);
					}
				});
				if (status.length > 0) {
					alert("您勾选的数据中包含非“草稿”状态的信息，只有“草稿”状态的信息才能删除”。");
					return;
				}
				if(window.confirm("确定要删除吗？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}
			//编辑
			function doEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择需要编辑的信息。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				// 只有草稿和已驳回的信息能够报审
				var status = new Array();
				items.each(function(item) {
					var v = $(item.value+"_status").value;
					if (v != 1 && v != 4) {
						status.push(item);
					}
				});
				if (status.length > 0) {
					alert("您勾选的数据中包含非“草稿”和“驳回”状态的信息，只有“草稿”和“驳回状态的信息才能编辑”。");
					return;
				}
				TabUtil.openAsTab({
					url : action + "?info.infoId="+items[0].value+"&frameName=" + window.name,
					title : '信息上报-编辑',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									document.getElementById('list_form').submit();
								}
							} else {
								return false;
							}
						}else{
								if(item.content.getContentWindow().needReload){
									document.getElementById('list_form').submit();
								}
						}
						/* if(!item.content.getContentWindow().needReload2){
							window.location.href ="${actionRequestURI}";
						} else{
							window.location.href ="${actionRequestURI}";
						} */
					}
				});
			}
			//查看详情
			function doDetail(infoId){
				var date = new Date();
				var action = "${ctx}/bmp/info/info_detail.action?info.infoId="+infoId+"&date="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '信息上报详情'
				});
			}

			// 日志详情
			function doOperate(infoId) {
				var date = new Date();
				var action = "${ctx}/bmp/info/info_log.action?info.infoId="+infoId+"&date="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '信息日志详情'
				});
			}

			//新增详情
			function doAdd(action){
				TabUtil.openAsTab({
					url : action /* + "?frameName=" + window.name */,
					title : '信息上报-新增',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在新增的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									document.getElementById('list_form').submit();
								}
							} else {
								return false;
							}
						}else{
								if(item.content.getContentWindow().needReload){
									document.getElementById('list_form').submit();
								}
						}
					}
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
			<cp:start defaultTitle="信息上报简介" ctx="${ctx}" icoPath="/bmp/info/borderlayout/skin/blue/img/.gif">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','信息上报简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','信息上报搜索');">查 询</div>
			</cp:start>
				<cp:msg show="false" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于信息上报
					</div>
					<div class="cpMsgContext">
						信息上报功能用于机关单位上报文章信息。
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="true" divId="cp002">
					<s:form action="info_list.action" id="list_form" name="list_form" theme="simple">
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									标题：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" id="info.title" name="info.title" value="${info.title}"/>
								</td>
								<td class="tbLable fr">
									类型：
								</td>
								<td class="tbValue fl" colspan="3">
									<s:select list="#request.infoTypeList" id="info.infoType.infoTypeId" name="info.infoType.infoTypeId"
										value="info.infoType.infoTypeId" headerKey="" headerValue="全部" cssStyle="width:100px;" theme="simple"
										listKey="infoTypeId" listValue="name" >
 									</s:select>
								</td>
								<!-- <td class="tbLable fr">
									上报单位：
								</td>
								<td class="tbValue fl">
									<input type="text" id="" name="" value=""/>
								</td> -->
								<td class="tbLable fr">
									信息状态：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="info_report" fieldCode="info_status" optionValue="${info.status}" id="info.status" name="info.status" title="true" titleText="全部" style="width: 130px;"></dictionary:select>
								</td>
								<td class="tbLable fr">
									上报状态：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="info_report" fieldCode="info_report_status" optionValue="${info.reportType}" id="info.reportType" name="info.reportType" title="true" titleText="全部" style="width: 130px;"></dictionary:select>
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
						信息上报列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.infoList.size>0">
						<ec:table items="infoList" var="info" tableId="infoList" border="0"
							action="${ctx}/bmp/info/info_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="infoId" alias="feedbackId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="title" title="标 题" cell="text" alias="size=18" width="20%"/>
								<ec:column property="infoType.name" title="类 型" width="10%"/>
								<ec:column property="source" title="来 源" cell="text" alias="size=6"  width="10%"/>
								<ec:column property="reportPhone" title="报送人电话" width="15%"/>
								<ec:column property="null" title="信息状态" width="5%">
									<input type="hidden" id="${info.infoId}_status" value="${info.status }"/>
									<dictionary:text tableCode="info_report" fieldCode="info_status" optionValue="${info.status}"/>
								</ec:column>
								<ec:column property="null" title="上报单位" width="10%">
									<c:forEach items="${info.infoWarnSet }" var="infoWarn">
										${infoWarn.organ.organName }
									</c:forEach>
								</ec:column>
								<ec:column property="null" title="上报状态" width="10%">
									<dictionary:text tableCode="info_report" fieldCode="info_report_status" optionValue="${info.reportType}"/>
								</ec:column>
								<ec:column property="createTime" cell="date" format="yyyy-MM-dd HH:mm:ss" title="创建时间" width="25%"/>
								<ec:column property="null" title="详 情" width="5%">
									<a href="###" onclick="doDetail('${info.infoId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="详细信息"/>
									</a>
								</ec:column>
								<ec:column property="null" title="日志" width="5%">
									<a href="###" onclick="doOperate('${info.infoId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="日志详情"/>
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