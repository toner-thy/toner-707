<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>机关网络建设情况</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/FCKeditor/fckeditor.js");
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

			String.prototype.trim=function(){
			     return this.replace(/(^\s*)|(\s*$)/g, "");
			}

			function checkRole(){
				var idx = 0;
				var roleAndNameArray1 = new Array();//first 2
				var roleAndNameArray2 = new Array();//third 1 安全审计员
				$('contentTable').getElements("select[name$='managerType']").each(function(e, i){
					if($(e).value == 3){
						roleAndNameArray2.push(e.parentNode.parentNode.getElement("input[name$='managerName']").value);
					}else{
						roleAndNameArray1.push(e.parentNode.parentNode.getElement("input[name$='managerName']").value);
					}
				});
				var flag = false;//不存在冲突
				for( var x=0; x<roleAndNameArray2.length; x++ ){
					for( var y=0; y<roleAndNameArray1.length; y++ ){
						if( roleAndNameArray2[x].trim() == roleAndNameArray1[y].trim() ){
							flag = true;//存在冲突
						}
					}
				}
				return flag;
			}

			function doSave(){
				//检查角色冲突
				if(checkRole()){
					alert("安全审计员不能同时兼任网络管理员或者安全管理员，请检查");
					return;
				}
				if (formcheck.isFormValid(true)) {
						$('sub').click();
						$('sbm_button').style.display='none';
						$('sbm_button_hidden').style.display='';
						window.setTimeout("$('sbm_button').style.display=''",8000);
						window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				 }
			}

			function doBackList(){
				if( confirm("确定退出吗？") ){
					window.location.href = "${ctx}/bmp/secretNetwork/secretNetwork_list.action?_ts="+ new Date().getTime();
				}
			}

			var rowNum = 0;
			function addRow(){
				var managerTypeStr = "<select name='secretNetwork.secretNetworkManagerses["+rowNum+"].managerType'><option value='1'>网络管理员</option><option value='2'>安全管理员</option><option value='3'>安全审计员</option></select>";
				var str = "<input type='hidden' name='secretNetwork.secretNetworkManagerses["+rowNum+"].secretNetworkManagersId' />"+managerTypeStr;
				var str1 = "<input type='text' name='secretNetwork.secretNetworkManagerses["+rowNum+"].managerName' size='15' id='mName_"+rowNum+"' value=''/><span style='color:red;'>&nbsp;&nbsp;*</span>";
				var str2 = "<input type='text' name='secretNetwork.secretNetworkManagerses["+rowNum+"].managerPost' size='15' id='mPost_"+rowNum+"'/><span style='color:red;'>&nbsp;&nbsp;*</span>";
				var str3 = "<input type='text' name='secretNetwork.secretNetworkManagerses["+rowNum+"].learning' size='15' id='mLearn_"+rowNum+"'/><span style='color:red;'>&nbsp;&nbsp;*</span>";
				var str4 = "<input type='text' name='secretNetwork.secretNetworkManagerses["+rowNum+"].graduateSchool' size='15' id='mGraduate_"+rowNum+"'/><span style='color:red;'>&nbsp;&nbsp;*</span>";
				var str5 = "<select name='secretNetwork.secretNetworkManagerses["+rowNum+"].isParticipatedSecre' style='width:50pt;'><option value='0'>否</option><option value='1'>是</option></select>";
				var str6 = "<a href='###' name='delBtn' onclick='delRow(this)' >删除</a>";

				var table = $('contentTable');

				var tr = new Element('tr', {'id':''});

				var td = new Element('td', {'style':'text-align:center;','html': str});
				var td1 = new Element('td', {'style':'text-align:center;','html': str1});
				var td2 = new Element('td', {'style':'text-align:center;','html': str2});
				var td3 = new Element('td', {'style':'text-align:center;','html': str3});
				var td4 = new Element('td', {'style':'text-align:center;','html': str4});
				var td5 = new Element('td', {'style':'text-align:center;','html': str5});
				var td6 = new Element('td', {'style':'text-align:center;','html': str6});

				td.inject(tr);
				td1.inject(tr);
				td2.inject(tr);
				td3.inject(tr);
				td4.inject(tr);
				td5.inject(tr);
				td6.inject(tr);

				tr.inject(table);
				$('mName_' + rowNum).addClass("validate['required','length[50]']");
				$('mPost_' + rowNum).addClass("validate['required','length[50]']");
				$('mLearn_' + rowNum).addClass("validate['required','length[50]']");
				$('mGraduate_' + rowNum).addClass("validate['required','length[50]']");
				formcheck.register($('mName_' + rowNum));
				formcheck.register($('mPost_' + rowNum));
				formcheck.register($('mLearn_' + rowNum));
				formcheck.register($('mGraduate_' + rowNum));
				rowNum++;
			}

			function delRow(obj){
				$(obj.parentNode.parentNode).getElements('input[type=text]').each(function(item, index) {
					formcheck.dispose(item);
				});
				 formcheck.dispose($('obsolete-field'));
				 $(obj.parentNode.parentNode).destroy();
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
					<a class="pop_button" href="javascript:doBackList();" id="back_button"><span>返回</span></a>
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
			<cp:start defaultTitle="机关网络建设情况简介" ctx="${ctx}" icoPath="#">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','机关网络建设情况简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于机关网络建设情况
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="annual_plan_description"> </cpc:tc>
					</div>
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						机关网络建设情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/secretNetwork' action='secretNetwork_adding' includeParams='true'/>" method="post">
						<table class="content_table" width="100%" id="contentTable">
							<tr>
								<td class="tbLable fr">
									建成时间：
								</td>
								<td class="tbValue fl" colspan="2">
									<input type="text" readonly="readonly" name="secretNetwork.buildTime" value="${secretNetwork.buildTime }"  class="Wdate validate['required']" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									承建单位：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" name="secretNetwork.constructionUnit" size="50" value="${secretNetwork.constructionUnit }" class="validate['required','length[100]']" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									审批部门：
								</td>
								<td class="tbValue fl" colspan="2">
									<input type="text" name="secretNetwork.approvalDepartment" size="50" value="${secretNetwork.approvalDepartment }" class="validate['required','length[100]']" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">
									审批时间：
								</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" readonly="readonly" name="secretNetwork.approvalTime" value="${secretNetwork.approvalTime }"  class="Wdate validate['required']" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/><span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									文号：
								</td>
								<td class="tbValue fl" colspan="6">
									<input type="text" name="secretNetwork.docNum" size="50" value="${secretNetwork.docNum }" class="validate['required','length[100]']" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr style="text-align: center">
								<td><b>三员类型</b></td>
								<td><b>姓名</b></td>
								<td><b>职务</b></td>
								<td><b>学历</b></td>
								<td><b>毕业学校</b></td>
								<td><b>是否参加过<br/>计算机保密业务培训</b></td>
								<td><b>操作</b>
									<a href="###" onclick="addRow()" style="cursor: hand;">增加</a>
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
		</div>

	</body>
</html>