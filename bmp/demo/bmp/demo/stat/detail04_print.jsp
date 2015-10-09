<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部位登记表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
		</script>

  		<!--print 这个属性可以在打印时有效-->
		<style type="text/css" media="print">
			.no_print{display:none;}
			.PageNext{page-break-after: always;}
		</style>

	</head>

	<body>
		<!-- 内容panel开始 -->
		<div class="print_button_bar no_print">
			<div class="left">
				<div class="print_pop_button no_print">
					<a class="print_pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right no_print">
				<div class="print_pop_button no_print">
					<a class="print_pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="print_panel" style="border: 0px;">
			<div class="print_panel_content" style="background-color: #fff;">
				<table align="center" style="width: 100%;" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td class="ftsize28 ft_kt" style="text-align:center;" height="30">
							保密要害部位登记表
						</td>
					</tr>
				</table>
				<table align="center" class="print_content_formTable" style="width: 100%; border: 1px solid #000;" cellspacing="0" cellpadding="1" bordercolor="#000000" border="1">
					<tr style="text-align:center;">
						<td>保密要害部位名称</td>
						<td>主管部门</td>
						<td>涉密等级</td>
						<td>负责人</td>
						<td>联系电话</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
					<tr style="text-align:center;">
						<td>
							信息中心
						</td>
						<td>
							技术处
						</td>
						<td>
							重要
						</td>
						<td>
							张三
						</td>
						<td>
							028-8729336
						</td>
					</tr>
				</table>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>