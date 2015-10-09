<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="ui" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>组织机构列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<script src="${pageContext.request.contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/template/borderlayout/resources/js/ectable/EcTable.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/resources/js/platform.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/SimpleUI/SimpleUI.js", function(){
			$ENV.onDomReady(function(){

			});
		});

		function show(id){
			$ENV.dialog.open({
				title : '组织机构详情',
				url : "${pageContext.request.contextPath}/platform/organ/organ_detail.action?organ.organId=" + id + "&_ts="+new Date().getTime(),
				width : 0.8,
				height : 0.8
			});
		}

		function organStatInfo(id){
			window.parent.location.href="${pageContext.request.contextPath}/bmp/statinfo/statinfo_index.action?organ.organId="+id+ "&flag=1&_ts="+new Date().getTime();
		}
		</script>
	</head>
	<body style="background-color: white;">
		<div class="panel">
			<%-- 头部 --%>
			<div class="panel_header">
				<%-- 标题 --%>
				<div class="panel_title panel_search_ico">
					组织机构查询
				</div>
				<%-- 右侧按钮 --%>
				<div class="panel_btn_bar pop_button_bar">
				</div>
			</div>
			<%-- 内容 --%>
			<div class="panel_content">
				<s:form action="statinfo_mainList.action" namespace="/bmp/statinfo" name="query_organ_form" id="query_organ_form" theme="simple">
				<input type="hidden" name="organ.parentOrgan.organId" value="${organ.parentOrgan.organId }"/>
				<input type="hidden" name="organ.district.districtCode" value="${organ.district.districtCode }"/>
				<table class="panel_content_search_form">
					<tr>
						<td style="text-align: right;width: 20%">
							<label for="organName">组织机构名称：</label>
						</td>
						<td style="text-align: left;width: 30%">
							<input type="text" name="organ.organName" id="organ.organName" value="${organ.organName}" />
						</td>
						<td>
							<div class="btn_query_bar pop_button_bar">
								<a class="pop_button" href="javascript:document.getElementById('query_organ_form').submit();"><span>查询</span></a>
							</div>
						</td>
					</tr>
				</table>
				</s:form>
			</div>
		</div>
		<br/>
		<div class="panel">
			<%-- 头部 --%>
			<div class="panel_header">
				<%-- 标题 --%>
				<div class="panel_title panel_list_ico">
				【${organ.district.districtName}】组织机构列表
				</div>
				<%-- 右侧按钮 --%>
				<div class="panel_btn_bar pop_button_bar">

				</div>
			</div>
			<div class="panel_content">
				<c:if test="${not empty list}">
					<ec:table items="list" var="organ" tableId="listId" border="0"
						action="${ctx}/bmp/statinfo/statinfo_mainList.action"
						imagePath="${pageContext.request.contextPath}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="false" showPagination="true">
						<ec:row>
							<ec:column property="organId" alias="organId_checkbox" cell="radio" title="选择"/>
							<ec:column property="organName" sortable="true" title="名称"/>
							<ec:column property="organShortName" sortable="true" title="简称"/>
							<ec:column property="logoutStatus" sortable="true" title="是否是注册机构">
								<span style="color:${organ.logoutStatus eq 'ENABLE' ? '' : 'red'}">
									${organ.logoutStatus eq 'ENABLE' ? '注册机构' : '未注册机构'}
								</span>
								<input type="hidden" id="${organ.organId}_name" value="${organ.organName}"/>
							</ec:column>
							<ec:column property="null" title="详细信息"><a href="javascript:show('${organ.organId}');"><img style="border: 0;" src="${pageContext.request.contextPath}/platform/template/borderlayout/skin/blue/img/btn/display.gif"/></a></ec:column>
							<ec:column property="null" title="概览"><a href="javascript:organStatInfo('${organ.organId}');">概览</a></ec:column>
						</ec:row>
					</ec:table>
				</c:if>
				<c:if test="${empty list}">
					<ui:noData text="当前查询未找到单位"/>
				</c:if>
			</div>
		</div>
	</body>
</html>