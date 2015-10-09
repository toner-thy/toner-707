<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="department" %>
<%@ taglib uri="http://www.cdthgk.com/tags/theme" prefix="theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<title>填报非涉密网络基本情况统计表</title>

		<script src="${pageContext.request.contextPath }/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/platform/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/css/page.css");
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/resources/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/formcheck/css/formcheck.css");
		$ENV.loader.loadStyleSheet("${contextPath}/resources/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/TextboxList/css/TextboxList.css");

		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/SimpleUI/SimpleUI.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/GrowingInput/GrowingInput.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/TextboxList/0.5/TextboxList.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/TextboxList/0.5/TextboxList.Autocomplete.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/formcheck/1.4/formcheck.js",function(){
			$ENV.onDomReady(function(){
				var formcheck = new FormCheck('form_save',{
					display:{
						showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
					},
					trimValue: true
				});

				var index = 0;

				var addRow = function(p) {
					if (!p) p = {};
					if (!p.id) p.id = '';
					if (!p.name) p.name = '';
					if (!p.num) p.num = '';
					if (!p.range) p.range = '';
					if (!p.purpose) p.purpose = '';
					var tr = new Element('tr');
					<%-- --%>
					var name = new Element('input', {
						type : 'text',
						'class' : "validate['required','length[200]']",
						name : 'noSecNetIntranetList['+ index +'].name',
						value : p.name,
						styles : {
							width : '90%'
						}
					}).inject(
						new Element('td',{
							align : 'center'
						}).inject(tr)
					);


					var num = new Element('input', {
						type : 'text',
						'class' : "validate['required','digit']",
						name : 'noSecNetIntranetList['+ index +'].userNum',
						value : p.num,
						styles : {
							width : '90%'
						}
					}).inject(
						new Element('td',{
							align : 'center'
						}).inject(tr)
					);
					var range = new Element('input', {
						type : 'text',
						'class' : "validate['required','length[200]']",
						name : 'noSecNetIntranetList['+ index +'].useRange',
						value : p.range,
						styles : {
							width : '90%'
						}
					}).inject(
						new Element('td',{
							align : 'center'
						}).inject(tr)
					);
					var purpose = new Element('input', {
						type : 'text',
						'class' : "validate['required','length[200]']",
						name : 'noSecNetIntranetList['+ index +'].purpose',
						value : p.purpose,
						styles : {
							width : '70%',
							margin : '0 12px 0 0'
						}
					});

					new Element('td',{
						align : 'center'
					})
					.adopt(purpose)
					.adopt(
						new Element('a' , {
							href : '###',
							text : '删除',
							'events' : {
								click : function(){
									formcheck.dispose(name);
									formcheck.dispose(range);
									formcheck.dispose(purpose);
									formcheck.dispose(num);
									var rowspan = $('tbody_intranet').getFirst().getFirst().get('rowspan');
									$('tbody_intranet').getFirst().getFirst().set('rowspan', rowspan - 1);
									tr.destroy();
									$('intranetNum').value = rowspan - 2;
								}
							}
						})
					)
					.adopt(
						new Element('input', {
							type : 'hidden',
							name : 'noSecNetIntranetList['+ index +'].intranetId',
							value : p.id
						})
					)
					.inject(tr);

					var rowspan = $('tbody_intranet').getFirst().getFirst().get('rowspan');
					$('tbody_intranet').getFirst().getFirst().set('rowspan', rowspan + 1);

					tr.inject($('tbody_intranet'));
					formcheck.register(name);
					formcheck.register(range);
					formcheck.register(num);
					formcheck.register(purpose);
					index++;

					$('intranetNum').value = rowspan;
				};
				<c:forEach var="i" items="${noSecNet.noSecNetIntranets}">
				addRow({
					id : '${i.intranetId}',
					name : '${i.name}',
					num : '${i.userNum}',
					range : '${i.useRange}',
					purpose : '${i.purpose}'
				});
				</c:forEach>

				$$('.multy_text').each(function(item){
					new TextboxList(item, {
						unique: true
					});
				});

				$('btnAdd').addEvent('click', function(){
					addRow();
				});
				$('btnSave').addEvent('click', function(){
					if (formcheck.isFormValid()) {
						if (window.confirm("确定保存填写内容")) {
							$('sub').click();
						}
					}
				});
			});
		});
		</script>
		<style type="text/css">
		html,body {padding: 0}
		body {overflow: auto;}

		.input_line {
			border: 0;border-bottom: 1px solid #000;
			width: 40px;
		}
		.input_line_other {
			border: 0;border-bottom: 1px solid #000;
			width: 80%;
		}
		.content_table td {
			padding-left: 10px;
			padding-right: 10px;
		}
		</style>

	</head>
	<body>
		<div class="panel">
			<!-- 头部 -->
			<div class="panel_header">
				<!-- 标题 -->
				<div class="panel_title panel_titleListIco">
				非涉密网络基本情况统计表
				</div>
			</div>
			<!-- 内容 -->
			<div class="panel_content panel_content_table">
				<form id="form_save" class="form"
					action="<s:url action="saveing" namespace="/bmp/pucha/noSecNet" includeParams="true"/>" method="post">
				<input type="hidden" name="noSecNet.noSecNetId" value="${noSecNet.noSecNetId }"/>
				<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
				<table border="0" style="width: 100%;">
					<tr>
						<td align="left" style="padding-left: 20px;">单位：（盖章）&nbsp;&nbsp; ${noSecNet.reportOrgan.name}</td>
						<td align="center">填表人：&nbsp;&nbsp; ${noSecNet.reportUser.userInfo.name}</td>
						<td align="right" style="padding-right: 20px;">填报日期：&nbsp;&nbsp; <fmt:formatDate value="${noSecNet.reportDate}" pattern="yyyy-MM-dd"/></td>
					</tr>
				</table>
				<table class="content_table">
					<tbody>
						<tr>
							<td width="30%" align="center" rowspan="3">
								与互联网连接的网络数量
								<br/>（<input type="text" name="noSecNet.internetNum" value="${noSecNet.internetNum}" class="validate['required','digit'] input_line"> 个）
							</td>
							<td width="70%" align="left" colspan="4">
								<input type="checkbox" name="noSecNet.internetOa" id="noSecNet.internetOa" value="true" <c:if test="${noSecNet.internetOa}">checked="checked"</c:if>>
								&nbsp;&nbsp;<label for="noSecNet.internetOa">机关OA网</label>
							</td>
						</tr>
						<tr>
							<td width="70%" align="left" colspan="4">
								<input type="checkbox" name="noSecNet.internetParty" id="noSecNet.internetParty" value="true" <c:if test="${noSecNet.internetParty}">checked="checked"</c:if>>
								&nbsp;&nbsp;<label for="noSecNet.internetParty">党政外网</label>
							</td>
						</tr>
						<tr>
							<td width="70%" align="left" colspan="4">
								<input type="checkbox" name="noSecNet.internetOther" id="noSecNet.internetOther" value="true" <c:if test="${noSecNet.internetOther}">checked="checked"</c:if>>
								&nbsp;&nbsp;<label for="noSecNet.internetOther">其他</label> <input type="text" name="noSecNet.internetOtherDesc" value="${noSecNet.internetOtherDesc}" class="validate['length[100]'] input_line_other">
							</td>
						</tr>
					</tbody>
					<tbody id="tbody_intranet">
						<tr>
							<td id="intranet_td_first" width="30%" align="center" rowspan="1">
								与互联网物理隔绝的网络数量
								<br/>（<input type="text" readonly="readonly" id="intranetNum" name="noSecNet.intranetNum" value="${empty noSecNet.intranetNum ? 0 : noSecNet.intranetNum}" class="validate['required','digit'] input_line"> 个）
								<br/> <a id="btnAdd" href="###">新增</a>
							</td>
							<td width="20%" align="center">
								网络名称
							</td>
							<td width="10%" align="center">
								用户数量
							</td>
							<td width="20%" align="center">
								使用范围
							</td>
							<td width="20%" align="center">
								用途
							</td>
						</tr>
					</tbody>
						<tr>
							<td width="30%" align="center">
								连接互联网计算机数量
								<br/>（<input type="text" name="noSecNet.internetComputerNum" value="${noSecNet.internetComputerNum}" class="validate['required','digit'] input_line"> 台）
							</td>
							<td width="70%" align="left" colspan="4">

							</td>
						</tr>
						<tr>
							<td width="30%" align="center" rowspan="3">
								互联网门户网站数量
								<br/>（<input type="text" name="noSecNet.internetWebsiteNum" value="${noSecNet.internetWebsiteNum}" class="validate['required','digit'] input_line"> 个）
							</td>
							<td align="left" colspan="2">
								是否存在互联网门户网址
							</td>
							<td align="left" colspan="2">
								<input type="radio" name="noSecNet.internetWebsiteExist" id="noSecNet.internetWebsiteExist.true" value="true" <c:if test="${noSecNet.internetWebsiteExist}">checked="checked"</c:if>
									class="validate['required']">
								&nbsp;&nbsp;<label for="noSecNet.internetWebsiteExist.true" style="margin-right: 40px;">是</label>
								<input type="radio" name="noSecNet.internetWebsiteExist" id="noSecNet.internetWebsiteExist.false" value="false" <c:if test="${noSecNet.internetWebsiteExist == false}">checked="checked"</c:if>
									class="validate['required']">
								&nbsp;&nbsp;<label for="noSecNet.internetWebsiteExist.false">否</label>
							</td>
						</tr>
						<tr>
							<td width="70%" align="left" colspan="4">
								<table style="width: 100%;" class="content_table">
									<tr>
										<td align="right" style="border: 0;width: 60px;">
											网址：
										</td>
										<td style="border: 0;">
											<input class="multy_text" name="noSecNet.internetWebsiteDn" value="${noSecNet.internetWebsiteDn}" type="text">
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td width="70%" align="left" colspan="4">
								<table style="width: 100%;" class="content_table">
									<tr>
										<td align="right" style="border: 0;width: 60px;">
											IP&nbsp;地址：
										</td>
										<td style="border: 0;">
											<input class="multy_text" name="noSecNet.internetWebsiteIp" value="${noSecNet.internetWebsiteIp}" type="text">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>

					</tbody>
				</table>
				<!-- 右侧按钮 -->
				<div class="panel_btn_bar pop_button_bar" style="text-align: center;">
					<a id="btnSave" href="###" class="pop_button"><span>保存</span></a>
				</div>
				</form>
			</div>
		</div>
	</body>
</html>