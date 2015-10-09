<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增单位保密工作机构</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js", function(){
				$ENV.onDomReady(function(){
					var fc = new FormCheck('form_secrecyWorkOrgan_add',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});

					$("sbm_button").addEvent('click', function(){
						if (fc.isFormValid()) {
							if( confirm("请仔细核对输入单位保密办成员的信息,确认无误请点击【确定】,如需修改请点击【取消】。") ){
								$('sbm_button').style.display='none';
								$('sbm_button_hidden').style.display='';
								$('sub').click();
								window.setTimeout("$('sbm_button').style.display=''",8000);
								window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
							}
						}
					});
				});
			});

			/*
			$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/DynamicTextarea/DynamicTextarea.js",function(){
				$ENV.onDomReady(function(){
					$('secrecyWorkOrgan.dutyMemberWork').setStyle('width', $('secrecyWorkOrgan.dutyMemberWork').getParent().getSize().x);
			        new DynamicTextarea('secrecyWorkOrgan.dutyMemberWork', {
			            minRows: 4
			        });
				});
			});
			*/
			function doSave(){
				/* if (formcheck.isFormValid(true)) {
				//if (confirm('请仔细核对输入单位保密工作机构信息，确认无误请点击【确定】,如需修改请点击【取消】!')){
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				//}
				} */
			}

			function doBackLink(){
				window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_detail.action";
			}
			function query(){
				window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_query.action";
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

<%-- 迁移数据时使用的					<a class="pop_button" href="javascript:query();" id="sbm_button"><span>query</span></a> --%>
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
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ"> </cpc:tc>
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
						新增单位保密工作机构
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_secrecyWorkOrgan_add" class="form" action="<s:url action='secrecyWorkOrgan_adding' namespace='/secrecyorganization/secrecyWorkOrgan' includeParams='true'/>" enctype="multipart/form-data" method="post">
						<table class="content_table" width="100%">
							<tr>
								<td class="fr" width="25%">
									单位名称：
								</td>
								<td class="fl" colspan="3">
									${currentOrgan.organName}
								</td>
							</tr>
							<tr>
								<td class="fr" width="25%">
									机构类别：
								</td>
								<td class="fl" colspan="1">
									<dictionary:select tableCode="bmp" fieldCode="organ_category" id="secrecyWorkOrgan.organType" name="secrecyWorkOrgan.organType" title="true" titleText="挂靠机构" titleTextValue="2" style="width:130px;"></dictionary:select>
								</td>
								<td class="fr" width="25%">
									行政级别：
								</td>
								<td class="fl" colspan="1">
									<dictionary:select tableCode="bmp" fieldCode="organ_admin_level" id="secrecyWorkOrgan.organAdminLevel" name="secrecyWorkOrgan.organAdminLevel" title="true" titleText="无级别" titleTextValue="0" style="width:130px;"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="fr" width="25%">
									机构类型：
								</td>
								<td class="fl">
									<select style="width: 130px;" name="secrecyWorkOrgan.groupType">
										<option value="1">保密工作领导小组</option>
										<option value="2">保密委员会</option>
									</select>
								</td>
								<td class="fr" width="15%">
									发文号：
								</td>
								<td class="fl">
									<input id="secrecyWorkOrgan.docNo" name="secrecyWorkOrgan.docNo" class="validate['length[20]']" value=""/> 例如：川XXX[2012]XX号
								</td>
							</tr>

							<tr>
								<td class="fr">
									保密办负责人：
								</td>
								<td class="fl">
									<ui:selectByOrgan valueEl="secrecyWorkOrgan.organPrincipal.userInfoId" textEl="secrecyWorkOrgan.organPrincipal.name" required="true" onlyFromValue="false"></ui:selectByOrgan>
								</td>
								<td class="fr">
									保密办设在：
								</td>
								<td class="fl">
									<dep:selectByOrgan valueEl="secrecyWorkOrgan.department.departmentId" textEl="secrecyWorkOrgan.department.departmentName" required="true" onlyFromValue="false" text="${department.departmentName }" value="${department.departmentId }"></dep:selectByOrgan>
								</td>
							</tr>
							<tr>
								<td class="fr">
									成立(发文)日期：
								</td>
								<td class="fl">
									<input type="text" id="secrecyWorkOrgan.setupDate" name="secrecyWorkOrgan.setupDate" class="Wdate validate['length[20]'] w130" value="<s:date name='#attr.secrecyWorkOrgan.setupDate' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								</td>
								<td class="fr">
									负责人行政级别：
								</td>
								<td class="fl">
									<dictionary:select tableCode="bmp" fieldCode="organ_admin_post_level" id="secrecyWorkOrgan.organAdminPostLevel" name="secrecyWorkOrgan.organAdminPostLevel" style="width:130px;" title="true" titleText="其他" titleTextValue="2"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="fr">
									行政职务：
								</td>
								<td class="fl">
									<input id="secrecyWorkOrgan.principalDuty" name="secrecyWorkOrgan.principalDuty" type="text" class="validate['length[10]']"/>
								</td>
								<td class="fr">
									电 话：
								</td>
								<td class="fl">
									<input type="text" id="secrecyWorkOrgan.principalPhone" name="secrecyWorkOrgan.principalPhone" class="textInput validate['phone','length[20]']" />
								</td>
							</tr>
							<%--
							<tr>
								<td class="fr">
									邮 编：
								</td>
								<td class="fl">
									<input id="secrecyWorkOrgan.zipCode" name="secrecyWorkOrgan.zipCode" class="validate['digit','length[6]']" type="text" value=""/>
								</td>
								<td class="fr">
									传 真：
								</td>
								<td class="fl">
									<input id="secrecyWorkOrgan.fax" name="secrecyWorkOrgan.fax" type="text" class="validate['phone','length[20]']" value=""/>
								</td>
							</tr>
							 --%>
							<tr>
								<td class="fr">
									地 址：
								</td>
								<td class="fl" colspan="3">
									<input id="secrecyWorkOrgan.address" name="secrecyWorkOrgan.address" class="validate['length[100]']" type="text" value="" size="120" />
								</td>
							</tr>
							<%--
							<tr>
								<td class="fr">
									主要职能：
								</td>
								<td colspan="5">
									<textarea id="secrecyWorkOrgan.dutyMemberWork" name="secrecyWorkOrgan.dutyMemberWork" class="validate['length[2000]']" style="width: 95%;padding: 5px;" rows="3"></textarea>
									提示：请填写保密工作机构的职能职责，以及成员分工情况。
								</td>
							</tr>
							 --%>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>
					</form>

					<table class="content_table st" id="table_part_add" width="100%">
						<tr>
							<td class="fr">
								发文上传：
							</td>
							<td class="fl" colspan="3">
								<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="form_secrecyWorkOrgan_add" applyName="secrecyWorkOrganAttachs" titleText="附件上传"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>