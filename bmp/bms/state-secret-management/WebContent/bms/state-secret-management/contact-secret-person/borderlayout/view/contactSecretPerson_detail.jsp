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
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>接触和知悉绝密级国家秘密文件人员情况</title>

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
						接触和知悉绝密级国家秘密文件人员情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/contactSecretPerson' action='contactSecretPerson_editing' includeParams='true'/>" method="post">
						<input type="hidden" name="contactSecretPerson.contactSecretPersonId" value="${contactSecretPerson.contactSecretPersonId }" />
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									文件名称：
								</td>
								<td class="tbValue fl" colspan="3">
									${contactSecretPerson.secretFileName }
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									发文机关：
								</td>
								<td class="tbValue fl">
									${contactSecretPerson.dispatchOrgan }
								</td>
								<td class="tbLable fr">
									发文日期：
								</td>
								<td class="tbValue fl">
									${contactSecretPerson.issuedDate }
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									收文日期：
								</td>
								<td class="tbValue fl">
									${contactSecretPerson.receiveDate }
								</td>
								<td class="tbLable fr">
									数量：
								</td>
								<td class="tbValue fl">
									${contactSecretPerson.fileNum }
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									人员姓名：
								</td>
								<td class="tbValue fl">
									${contactSecretPerson.contactPersonName }
								</td>
								<td class="tbLable fr">
									接触时间：
								</td>
								<td class="tbValue fl">
									${contactSecretPerson.contactDate }
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									接触类型：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="contactType" optionValue="${contactSecretPerson.contactType}" ></dictionary:text>
								</td>
								<td class="tbLable fr">
									接触方式：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="contactWay" optionValue="${contactSecretPerson.contactWay}" ></dictionary:text>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									承办人：
								</td>
								<td class="tbValue fl" colspan="3">
									${contactSecretPerson.undertackerNames }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									审批领导：
								</td>
								<td class="tbValue fl" colspan="3">
									${contactSecretPerson.approvedLeaderNames }
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>

	</body>
</html>