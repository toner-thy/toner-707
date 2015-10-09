<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<title>如何预订票券产品</title>
<link href="/bmp/platform/jsp/public/questionAndAnswer/help/style.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="/bmp/platform/jsp/public/questionAndAnswer/help/Tail_height.css" />
<script
	src="/bmp/platform/jsp/public/helpTree/resource/js/jquery-1.6.2.min.js"
	type="text/javascript"></script>
<link href="/bmp/bmp/technologyDefence/js/layout.css" rel="stylesheet" type="text/css"/>
<link href="/bmp/bmp/technologyDefence/js/bluegray.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
	$(function() {
		$("a[name='selectBtn']").first().click();
		$("a[name='selectBtn']").first().find("font").first().addClass(
				"chengse");
	});
	function showDetail(obj, id) {
		$.ajax({
			type : "POST",
			url : "/bmp/bmp/sysQuestion/sysQuestion_findAnswer.action",
			data : "id=" + id,
			success : function(data) {
				$("#questionTitle").html("<p> </p>");
				$("#questionTitle").html(data.questionTitle);
				$("#answerContent").html("<p> </p>");
				$("#answerContent").html(data.answers);
				$("a[name='selectBtn']").find("font").removeClass("chengse");
				$(obj).find("font").first().addClass("chengse");
			}
		});

	}
	function check_radio(id){
		var chkObjs = id.name;
		if($("input[name='"+chkObjs+"']:checked")){
			$("input[name='"+chkObjs+"']:checked").next().next().show();
		}
		}
</script>
<style type="text/css">

.survey-top {
    background-image: url("/bmp/bmp/technologyDefence/images/menu_bj.jpg");
    background-repeat: repeat-x;
    font-size: 14px;
    font-weight: bold;
    height: 25px;
    padding-top: 6px;
    text-align: left;
    text-indent: 10px;
    width:900px;
}
.subject{
 	font-weight: bold;
}

</style>

