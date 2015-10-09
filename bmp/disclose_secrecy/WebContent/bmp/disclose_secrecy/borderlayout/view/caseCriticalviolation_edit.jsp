<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑严重违规案件</title>


		<!-- FCK支持 -->
		<script src="${ctx}/resources/FCKeditor/fckeditor.js" type="text/javascript"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
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
						if (!confirm('编辑成功，是否继续编辑？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					new FormCheck('form_secrecyLawCode_update',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});
			var needReload = ${needReload};
			var needReload2 = false;

			function doSave() {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
			}
			function doAddPerson(id){
				$('subdoAddPerson').click();
				//window.location.href="${ctx}/bmp/caseCriticalviolation/caseHandledutyPerson_add.action?caseCriticalviolation.caseCriticalviolationId="+id;
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
				window.location.href="${ctx}/bmp/caseCriticalviolation/caseHandledutyPerson_edit.action?caseHandledutyPerson.caseHandledutyPersonId="+items[0].value
						+"&&caseCriticalviolation.caseCriticalviolationId="+id;
			}

			// 删除部位涉密人员
			function doDelPerson(action,id){
				var items =EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}
				 if(window.confirm("确定删除？")){
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids+"&&caseCriticalviolation.caseCriticalviolationId="+id;
				};
			}
			function doBack(){
				TabUtil.closeTab();
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
		<div class="button_bar no_print">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBack();"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密法律法规简介" ctx="${ctx}" icoPath="/bmp/disclose_secrecy/borderlayout/skin/blue/img/09.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密法律法规简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于严重违规案件
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_caseCriticalviolation"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						编辑严重违规案件
					</div>
					<div class="panel_btn_bar pop_button_bar no_print">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_secrecyLawCode_update" class="form" action="<s:url action="caseCriticalviolation_editing" includeParams="true"/>" method="post" enctype="multipart/form-data">



						<!-- 隐藏域 -->
						<input type="hidden" value="${caseCriticalviolation.caseCriticalviolationId}" name="caseCriticalviolation.caseCriticalviolationId" id="caseCriticalviolation.caseCriticalviolationId" />

						<table class="content_table st" width="100%">

						<tr>
							<td class="tbLable fr">名 称：</td>
							<td class="tbValue fl" colspan="3"><input type="text"
								id="caseCriticalviolation.name" name="caseCriticalviolation.name"
								style="width: 65%" value="${caseCriticalviolation.name}"
								class="validate['required','length[100]']" maxlength="100" /> <span
								style="color: red;">*</span>
								<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('expid')" onmouseout="noneOne('expid')"  />
                                    <div id="expid"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
	                                                                              &ensp;&ensp; 1、 案件名称应包括：案件发生部门、姓名和发案形式。 <br/>
	                                                                              &ensp;&ensp; 2、 如：×× 部门 李×网络泄密案；<br/>
	                                                <br/>
                                    </div>
								</td>
								</td>
						</tr>
						<tr>
							<td class="tbLable fr">部门名称：</td>
							<td class="tbValue fl">
								<dep:selectByOrgan valueEl="caseCriticalviolation.department.departmentId"
									textEl="caseCriticalviolation.department.departmentName" required="true" onlyFromValue="true"
									styleClass="validate['length[32]']" text="${caseCriticalviolation.department.departmentName }" value="${caseCriticalviolation.department.departmentId }" buttonEl="sss" />
							<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('dapartName')" onmouseout="noneOne('dapartName')"  />
                                    <div id="dapartName"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
			                                                           &ensp;&ensp;1、只能点击选择按钮选择对应的部门。<br/>

	                                                <br/>
                                    </div>
							</td>
							<td class="tbLable fr">发案形式：</td>
							<td class="tbValue fl"><dictionary:select tableCode="bmp"
							        filterValues="${filterValues}"
									fieldCode="case_Type" id="caseCriticalviolation.caseType"
									name="caseCriticalviolation.caseType" style="width: 200px;"
									optionValue="${caseCriticalviolation.caseType}" /></td>
						</tr>
						<tr>
							<td class="tbLable fr">责任单位性质：</td>
							<td class="tbValue fl"><dictionary:select tableCode="bmp"
									fieldCode="duty_organ_kind"
									id="caseCriticalviolation.dutyOrganKind"
									name="caseCriticalviolation.dutyOrganKind" style="width: 200px;"
									optionValue="${caseCriticalviolation.dutyOrganKind}" /></td>
							<td class="tbLable fr">创建时间：</td>
							<td class="tbValue fl"><input type="text"
								id="secrecyPolicyCode.awardTime"
								name="secrecyPolicyCode.awardTime"
								class="Wdate validate['required','length[20]']"
								value="<s:date name='#attr.caseCriticalviolation.createTime' format='yyyy-MM-dd'/>"
								onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								readonly="readonly" /> <font color="red">*</font>
								<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('time')" onmouseout="noneOne('time')"  />
                                    <div id="time"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
	                                                                               &ensp;&ensp; 1、选择当前时间。&ensp;&ensp;&ensp;
	                                                <br/>
                                    </div>

								</td>
						</tr>
						<tr>
							<td class="tbLable fr">密 级：</td>
							<td class="tbValue fl"><dictionary:select tableCode="bmp"
									fieldCode="secrecy_level_thing" id="caseCriticalviolation.secrecyLevel"
									name="caseCriticalviolation.secrecyLevel" style="width: 132px;"
									optionValue="${caseCriticalviolation.secrecyLevel}" /></td>
							<td class="tbLable fr">查处结果：</td>
							<td class="tbValue fl"><dictionary:select tableCode="bmp"
									fieldCode="find_result" id="caseCriticalviolation.dealResult"
									name="caseCriticalviolation.dealResult" style="width: 132px;"
									optionValue="${caseCriticalviolation.dealResult}"
									 /> <font
								color="red">*</font>
								<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
                                                style="vertical-align:middle;"  onmouseover="disOne('dealResult')" onmouseout="noneOne('dealResult')"  />
                                    <div id="dealResult"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
	                                                <br/>
	                                                                               &ensp;&ensp;1、年度内结案的，选择“已查结” 。<br/>
                                                                                   &ensp;&ensp;2、年度内未结案的，选择“未查结”。<br/>

	                                                <br/>
                                    </div>
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
			<!-- 内容panel结束 -->

			<!-- 严重违规案件处理责任人panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						严重违规案件【${caseCriticalviolation.name}】处理责任人列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="javascript:doAddPerson('${caseCriticalviolation.caseCriticalviolationId}');"><span>新 增</span></a>
						<a class="pop_button" href="javascript:doEditPerson('${caseCriticalviolation.caseCriticalviolationId}');"><span>编 辑</span></a>
						<a class="pop_button" href="javascript:doDelPerson('${ctx}/bmp/caseCriticalviolation/caseHandledutyPerson_del.action','${caseCriticalviolation.caseCriticalviolationId}');"><span>删 除</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.caseHandledutyPersonList.size>0">
						<ec:table items="caseHandledutyPersonList" var="caseHandledutyPerson" tableId="caseHandledutyPersonList" border="0"
							action="${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_edit.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="caseHandledutyPersonId" alias="caseHandledutyPersonId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="userInfo.name" title="姓 名" width="20%" cell="text" alias="size=8"/>
								<ec:column property="null" title="行政级别" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="organ_admin_level" optionValue="${caseHandledutyPerson.manageLevel}"/>
								</ec:column>
								<ec:column property="null" title="处理形式" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="bmp_handleType" optionValue="${caseHandledutyPerson.handleType}"/>
								</ec:column>
								<ec:column property="null" title="政治面貌" width="10%">
									<dictionary:text tableCode="person" fieldCode="polity" optionValue="${caseHandledutyPerson.politicalLandscape}"/>
								</ec:column>
								<ec:column property="department.departmentName" title="部门名称" width="15%" cell="text" alias="size=12" />
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无涉密人员，请点击【新增】按钮添加。"/>
					</s:else>
				</div>
			</div>
			<form  class="form" action="<s:url action="caseHandledutyPerson_add.action" includeParams="true"/>" method="post" enctype="multipart/form-data">
                <input type="hidden" value="${caseCriticalviolation.name}" name="caseCriticalviolation.name" id="caseCriticalviolation.name" />
                <input type="hidden" value="${caseCriticalviolation.caseCriticalviolationId}" name="caseCriticalviolation.caseCriticalviolationId" id="caseCriticalviolation.caseCriticalviolationId" />
				<input type="submit" id="subdoAddPerson" value="subdoAddPerson" style="display: none;" />

			</form>
			<!-- 保密要害部位人员panel结束 -->
		</div>
	</body>
</html>