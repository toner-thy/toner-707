<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>新增办公室(保密局)人员</title>

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

			function doBackToSecrecyBureauEdit(){
				window.location.href = "${ctx}/bmp/demo/secrecyOrgan/secrecyBureau/secrecyBureau_edit.jsp";
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick="doBackToSecrecyBureauEdit();"><span>保 存</span></a>
					<a class="pop_button" href="###" onclick="doBackToSecrecyBureauEdit();"><span>返 回</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密办(保密局)简介" ctx="${ctx}" icoPath="/bmp/demo/secrecyOrgan/secrecyBureau/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密办(保密局)简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密办(保密局)
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ_secrecy_bureau"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

					<!-- 联系方式 -->
					<div class="cpMsgContactInfoTitle">
						业务指导
					</div>
					<div class="cpMsgContactInfoContext">
						联系人：四川省国家保密局督察处 XXX 联系电话：028-85229437
					</div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 新增办公室(保密局)人员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						新增办公室(保密局)人员
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" width="100%">
						<tr>
							<td class="tbLable fr">
								单位名称
							</td>
							<td class="tbValue fl" colspan="3">
								<b>中共眉山市委保密委员会办公室（眉山市国家保密局）</b>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								姓 名：
							</td>
							<td class="tbValue fl">
								<input type="text" value="">
							</td>
							<td class="tbLable fr">
								性 别：
							</td>
							<td class="tbValue fl">
								<select style="width: 130px;">
									<option>男</option>
									<option>女</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								民 族：
							</td>
							<td class="tbValue fl">
								<select style="width: 130px;">
									<option>汉族</option>
									<option>....</option>
								</select>
							</td>
							<td class="tbLable fr">
								出生年月：
							</td>
							<td class="tbValue fl">
								<input type="text" style="width: 130px;" class="Wdate validate['required']" value="" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								文化程度：
							</td>
							<td class="tbValue fl">
								<select style="width: 130px;">
									<option>本科</option>
									<option>....</option>
								</select>
							</td>
							<td class="tbLable fr">
								身份证号：
							</td>
							<td class="tbValue fl">
								<input type="text" value="">
							</td>
						</tr>
						<tr>
							<td class="fr">
								政治面貌：
							</td>
							<td class="fl">
								<select style="width: 130px;">
									<option>共产党员</option>
									<option>....</option>
								</select>(注意，这些选项需要仔细斟酌，韩处曾提出异议，说含义不清)
							</td>
							<td class="fr">
								参见工作时间：
							</td>
							<td class="fl">
								<input type="text" style="width: 130px;" class="Wdate validate['required']" value="1986-03-16" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							</td>
						</tr>
						<tr>
							<td class="fr">
								部 门：
							</td>
							<td class="fl">
								<input type="text" value="">
							</td>
							<td class="fr">
								职 务：
							</td>
							<td class="fl">
								<input type="text" value="">
							</td>
						</tr>
						<tr>
							<td class="fr">
								岗 位：
							</td>
							<td class="fl">
								<input type="text" value="">
							</td>
							<td class="fr">
								涉密等级：
							</td>
							<td class="fl">
								<select style="width: 130px;">
									<option>一般</option>
									<option>重要</option>
									<option>核心</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="fr">
								办公室电话：
							</td>
							<td class="fl">
								<input type="text" value="">
							</td>
							<td class="tbLable fr">
								手 机：
							</td>
							<td class="tbValue fl">
								<input type="text" value="">
							</td>
						</tr>
						<tr>
							<td class="fr">
								编 制：
							</td>
							<td class="fl">
								<select style="width: 130px;">
									<option>行政</option>
									<option>事业</option>
									<option>工勤</option>
									<option>应聘</option>
								</select>
							</td>
							<td class="tbLable fr">
								&nbsp;
							</td>
							<td class="tbValue fl">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td class="tbLable fr" valign="top">
								个人简历：
							</td>
							<td class="tbLable fl" colspan="3" valign="top" height="50">
								<textarea style="width: 90%;padding: 5px;" rows="5"></textarea>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 新增办公室(保密局)人员panel结束 -->

		</div>
	</body>
</html>