<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>导入涉密人员</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />


		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-v1.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js", function(){
				$ENV.onDomReady(function(){
					var needReload = "${needReload}";
					if (needReload=="true") {
						if (!confirm('导入成功，是否继续导入？')){
							location.href="<s:url action="secrecyPerson_list" namespace="/bmp/secrecyperson"/>";
						}
					};
				});
			});
			function doImport(){
				var str=$('file').value;
				if(str==""){
					alert("请选择需要导入的文件。");
				} else {
					$('save').click();
					$('import').style.display='none';
					$('import_hidden').style.display='';
					window.setTimeout("$('import').style.display=''",8000);
					window.setTimeout("$('import_hidden').style.display='none'",8000);
				}
			}
		</script>
	</head>

	<body>
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
				<a class="pop_button" href="${ctx}/bmp/product/product_list.action"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_close" href="<s:url action="secrecyPerson_list" namespace="/bmp/secrecyperson"/>"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div class="body_content">
			<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleListIco">安全保密产品导入</div>
					<!-- 右侧按钮 -->
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form action="<s:url action="productExcel_saveImportProduct" namespace="/bmp/product"/>"
				   		enctype="multipart/form-data" method="post">
				   		<table id="edit-0" align="center" class="edit_table">
							<tr height="36px">
								<td align="right">请选择导入文件：</td>
								<td >
						   		<input type="file" name="file" id="file"/>
						   		<input type="submit" value="导入中.." name="save" style="display: none" id="save"/>
						   		<a href="###" onclick="javascript:void();" class="pop_button" name="import_hidden" id="import_hidden" style="display: none"><span>导入中...</span></a>
						   		<a href="###" onclick="javascript:doImport();" class="pop_button" name="import" id="import"><span>导入</span></a>
						   		</td>
						   	</tr>
						   	<tr>
						   		<td colspan="10">
						   		<a href="${ctx}/bmp/product/borderlayout/view/ProductExcel.xls" style="color: green;">模板下载</a>
						   		</td>
						   	</tr>
						</table>
				   	</form>
				   	 <table  style="margin-left: 50px;border: 0px;">
				   		 <tr height="36px">
							<td >
						   	 温馨提示：
				   			</td>
				   		</tr>
						<tr height="36px">
							<td >
						   	1.请下载模板，再根据模板填写相应数据。
						    </td>
						</tr>
						<tr height="36px">
							<td >
						   	2.模板中<b>列名为红色的项目</b>为必须填写的字段。
						    </td>
						</tr>
				   	</table>
				</div>
				<div class="panel_content panel_content_table">
					<div style="margin: 13px;overflow: auto;">
					${errorMsg }
					</div>
				</div>
			</div>
		</div>
  </body>
</html>
