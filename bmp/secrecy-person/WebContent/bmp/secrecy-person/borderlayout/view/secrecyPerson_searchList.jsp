<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部门选择列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${contextPath}/resources/js/SimpleUI/SimpleUI.js", function(){
					$ENV.onDomReady(function(){
						<%-- 为点击进行选择的按钮加入事件--%>
						$('btn_select').addEvent('click', function(){
							var value = EcTable.getRadioItem();
							var arg = window.getParams();
							if (arg.select) {
								arg.select({
									"text" : $('name_' + value).value,
									"value" : value
								});
							} else {
								var arg = window.getParams();
								var dc = window.getOpener().document;
								dc.getElementById(arg.hidden).value = value;
								dc.getElementById(arg.text).value = $('name_' + value).value;
							}
							window.close();
						});
						<%-- 选中已经选择的数据 --%>
						window.onDialogReady = function(){
							var arg = window.getParams();
							if (arg.value) {
								EcTable.getAllRadioItems().each(function(item){
									if (item.value == arg.value) item.checked = true;
								});
							}
						};

						$('btn_query').addEvent('click', function(){
							$('secrecyPerson_query_form').submit();
						});
					});
				});

		</script>

		<style type="text/css">
		.panel .panel_content .panel_content_formTable td{
			border:0px;
			border-bottom: 1px dashed #B3C0C8;
			padding: 0;
		}
		.panel .panel_content .panel_content_formTable td span{
			color:white;
		}
		.st td {
		    border-bottom: 1px dashed #B3C0C8;
		    color: #6B6B6B;
		    padding-bottom: 4px;
		    padding-bottom: 4px;
		    padding-top: 4px;
		}
		</style>
	</head>

	<body>
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" id="btn_select" ><span>选择</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a href="javascript:window.close();" class="pop_button pop_button_close"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div class="body_content" style="overflow: auto;">
		    <div class="panel">
		    	<div class="panel_header">
		    		涉密人员查询
		    	</div>
		    	<div class="panel_content" >
		    	<form id="secrecyPerson_query_form" action="<s:url action="singelSelect" namespace="/bmp/secrecyPersonSearch"/>" method="post"  >
						<table width="100%">
							<tr>
								<td class="tbLable fr">
									姓名：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyPersonQo.name" id="secrecyPersonQo.name" value="${secrecyPersonQo.name}" />
								</td>
								<td colspan="1" class="fl" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="###" id="btn_query"><span>查 询</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
		    	</div>
		    </div>
			<div class="panel_content">

				<!-- 内容panel开始 -->
				<div class="panel tMargin">
					<div class="panel_content panel_content_table">
						<s:if test="#request.secrecyPersonList.size>0">
						<%-- <s:property value="request.secrecyPersonList.size"/> --%>
							<ec:table items="secrecyPersonList" var="secrecyPerson" tableId="secrecyPersonlist" border="0" action="${ctx}/bmp/secrecyPersonSearch/singelSelect.action"	imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif" width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit" filterable="false" autoIncludeParameters="true" sortable="false">
								<ec:row>
									<ec:column property="userInfo.userInfoId" alias="选择" cell="radio"   width="5%"/>
									<ec:column property="NULL" title="姓名" width="8%" >
										${secrecyPerson.userInfo.name }
										<input type="hidden" name="name_${secrecyPerson.userInfo.userInfoId }" id="name_${secrecyPerson.userInfo.userInfoId }" value="${secrecyPerson.userInfo.name }" />
									</ec:column>
									<%-- <ec:column property="null" title="民族" width="5%" >
										<c:if test="${not empty secrecyPerson.userInfo and not empty secrecyPerson.userInfo.nation}" >
											<dictionary:text tableCode="person" fieldCode="nation" optionValue="${secrecyPerson.userInfo.nation}"></dictionary:text>
										</c:if>
									</ec:column> --%>
									<ec:column property="department.departmentName" title="部门名称" width="8%" />
								</ec:row>
							</ec:table>
						</s:if>
						<s:else>
							<u:noData text="暂无数据"/>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>