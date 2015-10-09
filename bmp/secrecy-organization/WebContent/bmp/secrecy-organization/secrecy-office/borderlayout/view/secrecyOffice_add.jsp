<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增保密办（保密局）</title>

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
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/DynamicTextarea/DynamicTextarea.js",function(){
				$ENV.onDomReady(function(){
					$('secrecyOffice.dutyMemberWork').setStyle('width', $('secrecyOffice.dutyMemberWork').getParent().getSize().x);
			        new DynamicTextarea('secrecyOffice.dutyMemberWork', {
			            minRows: 4
			        });
				});
			});

			function doSave(){
				if (formcheck.isFormValid(true)) {
				//if (confirm('请仔细核对输入保密办信息，确认无误请点击【确定】,如需修改请点击【取消】!')){
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				//}
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
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
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
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增保密办（保密局）
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecyoffice' action='secrecyOffice_adding' includeParams='true'/>" method="post">
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									单位名称：
								</td>
								<td class="tbValue fl" colspan="3">
									<b><input type="text" id="secrecyOffice.name" name="secrecyOffice.name" class="validate['required','length[50]']" style="width: 70%" value="中共${currentOrgan.district.districtName}委保密委员会办公室（${currentOrgan.organName}）"/></b><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主管单位：
								</td>
								<td class="tbValue fl">
									<input type="hidden" id="secrecyOffice.mainOrgan.organId"  name="secrecyOffice.mainOrgan.organId" value="${secrecyOffice.mainOrgan.organId}"/>${secrecyOffice.mainOrgan.organName}
								</td>
								<td class="tbLable fr">
									成立时间：
								</td>
								<td class="tbValue fl">
									<input type="text" class="Wdate validate['required']" id="secrecyOffice.establishTime" name="secrecyOffice.establishTime" value="<s:date name='#attr.secrecyOffice.establishTime' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									行政级别：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="organ_admin_level" id="secrecyOffice.administrativeLevel" name="secrecyOffice.administrativeLevel"  title="true" titleText="无级别"  titleTextValue="0" style="width: 130px;" optionValue="${secrecyOffice.administrativeLevel}"></dictionary:select>
								</td>
								<td class="tbLable fr">
									经费来源：
								</td>
								<td class="tbValue fl">
									<input type="text" class="validate['required','length[200]']" id="secrecyOffice.fundsSource" name="secrecyOffice.fundsSource" value="${secrecyOffice.fundsSource }" /><span style="color:red;">&nbsp;&nbsp;*</span>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne('expid')" onmouseout="noneOne('expid')"  />
									<div id="expid"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
											指国家或地方财政拨款。
										<br/>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									机构类别：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="organ_category" id="secrecyOffice.organType" name="secrecyOffice.organType"  title="true" titleText="挂靠机构" titleTextValue="2" style="width: 130px;" optionValue="${secrecyOffice.organType}"></dictionary:select>
								</td>
								<td class="tbLable fr">
									是否政府序列：
								</td>
								<td class="tbValue fl">
										<s:select list="#{'1':'是','0':'否'}"
										style="width:130px;"
										theme="simple"
										name="secrecyOffice.govSequence"
										value="0"
										cssClass="validate['required']"
										>
									</s:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主任(局长)：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyOffice.director.userInfoId" textEl="secrecyOffice.director.name" required="true" onlyFromValue="false" value="${secrecyOffice.director.userInfoId }" text="${secrecyOffice.director.name }"></ui:selectByOrgan>
								</td>
								<td class="tbLable fr">
									办公室设在：
								</td>
								<td class="tbValue fl">
									<dep:selectByOrgan valueEl="secrecyOffice.dept.departmentId" textEl="secrecyOffice.dept.departmentName" required="true" onlyFromValue="false" value="${secrecyOffice.dept.departmentId}" text="${secrecyOffice.dept.departmentName}"></dep:selectByOrgan>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									负责人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="secrecyOffice.person.userInfoId" textEl="secrecyOffice.person.name" required="true" onlyFromValue="false" value="${secrecyOffice.person.userInfoId }" text="${secrecyOffice.person.name }"></ui:selectByOrgan>
								</td>
								<td class="tbLable fr">
									联系电话：
								</td>
								<td class="tbValue fl">
									<input id="secrecyOffice.telephone" name="secrecyOffice.telephone" class="validate['length[20]','phone']" value=""/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									传 真：
								</td>
								<td class="tbValue fl">
									<input id="secrecyOffice.fax" name="secrecyOffice.fax" class="validate['phone','length[30]']" value=""/>
								</td>
								<td class="tbLable fr">
									邮 编：
								</td>
								<td class="tbValue fl">
									<input id="secrecyOffice.postcode" name="secrecyOffice.postcode" class="validate['digit','length[6]']" value=""/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									地 址：
								</td>
								<td class="tbValue fl" colspan="3">
									<input id="secrecyOffice.address" name="secrecyOffice.address" class="validate['length[100]']" style="width: 70%"  value=""/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主要职能：
								</td>
								<td class="tbValue fl" colspan="5">
									<textarea id="secrecyOffice.dutyMemberWork" name="secrecyOffice.dutyMemberWork" class="validate['length[1000]']" style="width: 95%;padding: 5px;" rows="3"></textarea>提示：请填写保密工作机构的职能职责，以及成员分工情况。
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