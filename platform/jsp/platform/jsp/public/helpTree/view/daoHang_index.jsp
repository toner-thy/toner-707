<%--     <ul id="tree1" class="mini-tree" url="${ctx}/agenda/agenda/agenda_loadTree.action" style="width:300px;height:300px;padding:5px;" --%>
<%@ page pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>系统使用帮助</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

    <link href="${ctx}/platform/jsp/public/helpTree/resource/css/miniui.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/platform/jsp/public/helpTree/resource/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/platform/jsp/public/helpTree/resource/js/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/platform/jsp/public/helpTree/resource/js/miniui.js" type="text/javascript"></script>

    <script type="text/javascript">
		function doTest(domainId){
			var url = '${ctx }/bmp/helptree/helpTree_content.action?domainId=' + domainId;
			document.getElementById('contentIframe').src = url;
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
				<a class="pop_button pop_button_close" href="###" onclick="javascript:window.close();"><span>退出本页</span></a>
			</div>
		</div>
	</div>
	<div class="body_content">
	    <div style="width:100%;padding:5px;float: left;">
	    <h1>系统使用帮助</h1>
		    <input type="text" id="text" />
			<a class="pop_button" href="###" onclick="filter();"><span>查询过滤</span></a>
		    <ul id="tree1" class="mini-tree" url="${ctx }/bmp/helptree/helpTree_loadDaoHangTree.action" style="width:300px;height:700px;padding:5px;"
		        showTreeIcon="true" textField="name" expandOnLoad="0" allowDrag="true" allowDrop="true" allowLeafDropIn="true"
		        idField="id" parentField="pid" resultAsTree="false">
		    </ul>
	    </div>
        <div>

        </div>
	</div>
</body>
</html>
<script type="text/javascript">
mini.parse();

function filter(){
    var tree = mini.get("tree1")

    var text = document.getElementById("text").value;

    var msgid = mini.loading("数据查询中，请稍后......");
    $.ajax({
        url: "${ctx }/bmp/helptree/helpTree_filterTree.action",
        data: { name: text },
        type: "post",
        success: function (text) {
            var data = mini.decode(text);
            tree.loadList(data);
            tree.expandAll();
            mini.hideMessageBox(msgid);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
        }
    });
}
</script>