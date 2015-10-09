<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/organ" prefix="organ" %>
<%@ taglib uri="http://www.cdthgk.com/tags/theme" prefix="theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<title>各单位填报情况一览</title>

		<script src="${pageContext.request.contextPath }/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/platform/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/css/page.css");
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/resources/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/formcheck/css/formcheck.css");

		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/SimpleUI/SimpleUI.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/formcheck/1.4/formcheck.js",function(){
			$ENV.onDomReady(function(){
				var formcheck = new FormCheck('query_form',{
					display:{
						showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
					},
					trimValue: true
				});

				$('btnQuery').addEvent('click', function(){
					if (formcheck.isFormValid()) {
						$('sub').click();
					}
				});
			});
		});
		</script>
	</head>
	<body>
		<div class="button_bar">
			<div class="left">
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###"><span>刷新本页</span></a>
<%-- 					<a class="pop_button pop_button_help" href="###"><span>本页帮助</span></a> --%>
					<a class="pop_button pop_button_close" href="###"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content">
			<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleSearchIco">
						各单位填报情况一览
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_titleBtnBar">
					</div>
				</div>
				<!-- 内容 -->
				<div class="panel_content panel_content_table">
					<s:form action="detail" id="query_form" namespace="/bmp/pucha/detail" target="_blank" theme="simple">
					<input type="submit" id="sub" style="height: 0;width: 0;visibility: hidden;"/>
					<table class="panel_content_search_form">
						<tr>
							<td align="right">
								年份：
							</td>
							<td>
								<select name="year">
									<c:forEach var="y" items="${years}">
									<option value="${y}" <c:if test="${year == y}">selected="selected"</c:if>>${y}</option>
									</c:forEach>
								</select>
							</td>
							<td align="right">
								单位：
							</td>
							<td id="td_organ_select">
								<organ:selectByDistrct valueEl="organ.organId" textEl="organ.organName" required="true" onlyFromValue="true"/>
							</td>
							<td>
								<div class="btn_query_bar pop_button_bar">
									<a class="pop_button" href="###" id="btnQuery"><span>查询</span></a>
								</div>
							</td>
						</tr>
					</table>
					</s:form>
				</div>
			</div>
		</div>
	</body>
</html>