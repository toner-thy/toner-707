<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>单位保密组织机构(综合统计)</title>

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
									height: 500
								},
								events : {
									load : function() {
									}
								}
							}).inject(content);
						}
					});

					new SimpleUI.SimpleTab({
						el : 'main_tab1',
						onActive : function(item, content, index) {

						},
						onFirstActive : function(item, content, index) {
							//alert(content.get('url'));
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

				});

			});

			//查看人员详情
			function doView(id){
				$ENV.dialog.open({
					url : '${ctx}/platform/userinfo/userInfo_detail.action?userInfo.userInfoId='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.7,
					title : '人员详细信息'
				});
			}

			function doEdit(){
				window.location.href="${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_edit.action?secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}";
			}

			function download(hostId,attachId){
				window.location.href = "<s:url action="part_download" includeParams="false"/>?part.partId=" + hostId + "&attachment.attachId=" + attachId;
			}

			function deleteAttachment(attachId){
				window.location.href = "<s:url action="part_deleteAttachment" includeParams="false"/>?attachment.attachId=" + attachId;
			}

			// 详 情
			function doDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/secrecyorganization/secrecyWorkOrganRelationMember/secrecyWorkOrganRelationMember_detail.action?secrecyWorkOrganRelationMember.secrecyWorkOrganRelationId='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '单位保密工作机构成员详情'
				});
			}

			function doDetailUnit(id){
				$ENV.dialog.open({
					url : '${ctx}/secrecyorganization/secrecyWorkOrganMemberUnit/secrecyWorkOrganMemberUnit_memberdetail.action?secrecyWorkOrganMemberUnit.secrecyWorkUnitMemberId='+id+'&secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '单位保密工作机构成员详情'
				});
			}

		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						【${organ.organName}】组织机构
					</div>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_content panel_content_table">
					<table class="content_table" width="100%">
						<tr>
							<td class="tbLable fr">
								单位名称：
							</td>
							<td class="tbValue fl" colspan="3">
								${secrecyWorkOrgan.organ.organName}
							</td>
						</tr>
						<tr>
							<td class="fr" width="25%">
								机构类别：
							</td>
							<td class="fl" colspan="1">
								<c:if test="${not empty secrecyWorkOrgan.organType }">
									<dictionary:text tableCode="bmp" fieldCode="organ_category" optionValue="${secrecyWorkOrgan.organType }" ></dictionary:text>
								</c:if>
								<c:if test="${empty secrecyWorkOrgan.organType }">
									未设置
								</c:if>
							</td>
							<td class="fr" width="25%">
								行政级别：
							</td>
							<td class="fl" colspan="1">
								<c:if test="${not empty secrecyWorkOrgan.organAdminLevel }">
 									<dictionary:text tableCode="bmp" fieldCode="organ_admin_level" optionValue="${secrecyWorkOrgan.organAdminLevel }"></dictionary:text>
 								</c:if>
								<c:if test="${empty secrecyWorkOrgan.organAdminLevel }">
									未设置
 								</c:if>
 							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								机构类型：
							</td>
							<td class="tbValue fl">
							<c:if test="${secrecyWorkOrgan.groupType==1}">保密工作领导小组</c:if>
							<c:if test="${secrecyWorkOrgan.groupType==2}">保密委员会</c:if>
							</td>
							<td class="tbLable fr">
								发文号：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.docNo}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								成立(发文)日期：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.setupDate}
							</td>
							<td class="tbLable fr">
								保密办设在：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.department.departmentName}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								保密办负责人：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.organPrincipal.name}
							</td>
							<td class="fr">
								负责人行政级别：
							</td>
							<td class="fl">
								<c:if test="${ not empty secrecyWorkOrgan.organAdminPostLevel }">
									<dictionary:text tableCode="bmp" fieldCode="organ_admin_post_level" optionValue="${secrecyWorkOrgan.organAdminPostLevel }"></dictionary:text>
								</c:if>
								<c:if test="${empty secrecyWorkOrgan.organAdminPostLevel }">
									未设置
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								行政职务：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.principalDuty}
							</td>
							<td class="tbLable fr">
								电 话：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.principalPhone}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								传 真：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.fax}
							</td>
							<td class="tbLable fr">
								邮 编：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.zipCode}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								地 址：
							</td>
							<td class="tbValue fl" colspan="3">
								${secrecyWorkOrgan.address}
							</td>
						</tr>
						<%-- <tr>
							<td class="tbLable fr">
								是否上报：
							</td>
							<td class="tbValue fl" colspan="3">
								<c:if test="${secrecyWorkOrgan.reportState == 0}"><span style="color:red">未上报</span></c:if>
								<c:if test="${secrecyWorkOrgan.reportState == 1}"><span style="color:green">已上报</span></c:if>
								<c:if test="${secrecyWorkOrgan.reportState == 2}"><span style="color:green">上报后修改</span></c:if>
							</td>
						</tr> --%>
						<tr>
							<td class="tbLable fr">
								主要职能：
							</td>
							<td class="tbValue fl" colspan="3">
								<textarea style="width:100%; height:150px;" readonly="readonly">${secrecyWorkOrgan.dutyMemberWork}</textarea>
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
					</table>

					<c:forEach items="${attachments}" var="attachment">
						<c:if test="${attachment!=null}">
							<table class="content_table no_print" width="600px;">
								<tr>
									<td class="tbLable fr">
										发文附件列表
									</td>
									<td class="fl" colspan="3">
										<attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" attachments="${attachments}" titleText="附件"></attach:view>
									</td>
								</tr>
							</table>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<!-- 内容panel结束 -->

		<div id="main_tab" class="panel tab_panel" style="height: 500px;">
			<div id="main_tab_header" class="panel_header">
				<div class="tab_bar">
					<div class="panel_title">保密工作机构人员列表</div>
					<div class="panel_title">保密工作机构人员变动历史列表</div>
				</div>
				<div style="float:right;margin: 2px;">
					<div class="">
					</div>
					<div class=""></div>
				</div>
			</div>
			<div class="tab_panel_content">
				<div class="panel_content" style="overflow: auto;">
					<div class="panel tMargin">
					<s:if test="#attr.secrecyWorkOrganRelationMemberList.size>0">
								<div class="eXtremeTable">
									<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
										<thead>
											<tr>
												<td class="tableHeader fc" width="5%">序 号</td>
												<td class="tableHeader fc" width="15%">机构职务</td>
												<td class="tableHeader fc" width="15%">成员姓名</td>
												<td class="tableHeader fc" width="20%">部 门</td>
												<td class="tableHeader fc" width="15%">行政职务</td>
												<td class="tableHeader fc" width="20%">办公室电话</td>
												<td class="tableHeader fc" width="10%">详 情</td>
											</tr>
										</thead>
										<tbody class="tableBody" >
											<c:set var="num" value="1"></c:set>
											<c:forEach items="${secrecyWorkOrganRelationMemberList}" var="swrm" varStatus="xm">
												<tr class="${xm.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${xm.count % 2 == 0 ? 'odd' : 'even' }'">
													<td class="fc">
														${num}
														<c:set var="num" value="${num + 1 }"></c:set>
													</td>
													<td class="fc">
														${swrm.personGroupPosition.positionName}
													</td>
													<td class="fc">
														${swrm.person.name}
													</td>
													<td class="fc" title="${swrm.person.department.departmentName }">
														${fn:substring(swrm.person.department.departmentName,0,6)}
														<c:if test="${fn:length(swrm.person.department.departmentName)>6 }">...</c:if>
													</td>
													<td class="fc">
														${swrm.person.duty}
													</td>
													<td class="fc" >
														${swrm.person.phone}
													</td>
													<td class="fc">
														<a href="###" onclick="doDetail('${swrm.secrecyWorkOrganRelationId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
						<!-- 保密工作机构人员panel结束 -->
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
					</div>
				</div>
				<div class="panel_content" url="${ctx }/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrganRelationMember_historyList.action?secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}">
				</div>
			</div>
		</div>

		<div id="main_tab1" class="panel tab_panel">
			<div id="main_tab_header" class="panel_header">
				<div class="tab_bar">
					<div class="panel_title">单位保密办成员列表</div>
					<div class="panel_title">单位保密办成员变动历史列表</div>
				</div>
				<div style="float:right;margin: 2px;">
					<div class="">
					</div>
					<div class=""></div>
				</div>
			</div>
			<div class="tab_panel_content">
				<div class="panel_content" >
					<div class="panel tMargin">
					<s:if test="#attr.secrecyWorkOrganMemberUnitList.size>0">
						<div class="eXtremeTable">
							<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
								<thead>
									<tr>
										<td class="tableHeader fc" width="5%" >序号</td>
										<td class="tableHeader fc" width="15%">成员姓名</td>
										<td class="tableHeader fc" width="20%">部 门</td>
										<td class="tableHeader fc" width="20%">行政职务</td>
										<td class="tableHeader fc" width="15%">办公室电话</td>
										<td class="tableHeader fc" width="25%">备 注</td>
										<td class="tableHeader fc" width="25%">详情</td>
									</tr>
								</thead>
								<tbody class="tableBody" >
									<c:set var="num" value="1"></c:set>
									<c:forEach items="${secrecyWorkOrganMemberUnitList}" var="swmu" varStatus="x">
										<tr class="${x.count % 2 != 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${x.count % 2 != 0 ? 'odd' : 'even' }'">
											<td class="fc">
												${num}
												<c:set var="num" value="${num + 1 }"></c:set>
											</td>
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
												<a href="###" onclick="doDetailUnit('${swmu.secrecyWorkUnitMemberId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- 办公室成员panel结束 -->
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
					</div>
				</div>
				<div class="panel_content" url="${ctx }/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrganMemberUnit_unitHistoryList.action?secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}">
				</div>
			</div>
		</div>

		</div>

		<form id="personGroup_list" action="${ctx}/personGroup_list.action" method="post">
		</form>
	</body>
</html>