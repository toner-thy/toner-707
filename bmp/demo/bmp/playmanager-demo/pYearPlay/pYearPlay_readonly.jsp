<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>计划管理--制定年度工作计划</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 复杂表格CSS支持 -->
<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/platform/tag/compositePanel/cp.css" type="text/css" rel="stylesheet" />

<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
<script src="${ctx}/platform/tag/compositePanel/cp.js" type="text/javascript"></script>
<script type="text/javascript">
	$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
	$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
	$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

	$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
	$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
	$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",
			function() {
				$ENV.onDomReady(function() {

				});
			});

	var count = 4;
	// 详 情
	function selectCheckRule() {
		$ENV.dialog.open({
					url : "${ctx}/bmp/playmanager-demo/pCheckProject/pCheckProject_select.jsp?_ts=" + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '详情',
					onClose : function(){
						var str = '<span style="margin-left:20px;font-size: large;">'+count+'、涉密环境检查</span><a  href="###" onclick="remove1(this)"  style="float: right;font-size: large;">删除</a></div>';
						var ele = new Element('div', {name : 'rowdiv'});
						ele.inject($('panlist'));
						ele.setStyle('width', '80%');
						ele.set('html',str);
						count++;
					}
				});
	}

	function remove1(obj){
		$(obj).getParent().remove();
	}
	function changePage(year){
			window.location.href="${ctx}/bmp/playmanager-demo/pYearPlay/pYearPlay_readonly.jsp?year="+year;
	}

	function doEdit(url){
		var year = 0;
		<c:choose>
		<c:when test="${empty param.year }">
			year = 2014;
		</c:when>
		<c:otherwise>
			year = "${param.year }";
		</c:otherwise>
	</c:choose>
		if( year < 2014 ){
			alert("今年以前的年度计划不可以编辑");
			return;
		}
		url = url + "?year=" + year;
		window.location.href= url;
	}

	function doExport(url){
		window.location.href= url;
	}

</script>
</head>

<body>
	<!-- 公共头部 -->
	<div class="button_bar">
		<div class="left">
			<div class="pop_button_bar">
				<div class="pop_button_bar">
					<a href="###" onclick="doEdit('${ctx}/bmp/playmanager-demo/pYearPlay/pYearPlay_plan.jsp');this.blur();" class="pop_button"><span>编辑</span></a>
					<a href="###" onclick="doExport('${ctx}/bmp/playmanager-demo/pYearPlay/pYearPlay_plan.xls');this.blur();" class="pop_button"><span>导出</span></a>
				</div>
			</div>
		</div>
		<div class="right">
			<div class="pop_button_bar">
				 <a	class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
				 <a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
			</div>
		</div>
	</div>
	<form action="">
		<div class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div style="text-align: center;border: 1px solid orange;">
					<s:iterator value="{'2010','2011','2012','2013','2014','2015'}" id='number'>
					    <a href="###" onclick="changePage('<s:property value='number'/>')">
						    <font style="font-weight: bold;font-size: large;">
							    <s:property value='number'/>年
						    </font>
					    </a>&nbsp;&nbsp;&nbsp;&nbsp;
					</s:iterator>
				</div>
				<div style="text-align: center;"><font style="font-weight: bolder;font-size: xx-large;">
				<c:choose>
					<c:when test="${empty param.year }">
						2014
					</c:when>
					<c:otherwise>
						${param.year }
					</c:otherwise>
				</c:choose>
				年年度计划</font></div>
				<div id="panlist">
					<div style="text-align: center;">
						<font style="font-weight: bolder;font-size: large;">计划内容</font>
					</div>
					<div style="width:80%;">
						<span style="margin-left:20px;font-size: large;">1、是否填写要害部门信息</span>
					</div>
					<div style="width:80%;">
						<span style="margin-left:20px;;font-size: large;">
							2、要害部门数量
						</span>
					<div style="width:80%;">
						<span style="margin-left:20px;;font-size: large;">
							3、要害部门XXXXX检查规则
						</span>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</form>
</body>
</html>


