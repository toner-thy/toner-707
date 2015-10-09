<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/organ" prefix="organ"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑保密技术培训</title>

		<!-- FCK支持 -->
		<script src="${ctx}/resources/FCKeditor/fckeditor.js" type="text/javascript"></script>
		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
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
					formcheck=new FormCheck('form_technicTrain_add',{
						display:{
							showErrors:1
						},
						trimValue: true
					});
				});
			});
			var needReload = ${needReload};
			var needReload2 = false;
			// 验证框架
			 // 保存
			 function doSave(){
				 if (formcheck.isFormValid(true)) {
						$('sub').click();
						$('sbm_button').style.display='none';
						$('sbm_button_hidden').style.display='';
						window.setTimeout("$('sbm_button').style.display=''",8000);
						window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				 }
		 	 }

		 	 // 返回
			 function doBack(){
				window.location.href="technicTrain_list.action";
			 }

			// 添加人员
			function addUserInfo(){
				window.location.href="userInfo_list.action";
			}

			// 下载附件
			function download(hostId,attachId){
				window.location.href="<s:url action="technicTrain_download" includeParams="false"/>?technicTrain.technicTrainingId="+hostId
				+"&attachment.attachId="+attachId;
			}

		 	// 删除附件
			function del(attachId,hostId){
				window.location.href='<s:url action="technicTrain_deleteAttachment" includeParams="false"/>?technicTrain.technicTrainingId='+hostId+"&attachment.attachId="+attachId;
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
					<a class="pop_button" href="javascript:TabUtil.closeTab();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="技术培训简介" ctx="${ctx}" icoPath="${ctx}/technictrain/borderlayout/skin/blue/img/add_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','技术培训简介');">简 介</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于技术培训
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="technicTrain_info"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑技术培训记录
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_technicTrain_add" class="form" action="<s:url namespace='/bmp/technicTrain' action='technicTrain_edit' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<!-- 隐藏域 -->
						<input type="hidden" name="technicTrainList_crd" value="${param.technicTrainList_crd }" />
						<input type="hidden" name="technicTrainList_p" value="${param.technicTrainList_p }" />
						<input type="hidden" name="technicTrain.technicTrainingId" value="${technicTrain.technicTrainingId }">
						<table class="content_table">
							<tr>
								<td align="right">
									培训主题：
								</td>
								<td colspan="3">
									<input name="technicTrain.trainingTitle" style="width:300px" id="trainingTitle" type="text" value="${technicTrain.trainingTitle}" class="validate['required','length[50]']" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td align="right" width="25%">
									参加培训人数：
								</td>
								<td width="25%">
									<input name="technicTrain.personNumber" id="personNumber" type="text" value="${technicTrain.personNumber}" class="validate['required','digit','length[10]']" size="25" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td align="right" width="25%">
									培训时间：
								</td>
								<td width="25%">
									<input type="text" name="technicTrain.trainingDate" value="<s:date format="yyyy-MM-dd" name="technicTrain.trainingDate"/>"class="Wdate validate['required']" id="technicTrain.trainingDate" onFocus="WdatePicker();" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td align="right">
									培训地点：
								</td>
								<td colspan="3">
									<input name="technicTrain.trainingPlace" id="trainingPlace" type="text" value="${technicTrain.trainingPlace}" class="validate['required','length[100]']" style="width:400px" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
							 <td align="right">
									培训对象：
								</td>
								<td colspan="3">
									<input name="technicTrain.trainingTarget" id="trainingTarget" type="text" value="${technicTrain.trainingTarget}" style="width:400px" class="validate['length[150]']" />
								</td>
							</tr>
							<tr>
								<td align="right" width="25%">
									应到场单位：
								</td>
								<td colspan="3" width="75%">
									<organ:multySelectByDistrict textEl="receivePersonNames1" valueEl="receivePersonIds1"  required="true" onlyFromValue="true"  buttonEl="readPerson1" value="${organs}"  valueProperty="organId" textProperty="organName" ></organ:multySelectByDistrict>
								</td>
							</tr>
							<tr>
								<td align="right">
									未到场单位：
								</td>
								<td colspan="3">
								<organ:multySelectByDistrict textEl="receivePersonNames2" valueEl="receivePersonIds2"  required="false" onlyFromValue="true"  buttonEl="readPerson2" value="${notorgans}"  valueProperty="organId" textProperty="organName" ></organ:multySelectByDistrict>
									<%-- <textarea rows="3" name="notorgansName" id="notorgansName" readonly="readonly" style="width: 100%;height: 90px;" class="validate['length[500]'] longText">${notorgansName}</textarea>
									<input type="hidden" name="notorgans" value="${notorgans}" id="notorgans" /> --%>
								</td>
							</tr>
							<tr>
								<td align="right">
									培训内容描述：
								</td>
								<td colspan="3">
									<textarea rows="3" name="technicTrain.trainingContent" id="trainingContent" style="width: 100%;height: 90px;" class="validate['length[500]'] longText">${technicTrain.trainingContent}</textarea>
								</td>
							</tr>
							<tr>
								<td align="right">
									备注：
								</td>
								<td colspan="3" height="100">
									<textarea rows="3" name="technicTrain.remark" id="remark" style="width: 100%;height: 90px;" class="validate['length[500]'] longText">${technicTrain.remark}</textarea>
								</td>
							</tr>
						</table>
						<!-- 附件 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
						</div>
					</form>
					<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="form_technicTrain_add" attachments="${attachmentList}" applyName="secAttach" showTitle="false"/>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>
<script>
	var multi_selector = new MultiSelector(document.getElementById('files_list'), 100);
	multi_selector.addElement(document.getElementById('my_file_element'));
</script>