<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
	<head>
		<title>首页统计保密要害部位(保密局)</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/bmp/borderlayout/skin/blue/css/index_stat_panel.css");
		</script>

		<script type="text/javascript">

 			function doPartView_District(id,secrecyLevel){
 				var titleText = "保密要害部位撰取-列表";
 				if(secrecyLevel=="-1") {
 					titleText = "保密要害部位合计-列表";
 				}
 				TabUtil.openAsTab({
 					url : '${ctx}/bmp/part/organPartData_District.action?id='+id+'&secrecyLevel='+secrecyLevel+'&t_date='+ new Date().getTime(),
 					title : titleText
 				});
 			}

			// 点击搜索框，提示消失
			function clrPartInput(){
				document.getElementById("part.partName").value = "";
			}
		</script>
	</head>

	<body>
		<!-- 注意！该面板要求高度150才不出现右侧滚动条 -->

		<!-- 设置合计数量 -->
		<c:set var="partNum" value="${keyPartArgs[0] + keyPartArgs[1] + keyPartArgs[2]}" />

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
						<input type="text" id="part.partName" value="请输入部位名称" onclick="clrPartInput();" style="color: #C6C6C6; height: 18px; width: 110px; border-bottom: 1px solid #7F9DB9; border-top: 1px solid #7F9DB9; border-left: 1px solid #7F9DB9; border-right: 0px; float: left; line-height: 19px; padding-left: 3px;"/><div onclick="doPartView_District2();" title="点击快速搜索要害部位" style="background: url('${ctx}/images/index/search/indexPanelSearchBtn.gif') 0px 0px no-repeat; height:20px;width: 24px;float: left; cursor: hand;"></div>
					</td>
					<td style="width: 5px;"></td>
				</tr>
			-->

			<!-- 内容 -->
			<tr>
				<td style="" colspan="4">
					<table style="width: 100%;">
						<tr>
							<td align="right" width="90" valign="top">
								<!-- 图标 -->
								<img src="${ctx}/bmp/images/index/keyPart/ico.jpg">
							</td>
							<td align="left" width="*">
								<!-- 统计内容 -->
								<table style="width: 100%;" cellpadding="0" cellspacing="0">
									<tr>
										<td class="td_content_left">
											一般:
										</td>
										<td class="td_content_right">
											<c:if test="${keyPartArgs[2] eq 0}">
												0
											</c:if>
											<c:if test="${keyPartArgs[2] ne 0}">
												<a href="###" onclick="doPartView_District('${organ.organId}','3')">
													${keyPartArgs[2]}
												</a>
											</c:if>
										</td>
									</tr>
									<tr>
										<td class="td_content_left">
											重要:
										</td>
										<td class="td_content_right">
											<c:if test="${keyPartArgs[1] eq 0}">
												0
											</c:if>
											<c:if test="${keyPartArgs[1] ne 0}">
												<a href="###" onclick="doPartView_District('${organ.organId}','2')">
													${keyPartArgs[1]}
												</a>
											</c:if>
										</td>
									</tr>
									<tr>
										<td class="td_content_left">
											核心:
										</td>
										<td class="td_content_right">
											<c:if test="${keyPartArgs[0] eq 0}">
												0
											</c:if>
											<c:if test="${keyPartArgs[0] ne 0}">
												<a href="###" onclick="doPartView_District('${organ.organId}','1')">
													${keyPartArgs[0]}
												</a>
											</c:if>
										</td>
									</tr>
									<tr align="right">
										<td class="td_total_left">
											合 计:
										</td>
										<td class="td_total_right">
											<c:if test="${partNum eq 0}">
												0
											</c:if>
											<c:if test="${partNum ne 0}">
												<a href="###" onclick="doPartView_District('${organ.organId}','-1')">
													${partNum}
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