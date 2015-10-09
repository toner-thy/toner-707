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
          <td colspan="5" align="center">其他涉密设备</td>
        </tr>
        <tr>
          <td width="96" align="center">编号</td>
          <td width="62" align="center" >类型</td>
          <td width="52" align="center" >密级</td>
          <td width="90" align="center" >责任人</td>
          <td width="92" align="center" >部门/部位名称</td>
        </tr>
        <c:forEach items="${secrecyOthersList}" var="secrecyOthers" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${secrecyOthers.secrecyothersNo }
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="other_media_type" optionValue="${secrecyOthers.secrecyothersType }"></dictionary:text>
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyOthers.secrecyLevel }" ></dictionary:text>
			</td>
			<td >
				${secrecyOthers.dutyPerson.name }
			</td>
			<td >
				${secrecyOthers.department.departmentName }
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>