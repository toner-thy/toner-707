<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>技术设备</title>
		
		<!-- css -->
		<link href="${ctx}/platform/theme/default/formcheck/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- js -->
		<script src="${ctx}/platform/theme/public_js/pub.js"type="text/javascript" ></script>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/mootools-core.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/mootools-more.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/utils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/TabUtils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
		
		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/bmp/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />
		
		<!-- 本页CSS/JS -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/miftree/css/mif-tree.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/explorer/message/css/ymPrompt.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/resources/js/miftree/1.1/mif.tree-v1.1.js" type="text/javascript"></script>
		<script src="${ctx}/explorer/message/js/ymPrompt.js" type="text/javascript" ></script>
		<script src="${ctx}/explorer/message/js/showMsg.js" type="text/javascript" ></script>
		
		<s:actionmessage theme="messages" />
	
		<style type="text/css">
			html,body {padding: 0}
		</style>
	</head>
	
	<body>
		<div id="body_content">
			<!-- 左侧树 -->
			<div class="tree">
				<!-- 树标题 -->
				<div id="tree_header" class="panel_header">
					<div class="panel_title">保密技术设备分类结构
					</div>
				</div>
				<!-- 树内容 -->
				<div id="secrecyEquipmentCategory_tree" class="panel_content"></div>
			</div>
			
			<!-- 右侧内容开始 -->
			<div class="tree_right_content" style="border: 1px solid #B5CBD9;">
				<iframe id="equipmentTrash_device" src="<s:url action="deviceMgrAction_list"/>" name="equipmentTrash_device" height="25%" width="99%" scrolling="auto" frameborder="0"></iframe>
				<div style="visibility: hidden;height: 1px;"></div>
				<iframe id="equipmentTrash_pass" src="<s:url action="equipmentTrash_trashPassList"/>" name="equipmentTrash_pass" height="25%" width="99%" scrolling="auto" frameborder="0"></iframe>
			</div>
			<!-- 右侧内容结束 -->
		</div>
	</body>
</html>
<script type="text/javascript">
	tree = new Mif.Tree({
		container: $('secrecyEquipmentCategory_tree'),
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
		url: '${pageContext.request.contextPath}/secrecyEquipmentCategory_tree.action?times='+new Date().getTime()});
	tree.addEvent('select',function(node){
		document.getElementById('equipmentTrash_device').src = "deviceMgrAction_list.action?secrecyEquipment.secrecyEquipmentCategory.secrecyEquipmentCategoryId="+node.data.id+"&secrecyEquipment.secrecyEquipmentCategory.name="+node.name+"&showPagination=false"
	});
	$("secrecyEquipmentCategory_tree").setStyle("height",window.getSize().y - $("tree_header").getSize().y - 10);
</script>
