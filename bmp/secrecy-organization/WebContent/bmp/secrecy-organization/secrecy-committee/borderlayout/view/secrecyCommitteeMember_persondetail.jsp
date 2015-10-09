<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密委成员</title>

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

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
				});
			});

			// 返回方法
			function doBackList(){
				$ENV.dialog.close();
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<%-- <a class="pop_button" href="javascript:doBackList();"><span>返回列表</span></a> --%>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:$ENV.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密委成员信息
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="edit_form" action="<s:url namespace='/secrecyorganization/secrecycommitteemember' action='secrecyCommitteeMember_editing.action' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyCommitteeMember.secrecyCommitteeMemberId" value="${secrecyCommitteeMember.secrecyCommitteeMemberId}"/>

						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									保密委名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<b>${secrecyCommitteeMember.secrecyCommittee.name}</b>
								</td>
							<tr/>
							<tr>
								<td class="tbLable fr">
									保密委员会职务：
								</td>
								<td class="tbValue fl">
									<c:forEach items="${personGroupPositionList}" var="pgp">
										<c:if test="${pgp.personGroupPosition==secrecyCommitteeMember.personGroupPosition.personGroupPosition}"> ${pgp.positionName}</c:if>
									</c:forEach>
								</td>
								<td class="tbLable fr">
									姓 名：
								</td>
								<td class="tbValue fl">
									${secrecyCommitteeMember.person }
								</td>
							<tr>
							<tr>
								<td class="tbLable fr">性别：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="sex" optionValue="${secrecyCommitteeMember.sex }"></dictionary:text>
								</td>
								<td class="tbLable fr">出生年月：</td>
								<td class="tbValue fl">
									<s:date name='#attr.secrecyCommitteeMember.birthdate' format='yyyy-MM-dd'/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">民族：</td>
								<td class="tbValue fl">
									<!-- demo 以后还需要在民族的字典表里添加‘其他’ -->
									<dictionary:text tableCode="person" fieldCode="nation" optionValue="${secrecyCommitteeMember.nation }"/>
								</td>
								<td class="tbLable fr">学历：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="learning_level" optionValue="${secrecyCommitteeMember.educationBackground }"></dictionary:text>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">学位：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="degree" optionValue="${secrecyCommitteeMember.degree }"></dictionary:text>
								</td>
								<td class="tbLable fr">政治面貌：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="polity" optionValue="${secrecyCommitteeMember.politicalStatus }"></dictionary:text>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">专业：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="specialty_code" optionValue="${secrecyCommitteeMember.major }"></dictionary:text>
								</td>
								<td class="tbLable fr">
									行政职务：
								</td>
								<td class="tbValue fl">
									${secrecyCommitteeMember.adminPost}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									技术职称：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="technic_title_level" optionValue="${secrecyCommitteeMember.technicalTitle }"></dictionary:text>
								</td>
								<td class="tbLable fr">行政级别：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="person_admin_level" optionValue="${secrecyCommitteeMember.administrativeLevel }"></dictionary:text>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									任命单位：
								</td>
								<td class="tbValue fl">
									${secrecyCommitteeMember.organName }
								</td>
								<td class="tbLable fr">
									办公室电话：
								</td>
								<td class="tbValue fl">
									${secrecyCommitteeMember.personPhone}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									${secrecyCommitteeMember.departmentName }
								</td>
								<td class="tbLable fr">
									何时从事保密工作：
								</td>
								<td class="tbValue fl">
									<s:date name='#attr.secrecyCommitteeMember.secrecyWorkStart' format='yyyy-MM-dd'/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									是否专职：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="sole_duty" optionValue="${secrecyCommitteeMember.isSoleDuty }"></dictionary:text>
								</td>
								<td class="tbLable fr">
									人员排序：
								</td>
								<td class="tbValue fl">
									${secrecyCommitteeMember.sort}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">兼任其他职务情况：</td>
								<td class="tbValue fl" colspan="3">
									<textarea style="width: 90%;height: 100px;" readonly="readonly">${secrecyCommitteeMember.otherDuty }</textarea>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									备 注：
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea rows="" cols="" style="width: 90%;height: 150px;" readonly="readonly">${secrecyCommitteeMember.remark}</textarea>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

	</body>
</html>