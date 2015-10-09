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
		<title>保密要害部位详情</title>

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
			function doBackToKeyPartList(id){
				window.location.href = "${ctx}/bmp/demo/keyPart/keyPart_list.jsp";
			}

			// 要害部位涉密人员详情
			function doViewKeyPartSecrecyPersonDetail(){
				window.location.href = "secrecyPersonForKeyPartDetail_detail.jsp";
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="###" onclick=""><span>打 印</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
				</div>
			</div>
		</div>

		<div class="body_content">

			<!-- 保密要害部位panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部位【保密室】详情
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
								部位名称：
							</td>
							<td class="tbValue fl">
								保密室
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
								刘国友
							</td>
						</tr>
						<tr>
							<td class="fr">
								涉密人员数量：
							</td>
							<td class="fl" colspan="3">
								<label title="涉密人员数量由当前系统自动统计">3</label>（开发说明：add页面没有，edit和detail有）
							</td>
						</tr>
						<tr>
							<td class="fr">
								具体位置：
							</td>
							<td class="fl" colspan="3">
								16楼21层2103号
							</td>
						</tr>
						<tr>
							<td class="fr">
								涉密工作事项及范围：
							</td>
							<td class="fl" colspan="3">
								......
							</td>
						</tr>
						<tr>
							<td class="fr">
								技防措施：
							</td>
							<td class="fl" colspan="3">
								安装了电子密码文件柜、铁门、铁窗、监控视频
							</td>
						</tr>
						<tr>
							<td class="fr">
								管理制度：
							</td>
							<td class="fl" colspan="3">
								是否建立并落实保密管理制度？<input id="ddd" name="" type="radio" checked="checked"/> 是 <input id="ddd" name="" type="radio"/> 否
							</td>
						</tr>
						<tr>
							<td class="fr" valign="top">
								本单位确定要害部位文件：
							</td>
							<td class="fl" colspan="3" valign="top">
								<a href="###">《关于确定xxx部门为保密要害部位的决定》.doc</a>
							</td>
						</tr>
						<tr>
							<td class="fr" valign="top">
								本单位确定涉密人员文件：
							</td>
							<td class="fl" colspan="3" valign="top">
								<a href="###">《关于确定杨林川为涉密人员的决定》.doc</a><br/>
								<a href="###">《关于确定刘小鹏为涉密人员的决定》.doc</a><br/>
								<a href="###">《关于确定李建科为涉密人员的决定》.doc</a>
							</td>
						</tr>
						<tr>
							<td class="fr" valign="top">
								保密制度：
							</td>
							<td class="fl" colspan="3" valign="top">
								<a href="###">保密制度1.doc</a><br/>
								<a href="###">保密制度2.doc</a><br/>
								<a href="###">保密制度3.doc</a>

							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 保密要害部位panel结束 -->

			<!-- 保密要害部位人员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部位【保密室】人员列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id=""  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<thead>
								<tr>
									<td class="tableHeader"  width="20%" >姓 名</td>
									<td class="tableHeader"  width="15%" >行政职务</td>
									<td class="tableHeader"  width="15%" >涉密等级</td>
									<td class="tableHeader"  width="15%" >岗 位</td>
									<td class="tableHeader"  width="15%" >联系电话</td>
									<td class="tableHeader"  width="2%" >详 情</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td>
										刘云山
									</td>
									<td>
										处长
									</td>
									<td>
										重要
									</td>
									<td>
										处长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
										<a href="###" onclick="doViewKeyPartSecrecyPersonDetail('')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td>
										刘颖
									</td>
									<td>
										副处长
									</td>
									<td>
										一般
									</td>
									<td>
										副处长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
										<a href="###" onclick="doViewKeyPartSecrecyPersonDetail('')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
			<!-- 保密要害部位人员panel结束 -->
		</div>
	</body>
</html>