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


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>单位装备配备情况列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
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
					new FormCheck('equipmentList',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			function doSave(action){
				$('equipmentList').action=action;
				$('sub').click();
			}

			function doView(action,equipmentTypeId,organId){
				$ENV.dialog.open({
					url :action+'?id='+equipmentTypeId+'&equipment.organ.organId='+organId+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.6,
					title : '单位装备配备情况详情'
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
			<cp:start defaultTitle="单位装备配备情况简介" ctx="${ctx}" icoPath="${ctx}/equipment/equipment/borderlayout/skin/blue/img/mainlist_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','单位装备配备情况简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','单位装备配备情况搜索');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于单位装备配备情况
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_equipment_main_info"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form id="queryform" name="equipment_list" action="${ctx}/equipment_mainList.action" method="post">
						<table width="100%" class="st">
							<tr>
								<td class="fr">
									装备类别名称：
								</td>
								<td class="fl" colspan="3">
									<input name="equipmentType.name" id="equipmentType.name" type="text" value="${equipmentType.name }" />
									<input name="equipment.organ.organId" id="equipment.organ.organId" type="hidden" value="${organId }" />
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
						【${Organ.organName}】单位装备配备情况列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.equipmentList.size > 0">
						<form id="equipmentList" action="${ctx}/equipment_save.action" method="post" >
							<div>
								<input type="hidden" name="ec_i" value="equipmentTypeList" />
								<input type="hidden" name="equipmentTypeList_crd" value="10" />
								<input type="hidden" name="equipmentTypeList_p" value="1" />
								<input type="hidden" name="equipmentTypeList_a_secrecyWardLog_checkbox" value="equipmentTypeId" />
							</div>
							<div class="eXtremeTable">
								<table id="equipmentTypeList_table" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%" >
									<thead>
										<tr>
											<td class="tableHeader" width="20%">装备类别名称</td>
											<td class="tableHeader" width="30%">说 明</td>
											<td class="tableHeader" width="10%">数 量</td>
											<td class="tableHeader" width="10%">显示详情</td>
										</tr>
									</thead>
									<tbody class="tableBody" >
										<c:forEach items="${equipmentList }" var="equipment" varStatus="st">
											<tr <c:if test="${st.index % 2 == 0}">class="odd"</c:if>
												<c:if test="${st.index % 2 == 1}">class="even"</c:if>
												onmouseover="this.className='highlight'"
												<c:if test="${st.index % 2 == 0}">onmouseout="this.className='odd'"</c:if>
												<c:if test="${st.index % 2 == 1}">onmouseout="this.className='even'"</c:if>>
												<td width="25%">
													<div title="${equipment.equipmentType.name }">
														${fn:substring(equipment.equipmentType.name,0,20) }<c:if test="${fn:length(equipment.equipmentType.name)>20}">....</c:if>
													</div>
												</td>
												<td width="40%">
													<div title="${equipment.equipmentType.description}">
														<c:if test="${equipment.equipmentType.description == ''}">暂 无</c:if>
														<c:if test="${equipment.equipmentType.description != ''}">
															${fn:substring(equipment.equipmentType.description, 0, 20)}
															<c:if test="${fn:length(equipment.equipmentType.description)>20}">...</c:if>
														</c:if>
													</div>
												</td>
												<td width="10%">
													<div title="${equipment.number}" >
														${equipment.number}
													</div>
												</td>
												<td width="10%">
													<a href='###' class="cpx12orange" onclick="doView('${ctx}/equipment_view.action','${equipment.equipmentType.equipmentTypeId }','${organId }');" >
														<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" alt ="详细信息" >
													</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

							<!-- 隐藏提交按钮 -->
							<div style="display: none">
								<input type="submit" id="sub" />
							</div>
						</form>
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