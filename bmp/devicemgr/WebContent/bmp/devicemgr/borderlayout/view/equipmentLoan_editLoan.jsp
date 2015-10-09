<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>编辑保密设备</title>
		
		<!-- css -->
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/formcheck/formcheck.css" type="text/css" rel="stylesheet" />
		
		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/formcheck/formcheck.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>
		
		<script type="text/javascript">
			window.addEvent('domready', function(){
				new FormCheck('form1',{
					display:{
						showErrors:1
					}
				});
			});
			
			//返回
			function backList(){
				window.location.href="<s:url action="deviceMgrAction_list" includeParams="false"/>";
			}
		</script>		
	</head>
	
	<body>
		<div class="body_content">
			<div class="edit_content" >
				<h2>
					申请报废设备
				</h2>
				<form id="form1" class="form" action="<s:url action="equipmentTrash_updateTrash" includeParams="true"/>" method="post">
					<table id="edit-0" align="center" class="edit_table">	
						<tr>
							<td>
								报废日期:
							</td>
							<td>
								<input name="equipmentTrash.trashDate" value="${equipmentTrash.trashDate}" type="text" class="Wdate" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
							</td>
						</tr>
						
						<tr>
							<td>
								报废原因:
							</td>
							<td>
								<textarea rows="30" cols="80" name="equipmentTrash.applyReason" class="validate['required','length[500]']">${equipmentTrash.applyReason}</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</tr>
						
						<tr>
							<td>
								备注：
							</td>
							<td>
								<textarea rows="30" cols="80" name="equipmentTrash.remark" class="validate['required','length[500]']">${equipmentTrash.remark}</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</tr>
					</table>
					<div align="center">
						<input name="query" type="submit" value="保存" class="btn_23">
						<input name="add" type="button" value="返回" class="btn_23" onclick="window.close()">
					</div>
				</form>
			</div>
		</div>
	</body>
</html>