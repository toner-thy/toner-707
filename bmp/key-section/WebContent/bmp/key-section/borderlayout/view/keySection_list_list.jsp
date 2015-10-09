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
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",function(){
				$ENV.onDomReady(function(){
				});
			});

			// 选择
			function selectOne(action){
				var value = EcTable.getRadioItem();

				var arg = window.getParams();
				if (arg.select) {
					arg.select({
						text :  $('name_' + value).value,
						value : value
					});
				} else {
					var dc = window.getOpener().document;
					dc.getElementById(arg.hidden).value = value;
					dc.getElementById(arg.text).value = $('name_' + value).value;
				}
				window.close();
			}

			//取消选择
			function cancelAll() {
				var values = EcTable.getAllRadioItems();
				for(var i=0; i<values.length; i++) {
					values[i].checked=false;
				}
			}

			//关闭选项框
			function colseWin() {
				window.close();
			}
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
					<a class="pop_button" href="javascript:selectOne();" id="sbm_button_hidden" ><span>选择</span></a>
					<%-- <a class="pop_button" href="javascript:cancelAll();" id="sbm_button"><span>取消</span></a> --%>
					<a class="pop_button" href="javascript:colseWin()"><span>关闭</span></a>
				</div>
			</div>
			<div class="right"></div>
		</div>

		<div class="body_content" style="overflow: hidden;">
		    <!-- 保密要害部门 列表 -->
			<div class="panel_content">
				<!-- 复合面板开始 -->
				<cp:start defaultTitle="保密要害部门简介" ctx="${ctx}" icoPath="/bmp/key-section/borderlayout/skin/blue/img/list_cpIco.gif">
					<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','保密要害部门查询');">查 询</div>
				</cp:start>
				<cp:search show="true" divId="cp002">
					<form action="<s:url action="singelSelect" namespace="/bmp/keySectionSearch"/>" method="post" id="keySection_query_form" >
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									保密要害部门：
								</td>
								<td class="tbValue fl">
									<input type="text" name="keySectionQo.departmentName" id="keySectionQo.departmentName" value="${keySectionQo.departmentName}">
								</td>
								<td class="tbLable fr">
									涉密等级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_section" id="keySectionQo.secrecyLevel" name="keySectionQo.secrecyLevel" title="true"  titleText="全部" style="width:130px;"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									负责人：
								</td>
								<td class="tbValue fl">
									<input type="text" name="keySectionQo.principal" id="keySectionQo.principal" value="${keySectionQo.principal}" />
								</td>
								<td class="tbLable fr">
									联系电话：
								</td>
								<td class="tbValue fl">
									<input type="text" name="keySectionQo.phone" id="keySectionQo.phone" value="${keySectionQo.phone }">
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('keySection_query_form').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('keySection_query_form').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</cp:search>
				<cp:end> </cp:end>
				<!-- 复合面板结束 -->

				<!-- 内容panel开始 -->
				<div class="panel tMargin">

					<div class="panel_content panel_content_table">
						<s:if test="#request.keySectionlist.size>0"><s:property value="request.keySectionList1.size"/>
							<ec:table items="keySectionlist" var="keySection" tableId="keySectionlist" border="0"
								action="${ctx}/bmp/keySectionSearch/singelSelect.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<ec:row>
									<ec:column property="keySectionId" alias="选择" cell="radio"   width="5%"/>
									<ec:column property="department.departmentName" title="保密要害部门" width="20%" cell="text" alias="size=20"/>
									<ec:column property="null" title="涉密等级" width="10%">
										<input type="hidden" name="name_${keySection.keySectionId}" id="name_${keySection.keySectionId}" value="${keySection.department.departmentName}" />
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_section" optionValue="${keySection.secrecyLevel}"/>
										<input type="hidden" name="${keySection.keySectionId}_reportState" id="${keySection.keySectionId}_reportState" value="${keySection.reportState}">
									</ec:column>
									<ec:column property="phone" title="联系电话" width="10%" cell="text" alias="size=18"/>
								</ec:row>
							</ec:table>
						</s:if>
						<s:else>
							<u:noData text="没有发现保密要害部门"/>
						</s:else>
					</div>

				</div>
			</div>

		</div>

	</body>
</html>