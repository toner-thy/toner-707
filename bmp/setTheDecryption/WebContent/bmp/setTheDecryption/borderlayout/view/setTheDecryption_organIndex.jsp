<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
          <td width="204" align="center" >日期</td>
          <td width="116" align="center">密级名称</td>
          <td width="159" align="center" >文号</td>
          <td width="159" align="center" >密级</td>
          <td width="73" align="center" >载体形式</td>
          <td width="73" align="center" >数量</td>
          <td width="73" align="center" >分发范围</td>
          <td width="73" align="center" >项目类型</td>

        </tr>
        <c:forEach items="${dataList}" var="setTheDecryption" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				<fmt:formatDate value="${setTheDecryption.date}" pattern="yyyy-MM-dd"  />
			</td>
			<td >
				${setTheDecryption.name}
			</td>
			<td>
			${setTheDecryption.docNumber}
			</td>
			<td>
				<dictionary:text fieldCode="secrecy_level_thing" tableCode="bmp" optionValue="${setTheDecryption.secrecyLevel}"></dictionary:text>
			</td>
			<td >
				${setTheDecryption.carrierType}
			</td>
			<td >
				${setTheDecryption.number}
			</td>
			<td >
				${setTheDecryption.scope}
			</td>
			<td >
				<c:if test="${setTheDecryption.secrecyType==0}">定密事项</c:if>
				<c:if test="${setTheDecryption.secrecyType==1}">解密事项</c:if>


			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>