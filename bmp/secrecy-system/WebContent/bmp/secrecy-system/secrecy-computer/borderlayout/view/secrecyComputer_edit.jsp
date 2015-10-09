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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑涉密计算机</title>

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
					if (needReload) {
						if (!confirm('编辑成功，是否继续编辑？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					var fc = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					// 页面加载完成，执行选择是否要害部门、部位
					showKeysectionpart('${secrecyComputer.isbelongKeydepartment}');
					var type = '${secrecyComputer.keySection.keySectionId}';
					if(type == ''){
						type = 2;
					} else {
						type = 1;
					}
					doChangeType(type);

					$('sbm_button').addEvent('click', function(){
						if(!chkDiskSeq()){
							return;
						}
						if(!chkComputerNo()){
							return;
						}
						new Request.JSON({
							url: '${ctx}/bmp/secrecycomputer/secrecyComputer_chkDiskSeq.action',
						    onComplete: function(result, text){
						    	if (result.chkDiskSeqFlag != null) {
									if ("false" == result.chkDiskSeqFlag){
										alert("硬盘序列号已在系统中存在，请修改");
										$('secrecyComputer.diskSeq').focus();
										returnValue = false;
									}else{
										new Request.JSON({
											url: '${ctx}/bmp/secrecycomputer/secrecyComputer_chkComputerNo.action',
										    onComplete: function(result, text){
										    	if (result.chkComputerNoFlag != null) {
													if ("false" == result.chkComputerNoFlag){
														alert("计算机编号已存在，请检查并修改");
														$('secrecyComputer.computerNo').focus();
													}else{
														saveFunction();
													}
										    	}else{
										    		alert("系统故障，请联系管理员处理");
										    	}
										    }
										}).get({
											'secrecyComputer.computerNo' : $('secrecyComputer.computerNo').value,
											'secrecyComputer.secrecycomputerId' : $('secrecyComputer.secrecycomputerId').value,
											'checkType' : 'edit',
										    '_ts': new Date().getTime()
										});
									}
						    	}else{
						    		alert("系统故障，请联系管理员处理");
						    		returnValue = false;
						    	}
						    }
						}).get({
							'secrecyComputer.diskSeq ' : $('secrecyComputer.diskSeq').value,
							'secrecyComputer.secrecycomputerId' : $('secrecyComputer.secrecycomputerId').value,
							'checkType' : 'edit',
						    '_ts': new Date().getTime()
						});
					});
					function saveFunction(){
						var radios = document.getElementsByName("secrecyComputer.isbelongKeydepartment");
						var selectedValue = "";
						$each(radios, function(aTag, index){
						    if($(aTag).get('checked')){
						    	selectedValue = $(aTag).get('value');
						    }
						});

						if( selectedValue == "1"){
							var subRadios = document.getElementsByName("keysectionpart");
							var selectedSubValue = "";
							$each(subRadios, function(aTag, index){
							    if($(aTag).get('checked')){
							    	selectedSubValue = $(aTag).get('value');
							    }
							});
							if( selectedSubValue == "1" ){
								if( $('secrecyComputer.keySection.keySectionId').get('value')=="" || $('secrecyComputer.keySection.department.departmentName').get('value')=="" ){
									alert("请选择要害部门。");
									return;
								}
							}
							if( selectedSubValue == "2" ){
								if( $('secrecyComputer.keyPart.partId').get('value')=="" || $('secrecyComputer.keyPart.partName').get('value')=="" ){
									alert("请选择要害部位。");
									return;
								}
							}
							if($('secrecyComputer.department.departmentId').get('value')=="" || $('secrecyComputer.department.departmentName').get('value')=="" ){
								alert("请填写部门名称。");
								return;
							}
						}
						if (fc.isFormValid(true)) {
							$('sub').click();
							$('sbm_button').style.display='none';
							$('sbm_button_hidden').style.display='';
							window.setTimeout("$('sbm_button').style.display=''",8000);
							window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
						}
					}
				});
			});
			var needReload = ${needReload};
			function doBack2(){
				TabUtil.closeTab();
			}

			// 隐藏、显示是否属于要害部门、部位
			function showKeysectionpart(value){
				if(value == 1){
					$('dept_img').style.display = 'none';
					$('keysectionpart_show').style.display = '';
					$('secrecyComputer.department.departmentName').disabled = 'disabled';
				} else {
					$('dept_img').style.display = '';
					$('keysectionpart_show').style.display = 'none';
					$('secrecyComputer.department.departmentName').disabled = '';
					$('secrecyComputer.department.departmentId').value = '${secrecyComputer.department.departmentId}';
					$('secrecyComputer.keyPart.partId').value = '';
					$('secrecyComputer.keyPart.partName').value = '';
					$('secrecyComputer.keySection.keySectionId').value = '';
					$('secrecyComputer.keySection.department.departmentName').value = '';
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
					$('secrecyComputer.keyPart.partId').value = '';
					$('secrecyComputer.keyPart.partName').value = '';
					$('keysectionpart1').checked = "checked" ;
					$('secrecyComputer.keySection.keySectionId').value = '${secrecyComputer.keySection.keySectionId}';
				} else {
					//显示要害部位, 隐藏要害部门信息及值
					$('keypart_show1').style.display = '';
					$('keypart_show2').style.display = '';
					$('keysection_show1').style.display = 'none';
					$('keysection_show2').style.display = 'none';
					$('secrecyComputer.keySection.keySectionId').value = '';
					$('secrecyComputer.keySection.department.departmentName').value = '';
					$('keysectionpart2').checked = "checked" ;
					$('secrecyComputer.keyPart.partId').value = '${secrecyComputer.keyPart.partId}';
				}
			}
			// 要害部门-----回填部门名称
			function doSelectKeySection(elements,data){
				$('secrecyComputer.department.departmentId').value = data.value;
				$('secrecyComputer.department.departmentName').value = data.text;
			}
			// 要害部位-----回填部门名称
			function doSelectKeyPart(elements,data){
				new Request.JSON({
					url: '${ctx}/bmp/part/ajax_departmentOfkeyPart.action',
				    onComplete: function(result, text){
				    	if (result!= null) {
				    		$('secrecyComputer.department.departmentId').value = result.departmentId;
				    		$('secrecyComputer.department.departmentName').value = result.departmentName;
				    	}else{
				    		alert("系统故障，请联系管理员处理");
				    	}
				    }
				}).get({
					'part.partId' : $('secrecyComputer.keyPart.partId').value,
				    '_ts': new Date().getTime()
				});

				//$('secrecyComputer.department.departmentId').value = data.data["department.departmentId"];
				//$('secrecyComputer.department.departmentName').value = data.data["department.departmentName"];
			}
			function chkDiskSeq(){
				var returnValue = true;
				var diskSeq = $('secrecyComputer.diskSeq').value;
				if( diskSeq==null || diskSeq=="" ){
					alert("请填写硬盘序列号");
					$('secrecyComputer.diskSeq').focus();
					returnValue = false;
				}
				return returnValue;
			}

			function chkComputerNo(){
				var returnValue = true;
				var computerNo = $('secrecyComputer.computerNo').value;
				if( computerNo==null || computerNo=="" ){
					alert("请填写计算机编号");
					$('secrecyComputer.computerNo').focus();
					returnValue = false;
				}
				return returnValue;
			}
			//显示层
			function disOne(id) {
				$(id).style.display="";
			}

			//消失层
			function noneOne(id){
				$(id).style.display="none";
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
			<cp:start defaultTitle="涉密计算机简介" ctx="${ctx}" icoPath="/bmp/secrecy-system/secrecy-computer/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','涉密计算机简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密计算机
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_computer"> </cpc:tc>
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
						新增涉密计算机
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/secrecycomputer' action='secrecyComputer_editing.action' includeParams='true'/>" method="post">
						<input type="hidden" id="secrecyComputer.secrecycomputerId" name="secrecyComputer.secrecycomputerId" value="${secrecyComputer.secrecycomputerId }" />
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									单位名称
								</td>
								<td class="tbValue fl" colspan="3">
									<b>${_.login.actor.user.organ.name }</b>
									<input type="hidden" id="secrecyComputer.createOrgan.organId" name="secrecyComputer.createOrgan.organId" value="${_.login.actor.user.organ.organId }"/>
								</td>
							</tr>
							<tr>

								<td class="tbLable fr">
									责任人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyComputer.dutyPerson.userInfoId" textEl="secrecyComputer.dutyPerson.name" required="true" onlyFromValue="false" value="${secrecyComputer.dutyPerson.userInfoId}" text="${secrecyComputer.dutyPerson.name}" buttonEl="btnEl"></ui:selectByOrgan>
									<%-- <sp:selectSecrecyPerson valueEl="secrecyComputer.dutyPerson.userInfoId" textEl="secrecyComputer.dutyPerson.name" value="${secrecyComputer.dutyPerson.userInfoId }" text="${secrecyComputer.dutyPerson.name }" buttonEl="secrecyPersonName" /> --%>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('expid')" onmouseout="noneOne('expid')"  />
									<div id="expid"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											【责任人】 填写涉密计算机使用人。
											1 人使用多台涉密计算机，责任人按 1 人统计；
											多人共用 1 台涉密计算机，明确1人为责任人。
										<br/>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyComputer.secrecyLevel" name="secrecyComputer.secrecyLevel"
									 title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyComputer.secrecyLevel}"></dictionary:select><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									机型：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="computer_type" id="secrecyComputer.computerType" name="secrecyComputer.computerType"
									 title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyComputer.computerType}"></dictionary:select><span style="color:red;">&nbsp;&nbsp;*</span>
							</tr>
							<tr>
								<td class="tbLable fr">
									编号：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyComputer.computerNo" name="secrecyComputer.computerNo" class="validate['required','length[100]']" value="${secrecyComputer.computerNo }"/><span style="color:red;">&nbsp;&nbsp;*</span>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('computerNo_helpId')" onmouseout="noneOne('computerNo_helpId')"  />
									<div id="computerNo_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											按本单位信息化管理部门的台账编号填写。
										<br/>
									</div>
								</td>
								<td class="tbLable fr">
									硬盘序列号：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyComputer.diskSeq" name="secrecyComputer.diskSeq"  class="validate['required','length[100]']" value="${secrecyComputer.diskSeq }"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									是否属于要害部门、部位：
								</td>
								<td class="tbValue fl">
									<c:forEach items="${sectionPartList}" var="sp">
										<input type="radio" name="secrecyComputer.isbelongKeydepartment" value="${sp.optionValue}" onclick="showKeysectionpart(this.value)"
											<c:if test="${secrecyComputer.isbelongKeydepartment == sp.optionValue}">checked="checked"</c:if>/>${sp.optionText }
									</c:forEach>
								</td>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="secrecyComputer.department.departmentId" textEl="secrecyComputer.department.departmentName"
										value="${secrecyComputer.department.departmentId }"
										text="${secrecyComputer.department.departmentName }" required="true" onlyFromValue="false" buttonEl="dept_img"/>
								</td>
							</tr>
							<tr id="keysectionpart_show" style="display: none">
								<td class="tbLable fr">
									请选择要害部门、部位：
								</td>
								<td class="tbLable fl">
									<input type="radio" id="keysectionpart1" name="keysectionpart" value="1" onclick="doChangeType(this.value)">要害部门
									<input type="radio" id="keysectionpart2" name="keysectionpart" value="2" onclick="doChangeType(this.value)">要害部位
								</td>
								<td class="tbLable fr" id="keysection_show1" style="display: ''">
									要害部门：
								</td>
								<td class="tbValue fl" id="keysection_show2" style="display: ''">
									<section:selectKeySection valueEl="secrecyComputer.keySection.keySectionId" textEl="secrecyComputer.keySection.department.departmentName"
										value="${secrecyComputer.keySection.keySectionId }" buttonEl="aa" required="false" onSelect="doSelectKeySection"></section:selectKeySection>
										<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr" id="keypart_show1" style="display: none">
									要害部位：
								</td>
								<td class="tbValue fl" id="keypart_show2" style="display: none">
									<part:selectKeyPart valueEl="secrecyComputer.keyPart.partId" textEl="secrecyComputer.keyPart.partName"
										value="${secrecyComputer.keyPart.partId }" buttonEl="bb"
										required="false" onSelect="doSelectKeyPart" dataSelector="kePartSelector"></part:selectKeyPart>
										<span style="color:red;">&nbsp;&nbsp;*</span>
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
									name="secrecyComputer.isFanghu"
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
									name="secrecyComputer.isWailian"
									>
								</s:select><span style="color:red;">&nbsp;&nbsp;*</span>
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