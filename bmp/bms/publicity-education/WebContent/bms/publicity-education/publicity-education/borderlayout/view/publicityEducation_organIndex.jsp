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
          <td colspan="5" align="center">开展保密宣传教育、培训情况</td>
        </tr>
        <tr>
          <td width="116" align="center">时间</td>
          <td width="204" align="center" >形式</td>
          <td width="159" align="center" >范围</td>
          <td width="73" align="center" >参加人数</td>
          <td width="237" align="center" >主要内容</td>
        </tr>
        <c:forEach items="${publicityeducationList}" var="publicityeducation" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${publicityeducation.trainTime }
			</td>
			<td >
				${publicityeducation.form }
			</td>
			<td >
				${publicityeducation.trainRange }
			</td>
			<td >
				${publicityeducation.participantsNum }
			</td>
			<td >
				${publicityeducation.content }
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>