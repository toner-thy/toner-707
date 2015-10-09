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
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑单位保密办成员</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

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
					formcheck = new FormCheck('edit_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					$('secrecyWorkOrganMemberUnit.person.department.departmentName').addEvent('blur', doBlur);
				});
			});

			var popFlag = true;
			function doBlur(){
				var value = $('secrecyWorkOrganMemberUnit.person.department.departmentName').value;
				if('${secrecyWorkOrganMemberUnit.person.department.departmentName}' != value){
					if(popFlag){
						doSelectDept();
						popFlag = false;
					}
				}
			}

			function doSelectDept(){
				$ENV.dialog.open({
					title : '请选择',
					url : "${ctx}/bmp/secrecy-organization/secrecy-workorgan/borderlayout/view/secrecyWorkOrganRelationMember_deptConfirm.jsp?&_ts=" + $time(),
					width : window.top.getSize().x * 0.3,
					height : window.top.getSize().y * 0.4
				});
			}
			function doSave(){
				if(!popFlag){
					return;
				}
				if (formcheck.isFormValid(true)) {
				//if (confirm('请仔细核对输入单位保密办成员的信息,确认无误请点击【确定】,如需修改请点击【取消】!')){
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				//}
				}
			}

			// 返回方法
			function doBackList(id){
				window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_edit.action?secrecyWorkOrgan.secrecyWorkOrganId="+id;
			}

			//格式化时间
			//格式化时间
			function getLocalTime(ns){
				//格式化时间方法
				Date.prototype.format = function(format){
					var o = {
							"M+" : this.getMonth()+1,
							"d+" : this.getDate(),
							"h+" : this.getHours(),
							"m+" : this.getMinutes(),
							"s+" : this.getSeconds(),
							"q+" : Math.floor((this.getMonth()+3)/3),
							"S" : this.getMilliseconds()
					}

					if(/(y+)/.test(format)){
						format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4-RegExp.$1.length));
					}
					for( var k in o ){
						if( new RegExp("("+k+")").test(format) ){
							format = format.replace(RegExp.$1,
									(RegExp.$1.length == 1) ? (o[k]) :
										(("00"+o[k]).substr((""+o[k]).length)));
						}
					}
					return format;
				}

				if( ns!=null && ns!="" && ns !="null" ){
					var localstr = new Date(parseInt(ns));
					return localstr.format('yyyy-MM-dd');
				}else{
					return "";
				}
			}

			//ui控件  补全选中人员信息
			function setUserInfo(elements, value){
				var jsonRequest = new Request.JSON({
 					url: '${ctx}/secrecyorganization/secrecyWorkOrganRelationMember/secrecyWorkOrganRelationMember_fillUserInfo.action',
 				    onComplete: function(result, text){
 				    	if (result.userName != null) {
 								$('secrecyWorkOrganMemberUnit.person.department.departmentName').value = result.departmentName;
 								$('secrecyWorkOrganMemberUnit.person.department.departmentId').value = result.departmentId;
 								$('secrecyWorkOrganMemberUnit.person.duty').value = result.duty;
 								$('secrecyWorkOrganMemberUnit.person.sex').value = result.sex == null ? "1" : result.sex;
 								$('secrecyWorkOrganMemberUnit.person.birthDate').value = result.birthDate == null ? "" : result.birthDate;
 								$('secrecyWorkOrganMemberUnit.person.learningLevel').value = result.learningLevel == null ? "1" : result.learningLevel;
 								$('secrecyWorkOrganMemberUnit.person.polity').value = result.polity == null ? "" : result.polity;
 								$('secrecyWorkOrganMemberUnit.person.phone').value = result.phone == null ? "" : result.phone;
 								$('secrecyWorkOrganMemberUnit.person.technicTitleLevel').value = result.technicTitleLevel;
 								$('secrecyWorkOrganMemberUnit.person.specialtyCode').value = result.specialtyCode;
 								$('secrecyWorkOrganMemberUnit.person.administrativeLevel').value = result.administrativeLevel == null ? "" : result.administrativeLevel;
 				    	} else {
 				    		alert(result.message);
 				    	}
 				    }
 				}).get({
 					'userInfoId' : value.value,
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
					<a class="pop_button" href="javascript:doBackList('${secrecyWorkOrgan.secrecyWorkOrganId}');;"><span>返回列表</span></a>
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
			<cp:start defaultTitle="单位保密办成员简介" ctx="${ctx}" icoPath="/bmp/secrecy-organization/secrecy-workorgan/borderlayout/skin/blue/img/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','单位保密办成员简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于单位保密办成员
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_workOrgan_memberUnit"> </cpc:tc>
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
						编辑单位保密办成员
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="edit_form" action="<s:url namespace='/secrecyorganization/secrecyWorkOrganMemberUnit' action='secrecyWorkOrganMemberUnit_editing.action' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyWorkOrganMemberUnit.secrecyWorkUnitMemberId" value="${secrecyWorkOrganMemberUnit.secrecyWorkUnitMemberId}"/>
						<input type="hidden" name="secrecyWorkOrgan.secrecyWorkOrganId" value="${secrecyWorkOrgan.secrecyWorkOrganId}"/>

						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									姓 名：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyWorkOrganMemberUnit.person.userInfoId" textEl="secrecyWorkOrganMemberUnit.person.name" required="true" onlyFromValue="false" onSelect="setUserInfo" dataSelector="userInfoSelector" value="${secrecyWorkOrganMemberUnit.person.userInfoId }" text="${secrecyWorkOrganMemberUnit.person.name }" buttonEl="selectPersonBtn"></ui:selectByOrgan>
								</td>
								<td class="tbLable fr">
									部门：
								</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="secrecyWorkOrganMemberUnit.person.department.departmentId" textEl="secrecyWorkOrganMemberUnit.person.department.departmentName" required="true" onlyFromValue="false" value="${secrecyWorkOrganMemberUnit.person.department.departmentId}" text="${secrecyWorkOrganMemberUnit.person.department.departmentName}" buttonEl="departmentBtn"></dep:selectByOrgan>
									<span style="display: none" id="deptFlagShow"></span>
									<input type="hidden" id="deptFlag" name="deptFlag" value="">
									<input type="hidden" id="secrecyWorkOrganMemberUnit.oldDeptId" name="secrecyWorkOrganMemberUnit.oldDeptId" value="${secrecyWorkOrganMemberUnit.person.department.departmentId}">
								</td>
							<tr>
							<tr>
								<td class="tbLable fr">
									行政职务：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyWorkOrganMemberUnit.person.duty" name="secrecyWorkOrganMemberUnit.person.duty" value="${secrecyWorkOrganMemberUnit.person.duty}" class="validate['length[10]']"/>
								</td>
								<td class="tbLable fr">
									办公室电话：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyWorkOrganMemberUnit.person.phone" name="secrecyWorkOrganMemberUnit.person.phone" value="${secrecyWorkOrganMemberUnit.person.phone}" class="validate['phone','length[20]']"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">性别：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="sex" id="secrecyWorkOrganMemberUnit.person.sex" name="secrecyWorkOrganMemberUnit.person.sex" optionValue="${secrecyWorkOrganMemberUnit.person.sex }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
									<font style="color: red;">*</font>
								</td>
								<td class="tbLable fr">出生年月：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyWorkOrganMemberUnit.person.birthDate" name="secrecyWorkOrganMemberUnit.person.birthDate" class="Wdate validate['required','length[20]']" value="<s:date name='#attr.secrecyWorkOrganMemberUnit.person.birthDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
									 <font style="color: red;">*</font>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">学历：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="learning_level" id="secrecyWorkOrganMemberUnit.person.learningLevel" name="secrecyWorkOrganMemberUnit.person.learningLevel" optionValue="${secrecyWorkOrganMemberUnit.person.learningLevel }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
									<font style="color: red;">*</font>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('learningLevel_helpId')" onmouseout="noneOne('learningLevel_helpId')"  />
									<div id="learningLevel_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有学历信息，请选择“无”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">政治面貌：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="polity" id="secrecyWorkOrganMemberUnit.person.polity" name="secrecyWorkOrganMemberUnit.person.polity" optionValue="${secrecyWorkOrganMemberUnit.person.polity }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
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
								<td class="tbLable fr">专业</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="specialty_code" id="secrecyWorkOrganMemberUnit.person.specialtyCode" name="secrecyWorkOrganMemberUnit.person.specialtyCode" optionValue="${secrecyWorkOrganMemberUnit.person.specialtyCode }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('specialtyCode_helpId')" onmouseout="noneOne('specialtyCode_helpId')"  />
									<div id="specialtyCode_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有专业信息，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">行政级别</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="person_admin_level" id="secrecyWorkOrganMemberUnit.person.administrativeLevel" name="secrecyWorkOrganMemberUnit.person.administrativeLevel" optionValue="${secrecyWorkOrganMemberUnit.person.administrativeLevel }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
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
								<td class="tbLable fr">技术职称：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="technic_title_level" id="secrecyWorkOrganMemberUnit.person.technicTitleLevel" name="secrecyWorkOrganMemberUnit.person.technicTitleLevel" optionValue="${secrecyWorkOrganMemberUnit.person.technicTitleLevel }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
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
									<dictionary:select tableCode="bmp" fieldCode="sole_duty" id="secrecyWorkOrganMemberUnit.isSoleDuty" name="secrecyWorkOrganMemberUnit.isSoleDuty" optionValue="${secrecyWorkOrganMemberUnit.isSoleDuty }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">其他职务：</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyWorkOrganMemberUnit.otherDuty" id="secrecyWorkOrganMemberUnit.otherDuty" value="${secrecyWorkOrganMemberUnit.otherDuty }" />
								</td>
								<td class="tbLable fr">何时从事保密工作</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyWorkOrganMemberUnit.secrecyWorkStart" name="secrecyWorkOrganMemberUnit.secrecyWorkStart" class="Wdate validate['length[20]'] w135" value="<s:date name='#attr.secrecyWorkOrganMemberUnit.secrecyWorkStart' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								 	<font style="color: red;">*</font>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									人员排序：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyWorkOrganMemberUnit.sort" value="${secrecyWorkOrganMemberUnit.sort}" class="validate['required','digit','length[11]']"/>&nbsp;&nbsp;<font color="red">*</font>
								</td>
								<td class="tbLable fr">
									&nbsp;
								</td>
								<td class="tbValue fl">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									备 注：
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea id="secrecyWorkOrganMemberUnit.remark" name="secrecyWorkOrganMemberUnit.remark" rows="" cols="" style="width: 90%;height: 150px;" class="validate['length[1000]']">${secrecyWorkOrganMemberUnit.remark}</textarea>
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