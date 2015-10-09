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
<title>计划管理--检查项目选择</title>

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

	// 详 情
	function doDetail() {
		$ENV.dialog.open({
					url : "${ctx}/bmp/playmanager-demo/pCheckProject/pCheckProject_detail.jsp?_ts=" + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '详情'

				});
	}


	// 详 情
	function select() {
		window.close();
	}
</script>
</head>

<body>
	<!-- 公共头部 -->
	<div class="button_bar">
		<div class="left">
			<div class="pop_button_bar">
				<div class="pop_button_bar">
					<a href="###" onclick="select();" class="pop_button"><span>选择</span></a>
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

		<div style="width:100%;">
			<div>
				检查项目：<select>
								<option>要害部门检查</option>
								<option>涉密人员检查</option>
								<option>涉密环境检查</option>
						 </select>
			</div>
		</div>
		<!-- 内容panel开始 -->
		<div class="panel tMargin">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">计划检查项目规则列表</div>
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
									<td class="tableHeader" width="10%">规则名称</td>
									<td class="tableHeader" width="10%">检查时间周期</td>
								</tr>
							</thead>
							<tbody class="tableBody">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td width="5%"><input type="checkbox" class="row_checkbox " /></td>
									<td width="10%">是否填写要害部门信息</td>
									<td width="10%">
										<input type="radio" name="radio_group" id="radio_1" checked="checked"/>
										<label for="radio_1">每月</label>
										<input type="radio" name="radio_group" id="radio_2"/>
										<label for="radio_2">每季度</label>
										<input type="radio" name="radio_group" id="radio_3"/>
										<label for="radio_3">每半年</label>
										<input type="radio" name="radio_group" id="radio_4"/>
										<label for="radio_4">每年</label>
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td width="5%"><input type="checkbox" class="row_checkbox " /></td>
									<td width="10%">要害部门数量</td>
									<td width="10%">
										<input type="radio" name="radio_group1" id="radio_1" checked="checked"/>
										<label for="radio_1">每月</label>
										<input type="radio" name="radio_group1" id="radio_2"/>
										<label for="radio_2">每季度</label>
										<input type="radio" name="radio_group1" id="radio_3"/>
										<label for="radio_3">每半年</label>
										<input type="radio" name="radio_group1" id="radio_4"/>
										<label for="radio_4">每年</label>
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td width="5%"><input type="checkbox" class="row_checkbox " /></td>
									<td width="10%">要害部门XXXXX检查规则</td>
									<td width="10%">
										<input type="radio" name="radio_group2" id="radio_1" checked="checked"/>
										<label for="radio_1">每月</label>
										<input type="radio" name="radio_group2" id="radio_2"/>
										<label for="radio_2">每季度</label>
										<input type="radio" name="radio_group2" id="radio_3"/>
										<label for="radio_3">每半年</label>
										<input type="radio" name="radio_group2" id="radio_4"/>
										<label for="radio_4">每年</label>
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


