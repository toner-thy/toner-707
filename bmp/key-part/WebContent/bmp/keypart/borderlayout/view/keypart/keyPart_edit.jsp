<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib prefix="secPersonlist" uri="http://www.cdthgk.com/tags/secrecyperson/list"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑保密要害部位</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />

		<!-- js -->
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
					if (needReload) {
						if (!confirm('保存成功，是否继续？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					new FormCheck('form_part_edit',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					$('part.department.departmentName').addEvent('blur', doBlur);
					//$('keyPartListId').focus();
				});
			});

			var needReload = ${needReload};
  			var needReload2 = false;

			var popFlag = true;
			function doBlur(){
				var value = $('part.department.departmentName').value;
				if('${part.department.departmentName}' != value){
					if(popFlag){
						doSelectDept();
						popFlag = false;
					}
				}
			}

			function doSelectDept(){
				$ENV.dialog.open({
					title : '请选择',
					url : "${ctx}/bmp/keypart/borderlayout/view/keypart/keyPart_deptConfirm.jsp?&_ts=" + $time(),
					width : window.top.getSize().x * 0.3,
					height : window.top.getSize().y * 0.4
				});
			}

			// 保存（更新）部位信息
			function doEditSave(){
				if(!popFlag){
					return;
				}
				$('sub').click();
				$('sbm_button').style.display='none';
				$('sbm_button_hidden').style.display='';
				window.setTimeout("$('sbm_button').style.display=''",3000);
				window.setTimeout("$('sbm_button_hidden').style.display='none'",3000);
			}

			function doAddPerson(id){
				window.location.href="${ctx}/bmp/part/part_addPerson.action?part.partId="+id;
			}

			function doEditPerson(id){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}
				window.location.href="${ctx}/bmp/part/part_editPerson.action?part.partId="+id+"&partPerson.id="+items[0].value;
			}

			// 删除部位涉密人员
			function doDelPerson(){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要删除的项。");
					return;
				}
				var isDel = false;
				items.each(function(item){
					var reportState = $(item.value + "_reportState").value;
					if(reportState > 0){
						alert("已经上报的数据不能进行删除操作！");
						isDel = true;
						// 这里return是跳出循环
						return;
					}
				});
				if(isDel){
					return;
				}
				if(window.confirm("确定要删除吗？")){
					var ids = "";
					items.each(function(item){
						ids += item.value + ",";
					});
					document.getElementById("partPersonIds").value = ids;
					document.getElementById("partPersonDelForm").action = "${ctx}/bmp/part/part_deletePerson.action";
					document.getElementById("partPersonDelForm").submit();
				}
			}

			// 查看详情
			function doDetail(secrecyPersonId, partId){
				$ENV.dialog.open({
					url : '${ctx}/bmp/part/part_detailPerson.action?partPerson.secrecyPerson.secrecyPersonId='+secrecyPersonId+'&part.partId=' + partId +'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '要害部位涉密人员详情'
				});
			}

			function download(hostId,attachId){
				window.location.href = "<s:url action="part_download" includeParams="false"/>?part.partId=" + hostId + "&attachment.attachId=" + attachId;
			}

			function deleteAttachment(attachId){
				window.location.href = "<s:url action="part_deleteAttachment" includeParams="false"/>?attachment.attachId=" + attachId;
			}

			// 返回方法
			function doBackList(){
				TabUtil.closeTab();
			}

			//密级变更
			function doSecrecyChange(){
				/* $ENV.dialog.open({
					url : "/bmp/bmp/key-section/borderlayout/view/keySection_secrecy_level_change.jsp?_ts="+new Date().getTime(),
					width : 0.5,
					height : 0.5,
					title : '密级变更'
				}); */
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请仅选择一项");
					return;
				}
				$ENV.dialog.open({
					url : "${ctx}/bmp/secrecyperson/secrecyPerson_secrecyLevelChange.action?secrecyPerson.secrecyPersonId="+items[0].value+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 340,
					title : '密级变更'
				});

			}

			//脱密
			function doLeaveDuty(){
				/* $ENV.dialog.open({
					url : "/bmp/bmp/secrecy-person/borderlayout/view/secrecyPerson_Leave_Duty.jsp?_ts="+new Date().getTime(),
					width : 0.5,
					height : 0.5,
					title : '脱密'
				}); */
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请仅选择一项");
					return;
				}
				$ENV.dialog.open({
					url : "${ctx}/bmp/secrecyperson/secrecyPerson_decryption.action?secrecyPerson.secrecyPersonId="+items[0].value+"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 320,
					title : '脱密'
				});
			}
		</script>


	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doEditSave();" id="sbm_button"><span>保 存</span></a>
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

		<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密要害部位简介" ctx="${ctx}" icoPath="/bmp/keypart/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密要害部位简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密要害部位
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_key_part"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 保密要害部位panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<s:if test="#request.nestedflag==1">保密要害部门【${part.department.departmentName}】 ─ 编辑保密要害部位</s:if>
						<s:else>编辑保密要害部位</s:else>
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_part_edit" class="form" action="<s:url action='part_editing' namespace='/bmp/part' includeParams='true'/>" enctype="multipart/form-data" method="post">
						<!--隐藏域 hidden -->
						<input type="hidden" name="part.partId" value="${part.partId}" />
						<input type="hidden" name="part.createperson.userId" value="${part.createperson.userId}" />
						<input type="hidden" name="part.createTime" value="${part.createTime}" />
						<input type="hidden" id="part.reportState" name="part.reportState" value="${part.reportState}"/>
						<input type="hidden" name="nestedflag" id="nestedflag" value="${nestedflag}"/>
						<input type="hidden" name="part.secrecyStatus" id="part.secrecyStatus" value="0"/>

						<table class="content_table st" id="table_part_edit" width="100%">
							<tr>
								<td class="tbLable fr">
									单位名称：
								</td>
								<td class="tbValue fl" colspan="3">
									${part.organ.organName}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									部位名称：
								</td>
								<td class="tbValue fl">
									<input id="part.partName" type="text" name="part.partName" value="${part.partName}"
									class="textInput validate['required','length[50]']" /> <font color="red">*</font>
								</td>
								<td class="tbLable fr">
									主管部门：
								</td>
								<td class="tbValue fl">
									<s:if test="#request.nestedflag==1">
										${part.department.departmentName}
										<input type="hidden" name="part.department.departmentId" id="part.department.departmentId" value="${part.department.departmentId}"/>
										<input type="hidden" name="part.department.departmentName" id="part.department.departmentName" value="${part.department.departmentName}"/>
									</s:if><!-- 内嵌页面 -->
									<s:else>
										<dep:selectByOrgan valueEl="part.department.departmentId" textEl="part.department.departmentName"
										required="true" text="${part.department.departmentName}" value="${part.department.departmentId }"
										onlyFromValue="false" buttonEl="btnDepartName" />
										<span style="display: none" id="deptFlagShow"></span>
										<input type="hidden" id="deptFlag" name="deptFlag" value="">
										<input type="hidden" id="part.oldDeptId" name="part.oldDeptId" value="${part.department.departmentId}">
									</s:else>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									主要负责人：
								</td>
								<td class="tbValue fl">
									<ui:selectByOrgan valueEl="part.person.userInfoId" textEl="part.person.name" text="${part.person.name}"
									onlyFromValue="false" value="${part.person.userInfoId }" required="true" buttonEl="btnPerson" />
								</td>
								<td class="tbLable fr">
									联系电话：
								</td>
								<td class="tbValue fl">
									<input type="text" name="part.phone" value="${part.phone}" class="textInput validate['length[20]','phone']" /> 请填写部位联系电话
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									涉密等级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}" title="false" name="part.secrecyLevel" style="width:130px;"/>
								</td>
								<td class="tbLable fr">
									涉密人员数量：
								</td>
								<td class="tbValue fl">
									<label title="涉密人员数量由当前系统自动统计">${partPersonSize} </label>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									具体位置：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" name="part.location" value="${part.location}" class="textInput validate['length[50]']" style="width: 90%" />
								</td>
							</tr>
							<tr>
								<td class="fr">
									涉密工作事项及范围：
								</td>
								<td class="fl" colspan="3">
									<textarea style="width:95%; height: 100px;" name="part.secScope" class="validate['length[500]']">${part.secScope}</textarea><br/>
									请填写涉密工作事项概况，不需要详细罗列具体事项。
								</td>
							</tr>
							<tr>
								<td class="fr">
									技防措施：
								</td>
								<td class="fl" colspan="3">
									<input type="text" name="part.skillMeasure" value="${part.skillMeasure}" class="validate['length[100]']" style="width: 90%" />
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>
					</form>

					<!-- 附件上传开始 -->
					<table class="content_table st" id="table_part_edit" width="100%">
						<tr>
							<td class="tbLable fr">
								保密制度：
							</td>
							<td class="fl" colspan="3">
								<attach:upload uploadBehavior="bmpUploadBehavior" applyForm="form_part_edit" applyName="keyPartAttachs" attachments="${attachments}" titleText="附件上传" limit="20"/>
							</td>
							<tr>
							<td class="fl" colspan="4">
								<b>填表提示：</b><br/>
								部位名称： 填写可以参考“机要室”、“保密室”、“档案室”、“文印室”、“机房”、“资料室”等。<br/>
								具体位置： 填写样式如“×××楼×××层×××号”。<br/>
								技防措施： 电子密码文件柜、铁门、铁窗、监控视频、红外报警、电子门禁等。<br/>
								附件上传包括：1.本单位确定要害部位文件（仅上传当前部位确定文件）2.本单位确定涉密人员文件  3.保密制度文件 。<br/>
								注：更改要害部位原有信息但不保存信息的情况，一旦页面发生跳转将视为放弃要害部位信息更改操作。
							</td>
						</tr>
					</table>
					<!-- 附件上传结束 -->
				</div>
			</div>
			<!-- 保密要害部位panel结束 -->

			<!-- 保密要害部位人员panel开始 -->
			<div class="split_line"></div><!-- 分隔线 -->

			<!-- 机关涉密人员 -->
			<div class="tMargin bMargin" style="height:570px;overflow:hidden;">
				<secPersonlist:formload readonly="false" parameter="true"  parameterText="departmentId=${part.department.departmentId}&nestedflag=1&partId=${part.partId }" formName="${part.department.departmentName}"/>
			</div>
			<!-- 保密要害部位人员panel结束 -->

		</div>

		<!-- 删除部位涉密人员用隐藏表单 -->
		<form action="" method="post" id="partPersonDelForm">
			<input type="hidden" name="partPersonIds" id="partPersonIds"/>
			<input type="hidden" name="part.partId" id="part.partId" value="${part.partId}"/>
		</form>
	</body>
</html>