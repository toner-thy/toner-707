<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>要害部门——涉密人员详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
			});

			// 返 回
			function doBackToDetailKeyPart(id){
				window.location.href = "keySectionForKeyPartDetail_detail.jsp";
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick=""><span>打 印</span></a>
					<a class="pop_button" href="###" onclick="doBackToDetailKeyPart()"><span>返 回</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 涉密人员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部门【信息中心】 ─ 涉密人员【郭东】详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" width="100%">
						<tr>
							<td class="tbLable fr">
								单 位：
							</td>
							<td class="tbValue fl" colspan="3">
								眉山市林业局
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								姓 名：
							</td>
							<td class="tbValue fl">
								张三
							</td>
							<td class="tbLable fr">
								性 别：
							</td>
							<td class="tbValue fl">
								男
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								民 族：
							</td>
							<td class="tbValue fl">
								汉族
							</td>
							<td class="tbLable fr">
								出生年月：
							</td>
							<td class="tbValue fl">
								XXXX年XX月XX日
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								文化程度：
							</td>
							<td class="tbValue fl">
								大专
							</td>
							<td class="tbLable fr">
								身份证号：
							</td>
							<td class="tbValue fl">
								51245784595654213
							</td>
						</tr>
						<tr>
							<td class="fr">
								政治面貌：
							</td>
							<td class="fl">
								共产党员
							</td>
							<td class="fr">
								参加工作时间：
							</td>
							<td class="fl">
								XXXX年XX月XX日
							</td>
						</tr>
						<tr>
							<td class="fr">
								部 门：
							</td>
							<td class="fl">
								信息中心
							</td>
							<td class="fr">
								职 务：
							</td>
							<td class="fl">
								科员
							</td>
						</tr>
						<tr>
							<td class="fr">
								岗 位：
							</td>
							<td class="fl">
								处长
							</td>
							<td class="fr">
								编 制：
							</td>
							<td class="fl">
								行政
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								涉密等级：
							</td>
							<td class="tbValue fl">
								重要
							</td>
							<td class="fr">
								办公室电话：
							</td>
							<td class="fl">
								028-12354895
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								签订保密责任书时间：
							</td>
							<td class="tbValue fl">
								XXXX年XX月XX日
							</td>
							<td class="tbLable fr">
								手 机：
							</td>
							<td class="tbValue fl">
								135479852
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								取得上岗证书时间：
							</td>
							<td class="tbValue fl">
								XXXX年XX月XX日
							</td>
							<td class="tbLable fr">
								&nbsp;
							</td>
							<td class="tbValue fl">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								个人简历：
							</td>
							<td class="tbLable fl" colspan="3">
								<textarea style="width: 90%;padding: 5px;" rows="10" readonly="readonly">个人简历......</textarea>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								单位审查意见：
							</td>
							<td class="tbLable fl" colspan="3">
								<textarea style="width: 90%;padding: 5px;" rows="5" readonly="readonly">XXX同志经过保密培训，经涉密人员资格审查，符合涉密人员上岗要求，特此同意。XXXX单位</textarea>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 涉密人员panel结束 -->
		</div>
	</body>
</html>