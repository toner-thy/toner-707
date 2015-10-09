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
          <td colspan="9" align="center"> 涉密计算机</td>
        </tr>
        <tr>
          <td width="96" align="center">编号</td>
          <td width="62" align="center" >责任人</td>
          <td width="52" align="center" >密级</td>
          <td width="90" align="center" >机型</td>
          <td width="126" align="center" >硬盘序列号</td>
          <td width="92" align="center" >部门/部位名称</td>
          <td width="83" align="center" >涉密网络</td>
          <td width="73" align="center" >是否安装三合一</td>
          <td width="85" align="center" >是否纳入违规外联监控</td>
        </tr>
        <c:forEach items="${secrecyComputerList}" var="secrecyComputer" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${secrecyComputer.computerNo }
			</td>
			<td >
				${secrecyComputer.dutyPerson.name }
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyComputer.secrecyLevel}"></dictionary:text>
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="computer_type" optionValue="${secrecyComputer.computerType}"></dictionary:text>
			</td>
			<td >
				${secrecyComputer.diskSeq }
			</td>
			<td >
				${secrecyComputer.department.departmentName }
			</td>
			<td >
				<c:forEach items="${secrecyComputer.secrecyNetworkterminals}" var="network">
					${network.secrecyNetwork.name }
				</c:forEach>
			</td>
			<td >
				<c:if test="${secrecyComputer.isFanghu eq 0 }">否</c:if>
				<c:if test="${secrecyComputer.isFanghu eq 1 }">是</c:if>
			</td>
			<td >
				<c:if test="${secrecyComputer.isWailian eq 0 }">否</c:if>
				<c:if test="${secrecyComputer.isWailian eq 1 }">是</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>