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
		<title>涉密人员列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
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
			function doDetail(secrecyPersonId){
				$ENV.dialog.open({
					title : '涉密人员详情',
					url : '${ctx}/bmp/secrecyperson/secrecyPerson_detail.action?secrecyPerson.secrecyPersonId='+secrecyPersonId+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
			}
			function back(){
				window.location.href = "${ctx}/bmp/secrecyworksummary/secrecyWorksummary_main.action?district.districtCode="+${district.districtCode};
			}
			//导出
			function doExport(){
				window.location.href = "${ctx}/bmp/secrecyworksummary/secrecyWorksummary_exportDataDetail.action?district.districtCode=${district.districtCode}&flag=secrecyPerson" +
						"&organ.organId=${organ.organId}"
						+"&t_date=" + new Date().getTime();
			}

		</script>

		<s:actionmessage theme="messages"/>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick="javascript:back();"><span>返 回</span></a>
					<a class="pop_button" href="###" onclick="javascript:doExport();"><span>导出</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

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
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<s:form action="secrecyWorksummary_detail.action" method="post" id="secrecyPersonForm" theme="simple">
						<!-- 隐藏域 -->
						<input type="hidden" id="flag" name="flag" value="secrecyPerson"/>
						<input type="hidden" id="organ.organId" name="organ.organId" value="${organ.organId}"/>
						<input type="hidden" id="district.districtCode" name="district.districtCode" value="${district.districtCode }"/>

						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">姓 名：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.userInfo.name" name="secrecyPerson.userInfo.name" value="${secrecyPerson.userInfo.name}" />
								</td>
								<td class="tbLable fr">涉密等级：</td>
								<td class="tbValue fl">
									<select id="secrecyPerson_secrecyPersonLevel" name="secrecyPerson.secrecyPersonLevel" style="width: 129px;">
										<option value="4">全部</option>
										<option value="3">一般</option>
										<option value="2">重要</option>
										<option value="1">核心</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">部 门：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.department.departmentName" name="secrecyPerson.department.departmentName" value="${secrecyPerson.department.departmentName}" />
								</td>
								<td class="tbLable fr">职 务：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.officeDuty" name="secrecyPerson.officeDuty" value="${secrecyPerson.officeDuty}" />
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('secrecyPersonForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('secrecyPersonForm').reset();"><span>重 置</span></a>
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
						【${organ.organName}】涉密人员列表
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyPersonList.size>0">
						<ec:table items="secrecyPersonList" var="secrecyPerson" tableId="secrecyPersonList" border="0"
							action="${ctx}/bmp/secrecyworksummary/secrecyWorksummary_detail.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secrecyPersonId" alias="secrecyPersonId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="userInfo.name" title="姓 名" width="20%" cell="text"/>
								<ec:column property="null" title="涉密等级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${secrecyPerson.secrecyPersonLevel}"/>
								</ec:column>
								<ec:column property="department.departmentName" title="部 门" width="20%" cell="text"/>
								<ec:column property="officeDuty" title="职 务" width="20%" cell="text"/>
								<ec:column property="null" title="办公室电话" width="20%" cell="text">
									<c:if test="${secrecyPerson.officePhone == ''}">
										暂无
									</c:if>
									<c:if test="${secrecyPerson.officePhone != ''}">
										${secrecyPerson.officePhone}
									</c:if>
								</ec:column>
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetail('${secrecyPerson.secrecyPersonId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无涉密人员，请点击【新增】按钮添加。"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>