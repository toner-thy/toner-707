<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>商业秘密事项详情</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

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

			function preview(oper){
				if (oper < 10)
					{
					bdhtml=window.document.body.innerHTML;//获取当前页的html代码
					sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域
					eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域

					prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html

					prnhtmlprnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html

					window.document.body.innerHTML=prnhtmlprnhtml;
					window.print();
					window.document.body.innerHTML=bdhtml;
				} else {
					window.print();
				}
			}

			// 查看详情
			function doDetail(secrecyPersonId, secrecyCountryItemId){
				$ENV.dialog.open({
					url : '${ctx}/bmp/secrecyCountryItem/keysecrecyCountryItem_detailPerson.action?secrecyCountryItemPerson.secrecyPerson.secrecyPersonId='+secrecyPersonId+'&secrecyCountryItem.secrecyCountryItemId=' + secrecyCountryItemId +'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '要害部位涉密人员详情'
				});
			}

		</script>
		<s:actionmessage theme="messages"/>
		<!--print 这个属性可以在打印时有效-->
		<style type="text/css" media="print">
			.no_print{display:none;}
			.PageNext{page-break-after: always;}
		</style>

	</head>

	<body style="overflow-y:auto;">
		<div class="panel_header no_print" >
			<div class="panel_title panel_titleListIco no_print">
				<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
					商业秘密事项【${secrecyCountryItem.secrecyCountryItemName }】详情
				</div>
			</div>
		</div>

		<div class="panel tMargin" style="margin-top: -1px;">
			<!-- 商业秘密事项     开始 -->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						商业秘密事项 - 基本信息
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr" style="white-space: nowrap;">
								商业秘密事项名称
							</td>
							<td class="tbValue fl" colspan="3">
								${secrecyCountryItem.secrecyCountryItemName}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								定密负责人
							</td>
							<td class="tbValue fl">
								${secrecyCountryItem.formulateSecrecyPerson.name}
							</td>
							<td class="tbLable fr">
								密级
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyCountryItem.secrecyLevel}"></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								保密期限类型
							</td>
							<td class="tbValue fl" >
								<dictionary:text tableCode="bmp" fieldCode="secrecy_limit_type" optionValue="${secrecyCountryItem.secrecyLimitType}"></dictionary:text>
							</td>
							<td class="tbLable fr">
								保密期限
							</td>
							<td class="tbValue fl">
								<c:if test="${secrecyCountryItem.secrecyLimit!=null}">
									${secrecyCountryItem.secrecyLimit}
									<dictionary:text tableCode="bmp" fieldCode="limit_type" optionValue="${secrecyCountryItem.limitType}"></dictionary:text>
								</c:if>
							</td>
						</tr>

						<!-- 保密期限 类型选择  begin -->
						<c:if test="${secrecyCountryItem.secrecyLimitType==1}">
							<tr>
								<td class="fr">保密期限起</td>
								<td class="fl" ><s:date name="secrecyCountryItem.secrecyLimitBeginDate" format="yyyy-MM-dd" /></td>
								<td class="fr" style="white-space: nowrap;">保密期限止</td>
								<td class="fl" ><s:date name="secrecyCountryItem.secrecyLimitEndDate" format="yyyy-MM-dd" /></td>
							</tr>
						</c:if>
						<c:if test="${secrecyCountryItem.secrecyLimitType==2}">
							<tr>
								<td class="fr" style="white-space: nowrap;">保密期限止</td>
								<td class="fl" colspan="3"><s:date name="secrecyCountryItem.secrecyLimitEndDate" format="yyyy-MM-dd" /></td>
							</tr>
						</c:if>
						<c:if test="${secrecyCountryItem.secrecyLimitType==3 || secrecyCountryItem.secrecyLimitType==4}">
							<tr>
								<td class="fr">解除条件</td>
							    <td class="fl" colspan="3">${secrecyCountryItem.removeSecrecyCondition}</td>
							</tr>
						</c:if>
						<!-- 保密期限 类型选择  end -->

						<!-- 是否是保密要害部门  选择   begin-->
						<c:if test="${secrecyCountryItem.isfromKeyDepartment==1}">
							<tr>
								<td class="fr" style="white-space: nowrap;">
									保密要害部门名称
								</td>
								<td class="fl" colspan="3">
									${secrecyCountryItem.departId.departmentName}
								</td>
							</tr>
						</c:if>
						<c:if test="${secrecyCountryItem.isfromKeyDepartment==0}">
							<tr>
								<td class="fr">
									部门名称
								</td>
								<td class="fl" colspan="3">
									${secrecyCountryItem.departId.departmentName}
								</td>
							</tr>
						</c:if>
						<!-- 是否是保密要害部门  选择    end-->

						<tr>
							<td class="fr">
								备注
							</td>
							<td class="fl" colspan="3">
								${secrecyCountryItem.content}
							</td>
						</tr>

					</table>
					<!--endprint1-->
				</div>
			</div>
			<!-- 商业秘密事项     结束 -->

			<div class="split_line"></div><!-- 分隔线 -->
			<!-- 密级变更的详情页面   开始 -->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						商业秘密事项 - 密级变更记录
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyCountryItemChangeList.size>0">
						<ec:table items="secrecyCountryItemChangeList" var="secrecyCountryItemChange" tableId="secrecyCountryItemChangeList" border="0"
							action="${ctx}/bmp/secrecycountryitem/secrecyCountryItem_change_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif" showPagination="false"
							width="100%" filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="changeDate" title="变更时间" width="10%"  cell="date" format="yyyy-MM-dd" />
								<ec:column property="null" title="原密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyCountryItemChange.beforeLevel}"/>
								</ec:column>
								<ec:column property="null" title="变更后密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyCountryItemChange.afterLevel}"/>
								</ec:column>
								<ec:column property="null" title="保密期限变更" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${secrecyCountryItemChange.changeTimeState}"/>
								</ec:column>
								<ec:column property="changeReason" title="变更原因" width="40%" cell="text" alias="size=40"/>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="该商业秘密事项 ,暂无密级变更记录。"/>
					</s:else>
				</div>
			</div>
			<!-- 密级变更的详情页面   结束 -->
		</div>
	</body>
</html>