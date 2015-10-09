<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部位列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<s:actionmessage theme="messages"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){
					new FormCheck('queryform',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});


			// 详 情
			function doDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/bmp/part/part_detail.action?part.partId='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '要害部位详情'
				});
			}

			function back(){
				window.location.href = "${ctx}/bmp/secrecyworksummary/secrecyWorksummary_main.action";
			}

			// 打印
			function doPrint(){
				$ENV.dialog.open({
					url : '${ctx}/demo/stat/detail03_print.jsp',
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '保密要害部门登记表打印'
				});
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="but_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick="javascript:back();"><span>返 回</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();" title="请确认是否已清空查询条件，否则查询结果有误差"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
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
					<s:form action="secrecyWorksummary_detail.action" method="post" id="queryform" theme="simple">
						<!-- 隐藏域 -->
						<input type="hidden" id="flag" name="flag" value="keyPart"/>
						<input type="hidden" id="organ.organId" name="organ.organId" value="${organ.organId}"/>

						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									部位名称：
								</td>
								<td class="tbValue fl">
									<input type="text" name="part.partName" value="${part.partName }" />
								</td>
								<td class="tbLable fr">
									涉密等级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}" title="true" name="part.secrecyLevel" titleText="全部" style="width:130px;"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									负责人：
								</td>
								<td class="tbValue fl">
									<input type="text" id="part.person.name" name="part.person.name" value="${part.person.name}" />
								</td>
								<td class="tbLable fr">
									联系电话：
								</td>
								<td class="tbValue fl">
									<input type="text" name="part.phone" value="${part.phone}"/>
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
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						【${secrecyWorkOrgan.organ.organName}】保密要害部位列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.partList.size>0">
						<ec:table items="partList" var="part" tableId="partList" border="0"
							action="${ctx}/bmp/secrecyworksummary/secrecyWorksummary_detail.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="partId" alias="partId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="partName" title="名 称" width="20%" cell="text" alias="size=8"/>
								<ec:column property="department.departmentName" title="主管部门" width="20%" cell="text" alias="size=8"/>
								<ec:column property="null" title="涉密等级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}"></dictionary:text>
								</ec:column>
								<ec:column property="person.name" title="负责人" width="20%" cell="text" alias="size=10"/>
								<ec:column property="phone" title="联系电话" width="20%" cell="text" alias="size=12"/>
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetail('${part.partId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无要害部位"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>