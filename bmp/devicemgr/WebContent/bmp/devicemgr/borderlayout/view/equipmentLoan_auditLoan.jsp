<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>

<html>
	<head>
		<title>保密设备使用申请列表</title>

		<!-- css -->
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/ec.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/pop_button/pop_button.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />

		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/pub.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js"></script>
		
		<script language="javascript">
			//新增
			function doAdd(action)
			{
				window.location.href = action;
			}
			
			//详情
			function doShow(action,id)
			{
			 	var items = EcTable.getCheckedItems();
				//window.location.href = action + '?id='+items[0].value;
				window.showModalDialog(action + '?id='+id
				 	,window
				 	,"dialogWidth=600px;dialogHeight=400px,status=no,directories=no,menubar=no,resizable=yes,scrollbars=auto");	
			}
			 
			//审批一条申请记录
			function doAudit(id)
			{
				window.location.href = 'equipmentLoan_addAuditLoan.action?equipmentLoan.equipmentLoanId='+id;
			}
			 
			function doUse(action,id)
			{
			 	var items = EcTable.getCheckedItems();
				window.location.href = action + '?id=' + id;
			}
			
			//编辑
			function doEdit(action)
			{
			 	var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}
			 	window.location.href = action+'?id='+items[0].value;
			}
			
			//编辑2
			function doEdit2(action,id)
			{
				document.location.href=action+'?id='+id;
			}
			
			//删除
			function doDel(action)
			{
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择删除的项!");
					return;
				}
				if(window.confirm("确定删除！！")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}
		</script>
	</head>
	
	<body>
		<ap:step/>
		<div class="body_content">
			<div class="edit_content">
				<s:actionmessage theme="messages"/>	
				<div class="edit_title">
					保密设备使用申请列表
				</div>
				<div class="edit_query_bar">
					<s:form action="equipmentLoan_auditLoan" id="secrecyEquipment_list_form" theme="simple">
						<label for="secrecyEquipment.name">保密设备名称：</label>
						<input type="text" name="equipmentLoan.secrecyEquipment.name" id="secrecyEquipment.name" value="${secrecyEquipment.name}" size="30"/>
						<span class="query_button_bar">
							<span class="pop_button_bar">
								<a href="javascript:document.getElementById('secrecyEquipment_list_form').submit();" class="pop_button"><span>查询</span></a>						
							</span>
						</span>
					</s:form>
				</div>
			</div>
			<br>
			<div class="edit_content">
				<div class="edit_title">
					保密设备使用申请管理列表
				</div>		
				<s:if test="#request.list.size>0">		
					<ec:table items="list" var="equipmentLoan" tableId="list" border="0"
						action="${pageContext.request.contextPath}/equipmentLoan_auditLoan.action"
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
							<ec:column property="null" title="状态" width="10%">
								<c:choose>
									<c:when test="${equipmentLoan.status==0}">
										待审批
									</c:when>
									<c:when test="${equipmentLoan.status==1}">
										本处室处长审批
									</c:when>
									<c:when test="${equipmentLoan.status==2}">
										本处室局长审批
									</c:when>
									<c:when test="${equipmentLoan.status==11}">
										审批通过
									</c:when>
									<c:when test="${equipmentLoan.status==7}">
										<font color="red">驳回</font>
									</c:when>
									<c:when test="${equipmentLoan.status==15}">
										本处局长审批
									</c:when>
									<c:otherwise>
										<font color="gray">审批结束</font>
									</c:otherwise>
								</c:choose>
							</ec:column>						
							<ec:column property="null" title="审批">
								<A href='###' class="cpx12orange" onclick="doAudit('${equipmentLoan.equipmentLoanId }');">
									<img src="${pageContext.request.contextPath}/platform/theme/default/images/icon/reportshop.gif" order="0" alt="审批">
								</A>
							</ec:column>
						</ec:row>
					</ec:table>
				</s:if>
				<s:else>
					<styles:nolist/>
				</s:else>
			</div>
		</div>
	</body>
</html>

