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
		<title>区、县（市）网络保密监督管理工作机制及制度建设情况统计表</title>
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
					 var oFCKeditor1 = new FCKeditor('secrecySupervision.netCheckAdvice');
					oFCKeditor1.BasePath= sBasePath ;
					oFCKeditor1.ReplaceTextarea() ;

					var oFCKeditor2 = new FCKeditor('secrecySupervision.errOtherAdvice');
					oFCKeditor2.BasePath= sBasePath ;
					oFCKeditor2.ReplaceTextarea() ;

					var oFCKeditor3 = new FCKeditor('secrecySupervision.warningOtherAdvice');
					oFCKeditor3.BasePath= sBasePath ;
					oFCKeditor3.ReplaceTextarea() ;

					new FormCheck('form_supervision_add',{
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
			var Editor1 = FCKeditorAPI.GetInstance('secrecySupervision.netCheckAdvice');
			var Editor2 = FCKeditorAPI.GetInstance('secrecySupervision.errOtherAdvice');
			var Editor3 = FCKeditorAPI.GetInstance('secrecySupervision.warningOtherAdvice');
		 	var acontent1 = Editor1.GetXHTML();
		 	var acontent2 = Editor2.GetXHTML();
		 	var acontent3 = Editor3.GetXHTML();
		 	if(acontent1.length > 4000){
				alert("小提示，网络核查信息中“存在问题及意见和建议”的内容不能超过4000字符,您可以通过粘贴时清理CSS样式来减少冗余字符。");
			}else if(acontent2.length > 4000){
				alert("小提示，网络保密检查信息中“存在问题及意见和建议”的内容不能超过4000字符,您可以通过粘贴时清理CSS样式来减少冗余字符。");
			}else if(acontent3.length > 4000){
				alert("小提示，检查处理违规信息中“存在问题及意见和建议”的内容不能超过4000字符,您可以通过粘贴时清理CSS样式来减少冗余字符。");
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
					区、县（市）网络保密监督管理工作机制及制度建设情况统计表
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_supervision_add" class="form" action="<s:url action="secrecySupervision_save" includeParams="true"/>" method="post">
						<div class="panel_content panel_content_table">
							<!-- 隐藏域 -->
							<input type="hidden" name="secrecySupervision.id" id="secrecySupervision.id" value="${secrecySupervision.id }"/>
							<input type="hidden" name="secrecySupervision.createPerson.userId" id="secrecySupervision.createPerson.userId" value="${secrecySupervision.createPerson.userId }"/>
							<input type="hidden" name="secrecySupervision.modifyPerson.userId" id="secrecySupervision.modifyPerson.userId" value="${secrecySupervision.modifyPerson.userId }"/>
							<input type="hidden" name="secrecySupervision.createTime" id="secrecySupervision.createTime" value="<fmt:formatDate value="${secrecySupervision.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<input type="hidden" name="secrecySupervision.modifyTime" id="secrecySupervision.modifyTime" value="<fmt:formatDate value="${secrecySupervision.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<input type="hidden" name="secrecySupervision.createOrgan.organId" id="secrecySupervision.createOrgan.organId" value="${secrecySupervision.createOrgan.organId }"/>
							<input type="hidden" name="secrecySupervision.createDepartment.departmentId" id="secrecySupervision.createDepartment.departmentId" value="${secrecySupervision.createDepartment.departmentId }"/>

							<table class="content_table">
								<tr>
									<td >
										单位：（盖章）
									</td>
									<td colspan="1">
										${secrecySupervision.reportOrgan.organName }
										<input size="5" type="hidden" id="secrecySupervision.reportOrgan.organId" name="secrecySupervision.reportOrgan.organId" value="${secrecySupervision.reportOrgan.organId }" />
										<input type="hidden" name="secrecySupervision.reportDepartment.departmentId" id="secrecySupervision.reportDepartment.departmentId" value="${secrecySupervision.reportDepartment.departmentId }"/>
									</td>
									<td>
										填表人：
									</td>
									<td colspan="1">
										${secrecySupervision.reportUser.userInfo.name }
										<input size="5" type="hidden" id="secrecySupervision.reportUser" name="secrecySupervision.reportUser.userId" value="${secrecySupervision.reportUser.userId }" />
									</td>
									<td>
										填报日期：
									</td>
									<td colspan="1">
										<fmt:formatDate value="${secrecySupervision.reportDate}" pattern="yyyy-MM-dd"/>
										<input type="hidden" id="secrecySupervision.reportDate" name="secrecySupervision.reportDate" readonly="readonly" value="<fmt:formatDate value="${secrecySupervision.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
										<input type="hidden" id="secrecySupervision.year" name="secrecySupervision.year" readonly="readonly" value="${secrecySupervision.year}" />
									</td>
								</tr>
								<tr>
									<td rowspan="6">网络保密监督管理工作机制及制度建设情况</td>
									<td colspan="5">
										内部：<br/>
										<input type="checkbox" id="secrecySupervision.inPlatformDuty" name="secrecySupervision.inPlatformDuty" <c:if test="${secrecySupervision.inPlatformDuty eq 1 }">checked="checked"</c:if> value="1"/>平台值班制度
										<input type="checkbox" id="secrecySupervision.inCaseInvestigation" name="secrecySupervision.inCaseInvestigation" <c:if test="${secrecySupervision.inCaseInvestigation eq 1 }">checked="checked"</c:if> value="1"/>案件协同查办机制
										<input type="checkbox" id="secrecySupervision.inImportantOrganNet" name="secrecySupervision.inImportantOrganNet" <c:if test="${secrecySupervision.inImportantOrganNet eq 1 }">checked="checked"</c:if> value="1"/>重要涉密单位互联网接入口保密监测平台工作制度
										<br/>
										<input type="checkbox" id="secrecySupervision.inSecrecyComInterent" name="secrecySupervision.inSecrecyComInterent" <c:if test="${secrecySupervision.inSecrecyComInterent eq 1 }">checked="checked"</c:if> value="1"/>涉密计算机违规外联监控工作制度
										<input type="checkbox" id="secrecySupervision.inInternetMsgCheck" name="secrecySupervision.inInternetMsgCheck" <c:if test="${secrecySupervision.inInternetMsgCheck eq 1 }">checked="checked"</c:if> value="1"/>互联网信息保密检查工作制度
										<br/>
										<input type="checkbox" id="secrecySupervision.inSecrecyCheck" name="secrecySupervision.inSecrecyCheck" <c:if test="${secrecySupervision.inSecrecyCheck eq 1 }">checked="checked"</c:if> value="1"/>保密技术核查工作制度
										<input type="checkbox" id="inOther" name="inOther" <c:if test="${not empty secrecySupervision.inOther }">checked="checked"</c:if> value="1"/>其他：
										<input type="text" id="secrecySupervision.inOther" name="secrecySupervision.inOther" size="80" value="${secrecySupervision.inOther }" class="validate['length[0, 500]']" />
									</td>
								</tr>
								<tr>
									<td colspan="5">
										外部：<br/>
										<input type="checkbox" id="secrecySupervision.outSociologySupervision" name="secrecySupervision.outSociologySupervision" <c:if test="${secrecySupervision.outSociologySupervision eq 1 }">checked="checked"</c:if> value="1"/>社会网站监管机制
										<input type="checkbox" id="secrecySupervision.outIpUserMsg" name="secrecySupervision.outIpUserMsg" <c:if test="${secrecySupervision.outIpUserMsg eq 1 }">checked="checked"</c:if> value="1"/>IP地址用户信息查询机制
										<input type="checkbox" id="secrecySupervision.outOrganReport" name="secrecySupervision.outOrganReport" <c:if test="${secrecySupervision.outOrganReport eq 1 }">checked="checked"</c:if> value="1"/>有关部门情况通报机制
										<br/>
										<input type="checkbox" id="secrecySupervision.outInternetAccess" name="secrecySupervision.outInternetAccess" <c:if test="${secrecySupervision.outInternetAccess eq 1 }">checked="checked"</c:if> value="1"/>与工信部门互联网安全接入管理工作机制
										<input type="checkbox" id="outOther" name="outOther" <c:if test="${not empty secrecySupervision.outOther }">checked="checked"</c:if> value="1"/>其他：
										<input type="text" id="secrecySupervision.outOther" name="secrecySupervision.outOther" size="80" value="${secrecySupervision.outOther }" class="validate['length[0, 500]']" />
									</td>
								</tr>
								<tr>
									<td colspan="5">
									 	<c:if test="${not empty secrecySupervision and not empty secrecySupervision.secrecySupervisionContentSet }" >
											<c:forEach var="sfc" items="${secrecySupervision.secrecySupervisionContentSet }" varStatus="status">
												<input type="hidden" name="secSupConSet[${status.index }].year" id="secSupConSet[${status.index }].year" value="${sfc.year }" />
												<input type="hidden" name="secSupConSet[${status.index }].id" id="secSupConSet[${status.index }].id" value="${sfc.id }" />
												${sfc.year }年，预算（
												<input size="5" type="text" name="secSupConSet[${status.index }].budget" id="secSupConSet[${status.index }].budget" value="${sfc.budget }" class="validate['required','number']" />
												）万元，实际使用（
												<input size="5" type="text" name="secSupConSet[${status.index }].actualUse" id="secSupConSet[${status.index }].actualUse" value="${sfc.actualUse }" class="validate['required','number']" />
												）万元，主要用于
												<input type="checkbox" id="secSupConSet[${status.index }].buyCheckTools" name="secSupConSet[${status.index }].buyCheckTools" <c:if test="${sfc.buyCheckTools eq 1 }">checked="checked"</c:if> value="1"/>
												检查工具购买
												<input type="checkbox" id="secSupConSet[${status.index }].platformConstruction" name="secSupConSet[${status.index }].platformConstruction" <c:if test="${sfc.platformConstruction eq 1 }">checked="checked"</c:if> value="1"/>
												平台建设
												<input type="checkbox" id="secSupConSet[${status.index }].businessTrain" name="secSupConSet[${status.index }].businessTrain" <c:if test="${sfc.businessTrain eq 1 }">checked="checked"</c:if> value="1"/>
												业务培训
												<input type="checkbox" id="secSupConSet[${status.index }].networkEvaluation" name="secSupConSet[${status.index }].networkEvaluation" <c:if test="${sfc.networkEvaluation eq 1 }">checked="checked"</c:if> value="1"/>
												网络测评
												<input type="checkbox" id="other" name="other" <c:if test="${not empty sfc.other }">checked="checked"</c:if> value="1"/>其他：
												<input type="text" id="secSupConSet[${status.index }].other" name="secSupConSet[${status.index }].other" size="20" value="${sfc.other }" class="validate['length[0, 500]']" />
											</c:forEach>
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="5">
										${secrecySupervision.year - 1 }年以来，开展网络核查（
										<input size="5" type="text" id="secrecySupervision.netCheckTimes" name="secrecySupervision.netCheckTimes" value="${secrecySupervision.netCheckTimes }" class="validate['required','digit','length[0,6]']" />
										）次，共核查网络（
										<input size="5" type="text" id="secrecySupervision.netCheckNum" name="secrecySupervision.netCheckNum" value="${secrecySupervision.netCheckNum }" class="validate['required','digit','length[0,6]']" />
										）个，检查测评涉密网络（
										<input size="5" type="text" id="secrecySupervision.netEvaluationNum" name="secrecySupervision.netEvaluationNum" value="${secrecySupervision.netEvaluationNum }" class="validate['required','digit','length[0,6]']" />
										）个。
										存在问题及意见和建议：
										<textarea id="secrecySupervision.netCheckAdvice" name="secrecySupervision.netCheckAdvice" >${secrecySupervision.netCheckAdvice }</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="5">
										${secrecySupervision.year - 1 }年以来，开展网络保密检查（
										<input size="5" type="text" id="secrecySupervision.netSecrecyCheckTimes" name="secrecySupervision.netSecrecyCheckTimes" value="${secrecySupervision.netSecrecyCheckTimes }" class="validate['required','digit','length[0,6]']" />
										）次，共检查涉密网（
										<input size="5" type="text" id="secrecySupervision.netSecrecyCheckNum" name="secrecySupervision.netSecrecyCheckNum" value="${secrecySupervision.netSecrecyCheckNum }" class="validate['required','digit','length[0,6]']" />
										）个，非涉密网（
										<input size="5" type="text" id="secrecySupervision.netNoneSecrecyNum" name="secrecySupervision.netNoneSecrecyNum" value="${secrecySupervision.netNoneSecrecyNum }" class="validate['required','digit','length[0,6]']" />
										）个，涉密计算机（
										<input size="5" type="text" id="secrecySupervision.computerSecrecyNum" name="secrecySupervision.computerSecrecyNum" value="${secrecySupervision.computerSecrecyNum }" class="validate['required','digit','length[0,6]']" />
										）台，非涉密计算机（
										<input size="5" type="text" id="secrecySupervision.computerNoneNum" name="secrecySupervision.computerNoneNum" value="${secrecySupervision.computerNoneNum }" class="validate['required','digit','length[0,6]']" />
										）台，连接互联网计算机（
										<input size="5" type="text" id="secrecySupervision.computerInternetNum" name="secrecySupervision.computerInternetNum" value="${secrecySupervision.computerInternetNum }" class="validate['required','digit','length[0,6]']" />
										）台，涉密移动存储介质（
										<input size="5" type="text" id="secrecySupervision.storageSecrecyNum" name="secrecySupervision.storageSecrecyNum" value="${secrecySupervision.storageSecrecyNum }" class="validate['required','digit','length[0,6]']" />
										）个，非涉密移动存储介质（
										<input size="5" type="text" id="secrecySupervision.stotageNoneNum" name="secrecySupervision.stotageNoneNum" value="${secrecySupervision.stotageNoneNum }" class="validate['required','digit','length[0,6]']" />
										）个。
										1、涉密计算机违规连接互联网的（
										<input size="5" type="text" id="secrecySupervision.errComputerInternetNum" name="secrecySupervision.errComputerInternetNum" value="${secrecySupervision.errComputerInternetNum }" class="validate['required','digit','length[0,6]']" />
										）台；2、互联网计算机存储处理涉密信息的（
										<input size="5" type="text" id="secrecySupervision.errInternetMsgNum" name="secrecySupervision.errInternetMsgNum" value="${secrecySupervision.errInternetMsgNum }" class="validate['required','digit','length[0,6]']" />
										）台；3、感染特种木马的计算机（
										<input size="5" type="text" id="secrecySupervision.errComputerTrojanNum" name="secrecySupervision.errComputerTrojanNum" value="${secrecySupervision.errComputerTrojanNum }" class="validate['required','digit','length[0,6]']" />
										）台；
										4、移动存储介质交叉使用的（
										<input size="5" type="text" id="secrecySupervision.errStorageExchangeUseNum" name="secrecySupervision.errStorageExchangeUseNum" value="${secrecySupervision.errStorageExchangeUseNum }" class="validate['required','digit','length[0,6]']" />
										）个；5、受到党纪政纪处分的（
										<input size="5" type="text" id="secrecySupervision.errPeoplePunishment" name="secrecySupervision.errPeoplePunishment" value="${secrecySupervision.errPeoplePunishment }" class="validate['required','digit','length[0,6]']" />
										）人。
										存在意见和建议：
										<textarea id="secrecySupervision.errOtherAdvice" name="secrecySupervision.errOtherAdvice" >${secrecySupervision.errOtherAdvice }</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="5">
										${secrecySupervision.year - 1 }年以来，检查处理违规外联报警信息（
										<input size="5" type="text" id="secrecySupervision.warningMsgNum" name="secrecySupervision.warningMsgNum" value="${secrecySupervision.warningMsgNum }" class="validate['required','digit','length[0,6]']" />
										）条，网站违规发布涉密或敏感信息（
										<input size="5" type="text" id="secrecySupervision.webSecrecyMsgNum" name="secrecySupervision.webSecrecyMsgNum" value="${secrecySupervision.webSecrecyMsgNum }" class="validate['required','digit','length[0,6]']" />
										）条。
										其他违规信息处理情况：
										<input type="text" id="secrecySupervision.illegalDealMsg" name="secrecySupervision.illegalDealMsg" size="20" value="${secrecySupervision.illegalDealMsg }" class="validate['length[0, 500]']" />
										存在问题及意见和建议：
										<textarea id="secrecySupervision.warningOtherAdvice" name="secrecySupervision.warningOtherAdvice" >${secrecySupervision.warningOtherAdvice }</textarea>
									</td>
								</tr>
							</table>
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