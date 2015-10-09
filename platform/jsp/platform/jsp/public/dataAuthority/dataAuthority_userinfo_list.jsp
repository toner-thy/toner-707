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
<title>数据权限列表</title>

<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />
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
	$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",
			function() {
				$ENV.onDomReady(function() {

				});
			});
	function doDetail(id) {
		$ENV.dialog.open({
			url : "${ctx}/platform/userinfo/userInfo_detail.action?userInfo.userInfoId="+id+"&_ts=" + new Date().getTime(),
			width : 0.8,
			height : 0.6,
			title : '人员数据权限详情'
		});
	}
	function doDetailUserInfo(id) {

		url ="${ctx}/bmp/dataAuthority/dataAuthority_detail_userInfo.action?userInfo.userInfoId="+id
				+"&dataAuthority.dataId=${dataAuthority.dataId}"
				+"&_ts=" + new Date().getTime(),
		 window.location.href =url;
	}
	// 到编辑页面
	function doEdit(action) {
		var items = EcTable.getCheckedItems();
		if(items.length==0) {
			alert("请至少选择一项记录！");
			return;
		} else if(items.length>1) {
			alert("最多只能选择一项记录！");
			return;
		}
		var id='status_'+items[0].value;
		var status=$(id).value;

		if(status==1)
		{
		  window.location.href =action+"?id="+items[0].value;
		}else{
       	   alert("已处理无法再次处理！");
		}
	}


		//添加
		function addUserInfos(action) {
			window.location.href=action+"?dataAuthority.dataId=${dataAuthority.dataId}";
		}
		//添加
		function removeUserInfos(action) {

			var items = EcTable.getCheckedItems();
			if(items.length==0) {
				alert("请至少选择一项记录！");
				return;
			} else if(items.length>=1) {
				if(window.confirm("确定删除？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids+"&&dataAuthority.dataId=${dataAuthority.dataId}";
				}
			}
		}
</script>
</head>
<body>
<!-- 公共头部 -->
	<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
				<c:if test="${dataAuthority.dataAuthority==null}">
				</c:if>
				<c:if test="${dataAuthority.dataAuthority!=null}">
				<a class="pop_button" href="javascript:addUserInfos('${ctx}/bmp/dataAuthority/dataAuthority_add.action');" id="sbm_button"><span>添加</span></a>
				<a class="pop_button" href="javascript:removeUserInfos('${ctx}/bmp/dataAuthority/dataAuthority_del.action');"><span>移除</span></a>
				</c:if>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="#" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="#" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
	</div>
	<div id="body_content" class="body_content">

		<cp:start defaultTitle="数据权限维护简介" ctx="${ctx}"
			icoPath="/bmp/disclose_secrecy/borderlayout/skin/blue/img/11.gif">
			<div id="cp001Btn" class="cpBtn_ov" href="###"
				onmouseover="javascript:showCp(2,'cp001','数据权限维护简介');">关 于</div>
			<div id="cp002Btn" class="cpBtn" href="###"
				onmouseover="javascript:showCp(2,'cp002','数据权限维护搜索');">查 询</div>
		</cp:start>
		<cp:msg show="true" divId="cp001">
			<!-- 模块简介 -->
			<div class="cpMsgTitle">关于数据权限维护</div>
			<div class="cpMsgContext">
				<cpc:tc ctx="${ctx}" showId="">
				</cpc:tc>
			</div>

			<!-- 上下之间的间隔，可以调节高度 -->
			<div class="cpMsgFg" style="height: 7px; overflow: hidden;"></div>
		</cp:msg>
		<cp:search show="false" divId="cp002">
			<form
				action="${ctx}/bmp/dataAuthority/dataAuthority_query_list_list.action"
				id="discloseSecrecy_list_form" name="discloseSecrecy_list_form">
				<table class="st" width="100%">
					<tr>
						<td class="tbLable fr">用户名：</td>
						<td class="tbValue fl" width="30%">
							<input id="userInfo.name" name="userInfo.name" type="text" />
						</td>
						<td class="tbLable fr" width="20%">部门名称：</td>
						<td class="tbValue fl" width="30%">
							<input id="userInfo.department.departmentName" name="userInfo.department.departmentName" type="text"  />
						</td>

					</tr>


					<tr>
						<td class="tbLable fr" width="20%">单位名称：</td>
						<td class="tbValue fl" width="30%">
							<input id="userInfo.organ.organName" name="userInfo.organ.organName" type="text"/>
						</td>
						<c:if test="${dataAuthority.dataAuthority==null}">
						<td class="tbLable fr" width="20%">
							<c:if test="${dataAuthority.dataAuthority==null}">
							${dataAuthority.name}：
							</c:if>
							<c:if test="${dataAuthority.dataAuthority!=null}">
							${dataAuthority.dataAuthority.name}：
							</c:if>
						</td>
						<td class="tbValue fl" width="30%">
							   <select name="dataAuthority.dataId" >
							          <option value="${dataAuthority.dataId}">全部</option>
								      <c:forEach items="${dataAuthoritiesChrild}" var="dataAuthority2">
										<option value="${dataAuthority2.dataId}">${dataAuthority2.name}</option>
								      </c:forEach>
								</select>
						</td>
						</c:if>
						<c:if test="${dataAuthority.dataAuthority!=null}">
								<input type="hidden"  value="${dataAuthority.dataId}" name="dataAuthority.dataId"></input>
						</c:if>
					</tr>

					<tr>
						<td colspan="4" class="fc" style="border: 0px;">
							<div class="stBtnBar">
								<a class="pop_button"
									href="javascript:document.getElementById('discloseSecrecy_list_form').submit();"><span>查
										询</span></a> <a class="pop_button"
									href="javascript:document.getElementById('discloseSecrecy_list_form').reset();"><span>重
										置</span></a>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</cp:search>
		<cp:end>
		</cp:end>
		<!-- 复合面板结束 -->

		<!-- 内容panel开始 -->
		<div class="panel tMargin bMargin">
			<div class="panel_header">

				<div class="panel_title panel_titleListIco">
							${dataAuthority.name}数据维护列表
				</div>

				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
				<!-- 这里自定义ectable -->
				<s:if test="#request.userInfoList.size>0">
					<ec:table items="userInfoList" var="userInfo"
						tableId="userInfoList" border="0"
						action="${ctx}/bmp/dataAuthority/dataAuthority_query_list_list.action"
						imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="userInfoId" width="3%"
								alias="userInfoId_checkbox" cell="checkbox"
								headerCell="checkbox" />
							<ec:column property="null" title="用户名"width="12%" style="text-align: left;">
								<a href="###" onclick="doDetailUserInfo('${userInfo.userInfoId}');">
										${userInfo.name}
								</a>
							</ec:column>
							<ec:column property="department.departmentName" title="部门名称"
								width="12%" style="text-align: left;">
							</ec:column>
							<ec:column property="organ.organName" title="单位名称"
								width="12%" style="text-align: left;">
							</ec:column>
							<ec:column property="null" title="详 情" width="3%">
								<a href="###" onclick="doDetail('${userInfo.userInfoId}');">
								<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情" />
								</a>
							</ec:column>
						</ec:row>
					</ec:table>
				</s:if>
				<s:else>
					<u:noData text="当前暂无数据权限维护信息" />
				</s:else>

			</div>
		</div>
	</div>
</body>
</html>