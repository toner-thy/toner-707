<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增保密委成员</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">


		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					var needReload = ${needReload};
					var needReload2 = false;
					if (needReload) {
						if (!confirm('新增成功，是否继续添加？')){
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

			function doSave(){
				if (formcheck.isFormValid(true)) {
				//if (confirm('请仔细核对输入保密委成员信息，确认无误请点击【确定】,如需修改请点击【取消】!')){
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				//}
				}
			}

			// 返回方法
			function doBackList(){
				window.location.href = "${ctx}/secrecyorganization/secrecycommittee/secrecyCommittee_edit.action?secrecyCommittee.secrecyCommitteeId=${secrecyCommittee.secrecyCommitteeId}";
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
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
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

		<div id="body_content" class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密委员会简介" ctx="${ctx}" icoPath="/bmp/secrecy-organization/secrecy-committee/borderlayout/skin/blue/img/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密委员会简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密委员会
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ_secrecy_committee"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

					<!-- 联系方式 -->
					<!-- <div class="cpMsgContactInfoTitle">
						业务指导
					</div>
					<div class="cpMsgContactInfoContext">
						联系人：四川省国家保密局督察处 XXX 联系电话：028-85229437
					</div> -->
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增保密委成员
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecycommitteemember' action='secrecyCommitteeMember_adding.action' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyCommittee.secrecyCommitteeId" value="${secrecyCommittee.secrecyCommitteeId}"/>

						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									保密委名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<b>${secrecyCommittee.name}</b>
								</td>
							<tr/>
							<tr>
								<td class="tbLable fr">
									保密委员会职务：
								</td>
								<td class="tbValue fl">
									<select style="width:130px;" name="secrecyCommitteeMember.personGroupPosition.personGroupPosition">
										<c:forEach items="${personGroupPositionList}" var="pgp">
											<option value="${pgp.personGroupPosition}">${pgp.positionName}</option>
										</c:forEach>
									</select>
								</td>
								<td class="tbLable fr">
									姓 名：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyCommitteeMember.person" id="secrecyCommitteeMember.person" value="${secrecyCommitteeMember.person }" class="validate['required','length[39]']">
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">性别：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="sex" id="secrecyCommitteeMember.sex" name="secrecyCommitteeMember.sex" style="width:130px;"></dictionary:select>
								</td>
								<td class="tbLable fr">
									行政职务：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyCommitteeMember.adminPost" name="secrecyCommitteeMember.adminPost" value="${secrecyCommitteeMember.adminPost}" class="validate['required','length[30]']" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">民族：</td>
								<td class="tbValue fl">
									<!-- demo 以后还需要在民族的字典表里添加‘其他’ -->
									<dictionary:select tableCode="person" fieldCode="nation" id="secrecyCommitteeMember.nation" name="secrecyCommitteeMember.nation" style="width:130px;" title="true" titleText="汉族" titleTextValue="1"/>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('nation_helpId')" onmouseout="noneOne('nation_helpId')"  />
									<div id="nation_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有民族信息或者列表中没有该民族选项，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">学历：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="learning_level" id="secrecyCommitteeMember.educationBackground" name="secrecyCommitteeMember.educationBackground" title="true" titleText="本科" titleTextValue="7" style="width:130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('learningLevel_helpId')" onmouseout="noneOne('learningLevel_helpId')"  />
									<div id="learningLevel_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有学历信息，请选择“无”
										<br/>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">学位：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="degree" id="secrecyCommitteeMember.degree" name="secrecyCommitteeMember.degree" title="true" titleText="学士" titleTextValue="2" style="width:130px;"></dictionary:select>
								</td>
								<td class="tbLable fr">政治面貌：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="polity" id="secrecyCommitteeMember.politicalStatus" name="secrecyCommitteeMember.politicalStatus" title="true" titleText="中共党员" titleTextValue="5" style="width:130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('polity_helpId')" onmouseout="noneOne('polity_helpId')"  />
									<div id="polity_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有政治面貌信息，请选择“其他”
										<br/>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">专业：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="specialty_code" id="secrecyCommitteeMember.major" name="secrecyCommitteeMember.major" title="true" titleText="其他" titleTextValue="99" style="width:130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('specialtyCode_helpId')" onmouseout="noneOne('specialtyCode_helpId')"  />
									<div id="specialtyCode_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有专业信息，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">出生年月：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyCommitteeMember.birthdate" name="secrecyCommitteeMember.birthdate" class="Wdate validate['required','length[20]']" value="<s:date name='#attr.secrecyCommitteeMember.birthdate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								 	<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									技术职称：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="technic_title_level" id="secrecyCommitteeMember.technicalTitle" name="secrecyCommitteeMember.technicalTitle" title="true" titleText="其他" titleTextValue="4" style="width:130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('technicTitleLevel_helpId')" onmouseout="noneOne('technicTitleLevel_helpId')"  />
									<div id="technicTitleLevel_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有技术职称信息，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">行政级别：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="person_admin_level" id="secrecyCommitteeMember.administrativeLevel" name="secrecyCommitteeMember.administrativeLevel" title="true" titleText="其他" titleTextValue="99" style="width:130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('administrativeLevel_helpId')" onmouseout="noneOne('administrativeLevel_helpId')"  />
									<div id="administrativeLevel_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有行政级别信息，请选择“其他”
										<br/>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									所属单位：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyCommitteeMember.organName" id="secrecyCommitteeMember.organName" value="${secrecyCommitteeMember.organName }" class="validate['required','length[50]']">
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									办公室电话：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyCommitteeMember.personPhone" value="${secrecyCommitteeMember.personPhone}" class="validate['length[20]','phone']"/>
								</td>
							</tr>
							<tr>
								<!--
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyCommitteeMember.departmentName" name="secrecyCommitteeMember.departmentName" class="validate['required','length[100]']"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								 -->
								<td class="tbLable fr">
									何时从事保密工作：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyCommitteeMember.secrecyWorkStart" name="secrecyCommitteeMember.secrecyWorkStart" class="Wdate validate['required','length[20]']" value="<s:date name='#attr.secrecyCommitteeMember.secrecyWorkStart' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								 	<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									是否专职：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="sole_duty" id="secrecyCommitteeMember.isSoleDuty" name="secrecyCommitteeMember.isSoleDuty" style="width:130px;" title="true" titleText="否" titleTextValue="0"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									人员排序：
								</td>
								<td class="tbValue fl" >
									<input type="text" name="secrecyCommitteeMember.sort" value="1" class="validate['required','digit','length[11]']"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td class="tbLable fr">兼任其他职务情况：</td>
								<td class="tbValue fl" colspan="3">
									<textarea id="secrecyCommitteeMember.otherDuty" name="secrecyCommitteeMember.otherDuty" style="width: 90%;height: 100px;" class="validate['length[1000]']"></textarea>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									备 注：
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea id="secrecyCommitteeMember.remark" name="secrecyCommitteeMember.remark" rows="" cols="" style="width: 90%;height: 150px;" class="validate['length[1000]']">${secrecyCommitteeMember.remark}</textarea>
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