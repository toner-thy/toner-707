<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/secrecyPerson/search" prefix="sp"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增涉密人员</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js", function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('新增成功，是否继续添加？')){
							needReload2 = true;
							nestedflag="${nestedflag}";
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('form_secrecyPerson_add',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					//设置两个radio的默认选项
					/* if( "${empty secrecyPerson or empty secrecyPerson.isSecrecyDepWorker}" == "true" ){
						$("secrecyPerson.isSecrecyDepWorker.NO").set("checked","checked");
					} */
					if( "${empty secrecyPerson or empty secrecyPerson.responsiblePerson}" == "true" ){
						$("secrecyPerson.responsiblePerson.NO").set("checked","checked");
					}

				});
			});

			$ENV.loader.loadJavaScript("${ctx}/resources/js/DynamicTextarea/DynamicTextarea.js",function(){
				$ENV.onDomReady(function(){
					$('secrecyPerson.resume').setStyle('width', $('secrecyPerson.resume').getParent().getSize().x * 0.89);
			        new DynamicTextarea('secrecyPerson.resume', {
			            minRows: 10
			        });

			        $("secrecyPerson.userInfo.identityCard").addEvent('keyup', function(){
			        	$("largeDiv").setStyle('display','');
			        	$("largeDiv").setStyle('margin-bottom','5px');
			        	$("largeDiv").setStyle('left','3px');
			        	var str = $("secrecyPerson.userInfo.identityCard").get("value");
			        	var strx = changeStr(str);
						$("largeDiv").set("text", strx);
			        });

			        $("secrecyPerson.userInfo.identityCard").addEvent('blur', function(){
			        	var str = $("secrecyPerson.userInfo.identityCard").get("value");
			        	if( str.length == 18 ){
			        		var   re =/^(.{6})(.{4})(.{2})(.{2})(.{4})$/;
							var result=  re.exec(str);
							if( result[2] && result[3] && result[4] ){
								$("secrecyPerson.userInfo.birthDate").set('value',result[2] + "-" + result[3] + "-" + result[4]);
							}
			        	}
			        	$("largeDiv").set("text", "");
			        	$("largeDiv").setStyle('display','none');
			        });
				});
			});

			function changeStr(str){
				var returnStr = "";
				if( str!=null && str!="" && str.length <=18 ){
					var len = str.length;
					if( len<6 ){
						returnStr = str;
					}else if( len<15 ){
						returnStr = str.substr(0,6)+" "+ str.substr(6);
					}else if(len<=18 ){
						returnStr = str.substr(0,6)+" "+ str.substr(6,8) + " " + str.substr(14);
					}
				}else{
					returnStr = "超过长度";
				}
				return returnStr;
			}

 			var needReload = ${needReload};
			var needReload2 = false;

			// 保存
			function doSave(){
				if (formcheck.isFormValid(true)) {
					var jsonRequest = new Request.JSON({
	 					url: '${pageContext.request.contextPath}/bmp/secrecyperson/secrecyPerson_checkUserInfoName.action',
	 				    onComplete: function(result, text){
	 				    	if (result.uNum != null && result.uNum > 1) {
	 				    		alert("姓名已存在，请修改人员姓名");
	 				    		return;
	 				    	}else{

	 								//if (confirm('请仔细核对输入人员基本信息，确认无误请点击【确定】,如需修改请点击【取消】!')){
	 									// 设置隐藏域userInfoId的值
	 									$('userInfoId').value = $('secrecyPerson.userInfo.userInfoId').value;
	 									$('sub').click();
	 									$('sbm_button').style.display='none';
	 									$('sbm_button_hidden').style.display='';
	 									window.setTimeout("$('sbm_button').style.display=''",8000);
	 									window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
	 								//}
	 				    	}
	 				    }
	 				}).get({
	 					'userName' : $('secrecyPerson.userInfo.name').value,
	 					'userInfoId' : $('secrecyPerson.userInfo.userInfoId').value,
	 				    '_ts': new Date().getTime()
	 				});
				}
			}

			// 返回
 			function backToList(){
			 	TabUtil.closeTab();
			}

 			// AJAX实时获取人员信息
 			function getUserInfo(elements, value){
 				var jsonRequest = new Request.JSON({
 					url: '${pageContext.request.contextPath}/bmp/secrecyperson/secrecyPerson_addViewUserInfo.action',
 				    onComplete: function(result, text){
 				    	if (result.userName != null) {
 							if (confirm('存在该人员信息，使用该人员点击【确认】，新增人员点击【取消】!')){
 								$('actionFlag').value = "update";
 								$('secrecyPerson.userInfo.sex').value = result.sex == null ? "1" : result.sex;
  		 						$('secrecyPerson.userInfo.nation').value = result.nation == null ? "1" : result.nation;
 								$('secrecyPerson.userInfo.birthDate').value = result.birthDate == null ? "" : result.birthDate;
 								$('secrecyPerson.userInfo.learningLevel').value = result.learningLevel == null ? "1" : result.learningLevel;
 								$('secrecyPerson.userInfo.identityCard').value = result.idCard == null ? "" : result.idCard;
 								$('secrecyPerson.department.departmentId').value = result.departmentId;
 								$('secrecyPerson.department.departmentName').value = result.departmentName;
 								$('secrecyPerson.userInfo.name').setProperty('disabled','disabled');
 								//$('secrecyPerson.userInfo.staff').value = result.staff == null ? "1" : result.staff;
 								$('secrecyPerson.userInfo.mobile').value = result.mobile == null ? "" : result.mobile;
 								$('secrecyPerson.userInfo.polity').value = result.polity == null ? "" : result.polity;
 								$('secrecyPerson.userInfo.administrativeLevel').value = result.administrativeLevel == null ? "" : result.administrativeLevel;
 							} else {
 								$('secrecyPerson.userInfo.name').value = $('secrecyPerson.userInfo.name').value +1;
 								$('actionFlag').value = "add";
 								$('secrecyPerson.userInfo.sex').value = "1";
 		 						$('secrecyPerson.userInfo.nation').value ="1";
 								$('secrecyPerson.userInfo.birthDate').value ="";
 								$('secrecyPerson.userInfo.learningLevel').value ="1";
 								$('secrecyPerson.userInfo.identityCard').value ="";
 								//$('secrecyPerson.userInfo.staff').value ="1";
 								$('secrecyPerson.userInfo.mobile').value ="";
 								$('secrecyPerson.userInfo.polity').value = "";
 								$('secrecyPerson.userInfo.administrativeLevel').value="";
 							}
 				    	} else {
 				    		alert(result.message);
 				    	}
 				    }
 				}).get({
 					'userInfoId ' : value.value,
 					'departmentId' : $('departmentId').value,
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
					<a class="pop_button" href="javascript:backToList();"><span>返回列表</span></a>
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
			<!-- 复合面板开始 -->
			<%-- <cp:start defaultTitle="涉密人员简介" ctx="${ctx}" icoPath="/bmp/secrecy-person/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','涉密人员简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密人员
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_person"> </cpc:tc>
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
			<cp:end> </cp:end> --%>
			<!-- 复合面板结束 -->

			<!-- 涉密人员panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<s:if test="#request.nestedflag!=1">新增涉密人员</s:if>
						<s:else>
							保密要害部门【${secrecyPerson.department.departmentName}】 ─ 新增涉密人员
						</s:else>
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<form id="form_secrecyPerson_add" action="<s:url namespace='/bmp/secrecyperson' action='secrecyPerson_adding.action' includeParams='true'/>" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" id="userInfoId" name="userInfoId"/>
						<input type="hidden" id="actionFlag" name="actionFlag"/>
						<input type="hidden" id="nestedflag" name="nestedflag" value="${nestedflag}"/>
						<input type="hidden" id="partId" name="partId" value="${partId}"/>
						<input type="hidden" id="departmentId" name="departmentId" value="${department.departmentId}"/>

						<table class="content_table st" width="100%">
							<tr>
								<td class="tbLable fr">单 位：</td>
								<td class="tbValue fl" colspan="3">
									${organ.organName}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">姓 名：</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyPerson.userInfo.userInfoId" textEl="secrecyPerson.userInfo.name" required="true" onlyFromValue="false" onSelect="getUserInfo" value="${secrecyPerson.userInfo.userInfoId}" text="${secrecyPerson.userInfo.name}" buttonEl="personBtn"></ui:selectByOrgan>
								</td>
								<td class="tbLable fr">身份证号：</td>
								<td class="tbValue fl">
									<div id="largeDiv" style="position: relative;display:none; background-color: yellow;border:solid 1px;border-color: red;font-size: x-large;" ></div>
									<input name="secrecyPerson.userInfo.identityCard" id="secrecyPerson.userInfo.identityCard" type="text" class="validate['required','length[18]','idCard']" value="${secrecyPerson.userInfo.identityCard}"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
							<td class="tbLable fr">部门名称：</td>
								<td class="tbValue fl">
									<s:if test="#request.nestedflag!=1">
										<dep:selectByOrgan valueEl="secrecyPerson.department.departmentId" textEl="secrecyPerson.department.departmentName" required="true" onlyFromValue="false" value="${secrecyPerson.department.departmentId}" text="${secrecyPerson.department.departmentName}"></dep:selectByOrgan>
									</s:if>
									<s:else>
										${secrecyPerson.department.departmentName}
										<input type="hidden" id="secrecyPerson.department.departmentName}" name="secrecyPerson.department.departmentName}" value="${secrecyPerson.department.departmentName}" />
										<input type="hidden" id="secrecyPerson.department.departmentId}" name="secrecyPerson.department.departmentId}" value="${secrecyPerson.department.departmentId}" />
									</s:else>
								</td>
								<td class="tbLable fr">性 别：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="sex" id="secrecyPerson.userInfo.sex" name="secrecyPerson.userInfo.sex"  title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.userInfo.sex}"></dictionary:select>
								</td>

							</tr>
							<tr>
								<td class="tbLable fr">学 历：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="learning_level" id="secrecyPerson.userInfo.learningLevel" name="secrecyPerson.userInfo.learningLevel" title="true" titleText="本科" titleTextValue="7" style="width: 130px;" optionValue="${secrecyPerson.userInfo.learningLevel}"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('learningLevel_helpId')" onmouseout="noneOne('learningLevel_helpId')"  />
									<div id="learningLevel_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有学历信息，请选择“无”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">出生年月：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.userInfo.birthDate" name="secrecyPerson.userInfo.birthDate" class="Wdate" value="<s:date name='secrecyPerson.userInfo.birthDate' format='yyyy-MM-dd'/>" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">政治面貌：</td>
								<td class="tbValue fl">
									<%-- <input name="secrecyPerson.politicalStatus" id="secrecyPerson.politicalStatus" type="text" value="${secrecyPerson.politicalStatus}"/> --%>
									<%-- <dictionary:select tableCode="person" fieldCode="polity" id="secrecyPerson.politicalType" name="secrecyPerson.politicalType" title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.politicalType}"></dictionary:select> --%>
									<dictionary:select tableCode="person" fieldCode="polity" id="secrecyPerson.userInfo.polity" name="secrecyPerson.userInfo.polity"    style="width: 130px;" optionValue="${secrecyPerson.userInfo.polity}"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('polity_helpId')" onmouseout="noneOne('polity_helpId')"  />
									<div id="polity_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有政治面貌信息，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">参加工作时间：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.firstWorkDate" name="secrecyPerson.firstWorkDate"  class="Wdate" value="<s:date name='#attr.secrecyPerson.firstWorkDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
							  <td class="tbLable fr">民 族：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="nation" id="secrecyPerson.userInfo.nation" name="secrecyPerson.userInfo.nation"  title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.userInfo.nation}"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('nation_helpId')" onmouseout="noneOne('nation_helpId')"  />
									<div id="nation_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有民族信息或者列表中没有该民族选项，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">职 务：</td>
								<td class="tbValue fl">
									<input name="secrecyPerson.officeDuty" id="secrecyPerson.officeDuty"  type="text" value="${secrecyPerson.officeDuty}" class="validate['length[30]']"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">岗 位：</td>
								<td class="tbValue fl">
									<input id="secrecyPerson.post" name="secrecyPerson.post" type="text" value="${secrecyPerson.post}" class="validate['length[30]']"/>
								</td>
								<td class="tbLable fr">人员类型：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="person_type" id="secrecyPerson.personType" name="secrecyPerson.personType"  style="width: 130px;"></dictionary:select>
								</td>
							</tr>
 							<tr>
								<td class="tbLable fr">行政级别：</td>
								<td class="tbValue fl">
									<%-- <input id="secrecyPerson.post" name="secrecyPerson.post" type="text" value="${secrecyPerson.post}"/> --%>
									<dictionary:select tableCode="bmp" fieldCode="person_admin_level" id="secrecyPerson.userInfo.administrativeLevel" title="true" titleText="其它" titleTextValue="99" name="secrecyPerson.userInfo.administrativeLevel"  style="width:130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('administrativeLevel_helpId')" onmouseout="noneOne('administrativeLevel_helpId')"  />
									<div id="administrativeLevel_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有行政级别信息，请选择“其他”
										<br/>
									</div>
								</td>
								<td class="tbLable fr">技术职称：</td>
								<td class="tbValue fl">
									<%-- <input id="secrecyPerson.post" name="secrecyPerson.post" type="text" value="${secrecyPerson.post}"/> --%>
									<dictionary:select tableCode="person" fieldCode="technic_title_level" id="secrecyPerson.userInfo.technicTitleLevel" title="true" titleText="其它" titleTextValue="4" name="secrecyPerson.userInfo.technicTitleLevel" style="width:130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('technicTitleLevel_helpId')" onmouseout="noneOne('technicTitleLevel_helpId')"  />
									<div id="technicTitleLevel_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有技术职称信息，请选择“其他”
										<br/>
									</div>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">涉密等级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_person" id="secrecyPerson.secrecyPersonLevel" name="secrecyPerson.secrecyPersonLevel" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.secrecyPersonLevel}"></dictionary:select>
								</td>
								<td class="tbLable fr">办公室电话：</td>
								<td class="tbValue fl">
									<input name="secrecyPerson.officePhone" id="secrecyPerson.officePhone" type="text"  class="validate['length[50]']" value="${secrecyPerson.officePhone}"/>
								</td>
							</tr>

							<tr>
								<!-- <td class="tbLable fr">是否属于要害部门工作人员：</td>
								<td class="tbValue fl">
									<input type="radio" name="secrecyPerson.isSecrecyDepWorker" id="secrecyPerson.isSecrecyDepWorker.YES" value="1" />
										<label for="secrecyPerson.isSecrecyDepWorker.YES">是</label>
									<input type="radio" name="secrecyPerson.isSecrecyDepWorker" id="secrecyPerson.isSecrecyDepWorker.NO" value="0" />
										<label for="secrecyPerson.isSecrecyDepWorker.NO">否</label>
								</td> -->
								<td class="tbLable fr">是否为定密责任人：</td>
								<td class="tbValue fl">
									<input type="radio" name="secrecyPerson.responsiblePerson" id="secrecyPerson.responsiblePerson.YES" value="1" />
										<label for="secrecyPerson.responsiblePerson.YES">是</label>
									<input type="radio" name="secrecyPerson.responsiblePerson" id="secrecyPerson.responsiblePerson.NO" value="0" />
										<label for="secrecyPerson.responsiblePerson.NO">否</label>
								</td>
								<td class="tbLable fr" style="width:20%;">签订保密责任书时间：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.secSignBookTime" name="secrecyPerson.secSignBookTime" class="Wdate" value="<s:date name='#attr.secrecyPerson.secSignBookTime' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">手 机：</td>
								<td>
									<input id="secrecyPerson.userInfo.mobile" name="secrecyPerson.userInfo.mobile" type="text"  class="validate['phone','length[50]']" value="${secrecyPerson.userInfo.mobile}"/>
								</td>
								<td class="tbLable fr">取得上岗证书时间：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.secUppostTime" name="secrecyPerson.secUppostTime" class="Wdate" value="<s:date name='#attr.secrecyPerson.secUppostTime' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">个人简历：</td>
								<td class="tbLable fl" colspan="3">
									<textarea id="secrecyPerson.resume" name="secrecyPerson.resume" style="width: 90%;padding: 5px;" rows="5" class="validate['length[2000]']">${secrecyPerson.resume}</textarea><br/>
									提示：填写的内容包含参加工作时间、工作性质、个人经历等内容。
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">单位审查意见：</td>
								<td class="tbLable fl" colspan="3">
									<textarea id="secrecyPerson.organCheckOpinion" name="secrecyPerson.organCheckOpinion" style="width: 90%;padding: 5px;" rows="5" class="validate['length[500]']">${secrecyPerson.organCheckOpinion}</textarea><br/>
									提示：XXX同志经过保密培训，经涉密人员资格审查，符合涉密人员上岗要求，特此同意。XXXX单位
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>
					</form>
				</div>
			</div>
			<!-- 涉密人员panel结束 -->

		</div>
	</body>
</html>