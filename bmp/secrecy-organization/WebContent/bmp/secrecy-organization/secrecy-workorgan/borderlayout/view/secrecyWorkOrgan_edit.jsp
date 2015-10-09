<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑单位保密工作机构</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
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
					formcheck = new FormCheck('form_secrecyWorkOrgan_edit',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					$('secrecyWorkOrgan.department.departmentName').addEvent('blur', doBlur);
					//$('personGroupListId').focus();

					new SimpleUI.SimpleTab({
						el : 'main_tab',
						onActive : function(item, content, index) {

						},
						onFirstActive : function(item, content, index) {
							//alert(content.get('url'));
							new IFrame({
								src : content.get('url'),
								frameborder : 0,
								styles : {
									width : content.getSize().x,
									height: 300
								},
								events : {
									load : function() {
									}
								}
							}).inject(content);
						}
					});
					/*
					new SimpleUI.SimpleTab({
						el : 'main_tab1',
						onActive : function(item, content, index) {

						},
						onFirstActive : function(item, content, index) {
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
					});
					*/
				});
			});

			var popFlag = true;
			function doBlur(){
				var value = $('secrecyWorkOrgan.department.departmentName').value;
				if('${secrecyWorkOrgan.department.departmentName}' != value){
					if(popFlag){
						doSelectDept();
						popFlag = false;
					}
				}
			}

			function doSelectDept(){
				$ENV.dialog.open({
					title : '请选择',
					url : "${ctx}/bmp/secrecy-organization/secrecy-workorgan/borderlayout/view/secrecyWorkOrgan_deptConfirm.jsp?&_ts=" + $time(),
					width : window.top.getSize().x * 0.3,
					height : window.top.getSize().y * 0.4
				});
			}

			/**$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/DynamicTextarea/DynamicTextarea.js",function(){
				$ENV.onDomReady(function(){
					$('secrecyWorkOrgan.dutyMemberWork').setStyle('width', $('secrecyWorkOrgan.dutyMemberWork').getParent().getSize().x);
			        new DynamicTextarea('secrecyWorkOrgan.dutyMemberWork', {
			            minRows: 4
			        });
				});
			});*/

			function doSave(){
				if(!popFlag){
					return;
				}
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				}
			}

			// 新增保密办成员
			function doAddOrganMember(){
				window.location.href="${ctx}/secrecyorganization/secrecyWorkOrganMemberUnit/secrecyWorkOrganMemberUnit_add.action?secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}";
			}

			// 编辑保密办成员
			function doEditOrganMember(id){
				var action = "${ctx}/secrecyorganization/secrecyWorkOrganMemberUnit/secrecyWorkOrganMemberUnit_edit.action";
				window.location.href = action + "?secrecyWorkOrganMemberUnit.secrecyWorkUnitMemberId=" + id + "&secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}";
			}

			// 附件下载
			function download(hostId,attachId){
				window.location.href = "<s:url action="part_download" includeParams="false"/>?part.partId=" + hostId + "&attachment.attachId=" + attachId;
			}
			// 附件删除
			function deleteAttachment(attachId){
				window.location.href = "<s:url action="part_deleteAttachment" includeParams="false"/>?attachment.attachId=" + attachId;
			}

			// 新增保密工作机构人员
			function doAddRelationMember(){
				window.location.href="${ctx}/secrecyorganization/secrecyWorkOrganRelationMember/secrecyWorkOrganRelationMember_add.action?secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}";
			}

			// 编辑保密工作机构人员
			function doEditRelationMember(id){
				var action = "${ctx}/secrecyorganization/secrecyWorkOrganRelationMember/secrecyWorkOrganRelationMember_edit.action";
				window.location.href = action + "?secrecyWorkOrganRelationMember.secrecyWorkOrganRelationId=" + id + "&secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}";
			}

			// 删除保密办人员
			function doDelMemberUnit(id){
				if(window.confirm("确定要删除吗？")){
					document.getElementById("secrecyWorkOrganMemberUnitIds").value = id;
					document.getElementById("secrecyWorkUnitMemberDelForm").action = "${ctx}/secrecyorganization/secrecyWorkOrganMemberUnit/secrecyWorkOrganMemberUnit_delete.action";
					document.getElementById("secrecyWorkUnitMemberDelForm").submit();
				}
			}

			// 删除保密工作机构人员
			function doDelRelationMember(id){
				if(window.confirm("确定要删除吗？")){
					document.getElementById("secrecyWorkOrganRelationMemberIds").value = id;
					document.getElementById("secrecyWorkUnitMemberDelForm").action = "${ctx}/secrecyorganization/secrecyWorkOrganRelationMember/secrecyWorkOrganRelationMember_delete.action";
					document.getElementById("secrecyWorkUnitMemberDelForm").submit();
				}
			}

			function doBackLink(){
				window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_detail.action";
			}

			//保密委成员人员变动
			function doSecrecyChange(memberId){
				$ENV.dialog.open({
					url : "${ctx}/secrecyorganization/secrecyWorkOrganRelationMember/secrecyWorkOrganRelationMember_personnelChange.action?secrecyWorkOrganRelationMember.secrecyWorkOrganRelationId="+memberId+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 0.5,
					title : '人员变动'
					});
			}

			//保密委 办公室成员 人员变动
			function doSecrecyUnitChange(memberId){
				$ENV.dialog.open({
					url : "${ctx}/secrecyorganization/secrecyWorkOrganMemberUnit/secrecyWorkOrganMemberUnit_personnelChange.action?secrecyWorkOrganMemberUnit.secrecyWorkUnitMemberId="+memberId+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 0.5,
					title : '人员变动'
				});
			}

		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBackLink();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a href='${ctx}/platform/help/help_clientInfo.do?help.helpId=secrecy_organization_help' class='pop_button' target='_back'/><span>帮 助</span></a>
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 复合面板开始 -->
			<%--
			<cp:start defaultTitle="单位保密工作机构简介" ctx="${ctx}" icoPath="/bmp/secrecy-organization/secrecy-workorgan/borderlayout/skin/blue/img/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','单位保密工作机构简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于单位保密工作机构
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ"> </cpc:tc>
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
			 --%>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑单位保密工作机构
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_secrecyWorkOrgan_edit" class="form" action="<s:url action='secrecyWorkOrgan_editing' namespace='/secrecyorganization/secrecyWorkOrgan' includeParams='true'/>" enctype="multipart/form-data" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyWorkOrgan.secrecyWorkOrganId" value="${secrecyWorkOrgan.secrecyWorkOrganId}"/>
						<input type="hidden" id="secrecyWorkOrgan.reportState" name="secrecyWorkOrgan.reportState" value="${secrecyWorkOrgan.reportState}"/>
						<table class="content_table" width="100%">
							<tr>
								<td class="fr" width="20%">
									单位名称：
								</td>
								<td class="fl" colspan="3">
									${secrecyWorkOrgan.organ.organName}
								</td>
							</tr>
							<tr>
								<td class="fr" width="25%">
									机构类别：
								</td>
								<td class="fl" colspan="1">
									<dictionary:select tableCode="bmp" fieldCode="organ_category" id="secrecyWorkOrgan.organType" name="secrecyWorkOrgan.organType" optionValue="${secrecyWorkOrgan.organType }" title="false" style="width:130px;"></dictionary:select>
								</td>
								<td class="fr" width="25%">
									行政级别：
								</td>
								<td class="fl" colspan="1">
									<dictionary:select tableCode="bmp" fieldCode="organ_admin_level" id="secrecyWorkOrgan.organAdminLevel" name="secrecyWorkOrgan.organAdminLevel" optionValue="${secrecyWorkOrgan.organAdminLevel }" title="false" style="width:130px;"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="fr" width="20%">
									机构类型：
								</td>
								<td class="fl">
									<select style="width: 130px;" name="secrecyWorkOrgan.groupType">
										<option value="1" <c:if test="${secrecyWorkOrgan.groupType==1}">selected</c:if>>保密工作领导小组</option>
										<option value="2" <c:if test="${secrecyWorkOrgan.groupType==2}">selected</c:if>>保密委员会</option>
									</select>
								</td>
								<td class="fr" width="15%">
									发文号：
								</td>
								<td class="fl">
									<input id="secrecyWorkOrgan.docNo" name="secrecyWorkOrgan.docNo" value="${secrecyWorkOrgan.docNo}" class="validate['length[20]']"/> 例如：川XXX[2012]XX号
								</td>
							</tr>
							<tr>
								<td class="fr">
									保密办负责人：
								</td>
								<td class="fl">
									<ui:selectByOrgan valueEl="secrecyWorkOrgan.organPrincipal.userInfoId" textEl="secrecyWorkOrgan.organPrincipal.name" text="${secrecyWorkOrgan.organPrincipal.name}" onlyFromValue="false" value="${secrecyWorkOrgan.organPrincipal.userInfoId }" required="true" />
								</td>
								<td class="fr">
									保密办设在：
								</td>
								<td class="fl">
									<dep:selectByOrgan valueEl="secrecyWorkOrgan.department.departmentId" textEl="secrecyWorkOrgan.department.departmentName" required="true" text="${secrecyWorkOrgan.department.departmentName}" value="${secrecyWorkOrgan.department.departmentId }" onlyFromValue="false" />
									<span style="display: none" id="deptFlagShow"></span>
									<input type="hidden" id="deptFlag" name="deptFlag" value="">
									<input type="hidden" id="secrecyWorkOrgan.oldDeptId" name="secrecyWorkOrgan.oldDeptId" value="${secrecyWorkOrgan.department.departmentId}">
								</td>
							</tr>
							<tr>
								<td class="fr">
									成立(发文)日期：
								</td>
								<td class="fl">
								<input type="text" id="secrecyWorkOrgan.setupDate" name="secrecyWorkOrgan.setupDate" class="Wdate validate['length[19]']" value="<s:date name='#attr.secrecyWorkOrgan.setupDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								</td>
								<td class="fr">
									负责人行政级别：
								</td>
								<td class="fl">
									<dictionary:select tableCode="bmp" fieldCode="organ_admin_post_level" id="secrecyWorkOrgan.organAdminPostLevel" name="secrecyWorkOrgan.organAdminPostLevel" optionValue="${secrecyWorkOrgan.organAdminPostLevel }"  style="width:130px;"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="fr">
									行政职务：
								</td>
								<td class="fl">
									<input id="secrecyWorkOrgan.principalDuty" name="secrecyWorkOrgan.principalDuty" type="text" value="${secrecyWorkOrgan.principalDuty}" class="validate['length[10]']"/>
								</td>
								<td class="fr">
									电 话：
								</td>
								<td class="fl">
									<input type="text" id="secrecyWorkOrgan.principalPhone" name="secrecyWorkOrgan.principalPhone" value="${secrecyWorkOrgan.principalPhone}" class="textInput validate['phone','length[20]']" />
								</td>
							</tr>
							<%--
							<tr>
								<td class="fr">
									传 真：
								</td>
								<td class="fl">
									<input id="secrecyWorkOrgan.fax" name="secrecyWorkOrgan.fax" type="text" class="validate['phone','length[20]']" value="${secrecyWorkOrgan.fax}"/>
								</td>
								<td class="fr">
									邮 编：
								</td>
								<td class="fl">
									<input id="secrecyWorkOrgan.zipCode" name="secrecyWorkOrgan.zipCode" class="validate['digit','length[6]']" type="text" value="${secrecyWorkOrgan.zipCode}"/>
								</td>
							</tr>
							 --%>
							<tr>
								<td class="fr">
									地 址：
								</td>
								<td class="fl" colspan="3">
									<input id="secrecyWorkOrgan.address" name="secrecyWorkOrgan.address" class="validate['length[100]']" type="text" value="${secrecyWorkOrgan.address}" size="120" />
								</td>
							</tr>
							<%--
							<tr>
								<td class="fr">
									主要职能：
								</td>
								<td class="fl" colspan="3">
									<textarea id="secrecyWorkOrgan.dutyMemberWork" name="secrecyWorkOrgan.dutyMemberWork" class="validate['length[2000]']" style="width: 95%;padding: 5px;" rows="3">${secrecyWorkOrgan.dutyMemberWork}</textarea>
									提示：请填写保密工作机构的职能职责，以及成员分工情况。
								</td>
							</tr>
							<tr>
								<td class="fr">
									编制人数：
								</td>
								<td colspan="3">
									<div style="margin-left: 8px;">${secrecyWorkOrgan.establishmentNum }</div>
								</td>
							</tr>
							 --%>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>
					</form>

					<table class="content_table" width="100%">
						<tr>
							<td class="fr">
								发文上传：
							</td>
							<td class="fl" colspan="3">
								<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="form_secrecyWorkOrgan_edit" applyName="secrecyWorkOrganAttachs" titleText="附件上传"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->

			<!-- 保密工作机构成员panel开始 -->
			<div id="main_tab" class="panel tab_panel" style="height: 500px;overflow-y: auto;overflow-x: hidden;">
				<div id="main_tab_header" class="panel_header">
					<div class="tab_bar">
						<div class="panel_title">保密工作机构人员列表</div>
						<div class="panel_title">保密工作机构人员变动历史列表</div>
					</div>
					<div style="float:right;margin: 2px;" class="btn_bar">
						<div class="">
							<a class="pop_button" href="###" onclick="javascript:doAddRelationMember();"><span>新 增</span></a>
						</div>
						<div class=""></div>
					</div>
				</div>
				<div class="tab_panel_content" style="height: 300px;">
					<div class="panel_content panel_content_table">
						<div class="panel tMargin">
								<%-- <div class="panel_header">
									<div class="panel_title panel_titleListIco">
										保密工作机构成员列表
									</div>
									<div class="panel_btn_bar pop_button_bar">
										<a class="pop_button" href="###" onclick="javascript:doAddRelationMember();"><span>新 增</span></a>
									</div>
								</div>
							<div class="panel_content panel_content_table">
								--%>

					<div class="eXtremeTable">
						<s:if test="#attr.secrecyWorkOrganRelationMemberList.size>0">
							<table id="relationMemberList" class="tableRegion" cellspacing="0" cellpadding="0" width="98%;">
								<thead>
									<tr>
										<td class="tableHeader fc" width="15%" >机构职务</td>
										<td class="tableHeader fc" width="10%" >成员姓名</td>
										<td class="tableHeader fc" width="15%" >部 门</td>
										<td class="tableHeader fc" width="15%" >行政职务</td>
										<td class="tableHeader fc" width="15%" >办公室电话</td>
										<td class="tableHeader fc" width="15%" >备 注</td>
										<td class="tableHeader fc" width="15%" >操 作</td>
									</tr>
								</thead>
								<tbody class="tableBody" >
									<c:forEach items="${secrecyWorkOrganRelationMemberList}" var="sworm" varStatus="x">
										<tr class="${x.count % 2 != 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${x.count % 2 != 0 ? 'odd' : 'even' }'">
											<td class="fc">
												${sworm.personGroupPosition.positionName}
											</td>
											<td class="fc">
												${sworm.person.name}
											</td>
											<td class="fc" title="${sworm.person.department.departmentName }">
												${fn:substring(sworm.person.department.departmentName,0,5)}
												<c:if test="${fn:length(sworm.person.department.departmentName)>5 }">...</c:if>
											</td>
											<td class="fc">
												${sworm.person.duty}
											</td>
											<td class="fc" >
												${sworm.person.phone}
											</td>
											<td class="fc">
												${fn:substring(sworm.remark,0,10)}
												<c:if test="${fn:length(sworm.remark)>10 }">...</c:if>
											</td>
											<td class="fc">
												<a href="###" onclick="javascript:doEditRelationMember('${sworm.secrecyWorkOrganRelationId}');"><span>编 辑</span></a>||
												<a href="###" onclick="javascript:doDelRelationMember('${sworm.secrecyWorkOrganRelationId}');"><span>删 除</span></a>||
												<br/>
												<a href="###" onclick="javascript:doSecrecyChange('${sworm.secrecyWorkOrganRelationId}');"><span>人员变动</span></a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</s:if>
						<s:else>
						  <u:noData text="暂无数据"/>
						</s:else>
					</div>
				<!--</div> -->
			</div>
					</div>
					<div class="panel_content" url="${ctx }/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrganRelationMember_historyList.action?secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}">
					</div>
				</div>
			</div>
			<!-- 保密工作机构成员panel结束 -->

			<!-- 办公室成员panel开始 -->
			<%--
			<div id="main_tab1" class="panel tab_panel" style="height: 500px;overflow-y: auto;overflow-y: hidden;">
			<div id="main_tab_header" class="panel_header">
				<div class="tab_bar">
					<div class="panel_title">单位保密办成员列表</div>
					<div class="panel_title">单位保密办成员变动历史列表</div>
				</div>
				<div style="position: absolute;right: 0;top: 0;" class="btn_bar">
						<div style="text-align: right;vertical-align: middle;">
						<a class="pop_button" href="###" onclick="javascript:doAddOrganMember();" ><span>新 增</span></a>
					</div>
					<div class=""></div>
				</div>
			</div>
			<div class="tab_panel_content">
				<div class="panel_content">
					<div class="panel tMargin">
						<!-- <div class="panel_header">
							<div class="panel_title panel_titleListIco">
								保密办成员列表
							</div>
							<div class="panel_btn_bar pop_button_bar">
								<a class="pop_button" href="###" onclick="javascript:doAddOrganMember();" ><span>新 增</span></a>
							</div>
						</div>
						<div class="panel_content panel_content_table"> -->
							<div class="eXtremeTable">
								<s:if test="#attr.secrecyWorkOrganMemberUnitList.size>0">
									<table id="organMemberUnitList" class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
										<thead>
											<tr>
												<td class="tableHeader fc" width="15%" >成员姓名</td>
												<td class="tableHeader fc" width="20%" >部 门</td>
												<td class="tableHeader fc" width="20%" >行政职务</td>
												<td class="tableHeader fc" width="15%" >办公室电话</td>
												<td class="tableHeader fc" width="15%" >备 注</td>
												<td class="tableHeader fc" width="15%" >操 作</td>
											</tr>
										</thead>
										<tbody class="tableBody" >
											<c:forEach items="${secrecyWorkOrganMemberUnitList}" var="swmu" varStatus="x">
												<tr class="${x.count % 2 != 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${x.count % 2 != 0 ? 'odd' : 'even' }'">
													<td class="fc">
														${swmu.person.name}
													</td>
													<td class="fc" title="${swmu.person.department.departmentName }">
														${fn:substring(swmu.person.department.departmentName,0,5)}
														<c:if test="${fn:length(swmu.person.department.departmentName)>5 }">...</c:if>
													</td>
													<td class="fc">
														${swmu.person.duty}
													</td>
													<td class="fc" >
														${swmu.person.phone}
													</td>
													<td class="fc">
														${fn:substring(swmu.remark,0,10)}
														<c:if test="${fn:length(swmu.remark)>10 }">...</c:if>
													</td>
													<td class="fc">
														<a href="###" onclick="javascript:doEditOrganMember('${swmu.secrecyWorkUnitMemberId}');"><span>编 辑</span></a>||
														<a href="###" onclick="javascript:doDelMemberUnit('${swmu.secrecyWorkUnitMemberId}');"><span>删 除</span></a>||
														<br/>
														<a href="###" onclick="javascript:doSecrecyUnitChange('${swmu.secrecyWorkUnitMemberId}');"><span>人员变动</span></a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</s:if>
								<s:else>
			                       <u:noData text="暂无数据"/>
								</s:else>
							</div>
						<!-- </div> -->
					</div>
				</div>
				<div class="panel_content" url="${ctx }/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrganMemberUnit_unitHistoryList.action?secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}">
				</div>
			</div>
		</div>

			 --%>
			<input type="text" id="personGroupListId" style="width: 0;height: 0;">
			<!-- 办公室成员panel结束 -->

			<!-- 删除用隐藏表单 -->
			<form action="" method="post" id="secrecyWorkUnitMemberDelForm">
				<input type="hidden" name="secrecyWorkOrganMemberUnit.secrecyWorkUnitMemberId" value="${secrecyWorkOrganMemberUnit.secrecyWorkUnitMemberId}"/>
				<input type="hidden" name="secrecyWorkOrgan.secrecyWorkOrganId" value="${secrecyWorkOrgan.secrecyWorkOrganId}"/>
				<input type="hidden" name="secrecyWorkOrganMemberUnitIds" id="secrecyWorkOrganMemberUnitIds"/>
				<input type="hidden" name="secrecyWorkOrganRelationMemberIds" id="secrecyWorkOrganRelationMemberIds"/>
			</form>
		</div>
	</body>
</html>