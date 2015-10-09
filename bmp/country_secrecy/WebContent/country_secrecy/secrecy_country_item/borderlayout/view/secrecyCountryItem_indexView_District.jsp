<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
	<head>
		<title>首页国家秘密事项统计</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/bmp/borderlayout/skin/blue/css/index_stat_panel.css");
		</script>

		<script type="text/javascript">

 			function doSecrecyCountryItemView(id,secrecyLevel){
 				var titleText = "国家秘密事项撰取-列表";
 				if(secrecyLevel=="-1") {
 					titleText = "国家秘密事项合计-列表";
 				}
 				TabUtil.openAsTab({
 					url : '${ctx}/bmp/secrecycountryitem/indexView_DetailList_District.action?layer='+id+'&secrecyLevel='+secrecyLevel+'&t_date='+ new Date().getTime(),
 					title : titleText
 				});
 			}


			// 点击搜索框，提示消失
			function clrSecrecyCountryItemInput(){
				document.getElementById("part.partName").value = "";
			}
		</script>
	</head>

	<body>
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
						<input type="text" id="part.partName" value="请输入部位名称" onclick="clrSecrecyCountryItemInput();" style="color: #C6C6C6; height: 18px; width: 110px; border-bottom: 1px solid #7F9DB9; border-top: 1px solid #7F9DB9; border-left: 1px solid #7F9DB9; border-right: 0px; float: left; line-height: 19px; padding-left: 3px;"/><div onclick="doSecrecyCountryItemView2();" title="点击快速搜索国家秘密事项" style="background: url('${ctx}/images/index/search/indexPanelSearchBtn.gif') 0px 0px no-repeat; height:20px;width: 24px;float: left; cursor: hand;"></div>
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
								<img src="${ctx}/bmp/images/index/keySecrecyCountryItem/ico.jpg">
							</td>
							<td align="left" width="*">
								<!-- 统计内容 -->
								<table style="width: 100%;" cellpadding="0" cellspacing="0">
									<tr>
										<td class="td_content_left">
											秘密:
										</td>
										<td class="td_content_right">
											<c:if test="${cmap.a3!=0}">
												<a href="###" onclick="doSecrecyCountryItemView('${organ.organId}','3')">
													${cmap.a3}
												</a>
											</c:if>
											<c:if test="${cmap.a3==0}">${cmap.a3}</c:if>
										</td>
									</tr>
									<tr>
										<td class="td_content_left">
											机密:
										</td>
										<td class="td_content_right">
											<c:if test="${cmap.a2!=0}">
												<a href="###" onclick="doSecrecyCountryItemView('${organ.organId}','2')">
													${cmap.a2}
												</a>
											</c:if>
											<c:if test="${cmap.a2==0}">${cmap.a2}</c:if>
										</td>
									</tr>
									<tr>
										<td class="td_content_left">
											绝密:
										</td>
										<td class="td_content_right">
											<c:if test="${cmap.a1!=0}">
												<a href="###" onclick="doSecrecyCountryItemView('${organ.organId}','1')">
													${cmap.a1}
												</a>
											</c:if>
											<c:if test="${cmap.a1==0}">${cmap.a1}</c:if>
										</td>
									</tr>
									<tr align="right">
										<td class="td_total_left">
											合 计:
										</td>
										<td class="td_total_right">
											<c:if test="${cmap.total!=0}">
												<a href="###" onclick="doSecrecyCountryItemView('${organ.organId}', '-1')">
													${cmap.total}
												</a>
											</c:if>
											<c:if test="${cmap.total==0}">${cmap.total}</c:if>
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