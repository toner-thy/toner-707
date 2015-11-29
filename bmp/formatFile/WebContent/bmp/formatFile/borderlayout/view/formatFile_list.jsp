<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<s:actionmessage theme="messages"/>

		<script language="javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){

				});
			});

			//新增
			function doAdd(action){
				window.location.href=action;
			}

			//发送
			function doSend(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				window.location.href=action + "?formatFile.id="+items[0].value + "&_dt="+ new Date().getTime();
			}

			//删除
			function doDelete(action) {
				var items = EcTable.getCheckedItems();
				if(items.length==0) {
					alert("请至少选择一项记录！");
					return;
				}
				if(window.confirm('确定删除所选记录吗？')){
					var ids = "";
					var flag = 1;
					items.each(function(item){
						if(document.getElementById('status_'+item.value).value == 1){
							flag = 0;
							return false;
						}
						ids += item.value + ",";
					});
					if(flag == 1){
						$('deleteIds').value = ids;
						var forms = $('delete_form');
						forms.action = action;
						forms.submit();
					} else {
						alert("已发送的不能删除。");
					}
				}
			}

			//查看文件
			function doDetail(formatFileId){
				$ENV.dialog.open({
					url : '${ctx}/bmp/formatFile/formatFile_detail.action?formatFile.id=' + formatFileId,
					width : 0.8,
					height :  0.9,
					title : '查看'
				});
			}

			//查看接收状态
			function doViewUserInfo(formatFileId){
				window.location.href='${ctx}/bmp/formatFile/formatFile_viewUserInfo.action?formatFile.id=' + formatFileId;
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
			<form action="${ctx}/bmp/formatFile/formatFile_list.action" method="post" id="myform">
				<table width="100%" class="st">
					<tr>
						<td class="tbLable fr">
							名称：
						</td>
						<td class="tbValue fl" colspan="3">
							<input type="text" value="${formatFile.name }" name="formatFile.name" value="${formatFile.name }"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="fc" style="border: 0px;">
							<div class="stBtnBar">
								<a class="pop_button" href="javascript:document.getElementById('myform').submit();"><span>查 询</span></a>
								<a class="pop_button" href="javascript:document.getElementById('myform').reset();"><span>重 置</span></a>
							</div>
						</td>
					</tr>
				</table>
			</form>

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						发送列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.formatFileList.size>0">
						<ec:table items="formatFileList" var="formatFile" tableId="formatFileList" border="0"
								action="${ctx}/bmp/formatFile/formatFile_list.action"
								imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
								width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
								filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="id" alias="id_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="name" title="名称" width="40%"/>
								<ec:column property="null" title="上传时间" width="40%">
									<c:if test="${formatFile.createTime!=null}">
										<div style="font-family: Arial, 'Times New Roman' !important;">
											<s:date name="#attr.formatFile.createTime" format="yyyy年MM月dd日"/>
										</div>
									</c:if>
									<c:if test="${formatFile.createTime==null}">
										暂 无
									</c:if>
								</ec:column>
								<ec:column property="null" title="状态" width="40%">
									<input type="hidden" id="status_${formatFile.id}" value="${formatFile.status}"/>
									<c:if test="${formatFile.status ==0}">
										<font color="red">未发送</font>
									</c:if>
									<c:if test="${formatFile.status ==1}">
										<font color="green"><a href="###" onclick="doViewUserInfo('${formatFile.id}')">已发送</a></font>
									</c:if>
								</ec:column>
								<ec:column property="null" title="查看文件" width="10%">
									<a href='###' onclick="doDetail('${formatFile.id}')">查看</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<styles:nolist/>
					</s:else>

					<form id="delete_form" name="delete_form" method="post">
						<input id="deleteIds" name="deleteIds" type="hidden"/>
					</form>

				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>