<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>

<html>
	<head>
    	<title>新增设备预警</title>
		
		<!-- css -->
		<link href="${pageContext.request.contextPath}/platform/theme/default/css/style.css" type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/platform/theme/public_js/mootools/miftree/mif_css/mif-tree.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/platform/theme/default/formcheck/formcheck.css" rel="stylesheet" type="text/css" />
		
		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/mootools-more.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/miftree/mif.tree-v1.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/AccordionMenu/AccordionMenu.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/mootools/formcheck/formcheck.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/platform/theme/public_js/fw-moo12-adapter.js"></script>
  		
  		<script type="text/javascript">
			window.addEvent('domready', function(){        
				new FormCheck('add_car_warning_form',{
					display:{
						showErrors:1
					}
				});
				$('warningAddIframe').src='warning_add.action?warning.url='+"${url}";
			 });
		</script>
	</head>
  
	<base target="_self">
  
	<body>
		<div>
 			<iframe name="warningAddIframe" id="warningAddIframe" scrolling="auto" src="" height="100%" width="100%" frameborder="0"></iframe>
  		</div>	
  	</body>
</html>
