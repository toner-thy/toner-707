<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/bmp/borderlayout/skin/blue/css/index_stat_panel.css");
 			function doComputerView(action) {
 				TabUtil.openAsTab({
 					url : action + "&t_date=" + new Date().getTime(),
 					title : '涉密计算机-列表'
 				});
 			}

		</script>
		<!-- 注意！该面板要求高度150才不出现右侧滚动条 -->

		<table style="width: 95%;">

			<!-- 搜索框 (目前尚未实现搜索，所以暂时屏蔽搜索框)-->
			<!--
				<tr>
					<td style="height: 4px;" colspan="4"></td>
				</tr>
				<tr>
					<td style="width: 5px;"></td>
					<td align="right" style="color: #2F6DA3; float: right; padding-left: 7px; padding-bottom: 8px; padding-top: 2px; border-bottom: 1px dotted #99CCE8;">
						快速查询：
					</td>
					<td align="left" style="float: left; padding-bottom: 8px; border-bottom: 1px dotted #99CCE8;">
						<input type="text" id="secrecyPerson.userInfo.name" value="请输入姓名" onclick="clrSecrecyPersonInput();" style="color: #C6C6C6; height: 18px; width: 110px; border-bottom: 1px solid #7F9DB9; border-top: 1px solid #7F9DB9; border-left: 1px solid #7F9DB9; border-right: 0px; float: left; line-height: 19px; padding-left: 3px;"/><div onclick="doSecPersonView2();" title="点击快速搜索涉密人员" style="background: url('${ctx}/images/index/search/indexPanelSearchBtn.gif') 0px 0px no-repeat; height:20px;width: 24px;float: left; cursor: hand;"></div>
					</td>
					<td style="width: 5px;"></td>
				</tr>
			 -->

			<!-- 内容 -->
			<tr>
				<td style="" colspan="4">
					<table style="width: 100%;">
						<tr>
							<td	class="td_img_left">
								<!-- 图标 -->
								<img src="${ctx}/bmp/images/index/secrecyComputer/ico.jpg">
							</td>
							<td class="td_img_right">
								<c:if test="${flag eq false }">
									<!-- 统计内容 本单位 -->
									<table style="width: 100%;" cellpadding="0" cellspacing="0">
										<tr>
											<td class="">
												&nbsp;
											</td>
											<c:forEach items="${computerTypeList}" var="c">
												<td class="td_content_right">
													${c.optionText }
												</td>
											</c:forEach>
										</tr>
										<c:set var="computerTotal" value="0"></c:set>
										<c:set var="otherTotal" value="0"></c:set>
										<c:set var="fuwuqiTotal" value="0"></c:set>
										<c:forEach items="${results}" var="r">
											<tr>
												<td class="td_content_left">${r.secrecyLevel}:</td>
												<td class="td_content_right">
													<c:if test="${r['1'] == 0}">0</c:if>
													<c:if test="${r['1'] != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=1&secrecyComputer.secrecyLevel=${r.secrecyLevelValue}&fromQuery=0')">${r['1']}</a></c:if>
												</td>
												<td class="td_content_right">
													<c:if test="${r['2'] == 0}">0</c:if>
													<c:if test="${r['2'] != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=2&secrecyComputer.secrecyLevel=${r.secrecyLevelValue}&fromQuery=0')">${r['2']}</a></c:if>
												</td>
												<td class="td_content_right">
													<c:if test="${r['3'] == 0}">0</c:if>
													<c:if test="${r['3'] != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=3&secrecyComputer.secrecyLevel=${r.secrecyLevelValue}&fromQuery=0')">${r['3']}</a></c:if>
												</td>
											</tr>
											<c:set var="computerTotal" value="${computerTotal +  r['1']}"></c:set>
											<c:set var="otherTotal" value="${otherTotal +  r['2']}"></c:set>
											<c:set var="fuwuqiTotal" value="${fuwuqiTotal + r['3']}"></c:set>
										</c:forEach>
										<tr>
											<td class="td_total_left">
												合 计:
											</td>
											<td class="td_total_right">
												<c:if test="${computerTotal == 0}">0</c:if>
												<c:if test="${computerTotal != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=1&fromQuery=0')">${computerTotal}</a></c:if>
											</td>
											<td class="td_total_right">
												<c:if test="${otherTotal == 0}">0</c:if>
												<c:if test="${otherTotal != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=2&fromQuery=0')">${otherTotal}</a></c:if>
											</td>
											<td class="td_total_right">
												<c:if test="${fuwuqiTotal == 0}">0</c:if>
												<c:if test="${fuwuqiTotal != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=3&fromQuery=0')">${fuwuqiTotal}</a></c:if>
											</td>
										</tr>
									</table>
								</c:if>

								<c:if test="${flag eq true}">
									<!-- 统计内容 保密局 -->
									<table style="width: 100%;" cellpadding="0" cellspacing="0">
										<tr>
											<td class="">
												&nbsp;
											</td>
											<c:forEach items="${computerTypeList}" var="c">
												<td class="td_content_right">
													${c.optionText }
												</td>
											</c:forEach>
										</tr>
										<c:set var="computerTotal" value="0"></c:set>
										<c:set var="otherTotal" value="0"></c:set>
										<c:set var="fuwuqiTotal" value="0"></c:set>
										<c:forEach items="${results}" var="r">
											<tr>
												<td class="td_content_left">${r.secrecyLevel}:</td>
												<td class="td_content_right">
													<c:if test="${r['1'] == 0}">0</c:if>
													<c:if test="${r['1'] != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=1&secrecyComputer.secrecyLevel=${r.secrecyLevelValue}&checkLower=1')">${r['1']}</a></c:if>
												</td>
												<td class="td_content_right">
													<c:if test="${r['2'] == 0}">0</c:if>
													<c:if test="${r['2'] != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=2&secrecyComputer.secrecyLevel=${r.secrecyLevelValue}&checkLower=1')">${r['2']}</a></c:if>
												</td>
												<td class="td_content_right">
													<c:if test="${r['3'] == 0}">0</c:if>
													<c:if test="${r['3'] != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=3&secrecyComputer.secrecyLevel=${r.secrecyLevelValue}&checkLower=1')">${r['3']}</a></c:if>
												</td>
											</tr>
											<c:set var="computerTotal" value="${computerTotal +  r['1']}"></c:set>
											<c:set var="otherTotal" value="${otherTotal +  r['2']}"></c:set>
											<c:set var="fuwuqiTotal" value="${fuwuqiTotal + r['3']}"></c:set>
										</c:forEach>
										<tr>
											<td class="td_total_left">
												合 计:
											</td>
											<td class="td_total_right">
												<c:if test="${computerTotal == 0}">0</c:if>
												<c:if test="${computerTotal != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=1&checkLower=1')">${computerTotal}</a></c:if>
											</td>
											<td class="td_total_right">
												<c:if test="${otherTotal == 0}">0</c:if>
												<c:if test="${otherTotal != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=2&checkLower=1')">${otherTotal}</a></c:if>
											</td>
											<td class="td_total_right">
												<c:if test="${fuwuqiTotal == 0}">0</c:if>
												<c:if test="${fuwuqiTotal != 0}"><a href="###" onclick="doComputerView('${ctx}/bmp/secrecycomputer/secrecyComputer_list.action?secrecyComputer.computerType=3&checkLower=1')">${fuwuqiTotal}</a></c:if>
											</td>
										</tr>
									</table>
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>