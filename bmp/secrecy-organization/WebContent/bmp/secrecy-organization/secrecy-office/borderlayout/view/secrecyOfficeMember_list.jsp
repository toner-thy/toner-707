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


			// 新增成员
			function addMember(id, districtCode){
				window.parent.location.href = "${ctx}/secrecyorganization/secrecyofficemember/secrecyOfficeMember_add.action?secrecyOffice.secrecyOfficeId="+id+"&district.districtCode=" + districtCode;
			}

			// 编辑成员
			function editMember(id, districtCode){
				var action = "${ctx}/secrecyorganization/secrecyofficemember/secrecyOfficeMember_edit.action";
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				window.parent.location.href = action + "?secrecyOfficeMember.secrecyOfficeMemberId=" + items[0].value + "&secrecyOffice.secrecyOfficeId="+id+"&district.districtCode=" + districtCode;
			}

			// 删除成员
			function deleteMember(id){
				$('secrecyOffice.secrecyOfficeId').value = id;
				var action = "${ctx}/secrecyorganization/secrecyofficemember/secrecyOfficeMember_delete.action";
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要删除的项。");
					return;
				}
				if(window.confirm("确定要删除吗？")){
					var ids = "";
					items.each(function(item){
						ids += item.value + ",";
					});
					document.getElementById("secrecyOfficeMemberIds").value = ids;
					document.getElementById("secrecyOfficeMembersDelForm").action = action;
					document.getElementById("secrecyOfficeMembersDelForm").submit();
				}
			}
			// 查看成员详情
			function doDetail(secrecyOfficeMemberId){
				$ENV.dialog.open({
					title : '保密办（保密局）成员详情',
					url : "${ctx}/secrecyorganization/secrecyofficemember/secrecyOfficeMember_detail.action?secrecyOfficeMember.secrecyOfficeMemberId=" + secrecyOfficeMemberId + "&_ts=" + $time(),
					width : window.top.getSize().x * 0.7,
					height : window.top.getSize().y * 0.8
				});
			}

			//密级变更
			function doSecrecyChange(id, districtCode){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请仅选择一项");
					return;
				}
				$ENV.dialog.open({
					url : "${ctx}/secrecyorganization/secrecyofficemember/secrecyOfficeMember_secrecyChange.action?secrecyOfficeMember.secrecyOfficeMemberId="+items[0].value
					 + "&secrecyOffice.secrecyOfficeId="+id+"&district.districtCode=" + districtCode + "&_ts="+new Date().getTime(),
					width : 0.5,
					height : 0.6,
					title : '密级变更'
				});
			}

			//脱密
			function doLeaveDuty(id, districtCode){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请仅选择一项");
					return;
				}
				$ENV.dialog.open({
					url : "${ctx}/secrecyorganization/secrecyofficemember/secrecyOfficeMember_decryption.action?secrecyOfficeMember.secrecyOfficeMemberId="+items[0].value
					 + "&secrecyOffice.secrecyOfficeId="+id+"&district.districtCode=" + districtCode + "&_ts="+new Date().getTime(),
					width : 0.5,
					height : 0.5,
					title : '脱密'
				});
			}

		</script>
		<style type="text/css">
			html, body {overflow: auto}
		</style>
	</head>
	<body>
		<div class="panel_content panel_content_table">
			<div class="panel tMargin">
				<s:if test="#attr.secrecyOfficeMemberList.size>0">
					<ec:table items="secrecyOfficeMemberList" var="m" tableId="secrecyOfficeMemberList" border="0"
						action="${ctx}/secrecyorganization/secrecyofficemember/secrecyOfficeMember_list.action"
						imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit" showPagination="true"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<c:if test="${isDetail eq 0}">
								<ec:column property="secrecyOfficeMemberId" alias="secrecyOfficeMemberId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="rowCount" cell="rowCount" sortable="false" title="序号" width="5%"/>
							</c:if>
							<c:if test="${isDetail eq 1}">
								<ec:column property="rowCount" cell="rowCount" sortable="false" title="序号" width="5%"/>
							</c:if>
							<ec:column property="person.department.departmentName" title="部 门" cell="text" alias="size=10" width="15%"/>
							<ec:column property="person.name" title="姓 名" cell="text" alias="size=10" width="15%"/>
							<ec:column property="person.duty" title="其他职务" cell="text" alias="size=10" width="15%"/>
							<ec:column property="officePhone" title="办公室电话" cell="text" width="15%"/>
							<ec:column property="remark" title="备 注" cell="text" alias="size=25" width="25%"/>
							<ec:column property="null" title="详 情" width="10%">
								<a href="###" onclick="doDetail('${m.secrecyOfficeMemberId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
							</ec:column>
						</ec:row>
					</ec:table>
				</s:if>
				<s:else>
					<u:noData text="当前暂无数据" />
				</s:else>

				<!-- 删除用隐藏表单 -->
				<form action="" method="post" id="secrecyOfficeMembersDelForm">
					<input type="hidden" name="secrecyOffice.secrecyOfficeId" id="secrecyOffice.secrecyOfficeId" value=""/>
					<input type="hidden" name="secrecyOfficeMemberIds" id="secrecyOfficeMemberIds"/>
				</form>
			</div>
		</div>
	</body>
</html>