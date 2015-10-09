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
		<title>要害部位——涉密人员编辑</title>

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
			function doBackToEditKeyPart(id){
				window.location.href = "${ctx}/bmp/demo/keyPart/keyPart_edit.jsp";
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick="doBackToEditKeyPart()"><span>保 存</span></a>
					<a class="pop_button" href="###" onclick="doBackToEditKeyPart()"><span>返 回</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密要害部位简介" ctx="${ctx}" icoPath="/bmp/demo/keyPart/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密要害部位简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密要害部位
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_key_part"> </cpc:tc>
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

			<!-- 涉密人员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部位【保密室】 ─ 编辑涉密人员
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
								<input id="" name="" value="刘禹锡"/>
							</td>
							<td class="tbLable fr">
								性 别：
							</td>
							<td class="tbValue fl">
								<select style="width:130px;">
									<option selected="selected">男</option>
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
									<option selected="selected">汉族</option>
									<option>藏族</option>
									<option>......</option>
								</select>
							</td>
							<td class="tbLable fr">
								出生年月：
							</td>
							<td class="tbValue fl">
								<input type="text" style="width: 130px;" class="Wdate validate['required']" value="1972-05-16" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								文化程度：
							</td>
							<td class="tbValue fl">
								<select style="width: 130px;">
									<option>大专</option>
									<option selected="selected">本科</option>
									<option>......</option>
								</select>
							</td>
							<td class="tbLable fr">
								身份证号：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="5131011972051643"/>
							</td>
						</tr>
						<tr>
							<td class="fr">
								政治面貌：
							</td>
							<td class="fl">
								<select style="width: 130px;">
									<option selected="selected">共产党员</option>
									<option>......</option>
								</select>
							</td>
							<td class="fr">
								参加工作时间：
							</td>
							<td class="fl">
								<input type="text" style="width: 130px;" class="Wdate validate['required']" value="2004-02-16" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							</td>
						</tr>
						<tr>
							<td class="fr">
								部 门：
							</td>
							<td class="fl">
								<select style="width: 130px;">
									<option selected="selected">技术处</option>
									<option>......</option>
								</select>
								<br/>
								开发说明：这里部门可以自由填写，因为这里的要害部位只有主管部门，而要害部位中的人员可以来自于其他部门。例如文印室是要害部位，它是挂靠在秘书处的，也就是说它的主管部门是秘书处，但是文印室的涉密人员可以是技术处的人员。
							</td>
							<td class="fr">
								职 务：
							</td>
							<td class="fl">
								<input id="" name="" value="处长"/>
							</td>
						</tr>
						<tr>
							<td class="fr">
								岗 位：
							</td>
							<td class="fl">
								<input id="" name="" value=""/>
							</td>
							<td class="fr">
								编 制：
							</td>
							<td class="fl">
								<select style="width: 130px;">
									<option selected="selected">行政</option>
									<option>事业</option>
									<option>工勤</option>
									<option>应聘</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								涉密等级：
							</td>
							<td class="tbValue fl">
								<select style="width: 130px;">
									<option selected="selected">一般</option>
									<option>重要</option>
									<option>核心</option>
								</select>
							</td>
							<td class="fr">
								办公室电话：
							</td>
							<td class="fl">
								<input id="" name="" value="028-8637620"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								签订保密责任书时间：
							</td>
							<td class="tbValue fl">
								<input type="text" style="width: 130px;" class="Wdate validate['required']" value="2010-05-10" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							</td>
							<td class="tbLable fr">
								手 机：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="13594837205"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								取得上岗证书时间：
							</td>
							<td class="tbValue fl">
								<input type="text" style="width: 130px;" class="Wdate validate['required']" value="2010-05-16" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
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
								<textarea style="width: 90%;padding: 5px;" rows="10">刘禹锡个人简历......</textarea><br/>
								提示：填写的内容包含参加工作时间、工作性质、个人经历等内容。
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								单位审查意见：
							</td>
							<td class="tbLable fl" colspan="3">
								<textarea style="width: 90%;padding: 5px;" rows="5">单位审查意见...</textarea><br/>
								提示：XXX同志经过保密培训，经涉密人员资格审查，符合涉密人员上岗要求，特此同意。XXXX单位
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 涉密人员panel结束 -->

		</div>
	</body>
</html>