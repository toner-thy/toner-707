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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>我的附件</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<s:actionmessage theme="messages"/>

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

			//新增附件
			function doAdd(action){
				window.location.href=action;
			}

			//编辑附件
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
					url : action + "?id="+items[0].value + "&_dt="+ new Date().getTime(),
					title : '附件管理-编辑',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在编辑的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								$ENV.page.refresh();
							}
							}else {
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

			//删除附件
			function doDelete(action) {
				var items = EcTable.getCheckedItems();
				if(items.length==0) {
					alert("请至少选择一项记录！");
					return;
				}
				if(window.confirm('确定删除所选记录吗？')){
					var ids = "";
					items.each(function(item){
					ids += item.value + ",";
					});
					$('deleteIds').value = ids;
					var forms = $('delete_form');
					forms.action = action;
					forms.submit();
					}
				}
			function doDownload(attachId){
				window.location.href="${ctx}/bmp/attachment/attachment_download.action?attachment.attachId="+attachId;
			}

			var DateUtil = {
				format:function(date){
				return date.getYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
			}
			};

			function doSearch(type, typeValue){

					var startTime = '';
					var endTime = '';

				if(type == 'today'){
					var dTime = new Date().getTime();
					var x = 1*24*60*60*1000;

					startTime = DateUtil.format(new Date());
					endTime = DateUtil.format(new Date(dTime + x));
				}else if(type == 'month'){
					var dTime = new Date().getTime();
					var xs = 1*24*60*60*1000;
					var x = 30*24*60*60*1000;

					endTime = DateUtil.format(new Date(dTime + xs));
					startTime = DateUtil.format(new Date(dTime - x));
				}else if(type == 'threeMonth'){
					var dTime = new Date().getTime();
					var xs = 1*24*60*60*1000;
					var x = 90*24*60*60*1000;

					endTime = DateUtil.format(new Date(dTime + xs));
					startTime = DateUtil.format(new Date(dTime - x));
				}

				window.location.href='${ctx}/bmp/attachment/attachment_myList.action?attachmentQueryDto.startTime=' + startTime + '&attachmentQueryDto.endTime=' + endTime+'&timeType='+typeValue;
			}
			//点击跳转到显示附件明细列表
			function showListAll( id ){
				TabUtil.openAsTab({
					url : "${ctx}/bmp/attachment/attachment_allList.action?attachmentList.attachmentListId="+id+"&_dt="+ new Date().getTime(),
					title : '附件-明细',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定退出吗？")) {
							if(item.content.getContentWindow().needReload){
								$ENV.page.refresh();
							}
							}else {
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

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="我的附件简介" ctx="${ctx}" icoPath="/bmp/attachment/borderlayout/skin/blue/img/allList_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','我的附件简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','我的附件搜索');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于我的附件
					</div>
					<div class="cpMsgContext">
						我的附件可以帮助您在日常工作对工作附件的统一管理。
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form action="${ctx}/bmp/attachment/attachment_myList.action" method="post" id="attachmentForm">
						<table width="100%" class="st">
							<tr>
								<td class="tbLable fr">
									附件标题：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" value="${attachmentQueryDto.attachmentName }" name="attachmentList.attachmentListName" />
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('attachmentForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('attachmentForm').reset();"><span>重 置</span></a>
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
						我的附件列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.attList.size>0">
						<ec:table items="attList" var="attachmentList" tableId="attachmentList" border="0"
								action="${ctx}/bmp/attachment/attachment_myList.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="attachmentListId" alias="attachId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="null" title="附件标题" width="40%">
									<a href="###" onclick="showListAll('${attachmentList.attachmentListId}');">${attachmentList.attachmentListName}</a>
								</ec:column>
								<ec:column property="null" title="上传时间" width="40%">
									<c:if test="${attachmentList.createTime!=null}">
										<div style="font-family: Arial, 'Times New Roman' !important;">
											<s:date name="#attr.attachmentList.createTime" format="yyyy年MM月dd日"/>
										</div>
									</c:if>
									<c:if test="${attachmentList.createTime==null}">
										暂 无
									</c:if>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<styles:nolist/>
					</s:else>

					<form id="delete_form" name="delete_form" method="post">
						<input id="deleteIds" name="deleteIds" type="hidden"/>
					</form>

				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>