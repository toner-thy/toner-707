<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>

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
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
		</script>
		<style type="text/css">
			html, body {overflow: auto}
		</style>
	</head>
	<body>
		<div class="panel_content panel_content_table">
			<div class="panel tMargin">
				<s:if test="#attr.secrecyOfficeMemberDecryptionList.size>0">
					<ec:table items="secrecyOfficeMemberDecryptionList" var="m" tableId="secrecyOfficeMemberDecryptionList" border="0"
						action="${ctx}/secrecyorganization/secrecyofficemember/secrecyOfficeMember_decryptionList.action"
						imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit" showPagination="true"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="rowCount" cell="rowCount" sortable="false" title="序号" width="5%"/>
							<ec:column property="secrecyOfficeMember.person.name" title="姓 名" width="10%" cell="text"/>
							<ec:column property="null" title="脱密类型" width="10%">
								<dictionary:text tableCode="bmp" fieldCode="decryption_type" optionValue="${m.decryptionType}"/>
							</ec:column>
							<ec:column property="decryptionLimit" title="脱密期限" width="10%" cell="text"/>
							<ec:column property="null" title="期限单位" width="10%">
								<dictionary:text tableCode="bmp" fieldCode="decryption_limit_type" optionValue="${m.limitMeasure}"/>
							</ec:column>
							<ec:column property="decryptionStart" title="脱密时间起" cell="date" format="yyyy-MM-dd" width="10%"/>
							<ec:column property="decryptionEnd" title="脱密时间止" cell="date" format="yyyy-MM-dd" width="10%"/>
							<ec:column property="decryptionReason" title="脱密原因" width="20%" cell="text"/>
						</ec:row>
					</ec:table>
				</s:if>
				<s:else>
					<u:noData text="当前暂无数据" />
				</s:else>

			</div>
		</div>
	</body>
</html>