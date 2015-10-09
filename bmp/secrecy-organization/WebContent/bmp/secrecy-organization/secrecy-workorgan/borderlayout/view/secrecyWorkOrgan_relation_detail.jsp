<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>单位保密工作机构成员</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
			});

		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="print_pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div>
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						单位保密工作机构成员详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td colspan="4" height="45" class="formTitle" align="center">
								<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
									单位保密工作机构成员详情
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								机构职务：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrganRelationMember.personGroupPosition.positionName}
							</td>
							<td class="tbLable fr">
								姓 名：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrganRelationMember.person.name}
							</td>
						<tr>
						<tr>
							<td class="tbLable fr">
								部门名称：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrganRelationMember.person.department.departmentName}
							</td>
							<td class="tbLable fr">
								行政职务：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrganRelationMember.person.duty}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">性别：</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="person" fieldCode="sex" optionValue="${secrecyWorkOrganRelationMember.person.sex }"></dictionary:text>
							</td>
							<td class="tbLable fr">出生年月：</td>
							<td class="tbValue fl">
								<s:date name='#attr.secrecyWorkOrganRelationMember.person.birthDate' format='yyyy-MM-dd'/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">学历：</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="person" fieldCode="learning_level" optionValue="${secrecyWorkOrganRelationMember.person.learningLevel }"></dictionary:text>
							</td>
							<td class="tbLable fr">政治面貌：</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="person" fieldCode="polity" optionValue="${secrecyWorkOrganRelationMember.person.polity}" ></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">专业：</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="person" fieldCode="specialty_code" optionValue="${secrecyWorkOrganRelationMember.person.specialtyCode }" ></dictionary:text>
							</td>
							<td class="tbLable fr">行政级别：</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="person_admin_level" optionValue="${secrecyWorkOrganRelationMember.person.administrativeLevel }" ></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">技术职称：</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="person" fieldCode="technic_title_level" optionValue="${secrecyWorkOrganRelationMember.person.technicTitleLevel }" ></dictionary:text>
							</td>
							<td class="tbLable fr">何时从事保密工作：</td>
							<td class="tbValue fl">
								<s:date name='#attr.secrecyWorkOrganRelationMember.secrecyWorkStart' format='yyyy-MM-dd'/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">是否专职：</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="sole_duty" optionValue="${secrecyWorkOrganRelationMember.isSoleDuty }" ></dictionary:text>
							</td>
							<td class="tbLable fr">其他职务：</td>
							<td class="tbValue fl">
								${secrecyWorkOrganRelationMember.otherDuty }
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">手机：</td>
							<td class="tbValue fl">
								${secrecyWorkOrganRelationMember.person.mobile }
							</td>
							<td class="tbLable fr">
								联系座机电话：
							</td>
							<td class="tbValue fl">
								${secrecyWorkOrganRelationMember.person.phone}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">人员排序：</td>
							<td class="tbValue fl">
								${secrecyWorkOrganRelationMember.sort}
							</td>

						</tr>
						<tr>
							<td class="tbLable fr">
								备 注：
							</td>
							<td class="tbValue fl" colspan="3" height="100" valign="top">
								${secrecyWorkOrganRelationMember.remark}
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>