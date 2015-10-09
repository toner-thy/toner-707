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
		市直机关保密队伍建设和保密培训情况登记表
		</div>
	</div>
	<div class="panel_content panel_content_table">
		<form id="form_found_add" class="form" action="<s:url action="secrecyFoundTrain_save" includeParams="true"/>" method="post">
			<div class="panel_content panel_content_table">
				<table class="content_table">
					<tr>
						<td >
							单位：（盖章）
						</td>
						<td colspan="2">
							${secrecyFoundTrain.reportOrgan.organName }
						</td>
						<td>
							填表人：
						</td>
						<td colspan="2">
							${secrecyFoundTrain.reportUser.userInfo.name }
						</td>
						<td>
							填报日期：
						</td>
						<td colspan="3">
							<fmt:formatDate value="${secrecyFoundTrain.reportDate}" pattern="yyyy-MM-dd"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: center" rowspan="3">
							保密队伍建设
						</td>
						<td style="text-align: center">
							保密干部人数
						</td>
						<td style="text-align: center" colspan="4">
							学历情况
						</td>
						<td style="text-align: center" colspan="2">
							专业情况
						</td>
						<td style="text-align: center" colspan="2">
							年龄段
						</td>
					</tr>
					<tr>
						<td  rowspan="2">
							${secrecyFoundTrain.secrecyCadreNum }
						</td>
						<td >
							博士生人数
						</td>
						<td >
							硕士生人数
						</td>
						<td >
							本科生人数
						</td>
						<td >
							大专及以下人数
						</td>
						<td >
							计算机及通信人员
						</td>
						<td >
							其他人数
						</td>
						<td >
							45岁以下人数
						</td>
						<td >
							45岁以上人数
						</td>
					</tr>
					<tr>
						<td >
							${secrecyFoundTrain.doctorNum }
						</td>
						<td >
							${secrecyFoundTrain.masterNum }
						</td>
						<td >
							${secrecyFoundTrain.undergraduateNum }
						</td>
						<td >
							${secrecyFoundTrain.juniorNum }
						</td>
						<td >
							${secrecyFoundTrain.signalCommuNum }
						</td>
						<td >
							${secrecyFoundTrain.otherNum }
						</td>
						<td >
							${secrecyFoundTrain.lessThanNum }
						</td>
						<td >
							${secrecyFoundTrain.greateThanNum }
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							保密培训
						</td>
						<td rowspan="1">
							培训情况
						</td>
						<td colspan="8">
						 	<c:if test="${not empty secrecyFoundTrain and not empty secrecyFoundTrain.secrecyFoundTrainContentSet }" >
								<c:forEach var="sfc" items="${secrecyFoundTrain.secrecyFoundTrainContentSet }" varStatus="status">
									${sfc.year }年，组织开展保密培训（${sfc.secrityTrainNum }）次，培训（${sfc.trainNum }）人次。
									<br/>
								</c:forEach>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>存在问题<br/>及意见<br/>和建议</td>
						<td colspan="8">
							<div>
								<pre>
								${fn:replace(secrecyFoundTrain.problemAdvice, vEnter , '<br/>') }
								</pre>
							</div>
						</td>
					</tr>
				</table>
				<!-- 附件 -->
				<div id="files_list"></div>

			</div>
		</form>
	</div>
</div>
