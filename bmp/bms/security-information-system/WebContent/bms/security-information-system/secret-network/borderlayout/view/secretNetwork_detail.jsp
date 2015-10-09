<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>机关网络建设情况</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

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
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/FCKeditor/fckeditor.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					<c:forEach var="snm" items="${secretNetwork.secretNetworkManagerses }">
						addExistRow('${snm.secretNetworkManagersId }', '${snm.managerType }', '${snm.managerName }', '${snm.managerPost }', '${snm.learning }', '${snm.graduateSchool }', '${snm.isParticipatedSecre }');
					</c:forEach>
				});
			});

			var dicArray = new Array();
			<c:forEach var="dicoption" items="${dicList }">
				dicArray['${dicoption.optionValue }']='${dicoption.optionText }';
			</c:forEach>

			var rowNum = 0;

			function addExistRow(i, t, n, p, l, g, ion){
				var managerTypeStr = dicArray[t];
				var str = managerTypeStr;
				var str1 = n;
				var str2 = p;
				var str3 = l;
				var str4 = g;
				var str5 = ion=="1"?"是":"否";
				var str6 = "";

				var table = $('contentTable');

				var tr = new Element('tr', {'id':''});

				var td = new Element('td', {'style':'text-align:center;','html': str});
				var td1 = new Element('td', {'style':'text-align:center;','html': str1});
				var td2 = new Element('td', {'style':'text-align:center;','html': str2});
				var td3 = new Element('td', {'style':'text-align:center;','html': str3});
				var td4 = new Element('td', {'style':'text-align:center;','html': str4});
				var td5 = new Element('td', {'style':'text-align:center;','html': str5});
				var td6 = new Element('td', {'style':'text-align:center;','html': str6});

				td.inject(tr);
				td1.inject(tr);
				td2.inject(tr);
				td3.inject(tr);
				td4.inject(tr);
				td5.inject(tr);
				td6.inject(tr);

				tr.inject(table);
				rowNum++;
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
					<a class="pop_button pop_button_close" href="###" onclick="javascript:window.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="机关网络建设情况简介" ctx="${ctx}" icoPath="#">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','机关网络建设情况简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于机关网络建设情况
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="annual_plan_description"> </cpc:tc>
					</div>
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						机关网络建设情况
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="add_form" action="<s:url namespace='/bmp/secretNetwork' action='secretNetwork_editing' includeParams='true'/>" method="post">
						<input type="hidden" name="secretNetwork.secretNetworkId" value="${secretNetwork.secretNetworkId }"/>
						<table class="content_table" width="100%" id="contentTable">
							<tr>
								<td class="tbLable fr">
									建成时间：
								</td>
								<td class="tbValue fl" colspan="2">
									${secretNetwork.buildTime }
								</td>
								<td class="tbLable fr">
									承建单位：
								</td>
								<td class="tbValue fl" colspan="3">
									${secretNetwork.constructionUnit }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									审批部门：
								</td>
								<td class="tbValue fl" colspan="2">
									${secretNetwork.approvalDepartment }
								</td>
								<td class="tbLable fr">
									审批时间：
								</td>
								<td class="tbValue fl" colspan="3">
									${secretNetwork.approvalTime }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									文号：
								</td>
								<td class="tbValue fl" colspan="6">
									${secretNetwork.docNum }
								</td>
							</tr>
							<tr style="text-align: center">
								<td><b>三员类型</b></td>
								<td><b>姓名</b></td>
								<td><b>职务</b></td>
								<td><b>学历</b></td>
								<td><b>毕业学校</b></td>
								<td><b>是否参加过<br/>计算机保密业务培训</b></td>
								<td>
								</td>
							</tr>
						</table>
						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>
				</div>
			</div>
		</div>

	</body>
</html>