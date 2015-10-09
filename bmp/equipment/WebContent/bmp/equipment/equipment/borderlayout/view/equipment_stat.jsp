<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
		
		<!-- css -->	
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/platform/theme/borderlayout/skin/blue/css/page.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/platform/template/borderlayout/skin/blue/css/ecTable.css">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-v1.1.css" />
		
		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/TabUtils.js"></script>	
        <script src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/mootools/mootools-core-1.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/mootools/mootools-more-1.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/js/notimoo/notimoo-v1.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>
		
		<s:actionmessage theme="messages"/>
		
	</head>
	
	<body>
	
		<div class="button_bar">
			<div class="left">
				<ap:operationbutton />
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.location.reload();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div class="body_content">
    		<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleSearchIco">
						设备统计查询
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_titleBtnBar">
					</div>
				</div>
				<!-- 内容 -->
				<div class="panel_content panel_content_table">
					<s:form action="equipment_stat" id="partForm" theme="simple">
						 <table class="panel_content_search_form">
					     	<tr class="odd">
								<td style="text-align:center;">
									单位名称：
									<input id="dname2" type="text" name="organName" value="${organName}" size="50px;">
									<a class="pop_button" href="javascript:document.getElementById('partForm').submit();"><span>查询</span></a>
								</td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
			<br/>
			<div class="panel" style="margin-top: -15px;">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleListIco">
					设备统计列表
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_btn_bar pop_button_bar">

					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="table_list">
						<div class="table_panel">
							<s:if test="#request.list.size>0">	
							<table id="list_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
								<thead>
									<tr class="table_header">
										<td class="tableHeader" >单位名称</td>
										<td class="tableHeader" >设备数量</td>
									</tr>
								</thead>
								<tbody class="tableBody">
									<c:set var="sum1" value="0"></c:set>
									<c:forEach items="${list}" var="e" varStatus="v">
										<c:if test="${v.count%2==1}">
											<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'">
												<td>${e[1]}</td>
												<td>
													<c:if test="${e[2] != null}">
														<a href="equipment_dataStat.action?equipment.organ.organId=${e[0]}"><font color="blue">${e[2]}</font></a>		
													</c:if>
													<c:if test="${e[2] == null}">0</c:if>
												</td>
												<c:set var="sum1" value="${sum1+e[2]}"></c:set>
											</tr>
										</c:if>	
										<c:if test="${v.count%2==0}">
											<tr class="even"  onmouseover="this.className='highlight'"  onmouseout="this.className='even'">
												<td>${e[1]}</td>
												<td>
													<c:if test="${e[2] != null}">
														<a href="equipment_dataStat.action?equipment.organ.organId=${e[0]}"><font color="blue">${e[2]}</font></a>		
													</c:if>
													<c:if test="${e[2] == null}">0</c:if>
												</td>
												<c:set var="sum1" value="${sum1+e[2]}"></c:set>
											</tr>
										</c:if>		
									</c:forEach>
									<tr class="even">
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
							</s:if>
							<s:else>
								<styles:nolist/>
							</s:else>
						</div>
					</div>
				</div>
			</div>
	</body>
</html>
