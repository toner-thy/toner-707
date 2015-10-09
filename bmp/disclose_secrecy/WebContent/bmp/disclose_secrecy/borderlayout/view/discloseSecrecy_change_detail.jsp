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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>泄密案件密级变更详情</title>

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

			function preview(oper){
				if (oper < 10)
					{
					bdhtml=window.document.body.innerHTML;//获取当前页的html代码
					sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域
					eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域

					prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html

					prnhtmlprnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html

					window.document.body.innerHTML=prnhtmlprnhtml;
					window.print();
					window.document.body.innerHTML=bdhtml;
				} else {
					window.print();
				}
			}
		</script>

	</head>

	<body style="overflow: auto;overflow-x: hidden;">
		<div>
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td colspan="4" height="45" class="formTitle" align="center">
								<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
									泄密案件【${discloseSecrecyChange.disclosesecrecycaseId.name}】密级变更详情
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								原&nbsp;&nbsp;密级：
							</td>
							<td class="tbValue fl" >
								<dictionary:text fieldCode="secrecy_level_thing" optionValue="${discloseSecrecyChange.beforeLevel}" tableCode="bmp" ></dictionary:text>
							</td>
							<td class="tbLable fr">
								变更后&nbsp;&nbsp;密级：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${discloseSecrecyChange.afterLevel}" ></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								变更时间：
							</td>
							<td class="tbValue fl" >
								<c:if test="${discloseSecrecyChange.changeDate!=null}">
										<s:date name="discloseSecrecyChange.changeDate" format="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${discloseSecrecyChange.changeDate==null}">
										暂无
								</c:if>
							</td>
							<td class="tbLable fr">
								保密期限变更：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${discloseSecrecyChange.changeTimeState}" ></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="fr">
								变更原因：
							</td>
							<td class="fl" colspan="3">
								<div style="word-break:break-all" >
		                            ${discloseSecrecyChange.changeReason }
								</div>
							</td>
						</tr>
					</table>
					<!--endprint1-->
					<!-- 打印标签 -->
				</div>
			</div>
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
			     <div class="panel_header no_print">
					<div class="panel_title panel_titleListIco">
						泄密案件【${discloseSecrecyChange.disclosesecrecycaseId.name}】详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.discloseSecrecy_list.size>0">
						<ec:table items="discloseSecrecy_list" var="discloseSecrecy" tableId="discloseSecrecy_list" border="0"
							action="${ctx}/bmp/discloseSecrecy/discloseSecrecyDetail.action?discloseSecrecy.disclosesecrecycaseId=${discloseSecrecy.disclosesecrecycaseId}"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" filterable="false" autoIncludeParameters="true" sortable="false" showPagination="false">
							<ec:row>
								<ec:column property="name" title="名称" width="15%" style="text-align: left;" cell="text" alias="size=15">
								</ec:column>
								<ec:column property="department.departmentName" title="部门名称" width="5%" style="text-align: left;">
								</ec:column>
								<ec:column property="null" title="密级" width="5%" style="text-align: left;">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${discloseSecrecy.secrecyLevel}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="发案形式" width="30%" style="text-align: left;" >
									<dictionary:text tableCode="bmp" fieldCode="case_Type" optionValue="${discloseSecrecy.caseType}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="责任单位性质" width="20%" style="text-align: left;" >
									<dictionary:text tableCode="bmp" fieldCode="duty_organ_kind" optionValue="${discloseSecrecy.dutyOrganKind}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="案件性质" width="10%" style="text-align: left;" >
									<dictionary:text tableCode="bmp" fieldCode="case_kind" optionValue="${discloseSecrecy.casekind}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="查处结果" width="15%" style="text-align: left;" >
									<dictionary:text tableCode="bmp" fieldCode="find_result" optionValue="${discloseSecrecy.dealResult}"></dictionary:text>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无泄密案件。"/>
					</s:else>
				</div>
		 </div>

		</div>
	</body>
</html>