<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>数据权限维护新增</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/FCKeditor/fckeditor.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
				});
			});


			function deletUserRole(uerInfoID,dataId,leixin){
				if(leixin=='delete')
					{
						if(confirm("温馨提示：您确定要移除?"))
							{
								action='${ctx}/bmp/dataAuthority/dataAuthority_editing.action';
								window.location.href=action+"?userInfo.userInfoId="+uerInfoID+"&dataAuthority.dataId="+dataId+"&leixin="+leixin;
							}
					}
				else
					{

					}

			 }

			function doBack22(){
				action='${ctx}/bmp/dataAuthority/dataAuthority_detail_userInfo.action';
				window.location.href=action+"?dataAuthority.dataId=${dataAuthority.dataId}"+"&userInfo.userInfoId=${userInfo.userInfoId}";
			}


		</script>
</head>

<body>
	<!-- 公共头部 -->
	<div class="button_bar">
		<div class="left">
			<div class="pop_button_bar">
				<a class="pop_button" href="javascript:doBack22();"><span>返回</span></a>
			</div>
		</div>
		<div class="right">
			<div class="pop_button_bar">
				<a class="pop_button pop_button_refresh" href="#"
					onclick="javascript:window.refresh();"><span>刷新本页</span></a> <a
					class="pop_button pop_button_close" href="#"
					onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
			</div>
		</div>
	</div>

	<div id="body_content" class="body_content" style="width: 99%;">

	        <!-- 复合面板开始 -->
			<cp:start defaultTitle="数据权限维护简介" ctx="${ctx}" icoPath="/bmp/disclose_secrecy/borderlayout/skin/blue/img/08.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','数据权限维护简介');">关 于</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于数据权限维护
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_discloseSecrecy"> </cpc:tc>
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
				<s:iterator var="mc" value="#request.map" status="mapList">
				 <s:if test="#mapList.index==0">
					 编辑《<s:property value="value.name"/>》数据权限
				 </s:if>
				 </s:iterator>
				</div>
				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
					<table class="content_table">
						<s:iterator var="mc" value="#request.map" status="mapList">
							<tr>
										<td class="tbLable fr" style="width: 20%">数据权限类型：</td>
										<td class="tbValue fl" style="width: 20%">
											<s:property value="key.name"/>
										</td>
										<td class="tbLable fr" style="width: 15%">人员名称：</td>
										<td class="tbValue fl" style="width: 15%">
										 <s:property value="value.name"/>
										</td>
										<td class="tbValue fl" style="width: 30%">
										   <%--  <a href="###" onclick="deletUserRole('<s:property value="value.userInfoId"/>','<s:property value="key.dataId"/>','alert');"> 修改</a>| --%>
										    <a href="###" onclick="deletUserRole('<s:property value="value.userInfoId"/>','<s:property value="key.dataId"/>','delete');"> 移除</a>
										</td>

							</tr>
						</s:iterator>
					</table>
			</div>
		</div>
		<!-- 内容panel结束 -->
	</div>

</body>
</html>