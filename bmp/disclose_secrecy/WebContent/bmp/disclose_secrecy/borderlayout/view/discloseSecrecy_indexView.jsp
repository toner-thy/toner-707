<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>首页泄密案件(本单位)统计</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script type="text/javascript">
	$ENV.loader.loadStyleSheet("${ctx}/bmp/borderlayout/skin/blue/css/index_stat_panel.css");
</script>

<script type="text/javascript">
	function doPartsView(action, type) {
		TabUtil.openAsTab({
			url : action + "&t_date=" + new Date().getTime(),
			title : '泄密案件(本单位)合计-列表'
		});
	}
	//泄密案件
	function doView(filter,district) {
		var title = null;
		var url = null;
		if(district=="bendanwei"){
		if (filter == "all") {
			url = '${ctx}/bmp/discloseSecrecy/discloseSecrecy_list.action?t_date='
					+ new Date().getTime();
			title = '泄密案件(本单位)-列表';
		}
		if (filter == "绝密") {
			url = '${ctx}/bmp/discloseSecrecy/discloseSecrecy_list.action?discloseSecrecy.secrecyLevel=1&&t_date='
					+ new Date().getTime();
			title = '泄密案件(本单位)-绝密列表';
		}
		if (filter == "机密") {
			url = '${ctx}/bmp/discloseSecrecy/discloseSecrecy_list.action?discloseSecrecy.secrecyLevel=2&&t_date='
					+ new Date().getTime();
			title = '泄密案件(本单位)-机密列表';
		}
		if (filter == "秘密") {
			url = '${ctx}/bmp/discloseSecrecy/discloseSecrecy_list.action?discloseSecrecy.secrecyLevel=3&&t_date='
					+ new Date().getTime();
			title = '泄密案件(本单位)-秘密列表';
		}
		}
		if(district=="baomiju"){
			if (filter == "all") {
				url = '${ctx}/bmp/discloseSecrecy/discloseSecrecy_list.action?baomijuOrbendanwei=baomiju&&t_date='
						+ new Date().getTime();
				title = '泄密案件(保密局)-列表';
			}
			if (filter == "绝密") {
				url = '${ctx}/bmp/discloseSecrecy/discloseSecrecy_list.action?discloseSecrecy.secrecyLevel=1&&baomijuOrbendanwei=baomiju&&t_date='
						+ new Date().getTime();
				title = '泄密案件(保密局)-绝密列表';
			}
			if (filter == "机密") {
				url = '${ctx}/bmp/discloseSecrecy/discloseSecrecy_list.action?discloseSecrecy.secrecyLevel=2&&baomijuOrbendanwei=baomiju&&t_date='
						+ new Date().getTime();
				title = '泄密案件(保密局)-机密列表';
			}
			if (filter == "秘密") {
				url = '${ctx}/bmp/discloseSecrecy/discloseSecrecy_list.action?discloseSecrecy.secrecyLevel=3&&baomijuOrbendanwei=baomiju&&t_date='
						+ new Date().getTime();
				title = '泄密案件(保密局)-秘密列表';
			}
		}
		TabUtil.openAsTab({
			url : url,
			title : title
		});
	}

	//严重违规案件
	function doView2(filter,district) {

		var title = null;
		var url = null;
		if(district=="bendanwei"){
		if (filter == "all") {
			url = '${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list.action?t_date='
					+ new Date().getTime();
			title = '严重违规案件(本单位)-列表';
		}
		if (filter == "绝密") {
			url = '${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list.action?caseCriticalviolation.secrecyLevel=1&&t_date='
					+ new Date().getTime();
			title = '严重违规案件(本单位)-绝密列表';
		}
		if (filter == "机密") {
			url = '${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list.action?caseCriticalviolation.secrecyLevel=2&&t_date='
					+ new Date().getTime();
			title = '严重违规案件(本单位)-机密列表';
		}
		if (filter == "秘密") {
			url = '${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list.action?caseCriticalviolation.secrecyLevel=3&&t_date='
					+ new Date().getTime();
			title = '严重违规案件(本单位)-秘密列表';
		}
		}

		if(district=="baomiju"){
			if (filter == "all") {
				url = '${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list.action?baomijuOrbendanwei=baomiju&&t_date='
						+ new Date().getTime();
				title = '严重违规案件(保密局)-列表';
			}
			if (filter == "绝密") {
				url = '${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list.action?caseCriticalviolation.secrecyLevel=1&&baomijuOrbendanwei=baomiju&&t_date='
						+ new Date().getTime();
				title = '严重违规案件(保密局)-绝密列表';
			}
			if (filter == "机密") {
				url = '${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list.action?caseCriticalviolation.secrecyLevel=2&&baomijuOrbendanwei=baomiju&&t_date='
						+ new Date().getTime();
				title = '严重违规案件(保密局)-机密列表';
			}
			if (filter == "秘密") {
				url = '${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list.action?caseCriticalviolation.secrecyLevel=3&&baomijuOrbendanwei=baomiju&&t_date='
						+ new Date().getTime();
				title = '严重违规案件(保密局)-秘密列表';
			}
		}

		TabUtil.openAsTab({
			url : url,
			title : title
		});

	}

	// 点击搜索框，提示消失
	function clrPartInput() {
		document.getElementById("part.partName").value = "";
	}