</head>
<body>
	<!--head-->
	<div id="main">
		<div id="topnav"></div>
		<div id="content">
			<div id="leftside">
				<!-- <div class="pp">
					<img src="/bmp/platform/jsp/public/questionAndAnswer/help/help.jpg" />
				</div> -->
				<div class="bg">技术防范类型</div>
				<ul class="fuwu">

					<li><a href="###" name="selectBtn" onclick=""><font>
								网络安全</font></a></li>
					<li><a href="###" name="selectBtn" onclick=""><font>
								物理安全 </font></a></li>
					<li><a href="###" name="selectBtn" onclick=""><font>
								应用安全 </font></a></li>
					<li><a href="###" name="selectBtn" onclick=""><font>
								计算机安全 </font></a></li>
					<li><a href="###" name="selectBtn" onclick=""><font>
								机房安全</font></a></li>
					<li><a href="###" name="selectBtn" onclick=""><font>
								 </font></a></li>

				</ul>
			</div>
			<div id="rightside">
				<div class="survey" id="survey" style="width: 900px;margin-top: 0px;border-radius:2px;">
					    <div class="survey-top"></div>
					    <div class="survey-body">
					        <div class="survey-header">
					                        <h1 class="survey-title">网络安全</h1>

					                        <div class="survey-intro">&nbsp;&nbsp;&nbsp;&nbsp;网络的安全是指通过采用各种技术和管理措施，使网络系统正常运行，从而确保网络数据的可用性、完整性和保密性。网络安全的具体含义会随着“角度”的变化而变化。
					                        比如：从用户（个人、企业等）的角度来说，他们希望涉及个人隐私或商业利益的信息在网络上传输时受到机密性、完整性和真实性的保护。
											</div>
		                    </div>

					        <div style="display: none;" class="loading">Loading ...</div>
			                <div class="survey-content" style="">
						        <div class="page" data-sn="c4d36d57-5e6a-4eda-acaa-f265d0ca9752">

								<div class="part select" data-sn="d873a5b1-cad2-4f8d-93c8-242868ef068b">
								    <h4 class="title">
								        <span class="index"></span>
								        <span class="subject">是否对用户访问网络资源的权限进行严格的认证和控制</span>
								                <label class="rules">(单选)</label>
								    </h4>
						            <ul class="options">
							            <li class="option">
								            <input id="i_1" name="1" value="af11fc3e-f035-4ccb-ae1f-1376d03e2870" type="radio">
								            	<label for="i_1">是</label>
								            </input>
								            </li>
								            <li class="option even">
								            <input id="i_2" name="1" value="6309b538-1d5a-433b-a1c6-9fe78645d6e9" type="radio" onclick="check_radio(this)">
								           		 <label for="i_2">否</label>
								           		 <span style="margin-left: 50px;color: red;display: none;">建议XXXXXXXXXXXXXXXXXX</span>
								           	 </input>
							            </li>
						            </ul>
						        </div>

								<div class="part select" data-sn="d8050618-a7db-474a-8662-f32ae5f23bff">
								    <h4 class="title">
								        <span class="index"></span>
								        <span class="subject">是否安装网络防病毒系统</span>
								                <label class="rules">(单选)</label>
								    </h4>

 									<ul class="options">
							            <li class="option">
								            <input id="i_1" name="3" value="af11fc3e-f035-4ccb-ae1f-1376d03e2870" type="radio">
								            	<label for="i_1">是</label>
								            </input>
								            </li>
								            <li class="option even">
								             <input id="i_2" name="2" value="6309b538-1d5a-433b-a1c6-9fe78645d6e9" type="radio" onclick="check_radio(this)">
							           		 <label for="i_2">否</label>
							           		 <span style="margin-left: 50px;color: red;display: none;">建议XXXXXXXXXXXXXXXXXX</span>
							           		</input>
							            </li>
						            </ul>
						        </div>
								<div class="part select" data-sn="06c3e68a-f19f-4210-8466-20c5cc463f43">
								    <h4 class="title">
								        <span class="index"></span>
								        <span class="subject">是否采用隔离卡来实现网络隔离</span>
								                <label class="rules">(单选)</label>
								    </h4>
 									<ul class="options">
							            <li class="option">
								            <input id="i_1" name="5" value="af11fc3e-f035-4ccb-ae1f-1376d03e2870" type="radio">
								            	<label for="i_1">是</label>
								            </input>
								            </li>
								            <li class="option even">
								            <input id="i_2" name="5" value="6309b538-1d5a-433b-a1c6-9fe78645d6e9" type="radio" onclick="check_radio(this)">
								           		 <label for="i_2">否</label>
								           		 <span style="margin-left: 50px;color: red;display: none;">建议XXXXXXXXXXXXXXXXXX</span>
								           	 </input>
							            </li>
						            </ul>
						        </div>
								<div class="part select" data-sn="0a909b09-d1e5-4bb0-bf70-c8ea6cf70224">
								    <h4 class="title">
								        <span class="index"></span>
								        <span class="subject">是否制定严格的网络安全规章制度</span>
								                <label class="rules">(单选)</label>
								    </h4>


							        <ul class="options">
							            <li class="option">
								            <input id="i_1" name="7" value="af11fc3e-f035-4ccb-ae1f-1376d03e2870" type="radio">
								            	<label for="i_1">是</label>
								            </input>
								            </li>
								            <li class="option even">
								            <input id="i_2" name="7" value="6309b538-1d5a-433b-a1c6-9fe78645d6e9" type="radio" onclick="check_radio(this)">
								           		 <label for="i_2">否</label>
								           		 <span style="margin-left: 50px;color: red;display: none;">建议XXXXXXXXXXXXXXXXXX</span>
								           	 </input>
							            </li>
						            </ul>
						        </div>
								<div class="part select" data-sn="3f7793b4-6862-42b5-b04f-eea1ce1baca8">
								    <h4 class="title">
								        <span class="index"></span>
								        <span class="subject">是否采取防辐射、防火以及安装不间断电源（UPS）</span>
								                <label class="rules">(单选)</label>
								    </h4>


					                <ul class="options">
							            <li class="option">
								            <input id="i_1" name="9" value="af11fc3e-f035-4ccb-ae1f-1376d03e2870" type="radio">
								            	<label for="i_1">是</label>
								            </input>
								            </li>
								            <li class="option even">
								            <input id="i_2" name="9" value="6309b538-1d5a-433b-a1c6-9fe78645d6e9" type="radio" onclick="check_radio(this)">
								           		 <label for="i_2">否</label>
								           		 <span style="margin-left: 50px;color: red;display: none;">建议XXXXXXXXXXXXXXXXXX</span>
								           	 </input>
							            </li>
						            </ul>
						        </div>
								</div>
					        </div>
					    </div>

					    <div class="survey-footer">
					        <div class="captcha" style="display:none;">
					        </div>
					        <div class="buttons" style="">
					            <button type="button" class="prev" style="display:none;">上一页</button>
					            <button type="button" class="submit" style="">提交</button>
					            <button type="button" class="next" style="display:none;">下一页</button>
					        </div>

			            </div>
					</div>




			</div>
		</div>
	</div>

</body>
</html>





