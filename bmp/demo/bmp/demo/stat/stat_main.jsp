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
		<title>统计</title>

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

			// 查看单位统计
			function doStatOrg(id){
				window.location.href = "stat_org_" + id + ".jsp";
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
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

			<table width="100%">
				<tr>
					<td width="200" valign="top" height="500">
						<!-- 树形 -->
						<div class="panel">
							<div class="panel_header">
								<div class="panel_title panel_titleListIco">
									行政区划树
								</div>
								<div class="panel_btn_bar pop_button_bar">
								</div>
							</div>
							<div class="panel_content panel_content_table">
								&nbsp;&nbsp;&nbsp;&nbsp;<a href="###" onclick="">四川省</a><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="###" onclick="">成都市</a><br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乐山市<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.........<br/>
							</div>
						</div>
					</td>
					<td width="*" valign="top" height="500">
						<!-- 主窗口 -->
						<div class="panel">
							<div class="panel_header">
								<div class="panel_title panel_titleListIco">
									【四川省】保密要害部门部位查询
								</div>
								<div class="panel_btn_bar pop_button_bar">
								</div>
							</div>
							<div class="panel_content panel_content_table">
								搜索：单位名称<input type="text"/> <input type="submit" value="提交"/>(开发提示：搜索在全省范围内搜索)<br/>
							</div>
						</div>

						<div class="panel tMargin">
							<div class="panel_header">
								<div class="panel_title panel_titleListIco">
									【四川省】单位列表
								</div>
								<div class="panel_btn_bar pop_button_bar">
								</div>
							</div>
							<div class="panel_content panel_content_table">
								<div class="eXtremeTable" >
									<table id=""  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
										<thead>
											<tr>
												<td class="tableHeader" ><input type="checkbox"  name="secrecyLawCodeId_checkbox"  id="secrecyLawCodeId_checkbox"  class="class_secrecyLawCodeId_checkbox"  onclick="EcTable.checkAll('secrecyLawCodeId_checkbox')" /></td>
												<td class="tableHeader"  width="15%" >单位名称</td>
												<td class="tableHeader"  width="15%" >保密工作机构成员</td>
												<td class="tableHeader"  width="15%" >保密办成员</td>
												<td class="tableHeader"  width="15%" >保密要害部门</td>
												<td class="tableHeader"  width="15%" >保密要害部位</td>
												<td class="tableHeader"  width="15%" >机关涉密人员</td>
											</tr>
										</thead>
										<tbody class="tableBody" >
											<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
												<td><input type="checkbox"  name="secrecyLawCodeId"  value=""  class="row_checkbox " /></td>
												<td width="15%" >
													<a href="">四川省教育厅</a>
												</td>
												<td width="15%" >
													<a href="detail01.jsp">12人</a>
												</td>
												<td width="15%" >
													<a href="detail02.jsp">3人</a>
												</td>
												<td width="15%" >
													<a href="detail03.jsp">2人</a>
												</td>
												<td width="15%" >
													<a href="detail04.jsp">3人</a>
												</td>
												<td width="15%" >
													<a href="detail05.jsp">5人</a>
												</td>
											</tr>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>