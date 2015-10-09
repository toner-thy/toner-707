<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
    <td colspan="9" align="center"><span style="font-size: 22px;font-weight: bolder;line-height: 42px;">保密委员会（保密工作领导小组）成员简况及分工</span></td>
  </tr>
  <tr>
    <td  colspan="3">保密办负责人：${secrecyWorkOrgan.organPrincipal.name}</td>
    <td  colspan="2">保密办设在：${secrecyWorkOrgan.department.departmentName}</td>
    <td  colspan="2">电 话：${secrecyWorkOrgan.principalPhone}</td>
    <td  colspan="2">成立(发文)日期：${secrecyWorkOrgan.setupDate}</td>
  </tr>
  <tr>
    <td  align="center">成员姓名</td>
    <td  align="center">性别</td>
    <td  align="center">年龄</td>
    <td  align="center">学历</td>
    <td  align="center">政治面貌</td>
    <td  align="center">部 门</td>
    <td  align="center">机构职务</td>
    <td  align="center">行政级别</td>
    <td align="center">联系电话</td>
  </tr>
  <c:forEach items="${secrecyWorkOrganRelationMemberList}" var="swrm" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${swrm.person.name}
			</td>
			<td >
				<dictionary:text tableCode="person" fieldCode="sex" optionValue="${swrm.person.sex }"></dictionary:text>
			</td>
			<td >
				${swrm.ageStr}
			</td>
			<td >
				<dictionary:text tableCode="person" fieldCode="learning_level" optionValue="${swrm.person.learningLevel }"></dictionary:text>
			</td>
			<td >
				<dictionary:text tableCode="person" fieldCode="polity" optionValue="${swrm.person.polity}" ></dictionary:text>
			</td>
			<td  >
				${swrm.person.department.departmentName}
			</td>
			<td >
				${swrm.personGroupPosition.positionName}
			</td>
			<td >
				${swrm.person.duty}
			</td>
			<td >
				${swrm.person.mobile }
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
