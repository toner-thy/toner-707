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
		<title>编辑保密要害部门</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>



		<style type="text/css" media="print">
			.no_print{display:none;}
		</style>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/theme/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/theme/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("/bmp/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});

			// 部门下部位详情
			function doDetailForKeyPart(){
				window.location.href = "keySectionForKeyPartDetail_detail.jsp";
			}

			// 部门涉密人员详情
			function doSecrecyPersonDetailForKeySection(){
				window.location.href = "keySectionSecrecyPersonForKeySectionDetail_detail.jsp";
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar no_print">
					<a class="pop_button" href="###" onclick="javascript:window.print();"><span>打 印</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar no_print">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">

			<!-- 保密要害部门panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部门【信息中心】详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" width="100%">
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
								部门名称：
							</td>
							<td class="tbValue fl">
								信息中心
							</td>
							<td class="tbLable fr">
								负责人：
							</td>
							<td class="tbValue fl">
								张三
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
								联系电话：
							</td>
							<td class="tbValue fl">
								028-12312313
							</td>
						</tr>
						<tr>
							<td class="fr">
								在编人员数量：
							</td>
							<td class="fl">
								14
							</td>
							<td class="fr">
								涉密人员数量：
							</td>
							<td class="fl">
								当前部门有涉密人员【3】人
							</td>
						</tr>
						<tr>
							<td class="fr">
								涉密工作事项及范围：
							</td>
							<td class="fl" colspan="3">
								<pre>工作事项:信息收集</pre>
							</td>
						</tr>
					</table>
					<table class="content_table st" id="table_part_add" width="100%">
						<tr>
							<td class="tbLable fr">
								保密制度：
							</td>
							<td class="fl" colspan="3">
									<table border="0" cellspacing="0" width="100%">
										<tr>
											<td width="85%">
												保密制度2.doc
											</td>
											<td align="center">
												<a href="">下载</a>&nbsp; &nbsp; <a href="">删除</a>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部门【信息中心】-保密要害部位列表
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
									<td class="tableHeader"  width="20%" >部位名称</td>
									<td class="tableHeader"  width="15%" >涉密等级</td>
									<td class="tableHeader"  width="15%" >主要负责人</td>
									<td class="tableHeader"  width="15%" >联系电话</td>
									<td class="tableHeader"  width="5%" >详情</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td>3</td>
									<td width="35%" >
										档案室
									</td>
									<td width="20%" >
										重要
									</td>
									<td width="20%" >
										张三
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doDetailForKeyPart()">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td>1</td>
									<td width="35%" >
										文印室
									</td>
									<td width="20%" >
										一般
									</td>
									<td width="20%" >
										郭东
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doDetailForKeyPart()">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td>2</td>
									<td width="35%" >
										机房
									</td>
									<td width="20%" >
										重要
									</td>
									<td width="20%" >
										王五
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doDetailForKeyPart()">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部门【信息中心】-涉密人员列表
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
									<td class="tableHeader"  width="5%" >详情</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td>1</td>
									<td>
										郭东
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
										<a href="###" onclick="doSecrecyPersonDetailForKeySection('')">
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
										<a href="###" onclick="doSecrecyPersonDetailForKeySection('')">
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
										<a href="###" onclick="doSecrecyPersonDetailForKeySection('')">
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




