<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>专项技术检查备案详情</title>

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

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
		</script>

		<script type="text/javascript">
			function doAddTable(){
				window.showModalDialog("secrecyCheckEvent_selectTable.action"
						,{window:window,
						text:'tableText',
						hidden:'tableIds'
						},"dialogWidth=700px;dialogHeight=645px,status=no,directories=no,menubar=no,resizable=yes,scrollbars=no");


			}

			function doCheckTable(){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请选择一项");
					return;
				}
				window.location.href="${ctx}/checkEventTable_list.action?checkEventTable.checkEventTableId="+items[0].value+"&secrecyCheckEvent.secrecyCheckEventId=${secrecyCheckEvent.secrecyCheckEventId}";
			}

			// 下载
			function download(hostId,attachId){
				window.location.href="<s:url action="secrecycheckevent_download" includeParams="false"/>?secrecyCheckEvent.secrecyCheckEventId="+hostId
					+"&attachment.attachId="+attachId;
			}

			function del(attachId,hostId){
				window.location.href='<s:url action="secrecycheckevent_deleteAttachment" includeParams="false"/>?secrecyCheckEvent.secrecyCheckEventId='+hostId+"&attachment.attachId="+attachId;
			}
		</script>
	</head>
	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar"></div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
				</div>
			</div>
		</div>
	 	<div class="body_content" >
			<!-- 内容panel开始 -->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						专项技术检查备案详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<!-- 隐藏域 -->
					<input type="hidden" name="secrecyCheckEvent.secrecyCheckEventId" value="${secrecyCheckEvent.secrecyCheckEventId }">
					<input type="hidden" value="${tableText }" name="tableText" id="tableText">
					<input type="hidden" value="${tableIds }" name="tableIds" id="tableIds">
					<table class="content_table">
						<tr>
							<td align="right" width="152px;">检查内容：</td>
							<td colspan="3">
								${secrecyCheckEvent.eventName}
							</td>
						</tr>
						<tr>
							<td align="right">检查日期：</td>
							<td>
								<s:date format="yyyy年MM月dd日" name="secrecyCheckEvent.eventDate"/>
							</td>
							 <td align="right">参加人数：</td>
						    <td>
						    ${secrecyCheckEvent.joinNumber}
							</td>
						</tr>
						<tr>
							<td align="right">受检部门：</td>
							<td colspan="3">
								<c:if test="${checkedDepartment== ''}">
									未填写
								</c:if>
								<c:if test="${checkedDepartment!= ''}">
									${checkedDepartment}
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">检查情况：</td>
							<td colspan="3" height="100" valign="top">
								<c:if test="${secrecyCheckEvent.checkCircs == ''}">
									未填写
								</c:if>
								<c:if test="${secrecyCheckEvent.checkCircs != ''}">
									${secrecyCheckEvent.checkCircs}
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">整改情况：</td>
							<td colspan="3" height="100" valign="top">
							 	<c:if test="${secrecyCheckEvent.rectification == ''}">
									未填写
								</c:if>
								<c:if test="${secrecyCheckEvent.rectification!= ''}">
									${secrecyCheckEvent.rectification}
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" attachments="${attachmentList}" showTitle="false" />
							</td>
						</tr>
					</table>
					<div id="files_list">
					</div>
				</div>
			</div>
			<%--
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						检查列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<form id="form_secrecyCheckEvent_update" class="form" action="<s:url action="secrecyCheckEvent_update" includeParams="true"/>" method="post" target="main" enctype="multipart/form-data">
						<s:if test="#request.list.size>0">
							<ec:table items="list" var="checkEventTable" tableId="list" border="0"
								action="${ctx}/checkEventTable_list.action"
								imagePath="${ctx}/platform/theme/default/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<ec:row>
									<ec:column property="organ.organName" title="单位名称"/>
									<ec:column property="checkTable.tableName" title="检查表名"/>
									<ec:column property="checkTime" title="填表时间" format="yyyy-MM-dd" cell="date"/>
								</ec:row>
							</ec:table>
						</s:if>
						<s:else>
							<styles:nolist/>
						</s:else>
					</form>
				</div>
			</div>
			--%>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>