</script>
</head>

<body>

	<table style="width: 95%;">

		<tr>
			<td style="" colspan="4">
				<table style="width: 100%;">
					<tr>
						<td align="right" width="90" valign="top">
							  <c:if test="${className eq 'DiscloseSecrecy' }">
						<!-- 图标 --> <img src="${ctx}/bmp/images/index/discloseSecrecy/ico.jpg">
							  </c:if>
							  <c:if test="${className ne 'DiscloseSecrecy' }">
						<!-- 图标 --> <img src="${ctx}/bmp/images/index/caseCriticalviolation/ico.jpg">
							  </c:if>
						</td>
						<td align="left" width="*">
							<!-- 统计内容 -->
							<table style="width: 100%;" cellpadding="0" cellspacing="0">
								<c:forEach items="${countSectionList}" var="countSection"
									varStatus="ifsStatus">
									<c:if test="${countSection.option_text ne '合计'}">
										<tr>
											<td class="td_content_left">${countSection.option_text}:
											</td>
											<td class="td_content_right">
											<c:if test="${countSection.fcount ne 0 }">
													<c:if test="${district eq 'baomiju' }">
                                                       <c:if test="${className eq 'DiscloseSecrecy' }">
															<a href="###"
																onclick="doView('${countSection.option_text}','${district}')">
																${countSection.fcount} </a>
														</c:if>
														<c:if
															test="${className eq 'CaseCriticalviolationAction' }">
															<a href="###"
																onclick="doView2('${countSection.option_text}','${district}')">
																${countSection.fcount} </a>
														</c:if>

													</c:if>
													<c:if test="${district eq 'bendanwei' }">
														<c:if test="${className eq 'DiscloseSecrecy' }">
															<a href="###"
																onclick="doView('${countSection.option_text}','${district}')">
																${countSection.fcount} </a>
														</c:if>
														<c:if
															test="${className eq 'CaseCriticalviolationAction' }">
															<a href="###"
																onclick="doView2('${countSection.option_text}','${district}')">
																${countSection.fcount} </a>
														</c:if>
													</c:if>
											</c:if>
											<c:if test="${countSection.fcount eq 0 }">
											${countSection.fcount}
											</c:if>
											</td>
										</tr>
									</c:if>
									<c:if test="${countSection.option_text eq '合计'}">
										<tr align="right">
											<td class="td_total_left">${countSection.option_text}:</td>
											<td class="td_total_right">
											<c:if test="${countSection.fcount ne 0 }">
													<c:if test="${district eq 'baomiju' }">
	                                                    <c:if test="${className eq 'DiscloseSecrecy' }">
															<a href="###"
																onclick="doView('all','${district}')">
																${countSection.fcount} </a>
														</c:if>
														<c:if
															test="${className eq 'CaseCriticalviolationAction' }">
															<a href="###"
																onclick="doView2('all','${district}')">
																${countSection.fcount} </a>
														</c:if>
													</c:if>

													<c:if test="${district eq 'bendanwei' }">
														<c:if test="${className eq 'DiscloseSecrecy' }">
															<a href="###" onclick="doView('all','${district}')">
																${countSection.fcount} </a>
														</c:if>
														<c:if
															test="${className eq 'CaseCriticalviolationAction' }">
															<a href="###" onclick="doView2('all','${district}')">
																${countSection.fcount} </a>
														</c:if>
													</c:if>
												</c:if>
											<c:if test="${countSection.fcount eq 0 }">
											${countSection.fcount}
											     </c:if>
											</td>
										</tr>
									</c:if>
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>