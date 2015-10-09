<%--<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>--%>
<%@ include file="/platform/jsp/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ include file="/platform/messages.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>会议分类</title>

		<!-- 头部信息 -->
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		
		<!-- css -->
		<style type="text/css">
			body{
				background: #FFF;
			}
		</style>		
	</head>
	
	<body>
		<div id="navigation"><ap:step/></div>
			<div id="title" class="edit_content" style="background: #FFF;">
				<table width="100%" height="100%">
					<tr> 
						<td width="20%" height="100%" align="left">
							<div class="edit_content" style="background: #FFF;">
								<h2>
									会议分类
								</h2>
								<div id="meetingCategory_tree" style="width: 100%; overflow: auto;float: left;margin-top:3px;"></div>
							</div>
						</td>
						<td width="80%" height="100%" align="left">
							<iframe name="meetingCategoryIframe" id="idmeetingCategoryIframe" scrolling="auto" src="meetingCategory_list.action" height="100%" width="100%" frameborder="0"></iframe>	
						</td>
					</tr>
				</table>
			</div>
	</body>
</html>
<!-- 构建树形的js -->
<script type="text/javascript">
	tree = new Mif.Tree({
		container: $('meetingCategory_tree'),
		forest: true,
		initialize: function(){
			//this.initCheckbox('deps');
			new Mif.Tree.KeyNav(this);
		},
		types: {
			folder:{
				openIcon: 'mif-tree-open-icon',
				closeIcon: 'mif-tree-close-icon'
			},
			loader:{
				openIcon: 'mif-tree-loader-open-icon',
				closeIcon: 'mif-tree-loader-close-icon'
			},
			disabled:{
				openIcon: 'mif-tree-open-icon',
				closeIcon: 'mif-tree-close-icon',
				dragDisabled: true,
				cls: 'disabled'
			},
			book:{
				openIcon: 'mif-tree-book-icon',
				closeIcon: 'mif-tree-book-icon',
				loadable: true
			},
			bin:{
				openIcon: 'mif-tree-bin-open-icon',
				closeIcon: 'mif-tree-bin-close-icon'
			}
		},
		dfltType:'folder'
	});
	tree.load({
		url: '${pageContext.request.contextPath}/meetingCategory_tree.action?times='+new Date().getTime()});
	tree.addEvent('select',function(node){
		document.getElementById('idmeetingCategoryIframe').src = "meetingCategory_list.action?meetingCategory.meetingCategoryId="+node.data.id+"&meetingCategory.name="+node.name+"&showPagination=false"
	});
	$("meetingCategory_tree").setStyle("height",window.getSize().y - $("navigation").getSize().y-42);
</script>