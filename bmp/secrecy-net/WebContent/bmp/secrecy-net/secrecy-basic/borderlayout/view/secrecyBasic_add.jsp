<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密网络基本情况统计表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />


		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mootools/mootools-core-1.4.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mootools/mootools-more-1.4.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/formcheck/1.4/formcheck.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/notimoo/notimoo-1.2.1.js" type="text/javascript"></script>
		<script src="${ctx}/platform/layout/borderlayout/js/TabUtils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js"></script>
		<script src="${ctx}/resources/js/SimpleUI/SimpleUI.js" type="text/javascript"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/bmp/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- FCK支持 -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/resources/FCKeditor/fckeditor.js" type="text/javascript"></script>

		<script type="text/javascript">
			window.addEvent('domready', function(){

				new FormCheck('add_form',{
					display:{
						showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
					},
					trimValue: true
				});
			});

			function doSave(){
				$('sub').click();
				$('sbm_button').style.display='none';
				$('sbm_button_hidden').style.display='';
				window.setTimeout("$('sbm_button').style.display=''",8000);
				window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
			 }
			function doBack() {
				window.location.href="${ctx}/net/secrecyBasic/secrecyBasic_list.action";
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
					<a class="pop_button" href="javascript:doBack();" id="sbm_button"><span>返 回</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="#" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="#" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content" style="width:99%;">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					${secrecyBasic.title }涉密网络基本情况统计表
				</div>
				<div class="panel_content panel_content_table">
					<c:if test="${flag eq 'add' }">
						<form id="add_form" action="<s:url namespace='/net/secrecyBasic' action='secrecyBasic_adding' includeParams='true'/>" method="post">
					</c:if>
					<c:if test="${flag eq 'edit' }">
						<form id="add_form" action="<s:url namespace='/net/secrecyBasic' action='secrecyBasic_editing' includeParams='true'/>" method="post">
						<input type="hidden" id="secrecyBasic.id" name="secrecyBasic.id" value="${secrecyBasic.id }"/>
						<input type="hidden" id="secrecyBasic.createOrgan.organId" name="secrecyBasic.createOrgan.organId" value="${secrecyBasic.createOrgan.organId}"/>
						<input type="hidden" id="secrecyBasic.createDepartment.departmentId" name="secrecyBasic.createDepartment.departmentId" value="${secrecyBasic.createDepartment.departmentId}"/>
						<input type="hidden" id="secrecyBasic.createTime" name="secrecyBasic.createTime" value="${secrecyBasic.createTime}"/>
						<input type="hidden" id="secrecyBasic.createPerson.userId" name="secrecyBasic.createPerson.userId" value="${secrecyBasic.createPerson.userId}"/>
						<input type="hidden" id="secrecyBasic.reportOrgan.organId" name="secrecyBasic.reportOrgan.organId" value="${secrecyBasic.reportOrgan.organId}"/>
						<input type="hidden" id="secrecyBasic.reportUser.userId" name="secrecyBasic.reportUser.userId" value="${secrecyBasic.reportUser.userId}"/>
						<input type="hidden" id="secrecyBasic.year" name="secrecyBasic.year" value="${secrecyBasic.year}"/>
					</c:if>
						<table class="content_table" border="0">
							<input type="hidden" id="secrecyBasic.title" name="secrecyBasic.title" value="${secrecyBasic.title}"/>
							<tr>
								<td align="right">单位：</td>
								<td align="left"><input type="text" id="secrecyBasic.reportOrgan.organName" name="secrecyBasic.reportOrgan.organName" value="${secrecyBasic.reportOrgan.organName }" readonly="readonly"/></td>
								<td align="right">填表人：</td>
								<td align="left"><input type="text" id="secrecyBasic.reportUser.userInfo.name" name="secrecyBasic.reportUser.userInfo.name" value="${secrecyBasic.reportUser.userInfo.name}" readonly="readonly"/></td>
								<td align="right">填报日期：</td>
								<td align="left">
									<input type="text" id="secrecyBasic.reportDate" name="secrecyBasic.reportDate" value="<fmt:formatDate value="${secrecyBasic.reportDate }" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
								</td>
							</tr>
						</table>
						<table class="content_table">
							<tr>
								<td align="center" width="20%">网络名称</td>
								<td colspan="2" align="left" width="80%"><input type="text" id="secrecyBasic.name" name="secrecyBasic.name" value="${secrecyBasic.name }" class="validate['required','length[100]']" style="width: 80%"/></td>
							</tr>
							<tr>
								<td align="center">网络密级</td>
								<td colspan="2" align="left">
									<input type="radio" id="secrecyBasic.secrecyLevel1" name="secrecyBasic.secrecyLevel" <c:if test="${secrecyBasic.secrecyLevel eq 1 }">checked="checked"</c:if> checked="checked" value="1"/>绝密
									<input type="radio" id="secrecyBasic.secrecyLevel2" name="secrecyBasic.secrecyLevel" <c:if test="${secrecyBasic.secrecyLevel eq 2 }">checked="checked"</c:if> value="2"/>机密
									<input type="radio" id="secrecyBasic.secrecyLevel3" name="secrecyBasic.secrecyLevel" <c:if test="${secrecyBasic.secrecyLevel eq 3 }">checked="checked"</c:if> value="3"/>秘密
								</td>
							</tr>
							<tr>
								<td rowspan="3" align="center">建设基本情况</td>
								<td align="left">
									<input type="radio" id="secrecyBasic.basic1" name="secrecyBasic.basic" <c:if test="${secrecyBasic.basic eq 1 }">checked="checked"</c:if> value="1" checked="checked"/>上级涉密网络延伸
								</td>
								<td rowspan="3" align="left">简要说明：<br/><textarea class="validate['length[100]']" id="secrecyBasic.basicExplanation" name="secrecyBasic.basicExplanation" cols="30" rows="5" style="width: 500px;">${secrecyBasic.basicExplanation }</textarea></td>
							</tr>
							<tr>
								<td align="left">
									<input type="radio" id="secrecyBasic.basic2" name="secrecyBasic.basic" <c:if test="${secrecyBasic.basic eq 2 }">checked="checked"</c:if> value="2"/>自行组网加入上级涉密网络
								</td>
							</tr>
							<tr>
								<td align="left">
									<input type="radio" id="secrecyBasic.basic3" name="secrecyBasic.basic" <c:if test="${secrecyBasic.basic eq 3 }">checked="checked"</c:if> value="3"/>自行建设的涉密网络
								</td>
							</tr>
							<tr>
								<td align="center" width="20%">安全保密责任机构</td>
								<td colspan="2" align="left" width="80%"><input type="text" id="secrecyBasic.safeSecrecyOrgan" name="secrecyBasic.safeSecrecyOrgan" class="validate['required','length[100]']" value="${secrecyBasic.safeSecrecyOrgan }" style="width: 80%"/></td>
							</tr>
							<tr>
								<td align="center" width="20%">运行维护机构</td>
								<td colspan="2" align="left" width="80%"><input type="text" id="secrecyBasic.workMaintainOrgan" name="secrecyBasic.workMaintainOrgan" class="validate['required','length[100]']" value="${secrecyBasic.workMaintainOrgan}" style="width: 80%"/></td>
							</tr>
							<tr>
								<td align="center" width="20%">“三员”情况</td>
								<td colspan="2" align="left" width="80%">
									<input type="radio" id="secrecyBasic.threePeople1" name="secrecyBasic.threePeople" <c:if test="${secrecyBasic.threePeople eq 1 }">checked="checked"</c:if> checked="checked" value="1"/>已指定
									<input type="radio" id="secrecyBasic.threePeople2" name="secrecyBasic.threePeople" <c:if test="${secrecyBasic.threePeople eq 0 }">checked="checked"</c:if>  value="0"/>未指定
								</td>
							</tr>
							<tr>
								<td align="center" width="20%">主要业务应用系统</td>
								<td colspan="2" align="left" width="80%"><input type="text" id="secrecyBasic.mainSystem" name="secrecyBasic.mainSystem" class="validate['required','length[100]']" value="${secrecyBasic.mainSystem}" style="width: 80%"/></td>
							</tr>
							<tr>
								<td align="center" width="20%">用户数量</td>
								<td colspan="2" align="left" width="80%"><input type="text" id="secrecyBasic.userNum" name="secrecyBasic.userNum" value="${secrecyBasic.userNum}" class="validate['required','digit','length[10]']" style="width: 80%"/></td>
							</tr>
							<tr>
								<td align="center" width="20%">网络使用范围</td>
								<td colspan="2" align="left" width="80%"><input type="text" id="secrecyBasic.netRange" name="secrecyBasic.netRange" value="${secrecyBasic.netRange}" class="validate['required','length[200]']" style="width: 80%"/></td>
							</tr>
							<tr>
								<td align="center" width="20%">网络终端数量</td>
								<td colspan="2" align="left" width="80%">
									规划共<input type="text" id="secrecyBasic.netTerminalGhNum" name="secrecyBasic.netTerminalGhNum" value="${secrecyBasic.netTerminalGhNum}" style="width: 20%" class="validate['required','digit','length[10]']"/>个；
									现接入<input type="text" id="secrecyBasic.netTerminalJrNum" name="secrecyBasic.netTerminalJrNum" value="${secrecyBasic.netTerminalJrNum}" style="width: 20%" class="validate['required','digit','length[10]']"/>个
								</td>
							</tr>
							<tr>
								<td rowspan="2" align="center" width="20%">现有安全保密技术防护措施</td>
								<td colspan="2" align="left" width="80%">
									<input type="checkbox" id="secrecyBasic.cushiIdcard" name="secrecyBasic.cushiIdcard" <c:if test="${secrecyBasic.cushiIdcard eq 1 }">checked="checked"</c:if> value="1"/>身份鉴定；
									<input type="checkbox" id="secrecyBasic.cushiVisitControl" name="secrecyBasic.cushiVisitControl" <c:if test="${secrecyBasic.cushiVisitControl eq 1 }">checked="checked"</c:if> value="1"   />访问控制；
									<input type="checkbox" id="secrecyBasic.cushiProcessControl" name="secrecyBasic.cushiProcessControl"  <c:if test="${secrecyBasic.cushiProcessControl eq 1 }">checked="checked"</c:if> value="1"  />流转控制
								</td>
							</tr>
							<tr>
								<td colspan="2" align="left" width="80%">
									<input type="checkbox" id="secrecyBasic.cushiSafe" name="secrecyBasic.cushiSafe" <c:if test="${secrecyBasic.cushiSafe eq 1 }">checked="checked"</c:if> value="1"  />安全审计；
									<input type="checkbox" id="secrecyBasic.cushiBianjie" name="secrecyBasic.cushiBianjie" <c:if test="${secrecyBasic.cushiBianjie eq 1 }">checked="checked"</c:if> value="1"  />边界防护；
									<input type="checkbox" id="secrecyBasic.cushiPassword" name="secrecyBasic.cushiPassword" <c:if test="${secrecyBasic.cushiPassword eq 1 }">checked="checked"</c:if> value="1"  />密码保护
								</td>
							</tr>
							<tr>
								<td align="center" width="20%">主机房地址</td>
								<td colspan="2" align="left" width="80%"><input type="text" id="secrecyBasic.address" name="secrecyBasic.address" value="${secrecyBasic.address }" style="width: 80%" class="validate['required','length[200]']"/></td>
							</tr>
							<tr>
								<td rowspan="2" align="center" width="20%">与其他网络连接情况</td>
								<td colspan="2" align="left" width="80%">
									<input type="radio" id="secrecyBasic.linkedNo1" name="secrecyBasic.linkedNo" <c:if test="${secrecyBasic.linkedNo eq 0 }">checked="checked"</c:if> checked="checked" value="0"/>无
								</td>
							</tr>
							<tr>
								<td colspan="2" align="left" width="80%">
									<input type="radio" id="secrecyBasic.linkedNo2" name="secrecyBasic.linkedNo" <c:if test="${secrecyBasic.linkedNo eq 1 }">checked="checked"</c:if> value="1"/>有，
									与<input type="text" id="secrecyBasic.linkedYesContent" name="secrecyBasic.linkedYesContent" value="${secrecyBasic.linkedYesContent }" style="width: 80%" class="validate['length[200]']"/>网络连接。
								</td>
							</tr>
							<tr>
								<td align="center">安全保密测评情况</td>
								<td colspan="2" align="left" width="80%">
									<input type="radio" id="secrecyBasic.safeSecrecyCeping1" name="secrecyBasic.safeSecrecyCeping" <c:if test="${secrecyBasic.safeSecrecyCeping eq 1 }">checked="checked"</c:if> value="1" checked="checked"/>通过测评
									<input type="radio" id="secrecyBasic.safeSecrecyCeping2" name="secrecyBasic.safeSecrecyCeping" <c:if test="${secrecyBasic.safeSecrecyCeping eq 2 }">checked="checked"</c:if> value="2" />测评中
									<input type="radio" id="secrecyBasic.safeSecrecyCeping3" name="secrecyBasic.safeSecrecyCeping" <c:if test="${secrecyBasic.safeSecrecyCeping eq 3 }">checked="checked"</c:if> value="3"/>未申报测评
								</td>
							</tr>
							<tr>
								<td align="center">保密行政管理部门审批情况</td>
								<td colspan="2" align="left" width="80%">
									<input type="radio" id="secrecyBasic.audit1" name="secrecyBasic.audit" <c:if test="${secrecyBasic.audit eq 1 }">checked="checked"</c:if> value="1"  checked="checked"/>通过审批
									<input type="radio" id="secrecyBasic.audit2" name="secrecyBasic.audit" <c:if test="${secrecyBasic.audit eq 2 }">checked="checked"</c:if> value="2" />审批中
									<input type="radio" id="secrecyBasic.audit3" name="secrecyBasic.audit" <c:if test="${secrecyBasic.audit eq 3 }">checked="checked"</c:if> value="3" />未申请审批
								</td>
							</tr>
						</table>
						<table class="content_table" border="0">
							<tr>
								<td align="left">
									<font color="red">注：1、“三员”指保密管理员、网络管理员和网络审计员<br/>
									&nbsp;&nbsp;&nbsp;&nbsp;2、每个涉密网络填写一张表</red>
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
		</div>

	</body>
</html>