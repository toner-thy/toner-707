<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap" %>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="ui" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>BMS使用情况</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<style type="text/css">
			.timeBtnBlue{
				padding:5px;
				background-color: #37A2DA;
				color: white;
				font-weight: bold;
				cursor: pointer;
			}
			.timeBtnOrange{
				padding:5px;
				background-color: orange;
				color: white;
				font-weight: bold;
				cursor: pointer;
			}

		</style>

		<script src="${pageContext.request.contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/template/borderlayout/resources/js/ectable/EcTable.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/resources/js/platform.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/SimpleUI/SimpleUI.js", function(){
			$ENV.onDomReady(function(){
					var timeBtnSelect = "${timeBtnSelect}";
					if( timeBtnSelect!=null && timeBtnSelect!="timeBtnOrange" ){
						$(timeBtnSelect).set("class", "timeBtnOrange");
					}
			});
		});

		function show(id){
			$ENV.dialog.open({
				title : '组织机构详情',
				url : "${pageContext.request.contextPath}/platform/organ/organ_detail.action?organ.organId=" + id + "&_ts="+new Date().getTime(),
				width : 0.8,
				height : 0.8
			});
		}

		function organStatInfo(id){
			window.parent.location.href="${pageContext.request.contextPath}/bmp/statinfo/statinfo_index.action?organ.organId="+id+ "&flag=1&_ts="+new Date().getTime();
		}

		function selectMe(obj){
			$$("a[name='timeBtn']").set("class","timeBtnBlue");
			$(obj).set("class","timeBtnOrange");
			var returnValue = "";
			switch ($(obj).get("id")){
				case '7d' :
					returnValue = addDay(-7);
					break;
				case '1m' :
					returnValue = addMonth(-1);
					break;
				case '3m' :
					returnValue = addMonth(-3);
					break;
				case '6m' :
					returnValue = addMonth(-6);
					break;
				case '1y' :
					returnValue = addYear(-1);
					break;
			}
			$("lastLoginTime").set("value", returnValue);
			$("timeBtnSelect").set("value", $(obj).get("id"));
		}

		function addDay(day ){
			 var d=new Date();
		     d.setDate(d.getDate()+day);
		     var m=d.getMonth()+1;
		     return d.getFullYear()+'-'+m+'-'+d.getDate();
		}
		function addMonth(month ){
			 var d=new Date();
			 d.setMonth(d.getMonth()+month);
		     var m=d.getMonth()+1;
		     return d.getFullYear()+'-'+m+'-'+d.getDate();
		}
		function addYear(year ){
			 var d=new Date();
			 d.setFullYear(d.getFullYear()+year);
		     var m=d.getMonth()+1;
		     return d.getFullYear()+'-'+m+'-'+d.getDate();
		}
		</script>
	</head>
	<body>
		<div class="panel">
			<%-- 头部 --%>
			<div class="panel_header">
				<%-- 标题 --%>
				<div class="panel_title panel_search_ico">
					<!--判断是否查看下级1查看，0不查看-->
					<c:if test="${checkLower ne '1'}">
						${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - BMS使用情况列表
					</c:if>
					<c:if test="${checkLower eq '1'}">
						${district.districtName} - BMS使用情况列表
					</c:if>
				</div>
				<%-- 右侧按钮 --%>
				<div class="panel_btn_bar pop_button_bar">
				</div>
			</div>
			<%-- 内容 --%>
			<div class="panel_content">
				<s:form action="newbieGuide_mainList.action" namespace="/bmp/newbieGuide" name="query_organ_form" id="query_organ_form" theme="simple">
					<input name="district.districtCode" type="hidden" value="${district.districtCode }"/>
					<input name="checkLower" type="hidden" value="${checkLower }"/>
					<table class="panel_content_search_form">
						<tr>
							<td style="text-align: right;width: 20%">
								<label for="organName">状态：</label>
							</td>
							<td style="text-align: left;width: 30%">
								<s:select list="#{'0':'未使用','1':'使用'}"
											style="width:105px;"
											headerValue="全部"
											theme="simple"
											headerKey=""
											name="bmBrowser.state"
											id="bmBrowser.state">
										</s:select>
							</td>
							<td>
								<a class="timeBtnBlue" onclick="selectMe(this)" name="timeBtn" id="7d" >最近七天</a>
								<a class="timeBtnBlue" onclick="selectMe(this)" name="timeBtn" id="1m">近一个月</a>
								<a class="timeBtnBlue" onclick="selectMe(this)" name="timeBtn" id="3m">近三个月</a>
								<a class="timeBtnBlue" onclick="selectMe(this)" name="timeBtn" id="6m">近六个月</a>
								<a class="timeBtnBlue" onclick="selectMe(this)" name="timeBtn" id="1y">近一年</a>
								<input type="hidden" id="lastLoginTime" name="bmBrowser.lastLoginTime" value=""/>
								<input type="hidden" id="timeBtnSelect" name="timeBtnSelect" value=""/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;width: 20%">
								<label for="organName">组织机构名称：</label>
							</td>
							<td style="text-align: left;width: 30%">
								<input type="text" name="bmBrowser.organ.organName" id="bmBrowser.organ.organName" value="${organ.organName}" />
							</td>
							<td>
								<div class="btn_query_bar pop_button_bar">
									<a class="pop_button" href="javascript:document.getElementById('query_organ_form').submit();"><span>查询</span></a>
								</div>
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
		<br/>
		<div class="panel">
			<%-- 头部 --%>
			<div class="panel_header">
				<%-- 标题 --%>
				<div class="panel_title panel_list_ico">
					<!--判断是否查看下级1查看，0不查看-->
					<c:if test="${checkLower ne '1'}">
						${district.districtName}<dis:levelText district="${district}"></dis:levelText>级机关单位 - BMS使用情况列表
					</c:if>
					<c:if test="${checkLower eq '1'}">
						${district.districtName} - BMS使用情况列表
					</c:if>
				</div>
				<%-- 右侧按钮 --%>
				<div class="panel_btn_bar pop_button_bar" style="font-weight: bolder;">

				</div>
			</div>
			<div class="panel_content">
				<s:if test="#request.bmBrowserList.size>0">
					<ec:table items="bmBrowserList" var="bmBrowser" tableId="bmBrowserList" border="0"
						action="${ctx}/bmp/newbieGuide/newbieGuide_mainList.action?checkLower=${checkLower}"
						imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
						width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
						filterable="false" autoIncludeParameters="true" sortable="false">
						<ec:row>
							<ec:column property="organName" width="30%" title="单位名称"/>
							<ec:column property="null" width="20%" title="状态">
								<c:if test="${bmBrowser.state == 1 }"><span style="font-weight: bolder;color:green">使用</span></c:if>
								<c:if test="${bmBrowser.state == 0 }"><span style="font-weight: bolder;color:red">未使用</span></c:if>
							</ec:column>
							<ec:column property="null" width="20%" title="最后登陆时间">
								<s:date name="#attr.bmBrowser.lastLoginTime" format="yyyy-MM-dd HH:mm:ss"/>
							</ec:column>
							<ec:column property="userAgent" width="20%" title="最后登陆浏览器"/>
<%-- 							<ec:column property="null"  width="10%" title="详 情"> --%>
<%-- 								<a href="javascript:doDetail('${id}')"> --%>
<%-- 									<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" alt ="详情" > --%>
<!-- 								</a> -->
<%-- 							</ec:column> --%>
						</ec:row>
					</ec:table>
				</s:if>
				<s:else>
					<ui:noData text="当前暂无数据"/>
				</s:else>
			</div>
		</div>
	</body>
</html>