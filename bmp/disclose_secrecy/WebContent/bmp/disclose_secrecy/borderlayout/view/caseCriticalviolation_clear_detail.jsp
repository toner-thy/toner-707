<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib prefix="secPersonlist" uri="http://www.cdthgk.com/tags/secrecyperson/list"%>
<%@ taglib prefix="nestedlist" uri="http://www.cdthgk.com/bmp/tags/nestedlist"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>严重违规案件密级解除详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<style type="text/css" media="print">
			.no_print{display:none;}
		</style>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
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
		</script>

	</head>

	<body style="overflow: auto;overflow-x: hidden;">


		<div>
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print" style="display:none;">
					<div class="panel_title panel_titleListIco no_print">
						严重违规案件【${caseCriticalviolation.name}】详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td colspan="4" height="45" class="formTitle" align="center">
								<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
									严重违规案件【${caseCriticalviolation.name}】
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								部门名称：
							</td>
							<td class="tbValue fl" >
								${caseCriticalviolation.department.departmentName}</td>
							<td class="tbLable fr">
								密&nbsp;&nbsp;&nbsp;&nbsp;级：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${caseCriticalviolation.secrecyLevel}"></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								查处结果：
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="find_result" optionValue="${caseCriticalviolation.dealResult}"></dictionary:text>
							</td>
							<td class="tbLable fr">
								创建时间：
							</td>
							<td class="tbValue fl">
								<c:if test="${caseCriticalviolation.createTime!=null}">
										<s:date name="caseCriticalviolation.createTime" format="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${caseCriticalviolation.createTime==null}">
										暂无
									</c:if>
							</td>
						</tr>
						<tr>
						    <td class="tbLable fr">
							    发案形式：
							</td>
							<td class="tbLable fl" colspan="3">
							<dictionary:text tableCode="bmp" fieldCode="case_Type" optionValue="${caseCriticalviolation.caseType}"></dictionary:text>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								单位性质：
							</td>
							<td class="tbValue fl"  colspan="3">
								<dictionary:text tableCode="bmp" fieldCode="duty_organ_kind" optionValue="${caseCriticalviolation.dutyOrganKind}"></dictionary:text>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<s:if test="#request.caseCriticalviolation_changelist.size>0">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
			     <div class="panel_header no_print">
					<div class="panel_title panel_titleListIco">
						严重违规案件【${caseCriticalviolation.name}】密级变更详情
					</div>
				</div>
				<div class="panel_content panel_content_table">

						<ec:table items="caseCriticalviolation_changelist" var="caseCriticalviolationChange" tableId="caseCriticalviolation_changelist" border="0"
							action="${ctx}/bmp/caseCriticalviolation/caseCriticalviolationDetail.action?caseCriticalviolation.caseCriticalviolationId=${caseCriticalviolation.caseCriticalviolationId}"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" filterable="false" autoIncludeParameters="true" sortable="false" showPagination="false">
							<ec:row>
								<ec:column property="caseCriticalviolation.name" title="严重违规案件" width="10%" cell="text" alias="size=15"/>
								<ec:column property="null" title="原密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${caseCriticalviolationChange.beforeLevel}"/>
								</ec:column>
								<ec:column property="null" title="变更后密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${caseCriticalviolationChange.afterLevel}"/>
								</ec:column>
								<ec:column property="null" title="保密期限变更" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${caseCriticalviolationChange.changeTimeState}"/>
								</ec:column>
								<ec:column property="changeDate" title="变更时间" width="10%"  cell="date" format="yyyy-MM-dd"/>
								<ec:column property="changeReason" title="变更原因" width="10%" cell="text" alias="size=10"/>
							</ec:row>
						</ec:table>

				</div>
		 </div>
	    </s:if>
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco">
						严重违规案件【${caseCriticalviolationClear.caseCriticalviolation.name}】密级解除详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">
								解除类型：
							</td>
							<td class="tbValue fl" >
								<dictionary:text tableCode="bmp" fieldCode="clear_secrecy_type" optionValue="${caseCriticalviolationClear.clearType }"></dictionary:text>
							</td>
							<td class="tbLable fr">
								解除时间：
							</td>
							<td class="tbValue fl">
							        <c:if test="${caseCriticalviolationClear.clearTime!=null}">
										<s:date name="caseCriticalviolationClear.clearTime" format="yyyy-MM-dd"/>
									</c:if>
									<c:if test="${caseCriticalviolationClear.clearTime==null}">
										暂无
									</c:if>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								解除前密级：
							</td>
							<td class="tbValue fl" colspan="3">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${caseCriticalviolationClear.caseCriticalviolation.secrecyLevel}"/>
							</td>
						</tr>

						<tr>
							<td class="fr">
								解除原因：
							</td>
							<td class="fl" colspan="3" width="500px;">
								<div style="word-break:break-all" >
		                           ${caseCriticalviolationClear.cleanReason }
								</div>
							</td>
						</tr>
					</table>
					<!--endprint1-->
					<!-- 打印标签 -->
				</div>
			</div>
		</div>
	</body>
</html>