<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>采购申请审批</title>
		
		<!-- 头部信息 -->
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css -->
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/ec.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/formcheck/formcheck.css" type="text/css" rel="stylesheet" />
		
		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/formcheck/formcheck.js"></script>
	
		<script type="text/javascript">
			window.addEvent('domready', function(){        
				new FormCheck('form_stockApply_audit',{
					display:{
						showErrors:1
					}
				});
			 });
			 
			//返回
		    function doCancel()
		    {
		    	window.location.href='<s:url action="equipmentLoan_auditLoan" includeParams="false"/>';
		    }
			
			//审批通过
		    function doAudit(flag)
		    {
		    	if(flag==7)
		    	{
		    		$('equipmentLoan.status').value=7;
		    	}
		    	else if(flag==11)
		    	{
			    	$('equipmentLoan.status').value=11;
		    	}else if(flag==15){
		    		$('equipmentLoan.status').value=15;
		    	}
				$('form_equipmentLoan_audit').submit();
		    }
		</script>	
		
		<!-- css -->
		<style> 
			v.multieditbox{ 
				background: #fff; 
			    border-bottom: #7f9db9 1px solid; 
			    border-left: #7f9db9 1px solid; 
			    border-right: #7f9db9 1px solid; 
			    border-top: #7f9db9 1px solid; 
			    color: Black; 
			    cursor: text; 
			    font-family: &quot;arial&quot;; 
			    font-size: 9pt; 
				line-height:26px; 
			    padding: 1px;  
			} 
			.coolscrollbar{ 
				color: #000000;  
				SCROLLBAR-FACE-COLOR: #BED8EB;  
				SCROLLBAR-SHADOW-COLOR: #DDF8FF;  
				SCROLLBAR-HIGHLIGHT-COLOR: #92C0D1;  
				SCROLLBAR-3DLIGHT-COLOR: #DDF8FF;  
				SCROLLBAR-DARKSHADOW-COLOR: #92C0D1;  
				SCROLLBAR-TRACK-COLOR:#e2f1fb;  
				SCROLLBAR-ARROW-COLOR: #92C0D1 ; 
			} 
		</style>
	</head>
	
	<body>
		<ap:step/>
		<div class="body_content">
			<div class="edit_content" >
				<h2>
					设备使用申请审批
				</h2>
				<form id="form_equipmentLoan_audit" class="form"
					action="<s:url action="equipmentLoan_saveAuditLoan" includeParams="true"/>" method="post">
					<input type="hidden" name="equipmentLoan.status" id="equipmentLoan.status" value="">
					<input type="hidden" name="equipmentLoan.equipmentLoanId" id="" value="${equipmentLoan.equipmentLoanId }">
					<table id="edit-0" align="center" class="edit_table">	
						<tr>
						    <Td width="15%" align="right">保密技术设备：</Td>
						    <Td width="25%">
						    	${equipmentLoan.secrecyEquipment.name }
						    </Td>
						    <Td align="right">申请人：</Td>
						    <Td>
						    	${equipmentLoan.loanPerson.name }
						    </Td>
						</tr>
						
						<tr>
							<td align="right">使用起始日期<font color="red">*</font>：</td>
							<td>
								${equipmentLoan.loanStartTime }
							</td>
							<td align="right">使用归还时间<font color="red">*</font>：</td>
							<td>
								${equipmentLoan.loanEndTime }
							</td>
						</tr>
						
						<tr>
							<Td align="right" valign="top">
								使用是由：
							</Td>
							<Td colspan="3">
								<textarea rows="" cols="" readonly="readonly">${equipmentLoan.loanReason}</textarea>
							</Td>
						</tr>
						
						<tr>
							<Td align="right"  valign="top">
								备注：
							</Td>
							<Td colspan="3">
								<textarea rows="" cols="" readonly="readonly">${equipmentLoan.remark}</textarea>
							</Td>
						</tr>
					</table>
				<!-- 
				<table id="edit-1" align="center" class="edit_table">
				<s:iterator value="list">
				<h2>历史审批记录</h2>
					<tr>
						<td align="left"><strong>审批者：</strong><s:property value="auditPerson.userInfo.name" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>计划购入时间：</strong><s:date name="planBuyDate" format="yyyy-MM-dd"/>&nbsp;
						</td>
					</tr>
					<tr>
						<td align="left"><strong>审核意见：</strong><s:property value="auditOpinion" />&nbsp;</td>
					</tr>
				</s:iterator>
				</table>
				 -->
					<h2>审批</h2>
					<table id="edit-2" align="center" class="edit_table">
						<tr>
							<td align="right" valign="top" width="15%">审批意见：</td>
							<td colspan="3">
								<textarea class="textarea validate['length[100]'] multieditbox coolscrollbar" style="width:600px;height:110px"></textarea>
							</td>
						</tr>
						
						<tr>
							<td colspan="4">
								<div align="center">
									<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
									<input name="update" type="button" value="审批通过" class="btn_23" onclick="doAudit(11)"/>
									<input name="update" type="button" value="提交给上级审批" class="btn_23" onclick="doAudit(15)"/>
									<input name="update" type="button" value="驳回" class="btn_23" onclick="doAudit(7)"/>
									<input name="back" type="button" value="返回" class="btn_23" onclick="doCancel()" >
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>