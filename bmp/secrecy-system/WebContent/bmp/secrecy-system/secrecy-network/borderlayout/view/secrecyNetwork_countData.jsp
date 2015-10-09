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
		<title>涉密网络列表</title>

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
			// 查看详情
			function doDetail(id){
				$ENV.dialog.open({
					title : '涉密网络详情',
					url : '${ctx}/bmp/secrecynetwork/secrecyNetwork_detail.action?secrecyNetwork.secrecyNetworkId='+id+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="body_content" >
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密网络简介" ctx="${ctx}" icoPath="/bmp/secrecy-system/secrecy-network/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','涉密网络简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','涉密网络查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密网络
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_network"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form action="<s:url action="secrecyNetwork_countData.action" namespace="/bmp/secrecynetwork"/>" method="post" id="queryForm" theme="simple">
						<input type="hidden" name="countType" id="countType" value="${countType }" />
						<input type="hidden" id="secrecyNetwork.networkType" name="secrecyNetwork.networkType" value="${secrecyNetwork.networkType}" />
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">涉密网络名称：</td>
								<td class="tbValue fl" colspan="1">
									<input type="text" id="secrecyNetwork.name" name="secrecyNetwork.name" value="${secrecyNetwork.name}" />
								</td>
								<td class="tbLable fr">网络密级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyNetwork.secrecyLevel" name="secrecyNetwork.secrecyLevel"
									 title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyNetwork.secrecyLevel}"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">是否通过测评：</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'是','0':'否'}"
									style="width:135px;"
									theme="simple"
									name="secrecyNetwork.isReview"
									headerKey=""
									headerValue="请选择"
									>
									</s:select>
								</td>
								<td class="tbLable fr">是否通过审批：</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'是','0':'否'}"
									style="width:135px;"
									theme="simple"
									name="secrecyNetwork.isApproval"
									headerKey=""
									headerValue="请选择"
									>
									</s:select>
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
					</form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密网络列表
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyNetworkList.size>0">
						<ec:table items="secrecyNetworkList" var="secrecyNetwork" tableId="secrecyNetworkList" border="0"
							action="${ctx}/bmp/secrecynetwork/secrecyNetwork_countData.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secrecyNetworkId" alias="secrecyNetworkId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="name" title="涉密网络名称" cell="text" alias="size=10" width="10%"/>
								<ec:column property="null" title="网络密级" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyNetwork.secrecyLevel }"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="网络类型 " width="10%">
									<dictionary:text tableCode="bmp" fieldCode="network_type" optionValue="${secrecyNetwork.networkType }"></dictionary:text>
								</ec:column>
								<ec:column property="networkNum" title="网络终端数量" width="10%">
								</ec:column>
								<ec:column property="null" title="是否通过测评" width="15%">
									<c:if test="${secrecyNetwork.isReview eq 1 }">是</c:if>
									<c:if test="${secrecyNetwork.isReview eq 0 }">否</c:if>
								</ec:column>
								<ec:column property="null" title="是否通过审批" width="15%">
									<c:if test="${secrecyNetwork.isApproval eq 1 }">是</c:if>
									<c:if test="${secrecyNetwork.isApproval eq 0 }">否</c:if>
								</ec:column>
								<ec:column property="startUseDate" title="投入使用时间" cell="date" format="yyyy-MM-dd"></ec:column>
								<ec:column property="department.departmentName" title="部门名称"></ec:column>
								<ec:column property="null"  width="5%" title="详 情">
									<a href="###" onclick="doDetail('${secrecyNetwork.secrecyNetworkId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" alt ="详情" >
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无涉密网络，请点击【新增】按钮添加。"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>