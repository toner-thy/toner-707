<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑保密技术设备类型</title>
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
			// 新增设备信息
			function doAdd() {
				var id = '${secrecyEquipmentCategory.secrecyEquipmentCategoryId}';
				if (id == '1'){
					alert("不能在根节点下增加技术设备！");
				}else{
					window.location.href = "${ctx}/bmp/devicemgr/deviceMgrAction_add.action?secrecyEquipmentCategory.secrecyEquipmentCategoryId="+id+'&t_date=' + new Date().getTime();
				}
			}

			// 编辑设备信息
			function doEdit() {
				var id = '${secrecyEquipmentCategory.secrecyEquipmentCategoryId}';
				var items = EcTable.getCheckedItems();
				if(items.length==0) {
					alert("请选择一项记录。");
					return;
				} else if(items.length>1) {
					alert("最多只能选择一项记录。");
					return;
				}
				window.location.href="${ctx}/bmp/devicemgr/deviceMgrAction_edit.action?secrecyEquipment.secrecyEquipmentId="+items[0].value+'&secrecyEquipmentCategory.secrecyEquipmentCategoryId='+id+'&t_date=' + new Date().getTime();
			}

			// 删除设备信息
			function doDelete() {
				var items = EcTable.getCheckedItems();
				if(items.length==0) {
					alert("请至少选择一项记录。");
					return;
				}
				var ids = "";
				if(window.confirm("确定要删除吗？")){
					items.each(function(item){
						ids += item.value + ",";
					});
					document.getElementById("secrecyEquipmentIdsStr").value = ids;
					var forms = $('secrecyEquipmentDelForm');
					forms.action = "${ctx}/bmp/devicemgr/deviceMgrAction_delete.action";
					forms.submit();
				}
			}

			// 设备信息详情
			function doDetail(id){
				environment.dialog.open({
					url : '${ctx}/bmp/devicemgr/deviceMgrAction_detail.action?secrecyEquipment.secrecyEquipmentId='+id+'&t_date=' + new Date().getTime(),
					width : (window.top.getCoordinates().width)*0.8,
					height : (window.top.getCoordinates().height)*0.9,
					title : '查看详情'
				});
			}

		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" id="sbm_button_hidden"  href="javascript:doAdd()"><span>新增</span></a>
					<a class="pop_button" id="sbm_button" href="javascript:doEdit()"><span>编辑</span></a>
					<a class="pop_button" href="javascript:doDelete()"><span>删除</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="#"
						onclick="javascript:window.refresh();"><span>刷新本页</span></a> <a
						class="pop_button pop_button_close" href="#"
						onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content" style="width: 99%;">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="设备分类简介" ctx="${ctx}" icoPath="/bmp/devicemgr/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','设备分类简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','设备配备搜索');">查 询</div>
			</cp:start>
				<cp:msg show="false" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于设备分类简介
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="boa_secrecy_equipment"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="true" divId="cp002">
					<form action="deviceMgrAction_list.action" id="queryform" name='queryform' theme="simple">
						<input type="hidden" name="secrecyEquipmentCategory.secrecyEquipmentCategoryId" value="${secrecyEquipmentCategory.secrecyEquipmentCategoryId}">
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									设备名称：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyEquipment.name" value="${secrecyEquipment.name}" />
								</td>
								<td class="tbLable fr">
									型号/序号：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyEquipment.typeCode" value="${secrecyEquipment.typeCode}" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									所属单位：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" name="secrecyEquipment.organ.organName" value="${secrecyEquipment.organ.organName}" />
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
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 设备信息列表 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						【${secrecyEquipmentCategory.name}】类型设备列表
					</div>
				</div>
				<div class="panel_content">
					<!-- 数据列表 -->
					<s:if test="#attr.secrecyEquipmentList.size>0">
						<ec:table items="secrecyEquipmentList" var="secrecyEquipment" tableId="secrecyEquipmentList" border="0"
							action="${ctx}/bmp/devicemgr/deviceMgrAction_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secrecyEquipmentId" alias="secrecyEquipmentId_checkbox" cell="checkbox" headerCell="checkbox" sortable="false"/>
								<ec:column property="name" title="设备名称"/>
								<ec:column property="typeCode" title="型号/序号"/>
								<ec:column property="null" title="责任人">
									${secrecyEquipment.dutyPerson.name }
								</ec:column>
								<ec:column property="null" title="所属单位">
									<div title="${secrecyEquipment.organ.organName}">${fn:substring(secrecyEquipment.organ.organName,0,10)}<c:if test="${fn:length(secrecyEquipment.organ.organName)>10}">……</c:if></div>
								</ec:column>
								<ec:column property="null" title="存放地点">
									<div title="${secrecyEquipment.atPlace}">${fn:substring(secrecyEquipment.atPlace,0,10)}<c:if test="${fn:length(secrecyEquipment.atPlace)>10}">……</c:if></div>
								</ec:column>
<%-- 								<ec:column property="null" title="状态"> --%>
<%-- 									<dictionary:text fieldCode="secrecyEquipment_status" tableCode="devicemgr" optionValue="${secrecyEquipment.status}"></dictionary:text> --%>
<%-- 								</ec:column> --%>
								<ec:column property="null" title="详情">
									<a href="javascript:doDetail('${secrecyEquipment.secrecyEquipmentId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无信息。"/>
					</s:else>
				</div>

				<!-- 用表单形式提交要删除的数据的id -->
				<form action="" method="post" id="secrecyEquipmentDelForm">
					<input type="hidden" name="secrecyEquipmentCategory.secrecyEquipmentCategoryId" value="${secrecyEquipmentCategory.secrecyEquipmentCategoryId}"/>
					<input type="hidden" id="secrecyEquipmentIdsStr" name="secrecyEquipmentIds" />
				</form>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>