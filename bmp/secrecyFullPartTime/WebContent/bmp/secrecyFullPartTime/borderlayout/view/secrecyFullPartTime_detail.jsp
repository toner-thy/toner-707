<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>保密工作专（兼）职人员情况详情</title>

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

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
		</script>

		<script type="text/javascript">
			function doAddTable(){
				window.showModalDialog("secrecyFullPartTime_selectTable.action"
						,{window:window,
						text:'tableText',
						hidden:'tableIds'
						},"dialogWidth=700px;dialogHeight=645px,status=no,directories=no,menubar=no,resizable=yes,scrollbars=no");


			}

			function doCheckTable(){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请选择一项");
					return;
				}
				window.location.href="${ctx}/checkEventTable_list.action?checkEventTable.checkEventTableId="+items[0].value+"&secrecyFullPartTime.id=${secrecyFullPartTime.id}";
			}

			// 下载
			function download(hostId,attachId){
				window.location.href="<s:url action="secrecyFullPartTime_download" includeParams="false"/>?secrecyFullPartTime.id="+hostId
					+"&attachment.attachId="+attachId;
			}

			function del(attachId,hostId){
				window.location.href='<s:url action="secrecyFullPartTime_deleteAttachment" includeParams="false"/>?secrecyFullPartTime.id='+hostId+"&attachment.attachId="+attachId;
			}
		</script>
	</head>
	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar"></div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
				</div>
			</div>
		</div>
	 	<div class="body_content" >
			<!-- 内容panel开始 -->
			<div class="panel">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密工作专（兼）职人员情况详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<!-- 隐藏域 -->
					<input type="hidden" name="secrecyFullPartTime.id" value="${secrecyFullPartTime.id }">
					<input type="hidden" value="${tableText }" name="tableText" id="tableText">
					<input type="hidden" value="${tableIds }" name="tableIds" id="tableIds">
					<table class="content_table">
						<tr>
								<td class="tbLable fr">
									姓名：
								</td>
								<td class="tbValue fl">
								${secrecyFullPartTime.name.name}
								 </td>
							</tr>
							<tr>
								<td class="tbLable fr" >
									职务：
								</td>
								<td class="tbValue fl">
									${secrecyFullPartTime.position}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									文化程度：
								</td>
								<td class="tbValue fl">
								<dictionary:text fieldCode="learning_level" tableCode="person"
								  optionValue="${secrecyFullPartTime.degree}" />
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									从事保密工作年限：
								</td>
								<td class="tbValue fl">
								   ${secrecyFullPartTime.workYear}
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									职务类型：
								</td>
								<td class="tbValue fl">
									    <c:if test="${secrecyFullPartTime.isFull==0}">
									    专职
									    </c:if>
									    <c:if test="${secrecyFullPartTime.isFull==1}">
										 兼职
									    </c:if>
								</td>
							</tr>
								 		<c:if test="${secrecyFullPartTime.isTrain==1}">
							<tr>
								<td class="tbLable fr" width="10%">
									是否接受保密培训：
								</td>
								<td class="tbValue fl">
									 否
								</td>
							</tr>
									    </c:if>
								 		<c:if test="${secrecyFullPartTime.isTrain==0}">
							<tr>
								<td class="tbLable fr" width="10%">
									是否接受保密培训：
								</td>
								<td class="tbValue fl">
									 是
								</td>
							</tr>
							<tr id="secrecyFullPartTime.date">
								<td class="tbLable fr" width="10%">
									接受保密培训时间：
								</td>
								<td class="tbValue fl">
									 <s:date name="secrecyFullPartTime.date" format="yyyy-MM-dd"/>
								</td>
							</tr>
							<tr  id="secrecyFullPartTime.content">
								<td class="tbLable fr">
									接受保密培训内容：
								</td>
								<td class="tbValue fl">
									${secrecyFullPartTime.content}
								</td>
							</tr>
									    </c:if>
					</table>
					<div id="files_list">
					</div>
				</div>
			</div>
		</div>
	</body>
</html>