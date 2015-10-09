<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增严重违规案件</title>

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
					new FormCheck('add_form',{
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
				window.location.href = "${ctx}/bmp/demo/xiemianjianDemo/yanZhongxiemianjianDemo_list.jsp";
			}

			function doAdd(){
				window.location.href = "${ctx}/bmp/demo/xiemianjianDemo/yanZhongxiemianjianDemo_deal_add.jsp";
			}
			function doEdit(){
				window.location.href = "${ctx}/bmp/demo/xiemianjianDemo/yanZhongxiemianjianDemo_deal_add.jsp";
			}

			function doDel(){
				return;
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
			<cp:start defaultTitle="严重违规案件简介" ctx="${ctx}" icoPath="#">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','严重违规案件简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于严重违规案件
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
						严重违规案件列表
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
									案件名称：
								</td>
								<td class="tbValue fl">
									<input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									查处结果：
								</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'已查结','2':'未查结'}"
									style="width:135px;"
									theme="simple"
									name=""
									>
								</s:select><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'秘密','2':'机密','3':'绝密'}"
									style="width:135px;"
									theme="simple"
									name=""
									>
									</s:select><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									发案形式：
								</td>
								<td class="tbValue fl">
									<s:select list="#{
									'1':'间谍窃密和故意向境外提供商业秘密',
									'2':'网络窃密',
									'3':'党政机关门户网站泄密',
									'4':'社会网站泄密',
									'5':'利用电子邮等方式通过互联网及其他公共信息网络传递涉密文件资料泄密',
									'6':'其他',
									'7':'非法获取、持有商业秘密载体泄密',
									'8':'买卖、转送或私自销毁商业秘密载体泄密',
									'9':'通过普通邮政、快递等无保密措施的渠道传递商业秘密载体泄密',
									'10':'邮寄、托运商业秘密载体出境，或者未经批准携带、传递商业秘密载体出境泄密',
									'11':'非法复制、记录、存储商业秘密泄密',
									'12':'私人交往和通信泄密',
									'13':'出版宣传工作泄密',
									'14':'丢失被盗泄密',
									'15':'考试泄密',
									'16':'密电明传泄密',
									'17':'其他形式泄密'
									}"
									style="width:135px;"
									theme="simple"
									name=""
									>
								</s:select><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							<tr>
								<td class="tbLable fr">
									责任单位性质：
								</td>
								<td class="tbValue fl">
									<s:select list="#{
									'1':'中央和国家机关',
									'2':'地方党政机关',
									'3':'武器装备科研生产事业单位',
									'4':'非武器装备科研生产事业单位',
									'5':'武器装备科研生产企业',
									'6':'非武器装备科研生产企业'
									}"
									style="width:135px;"
									theme="simple"
									name=""
									>
								</s:select><span style="color:red;">&nbsp;&nbsp;*</span>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									<input type="text" /><span style="color:red;">&nbsp;&nbsp;*</span>
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
			<div class="panel tMargin">
				<div class="panel_header no_print" style="position:fixed;">
					<div class="panel_title panel_titleListIco no_print">
						保密要害部门【部门名】保密要害部位列表
					</div>
					<div class="pop_button_bar" style="float:right;">
							<a href="###" id="doAdd" onclick="doAdd()" class="pop_button"><span>新增</span></a>
							<a href="###" id="doEdit" onclick="doEdit()" class="pop_button"><span>编辑</span></a>
							<a href="###" id="doDel" onclick="doDel()" class="pop_button"><span>删除</span></a>
					</div>
				</div>
				<div id="panel_content_keyPart" class="panel_content">
					<div class="eXtremeTable" >
						<table id="keySectionlist_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<thead>
							<tr>
								<td class="tableHeader"  width="5%" ><input type="checkbox"  name="keySectionId_checkbox"  id="keySectionId_checkbox"  class="class_keySectionId_checkbox"  onclick="EcTable.checkAll('keySectionId_checkbox')" /></td>
								<td class="tableHeader"  width="10%" >姓名</td>
								<td class="tableHeader"  width="10%" >行政级别</td>
								<td class="tableHeader"  width="10%" >处理形式</td>
								<td class="tableHeader"  width="10%" >政治面貌</td>
								<td class="tableHeader"  width="10%" >部门名称</td>
							</tr>
							</thead>
							<tbody class="tableBody" >
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>