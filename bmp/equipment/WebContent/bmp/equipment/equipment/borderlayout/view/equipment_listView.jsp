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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>设备管理详情</title>

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
			function doSave(action){
				$('equipmentList').action=action;
				$('sub').click();
			}

			function doView(action,equipmentTypeId,organId){
				$ENV.dialog.open({
					url : action+'?id='+equipmentTypeId+'&equipment.organ.organId='+organId+'&t_date=' + new Date().getTime(),
					width : 0.7,
					height : 0.5,
					title : '单位保密设备详情'
				});
			}

			function doReport(action){
				$('equipmentList').action=action;
				$('sub').click();
			}

		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<!-- 有权限按钮 上报/保存-->
				<!-- <ap:operationbutton />-->
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 内容panel开始-->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleSearchIco">
						${Organ.organName }&nbsp;&nbsp;单位设备配备列表
					</div>
					<div class="panel_titleBtnBar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.equipmentList.size > 0">
						<form id="equipmentList" action="${ctx}/equipment_save.action" method="post">
							<!-- 隐藏域 -->
							<input type="hidden" name="ec_i" value="equipmentTypeList" />
							<input type="hidden" name="equipmentTypeList_crd" value="10" />
							<input type="hidden" name="equipmentTypeList_p" value="1" />
							<input type="hidden" name="equipmentTypeList_a_secrecyWardLog_checkbox" value="equipmentTypeId" />

							<table id="equipmentTypeList_table" border="0" cellspacing="0" cellpadding="0" class="content_table" width="100%">
								<thead>
									<tr align="center">
										<td class="tableHeader" width="30%" >设备名称</td>
										<td class="tableHeader" width="30%" >说 明</td>
										<td class="tableHeader" width="10%" >数 量</td>
										<td class="tableHeader" width="10%" >显示详情</td>
									</tr>
								</thead>
								<tbody class="tableBody" align="center">
									<c:forEach items="${equipmentList}" var="equipment" varStatus="st">
										<tr <c:if test="${st.index % 2 == 0}">class="odd"</c:if>
											<c:if test="${st.index % 2 == 1}">class="even"</c:if>
										 	onmouseover="this.className='highlight'"
											 <c:if test="${st.index % 2 == 0}">onmouseout="this.className='odd'"</c:if>
											 <c:if test="${st.index % 2 == 1}">onmouseout="this.className='even'"</c:if>>
											 <td width="25%" >
												<div title="${equipment.equipmentType.name }">
													${fn:substring(equipment.equipmentType.name,0,20) }
													<c:if test="${fn:length(equipment.equipmentType.name)>20}">....</c:if>
												</div>
											 </td>
											<td width="30%" >
												<div title="${equipment.equipmentType.description}">
													<c:if test="${equipment.equipmentType.description == ''}">暂 无</c:if>
													<c:if test="${equipment.equipmentType.description != ''}">
														${fn:substring(equipment.equipmentType.description, 0, 20)}
														<c:if test="${fn:length(equipment.equipmentType.description)>20}">...</c:if>
													</c:if>
												</div>
											</td>
											<td width="10%" align="center">
												<div title="${equipment.number}" >
													<c:if test="${empty saveFlag}">
														${equipment.number}
													</c:if>
													<c:if test="${not empty saveFlag}">
														${equipment.number}
													</c:if>
												</div>
											</td>
											<td width="10%" >
												<a href='###' class="cpx12orange" onclick="doView('${ctx}/equipment_view.action','${equipment.equipmentType.equipmentTypeId }','${organId }');" >
													<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" alt ="详细信息" >
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<!-- 隐藏提交按钮 -->
							<div style="display: none">
								<input type="submit" id="sub"/>
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