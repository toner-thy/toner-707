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
		<title>新增保密要害部门</title>

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

			// 返 回
			function doBackToDetailKeyPart(id){
				window.location.href = "keyDepartment_detail.jsp";
			}

			// 部门涉密人员详情
			function doSecrecyPersonDetailForKeyPart(){
				window.location.href = "keySectionSecrecyPersonForKeyPartDetail_detail.jsp";
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar no_print">
			<div class="left no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button" href="###" onclick="javascript:window.print();"><span>打印当前页</span></a>
					<a class="pop_button" href="###" onclick="javascript:preview(1);"><span>打印要害部位信息</span></a>
					<a class="pop_button" href="###" onclick="doBackToDetailKeyPart()"><span>返 回</span></a>

				</div>
			</div>
			<div class="right no_print">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<!--startprint1-->
		<div class="body_content printCss">
			<!-- 保密要害部位panel开始 -->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						保密要害部门【信息中心】 ─ 保密要害部位详情
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td colspan="4" height="45" class="formTitle" align="center">
								<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
									保密要害部位
								</div>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								单位名称：
							</td>
							<td class="tbValue fl" colspan="3">
								眉山市林业局
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								部位名称：
							</td>
							<td class="tbValue fl">
								档案室
							</td>
							<td class="tbLable fr">
								主管部门：
							</td>
							<td class="tbValue fl">
								信息中心
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								涉密等级：
							</td>
							<td class="tbValue fl">
								重要
							</td>
							<td class="tbLable fr">
								主要负责人：
							</td>
							<td class="tbValue fl">
								张三
							</td>
						</tr>
						<tr>
							<td class="fr">
								涉密人员数量：
							</td>
							<td class="fl" colspan="3">
								<label title="涉密人员数量由当前系统自动统计">3</label>
							</td>
						</tr>
						<tr>
							<td class="fr">
								具体位置：
							</td>
							<td class="fl" colspan="3">
								18楼203号
							</td>
						</tr>
						<tr>
							<td class="fr">
								涉密工作事项及范围：
							</td>
							<td class="fl" colspan="3">
								<pre>资料管理</pre>
							</td>
						</tr>
						<tr>
							<td class="fr">
								技防措施：
							</td>
							<td class="fl" colspan="3">
								暂无
							</td>
						</tr>
					</table>

				</div>
			</div>

			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部位【档案室】 ─ 涉密人员列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id=""  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<thead>
								<tr>
									<td class="tableHeader" >序号</td>
									<td class="tableHeader"  width="20%" >姓 名</td>
									<td class="tableHeader"  width="15%" >涉密等级</td>
									<td class="tableHeader"  width="20%" >行政职务</td>
									<td class="tableHeader"  width="15%" >岗 位</td>
									<td class="tableHeader"  width="20%" >联系电话</td>
									<td class="tableHeader"  width="5%" >详细</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td>1</td>
									<td>
										张三
									</td>
									<td>
										重要
									</td>
									<td>
										处长
									</td>
									<td>
										处长
									</td>
									<td width="20%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doSecrecyPersonDetailForKeyPart()">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td>2</td>
									<td>
										刘颖
									</td>
									<td>
										一般
									</td>
									<td>
										副处长
									</td>
									<td>
										副处长
									</td>
									<td width="20%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doSecrecyPersonDetailForKeyPart()">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td>3</td>
									<td>
										杨丽
									</td>
									<td>
										一般
									</td>
									<td>
										科员
									</td>
									<td>
										科员
									</td>
									<td width="20%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doSecrecyPersonDetailForKeyPart()">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>