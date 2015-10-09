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
		<title>涉密涉外活动记录列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<s:actionmessage theme="messages"/>
		<script language="javascript">
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

				});
			});
			 function doAdd(action){
				TabUtil.openAsTab({
					url : action,
					title : '主要涉密涉外活动备案-新增',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
						if(item.content.getContentWindow().needReload){
							document.getElementById('queryform').submit();
						}
					}else {
							return false;
						}
					}else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('queryform').submit();
							}
						}
					}
				});
			 }
			// 重新加载
	  		function reload() {
	  			window.location.href = "<s:url action="externalPidgin_list"  includeParams="false"/>";
	  		}
			function doEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				TabUtil.openAsTab({
					url : action + "?id="+items[0].value,
					title : '主要设秘密涉外活动备案-编辑',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在编辑的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								$ENV.page.refresh();
							}
							}else {
								return false;
							}
						}else{
							if(item.content.getContentWindow().needReload){
								$ENV.page.refresh();
							}
						}
					}
				});
			}

			function doDetail(action,externalPidginId){
				$ENV.dialog.open({
					url : action+'?id='+externalPidginId+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '涉密涉外活动详细记录'
				});
			}

			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}
				if(window.confirm("确定删除吗？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}
			function isCheckedOneClass(ckArr){
				var pkValue = "";
				var count = 0;
				for(var i = 0; i < ckArr.length; i++)
				{
					var e = ckArr[i];
					if(e.name != 'idall' && e.type.toLowerCase() == "checkbox")
					{
						if(e.checked)
							{
							count++;
							pkValue = e.value;
							if(count > 1)
								break;
						}
					}
				}

				if(count == 1) {
					return pkValue;
				}
				else if(count == 0){
					alert(checkFirst);
					return "";
				}
				else if(count > 1){
					alert(onlyOne);
				return "";
				}
			}
			function showStat(){
				window.location.href=("bmp/pages/externalpidgin/showStat.html");
			}

			//上报
			function doReport(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要上报的数据项！");
					return;
				}
				if(window.confirm("确定上报吗？")){
					var ids = "";
					var queryStr = '';
					items.each(function(item){
						ids += item.value + ",";
					});
					queryStr = "externalPidginIds=" + ids;
					var myRequest = new Request({
				    method: 'get',
				    url: action,
				    onSuccess:function(text) {
				    	var obj = eval("("+text+")");
				    	alert(obj.message);
				    	reload();
				    }
				}).send(queryStr +'&time=' + new Date().getTime());
					}
			}
		</script>
	</head>

	<body>
	<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<ap:operationbutton />
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<%-- <a href='/bmp/help/help/help_clientInfo.do?help.helpId=externalPidgin_course' class='pop_button' target='_back'/><span>操作教程</span></a> --%>
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="主要涉密涉外活动备案简介" ctx="${ctx}" icoPath="${ctx}/externalpidgin/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','主要涉密涉外活动备案简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','主要涉密涉外活动备案搜索');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于主要涉密涉外活动备案
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_externalPidgin_list"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

				</cp:msg>
				<cp:search show="false" divId="cp002">
					<s:form action="externalPidgin_list" id="queryform" theme="simple">
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									涉密涉外活动名称：
								</td>
								<td class="tbValue fl">
									<input name="externalPidginDto.eternalPidginName" style="width: 120px;" id="title" type="text" value="${externalPidginDto.eternalPidginName}" />
								</td>
								<td class="tbLable fr" colspan="2">
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									开始时间范围：
								</td>
								<td class="tbValue fl" colspan="3">
									<input name="externalPidginDto.startTime" id="title" type="text" value="${externalPidginDto.startTime}" class="Wdate" onFocus="WdatePicker();" style="width:100px" />
									至<input name="externalPidginDto.endTime" id="title" type="text" value="${externalPidginDto.endTime}" class="Wdate" onFocus="WdatePicker();"  style="width:100px" />
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('queryform').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('queryform').reset();"><span>重 置</span></a>
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
						涉密涉外活动记录列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.externalPidginList.size>0">
						<ec:table items="externalPidginList" var="externalPidgin" tableId="externalPidginList" border="0"
							action="${ctx}/bmp/externalPidgin/externalPidgin_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="externalPidginId" alias="externalPidginId_checkbox" cell="checkbox" headerCell="checkbox"/>
								<ec:column property="eternalPidginName" title="涉密涉外活动名称"/>
								<ec:column property="mainOrgan" title="主办单位"/>
								<ec:column property="null" title="开始时间">
									<div style="font-family: Arial, 'Times New Roman' !important;">
										<s:date name="#attr.externalPidgin.startDate" format="yyyy/MM/dd"/>
									</div>
								</ec:column>
								<ec:column property="null" title="结束时间">
									<div style="font-family: Arial, 'Times New Roman' !important;">
										<s:date name="#attr.externalPidgin.endDate" format="yyyy/MM/dd"/>
									</div>
								</ec:column>
								<ec:column property="null" title="密 级">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_externalpidgin" optionValue="${externalPidgin.secrecyLevel}"/>
								</ec:column>
								<%-- <ec:column property="null" title="上报状态">
									<dictionary:text tableCode="bmp" fieldCode="report_state" optionValue="${externalPidgin.reportState}" />
								</ec:column>
								<ec:column property="null" title="上报时间">
									<c:if test="${externalPidgin.reportTime!=null}">
										<div style="font-family: Arial, 'Times New Roman' !important;">
											<s:date name="#attr.externalPidgin.reportTime" format="yyyy年MM月dd日"/>
										</div>
									</c:if>
									<c:if test="${externalPidgin.reportTime==null}">
										暂 无
									</c:if>
								</ec:column>--%>
								<ec:column property="null" title="显示详情" width="8%">
									<a href="javascript:doDetail('${ctx}/bmp/externalPidgin/externalPidgin_detail.action','${externalPidgin.externalPidginId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
				</div>
			</div>
		</div>
			<!-- 内容panel结束 -->
	</body>
</html>