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
		<title>选择保密要害部位的列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
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
						"text" : $('name_' + value).value,
						"value" : value
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
			.body_content{
				top:0px;
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

		<!-- 保密要害部位列表  -->
		<div class="panel_content" style="overflow: auto;" >
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密要害部位简介" ctx="${ctx}" icoPath="/bmp/keypart/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','保密要害部位简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','保密要害部位搜索');">查 询</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于保密要害部位
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_key_part"> </cpc:tc>
				</div>

				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
			</cp:msg>
			<cp:search show="false" divId="cp002">
				<form action="<s:url action="singelSelect" namespace="/bmp/keyPartSearch" />" method="post" id="queryform">
					<input type="hidden" name="nestedflag" id="nestedflag" value="${nestedflag}" />
					<input type="hidden" name="part.department.departmentId" id="part.department.departmentId" value="${part.department.departmentId}" />

					<table class="st" width="100%">
						<tr>
							<td class="tbLable fr">
								部位名称：
							</td>
							<td class="tbValue fl">
								<input type="text" name="part.partName" id="part.partName" value="${part.partName }" />
							</td>
							<td class="tbLable fr">
								涉密等级：
							</td>
							<td class="tbValue fl">
								<dictionary:select tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}" title="true" name="part.secrecyLevel" id="part.secrecyLevel" titleText="全部" style="width:130px;"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								负责人：
							</td>
							<td class="tbValue fl">
								<input type="text" id="part.person.name" name="part.person.name" id="part.person.name" value="${part.person.name}" />
							</td>
							<td class="tbLable fr">
								联系电话：
							</td>
							<td class="tbValue fl">
								<input type="text" name="part.phone" id="part.phone" value="${part.phone}"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="fc" style="border: 0px;">
								<div class="stBtnBar">
									<a class="pop_button" href="javascript:document.getElementById('queryform').submit();"><span>查 询</span></a>
									<a class="pop_button" href="javascript:document.getElementById('queryform').reset();"><span>重 置</span></a>
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
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部位列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.partList.size>0">
						<ec:table items="partList" var="part" tableId="partList" border="0"
							action="${ctx}/bmp/keyPartSearch/singelSelect.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="partId" alias="选择" cell="radio"   width="5%"/>
								<ec:column property="partName" title="要害部位名称" width="20%" cell="text" alias="size=20"/>
								<ec:column property="department.departmentName" title="主管部门" width="20%" cell="text" alias="size=20"/>
								<ec:column property="null" title="涉密等级" width="10%">
									<input type="hidden" name="name_${part.partId}" id="name_${part.partId}" value="${part.partName}" />
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}"></dictionary:text>
									<input type="hidden" name="${part.partId}_reportState" id="${part.partId}_reportState" value="${part.reportState}">
								</ec:column>
								<ec:column property="person.name" title="负责人" width="15%" cell="text" alias="size=25"/>
								<ec:column property="phone" title="联系电话" width="10%" cell="text" alias="size=12"/>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="没有发现要害部位。"/>
					</s:else>
				</div>

			</div>
			<!-- 内容panel结束 -->
		</div>
</body>
