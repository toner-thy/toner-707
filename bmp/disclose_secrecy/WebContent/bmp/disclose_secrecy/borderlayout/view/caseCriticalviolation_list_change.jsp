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
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>严重违规案件密级变更列表</title>

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

			//查看严重违规案件 的密级变更的明细
			function doDetailChange(id){
				$ENV.dialog.open({
					url : "${ctx}/bmp/caseCriticalviolation/caseCriticalviolationChangeDetail.action?caseCriticalviolationChange.caseCriticalviolationChangeId=" + id +"&_ts="+new Date().getTime(),
					width : 0.9,
					height : 0.5,
					title : '严重违规案件变更详情'
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
		<div class="body_content">
		<!-- 严重违规案件密级变更历史列表 -->
		<div class="panel_content" style="overflow: auto;">
	        <cp:start defaultTitle="严重违规案件密级变更历史记录简介" ctx="${ctx}" icoPath="/bmp/disclose_secrecy/borderlayout/skin/blue/img/09.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','严重违规案件密级变更历史记录简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','严重违规案件密级变更历史记录查询');">查 询</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于严重违规案件密级变更历史记录
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_caseCriticalviolation"> </cpc:tc>
				</div>
				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
			</cp:msg>
			<cp:search show="false" divId="cp002">
				<form id="keyPart_query_form_change" name="keyPart_query_form_change"
				action="${districtCode==null?'caseCriticalviolation_list_change.action':'caseCriticalviolation_select_change.action' }" method="post">
					<input type="hidden" value="${districtCode}" name="districtCode"/>
				    <input type="hidden" value="${includeChild}" name="includeChild"/><!-- 用于搜索区分本单位还是保密局 -->
					<table class="st" width="100%">
						<tr>
							<td class="tbLable fr" width="20%">严重违规案件：</td>
							<td class="tbValue fl" width="30%">
								<input type="text" name="caseCriticalviolationChange.caseCriticalviolation.name"
								id="caseCriticalviolationChange.caseCriticalviolation.name"  value="${caseCriticalviolationChange.caseCriticalviolation.name}">
							</td>
							<td class="tbLable fr" width="20%">原密级：</td>
							<td class="tbValue fl" width="30%">
								<dictionary:select id="caseCriticalviolationChange.beforeLevel" name="caseCriticalviolationChange.beforeLevel" tableCode="bmp" fieldCode="secrecy_level_thing"
								 title="true"  titleText="请选择"  style="width:130px;" optionValue="${caseCriticalviolationChange.beforeLevel}"></dictionary:select>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">变更后密级：</td>
							<td class="tbValue fl" >
								<dictionary:select id="caseCriticalviolationChange.afterLevel" name="caseCriticalviolationChange.afterLevel" tableCode="bmp" fieldCode="secrecy_level_thing"
								 title="true"  titleText="请选择"  style="width:130px;" optionValue="${caseCriticalviolationChange.afterLevel}"></dictionary:select>
							</td>
							<td class="tbLable fr">变更时间：</td>
							<td class="tbValue fl" >
								<input type="text" name="caseCriticalviolationChange.changeDate" id="caseCriticalviolationChange.changeDate" class="Wdate validate['length[20]'] w130"
								 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" value="<s:date name="caseCriticalviolationChange.changeDate" format="yyyy-MM-dd"/>"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">保密期限变更：</td>
							<td class="tbValue fl" colspan="3">
								<dictionary:select id="caseCriticalviolationChange.changeTimeState" name="caseCriticalviolationChange.changeTimeState" tableCode="bmp" fieldCode="secrecy_limit"
								 title="true"  titleText="请选择"  style="width:130px;" optionValue="${caseCriticalviolationChange.changeTimeState}"></dictionary:select>
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
				<div class="panel_content panel_content_table">
					<s:if test="#request.caseCriticalviolation_changelist.size>0">
						<ec:table items="caseCriticalviolation_changelist" var="caseCriticalviolationChange" tableId="caseCriticalviolation_changelist" border="0"
							action="${districtCode==null?'${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list_change.action':'${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_select_change.action' }"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="caseCriticalviolation.name" title="严重违规案件" width="20%" cell="text" alias="size=15"/>
								<ec:column property="null" title="原密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${caseCriticalviolationChange.beforeLevel}"/>
								</ec:column>
								<ec:column property="null" title="变更后密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${caseCriticalviolationChange.afterLevel}"/>
								</ec:column>
								<ec:column property="null" title="保密期限变更" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${caseCriticalviolationChange.changeTimeState}"/>
								</ec:column>
								<ec:column property="changeDate" title="变更时间" width="10%"  cell="date" format="yyyy-MM-dd" />
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetailChange('${caseCriticalviolationChange.caseCriticalviolationChangeId}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无严重违规案件密级变更历史记录。"/>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</body>
