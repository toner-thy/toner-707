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
		<title>保密要害部位(总)</title>

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

			// 新 增   非内嵌页面的新增方法
			function doAdd(action){
				TabUtil.openAsTab({
					url : action,
					title : '保密要害部位-新增',
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

			// 编 辑   非内嵌页面的编辑方法
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
					url : action+'?part.partId='+items[0].value,
					title : '保密要害部位-编辑',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									window.frames[0].location.reload();
								}
							} else {
								return false;
							}
						} else{
							if(item.content.getContentWindow().needReload){
								window.frames[0].location.reload();
							}
						}
					}
				});
			}

			// 删除
			function doDelete(action){
				var items = window.frames[0].EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要删除的项。");
					return;
				}

				//判断  是否可以删除，如果是已经  密级变更    密级解除
				var jsonRequest = new Request.JSON({
 					url: '${ctx}/bmp/part/ajax_keyPart.action?partId='+items[0].value,
 				    onComplete: function(result, text){
 				    	if (result.flag != null && result.flag != "0") {
 				    		alert("删除失败，该记录已经被："+result.message + " 引用。");
 				    		return;

 				    	}else {
 				    		if(window.confirm("确定要删除吗？")){
 								var ids = "";
 								items.each(function(item){
 									ids += item.value + ",";
 								});
 								document.getElementById("partIds").value = ids;
 								document.getElementById("partDelForm").action = action;
 								document.getElementById("partDelForm").submit();
 							}
 				    	}
 				    }
 				}).get({
 					'partId' : items[0].value
 				});

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
					queryStr = "?partIds=" + ids;
					window.location.href = action + queryStr + '&time=' + new Date().getTime();
				}
			}

			//导出
			function doExport(action){
				var frm = window.frames[0].document.getElementById("queryform");
				frm.action = "${ctx}/bmp/part/exportExcel_List.action";
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
					url : action + "?part.partId=" + items[0].value + "&_ts="+new Date().getTime(),
					width : 0.5,
					height : 310,
					title : '要害部位密级变更'
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
					url : action + "?part.partId=" + items[0].value + "&_ts="+new Date().getTime(),
					width : 0.5,
					height : 290,
					title : '要害部位密级解除'
				});

			}

		</script>

	</head>

	<body>

		<div class="body_content" id="bcon" >

			<div id="main_tab" class="panel tab_panel" style="height: 650px;width: 100%;">
				<div id="main_tab_header" class="panel_header" style="width: 100%;">
					<div class="tab_bar">
						<div class="panel_title">保密要害部位列表</div>
						<div class="panel_title">保密要害部位密级变更历史列表</div>
						<div class="panel_title">保密要害部位解除部门信息列表</div>
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

					<!-- 保密要害部位列表  -->
					<div class="panel_content" id="list_list" style="overflow: auto;" url="${ctx}/bmp/part/part_list_list.action?districtCode=${district.districtCode}&ywFlag=${ywFlag}&isChildren=${isChildren}">
					</div>

					<!-- 保密要害部位  密级变更列表  -->
					<div class="panel_content" style="overflow: auto;" url="${ctx}/bmp/part/keyPart_list_change.action?districtCode=${district.districtCode}&ywFlag=${ywFlag}&isChildren=${isChildren}">
					</div>

					<!-- 保密要害部位  密级解除列表  -->
					<div class="panel_content" style="overflow: auto;" url="${ctx}/bmp/part/keyPart_list_clear.action?districtCode=${district.districtCode}&ywFlag=${ywFlag}&isChildren=${isChildren}">
					</div>

				</div>
				<!-- 3个面板结束 -->

			</div>
		</div>


		<form action="" name="partDelForm" method="post" id="partDelForm">
			<input type="hidden" name="partIds" id="partIds" />
		</form>
	</body>
</html>