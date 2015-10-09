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
		<title>编辑【涉密会议（活动）】</title>

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
					if (needReload) {
						if (!confirm('新增成功，是否继续添加?')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('meeting_add',{
						display:{
							showErrors:1
						},
						trimValue: true
					});
				});
			});
			var needReload = ${needReload};
			var needReload2 = false;

			var massage = '${msg}';
			if(massage!=''){
				alert("请先添加会议分类。");
				TabUtil.closeTab();
			}
		    //下载
  			function download(hostId,attachId){
  				window.location.href="<s:url action="meeting_download"  includeParams="false"/>?meeting.meetingId="+hostId
				+"&attachment.attachId="+attachId;
  			}

  			//删除
			function del(attachId,hostId){
				window.location.href='<s:url action="meeting_deleteAttachment" includeParams="false"/>?meeting.meetingId='+hostId+"&attachment.attachId="+attachId;
			}

			//保存
			 function doSave(){
				 if (formcheck.isFormValid(true)) {
					    	$('sub').click();
					    	$('sbm_button').style.display='none';
							$('sbm_button_hidden').style.display='';
							window.setTimeout("$('sbm_button').style.display=''",8000);
							window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				 }
	    	}

			// 返回列表
			function backList(){
				window.location.href="meeting_list.action";
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:backList();" ><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<%-- <cp:start defaultTitle="新增会议记录简介" ctx="${ctx}" icoPath="${ctx}/meetingcategory/borderlayout/skin/blue/img/add_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','新增会议记录简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于编辑会议记录
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_meeting_add"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

				</cp:msg>
			<cp:end> </cp:end> --%>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑【${meeting.meetingCategory.categoryName}】会议
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
					<div class="panel_content panel_content_table">
					<form id="meeting_add" class="form"  action="<s:url namespace='/bmp/meeting' action='meeting_update' includeParams='true'/>"  method="post"  enctype="multipart/form-data">
						<table class="content_table">
							<tr>
								<td class="tbLable fr">
									涉密会议（活动）名称：
								</td>
								<td class="tbValue fl" colspan="5">
									<input name="meeting.meetingName" style="width: 95%" id="meeting.meetingName" type="text" value="${meeting.meetingName}" class="validate['required','length[128]']" size="19"><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr" style="width: 10%">
									开会日期：
								</td>
								<td class="tbValue fl"style="width: 20%">
									<input name="meetingTime" id="meetingTime" type="text" value="<fmt:formatDate value='${meeting.meetingTime}' pattern='yyyy-MM-dd'/>"
									 class="Wdate validate['required']" onfocus="WdatePicker({isShowClear:true,dateFmt:'yyyy-MM-dd'})">
									 <span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr" style="width: 10%">
									涉密等级：
								</td>
								<td class="tbValue fl" style="width: 20%">
								<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing"
									 id="meeting.secrecyLevel" optionValue="${meeting.secrecyLevel}"
									name="meeting.secrecyLevel" style="width: 132px;"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr" style="width: 10%">
									地点：
								</td>
								<td class="tbValue fl" style="width: 20%">
									<input name="meeting.place" id="meeting.place"  value="${meeting.place }" type="text"  class="validate['required','length[128]']" >
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr style="height: 70px">
								<td class="tbLable fr">
									出席人员：
								</td>
								<td class="tbValue fl" colspan="5">
									 <ui:multySelect textEl="readPersonNames" required="true"  value="${userInfos}"
									 valueProperty="userInfoId" textProperty="name"   valueEl="meeting.attendUserInfos"
									   buttonEl="readPerson" ></ui:multySelect>
								 </td>
							</tr>
							<tr>
								<td class="tbLable fr">
									会议知悉范围：
								</td>
								<td class="tbValue fl" colspan="5">
								    <textarea  style="width:99%;height:100px" class="validate['length[4000]']" name="meeting.scope">${meeting.scope}</textarea>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									保密办参与情况：
								</td>
								<td class="tbValue fl" colspan="5">
								    <textarea  style="width:99%;height:100px" class="validate['length[4000]']" name="meeting.situation">${meeting.situation}</textarea>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主要管理措施：
								</td>
								<td class="tbValue fl" colspan="5">
								    <textarea  style="width:99%;height:100px" class="validate['length[4000]']" name="meeting.measure">${meeting.measure}</textarea>
								</td>
							</tr>
						</table>
						<!-- 按钮 -->
						<input type="hidden" name="meeting.meetingCategory.meetingCategoryId" id="meeting.meetingCategory.meetingCategoryId" value="${meeting.meetingCategory.meetingCategoryId}">
						<input type="hidden" name="meeting.meetingId" id="meeting.meetingId" value="${meeting.meetingId}">
						<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
					</form>
					<div>
					<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="meeting_add" applyName="secAttach" attachments="${attachmentList }" showTitle="false"/>
				 	</div>
				</div>
			</div>
		</div>
		<!-- 内容panel结束 -->
	</body>
</html>