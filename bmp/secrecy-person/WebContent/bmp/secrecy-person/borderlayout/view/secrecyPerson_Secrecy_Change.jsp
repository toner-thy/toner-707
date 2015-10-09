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
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib prefix="secPersonlist" uri="http://www.cdthgk.com/tags/secrecyperson/list"%>
<%@ taglib prefix="nestedlist" uri="http://www.cdthgk.com/bmp/tags/nestedlist"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部门详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<style type="text/css" media="print">
			.no_print{display:none;}
		</style>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			//$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){

			});

			function preview(oper){
				if (oper < 10)
					{
					bdhtml=window.document.body.innerHTML;//获取当前页的html代码
					sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域
					eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域

					prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html

					prnhtmlprnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html

					window.document.body.innerHTML=prnhtmlprnhtml;
					window.print();
					window.document.body.innerHTML=bdhtml;
				} else {
					window.print();
				}
			}
		</script>

	</head>

	<body style="overflow: auto;overflow-x: hidden; ">
<!-- 公共头部 -->
		<div class="but_bar no_print">
			<div class="left no_print">
				<div class="pop_button_bar no_print">
				</div>
			</div>
			<div class="right no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>


		<!-- 保密要害部门panel 及 打印标签开始 -->
		<!--startprint1-->
			<!-- 这个CSS在IE6下影响了样式，导致滚动条没有出来 -->
<!-- 		<div class="printCss"> -->
		<div>
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						<%-- 保密要害部门【${keySection.department.departmentName }】详情 --%>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">
								原密级：
							</td>
							<td class="tbValue fl">
								一般
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								现密级：
							</td>
							<td class="tbValue fl">
								<dictionary:select tableCode="bmp" fieldCode="secrecy_level_section" id="keySection.secrecyLevel" name="keySection.secrecyLevel" optionValue="${keySection.secrecyLevel }" title="false" style="width:130px;"></dictionary:select>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								保密期限变更：
							</td>
							<td class="tbValue fl">
								<s:select
										list="#{'1':'延长','2':'缩短','3':'不变'}"
										cssStyle="width:100px;"
										theme="simple"
									>
									</s:select>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								变更时间：
							</td>
							<td class="tbValue fl">
								<input type="text" id="secrecyCommittee.setupDate" name="secrecyCommittee.setupDate" class="Wdate validate['length[20]'] w135" value="2013-06-13" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
							</td>
						</tr>
						<!-- <tr>
							<td class="tbLable fr">
								审批人：
							</td>
							<td class="tbValue fl">
								<input type="text" />
							</td>
						</tr> -->
						<tr>
							<td class="tbLable fr">
								变更原因：
							</td>
							<td class="tbValue fl">
								<textarea rows="5" cols="50"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align: center;" >
								<a class="pop_button" href="javascript:function(){return;}"><span>确定</span></a>
							</td>
						</tr>
					</table>

				</div>
			</div>
		</div>
	</body>
</html>