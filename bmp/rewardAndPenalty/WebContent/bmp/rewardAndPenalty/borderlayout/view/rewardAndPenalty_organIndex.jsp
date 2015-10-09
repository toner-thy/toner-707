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
          <td colspan="5" align="center">保密工作实施奖惩情况</td>
        </tr>
        <tr>
          <td width="204" align="center" >序号</td>
          <td width="116" align="center">事项（人员）名称</td>
          <td width="159" align="center" >时间</td>
          <td width="159" align="center" >奖惩情况</td>
          <td width="73" align="center" >惩处情况</td>
        </tr>
        <c:forEach items="${rewardAndPenaltylist}" var="rewardAndPenalty" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${xm.index+1 }
			</td>
			<td >
				${fn:substring(rewardAndPenalty.name, 0, 12)}
				<c:if test="${fn:length(rewardAndPenalty.name)>12}">...</c:if>
			</td>
			<td >
				<fmt:formatDate value="${rewardAndPenalty.date }" pattern="yyyy-MM-dd"/>
			</td>
			<td >
				${fn:substring(rewardAndPenalty.rewardCircs, 0, 12)}
				<c:if test="${fn:length(rewardAndPenalty.rewardCircs)>12}">...</c:if>
			</td>
			<td>
				${fn:substring(rewardAndPenalty.penaltyCircs, 0, 12)}
				<c:if test="${fn:length(rewardAndPenalty.penaltyCircs)>12}">...</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>