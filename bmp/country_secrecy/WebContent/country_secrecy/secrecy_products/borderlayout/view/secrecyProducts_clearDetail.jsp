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
		<title>密品密级解除详情</title>

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

	<body style="overflow-y:auto;">
		<div class="panel_header no_print" >
			<div class="panel_title panel_titleListIco no_print">
				<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
					密品【${secrecyProducts.secrecyproductsName }】密级解除详情
				</div>
			</div>
		</div>

		<div class="panel tMargin" style="margin-top: -1px;">
			<!-- 密品  开始 -->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						密品 - 基本信息
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">
								密品名称
							</td>
							<td class="tbValue fl" colspan="3">
								${secrecyProducts.secrecyproductsName}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr" style="white-space: nowrap;">
								定密负责人
							</td>
							<td class="tbValue fl">
								${secrecyProducts.formulatesecrecyPerson.name}
							</td>
							<td class="tbLable fr">
								密级
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyProducts.secrecyLevel}"></dictionary:text>
							</td>
						</tr>

						<tr>
							<td class="fr">
								部门名称
							</td>
							<td class="fl" >
								${secrecyProducts.departId.departmentName}
							</td>
							<td class="tbLable fr">
								保密期限
							</td>
							<td class="tbValue fl">
								<c:if test="${secrecyProducts.secrecyLimit!=null}">
									${secrecyProducts.secrecyLimit}
									<dictionary:text tableCode="bmp" fieldCode="limit_type" optionValue="${secrecyProducts.limitType}"></dictionary:text>
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="fr">
								保密期限起
							</td>
							<td class="fl" >
								<s:date name="secrecyProducts.secrecyLimitBegindate" format="yyyy-MM-dd"/>
							</td>
							<td class="fr" style="white-space: nowrap;">
								保密期限止
							</td>
							<td class="fl" >
								<s:date name="secrecyProducts.secrecyLimitEnddate" format="yyyy-MM-dd"/>
							</td>
						</tr>
						<tr>
							<td class="fr">
								内容
							</td>
							<td class="fl" colspan="3">
								${secrecyProducts.content}
							</td>
						</tr>

					</table>
					<!--endprint1-->
				</div>
			</div>
			<!-- 密品   结束 -->

			<div class="split_line"></div><!-- 分隔线 -->
			<!-- 变更记录  开始 -->
			<s:if test="#request.secrecyProductsChangeList.size>0">
				<div class="panel tMargin" style="margin-top: -1px;">
					<div class="panel_header no_print">
						<div class="panel_title panel_titleListIco no_print">
							密品 - 密级变更记录
						</div>
					</div>
					<div class="panel_content panel_content_table">
						<ec:table items="secrecyProductsChangeList" var="secrecyProductsChange" tableId="secrecyProductsChangeList" border="0"
							action="${ctx}/bmp/secrecyProducts/secrecyProducts_change_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif" showPagination="false"
							width="100%" filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="changeDate" title="变更时间" width="10%"  cell="date" format="yyyy-MM-dd" />
								<ec:column property="null" title="原密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyProductsChange.beforeLevel}"/>
								</ec:column>
								<ec:column property="null" title="变更后密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyProductsChange.afterLevel}"/>
								</ec:column>
								<ec:column property="null" title="保密期限变更" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${secrecyProductsChange.changeTimeState}"/>
								</ec:column>
								<ec:column property="changeReason" title="变更原因" width="40%" cell="text" alias="size=45"/>
							</ec:row>
						</ec:table>
					</div>
				</div>
				<div class="split_line"></div><!-- 分隔线 -->
			</s:if>
			<!-- 变更记录  结束 -->

			<!-- 解除  开始 -->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						密品 - 密级解除情况
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">
								解除类型：
							</td>
							<td class="tbValue fl" >
								<dictionary:text tableCode="bmp" fieldCode="clear_secrecy_type" optionValue="${secrecyProductsClear.clearType }"></dictionary:text>
							</td>
							<td class="tbLable fr" style="white-space: nowrap;">
								解除时间：
							</td>
							<td class="tbValue fl">
								<s:date name="secrecyProductsClear.clearTime" format="yyyy-MM-dd"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr" style="white-space: nowrap;">
								解除前密级：
							</td>
							<td class="tbValue fl" colspan="3">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyProductsClear.secrecyProducts.secrecyLevel}"/>
							</td>
						</tr>

						<tr>
							<td class="fr" style="white-space: nowrap;">
								解除原因：
							</td>
							<td class="fl" colspan="3" >
								${secrecyProductsClear.cleanReason }
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 解除  结束 -->
		</div>
	</body>


</html>