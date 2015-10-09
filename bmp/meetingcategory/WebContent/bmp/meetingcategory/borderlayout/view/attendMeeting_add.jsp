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
		<title>新增会议资料</title>

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
				new FormCheck('attendMeeting_add',{
					display:{
						showErrors:1
					}
				});
			});

			//打开会议信息
			function openAttendMeeting(){
				window.showModalDialog("<s:url action="meeting_openDialog" includeParams="false"/>"
			 		,{window:window,
				 		text:"attendMeeting.meeting.meetingName2",
				 		hidden:"attendMeeting.meeting.meetingId2"
			 		},"dialogWidth=800px;dialogHeight=600px;status=no;help=no");
			}


			//验证3秒内不能重复提交
			 function doSave(){
	   			$('sbm').click();
	   			$('sbm_button').style.display='none';
				$('sbm_button_hidden').style.display='';
				window.setTimeout("$('sbm_button').style.display=''",8000);
				window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
		    }

			 function doReturn(id){
	   			 window.location.href="attendMeeting_list.action?attendMeeting.meeting.meetingId="+id;
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
					<a class="pop_button" href="javascript:doReturn('${attendMeeting.meeting.meetingId }');" id="sbm_button"><span>返 回</span></a>
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
						新增会议资料
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_titleBtnBar">
					</div>
				</div>
				<!-- 内容 -->
				<div class="panel_content panel_content_table">
					<s:form id="attendMeeting_add" class="form" action="attendMeeting_save" method="post" theme="simple"  enctype="multipart/form-data">
						<input id="sbm" type="submit" style="display: none;">
						<table class="content_table">
							<tr>
								<td align="right">资料名称：</td>
								<td colspan="4">
									<input name="attendMeeting.attendMeetingName" id="attendMeeting.attendMeetingName" type="text" value="" class="validate['required','length[20]']" size="40"><span style="color:red;">&nbsp;&nbsp;*</span>
									<input name="attendMeeting.meeting.meetingId" type="hidden" value="${attendMeeting.meeting.meetingId }" id="attendMeeting.meeting.meetingId2"/>
								</td>
							</tr>

							<tr>
								<td align="right" valign="middle">资料内容信息：</td>
								<td colspan="4" height="200">
									<textarea name="attendMeeting.attendMeetingContent" rows="10" cols="50" style="width: 95%; height: 200px" class="validate['length[2000]']"></textarea>
								</td>
							</tr>
						</table>

						<!-- 附件 -->
						<table class="content_table">
							<tr>
								<td colspan="5">
									<div class="fjdiv">
										<a href="javascript:void(1==1);" class="addfile" style="cursor: default;">
											<input id="my_file_element" class="addfile" type="file" name="file_1" size="1" title="点击选择附件" />
										</a>
									</div>
								</td>
							</tr>
						</table>

						<div id="files_list"></div>

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
