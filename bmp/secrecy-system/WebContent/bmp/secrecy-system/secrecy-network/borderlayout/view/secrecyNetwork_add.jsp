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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增涉密网络</title>

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
						if (!confirm('新增成功，是否继续添加？')){
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
			var needReload = ${needReload};
			function doBack2(){
				TabUtil.closeTab();
			}
			function doSave(){
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				}
			}
			function selectPC(value) {
				if(value==1) {
					$('pass11').style.display="";
				}else {
					$('pass11').style.display="none";
					$('secrecyNetwork.reviewTime').set("value","");
					$('secrecyNetwork.reviewOrgan.organName').set("value","");
					$('secrecyNetwork.reviewOrgan.organId').set("value","");

				}
			}

			function selectSP(value) {
				if(value==1) {
					$('pass22').style.display="";
				}else {
					$('pass22').style.display="none";
					$('secrecyNetwork.approvalTime').set("value","");
					$('secrecyNetwork.approvalNo').set("value","");
				}
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
			<cp:start defaultTitle="涉密网络简介" ctx="${ctx}" icoPath="/bmp/secrecy-system/secrecy-network/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','涉密网络简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密网络
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_network"> </cpc:tc>
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
						新增涉密网络
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/secrecynetwork' action='secrecyNetwork_adding.action' includeParams='true'/>" method="post">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									涉密网络名称：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyNetwork.name" name="secrecyNetwork.name" class="validate['required','length[100]']" style="width: 70%"/><span style="color:red;">&nbsp;&nbsp;*</span>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('name_helpId')" onmouseout="noneOne('name_helpId')"  />
									<div id="name_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											以《涉及商业秘密的信息系统检测评估报告》中的名称为准。
										<br/>
									</div>
								</td>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="secrecyNetwork.department.departmentId" textEl="secrecyNetwork.department.departmentName"
										required="true" onlyFromValue="false" buttonEl="dept_img"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									网络密级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyNetwork.secrecyLevel" name="secrecyNetwork.secrecyLevel"
									 title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyNetwork.secrecyLevel}"></dictionary:select><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									网络类型：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="network_type" id="secrecyNetwork.networkType" name="secrecyNetwork.networkType"
									 title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyNetwork.networkType}"></dictionary:select><span style="color:red;">&nbsp;&nbsp;*</span>
							</tr>
							<tr>
								<td class="tbLable fr">
									是否通过测评：
								</td>
								<td class="tbValue fl">
								  	<input type="radio" name="secrecyNetwork.isReview"  value="1" onclick="selectPC(this.value)">是
									<input type="radio" name="secrecyNetwork.isReview" checked="checked" value="0" onclick="selectPC(this.value)">否
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									是否通过审批：
								</td>
								<td class="tbValue fl">
									<input type="radio" name="secrecyNetwork.isApproval"  value="1" onclick="selectSP(this.value)">是
									<input type="radio" name="secrecyNetwork.isApproval" checked="checked" value="0" onclick="selectSP(this.value)">否
							        <span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr style="display: none;" id="pass11">
								<td class="tbLable fr">
								           测评通过时间：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyNetwork.reviewTime" id="secrecyNetwork.reviewTime"
										class="Wdate" onFocus="WdatePicker();" readonly="readonly" />
								</td>
								<td class="tbLable fr">
									测评通过机构：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyNetwork.reviewOrgan" id="secrecyNetwork.reviewOrgan"
										value="${secrecyNetwork.reviewOrgan }" class="validate['length[30]']"/>
								</td>
							</tr>
							<tr style="display: none;" id="pass22">
								<td class="tbLable fr">
								          审批时间：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyNetwork.approvalTime" id="secrecyNetwork.approvalTime"
										class="Wdate" onFocus="WdatePicker();" readonly="readonly" />
								</td>
								<td class="tbLable fr">
									审批文号：
								</td>
								<td class="tbValue fl">
								    <input type="text" id="secrecyNetwork.approvalNo" name="secrecyNetwork.approvalNo" >
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									投入使用时间：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" id="secrecyNetwork.startUseDate" name="secrecyNetwork.startUseDate" class="Wdate validate['required'] w135" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" /><span style="color:red;">&nbsp;&nbsp;*</span>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('startUseDate_helpId')" onmouseout="noneOne('startUseDate_helpId')"  />
									<div id="startUseDate_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											以保密行政管理部门批复《涉及商业秘密的信息系统使用许可证》 中标注的时间为准。
										<br/>
									</div>
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