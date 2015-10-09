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
		<title>涉密设备维修情况详情</title>

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
						涉密设备维修情况详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<!-- 隐藏域 -->
					<table class="content_table">
						<tr>
							<tr>
								<td class="tbLable fr">
									设备类型：
								</td>
								<td class="tbValue fl">
								${secrecyMaintain.type}
								</td>
								<td class="tbLable fr">
									送修时间：
								</td>
								<td class="tbValue fl">
									<s:date name="secrecyMaintain.date" format="yyyy-MM-dd"/>
								</td>

							</tr>
							<tr>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									<dictionary:text fieldCode="secrecy_level_thing" tableCode="bmp"
									optionValue="${secrecyMaintain.secrecyLevel}"/>
								</td>
								<td class="tbLable fr">
									维修单位：
								</td>
								<td class="tbValue fl">
									${secrecyMaintain.maintainOrgan.organName}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									使用部门：
								</td>
								<td class="tbValue fl">
								 ${secrecyMaintain.useDepartment.departmentName}
								</td>
								<td class="tbLable fr">
									监修人：
								</td>
								<td class="tbValue fl">
									${secrecyMaintain.seeUserInfo.name }
								</td>

							</tr>
							<tr>
								<td class="tbLable fr">
									维修原因：
								</td>
								<td class="tbValue fl"  style="width:98%" colspan="3">
								${secrecyMaintain.reason}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									领导审批意见：
								</td>
								<td class="tbValue fl" colspan="3">
								 ${secrecyMaintain.leaderIdea}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									部门意见：
								</td>
								<td class="tbValue fl" style="width:98%" colspan="3">
								 ${secrecyMaintain.depIdea}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									备注：
								</td>
								<td class="tbValue fl" colspan="3" style="width:98%" colspan="3">
								${secrecyMaintain.description}
								</td>
							</tr>
					</table>
					<div id="files_list">
					</div>
				</div>
			</div>
		</div>
	</body>
</html>