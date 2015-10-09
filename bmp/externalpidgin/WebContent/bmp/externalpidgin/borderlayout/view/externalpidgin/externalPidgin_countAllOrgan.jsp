<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>涉密涉外活动记录列表</title>
		
		<!-- css -->	
		<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath }/platform/theme/borderlayout/skin/blue/css/page.css">
		<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath }/platform/template/borderlayout/skin/blue/css/ecTable.css">
		<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-v1.1.css" />
		
		<!-- js -->	
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/pub.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/ectable/EcTable.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/notimoo/notimoo-v1.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>
		<script src="${pageContext.request.contextPath }/resources/js/mootools/mootools-core-1.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath }/resources/js/mootools/mootools-more-1.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/js/notimoo/notimoo-v1.1.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/ectable/EcTable.js" type="text/javascript"></script>

		<s:actionmessage theme="messages"/>
		
		<script type="text/javascript">
			window.addEvent('domready',function(){
				$$('.countType').each(function(item,index){
					if(item.value=='${countType}'){
						item.checked=true;
					}
				});
				if('${countType}'==2){
					$('dname').disabled='disabled';
					$('dname').style.display='none';
					$('dname2').disabled='disabled';
					$('dname2').style.display='none';
					
				}else{
					$('dname').disabled='';
					$('dname').style.display='';
					$('dname2').disabled='';
					$('dname2').style.display='';
				}
			});
			
			//显示单位名称查询
			function doChange(obj)
			{
				if(obj.value==2){
					$('dname').disabled='disabled';
					$('dname').style.display='none';
					$('dname2').disabled='disabled';
					$('dname2').style.display='none';
				}else{
					$('dname').disabled='';
					$('dname').style.display='';
					$('dname2').disabled='';
					$('dname2').style.display='';
				}
			}
		</script>
		
		<style>
			.widthTD{width: 20%;};
        </style>
        
	</head>
	
	<body>
		<div class="button_bar">
			<div class="left">
				<ap:operationbutton />
			</div>
			
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###"><span>刷新本页</span></a>
					<a class="pop_button pop_button_max" href="###"><span>最大化</span></a>
					<a class="pop_button pop_button_help" href="###"><span>本页帮助</span></a>
					<a class="pop_button pop_button_close" href="###"><span>退出本页</span></a>
				</div>
			</div>
		</div>
		<div id="body_content" class="body_content">
			<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleSearchIco">
					涉密涉外活动统计
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_titleBtnBar">
					</div>
				</div>
				
				<div class="panel_content panel_content_table">
					<s:form action="externalPidgin_countAllOrgan" id="externalPidgin_count_list_form" theme="simple">
						<table>
							<tr>
								<td class="widthTD">
									<input type="radio" id="ct1" onclick="doChange(this)" value="1" name="countType" class="countType"/>
									<label for="">按单位统计</label>
									<input type="radio" id="ct2" onclick="doChange(this)" value="2" name="countType" class="countType"/>
									<label for="">按行政区划</label>
								</td>
								<td class="">
									<label for="">开始时间范围：</label>
									<input name="startTime" id="title" type="text" value="${startTime}" class="Wdate" onFocus="WdatePicker();">
									-<input name="endTime" id="title" type="text" value="${endTime}" class="Wdate" onFocus="WdatePicker();">
								</td>
								<td class="widthTD">
									<label id="dname">单位名称：</label>
									<input id="dname2" type="text" name="organName" value="${organName}" size="50px;">
								</td>
								<td class="widthTD">
									<span class="pop_button_bar">
										<a href="###" onclick="javascript:document.getElementById('externalPidgin_count_list_form').submit();" class="pop_button"><span>查询</span></a>				
									</span>
								</td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
			<br>
			<!-- 列表 -->	
			<div class="panel">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleListIco">
					涉密涉外活动统计列表
					</div>
					<!-- 右侧按钮 -->
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content" >
					<s:if test="#request.list.size>0">	
						<div class="eXtremeTable" >
							<table id="list_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
								<thead>
									<tr align="center">
										<td class="tableHeader" >单位/行政区划名称</td>
										<td class="tableHeader" >绝密</td>
										<td class="tableHeader" >机密</td>
										<td class="tableHeader" >秘密</td>
										<td class="tableHeader" >工作秘密</td>
										<td class="tableHeader" >内部</td>
										<td class="tableHeader" >非密</td>
										<td class="tableHeader" >合计</td>
									</tr>
								</thead>
								<tbody class="tableBody">
									<c:set var="sum1" value="0"></c:set>
									<c:set var="sum2" value="0"></c:set>
									<c:set var="sum3" value="0"></c:set>
									<c:set var="sum4" value="0"></c:set>
									<c:set var="sum5" value="0"></c:set>
									<c:set var="sum6" value="0"></c:set>
									<c:set var="sum7" value="0"></c:set>
									<c:forEach items="${list}" var="e">
										<tr align="center">
											<td>${e[1]}</td>
											<td>
												<c:if test="${e[2] != 0}">
													<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.organName=${e[1]}&externalPidginDto.externalPidgin.secrecyLevel=1">${e[2]}</a>		
												</c:if>
												<c:if test="${e[2] == 0}">${e[2]}</c:if>
											</td>
											<td>
												<c:if test="${e[3] != 0}">
													<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.organName=${e[1]}&externalPidginDto.externalPidgin.secrecyLevel=2">${e[3]}</a>		
												</c:if>
												<c:if test="${e[3] == 0}">${e[3]}</c:if>
											</td>
											<td>
												<c:if test="${e[4] != 0}">
													<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.organName=${e[1]}&externalPidginDto.externalPidgin.secrecyLevel=3">${e[4]}</a>		
												</c:if>
												<c:if test="${e[4] == 0}">${e[4]}</c:if>
											</td>
											<td>
												<c:if test="${e[5] != 0}">
													<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.organName=${e[1]}&externalPidginDto.externalPidgin.secrecyLevel=4">${e[5]}</a>		
												</c:if>
												<c:if test="${e[5] == 0}">${e[5]}</c:if>
											</td>
											<td>
												<c:if test="${e[6] != 0}">
													<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.organName=${e[1]}&externalPidginDto.externalPidgin.secrecyLevel=5">${e[6]}</a>		
												</c:if>
												<c:if test="${e[6] == 0}">${e[6]}</c:if>
											</td>
											<td>
												<c:if test="${e[7] != 0}">
													<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.organName=${e[1]}&externalPidginDto.externalPidgin.secrecyLevel=6">${e[7]}</a>		
												</c:if>
												<c:if test="${e[7] == 0}">${e[7]}</c:if>
											</td>
											<td>
												<c:if test="${(e[2]+e[3]+e[4]+e[5]+e[6]+e[7]) lt 1}">
													<font color="blue">${e[2]+e[3]+e[4]+e[5]+e[6]+e[7]}</font>
												</c:if>
												<c:if test="${(e[2]+e[3]+e[4]+e[5]+e[6]+e[7]) ge 1}">
													<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.organName=${e[1]}&externalPidginDto.externalPidgin.secrecyLevel=100">
														<font color="red">${e[2]+e[3]+e[4]+e[5]+e[6]+e[7]}</font>
													</a>
												</c:if>
											</td>
											<c:set var="sum1" value="${sum1+e[2]}"></c:set>
											<c:set var="sum2" value="${sum2+e[3]}"></c:set>
											<c:set var="sum3" value="${sum3+e[4]}"></c:set>
											<c:set var="sum4" value="${sum4+e[5]}"></c:set>
											<c:set var="sum5" value="${sum5+e[6]}"></c:set>
											<c:set var="sum6" value="${sum6+e[7]}"></c:set>
											<c:set var="sum7" value="${sum7+e[2]+e[3]+e[4]+e[5]+e[6]+e[7]}"></c:set>
										</tr>
									</c:forEach>
									<tr align="center">
										<td>合计</td>
										<td>
										<c:if test="${sum1 != 0}">
											<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.externalPidgin.secrecyLevel=1&organNames=${organName}">${sum1 }</a>		
										</c:if>
										<c:if test="${sum1 == 0 }">
											${sum1 }
										</c:if>
										</td>
										<td>
										<c:if test="${sum2 != 0}">
											<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.externalPidgin.secrecyLevel=2&organNames=${organName}">${sum2 }</a>		
										</c:if>
										<c:if test="${sum2 == 0 }">
											${sum2 }
										</c:if>
										</td>
										<td>
										<c:if test="${sum3 != 0}">
											<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.externalPidgin.secrecyLevel=3&organNames=${organName}">${sum3 }</a>		
										</c:if>
										<c:if test="${sum3 == 0 }">
											${sum3 }
										</c:if>
										</td>
										<td>
										<c:if test="${sum4 != 0}">
											<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.externalPidgin.secrecyLevel=4&organNames=${organName}">${sum4 }</a>		
										</c:if>
										<c:if test="${sum4 == 0 }">
											${sum4 }
										</c:if>
										</td>
										<td>
										<c:if test="${sum5 != 0}">
											<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.externalPidgin.secrecyLevel=5&organNames=${organName}">${sum5 }</a>		
										</c:if>
										<c:if test="${sum5 == 0 }">
											${sum5 }
										</c:if>
										</td>
										<td>
										<c:if test="${sum6 != 0}">
											<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.externalPidgin.secrecyLevel=6&organNames=${organName}">${sum6 }</a>		
										</c:if>
										<c:if test="${sum6 == 0 }">
											${sum6 }
										</c:if>
										</td>
										<td>
										<c:if test="${sum7 != 0}">
											<a href="externalPidgin_organAllEpList.action?countType=${countType}&externalPidginDto.externalPidgin.secrecyLevel=1000&organNames=${organName}">${sum7 }</a>		
										</c:if>
										<c:if test="${sum7 == 0 }">
											${sum7 }
										</c:if>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</s:if>
					<s:else>
						<styles:nolist/>
					</s:else>
			</div>
		</div>
	</body>
</html>
