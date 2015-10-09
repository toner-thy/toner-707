<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密队伍建设</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />


		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mootools/mootools-core-1.4.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/mootools/mootools-more-1.4.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/formcheck/1.4/formcheck.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/notimoo/notimoo-1.2.1.js" type="text/javascript"></script>
		<script src="${ctx}/platform/layout/borderlayout/js/TabUtils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js"></script>
		<script src="${ctx}/resources/js/SimpleUI/SimpleUI.js" type="text/javascript"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/bmp/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- FCK支持 -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/resources/FCKeditor/fckeditor.js" type="text/javascript"></script>

		<script type="text/javascript">
			window.addEvent('domready', function(){

				new FormCheck('add_form',{
					display:{
						showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
					},
					trimValue: true
				});
			});

			function doSave(){
				$('sub').click();
				$('sbm_button').style.display='none';
				$('sbm_button_hidden').style.display='';
				window.setTimeout("$('sbm_button').style.display=''",8000);
				window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
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
					<a class="pop_button pop_button_refresh" href="#" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="#" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content" style="width:99%;">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					区、县（市）保密队伍建设情况统计表
				</div>
				<div class="panel_content panel_content_table">
					<c:if test="${flag eq 'add' }">
						<form id="add_form" action="<s:url namespace='/net/secrecyTroops' action='secrecyTroops_adding' includeParams='true'/>" method="post">
					</c:if>
					<c:if test="${flag eq 'edit' }">
						<form id="add_form" action="<s:url namespace='/net/secrecyTroops' action='secrecyTroops_editing' includeParams='true'/>" method="post">
						<input type="hidden" id="secrecyTroops.id" name="secrecyTroops.id" value="${secrecyTroops.id }"/>
						<input type="hidden" id="secrecyTroops.year" name="secrecyTroops.year" value="${secrecyTroops.year}"/>
					</c:if>
						<table class="content_table" border="0">
							<tr>
								<td align="right">单位：</td>
								<td align="left">
									${secrecyTroops.reportOrgan.organName}
									<input type="hidden" id="secrecyTroops.reportOrgan.organName" name="secrecyTroops.reportOrgan.organName" value="${secrecyTroops.reportOrgan.organName }" readonly="readonly"/>
								</td>
								<td align="right">填表人：</td>
								<td align="left">
									${secrecyTroops.reportUser.userInfo.name}
									<input type="hidden" id="secrecyTroops.reportUser.userInfo.name" name="secrecyTroops.reportUser.userInfo.name" value="${secrecyTroops.reportUser.userInfo.name}" readonly="readonly"/>
								</td>
								<td align="right">填报日期：</td>
								<td align="left">
									<fmt:formatDate value="${secrecyTroops.reportDate }" pattern="yyyy-MM-dd"/>
									<input type="hidden" id="secrecyTroops.reportDate" name="secrecyTroops.reportDate" value="<fmt:formatDate value="${secrecyTroops.reportDate }" pattern="yyyy-MM-dd"/>" readonly="readonly"/>
								</td>
							</tr>
						</table>
						<table class="content_table">
							<tr>
								<td colspan="13" rowspan="4">保密队伍建设</td>
							</tr>
							<tr>
								<td rowspan="2">机关单位数量（个）</td>
								<td colspan="2" align="center">局</td>
								<td colspan="2" align="center">检查中心</td>
								<td colspan="4" align="center">学历情况</td>
								<td colspan="2" align="center">专业情况</td>
								<td colspan="2" align="center">年龄段</td>
							</tr>
							<tr>
								<td >编制人数</td>
								<td >实有人数</td>
								<td >编制人数</td>
								<td >实有人数</td>
								<td >博士生人数</td>
								<td >硕士生人数</td>
								<td >本科生人数</td>
								<td >大专及以下人数</td>
								<td >计算机及通信人数</td>
								<td >其他人数</td>
								<td >45岁以下人数</td>
								<td >45岁以上人数</td>
							</tr>
							<tr>
								<td><input id="secrecyTroops.smallOrganNum" name="secrecyTroops.smallOrganNum" value="${secrecyTroops.smallOrganNum}" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.jupPlaitNum" name="secrecyTroops.jupPlaitNum" value="${secrecyTroops.jupPlaitNum }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.juExistNum" name="secrecyTroops.juExistNum" value="${secrecyTroops.juExistNum }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.zxPlaitNum" name="secrecyTroops.zxPlaitNum" value="${secrecyTroops.zxPlaitNum }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.zxExistNum" name="secrecyTroops.zxExistNum" value="${secrecyTroops.zxExistNum }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.degreeBoNum" name="secrecyTroops.degreeBoNum" value="${secrecyTroops.degreeBoNum }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.degreeSuoNum" name="secrecyTroops.degreeSuoNum" value="${secrecyTroops.degreeSuoNum }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.degreeBenNum" name="secrecyTroops.degreeBenNum" value="${secrecyTroops.degreeBenNum }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.degreeDazNum" name="secrecyTroops.degreeDazNum" value="${secrecyTroops.degreeDazNum }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.specialyComputerNum" name="secrecyTroops.specialyComputerNum" value="${secrecyTroops.specialyComputerNum }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.specialyOtherNum" name="secrecyTroops.specialyOtherNum" value="${secrecyTroops.specialyOtherNum }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.ageFortyfiveDown" name="secrecyTroops.ageFortyfiveDown" value="${secrecyTroops.ageFortyfiveDown }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
								<td><input id="secrecyTroops.ageFortyfiveUp" name="secrecyTroops.ageFortyfiveUp" value="${secrecyTroops.ageFortyfiveUp }" style="width:60px;" type="text" class="validate['required','digit','length[6]']" /></td>
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