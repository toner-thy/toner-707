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
<%@ taglib uri="http://www.cdthgk.com/tags/district" prefix="dis"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>商业秘密事项密级解除列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

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


			//查看商业秘密事项的解除密级明细
			function doDetailClear(id) {
				$ENV.dialog.open({
					url : "${ctx}/bmp/secrecycountryitem/secrecyCountryItem_clearDetial.action?secrecyCountryItemClear.secrecyContryClearId=" + id +"&district.districtCode=${district.districtCode}&ywFlag=${ywFlag}&_ts="+new Date().getTime(),
					width : 0.8,
					height : 0.8,
					title : '商业秘密事项密级解除详情'
				});
			}
		</script>
		<style type="text/css">
			.body_content{
				top:0px;
			}
		</style>
	</head>

	<body>	<!-- 商业秘密事项解除信息列表 -->
		<div id="body_content" class="body_content">
		    <cp:start defaultTitle="商业秘密事项解除信息简介" ctx="${ctx}" icoPath="/country_secrecy/secrecy_country_item/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','商业秘密事项解除简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','商业秘密事项解除记录查询');">查 询</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于商业秘密事项解除部位信息
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_secrecy_countryitem"> </cpc:tc>
				</div>
				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
			</cp:msg>
			<cp:search show="false" divId="cp002">
				<form id="query_form_clear" name="query_form_clear" action="${ctx}/bmp/secrecycountryitem/secrecyCountryItem_clear_list.action" method="post">
					<input type="hidden" name="districtCode" id="districtCode" value="${district.districtCode}"><!-- 行政区划编码 -->
					<input type="hidden" name="ywFlag" id="ywFlag" value="${ywFlag}"><!-- 业务标志 1： 查询页面     0：普通业务模块   -->
					<input type="hidden" name="isChildren" id="isChildren" value="${isChildren}"><!-- 是否包含下级  1包含  0不包含 -->

					<table class="st" width="100%">
						<tr>
							<td class="tbLable fr">
								商业秘密事项：
							</td>
							<td class="tbValue fl">
								<input type="text" name="secrecyCountryItemClear.secrecyCountryItem.secrecyCountryItemName"
								id="secrecyCountryItemClear.secrecyCountryItem.secrecyCountryItemName" value=${secrecyCountryItemClear.secrecyCountryItem.secrecyCountryItemName } >
							</td>
							<td class="tbLable fr">
								解除类型：
							</td>
							<td class="tbValue fl">
								<dictionary:select id="secrecyCountryItemClear.clearType" name="secrecyCountryItemClear.clearType" tableCode="bmp" fieldCode="clear_secrecy_type"
								 title="true" titleText="全部"  style="width:130px;" optionValue="${secrecyCountryItemClear.clearType }"></dictionary:select>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								解除时间：
							</td>
							<td class="tbValue fl">
								<input type="text" name="secrecyCountryItemClear.clearTime" id="secrecyCountryItemClear.clearTime" class="Wdate validate['length[20]'] w130"
								 onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" value='<s:date format="yyyy-MM-dd" name="secrecyCountryItemClear.clearTime"/>' />
							</td>
							<td class="tbLable fr">
								解除前密级：
							</td>
							<td class="tbValue fl" >
								<dictionary:select id="secrecyCountryItemClear.secrecyCountryItem.secrecyLevel" name="secrecyCountryItemClear.secrecyCountryItem.secrecyLevel"
								tableCode="bmp" fieldCode="secrecy_level_thing" title="true" titleText="全部"  style="width:130px;"
								optionValue="${secrecyCountryItemClear.secrecyCountryItem.secrecyLevel }"></dictionary:select>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="fc" style="border: 0px;">
								<div class="stBtnBar">
									<a class="pop_button" href="javascript:document.getElementById('query_form_clear').submit();"><span>查 询</span></a>
									<a class="pop_button" href="javascript:document.getElementById('query_form_clear').reset();"><span>重 置</span></a>
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
							商业秘密事项密级解除列表
						</c:if>
						<c:if test="${ywFlag eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${isChildren ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 商业秘密事项密级解除列表
							</c:if>
							<c:if test="${isChildren eq '1'}">
								${district.districtName} - 商业秘密事项密级解除列表
							</c:if>
						</c:if>
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyCountryItemClearList.size>0">
						<ec:table items="secrecyCountryItemClearList" var="secrecyCountryItemClear" tableId="secrecyCountryItemClearList" border="0"
							action="${ctx}/bmp/secrecycountryitem/secrecyCountryItem_clear_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secrecyCountryItem.secrecyCountryItemName" title="商业秘密事项" width="20%" cell="text" alias="size=15"/>
								<ec:column property="null" title="解除前密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyCountryItemClear.secrecyCountryItem.secrecyLevel}"/>
								</ec:column>
								<ec:column property="null" title="解除类型" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="clear_secrecy_type" optionValue="${secrecyCountryItemClear.clearType}"/>
								</ec:column>
								<ec:column property="clearTime" title="解除时间" width="10%" cell="date" format="yyyy-MM-dd" />
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetailClear('${secrecyCountryItemClear.secrecyContryClearId}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
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