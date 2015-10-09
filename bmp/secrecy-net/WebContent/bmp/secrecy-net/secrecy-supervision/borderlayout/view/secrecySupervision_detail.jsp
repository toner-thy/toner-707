<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%
	request.setAttribute("vEnter", "\n");
%>

<div class="panel">
	<!-- 头部 -->
	<div class="panel_header">
		<!-- 标题 -->
		<div class="panel_title panel_titleListIco">
		区、县（市）网络保密监督管理工作机制及制度建设情况统计表
		</div>
	</div>
	<div class="panel_content panel_content_table">
		<form id="form_supervision_add" class="form" action="<s:url action="secrecySupervision_save" includeParams="true"/>" method="post">
			<div class="panel_content panel_content_table">
				<table class="content_table">
					<tr>
						<td >
							单位：（盖章）
						</td>
						<td colspan="1">
							${secrecySupervision.reportOrgan.organName }
						</td>
						<td>
							填表人：
						</td>
						<td colspan="1">
							${secrecySupervision.reportUser.userInfo.name }
						</td>
						<td>
							填报日期：
						</td>
						<td colspan="1">
							<fmt:formatDate value="${secrecySupervision.reportDate}" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<td rowspan="6">网络保密监督管理工作机制及制度建设情况</td>
						<td colspan="5">
							内部：<br/>
							<input type="checkbox" onclick="javascript:return false;" id="secrecySupervision.inPlatformDuty" name="secrecySupervision.inPlatformDuty" <c:if test="${secrecySupervision.inPlatformDuty eq 1 }">checked="checked"</c:if> value="1"/>平台值班制度
							<input type="checkbox" onclick="javascript:return false;" id="secrecySupervision.inCaseInvestigation" name="secrecySupervision.inCaseInvestigation" <c:if test="${secrecySupervision.inCaseInvestigation eq 1 }">checked="checked"</c:if> value="1"/>案件协同查办机制
							<input type="checkbox" onclick="javascript:return false;" id="secrecySupervision.inImportantOrganNet" name="secrecySupervision.inImportantOrganNet" <c:if test="${secrecySupervision.inImportantOrganNet eq 1 }">checked="checked"</c:if> value="1"/>重要涉密单位互联网接入口保密监测平台工作制度
							<br/>
							<input type="checkbox" onclick="javascript:return false;" id="secrecySupervision.inSecrecyComInterent" name="secrecySupervision.inSecrecyComInterent" <c:if test="${secrecySupervision.inSecrecyComInterent eq 1 }">checked="checked"</c:if> value="1"/>涉密计算机违规外联监控工作制度
							<input type="checkbox" onclick="javascript:return false;" id="secrecySupervision.inInternetMsgCheck" name="secrecySupervision.inInternetMsgCheck" <c:if test="${secrecySupervision.inInternetMsgCheck eq 1 }">checked="checked"</c:if> value="1"/>互联网信息保密检查工作制度
							<br/>
							<input type="checkbox" onclick="javascript:return false;" id="secrecySupervision.inSecrecyCheck" name="secrecySupervision.inSecrecyCheck" <c:if test="${secrecySupervision.inSecrecyCheck eq 1 }">checked="checked"</c:if> value="1"/>保密技术核查工作制度
							<input type="checkbox" onclick="javascript:return false;" id="inOther" name="inOther" <c:if test="${not empty secrecySupervision.inOther }">checked="checked"</c:if> value="1"/>其他：
							${secrecySupervision.inOther }
						</td>
					</tr>
					<tr>
						<td colspan="5">
							外部：<br/>
							<input onclick="javascript:return false;"  type="checkbox" id="secrecySupervision.outSociologySupervision" name="secrecySupervision.outSociologySupervision" <c:if test="${secrecySupervision.outSociologySupervision eq 1 }">checked="checked"</c:if> value="1"/>社会网站监管机制
							<input onclick="javascript:return false;"  type="checkbox" id="secrecySupervision.outIpUserMsg" name="secrecySupervision.outIpUserMsg" <c:if test="${secrecySupervision.outIpUserMsg eq 1 }">checked="checked"</c:if> value="1"/>IP地址用户信息查询机制
							<input onclick="javascript:return false;"  type="checkbox" id="secrecySupervision.outOrganReport" name="secrecySupervision.outOrganReport" <c:if test="${secrecySupervision.outOrganReport eq 1 }">checked="checked"</c:if> value="1"/>有关部门情况通报机制
							<br/>
							<input onclick="javascript:return false;"  type="checkbox" id="secrecySupervision.outInternetAccess" name="secrecySupervision.outInternetAccess" <c:if test="${secrecySupervision.outInternetAccess eq 1 }">checked="checked"</c:if> value="1"/>与工信部门互联网安全接入管理工作机制
							<input onclick="javascript:return false;"  type="checkbox" id="outOther" name="outOther" <c:if test="${not empty secrecySupervision.outOther }">checked="checked"</c:if> value="1"/>其他：
							${secrecySupervision.outOther }
						</td>
					</tr>
					<tr>
						<td colspan="5">
						 	<c:if test="${not empty secrecySupervision and not empty secrecySupervision.secrecySupervisionContentSet }" >
								<c:forEach var="sfc" items="${secrecySupervision.secrecySupervisionContentSet }" varStatus="status">
									${sfc.year }年，预算（
									${sfc.budget }
									）万元，实际使用（
									${sfc.actualUse }
									）万元，主要用于
									<input onclick="javascript:return false;"  type="checkbox" id="secSupConSet[${status.index }].buyCheckTools" name="secSupConSet[${status.index }].buyCheckTools" <c:if test="${sfc.buyCheckTools eq 1 }">checked="checked"</c:if> value="1"/>
									检查工具购买
									<input onclick="javascript:return false;"  type="checkbox" id="secSupConSet[${status.index }].platformConstruction" name="secSupConSet[${status.index }].platformConstruction" <c:if test="${sfc.platformConstruction eq 1 }">checked="checked"</c:if> value="1"/>
									平台建设
									<input onclick="javascript:return false;"  type="checkbox" id="secSupConSet[${status.index }].businessTrain" name="secSupConSet[${status.index }].businessTrain" <c:if test="${sfc.businessTrain eq 1 }">checked="checked"</c:if> value="1"/>
									业务培训
									<input onclick="javascript:return false;"  type="checkbox" id="secSupConSet[${status.index }].networkEvaluation" name="secSupConSet[${status.index }].networkEvaluation" <c:if test="${sfc.networkEvaluation eq 1 }">checked="checked"</c:if> value="1"/>
									网络测评
									<input onclick="javascript:return false;"  type="checkbox" id="other" name="other" <c:if test="${not empty sfc.other }">checked="checked"</c:if> value="1"/>其他：
									${sfc.other }
								</c:forEach>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="5">
							${secrecySupervision.year - 1 }年以来，开展网络核查（
							${secrecySupervision.netCheckTimes }
							）次，共核查网络（
							${secrecySupervision.netCheckNum }
							）个，检查测评涉密网络（
							${secrecySupervision.netEvaluationNum }
							）个。
							存在问题及意见和建议：
							<div>
								<pre>
									${fn:replace(secrecySupervision.netCheckAdvice, vEnter , '<br/>') }
								</pre>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="5">
							${secrecySupervision.year - 1 }年以来，开展网络保密检查（
							${secrecySupervision.netSecrecyCheckTimes }
							）次，共检查涉密网（
							${secrecySupervision.netSecrecyCheckNum }
							）个，非涉密网（
							${secrecySupervision.netNoneSecrecyNum }
							）个，涉密计算机（
							${secrecySupervision.computerSecrecyNum }
							）台，非涉密计算机（
							${secrecySupervision.computerNoneNum }
							）台，连接互联网计算机（
							${secrecySupervision.computerInternetNum }
							）台，涉密移动存储介质（
							${secrecySupervision.storageSecrecyNum }
							）个，非涉密移动存储介质（
							${secrecySupervision.stotageNoneNum }
							）个。
							1、涉密计算机违规连接互联网的（
							${secrecySupervision.errComputerInternetNum }
							）台；2、互联网计算机存储处理涉密信息的（
							${secrecySupervision.errInternetMsgNum }
							）台；3、感染特种木马的计算机（
							${secrecySupervision.errComputerTrojanNum }
							）台；
							4、移动存储介质交叉使用的（
							${secrecySupervision.errStorageExchangeUseNum }
							）个；5、受到党纪政纪处分的（
							${secrecySupervision.errPeoplePunishment }
							）人。
							存在意见和建议：
							<div>
								<pre>
									${fn:replace(secrecySupervision.errOtherAdvice, vEnter , '<br/>') }
								</pre>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="5">
							${secrecySupervision.year - 1 }年以来，检查处理违规外联报警信息（
							${secrecySupervision.warningMsgNum }
							）条，网站违规发布涉密或敏感信息（
							${secrecySupervision.webSecrecyMsgNum }
							）条。
							其他违规信息处理情况：
							 ${secrecySupervision.illegalDealMsg }
							存在问题及意见和建议：
							<div>
								<pre>
									${fn:replace(secrecySupervision.warningOtherAdvice, vEnter , '<br/>') }
								</pre>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
