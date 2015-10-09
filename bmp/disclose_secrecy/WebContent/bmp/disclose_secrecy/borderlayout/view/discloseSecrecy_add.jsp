<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>泄密案件新增</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
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
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});


			var needReload = ${needReload};
			var needReload2 = false;

			function doSave(){
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);

				}
			 }

			function doBack(){
				TabUtil.closeTab();
			}

			// 跳转保密类别新增
			function addCategory(action) {
				TabUtil.openAsTab({
					url : action,
					title : '保密类别维护-新增'
				});
			}
			//显示层
			function disOne(id) {
				$(id).style.display="";
			}

			//消失层
			function noneOne(id){
				$(id).style.display="none";
			}

		</script>
</head>

<body>
	<!-- 公共头部 -->
	<div class="button_bar">
		<div class="left">
			<div class="pop_button_bar">
				<a class="pop_button" href="javascript:void();"
					id="sbm_button_hidden" style="display: none;"><span>保存中...</span></a>
				<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保
						存</span></a> <a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
			</div>
		</div>
		<div class="right">
			<div class="pop_button_bar">
				<a class="pop_button pop_button_refresh" href="#"
					onclick="javascript:window.refresh();"><span>刷新本页</span></a> <a
					class="pop_button pop_button_close" href="#"
					onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
			</div>
		</div>
	</div>

	<div id="body_content" class="body_content" style="width: 99%;">

	        <!-- 复合面板开始 -->
			<cp:start defaultTitle="泄密案件简介" ctx="${ctx}" icoPath="/bmp/disclose_secrecy/borderlayout/skin/blue/img/08.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','泄密案件简介');">关 于</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于泄密案件
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_discloseSecrecy"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

			</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->
		<!-- 内容panel开始 -->
		<div class="panel tMargin">
			<div class="panel_header">
				<div class="panel_title panel_titleListIco">泄密案件新增</div>
				<div class="panel_btn_bar pop_button_bar">
					<!-- 右侧按钮 -->
				</div>
			</div>
			<div class="panel_content panel_content_table">

				<form id="add_form"
					action="<s:url namespace='/bmp/discloseSecrecy' action='discloseSecrecy_adding.action' includeParams='true'/>"
					method="post" enctype="multipart/form-data">
					<table class="content_table">
						<tr>
							<td class="tbLable fr">名 称：</td>
							<td class="tbValue fl" colspan="3"><input type="text"
								id="discloseSecrecy.name" name="discloseSecrecy.name"
								style="width: 65%"
								class="validate['required','length[100]']"/> <span
								style="color: red;">*</span>
								<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('expid')" onmouseout="noneOne('expid')"  />
                                    <div id="expid"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
	                                                                              &ensp;&ensp; 1、 案件名称应包括：案件发生部门、姓名和发案形式。 <br/>
	                                                                              &ensp;&ensp; 2、 如：×× 部门 李×网络泄密案；<br/>
	                                                <br/>
                                    </div>
								</td>

						</tr>
						<tr>
							<td class="tbLable fr">案件性质：</td>
							<td class="tbValue fl"><dictionary:select tableCode="bmp"
									fieldCode="case_kind" id="discloseSecrecy.casekind"
									name="discloseSecrecy.casekind" style="width: 132px;"/></td>
							<td class="tbLable fr">发案形式：</td>
							<td class="tbValue fl"><dictionary:select tableCode="bmp"
							        filterValues="${filterValues}"
									fieldCode="case_Type" id="discloseSecrecy.caseType"
									name="discloseSecrecy.caseType" style="width: 200px;"/></td>
						</tr>
						<tr>
							<td class="tbLable fr">责任单位性质：</td>
							<td class="tbValue fl"><dictionary:select tableCode="bmp"
									fieldCode="duty_organ_kind"
									id="discloseSecrecy.dutyOrganKind"
									name="discloseSecrecy.dutyOrganKind" style="width: 200px;"/></td>
							<td class="tbLable fr">创建时间：</td>
							<td class="tbValue fl"><input type="text"
								id="secrecyPolicyCode.awardTime"
								name="secrecyPolicyCode.awardTime"
								class="Wdate validate['required','length[20]']"
								value="<s:date name='#attr.secrecyPolicyCode.awardTime' format='yyyy-MM-dd'/>"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								readonly="readonly" /> <font color="red">*</font>
								<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('time')" onmouseout="noneOne('time')"  />
                                    <div id="time"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
	                                                                               &ensp;&ensp; 1、选择当前时间。&ensp;&ensp;&ensp;
	                                                <br/>
                                    </div>

							</td>
						</tr>
						<tr>
							<td class="tbLable fr">密 级：</td>
							<td class="tbValue fl"><dictionary:select
							tableCode="bmp" fieldCode="secrecy_level_thing"
									 id="discloseSecrecy.secrecyLevel"
									name="discloseSecrecy.secrecyLevel" style="width: 132px;"/></td>
							<td class="tbLable fr">查处结果：</td>
							<td class="tbValue fl"><dictionary:select tableCode="bmp"
									fieldCode="find_result" id="discloseSecrecy.dealResult"
									name="discloseSecrecy.dealResult" style="width: 132px;"/>
									<font color="red">*</font>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('dealResult')" onmouseout="noneOne('dealResult')"  />
                                    <div id="dealResult"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
	                                                                               &ensp;&ensp;1、年度内结案的，选择“已查结” 。<br/>
                                                                                   &ensp;&ensp;2、年度内未结案的，选择“未查结”。<br/>

	                                                <br/>
                                    </div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">部门名称：</td>
							<td class="tbValue fl"><dep:selectByOrgan valueEl="discloseSecrecy.department.departmentId"
									textEl="discloseSecrecy.department.departmentName" required="true" onlyFromValue="true"
									styleClass="validate['length[32]']"
									 buttonEl="sss" />
									 <img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('dapartName')" onmouseout="noneOne('dapartName')"  />
                                    <div id="dapartName"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
			                                                           &ensp;&ensp;1、只能点击选择按钮选择对应的部门。<br/>

	                                                <br/>
                                    </div>
							</td>

						</tr>

					</table>

					<!-- 隐藏提交按钮 -->
					<div align="center">
						<input id="sub" value="sub" type="submit" style="display: none;" />
					</div>
				</form>
			</div>
		</div>
		<!-- 内容panel结束 -->
	</div>

</body>
</html>