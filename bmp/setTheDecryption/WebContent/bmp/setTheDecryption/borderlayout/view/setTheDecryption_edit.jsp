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
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/organ" prefix="organ"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>编辑保密定（解）密工作情况</title>

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
					formcheck = new FormCheck('form_setTheDecryption_update',{
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
				window.location.href="<s:url action="setTheDecryption_list"  includeParams="false"/>";
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
						新增保密定（解）密工作情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
				<form id="form_setTheDecryption_update" class="form"  action="<s:url namespace='/bmp/setTheDecryption' action='setTheDecryption_update' includeParams='true'/>" method="post" enctype="multipart/form-data">
					<table class="content_table">
							<tr>
								<td class="tbLable fr">
									日期：
								</td>
								<td class="tbValue fl">
								    <input class="Wdate validate['required']" readonly="readonly" onfocus="WdatePicker()" value="<s:date name="setTheDecryption.date" format="yyyy-MM-dd"/>" name="setTheDecryption.date">
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									密级名称：
								</td>
								<td class="tbValue fl">
									<input type="text"  name="setTheDecryption.name" class="validate['required','length[100]']" value="${setTheDecryption.name}"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									数量：
								</td>
								<td class="tbValue fl">
									<input type="text" name="setTheDecryption.number" value="${setTheDecryption.number}" class="validate['required','length[100]']"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									文号：
								</td>
								<td class="tbValue fl">
									<input type="text" name="setTheDecryption.docNumber" value="${setTheDecryption.docNumber}"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
								<dictionary:select fieldCode="secrecy_level_thing" tableCode="bmp" id="setTheDecryption.secrecyLevel"
								                           name="setTheDecryption.secrecyLevel" style="width: 132px;"
								                           title="true" titleText="请选择" styleClass="validate['required']"
								                           optionValue="${setTheDecryption.secrecyLevel}" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									载体形式：
								</td>
								<td class="tbValue fl">
								   <input type="text" name="setTheDecryption.carrierType" value="${setTheDecryption.carrierType}"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									事项类型：
								</td>
								<td class="tbValue fl">
								     <c:if test="${setTheDecryption.secrecyType==0 }">
								      <input type="radio" name="setTheDecryption.secrecyType" value="0" checked="checked"/> 定密事项
									 <input type="radio" name="setTheDecryption.secrecyType" value="1"/> 解密事项
									 <span style="color:red;">&nbsp;&nbsp;*</span>
								     </c:if>
									 <c:if test="${setTheDecryption.secrecyType==1}">
									  <input type="radio" name="setTheDecryption.secrecyType" value="0" /> 定密事项
									 <input type="radio" name="setTheDecryption.secrecyType" value="1" checked="checked"/> 解密事项
									 <span style="color:red;">&nbsp;&nbsp;*</span>
									 </c:if>

								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									分发范围：
								</td>
								<td class="tbValue fl">
									<textarea rows="5" name="setTheDecryption.scope"  cols="120">${setTheDecryption.scope}</textarea>
								</td>
							</tr>
					</table>
						<input type="hidden" id="setTheDecryption.id" name='setTheDecryption.id' value="${setTheDecryption.id}" class="btn_23" style="display: none;"/>
						<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
				</form>
			</div>
			</div>
		</div>
	</body>
</html>