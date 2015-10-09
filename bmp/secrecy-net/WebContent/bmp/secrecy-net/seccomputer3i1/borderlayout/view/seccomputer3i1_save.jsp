<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="department" %>
<%@ taglib uri="http://www.cdthgk.com/tags/theme" prefix="theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<title>填报涉密计算机及“三合一”安装情况统计表</title>

		<script src="${pageContext.request.contextPath }/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/platform/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/css/page.css");
		$ENV.loader.loadStyleSheet("${pageContext.request.contextPath}/resources/theme/<theme:theme module="department"/>/skin/<theme:skin module="department"/>/formcheck/css/formcheck.css");

		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/notimoo/notimoo-1.2.1.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/SimpleUI/SimpleUI.js");
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
					if (!p.dep) p.dep = '';
					if (!p.sl) p.sl = '0';
					if (!p.user) p.user = '';
					if (!p.sn) p.sn = '';
					if (!p.install) p.install = '';
					var tr = new Element('tr');
					<%-- --%>
					var dep = new Element('input', {
						type : 'text',
						'class' : "validate['required']",
						name : 'secComputer3i1List['+ index +'].departmentName',
						value : p.dep,
						styles : {
							width : '90%'
						}
					}).inject(
						new Element('td',{
							width : '20%',
							align : 'center'
						}).inject(tr)
					);


					var l1 = new Element('label', {
						html : '<br/>绝密'
					});

					var sl1 = new Element('input', {
						type : 'radio',
						'class' : "validate['required']",
						name : 'secComputer3i1List['+ index +'].secrecyLevel',
						value : 1,
						checked : p.sl == '1'
					});
					var l2 = new Element('label', {
						html : '<br/>机密'
					});
					var sl2 = new Element('input', {
						type : 'radio',
						'class' : "validate['required']",
						name : 'secComputer3i1List['+ index +'].secrecyLevel',
						value : 2,
						checked : p.sl == '2'
					});
					var l3 = new Element('label', {
						text : '秘密'
					});
					var sl3 = new Element('input', {
						type : 'radio',
						'class' : "validate['required']",
						name : 'secComputer3i1List['+ index +'].secrecyLevel',
						value : 3,
						checked : p.sl == '3'
					});
					new Element('td',{
						width : '15%',
						align : 'center'
					}).adopt(l3).adopt(sl3).adopt(l2).adopt(sl2).adopt(l1).adopt(sl1).inject(tr);

					var user = new Element('input', {
						type : 'text',
						'class' : "validate['required']",
						name : 'secComputer3i1List['+ index +'].useDutyPerson',
						value : p.user,
						styles : {
							width : '90%'
						}
					}).inject(
						new Element('td',{
							width : '20%',
							align : 'center'
						}).inject(tr)
					);

					var sn = new Element('input', {
						type : 'text',
						'class' : "validate['required']",
						value : p.sn,
						name : 'secComputer3i1List['+ index +'].diskSn',
						styles : {
							width : '90%'
						}
					}).inject(
						new Element('td',{
							width : '30%',
							align : 'center'
						}).inject(tr)
					);


					var il1 = new Element('label', {
						html : '是'
					});

					var i1 = new Element('input', {
						type : 'radio',
						'class' : "validate['required']",
						name : 'secComputer3i1List['+ index +'].install3i1',
						value : 'true',
						checked : p.install == 'true'
					});
					var il2 = new Element('label', {
						html : '<br/>否'
					});

					var i2 = new Element('input', {
						type : 'radio',
						'class' : "validate['required']",
						name : 'secComputer3i1List['+ index +'].install3i1',
						value : 'false',
						checked : p.install == 'false'
					});
					new Element('td',{
						width : '10%',
						align : 'center'
					}).adopt(il1).adopt(i1).adopt(il2).adopt(i2).inject(tr);



					new Element('td',{
						width : '5%',
						align : 'center'
					}).adopt(
						new Element('a' , {
							href : '###',
							text : '删除',
							'events' : {
								click : function(){
									formcheck.dispose(sl1);
									formcheck.dispose(sl2);
									formcheck.dispose(sl3);
									formcheck.dispose(i1);
									formcheck.dispose(i2);
									formcheck.dispose(dep);
									formcheck.dispose(user);
									formcheck.dispose(sn);
									tr.destroy();
								}
							}
						})
					).adopt(
						new Element('input', {
							type : 'hidden',
							name : 'secComputer3i1List['+ index +'].id',
							value : p.id
						})
					).inject(tr);


					tr.inject($('table_content_body'));

					formcheck.register(sl1);
					formcheck.register(sl2);
					formcheck.register(sl3);
					formcheck.register(i1);
					formcheck.register(i2);
					formcheck.register(dep);
					formcheck.register(user);
					formcheck.register(sn);

					index++;
				};

				<c:forEach items="${list}" var="i">
				addRow({
					id : '${i.id}',
					dep : '${i.departmentName}',
					sl : '${i.secrecyLevel}',
					user : '${i.useDutyPerson}',
					sn : '${i.diskSn}',
					install : '${i.install3i1}'
				});
				</c:forEach>
				<c:if test="${empty list}">
				addRow();
				</c:if>

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
		</style>

	</head>
	<body>
		<div class="panel">
			<!-- 头部 -->
			<div class="panel_header">
				<!-- 标题 -->
				<div class="panel_title panel_titleListIco">
				涉密计算机及“三合一”安装情况统计表
				</div>
			</div>
			<!-- 内容 -->
			<div class="panel_content panel_content_table">
				<form id="form_save" class="form"
					action="<s:url action="saveing" namespace="/bmp/pucha/seccomputer3i1" includeParams="true"/>" method="post">
				<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
				<table border="0" style="width: 100%;">
					<tr>
						<td align="left" style="padding-left: 20px;">单位：（盖章）&nbsp;&nbsp; ${currentUser.organ.name}</td>
						<td align="center">填表人：&nbsp;&nbsp; ${currentUser.userInfo.name}</td>
						<td align="right" style="padding-right: 20px;">填报日期：&nbsp;&nbsp; ${date}</td>
					</tr>
				</table>
				<table class="content_table">
					<thead>
					<tr>
						<td width="20%" align="center">部门</td>
						<td width="15%" align="center">密级</td>
						<td width="20%" align="center">使用管理责任人</td>
						<td width="30%" align="center">硬盘序列号</td>
						<td width="10%" align="center">是否安装“三合一”</td>
						<td width="5%" align="center">操作</td>
					</tr>
					</thead>
					<tbody id="table_content_body">
					</tbody>
				</table>
				<!-- 右侧按钮 -->
				<div class="panel_btn_bar pop_button_bar" style="text-align: center;">
					<a id="btnAdd" href="###" class="pop_button"><span>新增</span></a>
					<a id="btnSave" href="###" class="pop_button"><span>保存</span></a>
				</div>
				</form>
			</div>
		</div>
	</body>
</html>