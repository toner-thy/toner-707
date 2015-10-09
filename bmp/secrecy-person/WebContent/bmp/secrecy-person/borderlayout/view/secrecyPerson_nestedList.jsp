<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

		<s:if test="#request.secrecyPersonList.size>0">
			<ec:table items="secrecyPersonList" var="secrecyPerson" tableId="secrecyPersonList" border="0"
				action="${ctx}/bmp/secrecyperson/nested/secrecyPersonList_list.action"
				imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
				width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
				filterable="false" autoIncludeParameters="true" sortable="false" showPagination="false">
				<ec:row>
					<ec:column property="secrecyPersonId" alias="secrecyPersonId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
					<ec:column property="userInfo.name" title="姓 名" cell="text"/>
					<ec:column property="null" title="涉密等级" >
						<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${secrecyPerson.secrecyPersonLevel}"/>
						<input type="hidden" name="${secrecyPerson.secrecyPersonId}_reportState" id="${secrecyPerson.secrecyPersonId}_reportState" value="${secrecyPerson.reportState}">
					</ec:column>
					<ec:column property="officeDuty" title="行政职务" cell="text"/>
					<ec:column property="null" title="岗 位" cell="text">
						<c:if test="${secrecyPerson.post == ''}">
							暂无
						</c:if>
						<c:if test="${secrecyPerson.post != ''}">
							${secrecyPerson.post}
						</c:if>
					</ec:column>
					<ec:column property="null" title="联系电话" cell="text">
						<c:if test="${secrecyPerson.officePhone == ''}">
							暂无
						</c:if>
						<c:if test="${secrecyPerson.officePhone != ''}">
							${secrecyPerson.officePhone}
						</c:if>
					</ec:column>
					<ec:column property="null" title="详 情" width="10%">
						<a href="javaScript:doDetailSecPerson('${secrecyPerson.secrecyPersonId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
					</ec:column>
				</ec:row>
			</ec:table>
		</s:if>
		<s:else>
			<u:noData text="当前暂无涉密人员，请点击【新增】按钮添加。"/>
		</s:else>

		<!-- 删除用隐藏表单 -->
		<form action="" method="post" id="secrecyPersonDelForm">
			<input type="hidden" name="secrecyPersonIds" id="secrecyPersonIds"/>
		</form>
