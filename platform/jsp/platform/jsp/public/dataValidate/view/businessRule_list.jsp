<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/organ" prefix="organ"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE link PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<script src="${ctx}/platform/jsp/public/dataValidate/resource/js/jquery-1.7.1.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			//保存
			function doSave(id){
				     var minRecordNum=document.getElementById('businessRule.minRecordNum'+id).value;
				     minRecordNum = $.trim(minRecordNum);
					 var isWrite;
                     if(document.getElementById('businessRule.isWrite'+id).checked)
                    	 {
                    	     isWrite=1;
                    	     if(minRecordNum.length==0)
                    	    	 {
                    	    	   alert("温馨提示：是否填写选【是】时，最低要求记录数必须为数字且不能为空！");
         						   return;
                    	    	 }
                    	 }else{
                    		 isWrite=0;
                    	 }

					var reg = /^\d+$/;

					if (minRecordNum.length!=0&&!minRecordNum.match(reg)){
						alert("温馨提示：最低要求记录数,必须为数字！");
						return;
					}
					var isRecentlyChanged;
					var verificationValue=document.getElementById('businessRule.verificationValue'+id).value;
					verificationValue = $.trim(verificationValue);
					if(document.getElementById('businessRule.isRecentlyChanged'+id).checked)
	               	 {
						 isRecentlyChanged=1;
						 if(minRecordNum.length==0)
            	    	 {
            	    	   alert("温馨提示：最近是否修改过选【是】时，最近是否使用过的度量阀值,必须为数字且不能为空！");
 						   return;
            	    	 }
	               	 }else{
	               		 isRecentlyChanged=0;
	               	 }

					if (minRecordNum.length!=0&&!verificationValue.match(reg))
					{
						alert("温馨提示：最近是否使用过的度量阀值,必须为数字！");
						return;
					}

					if(window.confirm("确定要更新吗？")){
						 $.ajax({
							 url: '${ctx}//bmp/dataVailidate/businessRule_edit.action?businessRule.businessRuleId='+id+'&businessRule.isWrite='+isWrite+'&businessRule.minRecordNum='+minRecordNum+'&businessRule.isRecentlyChanged='+isRecentlyChanged+'&businessRule.verificationValue='+verificationValue,
						        data: { name: isRecentlyChanged },
						        type: "post",
						        success: function (text) {
						        	alert(text.message);
						        },
						        error: function (jqXHR, textStatus, errorThrown) {
						            alert(jqXHR.responseText);
						        }
						    });
					}
	    	}
		$(function(){
		    var browser_version=$.browser.version;
		    $("#scroll_table").scroll(function(e) {
		        if($(this).scrollTop()>0)
		        {
		            if($("#thead").length==0)
		            {
		                var thead=$('<table id="thead">'+$("table thead").html()+'</table>');
		                $("#scroll_table").prepend(thead);
		            }
		            else if('6.0'==browser_version||'7.0'==browser_version)
		            {

		                $("#thead").css("position","relative");
		                $("#thead").css("top",$(this).scrollTop());
		            }
		        }
		        else
		        {
		            $("#thead").remove();
		        }
		    });
		});
	</script>
	</head>

