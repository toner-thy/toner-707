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
<%@ taglib uri="http://www.cdthgk.com/tags/keySection" prefix="section" %>
<%@ taglib uri="http://www.cdthgk.com/tags/keyPart" prefix="part" %>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑涉密移动存储介质</title>

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
						if (!confirm('编辑成功，是否继续编辑？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					var fc = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});

					$("sbm_button").addEvent('click', function(){
						var radios = document.getElementsByName("secrecyMobilestoragemedia.isbelongKeydepartment");
						var selectedValue = "";
						$each(radios, function(aTag, index){
						    if($(aTag).get('checked')){
						    	selectedValue = $(aTag).get('value');
						    }
						});

						if( selectedValue == "1"){
							var subRadios = document.getElementsByName("keysectionpart");
							var selectedSubValue = "";
							$each(subRadios, function(aTag, index){
							    if($(aTag).get('checked')){
							    	selectedSubValue = $(aTag).get('value');
							    }
							});
							if( selectedSubValue == "1" ){
								if( $('secrecyMobilestoragemedia.keySection.keySectionId').get('value')=="" || $('secrecyMobilestoragemedia.keySection.department.departmentName').get('value')=="" ){
									alert("请选择要害部门。");
									return;
								}
							}
							if( selectedSubValue == "2" ){
								if( $('secrecyMobilestoragemedia.keyPart.partId').get('value')=="" || $('secrecyMobilestoragemedia.keyPart.partName').get('value')=="" ){
									alert("请选择要害部位。");
									return;
								}
							}
							if($('secrecyMobilestoragemedia.department.departmentId').get('value')=="" || $('secrecyMobilestoragemedia.department.departmentName').get('value')=="" ){
								alert("请填写部门名称。");
								return;
							}
						}
						if (fc.isFormValid()) {
							if( confirm("确定提交吗？") ){
								$('sbm_button').style.display='none';
								$('sbm_button_hidden').style.display='';
								$('sub').click();
								window.setTimeout("$('sbm_button').style.display=''",8000);
								window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
							}
						}
					});

					// 页面加载完成，执行选择是否要害部门、部位
					showKeysectionpart('${secrecyMobilestoragemedia.isbelongKeydepartment}');
					var type = '${secrecyMobilestoragemedia.keySection.keySectionId}';
					if(type == ''){
						type = 2;
					} else {
						type = 1;
					}
					doChangeType(type);

				});
			});

 			var needReload = ${needReload};
			var needReload2 = false;

			// 返回
 			function backToList(){
			 	TabUtil.closeTab();
			}
			// 隐藏、显示是否属于要害部门、部位
			function showKeysectionpart(value){
				if(value == 1){
					$('dept_img').style.display = 'none';
					$('keysectionpart_show').style.display = '';
					$('secrecyMobilestoragemedia.department.departmentName').disabled = 'disabled';
				} else {
					$('dept_img').style.display = '';
					$('keysectionpart_show').style.display = 'none';
					$('secrecyMobilestoragemedia.department.departmentName').disabled = '';
					$('secrecyMobilestoragemedia.department.departmentId').value = '${secrecyMobilestoragemedia.department.departmentId}';
					$('secrecyMobilestoragemedia.keyPart.partId').value = '';
					$('secrecyMobilestoragemedia.keyPart.partName').value = '';
					$('secrecyMobilestoragemedia.keySection.keySectionId').value = '';
					$('secrecyMobilestoragemedia.keySection.department.departmentName').value = '';
				}
			}
			// 隐藏、显示要害部门、部位名称
			function doChangeType(value){
				if(value == 1){
					//显示要害部门, 隐藏要害部位信息及值
					$('keypart_show1').style.display = 'none';
					$('keypart_show2').style.display = 'none';
					$('keysection_show1').style.display = '';
					$('keysection_show2').style.display = '';
					$('secrecyMobilestoragemedia.keyPart.partId').value = '';
					$('secrecyMobilestoragemedia.keyPart.partName').value = '';
					$('keysectionpart1').checked = "checked" ;
					$('secrecyMobilestoragemedia.keySection.keySectionId').value = '${secrecyMobilestoragemedia.keySection.keySectionId}';
				} else {
					//显示要害部位, 隐藏要害部门信息及值
					$('keypart_show1').style.display = '';
					$('keypart_show2').style.display = '';
					$('keysection_show1').style.display = 'none';
					$('keysection_show2').style.display = 'none';
					$('secrecyMobilestoragemedia.keySection.keySectionId').value = '';
					$('secrecyMobilestoragemedia.keySection.department.departmentName').value = '';
					$('keysectionpart2').checked = "checked" ;
					$('secrecyMobilestoragemedia.keyPart.partId').value = '${secrecyMobilestoragemedia.keyPart.partId}';
				}
			}
			// 要害部门-----回填部门名称
			function doSelectKeySection(elements,data){
				$('secrecyMobilestoragemedia.department.departmentId').value = data.value;
				$('secrecyMobilestoragemedia.department.departmentName').value = data.text;
			}
			// 要害部位-----回填部门名称
			function doSelectKeyPart(elements,data){
				new Request.JSON({
					url: '${ctx}/bmp/part/ajax_departmentOfkeyPart.action',
				    onComplete: function(result, text){
				    	if (result!= null) {
				    		$('secrecyMobilestoragemedia.department.departmentId').value = result.departmentId;
				    		$('secrecyMobilestoragemedia.department.departmentName').value = result.departmentName;
				    	}else{
				    		alert("系统故障，请联系管理员处理");
				    	}
				    }
				}).get({
					'part.partId' : $('secrecyMobilestoragemedia.keyPart.partId').value,
				    '_ts': new Date().getTime()
				});
				//$('secrecyMobilestoragemedia.department.departmentId').value = data.data["department.departmentId"];
				//$('secrecyMobilestoragemedia.department.departmentName').value = data.data["department.departmentName"];
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
					<a class="pop_button" href="###" id="sbm_button"><span>保 存</span></a>
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
			<cp:start defaultTitle="涉密人员简介" ctx="${ctx}" icoPath="/bmp/secrecy-system/secrecy-otherdevice/borderlayout/skin/blue/img/secrecyMobilestoragemedia_list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','涉密人员简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密移动存储介质
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_mobilestoragemedia"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 涉密人员panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
							涉密移动存储介质
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/secrecysystem/secrecymobilestoragemedia' action='mobilestoragemedia_editing' includeParams='true'/>" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" id="secrecyMobilestoragemedia.secrecymobilestoragemediaId" name="secrecyMobilestoragemedia.secrecymobilestoragemediaId" value="${secrecyMobilestoragemedia.secrecymobilestoragemediaId }" />
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									介质类型：
								</td>
								<td class="tbValue fl" >
									<dictionary:select tableCode="bmp" fieldCode="media_type" id="secrecyMobilestoragemedia.mediaType" name="secrecyMobilestoragemedia.mediaType" optionValue="${secrecyMobilestoragemedia.mediaType }" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									&nbsp;
								</td>
								<td class="tbValue fl" >
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyMobilestoragemedia.secrecyLevel" name="secrecyMobilestoragemedia.secrecyLevel" optionValue="${secrecyMobilestoragemedia.secrecyLevel }" title="false" style="width:130px;"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									责任人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyMobilestoragemedia.dutyPerson.userInfoId" textEl="secrecyMobilestoragemedia.dutyPerson.name" required="true" onlyFromValue="false" value="${secrecyMobilestoragemedia.dutyPerson.userInfoId}" text="${secrecyMobilestoragemedia.dutyPerson.name }" buttonEl="btnEl"></ui:selectByOrgan>
									<%-- <sp:selectSecrecyPerson valueEl="secrecyMobilestoragemedia.dutyPerson.userInfoId" textEl="secrecyMobilestoragemedia.dutyPerson.name" text="${secrecyMobilestoragemedia.dutyPerson.name }" value="${secrecyMobilestoragemedia.dutyPerson.userInfoId }" buttonEl="secrecyPersonName" /> --%>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('userInfoId_helpId')" onmouseout="noneOne('userInfoId_helpId')"  />
									<div id="userInfoId_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											填写涉密移动存储介质使用人。多人共用 1 个涉密移动存储介质，明确 1  人为责任人。
										<br/>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									编号：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyMobilestoragemedia.mediaNo" name="secrecyMobilestoragemedia.mediaNo" value="${secrecyMobilestoragemedia.mediaNo }" class="validate['required','length[100]']"/><span style="color:red;">&nbsp;&nbsp;*</span>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('mediaNo_helpId')" onmouseout="noneOne('mediaNo_helpId')"  />
									<div id="mediaNo_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											按本单位信息化管理部门的台账编号填写。
										<br/>
									</div>
								</td>
								<td class="tbLable fr">
									序列号：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyMobilestoragemedia.mediaSeq" name="secrecyMobilestoragemedia.mediaSeq" value="${secrecyMobilestoragemedia.mediaSeq }" class="validate['required','length[100]']" /><span style="color:red;">&nbsp;&nbsp;*</span>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('mediaSeq_helpId')" onmouseout="noneOne('mediaSeq_helpId')"  />
									<div id="mediaSeq_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											填写涉密移动存储介质的出厂序列号。
										<br/>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									是否属于要害部门、部位：
								</td>
								<td class="tbValue fl">
									<c:forEach items="${isbelongKeydepartmentOptions}" var="sp" varStatus="spStat">
										<input type="radio" name="secrecyMobilestoragemedia.isbelongKeydepartment" value="${sp.optionValue}" onclick="showKeysectionpart(this.value)"
											<c:if test="${secrecyMobilestoragemedia.isbelongKeydepartment == sp.optionValue}">checked="checked"</c:if>/>${sp.optionText }
									</c:forEach>
 									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="secrecyMobilestoragemedia.department.departmentId" textEl="secrecyMobilestoragemedia.department.departmentName"
										value="${secrecyMobilestoragemedia.department.departmentId }"
										text="${secrecyMobilestoragemedia.department.departmentName }"
										required="true" onlyFromValue="false" buttonEl="dept_img"/>
								</td>
							</tr>
							<tr id="keysectionpart_show" style="display: none">
								<td class="tbLable fr">
									请选择要害部门、部位：
								</td>
								<td class="tbLable fl">
									<input type="radio" id="keysectionpart1" name="keysectionpart" value="1" onclick="doChangeType(this.value)">要害部门
									<input type="radio" id="keysectionpart2" name="keysectionpart" value="2" onclick="doChangeType(this.value)">要害部位
								</td>
								<td class="tbLable fr" id="keysection_show1" style="display: ''">
									要害部门：
								</td>
								<td class="tbValue fl" id="keysection_show2" style="display: ''">
									<section:selectKeySection valueEl="secrecyMobilestoragemedia.keySection.keySectionId" textEl="secrecyMobilestoragemedia.keySection.department.departmentName"
										value="${secrecyMobilestoragemedia.keySection.keySectionId }" buttonEl="aa" required="false" onSelect="doSelectKeySection"></section:selectKeySection>
										<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr" id="keypart_show1" style="display: none">
									要害部位：
								</td>
								<td class="tbValue fl" id="keypart_show2" style="display: none">
									<part:selectKeyPart valueEl="secrecyMobilestoragemedia.keyPart.partId" textEl="secrecyMobilestoragemedia.keyPart.partName"
										value="${secrecyMobilestoragemedia.keyPart.partId }" buttonEl="bb" required="false" onSelect="doSelectKeyPart"  dataSelector="kePartSelector"></part:selectKeyPart>
										<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									介质描述：<div >(介质类型为<br/>“其他”时填写)</div>
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea rows="5" style="width:80%;" id="secrecyMobilestoragemedia.mediaDescription" name="secrecyMobilestoragemedia.mediaDescription" class="validate['length[200]']">${secrecyMobilestoragemedia.mediaDescription }</textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>
					</form>
					<div>
						<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="add_form" applyName="secAttach" attachments="${attachmentList }" showTitle="false"/>
					 </div>
				</div>
			</div>
			<!-- 涉密人员panel结束 -->

		</div>
	</body>
</html>