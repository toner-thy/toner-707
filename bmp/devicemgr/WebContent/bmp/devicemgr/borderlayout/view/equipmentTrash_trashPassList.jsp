<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密设备管理列表</title>
		
		<!-- css -->
		<link href="${ctx}/platform/theme/default/formcheck/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- js -->
		<script src="${ctx}/platform/theme/public_js/pub.js"type="text/javascript" ></script>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/mootools-core.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/mootools-more.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/formcheck/formcheck.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/utils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/TabUtils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
		
		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/bmp/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />
		
		<s:actionmessage theme="messages" />
		<script type="text/javascript">
			function submitSearch(){
				document.getElementById('equipmentTrash_trashPassList').submit();
			}
		</script>
	</head>
	
	<body>
		<!-- 公共头部 -->
    	<div class="but_bar">
			<div class="left">
				<ap:operationbutton />
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		
		<div id="body_content" class="body_content">
			<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleSearchIco">
						使用过期列表查询
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_titleBtnBar">
					</div>
				</div>
				<!-- 内容 -->
				<div class="panel_content">
					<s:form action="equipmentTrash_trashPassList" id="equipmentTrash_trashPassList" theme="simple">
						<table class="panel_content_search_form">
							<tr>
								<td align="right">
									保密设备名称：
								</td>
								<td align="left">
									<input type="text" name="equipmentLoan.secrecyEquipment.name" id="equipmentLoan.secrecyEquipment.name" value="${equipmentLoan.secrecyEquipment.name}"/>
								</td>
								<td width="15%">
									<div class="btn_query_bar pop_button_bar">
										<a href="###" onclick="submitSearch()" class="pop_button"><span>查询</span></a>
									</div>
								<td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						使用过期列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				
				<div class="panel_content">
					<div class="eXtremeTable" >
						<s:if test="#request.list.size>0">				
							<ec:table items="list" var="equipmentLoan" tableId="list" border="0"
								action="${pageContext.request.contextPath}/equipmentLoan_loanList.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="true">
								<ec:row>
									<ec:column property="equipmentLoanId" alias="secrecyEquipmentId_checkbox" cell="checkbox" headerCell="checkbox" sortable="false"/>
									<ec:column property="secrecyEquipment.name" title="保密技术设备"/>
									<ec:column property="loanPerson.name" title="申请人"/>
									<ec:column property="loanReason" title="使用是由"/>
									<ec:column property="loanStartTime" title="起始日期" format="yyyy-MM-dd" parse="yyyy-MM-dd" cell="date"/>
									<ec:column property="loanEndTime" title="截止时间" format="yyyy-MM-dd" parse="yyyy-MM-dd" cell="date"/>
									<ec:column property="remark" title="备注"/>
								</ec:row>
							</ec:table>
						</s:if>
						<s:else>
						   <!-- 没有数据时显示页面 -->
	                         <styles:nolist />
						</s:else>
					</div>
				</div>
				<!-- 内容panel结束 -->
			</div>
		</div>
	</body>
</html>