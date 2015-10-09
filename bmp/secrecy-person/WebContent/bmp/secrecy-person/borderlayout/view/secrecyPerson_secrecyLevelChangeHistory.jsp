<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密人员密级变更列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){
				});
			});
			function doDetail( secrecyPersonLevelChangeId ){
				$ENV.dialog.open({
					title : '涉密人员密级变更详情',
					url : '${ctx}/bmp/secrecyperson/secrecyPerson_secrecyLevelChangedetail.action?secrecyPersonLevelChange.secrecyPersonLevelChangeId='+secrecyPersonLevelChangeId+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
					}
				});
			}
		</script>
		<style type="text/css">
			.body_content{
				top:0px;
			}
		</style>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密人员简介" ctx="${ctx}" icoPath="/bmp/secrecy-person/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','涉密人员简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','涉密人员查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密人员
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_person"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

					<!-- 联系方式 -->
					<!-- <div class="cpMsgContactInfoTitle">
						业务指导
					</div>
					<div class="cpMsgContactInfoContext">
						联系人：四川省国家保密局督察处 XXX 联系电话：028-85229437
					</div> -->
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<s:form action="secrecyPerson_secrecyLevelChangeHistory.action" method="post" id="secrecyPersonLevelChangeForm" theme="simple">
						<input type="hidden" name="districtCode" id="districtCode" value="${districtCode }" />
						<input type="hidden" id="checkLower" name="checkLower" value="${checkLower}" />
						<input type="hidden" id="fromQuery" name="fromQuery" value="${fromQuery}" />
						<input type="hidden" id="departmentId" name="departmentId" value="${secrecyPersonLevelChange.secrecyPersonId.department.departmentId}"/>

						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">姓 名：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPersonLevelChange.secrecyPersonId.userInfo.name" name="secrecyPersonLevelChange.secrecyPersonId.userInfo.name" value="${secrecyPersonLevelChange.secrecyPersonId.userInfo.name}" />
								</td>
								<td class="tbLable fr"><!-- 审批人： -->&nbsp;</td>
								<td class="tbValue fl">
									<%-- <input type="text" id="secrecyPersonLevelChange.reviewPerson.name" name="secrecyPersonLevelChange.reviewPerson.name" value="${secrecyPersonLevelChange.reviewPerson.name }" /> --%>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">变更时间范围：</td>
								<td class="tbValue fl">
									<input type="text" id="changeDateStart" name="changeDateStart"  class="Wdate" value="<s:date name='#attr.changeDateStart' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td class="tbLable fm">--</td>
								<td class="tbValue fl">
									<input type="text" id="changeDateEnd" name="changeDateEnd"  class="Wdate" value="<s:date name='#attr.changeDateEnd' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('secrecyPersonLevelChangeForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('secrecyPersonLevelChangeForm').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<c:if test="${fromQuery ne '1'}">
							涉密人员密级变更列表
						</c:if>
						<c:if test="${fromQuery eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${checkLower ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 涉密人员密级变更列表
							</c:if>
							<c:if test="${checkLower eq '1'}">
								${district.districtName} - 涉密人员密级变更列表
							</c:if>
						</c:if>

					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyLevelChangeHistoryList.size>0">
						<ec:table items="secrecyLevelChangeHistoryList" var="secrecyLevelChange" tableId="secrecyLevelChangeHistoryTable" border="0"
							action="${ctx}/bmp/secrecyperson/secrecyPerson_secrecyLevelChangeHistory.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="rowCount" cell="rowCount" sortable="false" title="序号" width="5%"/>
								<ec:column property="secrecyPersonId.userInfo.name" title="姓 名" width="10%" cell="text"/>
								<ec:column property="null" title="变更前" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${secrecyLevelChange.beforeLevel}"/>
								</ec:column>
								<ec:column property="null" title="变更后" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${secrecyLevelChange.currentLevel}"/>
								</ec:column>
								<ec:column property="changeDate" title="变更时间" cell="date" format="yyyy-MM-dd" width="10%"/>
								<ec:column property="null" title="保密期限变更" width="10%" cell="text">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${secrecyLevelChange.changeTimeState}"/>
								</ec:column>
								<%-- <ec:column property="reviewPerson.name" title="审批人" width="10%" cell="text"/> --%>
								<ec:column property="null" title="变更原因" width="20%" cell="text">
									<c:choose>
										<c:when test="${fn:length(secrecyLevelChange.changeReason) > 15 }">
											${fn:substring(secrecyLevelChange.changeReason,0,12) }...
										</c:when>
										<c:otherwise>
											${secrecyLevelChange.changeReason }
										</c:otherwise>
									</c:choose>
								</ec:column>
							</ec:row>
							<ec:column property="null" title="详 情" width="10%">
								<a href="###" onclick="doDetail('${secrecyLevelChange.secrecyPersonLevelChangeId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
							</ec:column>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>