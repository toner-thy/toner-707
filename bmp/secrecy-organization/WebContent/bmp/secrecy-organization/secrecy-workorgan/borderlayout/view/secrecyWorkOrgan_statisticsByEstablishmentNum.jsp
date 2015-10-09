<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<script type="text/javascript">

$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

</script>


<c:set var="ctx" value="${pageContext.request.contextPath}"/>



	<!-- 内容开始 -->
		<s:if test="#request.organNumMap.size>0">
				<div class="eXtremeTable" >
					<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="30%">
						<thead>
							<tr>
								<td align="center" style="font-weight:bolder;color:black;">单位名称</td>
								<c:forEach var="numMap" items="${headMap }" >
									<td align="center" style="font-weight:bolder;color:black;">
										<c:set var="start" value="" />
										<c:set var="end" value="" />
										<c:forEach var="v" items="${numMap }" >
											<c:if test="${v.key eq 'start' }">
												<c:set var="start" value="${v.value }" />
											</c:if>
											<c:if test="${v.key eq 'end' }">
												<c:set var="end" value="${v.value }" />
											</c:if>
										</c:forEach>
										<c:if test="${start==0 or start==null }" >
												小于${end }人
										</c:if>
										<c:if test="${start>0 and not empty end }" >
											${start }人~${end }人
										</c:if>
										<c:if test="${start>0 and empty end }" >
											大于${start }人
										</c:if>
									</td>
								</c:forEach>
							</tr>
						</thead>
						<tbody class="tableBody">
							<c:forEach var="row" items="${organNumMap }" varStatus="st">
								<tr class="odd">
									<td align="center" >${ row.key}</td>
									<c:forEach var="numMap" items="${headMap }" >
										<td align="center" style="font-weight:bolder;color:black;">
										${v.value }
											<c:set var="start" value="" />
											<c:set var="end" value="" />
											<c:forEach var="v" items="${numMap }" >
												<c:if test="${v.key eq 'start' }">
													<c:set var="start" value="${v.value }" />
												</c:if>
												<c:if test="${v.key eq 'end' }">
													<c:set var="end" value="${v.value }" />
												</c:if>
											</c:forEach>
											<c:if test="${start==0 or start==null }" >
												<c:if test="${row.value < end }">
													是
												</c:if>
											</c:if>
											<c:if test="${start>0 and not empty end }" >
												<c:if test="${row.value > start and row.value < end }">
													是
												</c:if>
											</c:if>
											<c:if test="${start>0 and empty end }" >
												<c:if test="${row.value > start}">
													是
												</c:if>
											</c:if>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</s:if>
		<s:else>
			<u:noData text="暂无数据"/>
		</s:else>
	<!-- 内容结束 -->
