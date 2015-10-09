<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增、编辑人员编制构成情况</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
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
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});



			function doBack2(){
				window.location.href = "${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_edit.action?secrecyOffice.secrecyOfficeId=${secrecyOffice.secrecyOfficeId}";
			}

			function doSave(){
				if (formcheck.isFormValid(true)) {
				//if (confirm('请仔细核对输入保密办成员的信息,确认无误请点击【确定】,如需修改请点击【取消】!')){
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				//}
				}
			}

		</script>

		<style type="text/css">
			.singleNum {
				width: 50px;
			}
		</style>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack2();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密办（保密局）简介" ctx="${ctx}" icoPath="/bmp/secrecy-organization/secrecy-office/borderlayout/skin/blue/img/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密办（保密局）简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密办（保密局）
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ_secrecy_office"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

					<!-- 联系方式 -->
					<!-- <div class="cpMsgContactInfoTitle">
						业务指导
					</div>
					<div class="cpMsgContactInfoContext">
						联系人：四川省国家保密局督察处 XXX 联系电话：028-85229437
					</div> -->
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						人员编制情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecyoffice' action='secrecyOffice_editEstablishSituationing.action' includeParams='true'/>" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyOffice.secrecyOfficeId" value="${secrecyOffice.secrecyOfficeId}"/>
						<input type="hidden" name="establishSituation.establishSituationId" value="${establishSituation.establishSituationId}"/>
						<input type="hidden" name="leaderStaff.leaderStaffId" value="${leaderStaff.leaderStaffId}"/>
						<input type="hidden" name="employPerson.employPersonId" value="${employPerson.employPersonId}"/>
							<table class="content_table st" width="100%">
									<tr>
										<td align="center" class="tableHeader" colspan="4">编制构成情况</td>
										<td align="center" class="tableHeader" colspan="5">领导职数</td>
										<td align="center" class="tableHeader" colspan="4">聘用人员</td>
									</tr>
									<tr >
										<td >公务员</td>
										<td >参照管理事业编制</td>
										<td >全额拨款事业编制</td>
										<td >自收自支事业编制</td>

										<td >局长</td>
										<td >副局长</td>
										<td >巡视员</td>
										<td >副巡视员</td>
										<td >处（室、科）领导职数</td>

										<td >领导岗位任职人员</td>
										<td >专业技术人员</td>
										<td >工勤人员</td>
										<td >其他人员</td>

									</tr>
									<tr >
										<td align="center"><input id="establishSituation.civil" name="establishSituation.civil" type="text" value="${establishSituation.civil }" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="establishSituation.referMamager" name="establishSituation.referMamager" type="text" value="${establishSituation.referMamager }" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="establishSituation.fullFunds" name="establishSituation.fullFunds" type="text" value="${establishSituation.fullFunds}" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="establishSituation.selfFunds" name="establishSituation.selfFunds" type="text" value="${establishSituation.selfFunds}" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="leaderStaff.secretary" name="leaderStaff.secretary" type="text" value="${leaderStaff.secretary}" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="leaderStaff.deputySecretary" name="leaderStaff.deputySecretary" type="text" value="${leaderStaff.deputySecretary}" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="leaderStaff.inspector" name="leaderStaff.inspector" type="text" value="${leaderStaff.inspector}" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="leaderStaff.deputyInspector" name="leaderStaff.deputyInspector" type="text" value="${leaderStaff.deputyInspector}" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="leaderStaff.deptStaff" name="leaderStaff.deptStaff" type="text" value="${leaderStaff.deptStaff}" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="employPerson.managerStaff" name="employPerson.managerStaff" type="text" value="${employPerson.managerStaff}" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="employPerson.specialtyStaff" name="employPerson.specialtyStaff" type="text" value="${employPerson.specialtyStaff}" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="employPerson.workStaff" name="employPerson.workStaff" type="text" value="${employPerson.workStaff}" class="singleNum validate['required','digit']" /></td>
										<td align="center"><input id="employPerson.other" name="employPerson.other" type="text" value="${employPerson.other}" class="singleNum validate['required','digit']" /></td>
									</tr>
							</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

	</body>
</html>