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
		<title>涉密移动存储介质列表</title>

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
				title : '涉密移动存储介质-新增',
				onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在新增的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									window.location.href ="${ctx}/secrecysystem/secrecymobilestoragemedia/mobilestoragemedia_main.action";
								}
							}else{
								return false;
							}
						}else{
							if(item.content.getContentWindow().needReload){
								window.location.href ="${ctx}/secrecysystem/secrecymobilestoragemedia/mobilestoragemedia_main.action";
							}
						}
					}
				});
			}

			// 编 辑
			function doEdit(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}
 				TabUtil.openAsTab({
					url : action + "?secrecyMobilestoragemedia.secrecymobilestoragemediaId="+items[0].value,
					title : '涉密移动存储介质-编辑',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (item.content.getContentWindow().confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									window.location.href ="${ctx}/secrecysystem/secrecymobilestoragemedia/mobilestoragemedia_main.action";
								}
							} else{
								return false;
							}
						} else{
							if(item.content.getContentWindow().needReload){
								window.location.href ="${ctx}/secrecysystem/secrecymobilestoragemedia/mobilestoragemedia_main.action";
							}
						}
					}
				});
			}

			// 删除
			function doDel(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}
				if(window.confirm("确定删除？")){
					var ids = "";
					items.each(function(item){
						ids += item.value + ",";
					});
					document.getElementById("secrecymobilestoragemediaIds").value = ids;
					document.getElementById("secrecymobilestoragemediaDelForm").action = action;
					document.getElementById("secrecymobilestoragemediaDelForm").submit();
				}
			}

			function doReportWord(action){
				window.location.href=action+"?t_time="+new Date().getTime();
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
					url : action + "?secrecyMobilestoragemedia.secrecymobilestoragemediaId="+items[0].value+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 340,
					title : '密级变更'
				});
			}

			//脱密/解除
			function doDeclassified(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请仅选择一项");
					return;
				}
				$ENV.dialog.open({
					url : action + "?secrecyMobilestoragemedia.secrecymobilestoragemediaId="+items[0].value+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 320,
					title : '脱密'
				});
			}

			//导出
			function doExport(action){
				var frm = window.frames[0].document.getElementById("queryForm");
				var oldAction = frm.action;
				frm.action = "${ctx}/secrecysystem/secrecymobilestoragemedia/mobilestoragemedia_export.action?fromQuery=${fromQuery}&checkLower=${checkLower}";
				frm.submit();
				frm.action = oldAction;
			}

		</script>

	</head>

	<body >
		<div class="body_content" style="overflow: hidden;">
			<div id="main_tab" class="panel tab_panel" style="height: 650px;width: 100%;">
				<div id="main_tab_header" class="panel_header" style="width: 100%;">
					<div class="tab_bar">
						<div class="panel_title">涉密移动存储介质列表</div>
						<div class="panel_title">涉密移动存储介质密级变更列表</div>
						<div class="panel_title">涉密移动存储介质密级解除列表</div>
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
					<div class="panel_content" style="height:650px;" url="${ctx}/secrecysystem/secrecymobilestoragemedia/mobilestoragemedia_list.action?districtCode=${districtCode}&fromQuery=${fromQuery}&checkLower=${checkLower}">
					</div>
					<div class="panel_content" style="height:650px;" url="${ctx}/secrecysystem/secrecymobilestoragemedia/mobilestoragemedia_secrecyLevelChangeHistory.action?districtCode=${districtCode}&fromQuery=${fromQuery}&checkLower=${checkLower}">
					</div>
					<div class="panel_content" style="height:650px;" url="${ctx}/secrecysystem/secrecymobilestoragemedia/mobilestoragemedia_declassifiedHistory.action?districtCode=${districtCode}&fromQuery=${fromQuery}&checkLower=${checkLower}">
					</div>
				</div>
			</div>
		</div>
		<!-- 删除用隐藏表单 -->
		<form action="${ctx }/secrecysystem/secrecymobilestoragemedia/mobilestoragemedia_delete.action" method="post" id="secrecymobilestoragemediaDelForm">
			<input type="hidden" name="secrecymobilestoragemediaIds" id="secrecymobilestoragemediaIds"/>
		</form>

	</body>
</html>