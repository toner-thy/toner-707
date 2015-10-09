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
					url : action,
					title : '涉密人员培训记录列表-新增',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								document.getElementById('secPersonTrain_list_form').submit();
							}
						} else {
							return false;
						}
					}else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('secPersonTrain_list_form').submit();
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
					url : action + "?secPersonTrain.secPersonTrainId="+items[0].value,
					title : '涉密人员培训列表-编辑',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								$ENV.page.refresh();
							}
						} else {
							return false;
						}
					}else{
							if(item.content.getContentWindow().needReload){
								$ENV.page.refresh();
							}
						}
					}
				});
			}

			function doDelete(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}
				if(window.confirm("确定删除？")){
					var ids = "";
					items.each(function(item){
						ids += item.value + ",";
					});
					document.getElementById("secPersonTrainIds").value = ids;
					document.getElementById("secPersonTrainDelForm").action = action;
					document.getElementById("secPersonTrainDelForm").submit();
				}
			}

			function doDetail(secPersonTrainId){
				environment.dialog.open({
					url : '${ctx}/bmpcd/secpersontrain/secPersonTrain_detail.action?secPersonTrain.secPersonTrainId='+secPersonTrainId+'&t_date=' + new Date().getTime(),
					width : 860,
					height : 560,
					icon : '${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif',
					title : '涉密人员培训详情 '
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
					<a href='/bmp/help/help/help_clientInfo.do?help.helpId=secPersonTrain_course' class='pop_button' target='_back'/><span>操作教程</span></a>
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密人员培训记录简介" ctx="${ctx}" icoPath="${ctx}/images/technictrain/secPersonTrain_list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','涉密人员培训记录简介');">简 介</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','涉密人员培训记录搜索');">搜 索</div>
			</cp:start>
				<cp:msg show="false" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密人员培训记录
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_person_record"> </cpc:tc>
					</div>
				</cp:msg>
				<cp:search show="true" divId="cp002">
					<form action="${ctx}/bmpcd/secpersontrain/secPersonTrain_list.action" id="secPersonTrain_list_form" method="post">
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									单位名称：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secPersonTrain.workPlace" id="secPersonTrain.workPlace" value="${secPersonTrain.workPlace}"/>
								</td>
								<td class="tbLable fr">
									姓 名：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secPersonTrain.secPersonName" id="secPersonTrain.secPersonName" value="${secPersonTrain.secPersonName}"/>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('secPersonTrain_list_form').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('secPersonTrain_list_form').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密人员培训列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<s:if test="#request.secPersonTrainList.size>0">
						<ec:table items="secPersonTrainList" var="secPersonTrain" tableId="secPersonTrainList" border="0"
							action="${ctx}/bmpcd/secpersontrain/secPersonTrain_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secPersonTrainId" alias="secPersonTrainId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="workPlace" title="工作单位"/>
								<ec:column property="secPersonName" title="姓 名"/>
								<ec:column property="null" title="性 别">
									<c:if test="${secPersonTrain.sex eq 1}">男</c:if>
									<c:if test="${secPersonTrain.sex eq 0}" >女</c:if>
								</ec:column>
								<ec:column property="duty" title="职 务"/>
								<ec:column property="trianGrade" title="培训成绩"/>
								<ec:column property="null" title="详 情" width="2%">
									<a href="###" onclick="doDetail('${secPersonTrain.secPersonTrainId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="详细信息"/>
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<styles:nolist/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

		<!-- 删除用隐藏表单 -->
		<form action="" method="post" id="secPersonTrainDelForm">
			<input type="hidden" name="secPersonTrainIds" id="secPersonTrainIds"/>
		</form>
	</body>
</html>