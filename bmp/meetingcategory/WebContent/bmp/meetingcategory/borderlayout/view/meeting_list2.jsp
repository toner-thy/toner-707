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
		<title>【会议（活动）记录】列表</title>
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
			//详情
			function view(id){
				var date = new Date();
				environment.dialog.open({
					url : "${ctx}/bmp/meeting/meeting_view.action?meeting.meetingId=" + id + "&date="+date.getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '会议详情'
				});
			}

			//导出
			function doExport(action){
				window.location.href=action+"?meeting.meetingCategory.meetingCategoryId=${meeting.meetingCategory.meetingCategoryId}";
			}

			//新增
			function doAdd(action){
				window.location.href=action+"?meetingCategoryId=${meeting.meetingCategory.meetingCategoryId}";
			}

			//编辑
			function doEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}
				window.location.href=action+"?meeting.meetingId="+items[0].value
			}

			//删除
			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}
				if(window.confirm("确定删除吗？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids+"meeting.meetingCategory.meetingCategoryId=${meeting.meetingCategory.meetingCategoryId}";
				}
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
			<cp:start defaultTitle="所有会议简介" ctx="${ctx}" icoPath="${ctx}/meetingcategory/borderlayout/skin/blue/img/add_cpIco.gif">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','所有会议简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','所有会议搜索');">查 询</div>
			</cp:start>
			<cp:msg show="false" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于所有会议
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_meeting_list"> </cpc:tc>
				</div>

				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

			</cp:msg>
			<cp:search show="true" divId="cp002">
				<s:form action="meeting_list" id="meeting_release_myList_form" theme="simple">
					<!-- 隐藏域 -->
					<table width="100%" class="st">
						<tr>
							<td class="tbLable fr">会议主题：</td>
							<td colspan="3">
								<input type="text" style="width:95%" name="meeting.meetingName" id="meeting.meetingName" value="${meeting.meetingName}"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">开会日期：</td>
							<td class="tbValue fl">
								<input type="text" id="meeting.meetingTime" name="meeting.meetingTime" value="<fmt:formatDate value='${meeting.meetingTime }' pattern='yyyy-MM-dd'/>" title="举行时间" class="Wdate " onFocus="WdatePicker()"readonly="readonly" />
							</td>
							<td class="tbLable fr">会议类型：</td>
							<td class="tbValue fl">
								<s:select list="#request.meetingCategoryList"  value="%{meeting.meetingCategory.meetingCategoryId}" id="meeting.meetingCategory.meetingCategoryId" name="meeting.meetingCategory.meetingCategoryId" listKey="meetingCategoryId" listValue="categoryName" theme="simple" style="width:125px;" ></s:select>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="fc" style="border: 0px;">
								<div class="stBtnBar">
									<a class="pop_button" href="javascript:document.getElementById('meeting_release_myList_form').submit();"><span>查 询</span></a>
									<a class="pop_button" href="javascript:document.getElementById('meeting_release_myList_form').reset();"><span>重 置</span></a>
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
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleListIco">
						【${meeting.meetingCategory.categoryName}】会议列表
					</div>
						<!-- 右侧按钮 -->
						<div class="panel_btn_bar pop_button_bar">

						</div>
					</div>
					<div class="panel_content">
						<s:if test="#request.allMeetingList.size>0">
							<ec:table items="allMeetingList" var="aml" tableId="allMeetingList" border="0"
								action="${ctx}/bmp/meeting/meeting_list.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<ec:row>
									<ec:column property="meetingId" alias="meetingId_checkbox" width="5%" cell="checkbox" headerCell="checkbox"/>
									<ec:column property="meetingName" title="会议主题" width="15%" style="line-height:20px;padding:5px;">
										<div title="${aml.meetingName}">${fn:substring(aml.meetingName,0,10)}<c:if test="${fn:length(aml.meetingName)>10}">...</c:if></div>
									</ec:column>
									<ec:column property="meetingCategory.categoryName" title="会议类型" width="15%" style="line-height:20px;padding:5px;">
										<div title="${aml.meetingCategory.categoryName}">${fn:substring(aml.meetingCategory.categoryName,0,10)}<c:if test="${fn:length(aml.meetingCategory.categoryName)>10}">...</c:if></div>
									</ec:column>
									<ec:column property="meetingTime" title="开会时间" parse="yyyy-MM-dd HH:mm:ss" cell="date" width="12%" style="line-height:20px;padding:5px;"/>
									<ec:column property="presenter.name" title="主持人" width="15%" style="line-height:20px;padding:5px;"/>
									<ec:column property="recorder.name" title="记录人" width="15%" style="line-height:20px;padding:5px;"/>
									 <ec:column property="null" title="详  情" width="5%">
										<a href="javascript:view('${aml.meetingId}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0"/></a>
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