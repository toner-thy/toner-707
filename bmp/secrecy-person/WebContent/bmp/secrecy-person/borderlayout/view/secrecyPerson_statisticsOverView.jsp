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
<script type="text/javascript">
</script>

	<!-- 内容开始 -->
		<s:if test="#request.currentView.size>0">
				<div class="eXtremeTable" style="overflow: auto;">
						<table border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%" >
							<thead>
								<tr>
									<td align="center" >&nbsp;</td>
									<td colspan="4" align="center" >直辖单位</td>
									<td colspan="4" align="center" >行政区内</td>
								</tr>
								<tr height="36px;" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td>行政区划</td>
									<td>核心</td>
									<td>重要</td>
									<td>一般</td>
									<td>合计</td>
									<td>核心</td>
									<td>重要</td>
									<td>一般</td>
									<td>合计</td>
								</tr>
							</thead>
						<tbody class="tableBody">
							<c:forEach var="current" items="${currentView }">
								<tr class="odd">
									<td nowrap="nowrap">${current.key }</td>
									<c:forEach var="dataMaps" items="${current.value }">
										<c:set var="sum1" value="0" />
										<c:set var="sum2" value="0" />
										<c:if test="${dataMaps.key eq '直机构' }">
											<c:forEach var="num" begin="1" end="3" step="1">
												<td nowrap="nowrap">
													<c:set var="flag" value="1" />
													<c:forEach var="mapValue" items="${dataMaps.value }">
														<c:if test="${mapValue.key == num }">
															${mapValue.value }
															<c:set var="flag" value="0" />
															<c:set var="sum1" value="${sum1 + mapValue.value }" />
														</c:if>
													</c:forEach>
													<c:if test="${flag == 1 }">
														0
													</c:if>
												</td>
											</c:forEach>
											<td>${sum1 }</td>
										</c:if>
										<c:if test="${dataMaps.key eq '行政区内' }">
											<c:forEach var="num" begin="1" end="3" step="1">
												<td nowrap="nowrap">
													<c:set var="flag" value="1" />
													<c:forEach var="mapValue" items="${dataMaps.value }">
														<c:if test="${mapValue.key == num }">
															${mapValue.value }
															<c:set var="flag" value="0" />
															<c:set var="sum2" value="${sum2 + mapValue.value }" />
														</c:if>
													</c:forEach>
													<c:if test="${flag == 1 }">
														0
													</c:if>
												</td>
											</c:forEach>
											<td>${sum2 }</td>
										</c:if>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</s:if>
		<s:if test="#request.distinctView.size>0">
				<div class="eXtremeTable" >
						<table border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%" >
							<thead>
								<tr>
									<td align="center" >&nbsp;</td>
									<td colspan="4" align="center" >直辖单位</td>
									<td colspan="4" align="center" >行政区内</td>
								</tr>
								<tr height="36px;" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td>行政区划</td>
									<td>核心</td>
									<td>重要</td>
									<td>一般</td>
									<td>合计</td>
									<td>核心</td>
									<td>重要</td>
									<td>一般</td>
									<td>合计</td>
								</tr>
							</thead>
						<tbody class="tableBody">
							<c:forEach var="current" items="${distinctView }">
								<tr class="odd">
									<td nowrap="nowrap">${current.key }</td>
									<c:forEach var="dataMaps" items="${current.value }">
										<c:set var="sum1" value="0" />
										<c:set var="sum2" value="0" />
										<c:if test="${dataMaps.key eq '直机构' }">
											<c:forEach var="num" begin="1" end="3" step="1">
												<td nowrap="nowrap">
													<c:set var="flag" value="1" />
													<c:forEach var="mapValue" items="${dataMaps.value }">
														<c:if test="${mapValue.key == num }">
															${mapValue.value }
															<c:set var="flag" value="0" />
															<c:set var="sum1" value="${sum1 + mapValue.value }" />
														</c:if>
													</c:forEach>
													<c:if test="${flag == 1 }">
														0
													</c:if>
												</td>
											</c:forEach>
											<td>${sum1 }</td>
										</c:if>
										<c:if test="${dataMaps.key eq '行政区内' }">
											<c:forEach var="num" begin="1" end="3" step="1">
												<td nowrap="nowrap">
													<c:set var="flag" value="1" />
													<c:forEach var="mapValue" items="${dataMaps.value }">
														<c:if test="${mapValue.key == num }">
															${mapValue.value }
															<c:set var="flag" value="0" />
															<c:set var="sum2" value="${sum2 + mapValue.value }" />
														</c:if>
													</c:forEach>
													<c:if test="${flag == 1 }">
														0
													</c:if>
												</td>
											</c:forEach>
											<td>${sum2 }</td>
										</c:if>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</s:if>
		<s:if test="#request.distinctView.size>0 && #request.distinctView.size>0">

		</s:if>
		<s:else>
			<u:noData text="暂无数据"/>
		</s:else>
	<!-- 内容结束 -->
