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
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑保密委员会</title>

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
					formcheck = new FormCheck('edit_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					$('secrecyCommitteeListId').focus();

					new SimpleUI.SimpleTab({
						el : 'main_tab',
						onActive : function(item, content, index) {
						},
						onFirstActive : function(item, content, index) {
							if( content.get('url')!="" ){
								new IFrame({
									src : content.get('url'),
									frameborder : 0,
									styles : {
										width : content.getSize().x,
										height: 500
									},
									events : {
										load : function() {
										}
									}
								}).inject(content);
							}
						}
					});
				});
			});

			function doSave(){
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				}
			}

			function doBackToSecrecyCommitteeDetail(){
				window.location.href = "${ctx}/secrecyorganization/secrecycommittee/secrecyCommittee_detail.action?_ts1356500601062";
			}

			// 新增成员
			function addMember(){
				window.location.href = "${ctx}/secrecyorganization/secrecycommitteemember/secrecyCommitteeMember_add.action?secrecyCommittee.secrecyCommitteeId=${secrecyCommittee.secrecyCommitteeId}";
			}

			// 编辑成员
			function editMember(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}

				window.location.href = action + "?secrecyCommitteeMember.secrecyCommitteeMemberId=" + items[0].value;
			}

			// 删除成员
			function deleteMember(action){
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
					document.getElementById("secrecyCommitteeMemberIds").value = ids;
					document.getElementById("secrecyCommitteeMembersDelForm").action = action;
					document.getElementById("secrecyCommitteeMembersDelForm").submit();
				}
			}

			//脱密
			function doChange(partId){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				$ENV.dialog.open({
					url : "${ctx}/secrecyorganization/secrecycommitteemember/secrecyCommitteeMember_personnelChange.action?secrecyCommitteeMember.secrecyCommitteeMemberId="+ items[0].value +"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 0.5,
					title : '人员变动'
				});
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
					<a class="pop_button" href="###" onclick="" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="###" onclick="doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="###" onclick="doBackToSecrecyCommitteeDetail()"><span>返回列表</span></a>
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

		<div id="body_content" class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密委员会简介" ctx="${ctx}" icoPath="${ctx }/secrecy-organization/secrecy-committee/borderlayout/skin/blue/img/detail_cpIco.gif">
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

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑保密委员会
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="edit_form" action="<s:url namespace='/secrecyorganization/secrecycommittee' action='secrecyCommittee_editing.action' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyCommittee.secrecyCommitteeId" value="${secrecyCommittee.secrecyCommitteeId}"/>

						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									保密委名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" id="secrecyCommittee.name" name="secrecyCommittee.name" class="validate['required','length[100]'] w500" value="${secrecyCommittee.name}"/> <font color="red">*</font>
								</td>
							<tr/>
							<tr>
								<td class="tbLable fr">
									发文号：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyCommittee.docNo" name="secrecyCommittee.docNo" class="validate['length[100]'] w135" value="${secrecyCommittee.docNo}"/>
									例如：川XXXX[2012]XX号
								</td>
								<td class="tbLable fr">
									成立(发文)日期：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyCommittee.setupDate" name="secrecyCommittee.setupDate" class="Wdate validate['length[20]'] w135" value="<s:date name='#attr.secrecyCommittee.setupDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								</td>
							<tr>
							<tr>
								<td class="tbLable fr">
									主要职能：
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea id="secrecyCommittee.dutyMemberWork" name="secrecyCommittee.dutyMemberWork" rows="" cols="" style="width: 90%;height: 150px;" class="validate['length[1000]']">${secrecyCommittee.dutyMemberWork}</textarea>
									<br/>
									提示：请填写保密委员会的职能职责，以及成员分工情况。
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>

					<table class="content_table" width="100%">
						<tr>
							<td class="tbLable fr">
								发文上传：
							</td>
							<td class="tbValue fl" colspan="3">
								<attach:upload uploadBehavior="secrecyCommittee.uploadBehavior" attachments="${attachmentList}" applyForm="edit_form" applyName="attachmentIds" limit="1"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr"></td>
							<td class="tbValue fl"></td>
							<td class="tbLable fr"></td>
							<td class="tbValue fl"></td>
						</tr>
					</table>

				</div>
			</div>
			<!-- 内容panel结束 -->

			<!-- 保密委成员panel开始 -->
			<div id="main_tab" class="panel tab_panel">
				<div id="main_tab_header" class="panel_header">
					<div class="tab_bar">
						<div class="panel_title">保密委成员列表</div>
						<div class="panel_title">保密委成员变动历史列表</div>
					</div>
					<div style="float: right;margin: 3px;"  class="btn_bar">
						<div>
							<a class="pop_button" href="###" onclick="addMember()"><span>新 增</span></a>
							<a class="pop_button" href="###" onclick="editMember('${ctx}/secrecyorganization/secrecycommitteemember/secrecyCommitteeMember_edit.action')"><span>编 辑</span></a>
							<a class="pop_button" href="###" onclick="deleteMember('${ctx}/secrecyorganization/secrecycommitteemember/secrecyCommitteeMember_delete.action')"><span>删 除</span></a>
							<a class="pop_button" href="###" onclick="doChange()"><span>人员变动</span></a>
						</div>
						<div class=""></div>
					</div>
				</div>
				<div class="tab_panel_content">
					<div class="panel_content" >
						<div class="panel tMargin">
							<%-- <div class="panel_header">
								<div class="panel_title panel_titleListIco">
									保密委成员列表
								</div>
								<div class="panel_btn_bar pop_button_bar">
									<a class="pop_button" href="###" onclick="addMember()"><span>新 增</span></a>
									<a class="pop_button" href="###" onclick="editMember('${ctx}/secrecyorganization/secrecycommitteemember/secrecyCommitteeMember_edit.action')"><span>编 辑</span></a>
									<a class="pop_button" href="###" onclick="deleteMember('${ctx}/secrecyorganization/secrecycommitteemember/secrecyCommitteeMember_delete.action')"><span>删 除</span></a>
									<a class="pop_button" href="###" onclick="doLeaveDuty()"><span>人员变动</span></a>
								</div>
							</div>
							<div class="panel_content panel_content_table"> --%>
								<%-- <s:if test="#attr.secrecyCommittee.secrecyCommitteeMembers.size>0"> --%>
								<s:if test="#attr.secrecyCommitteeMemberList.size>0">
									<ec:table items="secrecyCommitteeMemberList" var="secrecyCommitteeMember" tableId="secrecyCommitteeMemberList" border="0"
										action="${ctx}/secrecyorganization/secrecycommittee/secrecyCommittee_edit.action"
										imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
										width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
										filterable="false" autoIncludeParameters="true" sortable="false">
										<ec:row>
											<ec:column property="secrecyCommitteeMemberId" alias="secrecyCommitteeMemberId_checkbox" cell="checkbox" headerCell="checkbox"/>
											<ec:column property="person" title="姓 名" width="10%" cell="text" alias="size=10"/>
											<ec:column property="organName" title="单 位" width="25%" cell="text" alias="size=20"/>
											<ec:column property="personGroupPosition.positionName" title="保密委员会职务" width="15%" cell="text" alias="size=10"/>
											<ec:column property="adminPost" title="行政职务" width="10%" cell="text" alias="size=10"/>
											<ec:column property="personPhone" title="办公室电话" width="15%" cell="text"/>
											<ec:column property="remark" title="备 注" width="20%" cell="text" alias="size=10"/>
											<ec:column property="null" title="详 情" width="10%">
												<a href="###" onclick="doDetail('${secrecyCommitteeMember.secrecyCommitteeMemberId}')">
													<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/>
												</a>
											</ec:column>
										</ec:row>
									</ec:table>
								</s:if>
								<s:else>
									<u:noData text="暂无数据"/>
								</s:else>

								<!-- 删除用隐藏表单 -->
								<form action="" method="post" id="secrecyCommitteeMembersDelForm">
									<input type="hidden" name="secrecyCommittee.secrecyCommitteeId" value="${secrecyCommittee.secrecyCommitteeId}"/>
									<input type="hidden" name="secrecyCommitteeMemberIds" id="secrecyCommitteeMemberIds"/>
								</form>
							</div>
						<!-- </div> -->
					</div>
					<div class="panel_content" url="${ctx }/secrecyorganization/secrecycommitteemember/secrecyCommitteeMember_historyList.action?secrecyCommittee.secrecyCommitteeId=${secrecyCommittee.secrecyCommitteeId}" >
					</div>
				</div>
			</div>


			<!-- 保密委成员panel结束 -->
			<input type="text" id="secrecyCommitteeListId" style="width: 0;height: 0;">
			<!-- 重载入用隐藏表单 -->
			<form action="${ctx}/secrecyorganization/secrecycommittee/secrecyCommittee_edit.action" method="post" id="secrecyCommitteeEditForm">
				<input type="hidden" name="secrecyCommittee.secrecyCommitteeId" value="${secrecyCommittee.secrecyCommitteeId}"/>
			</form>
		</div>
	</body>
</html>