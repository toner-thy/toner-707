<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib prefix="dep" uri="http://www.cdthgk.com/tags/organization/department"%>
<%@ taglib prefix="ui" uri="http://www.cdthgk.com/tags/organization/userinfo"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<%@ taglib prefix="section" uri="http://www.cdthgk.com/tags/keySection"%>
<%@ taglib prefix="part" uri="http://www.cdthgk.com/tags/keyPart"%>
<%@ taglib prefix="sp" uri="http://www.cdthgk.com/tags/secrecyPerson/search"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑国家秘密事项</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
				$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
				$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css");
				$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

				$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
				$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
				$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
				$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
				$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
				$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
				$ENV.loader.loadJavaScript("${ctx}/platform/resources/js/platform.js");
				$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js",function(){
				$ENV.onDomReady(function(){
					if (needReload) {
						if (!confirm('编辑成功，是否继续？')){
							needReload2 = true;
							TabUtil.closeTab();
						}
					};
					formcheck = new FormCheck('add_form',{
						container: $('body_content'),
						display:{
							showErrors:${_.config.formcheck.showErrors},errorsLocation: ${_.config.formcheck.errorsLocation}
						},
						trimValue: true
					});
				});
			});

			var needReload = ${needReload};
  			var needReload2 = false;

  			// 提交方法
			function doSave(){
				if (formcheck.isFormValid(true)) {
					//选择 "1时间区间”，只填写“保密期限起”、“保密期限止”;
					//选择“2解除时间”，只填写“保密期限止”;
					//选择“3解除条件”或者“4其他（长期）”，只填写“解除条件”;
					var obj = document.getElementById("secrecyCountryItem.secrecyLimitType");
					var iValue = obj.value; //得到保密期限的值

					var begdate = $("secrecyCountryItem.secrecyLimitBeginDate").value;//保密期限起
					var enddate = $("secrecyCountryItem.secrecyLimitEndDate").value;//保密期限止
					//var removedate = $("secrecyCountryItem.removeSecrecyDate").value;//解除时间
					var removeCondition = $("secrecyCountryItem.removeSecrecyCondition").value;//解除条件

					if(iValue=="1") {//时间区间
						if(begdate=="" || enddate=="" ) {
							alert("保密期限起、保密期限止不能为空");
							return;
						}
						//验证时间
						if(begdate>enddate) {
							alert("保密期限起时间不能大于保密期限止时间");
							return;
						}

					}else if(iValue=="2") {//解除时间
						if(enddate=="" ) {
							alert("保密期限止不能为空");
							return ;
						}

					}else if(iValue=="3" || iValue=="4") {//解除条件  或者其他
						if(removeCondition=="" ) {
							alert("解除条件不能为空!");
							return;
						}
					}

					//是否由要害部门产生
					var rads = document.getElementsByName("isfromKeyDepartment");
					for(var i=0; i<rads.length; i++) {
						var r_obj = rads[i];
						if(r_obj.checked==true) {
							if(rads[i].value=="1" && $("keySectionName").value=="") { //要害部门
								alert("要害部门不能为空.");
								return;
							}else if(rads[i].value=="0" && $("departmentName").value=="") {//系统部门
								alert("部门名称不能为空.");
								return;
							}
						}
					}


				 	$('sub').click();
		    		$('sbm_button').style.display='none';
					$('sbm_button_hidden').style.display='';
					window.setTimeout("$('sbm_button').style.display=''",2000);
					window.setTimeout("$('sbm_button_hidden').style.display='none'",2000);
				}
			}

			// 返回方法
			function doBackList(){
				TabUtil.closeTab();
			}

			//是否 由要害部门产生
			function isfromKeyDepartment_f(){
				var rads = document.getElementsByName("isfromKeyDepartment");

				for(var i=0; i<rads.length; i++) {
					var r_obj = rads[i];
					if(r_obj.checked==true) {
						$("secrecyCountryItem.isfromKeyDepartment").value = rads[i].value;//设置radio的值

						if(rads[i].value=="1"){ //是保密要害部门生成
							$("pu_department").style.display="none";
							$("section_department").style.display="";
							$("secrecyCountryItem.departId.departmentId").value="";
							$("departmentName").value="";
						}else {//不是
							$("pu_department").style.display="";
							$("section_department").style.display="none";
							$("secrecyCountryItem.keySectionId.keySectionId").value="";
							$("keySectionName").value="";
						}
					}
				}
			}

			//保密期限的选择
			function change_secrecyLimitType() {
				//选择 "1时间区间”，只填写“保密期限起”、“保密期限止”;
				//选择“2解除时间”，只填写“保密期限止”;
				//选择“3解除条件”或者“4其他（长期）”，只填写“解除条件”;
				var obj = document.getElementById("secrecyCountryItem.secrecyLimitType");
				var iValue = obj.value; //得到保密期限的值
				var red_secrecyLimitBeginDate = document.getElementById("secrecyLimitBeginDate_red");//保密期限起
				var red_secrecyLimitEndDate = document.getElementById("secrecyLimitEndDate_red");//保密期限止
				var red_removeSecrecyCondition = document.getElementById("removeSecrecyCondition_red");//解除条件

				if(iValue=="1") {//时间区间
					red_secrecyLimitBeginDate.style.display="";
					red_secrecyLimitEndDate.style.display="";
					red_removeSecrecyCondition.style.display="none";

				}else if(iValue=="2") {//解除时间
					red_secrecyLimitBeginDate.style.display="none";
					red_secrecyLimitEndDate.style.display="";
					red_removeSecrecyCondition.style.display="none";

				}else if(iValue=="3") {//解除条件
					red_secrecyLimitBeginDate.style.display="none";
					red_secrecyLimitEndDate.style.display="none";
					red_removeSecrecyCondition.style.display="";

				}if(iValue=="4") {//其他（长期）
					red_secrecyLimitBeginDate.style.display="none";
					red_secrecyLimitEndDate.style.display="none";
					red_removeSecrecyCondition.style.display="";
				}
			}

			//初始化js函数
			function init() {
				//保密期限的限制
				change_secrecyLimitType();

				//是否是要害部门的限制
				var iV = document.getElementById("secrecyCountryItem.isfromKeyDepartment").value;
				if (iV == "1") {
					document.getElementById("r1").checked = true;
					document.getElementById("r2").checked = false;
					$("pu_department").style.display = "none";
					$("section_department").style.display = "";
					$("secrecyCountryItem.departId.departmentId").value="";
					$("departmentName").value="";
					$("secrecyCountryItem.isfromKeyDepartment").value = '1';
				} else {
					document.getElementById("r2").checked = true;
					document.getElementById("r1").checked = false;
					$("pu_department").style.display = "";
					$("section_department").style.display = "none";
					$("secrecyCountryItem.keySectionId.keySectionId").value="";
					$("keySectionName").value="";
					$("secrecyCountryItem.isfromKeyDepartment").value = '0';
				}
			}

			//显示层
			function disOne() {
				$("expid").style.display="";
			}

			//消失层
			function noneOne(){
				$("expid").style.display="none";
			}
		</script>
	</head>

	<body onload="init()">
		<!-- 公共头部-->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="javascript:void();" id="sbm_button_hidden" style="display:none;"><span>保存中...</span></a>
					<a class="pop_button" href="javascript:doSave();" id="sbm_button"><span>保 存</span></a>
					<a class="pop_button" href="javascript:doBackList()"><span>返回列表</span></a>
				</div>
			</div>

			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:TabUtil.refreshTab();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 复合面板开始 -->
			<%-- <cp:start defaultTitle="国家秘密事项简介" ctx="${ctx}" icoPath="/country_secrecy/secrecy_country_item/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','国家秘密事项简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于国家秘密事项
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_countryitem"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>
				</cp:msg>
			<cp:end> </cp:end> --%>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始-->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑国家秘密事项
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<form action="${ctx}/bmp/secrecycountryitem/secrecyCountryItem_editing.action" method="post" id="add_form" name="add_form">
						<table align="center" class="content_table st" width="100%">
							<tr>
								<td class="tbLable fr">国家秘密事项名称：</td>
								<td class="tbValue fl" colspan="3">
									<input id="secrecyCountryItem.secrecyCountryItemName" name="secrecyCountryItem.secrecyCountryItemName" type="text"
									class="validate['required','length[300]']" value="${secrecyCountryItem.secrecyCountryItemName }" style="width: 75%"/>
									<input type="hidden" id="secrecyCountryItem.secrecyCountryItemId" name="secrecyCountryItem.secrecyCountryItemId" value="${secrecyCountryItem.secrecyCountryItemId }" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">密 级：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyCountryItem.secrecyLevel }"  style="width:130px;"
									id="secrecyCountryItem.secrecyLevel" name="secrecyCountryItem.secrecyLevel"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
								<td class="tbLable fr">保密期限类型：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="secrecy_limit_type" optionValue="${secrecyCountryItem.secrecyLimitType }" style="width:130px;"
									id="secrecyCountryItem.secrecyLimitType" name="secrecyCountryItem.secrecyLimitType" onchange="change_secrecyLimitType()"></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
									<img alt="" src="${ctx}/bmp/key-section/borderlayout/skin/blue/img/wen.jpg" width="20px" height="20px;"
									style="vertical-align:middle;"  onmouseover="disOne()" onmouseout="noneOne()"  />
									<div id="expid"  style="display:none;z-index:500;position:absolute; background-color:#CCFFCC;" >
										<br/>
										1、当选择“时限区间”时，只填写“保密期限起”、“保密期限止”；<br/>
										2、当选择“解除时间”时，只填写“保密期限止”；<br/>
										3、当选择“解除条件”或者“其他（长期）”，只填写“解除条件”；<br/>
										<br/>
									</div>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">保密期限：</td>
								<td class="tbValue fl">
									<input id="secrecyCountryItem.secrecyLimit" name="secrecyCountryItem.secrecyLimit" type="text"
									class="validate['length[20]','digit']" value="${secrecyCountryItem.secrecyLimit }"/>
								</td>
								<td class="tbLable fr">期限单位：</td>
								<td class="tbValue fl">
									<dictionary:select tableCode="bmp" fieldCode="limit_type" optionValue="${secrecyCountryItem.limitType }" style="width:130px;"
									id="secrecyCountryItem.limitType" name="secrecyCountryItem.limitType" ></dictionary:select>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr id="secrecyLimitBeginDate_red">
								<td class="tbLable fr">保密期限起：</td>
								<td class="tbValue fl"colspan="3">
									<input type="text" name="secrecyCountryItem.secrecyLimitBeginDate" style="width:130px;"
									value="<s:date format="yyyy-MM-dd" name="secrecyCountryItem.secrecyLimitBeginDate"/>"
									class="Wdate" id="secrecyCountryItem.secrecyLimitBeginDate"
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr id="secrecyLimitEndDate_red">
								<td class="tbLable fr">保密期限止：</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" name="secrecyCountryItem.secrecyLimitEndDate" style="width:130px;"
									value="<s:date format="yyyy-MM-dd" name="secrecyCountryItem.secrecyLimitEndDate"/>"
									class="Wdate" id="secrecyCountryItem.secrecyLimitEndDate"
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">定密负责人：</td>
								<td class="tbValue fl" colspan="3">
									<sp:selectSecrecyPerson required="true" onlyFromValue="false" styleClass="validate['length[32]']" buttonEl="secrecyPersonName"
									 valueEl="secrecyCountryItem.formulateSecrecyPerson.userInfoId" value="${secrecyCountryItem.formulateSecrecyPerson.userInfoId}"
									 textEl="secrecyCountryItem.formulateSecrecyPerson.name" text="${secrecyCountryItem.formulateSecrecyPerson.name}" >
									 </sp:selectSecrecyPerson>
								</td>

							</tr>

						<%-- 	<tr>
								<td class="tbLable fr">解除时间：</td>
								<td class="tbValue fl" colspan="3">
									<input type="text" name="secrecyCountryItem.removeSecrecyDate" style="width:130px;"
									value="<s:date format="yyyy-MM-dd" name="secrecyCountryItem.removeSecrecyDate"/>"
									class="Wdate" id="secrecyCountryItem.removeSecrecyDate"
									onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr> --%>

							<tr id="removeSecrecyCondition_red" >
								<td class="tbLable fr">解除条件：</td>
								<td class="tbValue fl" colspan="3">
									<input id="secrecyCountryItem.removeSecrecyCondition" name="secrecyCountryItem.removeSecrecyCondition"
									 type="text" class="validate['length[400]']" value="${secrecyCountryItem.removeSecrecyCondition}" style="width:75%;" />
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td class="tbLable fr">是否由保密要害部门产生：</td>
								<td class="tbValue fl" colspan="3">
									<input type="radio" id="r1" name="isfromKeyDepartment" value="1" onchange="isfromKeyDepartment_f()" onclick="isfromKeyDepartment_f()"  />是&nbsp;&nbsp;&nbsp;
									<input type="radio" id="r2" name="isfromKeyDepartment" value="0" onchange="isfromKeyDepartment_f()" onclick="isfromKeyDepartment_f()" checked="checked" />否&nbsp;&nbsp;&nbsp;
									<input type="hidden" id="secrecyCountryItem.isfromKeyDepartment" name="secrecyCountryItem.isfromKeyDepartment" value="${secrecyCountryItem.isfromKeyDepartment }">
								</td>
							</tr>


							<tr id="pu_department">
								<td class="tbLable fr">部门名称：</td>
								<td class="tbValue fl" colspan="3">
									<dep:selectByOrgan valueEl="secrecyCountryItem.departId.departmentId" textEl="departmentName"
									 onlyFromValue="false" text="${secrecyCountryItem.departId.departmentName}" required="false"
									 value="${secrecyCountryItem.departId.departmentId}" buttonEl="btnDepartment"></dep:selectByOrgan>
									 <span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>
							<tr id="section_department">
								<td class="tbLable fr">要害部门：</td>
								<td class="tbValue fl" colspan="3">
									<section:selectKeySection valueEl="secrecyCountryItem.keySectionId.keySectionId" textEl="keySectionName"
									  onlyFromValue="false" text="${secrecyCountryItem.departId.departmentName}" required="false"
									  value="${secrecyCountryItem.keySectionId.keySectionId}"  buttonEl="btnKeySection" dialogHeight="0.9" dialogWidth="0.8">
									</section:selectKeySection>
									<span style="color:red;">&nbsp;&nbsp;*</span>
								</td>
							</tr>

							<tr>
								<td align="right">备 注：</td>
								<td align="left" colspan="3">
									<textarea class="validate['length[2000]']" id="secrecyCountryItem.content" name="secrecyCountryItem.content" style="width:75%;height:150px;" >${secrecyCountryItem.content }</textarea>
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
							<input type="hidden" id="secrecyCountryItem.secrecyStatus" name="secrecyCountryItem.secrecyStatus" value="${secrecyCountryItem.secrecyStatus}" />
							<input type="hidden" id="secrecyCountryItem.createPerson.userId" name="secrecyCountryItem.createPerson.userId" value="${secrecyCountryItem.createPerson.userId}" />
							<input type="hidden" id="secrecyCountryItem.createTime" name="secrecyCountryItem.createTime" value="${secrecyCountryItem.createTime}" />
							<input type="hidden" id="secrecyCountryItem.createOrgan.organId" name="secrecyCountryItem.createOrgan.organId" value="${secrecyCountryItem.createOrgan.organId}" />
						</div>
					</form>
				</div>
			</div>
			<!-- 内容panel结束-->
		</div>
	</body>
</html>