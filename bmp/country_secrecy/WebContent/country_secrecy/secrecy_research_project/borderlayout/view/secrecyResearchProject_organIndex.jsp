<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
          <td colspan="8" align="center">涉密科研项目</td>
        </tr>
        <tr>
          <td width="50" align="center">名称</td>
          <td width="86" align="center" >定密负责人</td>
          <td width="36" align="center" >密级</td>
          <td width="101" align="center" >部门</td>
          <td width="129" align="center" >保密期限起止日期</td>
          <td width="78" align="center" >保密期限</td>
          <td width="78" align="center" >项目负责人</td>
          <td width="78" align="center" >项目状态</td>
        </tr>
       <c:forEach items="${secrecyResearchProjectList}" var="secrecyResearchProject" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td width="200px;">
				${secrecyResearchProject.secrecyResearchProjectName}
			</td>
			<td >
				${secrecyResearchProject.formulateSecrecyPerson.name}
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyResearchProject.secrecyLevel}"></dictionary:text>
			</td>
			<td >
				${secrecyResearchProject.departId.departmentName}
			</td>
			<td width="200px;">
				<fmt:formatDate value='${secrecyResearchProject.secrecyLimitBeginDate}' pattern='yyyy-MM-dd'/> 至 <fmt:formatDate value='${secrecyResearchProject.secrecyLimitEndDate}' pattern='yyyy-MM-dd'/>
			</td>
			<td >
				${secrecyResearchProject.secrecyLimit}
				<dictionary:text tableCode="bmp" fieldCode="limit_type" optionValue="${secrecyResearchProject.limitType}"></dictionary:text>
			</td>
			<td >
				${secrecyResearchProject.projectPerson.name}
			</td>
			<td >
				<dictionary:text tableCode="bmp" fieldCode="project_state" optionValue="${secrecyResearchProject.projectState}"></dictionary:text>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>