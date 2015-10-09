<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>保密委员会（保密工作领导小组）年度工作计划</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 复杂表格CSS支持 -->
<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/platform/tag/compositePanel/cp.css" type="text/css" rel="stylesheet" />

<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
<script src="${ctx}/platform/tag/compositePanel/cp.js" type="text/javascript"></script>

<script type="text/javascript">
	$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
	$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
	$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

	$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
	$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
	$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",
			function() {
				$ENV.onDomReady(function() {

				});
			});

	// 新 增
	function doAdd(action) {
		window.location.href = action+"?_ts="+ new Date().getTime();
	}

	// 编 辑
	function doEdit(action) {
		var items = EcTable.getCheckedItems();
		if(items.length!=1) {
			alert("请选择一项记录");
			return;
		}
		window.location.href = action+"?annualPlan.annualPlanId=" + items[0].value + "&_ts="+ new Date().getTime();
	}

	// 删除方法
	function doDel(action) {
		var items = EcTable.getCheckedItems();
		if(items.length==0) {
			alert("请至少选择一项记录");
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

	//引用
	function doQuote(action){
		var items = EcTable.getCheckedItems();
		if(items.length!=1) {
			alert("请选择一项记录");
			return;
		}
		window.location.href = action + "?annualPlan.annualPlanId=" + items[0].value + "&_ts="+ new Date().getTime();
	}

	// 详 情
	function doDetail(id) {
		if( id!=null && id!="" ){
				$ENV.dialog.open({
					url : "${ctx}/bmp/annualPlan/annualPlan_detail.action?annualPlan.annualPlanId=" + id + "&_ts=" + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '详情'
				});
		}else{
			alert("系统获取id错误，请重试");
			return;
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
				 <a	class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
				 <a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
			</div>
		</div>
	</div>

	<div class="body_content">
		<!-- 复合面板开始 -->
		<div class="panel">
			<div id="cpPanel" style="width: 100%;">
				<!-- 切换按钮 -->
				<div class="cpBtnBar">
					<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','年度工作计划简介');">关 于</div>
					<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','年度工作计划查询');">查 询</div>
				</div>
				<!-- 切换面板 -->
				<div id="cpMainDiv" class="cpMainDiv">
					<table align="center" class="content_table" style="width: 100%;">
						<tr>
							<td width="100" valign="top"><img src="##" /></td>
							<td width="1" style="background-color: #ddd;"></td>
							<td width="15"></td>
							<td width="*" valign="top">
								<div id="cp001"
									style="width: 100%; padding-bottom: 1px; padding-left: 1px; padding-right: 1px;">
									<!-- 模块简介 -->
									<div class="cpMsgTitle">关于保密委员会（保密工作领导小组）年度工作计划</div>
									<div class="cpMsgContext">
										<cpc:tc ctx="${ctx}" showId="annual_plan_description"> </cpc:tc>
									</div>
									<!-- 上下之间的间隔，可以调节高度 -->
									<div class="cpMsgFg" style="height: 7px; overflow: hidden;"></div>
								</div>
								<div id="cp002"
									style="display: none; width: 100%; padding-bottom: 1px; padding-left: 1px; padding-right: 1px;">
									<form method="post" id="queryform" action="${ctx }/bmp/annualPlan/annualPlan_list.action">
										<table class="st" width="100%">
											<tr>
												<td class="tbLable fr">年度工作计划标题：</td>
												<td class="tbValue fl"><input name="annualPlan.annualPlanTitle" value="${annualPlan.annualPlanTitle }" type="text"/></td>
												<td class="tbLable fr">年度：</td>
												<td class="tbValue fl">
													<input type="text" name="annualPlan.annualPlanYear" value="${annualPlan.annualPlanYear }" readonly="readonly" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy'})" />
												</td>
											</tr>
											<tr>
												<td colspan="4" class="fc" style="border: 0px;">
													<div class="stBtnBar">
														<a class="pop_button" href="javascript:document.getElementById('queryform').submit();"><span>查	询</span></a> <a class="pop_button"
															href="javascript:document.getElementById('queryform').reset();"><span>重	置</span></a>
													</div>
												</td>
											</tr>
										</table>
									</form>
								</div>
							<td width="15"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<!-- 复合面板结束 -->

		<!-- 内容panel开始 -->
		<div class="panel tMargin">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">年度工作计划列表</div>
				<div class="panel_btn_bar pop_button_bar"></div>
			</div>
			<div class="panel_content panel_content_table">
				<s:if test="#request.dataList.size>0">
				<s:property value="request.dataList.size"/>
					<ec:table items="dataList" var="dataRow" tableId="dataList" border="0"
						action="${actionRequestURI}"
						imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="annualPlanId" alias="annualPlanId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
							<ec:column property="annualPlanTitle" title="年度工作计划标题" width="20%" cell="text" alias="size=25"/>
							<ec:column property="annualPlanYear" title="年度" width="10%" />
							<ec:column property="createTime" title="创建时间" width="" cell="date" format="yyyy-MM-dd" />
							<ec:column property="createPerson.name" title="创建人" width="10%" cell="text" alias="size=18"/>
							<ec:column property="null" title="详 情" width="10%">
								<a href="###" onclick="doDetail('${dataRow.annualPlanId}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
							</ec:column>
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

	<!-- 删除用隐藏表单 -->
	<form id="delete_form" name="delete_form" method="post">
		<input id="deleteIds" name="deleteIds" type="hidden"/>
	</form>

</body>
</html>


