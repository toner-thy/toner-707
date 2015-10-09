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

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑保密技术设备类型</title>
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
					formcheck=new FormCheck('edit_form',{
						display:{
							showErrors:1
						},
						trimValue: true
					});
				});
			});
			var needReload2 = false;

			// 刷新页面
			<c:if test="${flashPage}">
				window.parent.location.reload();
				<%
					session.removeAttribute("flashPage");
				%>
			</c:if>

			// 新增类型
			function doAddCategory() {
				 window.location.href = "${ctx}/bmp/secrecyEquipmentCategory/secrecyEquipmentCategory_add.action?secrecyEquipmentCategory.secrecyEquipmentCategoryId=${secrecyEquipmentCategory.secrecyEquipmentCategoryId}"+'&t_date=' + new Date().getTime();
			}

			// 编辑类型
			function doEditCategory() {
				if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",3000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",3000);
				}
			}

			// 删除类型
			function doDelCategory() {
				if(window.confirm("确定删除类型【${secrecyEquipmentCategory.name}】吗？请确保该设备类型下没有设备信息。")){
					window.location.href = "${ctx}/bmp/secrecyEquipmentCategory/secrecyEquipmentCategory_delete.action?secrecyEquipmentCategory.secrecyEquipmentCategoryId=${secrecyEquipmentCategory.secrecyEquipmentCategoryId}"+'&t_date=' + new Date().getTime();
				}
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<s:if test="#attr.secrecyEquipmentCategory.name!='分类'">
										<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
										<a id="addCategory" name="addCategory" class="pop_button" href="javascript:doAddCategory()"><span>新 增</span></a>
										<a id="sbm_button" name="sub" class="pop_button" href="javascript:doEditCategory()"><span>更 新</span></a>
										<a id="delCategory" name="delCategory" class="pop_button" href="javascript:doDelCategory()"><span>删 除</span></a>
					</s:if>
					<s:else>
										<a id="addCategory" name="addCategory" class="pop_button" href="javascript:doAddCategory()"><span>新 增</span></a>
					</s:else>
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

			<!-- 内容panel 1开始-->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<s:if test="#attr.secrecyEquipmentCategory.secrecyEquipmentCategoryId!='1'">
							类型 【${secrecyEquipmentCategory.name}】编辑
						</s:if>
						<s:else>
							【${secrecyEquipmentCategory.name}】
						</s:else>
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form action="<s:url namespace='/bmp/secrecyEquipmentCategory' action='secrecyEquipmentCategory_editing.action' includeParams='true'/>" method="post" id="edit_form" enctype="multipart/form-data">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyEquipmentCategory.secrecyEquipmentCategoryId" value="${secrecyEquipmentCategory.secrecyEquipmentCategoryId}"/>
						<input type="submit" id="sub" value="sub" style="display: none;" />
						<table align="center" class="content_table">
							<s:if test="#attr.secrecyEquipmentCategory.name!='类型'">
								<tr class="odd">
									<td style="text-align:right;">类型名称：</td>
									<td style="text-align:left;">
										<input type="text" name="secrecyEquipmentCategory.name" id="secrecyEquipmentCategory.name" class="validate['required','specialFilter','length[20]']" value="${secrecyEquipmentCategory.name}" size="50"/>&nbsp;&nbsp;<font color="red">*</font>
									</td>
								</tr>
								<tr class="odd">
									<td style="text-align:right;">类型描述：</td>
									<td colspan="3" style="text-align:left;padding: 5px;" valign="top">
										<textarea name="secrecyEquipmentCategory.description" id="secrecyEquipmentCategory.description" style="width: 90%; height:75px;" class="validate['length[300]']">${secrecyEquipmentCategory.description}</textarea>
									</td>
								</tr>
							</s:if>
							<s:if test="#attr.secrecyEquipmentCategory.name=='类型'">
								<tr class="odd">
									<td style="text-align:right;">类型名称：</td>
									<td style="text-align:left;">
										${secrecyEquipmentCategory.name}
									</td>
								</tr>
								<tr class="odd">
									<td style="text-align:right;">类型描述：</td>
									<td colspan="3" style="text-align:left;width: 90%;height: 75px;" valign="top">
										${secrecyEquipmentCategory.description}
									</td>
								</tr>
							</s:if>
						</table>
					</form>
				</div>
			</div>
			<!-- 内容panel 1结束-->
		</div>
	</body>
</html>