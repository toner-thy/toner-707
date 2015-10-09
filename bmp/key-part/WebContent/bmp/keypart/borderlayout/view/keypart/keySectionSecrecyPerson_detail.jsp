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
		<title>涉密人员详情</title>

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

			// 返 回
			function doBackToKeySectionDetail(id){
				window.location.href="${ctx}/bmp/keySection/keySection_detail.action?keySection.keySectionId="+id;
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="but_bar no_print">
			<div class="left no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
					<a class="pop_button" href="###" onclick="doBackToKeySectionDetail('${keySection.keySectionId}')"><span>返 回</span></a>
				</div>
			</div>
			<div class="right no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<!-- 涉密人员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco no_print">
						保密要害部门【${secrecyPerson.department.departmentName}】 ─ 涉密人员【${secrecyPerson.userInfo.name}】详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" width="100%">
						<tr>
							<td colspan="4" height="45" class="formTitle" align="center">
								<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
									涉密人员【${secrecyPerson.userInfo.name}】信息
								</div>
							</td>
						</tr>
						<tr>
							<td class="fr" style="width: 20%">单 位</td>
							<td class="fl" colspan="3">
								<div style="margin-left: 8px">
									${organ.organName}
								</div>
							</td>
						</tr>
						<tr>
							<td class="fr" style="width: 20%">姓 名</td>
							<td class="fl">
								<div style="margin-left: 8px;width: 30%;">
									${secrecyPerson.userInfo.name}
								</div>
							</td>
							<td class="fr" style="width: 20%">性 别</td>
							<td class="fl" style="width: 30%">
								<div style="margin-left: 8px">
									<dictionary:text tableCode="person" fieldCode="sex" optionValue="${secrecyPerson.userInfo.sex}"/>
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
										<dictionary:text tableCode="person" fieldCode="nation" optionValue="${secrecyPerson.userInfo.nation}"/>
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
										<dictionary:text tableCode="person" fieldCode="learning_level" optionValue="${secrecyPerson.userInfo.learningLevel}"/>
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
										<dictionary:text tableCode="person" fieldCode="staff" optionValue="${secrecyPerson.userInfo.staff}"/>
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
										<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${secrecyPerson.secrecyPersonLevel}"/>
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
							<td class="tbLable fr">取得上岗证书时间：</td>
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
							<td class="tbLable fr">单位审查意见：</td>
							<td class="fl" colspan="3" valign="top" height="50;">
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

					<c:if test="${secrecyPerson.resume != ''}">
						<!-- 分页中间横线开始 -->
						<hr align="center" width="100%" size="1" noshade="noshade" class="no_print">
						<div class="PageNext"></div>
						<!-- 分页中间横线结束 -->
						<table align="center" class="print_content_formTable" style="word-break:break-word;word-wrap:break-word;" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="4" height="45" class="formTitle" align="center">
									<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
										个人简历
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<div style="width: 100%;margin-top: 15px;padding-top: 5px;line-height: 25px; font-family: Arial, 'Times New Roman' !important;" align="left">
										<c:out escapeXml="false" value="${secrecyPerson.resume}" />
									</div>
								</td>
							</tr>
						</table>
					</c:if>
				</div>
			</div>
			<!-- 涉密人员panel结束 -->

		</div>
	</body>
</html>