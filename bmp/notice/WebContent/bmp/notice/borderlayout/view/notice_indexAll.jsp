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
		<title>公告管理</title>

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

			function doAdd(action){
				//window.location.href=action;
				TabUtil.openAsTab({
					url : action + "?_dt="+ new Date().getTime(),
					title : '公告管理-新增',
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

			function doEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				//window.location.href=action + "?id="+items[0].value;
				TabUtil.openAsTab({
					url : action + "?id="+items[0].value + "&_dt="+ new Date().getTime(),
					title : '公告管理-编辑',
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

			function doDel(action) {
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
			//查看明细
			function doDetail(noticeId){
				var date = new Date();
				var action = "${ctx}/bmp/notice/notice_detail.action?id="+noticeId+"&date="+date.getTime();
				$ENV.dialog.open({
					url : action,
					width : 0.8,
					height : 0.9,
					title : '公告详情'
				});
			}

		</script>
	</head>

	<body>
		<div class="body_content" style="top: 0px;">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						公告列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.noticeList.size>0">
						<ec:table items="noticeList" var="notice" tableId="noticeList" border="0"
								action="${ctx}/bmp/notice/notice_indexAll.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="noticeId" alias="noticeId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="noticeName" title="公告标题" width="50%"/>
								<ec:column property="null" title="发布时间" width="10%">
									<c:if test="${notice.noticePublishDate!=null}">
										<div style="font-family: Arial, 'Times New Roman' !important;">
											<s:date name="#attr.notice.noticePublishDate" format="yyyy年MM月dd日"/>
										</div>
									</c:if>
									<c:if test="${notice.noticePublishDate==null}">
										暂 无
									</c:if>
								</ec:column>
								<ec:column property="noticePublisher" title="发布人" width="10%"/>
								<ec:column property="null" title="详 情" width="2%"><a href="###" onclick="doDetail('${notice.noticeId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="详细信息"/></a></ec:column>
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