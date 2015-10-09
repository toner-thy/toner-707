<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>信息发布保密审查情况</title>

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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/FCKeditor/fckeditor.js");
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

			function doSave(){
				if (yanzheng()&&formcheck.isFormValid(true)) {
						$('sub').click();
						$('sbm_button').style.display='none';
						$('sbm_button_hidden').style.display='';
						window.setTimeout("$('sbm_button').style.display=''",8000);
						window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				 }
			}

			function doBackList(){
				if( confirm("确定退出吗？") ){
					window.location.href = "${ctx}/bmp/informationReview/informationReview_list.action?_ts="+ new Date().getTime();
				}
			}
		</script>
		<script language="javascript">
			function  yanzheng(){
				var flag=0;
			  var theCheckboxInputs=document.getElementsByName('infoSources');
			  var theCheckboxInputs1=document.getElementsByName('releaseWays');
			  for(var i=0;i<theCheckboxInputs.length;i++)
			  {
			   if(theCheckboxInputs[i].checked){
				   flag+=1;
				   break;
			   }
			  }
			  for(var i=0;i<theCheckboxInputs1.length;i++)
			  {
			   if(theCheckboxInputs1[i].checked){
				   flag+=2;
				   break;
			   }
			  }
			  if(flag==3){
				  return true;
			  }
			  else{
				  if(flag==1){
					  alert("请填写发布途径！");
				  }
				  if(flag==2){
					  alert("请填写信息来源！");
				  }
				  if(flag==0){
					  alert("请填写信息来源和发布途径！");
				  }
				return false;
			  }
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
					<a class="pop_button" href="javascript:doBackList();" id="back_button"><span>返回</span></a>
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
			<cp:start defaultTitle="信息发布保密审查情况简介" ctx="${ctx}" icoPath="#">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','信息发布保密审查情况简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于信息发布保密审查情况
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="annual_plan_description"> </cpc:tc>
					</div>
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						信息发布保密审查情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/informationReview' action='informationReview_adding' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									标题：
								</td>
								<td class="tbValue fl">
									<input type="text" name="informationReview.title" class="validate['required','length[100]']" style="width: 90%;" value="${informationReview.title }" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									内容：
								</td>
								<td class="tbValue fl">
									<textarea rows="5" cols="150" style="width: 90%;" name="informationReview.content" class="validate['required','length[1000]']">${informationReview.content }</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									信息来源：
								</td>
								<td class="tbValue fl">
									<dictionary:checkbox fieldCode="infoSource" tableCode="bmp" name="infoSources" optionValues="${informationReview.informationSources}" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									承办（提供）部门：
								</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="informationReview.undertakingDepartment.departmentId"
									textEl="informationReview.undertakingDepartment.departmentName" required="true" onlyFromValue="true"
									styleClass="validate['length[39]']"
									 buttonEl="sss" />
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									发布途径：
								</td>
								<td class="tbValue fl">
									<dictionary:checkbox fieldCode="releaseWay" onchange="bianmei()" tableCode="bmp" name="releaseWays" optionValues="${informationReview.releaseWay}" />
									<span style="color:red;">&nbsp;&nbsp;*</span>

								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									部门初审意见：
								</td>
								<td class="tbValue fl">
									<textarea rows="5" cols="150" style="width: 90%;" name="informationReview.initialOpinions" class="validate['required','length[1000]']">${informationReview.initialOpinions }</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									专业审核人意见：
								</td>
								<td class="tbValue fl">
									<textarea rows="5" cols="150" style="width: 90%;" name="informationReview.professionalOpinion" class="validate['required','length[1000]']">${informationReview.professionalOpinion }</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									单位领导签批意见：
								</td>
								<td class="tbValue fl">
									<textarea rows="5" cols="150" style="width: 90%;" name="informationReview.unitLeadersOpinions" class="validate['required','length[1000]']">${informationReview.unitLeadersOpinions }</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									备注：
								</td>
								<td class="tbValue fl">
									<textarea rows="5" cols="150" style="width: 90%;" name="informationReview.remark" class="validate['length[1000]']">${informationReview.remark }</textarea>
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
		</div>

	</body>
</html>