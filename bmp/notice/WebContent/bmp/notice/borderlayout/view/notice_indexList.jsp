<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" />
<script src="${pageContext.request.contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>
<script type="text/javascript">
$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-core-1.4.js");
$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/mootools/mootools-more-1.4.js", function() {
});
</script>
		<style type="text/css">
			.notice_div{
				background-color: #66B9D7;
				width: 100%;
				height: 30px;
				display: block;
				vertical-align: middle;
				padding-top:10px;
				font-size:16px ;
			}
		</style>

		<script language="javascript">
			//打开新页面
			function openNewPage(url){
				window.open(url);
			}
			//打开新页面显示明细
			function doDetail( id ){
				var date = new Date();
				var action = "${pageContext.request.contextPath}/bmp/notice/notice_detail.action?id="+id+"&date="+date.getTime();
				window.open(action);
			}
		</script>
		<div class="notice_div">
			<div style="float: left;width:75%;text-align: left;padding-left:5px;"><font style="color: white;">通知公告</font></div>
			<div style="float: right;width:20%"><a style="float: right;color: white;font-size:14px ;text-decoration: none;" href="###" onclick="openNewPage('${ctx}/bmp/notice/notice_indexAll.action')" >更多...</a></div>
		</div>
		<div style="font-size:14px ;">
			<s:if test="#request.noticeList.size>0">
				<c:forEach items="${noticeList }" var="notice">
					<div style="width: 100%;position: relative;padding: 5px 0;border-bottom: 1px solid #000;line-height:18px;height: 25px;">
						<div title="${notice.noticeName }" style="position: absolute;left: 5px;">&nbsp;&nbsp;.&nbsp;
							<a style="text-decoration: none;" href="###" onclick="doDetail('${notice.noticeId }')">
								 ${fn:substring(notice.noticeName,0,20)}
								<c:if test="${fn:length(notice.noticeName)>20 }">...</c:if>
							</a>
						</div>
						<div style="position: absolute;right: 5px;"><s:date name="#attr.notice.noticePublishDate" format="yyyy年MM月dd日"/></div>
					</div>
				</c:forEach>
			</s:if>
		</div>
