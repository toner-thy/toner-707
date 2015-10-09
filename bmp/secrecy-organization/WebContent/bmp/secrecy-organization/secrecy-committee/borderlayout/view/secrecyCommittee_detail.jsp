<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密委员会</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

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
					var needReload = ${needReload};
					var needReload2 = false;

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
									height: 400
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
				window.location.href="${ctx}/secrecyorganization/secrecycommittee/secrecyCommittee_add.action";
			}

			function doEdit(){
				window.location.href="${ctx}/secrecyorganization/secrecycommittee/secrecyCommittee_edit.action?secrecyCommittee.secrecyCommitteeId=${secrecyCommittee.secrecyCommitteeId}";
			}

			// 新增成员
			function addMember(){
				window.location.href="${ctx}/secrecyorganization/secrecycommitteemember/secrecyCommitteeMember_add.action?secrecyCommittee.secrecyCommitteeId=${secrecyCommittee.secrecyCommitteeId}";
			}

			// 上报
			function doReport(){
				if(window.confirm("确定上报吗？")){
					var action = "${ctx}/secrecyorganization/secrecycommittee/secrecyCommittee_report.action";
					window.location.href = action + '?secrecyCommitteeIds=${secrecyCommittee.secrecyCommitteeId}&time=' + new Date().getTime();
				}
			}

			//导出
			function doExport(){
				<c:if test="${not empty secrecyCommittee }">
					window.location.href = "${ctx}/secrecyorganization/secrecycommittee/secrecyCommittee_exportData.action?district.districtCode=${district.districtCode}&t_date=" + new Date().getTime();
				</c:if>
				<c:if test="${empty secrecyCommittee }">
					alert("当前暂未录入数据，无法导出信息。");
				</c:if>
			}
			function doDetail(id){
				$ENV.dialog.open({
					title : '保密委员会成员详情',
					url : '${ctx}/secrecyorganization/secrecycommitteemember/secrecyCommitteeMember_persondetail.action?secrecyCommitteeMember.secrecyCommitteeMemberId='+ id + '&t_date=' + new Date().getTime(),
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
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<c:if test="${viewCondition eq 1}">
					<!-- 1当前单位行政区划与传入进来的行政区划相等，可以操作 -->
					<!-- 0当前单位行政区划与传入进来的行政区划不相等，只能查询 -->
						<s:if test="#request.secrecyCommittee==null">
							<a class="pop_button" href="###" onclick="doAdd()"><span>新 增</span></a>
						</s:if>
						<s:else>
							<a class="pop_button" href="###" onclick="doEdit()"><span>编 辑</span></a>
							<%-- <a class="pop_button" href="###" onclick="doReport()"><span>上 报</span></a> --%>
						</s:else>
					</c:if>
						<a class="pop_button" href="###" onclick="doExport()"><span>导出</span></a>
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
			<cp:start defaultTitle="保密委员会简介" ctx="${ctx}" icoPath="/bmp/secrecy-organization/secrecy-committee/borderlayout/skin/blue/img/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密委员会简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密委员会
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ_secrecy_committee"> </cpc:tc>
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

			<!-- 保密委员会panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						中共${district.districtName }委保密委员会
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#attr.secrecyCommittee!=null">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									保密委名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<b>${secrecyCommittee.name}</b>
								</td>
							<tr/>
							<tr>
								<td class="tbLable fr">
									发文号：
								</td>
								<td class="tbValue fl">
									${secrecyCommittee.docNo}
								</td>
								<td class="tbLable fr">
									成立(发文)日期：
								</td>
								<td class="tbValue fl">
									<s:date name='#attr.secrecyCommittee.setupDate' format='yyyy年MM月dd日'/>
								</td>
							<tr>
							<tr>
								<td class="tbLable fr">
									发文上传：
								</td>
								<td class="tbValue fl" colspan="3">
									<attach:view attachments="${attachmentList}" uploadBehavior="secrecyCommittee.uploadBehavior" showTitle="false"/>
								</td>
							</tr>
							<%-- <c:if test="${isShow}">
								<tr>
									<td class="tbLable fr">
										是否上报：
									</td>
									<td class="tbValue fl" colspan="3">
										<c:if test="${secrecyCommittee.reportState == 0}"><span style="color:red">未上报</span></c:if>
										<c:if test="${secrecyCommittee.reportState == 1}"><span style="color:green">已上报</span></c:if>
										<c:if test="${secrecyCommittee.reportState == 2}"><span style="color:green">上报后修改</span></c:if>
									</td>
								</tr>
							</c:if> --%>
							<tr>
								<td class="tbLable fr">
									主要职能：
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea style="width: 100%;height: 150px;" readonly="readonly">${secrecyCommittee.dutyMemberWork}</textarea>
								</td>
							</tr>
						</table>
					</s:if>
					<s:else>
						<u:noData text="当前尚未填写保密委员会，请点击【新增】按钮添加。"/>
					</s:else>
				</div>
			</div>
			<!-- 保密委员会panel结束 -->

			<!-- 保密委成员panel开始 -->
			<div id="main_tab" class="panel tab_panel" style="height: 500px; overflow: auto;">
				<div id="main_tab_header" class="panel_header">
					<div class="tab_bar">
						<div class="panel_title">现任保密委员会成员列表</div>
						<div class="panel_title">历史保密委员会成员列表</div>
					</div>
					<div style="float: right;margin: 3px;">
						<div class="">
						</div>
						<div class=""></div>
					</div>
				</div>
				<div class="tab_panel_content">
					<div class="panel_content" style="height:400px;overflow: auto;">
						<%-- <s:if test="#attr.secrecyCommittee.secrecyCommitteeMembers.size>0"> --%>
						<s:if test="#attr.secrecyCommitteeMemberList.size>0">
							<div class="panel tMargin">
								<%-- <div class="panel_header">
									<div class="panel_title panel_titleListIco">
										中共${district.districtName }委保密委员会成员列表
									</div>
									<div class="panel_btn_bar pop_button_bar">
									</div>
								</div> --%>
								<div class="">
									<ec:table items="secrecyCommitteeMemberList" var="secrecyCommitteeMember" tableId="secrecyCommitteeMemberList" border="0"
										action="${ctx}/secrecyorganization/secrecycommittee/secrecyCommittee_detail.action"
										imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
										width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
										filterable="false" autoIncludeParameters="true" sortable="false">
										<ec:row>
											<ec:column property="rowCount" cell="rowCount" sortable="false" title="序号" width="5%"/>
											<ec:column property="person" title="姓 名" width="10%" cell="text" alias="size=10"/>
											<ec:column property="organName" title="单 位" width="15%" cell="text" alias="size=20"/>
											<ec:column property="personGroupPosition.positionName" title="保密委员会职务" width="10%" cell="text" alias="size=10"/>
											<ec:column property="adminPost" title="行政职务" width="10%" cell="text" alias="size=10"/>
											<ec:column property="personPhone" title="办公室电话" width="15%" cell="text"/>
											<ec:column property="remark" title="备 注" width="20%" alias="size=10"/>
											<ec:column property="null" title="明细" width="15%">
												<a href="###" onclick="doDetail('${secrecyCommitteeMember.secrecyCommitteeMemberId}')">
													<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/>
												</a>
											</ec:column>
										</ec:row>
									</ec:table>
								</div>
							</div>
						</s:if>
						<s:else>
							<u:noData text="暂无数据"/>
						</s:else>
					</div>
					<div class="panel_content" style="overflow: auto;"
						url="${ctx }/secrecyorganization/secrecycommitteemember/secrecyCommitteeMember_historyList.action?secrecyCommittee.secrecyCommitteeId=${secrecyCommittee.secrecyCommitteeId}&district.districtCode=${district.districtCode}" >
					</div>
				</div>
			</div>

			<!-- 保密委成员panel结束 -->

		</div>
	</body>
</html>