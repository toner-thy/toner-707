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
	.ko {
		padding: 12px 12px 0;
	}
	#dvContent {
		font-family: arial, verdana, sans-serif;
		font-size: 14px;
		line-height: 1.666;
		margin: 0;
		min-height: 100px;
		overflow: auto;
		padding: 0;
		white-space: normal;
		word-wrap: break-word;
		color: black;
		background-color: white;
	}
	#dvContent ul,ol{
		margin-left: 40px;
	}
	#dvContent ol li{
		list-style-type:decimal;
		display: list-item;
	}
	#dvContent ul li{
		list-style-type: disc;
		display: list-item;
	}
</style>

<body>

 <c:forEach items="${dataList}" var="data">
	<table class="contentTable" width="900" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td align="center"><h1><strong>保密委员会（保密工作领导小组）年度工作计划</strong></h1></td>
	  </tr>
	  <tr>
	    <td align="center"><strong>（ ${data.annualPlanYear }）年</strong></td>
	  </tr>
	  <tr>
	    <td>
	    	<div class="ko">
				<div id="dvContent" class="nui-fClear pm">${data.annualPlanContent }</div>
			</div>
	    </td>
	  </tr>
	</table>
</c:forEach>


</body>
</html>
