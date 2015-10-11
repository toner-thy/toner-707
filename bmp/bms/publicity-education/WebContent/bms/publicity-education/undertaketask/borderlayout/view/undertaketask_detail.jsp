<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>承担课题情况</title>

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
				});
			});

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
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:window.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						承担课题情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/undertaketask' action='undertaketask_editing' includeParams='true'/>" method="post">
						<input type="hidden" name="undertaketask.undertaketaskId" value="${undertaketask.undertaketaskId }"/>
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									课题（试点）名称：
								</td>
								<td class="tbValue fl">
									${undertaketask.taskName }
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									下达单位：
								</td>
								<td class="tbValue fl">
									${undertaketask.releaseUnit.organName}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									承办单位：
								</td>
								<td class="tbValue fl">
									${undertaketask.undertakeOrgan.organName}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">课题负责人：</td>
								<td class="tbValue fl">
									 ${undertaketask.projectLeader.name}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									承办人：
								</td>
								<td class="tbValue fl">
									<c:forEach var="uiTmp" items="${undertaketask.undertakerList }" varStatus="idx">
										<c:if test="${idx.index eq 0 }" >
											${uiTmp.name}
										</c:if>
										<c:if test="${idx.index ne 0 }" >
											,${uiTmp.name}
										</c:if>
									</c:forEach>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									完成时间：
								</td>
								<td class="tbValue fl">
									${undertaketask.completeTime }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									具体成效：
								</td>
								<td >
									<textarea rows="10" cols="150" style="width: 90%;" readonly="readonly">${undertaketask.specificResults }</textarea>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									获奖情况：
								</td>
								<td >
									<textarea rows="10" cols="150" style="width: 90%;" readonly="readonly">${undertaketask.awards }</textarea>
								</td>
							</tr>
							<!-- 附件 -->
							<tr>
								<td colspan="2">
									<div>
									    <attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" attachments="${attachmentList}" showTitle="false" />
				 					</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>

	</body>
</html>