<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>操作日志</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

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

			function doBatchDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0) {
					alert("请至少选择一项记录！");
					return;
				}
				if(window.confirm('确定删除所选记录吗？')){
					var ids = "";
					items.each(function(item){
						ids += item.value + ",";
					});
					$('deleteIds').value = ids;
					var forms = $('delete_form');
					forms.action = action;
					forms.submit();
				}
			}

			function doDelete(action, id) {
				if(window.confirm('确定删除所选记录吗？')){
					var ids = id + ",";
					$('deleteIds').value = ids;
					var forms = $('delete_form');
					forms.action = action;
					forms.submit();
				}
			}

			//查看明细
			function doDetail(action,id){
				var action = action + "?businessLog.logId="+id+"&date="+new Date().getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.7,
					title : '操作日志详细内容'
				});
			}

			function doRedict(flag){
				// falg 1=今天;2=近7天;3=近1个月；4=近3个月
				var startTime,endTime;
				var currentDate = new Date();
				var currentDateStr = currentDate.getFullYear() + "-" + judge(currentDate.getMonth() + 1) + "-" + judge(currentDate.getDate());
				if(flag == 1){
					startTime = currentDateStr;
					endTime = startTime;
				}
				if(flag == 2){
					var date = parseInt(currentDate.getDate());
					var month = parseInt(currentDate.getMonth()+1);
					var year = parseInt(currentDate.getFullYear());
					if(date < 7){
						if(month == 1){
							year = year - 1;
							month = 12;
						} else {
							month = month - 1;
							date = 31;
						}
					} else {
						date = date - 7;
					}
					startTime = year + "-" + month + "-" + date;
					endTime = currentDateStr;
				}
				if(flag == 3){
					var month = parseInt(currentDate.getMonth()+1);
					var year = parseInt(currentDate.getFullYear());
					if(month == 1){
						year = year - 1;
						month = 12;
					} else {
						month = month - 1;
					}
					startTime = year + "-" + month + "-" + parseInt(currentDate.getDate());
					endTime = currentDateStr;
				}
				if(flag == 4){
					var month = parseInt(currentDate.getMonth()+1);
					var year = parseInt(currentDate.getFullYear());
					if(month-2 < 1){
						year = year - 1;
						month = 12 + (month - 2);
					} else {
						month = month - 2;
					}
					startTime = year + "-" + month + "-" + parseInt(currentDate.getDate());
					endTime = currentDateStr;
				}
				$('startTime').value = startTime;
				$('endTime').value = endTime;
				$('queryForm').submit();
			}

			function judge(intValue){
				intValue = parseInt(intValue);
			    if (intValue < 10) {
			    	intValue = '0' + intValue;
			    }
			    return intValue;
			}

			function doRest(){
				$('startTime').value = '';
				$('endTime').value = '';
				$('businessLog.operateUser.userInfo.name').value = '';
				$('businessLog.operateOrgan.organName').value = '';
				$('businessLog.operateType').value = '';
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<ap:operationbutton></ap:operationbutton>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_search_ico">
						操作日志查询
					</div>
					<div class="panel_titleBtnBar">
					</div>
				</div>
				<div class="panel_content">
					<form action="${ctx}/bmp/businessLog/businessLog_districtList.action" method="post" id="queryForm">
						<table width="100%" class="panel_content_search_form">
							<tr>
								<td style="text-align: right;">
									操作人员：
								</td>
								<td style="text-align: left;">
									<div style="float: left;">
										<input id="businessLog.operateUser.userInfo.name" name="businessLog.operateUser.userInfo.name" value="${businessLog.operateUser.userInfo.name }"/>
									</div>
									<div style="margin-left: 20px;float: left;">
										操作单位：
										<input id="businessLog.operateOrgan.organName" name="businessLog.operateOrgan.organName" value="${businessLog.operateOrgan.organName }" style="width: 400px;"/>
									</div>
								</td>
							</tr>
							<tr>
								<td style="text-align: right;">
									操作时间：
								</td>
								<td style="text-align: left;">
									<div style="float: left;">
										从
										<input id="startTime" name="startTime" value="${startTime}" class="Wdate" type="text" onFocus="var endTime=$dp.$('endTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}'})"/>
										至
										<input id="endTime" name="endTime" value="${endTime}" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})"/>
									</div>
									<div style="margin-left: 50px;padding-top:5px;float: left;">
										<a href="###" onclick="doRedict(1)">今天</a>
										<a href="###" onclick="doRedict(2)">近7天</a>
										<a href="###" onclick="doRedict(3)">近1个月</a>
										<a href="###" onclick="doRedict(4)">近3个月</a>
									</div>
								</td>
							</tr>
							<tr>
								<td style="text-align: right;">
									操作类型：
								</td>
								<td style="text-align: left;">
									<div style="float: left;padding-top:5px;">
										<s:select list="#{'1':'新增','2':'编辑','3':'删除'}"
											style="width:105px;"
											headerValue="全部"
											theme="simple"
											headerKey=""
											name="businessLog.operateType"
											id="businessLog.operateType">
										</s:select>
									</div>
									<div style="margin-left: 20px;float: left;">
										<a class="pop_button" href="javascript:document.getElementById('queryForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:doRest();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
					</div>
				</div>
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						操作日志列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.businessLogList.size>0">
						<ec:table items="businessLogList" var="businessLog" tableId="businessLogList" border="0"
								action="${ctx}/bmp/businessLog/businessLog_districtList.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="logId" alias="logId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="null" title="类型" sortable="true">
									<c:if test="${businessLog.operateType == 1}">新增</c:if>
									<c:if test="${businessLog.operateType == 2}">编辑</c:if>
									<c:if test="${businessLog.operateType == 3}">删除</c:if>
								</ec:column>
								<ec:column property="tableName" title="表名" sortable="true"></ec:column>
								<ec:column property="businessName" title="业务名称" sortable="true"></ec:column>
								<ec:column property="operateUser.userInfo.name" title="操作人员" sortable="true"></ec:column>
								<ec:column property="operateOrgan.organName" title="操作人单位" sortable="true"></ec:column>
								<ec:column property="null" title="操作时间">
									<div style="font-family: Arial, 'Times New Roman' !important;">
										<s:date name="#attr.businessLog.operateTime" format="yyyy-MM-dd HH:mm:ss"/>
									</div>
								</ec:column>
								<ec:column property="null" title="操作">
									<ap:operationlink value="${businessLog.logId }"></ap:operationlink>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无数据。"/>
					</s:else>
					<form id="delete_form" name="delete_form" method="post">
						<input id="deleteIds" name="deleteIds" type="hidden"/>
					</form>

				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>