<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>上传附件</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<s:actionmessage theme="messages"/>

		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");

			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js", function(){
				$ENV.onDomReady(function(){
					formcheck = new FormCheck('form_add',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					hObject=document.all.HWPostil1;
					//  TODO 隐藏菜单、按钮不起作用
					hObject.ShowDefMenu = 0; //显示菜单
					hObject.ShowToolBar = 0; //隐藏工具条
				});
			});
			var hObject;
		//返回
		function doBackList(){
			window.location.href="${ctx}/bmp/formatFile/formatFile_list.action";
		}

		//打开文件
		function doOpen(){
			hObject.LoadFile('');
			hObject.WaterMarkTextOrPath="";
		}

		//添加水印
		function addWater(hObject){
				hObject.Login("HWSEALDEMO**", 4, 65535, "DEMO", "");
				hObject.WaterMarkAlpha=50;
				hObject.WaterMarkAngle=-450;
				hObject.WaterMarkMode=1;// 1、文字5、图片
// 				hObject.WaterMarkMode=5;
				hObject.WaterMarkPosX=80;
				hObject.WaterMarkPosY=80;;
				hObject.WaterMarkTextColor=0;
				hObject.WaterMarkTextOrPath="中国石油集团川庆钻探工程有限公司";
// 			    hObject.WaterMarkTextOrPath="C:/Users/lx/Pictures/1.png";
				hObject.WaterMarkTxtHOrImgZoom=100;
		}

		//保存
		function doSave(){
			if (formcheck.isFormValid(true)) {
				//var hObject=document.all.HWPostil1;
				hObject.HttpInit(); //初始化HTTP引擎。
				addWater(hObject);
				var fileName = document.getElementById("formatFile.name").value;
 				hObject.HttpAddPostString("NAME",fileName); //设置上传文件名。
				hObject.HttpAddPostString("FTYPE","aip"); //设置上传文件类型。
				hObject.HttpAddPostCurrFile("FileBlod");//设置上传当前文件,文件标识为FileBlod。
				var url = "/formatFile_adding.action?formatFile.name="+fileName;
				if(hObject.HttpPost(url) != "failed: can't save the file data"){
					window.location.href="${ctx}/bmp/formatFile/formatFile_list.action?msg=1";
				} else {
					alert("不能保存空文件。")
				}
			}
		}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();"  id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBackList();"><span>返回列表</span></a>
				</div>
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
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						拟稿
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<form id="form_add" class="form" enctype="multipart/form-data" action="<s:url action="formatFile_adding" includeParams="true"/>" method="post">
					<div class="panel_content panel_content_table">
						<table class="content_table">
							<tr height="36px;">
								<td style="text-align:right;">名称:</td>
								<td>
									<input id="formatFile.name" name="formatFile.name" value="${formatFile.name}"  class="validate['required','length[100]']"
									style="width:400px;height: 32px;"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="button" value="打开文件" onclick="doOpen()">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div style="width:1000px;height:500px;">
										<object id="HWPostil1" height='100%' width='100%' style='LEFT: 0px; TOP: 0px'  classid='clsid:FF1FE7A0-0578-4FEE-A34E-FB21B277D561'></OBJECT>
									</div>
								</td>
							</tr>
						</table>
						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>

					</div>
				</form>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>