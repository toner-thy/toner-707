<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="nestedlist" uri="http://www.cdthgk.com/bmp/tags/nestedlist"%>
<%@ taglib prefix="secPersonlist" uri="http://www.cdthgk.com/tags/secrecyperson/list"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑保密要害部门</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">


		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<s:actionmessage theme="messages"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('保存成功，是否继续？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};


				/* 	$('keySectionListId').focus(); */

					$('keySection.department.departmentName').addEvent('blur', function(){
						var departmentName = $('keySection.department.departmentName').value;
						var oldDepartmentName = $('oldDepartmentName').value;
						if(oldDepartmentName != departmentName){
							searchDepartment(departmentName);
						}
					});
					//查看该保密要害部门是否已经在  系统中存在
					function searchDepartment(departmentName, fn){
						new Request.JSON({
		 					url: '${pageContext.request.contextPath}/bmp/keySection/keySection_searchDepartment.action?addOrEditSearch=edit',
		 				    onComplete: function(result, text){
		 				    	if(result.message != null){
		 				    		//$('keySection.department.departmentName').focus();
		 				    		alert(result.message);
		 				    		return;
		 				    	} else if (fn) {
									fn();
		 				    	}
		 				    }
		 				}).get({
		 					'departmentName' : departmentName
		 				});
					}


					var formCheck = new FormCheck('edit_keySection_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});

					$('sbm_button').addEvent('click', function(){
						var departmentName = $('keySection.department.departmentName').value;
						var oldDepartmentName = $('oldDepartmentName').value;

						var submit = function() {
							$('sub').click();
							new SimpleUI.Overlay({
								maskWithIframe : true,
								zIndex : 100,
								loading : true
							}).show();
						};
						if (formCheck.isFormValid()) {
							if(oldDepartmentName != departmentName){
								searchDepartment(departmentName, submit);
							} else {
								submit();
							}
						}

					});

				});

			});

			var needReload = ${needReload};
  			var needReload2 = false;


			// 返回方法
			function doBackList(){
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
					<a class="pop_button" href="###" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBackList();"><span>返回列表</span></a>
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
			<%-- <cp:start defaultTitle="保密要害部门简介" ctx="${ctx}" icoPath="/bmp/key-section/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密要害部门简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密要害部门
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_key_department"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end> --%>
			<!-- 复合面板结束 -->

			<!-- 保密要害部门panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑保密要害部门
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="edit_keySection_form" action="<s:url namespace='/bmp/keySection' action='keySection_editing.action' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<table class="content_table st" width="100%">
							<tr>
								<td class="tbLable fr">
									单位名称：
								</td>
								<td class="tbValue fl" colspan="3">
									${keySection.organ.organName }
									<input type="hidden" id="keySection.keySectionId" name="keySection.keySectionId" value="${keySection.keySectionId }"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="keySection.department.departmentId" textEl="keySection.department.departmentName"
									text="${keySection.department.departmentName }" value="${keySection.department.departmentId }" required="true"
									onlyFromValue="false" styleClass="validate['length[32]']" buttonEl="btnDepartName" />
									<input type="hidden" id="oldDepartmentName" name="oldDepartmentName" value="${keySection.department.departmentName }">
								</td>
								<td class="tbLable fr">
									负责人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="keySection.principal.userInfoId" textEl="keySection.principal.name"
									text="${keySection.principal.name }" value="${keySection.principal.userInfoId }" required="true"
									onlyFromValue="false" styleClass="validate['length[32]']" buttonEl="btnPerson" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									涉密等级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_section" name="keySection.secrecyLevel" optionValue="${keySection.secrecyLevel }" title="false" style="width:130px;"></dictionary:select>
								</td>
								<td class="tbLable fr">
									联系电话：
								</td>
								<td class="tbValue fl">
									<input id="keySection.phone" name="keySection.phone" value="${keySection.phone }" class="validate['phone','length[32]']"/>
								</td>
							</tr>
							<tr>
								<td class="fr">
									在编人员数量：
								</td>
								<td class="fl">
									<input type="text" id="keySection.personNum" name="keySection.personNum" value="${keySection.personNum }" class="validate['number','length[5]']"/>
								</td>
								<td class="fr">
									涉密人员数量：
								</td>
								<td class="fl">
									<c:if test="${secPersonNum == null}">
										当前部门有涉密人员【0}】人
									</c:if>
									<c:if test="${secPersonNum != null}">
										当前部门有涉密人员【${secPersonNum }】人
									</c:if>
								</td>
							</tr>
							<tr>
								<td class="fr">
									涉密工作事项及范围：
								</td>
								<td class="fl" colspan="3">
									<textarea style="width:100%; height: 100px;" id="keySection.secScope" name="keySection.secScope" class="validate['length[1024]']">${keySection.secScope }</textarea><br/>
									请填写涉密工作事项概况，不需要详细罗列具体事项。
								</td>
							</tr>
						</table>
						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>
					<table class="content_table st" id="table_part_add" width="100%">
						<tr>
							<td class="tbLable fr">
								保密制度：
							</td>
							<td class="fl" colspan="3">
								<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="edit_keySection_form" applyName="secAttach" attachments="${attachments }" showTitle="false" titleText="保密制度"/>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<%-- <div class="split_line"></div><!-- 分隔线 -->

            <!-- 要害部位 -->
			<div class="tMargin bMargin" style="height:570px;">
				 <nestedlist:formload readonly="false" parameter="true" parameterText="departmentId=${keySection.department.departmentId}&nestedflag=1" formName="${keySection.department.departmentName }" />
			</div>

			<br/><br/>
			<div class="split_line"></div><!-- 分隔线 -->

			<!-- 机关涉密人员 -->
			<div class="tMargin bMargin" style="height:570px;overflow:hidden;">
				<secPersonlist:formload readonly="false" parameter="true"  parameterText="departmentId=${keySection.department.departmentId}&nestedflag=1" formName="${keySection.department.departmentName }"/>
			</div>

			<br/><br/>
			<div class="split_line"></div><!-- 分隔线 -->

			<input type="text" id="keySectionListId" style="width: 0;height: 0;"> --%>
		</div>
	</body>
</html>