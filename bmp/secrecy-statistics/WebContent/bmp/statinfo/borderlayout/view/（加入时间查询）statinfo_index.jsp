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
	<script>
	$(document).ready(function(){
		<c:forEach items="${selectedModuleList}" var="module" varStatus="moduleIndex">
			$('#module${module.id}_title').click(function(){
			    $('#module${module.id}_content').load('${ctx}${module.url}?organId=${organId}&queryDto.year=${queryDto.year}&queryDto.month=${queryDto.month}',function(){
			    	$('#module${module.id}_content').toggle(1000);
			    });
			  });
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

	var count = 0 ;
	function doShowQueryDiv(){
		count++ ;
		if(count % 2 != 0){
			document.getElementById("custom_id").style.display = '';
			document.getElementById("busniess_id").style.display = '';
			document.getElementById("year_id").style.display = 'none';
		} else {
			document.getElementById("custom_id").style.display = 'none';
			document.getElementById("busniess_id").style.display = 'none';
			document.getElementById("year_id").style.display = '';
		}
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
					<a class="pop_button" href="###" onclick="javascript:window.refresh();"><span>导出</span></a>
				</div>
			</div>
			<div class="panel_content">
				<form id="query_form" name="query_form" action="${ctx}/bmp/statinfo/statinfo_index.action" method="post">
					<table class="panel_content_search_form">
						<tr id="year_id">
							<td width="100%" colspan="3">
								年度：
								<input class="Wdate" type="text" id="queryDto.year" name="queryDto.year"
									value="${queryDto.year }"
									onFocus="WdatePicker({skin:'whyGreen',isShowClear:false,readOnly:true,dateFmt:'yyyy'})"
									style="width: 60px;"/>
								<input type="hidden" id="queryDto.month" name="queryDto.month" value="${queryDto.month }"/>
								<a id='1' style="color:${queryDto.month=='1'?'red':''}" href="###" onclick="javascript:document.getElementById('queryDto.month').value='1',query_form.submit()">1月</a>
								<a id='2' style="color:${queryDto.month=='2'?'red':''}"  href="###" onclick="javascript:document.getElementById('queryDto.month').value='2',query_form.submit()">2月</a>
								<a id='3' style="color:${queryDto.month=='3'?'red':''}"  href="###" onclick="javascript:document.getElementById('queryDto.month').value='3',query_form.submit()">3月</a>
								<a id='4' style="color:${queryDto.month=='4'?'red':''}"  href="###" onclick="javascript:document.getElementById('queryDto.month').value='4',query_form.submit()">4月</a>
								<a id='5' style="color:${queryDto.month=='5'?'red':''}"  href="###" onclick="javascript:document.getElementById('queryDto.month').value='5',query_form.submit()">5月</a>
								<a id='6' style="color:${queryDto.month=='6'?'red':''}"  href="###" onclick="javascript:document.getElementById('queryDto.month').value='6',query_form.submit()">6月</a>
								<a id='7' style="color:${queryDto.month=='7'?'red':''}"  href="###" onclick="javascript:document.getElementById('queryDto.month').value='7',query_form.submit()">7月</a>
								<a id='8' style="color:${queryDto.month=='8'?'red':''}"  href="###" onclick="javascript:document.getElementById('queryDto.month').value='8',query_form.submit()">8月</a>
								<a id='9' style="color:${queryDto.month=='9'?'red':''}"  href="###" onclick="javascript:document.getElementById('queryDto.month').value='9',query_form.submit()">9月</a>
								<a id='10' style="color:${queryDto.month=='10'?'red':''}" href="###" onclick="javascript:document.getElementById('queryDto.month').value='10',query_form.submit()">10月</a>
								<a id='11' style="color:${queryDto.month=='11'?'red':''}" href="###" onclick="javascript:document.getElementById('queryDto.month').value='11',query_form.submit()">11月</a>
								<a id='12' style="color:${queryDto.month=='12'?'red':''}" href="###" onclick="javascript:document.getElementById('queryDto.month').value='12',query_form.submit()">12月</a>
							</td>
						</tr>
						<tr id="busniess_id" style="display: none">
							<td colspan="3" class="select">
							    <input id="quanxuan" type="checkbox" value="1000" /><label id="selectTitle">全选</label>
							    <a id="fanxuan" href="javascript:;" style="margin-left: 20px;margin-right: 20px;">反选</a>
						        <c:forEach items="${list}" var="module" varStatus="moduleIndex">
						        	<input class="moduleClass" type="checkbox" name="selectedModuleValueList[${module.id}]" id="id_${module.id}" value="${module.id}" /><label>${module.business_module}</label>
						        </c:forEach>
							</td>
						</tr>
						<tr id="custom_id" style="display: none">
							<td width="50%" colspan="2">
								<input type="radio" name="radioType" value="1"/>第一季度
								<input type="radio" name="radioType" value="1"/>第二季度
								<input type="radio" name="radioType" value="1"/>第三季度
								<input type="radio" name="radioType" value="1"/>第四季度
								<input type="radio" name="radioType" value="1"/>上半年
								<input type="radio" name="radioType" value="1"/>下半年
								<input type="radio" name="radioType" value="1"/>全年
							</td>
							<td width="50%">
								<label>自定义时间段：</label>
								<input id="txtStartDate" type="text" class="Wdate"
									 onfocus="var d5222=$dp.$('txtEndDate');WdatePicker({onpicked:function(){txtEndDate.focus();},maxDate:'#F{$dp.$D(\'txtEndDate\')}'})"
									 onclick="WdatePicker({readOnly:true,maxDate:'#F{$dp.$D(\'txtEndDate\',{d:-1})}'})" />
								<input id="txtEndDate" type="text" class="Wdate"
									onfocus="WdatePicker({minDate:'#F{$dp.$D(\'txtStartDate\')}'})"
									onclick="WdatePicker({readOnly:true,minDate:'#F{$dp.$D(\'txtStartDate\',{d:1})}'})" />

							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div class="btn_query_bar pop_button_bar">
	 								<a class="pop_button" href="###" onclick="javascript:query_form.submit()"><span>查询${organId }</span></a>
	 								<a class="pop_button" href="###" onclick="doShowQueryDiv()"><span>高级选项</span></a>
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
					【${title}】保密工作信息总览
				</div>
				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
				<c:forEach items="${selectedModuleList}" var="module" varStatus="moduleIndex">
					 <div class="module_title" id="module${module.id}_title">${moduleIndex.index + 1}、${module.business_module}</div>
					 <div class="module_content" id="module${module.id}_content" style="display: none"></div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 内容panel结束 -->
</body>
</html>