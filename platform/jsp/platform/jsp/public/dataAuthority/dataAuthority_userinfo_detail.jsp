<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib prefix="secPersonlist" uri="http://www.cdthgk.com/tags/secrecyperson/list"%>
<%@ taglib prefix="nestedlist" uri="http://www.cdthgk.com/bmp/tags/nestedlist"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>人员数据权限详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<style type="text/css" media="print">
			.no_print{display:none;}
		</style>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});


			function edit3(action) {
				window.location.href=action+"?userInfo.userInfoId=${userInfo2.userInfoId}&dataAuthority.dataId=${dataAuthority.dataId}";
			}
			function reback(action) {
				window.location.href=action+"?dataAuthority.dataId=${dataAuthority.dataId}";
			}
		</script>



	</head>

	<body style="overflow: auto;overflow-x: hidden;">
		<div class="button_bar">
				<div class="left">
					<div class="pop_button_bar">
					  <s:if test="#request.map.size>0">
					<a class="pop_button" href="javascript:edit3('${ctx}/bmp/dataAuthority/dataAuthority_edit.action');" id="sbm_button"><span>编辑</span></a>
					</s:if>
					<a class="pop_button" href="javascript:reback('${ctx}/bmp/dataAuthority/dataAuthority_query_list_list.action');"><span>返回列表</span></a>
					</div>
				</div>
				<div class="right">
					<div class="pop_button_bar">
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
					 <s:property value="value.name"/>--数据权限详情
				 </s:if>
				 </s:iterator>
				</div>
				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">
			   <s:if test="#request.map.size>0">
					<table class="content_table">
						 <s:iterator var="mc" value="#request.map" status="mapList">
									<tr>
										<td class="tbLable fr">数据权限类型：</td>
										<td class="tbValue fl" colspan="5">
											<s:property value="key.name"/>
										</td>

									</tr>
									<tr>
										<td class="tbLable fr" style="width: 10%">用户名：</td>
										<td class="tbValue fl" style="width: 10%">
											<s:property value="value.name"/>
										</td>
										<td class="tbLable fr" style="width: 10%">部门名称：</td>
										<td class="tbValue fl" style="width: 20%">
											<s:property value="value.department.departmentName"/>
										</td>
										<td class="tbLable fr" style="width: 10%">单位名称：</td>
										<td class="tbValue fl" style="width: 20%">
											<s:property value="value.organ.organName"/>
										</td>
									</tr>
								<!-- 隐藏提交按钮 -->
								<div align="center">
									<input id="sub" value="sub" type="submit" style="display: none;" />
								</div>
						</s:iterator>
					</table>
				</s:if>
				<s:else>
					<u:noData text="当前暂无数据权限信息" />
				</s:else>
			</div>
		</div>
		<!-- 内容panel结束 -->
	</div>
	</body>
</html>