<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>意见反馈页面</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${ctx}/resources/FCKeditor/fckeditor.js" type="text/javascript"></script>
		<s:actionmessage theme="messages"/>


		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");


			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js", function(){
				$ENV.onDomReady(function(){
					var needReload = ${needReload };
					var needReload2 = false;
					//var multi_selector = new MultiSelector(document.getElementById('files_list'), 100);
					//multi_selector.addElement(document.getElementById('my_file_element'));
					var sBasePath ='${ctx}/resources/FCKeditor/';
					var oFCKeditor = new FCKeditor('feedbackInfo.content');
					oFCKeditor.BasePath	= sBasePath ;
					oFCKeditor.ReplaceTextarea() ;
					if (needReload) {
						if (!confirm('是否继续添加?')){
							needReload2 = true;
							TabUtil.closeTab();
						};
					};
					formcheck = new FormCheck('feedbackInfo_add',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			//保存
			function doSave(){
				if (formcheck.isFormValid(true)) {
					var Editor = FCKeditorAPI.GetInstance('feedbackInfo.content');
					var acontent = Editor.GetXHTML();
					if(acontent.length > 4000){
						alert("描述内容字数超过4000字符，请调整描述内容。");
					}else{
			    		$('sub').click();
						$('sbm_button').style.display='none';
						$('sbm_button_hidden').style.display='';
						window.setTimeout("$('sbm_button').style.display=''",8000);
						window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
		    	    };
	    		}
	    	}

	    	//返回列表
			 function doBack(){
				TabUtil.closeTab();
			 }

		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar no_print">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:TabUtil.refreshTab();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						意见反馈					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="feedbackInfo_add" name="feedbackInfo_add" action="<s:url action="feedbackInfo_save" includeParams="true"/>" method="post" enctype="multipart/form-data">
						<table class="content_table">
							<tr>
								<td width="100px" align="right">主 题：</td>
								<td>
									<input type="text" id="feedbackInfo.feedbackTitle" name="feedbackInfo.feedbackTitle" value="${feedbackInfo.feedbackTitle}" class="validate['required','length[100]']" maxlength="50" style="width: 400px;height: 20px;"/> <font style="color:red;">*</font>
								</td>
							</tr>
							<tr>
								<td width="100px" align="right">问题类型：</td>
								<td>
									<s:select list="#{'1':'改进','2':'异常','3':'新功能'}" id="feedbackInfo.feedbackType"
										 name="feedbackInfo.feedbackType" value="feedbackInfo.feedbackType" cssStyle="width:100px;" theme="simple">
									</s:select>
								</td>
							</tr>
							<tr>
								<td width="100px" align="right" valign="top">内容描述：</td>
								<td colspan="3" >
									<textarea id="feedbackInfo.content" name="feedbackInfo.content" >${feedbackInfo.content }</textarea>
								</td>
							</tr>
						</table>
						<div id="files_list"></div>
						<!-- 按钮 -->
						<input type="submit" id="sub" value="sub" class="pop_button" style="display: none;"/>
						<input type="hidden" name="feedbackInfo.feedbackInfoId" value="${feedbackInfo.feedbackInfoId}">
					</form>
					<table class="content_table st" id="table_att_add" width="100%">
						<tr>
							<td class="tbLable fr">
								&nbsp;
							</td>
							<td class="fl" colspan="3"><font color="red">注：选择好要上传的附件后，请点击附件列表下方的开始上传按钮，否则附件无法上传</font></td>
						</tr>
						<tr>
							<td class="tbLable fr">
								附件列表：
							</td>
							<td class="fl" colspan="3">
								<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="feedbackInfo_add" limit="2" applyName="attachmentIds" showTitle="false"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<!-- 内容panel结束 -->
	</body>
</html>