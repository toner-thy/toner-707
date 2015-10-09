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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密科研项目</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">


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
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
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
									height: 620
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
				var url ="<s:url action="secrecyResearchProject_add" namespace="/bmp/secrecyResearchProject"/>";
				TabUtil.openAsTab({
					url : url,
					title : '涉密科研项目-新增',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在新增的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									window.frames[0].location.reload();
								}
							}else {
									return false;
								}
						}else{
							if(item.content.getContentWindow().needReload){
								window.frames[0].location.reload();
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
				var url = action+'?secrecyResearchProject.secrecyResearchProjectId='+items[0].value;
				TabUtil.openAsTab({
					url : url,
					title : '涉密科研项目-编辑',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									window.frames[0].location.reload();
								}
							}else {
									return false;
								}
						}else{
							if(item.content.getContentWindow().needReload){
								window.frames[0].location.reload();
							}
						}
					}
				});
			}

			// 删除
			function doDel(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要删除的项。");
					return;
				}
				var ids = "";
				items.each(function(item){
					ids += item.value + ",";
				});

				//判断  是否可以删除，如果是已经  密级变更    密级解除
				var jsonRequest = new Request.JSON({
 					url: '${ctx}/bmp/secrecyResearchProject/ajax_relationship_secrecyResearchProject.action?secrecyResearchProjectId='+ids,
 				    onComplete: function(result, text){
 				    	if (result.flag != null && result.flag != "0") {
 				    		alert("不能删除，你选择的记录已经进行了："+result.message + "。");
 				    		return;

 				    	}else {
 				    		if(window.confirm("确定要删除吗？")){
 								document.getElementById("secrecyResearchProjectIds").value = ids;
 								document.getElementById("secrecyResearchProjectDelForm").action = action;
 								document.getElementById("secrecyResearchProjectDelForm").submit();
 							}
 				    	}
 				    }
 				}).get({
 					'secrecyResearchProjectId' : ids
 				});
			}

			//导出
			function doExport(action){
				var frm = window.frames[0].$("queryform");
				frm.action = action;
				frm.submit();
			}

			//密级变更
			function doSecrecyChange(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}

				$ENV.dialog.open({
					url : action + "?secrecyResearchProject.secrecyResearchProjectId=" + items[0].value + "&_ts="+new Date().getTime(),
					width : 0.4,
					height : 310,
					title : '涉密科研项目密级变更'
				});
			}

			//解除
			function doSecrecyClear(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}

				$ENV.dialog.open({
					url : action + "?secrecyResearchProject.secrecyResearchProjectId=" + items[0].value + "&_ts="+new Date().getTime(),
					width : 0.4,
					height : 300,
					title : '涉密科研项目密级解除'
				});
			}
		</script>

	</head>

	<body>

		<div class="body_content" id="bcon" >

			<div id="main_tab" class="panel tab_panel" style="height: 650px;width: 100%;">
				<div id="main_tab_header" class="panel_header" style="width: 100%;">
					<div class="tab_bar">
						<div class="panel_title">涉密科研项目列表</div>
						<div class="panel_title">涉密科研项目密级变更列表</div>
						<div class="panel_title">涉密科研项目解除列表</div>
					</div>
					<div style="position: absolute;right: 0;top: 0;" class="btn_bar">
						<div style="text-align: right;vertical-align: middle;" >
							<ap:operationbutton />
						</div>
						<div class=""></div>
						<div class=""></div>
					</div>
				</div>

				<!-- 3个面板开始 -->
				<div class="tab_panel_content">

					<!-- 涉密科研项目列表  -->
					<div class="panel_content" id="list_list" style="overflow: auto;" url="${ctx}/bmp/secrecyResearchProject/secrecyResearchProject_list_list.action?districtCode=${district.districtCode}&ywFlag=${ywFlag}&isChildren=${isChildren}">
					</div>

					<!-- 涉密科研项目密级变更列表  -->
					<div class="panel_content" style="overflow: auto;" url="${ctx}/bmp/secrecyResearchProject/secrecyResearchProject_change_list.action?districtCode=${district.districtCode}&ywFlag=${ywFlag}&isChildren=${isChildren}">
					</div>

					<!-- 涉密科研项目解除列表  -->
					<div class="panel_content" style="overflow: auto;" url="${ctx}/bmp/secrecyResearchProject/secrecyResearchProject_clear_list.action?districtCode=${district.districtCode}&ywFlag=${ywFlag}&isChildren=${isChildren}">
					</div>

				</div>
				<!-- 3个面板结束 -->

			</div>
		</div>


		<form action="" name="secrecyResearchProjectDelForm" method="post" id="secrecyResearchProjectDelForm">
			<input type="hidden" name="secrecyResearchProjectIds" id="secrecyResearchProjectIds" />
		</form>
	</body>
</html>