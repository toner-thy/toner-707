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
		<title>新增产品工具</title>

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
						if (!confirm('新增成功，是否继续添加?')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck=new FormCheck('form_product_add',{
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

			function doSave()
			{
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
						新增产品工具
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<form id="form_product_add" class="form" action="product_save.action" method="post">
					<div class="panel_content panel_content_table">
						<table class="content_table">
							<tr>
								<td class="tbLable fr">产品工具名称：</td>
								<td class="tbValue fl">
									<input id="product.productName" name="product.productName" type="text" class="validate['required','length[100]']" value="" /> <span style="color:red;">*</span>
								</td>
								<td class="tbLable fr">通过日期：</td>
								<td class="tbValue fl">
									<input id="product.passTime" name="product.passTime" type="text" class="Wdate" onFocus="WdatePicker({isShowClear:false,readOnly:true});" value="" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">证书编号：</td>
								<td class="tbValue fl">
									<input id="product.certificateCode" name="product.certificateCode" type="text" value="" class="validate['length[50]']" />
								</td>
								<td class="tbLable fr">厂家电话：</td>
								<td class="tbValue fl">
									<input id="product.manufacturePhone" name="product.manufacturePhone" type="text" value="" class="validate['length[12]','phone']" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">厂家名称：</td>
								<td class="tbValue fl">
									<input id="product.manufacturerName" name="product.manufacturerName" type="text" value="" class="validate['length[100]']" />
								</td>
								<td class="tbLable fr">送测单位：</td>
								<td class="tbValue fl">
									<input id="product.inspectCenter" name="product.inspectCenter" type="text" value="" class="validate['length[100]']" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">厂家地址：</td>
								<td class="tbValue fl">
									<input id="product.manufacturerAddress" name="product.manufacturerAddress" type="text" value="" class="validate['length[100]']" />
								</td>
								<td class="tbLable fr">送测单位电话：</td>
								<td class="tbValue fl">
									<input id="product.inspectCenterPhone" name="product.inspectCenterPhone" type="text" value="" class="validate['length[12]','phone']" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">送测单位地址：</td>
								<td class="tbValue fl">
									<input id="product.inspectCenterAddress" name="product.inspectCenterAddress" type="text" value="" class="validate['length[100]']" />
								</td>
								<td class="tbLable fr">是否有效：</td>
								<td class="tbValue fl">
									<dictionary:select fieldCode="is_available" tableCode="bmp" id="product.isAvailable" name="product.isAvailable"  styleClass="validate['required']" title="true" titleText="请选择"></dictionary:select> <span style="color:red;">*</span>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">产品工具类型：</td>
								<td class="tbValue fl">
									<dictionary:select fieldCode="product" tableCode="bmp" id="product.productType"  styleClass="validate['required']" title="true" titleText="请选择" name="product.productType" style="width:200px"></dictionary:select><span style="color:red;">*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr" valign="middle" height="100px;">产品简介：</td>
								<td colspan="3" class="tbValue fl">
									<textarea class="validate['length[500]']" id="product.productDesc" name="product.productDesc" style="width:100%;height:100px;"></textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
							<input id="savebt" name="update" type="hidden" value="保 存" class="btn_23" onclick="doSave()" />
						</div>
					</div>
				</form>
			</div>
			<!-- 内容panel结束-->
		</div>
	</body>
</html>