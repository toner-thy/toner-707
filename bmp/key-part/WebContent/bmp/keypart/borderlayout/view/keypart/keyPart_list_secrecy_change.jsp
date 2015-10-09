<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/district" prefix="dis"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部位密级变更列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",function(){
				$ENV.onDomReady(function(){
				});
			});

			//查看保密要害部位 的密级变更的明细
			function doDetailChange(id){
				$ENV.dialog.open({
					url : "${ctx}/bmp/part/part_changeDetail.action?keyPartChange.keyPartSecrecyChangeId=" + id +"&_ts="+new Date().getTime(),
					width : 0.8,
					height : 0.7,
					title : '保密要害部位密级变更详情'
				});
			}
		</script>
		<style type="text/css">
			.body_content{
				top:0px;
			}
		</style>
	</head>

	<body>

		<!-- 保密要害部位密级变更历史列表 -->
		<div id="body_content" class="body_content">
	        <cp:start defaultTitle="保密要害部位密级变更历史记录简介" ctx="${ctx}" icoPath="/bmp/key-section/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','保密要害部位密级变更历史记录简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','保密要害部位密级变更历史记录查询');">查 询</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于保密要害部位密级变更历史记录
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_key_department"> </cpc:tc>
				</div>
				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
			</cp:msg>
			<cp:search show="false" divId="cp002">
				<form id="keyPart_query_form_change" name="keyPart_query_form_change" action="keyPart_list_change.action" method="post">
					<input type="hidden" name="districtCode" id="districtCode" value="${district.districtCode}"><!-- 行政区划编码 -->
					<input type="hidden" name="ywFlag" id="ywFlag" value="${ywFlag}"><!-- 业务标志 1： 查询页面     0：普通业务模块   -->
					<input type="hidden" name="isChildren" id="isChildren" value="${isChildren}"><!-- 是否包含下级  1包含  0不包含 -->

					<table class="st" width="100%">
						<tr>
							<td class="tbLable fr">保密要害部位：</td>
							<td class="tbValue fl">
								<input type="text" name="keyPartChange.keyPartId.partName" id="keySectionChange.keyPartId.partName" >
							</td>
							<td class="tbLable fr">原密级：</td>
							<td class="tbValue fl">
								<dictionary:select id="keyPartChange.beforeLevel" name="keyPartChange.beforeLevel" tableCode="bmp" fieldCode="secrecy_level_section"
								 title="false" style="width:130px;"></dictionary:select>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">变更后密级：</td>
							<td class="tbValue fl" >
								<dictionary:select id="keyPartChange.afterLevel" name="keyPartChange.afterLevel" tableCode="bmp" fieldCode="secrecy_level_section"
								 title="false" style="width:130px;"></dictionary:select>
							</td>
							<td class="tbLable fr">变更时间：</td>
							<td class="tbValue fl" >
								<input type="text" name="keyPartChange.changeDate" id="keyPartChange.changeDate" class="Wdate validate['length[20]'] w130"
								 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">保密期限变更：</td>
							<td class="tbValue fl" colspan="3">
								<dictionary:select id="keyPartChange.changeTimeState" name="keyPartChange.changeTimeState" tableCode="bmp" fieldCode="secrecy_limit"
								 title="false" style="width:130px;"></dictionary:select>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="fc" style="border:0px;">
								<div class="stBtnBar">
									<a class="pop_button" href="javascript:document.getElementById('keyPart_query_form_change').submit();"><span>查 询</span></a>
									<a class="pop_button" href="javascript:document.getElementById('keyPart_query_form_change').reset();"><span>重 置</span></a>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</cp:search>
			<cp:end> </cp:end>


			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<c:if test="${ywFlag eq '0'}">
							保密要害部位密级变更列表
						</c:if>
						<c:if test="${ywFlag eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${isChildren ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 保密要害部位密级变更列表
							</c:if>
							<c:if test="${isChildren eq '1'}">
								${district.districtName} - 保密要害部位密级变更列表
							</c:if>
						</c:if>
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<s:if test="#request.keyPart_changelist.size>0">
						<ec:table items="keyPart_changelist" var="keyPartChange" tableId="keyPart_changelist" border="0"
							action="${ctx}/bmp/part/keyPart_list_change.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" filterable="false" autoIncludeParameters="true" sortable="false">
							<!-- retrieveRowsCallback="limit" sortRowsCallback="limit" -->
							<ec:row>
								<ec:column property="keyPartId.partName" title="保密要害部位" width="20%" cell="text" alias="size=15"/>
								<ec:column property="null" title="原密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_section" optionValue="${keyPartChange.beforeLevel}"/>
								</ec:column>
								<ec:column property="null" title="变更后密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_section" optionValue="${keyPartChange.afterLevel}"/>
								</ec:column>
								<ec:column property="null" title="保密期限变更" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${keyPartChange.changeTimeState}"/>
								</ec:column>
								<ec:column property="changeDate" title="变更时间" width="10%"  cell="date" format="yyyy-MM-dd" />
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetailChange('${keyPartChange.keyPartSecrecyChangeId}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无数据"/>
					</s:else>
				</div>
			</div>
		</div>
</body>
