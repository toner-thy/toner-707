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
		<title>离退休档案详情</title>

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){

				});
			});
		</script>
		<style type="text/css" media="print">
			.no_print{display:none;}
		</style>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar no_print">
			<div class="left">
				<div class="pop_button_bar">
				<a class="print_pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						产品工具详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
						<table class="content_table">
						<tr>
							<td class="tbLable fr">产品名称：</td>
							<td class="tbvalue fl" colspan="3">
								${product.productName}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr" width="20%">证书类型：</td>
							<td class="tbvalue fl" width="30%">
								<dictionary:text fieldCode="product" tableCode="bmp" optionValue="${product.productType}"  ></dictionary:text>
							</td>
							<td class="tbLable fr">通过日期：</td>
							<td class="tbvalue fl">
								<c:if test="${product.passTime==null}">
									暂无
								</c:if>
								<c:if test="${product.passTime!=null}">
									<div style="font-family: Arial, "Times New Roman" !important;">
										<s:date name="#attr.product.passTime" format="yyyy年MM月dd日"/>
									</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">证书编号：</td>
							<td class="tbvalue fl">
								<c:if test="${product.certificateCode==''}">
									暂未填写
								</c:if>
								<c:if test="${product.certificateCode!=''}">
									<div style="font-family: Arial, "Times New Roman" !important;">
										${product.certificateCode}
									</div>
								</c:if>
							</td>
							<td class="tbLable fr">是否有效：</td>
							<td class="tbvalue fl">
								<dictionary:text fieldCode="is_available" optionValue="${product.isAvailable}" tableCode="bmp"></dictionary:text>
							</td>
						</tr>

						<tr>
							<td class="tbLable fr">厂家名称：</td>
							<td class="tbvalue fl">
								<c:if test="${product.manufacturerName==''}">
									暂未填写
								</c:if>
								<c:if test="${product.manufacturerName!=''}">
									${product.manufacturerName}
								</c:if>
							</td>
							<td class="tbLable fr">厂家电话：</td>
							<td class="tbvalue fl">
								<c:if test="${product.manufacturePhone==''}">
									暂未填写
								</c:if>
								<c:if test="${product.manufacturePhone!=''}">
									<div style="font-family: Arial, "Times New Roman" !important;">
										${product.manufacturePhone}
									</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">厂家地址：</td>
							<td class="tbvalue fl">
								<c:if test="${product.manufacturerAddress==''}">
									暂未填写
								</c:if>
								<c:if test="${product.manufacturerAddress!=''}">
									${product.manufacturerAddress}
								</c:if>
							</td>
							<td class="tbLable fr">送测单位：</td>
							<td class="tbvalue fl">
								<c:if test="${product.inspectCenter==''}">
									暂未填写
								</c:if>
								<c:if test="${product.inspectCenter!=''}">
									${product.inspectCenter}
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">送测单位地址：</td>
							<td class="tbvalue fl">
								<c:if test="${product.inspectCenterAddress==''}">
									暂未填写
								</c:if>
								<c:if test="${product.inspectCenterAddress!=''}">
									${product.inspectCenterAddress}
								</c:if>
							</td>
							<td class="tbLable fr">送测单位电话：</td>
							<td class="tbvalue fl">
								<c:if test="${product.inspectCenterPhone==''}">
									暂未填写
								</c:if>
								<c:if test="${product.inspectCenterPhone!=''}">
									<div style="font-family: Arial, "Times New Roman" !important;">
										${product.inspectCenterPhone}
									</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">创建人：</td>
							<td class="tbvalue fl">
								${product.createPerson.name}
							</td>
							<td class="tbLable fr">创建时间：</td>
							<td class="tbvalue fl">
								<c:if test="${product.createTime==null}">
									暂无
								</c:if>
								<c:if test="${product.createTime!=null}">
									<div style="font-family: Arial, "Times New Roman" !important;">
										<s:date name="#attr.product.createTime" format="yyyy年MM月dd日"/>
									</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">创建单位：</td>
							<td class="tbvalue fl">
								${product.organ.organName}
							</td>
							<td class="tbLable fr">创建部门：</td>
							<td class="tbvalue fl">
								${product.department.departmentName}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">修改人：</td>
							<td class="tbvalue fl">
								${product.modifyPerson.name}
							</td>
							<td class="tbLable fr">修改时间：</td>
							<td class="tbvalue fl">
								<c:if test="${product.modifyTime==null}">
									暂无
								</c:if>
								<c:if test="${product.modifyTime!=null}">
									<div style="font-family: Arial, "Times New Roman" !important;">
										<s:date name="#attr.product.modifyTime" format="yyyy年MM月dd日"/>
									</div>
								</c:if>
							</td>
						<tr>
							<td class="tbLable fr">产品简介：</td>
							<td class="tbvalue fl" height="100px;" colspan="3" valign="top">
								<c:if test="${product.productDesc==''}">
									暂未填写
								</c:if>
								<c:if test="${product.productDesc!=''}">
									${product.productDesc}
								</c:if>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>