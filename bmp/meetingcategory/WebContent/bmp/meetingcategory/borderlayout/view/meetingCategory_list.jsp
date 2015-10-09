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
		<title>会议分类列表</title>

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
  			function doAdd(action){
  				var meetingCategoryId = '${parentMeetingCategory.meetingCategoryId}';
  				TabUtil.openAsTab({
					url : action + "?meetingCategory.meetingCategoryId="+meetingCategoryId,
					title : '会议分类-新增',
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

  			//编辑
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
					url : action + "?meetingCategory.meetingCategoryId="+items[0].value,
					title : '会议分类-编辑',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在编辑的内容吗？")) {
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

  			//删除
  			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要删除的项。");
					return;
				}
				if(window.confirm("确定要删除吗？删除该分类同时会删除分类下所有会议。")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
  			}

  			//详情
  			function doView(id){
  				environment.dialog.open({
					url : '${pageContext.request.contextPath}/bmp/meetingcategory/meetingCategory_view.action?meetingCategory.meetingCategoryId='+ id +'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '会议分类详情 '
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
			<cp:start defaultTitle="会议分类简介" ctx="${ctx}" icoPath="${ctx}/meetingcategory/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','会议分类简介');">关 于</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					会议分类
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_meeting_type"> </cpc:tc>
				</div>

				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

			</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
		  				${parentMeetingCategory.categoryName}&nbsp;&nbsp;会议分类列表
		  			</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<s:if test="#request.meetingCategoryList.size > 0">
						<!-- list -->
						<ec:table items="meetingCategoryList" var="meetingCategory" tableId="meetingCategoryList" border="0"
								action="${pageContext.request.contextPath}/bmp/meetingcategory/meetingCategory_list.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="meetingCategoryId" alias="meetingCategoryId_checkbox" width="5%" cell="checkbox" headerCell="checkbox"/>
								<ec:column property="categoryName" title="分类名称" width="10%" style="line-height:20px;padding:5px;" cell="text" alias="size=10"/>
								<ec:column property="organ.organName" title="所属单位" width="15%" style="line-height:20px;padding:5px;" cell="text" alias="size=10"/>
								<ec:column property="department.departmentName" title="所属部门" width="20%" style="line-height:20px;padding:5px;" cell="text" alias="size=12"/>
								<ec:column property="createPerson.userInfo.name" title="创建人" width="20%" style="line-height:20px;padding:5px;"/>
								<ec:column property="createTime" format="yyyy-MM-dd" title="创建日期" width="10%" cell="date" style="line-height:20px;padding:5px;"/>
								<ec:column property="meetingCategoryId" title="详 情" width="5%"><a href="###" onclick="doView('${meetingCategory.meetingCategoryId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" title="详细信息" border="0"/></a></ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
							<u:noData text="暂无数据！"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
			<form action="${pageContext.request.contextPath}/bmp/meetingcategory/meetingCategory_list.action" id="secPersonTrain_list_form" method="post">
			</form>
		</div>
	</body>
</html>