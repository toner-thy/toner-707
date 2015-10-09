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
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部门列表</title>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});

			// 新 增
			function doAdd(action){
				/* TabUtil.openAsTab({
					url : action,
					title : '保密要害部门-新增',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在新增的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									document.getElementById('keySectionForm').submit();
								}
							}else {
									return false;
								}
						}else{
								if(item.content.getContentWindow().needReload){
									document.getElementById('keySectionForm').submit();
								}
							}
						}
					}); */
				window.location.href = action;
			}

			// 编 辑
			function doEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}
				window.location.href = action + "?keySection.keySectionId=" + items[0].value;
			}

			// 删除方法
			function doDel(action) {
				var items = EcTable.getCheckedItems();
				if(items.length==0) {
					alert("请至少选择一项记录！");
					return;
				}
				var ids = "";

				if(window.confirm("确定删除所选记录吗？")){
					items.each(function(item){
						ids += item.value + ",";
					});
					$('keySectionIds').value = ids;
					var forms = $('keySectionDelForm');
					forms.action = action;
					forms.submit();
				}
			}

			// 详 情
			function doDetail(id){
				$ENV.dialog.open({
					url : "${ctx}/bmp/keySection/keySection_detail.action?keySection.keySectionId=" + id + "&_ts="+new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '保密要害部门详情'
				});
			}

			// 上报
			function doReport(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要上传的项。");
					return;
				}
				if(window.confirm("确定上报吗？")){
					var ids = "";
					var queryStr = '';
					items.each(function(item){
						ids += item.value + ",";
					});
					queryStr = "?keySectionIds=" + ids;
					window.location.href = action + queryStr + '&time=' + new Date().getTime();
				}
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
					<a class='pop_button' href='${ctx}/platform/help/help_clientInfo.do?help.helpId=keySection_help' target='_back'><span>帮 助</span></a>
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密要害部门简介" ctx="${ctx}" icoPath="/bmp/key-section/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','保密要害部门简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','保密要害部门查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密要害部门
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_key_department"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

					<!-- 联系方式 -->
					<!-- <div class="cpMsgContactInfoTitle">
						业务指导
					</div>
					<div class="cpMsgContactInfoContext">
						联系人：四川省国家保密局督察处 XXX 联系电话：028-85229437
					</div> -->
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<s:form namespace="/bmp/keySection" action="keySection_allList.action" method="post" id="keySection_query_form" theme="simple">
						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">
									部门名称：
								</td>
								<td class="tbValue fl">
									<input type="text" name="keySectionQo.departmentName" id="keySectionQo.departmentName" value="${keySectionQo.departmentName }">
								</td>
								<td class="tbLable fr">
									涉密等级：
								</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_section" id="keySectionQo.secrecyLevel" name="keySectionQo.secrecyLevel" title="true"  titleText="全部" style="width:130px;"></dictionary:select>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									负责人：
								</td>
								<td class="tbValue fl">
									<input type="text" name="keySectionQo.principal" value="${keySectionQo.principal}" />
								</td>
								<td class="tbLable fr">
									联系电话：
								</td>
								<td class="tbValue fl">
									<input type="text" name="keySectionQo.phone" id="keySectionQo.phone" value="${keySectionQo.phone }">
								</td>
							</tr>
							<tr>
								<td class="tbLable fr" colspan="2">
									是否包含下级要害部门：
								</td>
								<td class="tbValue fl" colspan="2">
									<input type="radio" id="showTypeYes" name="showType" value="1" ${showType eq "1" ? "checked" : "" }/><label for="showTypeYes">包含</label>
									<input type="radio" id="showTypeNo" name="showType" value="0" ${showType eq "0" ? "checked" : "" }/><label for="showTypeNo">不包含</label>
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('keySection_query_form').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('keySection_query_form').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部门列表
					</div>
					<div class="panel_btn_bar pop_button_bar">

					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.keySectionlist.size>0">
						<ec:table items="keySectionlist" var="keySection" tableId="keySectionlist" border="0"
							action="${ctx}/bmp/keySection/keySection_allList.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="keySectionId" alias="keySectionId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="department.departmentName" title="名称" width="20%" cell="text" alias="size=15"/>
								<ec:column property="null" title="涉密等级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_section" optionValue="${keySection.secrecyLevel}"/>
								</ec:column>
								<ec:column property="principal.name" title="负责人" width="20%" cell="text" alias="size=10"/>
								<ec:column property="phone" title="联系电话" width="20%" cell="text" alias="size=18"/>
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetail('${keySection.keySectionId}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="没有发现保密要害部门。"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

		<!-- 删除用隐藏表单 -->
		<form action="" method="post" id="keySectionDelForm">
			<input type="hidden" name="keySectionIds" id="keySectionIds"/>
		</form>

	</body>
</html>