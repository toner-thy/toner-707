<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary"
	prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/district" prefix="dis"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密安全产品列表</title>
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
			function doAdd(action){
				window.location.href = action;
			 }
			function doImport(action){
				window.location.href = action;
			 }

			 function doShow(action,id){
				window.showModalDialog(action + '?id='+id
					 	,window
					 	,"dialogWidth=600px;dialogHeight=400px,status=no,directories=no,menubar=no,resizable=yes,scrollbars=auto");
			 }

			 // 审批一条申请记录
			 function doDetail(action,id){
			 	environment.dialog.open({
					url : action+'?id='+id,
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.6,
					title : '产品工具详情'
				});
			 }

			 function doUse(action,id){
			 	var items = EcTable.getCheckedItems();
				window.location.href = action + '?id=' + id;
			 }

			 function doEdit(action){
			 	var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}
			 	window.location.href = action+'?id='+items[0].value;
			 }

			function doEdit2(action,id){
				document.location.href=action+'?id='+id;
			}

			function doDel(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择删除的项？");
					return;
				}
				if(window.confirm("确定删除吗？")){

					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<ap:operationbutton />
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="产品工具查询简介" ctx="${ctx}" icoPath="ddd">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','产品工具查询简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','产品工具搜索');">查 询</div>
			</cp:start>
			<cp:msg show="false" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于产品工具查询
				</div>
				<div class="cpMsgContext">
					产品工具查询为您维护产品工具提供服务。这里你可以导入产品工具。
				</div>

				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
			</cp:msg>
			<cp:search show="true" divId="cp002">
				<form action="product_list.action" method="post" id="documentForm">

					 <table class="st" width="100%">
						<tr>
							<td class="tbLable fr">证书编号：</td>
							<td class="tbValue fl">
								<input type="text" name="product.certificateCode" value="${product.certificateCode}" />
							</td>
							<td class="tbLable fr">产品工具名称：</td>
							<td class="tbValue fl">
								<input type="text" name="product.productName" value="${product.productName }" />
							</td>

						</tr>
						<tr>
							<td class="tbLable fr">送测单位：</td>
							<td class="tbValue fl">
								<input type="text" name="product.inspectCenter" value="${product.inspectCenter }" />
							</td>
							<td class="tbLable fr">产品工具类别：</td>
							<td class="tbValue fl" >
								<dictionary:select fieldCode="product" tableCode="bmp" title="true" titleText="请选择" id="product.productType" optionValue="${product.productType}"  name="product.productType" ></dictionary:select>
							<td>
						</tr>
						<tr>
							<td class="tbLable fr">送测单位地址：</td>
							<td class="tbValue fl">
								<input type="text" name="product.inspectCenterAddress" value="${product.inspectCenterAddress }" />
							</td>
							<td class="tbLable fr">通过时间：</td>
							<!--
							时间判断在IE上不起效；
							 -->
							<td class="tbValue fl">
								<input type="text" id="startTime" name="startTime" value="<fmt:formatDate pattern="yyyy-MM-dd" value='${startTime}'/>" title="通过时间" class="Wdate " onFocus="WdatePicker()" readonly="readonly" />
								到
								<input type="text" id="endTime" name="endTime" value="<fmt:formatDate pattern="yyyy-MM-dd" value='${endTime}'/>" class="Wdate validate['dateAfter[startTime]']" onFocus="WdatePicker()" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td colspan="4" class="fc" style="border: 0px;">
								<div class="btn_query_bar pop_button_bar">
									<a class="pop_button" href="javascript:document.getElementById('documentForm').submit();"><span>查 询</span></a>
									<a class="pop_button" href="javascript:document.getElementById('documentForm').reset();"><span>重 置</span></a>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密安全产品列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<s:if test="#request.productList.size>0">
						<ec:table items="productList" var="product" tableId="productList"
							action="${ctx}/bmp/product/product_list.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="productId" alias="productId_checkbox" cell="checkbox" headerCell="checkbox" sortable="false" />
								<ec:column property="null" style="padding-left:8px;padding-right:8px;" title="证书编号">
									<c:if test="${product.certificateCode==''}">
										暂未填写
									</c:if>
									<c:if test="${product.certificateCode!=''}">
										${product.certificateCode}
									</c:if>
								</ec:column>
								<ec:column property="null" title="产品工具类别" width="17%">
								<dictionary:text fieldCode="product" tableCode="bmp" optionValue="${product.productType}"  ></dictionary:text>
								</ec:column>

								<ec:column property="productName" style="text-align:center;line-height:20px;padding-left:8px;padding-right:8px;" title="产品工具名称"/>
								<ec:column property="null" title="通过时间" width="10%">
									<c:if test="${product.passTime==null}">
										暂无
									</c:if>
									<c:if test="${product.passTime!=null}">
										<fmt:formatDate pattern="yyyy-MM-dd" value='${product.passTime}'/>
									</c:if>
								</ec:column>
								<ec:column property="null" style="line-height:20px;padding-left:8px;padding-right:8px;" title="厂家名称">
									<c:if test="${product.manufacturerName==''}">
										暂无
									</c:if>
									<c:if test="${product.manufacturerName!=''}">
										${product.manufacturerName}
									</c:if>
								</ec:column>
								<ec:column property="null" width="5%" title="详 情">
									<a href="###" onclick="doDetail('${ctx}/bmp/product/product_detail.action','${product.productId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情" />
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
							<u:noData text="暂无数据！"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束-->
		</div>
	</body>
</html>