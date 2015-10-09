<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary"
	prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/district" prefix="dis"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>数据权限列表</title>

<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
<script type="text/javascript">
	$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
	$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
	$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
	$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",
			function() {
				$ENV.onDomReady(function() {

				});
			});
	function doDetail(id) {
		$ENV.dialog.open({
			url : "${ctx}/bmp/dataAuthority/dataAuthority_detail.action?dataAuthority.dataId="+ id + "&_ts=" + new Date().getTime(),
			width : 0.8,
			height : 0.6,
			title : '数据权限详情'
		});
	}
	function querydoDetail(id,name) {
		TabUtil.openAsTab({
			url : "${ctx}/bmp/dataAuthority/dataAuthority_query_list_main.action?dataAuthority.dataId="+ id + "&_ts=" + new Date().getTime(),
			title : name+'列表'
		});

	}
</script>
</head>
<body>
	<div id="body_content" class="body_content">
		<div class="panel tMargin bMargin">
			<div class="panel_header">

				<div class="panel_title panel_titleListIco">
					<s:if
						test="discloseSecrecy.createOrgan.organId!=null&&discloseSecrecy.createOrgan.organId!=''">
				           【<s:property
							value="discloseSecrecy.createOrgan.organName" />】- 数据权限维护列表
				       </s:if>
					<s:else>
						<s:if
							test="#request.districtCode==null||#request.districtCode==''">
							数据维护列表
						</s:if>
						<s:else>
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${includeChild ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 数据权限维护列表
							</c:if>
							<c:if test="${includeChild eq '1'}">
								${district.districtName} - 数据权限维护列表
							</c:if>
						</s:else>
					</s:else>
				</div>

				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
				<!-- 这里自定义ectable -->
				<s:if test="#request.dataAuthorityList.size>0">
					<ec:table items="dataAuthorityList" var="dataAuthority"
						tableId="dataAuthorityList" border="0"
						action="${ctx}/bmp/dataAuthority/dataAuthority_list.action"
						imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="dataId" width="3%" alias="disclosesecrecycaseId_checkbox" cell="checkbox" headerCell="checkbox" />
							<ec:column property="null" title="名称" width="14%">
										<a href="###" onclick="querydoDetail('${dataAuthority.dataId}','${dataAuthority.name}');">${dataAuthority.name}</a>
							</ec:column>
							<%-- <ec:column property="dataType" title="类型" width="9%"
								style="text-align: left;">
								<dictionary:text tableCode="bmp" fieldCode="case_kind"
									optionValue="${discloseSecrecy.casekind}"></dictionary:text>
							</ec:column> --%>
							<ec:column property="describe" title="描述"
								width="12%" style="text-align: left;">
							</ec:column>
							<ec:column property="null" title="详 情" width="3%">
								<a href="###" onclick="doDetail('${dataAuthority.dataId}');">
								<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情" />
								</a>
							</ec:column>
						</ec:row>
					</ec:table>
				</s:if>
				<s:else>
					<u:noData text="当前暂无数据权限维护信息" />
				</s:else>

			</div>
		</div>
	</div>
</body>
</html>