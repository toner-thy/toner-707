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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	    <title>会议详细信息</title>

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
		  	function download(hostId,attachId){
		 	   window.location.href="<s:url action="meeting_download"  includeParams="false"/>?meeting.meetingId="+hostId
				+"&attachment.attachId="+attachId;
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
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						会议详细信息
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<c:if test="${meeting !=null}">
						<table class="content_table"  style="width: 100%">
							<tr>
								<td align="right" width="18%">
									涉密会议（活动）名称：
								</td>
								<td width="40%">
									${meeting.meetingName }<br></td>
								<td align="right" width="20%">
									开会日期：
								</td>
								<td width="22%">&nbsp;&nbsp;<fmt:formatDate value='${meeting.meetingTime}' pattern='yyyy-MM-dd HH:mm'/><br></td>
							</tr>

							<tr>
								<td align="right">
									涉密等级：
								</td>
								<td>
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${meeting.secrecyLevel}"></dictionary:text>
								</td>
								<td align="right">
									地点：
								</td>
								<td>
									${meeting.place}
								</td>
							</tr>

							<tr>
								<td align="right">
									出席人员：
								</td>
								<td align="left" colspan="4" valign="top" height="100">
									<div style="word-wrap:break-word;overflow: hidden;width:99%;">
										${meeting.attendUserInfoNames}
									</div>
								</td>
							</tr>
							<tr>
								<td align="right">
									会议知悉范围：
								</td>
								<td align="left" colspan="4" valign="top" height="100">
									<div style="word-wrap:break-word;overflow: hidden;width:99%;">
										${meeting.scope}
									</div>
								</td>
							</tr>
							<tr>
								<td align="right">
									保密办参与情况：
								</td>
								<td align="left" colspan="4" valign="top" height="100">
									<div style="word-wrap:break-word;overflow: hidden;width:99%;">
										${meeting.situation}
									</div>
								</td>
							</tr>
							<tr>
								<td align="right">
									主要管理措施：
								</td>
								<td align="left" colspan="4" valign="top" height="100">
									<div style="word-wrap:break-word;overflow: hidden;width:99%;">
										${meeting.measure}
									</div>
								</td>
							</tr>
							<!-- 附件 -->
							<tr>
								<td colspan="4">
									<div>
									    <attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" attachments="${attachmentList}" showTitle="false" />
				 					</div>
								</td>

							</tr>
						</table>
					</c:if>
					<c:if test="${meeting ==null}">
						<jsp:include page="/bmp/pages/public/noDate.jsp">
							<jsp:param name="showMsg" value="未找到该会议相关信息，请查会议类型是否被删除。"/>
						</jsp:include>
					</c:if>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
  	</body>
</html>