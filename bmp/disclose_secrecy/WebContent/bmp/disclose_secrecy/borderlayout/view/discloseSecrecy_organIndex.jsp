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
          <td colspan="8" align="center">失泄密事件查处情况情况</td>
        </tr>
        <tr>
          <td width="204" align="center" >名称</td>
          <td width="116" align="center">部门名称</td>
          <td width="159" align="center" >密级</td>
          <td width="159" align="center" >发案形式</td>
          <td width="73" align="center" >责任单位性质</td>
          <td width="73" align="center" >案件性质</td>
          <td width="73" align="center" >查处结果</td>
        </tr>
        <c:forEach items="${dataList}" var="discloseSecrecy" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${discloseSecrecy.name}
			</td>
			<td >
				${discloseSecrecy.department.departmentName}
			</td>
			<td>
				<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing"
									optionValue="${discloseSecrecy.secrecyLevel}"></dictionary:text>
			</td>
			<td>
				<dictionary:text tableCode="bmp" fieldCode="case_Type"
									optionValue="${discloseSecrecy.caseType}"></dictionary:text>
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="duty_organ_kind"
									optionValue="${discloseSecrecy.dutyOrganKind}"></dictionary:text>
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="case_kind"
									optionValue="${discloseSecrecy.casekind}"></dictionary:text>
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="find_result"
									optionValue="${discloseSecrecy.dealResult}"></dictionary:text>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>