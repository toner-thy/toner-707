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
		<title>资料列表</title>
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
			//新增
			function doFill(action){
				if('${dataClass.dataClassId}' == ''){
					alert("请选择资料分类");
					return;
				}
				window.location.href=action+"?dataClass.dataClassId=${dataClass.dataClassId}";
			}

			function doDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/bmp/dataClass/dataClass_fillView.action?dataClassPro.id='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.7,
					title : '资料详情'
				});
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<ap:operationbutton />
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<s:form action="dataClass_fillList" id="my_form" theme="simple">
				<!-- 隐藏域 -->
				<table width="100%" class="st">
					<tr>
						<td class="tbLable fr">填报时间：</td>
						<td class="tbValue fl">
							<input type="text" id="dataClassPro.fillTime" name="dataClassPro.fillTime" value="<fmt:formatDate value='${dataClassPro.fillTime}' pattern='yyyy-MM-dd'/>" title="填报时间" class="Wdate " onFocus="WdatePicker()"readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td colspan="4" class="fc" style="border: 0px;">
							<div class="stBtnBar">
								<a class="pop_button" href="javascript:document.getElementById('my_form').submit();"><span>查 询</span></a>
								<a class="pop_button" href="javascript:document.getElementById('my_form').reset();"><span>重 置</span></a>
							</div>
						</td>
					</tr>
				</table>
		 	</s:form>

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleListIco">
						【${dataClass.name}】资料列表
					</div>
						<!-- 右侧按钮 -->
						<div class="panel_btn_bar pop_button_bar">

						</div>
					</div>
					<div class="panel_content">
						<s:if test="#request.dataClassProList.size>0">
							<ec:table items="dataClassProList" var="aml" tableId="dataClassProList" border="0"
								action="${ctx}/bmp/dataClass/dataClass_fillList.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<ec:row>
									<ec:column property="id" alias="id_checkbox" width="5%" cell="checkbox" headerCell="checkbox"/>
									<ec:column property="fillPerson" title="填报人" width="15%" style="line-height:20px;padding:5px;">
									</ec:column>
									<ec:column property="auditPerson" title="审核人" width="15%" style="line-height:20px;padding:5px;">
									</ec:column>
									<ec:column property="fillTime" title="填报日期" parse="yyyy-MM-dd HH:mm:ss" cell="date" width="12%" style="line-height:20px;padding:5px;"/>
									 <ec:column property="null" title="详  情" width="5%">
										<a href="javascript:doDetail('${aml.id}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0"/></a>
									</ec:column>
								</ec:row>
							</ec:table>
						</s:if>
						<!-- 没有数据时给出提示 -->
						<s:else>
							<u:noData text="暂无数据！"/>
						</s:else>
					</div>
				</div>
			</div>
	</body>
</html>