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
<%@ taglib uri="http://www.cdthgk.com/tags/secrecyPerson/search" prefix="sp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/keySection" prefix="section" %>
<%@ taglib uri="http://www.cdthgk.com/tags/keyPart" prefix="part" %>
<%@ taglib uri="http://www.cdthgk.com/tags/secrecyComputer" prefix="secom" %>
<%@ taglib uri="http://www.cdthgk.com/tags/secrecyNetwork" prefix="sn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新涉密终端</title>

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
					if ("${needReload}"=="true") {
						TabUtil.closeTab();
					};
					var fc = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});

					$("sbm_button").addEvent('click', function(){
						/* if(!checkIp()){
							return false;
						} */
						if(!checkMac()){
							return false;
						}
						if (fc.isFormValid()) {
							if( confirm("确定提交吗？") ){
								$('sbm_button').style.display='none';
								$('sbm_button_hidden').style.display='';
								$('sub').click();
								window.setTimeout("$('sbm_button').style.display=''",8000);
								window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
							}
						}
					});
				});
			});

			function dealIp(){
				var ip1 = $("ip_1").value;
				var ip2 = $("ip_2").value;
				var ip3 = $("ip_3").value;
				var ip4 = $("ip_4").value;
				var ip = ip1 + "." + ip2 + "." + ip3 +"." + ip4;
				$("secrecyNetworkterminal.ipAddress").value = ip;
			}

			function dealMac(){
				var temp = /[A-Fa-f0-9]{2}/;
				var mac1 = $("mac_1").value;
				if( mac1!=null && mac1!="" && !temp.test(mac1) ){
					alert("填写内容必须为0-9和A-F组成的字符");
					$("mac_1").focus();
					return;
				}
				var mac2 = $("mac_2").value;
				if( mac2!=null && mac2!="" && !temp.test(mac2) ){
					alert("填写内容必须为0-9和A-F组成的字符");
					$("mac_2").focus();
					return;
				}
				var mac3 = $("mac_3").value;
				if( mac3!=null && mac3!="" && !temp.test(mac3) ){
					alert("填写内容必须为0-9和A-F组成的字符");
					$("mac_3").focus();
					return;
				}
				var mac4 = $("mac_4").value;
				if( mac4!=null && mac4!="" && !temp.test(mac4) ){
					alert("填写内容必须为0-9和A-F组成的字符");
					$("mac_4").focus();
					return;
				}
				var mac5 = $("mac_5").value;
				if( mac5!=null && mac5!="" && !temp.test(mac5) ){
					alert("填写内容必须为0-9和A-F组成的字符");
					$("mac_5").focus();
					return;
				}
				var mac6 = $("mac_6").value;
				if( mac6!=null && mac6!="" && !temp.test(mac6) ){
					alert("填写内容必须为0-9和A-F组成的字符");
					$("mac_6").focus();
					return;
				}
				var mac = mac1 + "-" + mac2 + "-" + mac3 +"-" + mac4 +"-" + mac5 +"-" + mac6;
				$("secrecyNetworkterminal.macAddress").value = mac;
			}

			function checkIp(){
				var temp = /^((1?d?d|(2([0-4]d|5[0-5])))\.){3}(1?d?d|(2[0-4]d|5[0-5]))$/;
				var ip = $("secrecyNetworkterminal.ipAddress").value;
				if( !temp.test(ip) ){
					alert("ip地址填写错误，请检查");
					return false;
				}
				return true;
			}

			function checkMac(){
				var temp = /[A-Fa-f0-9]{2}-[A-Fa-f0-9]{2}-[A-Fa-f0-9]{2}-[A-Fa-f0-9]{2}-[A-Fa-f0-9]{2}-[A-Fa-f0-9]{2}/;
				var mac = $("secrecyNetworkterminal.macAddress").value;
				if( !temp.test(mac) ){
					alert("MAC地址填写错误，请检查");
					return false;
				}
				return true;
			}

		</script>
	</head>
	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="###" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
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

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						接入网络
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/secrecynetworkterminal' action='secrecyNetworkterminal_save' includeParams='true'/>" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyComputer.secrecycomputerId" id="secrecyComputer.secrecycomputerId" value="${secrecyComputer.secrecycomputerId }" />
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									<b>选择需要接入的涉密网络：</b>
								</td>
								<td class="tbValue fl">
									<sn:selectComputer valueEl="secrecyNetwork.secrecyNetworkId" textEl="secrecyNetwork.name" buttonEl="x"></sn:selectComputer>
								</td>
								<td class="tbLable fr">
									责任人：
								</td>
								<td class="tbValue fl">
									${secrecyComputer.dutyPerson.name }
									<input type="hidden" name="secrecyNetworkterminal.dutyPerson" id="secrecyNetworkterminal.dutyPerson" value="${secrecyComputer.dutyPerson }" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyComputer.secrecyLevel}"></dictionary:text>
									<input type="hidden" name="secrecyNetworkterminal.secrecyLevel" id="secrecyNetworkterminal.secrecyLevel" value="${secrecyComputer.secrecyLevel}" />
								</td>
								<td class="tbLable fr">
									机型：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="computer_type" optionValue="${secrecyComputer.computerType}"></dictionary:text>
									<input type="hidden" name="secrecyNetworkterminal.computerType" id="secrecyNetworkterminal.computerType" value="${secrecyComputer.computerType}" />
								</td>
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
								<td class="tbLable fr">
								          是否属于要害部门、部位：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="is_section_part" optionValue="${secrecyComputer.isbelongKeydepartment}"></dictionary:text>
									<input type="hidden" name="secrecyNetworkterminal.isbelongKeydepartment" id="secrecyNetworkterminal.isbelongKeydepartment" value="${secrecyComputer.isbelongKeydepartment}" />
								</td>
								<td class="tbLable fr">
									是否安装保密技术防护专用系统（三合一）：
								</td>
								<td class="tbValue fl">
									<c:if test="${secrecyComputer.isFanghu eq 0 }">否</c:if>
									<c:if test="${secrecyComputer.isFanghu eq 1 }">是</c:if>
									<input type="hidden" name="secrecyNetworkterminal.isFanghu" id="secrecyNetworkterminal.isFanghu" value="${secrecyComputer.isFanghu}" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									是否纳入违规外联监控系统：
								</td>
								<td class="tbValue fl">
									<c:if test="${secrecyComputer.isWailian eq 0 }">否</c:if>
									<c:if test="${secrecyComputer.isWailian eq 1 }">是</c:if>
									<input type="hidden" name="secrecyNetworkterminal.isWailian" id="secrecyNetworkterminal.isWailian" value="${secrecyComputer.isFanghu}" />
								</td>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									${secrecyComputer.department.departmentName }
									<input type="hidden" name="secrecyNetworkterminal.department.departmentId" id="secrecyNetworkterminal.department.departmentId" value="${secrecyComputer.department.departmentId}" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
								   	IP地址：
								</td>
								<td class="tbValue fl">
									<input type="text" id="ip_1" name="ip_1" size="3" class="validate['required','digit[0,255]']" onChange="dealIp()"/>.
									<input type="text" id="ip_2" name="ip_2" size="3" class="validate['required','digit[0,255]']" onChange="dealIp()"/>.
									<input type="text" id="ip_3" name="ip_3" size="3" class="validate['required','digit[0,255]']" onChange="dealIp()"/>.
									<input type="text" id="ip_4" name="ip_4" size="3" class="validate['required','digit[0,255]']" onChange="dealIp()"/>
									<input type="hidden" id="secrecyNetworkterminal.ipAddress" name="secrecyNetworkterminal.ipAddress" class="validate['required','length[100]']"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									MAC地址：
								</td>
								<td class="tbValue fl">
									<input type="text" id="mac_1" name="mac_1" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>-
									<input type="text" id="mac_2" name="mac_2" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>-
									<input type="text" id="mac_3" name="mac_3" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>-
									<input type="text" id="mac_4" name="mac_4" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>-
									<input type="text" id="mac_5" name="mac_5" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>-
									<input type="text" id="mac_6" name="mac_6" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>
									<input type="hidden" id="secrecyNetworkterminal.macAddress" name="secrecyNetworkterminal.macAddress" class="validate['required','length[100]']" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
								   	接入网络时间：
								</td>
								<td class="tbValue fl">
										<input type="text" id="secrecyNetworkterminal.joinNetworkDate" name="secrecyNetworkterminal.joinNetworkDate"
									class="Wdate validate['required','length[20]'] w130" value="<s:date name='#attr.secrecyNetworkterminal.joinNetworkDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									&nbsp;
								</td>
								<td class="tbValue fl">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									接入网络说明：
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea style="width: 80%;height: 100px;" id="secrecyNetworkterminal.joinNetworkReason" name="secrecyNetworkterminal.joinNetworkReason" class="validate['length[1000]'] "></textarea>
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
</html>