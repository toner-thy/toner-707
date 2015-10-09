<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow: hidden;">
	<head>
		<title>全局异常处理页面</title>

		<script>
			function showException(){
				var showFlag = document.getElementById("exceptionId").style.display;
				if(showFlag == 'none'){
					document.getElementById("exceptionId").style.display = "block"
				}else{
					document.getElementById("exceptionId").style.display = "none"
				}
			}
		</script>
		
	</head>
		<% 
			com.opensymphony.xwork2.util.ValueStack vs = (com.opensymphony.xwork2.util.ValueStack)request.getAttribute("struts.valueStack");
		%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-color: #fff;font-size: 12px;">
			<tr>
				<td width="127" valign="top" style="border-right: 1px dotted #ddd;">
					<img src="${ctx}/exception/images/exception.jpg" />
				</td>
				<td width="15" valign="top">
				</td>
				<td width="*" valign="top">
					<!-- 标题 -->
					<div style="border-bottom: 1px solid #ddd;margin-top: 10px;">
						<img src="${ctx}/exception/images/title.gif" />
					</div>
					
					<div style="width: 100%;padding-top: 10px;color: #797979;margin-bottom: 10px;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;抱歉，当前系统出现异常。请联系系统管理员进行处理，或点击 <a href="javascript:showException();"><span id="content">这 里</span></a> 查看具体异常信息。
					</div>
					<div id="exceptionId" style="display: none; overflow-y: auto; overflow-x: hidden; height: 458px;border: 1px solid #7692BA;">
						<%=vs.findValue("exceptionStack")%>
					</div>
				</td>
				<td width="15" valign="top">
				</td>
			</tr>
		</table>
	</body>
</html>

