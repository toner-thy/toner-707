<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>主页涉密涉外活动统计</title>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/bmp/borderlayout/skin/blue/css/index_stat_panel.css");
		</script>

		<script type="text/javascript">
			function doExtPidginsView(action, type) {
				TabUtil.openAsTab({
					url : action + "&t_date=" + new Date().getTime(),
					title : '涉密涉外活动合计-列表'
				});
			}

			function doExtPidginView(id,secrecyLevel){
				TabUtil.openAsTab({
					url : '${pageContext.request.contextPath}/externalPidgin_organExtPidginData.action?id='+id+'&secrecyLevel='+secrecyLevel+'&t_date='+ new Date().getTime(),
					title : '涉密涉外活动-列表'
				});
			}

			function doExtPidginView2(){
				var name = $('externalPidgin.externalPidginName').value;
				TabUtil.openAsTab({
					url : '${pageContext.request.contextPath}/externalPidgin_extPidginQueryData.action?externalPidgin.externalPidginName='+name+'&t_date='+ new Date().getTime(),
					title : '涉密涉外活动查询-详情'
				});
			}

			// 点击搜索框，提示消失
			function clrExternalPidginInput(){
				document.getElementById("externalPidgin.externalPidginName").value = "";
			}
		</script>

	</head>

	<body>
		<!-- 注意！该面板要求高度220才不出现右侧滚动条 -->

		<!-- 设置合计数量 -->
		<c:set var="externalPidginNum" value="${cosDto.externalPidginTopSecrectNum + cosDto.externalPidginNotSecrectNum + cosDto.externalPidginInnerSecrectNum + cosDto.externalPidginWorkSecrectNum + cosDto.externalPidginSecrectNum + cosDto.externalPidginConfidentialNum}" />

		<table style="width: 95%;">
			<!-- 搜索框 -->
			<tr>
				<td style="height: 4px;" colspan="4"></td>
			</tr>
			<tr>
				<td style="width: 5px;"></td>
				<td align="right" style="color: #2F6DA3; float: right; padding-left: 7px; padding-bottom: 8px; padding-top: 2px; border-bottom: 1px dotted #99CCE8;">
					快速查询：
				</td>
				<td align="left" style="float: left; padding-bottom: 8px; border-bottom: 1px dotted #99CCE8;">
					<input type="text" id="externalPidgin.externalPidginName" value="请输入活动名称" onclick="clrExternalPidginInput();" style="color: #C6C6C6; height: 18px; width: 110px; border-bottom: 1px solid #7F9DB9; border-top: 1px solid #7F9DB9; border-left: 1px solid #7F9DB9; border-right: 0px; float: left; line-height: 19px; padding-left: 3px;"/><div onclick="doExtPidginView2();" title="点击快速搜索涉密涉外活动" style="background: url('${pageContext.request.contextPath}/platform/images/index/panel/indexPanelSearchBtn.gif') 0px 0px no-repeat; height:20px;width: 24px;float: left; cursor: hand;"></div>
				</td>
				<td style="width: 5px;"></td>
			</tr>
			<!-- 内容 -->
			<tr>
				<td style="" colspan="4">
					<table style="width: 100%;">
						<tr>
							<td align="right" width="90" valign="top">
								<!-- 图标 -->
								<img src="${pageContext.request.contextPath}/bmp/images/index/externalPidgin/ico.jpg">
							</td>
							<td align="left" width="*">
								<!-- 统计内容 -->
								<table style="width: 100%;" cellpadding="0" cellspacing="0">
									<tr>
										<td style="color: #666666; padding-left: 5px;" width="60" align="right">
											非 密:
										</td>
										<td class="td_content_right">
											<c:if test="${cosDto.externalPidginNotSecrectNum eq 0}">
												0
											</c:if>
											<c:if test="${cosDto.externalPidginNotSecrectNum ne 0}">
												<a href="###" onclick="doExtPidginView('${organ.organId}','6')">
													${cosDto.externalPidginNotSecrectNum }
												</a>
											</c:if>
										</td>
									</tr>
									<tr>
										<td style="color: #666666; padding-left: 5px;" width="60" align="right">
											内 部:
										</td>
										<td class="td_content_right">
											<c:if test="${cosDto.externalPidginInnerSecrectNum eq 0}">
												0
											</c:if>
											<c:if test="${cosDto.externalPidginInnerSecrectNum ne 0}">
												<a href="###" onclick="doExtPidginView('${organ.organId}','5')">
													${cosDto.externalPidginInnerSecrectNum }
												</a>
											</c:if>
										</td>
									</tr>
									<tr>
										<td style="color: #666666; padding-left: 5px;" width="60" align="right">
											工作秘密:
										</td>
										<td class="td_content_right">
											<c:if test="${cosDto.externalPidginWorkSecrectNum eq 0}">
												0
											</c:if>
											<c:if test="${cosDto.externalPidginWorkSecrectNum ne 0}">
												<a href="###" onclick="doExtPidginView('${organ.organId}','4')">
													${cosDto.externalPidginWorkSecrectNum }
												</a>
											</c:if>
										</td>
									</tr>
									<tr>
										<td style="color: #666666; padding-left: 5px;" width="60" align="right">
											秘 密:
										</td>
										<td class="td_content_right">
											<c:if test="${cosDto.externalPidginSecrectNum eq 0}">
												0
											</c:if>
											<c:if test="${cosDto.externalPidginSecrectNum ne 0}">
												<a href="###" onclick="doExtPidginView('${organ.organId}','3')">
													${cosDto.externalPidginSecrectNum }
												</a>
											</c:if>
										</td>
									</tr>
									<tr>
										<td style="color: #666666; padding-left: 5px;" width="60" align="right">
											机 密:
										</td>
										<td class="td_content_right">
											<c:if test="${cosDto.externalPidginConfidentialNum eq 0}">
												0
											</c:if>
											<c:if test="${cosDto.externalPidginConfidentialNum ne 0}">
												<a href="###" onclick="doExtPidginView('${organ.organId}','2')">
													${cosDto.externalPidginConfidentialNum}
												</a>
											</c:if>
										</td>
									</tr>
									<tr>
										<td style="color: #666666;padding-left: 5px;" width="60" align="right">
											绝 密:
										</td>
										<td class="td_content_right">
											<c:if test="${cosDto.externalPidginTopSecrectNum eq 0}">
												0
											</c:if>
											<c:if test="${cosDto.externalPidginTopSecrectNum ne 0}">
												<a href="###" onclick="doExtPidginView('${organ.organId}','1')">
													${cosDto.externalPidginTopSecrectNum }
												</a>
											</c:if>
										</td>
									</tr>
									<tr align="right">
										<td style="color: #F27D00; padding-left: 5px; font-weight: bold; border-top: 1px solid #95B8C9;" width="60">
											合 计:
										</td>
										<td class="td_total_right">
											<c:if test="${externalPidginNum == 0}">
												0
											</c:if>
											<c:if test="${externalPidginNum != 0}">
												<a href="###" onclick="doExtPidginsView('${pageContext.request.contextPath }/externalPidgin_epList.action?district.districtCode=${organ.district.districtCode }', 'externalPidgin')">
													${externalPidginNum}
												</a>
											</c:if>
										</td>
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