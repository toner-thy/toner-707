<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib tagdir="/WEB-INF/tags/page" prefix="p"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>会议资料详细信息</title>

		<!-- css -->
		<link href="${ctx}/platform/theme/default/formcheck/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/css/attachment.css" rel="stylesheet" />
		
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
		<script type="text/javascript" src="${ctx}/FCKeditor/fckeditor.js"></script>
		<script type="text/javascript" src="${ctx}/platform/theme/public_js/attachment.js"></script>
		<script src="${ctx}/resources/js/SimpleUI/SimpleUI.js" type="text/javascript"></script>
		
		<script type="text/javascript"> 
			window.addEvent('domready', function(){				
				new FormCheck('attendMeeting_edit',{
					display:{
						showErrors:1
						
					}
				}); 
			});
			
			//打开会议记录信息
			function openAttendMeeting(){
				window.showModalDialog("<s:url action="meeting_openDialog" includeParams="false"/>"
			 		,{window:window,
				 		text:"attendMeeting.meeting.meetingName",
				 		hidden:"attendMeeting.meeting.meetingId2"
			 		},"dialogWidth=800px;dialogHeight=600px;status=no;help=no");
			}
			
			//下载
  			function download(attendMeetingId,attachId){
  				window.location.href="<s:url action="am_download"  includeParams="false"/>?attendMeeting.attendMeetingId="+attendMeetingId+"&&attachment.attachId="+attachId;
  			    // window.dialogArguments.open("<s:url action="am_download" includeParams="false"/>?attending.attendMeetingId="+attendMeeingId+"&attachment.attachId="+attachId);
  			}
  			
  			//删除
			function del(attachmentId,id){
				window.location.href='<s:url action="am_deleteAttachment" includeParams="false"/>?attendMeeting.attendMeetingId='+id+"&attachment.attachId="+attachmentId;
			}
			
			//返回
			function doBack(meetingId){
				window.location.href="attendMeeting_list.action?attendMeeting.meeting.meetingId="+meetingId+"&myListFlag=${myListFlag}";
			}
		</script>
	</head>
	<body>
		<!-- 公共头部 -->
		<div class="but_bar no_print">
			<div class="left">
				<div class="pop_button_bar no_print">
				</div>
			</div>
			<div class="right no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content" style="overflow-x: auto;">
			<!-- 内容panel开始 -->
			<div class="panel">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						会议资料详细信息
					</div>
					<div class="panel_btn_bar pop_button_bar no_print">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<s:form id="attendMeeting_edit" class="form" action="attendMeeting_update" method="post" theme="simple"  enctype="multipart/form-data">
						<table align="center" width="800px;" class="content_table" style="word-break:break-word;word-wrap:break-word;">
							<tr>
								<td align="right" width="20%">资料名称：</td>
								<td align="left" width="30%">
									<div style="margin-left: 8px">${attendMeeting.attendMeetingName}</div>
								</td>
								<td align="right"  width="20%"> 所属会议： </td>
								<td align="left" width="30%">
									<div style="margin-left: 8px">${attendMeeting.meeting.meetingName}</div>
								</td>
							</tr>
							
							<tr>
								<td align="right">资料内容信息：</td>
								<td colspan="3" valign="top" height="100">
									${attendMeeting.attendMeetingContent}
								</td>
							</tr>
						<!-- 附件 -->
							<!-- 
							<tr>
								<td colspan="3">
									<div class="fjdiv">
										<a href="javascript:void(1==1);" class="addfile"
											style="cursor: default;" hidefocus="true"> <input
												id="my_file_element" class="addfile" type="file"
												name="file_1" size="1" title="点击选择附件"></a>
									</div>
								</td>
							</tr>
							 -->
							<tr>
								<td colspan="4">
									<c:if test="${attachmentList!=null}">
										<c:if test="${attachmentList!='[]'}">
											<div id="files_list2">
												<span id="ajaxAttachment">
													<c:forEach items="${attachmentList}" var="AList">
														<div id="rowdiv" style="width: 776px;">
															<span class='fjlist'> <img
																	src='${pageContext.request.contextPath}/platform/theme/default/images/fj.gif'/> &nbsp;
																${AList.attachName} &nbsp; 
	                        									<a onclick="download('${meeting.meetingId}','${AList.attachId}')" style="cursor: hand;color: blue">下载</a>
	                        								</span>
														</div>
													</c:forEach>
												</span>
											</div>
										</c:if>
									</c:if>
								</td>
							</tr>
						</table>
						<div id="files_list"></div>
							<input type="hidden" name="attendMeeting.attendMeetingId" value="${attendMeeting.attendMeetingId }"/>
							<div align="center">
						</div>
					</s:form>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>