<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>功能导航</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

	<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
 	<script type="text/javascript">
  		$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");

		$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
		$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
			$ENV.onDomReady(function(){

			});
		});
	</script>

    <script type="text/javascript">
		function doRedictUrl(resourceUrl,title){
			var action = "${ctx}" + resourceUrl;
			TabUtil.openAsTab({
				url : action,
				title : title
			});
		}
		function filter(){
			var domainName= document.getElementById("text").value;
			var action = "${ctx }/bmp/helptree/helpTree_filterDaoHangTree.action?domainName="+domainName;
			window.location.href=action;
		}
	</script>
</head>
<body>
<!-- 公共头部 -->
	<div class="button_bar">
		<div class="left">
		</div>
		<div class="right">
			<div class="pop_button_bar">
				<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
				<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
			</div>
		</div>
	</div>
	<div class="body_content">
	       	<div style="font-size: 24px;text-align:right;font-weight: bolder;float: left;width: 60%">系统功能导航</div>
	       	<div style="margin-bottom: 20px;float: left;width: 40%;">
	       		<input type="text" id="text" id="domainName" value="${domainName}" onkeyup="if(event.keyCode==13) { filter();}"/>
				<a class="pop_button" href="###" onclick="filter();"><span>查询</span></a>
	        </div>
			<div style="width:100%;padding:5px;float: left;margin-left: 80px;">
				<c:forEach items="${lists1}" var="domain1">
					<div class="column" style="border:1px solid orange;padding:3px; display: block;  top: 0px; width: 20%;float: left;height: 300px;overflow: auto;margin-right: 10px;margin-top: 10px;">
						<h4 style="color: red;text-align:center;">${domain1.domainName}</h4>
						<ul>
							<c:forEach items="${domain1.list}" var="domain2">
								<li>
								    <h2>${domain2.domainName}：</h2>
									<ul>
									     <c:forEach items="${domain2.list}" var="domain3">
											<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<c:forEach items="${domain3.resources}" var="resource">
													<a href="###" onclick="doRedictUrl('${resource.url }','${domain3.domainName}')">
														${domain3.domainName}
													</a>
												</c:forEach>
											</li>
										</c:forEach>
									</ul>
								</li>
							</c:forEach>
						</ul>
					</div>
<%-- 					<div style="height: 300px;width:20px;float:left;background-image: url('${ctx}/platform/resource1/borderlayout/skin/blue/img/btn/btn_bg_over.gif');"></div> --%>
				</c:forEach>
			</div>
        <div>

        </div>
	</div>
</body>
</html>
