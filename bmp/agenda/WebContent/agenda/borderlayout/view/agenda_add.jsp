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
		<title>新增我的日程</title>

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js", function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('新增成功，是否继续添加？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('form_agenda_add',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					<c:if test="${other == true}">
						Util.selectUser("selector_agendaExecutor","agendaExecutor","agenda.agendaExecutor.userId");
					</c:if>
				});
			});
  			var needReload = ${needReload };
  			var needReload2 = false;

			function doBack(){
				window.location.href="<s:url action="agenda_list" namespace="/agenda/agenda"/>";
			}

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
					<a class="pop_button" href="###" onclick="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
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
						新增我的日程
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_agenda_add" class="form" enctype="multipart/form-data" action="<s:url action="agenda_adding" namespace="/agenda/agenda"  includeParams="true"/>" method="post">
						<table class="content_table">
							<tr>
								<td align="right" width="15%">
									日程标题：
								</td>
								<td width="75%" align="left" colspan="3">
									<input name="agenda.agendaTitle"  type="text" style="width: 500px;" class="validate['required','length[1,40]']"/> <font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td align="right" width="15%">
									时间安排：
								</td>
								<td align="left" width="50%" colspan="3">
									<input type="text" id="agenda.startTime" title="开始时间" name="agenda.startTime" value="" class="Wdate validate['required'] data_sys_input" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'agenda.endTime\')||\'2020-10-01\'}'})" readonly="readonly"/>
									&nbsp;&nbsp;--&nbsp;
									<input type="text" id="agenda.endTime" name="agenda.endTime" value="" class="Wdate validate['required','dateAfter[\'agenda.startTime\']'] data_sys_input" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'agenda.startTime\')}',maxDate:'2020-10-01'})" readonly="readonly"/> <font color="red">*</font>
						   		</td>
							</tr>
							<tr>
								<td align="right" valign="top">日程安排：</td>
								<td colspan="3">
									<textarea style="width: 700px;height: 200px;" id="agenda.agendaContent" name="agenda.agendaContent" class="validate['length[1,1000]']"></textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" name="sub" value="sub" style="display: none;"/>
						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>