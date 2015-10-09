<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密装备配备检测列表</title>

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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
			function doAdd(action){
				TabUtil.openAsTab({
					url : action,
					title : '保密装备配备检测-新增',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								document.getElementById('queryform').submit();
							}
							}else {
									return false;
								}
						}else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('queryform').submit();
							}
						}
					}
				});
			}

			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length == 0){
					alert('请选择一项。');
					return;
				}
				if(window.confirm('确定删除吗？')){
					var ids = '';
					items.each(function(item){
						ids += item.value + ',';
					});
					window.location.href = action+'?equipmentCheckIds='+ids;
				}
			}

			function doEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				TabUtil.openAsTab({
					url : action + "?equipmentCheck.equipmentCheckId="+items[0].value,
					title : '保密装备配备检测-编辑',
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

			function doDetail(action, equipmentCheckId){
				$ENV.dialog.open({
					url : action +'?equipmentCheck.equipmentCheckId='+equipmentCheckId+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.6,
					title : '设备配备检测详情'
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
			<cp:start defaultTitle="保密设备配备检测简介" ctx="${ctx}" icoPath="${ctx}/equipment/equipmentcheck/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','保密设备配备检测简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','保密设备配备检测搜索');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密设备配备检测
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_equipmentcheck_info"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form action="${ctx}/bmp/equipmentcheck/equipmentCheck_list.action" method="post" id="queryform">
						<table width="100%" class="st">
							<tr>
								<td class="tbLable fr">
									检测名称：
								</td>
								<td class="tbValue fl">
									<input type="text" name="equipmentCheck.equipmentName" value="${equipmentCheck.equipmentName }" />
								</td>
								<td class="tbLable fr">
									设备型号：
								</td>
								<td class="tbValue fl">
									<input type="text" name="equipmentCheck.equipmentType" value="${equipmentCheck.equipmentType }" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									所属单位：
								</td>
								<td class="tbValue fl">
									<input type="text" name="equipmentCheck.equipmentOrgan" value="${equipmentCheck.equipmentOrgan}" />
								</td>
								<td class="tbLable fr">
									检查时间：
								</td>
								<td class="tbValue fl">
									<input type="text" name="equipmentCheck.checkTime" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${equipmentCheck.checkTime}"/>'
									title="检查时间" class="Wdate " onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true})" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('queryform').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('queryform').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</cp:search>
			<cp:end>
			</cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密装备配备检测列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.equipmentCheckList.size > 0">
						<ec:table items="equipmentCheckList" var="cg" tableId="equipmentCheckList" border="0"
							action="${ctx}/bmp/equipmentcheck/equipmentCheck_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="checkbox" alias="equipmentCheckId_checkbox" headerCell="checkbox">
									<input type="checkbox" value="${cg.equipmentCheckId}" class="row_checkbox" />
								</ec:column>
								<ec:column property="equipmentName" title="检测名称" cell="text" alias="size=8" />
								<ec:column property="equipmentType" title="设备型号" cell="text" alias="size=8"/>
								<ec:column property="number" title="数 量" >
									<div style="font-family: Arial, "Times New Roman" !important;">
										${cg.number}
									</div>
								</ec:column>
								<ec:column property="equipmentOrgan" title="所属单位">
									<c:if test="${cg.equipmentOrgan==''}">
										暂无
									</c:if>
									<c:if test="${cg.equipmentOrgan!=''}">
										${fn:substring(cg.equipmentOrgan, 0, 8)}
										<c:if test="${fn:length(cg.equipmentOrgan)>8}">...</c:if>
									</c:if>
								</ec:column>
								<ec:column property="checkPerson.name" title="检查人" cell="text" alias="size=8" />
								<ec:column property="null" title="检查时间" >
									<div style="font-family: Arial, "Times New Roman" !important;">
										<s:date name="#attr.cg.checkTime" format="yyyy/MM/dd"/>
									</div>
								</ec:column>
								<ec:column property="null" title="详 情">
									<a href='###' class="cpx12orange" onclick="doDetail('${ctx}/bmp/equipmentcheck/equipmentCheck_detail.action','${cg.equipmentCheckId }');" >
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" alt ="详情" />
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