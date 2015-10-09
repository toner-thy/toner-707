<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密工作机构</title>

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

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
			});

			// 详 情
			function doDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/secrecyorganization/secrecyWorkOrganRelationMember/secrecyWorkOrganRelationMember_detail.action?secrecyWorkOrganRelationMember.secrecyWorkOrganRelationId='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '单位组织机构成员详情'
				});
			}
			function back(){
				window.location.href = "${ctx}/bmp/secrecyworksummary/secrecyWorksummary_main.action";
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="but_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick="javascript:back();"><span>返 回</span></a>
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
			<cp:start defaultTitle="保密工作机构简介" ctx="${ctx}" icoPath="/bmp/secrecy-organization/secrecy-workorgan/borderlayout/skin/blue/img/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密工作机构简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密工作机构
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
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						【${secrecyWorkOrgan.organ.organName}】保密工作机构
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" width="100%">
						<tr>
							<td class="tbLable fr">
								单位名称：
							</td>
							<td class="tbValue fl" colspan="3">
								${secrecyWorkOrgan.organ.organName}
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
							<td class="tbLable fr">
								行政职务：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.principalDuty}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								电 话：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.principalPhone}
							</td>
							<td class="tbLable fr">
								地 址：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrgan.address}
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
								主要职能：
							</td>
							<td class="tbValue fl" colspan="3">
								<textarea style="width:100%; height:150px;" readonly="readonly">${secrecyWorkOrgan.dutyMemberWork}</textarea>
							</td>
						</tr>
					</table>

					<c:forEach items="${attachments}" var="attachment">
						<c:if test="${attachment!=null}">
							<table class="content_table st no_print" width="600px;">
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

			<s:if test="#attr.secrecyWorkOrganMemberUnitList.size>0">
				<!-- 办公室成员panel开始 -->
				<div class="panel tMargin">
					<div class="panel_header">
						<div class="panel_title panel_titleListIco">
							办公室成员列表
						</div>
						<div class="panel_btn_bar pop_button_bar">
						</div>
					</div>
					<div class="panel_content panel_content_table">
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
											<td class="fc">
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
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- 办公室成员panel结束 -->
			</s:if>
		</div>
	</body>
</html>