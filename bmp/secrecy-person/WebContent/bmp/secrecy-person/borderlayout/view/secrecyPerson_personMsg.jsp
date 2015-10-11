<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>

		<div class="print_panel" style="border: 0px;">
			<div class="print_panel_content" style="border: 0px;">
				<table class="print_content_formTable" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="4" height="45" class="formTitle" align="center">
							<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
								涉密人员【${secrecyPerson.userInfo.name}】信息
							</div>
						</td>
					</tr>
					<tr>
						<td class="fr" style="width: 20%">单 位：</td>
						<td class="fl" colspan="3">
							<div style="margin-left: 8px">
								${secrecyPerson.organ.organName}
							</div>
						</td>
					</tr>
					<tr>
						<td class="fr" style="width: 20%">姓 名：</td>
						<td class="fl">
							<div style="margin-left: 8px;width: 30%;">
								${secrecyPerson.userInfo.name}
							</div>
						</td>
						<td class="fr" style="width: 20%">性 别：</td>
						<td class="fl" style="width: 30%">
							<div style="margin-left: 8px">
								<c:if test="${not empty secrecyPerson.userInfo.sex}">
									<dictionary:text tableCode="person" fieldCode="sex" optionValue="${secrecyPerson.userInfo.sex}"/>
								</c:if>
								<c:if test="${empty secrecyPerson.userInfo.sex}">
									暂未填写
								</c:if>
							</div>
						</td>
					</tr>
					<tr>
						<td class="fr">民 族：</td>
						<td class="fl">
							<div style="margin-left: 8px">
								<c:if test="${empty secrecyPerson.userInfo.nation}">
									暂未填写
								</c:if>
								<c:if test="${not empty secrecyPerson.userInfo.nation}">
									<dictionary:text tableCode="person" fieldCode="nation" optionValue="${secrecyPerson.userInfo.nation}"/>
							 	</c:if>
							 </div>
						</td>
						<td class="fr">出生年月：</td>
						<td class="fl">
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
						<td class="fr">文化程度：</td>
						<td class="fl">
							<div style="margin-left: 8px">
								<c:if test="${secrecyPerson.userInfo.learningLevel == null}">
									暂未填写
								</c:if>
								<c:if test="${secrecyPerson.userInfo.learningLevel != null}">
									<dictionary:text tableCode="person" fieldCode="learning_level" optionValue="${secrecyPerson.userInfo.learningLevel}"/>
								</c:if>
							</div>
						</td>
						<td class="fr">身份证号：</td>
						<td class="fl">
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
						<td class="fr">政治面貌：</td>
						<td class="fl">
							<div style="margin-left: 8px">
								<c:if test="${empty secrecyPerson.userInfo or empty secrecyPerson.userInfo.polity}">
									暂未填写
								</c:if>
								<c:if test="${not empty secrecyPerson.userInfo and not empty secrecyPerson.userInfo.polity}">
									<dictionary:text tableCode="person" fieldCode="polity" optionValue="${secrecyPerson.userInfo.polity}"/>
								</c:if>
							</div>
						</td>
						<td class="fr">参加工作时间：</td>
						<td class="fl">
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
						<td class="fr">部 门：</td>
						<td class="fl">
							<div style="margin-left: 8px">
								<c:if test="${secrecyPerson.department.departmentName == null}">
									暂未填写
								</c:if>
								<c:if test="${secrecyPerson.department.departmentName != null}">
									${secrecyPerson.department.departmentName}
								</c:if>
							</div>
						</td>
						<td class="fr">职 务：</td>
						<td class="fl">
							<div style="margin-left: 8px">
								${secrecyPerson.officeDuty}
							</div>
						</td>
					</tr>
					<tr>
						<td class="fr">岗 位：</td>
						<td class="fl">
							<div style="margin-left: 8px">
								<c:if test="${secrecyPerson.post == ''}">
									暂未填写
								</c:if>
								<c:if test="${secrecyPerson.post != ''}">
									${secrecyPerson.post}
								</c:if>
							</div>
						</td>
						<td class="tbLable fr">人员类型：</td>
						<td class="tbValue fl">
							<div style="margin-left: 8px">
							 <c:if test="${empty secrecyPerson.personType }">
							 	暂未填写
							 </c:if>
							 <c:if test="${not empty secrecyPerson.personType }">
								<dictionary:text tableCode="bmp" fieldCode="person_type" optionValue="${secrecyPerson.personType }" ></dictionary:text>
							 </c:if>
							</div>
						</td>
					</tr>

					<tr>
						<td class="tbLable fr">行政级别：</td>
						<td class="tbValue fl">
							<c:if test="${secrecyPerson.userInfo == null or secrecyPerson.userInfo.administrativeLevel == null }">
								暂未填写
							</c:if>
							<c:if test="${secrecyPerson.userInfo != null and secrecyPerson.userInfo.administrativeLevel != null }">
								<dictionary:text tableCode="bmp" fieldCode="person_admin_level" optionValue="${secrecyPerson.userInfo.administrativeLevel }"></dictionary:text>
							</c:if>
						</td>
						<td class="tbLable fr">技术职称：</td>
						<td class="tbValue fl">
							<c:if test="${secrecyPerson.userInfo == null or secrecyPerson.userInfo.technicTitleLevel == null }">
								暂未填写
							</c:if>
							<c:if test="${secrecyPerson.userInfo != null and secrecyPerson.userInfo.technicTitleLevel != null }">
									<dictionary:text tableCode="person" fieldCode="technic_title_level" optionValue="${secrecyPerson.userInfo.technicTitleLevel }"></dictionary:text>
							</c:if>
						</td>
					</tr>

					<tr>
						<td class="fr">涉密等级：</td>
						<td class="fl">
							<div style="margin-left: 8px">
								<c:if test="${secrecyPerson.secrecyPersonLevel == null}">
									暂未填写
								</c:if>
								<c:if test="${secrecyPerson.secrecyPersonLevel != null}">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${secrecyPerson.secrecyPersonLevel}"/>
								</c:if>
							</div>
						</td>
						<td class="fr">办公室电话：</td>
						<td class="fl">
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
						<td class="tbLable fr">是否属于要害部门工作人员：</td>
						<td class="tbValue fl">
							<div style="margin-left: 8px">
								<c:if test="${empty secrecyPerson.isSecrecyDepWorker }">
									暂未填写
								</c:if>
								<c:if test="${secrecyPerson.isSecrecyDepWorker == 1}">
									是
								</c:if>
								<c:if test="${secrecyPerson.isSecrecyDepWorker == 0 }">
									否
								</c:if>
							</div>
						</td>
						<td class="tbLable fr">是否为定密责任人：</td>
						<td class="tbValue fl">
							<div style="margin-left: 8px">
								<c:if test="${empty secrecyPerson.responsiblePerson }">
									暂未填写
								</c:if>
								<c:if test="${secrecyPerson.responsiblePerson == 0 }">
									否
								</c:if>
								<c:if test="${secrecyPerson.responsiblePerson == 1 }">
									是
								</c:if>
							</div>
						</td>
					</tr>

					<tr>
						<td class="fr" style="width: 30%">签订保密责任书时间：</td>
						<td class="fl">
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
						<td class="fr">手 机：</td>
						<td class="fl">
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
						<td class="fr" style="width: 30%">最近一次接受保密教育时间：</td>
						<td class="fl">
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
						<td class="fr">
							&nbsp;
						</td>
						<td class="fl">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="fr" style="width: 30%">单位审查意见：</td>
						<td class="fl" colspan="3" valign="top" height="50;">
							<div style="margin-left: 8px;">
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
			<!-- 涉密人员panel结束 -->
		</div>
