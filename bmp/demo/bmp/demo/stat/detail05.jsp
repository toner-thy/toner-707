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
		<title>机关涉密人员列表</title>

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

			// 返回单位列表
			function backToStat(id){
				window.location.href = "stat_main.jsp";
			}

			// 打印
			function doPrint(){
				$ENV.dialog.open({
					url : '${ctx}/bmp/demo/stat/detail05_print.jsp',
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '机关涉密人员打印'
				});
			}

		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick="backToStat()"><span>返 回</span></a>
					<a class="pop_button print_pop_button" href="###" onclick="doPrint()"><span>打 印</span></a>
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
			<cp:start defaultTitle="涉密人员简介" ctx="${ctx}" icoPath="/bmp/demo/secrecyPerson/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','涉密人员简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密人员
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_person"> </cpc:tc>
					</div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						机关涉密人员列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable">
						<table id="" border="0" cellspacing="0" cellpadding="0" class="tableRegion" width="100%">
							<thead>
								<tr>
									<td class="tableHeader" width="30%">姓 名</td>
									<td class="tableHeader" width="15%">涉密等级</td>
									<td class="tableHeader" width="15%">部 门</td>
									<td class="tableHeader" width="15%">职 务</td>
									<td class="tableHeader" width="15%">办公室电话</td>
									<td class="tableHeader" width="10">详 情</td>
								</tr>
							</thead>
							<tbody class="tableBody">
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td>
										杨一民
									</td>
									<td>
										重要
									</td>
									<td>
										信息中心
									</td>
									<td>
										处长
									</td>
									<td>
										028-2984235
									</td>
									<td>
										<a href="###" onclick="doDetail('')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="even" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td>
										刘桂英
									</td>
									<td>
										一般
									</td>
									<td>
										秘书处
									</td>
									<td>
										处长
									</td>
									<td>
										028-2984235
									</td>
									<td>
										<a href="###" onclick="doDetail('')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
									<td>
										袁军
									</td>
									<td>
										核心
									</td>
									<td>
										生产一科
									</td>
									<td>
										科长
									</td>
									<td>
										028-2984235
									</td>
									<td>
										<a href="###" onclick="doDetail('')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
							</tbody>
						</table>

						<table class="page_control">
							<tr style="padding: 0px;">
								<td colspan="7">
									<table border="0" cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td class="compactToolbar" align="right">
												<table border="0" cellpadding="1" cellspacing="2">
													<tr>
														<td class="statusBar" align="right">共找到<font color=red>3</font>条记录,分<font color=red>1</font>页显示&#160;</td>
														<td><span>第一页</span></td>
														<td><span>上一页</span></td>
														<td><a href='#' onclick="document.forms.secrecyLawCodeList.secrecyLawCodeList_p.value='2';document.forms.secrecyLawCodeList.setAttribute('action','/bmp/secrecylawcode_list.action');document.forms.secrecyLawCodeList.setAttribute('method','post');document.forms.secrecyLawCodeList.submit()"><span title="下一页">下一页</span></a></td>
														<td><a href='#' onclick="document.forms.secrecyLawCodeList.secrecyLawCodeList_p.value='25';document.forms.secrecyLawCodeList.setAttribute('action','/bmp/secrecylawcode_list.action');document.forms.secrecyLawCodeList.setAttribute('method','post');document.forms.secrecyLawCodeList.submit()"><span title="最后页">最后页</span></a></td>
														<td><img src="${ctx}/platform/theme/default/images/table/separator.gif" style="border:0" alt="Separator" /></td>
														<td>
															每页显示
															<select name="secrecyLawCodeList_rd" class="page_control" onchange="javascript:document.forms.secrecyLawCodeList.secrecyLawCodeList_crd.value=this.options[this.selectedIndex].value;document.forms.secrecyLawCodeList.secrecyLawCodeList_p.value='1';document.forms.secrecyLawCodeList.setAttribute('action','/bmp/secrecylawcode_list.action');document.forms.secrecyLawCodeList.setAttribute('method','post');document.forms.secrecyLawCodeList.submit()">
																<option value="10" selected="selected">10</option><option value="30">30</option><option value="100">100</option>
															</select>
															条
															</td>
														<td><img src="${ctx}/platform/theme/default/images/table/separator.gif" style="border:0" alt="Separator" /></td>
														<td class="statusBar">转到<input type="text" name="secrecyLawCodeList_cpn" class="page_control" value="1" size="1" onkeypress="javascript111:if(event.keyCode==13){var re = /^[0-9]*[1-9][0-9]*$/;if(this.value < 1 || this.value> 25|| !re.test(this.value)){alert('页码不正确，请重新输入!');return false;}document.forms.secrecyLawCodeList.secrecyLawCodeList_p.value=this.value;document.forms.secrecyLawCodeList.setAttribute('action','/bmp/secrecylawcode_list.action');document.forms.secrecyLawCodeList.setAttribute('method','post');document.forms.secrecyLawCodeList.submit()}">页</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>