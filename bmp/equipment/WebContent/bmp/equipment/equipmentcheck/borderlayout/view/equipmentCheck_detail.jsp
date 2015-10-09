<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密装备配备检测详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
			function doClose(){
				window.close();
			}

		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left"></div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 内容panel开始-->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密装备配备检测详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table">
						<tr bgcolor="#F3FBFE" >
							<td colspan="11" align="center" height="60">
								<font style="font-size:24px;font-weight: bold; font-family: '楷体_gb2312';">
									保密装备配备检测详情
								</font>
							</td>
						</tr>
					</table>

				 	<table class="content_table">
						<tr align="center" bgcolor="#FFFFFF" style="height: 28px;">
							<td align="right" width="15%">
								检测名称：
							</td>
							<td align="left" width="30%">
								${equipmentCheck.equipmentName}
							</td>
							<td align="right" width="20%">
								装备所属单位：
							</td>
							<td align="left" width="40%">
								<c:if test="${equipmentCheck.equipmentOrgan==''}">
									暂未填写
								</c:if>
								<c:if test="${equipmentCheck.equipmentOrgan!=''}">
									${equipmentCheck.equipmentOrgan}
								</c:if>
							</td>
					 	</tr>
					 	<tr align="center" bgcolor="#FFFFFF" style="height: 28px;">
						 	<td align="right">
								装备型号：
							</td>
							<td align="left">
								${equipmentCheck.equipmentType}
							</td>
							<td align="right">
						 		数 量：
							</td>
							<td align="left">
								<div style="font-family: Arial, "Times New Roman" !important;">
									${equipmentCheck.number}
								</div>
							</td>
					 	</tr>
					 	<tr align="center" bgcolor="#FFFFFF" style="height: 28px;">
						 	<td align="right">
								产 地：
							</td>
							<td align="left">
								<c:if test="${equipmentCheck.equipmentProducingArea==''}">
									暂未填写
								</c:if>
								<c:if test="${equipmentCheck.equipmentProducingArea!=''}">
									${equipmentCheck.equipmentProducingArea}
								</c:if>
							</td>

							<td align="right">
								检查时间：
							</td>
							<td align="left">
								<div style="font-family: Arial, "Times New Roman" !important;">
									<s:date name="#attr.equipmentCheck.checkTime" format="yyyy年MM月dd日"/>
								</div>
							</td>
						</tr>
					 	<tr align="center" bgcolor="#FFFFFF" style="height: 28px;">
							<td align="right">
						 		用 途：
							</td>
							<td align="left" colspan="3" height="100" valign="top">
								<c:if test="${equipmentCheck.equipmentPurpose==''}">
									暂未填写
								</c:if>
								<c:if test="${equipmentCheck.equipmentPurpose!=''}">
									${equipmentCheck.equipmentPurpose}
								</c:if>
							</td>
					 	</tr>

					 	<tr align="center" bgcolor="#FFFFFF" style="height: 28px;">
						 	<td align="right">
								检查单位：
							</td>
							<td align="left">
								${equipmentCheck.checkOrgan.organName}
							</td>
							<td align="right">
						 		检查人：
							</td>
							<td align="left">
								${equipmentCheck.checkPerson.name }
								<%-- ${equipmentCheck.checkPerson.name } --%>
							</td>
					 	</tr>

					 	<tr align="center" bgcolor="#FFFFFF" style="height: 28px;">
						 	<td align="right">
								检查内容：
							</td>
							<td align="left" colspan="3" height="100px;" valign="top">
								<c:if test="${equipmentCheck.checkContent==''}">
									暂未填写
								</c:if>
								<c:if test="${equipmentCheck.checkContent!=''}">
									${equipmentCheck.checkContent}
								</c:if>
							</td>
					 	</tr>

					 	<tr align="center" bgcolor="#FFFFFF" style="height: 28px;">
						 	<td align="right">
								备 注：
							</td>
							<td align="left" colspan="3" height="100px;" valign="top">
								<c:if test="${equipmentCheck.remark==''}">
									暂未填写
								</c:if>
								<c:if test="${equipmentCheck.remark!=''}">
									${equipmentCheck.remark}
								</c:if>
							</td>
					 	</tr>
					</table>
				</div>
			</div>
		<!-- 内容panel结束-->
		</div>
	</body>
</html>