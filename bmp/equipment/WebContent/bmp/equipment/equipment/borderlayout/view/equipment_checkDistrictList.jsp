<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密装备配备查询列表</title>

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
					new FormCheck('equipmentList',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			function doView(action,organId){
				$ENV.dialog.open({
					url : action+'?equipment.organ.organId='+organId+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.6,
					title : '设备配备检测详情'
				});
			}

			function doReport(action){
				var items = EcTable.getCheckedItems();
					if(items.length == 0){
						alert('请选择一项。');
						return;
					}
					if(window.confirm("确定上报吗？")){
						var ids = "?";
						items.each(function(item){
							ids += "ids=" + item.value + "&";
						});
						window.location.href = action+ids;
					}
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
			<!-- 取消上报权限 -->
<%-- 				<ap:operationbutton /> --%>
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
			<cp:start defaultTitle="保密装备配备查询简介" ctx="${ctx}" icoPath="${ctx}/equipment/equipment/borderlayout/skin/blue/img/equipment_check_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','保密装备配备查询简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','保密装备配备查询搜索');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密装备配备查询
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_equipment_check_info"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form id="queryform" name="equipment_list" action="${ctx}/equipment_checkDistrictList.action" method="post">
						<!-- 隐藏提交行政区划code -->
						<input type="hidden" id="district.districtCode" name="district.districtCode" value="${district.districtCode }">

						<table width="100%" class="st">
							<tr>
								<td class="tbLable fr">
									单位名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" name="equipment.organ.organName" id="equipment.organ.organName" value="${equipment.organ.organName}" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									查询范围：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="radio" id="status1" onclick="equipment_list_form.submit()" ${status==1?'checked':''} value="1" name="status" /><label>包含子机构</label>
									<input type="radio" id="status2" onclick="equipment_list_form.submit()" ${status==2?'checked':''} value="2" name="status" /><label>不包含子机构</label>
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

			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						【${district.districtName}】单位装备配备列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.equipmentList.size > 0">
						<ec:table items="equipmentList" var="organ" tableId="equipmentList"
							border="0"
							action="${ctx}/equipment_checkDistrictList.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="organName" title="单位名称" width="20%" />
								<ec:column property="null" title="详情" width="10%">
									<a href="###" onclick="doView('${ctx}/equipment_listView.action','${organ.organId }');"><img
										src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif"
										border="0" title="详情" />
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<styles:nolist/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束-->
		</div>
	</body>
</html>