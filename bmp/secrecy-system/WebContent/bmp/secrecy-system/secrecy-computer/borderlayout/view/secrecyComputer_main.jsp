<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密计算机列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<s:actionmessage theme="messages"/>
		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
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
					new SimpleUI.SimpleTab({
						el : 'main_tab',
						onActive : function(item, content, index) {
						},
						onFirstActive : function(item, content, index) {
							new IFrame({
								src : content.get('url'),
								frameborder : 0,
								styles : {
									width : content.getSize().x,
									height: content.getSize().y
								},
								events : {
									load : function() {
									}
								}
							}).inject(content);
						}
					});
				});
			});

 			// 新 增
			function doAdd(action){
				TabUtil.openAsTab({
				url : action,
				title : '涉密计算机-新增',
				onClose : function(tab, item) {
				if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								window.location.href ="${ctx}/bmp/secrecycomputer/secrecyComputer_main.action";
							}
						}else {
								return false;
							}
					}else{
							if(item.content.getContentWindow().needReload){
								window.location.href ="${ctx}/bmp/secrecycomputer/secrecyComputer_main.action";
							}
						}
					}
				});
			}

			// 编 辑
			function doEdit(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请选择一项");
					return;
				}
 				TabUtil.openAsTab({
					url : action + "?secrecyComputer.secrecycomputerId="+items[0].value,
					title : '涉密计算机-编辑',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (item.content.getContentWindow().confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									window.location.href ="${ctx}/bmp/secrecycomputer/secrecyComputer_main.action";
								}
							} else {
								return false;
							}
						} else{
							if(item.content.getContentWindow().needReload){
								window.location.href ="${ctx}/bmp/secrecycomputer/secrecyComputer_main.action";
							}
						}
					}
				});
			}

			// 删除
			function doDel(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}

				if(window.confirm("确定删除？")){
					var ids = "";
					items.each(function(item){
						ids += item.value + ",";
					});
					document.getElementById("secrecyComputerIds").value = ids;
					document.getElementById("delForm").action = action;
					document.getElementById("delForm").submit();

				}
			}

			//导出
			function doExport(){
				var frm = window.frames[0].document.getElementById("queryForm");
				frm.action = "${ctx}/bmp/secrecycomputer/secrecyComputer_exportData.action";
				frm.submit();
				frm.action = "${ctx}/bmp/secrecycomputer/secrecyComputer_list.action";
			}

			//密级变更
			function doSecrecyChange(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请仅选择一项");
					return;
				}
				$ENV.dialog.open({
					url : action + "?secrecyComputer.secrecycomputerId="+items[0].value+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 340,
					title : '密级变更'
				});
			}

			//密级解除
			function doDecryption(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请仅选择一项");
					return;
				}
				$ENV.dialog.open({
					url : action + "?secrecyComputer.secrecycomputerId="+items[0].value+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 320,
					title : '脱密'
				});
			}

			// 接入网络
			function doAddNet(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}
				var isNet = window.frames[0].document.getElementById(items[0].value + '_isNet').value;
				if( isNet == 1 ){
					alert("选择的计算机已接入网络，不能同时接入多网络。如需接入其他网络，请现移除出当前网络。");
					return;
				}
				TabUtil.openAsTab({
					url : action + "?secrecyComputer.secrecycomputerId="+items[0].value+"&_ts="+new Date().getTime(),
					title : '接入网络',
					onClose : function(tab, item) {
								window.location.href ="${ctx}/bmp/secrecycomputer/secrecyComputer_main.action";
					}
				});
			}

			// IFrame引入进来的面板，重新刷新编辑页面
			function reloadMember() {
				window.location.href="${ctx}/bmp/secrecycomputer/secrecyComputer_main.action";
			}

			//移除网络
			function doRemoveNet(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}
				var isNet = window.frames[0].document.getElementById(items[0].value + '_isNet').value;
				if( isNet == 0 ){
					alert("选择的计算机未接入任何网络，无需移除网络。");
					return;
				}
				$ENV.dialog.open({
					url : action + "?secrecyComputer.secrecycomputerId="+items[0].value+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 340,
					title : '移除网络'
				});
			}
		</script>

	</head>

	<body >
		<div class="body_content" style="overflow: hidden;">
			<div id="main_tab" class="panel tab_panel" style="height: 650px;width: 100%;">
				<div id="main_tab_header" class="panel_header" style="width: 100%;">
					<div class="tab_bar">
						<div class="panel_title">涉密计算机列表</div>
						<div class="panel_title">涉密计算机密级变更列表</div>
						<div class="panel_title">涉密计算机密级解除列表</div>
					</div>
					<div style="position: absolute;right: 0;top: 0;" class="btn_bar">
						<div style="text-align: right;vertical-align: middle;">
							<ap:operationbutton />
						</div>
						<div class=""></div>
						<div class=""></div>
					</div>
				</div>

				<div class="tab_panel_content"><!-- 3个面板  开始-->
					<div class="panel_content" style="height:650px;" url="${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?district.districtCode=${district.districtCode}&fromQuery=${fromQuery}&checkLower=${checkLower}">
					</div>
					<div class="panel_content" style="height:650px;" url="${ctx}/bmp/secrecycomputer/secrecyComputer_secrecyChangeList.action?district.districtCode=${district.districtCode}&fromQuery=${fromQuery}&checkLower=${checkLower}">
					</div>
					<div class="panel_content" style="height:650px;" url="${ctx}/bmp/secrecycomputer/secrecyComputer_decryptionList.action?district.districtCode=${district.districtCode}&fromQuery=${fromQuery}&checkLower=${checkLower}">
					</div>
				</div>
			</div>
		</div>
		<!-- 删除用隐藏表单 -->
		<form action="" method="post" id="delForm">
			<input type="hidden" name="secrecyComputerIds" id="secrecyComputerIds"/>
		</form>
	</body>
</html>