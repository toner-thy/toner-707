<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑产品工具</title>

		<link href="${ctx}/platform/css/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/FCKeditor/fckeditor.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('编辑成功，是否编辑添加?')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck=new FormCheck('form_product_update',{
					display:{
						showErrors:1
					}
				});
			});
			});
			var needReload = ${needReload};
			var needReload2 = false;
			function backList(){
				window.location.href="<s:url action="product_list" includeParams="false"/>";
			}

			function doSave(){
				 if (formcheck.isFormValid(true)) {
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				 }
			}

		</script>

	</head>

	<body>
		<!-- 公共头部-->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="${ctx}/bmp/product/product_list.action"><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:TabUtil.refreshTab();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<!-- 内容panel开始-->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑工具
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<form id="form_product_update" class="form" action="product_update.action" method="post">
					<!-- 隐藏域 -->
					<input type="hidden" name="product.productId" value="${product.productId}">

					<div class="panel_content panel_content_table">
						<table class="content_table">
							<tr>
								<td class="tbLable fr">产品名称：</td>
								<td class="tbValue fl">
									<input name="product.productName" id="product.productName" type="text" class="validate['required','length[100]']" value="${product.productName}"> <span style="color:red;">*</span>
								</td>
								<td class="tbLable fr">证书编号：</td>
								<td class="tbValue fl">
									<input name="product.certificateCode" id="product.certificateCode" type="text" value="${product.certificateCode}" class="validate['length[500]']" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">厂家名称：</td>
								<td class="tbValue fl">
									<input name="product.manufacturerName" id="product.manufacturerName" type="text" value="${product.manufacturerName}" class="validate['length[100]']" />
								</td>
								<td class="tbLable fr">通过日期：</td>
								<td class="tbValue fl">
									<input name="product.passTime" id="product.passTime" type="text" class="Wdate" onFocus="WdatePicker({isShowClear:false,readOnly:true});" value="${product.passTime}" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">厂家地址：</td>
								<td class="tbValue fl">
									<input name="product.manufacturerAddress" id="product.manufacturerAddress" type="text" value="${product.manufacturerAddress}" class="validate['length[100]']" />
								</td>
								<td class="tbLable fr">厂家电话：</td>
								<td class="tbValue fl">
									<input name="product.manufacturePhone" id="product.manufacturePhone" type="text" value="${product.manufacturePhone}" class="validate['length[12]','phone']" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">送测单位地址：</td>
								<td class="tbValue fl">
									<input name="product.inspectCenterAddress" id="product.inspectCenterAddress" type="text" value="${product.inspectCenterAddress}" class="validate['length[100]']" />
								</td>
								<td class="tbLable fr">送测单位：</td>
								<td class="tbValue fl">
									<input name="product.inspectCenter" id="${product.inspectCenter}" type="text" value="${product.inspectCenter}" class="validate['length[100]']" />
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">送测单位电话：</td>
								<td class="tbValue fl">
									<input name="product.inspectCenterPhone" id="product.inspectCenterPhone" type="text" value="${product.inspectCenterPhone}" class="validate['length[20]','phone']" />
								</td>
								<td class="tbLable fr">是否有效：</td>
								<td class="tbValue fl">
									<dictionary:select fieldCode="is_available" tableCode="bmp" id="product.isAvailable" name="product.isAvailable"  optionValue="${product.isAvailable}"></dictionary:select> <span style="color:red;">*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">产品类型：</td>
								<td class="tbValue fl">
								<dictionary:select fieldCode="product" tableCode="bmp" id="product.productType"   name="product.productType"  optionValue="${product.productType}"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr" valign="middle" height="100px;" >产品简介：</td>
								<td colspan="3" class="tbValue fl">
									<textarea class="validate['length[500]']" name="product.productDesc" id="product.productDesc" style="width:100%;height: 100px;">${product.productDesc}</textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div class="fc">
							<input type="submit" id="sub" value="sub" style="display: none;" />
							<input id="savebt" name="update" type="hidden" value="保 存" class="btn_23" onclick="doSave()" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
TabUtil.closeTab = function(item) {
	var tab = window.top['content_top'].main.tp;
	if (!item) item = tab.getActiveItem();
	if(confirm("您确定要放弃正在新增的内容吗？")){
	 	tab.close(item);
	 }
};
</script>