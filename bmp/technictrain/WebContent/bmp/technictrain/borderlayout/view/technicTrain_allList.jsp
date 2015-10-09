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
				window.location.href=action;
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
				window.location.href=action+"?technicTrain.technicTrainingId="+items[0].value;
			}

			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要删除的项。");
					return;
				}
				if(window.confirm("确定要删除吗？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}

			// 详情
			function doView(id){
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
			<cp:start defaultTitle="保密技术培训简介" ctx="${ctx}" icoPath="${ctx}/technictrain/borderlayout/skin/blue/img/allList_cpIco.gif">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','保密技术培训简介');">简 介</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','保密技术培训搜索');">搜 索</div>
			</cp:start>
				<cp:msg show="false" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于技术培训查询
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="technicTrain_alllist_info"> </cpc:tc>
					</div>
				</cp:msg>
				<cp:search show="true" divId="cp002">
					<s:form action="technicTrain_allList" id="queryform" theme="simple">
						<!-- 隐藏域 -->
						<input type="hidden" name="district.districtCode" value="${district.districtCode}" />

						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									培训主题：
								</td>
								<td class="tbValue fl">
									<input name="technicTrainDto.technicTrain.trainingTitle" id="technicTrainDto.technicTrain.trainingTitle" type="text" value="${technicTrainDto.technicTrain.trainingTitle}" size="20" />
								</td>
								<td class="tbLable fr">
									查询范围：
								</td>
								<td class="tbValue fl">
									<s:radio list="#{'0':'包含子机构','1':'不含子机构'}" name="showType" value="showType"></s:radio>
								</td>
								<%-- <td class="tbLable fr">
									发起单位：
								</td>
								<td class="tbValue fl">
									<input name="technicTrainDto.organ.organName" id="technicTrainDto.organ.organName" type="text" value="${technicTrainDto.organ.organName}" size="20" />
								</td> --%>
							</tr>
							<%-- <tr>
								<td class="tbLable fr">
									查询范围：
								</td>
								<td class="tbValue fl" colspan="3">
									<s:radio list="#{'0':'包含子机构','1':'不含子机构'}" name="showType" value="showType"></s:radio>
								</td>
							</tr> --%>
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

			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断是否查看下级1查看，0不查看-->
						<c:if test="${showType eq '1'}">
							${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 技术培训列表
						</c:if>
						<c:if test="${showType ne '1'}">
							${district.districtName} - 技术培训列表
						</c:if>

					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<s:if test="#request.technicTrainList.size>0">
						<ec:table items="technicTrainList" var="technicTrain" tableId="technicTrainList" border="0"
								action="${ctx}/bmp/technicTrain/technicTrain_allList.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="technicTrainingId" alias="technicTrainId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="organ.organName" title="发起单位" width="25%" cell="text" alias="size=10"/>
								<ec:column property="trainingTitle" title="培训主题" width="30%" cell="text" alias="size=10"/>
								<ec:column property="null" title="培训日期" width="15%">
									<div style="font-family: Arial, 'Times New Roman' !important;">
										<s:date name="#attr.technicTrain.trainingDate" format="yy年MM月dd日"/>
									</div>
								</ec:column>
								<ec:column property="trainingPlace" title="培训地点" width="15%" cell="text" alias="size=10"/>
								<ec:column property="null" title="显示详情" width="10%"><a href="###" onclick="doView('${technicTrain.technicTrainingId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a></ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据！"></u:noData>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>