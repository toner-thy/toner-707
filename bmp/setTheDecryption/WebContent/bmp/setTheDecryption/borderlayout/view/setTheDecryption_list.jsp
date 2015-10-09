<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>定（解）密工作情况列表</title>

		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
			//新增
			function doAdd(action){
			 	TabUtil.openAsTab({
					url : action+"?list_crd=${param.list_crd}&list_p=${param.list_p}",
					title : '定（解）密工作情况-新增',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								document.getElementById('setTheDecryption_list_form').submit();
							}
						} else {
							return false;
						}
					}else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('setTheDecryption_list_form').submit();
							}
						}
					}
				});
			}

			//编辑
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
					url : action+"?list_crd=${param.list_crd}&list_p=${param.list_p}&setTheDecryption.id="+items[0].value,
					title : '定（解）密工作情况-编辑',
					onClose : function(tab, item) {
					if(!item.content.getContentWindow().needReload2){
						if (window.confirm("您确定放弃正在新增的内容吗？")) {
							if(item.content.getContentWindow().needReload){
								document.getElementById('setTheDecryption_list_form').submit();
							}
						} else {
							return false;
						}
					}else{
							if(item.content.getContentWindow().needReload){
								document.getElementById('setTheDecryption_list_form').submit();
							}
						}
					}
				});


			}

			//删除
			function doDel(action){
				var items = EcTable.getCheckedItems();
					if(items.length==0){
						alert("请选择一项！");
						return;
					}
				if(window.confirm("确定删除吗？")){

					var ids = "?list_crd=${param.list_crd}&list_p=${param.list_p}&";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}

			//详情
			function doDetail(checkEntryId){
			 environment.dialog.open({
					url : '${ctx}/bmp/setTheDecryption/setTheDecryption_detail.action?setTheDecryption.id='+checkEntryId+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '定（解）密工作情况详情'
				});
			}

			//打印
			function doPrint(action){
		    	var item = EcTable.getCheckedItems();
		     	if(item!=null && item!=""){
			    	window.showModalDialog( action+'?setTheDecryption.id='+item[0].value+'&t_time='+new Date().getTime(),window,"dialogWidth=630px;dialogHeight=400px;status=no;directories=no;menubar=no;resizable=yes;crollbars=auto;help=no");
		     	}else{
			    	window.alert("请选择你要打印的申请记录.");
		     	}
       		}

       		//导出
       	  	function doExport(action){
       	     	$('setTheDecryption_list_form').submit();
		     	$('setTheDecryption_list_form').action="<s:url action="setTheDecryption_list.action" includeParams="true"/>";
       	  	}
		</script>
		<s:actionmessage theme="messages"/>
	</head>

	<body>

	    <!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<ap:operationbutton />
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="#"
						onclick="javascript:window.refresh();"><span>刷新本页</span></a> <a
						class="pop_button pop_button_close" href="#"
						onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content" style="width: 99%;">

		   <!-- 复合面板开始 -->
			<cp:start defaultTitle="设备分类简介" ctx="${ctx}" icoPath="">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','定（解）密工作情况简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','定（解）密工作情况搜索');">查 询</div>
			</cp:start>
				<cp:msg show="false" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于定（解）密工作情况简介
					</div>
					<div class="cpMsgContext">
						定（解）密工作情况
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="true" divId="cp002">
					<form action="setTheDecryption_list.action" id="setTheDecryption_list_form" name='queryform'>
						<table class="st" width="100%">

							 <tr>
								<td class="tbLable fr">
									日期：
								</td>
								<td class="tbValue fl">

									<input name="setTheDecryption.date" id="setTheDecryption.date" type="text" value="<s:date name="setTheDecryption.date" format="yyyy-MM-dd"/>" class="Wdate default" onFocus="WdatePicker()" readonly="readonly">
								</td>
								<td class="tbLable fr">
									文件名称：
								</td>
								<td class="tbValue fl">
									<input name="setTheDecryption.name"  id="setTheDecryption.name" type="text" value="${setTheDecryption.name}">
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('setTheDecryption_list_form').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('setTheDecryption_list_form').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 设备信息列表 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						本单位定（解）密工作情况列表
					</div>
				</div>
				<div class="panel_content">
					<!-- 数据列表 -->
				<s:if test="#request.list.size>0">
					<ec:table items="list" var="setTheDecryption" tableId="list" border="0"
						action="${ctx}/bmp/setTheDecryption/setTheDecryption_list.action"
						imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="id" alias="id_checkbox" cell="checkbox" headerCell="checkbox"/>
							<ec:column property="name" title="文件名称">
								<div title="${setTheDecryption.name}">
									${fn:substring(setTheDecryption.name, 0, 12)}
									<c:if test="${fn:length(setTheDecryption.name)>13}">...</c:if>
								</div>
							</ec:column>
							<ec:column property="date" title="日期" cell="date" format="yyyy-MM-dd" parse="yyyy-MM-dd"/>
							<ec:column property="null" title="文号">
								<div title="${setTheDecryption.docNumber}">
									${fn:substring(setTheDecryption.docNumber, 0, 20)}
									<c:if test="${fn:length(setTheDecryption.docNumber)>20}">...</c:if>
								</div>
							</ec:column>
							<ec:column property="null" title="数量">
								<div title="${setTheDecryption.number}">
									${fn:substring(setTheDecryption.number, 0, 20)}
									<c:if test="${fn:length(setTheDecryption.number)>20}">...</c:if>
								</div>
							</ec:column>
							<ec:column property="null" title="密级">
							<dictionary:text fieldCode="secrecy_level_thing" optionValue="${setTheDecryption.secrecyLevel}" tableCode="bmp"></dictionary:text>
							</ec:column>
							<ec:column property="null" title="事项类型">
									<c:if test="${setTheDecryption.secrecyType==1}">解密事项</c:if>
									<c:if test="${setTheDecryption.secrecyType==0}">定密事项</c:if>
							</ec:column>
							<ec:column property="null" title="载体形式">
								<div title="${setTheDecryption.carrierType}">
									${fn:substring(setTheDecryption.carrierType, 0, 20)}
									<c:if test="${fn:length(setTheDecryption.carrierType)>20}">...</c:if>
								</div>
							</ec:column>
							<ec:column property="null" title="显示详请">
								<a href="###" onclick="doDetail('${setTheDecryption.id}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
							</ec:column>
						</ec:row>
					</ec:table>
				</s:if>
				<s:else>
					<u:noData text="当前暂无信息。"/>
				</s:else>
			</div>
			</div>
		</div>
	</body>
</html>