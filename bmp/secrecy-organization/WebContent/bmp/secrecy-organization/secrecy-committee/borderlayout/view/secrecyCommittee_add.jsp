<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增保密委员会</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

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
					var needReload = ${needReload};
					var needReload2 = false;
					if (needReload) {
						if (!confirm('编辑成功，是否继续添加？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			function doSave(){
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				}
			}

			function doBackToSecrecyCommitteeDetail(){
				window.location.href = "${ctx}/secrecyorganization/secrecycommittee/secrecyCommittee_detail.action?_ts1356500601062";
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick="" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="###" onclick="doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="###" onclick="doBackToSecrecyCommitteeDetail()"><span>返回列表</span></a>
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
			<cp:start defaultTitle="保密委员会简介" ctx="${ctx}" icoPath="/bmp/secrecy-organization/secrecy-committee/borderlayout/skin/blue/img/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密委员会简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密委员会
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ_secrecy_committee"> </cpc:tc>
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
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增保密委员会
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecycommittee' action='secrecyCommittee_adding.action' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									保密委名称：
								</td>
								<td class="tbValue fl" colspan="3">
									${secrecyCommittee.name}
									<input type="hidden" name="secrecyCommittee.name" value="${secrecyCommittee.name}"/>
								</td>
							<tr/>
							<tr>
								<td class="tbLable fr">
									发文号：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyCommittee.docNo" name="secrecyCommittee.docNo" class="validate['length[100]'] w135" value="${secrecyCommittee.docNo}"/>
									例如：川XXXX[2012]XX号
								</td>
								<td class="tbLable fr">
									成立(发文)日期：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyCommittee.setupDate" name="secrecyCommittee.setupDate" class="Wdate validate['length[20]'] w135" value="<s:date name='#attr.secrecyCommittee.setupDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								</td>
							<tr>
							<tr>
								<td class="tbLable fr">
									主要职能：
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea id="secrecyCommittee.dutyMemberWork" name="secrecyCommittee.dutyMemberWork" rows="" cols="" style="width: 90%;height: 150px;" class="validate['length[1000]']">${secrecyCommittee.dutyMemberWork}</textarea>
									<br/>
									提示：请填写保密委员会的职能职责，以及成员分工情况。
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>

					<table class="content_table" width="100%">
						<tr>
							<td class="tbLable fr">
								发文上传：
							</td>
							<td class="tbValue fl" colspan="3">
								<attach:upload uploadBehavior="secrecyCommittee.uploadBehavior" attachments="${attachmentList}" applyForm="add_form" applyName="attachmentIds" limit="1"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr"></td>
							<td class="tbValue fl"></td>
							<td class="tbLable fr"></td>
							<td class="tbValue fl"></td>
						</tr>
					</table>

				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

	</body>
</html>