<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>论文评奖情况详情</title>

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
		</script>

	</head>
	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar"></div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
				</div>
			</div>
		</div>
	 	<div class="body_content" >
			<!-- 内容panel开始 -->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						论文评奖情况详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<!-- 隐藏域 -->
					<table class="content_table">
						<tr height="36px;">
							<td align="right">作者：</td>
							<td colspan="3">
								<c:if test="${paperPresented.author== ''}">
									未填写
								</c:if>
								<c:if test="${paperPresented.author!= ''}">
									${paperPresented.author}
								</c:if>
							</td>
						</tr>
						<tr>
								<td class="tbLable fr">
									期号：
								</td>
								<td class="tbValue fl">
								<c:if test="${paperPresented.date== ''}">
									未填写
								</c:if>
								<c:if test="${paperPresented.date!= ''}">
									<s:date format="yyyy-MM-dd" name="paperPresented.date"/>
								</c:if>
								</td>
							</tr>
						<tr>
								<td class="tbLable fr">
									篇名：
								</td>
								<td class="tbValue fl">
								<c:if test="${paperPresented.name== ''}">
									未填写
								</c:if>
								<c:if test="${paperPresented.name!= ''}">
									${paperPresented.name}
								</c:if>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									刊物：
								</td>
								<td class="tbValue fl">
								<c:if test="${paperPresented.periodical== ''}">
									未填写
								</c:if>
								<c:if test="${paperPresented.periodical!= ''}">
									${paperPresented.periodical}
								</c:if>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">
									奖项名称：
								</td>
								<td class="tbValue fl">
								<c:if test="${paperPresented.awardName== ''}">
									未填写
								</c:if>
								<c:if test="${paperPresented.awardName!= ''}">
									${paperPresented.awardName}
								</c:if>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									评奖单位：
								</td>
								<td class="tbValue fl">
								<c:if test="${paperPresented.awardOrgan== ''}">
									未填写
								</c:if>
								<c:if test="${paperPresented.awardOrgan!= ''}">
									${paperPresented.awardOrgan}
								</c:if>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr"  style="vertical-align: top;">
									备注：
								</td>
								<td class="tbValue fl">
								<c:if test="${paperPresented.description== ''}">
									未填写
								</c:if>
								<c:if test="${paperPresented.description!= ''}">
									${paperPresented.description}
								</c:if>
								</td>
							</tr>
					</table>
					<div id="files_list">
					</div>
				</div>
			</div>
			<%--
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						检查列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<form id="form_paperPresented_update" class="form" action="<s:url action="paperPresented_update" includeParams="true"/>" method="post" target="main" enctype="multipart/form-data">
						<s:if test="#request.list.size>0">
							<ec:table items="list" var="checkEventTable" tableId="list" border="0"
								action="${ctx}/checkEventTable_list.action"
								imagePath="${ctx}/platform/theme/default/images/table/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
								<ec:row>
									<ec:column property="organ.organName" title="单位名称"/>
									<ec:column property="checkTable.tableName" title="检查表名"/>
									<ec:column property="checkTime" title="填表时间" format="yyyy-MM-dd" cell="date"/>
								</ec:row>
							</ec:table>
						</s:if>
						<s:else>
							<styles:nolist/>
						</s:else>
					</form>
				</div>
			</div>
			--%>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>