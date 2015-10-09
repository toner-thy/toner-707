<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	
		<title>保密设备配备历史统计</title>
		<!-- css -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/platform/theme/borderlayout/skin/blue/css/page.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/platform/template/borderlayout/skin/blue/css/ecTable.css">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-v1.1.css" />
		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/TabUtils.js"></script>	
       	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>
		<script src="${pageContext.request.contextPath }/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/mootools/mootools-core-1.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/mootools/mootools-more-1.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/js/notimoo/notimoo-v1.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>
		
		<s:actionmessage theme="messages"/>
		
		<script type="text/javascript">
			function backList(){
				window.location.href="<s:url action="externalPidgin_list"  includeParams="false"/>";
			  }
			  
			function doSave(){
				if (formcheck.isFormValid(true)) {
					$('sub').click();
				}
		</script>
		
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
				<div class="panel_header">
					<div class="panel_title panel_titleSearchIco">
						保密设备配备历史统计
					</div>
					<div class="panel_titleBtnBar"></div>
				</div>
				<!-- 搜索面板 -->
				<div class="panel_content"  class="body_content">
					<form action="equipment_showHistoryStat.action" method="post" id="equipmentForm">
						<table class="panel_content_search_form">
							<tr align="center">
								<td style="text-align:right;">
									请选择年份：
									<input type="text" name="reportDto.years" style="width: 100px;" value="${reportDto.years}"  class="Wdate" type="text" onFocus="WdatePicker({minDate:'2008}',maxDate:'2050',dateFmt:'yyyy'})" readonly="readonly">
								</td>
								<td style="text-align:right;">请选择季度：</td>
								<td style="text-align:left;">
									<s:select list="#{'12':'全年','6':'半年','4':'第一季度','7':'第二季度','10':'第三季度','1':'第四季度'}" theme="simple" listValue="value" listKey="key"  headerKey="all" cssStyle="margin-top:10px;" name="reportDto.quarter" value="reportDto.quarter"></s:select>
									<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
									<a class="pop_button" href="javascript:doSave();"><span>统计</span></a>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- 内容显示div -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						${reportDto.years}年单位涉密设备统计
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable">
						<table cellpadding="0" cellspacing="0" border="0" width="100%"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion" >
							<thead>
								<tr>
									<td class="tableHeader">单位</td>
									<td class="tableHeader">设备数量</td>
								</tr>
							</thead>
							<tbody class="tableBody">
								<c:if test="${fn:length(equipmentReportOrganList)>0}">
									<c:forEach items="${equipmentReportOrganList}" var="report" varStatus="x">
										<tr class="${x.count % 2 == 0 ? 'odd' : 'even' }"  onmouseover="this.className='highlight'"  onmouseout="this.className='${x.count % 2 == 0 ? 'odd' : 'even' }'">
											<td align="center">${report[0]}</td>
											<td align="center">${report[1]}</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${fn:length(equipmentReportOrganList)<=0}">
									<tr>
										<td align="center" colspan="5" class="${x.count % 2 == 0 ? 'odd' : 'even' }"  onmouseover="this.className='highlight'"  onmouseout="this.className='${x.count % 2 == 0 ? 'odd' : 'even' }'">暂未生成报表</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>