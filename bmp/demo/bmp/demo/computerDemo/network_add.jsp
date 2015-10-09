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
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增涉密网络</title>

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
					new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/DynamicTextarea/DynamicTextarea.js",function(){
				$ENV.onDomReady(function(){
					$('secrecyOffice.dutyMemberWork').setStyle('width', $('secrecyOffice.dutyMemberWork').getParent().getSize().x);
			        new DynamicTextarea('secrecyOffice.dutyMemberWork', {
			            minRows: 4
			        });
				});
			});

			function doSave(){
				window.location.href = "${ctx}/bmp/demo/computerDemo/network_list.jsp";
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
			<cp:start defaultTitle="涉密网络简介" ctx="${ctx}" icoPath="#">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','涉密网络简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密网络
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
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增涉密网络
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecyoffice' action='secrecyOffice_adding' includeParams='true'/>" method="post">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									部门名称
								</td>
								<td class="tbValue fl" colspan="3">
									<b>四川省国家保密局</b>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									涉密网络名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" class="input1"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									网络密级：
								</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'秘密','2':'机密','3':'绝密'}"
									style="width:135px;"
									theme="simple"
									name=""
									>
								</s:select><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									网络类型：
								</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'广域网','2':'城域网','2':'局域网'}"
									style="width:135px;"
									theme="simple"
									name=""
									>
								</s:select><span style="color:red;">&nbsp;&nbsp;*</span>
							</tr>


							<tr>
								<td class="tbLable fr">
									是否通过测评：
								</td>
								<td class="tbValue fl" >
								    <select style="width:135px;" name="pass1" id="pass1" onchange="selectPC();">
										<option value="1">是</option>
										<option value="2" selected="selected">否</option>
								    </select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									是否通过审批：
								</td>
								<td class="tbValue fl" >
									<select style="width:135px;" name="pass2" id="pass2" onchange="selectSP();">
										<option value="1">是</option>
										<option value="2" selected="selected">否</option>
								    </select>
							        <span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr style="display: none;" id="pass11">
								<td class="tbLable fr">
								           测评通过时间：
								</td>
								<td class="tbValue fl">
									<input type="text" name="sealingProduts.sealingProdutsDate" value=""
										class="Wdate validate['required']" id="sealingProduts.sealingProdutsDate"
										onFocus="WdatePicker();" readonly="readonly" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									测评通过机构：
								</td>
								<td class="tbValue fl">
								    <input type="text">
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>



							<tr style="display: none;" id="pass22">
								<td class="tbLable fr">
								          审批时间：
								</td>
								<td class="tbValue fl">
									<input type="text" name="sealingProduts.sealingProdutsDate" value=""
										class="Wdate validate['required']" id="sealingProduts.sealingProdutsDate"
										onFocus="WdatePicker();" readonly="readonly" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									审批文号：
								</td>
								<td class="tbValue fl">
								    <input type="text">
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>


							<tr>
								<td class="tbLable fr">
									投入使用时间：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" class="Wdate validate['length[20]'] w135" value="<s:date name='#attr.secrecyCommittee.setupDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
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
	<script type="text/javascript">
			function selectPC() {
				var pass1 = document.getElementById("pass1").value;
				if(pass1==1) {
					document.getElementById("pass11").style.display="";
				}else {
					document.getElementById("pass11").style.display="none";
				}
			}

			function selectSP() {
				var pass2 = document.getElementById("pass2").value;
				if(pass2==1) {
					document.getElementById("pass22").style.display="";
				}else {
					document.getElementById("pass22").style.display="none";
				}
			}


	</script>
</html>