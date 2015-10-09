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
		<title>保密办(保密局)</title>

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

			// 详 情
			function doDetail(id){
				window.location.href = "${ctx}/bmp/demo/secrecyOrgan/secrecyBureau/secrecyBureau_person_detail.jsp";
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="secrecyBureau_add.jsp" onclick=""><span>新 增</span></a>
					<a class="pop_button" href="secrecyBureau_edit.jsp" onclick=""><span>编 辑</span></a>
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

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密办(保密局)
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
								主任(局长)：
							</td>
							<td class="tbValue fl">
								王海星
							</td>
							<td class="tbLable fr">
								办公室：
							</td>
							<td class="tbValue fl">
								综合科
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								负责人：
							</td>
							<td class="tbValue fl">
								刘山平
							</td>
							<td class="tbLable fr">
								联系电话：
							</td>
							<td class="tbValue fl">
								028-2334623
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								传 真：
							</td>
							<td class="tbValue fl">
								028-2334623
							</td>
							<td class="tbLable fr">
								邮 编：
							</td>
							<td class="tbValue fl">
								6234040
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								地 址：
							</td>
							<td class="tbValue fl">
								眉山市国强路23号
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
								职责与成员分工：
							</td>
							<td class="tbValue fl" colspan="5">
								......
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->

			<!-- 办公室(保密局)成员列表panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						办公室(保密局)成员列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id="" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%" >
							<thead>
								<tr>
									<td class="tableHeader" width="25%" >部 门</td>
									<td class="tableHeader" width="25%" >职 务</td>
									<td class="tableHeader" width="15%" >姓 名</td>
									<td class="tableHeader" width="15%" >办公室电话</td>
									<td class="tableHeader" width="15%" >备 注</td>
									<td class="tableHeader" width="15%" >详 情</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
									<td>
										保密办(保密局)
									</td>
									<td>
										主任(局长)
									</td>
									<td>
										刘海涛
									</td>
									<td>
										048-8473623
									</td>
									<td width="15%" >

									</td>
									<td width="15%" >
										<a href="###" onclick="doDetail('id');">详情</a>
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
									<td>
										保密办(保密局)
									</td>
									<td>
										副主任(副局长)
									</td>
									<td>
										张玉宁
									</td>
									<td>
										048-8473623
									</td>
									<td width="15%" >

									</td>
									<td width="15%" >
										<a href="###" onclick="doDetail('id');">详情</a>
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
									<td>
										技术处
									</td>
									<td>
										处长
									</td>
									<td>
										马东
									</td>
									<td>
										048-8473623
									</td>
									<td width="15%" >

									</td>
									<td width="15%" >
										<a href="###" onclick="doDetail('id');">详情</a>
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
									<td>
										技术处
									</td>
									<td>
										科员
									</td>
									<td>
										于思倩
									</td>
									<td>
										048-8473623
									</td>
									<td width="15%" >

									</td>
									<td width="15%" >
										<a href="###" onclick="doDetail('id');">详情</a>
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
									<td>
										秘书处
									</td>
									<td>
										处长
									</td>
									<td>
										魏百新
									</td>
									<td>
										048-8473623
									</td>
									<td width="15%" >

									</td>
									<td width="15%" >
										<a href="###" onclick="doDetail('id');">详情</a>
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
									<td>
										秘书处
									</td>
									<td>
										科员
									</td>
									<td>
										刘成平
									</td>
									<td>
										048-8473623
									</td>
									<td width="15%" >

									</td>
									<td width="15%" >
										<a href="###" onclick="doDetail('id');">详情</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- 办公室(保密局)成员列表panel结束 -->

		</div>
	</body>
</html>