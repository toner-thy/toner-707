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
          <td colspan="6" align="center"> 涉密移动存储介质</td>
        </tr>
        <tr>
          <td width="96" align="center">编号</td>
          <td width="62" align="center" >介质类型</td>
          <td width="52" align="center" >密级</td>
          <td width="90" align="center" >责任人</td>
          <td width="126" align="center" >序列号</td>
          <td width="92" align="center" >部门/部位名称</td>
        </tr>
        <c:forEach items="${secrecyMobilestoragemediaList}" var="secrecyMobilestoragemedia" varStatus="xm">
        <tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${secrecyMobilestoragemedia.mediaNo }
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="media_type" optionValue="${secrecyMobilestoragemedia.mediaType }"></dictionary:text>
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyMobilestoragemedia.secrecyLevel }" ></dictionary:text>
			</td>
			<td >
				${secrecyMobilestoragemedia.dutyPerson.name }
			</td>
			<td >
				${secrecyMobilestoragemedia.mediaSeq }
			</td>
			<td >
				${secrecyMobilestoragemedia.department.departmentName }
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>