<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>单位设备配备详情</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<s:actionmessage theme="messages"/>

		<script language="javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
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
			<div class="left">
			</div>
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
						单位保密设备详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<c:if test="${equipment!=null}">
						 <table class="content_table">
							<tr>
								<td width="20%" align="right">
									设备名称：
								</td>
								<td style="padding-left: 10px;" width="30%">
									<div style="word-wrap:break-word;overflow: hidden;width: 250px">
										${equipment.equipmentType.name }
									</div>
								</td>
								<td align="right" height="30" width="20%">
									数 量：
								</td>
								<td style="padding-left: 10px;" width="30%">
									<div style="font-family: Arial, "Times New Roman" !important;">
										${equipment.number}
									</div>
								</td>
							</tr>
							<tr>
								<td align="right" height="36">
									创建人：
								</td>
								<td style="padding-left: 10px;">
									${equipment.createPerson.userInfo.name }
								</td>
								<td align="right" height="36">
									创建时间：
								</td>
								<td style="padding-left: 10px;">
									<div style="font-family: Arial, "Times New Roman" !important;">
										<s:date name="#attr.equipment.createTime" format="yyyy年MM月dd日"/>
									</div>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right" height="80">
									说 明：
								</td>
								<td colspan="3" height="100px;" valign="top">
									${equipment.equipmentType.description}
								</td>
							</tr>
						</table>
					</c:if>
					<c:if test="${equipment==null}">
						<u:noData text="暂无数据"/>
					</c:if>
				</div>
			</div>
			<!-- 内容panel结束-->
		</div>
	</body>
</html>