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

<c:forEach items="${meetingList1}" var="m">
	<table class="contentTable" width="900" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td align="center"><h1><strong>保密委员会（保密工作领导小组）会议记录</strong></h1></td>
	  </tr>
	  <tr>
	    <td align="center"><strong>${m.createTime }</strong></td>
	  </tr>
	  <tr>
	    <td align="left">议题：${m.meetingName}</td>
	  </tr>
	  <tr>
	    <td align="left">主持人：${m.presenter.name}</td>
	  </tr>
	  <tr>
	    <td align="left">出席人员：${m.attendUserInfoNames}</td>
	  </tr>
	  <tr>
	    <td align="left">记录人：${m.recorder.name}</td>
	  </tr>
	</table>
</c:forEach>
</body>
</html>
