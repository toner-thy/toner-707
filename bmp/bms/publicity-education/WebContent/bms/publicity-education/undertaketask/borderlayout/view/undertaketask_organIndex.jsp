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
          <td colspan="5" align="center">承担课题情况</td>
        </tr>
        <tr>
          <td width="148" align="center">课题名称</td>
          <td width="172" align="center" >下达单位</td>
          <td width="106" align="center" >承办人</td>
          <td width="112" align="center" >完成时间</td>
          <td width="251" align="center" >具体成效</td>
        </tr>
       <c:forEach items="${undertaketaskList}" var="undertaketask" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${undertaketask.taskName }
			</td>
			<td >
				${undertaketask.releaseUnit.organName}
			</td>
			<td >
				<c:forEach items="${undertaketask.undertakerList }" var="uiTmp" varStatus="idx">
					<c:if test="${idx.index eq 0 }" >
						${uiTmp.name}
					</c:if>
					<c:if test="${idx.index ne 0 }" >
						,${uiTmp.name}
					</c:if>
				</c:forEach>
			</td>
			<td >
				${undertaketask.completeTime }
			</td>
			<td >
				${undertaketask.specificResults }
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>