<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>单位保密办成员详情</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<s:actionmessage theme="messages"/>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					formcheck = new FormCheck('edit_form',{
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
					$('secrecyWorkOrganMemberUnit.person.department.departmentName').addEvent('blur', doBlur);
				});
			});

			var popFlag = true;
			function doBlur(){
				var value = $('secrecyWorkOrganMemberUnit.person.department.departmentName').value;
				if('${secrecyWorkOrganMemberUnit.person.department.departmentName}' != value){
					if(popFlag){
						doSelectDept();
						popFlag = false;
					}
				}
			}

			function doSelectDept(){
				$ENV.dialog.open({
					title : '请选择',
					url : "${ctx}/bmp/secrecy-organization/secrecy-workorgan/borderlayout/view/secrecyWorkOrganRelationMember_deptConfirm.jsp?&_ts=" + $time(),
					width : window.top.getSize().x * 0.3,
					height : window.top.getSize().y * 0.4
				});
			}
			function doSave(){
				if(!popFlag){
					return;
				}
				if (formcheck.isFormValid(true)) {
				//if (confirm('请仔细核对输入单位保密办成员的信息,确认无误请点击【确定】,如需修改请点击【取消】!')){
					$('sub').click();
					$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",8000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",8000);
				//}
				}
			}

			// 返回方法
			function doBackList(id){
				window.location.href = "${ctx}/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_edit.action?secrecyWorkOrgan.secrecyWorkOrganId="+id;
			}

			//格式化时间
			function getLocalTime(ns){
				//格式化时间方法
				Date.prototype.format = function(format){
					var o = {
							"M+" : this.getMonth()+1,
							"d+" : this.getDate(),
							"h+" : this.getHours(),
							"m+" : this.getMinutes(),
							"s+" : this.getSeconds(),
							"q+" : Math.floor((this.getMonth()+3)/3),
							"S" : this.getMilliseconds()
					}

					if(/(y+)/.test(format)){
						format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4-RegExp.$1.length));
					}
					for( var k in o ){
						if( new RegExp("("+k+")").test(format) ){
							format = format.replace(RegExp.$1,
									(RegExp.$1.length == 1) ? (o[k]) :
										(("00"+o[k]).substr((""+o[k]).length)));
						}
					}
					return format;
				}

				if( ns!=null && ns!="" && ns !="null" ){
					var localstr = new Date(parseInt(ns));
					return localstr.format('yyyy-MM-dd');
				}else{
					return "";
				}
			}

			//ui控件  补全选中人员信息
			function setUserInfo(elements, value){
				$('secrecyWorkOrganMemberUnit.person.department.departmentName').value=value.data["department.departmentName"];
				$('secrecyWorkOrganMemberUnit.person.department.departmentId').value=value.data["department.departmentId"];
				$('secrecyWorkOrganMemberUnit.person.duty').value=value.data["duty"];
 				$('secrecyWorkOrganMemberUnit.person.sex').value=value.data["sex"];
				$('secrecyWorkOrganMemberUnit.person.birthDate').value=getLocalTime(value.data["birthDate"]);
				$('secrecyWorkOrganMemberUnit.person.learningLevel').value=value.data["learningLevel"];
				$('secrecyWorkOrganMemberUnit.person.polity').value=value.data["polity"];
				$('secrecyWorkOrganMemberUnit.person.phone').value=value.data["phone"];
				$('secrecyWorkOrganMemberUnit.person.technicTitleLevel').value=value.data["technicTitleLevel"];
				$('secrecyWorkOrganMemberUnit.person.specialtyCode').value=value.data["specialtyCode"];
				$('secrecyWorkOrganMemberUnit.person.administrativeLevel').value=value.data["administrativeLevel"];
			}
			//显示层
			function disOne(id) {
				$(id).style.display="";
			}

			//消失层
			function noneOne(id){
				$(id).style.display="none";
			}
		</script>
	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:environment.dialog.close();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div id="body_content" class="body_content">

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						单位保密办成员详情
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form id="edit_form" action="<s:url namespace='/secrecyorganization/secrecyWorkOrganMemberUnit' action='secrecyWorkOrganMemberUnit_editing.action' includeParams='true'/>" method="post" enctype="multipart/form-data">
						<!-- 隐藏域 -->
						<input type="hidden" name="secrecyWorkOrganMemberUnit.secrecyWorkUnitMemberId" value="${secrecyWorkOrganMemberUnit.secrecyWorkUnitMemberId}"/>
						<input type="hidden" name="secrecyWorkOrgan.secrecyWorkOrganId" value="${secrecyWorkOrgan.secrecyWorkOrganId}"/>

						<table class="content_table" width="100%">
							<tr>
								<td colspan="4" height="45" class="formTitle" align="center">
									<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
										单位保密办成员详情
									</div>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									姓 名：
								</td>
								<td class="tbValue fl">
									${secrecyWorkOrganMemberUnit.person.name}
								</td>
								<td class="tbLable fr">
									部门：
								</td>
								<td class="tbValue fl">
									${secrecyWorkOrganMemberUnit.person.department.departmentName }
								</td>
							<tr>
							<tr>
								<td class="tbLable fr">
									行政职务：
								</td>
								<td class="tbValue fl">
									${secrecyWorkOrganMemberUnit.person.duty }
								</td>
								<td class="tbLable fr">
									办公室电话：
								</td>
								<td class="tbValue fl">
									${secrecyWorkOrganMemberUnit.person.phone }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">性别：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="sex" optionValue="${secrecyWorkOrganMemberUnit.person.sex }"></dictionary:text>
								</td>
								<td class="tbLable fr">出生年月：</td>
								<td class="tbValue fl">
									<s:date name='#attr.secrecyWorkOrganMemberUnit.person.birthDate' format='yyyy-MM-dd'/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">学历：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="learning_level" optionValue="${secrecyWorkOrganMemberUnit.person.learningLevel }" ></dictionary:text>
								</td>
								<td class="tbLable fr">政治面貌：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="polity" optionValue="${secrecyWorkOrganMemberUnit.person.polity }" ></dictionary:text>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">专业：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="specialty_code" optionValue="${secrecyWorkOrganMemberUnit.person.specialtyCode }"></dictionary:text>
								</td>
								<td class="tbLable fr">行政级别：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="person_admin_level" optionValue="${secrecyWorkOrganMemberUnit.person.administrativeLevel }"></dictionary:text>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">技术职称：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="person" fieldCode="technic_title_level" optionValue="${secrecyWorkOrganMemberUnit.person.technicTitleLevel }" ></dictionary:text>
								</td>
								<td class="tbLable fr">是否专职：</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="sole_duty" optionValue="${secrecyWorkOrganMemberUnit.isSoleDuty }" ></dictionary:text>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">其他职务：</td>
								<td class="tbValue fl">
									${secrecyWorkOrganMemberUnit.otherDuty }
								</td>
								<td class="tbLable fr">何时从事保密工作：</td>
								<td class="tbValue fl">
									<s:date name='#attr.secrecyWorkOrganMemberUnit.secrecyWorkStart' format='yyyy-MM-dd'/>
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									人员排序：
								</td>
								<td class="tbValue fl">
									${secrecyWorkOrganMemberUnit.sort }
								</td>
								<td class="tbLable fr">
									&nbsp;
								</td>
								<td class="tbValue fl">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									备 注：
								</td>
								<td class="tbValue fl" colspan="3">
									<textarea id="secrecyWorkOrganMemberUnit.remark" name="secrecyWorkOrganMemberUnit.remark" rows="" cols="" style="width: 90%;height: 150px;" class="validate['length[1000]']" readonly="readonly">${secrecyWorkOrganMemberUnit.remark}</textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input id="sub" value="sub" type="submit" style="display: none;"/>
						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>

	</body>
</html>