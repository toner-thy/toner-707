<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>人员信息详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
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

	</head>

	<body>
		<!-- 公共头部 -->
		<div>
			<!-- 涉密人员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						涉密人员详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" width="100%">
						<tr>
							<td class="tbLable fr">单 位：</td>
							<td class="tbValue fl" colspan="3">
								<div style="margin-left: 8px">
									${organ.organName}
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">姓 名：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									${secrecyPerson.userInfo.name}
								</div>
							</td>
							<td class="tbLable fr">性 别：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<dictionary:text fieldCode="sex" optionValue="${secrecyPerson.userInfo.sex}"/>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">民 族：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.userInfo.nation == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.userInfo.nation != null}">
										<dictionary:text fieldCode="nation" optionValue="${secrecyPerson.userInfo.nation}"/>
								 	</c:if>
								 </div>
							</td>
							<td class="tbLable fr">出生年月：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.userInfo.birthDate == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.userInfo.birthDate != null}">
										<div style="font-family: Arial, "Times New Roman" !important;">
											<s:date name="secrecyPerson.userInfo.birthDate" format="yyyy年MM月dd日" />
										</div>
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">文化程度：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.userInfo.learningLevel == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.userInfo.learningLevel != null}">
										<dictionary:text fieldCode="learning_level" optionValue="${secrecyPerson.userInfo.learningLevel}"/>
									</c:if>
								</div>
							</td>
							<td class="tbLable fr">身份证号：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.userInfo.identityCard == ''}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.userInfo.identityCard != ''}">
										${secrecyPerson.userInfo.identityCard}
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">政治面貌：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${empty secrecyPerson.politicalType}">
										暂未填写
									</c:if>
									<c:if test="${not empty secrecyPerson.politicalType}">
										<dictionary:text tableCode="person" fieldCode="polity" optionValue="${secrecyPerson.politicalType}"/>
									</c:if>
								</div>
							</td>
							<td class="tbLable fr">参加工作时间：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.firstWorkDate == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.firstWorkDate != null}">
										<div style="font-family: Arial, "Times New Roman" !important;">
											<s:date name="secrecyPerson.firstWorkDate" format="yyyy年MM月dd日" />
										</div>
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">部 门：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.department.departmentName == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.department.departmentName != null}">
										${secrecyPerson.department.departmentName}
									</c:if>
								</div>
							</td>
							<td class="tbLable fr">职 务：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									${secrecyPerson.officeDuty}
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">岗 位：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.post == ''}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.post != ''}">
										${secrecyPerson.post}
									</c:if>
								</div>
							</td>
							<td class="tbLable fr">编 制：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.userInfo.staff == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.userInfo.staff != null}">
										<dictionary:text fieldCode="staff" optionValue="${secrecyPerson.userInfo.staff}"/>
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">涉密等级：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.secrecyPersonLevel == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.secrecyPersonLevel != null}">
										<dictionary:text fieldCode="secrecyLevel_secrecyPerson" optionValue="${secrecyPerson.secrecyPersonLevel}"/>
									</c:if>
								</div>
							</td>
							<td class="tbLable fr">办公室电话：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.officePhone == ''}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.officePhone != ''}">
										${secrecyPerson.officePhone}
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">签订保密责任书时间：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.secSignBookTime == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.secSignBookTime != null}">
										<div style="font-family: Arial, "Times New Roman" !important;">
											<s:date name="secrecyPerson.secSignBookTime" format="yyyy年MM月dd日" />
										</div>
									</c:if>
								</div>
							</td>
							<td class="tbLable fr">手 机：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.userInfo.mobile == ''}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.userInfo.mobile != ''}">
										<div style="font-family: Arial, "Times New Roman" !important;">
											${secrecyPerson.userInfo.mobile}
										</div>
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">最近一次接受保密教育时间：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.secUppostTime == null}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.secUppostTime != null}">
										<div style="font-family: Arial, "Times New Roman" !important;">
											<s:date name="secrecyPerson.secUppostTime" format="yyyy年MM月dd日" />
										</div>
									</c:if>
								</div>
							</td>
							<td class="tbLable fr">
								&nbsp;
							</td>
							<td class="tbValue fl">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td class="tbLable fr" valign="top">个人简历：</td>
							<td class="tbLable fl" colspan="3">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.resume == ''}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.resume != ''}">
										<div style="font-family: Arial, "Times New Roman" !important;">
											${secrecyPerson.resume}
										</div>
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">单位审查意见：</td>
							<td class="tbLable fl" colspan="3">
								<div style="margin-left: 8px">
									<c:if test="${secrecyPerson.organCheckOpinion == ''}">
										暂未填写
									</c:if>
									<c:if test="${secrecyPerson.organCheckOpinion != ''}">
										<div style="font-family: Arial, "Times New Roman" !important;">
											${secrecyPerson.organCheckOpinion}
										</div>
									</c:if>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 涉密人员panel结束 -->

		</div>
	</body>
</html>