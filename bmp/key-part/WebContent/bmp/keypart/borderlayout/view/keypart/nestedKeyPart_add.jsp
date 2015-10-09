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
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增保密要害部位</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />
		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<s:actionmessage theme="messages"/>

		<script type="text/javascript">
			var needReload = ${needReload};
			var needReload2 = false;

			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/public_js/utils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js", function(){
				$ENV.onDomReady(function(){
 					if (needReload) {
						if (!confirm('新增成功，是否继续添加？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('form_part_add',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			function doSave(){
				//判断 页面上所有必填项 都已经填写了 并且填写的格式是正确的
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",2000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",2000);
				}
			}

			function doBack(){
				TabUtil.closeTab();
			}

			// 返回方法
			function doBackList(){
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
					<a class="pop_button" href="javascript:doBackList();"><span>返回列表</span></a>
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
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密要害部门简介" ctx="${ctx}" icoPath="/bmp/key-section/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密要害部门简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密要害部门
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_key_department"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 保密要害部位panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部门【${department.departmentName}】 ─ 新增保密要害部位

					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_part_add" class="form" action="<s:url action='nestedpart_adding' namespace='/bmp/part' includeParams='true'/>" enctype="multipart/form-data" method="post">
						<input type="hidden" id="part.department.departmentId" name="part.department.departmentId" value="${department.departmentId}"/>

						<table class="content_table st" id="table_part_add" width="100%">
							<tr>
								<td class="tbLable fr">
									单位名称：
								</td>
								<td class="tbValue fl" colspan="3">
									${organ.organName}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									部位名称：
								</td>
								<td class="tbValue fl">
									<input type="text" name="part.partName" value="${part.partName}" class="textInput validate['required','length[32]']" /> <font color="red">*</font>
								</td>
								<td class="tbLable fr">
									主管部门：
								</td>
								<td class="tbValue fl">
									${department.departmentName}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主要负责人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="part.person.userInfoId" textEl="part.person.name" required="true" onlyFromValue="false" styleClass="validate['length[32]']"/>
								</td>
								<td class="tbLable fr">
									联系电话：
								</td>
								<td class="tbValue fl">
									<input type="text" name="part.phone" value="${part.phone}" class="textInput validate['length[20]']" /> 请填写部位联系电话
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									涉密等级：
								</td>
								<td class="tbValue fl"  colspan="3">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}" title="false" name="part.secrecyLevel" style="width:130px;"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									具体位置：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" name="part.location" value="${part.location}" class="textInput validate['length[50]']" style="width: 90%" />
								</td>
							</tr>
							<tr>
								<td class="fr">
									涉密工作事项及范围：
								</td>
								<td class="fl" colspan="3">
									<textarea style="width:95%; height: 100px;" name="part.secScope"></textarea><br/>
									请填写涉密工作事项概况，不需要详细罗列具体事项。
								</td>
							</tr>
							<tr>
								<td class="fr">
									技防措施：
								</td>
								<td class="fl" colspan="3">
									<input type="text" name="part.skillMeasure" value="${part.skillMeasure}" class="textInput validate['length[1000]']" style="width: 90%" />
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>
					</form>

					<table class="content_table st" id="table_part_add" width="100%">
						<tr>
							<td class="tbLable fr">
								保密制度：
							</td>
							<td class="fl" colspan="3">
								<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="form_part_add" applyName="keyPartAttachs" titleText="附件上传" limit="20"/>
							</td>
						</tr>
						<tr>
							<td class="fl" colspan="4">
								<b>填表提示：</b><br/>
								部位名称： 填写可以参考“机要室”、“保密室”、“档案室”、“文印室”、“机房”、“资料室”等。<br/>
								具体位置： 填写样式如“×××楼×××层×××号”。<br/>
								技防措施： 电子密码文件柜、铁门、铁窗、监控视频、红外报警、电子门禁等。<br/>
								附件上传包括：1.保密制度文件 2.本单位确定涉密人员文件  3.本单位确定要害部位文件
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 保密要害部位panel结束 -->
		</div>
	</body>
</html>