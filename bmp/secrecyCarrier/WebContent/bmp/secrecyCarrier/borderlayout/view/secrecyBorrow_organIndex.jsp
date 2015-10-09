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
          <td colspan="9" align="center">涉密载体借阅情况</td>
        </tr>
        <tr>
          <td width="204" align="center" >序号</td>
          <td width="204" align="center" >借阅时间</td>
          <td width="116" align="center">文件名称</td>
          <td width="159" align="center" >密级</td>
          <td width="159" align="center" >文号</td>
          <td width="73" align="center" >份数</td>
          <td width="73" align="center" >借阅人</td>
          <td width="73" align="center" >审批人</td>
          <td width="73" align="center" >归还时间</td>
        </tr>
        <c:forEach items="${secrecyBorrowList}" var="secrecyBorrow" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${xm.index+1 }
			</td>
			<td >
				<fmt:formatDate value="${secrecyBorrow.date }" pattern="yyyy-MM-dd"/>
			</td>
			<td >
				${fn:substring(secrecyBorrow.name, 0, 12)}
				<c:if test="${fn:length(secrecyBorrow.name)>12}">...</c:if>
			</td>
			<td >
				${secrecyBorrow.secrecyLevelTxt }
			</td>
			<td>
				${secrecyBorrow.docNumber }
			</td>
			<td>
				${secrecyBorrow.number }
			</td>
			<td >
				${secrecyBorrow.borrowUserInfo.name }
			</td>
			<td >
				${secrecyBorrow.approver.name }
			</td>
			<td >
				<fmt:formatDate value="${secrecyBorrow.returnDate }" pattern="yyyy-MM-dd"/>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>