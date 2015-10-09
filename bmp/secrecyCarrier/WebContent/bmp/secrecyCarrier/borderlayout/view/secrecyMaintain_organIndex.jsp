<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
</head>

<style type="text/css">
	.contentTable td{
		border-color: #B9D2DE;
		padding: 5px 5px;
	}
	.contentTable {
	    background-color: #FFFFFF;
	    color: #446586;
	}
</style>

<body>

<table class="contentTable" width="900" border="1" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td colspan="11" align="center">涉密设备维修登记情况</td>
        </tr>
        <tr>
          <td width="204" align="center" >序号</td>
          <td width="116" align="center">使用部门</td>
          <td width="159" align="center" >设备类型（品牌型号）</td>
          <td width="159" align="center" >密级</td>
          <td width="73" align="center" >维修原因</td>
          <td width="73" align="center" >送修时间</td>
          <td width="73" align="center" >维修单位</td>
          <td width="73" align="center" >监修人</td>
          <td width="73" align="center" >部门意见</td>
          <td width="73" align="center" >领导审批意见</td>
          <td width="73" align="center" >备注</td>
        </tr>
        <c:forEach items="${secrecyMaintainList}" var="secrecyMaintain" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td>
				${xm.index+1 }
			</td>
			<td>
				${secrecyMaintain.useDepartment.departmentName }
			</td>
			<td>
				${secrecyMaintain.type }
			</td>
			<td>
				${secrecyMaintain.secrecyLevelTxt }
			</td>
			<td >
				${fn:substring(secrecyMaintain.reason, 0, 12)}
				<c:if test="${fn:length(secrecyMaintain.reason)>12}">...</c:if>
			</td>
			<td >
				<fmt:formatDate value="${secrecyMaintain.date }" pattern="yyyy-MM-dd"/>
			</td>
			<td>
				${secrecyMaintain.maintainOrgan.organName }
			</td>
			<td>
				${secrecyMaintain.seeUserInfo.name }
			</td>
			<td >
				${fn:substring(secrecyMaintain.depIdea, 0, 12)}
				<c:if test="${fn:length(secrecyMaintain.depIdea)>12}">...</c:if>
			</td>
			<td >
				${fn:substring(secrecyMaintain.leaderIdea, 0, 12)}
				<c:if test="${fn:length(secrecyMaintain.leaderIdea)>12}">...</c:if>
			</td>
			<td >
				${fn:substring(secrecyMaintain.description, 0, 12)}
				<c:if test="${fn:length(secrecyMaintain.description)>12}">...</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>