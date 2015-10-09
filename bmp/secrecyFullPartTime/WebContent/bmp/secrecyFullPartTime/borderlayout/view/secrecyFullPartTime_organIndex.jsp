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
          <td colspan="8" align="center">保密工作专（兼）职人员情况</td>
        </tr>
        <tr>
          <td width="204" align="center" >姓名</td>
          <td width="116" align="center">职务</td>
          <td width="159" align="center" >文化程度</td>
          <td width="159" align="center" >是否接受保密培训</td>
          <td width="73" align="center" >从事保密工作年限</td>
          <td width="73" align="center" >专（兼）职</td>
        </tr>
        <c:forEach items="${dataList}" var="secrecyFullPartTime" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${secrecyFullPartTime.name.name }
			</td>
			<td >
				${secrecyFullPartTime.position}
			</td>
			<td>
				<dictionary:text fieldCode="learning_level" tableCode="person" optionValue="${secrecyFullPartTime.degree}"></dictionary:text>
			</td>
			<td>
				<c:if test="${secrecyFullPartTime.isTrain==0}">
				是
				</c:if>
				<c:if test="${secrecyFullPartTime.isTrain==1}">
				否
				</c:if>
			</td>
			<td >
				${secrecyFullPartTime.workYear}
			</td>
			<td >
				<c:if test="${secrecyFullPartTime.isFull==0}">
				专职
				</c:if>
				<c:if test="${secrecyFullPartTime.isFull==1}">
				兼职
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>