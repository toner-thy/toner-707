<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>定（解）密工作情况列表</title>

		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
			//详情
			function doDetail(checkEntryId){
			 environment.dialog.open({
					url : '${ctx}/bmp/setTheDecryption/setTheDecryption_detail.action?setTheDecryption.id='+checkEntryId+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '定（解）密工作情况详情'
				});
			}
		</script>
	</head>
	<body>
		<!-- 公共头部 -->
    	<div class="button_bar">
			<div class="left">
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密定（解）密工作情况简介" ctx="${ctx}" icoPath="">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','保密定（解）密工作情况简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','保密定（解）密工作情况搜索');">查 询</div>
			</cp:start>
				<cp:msg show="false" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于定（解）密工作情况
					</div>
					<div class="cpMsgContext">
						定（解）密工作情况
					</div>
				</cp:msg>
				<cp:search show="true" divId="cp002">
					<s:form action="setTheDecryption_allList" id="setTheDecryption_list_form" theme="simple">
						<!-- 隐藏域 -->
						<input type="hidden" name="district.districtCode" value="${district.districtCode}" />
						<input type="hidden" value="${showType}" name="showType" />
						<table class="st" width="100%">
							  <tr>
								<td class="tbLable fr">
									日期：
								</td>
								<td class="tbValue fl">
									<input name="setTheDecryption.date" id="setTheDecryption.date" type="text" value="<s:date name="setTheDecryption.date" format="yyyy-MM-dd"/>" class="Wdate default" onFocus="WdatePicker()" readonly="readonly">
								</td>
								<td class="tbLable fr">
									文件名称：
								</td>
								<td class="tbValue fl">
									<input name="setTheDecryption.name"  id="setTheDecryption.name" type="text" value="${setTheDecryption.name}">
								</td>
							</tr>
							<%-- <tr>
								<td class="tbLable fr">
									查询类型：
								</td>
								<td class="tbValue fl" colspan="3">
									<s:radio list="#{'0':'含子机构','1':'不含子机构'}" name="showType" value="showType"></s:radio>
									<input type="hidden" name="setTheDecryption.organId.organId" value="${setTheDecryption.organId.organId }"/>
								</td>
							</tr> --%>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('setTheDecryption_list_form').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('setTheDecryption_list_form').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板开始 -->

			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断是否查看下级1查看，0不查看-->
						<c:if test="${showType eq '1'}">
							${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 保密定（解）密工作情况列表
						</c:if>
						<c:if test="${showType ne '1'}">
							${district.districtName} - 保密定（解）密工作情况列表
						</c:if>
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content">
					<s:if test="#request.list.size>0">
						<ec:table items="list" var="setTheDecryption" tableId="list" border="0"
								action="${ctx}/bmp/setTheDecryption/setTheDecryption_allList.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="id" alias="id_checkbox" cell="checkbox" headerCell="checkbox"/>
							<ec:column property="name" title="文件名称">
								<div title="${setTheDecryption.name}">
									${fn:substring(setTheDecryption.name, 0, 12)}
									<c:if test="${fn:length(setTheDecryption.name)>13}">...</c:if>
								</div>
							</ec:column>
							<ec:column property="date" title="日期" cell="date" format="yyyy-MM-dd" parse="yyyy-MM-dd"/>
							<ec:column property="null" title="文号">
								<div title="${setTheDecryption.docNumber}">
									${fn:substring(setTheDecryption.docNumber, 0, 20)}
									<c:if test="${fn:length(setTheDecryption.docNumber)>20}">...</c:if>
								</div>
							</ec:column>
							<ec:column property="null" title="数量">
								<div title="${setTheDecryption.number}">
									${fn:substring(setTheDecryption.number, 0, 20)}
									<c:if test="${fn:length(setTheDecryption.number)>20}">...</c:if>
								</div>
							</ec:column>
							<ec:column property="null" title="密级">
							<dictionary:text fieldCode="secrecy_level_thing" optionValue="${setTheDecryption.secrecyLevel}" tableCode="bmp"></dictionary:text>
							</ec:column>
							<ec:column property="null" title="事项类型">
									<c:if test="${setTheDecryption.secrecyType==1}">解密事项</c:if>
									<c:if test="${setTheDecryption.secrecyType==0}">定密事项</c:if>
							</ec:column>
							<ec:column property="null" title="载体形式">
								<div title="${setTheDecryption.carrierType}">
									${fn:substring(setTheDecryption.carrierType, 0, 20)}
									<c:if test="${fn:length(setTheDecryption.carrierType)>20}">...</c:if>
								</div>
							</ec:column>
							<ec:column property="null" title="显示详请">
								<a href="###" onclick="doDetail('${setTheDecryption.id}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
							</ec:column>
						</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据！"></u:noData>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>