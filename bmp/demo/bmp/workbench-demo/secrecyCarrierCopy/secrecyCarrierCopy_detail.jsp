<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密委员会</title>

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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			function doSave(){
				window.location.href = "${ctx}/bmp/workbench-demo/participateActivitie/participateActivitie_list.jsp";
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密载体复印情况详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecyoffice' action='secrecyOffice_adding' includeParams='true'/>" method="post">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									文件名称：
								</td>
								<td class="tbValue fl" colspan="3">
									XX议题
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									文号：
								</td>
								<td class="tbValue fl">
									皖政字〔2004 〕18号
								</td>
								<td class="tbLable fr">
									复制日期：
								</td>
								<td class="tbValue fl">
									2014-03-24
								</td>

							</tr>
							<tr>

								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									机密
								</td>
								<td class="tbLable fr">
									承办人：
								</td>
								<td class="tbValue fl">
									李青
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									每份页数：
								</td>
								<td class="tbValue fl">
									20
								</td>
								<td class="tbLable fr">
									申请人：
								</td>
								<td class="tbValue fl">
									刘武
								</td>

							</tr>
							<tr>
							    <td class="tbLable fr">
									份数：
								</td>
								<td class="tbValue fl">
									2
								</td>

								<td class="tbLable fr">
									文件制发单位：
								</td>
								<td class="tbValue fl">
								    四川省国家保密局
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									申请部门：
								</td>
								<td class="tbValue fl">
									无
								</td>
								<td class="tbLable fr">
									批准人：
								</td>
								<td class="tbValue fl">
									张敏
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									用途：
								</td>
								<td class="tbValue fl" colspan="3">
									分发给单位人员查阅，学习
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									备注：
								</td>
								<td class="tbValue fl" colspan="3">
								    暂无
								</td>
							</tr>
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