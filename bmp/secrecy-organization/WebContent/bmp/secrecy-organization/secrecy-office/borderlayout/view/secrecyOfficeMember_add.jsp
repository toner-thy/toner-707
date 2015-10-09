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
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增保密办（保密局）成员</title>

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
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			$ENV.loader.loadJavaScript("${ctx}/resources/js/DynamicTextarea/DynamicTextarea.js",function(){
				$ENV.onDomReady(function(){
					$('secrecyOfficeMember.resume').setStyle('width', $('secrecyOfficeMember.resume').getParent().getSize().x * 0.89);
			        new DynamicTextarea('secrecyOfficeMember.resume', {
			            minRows: 10
			        });
				});
			});

			function doBack2(){
				window.location.href = "${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_edit.action?secrecyOffice.secrecyOfficeId=${secrecyOffice.secrecyOfficeId}&district.districtCode=${district.districtCode}";
			}

			function doSave(){
				if (formcheck.isFormValid(true)) {
				//将以前的政治面貌保存到人员表中
				$('secrecyOfficeMember.person.polity').value = $('secrecyOfficeMember.politicalType').value;

				//if (confirm('请仔细核对输入保密办成员的信息,确认无误请点击【确定】,如需修改请点击【取消】!')){
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				//}
				}
			}

			function getUserInfo(elements, value){
				new Request.JSON({
 					url: '${ctx}/secrecyorganization/secrecyofficemember/secrecyOfficeMember_catchUserInfo.action',
 				    onComplete: function(result, text){
 				    	$('secrecyOfficeMember.person.duty').value = result.duty;
 				    	$('secrecyOfficeMember.person.sex').value = result.sex;
 				    	$('secrecyOfficeMember.person.nation').value = result.nation;
 				    	$('secrecyOfficeMember.person.birthDate').value = result.birthDate;
 				    	$('secrecyOfficeMember.person.learningLevel').value = result.learningLevel;
 				    	$('secrecyOfficeMember.person.identityCard').value = result.identityCard;
 				    	$('secrecyOfficeMember.person.specialtyCode').value = result.specialtyCode;
 				    	$('secrecyOfficeMember.person.polity').value = result.polity;
 				    	$('secrecyOfficeMember.person.administrativeLevel').value = result.administrativeLevel;
 				    	$('secrecyOfficeMember.person.technicTitleLevel').value = result.technicTitleLevel;
 				    	$('secrecyOfficeMember.person.department.departmentId').value = result.departmentId;
 				    	$('secrecyOfficeMember.person.department.departmentName').value = result.departmentName;
 				    	$('secrecyOfficeMember.person.postType').value = result.postType;
 				    	$('secrecyOfficeMember.person.staff').value = result.staff;
 				    	$('secrecyOfficeMember.person.mobile').value = result.mobile;
 				    }
 				}).get({
 					'userInfoId ' : value.value,
 				    '_ts': new Date().getTime()
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
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack2();"><span>返回列表</span></a>
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
			<%--
			<cp:start defaultTitle="保密办（保密局）简介" ctx="${ctx}" icoPath="/bmp/secrecy-organization/secrecy-office/borderlayout/skin/blue/img/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密办（保密局）简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密办（保密局）
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ_secrecy_office"> </cpc:tc>
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
			 --%>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增保密办（保密局）成员
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecyofficemember' action='secrecyOfficeMember_adding.action' includeParams='true'/>" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyOffice.secrecyOfficeId" value="${secrecyOffice.secrecyOfficeId}"/>
						<input type="hidden" name="district.districtCode" value="${district.districtCode}"/>

						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">单位名称：</td>
								<td class="tbValue fl" colspan="3">
									<b>${secrecyOffice.name}</b>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">姓 名：</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyOfficeMember.person.userInfoId" textEl="secrecyOfficeMember.person.name" required="true" onlyFromValue="false" value="${secrecyOfficeMember.person.userInfoId }" text="${secrecyOfficeMember.person.name }" onSelect="getUserInfo"></ui:selectByOrgan>
								</td>
								<td class="tbLable fr">性 别：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="sex" id="secrecyOfficeMember.person.sex" name="secrecyOfficeMember.person.sex" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyOfficeMember.person.sex}"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">民 族：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="nation" id="secrecyOfficeMember.person.nation" name="secrecyOfficeMember.person.nation" style="width: 130px;" optionValue="${secrecyOfficeMember.person.nation}"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('nation_helpId')" onmouseout="noneOne('nation_helpId')"  />
									<div id="nation_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有民族信息或者列表中没有该民族选项，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">出生年月：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyOfficeMember.person.birthDate" name="secrecyOfficeMember.person.birthDate" class="Wdate validate['required']" value="<s:date name='secrecyOfficeMember.person.birthDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">学 历：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="learning_level" id="secrecyOfficeMember.person.learningLevel" name="secrecyOfficeMember.person.learningLevel" title="true" titleText="本科" titleTextValue="7" style="width: 130px;" optionValue="${secrecyOfficeMember.person.learningLevel}"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('learningLevel_helpId')" onmouseout="noneOne('learningLevel_helpId')"  />
									<div id="learningLevel_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有学历信息，请选择“无”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">身份证号：</td>
								<td class="tbValue fl">
									<input name="secrecyOfficeMember.person.identityCard" id="secrecyOfficeMember.person.identityCard" type="text" class="validate['length[18]','idCard']" value="${secrecyOfficeMember.person.identityCard}"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">专 业：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="specialty_code" id="secrecyOfficeMember.person.specialtyCode" name="secrecyOfficeMember.person.specialtyCode" title="true" titleText="其他" titleTextValue="99"  style="width:130px;"></dictionary:select>
								</td>
								<td class="tbLable fr">政治面貌：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="polity" id="secrecyOfficeMember.politicalType" name="secrecyOfficeMember.politicalType" style="width: 130px;"></dictionary:select>
									<input type="hidden" id="secrecyOfficeMember.person.polity" value="secrecyOfficeMember.person.polity"/>
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
								<td class="tbLable fr">行政级别：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="person_admin_level" id="secrecyOfficeMember.person.administrativeLevel" name="secrecyOfficeMember.person.administrativeLevel" title="true" titleText="其他" titleTextValue="99" style="width: 130px;"></dictionary:select>
								</td>
								<td class="tbLable fr">参加工作时间：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyOfficeMember.firstWorkDate" name="secrecyOfficeMember.firstWorkDate"  class="Wdate" value="<s:date name='#attr.secrecyOfficeMember.firstWorkDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">技术职称：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="technic_title_level" id="secrecyOfficeMember.person.technicTitleLevel" name="secrecyOfficeMember.person.technicTitleLevel" style="width:130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('technicTitleLevel_helpId')" onmouseout="noneOne('technicTitleLevel_helpId')"  />
									<div id="technicTitleLevel_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有技术职称信息，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">是否专职：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="sole_duty" id="secrecyOfficeMember.isSoleDuty" name="secrecyOfficeMember.isSoleDuty" style="width:130px;" title="true" titleText="否" titleTextValue="0"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">其他职务：</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyOfficeMember.person.duty" id="secrecyOfficeMember.person.duty" />
								</td>
								<td class="tbLable fr">何时从事保密工作：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyOfficeMember.secrecyWorkStart" name="secrecyOfficeMember.secrecyWorkStart" class="Wdate validate['length[20]']" value="<s:date name='secrecyOfficeMember.secrecyWorkStart' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								 	<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">部 门：</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="secrecyOfficeMember.person.department.departmentId" textEl="secrecyOfficeMember.person.department.departmentName" required="true" onlyFromValue="false" value="${secrecyOfficeMember.person.department.departmentId}" text="${secrecyOfficeMember.person.department.departmentName}"></dep:selectByOrgan>
								</td>
								<td class="tbLable fr">岗 位：</td>
								<td class="tbValue fl">
									<input id="secrecyOfficeMember.post" name="secrecyOfficeMember.post" type="text" value="${secrecyOfficeMember.post}"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">涉密等级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_person" id="secrecyOfficeMember.secrecyPersonLevel" name="secrecyOfficeMember.secrecyPersonLevel" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyOfficeMember.secrecyPersonLevel}"></dictionary:select>
								</td>
								<td class="tbLable fr">岗位分组：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="post_type" id="secrecyOfficeMember.person.postType" name="secrecyOfficeMember.person.postType" title="true" titleText="其他人员" titleTextValue="4" style="width: 130px;" optionValue="${secrecyOfficeMember.person.postType}"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">编 制：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="staff" id="secrecyOfficeMember.person.staff" name="secrecyOfficeMember.person.staff" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyOfficeMember.person.staff}"></dictionary:select>
								</td>
								<td class="tbLable fr">办公室电话：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyOfficeMember.officePhone" name="secrecyOfficeMember.officePhone" value="${secrecyOfficeMember.officePhone }" class="validate['length[50]','phone']"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">手 机：</td>
								<td>
									<input id="secrecyOfficeMember.person.mobile" name="secrecyOfficeMember.person.mobile" type="text"  class="validate['length[50]','phone']" value="${secrecyOfficeMember.person.mobile}"/>
								</td>
								<td class="tbLable fr">人员排序：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyOfficeMember.sort" name="secrecyOfficeMember.sort" value="1" class="validate['required','digit','length[11]']"/>&nbsp;&nbsp;<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">个人简历：</td>
								<td class="tbLable fl" colspan="3">
									<textarea id="secrecyOfficeMember.resume" name="secrecyOfficeMember.resume" style="padding: 5px;" class="validate['length[2000]']">${secrecyOfficeMember.resume}</textarea>
									提示：填写的内容包含参加工作时间、工作性质、个人经历等内容。
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									备 注：
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea id="secrecyOfficeMember.remark" name="secrecyOfficeMember.remark" rows="" cols="" style="width: 90%;height: 100px;" class="validate['length[1000]']">${secrecyOfficeMember.remark}</textarea>
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