<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增、编辑内设机构</title>

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
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					formcheck = new FormCheck('add_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					$$('.btn_add_row').addEvent('click', function() {
						var type = this.get('type');
						addRow({
							type : type
						});
					});
					//页面加载完成动态添加已有数据到面板中
					<c:forEach items="${sortInternalOrganList}" var="ios" varStatus="internal">
						var _p = {
							type : '${ios.internalType}',
							name : '${ios.internalDeptname}',
							workNum : '${ios.internalWorkNum}',
							realNum : '${ios.internalRealNum}',
							adminLevel : '${ios.internalAdminLevel}',
							sort : '${ios.sort}'
						};
						addRow(_p);
					</c:forEach>
				});
			});



			function doBack2(){
				window.location.href = "${ctx}/secrecyorganization/secrecyoffice/secrecyOffice_edit.action?secrecyOffice.secrecyOfficeId=${secrecyOffice.secrecyOfficeId}";
			}

			function doSave(){
				if (formcheck.isFormValid(true)) {
				//if (confirm('请仔细核对输入保密办成员的信息,确认无误请点击【确定】,如需修改请点击【取消】!')){
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				//}
				}
			}


			function deleteRow(src){
				var tr = $(src).getParent().getParent();
				var tbody = tr.getParent();
				tbody.deleteRow(tr.rowIndex - 1);
			}

			var count = 0;
			var adminLevels = [];
			<c:forEach items="${adminLevelList}" var="dict">
				adminLevels.push({
					text : '${dict.optionText}',
					value : ${dict.optionValue}
				});
			</c:forEach>
			function addRow(p) {
				var _p = {
					type : '1',
					name : '',
					workNum : '',
					realNum : '',
					adminLevel : '1',
					sort : count + 1
				};
				if (p) {
					p = $merge(_p, p);
				}

				var thbody = $('sTbody_'+ p.type);
				var tr = new Element('tr', {
					'align':'center'
				});
				var td1 = new Element('td', {
						'align':'center',
						'html' : '<input type="text" id="internalOrganList[' +count+ '].internalDeptname" name="internalOrganList[' +count+ '].internalDeptname" value="'+p.name+'" style="width: 300px"/><input type="hidden" id="internalOrganList[' +count+ '].sort" name="internalOrganList[' +count+ '].sort" value="'+ p.sort + '" class="xuhao"/>'
					});
				td1.inject(tr);
				var td2 = new Element('td', {
						'align':'center',
						'html' : '<input type="text" id="internalOrganList[' +count+ '].internalWorkNum" name="internalOrganList[' +count+ '].internalWorkNum" value="'+p.workNum+'" style="width: 100px"/>'
					});
				td2.inject(tr);
				var td3 = new Element('td', {
						'align':'center',
						'html' : '<input type="text" id="internalOrganList[' +count+ '].internalRealNum" name="internalOrganList[' +count+ '].internalRealNum" value="'+p.realNum+'" style="width: 100px"/>'
					});
				td3.inject(tr);
				var td4 = new Element('td', {
						'align':'center'
					});
				var select = new Element('select', {
					id : "internalOrganList["+ count +"].internalAdminLevel" ,
					name : "internalOrganList["+ count +"].internalAdminLevel",
					style : "width:80%"
				})
				adminLevels.each(function(item){
					new Element('option', {
						value : item.value,
						text : item.text,
						selected : (p.adminLevel ==  item.value) ? true : false
					}).inject(select);
				})
				select.inject(td4);

				td4.inject(tr);
				var td5 = new Element('td', {
						'align':'center',
						'html' : '<input type="button" onclick="deleteRow(this)" value="删除"><input type="hidden" id="internalOrganList[' +count+ '].internalType" name="internalOrganList[' +count+ '].internalType" value="' +p.type+ '">'
					});
				td5.inject(tr);
				tr.inject(thbody);
				count++;
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
					<a class="pop_button" href="javascript:doBack2();"><span>返回列表</span></a>
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
			<form id="add_form" action="<s:url namespace='/secrecyorganization/secrecyoffice' action='secrecyOffice_editInternalOrganing' includeParams='true'/>" method="post">
				<!-- 隐藏域 -->
				<input type="hidden" name="secrecyOffice.secrecyOfficeId" value="${secrecyOffice.secrecyOfficeId}"/>
				<!-- 内容panel开始 -->

				<c:forEach items="${dictionaryOptionList}" var="d" varStatus="opt">
					<div class="panel tMargin bMargin">
						<div class="panel_header">
							<div class="panel_title panel_titleListIco">
								${d.optionText}
							</div>
							<div class="panel_btn_bar pop_button_bar">
								<a class="pop_button btn_add_row" href="###" type="${opt.index + 1}"><span>新增一行</span></a>
							</div>
						</div>
						<div class="panel_content panel_content_table">
							<table class="content_table st" width="100%">
								<thead>
									<tr>
										<td class="tableHeader" align="center" width="40%">部门名称</td>
										<td class="tableHeader" align="center" width="15%">编制人数</td>
										<td class="tableHeader" align="center" width="15%">实有人数</td>
										<td class="tableHeader" align="center" width="20%">行政级别</td>
										<td class="tableHeader" align="center" width="10%" rowspan="1">操作</td>
									</tr>
								</thead>
								<tbody id="sTbody_${opt.index + 1}">
								</tbody>
							</table>
						</div>
					</div>
				</c:forEach>

				<!-- 隐藏提交按钮 -->
				<div align="center">
					<input id="sub" value="sub" type="submit" style="display: none;"/>
				</div>
			</form>

	</body>
</html>