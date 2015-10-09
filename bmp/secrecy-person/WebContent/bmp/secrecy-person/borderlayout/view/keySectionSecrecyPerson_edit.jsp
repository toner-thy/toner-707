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
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑涉密人员</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-v1.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js", function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('编辑成功，是否继续编辑？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('form_secrecyPerson_edit',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			$ENV.loader.loadJavaScript("${ctx}/resources/js/DynamicTextarea/DynamicTextarea.js",function(){
				$ENV.onDomReady(function(){
					$('secrecyPerson.resume').setStyle('width', $('secrecyPerson.resume').getParent().getSize().x * 0.89);
			        new DynamicTextarea('secrecyPerson.resume', {
			            minRows: 10
			        });
				});
			});

			var needReload = ${needReload};
			var needReload2 = false;

			function doSave(){
				if (formcheck.isFormValid(true)) {
				//if (confirm('请仔细核对输入人员基本信息，确认无误请点击【确定】,如需修改请点击【取消】!')){
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				//}
				}
			}

			function backToList(){
				TabUtil.closeTab();
			}

			var loadFlag = "${loadFlag}";
 			// AJAX实时获取人员信息
 			function getUserInfo(elements, value){
 				if(loadFlag){
 					loadFlag = false;
 					return;
 				}
 				var jsonRequest = new Request.JSON({
 					url: '${pageContext.request.contextPath}/bmp/secrecyperson/secrecyPerson_editViewUserInfo.action',
 				    onComplete: function(result, text){
 				    	if (result.userName != null) {
 							if (confirm('存在该人员信息，重新关联该人员点击【确认】，修改姓名点击【取消】!')){
 								$('actionFlag').value = "resetUser";
 								$('secrecyPerson.userInfo.sex').value = result.sex == null ? "1" : result.sex;
  		 						$('secrecyPerson.userInfo.nation').value = result.nation == null ? "1" : result.nation;
 								$('secrecyPerson.userInfo.birthDate').value = result.birthDate == null ? "" : result.birthDate;
 								$('secrecyPerson.userInfo.learningLevel').value = result.learningLevel == null ? "1" : result.learningLevel;
 								$('secrecyPerson.userInfo.identityCard').value = result.idCard == null ? "" : result.idCard;
 								$('secrecyPerson.department.departmentId').value = result.departmentId;
 								$('secrecyPerson.department.departmentName').value = result.departmentName;
 								$('secrecyPerson.userInfo.staff').value = result.staff == null ? "1" : result.staff;
 								$('secrecyPerson.userInfo.mobile').value = result.mobile == null ? "" : result.mobile;
 							}
 				    	}
 				    }
 				}).get({
 					'userInfoId ' : value.value,
 				    '_ts': new Date().getTime()
 				});
 			}
		</script>

		<s:actionmessage theme="messages"/>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="but_bar">
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
			<%-- <cp:start defaultTitle="保密要害部门简介" ctx="${ctx}" icoPath="/bmp/key-section/borderlayout/skin/blue/img/list_cpIco.gif">
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
						保密要害部门【${department.departmentName}】 ─ 编辑涉密人员
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<form id="form_secrecyPerson_edit" action="<s:url namespace='/bmp/secrecyperson' action="secrecyPerson_editing" includeParams="true"/>" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" id="actionFlag" name="actionFlag"/>
						<input type="hidden" name="secrecyPerson.secrecyPersonId" value="${secrecyPerson.secrecyPersonId}" />
						<input type="hidden" name="userInfoId" value="${secrecyPerson.userInfo.userInfoId}" />
						<input type="hidden" id="departmentId" name="departmentId" value="${department.departmentId}"/>
						<input type="hidden" name="secrecyPerson.createPerson.userId" value="${secrecyPerson.createPerson.userId}"/>
						<input type="hidden" name="secrecyPerson.createTime" value="${secrecyPerson.createTime}"/>

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
 									<ui:selectByOrgan valueEl="secrecyPerson.userInfo.userInfoId" textEl="secrecyPerson.userInfo.name" required="true" onlyFromValue="false" onSelect="getUserInfo" value="${secrecyPerson.userInfo.userInfoId}" text="${secrecyPerson.userInfo.name}"></ui:selectByOrgan>
								</td>
								<td class="tbLable fr">性 别：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="sex" id="secrecyPerson.userInfo.sex" name="secrecyPerson.userInfo.sex" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.userInfo.sex}"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">民 族：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="nation" id="secrecyPerson.userInfo.nation" name="secrecyPerson.userInfo.nation" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.userInfo.nation}"></dictionary:select>
								</td>
								<td class="tbLable fr">出生年月：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.userInfo.birthDate" name="secrecyPerson.userInfo.birthDate" class="Wdate" value="<s:date name='#attr.secrecyPerson.userInfo.birthDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">文化程度：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="learning_level" id="secrecyPerson.userInfo.learningLevel" name="secrecyPerson.userInfo.learningLevel" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.userInfo.learningLevel}"></dictionary:select>
								</td>
								<td class="tbLable fr">身份证号：</td>
								<td class="tbValue fl">
									<input name="secrecyPerson.userInfo.identityCard" id="secrecyPerson.userInfo.identityCard" type="text" class="validate['length[18]','idCard']" value="${secrecyPerson.userInfo.identityCard}"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">政治面貌：</td>
								<td class="tbValue fl">
									<%-- <input name="secrecyPerson.politicalStatus" id="secrecyPerson.politicalStatus" type="text" value="${secrecyPerson.politicalStatus}"/> --%>
									<dictionary:select tableCode="person" fieldCode="polity" id="secrecyPerson.politicalType" name="secrecyPerson.politicalType" title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.politicalType}"></dictionary:select>
								</td>
								<td class="tbLable fr">参加工作时间：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.firstWorkDate" name="secrecyPerson.firstWorkDate"  class="Wdate" value="<s:date name='#attr.secrecyPerson.firstWorkDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">部 门：</td>
								<td class="tbValue fl">
									<c:if test="${department.departmentId == null}">
										<dep:selectByOrgan valueEl="secrecyPerson.department.departmentId" textEl="secrecyPerson.department.departmentName" required="true" onlyFromValue="false" value="${secrecyPerson.department.departmentId}" text="${secrecyPerson.department.departmentName}"></dep:selectByOrgan>
									</c:if>
									<c:if test="${department.departmentId != null}">
										${department.departmentName}
									</c:if>
								</td>
								<td class="tbLable fr">职 务：</td>
								<td class="tbValue fl">
									<input name="secrecyPerson.officeDuty" id="secrecyPerson.officeDuty" type="text" value="${secrecyPerson.officeDuty}"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">岗 位：</td>
								<td class="tbValue fl">
									<input id="secrecyPerson.post" name="secrecyPerson.post" type="text" value="${secrecyPerson.post}"/>
								</td>
								<td class="tbLable fr">编 制：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" fieldCode="staff" id="secrecyPerson.userInfo.staff" name="secrecyPerson.userInfo.staff" title="false" titleText="请选择" style="width: 130px;" optionValue="${secrecyPerson.userInfo.staff}"></dictionary:select>
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
								<td class="tbLable fr" style="width:20%;">签订保密责任书时间：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.secSignBookTime" name="secrecyPerson.secSignBookTime" class="Wdate" value="<s:date name='#attr.secrecyPerson.secSignBookTime' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td class="tbLable fr">手 机：</td>
								<td>
									<input id="secrecyPerson.userInfo.mobile" name="secrecyPerson.userInfo.mobile" type="text"  class="validate['length[50]']" value="${secrecyPerson.userInfo.mobile}"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">取得上岗证书时间：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.secUppostTime" name="secrecyPerson.secUppostTime" class="Wdate" value="<s:date name='#attr.secrecyPerson.secUppostTime' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td class="tbLable fr">&nbsp;</td>
								<td class="tbValue fl">&nbsp;</td>
							</tr>
							<tr>
								<td class="tbLable fr">个人简历：</td>
								<td class="tbLable fl" colspan="3">
									<textarea id="secrecyPerson.resume" name="secrecyPerson.resume" style="padding: 5px;" class="validate['length[2000]']" >${secrecyPerson.resume}</textarea>
									提示：填写的内容包含参加工作时间、工作性质、个人经历等内容。
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">单位审查意见：</td>
								<td class="tbLable fl" colspan="3">
									<textarea id="secrecyPerson.organCheckOpinion" name="secrecyPerson.organCheckOpinion" style="width: 90%;padding: 5px;" rows="5" class="validate['length[500]']" >${secrecyPerson.organCheckOpinion}</textarea><br/>
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