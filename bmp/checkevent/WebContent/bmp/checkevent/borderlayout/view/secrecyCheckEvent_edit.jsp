<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/organ" prefix="organ"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>编辑保密检查事件</title>

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
								if (!confirm('编辑成功，是否继续编辑？')){
									needReload2 = true;
									TabUtil.closeTab();
								}
							};
					formcheck = new FormCheck('form_secrecyCheckEvent_update',{
								display:{
									showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
								},
								trimValue: true
							});
						});
					});
			var needReload = ${needReload};
			var needReload2 = false;
			//返回
			function doBack(){
				window.location.href="<s:url action="secrecyCheckEvent_list"  includeParams="false"/>";
			}

			//添加表格
			function doAddTable(){
				window.showModalDialog("secrecyCheckEvent_selectTable.action"
					,{window:window,
					text:'tableText',
					hidden:'tableIds'
					},"dialogWidth=700px;dialogHeight=645px,status=no,directories=no,menubar=no,resizable=yes,scrollbars=no");
			}

			//检查表格
			function doCheckTable(){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请选择一项");
					return;
				}
				window.location.href="${pageContext.request.contextPath}/checkEventTable_list.action?checkEventTable.checkEventTableId="+items[0].value+"&secrecyCheckEvent.secrecyCheckEventId=${secrecyCheckEvent.secrecyCheckEventId}";
				//alert("${pageContext.request.contextPath}/checkEventTable_list.action?checkEventTable.checkEventTableId="+items[0].value+"&secrecyCheckEvent.secrecyCheckEventId=${secrecyCheckEvent.secrecyCheckEventId}");
			}
			//保存
			function doSave(){
				if (formcheck.isFormValid(true)) {
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
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保存</span></a>
					<a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:doBack();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
		<!-- 内容panel开始 -->
	 		<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增保密检查事件
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
				<form id="form_secrecyCheckEvent_update" class="form"  action="<s:url namespace='/bmp/secrecyCheckEvent' action='secrecyCheckEvent_update' includeParams='true'/>" method="post" enctype="multipart/form-data">
					<input type="hidden" name="list_p" value="${param.list_p }">
					<input type="hidden" name="list_crd" value="${param.list_crd }">
					<table class="content_table">
						<tr height="36px;">
							<td align="right">检查内容：</td>
							<td colspan="3">
								<input name="secrecyCheckEvent.eventName" style="width:82%;"  type="text" value="${secrecyCheckEvent.eventName}" class="validate['required','length[100]']"><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</tr>

						<tr height="36px;">
						    <td align="right">检查日期：</td>
						    <td>
						    	<input name="secrecyCheckEvent.eventDate" value="<s:date format="yyyy-MM-dd" name="secrecyCheckEvent.eventDate"/>" class="Wdate validate['required']" onFocus="WdatePicker()" readonly="readonly"/><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
							 <td align="right">参加人数：</td>
						    <td>
						    	<input name="secrecyCheckEvent.joinNumber" value="${secrecyCheckEvent.joinNumber}" class="validate['required','length[50]','number']"/><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</tr>

						<Tr height="36px;">
							<td align="right">被查部门：</td>
							<td colspan="3">
							    <%-- <dep:selectByOrgan textEl="receivePersonNames2" valueEl="receivePersonIds2"  required="true" onlyFromValue="true"  buttonEl="readPerson2"></dep:selectByOrgan>--%>
						 		<dep:multySelect dialogWidth="0.9" dialogHeight="0.8" textEl="receivePersonNames2" valueEl="secrecyCheckEvent.checkedDepartment" onlyFromValue="true" required="true"
						 		selectComponentURI="/platform/organization/department/multy_select_by_dis_org.component"  value="${checkedDepartment}" textProperty="departmentName" valueProperty="departmentId"
						 		 buttonEl="readPerson2" />

							</td>
						</Tr>


						<Tr>
							<td align="right" valign="middle">检查情况：</td>
							<td colspan="3"  height="100px;">
								<textarea style="width:95%;height:80px;margin-left: 8px;" name="secrecyCheckEvent.checkCircs" class="validate['required','length[500]']" cols="40" rows="60">${secrecyCheckEvent.checkCircs}</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</Tr>

						<Tr>
							<td align="right" valign="middle">整改情况：</td>
							<td colspan="3"  height="100px;">
								<textarea style="width:95%;height:80px;margin-left: 8px;" name="secrecyCheckEvent.rectification" class="validate['required','length[500]']" cols="40" rows="60">${secrecyCheckEvent.rectification}</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</Tr>
					</table>
						<input type="hidden" id="secrecyCheckEvent.secrecyCheckEventId" name='secrecyCheckEvent.secrecyCheckEventId' value="${secrecyCheckEvent.secrecyCheckEventId}" class="btn_23" style="display: none;"/>
						<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
				</form>
				<div>
					<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="form_secrecyCheckEvent_update" attachments="${attachmentList}" applyName="secAttach" showTitle="false"/>
				</div>
			</div>
			</div>
		</div>
	</body>
</html>