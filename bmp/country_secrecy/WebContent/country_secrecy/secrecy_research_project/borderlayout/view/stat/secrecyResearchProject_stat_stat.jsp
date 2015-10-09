<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.thgk.framework.core.engine.constant.ConstantService"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="bmp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

		<title>行政区涉密科研项目统计</title>

		<s:actionmessage theme="messages"/>
		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
				});
			});


			function doView(districtCode){
				environment.dialog.open({
					url : '${ctx}/bmp/keysectionstat/keySectionStat_printPreview.action?district.districtCode='+districtCode,
					width : 800,
					height : 800,
					icon : '${pageContext.request.contextPath}/platform/theme/default/images/main/display.gif',
					title : '行政区涉密科研项目统计'
				});
			}

			function doDistictView(districtCode){
				environment.dialog.open({
					url : '${ctx}/bmp/keysectionstat/keySectionStat_printPreviewview.action?district.districtCode='+districtCode,
					width : 800,
					height : 800,
					icon : '${pageContext.request.contextPath}/platform/theme/default/images/main/display.gif',
					title : '行政区涉密科研项目统计'
				});
			}

			function doDownLoad(districtCode){
				window.location.href="${pageContext.request.contextPath}/bmp/secrecyResearchProject/stat_exportDistrict.action?district.districtCode="+districtCode;
			}

			function doDownLoad_1(districtCode){
				window.location.href="${pageContext.request.contextPath}/bmp/secrecyResearchProject/stat_exportLayer.action?district.districtCode="+districtCode;;
			}
		</script>

	</head>

	<body>
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<%-- &nbsp;列表
					 <a class="pop_button" href="${pageContext.request.contextPath}/bmp/keysectionstat/keySectionStat_columnChartByDirectLy.action?district.districtCode=${district.districtCode}"><span>柱形图</span></a>
					<a class="pop_button" href="${pageContext.request.contextPath}/bmp/keysectionstat/keySectionStat_pieChartByDirectLy.action?district.districtCode=${district.districtCode}"><span>饼状图</span></a> --%>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
				    <a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.location.reload();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<div>
				<!-- 导航条 -->
				<bmp:navigationBar district="${district}" topDistrict="${topDistrict}" url="${pageContext.request.contextPath}/bmp/secrecyResearchProject/zhtj_query_Detail.action" />
			</div>
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleSearchIco">
						【${district.districtName }】<%=ConstantService.getConstant("District")%>涉密科研项目统计
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_btn_bar pop_button_bar" align="right">
						<a class="pop_button" href="###" onclick="doDownLoad('${districtCode}');"><span>导出</span></a>
						<%-- <a class="pop_button" href="###" onclick="javascript:doView('${districtCode}');"><span>打印预览</span></a> --%>
					</div>
				</div>
				<br>

				<div class="panel_content" style="margin-top: -20px" align="center">
					<div class="eXtremeTable" >
						<table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<thead>
								<tr>
									<td align="center" class="tableHeader">&nbsp;</td>
									<td colspan="4" align="center" class="tableHeader"><%=ConstantService.getConstant("District_Organ")%></td>
									<td colspan="4" align="center" class="tableHeader"><%=ConstantService.getConstant("District_Area")%></td>
								</tr>
							</thead>
							<tbody class="tableBody">
								<tr height="36px;" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td><%=ConstantService.getConstant("District")%></td>
									<td>绝密</td>
									<td>机密</td>
									<td>秘密</td>
									<td>合计</td>
									<td>绝密</td>
									<td>机密</td>
									<td>秘密</td>
									<td>合计</td>
								</tr>
								<c:forEach items="${secrecyResearchProjectStatDtoList}" var="o" varStatus="x">
									<tr height="36px;" class="${x.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${x.count % 2 == 0 ? 'odd' : 'even' }'">
										<td><a href="${pageContext.request.contextPath}/bmp/secrecyResearchProject/zhtj_queryByDistrict.action?district.districtCode=${o.code}">${o.name}</a></td>
										<td>${o.layerSecrecyLevel1}</td><!-- 直辖单位 -->
										<td>${o.layerSecrecyLevel2}</td>
										<td>${o.layerSecrecyLevel3}</td>
										<td>${o.layerTotal}</td>
										<td>${o.districtSecrecyLevel1}</td><!-- 行政区划 -->
										<td>${o.districtSecrecyLevel2}</td>
										<td>${o.districtSecrecyLevel3}</td>
										<td>${o.districtTotal}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<br>

			<c:if test="${not empty childrenStatDtoList}">
				<!-- 列表 -->
				<div class="panel" style="margin-top: -15px;">
					<div class="panel_header">
						<div class="panel_title panel_titleListIco">
							<h2>
								【${district.districtName }】<%=ConstantService.getConstant("District_Area")%>涉密科研项目统计
							</h2>
						</div>
						<!-- 右侧按钮 -->
						<div class="panel_btn_bar pop_button_bar" align="right">
							<a class="pop_button" href="###" onclick="doDownLoad_1('${districtCode}');"><span>导出</span></a>
							<%-- <a class="pop_button" href="###" onclick="javascript:doDistictView('${districtCode}');"><span>打印预览</span></a> --%>
						</div>
					</div>
					<br>
					<div class="panel_content" style="margin-top: -20px" align="center">
						<div class="eXtremeTable" >
							<table border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%" >
								<thead>
									<tr>
										<td align="center" class="tableHeader">&nbsp;</td>
										<td colspan="4" align="center" class="tableHeader"><%=ConstantService.getConstant("District_Organ")%></td>
										<td colspan="4" align="center" class="tableHeader"><%=ConstantService.getConstant("District_Area")%></td>
									</tr>
								</thead>
								<tbody class="tableBody">
									<tr height="36px;" class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
										<td><%=ConstantService.getConstant("District")%></td>
										<td>绝密</td>
										<td>机密</td>
										<td>秘密</td>
										<td>合计</td>
										<td>绝密</td>
										<td>机密</td>
										<td>秘密</td>
										<td>合计</td>
									</tr>
									<c:forEach items="${childrenStatDtoList}" var="o" varStatus="x">
										<tr height="36px;" class="${x.count % 2 == 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'"  onmouseout="this.className='${x.count % 2 == 0 ? 'odd' : 'even' }'">
											<td>
												<c:if test="${o.code == null}">${o.name}</c:if>
												<c:if test="${o.code != null}"><a href="${pageContext.request.contextPath}/bmp/secrecyResearchProject/zhtj_query_Detail.action?district.districtCode=${o.code}">${o.name}</a></c:if>
											</td>
											<td>${o.layerSecrecyLevel1}</td><!-- 直辖单位 -->
											<td>${o.layerSecrecyLevel2}</td>
											<td>${o.layerSecrecyLevel3}</td>
											<td>${o.layerTotal}</td>
											<td>${o.districtSecrecyLevel1}</td><!-- 行政区划 -->
											<td>${o.districtSecrecyLevel2}</td>
											<td>${o.districtSecrecyLevel3}</td>
											<td>${o.districtTotal}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</c:if>

		</div>

	</body>
</html>