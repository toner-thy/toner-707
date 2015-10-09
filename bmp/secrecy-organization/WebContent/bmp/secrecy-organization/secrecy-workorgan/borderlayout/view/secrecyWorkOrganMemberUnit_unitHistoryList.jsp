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
		<title>人员变动列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
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

			// 查看详情
			function doDetail(id){
				$ENV.dialog.open({
					title : '人员变更历史详情',
					url : '${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrganMemberUnit_unitChangedList.action?secrecyWorkOrganRelationMember.secrecyWorkOrganRelationId='+id+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
			}
		</script>
		<style type="text/css">
			html,body{
				padding: 0px;
			}

		</style>

	</head>

	<body>
		<div class="tab_panel_content">
			<!-- 内容panel开始 -->
			<div class="panel_content">
				<div class="panel tMargin">
					<s:if test="#request.secrecyWorkOrganMemberUnitHistoryList.size>0">
						<ec:table items="secrecyWorkOrganMemberUnitHistoryList" var="unitMemberChange" tableId="memberUnitHistoryList" border="0"
							action="${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrganMemberUnit_unitHistoryList.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="rowCount" cell="rowCount" sortable="false" title="序号" width="5%"/>
								<ec:column property="secrecyWorkOrganMembersId.person.name" title="成员姓名" width="10%" />
								<ec:column property="secrecyWorkOrganMembersId.person.department.departmentName" title="部 门" width="20%" cell="text"/>
								<ec:column property="secrecyWorkOrganMembersId.person.duty" title="行政职务" width="20%" cell="text"/>
								<ec:column property="null" title="变动事由" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="personnel_changes" optionValue="${unitMemberChange.changeType }"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="办公室电话" width="10%">
									<c:if test="${empty unitMemberChange.secrecyWorkOrganMembersId.person.phone}">
										暂无
									</c:if>
									<c:if test="${not empty unitMemberChange.secrecyWorkOrganMembersId.person.phone}">
										${unitMemberChange.secrecyWorkOrganMembersId.person.phone}
									</c:if>
								</ec:column>
								<ec:column property="null" title="变动详情" width="10%">
									<a href="###" onclick="doDetail('${unitMemberChange.secrecyWorkOrganMembersId.secrecyWorkUnitMemberId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
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