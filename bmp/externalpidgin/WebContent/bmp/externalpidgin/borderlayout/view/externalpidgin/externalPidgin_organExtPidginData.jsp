<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib prefix="cp" uri="/WEB-INF/tags/cp.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密涉外活动记录列表</title>

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/mootools-core.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/mootools-more.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/formcheck/formcheck.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/utils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/TabUtils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
		<script src="${ctx}/resources/js/SimpleUI/SimpleUI.js" type="text/javascript"></script>

		<s:actionmessage theme="messages"/>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/bmp/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />
		
		<script language="javascript">
			function doDetail(action,externalPidginId){
				environment.dialog.open({
					url : action+'?id='+externalPidginId+'&t_date=' + new Date().getTime(),
					width : 800,
					height : 600,
					icon : '${pageContext.request.contextPath}/platform/theme/default/images/main/display.gif',
					title : '涉密涉外活动列表'
				});
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		
		<div class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						涉密涉外活动记录列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.externalPidginList.size>0">
						<ec:table items="externalPidginList" var="externalPidgin" tableId="externalPidginList" border="0"
							action="${ctx}/externalPidgin_organExtPidginData.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="eternalPidginName" title="涉密涉外活动名称"/>
								<ec:column property="organ.organName" title="所属单位"/>
								<ec:column property="mainOrgan" title="主办单位"/>
								<ec:column property="secrecyLevel" title="密 级">
									<dictionary:text fieldCode="secrecy_level" optionValue="${externalPidgin.secrecyLevel}"/>
								</ec:column>
								<ec:column property="null" title="开始时间">
									<div style="font-family: Arial, 'Times New Roman' !important;">
										<s:date name="#attr.externalPidgin.startDate" format="yy年MM月dd日"/>
									</div>
								</ec:column>
								<ec:column property="null" title="结束时间">
									<div style="font-family: Arial, 'Times New Roman' !important;">
										<s:date name="#attr.externalPidgin.endDate" format="yy年MM月dd日"/>
									</div>
								</ec:column>
								<ec:column property="null" title="显示详情" width="8%">
									<a href="javascript:doDetail('${ctx}/externalPidgin_detail.action','${externalPidgin.externalPidginId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<styles:nolist/>
					</s:else>
				</div>
			</div>
		</div>
	</body>
</html>