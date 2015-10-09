<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密办（保密局）</title>

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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					new SimpleUI.SimpleTab({
						el : 'main_tab',
						onActive : function(item, content, index) {
						},
						onFirstActive : function(item, content, index) {
							new IFrame({
								src : content.get('url'),
								frameborder : 0,
								styles : {
									width : content.getSize().x,
									height: content.getSize().y
								},
								events : {
									load : function() {
									}
								}
							}).inject(content);
						}
					});
				});
			});

			function doAdd(){
				window.location.href="${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_add.action";
			}

			function doEdit(){
				window.location.href="${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_edit.action?secrecyOffice.secrecyOfficeId=${secrecyOffice.secrecyOfficeId}&district.districtCode=${district.districtCode}";
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

			// 上报
			function doReport(){
				if(window.confirm("确定上报吗？")){
					var action = "${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_report.action";
					window.location.href = action + '?secrecyOfficeIds=${secrecyOffice.secrecyOfficeId}&time=' + new Date().getTime();
				}
			}

			//导出
			function doExport(){
				<c:if test="${not empty secrecyOffice }">
					window.location.href = "${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_exportData.action?district.districtCode=${district.districtCode}&t_date=" + new Date().getTime();
				</c:if>
				<c:if test="${empty secrecyOffice }">
					alert("当前暂未录入数据，无法导出信息。");
				</c:if>
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<c:if test="${viewCondition eq 1}">
						<c:if test="${secrecyCommittee eq '1'}">
							<s:if test="#request.secrecyOffice==null">
								<a class="pop_button" href="###" onclick="doAdd()"><span>新 增</span></a>
							</s:if>
							<s:else>
								<a class="pop_button" href="###" onclick="doEdit()"><span>编 辑</span></a>
								<%-- <a class="pop_button" href="###" onclick="doReport()"><span>上 报</span></a> --%>
							</s:else>
						</c:if>
					</c:if>
					<a class="pop_button" href="###" onclick="doExport()"><span>导出</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class='pop_button' href='${ctx}/platform/help/help_clientInfo.do?help.helpId=secrecy_office_help' target='_back'><span>帮 助</span></a>
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密办（保密局）简介" ctx="${ctx}" icoPath="/bmp/secrecy-organization/secrecy-office/borderlayout/skin/blue/img/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密办（保密局）简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密办（保密局）
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ_secrecy_office"> </cpc:tc>
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
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 保密办（保密局）panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						中共${district.districtName }委保密委员会办公室（${district.districtName }国家保密局）
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyOffice!=null">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									单位名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<b>${secrecyOffice.name}</b>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主管单位：
								</td>
								<td class="tbValue fl">
									${secrecyOffice.mainOrgan.organName }
								</td>
								<td class="tbLable fr">
									成立时间：
								</td>
								<td class="tbValue fl">
									<fmt:formatDate pattern="yyyy-MM-dd" value="${secrecyOffice.establishTime }"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									行政级别：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="organ_admin_level" optionValue="${secrecyOffice.administrativeLevel}"></dictionary:text>
								</td>
								<td class="tbLable fr">
									经费来源：
								</td>
								<td class="tbValue fl">
									${secrecyOffice.fundsSource }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									是否政府序列：
								</td>
								<td class="tbValue fl">
									<c:if test="${secrecyOffice.govSequence eq 0}">否</c:if>
									<c:if test="${secrecyOffice.govSequence eq 1}">是</c:if>
								</td>
								<td class="tbLable fr">
									机构类别：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="organ_category" optionValue="${secrecyOffice.organType}"></dictionary:text>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主任(局长)：
								</td>
								<td class="tbValue fl">
									${secrecyOffice.director.name }
								</td>
								<td class="tbLable fr">
									办公室设在：
								</td>
								<td class="tbValue fl">
									${secrecyOffice.dept.departmentName }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									负责人：
								</td>
								<td class="tbValue fl">
									${secrecyOffice.person.name }
								</td>
								<td class="tbLable fr">
									联系电话：
								</td>
								<td class="tbValue fl">
									${secrecyOffice.telephone }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									传 真：
								</td>
								<td class="tbValue fl">
									${secrecyOffice.fax }
								</td>
								<td class="tbLable fr">
									邮 编：
								</td>
								<td class="tbValue fl">
									${secrecyOffice.postcode }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									地 址：
								</td>
								<td class="tbValue fl">
									${secrecyOffice.address }
								</td>
								<td class="tbLable fr">
									&nbsp;
								</td>
								<td class="tbValue fl">
									&nbsp;
								</td>
							</tr>
							<%-- <c:if test="${isShow}">
								<tr>
									<td class="tbLable fr">
										是否上报：
									</td>
									<td class="tbValue fl" colspan="3">
										<c:if test="${secrecyOffice.reportState == 0}"><span style="color:red">未上报</span></c:if>
										<c:if test="${secrecyOffice.reportState == 1}"><span style="color:green">已上报</span></c:if>
										<c:if test="${secrecyOffice.reportState == 2}"><span style="color:green">上报后修改</span></c:if>
									</td>
								</tr>
							</c:if> --%>
							<tr>
								<td class="tbLable fr">
									主要职能：
								</td>
								<td class="tbValue fl" colspan="5">
									<textarea style="width:99%; height:150px;" readonly="readonly">${secrecyOffice.dutyMemberWork }</textarea>
								</td>
							</tr>
						</table>
					</s:if>
					<s:else>
						<u:noData text="${message}"/>
					</s:else>
				</div>
			</div>
			<div class="split_line"></div>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						中共${district.districtName }委保密委员会办公室（${district.districtName }国家保密局）编制情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="secrecyOfficeMemberList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" colspan="4"  >编制构成情况</td>
								<td class="tableHeader" colspan="5"  >领导职数</td>
								<td class="tableHeader" colspan="4"  >聘用人员</td>
							</tr>
							<tr class="odd" >
								<td class="tableHeader">公务员</td>
								<td class="tableHeader">参照管理事业编制</td>
								<td class="tableHeader">全额拨款事业编制</td>
								<td class="tableHeader">自收自支事业编制</td>

								<td class="tableHeader">局长</td>
								<td class="tableHeader">副局长</td>
								<td class="tableHeader">巡视员</td>
								<td class="tableHeader">副巡视员</td>
								<td class="tableHeader">处（室、科）领导职数</td>

								<td class="tableHeader">领导岗位任职人员</td>
								<td class="tableHeader">专业技术人员</td>
								<td class="tableHeader">工勤人员</td>
								<td class="tableHeader">其他人员</td>

							</tr>
							<tr class="odd" >
								<c:forEach items="${secrecyOffice.establishSituationSet }" var="es">
									<td >${es.civil }</td>
									<td >${es.referMamager }</td>
									<td >${es.fullFunds }</td>
									<td >${es.selfFunds }</td>
								</c:forEach>
								<c:forEach items="${secrecyOffice.leaderStaffSet }" var="ls">
									<td >${ls.secretary }</td>
									<td >${ls.deputySecretary }</td>
									<td >${ls.inspector }</td>
									<td >${ls.deputyInspector }</td>
									<td >${ls.deptStaff }</td>
								</c:forEach>
								<c:forEach items="${secrecyOffice.employPersonSet }" var="ep">
									<td >${ep.managerStaff }</td>
									<td >${ep.specialtyStaff }</td>
									<td >${ep.workStaff }</td>
									<td >${ep.other }</td>
								</c:forEach>
								<c:if test="${empty secrecyOffice.establishSituationSet && empty secrecyOffice.leaderStaffSet && empty secrecyOffice.employPersonSet}">
									<td colspan="13"><u:noData text="当前暂无数据" /></td>
								</c:if>
							</tr>
						</table>
					</div>
				</div>
			</div>


			<div class="split_line"></div>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						中共${district.districtName }委保密委员会办公室（${district.districtName }国家保密局） 内设机构基本情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="secrecyOfficeMemberList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" rowspan="2">序号</td>
								<c:forEach items="${dictionaryOptionList}" var="dictionary" >
									<td class="tableHeader" colspan="4"  >${dictionary.optionText}</td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${dictionaryOptionList}" var="dictionary" >
									<td class="tableHeader">部门名称</td>
									<td class="tableHeader">编制人数</td>
									<td class="tableHeader">实有人数</td>
									<td class="tableHeader">行政级别</td>
								</c:forEach>
							</tr>
							<c:if test="${not empty list}">
								<c:forEach items="${list}" var="es" varStatus="stat">
									<tr <c:if test="${stat.index % 2 ==0}">class="odd"</c:if><c:if test="${stat.index % 2 != 0}">class="even"</c:if>>
										<c:forEach items="${dictionaryOptionList}" var="dictionary" >
											<c:set var="deptName" value="deptName${dictionary.optionValue}"/>
											<c:set var="workNum" value="workNum${dictionary.optionValue}"/>
											<c:set var="realNum" value="realNum${dictionary.optionValue}"/>
											<c:set var="adminLevel" value="adminLevel${dictionary.optionValue}"/>
											<c:if test="${dictionary.optionValue == 1}">
												<td>${stat.index + 1}</td>
											</c:if>
											<td title="${es[deptName] }">
												${fn:substring(es[deptName],0,30)}
											</td>
											<td>${es[workNum] }</td>
											<td>${es[realNum] }</td>
											<td>${es[adminLevel] }</td>
										</c:forEach>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty list}">
								<tr class="odd">
									<td colspan="13"><u:noData text="当前暂无数据" /></td>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
			</div>


			<div class="split_line"></div>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						中共${district.districtName }委保密委员会办公室（${district.districtName }国家保密局）基础设施建设
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="secrecyOfficeMemberList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<tr>
								<td class="tableHeader" align="center" width="5%">序号</td>
								<td class="tableHeader" align="center" width="25%">单位名称</td>
								<td class="tableHeader" align="center" width="10%">基础设施类别</td>
								<td class="tableHeader" align="center" width="20%">名称</td>
								<td class="tableHeader" align="center" width="20%">地点</td>
								<td class="tableHeader" align="center" width="10%">占地面积(平方米)</td>
								<td class="tableHeader" align="center" width="10%">投入资金(万元)</td>
							</tr>
							<c:if test="${not empty secrecyOffice.infrastructureSet}">
								<c:forEach items="${secrecyOffice.infrastructureSet}" var="ifs" varStatus="ifsStatus">
									<tr <c:if test="${ifsStatus.index % 2 ==0}">class="odd"</c:if><c:if test="${ifsStatus.index % 2 != 0}">class="even"</c:if>>
										<td>${ifsStatus.index + 1 }</td>
										<td>${ifs.organName }</td>
										<td>
											<dictionary:text tableCode="bmp" fieldCode="infrastructure" optionValue="${ifs.infrastructureType }"></dictionary:text>
										</td>
										<td>${ifs.name }</td>
										<td>${ifs.address }</td>
										<td>${ifs.area }</td>
										<td>${ifs.finance }</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty secrecyOffice.infrastructureSet}">
								<tr class="odd">
									<td colspan="12"><u:noData text="当前暂无数据" /></td>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
			</div>





			<div class="split_line"></div>
			<!-- 保密办（保密局）panel结束 -->
			<!-- 保密办（保密局）成员panel开始 -->
			<div id="main_tab" class="panel tab_panel">
				<div id="main_tab_header" class="panel_header">
					<div class="tab_bar">
						<div class="panel_title">${district.districtName }国家保密局成员列表</div>
						<div class="panel_title">${district.districtName }国家保密局成员密级变更信息列表</div>
						<div class="panel_title">${district.districtName }国家保密局成员脱密人员信息列表</div>
					</div>
					<div style="float: right;margin: 3px;">
						<div class=""></div>
						<div class=""></div>
						<div class=""></div>
					</div>
				</div>
				<div class="tab_panel_content" >
					<div class="panel_content" style="height: 500px;"
						url="${ctx }/secrecyorganization/secrecyofficemember/secrecyOfficeMember_list.action?district.districtCode=${district.districtCode}&isDetail=${isDetail}">
					</div>
					<div class="panel_content" style="height: 500px;"
						url="${ctx }/secrecyorganization/secrecyofficemember/secrecyOfficeMember_secrecyChangeList.action?district.districtCode=${district.districtCode}">
					</div>
					<div class="panel_content" style="height: 500px;"
						url="${ctx }/secrecyorganization/secrecyofficemember/secrecyOfficeMember_decryptionList.action?district.districtCode=${district.districtCode}">
					</div>
				</div>
			</div>

			<!-- 保密办（保密局）成员panel结束 -->
		</div>
	</body>
</html>