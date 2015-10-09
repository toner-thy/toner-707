<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!-- css -->
<link href="${pageContext.request.contextPath}/cms/resources/theme/default/css/attachment.css" rel="stylesheet"/>

<!-- js -->	
<script type="text/javascript">
	window.addEvent('domready', function(){
		//给id为'load'的元素添加click事件
		$$('.deletefj').each(function(item,index){
			item.addEvent('click', function() {						
				$('ajaxAttachment').set('load',{
					evalScripts:true
				}).load("<s:url action="externalPidgin_deleteAttachment"  includeParams="false"/>?externalPidgin.externalPidginId="+item.get('name')+"&attachment.attachId="+item.get('id')+"&&timer="+new Date()
				);
			});	
		})
	});
</script>
<c:if test="${attachmentList!=null}">
	<c:if test="${attachmentList!='[]'}">
		<span id="ajaxAttachment">
			<div id="files_list2">									
				<c:forEach items="${attachmentList}" var="attachment">
					<div id="rowdiv">
						<span class='fjlist'>
							<img src='/thgkcms/cms/resources/theme/default/images/icon/fj.gif'/>
							&nbsp; ${attachment.name} &nbsp;
							<a href="javascript:download('${externalPidgin.externalPidginId }','${attachment.attachId}');">下载</a>
							<input type="button" id="${attachment.attachId}" name="${externalPidgin.externalPidginId }" class="deletefj" value="删除" />
						</span>
					</div> 
				</c:forEach>
			</div>  
		</span>
	</c:if> 
</c:if>