<style type="text/css">
		#scroll_table{ height:700px; overflow:auto;}
		table{border-collapse:collapse; }
		table thead{background-color:#ECF1F5}
		th,td{border:1px solid #CCC}
		#thead{ position:fixed; z-index:100;background-color:#ECF1F5}
		.w_100{ width:100px;}
		.w_160{ width:160px;}
		.w_170{ width:250px;}
		.w_180{ width:200px;}
		.w_50{ width:50px;}
		.w_20{ width:20px;}
</style>

<body style="background-color:#ECF1F5">
		<form id="form_businessRule_update" class="form"  action="<s:url namespace='/bmp/destructionScrap' action='destructionScrap_save' includeParams='true'/>" method="post" enctype="multipart/form-data">
			<div id="scroll_table"  >
				<table id="data_table">
				        <thead>
					        <tr>
					          	<th class="w_20">序列</th>
					          	<th class="w_180">模块名字</th>
					          	<th class="w_160">是否填写</th>
					          	<th class="w_160">最低要求记录数(单位：条)</th>
					          	<th class="w_160">最近是否修改过</th>
					         	<th class="w_170">最近是否使用过的度量阀值（单位：天）</th>
					         	<th class="w_50">操作</th>
					        </tr>
				         </thead>
				         <tbody>
					        <c:forEach items="${list}" var="businessRule" varStatus="xm">
							<tr>
									<td class="w_20">
										${xm.index+1}
									</td>
									<td class="${xm.index == 0 ? 'w_180' : ''}">
										${businessRule.name}
									</td>
									<td class="${xm.index == 0 ? 'w_160' : ''}" align="center">

										<c:if test="${businessRule.isWrite==1}">
										  <input type="radio" value="1" checked="checked" name="businessRule.isWrite${businessRule.businessRuleId}" id="businessRule.isWrite${businessRule.businessRuleId}"/> 是
										  <input type="radio" value="0" name="businessRule.isWrite${businessRule.businessRuleId}" id="businessRule.isWrite${businessRule.businessRuleId}"/> 否
										  </c:if>
										<c:if test="${businessRule.isWrite==0}">
										  <input type="radio" value="1" name="businessRule.isWrite${businessRule.businessRuleId}" id="businessRule.isWrite${businessRule.businessRuleId}"/> 是
										  <input type="radio" value="0" checked="checked" name="businessRule.isWrite${businessRule.businessRuleId}" id="businessRule.isWrite${businessRule.businessRuleId}"/> 否
										</c:if>
										<c:if test="${businessRule.isWrite!=0&&businessRule.isWrite!=1}">
										  <input type="radio" value="1" name="businessRule.isWrite${businessRule.businessRuleId}" id="businessRule.isWrite${businessRule.businessRuleId}"/> 是
										  <input type="radio" value="0" name="businessRule.isWrite${businessRule.businessRuleId}" id="businessRule.isWrite${businessRule.businessRuleId}"/> 否
										</c:if>
									</td>
									<td class="${xm.index == 0 ? 'w_160' : ''}">

								      	 <input type="text" name="businessRule.minRecordNum" class="validate['length[50]','number']" id="businessRule.minRecordNum${businessRule.businessRuleId}" value="${businessRule.minRecordNum}"/>
									</td>
									<td class="${xm.index == 0 ? 'w_160' : ''}" align="center">
										<c:if test="${businessRule.isRecentlyChanged==1}">
										  <input type="radio" value="1" name="businessRule.isRecentlyChanged${businessRule.businessRuleId}"  checked="checked" id="businessRule.isRecentlyChanged${businessRule.businessRuleId}"/> 是
										  <input type="radio" value="0"  name="businessRule.isRecentlyChanged${businessRule.businessRuleId}" id="businessRule.isRecentlyChanged${businessRule.businessRuleId}"/> 否
										  </c:if>
										<c:if test="${businessRule.isRecentlyChanged==0}">
										   <input type="radio" value="1" name="businessRule.isRecentlyChanged${businessRule.businessRuleId}" id="businessRule.isRecentlyChanged${businessRule.businessRuleId}"/> 是
										  <input type="radio" value="0"checked="checked" name="businessRule.isRecentlyChanged${businessRule.businessRuleId}" id="businessRule.isRecentlyChanged${businessRule.businessRuleId}"/> 否
										</c:if>
										<c:if test="${businessRule.isRecentlyChanged!=0&&businessRule.isRecentlyChanged!=1}">
										  <input type="radio" value="1" name="businessRule.isRecentlyChanged${businessRule.businessRuleId}" id="businessRule.isRecentlyChanged${businessRule.businessRuleId}"/> 是
										  <input type="radio" value="0" name="businessRule.isRecentlyChanged${businessRule.businessRuleId}" id="businessRule.isRecentlyChanged${businessRule.businessRuleId}"/> 否
										</c:if>
									</td>
									<td class="${xm.index == 0 ? 'w_170' : ''}">
										 <input type="text"  name="businessRule.verificationValue" class="validate['length[50]','number']" id="businessRule.verificationValue${businessRule.businessRuleId}" value="${businessRule.verificationValue}"/>
									</td>
									<td class="${xm.index == 0 ? 'w_50' : ''}" align="center">
									  <a href="####" id="gengXing${businessRule.businessRuleId}"  onclick="doSave('${businessRule.businessRuleId}')">更新</a>
									</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</form>
	</body>
</html>