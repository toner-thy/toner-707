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
		<title>技术培训列表</title>

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
			function doAdd(action){
				TabUtil.openAsTab({
					url : action + '?technicTrainList_crd=${param.technicTrainList_crd}&technicTrainList_p=${param.technicTrainList_p}',
					title : '技术培训-新增',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								document.getElementById('queryform').submit();
							}
						} else {
							return false;
						}
					}else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('queryform').submit();
							}
						}
					}
				});
			}

			function doEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				TabUtil.openAsTab({
					url : action + '?technicTrainList_crd=${param.technicTrainList_crd}&technicTrainList_p=${param.technicTrainList_p}&technicTrain.technicTrainingId='+items[0].value,
					title : '技术培训-编辑',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								document.getElementById('queryform').submit();
							}
						} else {
							return false;
						}
					}else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('queryform').submit();
							}
						}
					}
				});
			}

			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要删除的项。");
					return;
				}
				if(window.confirm("确定要删除吗？")){
					var ids = "?technicTrainList_crd=${param.technicTrainList_crd}&technicTrainList_p=${param.technicTrainList_p}&";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}
			// 详情
			function doView1(id){
				environment.dialog.open({
					url : '${ctx}/bmp/technicTrain/technicTrain_view.action?technicTrain.technicTrainingId='+id+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '技术培训详情'
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

		<div id="body_content" class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="技术培训简介" ctx="${ctx}" icoPath="${ctx}/technictrain/borderlayout/skin/blue/img/add_cpIco.gif">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','技术培训简介');">简 介</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','技术培训搜索');">搜 索</div>
			</cp:start>
				<cp:msg show="false" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于技术培训
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="technicTrain_info"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="true" divId="cp002">
					<s:form action="technicTrain_list.action" id="queryform" namespace="technicTrain" theme="simple">
						<!-- 隐藏域 -->
						<input type="hidden" name="technicTrainList_crd" value="${param.technicTrainList_crd }" />
						<input type="hidden" name="technicTrainList_p" value="${param.technicTrainList_p }" />

						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									培训主题：
								</td>
								<td class="tbValue fl" colspan="3">
									<input name="technicTrainDto.technicTrain.trainingTitle" id="technicTrainDto.technicTrain.trainingTitle" type="text" value="${technicTrainDto.technicTrain.trainingTitle}" size="40" />
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('queryform').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('queryform').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						技术培训列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<s:if test="#request.technicTrainList.size>0">
					<ec:table items="technicTrainList" var="technicTrain" tableId="technicTrainList" border="0"
							action="${ctx}/bmp/technicTrain/technicTrain_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
							<ec:column property="technicTrainingId" alias="technicTrainId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
							<ec:column property="trainingTitle" title="培训主题" width="20%" cell="text" alias="size=12"/>
							<ec:column property="null" title="培训时间" width="8%">
								<div style="font-family: Arial, 'Times New Roman' !important;">
										<s:date name="#attr.technicTrain.trainingDate" format="yy年MM月dd日"/>
								</div>
							</ec:column>
							<ec:column property="trainingPlace" title="培训地点" width="20%" cell="text" alias="size=12"/>
							<ec:column property="null" title="详情" width="6%"><a href="###" onclick="doView1('${technicTrain.technicTrainingId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a></ec:column>
						</ec:row>
					</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据！"></u:noData>
					</s:else>
				</div>
			</div>
			<!-- 内容panel开始 -->
		</div>
	</body>
</html>