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
          <td colspan="11" align="center">涉密人员</td>
        </tr>
        <tr>
          <td align="center">姓名</td>
          <td align="center" >性别</td>
          <td align="center" >年龄</td>
          <td align="center" >学历</td>
          <td align="center" >民族</td>
          <td align="center" >政治面貌 </td>
          <td align="center" >部门</td>
          <td align="center" >行政级别</td>
          <td align="center" >联系电话</td>
          <td align="center" >涉密等级</td>
          <td align="center" >是否为定密责任人</td>
        </tr>
        <c:forEach items="${secrecyPersonList}" var="secrecyPerson" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${secrecyPerson.userInfo.name}
			</td>
			<td >
				<dictionary:text tableCode="person" fieldCode="sex" optionValue="${secrecyPerson.userInfo.sex}"/>
			</td>
			<td >
				${secrecyPerson.personAge}
			</td>
			<td >
				<dictionary:text tableCode="person" fieldCode="learning_level" optionValue="${secrecyPerson.userInfo.learningLevel}"/>
			</td>
			<td >
				<dictionary:text tableCode="person" fieldCode="nation" optionValue="${secrecyPerson.userInfo.nation}"/>
			</td>
			<td >
				<dictionary:text tableCode="person" fieldCode="polity" optionValue="${secrecyPerson.userInfo.polity}"/>
			</td>
			<td >
				${secrecyPerson.department.departmentName}
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="person_admin_level" optionValue="${secrecyPerson.userInfo.administrativeLevel }"></dictionary:text>
			</td>
			<td >
				${secrecyPerson.userInfo.mobile}
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${secrecyPerson.secrecyPersonLevel}"/>
			</td>
			<td >
				<c:if test="${secrecyPerson.responsiblePerson == 0 }">
					否
				</c:if>
				<c:if test="${secrecyPerson.responsiblePerson == 1 }">
					是
				</c:if>
			</td>

		</tr>
	</c:forEach>
</table>
</body>
</html>
