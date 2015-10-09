<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新手引导</title>
<!-- 复杂表格CSS支持 -->

<!-- 本页CSS/JS -->
<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
<script src="${ctx}/platform/layout/borderlayout/js/TabUtils.js" type="text/javascript"></script>
<script type="text/javascript">
	function openNewTab(url,moduleName){
		if(url==null || url=="" || url=="null"){
			alert("系统未设置跳转链接，请从【功能菜单】中查找模块或者使用【功能导航】查找并修复");
		}else{
		 	TabUtil.openAsTab({
				url : "${ctx}/"+url,
				//url : url,
				title : moduleName,
				onClose : function(){
					window.location.href = "${ctx}/bmp/newbieGuide/newbieGuide_index.action?_tm="+new Date().getTime();
				},
				onClick : function(){
					alert(1);
				}
			});
		}
	}


	TabUtil.closeTab = function(item, freshOpener) {
		var tab = TabUtil.getTabPanel();
		if (!item) item = tab.getActiveItem();
		if (freshOpener) {
			var openWin = tab.getActivingItemContent();
			if (openWin && openWin.refresh) {
				openWin.refresh();
			} else {
				openWin.location.reload();
			}
		}
		tab.close(item);
	};


</script>

<style type="text/css">
	.btns-zone {
	    padding: 15px 0px;
	    text-align: center;
	    border-style: dashed;
	    border-color: #CCC;
	    -moz-border-top-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    border-image: none;
	    border-width: 1px 0px;
	    line-height:3;
	}
	.btns-zone a {
	    background-color: #3ED4E5;
	    border-radius: 5px;
	    color: #FFF;
	    margin: 10px;
	    padding: 10px 10px;
	    text-transform: uppercase;
	    cursor: pointer;
	    word-break:keep-all;
	    white-space:nowrap;
	}
	.btns-zone .btn-nodata {
	    background-color: #F47837;
	}
</style>
</head>
<body style="width: 95%;">
	<div style="width:90%;text-align: center;"><font style="color: orange;font-weight: bold;font-size: x-large;font-stretch: semi-expanded;">业务引导</font></div>
	<div class="btns-zone">
		<c:forEach items="${newbieGuideList }" var="newbieGuide" varStatus="status" >
			<c:if test="${status.index ne 0 }">
				<font style="color: green;">>></font>
			</c:if>
			<c:choose>
				<c:when test="${not empty newbieGuide.dataNum and newbieGuide.dataNum>0 }">
					<a class="" herf="###" onclick="javascript:openNewTab('${newbieGuide.listUrl }', '${newbieGuide.name }')">${newbieGuide.name }</a>
				</c:when>
				<c:otherwise>
					<a class="btn-nodata" herf="###" onclick="javascript:openNewTab('${newbieGuide.addUrl }', '${newbieGuide.name }')">${newbieGuide.name }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<div><font style="color: red;">温馨提示：</font><span style="background-color: #F47837;">&nbsp;&nbsp;&nbsp;&nbsp;</span>表示数据没有填写；<span style="background-color: #3ED4E5;">&nbsp;&nbsp;&nbsp;&nbsp;</span>表示数据已填写；点击图标进入相应操作界面。</div>
	</div>
	<div style="width: 100%;">
		<iframe name="tedt" src="${ctx }/bmp/dataVailidate/dataValidate_index.action" style="width: 100%;height: 560px;overflow-x:hidden;margin-top: 3px;" frameborder="0"></iframe>
	</div>

</body>
</html>