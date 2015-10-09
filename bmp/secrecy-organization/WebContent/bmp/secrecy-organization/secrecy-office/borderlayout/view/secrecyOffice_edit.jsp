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
		<title>编辑保密办（保密局）</title>

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
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					$('secrecyOfficeListId').focus();

					new SimpleUI.SimpleTab({
						el : 'main_tab',
						onActive : function(item, content, index) {
						},
						onFirstActive : function(item, content, index) {
							var frm = new IFrame({
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
							$('btnEditMember').addEvent('click', function(){
								frm.contentWindow.editMember('${secrecyOffice.secrecyOfficeId}','${district.districtCode}');
							});
							$('btnAddMember').addEvent('click', function(){
								frm.contentWindow.addMember('${secrecyOffice.secrecyOfficeId}','${district.districtCode}');
							});
							$('deleteMember').addEvent('click', function(){
								frm.contentWindow.deleteMember('${secrecyOffice.secrecyOfficeId}');
							});
							$('secrecyChange').addEvent('click', function(){
								frm.contentWindow.doSecrecyChange('${secrecyOffice.secrecyOfficeId}','${district.districtCode}');
							});
							$('leaveDuty').addEvent('click', function(){
								frm.contentWindow.doLeaveDuty('${secrecyOffice.secrecyOfficeId}','${district.districtCode}');
							});
						}
					});
				});
			});

			$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/DynamicTextarea/DynamicTextarea.js",function(){
				$ENV.onDomReady(function(){
					$('secrecyOffice.dutyMemberWork').setStyle('width', $('secrecyOffice.dutyMemberWork').getParent().getSize().x);
			        new DynamicTextarea('secrecyOffice.dutyMemberWork', {
			            minRows: 4
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
			function doBack2(){
				window.location.href = "${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_detail.action?secrecyOffice.secrecyOfficeId=${secrecyOffice.secrecyOfficeId}";
			}

			// 新增编辑(人员编制构成情况)
			function editEstablishSituation() {
				window.location.href = "${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_editEstablishSituation.action?secrecyOffice.secrecyOfficeId=${secrecyOffice.secrecyOfficeId}";
			}
			// 新增编辑内设机构
			function editInternalOrgan() {
				window.location.href = "${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_editInternalOrgan.action?secrecyOffice.secrecyOfficeId=${secrecyOffice.secrecyOfficeId}";
			}
			// 新增编辑基础设施建设
			function editInfrastructure() {
				window.location.href = "${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_editInfrastructure.action?secrecyOffice.secrecyOfficeId=${secrecyOffice.secrecyOfficeId}";
			}
			// IFrame引入进来的面板，重新刷新编辑页面
			function reloadMember() {
				window.location.href="${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_edit.action?secrecyOffice.secrecyOfficeId=${secrecyOffice.secrecyOfficeId}&district.districtCode=${district.districtCode}";
			}
			//显示层
			function disOne(id) {
				$(id).style.display="";
			}

			//消失层
			function noneOne(id){
				$(id).style.display="none";
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
					<a class="pop_button" href="javascript:doBack2();"><span>返回</span></a>
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

		<div id="body_content" class="body_content">
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

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑保密办（保密局）
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecyoffice' action='secrecyOffice_editing' includeParams='true'/>" method="post">
						<input type="hidden" id="secrecyOffice.secrecyOfficeId" name="secrecyOffice.secrecyOfficeId" value="${secrecyOffice.secrecyOfficeId}" />
						<input type="hidden" id="secrecyOffice.secrecyCommittee.secrecyCommitteeId" name="secrecyOffice.secrecyCommittee.secrecyCommitteeId" value="${secrecyOffice.secrecyCommittee.secrecyCommitteeId}" />
						<input type="hidden" id="secrecyOffice.createTime" name="secrecyOffice.createTime" value="${secrecyOffice.createTime}" />
						<input type="hidden" id="secrecyOffice.createOrgan.organId" name="secrecyOffice.createOrgan.organId" value="${secrecyOffice.createOrgan.organId}" />
						<input type="hidden" id="secrecyOffice.createUser.userId" name="secrecyOffice.createUser.userId" value="${secrecyOffice.createUser.userId}" />
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									单位名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<b><input type="text" id="secrecyOffice.name" name="secrecyOffice.name" class="validate['required','length[50]']" style="width: 70%"  value="${secrecyOffice.name }" /></b><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主管单位：
								</td>
								<td class="tbValue fl">
									${secrecyOffice.mainOrgan.organName }<input type="hidden" id="secrecyOffice.mainOrgan.organId" name="secrecyOffice.mainOrgan.organId" value="${secrecyOffice.mainOrgan.organId}"/>
								</td>
								<td class="tbLable fr">
									成立时间：
								</td>
								<td class="tbValue fl">
									<input type="text" class="Wdate validate['required']" id="secrecyOffice.establishTime" name="secrecyOffice.establishTime" value="<s:date name='#attr.secrecyOffice.establishTime' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									行政级别：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="organ_admin_level" styleClass="validate['required']" id="secrecyOffice.administrativeLevel" name="secrecyOffice.administrativeLevel"  title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyOffice.administrativeLevel}"></dictionary:select>
								</td>
								<td class="tbLable fr">
									经费来源：
								</td>
								<td class="tbValue fl">
									<input type="text" class="validate['required','length[200]']" id="secrecyOffice.fundsSource" name="secrecyOffice.fundsSource" value="${secrecyOffice.fundsSource }" /><span style="color:red;">&nbsp;&nbsp;*</span>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('expid')" onmouseout="noneOne('expid')"  />
									<div id="expid"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											指国家或地方财政拨款。
										<br/>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									机构类别：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="organ_category" styleClass="validate['required']" id="secrecyOffice.organType" name="secrecyOffice.organType"  title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyOffice.organType}"></dictionary:select>
								</td>
								<td class="tbLable fr">
									是否政府序列：
								</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'是','0':'否'}"
										style="width:130px;"
										theme="simple"
										name="secrecyOffice.govSequence"
										headerValue="请选择" headerKey=""
										cssClass="validate['required']"
										>
									</s:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主任(局长)：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyOffice.director.userInfoId" textEl="secrecyOffice.director.name" required="true" onlyFromValue="false" value="${secrecyOffice.director.userInfoId }" text="${secrecyOffice.director.name }"></ui:selectByOrgan>
								</td>
								<td class="tbLable fr">
									办公室设在：
								</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="secrecyOffice.dept.departmentId" textEl="secrecyOffice.dept.departmentName" required="true" onlyFromValue="false" value="${secrecyOffice.dept.departmentId}" text="${secrecyOffice.dept.departmentName}"></dep:selectByOrgan>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									负责人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyOffice.person.userInfoId" textEl="secrecyOffice.person.name" required="true" onlyFromValue="false" value="${secrecyOffice.person.userInfoId }" text="${secrecyOffice.person.name }"></ui:selectByOrgan>
								</td>
								<td class="tbLable fr">
									联系电话：
								</td>
								<td class="tbValue fl">
									<input id="secrecyOffice.telephone" name="secrecyOffice.telephone" class="validate['length[20]','phone']" value="${secrecyOffice.telephone }"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									传 真：
								</td>
								<td class="tbValue fl">
									<input id="secrecyOffice.fax" name="secrecyOffice.fax" class="validate['phone','length[30]']" value="${secrecyOffice.fax }"/>
								</td>
								<td class="tbLable fr">
									邮 编：
								</td>
								<td class="tbValue fl">
									<input id="secrecyOffice.postcode" name="secrecyOffice.postcode" class="validate['digit','length[6]']" value="${secrecyOffice.postcode }"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									地 址：
								</td>
								<td class="tbValue fl" colspan="3">
									<input id="secrecyOffice.address" name="secrecyOffice.address" class="validate['length[100]']" style="width: 70%" value="${secrecyOffice.address }"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主要职能：
								</td>
								<td class="tbValue fl" colspan="5">
									<textarea id="secrecyOffice.dutyMemberWork" name="secrecyOffice.dutyMemberWork" class="validate['length[1000]']" style="width: 95%;padding: 5px;" rows="3">${secrecyOffice.dutyMemberWork }</textarea>提示：请填写保密工作机构的职能职责，以及成员分工情况。
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束 -->
			<div class="split_line"></div>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						中共${district.districtName }委保密委员会办公室（${district.districtName }国家保密局）编制情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="###" onclick="editEstablishSituation()"><span>编 辑</span></a>
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
							<tr>
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
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="split_line"></div>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						中共${district.districtName }委保密委员会办公室（${district.districtName }国家保密局）内设机构基本情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="###" onclick="editInternalOrgan()"><span>编 辑</span></a>
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
						<a class="pop_button" href="###" onclick="editInfrastructure()"><span>编 辑</span></a>
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
			<!-- 保密办（保密局）成员panel开始 -->
			<div id="main_tab" class="panel tab_panel">
				<div id="main_tab_header" class="panel_header">
					<div class="tab_bar">
						<div class="panel_title">保密办（保密局）成员列表</div>
						<div class="panel_title">保密办（保密局）成员密级变更信息列表</div>
						<div class="panel_title">保密办（保密局）成员脱密人员信息列表</div>
					</div>
					<div style="float: right;margin: 3px;" class="btn_bar">
						<div class="">
							<a class="pop_button" href="###" id="btnAddMember"><span>新 增</span></a>
							<a class="pop_button" href="###" id="btnEditMember"><span>编 辑</span></a>
							<a class="pop_button" href="###" id="deleteMember"><span>删 除</span></a>
							<a class="pop_button" href="###" id="secrecyChange"><span>密级变更</span></a>
							<a class="pop_button" href="###" id="leaveDuty"><span>脱密</span></a>
						</div>
						<div class=""></div>
						<div class=""></div>
					</div>
				</div>
				<div class="tab_panel_content">
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
			<input type="text" id="secrecyOfficeListId" style="width: 0;height: 0;">
		</div>
	</body>
</html>