<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/attachment" prefix="attach" %>
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
			function doDownload(attachId){
				var url = $(attachId).value;
				window.open(url);
			}
		</script>
		<div class="notice_div">
			<div style="float: left;width:75%;text-align: left;padding-left:5px;"><font style="color: white;">资料下载</font></div>
			<div style="float: right;width:20%"><a style="float: right;color: white;font-size:14px ;text-decoration: none;" href="###" onclick="openNewPage('${ctx}/bmp/attachment/attachment_indexAll.action')" >更多...</a></div>
		</div>
		<c:set var="count" value="0" />
		<c:set var="countOut" value="0" />
		<div style="font-size:14px ;line-height: 2em;">
			<s:if test="#request.attList.size>0">
				<c:forEach items="${attList }" var="attal">
					<c:set var="countOut" value="${countOut + 1 }" />
					<c:if test="${countOut lt 4 }">
					<div style="font-size: 12px;font-weight: bold;margin-top: 2px;text-align: left;">
						${fn:substring(attal.attachmentListName,0,30)}
						<c:if test="${fn:length(attal.attachmentListName)>30 }">...</c:if>
					</div>
					<div style="width: 100%;">
						<c:forEach items="${attal.attachmentCollect }" var="attach">
							<c:set var="count" value="${count + 1 }" />
							<c:if test="${count lt 5 }">
								<input type="hidden" id="${attach.attachId}" name="${attach.attachId}" value="<attach:url uploadBehavior="bmpUploadBehavior" attachId="${attach.attachId}" />"/>
								<div style="width: 100%;position: relative;padding: 5px 0;border-bottom: 1px solid #000;line-height:18px;height: 25px;">
									<div title="${attach.attachName }" style="position: absolute;left: 5px;">&nbsp;&nbsp;.&nbsp;
										<a style="text-decoration: none;" href='###' onclick="doDownload('${attach.attachId}')">
											${fn:substring(attach.attachName,0,18)}
											<c:if test="${fn:length(attach.attachName)>18 }">...</c:if>
										</a>
									</div>
									<div style="position: absolute;right: 5px;"><s:date name="#attr.attach.createTime" format="yyyy年MM月dd日"/></div>
								</div>
							</c:if>
						</c:forEach>
					</div>
					</c:if>
				</c:forEach>
			</s:if>
		</div>