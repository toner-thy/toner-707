<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%
	request.setAttribute("vEnter", "\n");
%>

<!-- 内容panel开始 -->
<c:forEach var="secrecyBasic" items="${secrecyBasicList}" varStatus="sta">
<div class="panel" style="margin-bottom: 5px;">
	<div class="panel_header">
		${secrecyBasic.title}涉密网络基本情况统计表 - ${sta.index + 1}
	</div>
	<div class="panel_content panel_content_table">
			<table border="0" style="width: 100%;">
				<tr>
					<td align="left" style="padding-left: 20px;">单位：（盖章）&nbsp;&nbsp; ${secrecyBasic.reportOrgan.organName}</td>
					<td align="center">填表人：&nbsp;&nbsp; ${secrecyBasic.reportUser.userInfo.name}</td>
					<td align="right" style="padding-right: 20px;">填报日期：&nbsp;&nbsp; <fmt:formatDate value="${secrecyBasic.reportDate }" pattern="yyyy-MM-dd"/></td>
				</tr>
			</table>
			<table class="content_table">
				<tr>
					<td align="center" width="20%">网络名称</td>
					<td colspan="2" align="left" width="80%">${secrecyBasic.name }</td>
				</tr>
				<tr>
					<td align="center">网络密级</td>
					<td colspan="2" align="left">
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.secrecyLevel eq 1 }">checked="checked"</c:if> value="1"/>绝密
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.secrecyLevel eq 2 }">checked="checked"</c:if> value="2"/>机密
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.secrecyLevel eq 3 }">checked="checked"</c:if> value="3"/>秘密
					</td>
				</tr>
				<tr>
					<td rowspan="3" align="center">建设基本情况</td>
					<td align="left">
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.basic eq 1}">checked="checked"</c:if> value="1"/>上级涉密网络延伸
					</td>
					<td rowspan="3" align="left" width="60%;">简要说明：<br/><div>${fn:replace(secrecyBasic.basicExplanation, vEnter , '<br/>')}</div></td>
				</tr>
				<tr>
					<td align="left">
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.basic eq 2}">checked="checked"</c:if> value="2"/>自行组网加入上级涉密网络
					</td>
				</tr>
				<tr>
					<td align="left">
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.basic eq 3}">checked="checked"</c:if> value="3"/>自行建设的涉密网络
					</td>
				</tr>
				<tr>
					<td align="center" width="20%">安全保密责任机构</td>
					<td colspan="2" align="left" width="80%">${secrecyBasic.safeSecrecyOrgan }</td>
				</tr>
				<tr>
					<td align="center" width="20%">运行维护机构</td>
					<td colspan="2" align="left" width="80%">${secrecyBasic.workMaintainOrgan}</td>
				</tr>
				<tr>
					<td align="center" width="20%">“三员”情况</td>
					<td colspan="2" align="left" width="80%">
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.threePeople eq 1 }">checked="checked"</c:if> value="1"/>已指定
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.threePeople eq 0 }">checked="checked"</c:if>  value="0"/>未指定
					</td>
				</tr>
				<tr>
					<td align="center" width="20%">主要业务应用系统</td>
					<td colspan="2" align="left" width="80%">${secrecyBasic.mainSystem}</td>
				</tr>
				<tr>
					<td align="center" width="20%">用户数量</td>
					<td colspan="2" align="left" width="80%">${secrecyBasic.userNum}</td>
				</tr>
				<tr>
					<td align="center" width="20%">网络使用范围</td>
					<td colspan="2" align="left" width="80%">${secrecyBasic.netRange}</td>
				</tr>
				<tr>
					<td align="center" width="20%">网络终端数量</td>
					<td colspan="2" align="left" width="80%">
						规划共${secrecyBasic.netTerminalGhNum}个；
						现接入${secrecyBasic.netTerminalJrNum}个
					</td>
				</tr>
				<tr>
					<td rowspan="2" align="center" width="20%">现有安全保密技术防护措施</td>
					<td colspan="2" align="left" width="80%">
						<input type="checkbox" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.cushiIdcard eq 1 }">checked="checked"</c:if> value="1"/>身份鉴定；
						<input type="checkbox" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.cushiVisitControl eq 1 }">checked="checked"</c:if> value="1"   />访问控制；
						<input type="checkbox" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.cushiProcessControl eq 1 }">checked="checked"</c:if> value="1"  />流转控制
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left" width="80%">
						<input type="checkbox" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.cushiSafe eq 1 }">checked="checked"</c:if> value="1"  />安全审计；
						<input type="checkbox" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.cushiBianjie eq 1 }">checked="checked"</c:if> value="1"  />边界防护；
						<input type="checkbox" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.cushiPassword eq 1 }">checked="checked"</c:if> value="1"  />密码保护
					</td>
				</tr>
				<tr>
					<td align="center" width="20%">主机房地址</td>
					<td colspan="2" align="left" width="80%">${secrecyBasic.address }</td>
				</tr>
				<tr>
					<td rowspan="2" align="center" width="20%">与其他网络连接情况</td>
					<td colspan="2" align="left" width="80%">
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.linkedNo eq 0 }">checked="checked"</c:if> value="0"/>无
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left" width="80%">
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.linkedNo eq 1 }">checked="checked"</c:if> value="1"/>有，与${secrecyBasic.linkedYesContent }网络连接。
					</td>
				</tr>
				<tr>
					<td align="center">安全保密测评情况</td>
					<td colspan="2" align="left" width="80%">
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.safeSecrecyCeping eq 1 }">checked="checked"</c:if> value="1"/>通过测评
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.safeSecrecyCeping eq 2 }">checked="checked"</c:if> value="2" />测评中
						<input type="radio" readonly="readonly" onclick="return false;" <c:if test="${secrecyBasic.safeSecrecyCeping eq 3 }">checked="checked"</c:if> value="3"/>未申报测评
					</td>
				</tr>
				<tr>
					<td align="center">保密行政管理部门审批情况</td>
					<td colspan="2" align="left" width="80%">
						<input type="radio" readonly="readonly" onclick="return false;"  <c:if test="${secrecyBasic.audit eq 1 }">checked="checked"</c:if> value="1" />通过审批
						<input type="radio" readonly="readonly" onclick="return false;"  <c:if test="${secrecyBasic.audit eq 2 }">checked="checked"</c:if> value="2" />审批中
						<input type="radio" readonly="readonly" onclick="return false;"  <c:if test="${secrecyBasic.audit eq 3 }">checked="checked"</c:if> value="3" />未申请审批
					</td>
				</tr>
			</table>
	</div>
</div>
</c:forEach>