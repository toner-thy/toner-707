<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>借用申请</title>

		<!-- css -->
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/formcheck/formcheck.css" type="text/css" rel="stylesheet" />

		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/formcheck/formcheck.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>
		
		<script language="javascript">
			//处理弹窗
			var flag = '${Flag}';
			if(flag=='succ'){
				alert('借用申请成功！');
				window.close();
			}
		
			window.addEvent('domready', function(){
				new FormCheck('form1',{
					display:{
						showErrors:1
					}
				});
				$('equipmentLoan.loanReason').focus();
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
					借用申请
				</h2>
				<form id="form1" class="form" action="<s:url action="equipmentLoan_saveLoan" includeParams="true"/>" method="post">
					<table id="edit-0" align="center" class="edit_table">	
						<tr>
							<td align="right" width="20%">
								使用日期：
							</td>
							<td>
								<input name="equipmentLoan.loanStartTime" value="${equipmentLoan.loanStartTime}" type="text" class="Wdate validate['required']" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
								-<input name="equipmentLoan.loanEndTime" value="${equipmentLoan.loanEndTime}" type="text" class="Wdate validate['required']" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</tr>
						
						<tr>
							<td  align="right" valign="top"> 
								使用事由：
							</td>
							<td>
								<textarea style="height:100px;width:90%" name="equipmentLoan.loanReason" id="equipmentLoan.loanReason" class="validate['required','length[500]']"></textarea><span style="color:red;">&nbsp;&nbsp;*</span>
							</td>
						</tr>
						
						<tr>
							<td  align="right" valign="top">
								备注：
							</td>
							<td>
								<textarea style="height:100px;width:90%" name="equipmentLoan.remark" class="validate['length[500]']">${equipmentLoan.remark}</textarea>
							</td>
						</tr>
					</table>
					<div align="center">
						<input name="query" type="submit" value="保存" class="btn_23" >
						<input name="add" type="button" value="关闭" class="btn_23" onclick="window.close()">
					</div>
					<input type="hidden" name="equipmentLoan.secrecyEquipment.secrecyEquipmentId" value="${secrecyEquipment.secrecyEquipmentId }">
				</form>
			</div>
		</div>
	</body>
</html>