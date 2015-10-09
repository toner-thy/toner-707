<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

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


			// 查看详情
			function doDetail(id){
				$ENV.dialog.open({
					title : '其他涉密设备详情',
					url : '${ctx}/secrecysystem/secrecyothers/others_secrecyLevelChangeDetail.action?secrecyOthersChange.secrecyothersChangeId='+ id + '&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
					}
				});
			}
		</script>
	</head>
	<body>
		<div class="body_content" >
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密移动存储介质简介" ctx="${ctx}" icoPath="/bmp/secrecy-system/secrecy-otherdevice/borderlayout/skin/blue/img/others_list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','其他涉密设备简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','其他涉密设备查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于其他涉密设备
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_others"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<s:form namespace="/secrecysystem/secrecyothers" action="others_secrecyLevelChangeHistory.action" method="post" id="queryForm" theme="simple">
						<input type="hidden" name="districtCode" id="districtCode" value="${districtCode }" />
						<input type="hidden" id="checkLower" name="checkLower" value="${checkLower}" />
						<input type="hidden" id="fromQuery" name="fromQuery" value="${fromQuery}" />
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">介质类型：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="other_media_type" id="secrecyOthersChange.secrecyOthers.secrecyothersType" name="secrecyOthersChange.secrecyOthers.secrecyothersType" title="true" titleText="" optionValue="${secrecyOthersChange.secrecyOthers.secrecyothersType }" style="width:130px;"></dictionary:select>
								</td>
								<td class="tbLable fr">保密期限变更：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_limit" id="secrecyOthersChange.changeTimeState" name="secrecyOthersChange.changeTimeState" title="true" titleText="" optionValue="${secrecyOthersChange.changeTimeState }" style="width:130px;"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">变更前密级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyOthersChange.beforeLevel" name="secrecyOthersChange.beforeLevel" title="true" titleText="" optionValue="${secrecyOthersChange.beforeLevel }" style="width:130px;"></dictionary:select>
								</td>
								<td class="tbLable fr" >变更后密级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyOthersChange.afterLevel" name="secrecyOthersChange.afterLevel" title="true" titleText="" optionValue="${secrecyOthersChange.afterLevel }" style="width:130px;"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('queryForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('queryForm').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<c:if test="${fromQuery ne '1'}">
							其他涉密设备密级变更列表
						</c:if>
						<c:if test="${fromQuery eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${checkLower ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 其他涉密设备密级变更列表
							</c:if>
							<c:if test="${checkLower eq '1'}">
								${district.districtName} - 其他涉密设备密级变更列表
							</c:if>
						</c:if>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#attr.secrecyOthersChangeList.size>0">
						<ec:table items="secrecyOthersChangeList" var="secrecyOthersChange"
							tableId="secrecyOthersChangeListTable" border="0"
							action="${ctx}/secrecysystem/secrecyothers/others_secrecyLevelChangeHistory.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit" showPagination="true"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="rowCount" cell="rowCount" sortable="false" title="序号" width="5%"/>
								<ec:column property="null" title="介质类型" alias="size=10" width="15%">
									<dictionary:text tableCode="bmp" fieldCode="other_media_type" optionValue="${secrecyOthersChange.secrecyOthers.secrecyothersType }" ></dictionary:text>
								</ec:column>
								<ec:column property="null" title="变更前密级" alias="size=10" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyOthersChange.beforeLevel }" ></dictionary:text>
								</ec:column>
								<ec:column property="null" title="变更后密级" alias="size=10" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyOthersChange.afterLevel }" ></dictionary:text>
								</ec:column>
								<ec:column property="null" title="保密期限变更" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${secrecyOthersChange.changeTimeState}" ></dictionary:text>
								</ec:column>
								<ec:column property="changeDate" title="变更时间" cell="date" format="yyyy-MM-dd" width="10%"/>
								<ec:column property="null" title="变更原因" width="20%">
									<c:choose>
										<c:when test="${fn:length(secrecyOthersChange.changeReason) > 15 }">
											${fn:substring(secrecyOthersChange.changeReason,0,12) }...
										</c:when>
										<c:otherwise>
											${secrecyOthersChange.changeReason }
										</c:otherwise>
									</c:choose>
								</ec:column>
							</ec:row>
							<ec:column property="null" title="详 情" width="10%">
								<a href="###" onclick="doDetail('${secrecyOthersChange.secrecyothersChangeId}')">
									<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/>
								</a>
							</ec:column>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
				</div>
			</div>
		</div>
	</body>
</html>