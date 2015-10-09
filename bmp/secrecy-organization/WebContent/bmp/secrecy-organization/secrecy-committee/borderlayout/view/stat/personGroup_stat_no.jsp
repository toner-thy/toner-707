<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>单位保密组织机构(综合统计)</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					new SimpleUI.SimpleTab({
						el : 'main_tab',
						onActive : function(item, content, index) {

						},
						onFirstActive : function(item, content, index) {
							//alert(content.get('url'));
							new IFrame({
								src : content.get('url'),
								frameborder : 0,
								styles : {
									width : content.getSize().x,
									height: 500
								},
								events : {
									load : function() {
									}
								}
							}).inject(content);
						}
					});

					new SimpleUI.SimpleTab({
						el : 'main_tab1',
						onActive : function(item, content, index) {

						},
						onFirstActive : function(item, content, index) {
							//alert(content.get('url'));
							new IFrame({
								src : content.get('url'),
								frameborder : 0,
								styles : {
									width : content.getSize().x,
									height: 500
								},
								events : {
									load : function() {
									}
								}
							}).inject(content);
						}
					});

				});

			});

			//查看人员详情
			function doView(id){
				$ENV.dialog.open({
					url : '${ctx}/platform/userinfo/userInfo_detail.action?userInfo.userInfoId='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.7,
					title : '人员详细信息'
				});
			}

			function doEdit(){
				window.location.href="${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_edit.action?secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}";
			}

			function download(hostId,attachId){
				window.location.href = "<s:url action="part_download" includeParams="false"/>?part.partId=" + hostId + "&attachment.attachId=" + attachId;
			}

			function deleteAttachment(attachId){
				window.location.href = "<s:url action="part_deleteAttachment" includeParams="false"/>?attachment.attachId=" + attachId;
			}

			// 详 情
			function doDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/secrecyorganization/secrecyWorkOrganRelationMember/secrecyWorkOrganRelationMember_detail.action?secrecyWorkOrganRelationMember.secrecyWorkOrganRelationId='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '单位保密工作机构成员详情'
				});
			}

			function doDetailUnit(id){
				$ENV.dialog.open({
					url : '${ctx}/secrecyorganization/secrecyWorkOrganMemberUnit/secrecyWorkOrganMemberUnit_memberdetail.action?secrecyWorkOrganMemberUnit.secrecyWorkUnitMemberId='+id+'&secrecyWorkOrgan.secrecyWorkOrganId=${secrecyWorkOrgan.secrecyWorkOrganId}&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '单位保密工作机构成员详情'
				});
			}

		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						组织机构
					</div>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_content panel_content_table">
					<u:noData text="暂无数据"/>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

		<form id="personGroup_list" action="${ctx}/personGroup_list.action" method="post">
		</form>
	</body>
</html>