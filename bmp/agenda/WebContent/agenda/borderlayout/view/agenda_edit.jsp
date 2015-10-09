<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑我的日程</title>

		<s:actionmessage theme="messages"/>
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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('是否继续编辑！')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('form_agenda_edit',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});
			var needReload = ${needReload };
  			var needReload2 = false;
			function doBack(){
				window.location.href="<s:url action="agenda_list" namespace="/agenda/agenda"/>";
			}

			function doEdit(){
				if (formcheck.isFormValid(true)) {
			    	$('sub').click();
			    	$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				}
	    	}
		</script>

		<style type="text/css">
			.data_sys_input{
				width: 140px;;
			}
		</style>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();"  id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="###" onclick="javascript:doEdit();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:TabUtil.closeTab();"><span>返 回</span></a>
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
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑我的日程
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_agenda_edit" class="form" enctype="multipart/form-data" action="<s:url action="agenda_editing" namespace="/agenda/agenda"  includeParams="true"/>" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" name="agenda.agendaId" value="${agenda.agendaId}"/>

						<table class="content_table">
							<tr>
								<td align="right" width="15%">
									日程标题：
								</td>
								<td width="75%" align="left" colspan="3">
									<input name="agenda.agendaTitle" value="${agenda.agendaTitle}" style="width: 500px;"  type="text" class="validate['required','length[1,40]']"/> <font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td align="right" width="15%">
									时间安排：
								</td>
								<td align="left" width="50%" colspan=3">
									<input type="text" id="agenda.startTime" title="开始时间" name="agenda.startTime" value="<s:date format="yyyy-MM-dd HH:mm" name="agenda.startTime"/>" class="Wdate validate['required'] data_sys_input" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly"/>
									&nbsp;&nbsp;--&nbsp;
									<input type="text" name="agenda.endTime" value="<s:date format="yyyy-MM-dd HH:mm" name="agenda.endTime"/>" class="Wdate validate['required','dateAfter[\'agenda.startTime\']'] data_sys_input" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly="readonly"/> <font color="red">*</font>
						   		</td>
							</tr>
							<tr>
								<td align="right" valign="top">日程安排：</td>
								<td colspan="3">
									<textarea style="width: 700px;height: 200px;" name="agenda.agendaContent" class="validate['length[1,1000]']">${agenda.agendaContent}</textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;"/>
						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>