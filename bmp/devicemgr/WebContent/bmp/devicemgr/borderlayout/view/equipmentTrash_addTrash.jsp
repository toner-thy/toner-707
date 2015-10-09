<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>申请报废设备</title>
		
		<!-- css -->
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/formcheck/formcheck.css" type="text/css" rel="stylesheet" />
		
		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/formcheck/formcheck.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>
		
		<script language="javascript">
			//弹窗问题
			var flag = '${Flag}';
			if(flag=='succ'){
				alert('申请报废设备成功！');
				window.close();
			}
			
			window.addEvent('domready', function(){
				new FormCheck('form1',{
					display:{
						showErrors:1
					}
				});
				$('equipmentTrash.applyReason').focus();
			});
			
			//返回
			function backList(){
				window.close();
			}
			
			//保存
			function save(){
				$('form1').submit();
			}
		</script>	
	</head>
	
	
	
	<body>
		<div class="body_content">
			<div class="edit_content" >
				<h2>
					申请报废设备
				</h2>
				<form id="form1" class="form" action="<s:url action="equipmentTrash_saveTrash" includeParams="true"/>" method="post">
					<table id="edit-0" align="center" class="edit_table">	
						<tr>
							<td align="right" width="20%">
								报废日期：
							</td>
							<td>
								<input name="equipmentTrash.trashDate" value="${equipmentTrash.trashDate}" type="text" class="Wdate validate['required']" readonly="readonly" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</tr>
						
						<tr>
							<td  align="right" valign="top">
								报废原因：
							</td>
							<td>
								<textarea style="height:100px;width:90%" name="equipmentTrash.applyReason"  id="equipmentTrash.applyReason"  class="validate['required','length[500]']">${equipmentTrash.applyReason}</textarea><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</tr>
						
						<tr>
							<td  align="right" valign="top">
								备注：
							</td>
							<td>
								<textarea style="height:100px;width:90%" name="equipmentTrash.remark"class="validate['length[500]']">${equipmentTrash.remark}</textarea>
							</td>
						</tr>
					</table>
					<div align="center">
						<input name="query" type="submit" value="保存" class="btn_23" >
						<input name="add" type="button" value="关闭" class="btn_23" onclick="window.close()">
					</div>
					<input type="hidden" name="equipmentTrash.secrecyEquipment.secrecyEquipmentId" value="${secrecyEquipment.secrecyEquipmentId }">
				</form>
			</div>
		</div>
	</body>
</html>