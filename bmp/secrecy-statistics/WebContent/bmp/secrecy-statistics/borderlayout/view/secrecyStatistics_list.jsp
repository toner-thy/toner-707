<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>机关单位数据录入情况一览表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<s:actionmessage theme="messages"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
		$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
		$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

		$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
		$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
		$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			function doDetail(flag, organId){
				//window.parent.location.href = "${ctx}/bmp/secrecyworksummary/secrecyWorksummary_detail.action?flag=" + flag + "&organ.organId="+ organId +"&t_date=" + new Date().getTime();
			}

			function doPrint(){
			    var printUrl = "${ctx}/bmp/secrecyStatistics/secrecyStatistics_print.action";
				$ENV.dialog.open({
					url : printUrl,
					width : 0.8,
					height : 0.9,
					title : '${district.districtName }级机关单位数据录入情况一览表'
				});
			}
			function doExport(){
				window.location.href = "${ctx}/bmp/secrecyStatistics/secrecyStatistics_exportData.action?t_date=" + new Date().getTime();
			}
		</script>
	</head>

	<body >
		<div class="but_bar">
			<div class="left">
				<div class="pop_button_bar">
					<ap:operationbutton />
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
			<cp:start defaultTitle="保密工作概况简介" ctx="${ctx}" icoPath="/bmp/secrecy-worksummary/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','保密工作概况简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','保密工作概况查询');">查 询</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于保密工作概况
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_secrecy_work_summary"> </cpc:tc>
				</div>
			</cp:msg>
			<cp:search show="false" divId="cp002">
				<s:form action="secrecyStatistics_list.action" method="post" id="queryform" theme="simple">
					<table class="st" width="100%">
						<tr>
							<td class="tbLable fr">单位名称：</td>
							<td class="tbValue fl">
								<input type="text" id="organ.organName" name="organ.organName" value="${organ.organName }"/>
								<input type="hidden" id="district.districtCode" name="district.districtCode" value="${district.districtCode}"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="fc" style="border: 0px;">
								<div class="stBtnBar">
									<a class="pop_button" href="javascript:document.getElementById('queryform').submit();"><span>查 询</span></a>
								</div>
							</td>
						</tr>
					</table>
				</s:form>
			</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title">
						${district.districtName }级机关单位数据录入情况一览表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#attr.list.size>0">
						<c:set var="numGroupEnteringSum" value="0" />
						<c:set var="numGroupReprotSum" value="0" />
						<c:set var="numGroupMemberSum" value="0" />
						<c:set var="numSecrecyWorkOrganMemberSum" value="0" />
						<c:set var="numKeysectionEnteringSum" value="0" />
						<c:set var="numKeysectionReportSum" value="0" />
						<c:set var="numKeyPartEnteringSum" value="0" />
						<c:set var="numKeyPartReportSum" value="0" />
						<c:set var="numSecrecyPersonEnteringSum" value="0" />
						<c:set var="numSecrecyPersonReportSum" value="0" />
						<table border="1" style="width: 100%;text-align: center;" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="3">
								单位名称
								</td>
								<td colspan="4" >
								保密工作机构
								</td>
								<td rowspan="2" colspan="2">
								要害部门
								</td>
								<td rowspan="2" colspan="2">
								要害部位
								</td>
								<td rowspan="2" colspan="2">
								涉密人员
								</td>
							</tr>
							<tr>
								<td colspan="2" >机构信息</td>
								<td rowspan="2">
								机构成员
								</td>
								<td rowspan="2">
								保密办成员
								</td>
							</tr>
							<tr>
								<td>
								数据录入
								</td>
								<td>
								已上报
								</td>
								<td>
								数据录入
								</td>
								<td>
								已上报
								</td>
								<td>
								数据录入
								</td>
								<td>
								已上报
								</td>
								<td>
								数据录入
								</td>
								<td>
								已上报
								</td>
							</tr>
							<!-- 表体 -->
							<c:forEach items="${list }" var="dto">
								<tr>
									<td style="text-align: left;padding-left: 5px;">
									${dto.organName }
									</td>
									<td>
										${dto.numGroupEntering }
									</td>
									<td>
										${dto.numGroupReprot }
									</td>
									<td>
										${dto.numGroupMember }
									</td>
									<td>
										${dto.numSecrecyWorkOrganMember }
									</td>
									<td>
										${dto.numKeysectionEntering }
									</td>
									<td>
										${dto.numKeysectionReport }
									</td>
									<td>
										${dto.numKeyPartEntering }
									</td>
									<td>
										${dto.numKeyPartReport }
									</td>
									<td>
										${dto.numSecrecyPersonEntering }
									</td>
									<td>
										${dto.numSecrecyPersonReport }
									</td>
								</tr>
							</c:forEach>
						</table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无统计数据"/>
					</s:else>
				</div>
			</div>
		</div>
 	</body>
</html>