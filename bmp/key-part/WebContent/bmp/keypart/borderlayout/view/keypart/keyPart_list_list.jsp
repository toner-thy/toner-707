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
<%@ taglib uri="http://www.cdthgk.com/tags/district" prefix="dis"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密要害部位列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",function(){
				$ENV.onDomReady(function(){

				});
			});

			// 详 情
			function doDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/bmp/part/part_detail.action?part.partId='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '要害部位详情'
				});
			}

			//新增
			function doAdd() {
				TabUtil.openAsTab({
					url : "${contextPath}/bmp/part/part_add.action?departmentId=${part.department.departmentId}&nestedflag=${nestedflag}",
					title : '保密要害部位-新增',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在新增的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									window.location.reload();
								}
							} else {
								return false;
							}
						} else{
							if(item.content.getContentWindow().needReload){
								window.location.reload();
							}
						}
					}
				});
			}

			//编辑
			function doEdit() {
				var items = EcTable.getCheckedItems("partList_table");
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				TabUtil.openAsTab({
					url : "${contextPath}/bmp/part/part_edit.action?part.partId="+items[0].value+"&departmentId=${part.department.departmentId}&nestedflag=${nestedflag}",
					title : '保密要害部位-编辑',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									window.location.reload();
								}
							} else {
								return false;
							}
						} else{
							if(item.content.getContentWindow().needReload){
								window.location.reload();
							}
						}
					}
				});
			}

			//删除
			function doDel() {
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要删除的项。");
					return;
				}

				//判断  是否可以删除，如果是已经  密级变更    密级解除
				var jsonRequest = new Request.JSON({
 					url: '${ctx}/bmp/part/ajax_keyPart.action?partId='+items[0].value,
 				    onComplete: function(result, text){
 				    	if (result.flag != null && result.flag != "0") {
 				    		alert("删除失败，该记录已经被："+result.message + " 引用。");
 				    		return;

 				    	}else {
 				    		if (window.confirm('确定要删除吗？ ' )) {
 								var ids = "";
 								items.each(function(item){
 									ids += item.value + ",";
 								});
 								new Request.JSON({
 								    url: "${contextPath}/bmp/part/nestedpart_delete.action",
 								    onComplete: function(result, text){
 								    	if (result.success) {
 								    		alert(result.message);
 								    		$('panel_content_keyPart').load('${contextPath}/bmp/part/nestedpart_list.action?t_date='+ $time() + '&departmentId=${part.department.departmentId}');
 								    	} else{
 								    		alert("删除失败");
 								    		return;
 								    	}
 								    }
 								}).get({
 									partIds : ids
 								});
 							}
 				    	}
 				    }
 				}).get({
 					'partId' : items[0].value
 				});

			}

			//密级变更
			function doSecrecyChange() {
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}

				$ENV.dialog.open({
					url : "${contextPath}/bmp/part/part_change.action?part.partId=" + items[0].value + "&_ts="+new Date().getTime(),
					width : 0.6,
					height : 310,
					title : '要害部位密级变更'
				});
			}

			//密级解除
			function doSecrecyClear() {
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}

				$ENV.dialog.open({
					url : "${contextPath}/bmp/part/part_secrecyClear.action?part.partId=" + items[0].value + "&_ts="+new Date().getTime(),
					width : 0.6,
					height : 290,
					title : '要害部位密级解除'
				});
			}

			function query(){
				var frm = document.getElementById('queryform');
				frm.action = "${ctx}/bmp/part/part_list_list.action";
				frm.submit();
			}
		</script>
		<style type="text/css">
			.body_content{
				top:0px;
			}
		</style>
	</head>

	<body>
		<c:if test="${dataGetFlag==1}">
			<div class="button_bar">
				<div class="left">
					<div class="pop_button_bar">
						<a class="pop_button" href="${ctx}/platform/stat/statFramework_organDetail.action?organId=${organ.organId}" id="dataflagfanhui" ><span>返回</span></a>
					</div>
				</div>
				<div class="right"></div>
			</div>
		</c:if>
		<s:if test="#request.nestedflag==1">
			<div class="button_bar">
				<div class="left">
					<a href="###" id="doAdd" onclick="doAdd()" class="pop_button"><span>新增</span></a>
					<a href="###" id="doEdit"  onclick="doEdit()" class="pop_button"><span>编辑</span></a>
					<a href="###" id="doDel"  onclick="doDel()" class="pop_button"><span>删除</span></a>
					<a href="###" id="doSecrecyChange"  onclick="doSecrecyChange()" class="pop_button"><span>密级变更</span></a>
					<a href="###" id="doSecrecyClear"  onclick="doSecrecyClear()" class="pop_button"><span>解除</span></a>
				</div>
				<div class="right">
				</div>
			</div>
		</s:if>

		<!-- 保密要害部位列表  -->
		<div id="body_content"  >
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密要害部位简介" ctx="${ctx}" icoPath="/bmp/keypart/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','保密要害部位简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','保密要害部位搜索');">查 询</div>
			</cp:start>
			<cp:msg show="true" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于保密要害部位
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_key_part"> </cpc:tc>
				</div>

				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
			</cp:msg>
			<cp:search show="false" divId="cp002">
				<form action="<s:url action="part_list_list" namespace="/bmp/part"/>" method="post" id="queryform">
					<input type="hidden" name="nestedflag" id="nestedflag" value="${nestedflag}" />
					<input type="hidden" name="part.department.departmentId" id="part.department.departmentId" value="${part.department.departmentId}" />
					<input type="hidden" name="districtCode" id="districtCode" value="${district.districtCode}"><!-- 行政区划编码 -->
					<input type="hidden" name="ywFlag" id="ywFlag" value="${ywFlag}"><!-- 业务标志 1： 查询页面     0：普通业务模块   -->
					<input type="hidden" name="isChildren" id="isChildren" value="${isChildren}"><!-- 是否包含下级  1包含  0不包含 -->
					<input type="hidden" name="organ.organId" id="organ.organId" value="${organ.organId}"><!-- 单位 -->
					<input type="hidden" name="dataGetFlag" id="dataGetFlag" value="${dataGetFlag}"><!-- 综合统计数据撰取的标志  用于综合统计 -->

					<table class="st" width="100%">
						<tr>
							<td class="tbLable fr">
								部位名称：
							</td>
							<td class="tbValue fl">
								<input type="text" name="part.partName" id="part.partName" value="${part.partName }" />
							</td>
							<td class="tbLable fr">
								涉密等级：
							</td>
							<td class="tbValue fl">
								<dictionary:select tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}" title="true" name="part.secrecyLevel" id="part.secrecyLevel" titleText="全部" style="width:130px;"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								负责人：
							</td>
							<td class="tbValue fl">
								<input type="text" id="part.person.name" name="part.person.name" id="part.person.name" value="${part.person.name}" />
							</td>
							<td class="tbLable fr">
								联系电话：
							</td>
							<td class="tbValue fl">
								<input type="text" name="part.phone" id="part.phone" value="${part.phone}"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="fc" style="border: 0px;">
								<div class="stBtnBar">
									<a class="pop_button" href="javascript:query();"><span>查 询</span></a>
									<a class="pop_button" href="javascript:document.getElementById('queryform').reset();"><span>重 置</span></a>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<c:if test="${ywFlag eq '0'}">
							<c:if test="${dataGetFlag==1}">【${organ.organName}】 - </c:if>
							保密要害部位列表
						</c:if>
						<c:if test="${ywFlag eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${isChildren ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 保密要害部位列表
							</c:if>
							<c:if test="${isChildren eq '1'}">
								${district.districtName} - 保密要害部位列表
							</c:if>
						</c:if>
					</div>
				</div>

				<div class="panel_content panel_content_table"><!-- ${ctx}/bmp/part/part_list_list.action -->
					<s:if test="#request.partList.size>0">
						<ec:table items="partList" var="part" tableId="partList" border="0"
							action="${actionRequestURI}"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="partId" alias="partId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="partName" title="名 称" width="20%" cell="text" alias="size=25"/>
								<ec:column property="department.departmentName" title="主管部位" width="20%" cell="text" alias="size=25"/>
								<ec:column property="null" title="涉密等级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}"></dictionary:text>
									<input type="hidden" name="${part.partId}_reportState" id="${part.partId}_reportState" value="${part.reportState}">
								</ec:column>
								<ec:column property="person.name" title="负责人" width="15%" cell="text" alias="size=20"/>
								<ec:column property="phone" title="联系电话" width="10%" cell="text" alias="size=18"/>
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetail('${part.partId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无数据"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
</body>
