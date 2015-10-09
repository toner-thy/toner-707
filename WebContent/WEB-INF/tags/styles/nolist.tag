<%@ tag body-content="scriptless" pageEncoding="utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="text" type="java.lang.String"%>
<%
	jspContext.setAttribute("text",text);
	
%>
<div align="center">
	<c:choose>
		<c:when test="${text==null||test==''}">
			<font color="red">没有发现数据！</font>
		</c:when>
		<c:otherwise>
			<font color="red">${text }</font>
		</c:otherwise>	
	</c:choose>
</div>