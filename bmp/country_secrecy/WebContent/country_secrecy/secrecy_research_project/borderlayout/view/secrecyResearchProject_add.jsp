<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib prefix="section" uri="http://www.cdthgk.com/tags/keySection"%>
<%@ taglib prefix="part" uri="http://www.cdthgk.com/tags/keyPart"%>
<%@ taglib prefix="sp" uri="http://www.cdthgk.com/tags/secrecyPerson/search"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增涉密科研项目</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
				$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
				$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
				$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

				$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
				$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
				$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
				$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
				$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
				$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
				$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
				$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('新增成功，是否继续？')){
							needReload2 = true;
							TabUtil.closeTab();
						}else{
							$("init_form").submit();
						}
					};
					formcheck = new FormCheck('add_form',{
						container: $('body_content'),
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			var needReload = ${needReload};
  			var needReload2 = false;

			// 提交方法
			function doSave(){
				if (formcheck.isFormValid(true)) {
					var begdate = $("secrecyResearchProject.secrecyLimitBeginDate").value;
					var enddate = $("secrecyResearchProject.secrecyLimitEndDate").value;
					if(begdate>enddate) {
						alert("保密期限起不能大于保密期限止");
						return;
					}


				 	$('sub').click();
		    		$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",2000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",2000);
				}
			}

			// 返回方法
			function doBackList(){
				TabUtil.closeTab();
			}

		</script>
	</head>

	<body onload="init()">
		<!-- 公共头部-->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBackList()"><span>返回列表</span></a>
				</div>
			</div>

			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:TabUtil.refreshTab();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 复合面板开始 -->
			<%-- <cp:start defaultTitle="涉密科研项目简介" ctx="${ctx}" icoPath="/country_secrecy/secrecy_research_project/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','涉密科研项目简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密科研项目
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_researchproject"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end> --%>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增涉密科研项目
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form action="${ctx}/bmp/secrecyResearchProject/secrecyResearchProject_adding.action" method="post" id="add_form" name="add_form">
						<table align="center" class="content_table st" width="100%">
							<tr>
								<td class="tbLable fr">涉密科研项目名称：</td>
								<td class="tbValue fl" colspan="3">
									<input id="secrecyResearchProject.secrecyResearchProjectName" name="secrecyResearchProject.secrecyResearchProjectName" type="text"
									class="validate['required','length[50]']" value="${secrecyResearchProject.secrecyResearchProjectName }"
									style="width: 75%"
									/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">定密负责人：</td>
								<td class="tbValue fl" colspan="3">
									<sp:selectSecrecyPerson required="true" onlyFromValue="false" styleClass="validate['length[50]']" buttonEl="secrecyPersonName"
									 valueEl="secrecyResearchProject.formulateSecrecyPerson.userInfoId"
									 textEl="secrecyResearchProject.formulateSecrecyPerson.name"  ></sp:selectSecrecyPerson>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">密 级：</td>
								<td class="tbValue fl" >
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyResearchProject.secrecyLevel }" style="width:134px;"
									id="secrecyResearchProject.secrecyLevel" name="secrecyResearchProject.secrecyLevel"></dictionary:select>
								</td>
								<td class="tbLable fr">部门名称：</td>
								<td class="tbValue fl" >
									<dep:selectByOrgan valueEl="secrecyResearchProject.departId.departmentId" textEl="departmentName"
									 onlyFromValue="false" text="${secrecyResearchProject.departId.departmentName}" required="true"
									 value="${secrecyResearchProject.departId.departmentId}" buttonEl="btnDepartment"></dep:selectByOrgan>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">保密期限：</td>
								<td class="tbValue fl">
									<input id="secrecyResearchProject.secrecyLimit" name="secrecyResearchProject.secrecyLimit" type="text"
									class="validate['length[20]','digit']" value="${secrecyResearchProject.secrecyLimit }" style="width:130px;"/>
								</td>
								<td class="tbLable fr">期限单位：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="limit_type" optionValue="${secrecyResearchProject.limitType }" style="width:134px;"
									id="secrecyResearchProject.limitType" name="secrecyResearchProject.limitType" ></dictionary:select>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">保密期限起：</td>
								<td class="tbValue fl" >
									<input type="text" name="secrecyResearchProject.secrecyLimitBeginDate" style="width:130px;"
									value="<s:date format="yyyy-MM-dd" name="secrecyResearchProject.secrecyLimitBeginDate"/>"
									class="Wdate validate['required']" id="secrecyResearchProject.secrecyLimitBeginDate"
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">保密期限止：</td>
								<td class="tbValue fl" >
									<input type="text" name="secrecyResearchProject.secrecyLimitEndDate"  style="width:130px;"
									value="<s:date format="yyyy-MM-dd" name="secrecyResearchProject.secrecyLimitEndDate"/>"
									class="Wdate validate['required']" id="secrecyResearchProject.secrecyLimitEndDate"
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">项目负责人：</td>
								<td class="tbValue fl">
								   <ui:selectByOrgan textEl="secrecyResearchProject.projectPerson.name" valueEl="secrecyResearchProject.projectPerson.userInfoId" onlyFromValue="false"
								       text="${secrecyResearchProject.projectPerson.name}" value="${secrecyResearchProject.projectPerson.userInfoId}" buttonEl="userInfo_Name"
								       styleClass="validate['required','length[50]']"  />
								</td>
								<td class="tbLable fr">项目状态：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="project_state" optionValue="${secrecyResearchProject.projectState}" style="width:134px;"
									id="secrecyResearchProject.projectState" name="secrecyResearchProject.projectState"  ></dictionary:select>
								</td>
							</tr>

							<tr>
								<td align="right">备 注：</td>
								<td align="left" colspan="3">
									<textarea  class="validate['length[2000]']" id="secrecyResearchProject.content" name="secrecyResearchProject.content" style="width:75%;height:150px;"></textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
							<input type="hidden" id="secrecyResearchProject.secrecyStatus" name="secrecyResearchProject.secrecyStatus" value="0" />
						</div>
					</form>

					<form action="${ctx}/bmp/secrecyResearchProject/secrecyResearchProject_add.action" method="post" id="init_form" name="init_form">
					</form>
				</div>
			</div>
			<!-- 内容panel结束-->
		</div>
	</body>
</html>