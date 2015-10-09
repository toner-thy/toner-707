<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<title>如何预订票券产品</title>
<link href="${ctx }/platform/jsp/public/questionAndAnswer/help/style.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx }/platform/jsp/public/questionAndAnswer/help/Tail_height.css" />
<script src="${ctx}/platform/jsp/public/helpTree/resource/js/jquery-1.6.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("a[name='selectBtn']").first().click();
		$("a[name='selectBtn']").first().find("font").first().addClass("chengse");
	});

	function showDetail(obj,id){
		$.ajax({
			   type: "POST",
			   url: "${ctx}/bmp/sysQuestion/sysQuestion_findAnswer.action",
			   data: "id="+id,
			   success: function(data){
			     $("#questionTitle").html("<p> </p>");
			     $("#questionTitle").html(data.questionTitle);
			     $("#answerContent").html("<p> </p>");
			     $("#answerContent").html(data.answers);
			     $("a[name='selectBtn']").find("font").removeClass("chengse");
			     $(obj).find("font").first().addClass("chengse");
			   }
			});

	}
</script>
<style type="text/css">
		.ko {
			padding: 12px 12px 0;
		}
		#answerContent {
			font-family: arial, verdana, sans-serif;
			font-size: 14px;
			line-height: 1.666;
			margin: 0;
			min-height: 100px;
			overflow: auto;
			padding: 0;
			white-space: normal;
			word-wrap: break-word;
			color: black;
			background-color: white;
		}
		#answerContent ul,ol{
			margin-left: 40px;
		}
		#answerContent ol li{
			list-style-type:decimal;
			display: list-item;
		}
		#answerContent ul li{
			list-style-type: disc;
			display: list-item;
		}
		</style>

</head>
<body>
<!--head-->
<div id="main">
  <div id="topnav"></div>
  <div id="content">
    <div id="leftside">
      <div class="pp"><img src="${ctx }/platform/jsp/public/questionAndAnswer/help/help.jpg" /></div>
      <div class="bg">
<%--       	<img src="${ctx }/platform/jsp/public/questionAndAnswer/help/jianhao.jpg" /> --%>
      	常见问题问答
      </div>
      <ul class="fuwu">
			<c:forEach items="${questionList }" var="dataRow" >
				<li ><a href="###" name="selectBtn" onclick="showDetail(this,'${dataRow.questionId}')"><font>
					<c:choose>
					    <c:when test="${fn:length(dataRow.questionTitle) > 10}">
					        <c:out value="${fn:substring(dataRow.questionTitle, 0, 10)}..." />
					    </c:when>
					   <c:otherwise>
					      <c:out value="${dataRow.questionTitle}" />
					    </c:otherwise>
					</c:choose>
				</font></a></li>
			</c:forEach>
      </ul>
    </div>
    <div id="rightside">
      <div id="wenti">
        <ul>
          <li class="kuang01">常见问题问答</li>
        </ul>
      </div>
      <div class="gray_bj">&nbsp;&nbsp;<span id="questionTitle"></span></div>
      <div class="ko">
     	<div id="answerContent" class="nui-fClear pm">
     	</div>
      </div>
      </div>
  </div>
</div>


</body></html>