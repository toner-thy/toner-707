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
          <td colspan="8" align="center">涉密会议活动情况</td>
        </tr>
        <tr>
          <td width="37" align="center">序号</td>
          <td width="99" align="center" >涉密会议（活动）名称</td>
          <td width="36" align="center" >涉密等级</td>
          <td width="134" align="center" >地点</td>
          <td width="51" align="center" >时间</td>
          <td width="123" align="center" >知悉范围</td>
          <td width="163" align="center" >保密办参与情况</td>
          <td width="131" align="center" >主要管理措施</td>
        </tr>
      <c:forEach items="${meetingList3}" var="m" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${xm.index + 1 }
			</td>
			<td >
				${m.meetingName}
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${m.secrecyLevel}"></dictionary:text>
			</td>
			<td >
				${m.place}
			</td>
			<td >
				<fmt:formatDate value='${m.meetingTime}' pattern='yyyy-MM-dd HH:mm'/>
			</td>
			<td >
				${m.scope}
			</td>
			<td >
				${m.situation}
			</td>
			<td >
				${m.measure}
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>