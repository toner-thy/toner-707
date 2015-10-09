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
		<title>保密要害部门密级变更详情</title>

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

	<body style="overflow-y: auto;" >
		<div class="panel_header no_print" >
			<div class="panel_title panel_titleListIco no_print">
				<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
					保密要害部门【${keySectionChange.keySectionId.department.departmentName}】密级变更详情
				</div>
			</div>
		</div>

		<div class="panel tMargin" style="margin-top:-1px;">
		    <!-- 密级变更  begin -->
			<div class="panel tMargin" style="margin-top:-1px;">
				<div class="panel_header no_print" >
					<div class="panel_title panel_titleListIco no_print">
						保密要害部门 - 密级变更详情
					</div>
				</div>
				<div class="panel_content panel_content_table" style="overflow:hidden;">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">
								原密级：
							</td>
							<td class="tbValue fl" >
								<dictionary:text fieldCode="secrecy_level_section" optionValue="${keySectionChange.beforeLevel}" tableCode="bmp" ></dictionary:text>
							</td>
							<td class="tbLable fr">
								变更后密级：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_section" optionValue="${keySectionChange.afterLevel}" ></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								变更时间：
							</td>
							<td class="tbValue fl" >
								<s:date name="keySectionChange.changeDate" format="yyyy-MM-dd"  />
							</td>
							<td class="tbLable fr" style="white-space: nowrap;">
								保密期限变更：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${keySectionChange.changeTimeState}" ></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="fr" style="white-space: nowrap;">
								变更原因：
							</td>
							<td class="fl" colspan="3">
								${keySectionChange.changeReason }
							</td>
						</tr>
					</table>
				</div>
			</div>
			 <!-- 密级变更 end -->
			<div class="split_line"></div><!-- 分隔线 -->

			<!-- 保密要害部门签开始 -->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						保密要害部门 - 基本信息
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">
								单位名称：
							</td>
							<td class="tbValue fl" colspan="3">
								${keySection.organ.organName }
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								部门名称：
							</td>
							<td class="tbValue fl">
								${keySection.department.departmentName }
							</td>
							<td class="tbLable fr">
								负责人：
							</td>
							<td class="tbValue fl">
								${keySection.principal.name }
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								涉密等级：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_section" optionValue="${keySection.secrecyLevel }"></dictionary:text>

							</td>
							<td class="tbLable fr">
								联系电话：
							</td>
							<td class="tbValue fl">
								${keySection.phone }
							</td>
						</tr>
						<tr>
							<td class="fr">
								在编人员数量：
							</td>
							<td class="fl">
								${keySection.personNum }
							</td>
							<td class="fr" style="white-space: nowrap;">
								涉密人员数量：
							</td>
							<td class="fl" >
								当前部门有涉密人员【${secPersonNum }】人
							</td>
						</tr>
						<tr>
							<td class="fr" style="white-space: nowrap;">
								涉密工作事项及范围：
							</td>
							<td class="fl" colspan="3" >
								${keySection.secScope }
							</td>
						</tr>
					</table>
					<table class="content_table st" id="table_part_add" width="100%">
						<tr>
							<td class="tbLable fr" >
								保密制度：
							</td>
							<td class="fl" colspan="3">
								<attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" attachments="${attachments }" showTitle="false" />
							</td>
						</tr>
					</table>
				</div>
			</div>


		</div>
	</body>
</html>