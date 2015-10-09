<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/district" prefix="dis"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密计算机列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

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
			// 查看详情
			function doDetail(id){
				$ENV.dialog.open({
					title : '涉密计算机详情',
					url : '${ctx}/bmp/secrecycomputer/secrecyComputer_detail.action?secrecyComputer.secrecycomputerId='+id+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
			}
		</script>
	</head>

	<body>
		<c:if test="${not empty zhtj and zhtj eq '1' }">
			<div class="button_bar">
				<div class="left">
					<div class="pop_button_bar">
						<a class="pop_button" href="${ctx}/platform/stat/statFramework_organDetail.action?organId=${organ.organId}" id="dataflagfanhui" ><span>返回</span></a>
					</div>
				</div>
				<div class="right"></div>
			</div>
		</c:if>
		<!-- 公共头部 -->
		<div class="body_content" >
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密计算机简介" ctx="${ctx}" icoPath="/bmp/secrecy-system/secrecy-computer/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','涉密计算机简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','涉密计算机查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密计算机
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_computer"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form action="<s:url action="secrecyComputer_list.action" namespace="/bmp/secrecycomputer"/>" method="post" id="queryForm" theme="simple">
						<input type="hidden" id="checkLower" name="checkLower" value="${checkLower}" />
						<input type="hidden" id="fromQuery" name="fromQuery" value="${fromQuery}" />
						<input type="hidden" id="district.districtCode" name="district.districtCode" value="${district.districtCode}" />
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">责任人：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyComputer.dutyPerson.name" name="secrecyComputer.dutyPerson.name" value="${secrecyComputer.dutyPerson.name}" />
								</td>
								<td class="tbLable fr">机型：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="computer_type" id="secrecyComputer.computerType" name="secrecyComputer.computerType"
									 title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyComputer.computerType}"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">密级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" id="secrecyComputer.secrecyLevel" name="secrecyComputer.secrecyLevel"
									 title="true" titleText="请选择" style="width: 130px;" optionValue="${secrecyComputer.secrecyLevel}"></dictionary:select>
								</td>
								<td class="tbLable fr">是否属于要害部门、部位：</td>
								<td class="tbValue fl">
									<s:select list="#{'1':'是','0':'否'}"
									style="width:135px;"
									theme="simple"
									name="secrecyComputer.isbelongKeydepartment"
									headerKey=""
									headerValue="请选择"
									>
									</s:select>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('queryForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('queryForm').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<c:if test="${fromQuery eq '0'}">
							涉密计算机列表
						</c:if>
						<c:if test="${fromQuery eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${checkLower ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 涉密计算机列表
							</c:if>
							<c:if test="${checkLower eq '1'}">
								${district.districtName} - 涉密计算机列表
							</c:if>
						</c:if>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyComputerList.size>0">
						<ec:table items="secrecyComputerList" var="secrecyComputer" tableId="secrecyComputerList" border="0"
							action="${ctx}/bmp/secrecycomputer/secrecyComputer_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secrecycomputerId" alias="secrecycomputerId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="dutyPerson.name" title="责任人" cell="text" alias="size=3" width="10%"/>
								<ec:column property="null" title="密级 " width="6%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyComputer.secrecyLevel }"></dictionary:text>
									<input type="hidden" id="${secrecyComputer.secrecycomputerId}_isNet" name="${secrecyComputer.secrecycomputerId}_isNet" value="${secrecyComputer.isNetTerminal}"/>
								</ec:column>
								<ec:column property="null" title="机型 " width="6%">
									<dictionary:text tableCode="bmp" fieldCode="computer_type" optionValue="${secrecyComputer.computerType }"></dictionary:text>
								</ec:column>
								<ec:column property="computerNo" title="编号" cell="text" alias="size=20" width="10%"/>
								<ec:column property="diskSeq" title="硬盘序列号" cell="text" alias="size=20" width="15%"/>
								<ec:column property="null" title="是否属于</br>要害部门、部位 "  width="10%">
									<dictionary:text tableCode="bmp" fieldCode="is_section_part" optionValue="${secrecyComputer.isbelongKeydepartment}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="是否安装保密技术<br/>防护专用系统（三合一）" width="10%">
									<c:if test="${secrecyComputer.isFanghu eq 1 }">是</c:if>
									<c:if test="${secrecyComputer.isFanghu eq 0 }">否</c:if>
								</ec:column>
								<ec:column property="null" title="是否纳入<br/>违规外联监控系统" width="15%">
									<c:if test="${secrecyComputer.isWailian eq 1 }">是</c:if>
									<c:if test="${secrecyComputer.isWailian eq 0 }">否</c:if>
								</ec:column>
								<ec:column property="null"  width="5%" title="详 情">
									<a href="###" onclick="doDetail('${secrecyComputer.secrecycomputerId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" alt ="详情" >
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无涉密计算机"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>