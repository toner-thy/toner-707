<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib prefix="cp" uri="/WEB-INF/tags/cp.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>涉密涉外活动记录列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/platform/theme/borderlayout/skin/blue/css/page.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath }/platform/template/borderlayout/skin/blue/css/ecTable.css">
		<link type="text/css" rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-v1.1.css" />
		<script src="${pageContext.request.contextPath }/resources/js/environment/environment.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/mootools/mootools-core-1.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/mootools/mootools-more-1.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/TabUtils.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/notimoo/notimoo-v1.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
		 <script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/utils.js"></script>

		<s:actionmessage theme="messages"/>

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
	<div class="button_bar">
			<div class="left">
				<ap:operationbutton />
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.location.reload();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div  class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="涉密涉外活动简介" ctx="${ctx}" icoPath="${ctx}/images/technictrain/organAllEpList_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','涉密涉外活动简介');">简 介</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','涉密涉外活动搜索');">搜 索</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					管辖单位涉密涉外活动查询为保密行政管理部门对已上报的涉密涉外活动信息统一管理。
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<s:form action="externalPidgin_organAllEpList" id="externalPidgin_list_form" theme="simple">
						<table align="center" class="content_table">
					        <tr>
								<td style="text-align:right;" width="30%">
									涉密涉外活动名称：
								</td>
								<td style="text-align:left;" width="30%">
									<input name="externalPidgin.eternalPidginName" id="title" type="text" value="${externalPidgin.eternalPidginName}">
								</td>
								<td width="40%">
									<div class="btn_query_bar pop_button_bar">
										<a class="pop_button" href="javascript:document.getElementById('externalPidgin_list_form').submit();"><span>查询</span></a>
									</div>
								</td>
							</tr>
						</table>
						<input type="hidden" name="externalPidgin.organ.organId" value="${externalPidgin.organ.organId }"/>
					</s:form>
				</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<br/>
			<!-- 列表 -->
			<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleListIco">
					涉密涉外活动记录列表
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_btn_bar pop_button_bar">

					</div>
				</div>
				<s:if test="#request.externalPidginList.size>0">
					<ec:table items="externalPidginList" var="externalPidgin" tableId="externalPidginList" border="0"
						action="${pageContext.request.contextPath}/externalPidgin_organAllEpList.action"
						imagePath="${pageContext.request.contextPath}/platform/theme/default/images/table/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="eternalPidginName" title="涉密涉外活动名称"/>
							<ec:column property="organ.organName" title="创建单位名称"/>
							<ec:column property="mainOrgan" title="主办单位"/>
							<ec:column property="null" title="开始时间">
								<div style="font-family: Arial, 'Times New Roman' !important;">
									<s:date name="#attr.externalPidgin.startDate" format="yy年MM月dd日"/>
								</div>
							</ec:column>
							<ec:column property="null" title="结束时间">
								<div style="font-family: Arial, 'Times New Roman' !important;">
									<s:date name="#attr.externalPidgin.endDate" format="yy年MM月dd日"/>
								</div>
							</ec:column>
							<ec:column property="secrecyLevel" title="密 级">
								<dictionary:text fieldCode="secrecy_level" optionValue="${externalPidgin.secrecyLevel}"/>
							</ec:column>
							<ec:column property="null" title="显示详情" width="8%">
								<a href="javascript:doDetail('${pageContext.request.contextPath}/externalPidgin_detail.action','${externalPidgin.externalPidginId}');"><img src="${pageContext.request.contextPath}/platform/theme/default/images/main/display.gif" border="0" title="显示详情"/></a>
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
