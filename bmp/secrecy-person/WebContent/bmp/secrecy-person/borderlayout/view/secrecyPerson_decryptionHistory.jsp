<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密人员脱密列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
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
			function doDetail( secrecyPersonDecyptionId ){
				$ENV.dialog.open({
					title : '涉密人员脱密信息详情',
					url : '${ctx}/bmp/secrecyperson/secrecyPerson_secrecyDecryptionDetail.action?secrecyPersonDecryption.SecrecyPersonDecryptionId='+secrecyPersonDecyptionId+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
					}
				});
			}
		</script>
		<style type="text/css">
			.body_content{
				top:0px;
			}
		</style>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密人员简介" ctx="${ctx}" icoPath="/bmp/secrecy-person/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','涉密人员简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','涉密人员查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密人员
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_person"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

					<!-- 联系方式 -->
					<!-- <div class="cpMsgContactInfoTitle">
						业务指导
					</div>
					<div class="cpMsgContactInfoContext">
						联系人：四川省国家保密局督察处 XXX 联系电话：028-85229437
					</div> -->
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<s:form action="secrecyPerson_decryptionHistory.action" method="post" id="secrecyPersonDecryptionHistoryForm" theme="simple">
						<input type="hidden" name="districtCode" id="districtCode" value="${districtCode }" />
						<input type="hidden" id="checkLower" name="checkLower" value="${checkLower}" />
						<input type="hidden" id="fromQuery" name="fromQuery" value="${fromQuery}" />
						<input type="hidden" id="departmentId" name="departmentId" value="${secrecyPersonDecryption.secrecyPersonId.department.departmentId}"/>

						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">姓 名：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPersonDecryption.secrecyPersonId.userInfo.name" name="secrecyPersonDecryption.secrecyPersonId.userInfo.name" value="${secrecyPersonDecryption.secrecyPersonId.userInfo.name}" />
								</td>
								<td class="tbLable fr">脱密类型：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="decryption_type" name="secrecyPersonDecryption.decryptionType" id="secrecyPersonDecryption.decryptionType" optionValue="${secrecyPersonDecryption.decryptionType }" title="true" titleText="请选择" style="width:100px;"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">脱密时间起：</td>
								<td class="tbValue fl">
									<input type="text" id="decryptionStartStart" name="decryptionStartStart"  class="Wdate" value="<s:date name='#attr.decryptionStartStart' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td class="tbLable fm">--</td>
								<td class="tbValue fl">
									<input type="text" id="decryptionStartEnd" name="decryptionStartEnd"  class="Wdate" value="<s:date name='#attr.decryptionStartEnd' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">脱密时间止：</td>
								<td class="tbValue fl">
									<input type="text" id="decryptionEndStart" name="decryptionEndStart"  class="Wdate" value="<s:date name='#attr.decryptionEndStart' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td class="tbLable fm">--</td>
								<td class="tbValue fl">
									<input type="text" id="decryptionEndEnd" name="decryptionEndEnd"  class="Wdate" value="<s:date name='#attr.decryptionEndEnd' format='yyyy-MM-dd'/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('secrecyPersonDecryptionHistoryForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('secrecyPersonDecryptionHistoryForm').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<c:if test="${fromQuery ne '1'}">
							涉密人员脱密信息列表
						</c:if>
						<c:if test="${fromQuery eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${checkLower ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 涉密人员脱密信息列表
							</c:if>
							<c:if test="${checkLower eq '1'}">
								${district.districtName} - 涉密人员脱密信息列表
							</c:if>
						</c:if>

					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyPersonDecyptionList.size>0">
						<ec:table items="secrecyPersonDecyptionList" var="secrecyPersonDecyption" tableId="secrecyPersonDecryptionTable" border="0"
							action="${ctx}/bmp/secrecyperson/secrecyPerson_decryptionHistory.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="rowCount" cell="rowCount" sortable="false" title="序号" width="5%"/>
								<ec:column property="secrecyPersonId.userInfo.name" title="姓 名" width="10%" cell="text"/>
								<ec:column property="null" title="脱密类型" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="decryption_type" optionValue="${secrecyPersonDecyption.decryptionType}"/>
								</ec:column>
								<ec:column property="decryptionLimit" title="脱密期限" width="10%" cell="text"/>
								<ec:column property="null" title="期限单位" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="decryption_limit_type" optionValue="${secrecyPersonDecyption.limitMeasure}"/>
								</ec:column>
								<ec:column property="decryptionStart" title="脱密时间起" cell="date" format="yyyy-MM-dd" width="10%"/>
								<ec:column property="decryptionEnd" title="脱密时间止" cell="date" format="yyyy-MM-dd" width="10%"/>
								<ec:column property="null" title="脱密原因" width="20%" cell="text">
									<c:choose>
										<c:when test="${fn:length(secrecyPersonDecyption.decryptionReason) > 15 }">
											${fn:substring(secrecyPersonDecyption.decryptionReason,0,12) }...
										</c:when>
										<c:otherwise>
											${secrecyPersonDecyption.decryptionReason }
										</c:otherwise>
									</c:choose>
								</ec:column>
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetail('${secrecyPersonDecyption.secrecyPersonDecryptionId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>