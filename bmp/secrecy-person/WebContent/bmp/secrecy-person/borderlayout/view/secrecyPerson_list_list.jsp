<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密人员列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

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

			// 查看详情
			function doDetail(secrecyPersonId){
				$ENV.dialog.open({
					title : '涉密人员详情',
					url : '${ctx}/bmp/secrecyperson/secrecyPerson_detail.action?secrecyPerson.secrecyPersonId='+secrecyPersonId+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
			}

			//新增
			function doAdd(){
			   TabUtil.openAsTab({
					url : "${contextPath}/bmp/secrecyperson/secrecyPerson_add.action?&departmentId=${secrecyPerson.department.departmentId}&partId=${partId}&nestedflag=1",
					title : '机关涉密人员-新增',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在新增的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									window.location.reload();
								}
							}else {
									return false;
								}
						}else{
							if(item.content.getContentWindow().needReload){
								window.location.reload();
							}
						}
					}
				});
			}

			//编辑
			function doEditSecPerson() {
				var items = EcTable.getCheckedItems("secrecyPersonList_table");
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				TabUtil.openAsTab({
					url : "${contextPath}/bmp/secrecyperson/secrecyPerson_edit.action?&secrecyPerson.secrecyPersonId="+items[0].value+"&departmentId=${secrecyPerson.department.departmentId}&partId=${partId}&nestedflag=1",
					title : '机关涉密人员-编辑',
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
			function doDeleteSecPerson(){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要删除的项。");
					return;
				}

				if (window.confirm('确定要删除吗？ ' )) {
					var ids = "";
					items.each(function(item){
						ids += item.value + ",";
					});
					new Request.JSON({
					    url: "${contextPath}/bmp/secrecyperson/nested/secrecyPersonList_delete.action?&nestedflag=1",
					    onComplete: function(result, text){
					    	alert(result.message);
					    	if (result.success) {
					    		window.refresh();
					    	}
					    }
					}).get({
						secrecyPersonIds : ids
					});
				}
			}

			//密级变更
			function doSecrecyChange1() {
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}

				$ENV.dialog.open({
					url : "${contextPath}/bmp/secrecyperson/secrecyPerson_secrecyLevelChange.action?secrecyPerson.secrecyPersonId="+items[0].value+"&$_ts="+new Date().getTime(),
					width : 0.5,
					height : 340,
					title : '密级变更'
				});
			}

			//脱密
			function doLeaveDuty() {
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}

				$ENV.dialog.open({
					url : "${contextPath}/bmp/secrecyperson/secrecyPerson_decryption.action?secrecyPerson.secrecyPersonId="+items[0].value+"&$_ts="+new Date().getTime(),
					width : 0.5,
					height : 320,
					title : '解除'
				});
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<s:if test="#request.nestedflag==1">
			<div class="button_bar">
				<div class="left">
					<a href="###" id="doAddSecPerson" onclick="doAdd()"  class="pop_button"><span>新增</span></a>
					<a href="###" id="doEditSecPerson" onclick="doEditSecPerson()"  class="pop_button"><span>编辑</span></a>
					<a href="###" id="doDeleteSecPerson"  onclick="doDeleteSecPerson()"  class="pop_button"><span>删除</span></a>
					<a href="###" id="doSecrecyChange1"  onclick="doSecrecyChange1()"  class="pop_button"><span>密级变更</span></a>
					<a href="###" id="doLeaveDuty"  onclick="doLeaveDuty()"  class="pop_button"><span>脱密</span></a>
				</div>
				<div class="right">
				</div>
			</div>
		</s:if>
		<c:if test="${not empty zhtj and zhtj eq '1' }">
			<div class="button_bar">
				<div class="left">
					<div class="pop_button_bar">
						<a class="pop_button" href="${ctx}/platform/stat/statFramework_organDetail.action?organId=${organ.organId}" id="dataflagfanhui" ><span>返回</span></a>
					</div>
				</div>
				<div class="right"></div>
			</div>
		</c:if>
		<!-- 公共头部 -->
		<div class="body_content" >
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密人员简介" ctx="${ctx}" icoPath="/bmp/secrecy-person/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','涉密人员简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','涉密人员查询');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于涉密人员
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_person"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<s:form action="secrecyPerson_list_list.action" method="post" id="secrecyPersonForm" theme="simple">
						<input type="hidden" name="districtCode" id="districtCode" value="${districtCode }" />
						<input type="hidden" id="checkLower" name="checkLower" value="${checkLower}" />
						<input type="hidden" id="fromQuery" name="fromQuery" value="${fromQuery}" />
						<input type="hidden" id="nestedflag" name="nestedflag" value="${nestedflag}" />
						<input type="hidden" id="partId" name="partId" value="${partId}" />

						<table class="st" width="100%">
							<tr>
								<td class="tbLable fr">姓 名：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.userInfo.name" name="secrecyPerson.userInfo.name" value="${secrecyPerson.userInfo.name}" />
								</td>
								<td class="tbLable fr">涉密等级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_person" id="secrecyPerson_secrecyPersonLevel" name="secrecyPerson.secrecyPersonLevel" title="true" titleText="全部" style="width: 130px;" optionValue="${secrecyPerson.secrecyPersonLevel}"></dictionary:select>
									<%-- <select id="secrecyPerson_secrecyPersonLevel" name="secrecyPerson.secrecyPersonLevel" style="width: 129px;">
										<option value="4">全部</option>
										<option value="3">一般</option>
										<option value="2">重要</option>
										<option value="1">核心</option>
									</select> --%>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">部 门：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.department.departmentName" name="secrecyPerson.department.departmentName" value="${secrecyPerson.department.departmentName}" />
									<input type="hidden" id="secrecyPerson.department.departmentId" name="secrecyPerson.department.departmentId" value="${secrecyPerson.department.departmentId}" />
								</td>
								<td class="tbLable fr">职 务：</td>
								<td class="tbValue fl">
									<input type="text" id="secrecyPerson.officeDuty" name="secrecyPerson.officeDuty" value="${secrecyPerson.officeDuty}" />
								</td>
							</tr>
							<tr>
								<td colspan="4" class="fc" style="border: 0px;">
									<div class="stBtnBar">
										<a class="pop_button" href="javascript:document.getElementById('secrecyPersonForm').submit();"><span>查 询</span></a>
										<a class="pop_button" href="javascript:document.getElementById('secrecyPersonForm').reset();"><span>重 置</span></a>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						<!--判断查询列表过来的1查看保密局，0不是查询列表过来的查看本单位 -->
						<c:if test="${fromQuery ne '1'}">
							涉密人员列表
						</c:if>
						<c:if test="${fromQuery eq '1'}">
							<!--判断是否查看下级1查看，0不查看-->
							<c:if test="${checkLower ne '1'}">
								${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - 涉密人员列表
							</c:if>
							<c:if test="${checkLower eq '1'}">
								${district.districtName} - 涉密人员列表
							</c:if>
						</c:if>

					</div>
				</div>
				<div class="panel_content panel_content_table">
					<s:if test="#request.secrecyPersonList.size>0">
						<ec:table items="secrecyPersonList" var="secrecyPerson" tableId="secrecyPersonList" border="0"
							action="${actionRequestURI}"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row >
								<ec:column property="secrecyPersonId" alias="secrecyPersonId_checkbox" cell="checkbox" headerCell="checkbox" width="5%"/>
								<ec:column property="userInfo.name" title="姓 名" width="20%" cell="text"/>
								<ec:column property="null" title="涉密等级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${secrecyPerson.secrecyPersonLevel}"/>
									<input type="hidden" name="${secrecyPerson.secrecyPersonId}_reportState" id="${secrecyPerson.secrecyPersonId}_reportState" value="${secrecyPerson.reportState}">
								</ec:column>
								<!-- 使用系统人员中的部门 -->
								<ec:column property="null" title="部 门" width="20%" cell="text">
									<c:if test="${secrecyPerson.department.departmentName == null}">
										<div style="color: red">
											暂未填写
										</div>
									</c:if>
									<c:if test="${secrecyPerson.department.departmentName != null}">
										${secrecyPerson.department.departmentName}
									</c:if>
								</ec:column>
								<ec:column property="null" title="职 务" width="20%" cell="text">
									<c:if test="${secrecyPerson.officeDuty ==null}">
										<div style="color: red">
											暂未填写
										</div>
									</c:if>
									<c:if test="${secrecyPerson.officeDuty!= null}">
										${secrecyPerson.officeDuty}
									</c:if>
								</ec:column>
								<ec:column property="null" title="办公室电话" width="10%" cell="text">
									<c:if test="${empty secrecyPerson.officePhone}">
										<div style="color: red">
											暂无
										</div>
									</c:if>
									<c:if test="${secrecyPerson.officePhone != ''}">
										${secrecyPerson.officePhone}
									</c:if>
								</ec:column>
								<ec:column property="null" title="详 情" width="10%">
									<a href="###" onclick="doDetail('${secrecyPerson.secrecyPersonId}')"><img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="当前暂无涉密人员"/>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>