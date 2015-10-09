<%@ page pageEncoding="UTF-8"%>
<%@page import="com.thgk.framework.core.engine.constant.ConstantService"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="bmp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>

		<title>行政区划涉密移动存储介质统计</title>
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

			function doView(id,secrecyLevel){
				environment.dialog.open({
				url : '${ctx}/secrecysystem/secrecymobilestoragemedia/zhtj_DetailList.action?organId='+id+'&secrecyLevel='+secrecyLevel+'&district.districtCode='+${district.districtCode}+'&t_date=' + new Date().getTime(),
				width : 800,
				height : 550,
				//icon : '${pageContext.request.contextPath}/platform/theme/default/images/main/display.gif',
				title : '涉密移动存储介质统计详情'
			});
		}
		</script>
	</head>

	<body>
		<div class="button_bar">
			<div class="left">
				<%-- 列表
				<a class="pop_button" href="${pageContext.request.contextPath}/bmp/keysectionstat/keySectionStat_organColumnChartByDirectLy.action?district.districtCode=${district.districtCode}"><span>柱形图</span></a> --%>
			    <a class="pop_button" href="${pageContext.request.contextPath}/secrecysystem/secrecymobilestoragemedia/zhtj_query_Detail.action?district.districtCode=${district.districtCode}"><span>返回</span></a>
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
				<%-- <bmp:districtBar district="${district}" url="${pageContext.request.contextPath}/bmp/keysectionstat/keySectionStat_organList.action"/> --%>
				<bmp:navigationBar district="${district}" topDistrict="${topDistrict}" url="${ctx}/secrecysystem/secrecymobilestoragemedia/zhtj_queryByDistrict.action" />
			</div>

			<!-- 查询panel开始 -->
    		<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleSearchIco">
						单位查询
					</div>
					<div class="panel_titleBtnBar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<!-- 查询内容开始 -->
					<form action="zhtj_queryByDistrict.action" method="post" id="secrecyCountryItemStat_organList_form" name="secrecyCountryItemStat_organList_form">
						<table class="panel_content_search_form">
				            <tr>
								<td style="text-align:right;">单位名称：</td>
								<td style="text-align:left;">
									<input type="text" id="organ.organName" name="organ.organName" class="input1"/>
									<input type="hidden" id="district.districtCode" name="district.districtCode" class="input1" value="${district.districtCode}"/>
								</td>
								<td>
									<div class="btn_query_bar pop_button_bar">
										<a class="pop_button" href="javascript:document.getElementById('secrecyCountryItemStat_organList_form').submit();"><span>查 询</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
					<!-- 查询内容结束 -->
				</div>
			</div>
    		<!-- 查询panel结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						【${district.districtName }】行政区划涉密移动存储介质统计
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content" align="center">
					<div class="eXtremeTable">
						<table cellpadding="0" cellspacing="0" border="0" width="80%"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion" >
							<thead>
								<tr>
									<td class="tableHeader"><%=ConstantService.getConstant("District_Organ")%></td>
									<td class="tableHeader">绝密</td>
									<td class="tableHeader">机密</td>
									<td class="tableHeader">秘密</td>
									<td class="tableHeader">合计</td>
								</tr>
							</thead>
							<tbody class="tableBody">
								<c:forEach items="${secrecyCountryItemStatDtoList}" var="o"  varStatus="x">
									<tr height="36px;" class="${x.count % 2 == 0 ? 'odd' : 'even' }" onmouseover="this.className='highlight'"  onmouseout="this.className='${x.count % 2 == 0 ? 'odd' : 'even' }'">
										<td>${o.organName}</td>
										<td>
											<c:if test="${o.organSecrecyLevel1==0}">${o.organSecrecyLevel1}</c:if>
											<c:if test="${o.organSecrecyLevel1>0}">
												<a href="###" onclick="doView('${o.organCode}','1')">${o.organSecrecyLevel1}</a>
											</c:if>
										</td>
										<td>
											<c:if test="${o.organSecrecyLevel2==0}">${o.organSecrecyLevel2}</c:if>
											<c:if test="${o.organSecrecyLevel2>0}">
												<a href="###" onclick="doView('${o.organCode}','2')">${o.organSecrecyLevel2}</a>
											</c:if>
										</td>
										<td>
											<c:if test="${o.organSecrecyLevel3==0}">${o.organSecrecyLevel3}</c:if>
											<c:if test="${o.organSecrecyLevel3>0}">
												<a href="###" onclick="doView('${o.organCode}','3')">${o.organSecrecyLevel3}</a>
											</c:if>
										</td>
										<td>${o.organTotal}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>
