<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增离退休档案</title>

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
					var sBasePath ='${ctx}/resources/FCKeditor/';
					var oFCKeditor = new FCKeditor('retireOfficial.personalRecord','99%','99%');
					oFCKeditor.BasePath	= sBasePath ;
					oFCKeditor.ReplaceTextarea() ;
					if (needReload) {
						if (!confirm('新增成功，是否继续添加?')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('form_retireOfficial_add',{
						display:{
							showErrors:1
						},
						trimValue: true
					});
				});
			});
			var needReload = ${needReload};
			var needReload2 = false;
			function doBack(){
			 	TabUtil.closeTab();
			}

			// 避免重复提交
			function doSave(){
				if (formcheck.isFormValid(true)) {
				 	var editor_personalRecord = FCKeditorAPI.GetInstance('retireOfficial.personalRecord');
					var personalRecord_value = editor_personalRecord.GetXHTML();
					if(personalRecord_value.length > 65535){
					alert("小提示，[内容备注]内容不能超过60000字符,您可以通过粘贴时清理CSS样式来减少冗余字符。");
					return null;
					}
					document.getElementById("retireOfficial.personalRecord").value = personalRecord_value;
					$('sub').click();
				    document.getElementById("sbm_button").disabled='true';
			        window.setTimeout("document.getElementById('sbm_button').disabled=false;",3000);
			        $('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				}
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
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();"  id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack()"><span>返回列表</span></a>
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
			<cp:start defaultTitle="离退休档案简介" ctx="${ctx}" icoPath="/bmp/retireofficial/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','离退休档案简介');">关 于</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于离退休档案
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_retire_offical"> </cpc:tc>
				</div>

				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
			</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增离退休档案
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>

				<!-- 内容 -->
				<div class="panel_content panel_content_table">
					<form id="form_retireOfficial_add" class="form" action="<s:url namespace='/bmp/retireofficial' action="retireofficial_save"  includeParams="true"/>" method="post">
						<input id="sub" value="sub" type="submit" style="display: none;"/>
						<table class="content_table" style="width: 99%">
							<tr height="36px;">
								<td align="right" width="15%">姓名：</td>
								<td>
									<input name="retireOfficial.name" id="retireOfficial.name" size="25" type="text" value="" class="validate['required','length[20]']"><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td align="right" width="15%">性别：</td>
							   	<td>
							   		<dictionary:select tableCode="person" fieldCode="sex" id="retireOfficial.sex" name="retireOfficial.sex" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.userInfo.sex}"></dictionary:select>
							   	</td>
							</tr>
							<tr height="36px;">
								<td align="right">出生年月：</td>
								<td>
									<input name="retireOfficial.birthDate" id="birthDate" type="text" value="" size="25" class="Wdate" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
								</td>
								<td align="right">民族：</td>
								<td>
									<dictionary:select tableCode="person" fieldCode="nation" id="retireOfficial.nation" name="retireOfficial.nation"  title="false" titleText="请选择" style="width: 130px;"></dictionary:select>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('nation_helpId')" onmouseout="noneOne('nation_helpId')"  />
									<div id="nation_helpId"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											若被填写人员没有民族信息或者列表中没有该民族选项，请选择“其他”
										<br/>
									</div>
								</td>
							</tr>

							<tr height="36px;">
								<td align="right">籍贯：</td>
								<td>
									<input name="retireOfficial.nativePlace" id="nativePlace" size="25" type="text" value="" class="validate['required','length[39]']"><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							    <td align="right">身份证号码：</td>
							    <td>
							    	<input name="retireOfficial.idCard" id="idCard" type="text" value="" size="25" class="validate['required','IDCard','length[18]']"/><span style="color:red;">&nbsp;&nbsp;*</span>
							    </td>
							</tr>

							<tr height="36px;">
								<td align="right">入党时间：</td>
								<td>
									<input name="retireOfficial.joinPartyTime" id="joinPartyTime" size="25" type="text" value="" class="Wdate" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
								</td>
								<td align="right">参加工作时间：</td>
								<td>
									<input name="retireOfficial.startWorkTime" id="startWorkTime" size="25" type="text" value="" class="Wdate" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
								</td>
							</tr>

							<tr height="36px;">
								<td align="right">离退休时间：</td>
								<td>
									<input name="retireOfficial.retireTime" id="retireTime" type="text" size="25" value="" class="Wdate" onFocus="WdatePicker({isShowClear:false,readOnly:true});"/>
								</td>
								<td align="right">离退休文号：</td>
								<td>
									<input name="retireOfficial.retireCode" id="retireCode" type="text" size="25" value="" class="validate['required','length[20]']"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr height="36px;">
							   	<td align="right"> 享受待遇/级别：</td>
							   	<td>
							   		<dictionary:select fieldCode="retireLevel" tableCode="retireOfficial" optionValue="" name="retireOfficial.retireLevel" ></dictionary:select>
							   	</td>
							    <td align="right">离退休时职务：</td>
							    <td>
							    	<input name="retireOfficial.retireOrganDuty" id="retireOrganDuty" type="text" value="" size="25" class="validate['required','length[50]']"/><span style="color:red;">&nbsp;&nbsp;*</span>
							    </td>
							</tr>

							<tr height="36px;">
							    <td align="right">月收入总额：</td>
							    <td>
							    	<input name="retireOfficial.monthEarning" id="monthEarning" type="text" size="25" value="" class="validate['arithmeticfloatnum','length[12]']"/>
							    </td>
							    <td align="right">基本离退休金：</td>
							    <td>
							    	<input name="retireOfficial.basicRetireMoney" id="basicRetireMoney" type="text" size="25" value="" class="validate['arithmeticfloatnum','length[12]']"/>
							    </td>
							</tr>
							<tr height="36px;">
							    <td align="right">生活补贴：</td>
							    <td><input name="retireOfficial.lifeSubsidy" id="lifeSubsidy" type="text" value="" size="25" class="validate['arithmeticfloatnum','length[12]']"/></td>
							    <td align="right">补贴2：</td>
							    <td><input name="retireOfficial.subsidy2" id="subsidy2" type="text" value="" size="25" class="validate['arithmeticfloatnum','length[12]']"/></td>
							</tr>
							<tr height="36px;">
							    <td align="right">粮贴：</td>
							    <td><input name="retireOfficial.foodSubsidy" id="foodSubsidy" type="text" value="" size="25" class="validate['arithmeticfloatnum','length[12]']"/></td>
							    <td align="right">身体状况：</td>
							    <td>
							    	<dictionary:select fieldCode="health" tableCode="retireOfficial" optionValue="" name="retireOfficial.health" style="width:154px;"></dictionary:select>
							    </td>
							</tr>

							<tr height="36px;">
							    <td align="right">住房面积：</td>
							    <td>
							    	<input name="retireOfficial.addressSize" id="addressSize" type="text" value="" size="25" class="validate['arithmeticfloatnum','length[5]']"/>
							    </td>
							    <td align="right">固定电话：</td>
							    <td>
							    	<input name="retireOfficial.phone" id="phone" type="text" value="" size="25" class="validate['length[15]','phone']"/>
							    </td>
							</tr>
							<tr height="36px;">
							    <td align="right">移动电话：</td>
							    <td>
							    	<input name="retireOfficial.mobile" id="mobile" type="text" value="" size="25" class="validate['length[15]','phone']"/>
							    </td>
							    <td align="right">配偶姓名：</td>
							    <td>
							    	<input name="retireOfficial.spouseName" id="spouseName" type="text" value="" size="25"  class="validate['length[20]']"/>
							    </td>
							</tr>
							<tr height="36px;">
								<td align="right">配偶出生年月：</td>
								<td>
									<input name="retireOfficial.spouseBirthDate" id="spouseBirthDate" type="text" value="" size="25" class="Wdate" onFocus="WdatePicker({isShowClear:false,readOnly:true})" />
								</td>
								<td align="right">配偶身体状况：</td>
								<td><dictionary:select fieldCode="health" tableCode="retireOfficial" optionValue=""  name="retireOfficial.spouseHealth" style="width:154px;"></dictionary:select></td>
							</tr>
							<tr height="36px;">
							    <td align="right" width="16%">配偶原单位及职务：</td>
							    <td>
							    	<input name="retireOfficial.spouseOrganDuty" id="spouseOrganDuty" type="text" value="" size="25" class="validate['length[50]']"/>
							    </td>
							    <td align="right">配偶目前状况：</td>
							    <td>
							    	<input name="retireOfficial.spouseInfo" id="spouseInfo" type="text" value="" size="25" class="validate['length[50]']"/>
							    </td>
							</tr>

							<tr height="36px;">
							    <td align="right">家庭住址：</td>
							    <td colspan="3">
							    	<input name="retireOfficial.address" id="address" type="text" value="" class="validate['length[50]']" style="width: 70%"/>
							    </td>
							</tr>

							<tr height="36px;">
								<td align="right" valign="middle">个人履历：</td>
								<td colspan="3" height="400">
									<textarea  id="retireOfficial.personalRecord" name="retireOfficial.personalRecord"></textarea>
									<input type="hidden" name="goOn" value="true"/>
									<input id="sub" type="submit" value="" class="pop_button"  style="display: none;">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>