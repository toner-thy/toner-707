<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户反馈列表页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});

			function doAdd(action){
				TabUtil.openAsTab({
					url : action,
					title : '意见反馈',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
									window.location.href ="${actionRequestURI}";
						}else{
									window.location.href ="${actionRequestURI}";
							}
					}
				});
			}

			function doEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				TabUtil.openAsTab({
					url : action + "?feedbackInfo.feedbackInfoId="+items[0].value,
					title : '反馈意见-编辑',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									document.getElementById('feedback_list_form').submit();
								}
							} else {
								return false;
							}
						} else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('feedback_list_form').submit();
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
					document.getElementById("feedbackIds").value = ids;
					document.getElementById("feedbackDelForm").action = action;
					document.getElementById("feedbackDelForm").submit();
				}
			}

			function doDetail(feedbackInfoId){
				/*
					environment.dialog.open({
					url : '${ctx}/platform/feedbackInfo/feedbackInfo_detail.action?feedbackInfo.feedbackInfoId='+feedbackInfoId,
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					//icon : '${ctx}/platform/theme/default/images/main/display.gif',
					title : '意见反馈详情'
				});
				*/
				var date = new Date();
				var action = "${ctx}/platform/feedbackInfo/feedbackInfo_detail.action?feedbackInfo.feedbackInfoId="+feedbackInfoId+"&date="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '意见反馈详情'
				});
			}

		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<%--
					<a id="add" name="add" class="pop_button" href="javascript:doAdd('${ctx}/platform/feedbackInfo/feedbackInfo_add.action')"><span>新 增</span></a>
					<a id="edit" name="edit" class="pop_button" href="javascript:doEdit('${ctx}/platform/feedbackInfo/feedbackInfo_edit.action')"><span>编 辑</span></a>
					<a id="delete" name="delete" class="pop_button" href="javascript:doDelete('${ctx}/platform/feedbackInfo/feedbackInfo_delete.action')"><span>删 除</span></a>
 					--%>
 					<ap:operationbutton />
 				</div>
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
			<cp:start defaultTitle="意见反馈简介" ctx="${ctx}" icoPath="/bmp/feedbackInfo/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','意见反馈简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','意见反馈搜索');">查 询</div>
			</cp:start>
				<cp:msg show="false" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于意见反馈
					</div>
					<div class="cpMsgContext">
						意见反馈功能用于收集用户在使用系统过程中所遇到的问题或者优化建议。
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="true" divId="cp002">
					<s:form action="feedbackInfo_userList" id="feedback_list_form" name="feedback_list_form" theme="simple">
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									主 题：
								</td>
								<td class="tbValue fl">
									<input type="text" id="feedbackInfo.feedbackTitle" name="feedbackInfo.feedbackTitle" value="${feedbackInfo.feedbackTitle}"/>
								</td>
								<td class="tbLable fr">
									类 型：
								</td>
								<td class="tbValue fl">
									<s:select list="#{'0':'全部','1':'改进','2':'异常','3':'新功能'}" id="feedbackInfo.feedbackType" title="请选择"
										 name="feedbackInfo.feedbackType" value="feedbackInfo.feedbackType" cssStyle="width:130px;" theme="simple">
									</s:select>
								</td>
							</tr>
							<tr>
								<td class="fc" colspan="4" style="border: 0px;">
									<div class="btn_query_bar pop_button_bar">
										<a class="pop_button" href="javascript:document.getElementById('feedback_list_form').submit();"><span>查 询</span></a>&nbsp;&nbsp;
										<a class="pop_button" href="javascript:document.getElementById('feedback_list_form').reset();"><span>重 置</span></a>
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
						反馈信息列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.feedbackInfoList.size>0">
						<ec:table items="feedbackInfoList" var="feedbackInfo" tableId="feedbackInfoList" border="0"
							action="${actionRequestURI}"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="feedbackInfoId" alias="feedbackId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="null" title="类 型" width="10%">
									<c:if test="${feedbackInfo.feedbackType==1}">
										改进
									</c:if>
									<c:if test="${feedbackInfo.feedbackType==2}">
										异常
									</c:if>
									<c:if test="${feedbackInfo.feedbackType==3}">
										新功能
									</c:if>
								</ec:column>
								<ec:column property="feedbackTitle" title="主 题" cell="text" alias="size=18" width="30%"/>
								<ec:column property="organ.organName" title="所在单位" width="20%"/>
								<ec:column property="createTime" cell="date" format="yyyy-MM-dd" title="创建时间" width="15%"/>
								<ec:column property="null" title="状 态" width="15%">
									<c:if test="${feedbackInfo.status == 0}">
										未处理
									</c:if>
									<c:if test="${feedbackInfo.status == 1}">
										已处理
									</c:if>
								</ec:column>
								<ec:column property="null" title="详 情" width="5%">
									<a href="###" onclick="doDetail('${feedbackInfo.feedbackInfoId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="详细信息"/>
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

		<!-- 删除用隐藏表单 -->
		<form action="" method="post" id="feedbackDelForm">
			<input type="hidden" name="feedbackIds" id="feedbackIds"/>
		</form>
	</body>
</html>