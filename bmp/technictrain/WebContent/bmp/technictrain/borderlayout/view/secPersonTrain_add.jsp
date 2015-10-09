<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增保密设备</title>

		<link href="${ctx}/platform/css/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/FCKeditor/fckeditor.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('新增成功，是否继续添加?')){
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
			var needReload2 = false;
			function doSave(){
				var trianGrade=$('secPersonTrain.trianGrade').value;
				if(trianGrade>100){
					alert("培训成绩不得超过100分，请重新输入。 ");
				}else if(trianGrade<0){
					alert("培训成绩不得低于0分，请重新输入。 ");
				}else{
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				}
			}

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
					<a class="pop_button" href="javascript:doBack();" ><span>返回列表</span></a>
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
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密人员培训新增
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<form id="add_form" action="<s:url namespace='/bmpcd/secpersontrain' action='secPersonTrain_adding.action' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<table class="content_table">
							<tr>
								<td align="right">
									姓 名：
								</td>
								<td>
									<input type="text" id="secPersonTrain.secPersonName" name="secPersonTrain.secPersonName" class="validate['required','length[100]']" value=""/> <span style="color:red;">*</span>
								</td>
								<td align="right">性 别：</td>
								<td>
									<dictionary:select id="secPersonTrain.sex" name="secPersonTrain.sex"  fieldCode="sex"  tableCode="secPersonTrain" style="width:131px;" optionValue="${secPersonTrain.sex}"></dictionary:select> <span style="color:red;">*</span>
								</td>
								<td rowspan="3" colspan="2" align="center" >
									<img id="secPersonTrain.photo.src" src="${ctx}/platform/theme/default/images/person_photo.jpg" width="80" height="100" border="0">
									<input type="file" contentEditable="false"  onpaste="return false" id="secPersonTrain.photo" name="secPersonTrain.photo" onchange="$('secPersonTrain.photo.src').src = $('secPersonTrain.photo').value">
								</td>
							</tr>
							<tr>
								<td align="right">出生年月：</td>
								<td>
									<input type="text" id="secPersonTrain.birthday" name="secPersonTrain.birthday" class="Wdate" value="" class="validate['required']" onFocus="WdatePicker()" readonly="readonly"/> <span style="color:red;">*</span>
								</td>
								<td align="right">
									职 务：
								</td>
								<td>
									<input type="text" id="secPersonTrain.duty" name="secPersonTrain.duty" class="validate['length[200]']" value=""/>
								</td>
							</tr>
							<tr>
								<td align="right">
									政治面貌：
								</td>
								<td>
									<dictionary:select id="secPersonTrain.politics" name="secPersonTrain.politics"  fieldCode="politics"  tableCode="secPersonTrain"  style="width:131px;" optionValue="${secPersonTrain.politics}"></dictionary:select> <span style="color:red;">*</span>
								</td>
								<td align="right">
									学 历：
								</td>
								<td>
									<dictionary:select id="secPersonTrain.studyLever" name="secPersonTrain.studyLever"  fieldCode="studyLever"  tableCode="secPersonTrain"  style="width:131px;"  optionValue="${secPersonTrain.studyLever}"></dictionary:select> <span style="color:red;">*</span>
								</td>
							</tr>
							<tr>
								<td align="right">
									工作单位：
								</td>
								<td>
									<input type="text" id="secPersonTrain.workPlace" name="secPersonTrain.workPlace" class="validate['required','length[200]']" value=""/> <span style="color:red;">*</span>
								</td>
								<td align="right">
									涉密岗位名称：
								</td>
								<td>
									<input type="text" id="secPersonTrain.secDutyName" name="secPersonTrain.secDutyName" class="validate['required','length[200]']" value=""/> <span style="color:red;">*</span>
								</td>
								<td align="right">
									参加工作日期：
								</td>
								<td>
									<input type="text" id="secPersonTrain.joinWorkDay" name="secPersonTrain.joinWorkDay" class="Wdate validate['required']" value="" onFocus="WdatePicker()" readonly="readonly"/> <span style="color:red;">*</span>
								</td>
							</tr>
							<tr>
								<td align="right">
									到涉密岗位工作日期：
								</td>
								<td>
									<input type="text" id="secPersonTrain.secWorkDay" name="secPersonTrain.secWorkDay" class="Wdate" value="" onFocus="WdatePicker()" readonly="readonly"/>
								</td>
								<td align="right">
									接触最高密级：
								</td>
								<td>
									<dictionary:select id="secPersonTrain.secLever" name="secPersonTrain.secLever"  fieldCode="secLever"  tableCode="secPersonTrain"  style="width:131px;" optionValue="${secPersonTrain.secLever}"></dictionary:select> <span style="color:red;">*</span>
								</td>
								<td align="right">
									是否与单位签订保密协议：
								</td>
								<td>
									<dictionary:select id="secPersonTrain.secAgreement" name="secPersonTrain.secAgreement"  fieldCode="secAgreement"  tableCode="secPersonTrain"  style="width:131px;" optionValue="${secPersonTrain.secAgreement}"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									培训日期：
								</td>
								<td>
									<input type="text" id="secPersonTrain.trainDay" name="secPersonTrain.trainDay" class="Wdate" value="" onFocus="WdatePicker()" readonly="readonly"/>
								</td>
								<td align="right">
									培训成绩：
								</td>
								<td>
									<input type="text" id="secPersonTrain.trianGrade" name="secPersonTrain.trianGrade" class="validate['required','arithmeticfloatnum']" value=""/> <span style="color:red;">*</span>
								</td>
								<td align="right">
									上岗证：
								</td>
								<td>
									<dictionary:select id="secPersonTrain.dutyCertificate" name="secPersonTrain.dutyCertificate"  fieldCode="dutyCertificate"  tableCode="secPersonTrain"  style="width:131px;" optionValue="${secPersonTrain.dutyCertificate}"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									上岗编号：
								</td>
								<td colspan="5">
									<input type="text" id="secPersonTrain.dutyNumber" name="secPersonTrain.dutyNumber" class="validate['required','length[100]']" value=""/> <span style="color:red;">*</span>
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