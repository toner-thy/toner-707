<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary"
	prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/district" prefix="dis"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>泄密案件列表</title>

<link href="${ctx}/platform/css/public/table/complexTbSustain.css"
	type="text/css" rel="stylesheet" />
<script src="${ctx}/resources/js/environment/environment.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$ENV.loader
			.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
	$ENV.loader
			.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
	$ENV.loader
			.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
	$ENV.loader
			.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
	$ENV.loader
			.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
	$ENV.loader
			.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
	$ENV.loader
			.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",
			function() {
				$ENV.onDomReady(function() {

				});
			});
	//查看泄密案件 的密级变更的明细
	function doDetail(id) {
		$ENV.dialog.open({
			url : "${ctx}/bmp/discloseSecrecy/discloseSecrecyDetail.action?discloseSecrecy.disclosesecrecycaseId="
					+ id + "&_ts=" + new Date().getTime(),
			width : 0.8,
			height : 0.6,
			title : '泄密案件详情'
		});
	}
</script>
</head>

<body>


	<s:if
		test="#request.retrun!=null&&#request.retrun!=''">
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button"
						href="${ctx}/platform/stat/statFramework_organDetail.action?organId=${discloseSecrecy.createOrgan.organId}"
						id="dataflagfanhui"><span>返回</span></a>
				</div>
			</div>
			<div class="right"></div>
		</div>
	</s:if>

	<div id="body_content" class="body_content">

		<!-- 复合面板开始 -->
		<cp:start defaultTitle="泄密案件简介" ctx="${ctx}"
			icoPath="/bmp/disclose_secrecy/borderlayout/skin/blue/img/08.gif">
			<div id="cp001Btn" class="cpBtn_ov" href="###"
				onmouseover="javascript:showCp(2,'cp001','泄密案件简介');">关 于</div>
			<div id="cp002Btn" class="cpBtn" href="###"
				onmouseover="javascript:showCp(2,'cp002','泄密案件搜索');">查 询</div>
		</cp:start>
		<cp:msg show="true" divId="cp001">
			<!-- 模块简介 -->
			<div class="cpMsgTitle">关于泄密案件</div>
			<div class="cpMsgContext">
				<cpc:tc ctx="${ctx}" showId="bm_discloseSecrecy">
				</cpc:tc>
			</div>

			<!-- 上下之间的间隔，可以调节高度 -->
			<div class="cpMsgFg" style="height: 7px; overflow: hidden;"></div>

		</cp:msg>
		<cp:search show="false" divId="cp002">
			<form
				action="${districtCode==null?'discloseSecrecy_list.action':'discloseSecrecy_selectListing2.action'}"
				id="discloseSecrecy_list_form" name="discloseSecrecy_list_form">
				<input type="hidden" value="${baomijuOrbendanwei}" name="baomiju" />
				<!-- 用于搜索区分本单位还是保密局 -->
				<input type="hidden" value="${districtCode}" name="districtCode" />
				<!-- 用于搜索区分保密局 -->
				<input type="hidden" value="${retrun}" name="retrun" />
				<input type="hidden" value="${includeChild}" name="includeChild" />
				<!-- 用于搜索区分保密局 -->
				<input type="hidden"
					value="${discloseSecrecy.createOrgan.organName}"
					name="discloseSecrecy.createOrgan.organName" />
				<!-- 用于搜索区分保密局 -->
				<input type="hidden" value="${discloseSecrecy.createOrgan.organId}"
					name="discloseSecrecy.createOrgan.organId" />
				<!-- 用于搜索区分保密局 -->
				<table class="st" width="100%">
					<tr>
						<td class="tbLable fr">名&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
						<td class="tbValue fl" width="30%"><input
							id="discloseSecrecy.name" name="discloseSecrecy.name" type="text"
							value="${discloseSecrecy.name}" /></td>
						<td class="tbLable fr" width="20%">查处结果：</td>
						<td class="tbValue fl" width="30%"><dictionary:select
								tableCode="bmp" fieldCode="find_result"
								id="discloseSecrecy.dealResult"
								name="discloseSecrecy.dealResult" style="width: 132px;"
								title="true" titleText="请选择"
								optionValue="${discloseSecrecy.dealResult}" /></td>

					</tr>


					<tr>
						<td class="tbLable fr" width="20%">密&nbsp;&nbsp;&nbsp;&nbsp;级：</td>
						<td class="tbValue fl" width="30%"><dictionary:select
								tableCode="bmp" fieldCode="secrecy_level_thing"
								id="discloseSecrecy.secrecyLevel"
								name="discloseSecrecy.secrecyLevel" style="width: 132px;"
								title="true" titleText="请选择"
								optionValue="${discloseSecrecy.secrecyLevel}" /></td>
					</tr>

					<tr>
						<td colspan="4" class="fc" style="border: 0px;">
							<div class="stBtnBar">
								<a class="pop_button"
									href="javascript:document.getElementById('discloseSecrecy_list_form').submit();"><span>查
										询</span></a> <a class="pop_button"
									href="javascript:document.getElementById('discloseSecrecy_list_form').reset();"><span>重
										置</span></a>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</cp:search>
		<cp:end>
		</cp:end>
		<!-- 复合面板结束 -->

		<!-- 内容panel开始 -->
		<div class="panel tMargin bMargin">
			<div class="panel_header">

				<div class="panel_title panel_titleListIco">
					<s:if
						test="discloseSecrecy.createOrgan.organId!=null&&discloseSecrecy.createOrgan.organId!=''">
				           【<s:property
							value="discloseSecrecy.createOrgan.organName" />】- 泄密案件列表
				       </s:if>
					<s:else>
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<s:if
							test="#request.districtCode==null||#request.districtCode==''">
							泄密案件列表
						</s:if>
						<s:else>
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${includeChild ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 泄密案件列表
							</c:if>
							<c:if test="${includeChild eq '1'}">
								${district.districtName} - 泄密案件列表
							</c:if>
						</s:else>
					</s:else>
				</div>

				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
				<!-- 这里自定义ectable -->
				<s:if test="#request.discloseSecrecyList.size>0">
					<ec:table items="discloseSecrecyList" var="discloseSecrecy"
						tableId="discloseSecrecyList" border="0"
						action="${districtCode==null?'${ctx}/bmp/discloseSecrecy/discloseSecrecy_list.action':'${ctx}/bmp/discloseSecrecy/discloseSecrecy_selectListing2.action' }"
						imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="disclosesecrecycaseId" width="3%"
								alias="disclosesecrecycaseId_checkbox" cell="checkbox"
								headerCell="checkbox" />
							<ec:column property="name" title="名称" width="14%"
								style="text-align: left;" cell="text" alias="size=15">
							</ec:column>
							<ec:column property="department.departmentName" title="部门名称"
								width="12%" style="text-align: left;">
							</ec:column>
							<ec:column property="null" title="密级" width="5%"
								style="text-align: left;">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing"
									optionValue="${discloseSecrecy.secrecyLevel}"></dictionary:text>
							</ec:column>
							<ec:column property="null" title="发案形式" width="30%"
								style="text-align: left;">
								<dictionary:text tableCode="bmp" fieldCode="case_Type"
									optionValue="${discloseSecrecy.caseType}"></dictionary:text>
							</ec:column>
							<ec:column property="null" title="责任单位性质" width="18%"
								style="text-align: left;">
								<dictionary:text tableCode="bmp" fieldCode="duty_organ_kind"
									optionValue="${discloseSecrecy.dutyOrganKind}"></dictionary:text>
							</ec:column>
							<ec:column property="null" title="案件性质" width="9%"
								style="text-align: left;">
								<dictionary:text tableCode="bmp" fieldCode="case_kind"
									optionValue="${discloseSecrecy.casekind}"></dictionary:text>
							</ec:column>
							<ec:column property="null" title="查处结果" width="6%"
								style="text-align: left;">
								<dictionary:text tableCode="bmp" fieldCode="find_result"
									optionValue="${discloseSecrecy.dealResult}"></dictionary:text>
							</ec:column>
							<ec:column property="null" title="详 情" width="3%">
								<a href="###"
									onclick="doDetail('${discloseSecrecy.disclosesecrecycaseId}');"><img
									src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif"
									border="0" title="显示详情" /></a>
							</ec:column>
						</ec:row>
					</ec:table>
				</s:if>
				<s:else>
					<u:noData text="当前暂无泄密案件信息" />
				</s:else>

			</div>
		</div>
	</div>
</body>
</html>