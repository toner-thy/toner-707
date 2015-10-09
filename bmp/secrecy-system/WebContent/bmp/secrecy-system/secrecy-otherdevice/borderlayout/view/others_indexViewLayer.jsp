<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
	<head>
		<title>首页其他涉密设备统计</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/bmp/borderlayout/skin/blue/css/index_stat_panel.css");
		</script>

		<script type="text/javascript">
 			function doSecrecyOthersTotalViewLayer( secrecyothersType) {
 				var action = "${ctx}/secrecysystem/secrecyothers/others_countData.action";
 				TabUtil.openAsTab({
 					//url : action + "?secrecyMobilestoragemedia.mediaType="+ mediaType + "&countType=${countType}" +"&t_date=" + new Date().getTime(),
 					url : action + "?secrecyOthers.secrecyothersType="+ secrecyothersType + "&countType=${countType}" +"&t_date=" + new Date().getTime(),
 					title : '其他涉密设备合计-列表'
 				});
 			}

 			function doSecrecyOthersCommonViewLayer(secrecyLevel, secrecyothersType){
 				var action ="${ctx}/secrecysystem/secrecyothers/others_countSubData.action";
 				TabUtil.openAsTab({
 					//url : action + '?secrecyMobilestoragemedia.secrecyLevel=' + secrecyLevel + "&countType=${countType}" + '&secrecyMobilestoragemedia.mediaType='+ mediaType +'&t_date='+ new Date().getTime(),
 					url : action + '?secrecyOthers.secrecyLevel=' + secrecyLevel + "&countType=${countType}" + '&secrecyOthers.secrecyothersType='+ secrecyothersType +'&t_date='+ new Date().getTime(),
 					title : '其他涉密设备-列表'
 				});
 			}

			// 快速搜索
			function doSecPersonView2(){
				/* var name = $('secrecyPerson.userInfo.name').value;
				TabUtil.openAsTab({
					url : '${ctx}/secrecyPerson_secPersonQueryData.action?secrecyPerson.userInfo.name='+name+'&t_date='+ new Date().getTime(),
					title : '涉密人员查询-详情'
				}); */
			}

			// 点击搜索框，提示消失
			function clrSecrecyPersonInput(){
				document.getElementById("secrecyPerson.userInfo.name").value = "";
			}
		</script>
	</head>

	<body>
		<!-- 注意！该面板要求高度150才不出现右侧滚动条 -->

		<!-- 设置合计数量 -->
		<c:set var="secrecyPersonNum" value="0" />

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
								<img src="${ctx}/bmp/images/index/others/ico.jpg">
							</td>
							<td class="td_img_right">
								<!-- 统计内容 -->
								<table style="width: 100%;" cellpadding="0" cellspacing="0">
									<tr>
										<td>&nbsp;</td>
										<c:forEach var="typeOption" items="${typeList }" >
											<td class="td_content_right">${typeOption.optionText }</td>
										</c:forEach>
									</tr>
									<c:if test="${not empty optionList}" >
										<c:forEach var="option" items="${optionList }">
											<tr>
											<td class="td_content_left">
												${option.optionText }:
											</td>
											<c:forEach var="typeOption" items="${typeList}" >
												<td class="td_content_right">
													<c:forEach var="aResultMap" items="${countMap }">
														<c:if test="${aResultMap.key eq option.optionValue }">
															<c:forEach var="aKeyValue" items="${aResultMap.value }">
																<c:if test="${aKeyValue.key eq typeOption.optionValue }">
																	<c:choose>
																		<c:when test="${aKeyValue.value eq 0 }">
																			0
																		</c:when>
																		<c:otherwise>
																			<a href="###" onclick="doSecrecyOthersCommonViewLayer('${aResultMap.key }','${aKeyValue.key }')">
																				${aKeyValue.value}
																			</a>
																		</c:otherwise>
																	</c:choose>
																</c:if>
															</c:forEach>
														</c:if>
													</c:forEach>
												</td>
											</c:forEach>
											</tr>
										</c:forEach>
									</c:if>
									<tr align="right">
										<td class="td_total_left">
											合 计:
										</td>
										<c:forEach var="typeOption" items="${typeList }" >
											<td class="td_total_right">
												<c:set var="totalNum" value="0" />
												<c:forEach var="aResultMap" items="${countMap }">
													<c:forEach var="aKeyValue" items="${aResultMap.value }">
														<c:if test="${aKeyValue.key eq typeOption.optionValue }">
															<c:set var="totalNum" value="${ totalNum + aKeyValue.value }" />
														</c:if>
													</c:forEach>
												</c:forEach>
												<c:choose>
												<c:when test="${totalNum eq 0}">
													0
												</c:when>
												<c:otherwise>
													<a href="###" onclick="doSecrecyOthersTotalViewLayer('${typeOption.optionValue}')">
														${totalNum }
													</a>
												</c:otherwise>
											</c:choose>
											</td>
										</c:forEach>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>