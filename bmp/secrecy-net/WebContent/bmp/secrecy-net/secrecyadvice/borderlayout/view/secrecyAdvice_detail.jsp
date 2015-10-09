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
<%
	request.setAttribute("vEnter", "\n");
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="panel">
	<div class="panel_header">
		<div class="panel_title panel_titleListIco">
			新增区、县（市）网络保密管理情况意见建议汇总表
		</div>
	</div>
	<div class="panel_content panel_content_table">
		<table class="content_table st" width="100%">
			<tr>
				<td align="center" colspan="6">
					<b><font style="font-size: 20px;">区、县（市）网络保密管理情况意见建议汇总表</font></b>
				</td>
			</tr>
			<tr>
				<td align="right" width="20%;">
					单位：（盖章）
				</td>
				<td  width="15%;">
					${secrecyAdvice.organ.name }
				</td>
				<td class="tbLable fr" width="20%;">
					填表人：
				</td>
				<td width="15%">
					${secrecyAdvice.reportUser.userInfo.name}
				</td>
				<td class="tbLable fr" width="20%">
					填报日期：
				</td>
				<td>
					<fmt:formatDate value="${secrecyAdvice.reportTime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					网络保密管理工作中存<br>
						在的其他问题
				</td>
				<td class="tbValue fl" colspan="5">
					<div>${fn:replace(secrecyAdvice.question, vEnter , '<br/>') }</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					建立"自上而下、覆盖全<br>
					国网络保密监管体系"的<br>
						对策和建议
				</td>
				<td class="tbValue fl" colspan="5">
					<div>${fn:replace(secrecyAdvice.advise, vEnter , '<br/>')}</div>
				</td>
			</tr>
		</table>
	</div>
</div>