<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- 内容panel开始 -->
	<s:if test="#request.partList.size>0">
		<ec:table items="partList" var="part" tableId="partList" border="0"
			action="${ctx}/bmp/part/nestedpart_list.action"
			imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
			width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
			filterable="false" autoIncludeParameters="false" sortable="false" showPagination="false">
			<ec:row>
				<ec:column property="partId" alias="partId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
				<ec:column property="partName" title="名 称" width="20%" cell="text" alias="size=8"/>
				<ec:column property="department.departmentName" title="主管部门" width="20%" cell="text" alias="size=8"/>
				<ec:column property="null" title="涉密等级" width="10%">
					<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}"></dictionary:text>
					<input type="hidden" name="${part.partId}_reportState" id="${part.partId}_reportState" value="${part.reportState}">
				</ec:column>
				<ec:column property="person.name" title="负责人" width="15%" cell="text" alias="size=10"/>
				<ec:column property="phone" title="联系电话" width="20%" cell="text" alias="size=20"/>
				<ec:column property="null" title="详 情" width="10%">
					<a href="javascript:doDetail('${part.partId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
				</ec:column>
			</ec:row>
		</ec:table>
	</s:if>
	<s:else>
		<u:noData text="当前部门下没有要害部位，请点击【新增】按钮添加。"/>
	</s:else>
<!-- 内容panel结束 -->
<!-- 删除用隐藏表单 -->
<form action="" method="post" id="partDelForm">
	<input type="hidden" name="partIds" id="partIds"/>
</form>