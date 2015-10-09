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
          <td colspan="4" align="center">涉密设备维修登记情况</td>
        </tr>
        <tr>
          <td width="204" align="center" >时间</td>
          <td width="116" align="center">装备部门、部位</td>
          <td width="159" align="center" >设备名称及用途</td>
          <td width="159" align="center" >数量</td>
        </tr>
        <c:forEach items="${secrecyTechnologyPreventionList}" var="secrecyTechnologyPrevention" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td>
				<fmt:formatDate value="${secrecyTechnologyPrevention.date }" pattern="yyyy-MM-dd"/>
			</td>
			<td>
				${secrecyTechnologyPrevention.equipDep.departmentName }
				${secrecyTechnologyPrevention.keyPart.partName }
			</td>
			<td>
				${secrecyTechnologyPrevention.name }，
				${fn:substring(secrecyTechnologyPrevention.purpose, 0, 12)}
				<c:if test="${fn:length(secrecyTechnologyPrevention.purpose)>12}">...</c:if>
			</td>
			<td>
				${secrecyTechnologyPrevention.number }
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>