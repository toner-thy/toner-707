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
		<title>市直机关保密队伍建设和保密培训情况统计表</title>
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
					var oFCKeditor = new FCKeditor('secrecyFoundTrain.problemAdvice');
					oFCKeditor.BasePath	= sBasePath ;
					oFCKeditor.ReplaceTextarea() ;

					new FormCheck('form_found_add',{
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
			var Editor = FCKeditorAPI.GetInstance('secrecyFoundTrain.problemAdvice');
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
					市直机关保密队伍建设和保密培训情况登记表
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_found_add" class="form" action="<s:url action="secrecyFoundTrain_save" includeParams="true"/>" method="post">
						<div class="panel_content panel_content_table">
							<!-- 隐藏域 -->
							<input type="hidden" name="secrecyFoundTrain.id" id="secrecyFoundTrain.id" value="${secrecyFoundTrain.id }"/>
							<input type="hidden" name="secrecyFoundTrain.createPerson.userId" id="secrecyFoundTrain.createPerson.userId" value="${secrecyFoundTrain.createPerson.userId }"/>
							<input type="hidden" name="secrecyFoundTrain.modifyPerson.userId" id="secrecyFoundTrain.modifyPerson.userId" value="${secrecyFoundTrain.modifyPerson.userId }"/>
							<input type="hidden" name="secrecyFoundTrain.createTime" id="secrecyFoundTrain.createTime" value="<fmt:formatDate value="${secrecyFoundTrain.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<input type="hidden" name="secrecyFoundTrain.modifyTime" id="secrecyFoundTrain.modifyTime" value="<fmt:formatDate value="${secrecyFoundTrain.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<input type="hidden" name="secrecyFoundTrain.createOrgan.organId" id="secrecyFoundTrain.createOrgan.organId" value="${secrecyFoundTrain.createOrgan.organId }"/>
							<input type="hidden" name="secrecyFoundTrain.createDepartment.departmentId" id="secrecyFoundTrain.createDepartment.departmentId" value="${secrecyFoundTrain.createDepartment.departmentId }"/>

							<table class="content_table">
								<tr>
									<td >
										单位：（盖章）
									</td>
									<td colspan="2">
										${secrecyFoundTrain.reportOrgan.organName }
										<input size="5" type="hidden" id="secrecyFoundTrain.reportOrgan.organId" name="secrecyFoundTrain.reportOrgan.organId" value="${secrecyFoundTrain.reportOrgan.organId }" />
										<input type="hidden" name="secrecyFoundTrain.reportDepartment.departmentId" id="secrecyFoundTrain.reportDepartment.departmentId" value="${secrecyFoundTrain.reportDepartment.departmentId }"/>
									</td>
									<td>
										填表人：
									</td>
									<td colspan="2">
										${secrecyFoundTrain.reportUser.userInfo.name }
										<input size="5" type="hidden" id="secrecyFoundTrain.reportUser" name="secrecyFoundTrain.reportUser.userId" value="${secrecyFoundTrain.reportUser.userId }" />
									</td>
									<td>
										填报日期：
									</td>
									<td colspan="3">
										<fmt:formatDate value="${secrecyFoundTrain.reportDate}" pattern="yyyy-MM-dd"/>
										<input type="hidden" id="secrecyFoundTrain.reportDate" name="secrecyFoundTrain.reportDate" readonly="readonly" value="<fmt:formatDate value="${secrecyFoundTrain.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
										<input type="hidden" id="secrecyFoundTrain.year" name="secrecyFoundTrain.year" readonly="readonly" value="${secrecyFoundTrain.year}" />
									</td>
								</tr>
								<tr>
									<td style="text-align: center" rowspan="3">
										保密队伍建设
									</td>
									<td style="text-align: center">
										保密干部人数
									</td>
									<td style="text-align: center" colspan="4">
										学历情况
									</td>
									<td style="text-align: center" colspan="2">
										专业情况
									</td>
									<td style="text-align: center" colspan="2">
										年龄段
									</td>
								</tr>
								<tr>
									<td  rowspan="2">
										<input size="5" type="text" id="secrecyFoundTrain.secrecyCadreNum" name="secrecyFoundTrain.secrecyCadreNum" value="${secrecyFoundTrain.secrecyCadreNum }" class="validate['required','digit','length[0,6]']" />
									</td>
									<td >
										博士生人数
									</td>
									<td >
										硕士生人数
									</td>
									<td >
										本科生人数
									</td>
									<td >
										大专及以下人数
									</td>
									<td >
										计算机及通信人员
									</td>
									<td >
										其他人数
									</td>
									<td >
										45岁以下人数
									</td>
									<td >
										45岁以上人数
									</td>
								</tr>
								<tr>
									<td >
										<input size="5" type="text" id="secrecyFoundTrain.doctorNum" name="secrecyFoundTrain.doctorNum" value="${secrecyFoundTrain.doctorNum }" class="validate['required','digit','length[0,6]']" />
									</td>
									<td >
										<input size="5" type="text" id="secrecyFoundTrain.masterNum" name="secrecyFoundTrain.masterNum" value="${secrecyFoundTrain.masterNum }" class="validate['required','digit','length[0,6]']" />
									</td>
									<td >
										<input size="5" type="text" id="secrecyFoundTrain.undergraduateNum" name="secrecyFoundTrain.undergraduateNum" value="${secrecyFoundTrain.undergraduateNum }" class="validate['required','digit','length[0,6]']" />
									</td>
									<td >
										<input size="5" type="text" id="secrecyFoundTrain.juniorNum" name="secrecyFoundTrain.juniorNum" value="${secrecyFoundTrain.juniorNum }" class="validate['required','digit','length[0,6]']" />
									</td>
									<td >
										<input size="5" type="text" id="secrecyFoundTrain.signalCommuNum" name="secrecyFoundTrain.signalCommuNum" value="${secrecyFoundTrain.signalCommuNum }" class="validate['required','digit','length[0,6]']" />
									</td>
									<td >
										<input size="5" type="text" id="secrecyFoundTrain.otherNum" name="secrecyFoundTrain.otherNum" value="${secrecyFoundTrain.otherNum }" class="validate['required','digit','length[0,6]']" />
									</td>
									<td >
										<input size="5" type="text" id="secrecyFoundTrain.lessThanNum" name="secrecyFoundTrain.lessThanNum" value="${secrecyFoundTrain.lessThanNum }" class="validate['required','digit','length[0,6]']" />
									</td>
									<td >
										<input size="5" type="text" id="secrecyFoundTrain.greateThanNum" name="secrecyFoundTrain.greateThanNum" value="${secrecyFoundTrain.greateThanNum }" class="validate['required','digit','length[0,6]']" />
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
									 	<c:if test="${not empty secrecyFoundTrain and not empty secrecyFoundTrain.secrecyFoundTrainContentSet }" >
											<c:forEach var="sfc" items="${secrecyFoundTrain.secrecyFoundTrainContentSet }" varStatus="status">
												<input type="hidden" name="secFounTrainConSet[${status.index }].year" id="secFounTrainConSet[${status.index }].year" value="${sfc.year }" />
												<input type="hidden" name="secFounTrainConSet[${status.index }].id" id="secFounTrainConSet[${status.index }].id" value="${sfc.id }" />
												${sfc.year }年，组织开展保密培训（<input size="5" type="text" name="secFounTrainConSet[${status.index }].secrityTrainNum" id="secFounTrainConSet[${status.index }].secrityTrainNum" value="${sfc.secrityTrainNum }" class="validate['required','digit','length[0,6]']" />）次，培训（<input size="5" type="text" name="secFounTrainConSet[${status.index }].trainNum" id="secFounTrainConSet[${status.index }].trainNum" value="${sfc.trainNum }" class="validate['required','digit','length[0,6]']" />）人次。
												<br/>
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<td>存在问题<br/>及意见<br/>和建议</td>
									<td colspan="8">
										<textarea id="secrecyFoundTrain.problemAdvice" name="secrecyFoundTrain.problemAdvice" >${secrecyFoundTrain.problemAdvice }</textarea>
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