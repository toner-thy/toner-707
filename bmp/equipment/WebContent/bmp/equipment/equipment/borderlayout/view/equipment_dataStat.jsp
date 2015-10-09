<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title></title>
		
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/platform/theme/borderlayout/skin/blue/css/page.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/platform/template/borderlayout/skin/blue/css/ecTable.css">
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-v1.1.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/TabUtils.js"></script>	
        <script src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/mootools/mootools-core-1.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/mootools/mootools-more-1.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/js/notimoo/notimoo-v1.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>
		
		<script type="text/javascript">
			function doBack(action){
				window.location.href=action;
			}
		</script>
		
	</head>
	
	<body>
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a href="###" onclick="doBack('${pageContext.request.contextPath}/equipment_stat.action');this.blur();" class="pop_button"><span>返回</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.location.reload();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div  class="body_content">
			<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleSearchIco">
						${dataList[0][0]}设备详情
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_titleBtnBar">
					</div>
				</div>
				<!-- 内容 -->
				<div class="panel_content panel_content_table">
					<s:if test="#request.dataList.size>0">	
						<div class="eXtremeTable" >
						<table id="list_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<thead>
								<tr>
									<td class="tableHeader" >设备名称</td>
									<td class="tableHeader" >设备数量</td>
								</tr>
							</thead>
							<tbody class="tableBody">
								<c:set var="sum1" value="0"></c:set>
								<c:forEach items="${dataList}" var="e" varStatus="v">
									<tr class="${v.count % 2 == 0 ? 'even':'odd' }">
										<td>${e[1]}</td>
										<td>
											<c:if test="${e[2] != null}">
												${e[2]}
											</c:if>
											<c:if test="${e[2] == null}">0</c:if>
										</td>
										<c:set var="sum1" value="${sum1+e[2]}"></c:set>
									</tr>
								</c:forEach>
								<tr>
									<td>合计</td>
									<td>
									<c:if test="${sum1 != 0}">
										${sum1 }	
									</c:if>
									<c:if test="${sum1 == 0 }">
										0
									</c:if>
									</td>
								</tr>
							</tbody>
						</table>
						</div>
					</s:if>
					<s:else>
						<styles:nolist/>
					</s:else>
				</div>
			</div>
		</div>
	</body>
</html>
