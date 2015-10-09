<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");

			// 查看详情
			function doDetail(id){
				$ENV.dialog.open({
					title : '涉密计算机详情',
					url : '${ctx}/bmp/secrecycomputer/secrecyComputer_detailChange.action?secrecyComputerChange.computerChangeId='+id+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
			}
		</script>

	</head>
	<body>
		<div class="body_content" >
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密计算机简介" ctx="${ctx}" icoPath="/bmp/secrecy-system/secrecy-computer/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','涉密计算机简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','涉密计算机查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密计算机
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_computer"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form action="<s:url action="secrecyComputer_secrecyChangeList.action" namespace="/bmp/secrecycomputer"/>" method="post" id="queryForm" theme="simple">
						<input type="hidden" id="checkLower" name="checkLower" value="${checkLower}" />
						<input type="hidden" id="fromQuery" name="fromQuery" value="${fromQuery}" />
						<input type="hidden" id="district.districtCode" name="district.districtCode" value="${district.districtCode}" />
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">责任人：</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" id="secrecyComputerChange.secrecyComputer.dutyPerson.name" name="secrecyComputerChange.secrecyComputer.dutyPerson.name" value="${secrecyComputerChange.secrecyComputer.dutyPerson.name}" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">原密级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyComputerChange.beforeLevel" name="secrecyComputerChange.beforeLevel"
									 title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyComputerChange.beforeLevel}"></dictionary:select>
								</td>
								<td class="tbLable fr">现密级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyComputerChange.afterLevel" name="secrecyComputerChange.afterLevel"
									 title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyComputerChange.afterLevel}"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('queryForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('queryForm').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<c:if test="${fromQuery eq '0'}">
							涉密计算机密级变更列表
						</c:if>
						<c:if test="${fromQuery eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${checkLower ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 涉密计算机密级并更列表
							</c:if>
							<c:if test="${checkLower eq '1'}">
								${district.districtName} - 涉密计算机密级并更列表
							</c:if>
						</c:if>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#attr.secrecyComputerChangeList.size>0">
						<ec:table items="secrecyComputerChangeList" var="m" tableId="secrecyComputerChangeList" border="0"
							action="${ctx}/bmp/secrecycomputer/secrecyComputer_secrecyChangeList.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit" showPagination="true"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="rowCount" cell="rowCount" sortable="false" title="序号" width="5%"/>
								<ec:column property="secrecyComputer.department.departmentName" title="部 门" cell="text" alias="size=10" width="15%"/>
								<ec:column property="secrecyComputer.dutyPerson.name" title="责任人" cell="text" alias="size=10" width="10%"/>
								<ec:column property="secrecyComputer.diskSeq" title="硬盘序列号" cell="text" alias="size=10" width="15%"/>
								<ec:column property="null" title="原密级" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${m.beforeLevel }"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="现密级" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${m.afterLevel }"></dictionary:text>
								</ec:column>
								<ec:column property="changeDate" title="变更时间" cell="date" format="yyyy-MM-dd" width="10%"/>
								<ec:column property="changeReason" title="变更原因" cell="text" alias="size=20" width="30%"/>
								<ec:column property="null"  width="5%" title="详 情">
									<a href="###" onclick="doDetail('${m.computerChangeId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" alt ="详情" >
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无涉密计算机"/>
					</s:else>
				</div>
			</div>
		</div>
	</body>
</html>