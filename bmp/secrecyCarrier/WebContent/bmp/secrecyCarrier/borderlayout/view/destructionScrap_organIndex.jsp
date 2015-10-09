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
          <td colspan="8" align="center">涉密载体销毁、涉密设备报废情况</td>
        </tr>
        <tr>
          <td width="204" align="center" >序号</td>
          <td width="116" align="center">涉密载体形式</br>（含报废涉密设备）</td>
          <td width="159" align="center" >涉密载体内容</td>
          <td width="159" align="center" >数量</td>
          <td width="73" align="center" >密级</td>
          <td width="73" align="center" >销毁、报废流向</td>
          <td width="73" align="center" >经办人</td>
          <td width="73" align="center" >审批人</td>
        </tr>
        <c:forEach items="${destructionScrapList}" var="destructionScrap" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td>
				${xm.index }
			</td>
			<td >
				${destructionScrap.typeTxt }
			</td>
			<td >
				${destructionScrap.content }
			</td>
			<td >
				${destructionScrap.number }
			</td>
			<td>
				${destructionScrap.secrecyLevelTxt }
			</td>
			<td >
				${destructionScrap.toPlace }
			</td>
			<td >
				${destructionScrap.responsibleUser.name }
			</td>
			<td >
				${destructionScrap.approver.name }
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>