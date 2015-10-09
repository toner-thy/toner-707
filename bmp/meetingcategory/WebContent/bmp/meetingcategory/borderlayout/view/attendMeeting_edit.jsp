<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑会议资料</title>

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

		<s:actionmessage theme="messages" />

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
  				window.location.href="<s:url action="am_download"  includeParams="false"/>?attendMeeting.attendMeetingId="+attendMeetingId
				+"&&attachment.attachId="+attachId;
  			}

  			//删除
			function del(attachmentId,id){
				window.location.href='<s:url action="am_deleteAttachment" includeParams="false"/>?attendMeeting.attendMeetingId='+id+"&attachment.attachId="+attachmentId;
			}

			//返回
			function doBack(id){
				window.location.href="attendMeeting_list.action?attendMeeting.meeting.meetingId="+id;
			}

			//验证3秒内不能重复提交
			 function doSave(){
	   			 $('sbm').click();
	   			 $('sbm_button').style.display='none';
	   			 $('sbm_button_hidden').style.display='';
	       		 window.setTimeout("$('sbm_button').style.display=''",3000);
	       		 window.setTimeout("$('sbm_button_hidden').style.display='none'",3000);
		    }
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="but_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack('${attendMeeting.meeting.meetingId }');" id="sbm_button"><span>返 回</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:doBack();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div class="body_content">
			<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleSearchIco">
						编辑征订信息
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_titleBtnBar">
					</div>
				</div>
				<!-- 内容 -->
				<div class="panel_content panel_content_table">
					<s:form id="attendMeeting_edit" class="form"  action="attendMeeting_update" method="post" theme="simple"  enctype="multipart/form-data">
						<input id="sbm" type="submit" style="display: none;">
						<table class="content_table">
							<tr height="36px;">
								<td align="right">所属会议：</td>
								<td colspan="4">
									&nbsp;&nbsp;${attendMeeting.meeting.meetingName}
								</td>
							</tr>

							<tr height="36px;">
								<td width="100px" align="right">资料名称：</td>
								<td>
									<input name="attendMeeting.attendMeetingName" id="attendMeeting.attendMeetingName" type="text" value="${attendMeeting.attendMeetingName}" class="validate['required','length[20]']"><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr height="36px;">
								<td align="right" valign="middle">资料内容信息：</td>
								<td height="200" colspan="4">
									<textarea name="attendMeeting.attendMeetingContent" rows="10" cols="50" style="width: 95%; height: 200px" class="validate['length[2000]']">${attendMeeting.attendMeetingContent}</textarea>
								</td>
							</tr>
						</table>

						<table class="content_table">
							<tr>
								<td colspan="5">
									<div class="fjdiv">
										<a href="javascript:void(1==1);" class="addfile"
											style="cursor: default;" hidefocus="true"> <input
												id="my_file_element" class="addfile" type="file"
												name="file_1" size="1" title="点击选择附件"></a>
									</div>
								</td>
							</tr>

							<tr>
								<td colspan="5">
									<c:if test="${attachmentList!=null}">
										<c:if test="${attachmentList!='[]'}">
											<span id="ajaxAttachment">
												<div id="files_list2">
													<c:forEach items="${attachmentList}" var="AList">
														<div id="rowdiv" style="width: 818px;">
															<span class='fjlist'>
																<img src='${pageContext.request.contextPath}/platform/theme/default/images/fj.gif'/> &nbsp; ${AList.attachName} &nbsp;
																<a href="javascript:download('${attendMeeting.attendMeetingId }','${AList.attachId}');">下载</a>
																<input type="button" id="${AList.attachId}" name="${attendMeeting.attendMeetingId}" class="deletefj" value="删除" onclick="del('${AList.attachId}','${attendMeeting.attendMeetingId }');"/>
															</span>
														</div>
													</c:forEach>
												</div>
											</span>
										</c:if>
									</c:if>
								</td>
							</tr>
						</table>
						<div id="files_list"></div>
							<input type="hidden" name="attendMeeting.meeting.meetingId" value="${attendMeeting.meeting.meetingId }"/>
							<input type="hidden" name="attendMeeting.attendMeetingId" value="${attendMeeting.attendMeetingId }"/>
						</div>
					</s:form>
				</div>
			</div>
		</div>
			<!-- 附件上传所需 -->
		<script>
			var multi_selector = new MultiSelector(document.getElementById('files_list'), 100);
			multi_selector.addElement(document.getElementById('my_file_element'));
		</script>
	</body>
</html>