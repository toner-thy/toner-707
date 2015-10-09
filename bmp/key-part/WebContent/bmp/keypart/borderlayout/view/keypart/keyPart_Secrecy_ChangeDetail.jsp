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
		<title>保密要害部位密级变更详情</title>

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

	<body style="overflow-y:auto; ">
		<div class="panel_header no_print" >
			<div class="panel_title panel_titleListIco no_print">
				<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
					保密要害部位【${keyPartChange.keyPartId.department.departmentName}】密级变更详情
				</div>
			</div>
		</div>

		<div class="panel tMargin" style="margin-top: -1px;">
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print" >
					<div class="panel_title panel_titleListIco no_print">
						保密要害部位 - 密级变更详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">
								原密级：
							</td>
							<td class="tbValue fl" >
								<dictionary:text fieldCode="secrecy_level_section" optionValue="${keyPartChange.beforeLevel}" tableCode="bmp" ></dictionary:text>
							</td>
							<td class="tbLable fr">
								变更后密级：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_section" optionValue="${keyPartChange.afterLevel}" ></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								变更时间：
							</td>
							<td class="tbValue fl" >
								<s:date name="keyPartChange.changeDate" format="yyyy-MM-dd"/>
							</td>
							<td class="tbLable fr"  style="white-space: nowrap;">
								保密期限变更：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${keyPartChange.changeTimeState}" ></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="fr" style="white-space: nowrap;">
								变更原因：
							</td>
							<td class="fl" colspan="3">
								${keyPartChange.changeReason }
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="split_line"></div><!-- 分隔线 -->


			<!-- 保密要害部位panel开始 -->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						保密要害部位 - 基本信息
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">
								单位名称
							</td>
							<td class="tbValue fl" colspan="3">
								${part.organ.organName}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								部位名称
							</td>
							<td class="tbValue fl">
								${part.partName}
							</td>
							<td class="tbLable fr">
								主管部门
							</td>
							<td class="tbValue fl">
								${part.department.departmentName}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								涉密等级
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}"></dictionary:text>
							</td>
							<td class="tbLable fr"  style="white-space: nowrap;">
								主要负责人
							</td>
							<td class="tbValue fl">
								${part.person.name}
							</td>
						</tr>
						<tr>
							<td class="fr">
								涉密人员数量
							</td>
							<td class="fl" colspan="3">
								<label title="涉密人员数量由当前系统自动统计">${partPersonSize}</label>
							</td>
						</tr>
						<tr>
							<td class="fr">
								具体位置
							</td>
							<td class="fl" colspan="3">
								${part.location}
							</td>
						</tr>
						<tr>
							<td class="fr"   style="white-space: nowrap;">
								涉密工作事项及范围
							</td>
							<td class="fl" colspan="3">
								${part.secScope}
							</td>
						</tr>
						<tr>
							<td class="fr">
								技防措施
							</td>
							<td class="fl" colspan="3">
								${part.skillMeasure}
							</td>
						</tr>
					</table>
					<table class="content_table st no_print" width="100%;">
						<tr>
							<td class="tbLable fr">
								保密制度
							</td>
							<td class="fl" colspan="3">
								<attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" attachments="${attachments}" titleText="附件"></attach:view>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 保密要害部位panel结束 -->

		</div>
	</body>
</html>