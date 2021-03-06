<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>常见问题问答</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/FCKeditor/fckeditor.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					var sBasePath ='${ctx}/resources/FCKeditor/';
					var oFCKeditor = new FCKeditor('sysQuestion.questionContent');
					oFCKeditor.BasePath	= sBasePath ;
					oFCKeditor.Height	= 250 ;
					oFCKeditor.ReplaceTextarea() ;

					var sFCKeditor = new FCKeditor('sysAnswer.answerContent');
					sFCKeditor.BasePath	= sBasePath ;
					sFCKeditor.Height	= 450 ;
					sFCKeditor.ReplaceTextarea() ;

					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			function doSave(){
				if (formcheck.isFormValid(true)) {
					var Editor = FCKeditorAPI.GetInstance('sysQuestion.questionContent');
				 	var acontent = Editor.GetXHTML();
				 	if(acontent.length > 65535){
						alert("提示，[正文]内容长度超出系统限制,您可以通过粘贴时清理CSS样式来减少冗余字符。");
						return;
				 	}

					var sEditor = FCKeditorAPI.GetInstance('sysAnswer.answerContent');
				 	var acontents = sEditor.GetXHTML();
				 	if(acontents.length<=0){
						alert("提示，[正文]不能为空。");
						return;
				 	}
				 	if(acontents.length > 65535){
						alert("提示， [正文]内容长度超出系统限制,您可以通过粘贴时清理CSS样式来减少冗余字符。");
						return;
				 	}

					// 导出word时使用，去掉FCK格式
			 		//$('annualPlan.annualPlanContentPre').value = document.all?Editor.EditorDocument.body.innerText.trim():Editor.EditorDocument.body.textContent.trim();
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				 }
			}

			function doBackList(){
				if( confirm("确定退出吗？") ){
					window.location.href = "${ctx}/bmp/sysQuestion/sysQuestion_list.action?_ts="+ new Date().getTime();
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
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBackList();" id="back_button"><span>返回</span></a>
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
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="常见问题问答简介" ctx="${ctx}" icoPath="#">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','常见问题问答简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						常见问题问答
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="annual_plan_description"> </cpc:tc>
					</div>
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						常见问题问答
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/sysQuestion' action='sysQuestion_adding' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									问题类别：
								</td>
								<td class="tbValue fl">
									<s:select list="#{0:'无类别'}" cssStyle="width:131px;"	theme="simple" value="0">
									</s:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									问题标题：
								</td>
								<td class="tbValue fl">
									<input type="text" name="sysQuestion.questionTitle" size="50" value="${sysQuestion.questionTitle }" class="validate['required','length[100]']"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									问题内容：
								</td>
								<td class="tbValue fl">
									<textarea name="sysQuestion.questionContent" >${sysQuestion.questionContent }</textarea>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									答案内容：
								</td>
								<td class="tbValue fl">
									<textarea name="sysAnswer.answerContent" >${sysAnswer.answerContent }</textarea>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
						</table>
						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>
<!-- 					<table class="content_table st" id="table_part_add" width="100%"> -->
<!-- 						<tr> -->
<!-- 							<td class="fr"> -->
<!-- 								附件上传： -->
<!-- 							</td> -->
<!-- 							<td class="fl" colspan="3"> -->
<%-- 								<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="add_form" applyName="attachs" titleText="附件上传" limit="1"/> --%>
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 					</table> -->
				</div>
			</div>
		</div>

	</body>
</html>