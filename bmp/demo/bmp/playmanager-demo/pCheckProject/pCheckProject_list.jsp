<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>计划管理--检查项目维护</title>

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
	$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
	$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
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
		window.location.href = action+"?_ts="+ new Date().getTime();
	}

	// 删除方法
	function doDel(action) {
		if( confirm("确定要删除吗？") ){

		}
	}

	function useUnuse(action){
		if( confirm("确定要修改状态吗？") ){

		}
	}
	function manageCheckRule(chekcProjectId){
		TabUtil.openAsTab({
			url : "${ctx}/bmp/playmanager-demo/pCheckRule/pCheckRule_list.jsp?pCheckProject.chekcProjectId="+chekcProjectId,
			title : '检查项目规则-列表',
			onClose : function(tab, item) {

			}
		});
	}

	// 详 情
	function doDetail() {
		$ENV.dialog.open({
					url : "${ctx}/bmp/playmanager-demo/pCheckProject/pCheckProject_detail.jsp?_ts=" + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '详情'
				});
	}
</script>
</head>

<body>
	<!-- 公共头部 -->
	<div class="button_bar">
		<div class="left">
			<div class="pop_button_bar">
				<div class="pop_button_bar">
					<a href="###" onclick="doAdd('${ctx}/bmp/playmanager-demo/pCheckProject/pCheckProject_add.jsp');this.blur();" class="pop_button"><span>新增</span></a>
					<a href="###" onclick="doEdit('${ctx}/bmp/playmanager-demo/pCheckProject/pCheckProject_edit.jsp');this.blur();" class="pop_button"><span>编辑</span></a>
					<a href="###" onclick="useUnuse('');this.blur();" class="pop_button"><span>启用/禁用</span></a>
					<a href="###" onclick="doDel('');this.blur();" class="pop_button"><span>删除</span></a>
				</div>
			</div>
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
					<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','计划管理检查类型简介');">关 于</div>
					<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','计划管理检查类型查询');">查 询</div>
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
									<div class="cpMsgTitle">关于计划检查项目</div>
									<div class="cpMsgContext">
										计划检查项目
									</div>
									<!-- 上下之间的间隔，可以调节高度 -->
									<div class="cpMsgFg" style="height: 7px; overflow: hidden;"></div>
								</div>
								<div id="cp002"
									style="display: none; width: 100%; padding-bottom: 1px; padding-left: 1px; padding-right: 1px;">
									<form method="post" id="queryform">
										<table class="st" width="100%">
											<tr>
												<td class="tbLable fr">检查项目名称：</td>
												<td class="tbValue fl"><input type="text"/></td>
											</tr>
											<tr>
												<td colspan="4" class="fc" style="border: 0px;">
													<div class="stBtnBar">
														<a class="pop_button" href="javascript:void();"><span>查	询</span></a> <a class="pop_button"
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
				<div class="panel_title panel_titleListIco">计划检查项目列表</div>
				<div class="panel_btn_bar pop_button_bar"></div>
			</div>
			<div class="panel_content panel_content_table">
				<form id="keySectionlist"
					action="/bmp/bmp/keySection/keySection_list.action" method="post">
					<div class="eXtremeTable">
						<table id="keySectionlist_table" border="0" cellspacing="0"
							cellpadding="0" class="tableRegion" width="100%">
							<thead>
								<tr>
									<td class="tableHeader" width="5%"><input type="checkbox"
										name="keySectionId_checkbox" id="keySectionId_checkbox"
										class="class_keySectionId_checkbox"
										onclick="EcTable.checkAll('keySectionId_checkbox')" /></td>
									<td class="tableHeader" width="10%">检查项目名称</td>
									<td class="tableHeader" width="10%">启用/禁用</td>
									<td class="tableHeader" width="10%">管理规则</td>
									<td class="tableHeader" width="10%">详情</td>
								</tr>
							</thead>
							<tbody class="tableBody">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td width="5%"><input type="checkbox" class="row_checkbox " /></td>
									<td width="10%">要害部门检查</td>
									<td width="10%">启用</td>
									<td width="10%"><a href="###" onclick="manageCheckRule('00000001')">管理</a></td>
									<td width="10%"><a title="查看详情"
										href="javaScript:doDetail()"> <img
											src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif"
											border="0" title="详细信息" />
										</a>
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td width="5%"><input type="checkbox" class="row_checkbox " /></td>
									<td width="10%">涉密人员检查</td>
									<td width="10%">禁用</td>
									<td width="10%"><a href="###" onclick="manageCheckRule('00000001')">管理</a></td>
									<td width="10%"><a title="查看详情"
										href="javaScript:doDetail()"> <img
											src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif"
											border="0" title="详细信息" />
										</a>
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td width="5%"><input type="checkbox" class="row_checkbox " /></td>
									<td width="10%">涉密环境检查</td>
									<td width="10%">启用</td>
									<td width="10%"><a href="###" onclick="manageCheckRule('00000001')">管理</a></td>
									<td width="10%"><a title="查看详情"
										href="javaScript:doDetail()"> <img
											src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif"
											border="0" title="详细信息" />
										</a>
									</td>
								</tr>							</tbody>
						</table>
						<table class="page_control">
							<tr style="padding: 0px;">
								<td colspan="6">
									<table border="0" cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td class="compactToolbar" align="right">
												<table border="0" cellpadding="1" cellspacing="2">
													<tr>
														<td class="statusBar" align="right">共找到<font
															color=red>1</font>条记录,分<font color=red>1</font>页显示&#160;
														</td>
														<td><span>第一页</span></td>
														<td><span>上一页</span></td>
														<td><span>下一页</span></td>
														<td><span>最后页</span></td>
														<td><img
															src="${ctx}/platform/theme/borderlayout/skin/blue/img/ec/separator.gif"
															style="border: 0" alt="Separator" /></td>
														<td>每页显示<select name="keySectionlist_rd"
															class="page_control"
															onchange="javascript:document.forms.keySectionlist.keySectionlist_crd.value=this.options[this.selectedIndex].value;document.forms.keySectionlist.keySectionlist_p.value='1';document.forms.keySectionlist.setAttribute('action','/bmp/bmp/keySection/keySection_list.action');document.forms.keySectionlist.setAttribute('method','post');document.forms.keySectionlist.submit()">
																<option value="10" selected="selected">10</option>
																<option value="30">30</option>
																<option value="100">100</option>
														</select>条
														</td>
														<td><img
															src="${ctx}/platform/theme/borderlayout/skin/blue/img/ec/separator.gif"
															style="border: 0" alt="Separator" /></td>
														<td class="statusBar">转到<input type="text"
															name="keySectionlist_cpn" class="page_control" value="1"
															size="1"
															onkeypress="javascript111:if(event.keyCode==13){var re = /^[0-9]*[1-9][0-9]*$/;if(this.value < 1 || this.value > 1|| !re.test(this.value)){alert('页码不正确，请重新输入!');return false;}document.forms.keySectionlist.keySectionlist_p.value=this.value;document.forms.keySectionlist.setAttribute('action','/bmp/bmp/keySection/keySection_list.action');document.forms.keySectionlist.setAttribute('method','post');document.forms.keySectionlist.submit()}">页
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
		<!-- 内容panel结束 -->
	</div>

	<!-- 删除用隐藏表单 -->
	<form action="" method="post" id="keySectionDelForm">
		<input type="hidden" name="keySectionIds" id="keySectionIds" />
	</form>

</body>
</html>


