<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>年度计划任务书</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

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
									height: 300
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
			// 详 情
			function doDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/secrecyorganization/secrecyWorkOrganRelationMember/secrecyWorkOrganRelationMember_detail.action?secrecyWorkOrganRelationMember.secrecyWorkOrganRelationId='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '单位年度计划任务书成员详情'
				});
			}
			//导出
			function doExport(){
				window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_exportData.action?district.districtCode=${district.districtCode}&t_date=" + new Date().getTime();
			}
		</script>
<style type="text/css">
	.contentTable td{
		border-color: #B9D2DE;
		padding: 5px 5px;
	}
	.contentTable {
	    background-color: #FFFFFF;
	    color: #446586;
	}
</style>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">

				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						要害部门检查
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="contentTable" width="900" border="1" align="center" cellpadding="0" cellspacing="0">
					        	<tr>
					         		 <td width="204" align="center" >XXX规则1</td>
					         		 <td >
										完成
									</td>
					             </tr>
					             <tr>
					         		 <td width="116" align="center">XXX规则2</td>
					         		 <td >
										完成
									</td>
					             </tr>
					             <tr>
					         		 <td width="159" align="center" >XXX规则3</td>
					         		 <td >
										完成
									</td>
					             </tr>
					             <tr>
					          		<td width="159" align="center" >XXX规则4</td>
					          		<td >
										完成
									</td>
					             </tr>
					             <tr>
					        		 <td width="73" align="center" >XXX规则5</td>
					        		 <td >
										完成
									</td>
					             </tr>
					             <tr>
					          		<td width="73" align="center" >XXX规则6</td>
					          		<td >
										完成
									</td>
					             </tr>
					             <tr>
					          		<td width="73" align="center" >XXX规则7</td>
					          		<td >
										完成
									</td>
					             </tr>
					             <tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>