<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>接触和知悉绝密级商业秘密文件人员情况</title>

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
		window.location.href = action+"?contactSecretPerson.contactSecretPersonId=" + items[0].value + "&_ts="+ new Date().getTime();
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

	// 详 情
	function doDetail(id) {
		if( id!=null && id!="" ){
				$ENV.dialog.open({
					url : "${ctx}/bmp/contactSecretPerson/contactSecretPerson_detail.action?contactSecretPerson.contactSecretPersonId=" + id + "&_ts=" + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '详情'
				});
		}else{
			alert("系统获取id错误，请重试");
			return;
		}
	}

	//导出
	function doExport(action){
		window.location.href = action+"?_ts="+ new Date().getTime();
	}
</script>
</head>

<body>
	<!-- 公共头部 -->
	<div class="button_bar">
		<div class="left">
			<div class="pop_button_bar">
				<div class="pop_button_bar">
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
					<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','接触和知悉绝密级商业秘密文件人员情况简介');">关 于</div>
					<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','接触和知悉绝密级商业秘密文件人员情况查询');">查 询</div>
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
									<div class="cpMsgTitle">关于接触和知悉绝密级商业秘密文件人员</div>
									<div class="cpMsgContext">
										<cpc:tc ctx="${ctx}" showId="annual_plan_description"> </cpc:tc>
									</div>
									<!-- 上下之间的间隔，可以调节高度 -->
									<div class="cpMsgFg" style="height: 7px; overflow: hidden;"></div>
								</div>
								<div id="cp002"
									style="display: none; width: 100%; padding-bottom: 1px; padding-left: 1px; padding-right: 1px;">
									<form method="post" id="queryform" action="${ctx }/bmp/contactSecretPerson/contactSecretPerson_listQuery.action">
										<input name="checkLower" value="${checkLower}" type="hidden"/>
<input name="district.districtCode" value="${district.districtCode}" type="hidden"/>
										<table class="st" width="100%">
											<tr>
												<td class="tbLable fr">文件名称：</td>
												<td class="tbValue fl">
													<input name="contactSecretPerson.secretFileName" value="${contactSecretPerson.secretFileName }" type="text"/>
												</td>
												<td class="tbLable fr">接触方式：</td>
												<td class="tbValue fl">
													<dictionary:select tableCode="bmp" fieldCode="contactWay" optionValue="${contactSecretPerson.contactWay}" name="contactSecretPerson.contactWay" title="true" titleText="请选择" style="width:50pt;"></dictionary:select>
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
				<div class="panel_title panel_titleListIco">
					<!--判断是否查看下级1查看，0不查看-->
					<c:if test="${checkLower ne '1'}">
						${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 接触和知悉绝密级商业秘密文件人员情况列表
					</c:if>
					<c:if test="${checkLower eq '1'}">
						${district.districtName} - 接触和知悉绝密级商业秘密文件人员情况列表
					</c:if>

				</div>
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
							<ec:column property="contactSecretPersonId" alias="data_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
							<ec:column property="secretFileName" title="文件名称" width="20%"/>
							<ec:column property="dispatchOrgan" title="发文机关" cell="text" alias="size=10" width="10%" />
							<ec:column property="fileNum" title="数量" width="5%" />
							<ec:column property="null" title="类型" width="5%" >
								<dictionary:text tableCode="bmp" fieldCode="contactType" optionValue="${dataRow.contactType }"></dictionary:text>
							</ec:column>
							<ec:column property="null" title="方式" width="5%" >
								<dictionary:text tableCode="bmp" fieldCode="contactWay" optionValue="${dataRow.contactWay }"></dictionary:text>
							</ec:column>
							<ec:column property="contactPersonName" title="人员姓名" cell="text" alias="size=10"/>
							<ec:column property="contactDate" title="接触时间" width="8%" cell="date" format="yyyy-MM-dd" />
							<ec:column property="undertackerNames" title="承办人" cell="text" alias="size=10" width="10%"/>
							<ec:column property="approvedLeaderNames" title="审批领导" cell="text" alias="size=10" />
							<ec:column property="null" title="详 情" width="10%">
								<a href="###" onclick="doDetail('${dataRow.contactSecretPersonId}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
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


