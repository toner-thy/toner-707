<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib prefix="secPersonlist" uri="http://www.cdthgk.com/tags/secrecyperson/list"%>
<%@ taglib prefix="nestedlist" uri="http://www.cdthgk.com/bmp/tags/nestedlist"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>泄密案件详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<style type="text/css" media="print">
			.no_print{display:none;}
		</style>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
		</script>

	</head>

	<body style="overflow: auto;overflow-x: hidden;">

		<div>
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print" style="display:none;">
					<div class="panel_title panel_titleListIco no_print">
						泄密案件【${discloseSecrecy.name}】详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td colspan="4" height="45" class="formTitle" align="center">
								<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
									泄密案件【${discloseSecrecy.name}】
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								部门名称：
							</td>
							<td class="tbValue fl" >
								${discloseSecrecy.departmentName}</td>
							<td class="tbLable fr">
								密&nbsp;&nbsp;&nbsp;&nbsp;级：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${discloseSecrecy.secrecyLevel}"></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								查处结果：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="find_result" optionValue="${discloseSecrecy.dealResult}"></dictionary:text>
							</td>
							<td class="tbLable fr">
								发生时间：
							</td>
							<td class="tbValue fl">
								<c:if test="${discloseSecrecy.findTime!=null}">
										<s:date name="discloseSecrecy.findTime" format="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${discloseSecrecy.findTime==null}">
										暂无
									</c:if>
							</td>
						</tr>
						<tr>
						    <td class="tbLable fr">
							    违规方式：
							</td>
							<td class="tbLable fl" colspan="3">
							<dictionary:text tableCode="bmp" fieldCode="case_Type" optionValue="${discloseSecrecy.caseType}"></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
							      案件性质：
							</td>
							<td class="tbLable fl" colspan="3">
								<dictionary:text tableCode="bmp" fieldCode="case_kind" optionValue="${discloseSecrecy.casekind}"></dictionary:text>
							</td>
						</tr>
						<!-- 附件 -->
							<tr>
								<td colspan="4">
									<div>
									    <attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" attachments="${attachmentList}" showTitle="false" />
				 					</div>
								</td>
							</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
			     <div class="panel_header no_print">
					<div class="panel_title panel_titleListIco">
						泄密案件【${discloseSecrecy.name}】密级变更详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.discloseSecrecy_changelist.size>0">
						<ec:table items="discloseSecrecy_changelist" var="discloseSecrecyChange" tableId="discloseSecrecy_changelist" border="0"
							action="${ctx}/bmp/discloseSecrecy/discloseSecrecyDetail.action?discloseSecrecy.disclosesecrecycaseId=${discloseSecrecy.disclosesecrecycaseId}"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" filterable="false" autoIncludeParameters="true" sortable="false" showPagination="false">
							<ec:row>
								<ec:column property="disclosesecrecycaseId.name" title="泄密案件" width="10%" cell="text" alias="size=15"/>
								<ec:column property="null" title="原密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${discloseSecrecyChange.beforeLevel}"/>
								</ec:column>
								<ec:column property="null" title="变更后密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${discloseSecrecyChange.afterLevel}"/>
								</ec:column>
								<ec:column property="null" title="保密期限变更" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${discloseSecrecyChange.changeTimeState}"/>
								</ec:column>
								<ec:column property="changeDate" title="变更时间" width="10%"  cell="date" format="yyyy-MM-dd"/>
								<ec:column property="changeReason" title="变更原因" width="10%" cell="text" alias="size=10"/>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无泄密案件密级变更历史记录。"/>
					</s:else>
				</div>
		 </div>
		</div>
	</body>
</html>