<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="department" %>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary" %>
<%@ taglib uri="http://www.cdthgk.com/tags/theme" prefix="theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="panel">
	<div class="panel_header">
		<div class="panel_title panel_titleListIco">
		涉密计算机及“三合一”安装情况统计表
		</div>
	</div>
	<div class="panel_content panel_content_table">
		<table border="0" style="width: 100%;">
			<tr>
				<td align="left" style="padding-left: 20px;">单位：（盖章）&nbsp;&nbsp; ${sc.reportOrgan.name}</td>
				<td align="center">填表人：&nbsp;&nbsp; ${sc.reportUser.userInfo.name}</td>
				<td align="right" style="padding-right: 20px;">填报日期：&nbsp;&nbsp; <fmt:formatDate value="${sc.reportDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</table>
		<table class="content_table">
			<thead>
				<tr>
					<td width="25%" align="center">部门</td>
					<td width="15%" align="center">密级</td>
					<td width="20%" align="center">使用管理责任人</td>
					<td width="30%" align="center">硬盘序列号</td>
					<td width="10%" align="center">是否安装“三合一”</td>
				</tr>
			</thead>
			<tbody id="table_content_body">
			<c:forEach items="${list}" var="i">
				<tr>
					<td align="center">${i.departmentName}</td>
					<td align="center"><dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${i.secrecyLevel}"/></td>
					<td align="center">${i.useDutyPerson}</td>
					<td align="center">${i.diskSn}</td>
					<td align="center">${i.install3i1? "是" : "否"}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>