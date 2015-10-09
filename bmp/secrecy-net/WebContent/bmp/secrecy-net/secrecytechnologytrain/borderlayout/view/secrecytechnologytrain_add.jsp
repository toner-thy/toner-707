<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>区、县（市）保密技术培训情况统计表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<!-- FCK支持 -->
		<script src="${ctx}/resources/FCKeditor/fckeditor.js" type="text/javascript"></script>

		<s:actionmessage theme="messages"/>

		<script type="text/javascript">
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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");

			var needReload = "${needReload }";

			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js", function(){
				$ENV.onDomReady(function(){
					var sBasePath ='${ctx}/resources/FCKeditor/';
					var oFCKeditor = new FCKeditor('secrecyTechnologyTrain.problemAdvice');
					oFCKeditor.BasePath	= sBasePath ;
					oFCKeditor.ReplaceTextarea() ;

					new FormCheck('form_technology_add',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});


		function doBackList(){
			//window.location.href="${ctx}/bmp/attachment/attachment_myList.action";
		}
		function doSave(){
			// 判断内容字符不能超过4000字符
			var Editor = FCKeditorAPI.GetInstance('secrecyTechnologyTrain.problemAdvice');
		 	var acontent = Editor.GetXHTML();
		 	if(acontent.length > 4000){
				alert("小提示，[正文]内容不能超过4000字符,您可以通过粘贴时清理CSS样式来减少冗余字符。");
			}else{
				$('sub').click();
				$('sbm_button').style.display='none';
				$('sbm_button_hidden').style.display='';
				window.setTimeout("$('sbm_button').style.display=''",8000);
				window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
			}
		}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();"  id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<%-- <a class="pop_button" href="javascript:doBackList();"><span>返回列表</span></a> --%>
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
			<!-- 内容panel开始 -->
			<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleListIco">
					区、县（市）保密技术培训情况统计表
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_technology_add" class="form" action="<s:url action="secrecyTechnologyTrain_save" includeParams="true"/>" method="post">
						<div class="panel_content panel_content_table">
							<!-- 隐藏域 -->
							<input type="hidden" name="secrecyTechnologyTrain.id" id="secrecyTechnologyTrain.id" value="${secrecyTechnologyTrain.id }"/>
							<input type="hidden" name="secrecyTechnologyTrain.createPerson.userId" id="secrecyTechnologyTrain.createPerson.userId" value="${secrecyTechnologyTrain.createPerson.userId }"/>
							<input type="hidden" name="secrecyTechnologyTrain.modifyPerson.userId" id="secrecyTechnologyTrain.modifyPerson.userId" value="${secrecyTechnologyTrain.modifyPerson.userId }"/>
							<input type="hidden" name="secrecyTechnologyTrain.createTime" id="secrecyTechnologyTrain.createTime" value="<fmt:formatDate value="${secrecyTechnologyTrain.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<input type="hidden" name="secrecyTechnologyTrain.modifyTime" id="secrecyTechnologyTrain.modifyTime" value="<fmt:formatDate value="${secrecyTechnologyTrain.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<input type="hidden" name="secrecyTechnologyTrain.createOrgan.organId" id="secrecyTechnologyTrain.createOrgan.organId" value="${secrecyTechnologyTrain.createOrgan.organId }"/>
							<input type="hidden" name="secrecyTechnologyTrain.createDepartment.departmentId" id="secrecyTechnologyTrain.createDepartment.departmentId" value="${secrecyTechnologyTrain.createDepartment.departmentId }"/>

							<table class="content_table">
								<tr>
									<td class="tbLable fr">
										单位：（盖章）
									</td>
									<td colspan="2">
										${secrecyTechnologyTrain.reportOrgan.organName }
										<input size="5" type="hidden" id="secrecyTechnologyTrain.reportOrgan.organId" name="secrecyTechnologyTrain.reportOrgan.organId" value="${secrecyTechnologyTrain.reportOrgan.organId }" />
										<input type="hidden" name="secrecyTechnologyTrain.reportDepartment.departmentId" id="secrecyTechnologyTrain.reportDepartment.departmentId" value="${secrecyTechnologyTrain.reportDepartment.departmentId }"/>
									</td>
									<td>
										填表人：
									</td>
									<td colspan="2">
										${secrecyTechnologyTrain.reportUser.userInfo.name }
										<input size="5" type="hidden" id="secrecyTechnologyTrain.reportUser" name="secrecyTechnologyTrain.reportUser.userId" value="${secrecyTechnologyTrain.reportUser.userId }" />
									</td>
									<td>
										填报日期：
									</td>
									<td colspan="2">
										<fmt:formatDate value="${secrecyTechnologyTrain.reportDate}" pattern="yyyy-MM-dd"/>
										<input type="hidden" id="secrecyTechnologyTrain.reportDate" name="secrecyTechnologyTrain.reportDate" readonly="readonly" value="<fmt:formatDate value="${secrecyTechnologyTrain.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
										<input type="hidden" id="secrecyTechnologyTrain.year" name="secrecyTechnologyTrain.year" readonly="readonly" value="${secrecyTechnologyTrain.year}" />
									</td>
								</tr>
								<tr>
									<td rowspan="2">
										保密培训
									</td>
									<td rowspan="1">
										培训情况
									</td>
									<td colspan="8">
									 	<c:if test="${not empty secrecyTechnologyTrain and not empty secrecyTechnologyTrain.secrecyTechnologyTrainContentSet }" >
											<c:forEach var="sttc" items="${secrecyTechnologyTrain.secrecyTechnologyTrainContentSet }" varStatus="status">
												<input type="hidden" name="secTechTrainConSet[${status.index }].year" id="secTechTrainConSet[${status.index }].year" value="${sttc.year }" />
												<input type="hidden" name="secTechTrainConSet[${status.index }].id" id="secTechTrainConSet[${status.index }].id" value="${sttc.id }" />
												${sttc.year }年，组织开展保密技术培训（<input size="5" type="text" name="secTechTrainConSet[${status.index }].secrityTechTrainNum" id="secTechTrainConSet[${status.index }].secrityTechTrainNum" value="${sttc.secrityTechTrainNum }" class="validate['required','digit','length[0,6]']" />）次，培训（<input size="5" type="text" name="secTechTrainConSet[${status.index }].trainNum" id="secTechTrainConSet[${status.index }].trainNum" value="${sttc.trainNum }" class="validate['required','digit','length[0,6]']" />）人次。
												<br/>
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<td>存在问题<br/>及意见<br/>和建议</td>
									<td colspan="8">
										<textarea id="secrecyTechnologyTrain.problemAdvice" name="secrecyTechnologyTrain.problemAdvice">${secrecyTechnologyTrain.problemAdvice }</textarea>
									</td>
								</tr>
							</table>
							<!-- 附件 -->
							<div id="files_list"></div>

							<!-- 隐藏提交按钮 -->
							<div align="center">
								<input type="submit" id="sub" value="sub" style="display: none;" />
							</div>

						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>