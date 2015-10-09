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
		<title>保密要害部门列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js",function(){
				$ENV.onDomReady(function(){
					new SimpleUI.SimpleTab({
						el : 'main_tab',
						onActive : function(item, content, index) {

						},
						onFirstActive : function(item, content, index) {
							new IFrame({
								src : content.get('url'),
								frameborder : 0,
								styles : {
									width : content.getSize().x,
									height: 620
								},
								events : {
									load : function() {
									}
								}
							}).inject(content);
						}
					});
				});
			});

			// 新 增
			function doAdd(action){
				TabUtil.openAsTab({
					url : action,
					title : '保密要害部门-新增',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在新增的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									document.getElementById('keySection_query_form').submit();
								}
							}else {
									return false;
								}
						}else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('keySection_query_form').submit();
							}
						}
					}
				});
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

				TabUtil.openAsTab({
					url : action + "?keySection.keySectionId=" + items[0].value,
					title : '保密要害部门-编辑',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									document.getElementById('keySection_query_form').submit();
								}
							} else {
								return false;
							}
						} else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('keySection_query_form').submit();
							}
						}
					}
				});

			}

			// 删除方法
			function doDel(action) {
				var items = EcTable.getCheckedItems();
				if(items.length==0) {
					alert("请至少选择一项记录！");
					return;
				}

				//判断  是否可以删除，如果是已经  密级变更    密级解除
				var jsonRequest = new Request.JSON({
 					url: '${ctx}/bmp/keySection/ajax_keySection.action?keySectionId='+items[0].value,
 				    onComplete: function(result, text){
 				    	if (result.flag != null && result.flag != "0") {
 				    		alert("删除失败，该记录已经被："+result.message + " 引用。");
 				    		return;

 				    	}else {
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
 				    }
 				}).get({
 					'keySectionId' : items[0].value
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

			//导出
			function doExport(action){
				var frm = document.getElementById("keySection_query_form");
				frm.action = "${ctx}/bmp/keySection/exportExcel_List.action";
				frm.submit();
			}

			//密级变更
			function doSecrecyChange(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}

				$ENV.dialog.open({
					url : action +  "?keySection.keySectionId=" + items[0].value + "&_ts="+new Date().getTime(),
					width : 0.5,
					height : 308,
					title : '要害部门密级变更'
				});
			}

			//解除
			function doSecrecyClear(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}

				$ENV.dialog.open({
					url : action +  "?keySection.keySectionId=" + items[0].value +"&_ts="+new Date().getTime(),
					width : 0.5,
					height : 280,
					title : '要害部门密级解除'
				});
			}


			//刷新页面
			function reload() {
				document.getElementById('keySection_query_form').submit();
			}


			//查看保密要害部门 的明细
			function doDetail(id){
				$ENV.dialog.open({
					url : "${ctx}/bmp/keySection/keySection_detail.action?keySection.keySectionId=" + id +"&_ts="+new Date().getTime(),
					width : 0.8,
					height : 0.8,
					title : '要害部门详情'
				});
			}

			//查询
			function query() {
				var frm = document.getElementById('keySection_query_form');
				frm.submit();
			}

		</script>

		<style type="text/css">
		.panel .panel_content .panel_content_formTable td{
			border:0px;
			border-bottom: 1px dashed #B3C0C8;
			padding: 0;
		}
		.panel .panel_content .panel_content_formTable td span{
			color:white;
		}
		.st td {
		    border-bottom: 1px dashed #B3C0C8;
		    color: #6B6B6B;
		    padding-bottom: 4px;
		    padding-bottom: 4px;
		    padding-top: 4px;
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

	    <!-- 保密要害部门 列表 -->
		<div id="body_content">
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
			</cp:msg>
			<cp:search show="false" divId="cp002">
				<form action="<s:url action="zhtj_OrganDetailList" namespace="/bmp/keySection"/>" method="post" id="keySection_query_form" >
					<input type="hidden" name="districtCode" id="districtCode" value="${district.districtCode}"><!-- 行政区划编码 -->
					<input type="hidden" name="ywFlag" id="ywFlag" value="${ywFlag}"><!-- 业务标志 1： 查询页面     0：普通业务模块   -->
					<input type="hidden" name="isChildren" id="isChildren" value="${isChildren}"><!-- 是否包含下级  1包含  0不包含 -->
					<input type="hidden" name="organId" id="organId" value="${organ.organId}"><!-- 单位 -->
					<input type="hidden" name="dataGetFlag" id="dataGetFlag" value="${dataGetFlag}"><!-- 综合统计数据撰取的标志  用于综合统计 -->

					<table class="st" width="100%">
						<tr>
							<td class="tbLable fr">
								保密要害部门：
							</td>
							<td class="tbValue fl">
								<input type="text" name="keySectionQo.departmentName" id="keySectionQo.departmentName" value="${keySectionQo.departmentName }">
							</td>
							<td class="tbLable fr">
								涉密等级：
							</td>
							<td class="tbValue fl">
								<dictionary:select tableCode="bmp" fieldCode="secrecy_level_section" id="keySectionQo.secrecyLevel" optionValue="${keySectionQo.secrecyLevel }"
								name="keySectionQo.secrecyLevel" title="true"  titleText="全部" style="width:135px;"></dictionary:select>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								负责人：
							</td>
							<td class="tbValue fl">
								<input type="text" name="keySectionQo.principal" id="keySectionQo.principal" value="${keySectionQo.principal}" />
							</td>
							<td class="tbLable fr">
								联系电话：
							</td>
							<td class="tbValue fl">
								<input type="text" name="keySectionQo.phone" id="keySectionQo.phone" value="${keySectionQo.phone }">
							</td>
						</tr>
						<tr>
							<td colspan="4" class="fc" style="border: 0px;">
								<div class="stBtnBar">
									<a class="pop_button" href="javascript:query();"><span>查 询</span></a>
									<a class="pop_button" href="javascript:document.getElementById('keySection_query_form').reset();"><span>重 置</span></a>
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
						【${organ.organName}】 - 保密要害部门列表
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<s:if test="#request.keySectionList.size>0"><s:property value="request.keySectionList.size"/>
						<ec:table items="keySectionList" var="keySection" tableId="keySectionList" border="0"
							action="${actionRequestURI}"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="keySectionId" alias="keySectionId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="department.departmentName" title="保密要害部门" width="20%" cell="text" alias="size=25"/>
								<ec:column property="null" title="涉密等级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_section" optionValue="${keySection.secrecyLevel}"/>
									<input type="hidden" name="${keySection.keySectionId}_reportState" id="${keySection.keySectionId}_reportState" value="${keySection.reportState}">
								</ec:column>
								<ec:column property="principal.name" title="负责人" width="20%" cell="text" alias="size=20"/>
								<ec:column property="phone" title="联系电话" width="10%" cell="text" alias="size=18"/>
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetail('${keySection.keySectionId}');"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无数据"/>
					</s:else>
				</div>
			</div>
		</div>


		<form action="" id="keySectionDelForm" name="keySectionDelForm" method="post">
			<input type="hidden" name="keySectionIds" id="keySectionIds" />
		</form>
	</body>
</html>