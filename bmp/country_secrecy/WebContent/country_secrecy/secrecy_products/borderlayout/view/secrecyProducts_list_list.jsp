<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/district" prefix="dis"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>密品列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<s:actionmessage theme="messages"/>
		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){
					new FormCheck('queryform',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			// 详 情
			function doDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/bmp/secrecyProducts/secrecyProducts_detail.action?secrecyProducts.secrecyproductsId='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.7,
					title : '密品详情'
				});
			}

			//查询
			function query(){
				var frm = document.getElementById('queryform');
				frm.action = "${ctx}/bmp/secrecyProducts/secrecyProducts_list_list.action";
				frm.submit();
			}
		</script>
	</head>

	<body>
		<div id="body_content"  >
			<c:if test="${dataGetFlag==1}">
				<div class="button_bar">
					<div class="left">
						<div class="pop_button_bar">
							<a class="pop_button" href="${ctx}/platform/stat/statFramework_organDetail.action?organId=${organ.organId}" id="dataflagfanhui" ><span>返回</span></a>
						</div>
					</div>
					<div class="right"></div>
				</div>
			</c:if>


			<!-- 复合面板开始 -->
			<cp:start defaultTitle="密品简介" ctx="${ctx}" icoPath="/country_secrecy/secrecy_products/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','密品简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','密品搜索');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于密品
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_products"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<form action="<s:url action="secrecyProducts_list_list" namespace="/bmp/secrecyProducts"/>" method="post" id="queryform" name="queryform">
					<input type="hidden" name="districtCode" id="districtCode" value="${district.districtCode}"><!-- 行政区划编码 -->
					<input type="hidden" name="ywFlag" id="ywFlag" value="${ywFlag}"><!-- 业务标志 1： 查询页面     0：普通业务模块   -->
					<input type="hidden" name="isChildren" id="isChildren" value="${isChildren}"><!-- 是否包含下级  1包含  0不包含 -->
					<input type="hidden" name="organ.organId" id="organ.organId" value="${organ.organId}"><!-- 单位 -->
					<input type="hidden" name="dataGetFlag" id="dataGetFlag" value="${dataGetFlag}"><!-- 综合统计数据撰取的标志  用于综合统计 -->

						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									密品名称：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyProducts.secrecyproductsName" id="secrecyProducts.secrecyproductsName" value="${secrecyProducts.secrecyproductsName }" />
								</td>
								<td class="tbLable fr">
									涉密等级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" style="width:130px;" optionValue="${secrecyProducts.secrecyLevel }"
									title="true" titleText="全部"  name="secrecyProducts.secrecyLevel" id="secrecyProducts.secrecyLevel"  />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									定密负责人：
								</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyProducts.formulatesecrecyPerson.name" name="secrecyProducts.formulatesecrecyPerson.name" value="${secrecyProducts.formulatesecrecyPerson.name }" />
									<input type="hidden" id="secrecyProducts.formulatesecrecyPerson.userInfoId" name="secrecyProducts.formulatesecrecyPerson.userInfoId" value="${secrecyProducts.formulatesecrecyPerson.userInfoId }" />
								</td>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									<input type="text" name="secrecyProducts.departId.departmentName" id="secrecyProducts.departId.departmentName" value="${secrecyProducts.departId.departmentName }"  />
									<input type="hidden" id="secrecyProducts.departId.departmentId" name="secrecyProducts.departId.departmentId" value="${secrecyProducts.departId.departmentId }" />
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:query();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('queryform').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<c:if test="${ywFlag eq '0'}">
							<c:if test="${dataGetFlag==1}">【${organ.organName}】 - </c:if>
							密品列表
						</c:if>
						<c:if test="${ywFlag eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${isChildren ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 密品列表
							</c:if>
							<c:if test="${isChildren eq '1'}">
								${district.districtName} - 密品列表
							</c:if>
						</c:if>
					</div>
				</div>
				<div class="panel_content panel_content_table"><!-- ${ctx}/bmp/secrecyProducts/secrecyProducts_list_list.action -->
					<s:if test="#request.secrecyProductsList.size>0">
						<ec:table items="secrecyProductsList" var="secrecyProducts" tableId="secrecyProductsList" border="0"
							action="${actionRequestURI}"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="secrecyproductsId" alias="secrecyProductsId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="secrecyproductsName" title="密品名称" width="20%" cell="text" alias="size=20"/>
								<ec:column property="formulatesecrecyPerson.name" title="定密负责人" width="10%" cell="text" alias="size=20"/>
								<ec:column property="null" title="涉密等级" width="5%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyProducts.secrecyLevel}"></dictionary:text>
								</ec:column>
								<ec:column property="null" title="保密期限" width="8%">
									<c:if test="${secrecyProducts.secrecyLimit!=null}">
										${secrecyProducts.secrecyLimit}
										<dictionary:text tableCode="bmp" fieldCode="limit_type" optionValue="${secrecyProducts.limitType}"></dictionary:text>
									</c:if>
								</ec:column>
								<ec:column property="secrecyLimitBegindate" title="保密期限起" width="10%" cell="date" format="yyyy-MM-dd" />
								<ec:column property="secrecyLimitEnddate" title="保密期限止" width="10%" cell="date" format="yyyy-MM-dd" />
								<ec:column property="departId.departmentName" title="部门名称" width="20%" cell="text" alias="size=20"/>
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetail('${secrecyProducts.secrecyproductsId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无数据"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

		<!-- 删除用隐藏表单 -->
		<form action="" method="post" id="secrecyProductsDelForm">
			<input type="hidden" name="secrecyProductsIds" id="secrecyProductsIds"/>
		</form>
	</body>
</html>