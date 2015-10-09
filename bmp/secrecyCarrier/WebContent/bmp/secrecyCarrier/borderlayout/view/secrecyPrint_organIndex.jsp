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
          <td colspan="11" align="center">涉密文件（资料）打印登记情况</td>
        </tr>
        <tr>
          <td width="204" align="center" >序号</td>
          <td width="116" align="center">打印时间</td>
          <td width="159" align="center" >文件（资料）名称</td>
          <td width="159" align="center" >文号</td>
          <td width="73" align="center" >密级</td>
          <td width="73" align="center" >份数</td>
          <td width="73" align="center" >每份页数</td>
          <td width="73" align="center" >起草部门</td>
          <td width="73" align="center" >批准人</td>
          <td width="73" align="center" >承办人</td>
          <td width="73" align="center" >备注</td>
        </tr>
        <c:forEach items="${secrecyPrintList}" var="secrecyPrint" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td>
				${xm.index+1 }
			</td>
			<td >
				<fmt:formatDate value="${secrecyPrint.date }" pattern="yyyy-MM-dd"/>
			</td>
			<td >
				${fn:substring(secrecyPrint.name, 0, 12)}
				<c:if test="${fn:length(secrecyPrint.name)>12}">...</c:if>
			</td>
			<td >
				${secrecyPrint.docNumber }
			</td>
			<td >
				${secrecyPrint.secrecyLevelTxt }
			</td>
			<td >
				${secrecyPrint.number }
			</td>
			<td>
				${secrecyPrint.pageNo }
			</td>
			<td >
				${secrecyPrint.draftingDep.departmentName }
			</td>
			<td >
				${secrecyPrint.approverNames }
			</td>
			<td >
				${secrecyPrint.uncertakerNames }
			</td>
			<td >
				${fn:substring(secrecyPrint.description, 0, 12)}
				<c:if test="${fn:length(secrecyPrint.description)>12}">...</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>