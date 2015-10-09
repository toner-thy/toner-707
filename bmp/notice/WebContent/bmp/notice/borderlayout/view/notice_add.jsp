<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>公告管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mootools/mootools-core-1.4.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mootools/mootools-more-1.4.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/formcheck/1.4/formcheck.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/notimoo/notimoo-1.2.1.js" type="text/javascript"></script>
		<script src="${ctx}/platform/layout/borderlayout/js/TabUtils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js"></script>
		<script src="${ctx}/resources/js/SimpleUI/SimpleUI.js" type="text/javascript"></script>



		<script src="${ctx}/resources/FCKeditor/fckeditor.js" type="text/javascript"></script>

		<script type="text/javascript">
			var needReload = ${needReload};
			var needReload2 = false;
			window.addEvent('domready', function(){
				var sBasePath ='${ctx}/resources/FCKeditor/';
				var oFCKeditor = new FCKeditor('notice.noticeContent');
				oFCKeditor.BasePath	= sBasePath ;
				oFCKeditor.ReplaceTextarea() ;

				if (needReload) {
					if (!confirm('新增成功，是否继续添加?')){
						needReload2 = true;
						TabUtil.closeTab();
					}
				};

				formcheck = new FormCheck('add_form',{
					display:{
						showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
					},
					trimValue: true
				});
			});

			function doSave(){
				if (formcheck.isFormValid(true)) {
					// 判断内容字符不能超过4000字符
					var Editor = FCKeditorAPI.GetInstance('notice.noticeContent');
				 	var acontent = Editor.GetXHTML();
				 	if(acontent.length > 4000){
						alert("小提示，[正文]内容不能超过4000字符,您可以通过粘贴时清理CSS样式来减少冗余字符。");
					}else{
						$('sub').click();
						$('sbm_button').style.display='none';
						$('sbm_button_hidden').style.display='';
						window.setTimeout("$('sbm_button').style.display=''",8000);
						window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
					}
				 }
			}

			function doBack(){
				TabUtil.closeTab();
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
					<a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="#" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="#" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content" style="width:99%;">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增公告
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/notice' action='notice_adding' includeParams='true'/>" method="post">
						<table class="content_table">
							<tr>
								<td class="tbLable fr">
									标 题：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" id="notice.noticeName" name="notice.noticeName"  value="${notice.noticeName}" class="validate['required','length[100]'] w400" maxlength="100" /> <span style="color:red;">*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">发布人：</td>
								<td class="tbValue fl">
										<input type="text" id="notice.noticePublisher" name="notice.noticePublisher" value="${notice.noticePublisher }" />
								</td>
								<td class="tbLable fr">发布日期：</td>
								<td class="tbValue fl">
									<input type="text" id="title" name="notice.noticePublishDate" readonly="readonly" value="${notice.noticePublishDate}" onFocus="WdatePicker()" class="Wdate validate['required']" /> <span style="color:red;">*</span>
								</td>
							</tr>
							<tr>
								<td class="tbValue fl" colspan="4">
									正 文：
								</td>
							</tr>
							<tr>
								<td class="tbValue fl" colspan="4" height="330">
									<textarea id="notice.noticeContent" name="notice.noticeContent" >${notice.noticeContent }</textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

	</body>
</html>