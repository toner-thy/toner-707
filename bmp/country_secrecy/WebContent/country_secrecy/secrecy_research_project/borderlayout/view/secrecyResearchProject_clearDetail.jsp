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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密科研项目密级解除详情</title>

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

	<body style="overflow-y: auto;">
		<div class="panel_header no_print" >
			<div class="panel_title panel_titleListIco no_print">
				<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
					涉密科研项目【${secrecyResearchProjectClear.secrecyResearchProject.secrecyResearchProjectName }】密级解除详情
				</div>
			</div>
		</div>


		<div class="panel tMargin" style="margin-top: -1px;">

				<!-- 涉密科研项目  开始 -->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						涉密科研项目 - 基本信息
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr" style="white-space: nowrap;">
								涉密科研项目名称
							</td>
							<td class="tbValue fl" colspan="3">
								${secrecyResearchProject.secrecyResearchProjectName}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">定密负责人</td>
							<td class="tbValue fl">
								${secrecyResearchProject.formulateSecrecyPerson.name}
							</td>
							<td class="tbLable fr">密级</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyResearchProject.secrecyLevel}"></dictionary:text>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">保密期限</td>
							<td class="tbValue fl">
								<c:if test="${secrecyResearchProject.secrecyLimit!=null}">
									${secrecyResearchProject.secrecyLimit }
									<dictionary:text tableCode="bmp" fieldCode="limit_type" optionValue="${secrecyResearchProject.limitType}"></dictionary:text>
								</c:if>
							</td>
							<td class="tbLable fr">部门名称</td>
							<td class="tbValue fl" >
								${secrecyResearchProject.departId.departmentName}
							</td>
						</tr>

						<tr>
							<td class="fr" style="white-space: nowrap;">保密期限起</td>
							<td class="fl" >
								<s:date name="secrecyResearchProject.secrecyLimitBeginDate" format="yyyy-MM-dd" />
							</td>
							<td class="fr" style="white-space: nowrap;">保密期限止</td>
							<td class="fl" >
								<s:date name="secrecyResearchProject.secrecyLimitEndDate" format="yyyy-MM-dd" />
							</td>
						</tr>

						<tr>
							<td class="fr">项目负责人</td>
							<td class="fl" >
								${secrecyResearchProject.projectPerson.name}
							</td>
							<td class="fr">项目状态</td>
							<td class="fl" >
								<dictionary:text tableCode="bmp" fieldCode="project_state" optionValue="${secrecyResearchProject.projectState}"></dictionary:text>
							</td>
						</tr>

						<tr>
							<td class="fr" style="white-space: nowrap;">内容</td>
							<td class="fl" colspan="3">
								${secrecyResearchProject.content}
							</td>
						</tr>

					</table>
					<!--endprint1-->
				</div>
			</div>
			<!-- 涉密科研项目  结束 -->

			<div class="split_line"></div><!-- 分隔线 -->
			<!-- 密级变更  开始 -->
			<s:if test="#request.secrecyResearchProjectChangeList.size>0">
				<div class="panel tMargin" style="margin-top: -1px;">
					<div class="panel_header no_print">
						<div class="panel_title panel_titleListIco no_print">
							涉密科研项目 - 密级变更记录
						</div>
					</div>
					<div class="panel_content panel_content_table">
						<ec:table items="secrecyResearchProjectChangeList" var="secrecyResearchProjectChange"
							tableId="secrecyResearchProjectChangeList" border="0"
							action="${ctx}/bmp/secrecyResearchProject/secrecyResearchProject_change_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif" showPagination="false"
							width="100%" filterable="false" autoIncludeParameters="false" sortable="false" >
							<ec:row>
								<ec:column property="changeDate" title="变更时间" width="10%"  cell="date" format="yyyy-MM-dd" />
								<ec:column property="null" title="原密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyResearchProjectChange.beforeLevel}"/>
								</ec:column>
								<ec:column property="null" title="变更后密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyResearchProjectChange.afterLevel}"/>
								</ec:column>
								<ec:column property="null" title="保密期限变更" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${secrecyResearchProjectChange.changeTimeState}"/>
								</ec:column>
								<ec:column property="changeReason" title="变更原因" width="40%" cell="text" alias="size=45"/>
							</ec:row>
						</ec:table>
					</div>
				</div>
				<div class="split_line"></div><!-- 分隔线 -->
			</s:if>
			<!-- 密级变更  结束 -->


			<!-- 密级解除 开始-->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print" >
					<div class="panel_title panel_titleListIco no_print">
						涉密科研项目 - 密级解除详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">
								解除类型：
							</td>
							<td class="tbValue fl" >
								<dictionary:text tableCode="bmp" fieldCode="clear_secrecy_type" optionValue="${secrecyResearchProjectClear.clearType }"></dictionary:text>
							</td>
							<td class="tbLable fr">
								解除时间：
							</td>
							<td class="tbValue fl">
								<s:date name="secrecyResearchProjectClear.clearTime" format="yyyy-MM-dd" />
							</td>
						</tr>
						<tr>
							<td class="tbLable fr" style="white-space: nowrap;">
								解除前密级：
							</td>
							<td class="tbValue fl" colspan="3">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyResearchProjectClear.secrecyResearchProject.secrecyLevel}"/>
							</td>
						</tr>

						<tr>
							<td class="fr" style="white-space: nowrap;">
								解除原因：
							</td>
							<td class="fl" colspan="3" >
								${secrecyResearchProjectClear.cleanReason }
							</td>
						</tr>
					</table>
					<!--endprint1-->
					<!-- 打印标签 -->
				</div>
			</div>
			<!-- 密级解除 结束-->

		</div>

	</body>
</html>