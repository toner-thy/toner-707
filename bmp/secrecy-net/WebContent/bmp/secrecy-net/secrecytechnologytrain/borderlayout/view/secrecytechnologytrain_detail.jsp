<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	request.setAttribute("vEnter", "\n");
%>
<div class="panel">
	<!-- 头部 -->
	<div class="panel_header">
		<!-- 标题 -->
		<div class="panel_title panel_titleListIco">
		区、县（市）保密技术培训情况统计表
		</div>
	</div>
	<div class="panel_content panel_content_table">
		<form id="form_technology_add" class="form" action="<s:url action="secrecyTechnologyTrain_save" includeParams="true"/>" method="post">
			<div class="panel_content panel_content_table">
				<!-- 隐藏域 -->
				<input type="hidden" name="secrecyTechnologyTrain.id" id="secrecyTechnologyTrain.id" value="${secrecyTechnologyTrain.id }"/>
				<input type="hidden" name="secrecyTechnologyTrain.createPerson.userId" id="secrecyTechnologyTrain.createPerson.userId" value="${secrecyTechnologyTrain.createPerson.userId }"/>
				<input type="hidden" name="secrecyTechnologyTrain.modifyPerson.userId" id="secrecyTechnologyTrain.modifyPerson.userId" value="${secrecyTechnologyTrain.modifyPerson.userId }"/>
				<input type="hidden" name="secrecyTechnologyTrain.createTime" id="secrecyTechnologyTrain.createTime" value="<fmt:formatDate value="${secrecyTechnologyTrain.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				<input type="hidden" name="secrecyTechnologyTrain.modifyTime" id="secrecyTechnologyTrain.modifyTime" value="<fmt:formatDate value="${secrecyTechnologyTrain.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				<input type="hidden" name="secrecyTechnologyTrain.createOrgan.organId" id="secrecyTechnologyTrain.createOrgan.organId" value="${secrecyTechnologyTrain.createOrgan.organId }"/>
				<input type="hidden" name="secrecyTechnologyTrain.createDepartment.departmentId" id="secrecyTechnologyTrain.createDepartment.departmentId" value="${secrecyTechnologyTrain.createDepartment.departmentId }"/>

				<table class="content_table">
					<tr>
						<td class="tbLable fr">
							单位：（盖章）
						</td>
						<td colspan="2">
							${secrecyTechnologyTrain.reportOrgan.organName }
							<input size="5" type="hidden" id="secrecyTechnologyTrain.reportOrgan.organId" name="secrecyTechnologyTrain.reportOrgan.organId" value="${secrecyTechnologyTrain.reportOrgan.organId }" />
							<input type="hidden" name="secrecyTechnologyTrain.reportDepartment.departmentId" id="secrecyTechnologyTrain.reportDepartment.departmentId" value="${secrecyTechnologyTrain.reportDepartment.departmentId }"/>
						</td>
						<td>
							填表人：
						</td>
						<td colspan="2">
							${secrecyTechnologyTrain.reportUser.userInfo.name }
							<input size="5" type="hidden" id="secrecyTechnologyTrain.reportUser" name="secrecyTechnologyTrain.reportUser.userId" value="${secrecyTechnologyTrain.reportUser.userId }" />
						</td>
						<td>
							填报日期：
						</td>
						<td colspan="2">
							<fmt:formatDate value="${secrecyTechnologyTrain.reportDate}" pattern="yyyy-MM-dd"/>
							<input type="hidden" id="secrecyTechnologyTrain.reportDate" name="secrecyTechnologyTrain.reportDate" readonly="readonly" value="<fmt:formatDate value="${secrecyTechnologyTrain.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
							<input type="hidden" id="secrecyTechnologyTrain.year" name="secrecyTechnologyTrain.year" readonly="readonly" value="${secrecyTechnologyTrain.year}" />
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							保密培训
						</td>
						<td rowspan="1">
							培训情况
						</td>
						<td colspan="8">
						 	<c:if test="${not empty secrecyTechnologyTrain and not empty secrecyTechnologyTrain.secrecyTechnologyTrainContentSet }" >
								<c:forEach var="sttc" items="${secrecyTechnologyTrain.secrecyTechnologyTrainContentSet }" varStatus="status">
									${sttc.year }年，组织开展保密技术培训（ ${sttc.secrityTechTrainNum }）次，培训（${sttc.trainNum }）人次。
									<br/>
								</c:forEach>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>存在问题<br/>及意见<br/>和建议</td>
						<td colspan="8">
							<div>
								<pre>
									${fn:replace(secrecyTechnologyTrain.problemAdvice, vEnter , '<br/>') }
								</pre>
							</div>
						</td>
					</tr>
				</table>
				<!-- 附件 -->
				<div id="files_list"></div>

				<!-- 隐藏提交按钮 -->
				<div align="center">
					<input type="submit" id="sub" value="sub" style="display: none;" />
				</div>

			</div>
		</form>
	</div>
</div>