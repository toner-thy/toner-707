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
          <td colspan="9" align="center">信息发布保密审查情况</td>
        </tr>
        <tr>
          <td width="39" align="center">序号</td>
          <td width="129" align="center">信息内容（标题）</td>
          <td width="97" align="center" >信息来源</td>
          <td width="133" align="center" >承办（提供）部门</td>
          <td width="152" align="center" >发布途径</td>
          <td width="130" align="center" >部门初审意见</td>
          <td width="130" align="center" >专业审核人员意见</td>
          <td width="130" align="center" >单位领导签批意见</td>
          <td width="121" align="center" >备注</td>
        </tr>
        <c:forEach items="${informationReviewList}" var="informationReview" varStatus="xm">
		<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
			<td >
				${xm.index + 1 }
			</td>
			<td >
				${informationReview.title }
			</td>
			<td >
				<dictionary:text fieldCode="infoSource" tableCode="bmp" optionValue="${informationReview.informationSources}" />
			</td>
			<td >
				${informationReview.undertakingDepartment.departmentName }
			</td>
			<td >
				<dictionary:text fieldCode="releaseWay" tableCode="bmp" optionValue="${informationReview.releaseWay}" />
			</td>
			<td >
				${informationReview.initialOpinions }
			</td>
			<td >
				${informationReview.professionalOpinion }
			</td>
			<td >
				${informationReview.unitLeadersOpinions }
			</td>
			<td >
				暂无
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>