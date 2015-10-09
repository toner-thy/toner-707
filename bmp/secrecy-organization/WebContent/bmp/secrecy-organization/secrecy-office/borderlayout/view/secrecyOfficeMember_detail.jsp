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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密办(保密局)成员详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">


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
		<div class="button_bar no_print">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>


		<div class="body_content">
			<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_list_ico">
						保密办（保密局）成员详情
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
			</div>



			<!-- 保密办(保密局)成员详情panel开始 -->
				<div class="panel tMargin" style="margin-top: -1px;">
					<div class="panel_content panel_content_table">
						<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
							<tr>
							<td class="fr">单位名称：</td>
							<td class="fl" colspan="3">
								<b>${secrecyOfficeMember.secrecyOffice.name}</b>
							</td>
						<tr/>
						<tr>
							<td class="fr">
								姓 名：
							</td>
							<td class="fl">
								<div style="margin-left: 8px">
									${secrecyOfficeMember.person.name }
								</div>
							</td>
							<td class="fr">
								性 别：
							</td>
							<td class="fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.person.sex == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.person.sex != null}">
										<dictionary:text tableCode="person" fieldCode="sex" optionValue="${secrecyOfficeMember.person.sex }"/>
								 	</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								民 族：
							</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.person.nation == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.person.nation != null}">
										<dictionary:text tableCode="person" fieldCode="nation" optionValue="${secrecyOfficeMember.person.nation}"/>
								 	</c:if>
								</div>
							</td>
							<td class="tbLable fr">
								出生年月：
							</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px; font-family: Arial," Times New Roman" !important;">
									<c:if test="${secrecyOfficeMember.person.birthDate == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.person.birthDate != null}">
										<s:date name="secrecyOfficeMember.person.birthDate" format="yyyy年MM月dd日" />
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								学 历：
							</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.person.learningLevel == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.person.learningLevel != null}">
										<dictionary:text tableCode="person" fieldCode="learning_level" optionValue="${secrecyOfficeMember.person.learningLevel}"/>
									</c:if>
								</div>
							</td>
							<td class="tbLable fr">
								身份证号：
							</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.person.identityCard == ''}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.person.identityCard != ''}">
										${secrecyOfficeMember.person.identityCard}
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">专 业：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<dictionary:text tableCode="person" fieldCode="specialty_code" optionValue="${secrecyOfficeMember.person.specialtyCode }"></dictionary:text>
								</div>
							</td>
							<td class="tbLable fr">政治面貌：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<dictionary:text tableCode="person" fieldCode="polity" optionValue="${secrecyOfficeMember.person.polity }"></dictionary:text>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">行政级别：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<dictionary:text tableCode="bmp" fieldCode="person_admin_level" optionValue="${secrecyOfficeMember.person.administrativeLevel }"></dictionary:text>
								</div>
							</td>
							<td class="tbLable fr">参加工作时间：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.firstWorkDate == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.firstWorkDate != null}">
										<div style="font-family: Arial, "Times New Roman" !important;">
											<s:date name="secrecyOfficeMember.firstWorkDate" format="yyyy年MM月dd日" />
										</div>
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">技术职称：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<dictionary:text tableCode="person" fieldCode="technic_title_level" optionValue="${secrecyOfficeMember.person.technicTitleLevel }"></dictionary:text>
								</div>
							</td>
							<td class="tbLable fr">是否专职：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<dictionary:text tableCode="bmp" fieldCode="sole_duty" optionValue="${secrecyOfficeMember.isSoleDuty }"></dictionary:text>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">其他职务：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.person.duty == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.person.duty != null}">
										${secrecyOfficeMember.person.duty}
									</c:if>
								</div>
							</td>
							<td class="tbLable fr">何时从事保密工作：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.secrecyWorkStart == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.secrecyWorkStart != null}">
										<div style="font-family: Arial, "Times New Roman" !important;">
											<s:date name="secrecyOfficeMember.secrecyWorkStart" format="yyyy年MM月dd日" />
										</div>
									</c:if>
								</div>
							</td>
						</tr>

						<tr>
							<td class="fr">
								部 门：
							</td>
							<td class="fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.person.department.departmentName == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.person.department.departmentName != null}">
										${secrecyOfficeMember.person.department.departmentName}
									</c:if>
								</div>
							</td>
							<td class="fr">
								岗 位：
							</td>
							<td class="fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.post == ''}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.post != ''}">
										${secrecyOfficeMember.post}
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="fr">
								涉密等级：
							</td>
							<td class="fl">
								<div style="margin-left: 8px">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${secrecyOfficeMember.secrecyPersonLevel}"></dictionary:text>
								</div>
							</td>
							<td class="fr">
								岗位分组：
							</td>
							<td class="fl">
								<div style="margin-left: 8px">
									<dictionary:text tableCode="person" fieldCode="post_type" optionValue="${secrecyOfficeMember.person.postType}"></dictionary:text>
								</div>
							</td>
						</tr>
						<tr>
							<td class="fr">
								编制：
							</td>
							<td class="fl">
								<div style="margin-left: 8px">
									<dictionary:text tableCode="person" fieldCode="staff" optionValue="${secrecyOfficeMember.person.staff}"></dictionary:text>
								</div>
							</td>
							<td class="fr">
								办公室电话：
							</td>
							<td class="fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.officePhone == ''}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.officePhone != ''}">
										${secrecyOfficeMember.officePhone}
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								手 机：
							</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyOfficeMember.person.mobile == ''}">
										暂未填写
									</c:if>
									<c:if test="${secrecyOfficeMember.person.mobile != ''}">
										${secrecyOfficeMember.person.mobile}
									</c:if>
								</div>
							</td>
							<td class="tbLable fr">
								人员排序：
							</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									${secrecyOfficeMember.sort}
								</div>
							</td>
						</tr>
						<tr>
							<td class="fr">个人简历：</td>
							<td class="fl" colspan="3">
								${secrecyOfficeMember.resume}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">备 注：</td>
							<td class="tbValue fl" colspan="3">
								${secrecyOfficeMember.remark}
							</td>
						</tr>

						</table>
					</div>
				</div>

			<!-- 保密办(保密局)成员详情panel结束 -->

		</div>
	</body>
</html>