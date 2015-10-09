<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密工作概况列表页</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
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

			function doDetail(flag, organId){
				window.parent.location.href = "${ctx}/bmp/secrecyworksummary/secrecyWorksummary_detail.action?flag=" + flag + "&organ.organId="+ organId+ "&district.districtCode="+ ${district.districtCode} +"&t_date=" + new Date().getTime();
			}

			//导出
			function doExport(){
				var organName = $("organ.organName").value;
				window.location.href = "${ctx}/bmp/secrecyworksummary/secrecyWorksummary_exportData.action?district.districtCode=${district.districtCode}" +
						"&organ.organName=" + encodeURI(organName)
						+"&t_date=" + new Date().getTime();
			}
		</script>
	</head>

	<body>
			<div class="button_bar">
				<div class="left">
					<div class="pop_button_bar">
					  	<c:if test="${ not empty list }">
							<a class="pop_button" href="###" onclick="doExport()"><span>导出</span></a>
					  	</c:if>
					</div>
				</div>
				<div class="right">
					<div class="pop_button_bar">
						<a class='pop_button' href='${ctx}/platform/help/help_clientInfo.do?help.helpId=secrecy_committee_help' target='_back'><span>帮 助</span></a>
						<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
						<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
					</div>
				</div>
			</div>

		<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密工作概况简介" ctx="${ctx}" icoPath="/bmp/secrecy-worksummary/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','保密工作概况简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','保密工作概况查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密工作概况
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_work_summary"> </cpc:tc>
					</div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<s:form action="secrecyWorksummary_list.action" method="post" id="queryform" theme="simple">
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">单位名称：</td>
								<td class="tbValue fl">
									<input type="text" id="organ.organName" name="organ.organName" value="${organ.organName }"/>
									<input type="hidden" id="district.districtCode" name="district.districtCode" value="${district.districtCode}"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('queryform').submit();"><span>查 询</span></a>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						【${district.districtName }】单位列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#attr.list.size>0">
						<ec:table items="list" var="m" tableId="list" border="0"
							action="${ctx}/bmp/secrecyworksummary/secrecyWorksummary_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="organName" title="单位名称" width="25%" />
								<ec:column property="null" title="保密机构成员" width="15%">
									<c:if test="${m.numPersonGroupMember == 0}">0</c:if>
									<c:if test="${m.numPersonGroupMember != 0}">
										<a href="###" onclick="doDetail('personGroupMember','${m.organId}')">${m.numPersonGroupMember}</a>
									</c:if>
								</ec:column>
								<ec:column property="null" title="保密办成员" width="15%">
									<c:if test="${m.numSecrecyWorkOrganMember == 0}">0</c:if>
									<c:if test="${m.numSecrecyWorkOrganMember != 0}">
										<a href="###" onclick="doDetail('secrecyWorkOrganMember','${m.organId}')">${m.numSecrecyWorkOrganMember}</a>
									</c:if>
								</ec:column>
								<ec:column property="null" title="保密要害部门" width="15%">
									<c:if test="${m.numKeysection == 0}">0</c:if>
									<c:if test="${m.numKeysection != 0}">
										<a href="###" onclick="doDetail('keysection','${m.organId}')">${m.numKeysection}</a>
									</c:if>
								</ec:column>
								<ec:column property="null" title="保密要害部位" width="15%">
									<c:if test="${m.numKeyPart == 0}">0</c:if>
									<c:if test="${m.numKeyPart != 0}">
										<a href="###" onclick="doDetail('keyPart','${m.organId}')">${m.numKeyPart}</a>
									</c:if>
								</ec:column>
								<ec:column property="null" title="涉密人员" width="15%">
									<c:if test="${m.numSecrecyPerson == 0}">0</c:if>
									<c:if test="${m.numSecrecyPerson != 0}">
										<a href="###" onclick="doDetail('secrecyPerson','${m.organId}')">
											${m.numSecrecyPerson}
										</a>
									</c:if>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无组织机构。"/>
					</s:else>
				</div>
			</div>
		</div>
 	</body>
</html>