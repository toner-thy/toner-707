<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>严重违规案件密级解除列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){
				});
			});


			//查看严重违规案件的解除密级明细
			function doDetailClear(id) {
				$ENV.dialog.open({
					url : "${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_clear_detail.action?caseCriticalviolationClear.caseCriticalviolationClearId=" + id +"&_ts="+new Date().getTime(),
					width : 0.7,
					height : 0.6,
					title : '严重违规案件密级解除详情'
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
	<!-- 严重违规案件解除部位信息列表 -->
		<div class="panel_content" style="overflow: auto;">
		    <cp:start defaultTitle="严重违规案件解除部位信息简介" ctx="${ctx}" icoPath="/bmp/disclose_secrecy/borderlayout/skin/blue/img/09.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','严重违规案件解除部位简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','严重违规案件解除部位记录查询');">查 询</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于严重违规案件解除部位信息
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_caseCriticalviolation"> </cpc:tc>
				</div>
				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
			</cp:msg>
			<cp:search show="false" divId="cp002">
				<form id="keyPart_query_form_clear" name="keyPart_query_form_clear"
				action="${districtCode==null?'caseCriticalviolation_list_clear.action':'caseCriticalviolation_select_clear.action' }" method="post">
				<input value="${districtCode}" name="districtCode" type="hidden"/>
				<input type="hidden" value="${includeChild}" name="includeChild"/><!-- 用于搜索区分本单位还是保密局 -->
					<table class="st" width="100%">
						<tr>
							<td class="tbLable fr">
								严重违规案件：
							</td>
							<td class="tbValue fl">
								<input type="text" name="caseCriticalviolationClear.caseCriticalviolation.name"
								id="caseCriticalviolationClear.caseCriticalviolation.name" value="${caseCriticalviolationClear.caseCriticalviolation.name}">
							</td>
							<td class="tbLable fr">
								解除类型：
							</td>
							<td class="tbValue fl">
								<dictionary:select id="caseCriticalviolationClear.clearType" name="caseCriticalviolationClear.clearType" tableCode="bmp" fieldCode="clear_secrecy_type"
								 title="true"  titleText="请选择" style="width:130px;" optionValue="${caseCriticalviolationClear.clearType}" ></dictionary:select>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								解除时间：
							</td>
							<td class="tbValue fl">
								<input type="text" name="caseCriticalviolationClear.clearTime" id="caseCriticalviolationClear.clearTime" class="Wdate validate['length[20]'] w130"
								 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" value="<s:date name="caseCriticalviolationClear.clearTime" format="yyyy-MM-dd"/>"/>
							</td>
							<td class="tbLable fr">
								解除前密级：
							</td>
							<td class="tbValue fl">
								<dictionary:select id="caseCriticalviolationClear.caseCriticalviolation.secrecyLevel" name="caseCriticalviolationClear.caseCriticalviolation.secrecyLevel" tableCode="bmp" fieldCode="secrecy_level_thing"
								 title="true"  titleText="请选择"  style="width:130px;"  optionValue="${caseCriticalviolationClear.caseCriticalviolation.secrecyLevel}"></dictionary:select>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="fc" style="border: 0px;">
								<div class="stBtnBar">
									<a class="pop_button" href="javascript:document.getElementById('keyPart_query_form_clear').submit();"><span>查 询</span></a>
									<a class="pop_button" href="javascript:document.getElementById('keyPart_query_form_clear').reset();"><span>重 置</span></a>
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
					<s:if test="#request.caseCriticalviolation_clearlist.size>0">
						<ec:table items="caseCriticalviolation_clearlist" var="caseCriticalviolationclear" tableId="caseCriticalviolation_clearlist" border="0"
							action="${districtCode==null?'${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list_clear.action':'${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_select_clear.action' }"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="caseCriticalviolation.name" title="严重违规案件" width="20%" cell="text" alias="size=15"/>
								<ec:column property="null" title="解除前密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${caseCriticalviolationclear.caseCriticalviolation.secrecyLevel}"/>
								</ec:column>
								<ec:column property="null" title="解除类型" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="clear_secrecy_type" optionValue="${caseCriticalviolationclear.clearType}"/>
								</ec:column>
								<ec:column property="clearTime" title="解除时间" width="10%" cell="date" format="yyyy-MM-dd" />
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetailClear('${caseCriticalviolationclear.caseCriticalviolationClearId}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无严重违规案件解除信息记录。"/>
					</s:else>
				</div>
			</div>
		</div>
	</div>
    </body>