<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密涉外活动记录列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<s:actionmessage theme="messages"/>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/bmp/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />

		<script language="javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

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
			// 重新加载
	  		function reload() {
	  			window.location.href = "<s:url action="externalPidgin_epList"  includeParams="false"/>";
	  		}

			function doDetail(action,externalPidginId){
				/* environment.dialog.open({
					url : action+'?id='+externalPidginId+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					icon : '${pageContext.request.contextPath}/platform/theme/default/images/main/display.gif',
					title : '涉密涉外活动详细记录'
				}); */
				$ENV.dialog.open({
					url : action+'?id='+externalPidginId+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '涉密涉外活动详细记录'
				});
			}

			//上报
			function doReport(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要上报的数据项！");
					return;
				}
				if(window.confirm("确定上报吗？")){
					var ids = "";
					var queryStr = '';
					items.each(function(item){
						ids += item.value + ",";
					});
					queryStr = "externalPidginIds=" + ids;
					var myRequest = new Request({
				    method: 'get',
				    url: action,
				    onSuccess:function(text) {
				    	var obj = eval("("+text+")");
				    	alert(obj.message);
				    	reload();
				    }
				}).send(queryStr +'&district.districtCode=${district.districtCode}'+ '&time=' + new Date().getTime());
					}
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<%-- <ap:operationbutton /> --%>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div  class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密涉外活动查询简介" ctx="${ctx}" icoPath="${ctx}/externalpidgin/borderlayout/skin/blue/img/epList_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','涉密涉外活动查询简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','涉密涉外活动查询搜索');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于主要涉密涉外活动查询
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_externalPidgin_query"> </cpc:tc>
					</div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<s:form action="externalPidgin_epList" id="queryform" theme="simple" target="_self">
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									查询范围：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="checkbox" name="showType" value="1" ${showType == 1 ? 'checked' : ''} /> 是否查看下级
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<input type="hidden" id="district.districtCode" name="district.districtCode" value="${district.districtCode}"/>
										<a class="pop_button" href="javascript:document.getElementById('queryform').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('queryform').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
					<!--判断是否查看下级1查看，0不查看-->
					<c:if test="${showType ne '1'}">
						${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 涉密涉外活动记录列表
					</c:if>
					<c:if test="${showType eq '1'}">
						${district.districtName} - 涉密涉外活动记录列表
					</c:if>

					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.externalPidginList.size>0">
						<ec:table items="externalPidginList" var="externalPidgin" tableId="externalPidginList" border="0"
							action="${ctx}/bmp/externalPidgin/externalPidgin_epList.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="checkbox" alias="externalPidginId_checkbox" headerCell="checkbox">
									<input type="checkbox" value="${externalPidgin.externalPidginId}" name="id" class="row_checkbox">
								</ec:column>
								<ec:column property="eternalPidginName" title="涉密涉外活动名称" cell="text" alias="size=8"/>
								<ec:column property="organ.organName" title="创建单位名称" cell="text" alias="size=8"/>
								<ec:column property="mainOrgan" title="主办单位" cell="text" alias="size=8"/>
								<ec:column property="null" title="开始时间">
									<div style="font-family: Arial, 'Times New Roman' !important;">
										<s:date name="#attr.externalPidgin.startDate" format="yy年MM月dd日"/>
									</div>
								</ec:column>
								<ec:column property="null" title="结束时间">
									<div style="font-family: Arial, 'Times New Roman' !important;">
										<s:date name="#attr.externalPidgin.endDate" format="yy年MM月dd日"/>
									</div>
								</ec:column>
								<ec:column property="null" title="密 级">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_externalpidgin" optionValue="${externalPidgin.secrecyLevel}"/>
								</ec:column>
								<%-- <ec:column property="null" title="上报状态">
									<c:choose>
										<c:when test="${currentOrgan.organId == externalPidgin.reportOrgan.organId }">
											<dictionary:text fieldCode="report_state" optionValue="${externalPidgin.reportOrganState}" tableCode="global"/>
										</c:when>
										<c:otherwise>
											<dictionary:text fieldCode="report_state" optionValue="${externalPidgin.reportState}" tableCode="global"/>
										</c:otherwise>
									</c:choose>
								</ec:column> --%>

								<ec:column property="null" title="显示详情" width="8%">
									<a href="javascript:doDetail('${ctx}/bmp/externalPidgin/externalPidgin_detail.action','${externalPidgin.externalPidginId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
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