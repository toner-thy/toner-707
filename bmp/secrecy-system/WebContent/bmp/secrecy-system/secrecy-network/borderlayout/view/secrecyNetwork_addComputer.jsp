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
<%@ taglib uri="http://www.cdthgk.com/tags/organization/organ" prefix="org"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/secrecyPerson/search" prefix="sp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/keySection" prefix="section" %>
<%@ taglib uri="http://www.cdthgk.com/tags/keyPart" prefix="part" %>
<%@ taglib uri="http://www.cdthgk.com/tags/secrecyComputer" prefix="secreyComputer" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密网络接入计算机</title>

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
					var needReload = ${needReload};
					if (needReload){
						if (!confirm('新增成功，是否继续添加？')){
							needReload2 = true;
							TabUtil.closeTab();
						};
					};
					 var fc = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});

					$("sbm_button").addEvent('click', function(){
						if(!chkDiskSeq()){
							return;
						}
						if(!chkComputerNo()){
							return;
						}
						var secrecyComputerId = $('secrecyNetworkterminalArray[0].secrecyComputer.secrecycomputerId').value;
						var checkType = "add";
						if( secrecyComputerId!=null && secrecyComputerId!="" ){
							checkType = "edit";
						}else{
							checkType = "add";
						}
						new Request.JSON({
							url: '${ctx}/bmp/secrecycomputer/secrecyComputer_chkDiskSeq.action',
						    onComplete: function(result, text){
						    	if (result.chkDiskSeqFlag != null) {
									if ("false" == result.chkDiskSeqFlag){
										alert("硬盘序列号已在系统中存在，请修改");
										$('secrecyNetworkterminalArray[0].secrecyComputer.diskSeq').focus();
										returnValue = false;
									}else{
										new Request.JSON({
											url: '${ctx}/bmp/secrecycomputer/secrecyComputer_chkComputerNo.action',
										    onComplete: function(result, text){
										    	if (result.chkComputerNoFlag != null) {
													if ("false" == result.chkComputerNoFlag){
														alert("计算机编号已存在，请检查并修改");
														$('secrecyNetworkterminalArray[0].secrecyComputer.computerNo').focus();
													}else{
														saveFunction();
													}
										    	}else{
										    		alert("系统故障，请联系管理员处理");
										    	}
										    }
										}).get({
											'secrecyComputer.computerNo' : $('secrecyNetworkterminalArray[0].secrecyComputer.computerNo').value,
											'secrecyComputer.secrecycomputerId' : $('secrecyNetworkterminalArray[0].secrecyComputer.secrecycomputerId').value,
											'checkType' : checkType,
										    '_ts': new Date().getTime()
										});
									}
						    	}else{
						    		alert("系统故障，请联系管理员处理");
						    		returnValue = false;
						    	}
						    }
						}).get({
							'secrecyComputer.diskSeq ' : $('secrecyNetworkterminalArray[0].secrecyComputer.diskSeq').value,
							'secrecyComputer.secrecycomputerId' : $('secrecyNetworkterminalArray[0].secrecyComputer.secrecycomputerId').value,
							'checkType' : checkType,
						    '_ts': new Date().getTime()
						});
					});

					function saveFunction(){
						/* if(!checkIp()){
							return false;
						} */
						if(!checkMac()){
							return false;
						}
						if (fc.isFormValid()){
							if( confirm("确定提交吗？")){
								$('sub').click();
								$('sbm_button').style.display='none';
								$('sbm_button_hidden').style.display='';
								window.setTimeout("$('sbm_button').style.display=''",8000);
								window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
							}
						}
					}

				});
			});
		</script>
		<script type="text/javascript">
			function secrecyComputerDealData(element, value){
				//$('secrecyNetworkterminalArray[0].secrecyComputer.secrecycomputerId').value
				//alert(value.data["isNetTerminal"]);//是否已经是涉密终端
				//alert(value.data[secrecycomputerId]);//id 判断是否出现过
				/* if( value.data["isNetTerminal"] == '1' ){
					alert("该计算机已接入网络，不能同时接入其它网络");
					$("secrecyNetworkterminalArray[0].secrecyComputer.secrecycomputerId").set("value",null);
					$("secrecyNetworkterminalArray[0].secrecyComputer.computerNo").set("value",null);
					return;
				} */
				new Request.JSON({
					url: '${ctx}/bmp/secrecycomputer/secrecyComputer_ajax_isTerminalOfkeyPart.action',
				    onComplete: function(result, text){
				    	if (result.isNetTerminal != null) {
							if ("1" == result.isNetTerminal){
								alert("该计算机已接入网络，不能同时接入其它网络");
								$("secrecyNetworkterminalArray[0].secrecyComputer.secrecycomputerId").set("value",null);
								$("secrecyNetworkterminalArray[0].secrecyComputer.computerNo").set("value",null);
							}else{
								//填充数据
								if( result.isbelongKeydepartment == "1" ){
									$('secrecyNetworkterminalArray[0].secrecyComputer.isbelongKeydepartment_1').set("checked","checked");
									showKeysectionpart(1);
									if( result.keySectionId!=null && result.keySectionId!="" ){
										$('keysectionpart_ks').set("checked","checked");
										doChangeType(1);
										$('secrecyNetworkterminalArray[0].secrecyComputer.keySection.keySectionId').value=result.keySectionId;
										$('secrecyNetworkterminalArray[0].secrecyComputer.keySection.department.departmentName').value=result.keySectionName;
									}
									if( result.partId!=null && result.partId!="" ){
										$('keysectionpart_kp').set("checked","checked");
										doChangeType(2);
										$('secrecyNetworkterminalArray[0].secrecyComputer.keyPart.partId').value=result.partId;
										$('secrecyNetworkterminalArray[0].secrecyComputer.keyPart.partName').value=result.keyPartName;
									}
								}else{
									$('secrecyNetworkterminalArray[0].secrecyComputer.isbelongKeydepartment_0').set("checked","checked");
									showKeysectionpart(0);
								}
								$('secrecyNetworkterminalArray[0].secrecyComputer.dutyPerson.userInfoId').value = result.userInfoId;
								$('secrecyNetworkterminalArray[0].secrecyComputer.dutyPerson.name').value = result.userInfoName;
								$('secrecyNetworkterminalArray[0].secrecyComputer.secrecyLevel').value = result.secrecyLevel;
								$('secrecyNetworkterminalArray[0].secrecyComputer.computerType').value = result.computerType;
								$('secrecyNetworkterminalArray[0].secrecyComputer.diskSeq').value = result.diskSeq;
								$('secrecyNetworkterminalArray[0].secrecyComputer.isFanghu').value = result.isFanghu;
								$('secrecyNetworkterminalArray[0].secrecyComputer.isWailian').value = result.isWailian;
								$('secrecyNetworkterminalArray[0].secrecyComputer.department.departmentId').value = result.departmentId;
								$('secrecyNetworkterminalArray[0].secrecyComputer.department.departmentName').value = result.departmentName;
							}
				    	}
				    }
				}).get({
					'secrecyComputer.secrecycomputerId' : value.value,
				    '_ts': new Date().getTime()
				});
			}
			// 隐藏、显示是否属于要害部门、部位
			function showKeysectionpart(value){
				if(value == 1){
					$('dept_img').style.display = 'none';
					$('keysectionpart_show').style.display = '';
					$('secrecyNetworkterminalArray[0].secrecyComputer.department.departmentName').disabled = 'disabled';
				} else {
					$('dept_img').style.display = '';
					$('keysectionpart_show').style.display = 'none';
					$('secrecyNetworkterminalArray[0].secrecyComputer.department.departmentName').disabled = '';
					$('secrecyNetworkterminalArray[0].secrecyComputer.department.departmentId').value = '';
					$('secrecyNetworkterminalArray[0].secrecyComputer.department.departmentName').value = '';
					$('secrecyNetworkterminalArray[0].secrecyComputer.keyPart.partId').value = '';
					$('secrecyNetworkterminalArray[0].secrecyComputer.keyPart.partName').value = '';
					$('secrecyNetworkterminalArray[0].secrecyComputer.keySection.keySectionId').value = '';
					$('secrecyNetworkterminalArray[0].secrecyComputer.keySection.department.departmentName').value = '';
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
					$('secrecyNetworkterminalArray[0].secrecyComputer.keyPart.partId').value = '';
					$('secrecyNetworkterminalArray[0].secrecyComputer.keyPart.partName').value = '';
				} else {
					//显示要害部位, 隐藏要害部门信息及值
					$('keypart_show1').style.display = '';
					$('keypart_show2').style.display = '';
					$('keysection_show1').style.display = 'none';
					$('keysection_show2').style.display = 'none';
					$('secrecyNetworkterminalArray[0].secrecyComputer.keySection.keySectionId').value = '';
					$('secrecyNetworkterminalArray[0].secrecyComputer.keySection.department.departmentName').value = '';
				}
			}
			// 要害部门-----回填部门名称
			function doSelectKeySection(elements,data){
				$('secrecyNetworkterminalArray[0].secrecyComputer.department.departmentId').value = data.value;
				$('secrecyNetworkterminalArray[0].secrecyComputer.department.departmentName').value = data.text;
			}
			// 要害部位-----回填部门名称
			function doSelectKeyPart(elements,data){
				new Request.JSON({
					url: '${ctx}/bmp/part/ajax_departmentOfkeyPart.action',
				    onComplete: function(result, text){
				    	if (result!= null) {
				    		$('secrecyNetworkterminalArray[0].secrecyComputer.department.departmentId').value = result.departmentId;
				    		$('secrecyNetworkterminalArray[0].secrecyComputer.department.departmentName').value = result.departmentName;
				    	}else{
				    		alert("系统故障，请联系管理员处理");
				    	}
				    }
				}).get({
					'part.partId' : $('secrecyNetworkterminalArray[0].secrecyComputer.keyPart.partId').value,
				    '_ts': new Date().getTime()
				});
				//$('secrecyComputer.department.departmentId').value = data.data["department.departmentId"];
				//$('secrecyComputer.department.departmentName').value = data.data["department.departmentName"];
			}

			function chkDiskSeq(){
				var returnValue = true;
				var diskSeq = $('secrecyNetworkterminalArray[0].secrecyComputer.diskSeq').value;
				if( diskSeq==null || diskSeq=="" ){
					alert("请填写硬盘序列号");
					$('secrecyNetworkterminalArray[0].secrecyComputer.diskSeq').focus();
					returnValue = false;
				}
				return returnValue;
			}

			function chkComputerNo(){
				var returnValue = true;
				var computerNo = $('secrecyNetworkterminalArray[0].secrecyComputer.computerNo').value;
				if( computerNo==null || computerNo=="" ){
					alert("请填写计算机编号");
					$('secrecyNetworkterminalArray[0].secrecyComputer.computerNo').focus();
					returnValue = false;
				}
				return returnValue;
			}

			function dealIp(){
				var ip1 = $("ip_1").value;
				var ip2 = $("ip_2").value;
				var ip3 = $("ip_3").value;
				var ip4 = $("ip_4").value;
				var ip = ip1 + "." + ip2 + "." + ip3 +"." + ip4;
				$("secrecyNetworkterminalArray[0].ipAddress").value = ip;
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
				$("secrecyNetworkterminalArray[0].macAddress").value = mac;
			}

			function checkIp(){
				var temp = /^(([3-9]d?|[01]d{0,2}|2d?|2[0-4]d|25[0-5]).){3}([3-9]d?|[01]d{0,2}|2d?|2[0-4]d|25[0-5])/;
				var ip = $("secrecyNetworkterminalArray[0].ipAddress").value;
				if( !temp.test(ip) ){
					alert("ip地址填写错误，请检查");
					return false;
				}
				return true;
			}

			function checkMac(){
				var temp = /[A-Fa-f0-9]{2}-[A-Fa-f0-9]{2}-[A-Fa-f0-9]{2}-[A-Fa-f0-9]{2}-[A-Fa-f0-9]{2}-[A-Fa-f0-9]{2}/;
				var mac = $("secrecyNetworkterminalArray[0].macAddress").value;
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
						涉密网络
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/secrecynetworkterminal' action='secrecyNetworkterminal_saveTerminals.action' includeParams='true'/>" method="post">
						<!-- 隐藏域  -->
						<input type="hidden" id="secrecyNetwork.secrecyNetworkId" name="secrecyNetwork.secrecyNetworkId" value="${secrecyNetwork.secrecyNetworkId }"/>
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									涉密网络名称：
								</td>
								<td class="tbValue fl">
									${secrecyNetwork.name }
								</td>
								<td class="tbLable fr">
									部门名称
								</td>
								<td class="tbValue fl">
									${secrecyNetwork.department.departmentName }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									网络密级：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyNetwork.secrecyLevel}"></dictionary:text>
								</td>
								<td class="tbLable fr">
									网络类型：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="network_type" optionValue="${secrecyNetwork.networkType}"></dictionary:text>
								</td>
							</tr>
						</table>
						<div class="panel_header">
							<div class="panel_title panel_titleListIco">
								涉密计算机终端
							</div>
							<div class="panel_btn_bar pop_button_bar">
								<!-- 右侧按钮 -->
							</div>
						</div>
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									涉密计算机：
								</td>
								<td class="tbValue fl">
									<secreyComputer:selectComputer valueEl="secrecyNetworkterminalArray[0].secrecyComputer.secrecycomputerId" textEl="secrecyNetworkterminalArray[0].secrecyComputer.computerNo" buttonEl="sna[0]" dataSelector="secrecyComputerSelector" onlyFromValue="false" onSelect="secrecyComputerDealData"></secreyComputer:selectComputer>
									<input type="hidden" id="secrecyNetworkterminalArray[0].secrecyComputer.createOrgan.organId" name="secrecyNetworkterminalArray[0].secrecyComputer.createOrgan.organId" value="${_.login.actor.user.organ.organId }"/>
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
									责任人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyNetworkterminalArray[0].secrecyComputer.dutyPerson.userInfoId" textEl="secrecyNetworkterminalArray[0].secrecyComputer.dutyPerson.name" required="true" onlyFromValue="false" value="${secrecyNetworkterminalArray[0].secrecyComputer.dutyPerson.userInfoId}" text="${secrecyNetworkterminalArray[0].secrecyComputer.dutyPerson.name}" buttonEl="btnEl"></ui:selectByOrgan>
								</td>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyNetworkterminalArray[0].secrecyComputer.secrecyLevel" name="secrecyNetworkterminalArray[0].secrecyComputer.secrecyLevel"
									 title="true" titleText="请选择" styleClass="validate['required']" style="width: 130px;" optionValue="${secrecyNetworkterminalArray[0].secrecyComputer.secrecyLevel}"></dictionary:select><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									机型：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="computer_type" id="secrecyNetworkterminalArray[0].secrecyComputer.computerType" name="secrecyNetworkterminalArray[0].secrecyComputer.computerType"
									 title="true" titleText="请选择" styleClass="validate['required']" style="width: 130px;" optionValue="${secrecyNetworkterminalArray[0].secrecyComputer.computerType}"></dictionary:select><span style="color:red;">&nbsp;&nbsp;*</span>
								<td class="tbLable fr">
									硬盘序列号：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyNetworkterminalArray[0].secrecyComputer.diskSeq" name="secrecyNetworkterminalArray[0].secrecyComputer.diskSeq"  class="validate['required','length[100]']"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									是否属于要害部门、部位：
								</td>
								<td class="tbValue fl">
									<c:forEach items="${sectionPartList}" var="sp" varStatus="spStat">
										<input type="radio" id="secrecyNetworkterminalArray[0].secrecyComputer.isbelongKeydepartment_${sp.optionValue }" name="secrecyNetworkterminalArray[0].secrecyComputer.isbelongKeydepartment" value="${sp.optionValue}" onclick="showKeysectionpart(this.value)"
											<c:if test="${spStat.index == 1}">checked="checked"</c:if>/>${sp.optionText }
									</c:forEach>
								</td>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="secrecyNetworkterminalArray[0].secrecyComputer.department.departmentId" textEl="secrecyNetworkterminalArray[0].secrecyComputer.department.departmentName"
										required="true" onlyFromValue="false" buttonEl="dept_img"/>
								</td>
							</tr>
							<tr id="keysectionpart_show" style="display: none">
								<td class="tbLable fr">
									请选择要害部门、部位：
								</td>
								<td class="tbLable fl">
									<input type="radio" id="keysectionpart_ks" name="keysectionpart" checked="checked" value="1" onclick="doChangeType(this.value)">要害部门
									<input type="radio" id="keysectionpart_kp" name="keysectionpart" value="2" onclick="doChangeType(this.value)">要害部位
								</td>
								<td class="tbLable fr" id="keysection_show1" style="display: ''">
									要害部门：
								</td>
								<td class="tbValue fl" id="keysection_show2" style="display: ''">
									<section:selectKeySection valueEl="secrecyNetworkterminalArray[0].secrecyComputer.keySection.keySectionId" textEl="secrecyNetworkterminalArray[0].secrecyComputer.keySection.department.departmentName"
										buttonEl="aa" required="false" onSelect="doSelectKeySection"></section:selectKeySection><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr" id="keypart_show1" style="display: none">
									要害部位：
								</td>
								<td class="tbValue fl" id="keypart_show2" style="display: none">
									<part:selectKeyPart valueEl="secrecyNetworkterminalArray[0].secrecyComputer.keyPart.partId" textEl="secrecyNetworkterminalArray[0].secrecyComputer.keyPart.partName"
										buttonEl="bb" required="false" onSelect="doSelectKeyPart" dataSelector="kePartSelector"></part:selectKeyPart><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									是否安装保密技术防护专用系统（三合一）：
								</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'是','0':'否'}"
									style="width:135px;"
									theme="simple"
									name="secrecyNetworkterminalArray[0].secrecyComputer.isFanghu"
									id="secrecyNetworkterminalArray[0].secrecyComputer.isFanghu"
									>
								</s:select><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									是否纳入违规外联监控系统：
								</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'是','0':'否'}"
									style="width:135px;"
									theme="simple"
									name="secrecyNetworkterminalArray[0].secrecyComputer.isWailian"
									id="secrecyNetworkterminalArray[0].secrecyComputer.isWailian"
									>
								</s:select><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									接入网络时间：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyNetworkterminalArray[0].joinNetworkDate" name="secrecyNetworkterminalArray[0].joinNetworkDate"
									class="Wdate validate['required','length[20]'] w130" value="<s:date name='#attr.secrecyNetworkterminalArray[0].joinNetworkDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
								   	IP地址：
								</td>
								<td class="tbValue fl">
									<input type="text" id="ip_1" name="ip_1" size="3" class="validate['required','digit[0,255]']" onChange="dealIp()"/>.
									<input type="text" id="ip_2" name="ip_1" size="3" class="validate['required','digit[0,255]']" onChange="dealIp()"/>.
									<input type="text" id="ip_3" name="ip_1" size="3" class="validate['required','digit[0,255]']" onChange="dealIp()"/>.
									<input type="text" id="ip_4" name="ip_1" size="3" class="validate['required','digit[0,255]']" onChange="dealIp()"/>
									<input type="hidden" id="secrecyNetworkterminalArray[0].ipAddress" name="secrecyNetworkterminalArray[0].ipAddress" class="validate['required','length[100]']"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									MAC地址：
								</td>
								<td class="tbValue fl">
									<input type="text" id="mac_1" name="mac_1" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>-
									<input type="text" id="mac_2" name="mac_1" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>-
									<input type="text" id="mac_3" name="mac_1" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>-
									<input type="text" id="mac_4" name="mac_1" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>-
									<input type="text" id="mac_5" name="mac_1" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>-
									<input type="text" id="mac_6" name="mac_1" size="2" class="validate['required','length[2]']" onChange="dealMac()"/>
									<input type="hidden" id="secrecyNetworkterminalArray[0].macAddress" name="secrecyNetworkterminalArray[0].macAddress" class="validate['required','length[100]']" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
 									&nbsp;
								</td>
								<td class="tbValue fl">
									&nbsp;
								</td>
							</tr>
						</table>
							<%--
							<table class="content_table" width="100%">
								<tr>
									<td class="tbLable fr">
										涉密计算机：
									</td>
									<td class="tbValue fl">
										<secreyComputer:selectComputer valueEl="secrecyNetworkterminalArray[1].secrecyComputer.secrecycomputerId" textEl="secrecyNetworkterminalArray[1].secrecyComputer.computerNo" buttonEl="sna[1]"></secreyComputer:selectComputer>
									</td>
									<td class="tbLable fr">
										&nbsp;
									</td>
									<td class="tbValue fl">
										&nbsp;
									</td>
								</tr>
							</table>
							 --%>


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