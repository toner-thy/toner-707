<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>保密信息总览</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx}/resources/js/My97DatePicker/WdatePicker.js");" type="text/javascript"></script>
	<script src="${ctx}/platform/jsp/public/helpTree/resource/js/jquery-1.6.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
	<script src="${ctx}/platform/layout/borderlayout/js/TabUtils.js" type="text/javascript"></script>
	<script src="${ctx}/bmp/statinfo/borderlayout/view/number.js" type="text/javascript"></script>
	<script>
	$(document).ready(function(){
		<c:forEach items="${selectedModuleList}" var="module" varStatus="moduleIndex">
			$('#module${module.id}_title').click(function(){
			    $('#module${module.id}_content').load('${ctx}${module.url}&organId=${organ.organId}&queryDto.year=${queryDto.year}&queryDto.month=${queryDto.month}',function(){
			    	$('#module${module.id}_content').toggle(1000);
			    });
			  });
			$('#module${module.id}_title').text(getchinese('${moduleIndex.index + 1}') + "、" + '${module.business_module}');
	    </c:forEach>

		var oA =  document.getElementById("fanxuan");
		var oInput = $(".select input");
		var oLabel = document.getElementById("selectTitle");
		var isCheckAll = function ()
		{
			for (var i = 1, n = 0; i < oInput.length; i++)
			{
				oInput[i].checked && n++
			}
			oInput[0].checked = n == oInput.length - 1;
			oLabel.innerHTML = oInput[0].checked ? "全不选" : "全选";
		};
		//全选/全不选
		oInput[0].onclick = function ()
		{
			for (var i = 1; i < oInput.length; i++)
			{
				oInput[i].checked = this.checked;
			}
			isCheckAll();
		};
		//反选
		oA.onclick = function ()
		{
			for (var i = 1; i < oInput.length; i++)
			{
				oInput[i].checked = !oInput[i].checked;
			}
			isCheckAll();
		};
		//根据复选个数更新全选框状态
		for (var i = 1; i < oInput.length; i++)
		{
			oInput[i].onclick = function (){
				isCheckAll();
			}
		}

		// 勾选模块
		var array = new Array();
		array = "${selectedModuleValueList}".replace('[','').replace(']','').split(',');
		if(array.length != 1){
	        for(var i=0;i<array.length;i++){
				if(array[i] != 'null'){
		        	var value = "#id_" + array[i].trim();
					$(value).attr('checked', true);
				}
	        }
	  	 } else{
	  		oInput[0].click();
	  	 }

	});

		/** 整体导出保密工作台账 */
		function doDownloadWord(){
			$('#query_form').attr("action", "${ctx}/bmp/statinfo/statinfo_xmlExport.action");
			$('#query_form').submit();
			$('#query_form').attr("action", "${ctx}/bmp/statinfo/statinfo_index.action?flag=${flag}");
		}
		/** 选择导出保密工作台账（不含表头） */
		function doDownloadWordSelected(){
			var oInput = $(".select input");
			var flag = 0;
			for (var i = 1, n = 0; i < oInput.length; i++){
				if(oInput[i].checked){
					flag = 1;
				}
			}
			if(flag == 0){
				alert("至少选择一个模块才能进行导出操作");
				return;
			}
			$('#query_form').attr("action", "${ctx}/bmp/statinfo/statinfo_xmlExportSelected.action");
			$('#query_form').submit();
			$('#query_form').attr("action", "${ctx}/bmp/statinfo/statinfo_index.action?flag=${flag}");
		}
		/**  查询  */
		function doFormQuery(){
			var oInput = $(".select input");
			var flag = 0;
			for (var i = 1, n = 0; i < oInput.length; i++){
				if(oInput[i].checked){
					flag = 1;
				}
			}
			if(flag == 0){
				alert("至少选择一个模块才能进行查询操作");
				return;
			}
			$('#query_form').submit();
		}
	</script>
	<style>
	 .module_title {
			cursor: pointer;
			line-height: 27px;
			height: 27px;
			text-align: left;
			background-color: #CEE0EE;
			padding-left: 39px;
			color: #27272B;
			font-weight: bold;
			border-bottom: 1px solid #A6BDCE;
		}
	 .module_content {
			text-align: center;
		}

	</style>
</head>
<body>
	<!-- 公共头部 -->
	<div class="button_bar">
		<div class="left">
			<div class="pop_button_bar">
				<c:if test="${flag eq '1' }">
					<a class="pop_button" href="${ctx}/bmp/statinfo/statinfo_main.action"><span>返回</span></a>
				</c:if>
			</div>
		</div>
		<div class="right">
			<div class="pop_button_bar">
				<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.location.reload();"><span>刷新本页</span></a>
				<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
			</div>
		</div>
	</div>
	<!-- 内容panel开始 -->
	<div class="body_content">
		<div class="panel">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">
					快速查询
				</div>
				<div class="panel_btn_bar pop_button_bar">

				</div>
			</div>
			<div class="panel_content">
				<form id="query_form" name="query_form" action="${ctx}/bmp/statinfo/statinfo_index.action?flag=${flag}" method="post">
					<input type="hidden" id="organ.organId" name="organ.organId" value="${organ.organId }"/>
					<table class="panel_content_search_form">
						<tr id="busniess_id">
							<td colspan="3" class="select">
							    <input id="quanxuan" type="checkbox" value="1000" /><label id="selectTitle">全选</label>
							    <a id="fanxuan" href="javascript:;" style="margin-left: 20px;margin-right: 20px;">反选</a>
						       	<div style="height: 100px;">
						       		<ul>
								        <c:forEach items="${list}" var="module" varStatus="moduleIndex">
		<%-- 						        	<input class="moduleClass" type="checkbox" name="selectedModuleValueList[${module.id}]" id="id_${module.id}" value="${module.id}" /><label>${module.business_module}</label> --%>
								        	<li style="width: 200px;float: left;"><input class="moduleClass" type="checkbox" name="selectedModuleValueList[${module.id}]" id="id_${module.id}" value="${module.id}" /><label>${module.business_module}</label></li>
								        </c:forEach>
						       		</ul>
						       	</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div class="btn_query_bar pop_button_bar">
	 								<a class="pop_button" href="###" onclick="javascript:doFormQuery()"><span>查询</span></a>
	 								<a class="pop_button" href="###" onclick="javascript:doDownloadWordSelected();"><span>选择模块导出</span></a>
									<a class="pop_button" href="###" onclick="javascript:doDownloadWord();"><span>导出保密工作台账</span></a>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<br/>
		<div class="panel tMargin">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">
<%-- 					【${title}】保密工作信息总览 --%>
					【${organ.organName}】保密工作信息总览
				</div>
				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
				<c:forEach items="${selectedModuleList}" var="module" varStatus="moduleIndex">
					 <div class="module_title" id="module${module.id}_title"></div>
					 <div class="module_content" id="module${module.id}_content" style="display: none"></div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 内容panel结束 -->
</body>
</html>