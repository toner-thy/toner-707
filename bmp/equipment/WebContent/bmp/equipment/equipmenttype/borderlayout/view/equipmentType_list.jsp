<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>装备配备类别类别列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<s:actionmessage theme="messages"/>

		<script language="javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
			function doAdd(action){
				TabUtil.openAsTab({
					url : action,
					title : '新增装备配备类别类别',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								document.getElementById('equipmentType_list').submit();
							}
							}else {
									return false;
								}
						}else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('equipmentType_list').submit();
							}
						}
					}
				});
			}

			function doPublish(action){
				var items = EcTable.getCheckedItems();
				if(items.length == 0){
					alert("请至少选择一项。");
					return;
				}
				if(window.confirm("确定发布吗？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}

			function doEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length == 0){
					alert("请至少选择一项。");
					return;
				}else if(items.length > 1){
					alert("最多只能选择一项。");
					return;
				}
				TabUtil.openAsTab({
					url : action + "?id="+items[0].value,
					title : '编辑装备配备类别类别',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在编辑的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								$ENV.page.refresh();
							}
							}else {
									return false;
								}
						}else{
							if(item.content.getContentWindow().needReload){
								$ENV.page.refresh();
							}
						}
					}
				});
			}

			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length == 0){
					alert("请至少选择一项。");
					return;
				}
				if(window.confirm("删除此数据会同时删除装备填报的信息,确定删除吗？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}

			function doView(action, equipmentTypeId){
				var date = new Date();
				window.showModalDialog(action+"?id="+equipmentTypeId+"&date="+date.getTime(),
						window,"dialogWidth=600px;dialogHeight=360px;status=no;scroll=no;help=no");
			}

			function isCheckedOneClass(ckArr){
				var pkValue = "";
				var count = 0;
				for(var i = 0; i < ckArr.length; i++){
					var e = ckArr[i];
					if(e.name != 'idall' && e.type.toLowerCase() == "checkbox"){
						if(e.checked){
							count++;
							pkValue = e.value;
							if(count > 1)
								break;
							}
						}
				}
				if(count == 1) {
					return pkValue;
				}
				else if(count == 0){
					alert(checkFirst);
					return "";
				}
				else if(count > 1){
					alert(onlyOne);
					return "";
				}
			}

			//查看历史记录
			function doViewObject(action,id){
				window.showModalDialog(action+"?equipmentType.equipmentTypeId="+id+"&t_date="+ $time(),
					window,"dialogWidth=900px;dialogHeight=380px;status=no;help=no");
			}

			function doDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/equipmentType_view.action?id='+id + "&_ts="+new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '装备配备类别类别详情'
				});
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
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="装备配备类别简介" ctx="${ctx}" icoPath="${ctx}/equipment/equipmenttype/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','装备配备类别简介');">简 介</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','装备配备类别搜索');">搜 索</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于装备配备类别管理
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_equipment_info"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form id="equipmentType_list" name="equipmentType_list" action="${ctx}/equipmentType_list.action" method="post">
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									装备配备类型名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<input name="equipmentType.name" id="name" type="text" value="${equipmentType.name }" size="40" />
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('equipmentType_list').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('equipmentType_list').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						装备配备类别列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.equipmentTypeList.size > 0">
						<ec:table items="equipmentTypeList" var="equipmentTypeObj" tableId="equipmentTypeList" border="0"
							action="${ctx}/equipmentType_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="equipmentTypeId" alias="secrecyWardLog_checkbox" cell="checkbox" headerCell="checkbox" width="10%"/>
								<ec:column property="name" title="装备配备类别名称" width="25%">
									<div title="${equipmentTypeObj.name}">
										${fn:substring(equipmentTypeObj.name, 0, 20)}
										<c:if test="${fn:length(equipmentTypeObj.name)>20}">...</c:if>
									</div>
								</ec:column>
								<ec:column property="null" title="说 明" width="37%">
									<c:if test="${equipmentTypeObj.description == ''}">暂 无</c:if>
									<c:if test="${equipmentTypeObj.description != ''}">
										<div title="${equipmentTypeObj.description}">
											${fn:substring(equipmentTypeObj.description, 0, 20)}
											<c:if test="${fn:length(equipmentTypeObj.description)>20}">...</c:if>
										</div>
									</c:if>
								</ec:column>
								<ec:column property="null" title="状 态" width="8%">
									<c:if test="${equipmentTypeObj.state eq 1}">
										已发布
									</c:if>
									<c:if test="${equipmentTypeObj.state eq 0}">
										未发布
									</c:if>
								</ec:column>
								<ec:column property="null" title="详 情" width="20%">
									<a title="查看详情" href="javaScript:doDetail('${equipmentTypeObj.equipmentTypeId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" />
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束-->
		</div>
	</body>
</html>