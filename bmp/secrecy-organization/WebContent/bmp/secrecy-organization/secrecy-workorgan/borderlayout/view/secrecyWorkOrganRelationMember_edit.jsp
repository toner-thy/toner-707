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
		<title>编辑单位保密工作机构成员</title>

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
					$('secrecyWorkOrganRelationMember.person.department.departmentName').addEvent('blur', doBlur);
				});
			});

			var popFlag = true;
			function doBlur(){
				var value = $('secrecyWorkOrganRelationMember.person.department.departmentName').value;
				if('${secrecyWorkOrganRelationMember.person.department.departmentName}' != value){
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
					if (confirm('请仔细核对输入单位保密工作机构成员的信息。\u000d确认无误请点击【确定】,如需修改请点击【取消】!')){
						$('sub').click();
						$('sbm_button').style.display='none';
						$('sbm_button_hidden').style.display='';
						window.setTimeout("$('sbm_button').style.display=''",8000);
						window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
					}
				}
			}

			// 返回方法
			function doBackList(id){
				window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_edit.action?secrecyWorkOrgan.secrecyWorkOrganId="+id;
			}

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
			//选人补全信息
			function setUserInfo(elements, value){
				var jsonRequest = new Request.JSON({
 					url: '${ctx}/secrecyorganization/secrecyWorkOrganRelationMember/secrecyWorkOrganRelationMember_fillUserInfo.action',
 				    onComplete: function(result, text){
 				    	if (result.userName != null) {
 								$('secrecyWorkOrganRelationMember.person.department.departmentName').value = result.departmentName;
 								$('secrecyWorkOrganRelationMember.person.department.departmentId').value = result.departmentId;
 								$('secrecyWorkOrganRelationMember.person.duty').value = result.duty;
 								$('secrecyWorkOrganRelationMember.person.sex').value = result.sex == null ? "1" : result.sex;
 								$('secrecyWorkOrganRelationMember.person.birthDate').value = result.birthDate == null ? "" : result.birthDate;
 								$('secrecyWorkOrganRelationMember.person.learningLevel').value = result.learningLevel == null ? "1" : result.learningLevel;
 								$('secrecyWorkOrganRelationMember.person.polity').value = result.polity == null ? "" : result.polity;
 								$('secrecyWorkOrganRelationMember.person.phone').value = result.phone == null ? "" : result.phone;
 								$('secrecyWorkOrganRelationMember.person.mobile').value = result.mobile == null ? "" : result.mobile;
 								$('secrecyWorkOrganRelationMember.person.technicTitleLevel').value = result.technicTitleLevel;
 								$('secrecyWorkOrganRelationMember.person.specialtyCode').value = result.specialtyCode;
 								$('secrecyWorkOrganRelationMember.person.administrativeLevel').value = result.administrativeLevel == null ? "" : result.administrativeLevel;
 								/* $('secrecyWorkOrganRelationMember.person.sex').value = "1";
 								$('secrecyWorkOrganRelationMember.person.birthDate').value ="";
 								$('secrecyWorkOrganRelationMember.person.learningLevel').value ="1";
 								$('secrecyWorkOrganRelationMember.person.mobile').value ="";
 								$('secrecyWorkOrganRelationMember.person.polity').value = "";
 								$('secrecyWorkOrganRelationMember.person.administrativeLevel').value=""; */
 				    	} else {
 				    		alert(result.message);
 				    	}
 				    }
 				}).get({
 					'userInfoId' : value.value,
 				    '_ts': new Date().getTime()
 				});
				/* $('secrecyWorkOrganRelationMember.person.department.departmentName').value=value.data["department.departmentName"];
				$('secrecyWorkOrganRelationMember.person.department.departmentId').value=value.data["department.departmentId"];
				$('secrecyWorkOrganRelationMember.person.duty').value=value.data["duty"];
				$('secrecyWorkOrganRelationMember.person.sex').value=value.data["sex"];
				$('secrecyWorkOrganRelationMember.person.birthDate').value=getLocalTime(value.data["birthDate"]);
				$('secrecyWorkOrganRelationMember.person.learningLevel').value=value.data["learningLevel"];
				$('secrecyWorkOrganRelationMember.person.polity').value=value.data["polity"];
				$('secrecyWorkOrganRelationMember.person.phone').value=value.data["phone"];
				$('secrecyWorkOrganRelationMember.person.mobile').value=value.data["mobile"];
				$('secrecyWorkOrganRelationMember.person.technicTitleLevel').value=value.data["technicTitleLevel"];
				$('secrecyWorkOrganRelationMember.person.specialtyCode').value=value.data["specialtyCode"];
				$('secrecyWorkOrganRelationMember.person.administrativeLevel').value=value.data["administrativeLevel"]; */
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
			<%--
			<cp:start defaultTitle="单位保密工作机构简介" ctx="${ctx}" icoPath="/bmp/secrecy-organization/secrecy-workorgan/borderlayout/skin/blue/img/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','单位保密工作机构简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于单位保密工作机构
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_workOrgan_relationMember"> </cpc:tc>
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
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑单位保密工作机构成员
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="edit_form" action="<s:url namespace='/secrecyorganization/secrecyWorkOrganRelationMember' action='secrecyWorkOrganRelationMember_editing.action' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyWorkOrganRelationMember.secrecyWorkOrganRelationId" value="${secrecyWorkOrganRelationMember.secrecyWorkOrganRelationId}"/>
						<input type="hidden" name="secrecyWorkOrgan.secrecyWorkOrganId" value="${secrecyWorkOrgan.secrecyWorkOrganId}"/>

						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									机构职务：
								</td>
								<td class="tbValue fl">
									<select class="w135" name="secrecyWorkOrganRelationMember.personGroupPosition.personGroupPosition">
										<c:forEach items="${personGroupPositionList}" var="pgp">
											<option value="${pgp.personGroupPosition}" <c:if test="${pgp.personGroupPosition==secrecyWorkOrganRelationMember.personGroupPosition.personGroupPosition}"> selected="selected"</c:if>>
												<c:choose>
													<c:when  test="${pgp.positionName eq '主任'}">保密办主任</c:when>
													<c:when  test="${pgp.positionName eq '副主任'}">保密办副主任</c:when>
													<c:when  test="${pgp.positionName eq '委员'}">工作人员</c:when>
													<c:otherwise>${pgp.positionName}</c:otherwise>
												</c:choose>
											</option>
										</c:forEach>
									</select>
								</td>
								<td class="tbLable fr">
									姓 名：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyWorkOrganRelationMember.person.userInfoId" textEl="secrecyWorkOrganRelationMember.person.name" required="true" onlyFromValue="false" value="${secrecyWorkOrganRelationMember.person.userInfoId }" text="${secrecyWorkOrganRelationMember.person.name }" onSelect="setUserInfo" dataSelector="userInfoSelector"></ui:selectByOrgan>
								</td>
							<tr>
							<tr>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="secrecyWorkOrganRelationMember.person.department.departmentId" textEl="secrecyWorkOrganRelationMember.person.department.departmentName" required="true" onlyFromValue="false" value="${secrecyWorkOrganRelationMember.person.department.departmentId}" text="${secrecyWorkOrganRelationMember.person.department.departmentName}"></dep:selectByOrgan>
									<span style="display: none" id="deptFlagShow"></span>
									<input type="hidden" id="deptFlag" name="deptFlag" value="">
									<input type="hidden" id="secrecyWorkOrganRelationMember.oldDeptId" name="secrecyWorkOrganRelationMember.oldDeptId" value="${secrecyWorkOrganRelationMember.person.department.departmentId}">
								</td>
								<td class="tbLable fr">
									人员排序：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyWorkOrganRelationMember.sort" value="${secrecyWorkOrganRelationMember.sort}" class="validate['required','digit','length[11]']" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">性别：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="sex" id="secrecyWorkOrganRelationMember.person.sex" name="secrecyWorkOrganRelationMember.person.sex" optionValue="${secrecyWorkOrganRelationMember.person.sex }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
								</td>
								<td class="tbLable fr">出生年月：</td>
								<td class="tbValue fl">
								<input type="text" id="secrecyWorkOrganRelationMember.person.birthDate" name="secrecyWorkOrganRelationMember.person.birthDate" class="Wdate validate['required','length[20]']" value="<s:date name='#attr.secrecyWorkOrganRelationMember.person.birthDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								 <font style="color: red;">&nbsp;&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">学历：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="learning_level" id="secrecyWorkOrganRelationMember.person.learningLevel" name="secrecyWorkOrganRelationMember.person.learningLevel" optionValue="${secrecyWorkOrganRelationMember.person.learningLevel }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
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
									<dictionary:select tableCode="person" fieldCode="polity" id="secrecyWorkOrganRelationMember.person.polity" name="secrecyWorkOrganRelationMember.person.polity" optionValue="${secrecyWorkOrganRelationMember.person.polity}" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
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
									<dictionary:select tableCode="person" fieldCode="specialty_code" id="secrecyWorkOrganRelationMember.person.specialtyCode" name="secrecyWorkOrganRelationMember.person.specialtyCode" optionValue="${secrecyWorkOrganRelationMember.person.specialtyCode }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('specialtyCode_helpId')" onmouseout="noneOne('specialtyCode_helpId')"  />
									<div id="specialtyCode_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有专业信息，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">行政级别：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="person_admin_level" id="secrecyWorkOrganRelationMember.person.administrativeLevel" name="secrecyWorkOrganRelationMember.person.administrativeLevel" optionValue="${secrecyWorkOrganRelationMember.person.administrativeLevel }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
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
									<dictionary:select tableCode="person" fieldCode="technic_title_level" id="secrecyWorkOrganRelationMember.person.technicTitleLevel" name="secrecyWorkOrganRelationMember.person.technicTitleLevel" optionValue="${secrecyWorkOrganRelationMember.person.technicTitleLevel }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('technicTitleLevel_helpId')" onmouseout="noneOne('technicTitleLevel_helpId')"  />
									<div id="technicTitleLevel_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有技术职称信息，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">何时从事保密工作：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyWorkOrganRelationMember.secrecyWorkStart" name="secrecyWorkOrganRelationMember.secrecyWorkStart" class="validate['required','length[20]'] Wdate" value="<s:date name='#attr.secrecyWorkOrganRelationMember.secrecyWorkStart' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								 	<font style="color: red;">&nbsp;&nbsp;*</font>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">是否专职：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="sole_duty" id="secrecyWorkOrganRelationMember.isSoleDuty" name="secrecyWorkOrganRelationMember.isSoleDuty" optionValue="${secrecyWorkOrganRelationMember.isSoleDuty }" title="true" styleClass="validate['required']" style="width:130px;"></dictionary:select>
								</td>
								<td class="tbLable fr">
									联系座机电话：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyWorkOrganRelationMember.person.phone" name="secrecyWorkOrganRelationMember.person.phone" value="${secrecyWorkOrganRelationMember.person.phone}" class="validate['required','phone','length[20]']"/>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									行政职务：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyWorkOrganRelationMember.person.duty" name="secrecyWorkOrganRelationMember.person.duty" value="${secrecyWorkOrganRelationMember.person.duty}" class="validate['length[10]']"/>
								</td>
								<td class="tbLable fr">手机：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyWorkOrganRelationMember.person.mobile" name="secrecyWorkOrganRelationMember.person.mobile" value="${secrecyWorkOrganRelationMember.person.mobile }"  class="validate['phone','length[20]']"/>
									<%-- <span style="color:red;">&nbsp;&nbsp;*</span> --%>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">其他职务：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyWorkOrganRelationMember.otherDuty" name="secrecyWorkOrganRelationMember.otherDuty" value="${secrecyWorkOrganRelationMember.otherDuty }" />
								</td>
								<td class="tbLable fr" colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									备 注：
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea id="secrecyWorkOrganRelationMember.remark" name="secrecyWorkOrganRelationMember.remark" rows="" cols="" style="width: 90%;height: 150px;" class="validate['length[1000]']">${secrecyWorkOrganRelationMember.remark}</textarea>
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