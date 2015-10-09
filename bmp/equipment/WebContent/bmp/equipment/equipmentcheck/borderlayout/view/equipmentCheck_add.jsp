<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="userinfo" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>新增保密装备配备检测</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<s:actionmessage theme="messages"/>

		<script language="javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			var needReload = ${needReload};
			var needReload2 = false;
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('是否继续添加！')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('form_equipmentcheck_add',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					//Util.selectOrgan("organName", "organName", "organId");
					//Util.selectUser("userName", "userName", "userId");
				});
			});

			function doSave(){
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",5000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",5000);
				}
			}
			/**
			function doBack(){
				window.location.href = "${ctx}/bmp/equipmentcheck/equipmentCheck_list.action";
			}
			**/
			function doBack(){
			 	TabUtil.closeTab();
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:TabUtil.refreshTab();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密装备配备检测简介" ctx="${ctx}" icoPath="${ctx}/equipment/equipmentcheck/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密装备配备检测简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密装备配备检测
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_equipmentcheck_info"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增保密装备配备记录
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_equipmentcheck_add" class="form" action="<s:url namespace="/bmp/equipmentcheck" action="equipmentCheck_adding" includeParams="true"/>" method="post">
						<table class="content_table">
							<tr bgcolor="#F3FBFE" >
								<td colspan="11" align="center" height="60">
									<font style="font-size:24px;font-weight: bold; font-family: '楷体_gb2312';">
										保密装备配备检测
									</font>
								</td>
							</tr>
							<tr>
								<td align="right" width="15%">
									检测名称：
								</td>
								<td align="left" width="30%">
									<input class="validate['required','length[20]'] ipt" type="text" name="equipmentCheck.equipmentName" value="${equipmentCheck.equipmentName}" />
									<span style="color:red;">*</span>
								</td>
								<td align="right" width="20%">
									装备所属单位：
								</td>
								<td align="left" width="40%">
									<input class="ipt validate['length[20]']" type="text" title="" name="equipmentCheck.equipmentOrgan" value="${equipmentCheck.equipmentOrgan}" />
								</td>
						 	</tr>
						 	<tr>
							 	<td align="right">
									装备型号：
								</td>
								<td align="left">
									<input class="validate['required','length[150]'] ipt" type="text" name="equipmentCheck.equipmentType" value="${equipmentCheck.equipmentType}" />
									<span style="color:red;">*</span>
								</td>
								<td align="right">
							 		数 量：
								</td>
								<td align="left">
									<input class="validate['required','digit[0,100000]','length[8]'] ipt" type="text" name="equipmentCheck.number" value="${equipmentCheck.number}" />
									<span style="color:red;">*</span>
								</td>
						 	</tr>
						 	<tr>
							 	<td align="right">
									产 地：
								</td>
								<td align="left">
									<input class="validate['length[50]'] ipt" type="text" name="equipmentCheck.equipmentProducingArea" value="${equipmentCheck.equipmentProducingArea}" />
								</td>
								<td align="right">
									检查时间：
								</td>
								<td align="left">
									<input type="text" id="time" name="equipmentCheck.checkTime" title="检查时间" class="Wdate ipt validate['required']"
									onFocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd',readOnly:true})" readonly="readonly" /> <span style="color:red;">*</span>
								</td>
							</tr>
						 	<tr>
								<td align="right">
							 		用 途：
								</td>
								<td align="left" colspan="3">
									<textarea name="equipmentCheck.equipmentPurpose" class="validate['length[300]']" style="width: 95%; height: 100px;">${equipmentCheck.equipmentPurpose}</textarea>
								</td>
						 	</tr>

						 	<tr>
							 	<td align="right">
									检查单位：
								</td>
								<td align="left">
									<%-- <input class="validate['required','length[39]'] ipt" type="text" value="${organName }" title="请选择" id="organName" readonly="readonly" />
								 	<input type="hidden" name="equipmentCheck.checkOrgan.organId" value="${organId }" id="organId" />
								 	<span style="color:red;">*</span> --%>
								 	<organ:select textEl="equipmentCheck.checkOrgan.organName" valueEl="equipmentCheck.checkOrgan.organId" onlyFromValue="true"
										required="true" buttonEl="selectcheckOrganName"
										value="${organId}" />
								</td>
								<td align="right" style="padding-left:5px ">
							 		检查人：
								</td>
								<td align="left">
									<%-- <input class="validate['required','length[39]'] ipt" type="text" value="${userName }" title="请选择" id=userName readonly="readonly" />
								 	<input type="hidden" name="equipmentCheck.checkPerson.userId" value="${userId }" id="userId" /> --%>
								 	<%-- <userinfo:multySelect textEl="equipmentCheck.checkPerson.name" valueEl="equipmentCheck.checkPerson.userInfoId" onlyFromValue="true"
										required="true" buttonEl="selectcheckPersonName"
										value="${userId}" valueProperty="equipmentCheck.checkPerson.userInfoId" textProperty="name"/> --%>
								 	<userinfo:select textEl="equipmentCheck.checkPerson.name" valueEl="equipmentCheck.checkPerson.userInfoId" onlyFromValue="true"
										required="true" buttonEl="selectcheckPersonName"
										value="${userId}" />
								</td>
						 	</tr>

						 	<tr>
							 	<td align="right">
									检查内容：
								</td>
								<td align="left" colspan="3">
									<textarea name="equipmentCheck.checkContent" class="validate['length[300]']" style="width: 95%; height: 100px;">${equipmentCheck.checkContent}</textarea>
								</td>
						 	</tr>

						 	<tr>
							 	<td align="right">
									备 注：
								</td>
								<td align="left" colspan="3">
									<textarea name="equipmentCheck.remark" class="validate['length[300]']" style="width: 95%; height: 50px;">${equipmentCheck.remark}</textarea>
								</td>
						 	</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;"/>
						</div>
					</form>
				</div>
			</div>
		<!-- 内容panel结束-->
		</div>
	</body>
</html>