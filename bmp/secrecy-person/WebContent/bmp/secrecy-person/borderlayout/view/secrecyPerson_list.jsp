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
		<title>涉密人员列表</title>

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
				title : '机关涉密人员-新增',
				onClose : function(tab, item) {
				if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								//window.frames[0].getElementById('secrecyPersonForm').submit();
								window.location.href ="${contextPath}/bmp/secrecyperson/secrecyPerson_list.action";
							}
						}else {
								return false;
							}
					}else{
							if(item.content.getContentWindow().needReload){
								//window.frames[0].getElementById('secrecyPersonForm').submit();
								window.location.href ="${contextPath}/bmp/secrecyperson/secrecyPerson_list.action";
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
					url : action + "?secrecyPerson.secrecyPersonId="+items[0].value,
					title : '机关涉密人员-编辑',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (item.content.getContentWindow().confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									window.location.href ="${contextPath}/bmp/secrecyperson/secrecyPerson_list.action";
								}
							} else {
								return false;
							}
						} else{
							if(item.content.getContentWindow().needReload){
								window.location.href ="${contextPath}/bmp/secrecyperson/secrecyPerson_list.action";
							}
						}
					}
				});
			}

			// 删除
			function doDelete(action){
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
					document.getElementById("secrecyPersonIds").value = ids;
					document.getElementById("secrecyPersonDelForm").action = action;
					document.getElementById("secrecyPersonDelForm").submit();
				}
			}

			// 查看详情
			function doDetail(secrecyPersonId){
				$ENV.dialog.open({
					title : '涉密人员详情',
					url : '${ctx}/bmp/secrecyperson/secrecyPerson_detail.action?secrecyPerson.secrecyPersonId='+secrecyPersonId+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
			}

			function doReportWord(action){
				window.location.href=action+"?t_time="+new Date().getTime();
			}

			// 上报
			function doReport(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要上传的项。");
					return;
				}
				if(window.confirm("确定上报吗？")){
					var ids = "";
					var queryStr = '';
					items.each(function(item){
						ids += item.value + ",";
					});
					queryStr = "?secrecyPersonIds=" + ids;
					window.location.href = action + queryStr + '&time=' + new Date().getTime();
				}
			}

			//导入
			function doImport(action){
				window.location.href = action;
			}

			//导出
			function doExport(action){
				var frm = window.frames[0].document.getElementById("secrecyPersonForm");
				frm.action = "${ctx}/bmp/secrecyperson/exportExcel_List.action?fromQuery=${fromQuery}&checkLower=${checkLower}";
				frm.submit();
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
					url : action + "?secrecyPerson.secrecyPersonId="+items[0].value+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 340,
					title : '密级变更'
				});
			}

			//脱密
			function doLeaveDuty(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请仅选择一项");
					return;
				}
				$ENV.dialog.open({
					url : action + "?secrecyPerson.secrecyPersonId="+items[0].value+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 320,
					title : '脱密'
				});
			}

			//批量修改时间
			function batchUpdateDate(){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}

				if (window.confirm('确定要修改吗？ ' )) {
					var ids = "";
					items.each(function(item){
						ids += item.value + ",";
					});
					TabUtil.openAsTab({
						url : '${ctx}/bmp/secrecyperson/secrecyPerson_batchUpdateDate.action?secrecyPersonIds='+ids+'&nestedflag=1&t_date=' + new Date().getTime(),
						title : '批量修改时间',
						onClose : function(tab, item) {
							if(!item.content.getContentWindow().needReload2){
								if (window.confirm("您确定放弃正在编辑的内容吗？")) {
									if(item.content.getContentWindow().needReload){
										window.location.reload();
									}
								} else {
									return false;
								}
							} else{
								if(item.content.getContentWindow().needReload){
									window.location.reload();
								}
							}
						}
					});
				}
			}
		</script>

	</head>

	<body >
		<!-- 公共头部 -->
		<%-- <div class="but_bar">
			<div class="left">
			</div>
			<div class="right">
				<div class="pop_button_bar">
				    <a class='pop_button' href='${ctx}/platform/help/help_clientInfo.do?help.helpId=secrecy_person_help' target='_back'><span>帮 助</span></a>
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div> --%>

		<div class="body_content" style="overflow: hidden;">
			<div id="main_tab" class="panel tab_panel" style="height: 650px;width: 100%;">
				<div id="main_tab_header" class="panel_header" style="width: 100%;">
					<div class="tab_bar">
						<div class="panel_title">涉密人员列表</div>
						<div class="panel_title">涉密人员密级变更历史列表</div>
						<div class="panel_title">脱密涉密人员信息列表</div>
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
					<div class="panel_content" style="height:600px;" url="${ctx}/bmp/secrecyperson/secrecyPerson_list_list.action?districtCode=${districtCode}&fromQuery=${fromQuery}&checkLower=${checkLower}">
					</div>
					<div class="panel_content" style="height:600px;" url="${ctx}/bmp/secrecyperson/secrecyPerson_secrecyLevelChangeHistory.action?districtCode=${districtCode}&fromQuery=${fromQuery}&checkLower=${checkLower}">
					</div>
					<div class="panel_content" style="height:600px;" url="${ctx}/bmp/secrecyperson/secrecyPerson_decryptionHistory.action?districtCode=${districtCode}&fromQuery=${fromQuery}&checkLower=${checkLower}">
					</div>
				</div>
		</div>
	</div>

		<!-- 删除用隐藏表单 -->
		<form action="" method="post" id="secrecyPersonDelForm">
			<input type="hidden" name="secrecyPersonIds" id="secrecyPersonIds"/>
		</form>

	</body>
</html>