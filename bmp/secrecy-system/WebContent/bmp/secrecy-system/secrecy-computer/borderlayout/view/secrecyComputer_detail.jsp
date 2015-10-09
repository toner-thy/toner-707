<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					showKeysectionpart('${secrecyComputer.isbelongKeydepartment}');
					var type = '${secrecyComputer.keySection.keySectionId}';
					if(type == ''){
						type = 2;
					} else {
						type = 1;
					}
					doChangeType(type);
				});
			});
			// 隐藏、显示是否属于要害部门、部位
			function showKeysectionpart(value){
				if(value == 1){
					$('keysectionpart_show').style.display = '';
				} else {
					$('keysectionpart_show').style.display = 'none';
				}
			}
			// 隐藏、显示要害部门、部位名称
			function doChangeType(value){
				if(value == 1){
					//显示要害部门, 隐藏要害部位信息及值
					$('keypart_show1').style.display = 'none';
					$('keypart_show2').style.display = 'none';
					$('keysection_show1').style.display = '';
					$('keysection_show2').style.display = '';
					$('keysection_show2').set("text","${not empty secrecyComputer.keySection.keySectionId ? secrecyComputer.keySection.department.departmentName : ''}");
					$('typeOfCute').set("text","要害部门");
					doSelectKeySection();
				} else {
					//显示要害部位, 隐藏要害部门信息及值
					$('keypart_show1').style.display = '';
					$('keypart_show2').style.display = '';
					$('keypart_show2').set("text", "${not empty secrecyComputer.keyPart.partId ? secrecyComputer.keyPart.partName : ''}");
					$('typeOfCute').set("text","要害部位");
					$('keysection_show1').style.display = 'none';
					$('keysection_show2').style.display = 'none';
					doSelectKeyPart("${not empty secrecyComputer.keyPart.partId ? secrecyComputer.keyPart.partId : ''}");
				}
			}

			// 要害部门-----回填部门名称
			function doSelectKeySection(){
				if( ${not empty secrecyComputer.keySection.department.departmentName}){
					$('departmentName').set("text", "${secrecyComputer.keySection.department.departmentName}");
				}
			}
			// 要害部位-----回填部门名称
			function doSelectKeyPart(data){
				if( ${not empty secrecyComputer.keyPart.department.departmentName} ){
					$('departmentName').set("text", "${secrecyComputer.keyPart.department.departmentName}");
				}
			}
		</script>
	</head>

	<body>

		<div id="body_content" class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密计算机详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/secrecycomputer' action='secrecyComputer_adding.action' includeParams='true'/>" method="post">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									单位名称
								</td>
								<td class="tbValue fl" colspan="3">
									<b>${_.login.actor.user.organ.name }</b>
								</td>
							</tr>
							<tr>

								<td class="tbLable fr">
									责任人：
								</td>
								<td class="tbValue fl">
									${secrecyComputer.dutyPerson.name }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyComputer.secrecyLevel}"></dictionary:text>
								</td>
								<td class="tbLable fr">
									机型：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="computer_type" optionValue="${secrecyComputer.computerType}"></dictionary:text>
							</tr>
							<tr>
								<td class="tbLable fr">
									编号：
								</td>
								<td class="tbValue fl">
									${secrecyComputer.computerNo }
								</td>
								<td class="tbLable fr">
									硬盘序列号：
								</td>
								<td class="tbValue fl">
									${secrecyComputer.diskSeq }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr" nowrap="nowrap">
									是否属于要害部门、部位：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="is_section_part" optionValue="${secrecyComputer.isbelongKeydepartment}"></dictionary:text>
								</td>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td id="departmentName" class="tbValue fl">
									${secrecyComputer.department.departmentName }
								</td>
							</tr>
							<tr id="keysectionpart_show" style="display: none">
								<td class="tbLable fr">
									类型：
								</td>
								<td class="tbLable fl" id="typeOfCute">
								</td>
								<td class="tbLable fr" id="keysection_show1" style="display: ''">
									要害部门：
								</td>
								<td class="tbValue fl" id="keysection_show2" style="display: ''">

								</td>
								<td class="tbLable fr" id="keypart_show1" style="display: none">
									要害部位：
								</td>
								<td class="tbValue fl" id="keypart_show2" style="display: none">

								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									是否安装保密技术防护专用系统（三合一）：
								</td>
								<td class="tbValue fl">
									<c:if test="${secrecyComputer.isFanghu eq 0 }">否</c:if>
									<c:if test="${secrecyComputer.isFanghu eq 1 }">是</c:if>
								</td>
								<td class="tbLable fr">
									是否纳入违规外联监控系统：
								</td>
								<td class="tbValue fl">
									<c:if test="${secrecyComputer.isWailian eq 0 }">否</c:if>
									<c:if test="${secrecyComputer.isWailian eq 1 }">是</c:if>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- 内容panel结束 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密计算机密级变更
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyComputer.secrecyComputerChanges.size>0">
						<table class="content_table" width="100%">
							<c:forEach items="${secrecyComputer.secrecyComputerChanges }" var="changes">
								<tr>
									<td width="5%" align="right">
										原密级：
									</td>
									<td width="5%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${changes.beforeLevel}"></dictionary:text>
									</td>
									<td width="5%" align="right">
										现密级：
									</td>
									<td width="5%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${changes.afterLevel}"></dictionary:text>
									</td>
									<td width="10%" align="right">
										变更时间：
									</td>
									<td width="10%" align="left">
										<fmt:formatDate value="${changes.changeDate }" pattern="yyyy-MM-dd"/>
									</td>
									<td width="10%" align="right">
										保密期限变更：
									</td>
									<td width="10%" align="left">
										<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${changes.changeTimeState }"></dictionary:text>
									</td>
									<td width="10%" align="right">
										变更原因：
									</td>
									<td width="30%" align="left">
										<div title="${changes.changeReason}">
											<c:if test="${fn:length(changes.changeReason) <= 25}">${changes.changeReason}</c:if>
											<c:if test="${fn:length(changes.changeReason) > 25}">${fn:substring(changes.changeReason,0,25)}...</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
						</table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
				</div>
			</div>

			<c:if test="${fn:length(secrecyComputer.secrecyComputerClears) > 0 }">
				<div class="panel tMargin bMargin">
					<div class="panel_header">
						<div class="panel_title panel_titleListIco">
							涉密计算机密级解除
						</div>
						<div class="panel_btn_bar pop_button_bar">
							<!-- 右侧按钮 -->
						</div>
					</div>
					<div class="panel_content panel_content_table">
						<s:if test="#request.secrecyComputer.secrecyComputerClears.size>0">
							<table class="content_table" width="100%">
								<c:forEach items="${secrecyComputer.secrecyComputerClears }" var="clear">
									<tr>
										<td width="5%" align="right">
											原密级：
										</td>
										<td width="5%" align="left">
											<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${clear.secrecyComputer.secrecyLevel}"></dictionary:text>
										</td>
										<td width="10%" align="right">
											解除类型：
										</td>
										<td width="10%" align="left">
											<dictionary:text tableCode="bmp" fieldCode="clear_secrecy_type" optionValue="${clear.clearType }"></dictionary:text>
										</td>
										<td width="10%" align="right">
											解除时间：
										</td>
										<td width="10%" align="left">
											<fmt:formatDate value="${clear.clearTime }" pattern="yyyy-MM-dd"/>
										</td>
										<td width="10%" align="right">
											解除原因：
										</td>
										<td width="40%" align="left">
											<div title="${clear.cleanReason}">
												<c:if test="${fn:length(clear.cleanReason) <= 30}">${clear.cleanReason}</c:if>
												<c:if test="${fn:length(clear.cleanReason) > 30}">${fn:substring(clear.cleanReason,0,30)}...</c:if>
											</div>
										</td>
									</tr>
								</c:forEach>
							</table>
						</s:if>
						<s:else>
							<u:noData text="暂无数据"/>
						</s:else>
					</div>
				</div>
			</c:if>
		</div>

	</body>
</html>