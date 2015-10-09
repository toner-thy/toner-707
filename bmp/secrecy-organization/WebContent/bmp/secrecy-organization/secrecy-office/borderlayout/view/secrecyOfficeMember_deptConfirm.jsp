<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密办（保密局）</title>

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
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js", function(){
				$ENV.dialog.onClose = function(event){
					event.stop = true;
					alert("不能关闭,请先选择部门修改方式。");
				}
			});

			function doSelect(value, title){
				var value;
				var title;
				var chkObjs = document.getElementsByName('dept');
				for(var i =0; i<chkObjs.length; i++){
					if(chkObjs[i].checked){
						value = chkObjs[i].value;
					}
				}
				if(value == 1){
					title = "部门改名";
				} else {
					title = "部门变更";
				}
				$ENV.dialog.getOpener().$('deptFlag').value = value;
				$ENV.dialog.getOpener().$('deptFlagShow').setStyle("display","");
				$ENV.dialog.getOpener().$('deptFlagShow').innerHTML = title;
				$ENV.dialog.getOpener().popFlag = true;
				$ENV.dialog.close();
			}
		</script>

	</head>

	<body>
		<div class="button_bar">
			<div class="left">
			</div>
			<div class="right">
			</div>
		</div>
		<div class="body_content">
			<div class="panel tMargin">
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='' action='.action' includeParams='true'/>" method="post">
					<table class="content_table" width="100%">
						<tr>
							<td class="tbLable fr">
								请选择部门修改方式：
							</td>
							<td class="tbValue fl" colspan="3">
								<input type="radio" name="dept" value="1"/>部门改名
								<input type="radio" name="dept" value="2"/>部门变更
								<input type="button" class="pop_button" value="确 定" onclick="doSelect()">
							</td>
						<tr/>
					</table>
					<!-- 隐藏提交按钮 -->
					<div align="center">
						<input id="sub" value="sub" type="submit" style="display: none;"/>
					</div>
				</form>
				</div>
			</div>

		</div>
	</body>
</html>