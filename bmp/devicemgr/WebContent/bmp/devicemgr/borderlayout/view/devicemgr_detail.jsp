<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密设备详情</title>
		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){

				});
			});
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar no_print">
			<div class="left">
				<div class="pop_button_bar no_print">
				</div>
			</div>
			<div class="right no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="panel tMargin" style="margin-top: -1px;">
			<!-- 内容panel开始 -->
			<div class="panel">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						保密设备详情
					</div>
					<div class="panel_btn_bar pop_button_bar no_print">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table">
						<tr height="36px;">
							<td class="tbLable fr">
								设备分类：
							</td>
							<td class="tbValue fl" colspan="3">
								&nbsp;&nbsp;保密技术设备
							</td>
						</tr>
						<tr height="36px;">
							<td class="tbLable fr">
								产品名称：
							</td>
							<td class="tbValue fl">
								&nbsp;&nbsp;${secrecyEquipment.name}
							</td>
							<td class="tbLable fr">
								型号/序号：
							</td>
							<td class="tbValue fl">
								&nbsp;&nbsp;${secrecyEquipment.typeCode}
							</td>
						</tr>
						<tr height="36px;">
							<td class="tbLable fr">
								设备数量：
							</td>
							<td class="tbValue fl">
								&nbsp;&nbsp;${secrecyEquipment.number}
							</td>
							<td class="tbLable fr">
								设备单价(单位：元)：
							</td>
							<td class="tbValue fl">
								&nbsp;&nbsp;${secrecyEquipment.unitPrice}
							</td>
						</tr>

						<tr height="36px;">
							<td class="tbLable fr">
								价格(单位：元)：
							</td>
							<td class="tbValue fl">
								&nbsp;&nbsp;${secrecyEquipment.price}
							</td>
							<td class="tbLable fr">
								购买时间：
							</td>
							<td class="tbValue fl">
								&nbsp;&nbsp;<s:date name="secrecyEquipment.buyTime" format="yyyy-MM-dd"/>
							</td>
						</tr>

						<tr height="36px;">
							<td class="tbLable fr">
								领用单位：
							</td>
							<td class="tbValue fl">
								&nbsp;&nbsp;${secrecyEquipment.obtainOrgan.organName}
							</td>
							<td class="tbLable fr">
								责任人：
							</td>
							<td class="tbValue fl">
								&nbsp;&nbsp;${secrecyEquipment.dutyPerson.name}
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>