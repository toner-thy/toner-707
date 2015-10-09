<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    	<title>我的日程</title>

		<!-- 复杂表格CSS支持 -->
  		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- 本页CSS/JS -->
		<s:actionmessage theme="messages"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
  		<script type="text/javascript">
  			$ENV.loader.loadStyleSheet("${ctx}/agenda/borderlayout/skin/blue/css/agenda.css");
	  		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
  			function doAdd(action){
  				var startTime = $('agenda.startTime').value;
  				var endTime = $('agenda.endTime').value;
  				TabUtil.openAsTab({
					url : action+"?agenda.startTime="+startTime+"&agenda.endTime="+endTime,
					title : '我的日程-新增',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在新增的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									document.getElementById('documentForm').submit();
								}
							} else {
								return false;
							}
						}else{
								if(item.content.getContentWindow().needReload){
									document.getElementById('documentForm').submit();
								}
						}
					}
				});
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
					title : '我的日程-编辑',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									$ENV.page.refresh();
								}
							} else {
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

  			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要删除的项。");
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

  			function doDetail(id){
  				$ENV.dialog.open({
					url : '${ctx}/agenda/agenda/agenda_detail.action?id='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height :  0.9,
					title : '我的日程安排详情'
				});
  			}

  			// 显示鼠标翻转图片
			function showButOv(id){
				document.getElementById(id).className = id + "Mv";
			}

			// 显示鼠标正常图片
			function showButOu(id){
				document.getElementById(id).className = id + "Mu";
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
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
    	<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="我的日程简介" ctx="${ctx}" icoPath="/agenda/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','我的日程简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','我的日程搜索');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于我的日程
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="agenda"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form action="<s:url action="agenda_list" namespace="/agenda/agenda"/>" method="post" id="documentForm">
		   				<table width="100%" class="st">
		   					<tr>
								<td class="tbLable fr">
					   				时间选择：
					   			</td>
					   			<td class="tbValue fl">
		   							从&nbsp;<input type="text" id="agenda.startTime" name="agenda.startTime" value="<fmt:formatDate value="${agenda.startTime }" pattern="yyyy-MM-dd"/>" class="Wdate data_input" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'agenda.endTime\')||\'2020-10-01\'}'})" readonly="readonly"/>
					   				&nbsp;到&nbsp;
					   				<input type="text" id="agenda.endTime" name="agenda.endTime" value="<fmt:formatDate value="${agenda.endTime }" pattern="yyyy-MM-dd"/>" class="Wdate data_input" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'agenda.startTime\')}',maxDate:'2020-10-01'})" readonly="readonly"/>
		   						</td>
		   					</tr>
		   					<tr>
								<td colspan="2" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('documentForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('documentForm').reset();"><span>重 置</span></a>
									</div>
				   				</td>
				   			</tr>
				   		</table>
				   	</form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

    		<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						我的日程安排列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.agendaList.size>0">
						<ec:table items="agendaList" var="agenda" tableId="agendaList" border="0"
							action="${ctx}/agenda/agenda/agenda_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="agendaId" alias="agendaId_checkbox" cell="checkbox" headerCell="checkbox" width="35px"/>
								<ec:column property="null" width="45%" title="标 题">
									<div style="font-family: Arial, 'Times New Roman' !important;">
										${agenda.agendaTitle}
									</div>
								</ec:column>
								<ec:column property="null" width="45%" title="日程时间">
									<div style="font-family: Arial, 'Times New Roman' !important;">
										<s:date name="#attr.agenda.startTime" format="MM/dd HH:mm"/> ~ <s:date name="#attr.agenda.endTime" format="MM/dd HH:mm"/>
									</div>
								</ec:column>
								<ec:column property="null"  width="5%" title="详 情">
									<a href="javascript:doDetail('${agenda.agendaId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" alt ="详情" >
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无日程安排，请点击【新增】按钮添加。"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>
