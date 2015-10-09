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
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/secrecyPerson/search" prefix="sp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/keySection" prefix="section" %>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑泄密案件处理责任人员</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

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
					var needReload = ${needReload};
					var needReload2 = false;
					if (needReload) {
						if (!confirm('修改成功，是否继续修改？')){

							needReload2 = true;
							window.location.href = "${ctx}/bmp/discloseSecrecy/discloseSecrecy_edit.action?discloseSecrecy.disclosesecrecycaseId=${discloseSecrecy.disclosesecrecycaseId}";

						}
					};
					new FormCheck('form_partSecrecyPerson_add',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			// 保存
			function doSave(){
				//if (confirm('请仔细核对输入人员基本信息，确认无误请点击【确定】,如需修改请点击【取消】!')){
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				//}
			}


			// 返回方法
			function doBackList(id){
				window.location.href = "${ctx}/bmp/discloseSecrecy/discloseSecrecy_edit.action?discloseSecrecy.disclosesecrecycaseId="+id;
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

		<s:actionmessage theme="messages"/>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBackList('${discloseSecrecy.disclosesecrecycaseId}');"><span>返回列表</span></a>
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
			<cp:start defaultTitle="泄密事件简介" ctx="${ctx}" icoPath="/bmp/disclose_secrecy/borderlayout/skin/blue/img/08.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','泄密事件简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于泄密事件
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_discloseSecrecy"> </cpc:tc>
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
					泄密事件【${caseHandledutyPerson.disclosesecrecycase.name}】 ─ 编辑处理责任人
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<form id="form_partSecrecyPerson_add" action="<s:url namespace='/bmp/discloseSecrecy/' action='caseHandledutyPerson_editing.action' includeParams='true'/>" method="post">
						<!-- 隐藏域 -->
						<input id="caseHandledutyPerson.caseHandledutyPersonId" name="caseHandledutyPerson.caseHandledutyPersonId" value="${caseHandledutyPerson.caseHandledutyPersonId}" type="hidden">
						<table class="content_table st" width="100%">
							<tr>
								<td class="tbLable fr">姓 名：</td>
								<td class="tbValue fl">
								<ui:selectByOrgan valueEl="caseHandledutyPerson.userInfo.userInfoId" textEl="caseHandledutyPerson.userInfo.name" required="true" onlyFromValue="false"
									text="${caseHandledutyPerson.userInfo.name}"
									buttonEl="secrecyPersonName"></ui:selectByOrgan>
                                  <img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('name')" onmouseout="noneOne('name')"  />
                                    <div id="name"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
	                                                                   &ensp;&ensp;1、可以自己输入处理责任人姓名。<br/>
			                                                           &ensp;&ensp;2、可以点击选择按钮选择对应的人员。<br/>

	                                                <br/>
                                    </div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">行政级别：</td>
								<td class="tbValue fl">
								<dictionary:select tableCode="bmp" optionValue="${caseHandledutyPerson.manageLevel}" fieldCode="organ_admin_level" id="caseHandledutyPerson.manageLevel" name="caseHandledutyPerson.manageLevel" title="false" titleText="请选择" style="width: 130px;"></dictionary:select>
								</td>
								<td class="tbLable fr">处理形式：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" optionValue="${caseHandledutyPerson.handleType}" fieldCode="bmp_handleType" id="caseHandledutyPerson.handleType" name="caseHandledutyPerson.handleType" title="false" titleText="请选择" style="width: 130px;"></dictionary:select>
								<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('expid')" onmouseout="noneOne('expid')"  />
                                    <div id="expid"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
	                                                                   &ensp;&ensp;1、 刑事处理，包括管制、拘役、有期徒刑、无期徒刑、罚金、剥夺政治权利、没收财产、驱逐出境等。<br/>
			                                                         &ensp;&ensp;2、  行政、党纪处理，包括警告、严重警告、记过、记大过、降级、撤职、开除、撤销党内职务、留党察看、开除党籍。<br/>
			                                                          &ensp;&ensp;3、  其他处理，包括教育、训诫、经济处罚、解聘等。
	                                                <br/>
                                    </div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">政治面貌：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="person" optionValue="${caseHandledutyPerson.politicalLandscape}"  fieldCode="polity" id="caseHandledutyPerson.politicalLandscape" name="caseHandledutyPerson.politicalLandscape" title="false" titleText="请选择" style="width: 130px;"></dictionary:select>
								 <img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('personName')" onmouseout="noneOne('personName')"  />
                                    <div id="personName"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
			                                                          &ensp;&ensp;1、如果该人员既不是中共党员、共青团员、民主党派请选择"其他"<br/>

	                                                <br/>
                                    </div>
								</td>
								<td class="tbLable fr">部门名称：</td>
								<td class="tbValue fl">
                                    <dep:selectByOrgan valueEl="caseHandledutyPerson.department.departmentId"
									textEl="caseHandledutyPerson.department.departmentName" required="true" onlyFromValue="true"
									styleClass="validate['length[32]']" text="${caseHandledutyPerson.department.departmentName}" value="${caseHandledutyPerson.department.departmentId}" buttonEl="sss" />
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('dapartName')" onmouseout="noneOne('dapartName')"  />
                                    <div id="dapartName"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
			                                                          &ensp;&ensp;1、只能点击选择按钮选择对应的部门。<br/>

	                                                <br/>
                                    </div>
                                    </td>

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