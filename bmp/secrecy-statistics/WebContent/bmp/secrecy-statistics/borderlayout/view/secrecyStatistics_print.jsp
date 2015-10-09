<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
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

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/public_js/utils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){

				});
			});

		</script>

  		<!--print 这个属性可以在打印时有效-->
		<style type="text/css" media="print">
			.no_print{display:none;}
			.PageNext{page-break-after: always;}
		</style>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="print_but_bar no_print">
			<div class="left">
				<div class="print_pop_button no_print">
					<a class="print_pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right no_print">
				<div class="print_pop_button no_print">
					<a class="print_pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="print_panel" style="border: 0px;">
			<div class="print_panel_content" style="border: 0px;">
				<table class="print_content_formTable" cellspacing="0" cellpadding="0">
					<tr>
						<td height="45" class="formTitle" align="center" colspan="11">
							<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
								${district.districtName }级机关单位数据录入情况一览表
							</div>
						</td>
					</tr>
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
							<tr style="font-weight: bolder;font-size: large;">
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
							<tr style="font-weight: bolder;font-size: large;">
								<td colspan="2" >机构信息</td>
								<td rowspan="2">
								机构成员
								</td>
								<td rowspan="2">
								保密办成员
								</td>
							</tr>
							<tr style="font-weight: bolder;font-size: large;">
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
								<c:set var="numGroupEnteringSum" value="${numGroupEnteringSum + dto.numGroupEntering }" />
								<c:set var="numGroupReprotSum" value="${numGroupReprotSum + dto.numGroupReprot }" />
								<c:set var="numGroupMemberSum" value="${numGroupMemberSum + dto.numGroupMember }" />
								<c:set var="numSecrecyWorkOrganMemberSum" value="${numSecrecyWorkOrganMemberSum + dto.numSecrecyWorkOrganMember }" />
								<c:set var="numKeysectionEnteringSum" value="${numKeysectionEnteringSum + dto.numKeysectionEntering }" />
								<c:set var="numKeysectionReportSum" value="${numKeysectionReportSum + dto.numKeysectionReport }" />
								<c:set var="numKeyPartEnteringSum" value="${numKeyPartEnteringSum + dto.numKeyPartEntering }" />
								<c:set var="numKeyPartReportSum" value="${numKeyPartReportSum + dto.numKeyPartReport }" />
								<c:set var="numSecrecyPersonEnteringSum" value="${numSecrecyPersonEnteringSum +dto.numSecrecyPersonEntering }" />
								<c:set var="numSecrecyPersonReportSum" value="${numSecrecyPersonReportSum + dto.numSecrecyPersonReport }" />
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
							<tr>
								<td style="text-align: left;padding-left: 5px;">统计结果：</td>
								<td>${numGroupEnteringSum }</td>
								<td>${numGroupReprotSum}</td>
								<td>${numGroupMemberSum}</td>
								<td>${numSecrecyWorkOrganMemberSum}</td>
								<td>${numKeysectionEnteringSum}</td>
								<td>${numKeysectionReportSum}</td>
								<td>${numKeyPartEnteringSum}</td>
								<td>${numKeyPartReportSum}</td>
								<td>${numSecrecyPersonEnteringSum}</td>
								<td>${numSecrecyPersonReportSum}</td>
							</tr>
						</table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无统计数据"/>
					</s:else>
			</div>
		</div>
	</body>
</html>