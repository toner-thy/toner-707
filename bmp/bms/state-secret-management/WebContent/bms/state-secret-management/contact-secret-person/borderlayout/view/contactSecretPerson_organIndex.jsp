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
          <td colspan="10" align="center">接触和知悉绝密级国家秘密文件人员情况</td>
        </tr>
        <tr>
          <td width="204" align="center" >文件名称（文号）</td>
          <td width="116" align="center">发文机关</td>
          <td width="159" align="center" >发文日期</td>
          <td width="159" align="center" >收文日期</td>
          <td width="73" align="center" >数量</td>
          <td width="73" align="center" >接触或知悉人员姓名</td>
          <td width="73" align="center" >时间</td>
          <td width="73" align="center" >接触或知悉方式</td>
          <td width="73" align="center" >承办人</td>
          <td width="73" align="center" >审批领导</td>
        </tr>
        <c:forEach items="${contactSecretPersonList}" var="contactSecretPerson" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${fn:substring(contactSecretPerson.secretFileName, 0, 12)}
				<c:if test="${fn:length(contactSecretPerson.secretFileName)>12}">...</c:if>
			</td>
			<td >
				${contactSecretPerson.dispatchOrgan}
			</td>
			<td >
				<fmt:formatDate value="${contactSecretPerson.issuedDate }" pattern="yyyy-MM-dd"/>
			</td>
			<td >
				<fmt:formatDate value="${contactSecretPerson.receiveDate }" pattern="yyyy-MM-dd"/>
			</td>
			<td >
				${contactSecretPerson.fileNum }
			</td>
			<td>
				${contactSecretPerson.contactPersonName }
			</td>
			<td >
				<fmt:formatDate value="${contactSecretPerson.contactDate }" pattern="yyyy-MM-dd"/>
			</td>
			<td >
				${contactSecretPerson.contactWayTxt }
			</td>
			<td >
				${contactSecretPerson.undertackerNames }
			</td>
			<td >
				${contactSecretPerson.approvedLeaderNames }
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>