<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="organ" uri="http://www.cdthgk.com/tags/organization/organ"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- js -->
		<script src="/bip/resources/js/environment/environment.js" type="text/javascript"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="/bip/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>



		<script language="javascript">
			$ENV.loader.loadStyleSheet("/bip/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("/bip/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("/bip/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("/bip/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("/bip/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("/bip/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("/bip/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("/bip/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("/bip/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("/bip/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("/bip/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("/bip/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){

				});
			});
			function doAdd(){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项！");
					return;
				}else if(items.length>1){
					alert("请选择一项！");
					return;
				}
				window.location.href='/bip/bmp/formatFile/borderlayout/view/formatFile_exchangeAdd.jsp';
			}
			function doViewStatus(){
			 	environment.dialog.open({
					url : '/bip/bmp/formatFile/borderlayout/view/formatFile_exchangeDetail.jsp?t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.7,
					height : window.top.getSize().y * 0.5,
					title : '详情'
				});
			}



		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar"><a href="###"  onclick="doAdd('');this.blur();" class="pop_button"><span>发送</span></a></div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<form action="/bip/bmp/formatFile/formatFile_list.action" method="post" id="myform">
				<table width="100%" class="st">
					<tr>
						<td class="tbLable fr">
							名称：
						</td>
						<td class="tbValue fl" colspan="3">
							<input type="text" value="" name="formatFile.name" value=""/>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="fc" style="border: 0px;">
							<div class="stBtnBar">
								<a class="pop_button" href="javascript:document.getElementById('myform').submit();"><span>查 询</span></a>
								<a class="pop_button" href="javascript:document.getElementById('myform').reset();"><span>重 置</span></a>
							</div>
						</td>
					</tr>
				</table>
			</form>

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						发送列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
<form id="formatFileList"  action="/bip/bmp/formatFile/formatFile_list.action"  method="post" >
<div>
<input type="hidden"  name="ec_i"  value="formatFileList" />
<input type="hidden"  name="formatFileList_crd"  value="10" />
<input type="hidden"  name="formatFileList_p"  value="1" />
<input type="hidden"  name="formatFileList_a_id_checkbox"  value="id" />
</div>
<div class="eXtremeTable" >
<table id="formatFileList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
	<thead>
	<tr>
		<td class="tableHeader"  width="5%" ><input type="checkbox"  name="id_checkbox"  id="id_checkbox"  class="class_id_checkbox"  onclick="EcTable.checkAll('id_checkbox')" /></td>
		<td class="tableHeader"  width="40%" >名称</td>
		<td class="tableHeader"  width="20%" >上传时间</td>
		<td class="tableHeader"  width="15%" >状态</td>
		<td class="tableHeader"  width="40%" >查看版式文件</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
		<td width="5%" ><input type="checkbox"  name="id"  value="402881cd4dd1e9fb014dd1ef51430001"  class="row_checkbox " /></td>
		<td width="40%" >文件测试1</td>
		<td width="20%" >

										<div style="font-family: Arial, 'Times New Roman' !important;">
											2015年06月08日
										</div>


								</td>
		<td width="15%" ><font color="red">未发送</font></td>
		<td width="40%" >
									<a href='###' onclick="doDownload('402881cd4dd1e9fb014dd1ef51430001')">查看</a>
								</td>
	<tr class="even"  onmouseover="this.className='highlight'"  onmouseout="this.className='even'" >
		<td width="5%" ><input type="checkbox"  name="id"  value="402881cd4dd1fe51014dd1ffc44a0000"  class="row_checkbox " /></td>
		<td width="40%" >文件测试12</td>
		<td width="20%" >

										<div style="font-family: Arial, 'Times New Roman' !important;">
											2015年06月08日
										</div>


								</td>
		<td width="15%" >
			<a href='###' onclick="doViewStatus()">
				<font color="green">已发送</font>
			</a>
		</td>
		<td width="40%" >
									<a href='###' onclick="doDownload('402881cd4dd1fe51014dd1ffc44a0000')">查看</a>
								</td>
	</tbody>
</table>
				<table class="page_control" >
	<tr style="padding: 0px;" >
		<td colspan="4" >
		<table border="0"  cellpadding="0"  cellspacing="0"  width="100%" >
			<tr>
				<td class="compactToolbar"  align="right" >
				<table border="0"  cellpadding="1"  cellspacing="2" >
					<tr>
				<td class="statusBar"  align="right" >共找到<font color=red>4</font>条记录,分<font color=red>1</font>页显示&#160;</td>
					<td><span>第一页</span></td>
					<td><span>上一页</span></td>
					<td><span>下一页</span></td>
					<td><span>最后页</span></td>
					<td><img src="/bip/platform/template/borderlayout/skin/blue/img/ec/separator.gif"  style="border:0"  alt="Separator" /></td>
					<td>每页显示<select name="formatFileList_rd"  class="page_control"  onchange="javascript:document.forms.formatFileList.formatFileList_crd.value=this.options[this.selectedIndex].value;document.forms.formatFileList.formatFileList_p.value='1';document.forms.formatFileList.setAttribute('action','/bip/bmp/formatFile/formatFile_list.action');document.forms.formatFileList.setAttribute('method','post');document.forms.formatFileList.submit()" >
				<option value="10"  selected="selected">10</option><option value="30" >30</option><option value="100" >100</option>
				</select>条</td>
					<td><img src="/bip/platform/template/borderlayout/skin/blue/img/ec/separator.gif"  style="border:0"  alt="Separator" /></td>
					<td class="statusBar" >转到<input type="text"  name="formatFileList_cpn"  class="page_control"  value="1"  size="1"  onkeypress="javascript111:if(event.keyCode==13){var re = /^[0-9]*[1-9][0-9]*$/;if(this.value < 1 || this.value > 1|| !re.test(this.value)){alert('页码不正确，请重新输入!');return false;}document.forms.formatFileList.formatFileList_p.value=this.value;document.forms.formatFileList.setAttribute('action','/bip/bmp/formatFile/formatFile_list.action');document.forms.formatFileList.setAttribute('method','post');document.forms.formatFileList.submit()}" >页</td>
					</tr>
				</table></td>
			</tr>
		</table>
		</td>
	</tr>

				</table>
</div>
</form>




					<form id="delete_form" name="delete_form" method="post">
						<input id="deleteIds" name="deleteIds" type="hidden"/>
					</form>

				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>