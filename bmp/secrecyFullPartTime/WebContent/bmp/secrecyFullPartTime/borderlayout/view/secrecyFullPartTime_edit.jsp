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
		<title>编辑保密工作专（兼）职人员情况</title>

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
					showTime('secrecyFullPartTime.isTrain1');
					showTime('secrecyFullPartTime.isTrain0');

							if (needReload) {
								if (!confirm('编辑成功，是否继续编辑？')){
									needReload2 = true;
									TabUtil.closeTab();
								}
							};
					formcheck = new FormCheck('form_secrecyFullPartTime_update',{
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
				window.location.href="<s:url action="secrecyFullPartTime_list"  includeParams="false"/>";
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
			//保存
			function showTime(id){
				var valueTime = $(id).value;
				var valueCheck=$(id).checked;
				if(valueTime==1)
					{
						if(valueCheck)
							{
							$('secrecyFullPartTime.date').style.display='none';
							$('secrecyFullPartTime.content').style.display='none';
							}
					}
				if(valueTime==0)
					{
						if(valueCheck)
						{
						$('secrecyFullPartTime.date').style.display='';
						$('secrecyFullPartTime.content').style.display='';
						}
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
						新增保密工作专（兼）职人员情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
				<form id="form_secrecyFullPartTime_update" class="form"  action="<s:url namespace='/bmp/secrecyFullPartTime' action='secrecyFullPartTime_update' includeParams='true'/>" method="post" enctype="multipart/form-data">
					<table class="content_table">
							<tr>
								<td class="tbLable fr">
									姓名：
								</td>
								<td class="tbValue fl">
								   <ui:selectByOrgan  text="${secrecyFullPartTime.name.name}" value="${secrecyFullPartTime.name.userInfoId}" textEl="secrecyFullPartTime.name.name" valueEl="secrecyFullPartTime.name.userInfoId" buttonEl="buttonUserName" required="true" onlyFromValue="true" />
								 </td>
							</tr>
							<tr>
								<td class="tbLable fr" >
									职务：
								</td>
								<td class="tbValue fl">
									<input type="text"  name="secrecyFullPartTime.position" class="validate['required','length[100]']" value="${secrecyFullPartTime.position}"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									文化程度：
								</td>
								<td class="tbValue fl">
								<dictionary:select fieldCode="learning_level" tableCode="person" name="secrecyFullPartTime.degree"
								 id="secrecyFullPartTime.degree" optionValue="${secrecyFullPartTime.degree}" style="width:120px"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									从事保密工作年限：
								</td>
								<td class="tbValue fl">
								   <input type="text" name="secrecyFullPartTime.workYear" value="${secrecyFullPartTime.workYear}"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									职务类型：
								</td>
								<td class="tbValue fl">
									    <c:if test="${secrecyFullPartTime.isFull==0}">
									     <input type="radio" name="secrecyFullPartTime.isFull" value="0" checked="checked"/> 专职
										 <input type="radio" name="secrecyFullPartTime.isFull" value="1"/> 兼职
									    </c:if>
									    <c:if test="${secrecyFullPartTime.isFull==1}">
									     <input type="radio" name="secrecyFullPartTime.isFull" value="0" /> 专职
										 <input type="radio" name="secrecyFullPartTime.isFull" value="1" checked="checked"/> 兼职
									    </c:if>
									 <span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr" width="10%">
									是否接受保密培训：
								</td>
								<td class="tbValue fl">

								 		<c:if test="${secrecyFullPartTime.isTrain==1}">
									 <input type="radio" name="secrecyFullPartTime.isTrain" onclick="showTime('secrecyFullPartTime.isTrain1')" id="secrecyFullPartTime.isTrain1" value="1" checked="checked"/> 否&nbsp;&nbsp;
									 <input type="radio" name="secrecyFullPartTime.isTrain" onclick="showTime('secrecyFullPartTime.isTrain0')" id="secrecyFullPartTime.isTrain0" value="0"/> 是
									    </c:if>
									    <c:if test="${secrecyFullPartTime.isTrain==0}">
									 <input type="radio" name="secrecyFullPartTime.isTrain" onclick="showTime('secrecyFullPartTime.isTrain1')" id="secrecyFullPartTime.isTrain1" value="1" /> 否&nbsp;&nbsp;
									 <input type="radio" name="secrecyFullPartTime.isTrain" onclick="showTime('secrecyFullPartTime.isTrain0')" id="secrecyFullPartTime.isTrain0" value="0"  checked="checked"/> 是
									    </c:if>
									 <span style="color:red;">&nbsp;&nbsp;*</span>

								</td>
							</tr>
							<tr style="display: none" id="secrecyFullPartTime.date">
								<td class="tbLable fr" width="10%">
									接受保密培训时间：
								</td>
								<td class="tbValue fl">
									 <input  readonly="readonly" onfocus="WdatePicker()" value="<s:date name="secrecyFullPartTime.date" format="yyyy-MM-dd"/>" name="secrecyFullPartTime.date">
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr style="display: none" id="secrecyFullPartTime.content">
								<td class="tbLable fr">
									接受保密培训内容：
								</td>
								<td class="tbValue fl">
									<textarea rows="5" name="secrecyFullPartTime.content"  cols="120">${secrecyFullPartTime.content}</textarea>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
					</table>
						<input type="hidden" id="secrecyFullPartTime.id" name='secrecyFullPartTime.id' value="${secrecyFullPartTime.id}" class="btn_23" style="display: none;"/>
						<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
				</form>
			</div>
			</div>
		</div>
	</body>
</html>