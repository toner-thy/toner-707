<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<html>
	<head>
		<title>涉密涉外活动记录列表</title>

		<!-- css -->
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/ec.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/pop_button/pop_button.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/notimoo/notimoo-v1.1.css" type="text/css" rel="stylesheet" />

		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/pub.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js"></script>

		<script language="javascript">
			function doAdd(action){
				var form = document.forms[0];
				form.action=action;
				form.submit();
			}

			function doEdit(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项");
					return;
				}else if(items.length>1){
					alert("请选择一项");
					return;
				}
				window.location.href=action+"?id="+items[0].value
			}

			function doDetail(action,externalPidginId){
				var date = new Date();
				window.showModalDialog(action+"?id="+externalPidginId+"&date="+date.getTime(),
					window,"dialogWidth=600px;dialogHeight=500px;status=no;directories=no;menubar=no;resizable=yes;scrollbars=no");
			}


			function doDel(action){
				if(window.confirm("确定删除！！")){
					var items = EcTable.getCheckedItems();
					if(items.length==0){
						alert("请选择一项");
						return;
					}
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}
			function isCheckedOneClass(ckArr){
			    var pkValue = "";
			    var count = 0;
			    for(var i = 0; i < ckArr.length; i++)
			    {
			        var e = ckArr[i];
			        if(e.name != 'idall' && e.type.toLowerCase() == "checkbox")
			        {
			            if(e.checked)
			            {
			                count++;
			                pkValue = e.value;
			                if(count > 1)
			                    break;
			            }
			        }
			    }


			    if(count == 1) {
			        return pkValue;
			    }
			    else if(count == 0){
			        alert(checkFirst);
			        return "";
			    }
			    else if(count > 1){
			        alert(onlyOne);
			        return "";
			    }
			}

			//上报
			function doReport(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择要上报的数据项！");
					return;
				}
				var ids = "";
				items.each(function(item){
					ids += item.value + ',';
				});
				window.location.href = action + '?externalPidginIds=' + ids+"&reportState=2";
			}
		</script>
	</head>

	<body>
	<ap:step></ap:step>
		<div class="body_content">
			<s:actionmessage theme="messages"/>

			<!-- 查询 -->
			<div class="edit_content">
				<div class="edit_title">
					涉密涉外活动查询
				</div>
				<div class="edit_query_bar">
					<s:form action="externalPidgin_organEpList" id="externalPidgin_list_form" theme="simple">
						<label for="externalPidgin.eternalPidginName">涉密涉外活动名称：</label>
						<input name="externalPidgin.eternalPidginName" id="title" type="text" value="${externalPidgin.eternalPidginName}">
						<s:radio list="#{'0':'包含子机构','1':'不含子机构'}" name="showType" value="showType"></s:radio>
						<span class="query_button_bar">
							<span class="pop_button_bar">
								<a href="###" onclick="javascript:document.getElementById('externalPidgin_list_form').submit();" class="pop_button"><span>查询</span></a>
							</span>
						</span>
						<input type="hidden" name="externalPidgin.organ.organId" value="${externalPidgin.organ.organId }"/>
					</s:form>
				</div>
			</div>

			<!-- 列表 -->
			<div class="edit_content" style="margin-top: 10px;">
				<div class="edit_title">
				涉密涉外活动列表
				<span class="button_bar">
					<ap:operationbutton />
				</span>
			</div>
				<s:if test="#request.externalPidginList.size>0">
					<ec:table items="externalPidginList" var="externalPidgin" tableId="externalPidginList" border="0"
						action="${pageContext.request.contextPath}/externalPidgin_organEpList.action"
						imagePath="${pageContext.request.contextPath}/platform/theme/default/images/table/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="eternalPidginName" title="涉密涉外活动名称"/>
							<ec:column property="organ.organName" title="创建单位名称"/>
							<ec:column property="mainOrgan" title="主办单位"/>
							<ec:column property="startDate" title="开始时间"  cell="date" format="yyyy-MM-dd"/>
							<ec:column property="endDate" title="结束时间"  cell="date" format="yyyy-MM-dd"/>
							<ec:column property="secrecyLevel" title="密级">
								<dictionary:text fieldCode="secrecy_level" optionValue="${externalPidgin.secrecyLevel}"/>
							</ec:column>
							<ec:column property="null" title="上报状态">
								<dictionary:text fieldCode="report_state" optionValue="${externalPidgin.reportState}" tableCode="global"/>
							</ec:column>
							<ec:column property="null" title="上报时间">
									${externalPidgin.reportTime }
							</ec:column>
							<ec:column property="null" title="显示详情" width="8%">
								<a href="javascript:doDetail('${pageContext.request.contextPath}/externalPidgin_detail.action','${externalPidgin.externalPidginId}')"><img src="${pageContext.request.contextPath}/platform/theme/default/images/main/display.gif" title="显示详情"/></a>
							</ec:column>
						</ec:row>
					</ec:table>
				</s:if>
				<s:else>
					<styles:nolist/>
				</s:else>
			</div>
		</div>
	</body>
</html>
