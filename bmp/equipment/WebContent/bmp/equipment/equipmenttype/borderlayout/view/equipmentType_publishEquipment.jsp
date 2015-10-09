<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>设备分类下发</title>

	   <!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/mootools-core.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/mootools-more.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/formcheck/formcheck.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/utils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/TabUtils.js" type="text/javascript"></script>
		<script src="${ctx}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/bmp/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />

		<s:actionmessage theme="messages"/>

		<script type="text/javascript">
			window.addEvent('domready', function(){
				Util.selectOrgansByCurrentDistrict("organNames","organNames","organIds");
				new FormCheck('form_product_add',{
					display:{
						showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
					},
					trimValue: true
				});
			});

			function doBack(){
				window.location.href="equipmentType_list.action";
			}

			function doSave()
		    {
		    	$('sub').click();
			    document.getElementById("savebt").disabled='true';
				window.setTimeout("document.getElementById('savebt').disabled=false;",4000);
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<a class="pop_button pop_button_refresh" href="###" onclick="doBack();"><span>返回列表</span></a>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密设备发布
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="form_product_add" action="<s:url action="equipmentType_publishingEquipment" />">
						<table id="edit-0" align="center" class="edit_table">
							<tr>
								<td height="20px;" align="right">
									选择发布单位：
								</td>
								<td width="80%"  >
									<textarea cols="61" class="textarea validate['required']" rows="80" style="width: 400px; height: 80px;"
									name="organNames" title="点击文本框选择单位" readonly="readonly" >${organNames }</textarea>
									<input type="hidden" id="organIds" name="organIds" value="${organIds }" /><span style="color:red;">*</span>
								</td>
							</tr>
						</table>
						<div align="center">
							<input type="submit" id="sub" value="sub" class="btn_23" style="display: none;"/>
							<input type="hidden" name="EquipmentIds" value="${EquipmentIds}" />
							<input type="hidden" value="发 布"  class="btn_23" onclick="doSave()" />
							<input name="update" id="savebt" type="hidden" value="返 回"  class="btn_23" onclick="doBack();"/>

							<a class="pop_button pop_button_refresh" href="###" onclick="doSave();"><span>发 布</span></a>
						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束-->
		</div>
	</body>
</html>
