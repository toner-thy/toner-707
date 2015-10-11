<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>严重违规案件列表</title>

        <link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
			//查看严重违规案件的明细
			function doDetail(id){
				$ENV.dialog.open({
					url : "${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_detail.action?caseCriticalviolation.caseCriticalviolationId=" + id +"&_ts="+new Date().getTime(),
				    width : 0.9,
					height : 0.5,
					title : '严重违规案件详情'
				});
			}
		</script>
	</head>

	<body>

      <s:if test="#request.retrun!=null&&#request.retrun!=''">
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button"
						href="${ctx}/platform/stat/statFramework_organDetail.action?organId=${caseCriticalviolation.createOrgan.organId}"
						id="dataflagfanhui"><span>返回</span></a>
				</div>
			</div>
			<div class="right"></div>
		</div>
	    </s:if>
		<div id="body_content" class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="严重违规案件简介" ctx="${ctx}" icoPath="/bmp/disclose_secrecy/borderlayout/skin/blue/img/09.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','严重违规案件简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','严重违规案件搜索');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于严重违规案件
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_caseCriticalviolation"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form  action="${districtCode==null?'caseCriticalviolation_list.action':'caseCriticalviolation_selectListing2.action' }"
					id="caseCriticalviolation_list_form" name="caseCriticalviolation_list_form">
						<input type="hidden" value="${baomijuOrbendanwei}" name="baomiju"/><!-- 用于搜索区分本单位还是保密局 -->
						<input type="hidden" value="${districtCode}" name="districtCode"/><!-- 用于搜索区分本单位还是保密局 -->
						<input type="hidden" value="${retrun}" name="retrun" />
						<input type="hidden" value="${includeChild}" name="includeChild"/><!-- 用于搜索区分本单位还是保密局 -->
						<input type="hidden" value="${caseCriticalviolation.createOrgan.organName}" name="caseCriticalviolation.createOrgan.organName"/><!-- 用于搜索区分保密局 -->
						<input type="hidden" value="${caseCriticalviolation.createOrgan.organId}" name="caseCriticalviolation.createOrgan.organId"/><!-- 用于搜索区分保密局 -->

						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									名称：
								</td>
								<td class="tbValue fl">
									<input size="25" id="caseCriticalviolation.name" name="caseCriticalviolation.name" type="text" value="${caseCriticalviolation.name}" />
								</td>
								<td class="tbLable fr">
									查处结果：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp"
									fieldCode="find_result" id="caseCriticalviolation.dealResult"
									name="caseCriticalviolation.dealResult" style="width: 132px;"
									optionValue="${caseCriticalviolation.dealResult}" title="true" titleText="请选择"
									 />
								</td>
							</tr>

							<tr>
								<td class="tbLable fr" width="20%">密&nbsp;&nbsp;&nbsp;&nbsp;级：</td>
								<td class="tbValue fl" width="30%">
									<dictionary:select tableCode="bmp"
									fieldCode="secrecy_level_thing" id="caseCriticalviolation.secrecyLevel"
									name="caseCriticalviolation.secrecyLevel" style="width: 132px;"
									optionValue="${caseCriticalviolation.secrecyLevel}"
								    title="true" titleText="请选择"
									 />
								</td>
							</tr>

							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('caseCriticalviolation_list_form').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('caseCriticalviolation_list_form').reset();"><span>重 置</span></a>
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
					 <s:if test="caseCriticalviolation.createOrgan.organId!=null&&caseCriticalviolation.createOrgan.organId!=''">
				           【<s:property value="caseCriticalviolation.createOrgan.organName"/>】- 严重违规案件列表
				       </s:if>
				       <s:else>
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<s:if test="#request.districtCode==null||#request.districtCode==''">
							严重违规案件列表
						</s:if>
						<s:else>
							<!--判断是否查看下级1查看，0不查看-->
					        <c:if test="${includeChild ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 严重违规案件列表
							</c:if>
							<c:if test="${includeChild eq '1'}">
								${district.districtName} - 严重违规案件列表
							</c:if>
						</s:else>
					</s:else>
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<!-- 这里自定义ectable -->
					<s:if test="#request.caseCriticalviolationList.size>0">
						<ec:table items="caseCriticalviolationList" var="caseCriticalviolation" tableId="caseCriticalviolationList" border="0"
							action="${districtCode==null?'${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_list.action':'${ctx}/bmp/caseCriticalviolation/caseCriticalviolation_selectListing2.action' }"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="caseCriticalviolationId" width="5%" alias="caseCriticalviolationId_checkbox" cell="checkbox" headerCell="checkbox"/>
								<ec:column property="name" title="名称" width="10%" style="text-align: left;" cell="text" alias="size=30">
								</ec:column>
								<ec:column property="department.departmentName" title="部门" width="15%" style="text-align: left;">
								</ec:column>
								<ec:column property="null" title="密级" width="5%" style="text-align: left;">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${caseCriticalviolation.secrecyLevel}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="违规方式" width="35%" style="text-align: left;">
									<dictionary:text tableCode="bmp" fieldCode="case_Type" optionValue="${caseCriticalviolation.caseType}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="责任单位性质" width="20%" style="text-align: left;" >
									<dictionary:text tableCode="bmp" fieldCode="duty_organ_kind" optionValue="${caseCriticalviolation.dutyOrganKind}"></dictionary:text>
								</ec:column>
								<%-- <ec:column property="null" title="案件性质" width="15%" style="text-align: left;" >
									<dictionary:text tableCode="bmp" fieldCode="case_kind" optionValue="${caseCriticalviolation.casekind}"></dictionary:text>
								</ec:column> --%>
								<ec:column property="null" title="查处结果" width="10%" style="text-align: left;" >
									<dictionary:text tableCode="bmp" fieldCode="find_result" optionValue="${caseCriticalviolation.dealResult}"></dictionary:text>
								</ec:column>
									<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetail('${caseCriticalviolation.caseCriticalviolationId}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>

						<u:noData text="当前暂无严重违规案件信息。"/>
					</s:else>
				</div>
			</div>
		</div>
	</body>
</html>