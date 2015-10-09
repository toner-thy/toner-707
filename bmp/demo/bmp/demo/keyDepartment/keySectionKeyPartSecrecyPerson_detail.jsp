<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密人员详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<script type="text/javascript">
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/public_js/utils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){

				});
			});

			$ENV.loader.loadJavaScript("${ctx}/resources/js/DynamicTextarea/DynamicTextarea.js",function(){
				$ENV.onDomReady(function(){
					$('secrecyPerson.resume').setStyle('width', $('secrecyPerson.resume').getParent().getSize().x);
			        new DynamicTextarea('secrecyPerson.resume', {
			            minRows: 10
			        });
				});
			});
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar no_print">
			<div class="left no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content" style="overflow-x: auto;">
			<!-- 涉密人员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						保密要害部位【保密室】 ─ 涉密人员详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" width="100%">
						<tr>
							<td class="tbLable fr">单 位：</td>
							<td class="tbValue fl" colspan="3">
								<div style="margin-left: 8px">
									眉山市林业局
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">姓 名：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									刘云山
								</div>
							</td>
							<td class="tbLable fr">性 别：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									男
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">民 族：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									汉
								 </div>
							</td>
							<td class="tbLable fr">出生年月：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									1973年11月3号
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">文化程度：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									中学
								</div>
							</td>
							<td class="tbLable fr">身份证号：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									51050212313131313
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">政治面貌：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									中共党员
								</div>
							</td>
							<td class="tbLable fr">参加工作时间：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									2001年6月21日
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">部 门：</td>
							<td class="tbValue fl">
								信息中心
							</td>
							<td class="tbLable fr">职 务：</td>
							<td class="tbValue fl">
								处长
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">岗 位：</td>
							<td class="tbValue fl">
								<div style="margin-left: 8px">
									信息中心处长
								</div>
							</td>
							<td class="tbLable fr">编 制：</td>
							<td class="tbValue fl">
								行政
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">涉密等级：</td>
							<td class="tbValue fl">
								重要
							</td>
							<td class="tbLable fr">办公室电话：</td>
							<td class="tbValue fl">
								028-43141414
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">签订保密责任书时间：</td>
							<td class="tbValue fl">
								2011年8月23号
							</td>
							<td class="tbLable fr">手 机：</td>
							<td class="tbValue fl">
								123132131313
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">取得上岗证书时间：</td>
							<td class="tbValue fl">
								2011年8月25号
							</td>
							<td class="tbLable fr">
								&nbsp;
							</td>
							<td class="tbValue fl">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td class="tbLable fr" valign="top">个人简历：</td>
							<td class="tbLable fl" colspan="3">
								暂未填写
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">单位审查意见：</td>
							<td class="tbLable fl" colspan="3">
								同意
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 涉密人员panel结束 -->

		</div>
	</body>
</html>