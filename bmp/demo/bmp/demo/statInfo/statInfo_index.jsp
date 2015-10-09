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
			$("#module1_title").click(function(){
			    $("#module1_content").load("${ctx}/bmp/demo/statInfo/test1.jsp",function(){
			    	$("#module1_content").toggle(1000);
			    });
			  });
			$("#module2_title").click(function(){
			    $("#module2_content").load("${ctx}/bmp/demo/statInfo/test2.jsp",function(){
			    	$("#module2_content").toggle(1000);
			    });
			  });
			$("#module3_title").click(function(){
			    $("#module3_content").load("${ctx}/bmp/demo/statInfo/test3.jsp",function(){
			    	$("#module3_content").toggle(1000);
			    });
			  });
			$("#module4_title").click(function(){
			    $("#module4_content").load("${ctx}/bmp/demo/statInfo/test4.jsp",function(){
			    	$("#module4_content").toggle(1000);
			    });
			  });
			$("#module5_title").click(function(){
			    $("#module5_content").load("${ctx}/bmp/demo/statInfo/test5.jsp",function(){
			    	$("#module5_content").toggle(1000);
			    });
			  });
			$("#module6_title").click(function(){
			    $("#module6_content").load("${ctx}/bmp/demo/statInfo/test6.jsp",function(){
			    	$("#module6_content").toggle(1000);
			    });
			  });
			$("#module7_title").click(function(){
			    $("#module7_content").load("${ctx}/bmp/demo/statInfo/test7.jsp",function(){
			    	$("#module7_content").toggle(1000);
			    });
			  });
			$("#module8_title").click(function(){
			    $("#module8_content").load("${ctx}/bmp/demo/statInfo/test8.jsp",function(){
			    	$("#module8_content").toggle(1000);
			    });
			  });
			$("#module9_title").click(function(){
			    $("#module9_content").load("${ctx}/bmp/demo/statInfo/test9.jsp",function(){
			    	$("#module9_content").toggle(1000);
			    });
			  });
			$("#module10_title").click(function(){
			    $("#module10_content").load("${ctx}/bmp/demo/statInfo/test10.jsp",function(){
			    	$("#module10_content").toggle(1000);
			    });
			  });
			$("#module11_title").click(function(){
			    $("#module11_content").load("${ctx}/bmp/demo/statInfo/test11.jsp",function(){
			    	$("#module11_content").toggle(1000);
			    });
			  });
			$("#module12_title").click(function(){
			    $("#module12_content").load("${ctx}/bmp/demo/statInfo/test12.jsp",function(){
			    	$("#module12_content").toggle(1000);
			    });
			  });

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
			oLabel.innerHTML = oInput[0].checked ? "全不选" : "全选"
		};
		//全选/全不选
		oInput[0].onclick = function ()
		{
			for (var i = 1; i < oInput.length; i++)
			{
				oInput[i].checked = this.checked
			}
			isCheckAll()
		};
		//反选
		oA.onclick = function ()
		{
			for (var i = 1; i < oInput.length; i++)
			{
				oInput[i].checked = !oInput[i].checked
			}
			isCheckAll()
		};
		//根据复选个数更新全选框状态
		for (var i = 1; i < oInput.length; i++)
		{
			oInput[i].onclick = function ()
			{
				isCheckAll()
			}
		}
	});
	var count = 0 ;
	function doShowQueryDiv(){
		count++ ;
		if(count % 2 != 0){
			document.getElementById("custom_id").style.display = '';
			document.getElementById("busniess_id").style.display = '';
		} else {
			document.getElementById("custom_id").style.display = 'none';
			document.getElementById("busniess_id").style.display = 'none';
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
				<form id="role_list_form" name="role_list_form" action="/bmp/platform/role/role_list.action" method="post">
				<table class="panel_content_search_form">
					<tr>
						<td width="100%" colspan="3">
							年度：
							<input class="Wdate" type="text" id="d15" onFocus="WdatePicker({skin:'whyGreen',isShowClear:false,readOnly:true,dateFmt:'yyyy年'})" style="width: 60px;"/>
							<input type="hidden" id="t_month" value=""/>
							<a id='1' style="color:${t_month=='1'?'red':''}"   href="###" onclick="javascript:$('t_month').value='1',window.location.href='http://74.2.74.75:3080/bmp//bmp/demo/statInfo/statInfo_index.jsp'">1月</a>
							<a id='2'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">2月</a>
							<a id='3'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">3月</a>
							<a id='4'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">4月</a>
							<a id='5'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">5月</a>
							<a id='6'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">6月</a>
							<a id='7'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">7月</a>
							<a id='8'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">8月</a>
							<a id='9'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">9月</a>
							<a id='10'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">10月</a>
							<a id='11'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">11月</a>
							<a id='12'   href="###" onclick="javascript:$('userInfo.name').value='-a',userInfo_list_form.submit()">12月</a>
						</td>
					</tr>
					<tr id="busniess_id" style="display: none">
						<td colspan="3" class="select">
						    <input id="quanxuan" type="checkbox" id="checkAll" /><label id="selectTitle">全选</label><a id="fanxuan" href="javascript:;" style="margin-left: 20px;">反选</a>
					        <input type="checkbox" name="item" /><label>保密工作机构</label>
					        <input type="checkbox" name="item" /><label>要害部门</label>
					        <input type="checkbox" name="item" /><label>要害部位</label>
					        <input type="checkbox" name="item" /><label>涉密人员</label>
					        <input type="checkbox" name="item" /><label>商业秘密事项</label>
					        <input type="checkbox" name="item" /><label>密品</label>
					        <input type="checkbox" name="item" /><label>涉密科研项目</label>
					        <input type="checkbox" name="item" /><label>涉密计算机</label>
					        <input type="checkbox" name="item" /><label>涉密网络</label>
					        <input type="checkbox" name="item" /><label>涉密网络终端</label>
					        <input type="checkbox" name="item" /><label>移动存储介质</label>
					        <input type="checkbox" name="item" /><label>其他设备</label>
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
 								<a class="pop_button" href="###"><span>查询</span></a>
 								<a class="pop_button" href="###" onclick="doShowQueryDiv()"><span>高级查询</span></a>
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
					【2014年1月】保密工作信息总览
				</div>
				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
			    <div class="module_title" id="module1_title">一、保密工作机构</div>
				<div class="module_content" id="module1_content" style="display: none"></div>


			    <div class="module_title" id="module2_title">二、要害部门</div>
				<div class="module_content" id="module2_content" style="display: none"></div>


			    <div class="module_title" id="module3_title">三、要害部位</div>
				<div class="module_content" id="module3_content" style="display: none"></div>


			    <div class="module_title" id="module4_title">四、涉密人员</div>
				<div class="module_content" id="module4_content" style="display: none"></div>

			    <div class="module_title" id="module5_title">五、商业秘密事项</div>
				<div class="module_content" id="module5_content" style="display: none"></div>

			    <div class="module_title" id="module6_title">六、密品</div>
				<div class="module_content" id="module6_content" style="display: none"></div>

			    <div class="module_title" id="module7_title">七、涉密科研项目</div>
				<div class="module_content" id="module7_content" style="display: none"></div>

			    <div class="module_title" id="module8_title">八、涉密计算机</div>
				<div class="module_content" id="module8_content" style="display: none"></div>

			    <div class="module_title" id="module9_title">九、涉密网络</div>
				<div class="module_content" id="module9_content" style="display: none"></div>

			    <div class="module_title" id="module10_title">十、涉密网络终端</div>
				<div class="module_content" id="module10_content" style="display: none"></div>

			    <div class="module_title" id="module11_title">十一、移动存储介质</div>
				<div class="module_content" id="module11_content" style="display: none"></div>

			    <div class="module_title" id="module12_title">十二、其他设备</div>
				<div class="module_content" id="module12_content" style="display: none"></div>
			</div>
		</div>
	</div>
	<!-- 内容panel结束 -->
</body>
</html>