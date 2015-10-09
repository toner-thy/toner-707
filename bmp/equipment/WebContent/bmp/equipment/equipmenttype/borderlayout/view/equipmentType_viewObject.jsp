<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>传输详情</title>
		
	    <!-- 头部信息 -->
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
	    <!-- css -->
	    <link href="${pageContext.request.contextPath}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/ec.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/formcheck/formcheck.css" type="text/css" rel="stylesheet" />
		
	    <!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-more.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/miftree/mif.tree-v1.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/AccordionMenu/AccordionMenu.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/formcheck/formcheck.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/fw-moo12-adapter.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>
	</head>
	
	<body>
		<div class="body_content">
			<div class="edit_content" style="margin-top: 10px;">
				<div class="edit_title">
					 设备传输日志列表
				</div>
				<s:if test="#request.transmitLogMainList.size>0">
					<div class="eXtremeTable" >
						<table id="list_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tbody class="tableBody">
								<c:forEach items="${transmitLogMainList}" var="logMain">
									<tr class="tableHeader">
										<td>执行时间</td>
										<td colspan="2"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${logMain.sendTime}"/></td>
										<td>传输数据地址</td>
										<td colspan="2">${logMain.transmitData }</td>
									</tr>
									<tr>
										<td >接收单位</td>
										<td >发送时间</td>
										<td >服务器地址</td>
										<td >备注</td>
										<td >传输状态</td>
									</tr>
									<c:forEach items="${logMain.transmitLogs}" var="t">
										<tr>
											<td>${t.organ.organName}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${t.createTime}"/></td>
											<td>${t.transmitServer }</td>
											<td>
												<div title="${t.comment}">
													<c:if test="${fn:length(t.comment) >20}">${fn:substring(t.comment,0,20)}...</c:if>
													<c:if test="${fn:length(t.comment) <=20}">${t.comment}</c:if>
												</div>
											</td>
											<td>
												<c:if test="${t.state eq 1}"><span style="color:green">成功</span></c:if>
												<c:if test="${t.state eq 0}"><span style="color:red">失败</span></c:if>
											</td>
											
										</tr>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</s:if>
				<s:else>
					<styles:nolist/>
				</s:else>
			</div>
		</div>
	</body>
</html>