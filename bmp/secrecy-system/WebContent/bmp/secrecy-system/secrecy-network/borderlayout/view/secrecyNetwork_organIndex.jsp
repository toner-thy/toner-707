<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
          <td colspan="7" align="center"> 涉密网络</td>
        </tr>
        <tr>
          <td width="96" align="center">名称</td>
          <td width="62" align="center" >部门</td>
          <td width="52" align="center" >密级</td>
          <td width="90" align="center" >网络类型</td>
          <td width="126" align="center" >是否通过测评</td>
          <td width="92" align="center" >是否通过审批</td>
          <td width="83" align="center" >投入使用时间</td>
        </tr>
        <c:forEach items="${secrecyNetworkList}" var="secrecyNetwork" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${secrecyNetwork.name }
			</td>
			<td >
				${secrecyNetwork.department.departmentName }
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyNetwork.secrecyLevel}"></dictionary:text>
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="network_type" optionValue="${secrecyNetwork.networkType}"></dictionary:text>
			</td>
			<td >
				<c:if test="${secrecyNetwork.isReview eq 1 }">是</c:if>
				<c:if test="${secrecyNetwork.isReview eq 0 }">否</c:if>
			</td>
			<td >
				<c:if test="${secrecyNetwork.isApproval eq 1 }">是</c:if>
				<c:if test="${secrecyNetwork.isApproval eq 0 }">否</c:if>
			</td>
			<td >
				<fmt:formatDate value='${secrecyNetwork.startUseDate}' pattern='yyyy-MM-dd'/>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>