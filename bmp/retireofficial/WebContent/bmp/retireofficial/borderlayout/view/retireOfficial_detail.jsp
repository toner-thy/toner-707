<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>离退休档案详情</title>

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){

				});
			});
		</script>
		<style type="text/css" media="print">
			.no_print{display:none;}
		</style>


	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar no_print">
			<div class="left">
				<div class=" pop_button_bar">
					<a class="print_pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right">
				<div class=" pop_button_bar ">
					<a class="print_pop_button print_pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="panel tMargin" style="margin-top: -1px;">
		   <div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						离退休档案 - 基本信息
					</div>
			</div>
			<div class="panel_content panel_content_table">
				<table class="content_table">
					<tr>
						<td colspan="6" height="45" class="formTitle" align="center">
							<div style="font-size:22px;  font-weight: bold; font-family: '楷体_gb2312';" align="center">
								离退休档案
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" align="right">姓&nbsp;&nbsp;名：</td>
						<td>
							${retireOfficial.name}
						</td>
						<td width="25%" align="right">性&nbsp;&nbsp;别：</td>
						<td>
							<dictionary:text tableCode="person" fieldCode="sex" optionValue="${retireOfficial.sex}"></dictionary:text>
						</td>
					</tr>
					<tr>
						<td align="right">出生年月：</td>
						<td>
							<s:date format="yyyy-MM-dd" name="retireOfficial.birthDate"/>

						</td>
						<td align="right">民&nbsp;&nbsp;族：</td>
						<td>
							<dictionary:text tableCode="person" fieldCode="nation"  optionValue="${retireOfficial.nation}"></dictionary:text>
						</td>
					</tr>
					<tr>
					   	<td align="right">籍&nbsp;&nbsp;贯：</td>
					   	<td>
					  	 		${retireOfficial.nativePlace}
					   	</td>
					  	<td align="right">身份证号码：</td>
					  	<td>${retireOfficial.idCard}</td>
					</tr>
					<tr>
						<td align="right">入党时间：</td>
						<td>
							<s:date format="yyyy-MM-dd" name="retireOfficial.joinPartyTime"/>
						</td>
						<td align="right">参加工作时间：</td>
						<td>
							<s:date format="yyyy-MM-dd" name="retireOfficial.startWorkTime"/>
						</td>
					</tr>
					<tr>
						<td align="right">离退休时间：</td>
						<td>
							<s:date format="yyyy-MM-dd" name="retireOfficial.retireTime"/>
						</td>
						<td align="right">离退休文号：</td>
						<td>
							${retireOfficial.retireCode}
						</td>
					</tr>
					<tr>
					    <td align="right">享受待遇/级别：</td>
					    <td>
					    	<dictionary:text fieldCode="retireLevel" tableCode="retireOfficial" optionValue="${retireOfficial.retireLevel}" ></dictionary:text>
					    </td>
					    <td align="right">离退休时职务：</td>
					    <td>
					    	${retireOfficial.retireOrganDuty}
					    </td>
					</tr>
					<tr>
					    <td align="right">月收入总额：</td>
					    <td>
					    	${retireOfficial.monthEarning}
					    </td>
					    <td align="right">基本离退休金：</td>
					    <td>
					    	${retireOfficial.basicRetireMoney}
					    </td>
					</tr>
					<tr>
					    <td align="right">生活补贴：</td>
					    <td>${retireOfficial.lifeSubsidy}</td>
					    <td align="right">补&nbsp;&nbsp;贴2：</td>
					    <td>${retireOfficial.subsidy2}</td>
					</tr>
					<tr>
					    <td align="right">粮&nbsp;&nbsp;贴：</td>
					    <td>${retireOfficial.foodSubsidy}</td>
					    <td align="right">身体状况：</td>
					    <td>
					    	<dictionary:text fieldCode="health" tableCode="retireOfficial" optionValue="${retireOfficial.health}"></dictionary:text>
					    </td>
					</tr>
					<tr>
					    <td align="right">住房面积：</td>
					    <td>
					    	${retireOfficial.addressSize}
					    </td>
					    <td align="right">固定电话：</td>
					    <td>
					    	${retireOfficial.phone}
					    </td>
					</tr>
					<tr>
					    <td align="right">移动电话：</td>
					    <td>
					    	${retireOfficial.mobile}
					    </td>
					    <td align="right">配偶姓名：</td>
					    <td>
					    	${retireOfficial.spouseName}
					    </td>
					</tr>
					<tr>
						<td align="right">配偶出生年月：</td>
						<td>
							<s:date format="yyyy-MM-dd" name="retireOfficial.spouseBirthDate"/>
						</td>
						<td align="right">配偶身体状况：</td>
						<td><dictionary:text fieldCode="health" tableCode="retireOfficial" optionValue="${retireOfficial.health}"></dictionary:text></td>
					</tr>
					<tr>
					    <td align="right">配偶原单位及职务：</td>
					    <td>
					    	${retireOfficial.spouseOrganDuty}
					    </td>
					    <td align="right">配偶目前状况：</td>
					    <td>
					    	${retireOfficial.spouseInfo}
					    </td>
					</tr>
					<tr>
					    <td align="right">家庭住址：</td>
					    <td colspan="3">
					    	${retireOfficial.address}
					    </td>
					</tr>
					<c:if test="${retireOfficial.personalRecord != ''}">
					<!-- 分页中间横线开始 -->
					<div class="PageNext"></div>
					<!-- 分页中间横线结束 -->
						<tr>
							<td colspan="4" height="45" class="formTitle" align="center">
								<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
									个人履历
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div style="width: 100%;margin-top: 10px;padding-top: 5px;line-height: 25px; font-family: Arial, 'Times New Roman' !important;" align="left">
									<c:out escapeXml="false" value="${retireOfficial.personalRecord}" />
								</div>
							</td>
						</tr>
				</c:if>
				</table>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>