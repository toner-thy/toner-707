<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密人员脱密信息详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

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
					$('msg_list').load($('msg_list').get('url'));
					$('person_msg').load($('person_msg').get('url'));
					$('decryption_list').load($('decryption_list').get('url'));
				});
			});

		</script>

  		<!--print 这个属性可以在打印时有效-->
		<style type="text/css" media="print">
			.no_print{display:none;}
			.PageNext{page-break-after: always;}
		</style>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="print_but_bar no_print">
			<div class="left">
				<div class="print_pop_button no_print">
					<a class="print_pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right no_print">
				<div class="print_pop_button no_print">
<%-- 					<a class="print_pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
 --%>
 				</div>
			</div>
		</div>
		<div id="person_msg" url="${ctx}/bmp/secrecyperson/secrecyPerson_personMsg.action?secrecyPerson.secrecyPersonId=${secrecyPersonDecryption.secrecyPersonId.secrecyPersonId}"></div>
		<div class="print_panel" style="border: 0px;">
			<div id="msg_list" url="${ctx}/bmp/secrecyperson/secrecyPerson_personSecrecyLevelChangeHis.action?secrecyPerson.secrecyPersonId=${secrecyPersonDecryption.secrecyPersonId.secrecyPersonId}"></div>
		</div>
		<div class="print_panel" style="border: 0px;">
			<div id="decryption_list" url="${ctx}/bmp/secrecyperson/secrecyPerson_personDecryptionHis.action?secrecyPerson.secrecyPersonId=${secrecyPersonDecryption.secrecyPersonId.secrecyPersonId}"></div>
		</div>

	</body>
</html>