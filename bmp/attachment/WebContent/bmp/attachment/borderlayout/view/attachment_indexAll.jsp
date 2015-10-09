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
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
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

			function doAdd(action){
				window.location.href=action;
			}

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
			//下载
			function doDownload(attachId){
				//alert("暂时下载不了，需要登录。。。。。");
				//window.location.href="${ctx}/platform/attachment/attachment_downloading_b_bmpUploadBehavior.action?id="+attachId;
				//window.location.href=url;
				var url = $(attachId).value;
				window.open(url);
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

		<div class="body_content" style="top: 0px;">

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						附件列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.attList.size>0">
						<ec:table items="attList" var="attachmentList" tableId="attachmentList" border="0"
								action="${ctx}/bmp/attachment/attachment_indexAll.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="attachmentListId" alias="attachId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="null" title="附件标题" width="30%">
									<%-- <a href="###" onclick="showListAll('${attachmentList.attachmentListId}');">${attachmentList.attachmentListName}</a> --%>
									${attachmentList.attachmentListName}
								</ec:column>
								<ec:column property="null" title="上传时间" width="10%">
									<c:if test="${attachmentList.createTime!=null}">
										<div style="font-family: Arial, 'Times New Roman' !important;">
											<s:date name="#attr.attachmentList.createTime" format="yyyy年MM月dd日"/>
										</div>
									</c:if>
									<c:if test="${attachmentList.createTime==null}">
										暂 无
									</c:if>
								</ec:column>
								<ec:column property="null" title="附件内容" width="60%">
									<c:if test="${not empty attachmentList.attachmentCollect }">
										<c:forEach items="${attachmentList.attachmentCollect }" var="atta" >
											<%-- <a href="###" onclick="doDownload('"+<attach:url uploadBehavior="bmpUploadBehavior" attachId="${atta.attachId}" />+"')">${atta.attachName }</a><br/> --%>
											<input type="hidden" id="${atta.attachId}" name="${atta.attachId}" value="<attach:url uploadBehavior="bmpUploadBehavior" attachId="${atta.attachId}" />"/>
											<a style="text-decoration: none;" href='###' onclick="doDownload('${atta.attachId}')">
												${fn:substring(atta.attachName,0,30)}
												<c:if test="${fn:length(atta.attachName)>30 }">...</c:if>
											</a>
										</c:forEach>
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