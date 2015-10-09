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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>会议类型详情</title>

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
			//返回
			function doClose(){
				environment.dialog.close();
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_close" href="###" onclick="javascript:doClose();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content">
			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						会议类型详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
				<table class="content_table">
					<tr height="36px;">
							<td align="right" width="100px">类型名称：</td>
							<td colspan="3">
								${meetingCategory.categoryName}
							</td>
						</tr>

						<tr height="36px;">
							<td align="right">所在单位：</td>
							<td>
								${meetingCategory.organ.organName}
							</td>
							<td align="right" valign="top">所在部门：</td>
							<td>
								${meetingCategory.department.departmentName}
							</td>
						</tr>

						<tr height="36px;">
							<td align="right">创建人：</td>
							<td>
								${meetingCategory.createPerson.userInfo.name}
							</td>
							<td align="right">创建时间：</td>
							<td>
								<s:date name="meetingCategory.createTime" format="yyyy-MM-dd HH:mm"/>
							</td>
						</tr>

						<tr height="36px;">
							<td align="right">修改人：</td>
							<td>
								<c:if test="${meetingCategory.modifyPerson.userInfo.name==null}">
									暂无
								</c:if>
								<c:if test="${meetingCategory.modifyPerson.userInfo.name!=null}">
									${meetingCategory.modifyPerson.userInfo.name}
								</c:if>
							</td>
							<td align="right">修改时间：</td>
							<td>
								<c:if test="${meetingCategory.modifyTime==null}">
									暂无
								</c:if>
								<c:if test="${meetingCategory.modifyTime!=null}">
									<s:date name="meetingCategory.modifyTime" format="yyyy-MM-dd HH:mm"/>
								</c:if>
							</td>
						</tr>

						<tr height="36px;">
							<td align="right" valign="top">描述：</td>
							<td colspan="3" valign="top" height="100">
								${meetingCategory.categoryDesc}
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>