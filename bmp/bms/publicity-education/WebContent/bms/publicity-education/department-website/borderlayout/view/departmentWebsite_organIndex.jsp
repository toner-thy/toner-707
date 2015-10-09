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
          <td colspan="4" align="center">部门网站管理</td>
        </tr>
        <tr>
          <td width="232" align="center">网站名称</td>
          <td width="140" align="center">管理员姓名</td>
          <td width="246" align="center">管理员职务</td>
        </tr>
        <c:forEach items="${departmentwebsiteList}" var="departmentWebsite" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${departmentWebsite.websiteName }
			</td>
			<td >
				${departmentWebsite.adminName }
			</td>
			<td >
				${departmentWebsite.adminPost }
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>