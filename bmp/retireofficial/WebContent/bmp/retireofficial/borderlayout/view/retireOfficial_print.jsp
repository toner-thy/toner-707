<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary"
	prefix="dictionary"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/district" prefix="dis"%>
<%@ taglib uri="http://www.cdthgk.com/tags/ui" prefix="u"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>离退休档案详情</title>

		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){

				});
			});
			function doClose(){
			 	window.close();
			}

			function window.onload(){
	 			if ( !factory.object ){
	   				alert("打印控件未安装，请关闭您电脑中的禁止下载网页控件的相关软件，\n\n并将您IE浏览器的Internet安全级别设置为中!谢谢！\n\n\n\n提示：如果修改Internet安全级别无效，请在浏览器下拉菜单“文档”中选择<页面设置>,\n\n将<页眉>和<页脚>删除,\n\n并将页边距（上，下，左，右）设置为“0”或最小值，然后打印！");
	  			}
			}

			function SetPrintSettings(tmp){
				if ( !factory.object ){
					SetTD.style.display="none";
				}else{
					factory.printing.header = ""
					factory.printing.footer = ""
					factory.printing.portrait = true     //方向，true
					factory.printing.leftMargin = 0
					factory.printing.topMargin = 0
					factory.printing.rightMargin = 0
					factory.printing.bottomMargin = 0

					SetTD.style.display="none";
					$('print_btn').setStyle('display','none');

					switch (tmp){
						case "view":
						factory.printing.Preview()         //打印预览
						break;
						case "print":
						factory.printing.Print()
						break;
					}
				}
				SetTD.style.display="";
				$('print_btn').setStyle('display','block');
			}

			function printHelp(){
				alert("温馨提示：如果提示“打印控件未安装”，修改Internet安全级别无效，请在浏览器下拉菜单“文档”中选择<页面设置>，将<页眉>和<页脚>删除,并将页边距（上，下，左，右）设置为“0”或最小值，然后打印！");
			}
 		</SCRIPT>
	</head>

	<body>
		<div class="body_content">
			<div class="edit_content" >
				<h2>
					离退休档案详情
				</h2>
				<table id="edit-0" align="center" class="edit_table">
					<tr>
						<td width="25%" align="right">姓名：</td>
						<td>
							${retireOfficial.name}
						</td>
						<td width="25%" align="right">性别：</td>
						<td>
							<dictionary:text fieldCode="sex" optionValue="${retireOfficial.sex}"></dictionary:text>
						</td>
					</tr>
					<Tr>
						<td align="right">出生年月：</td>
						<td>
							<s:date format="yyyy-MM-dd" name="retireOfficial.birthDate"/>

						</td>
						<td align="right">民族：</td>
						<td>
							<s:property value="code.nation[retireOfficial.nation]"/>
						</td>
					</Tr>
					<tr>
					   	<td align="right">籍贯：</td>
					   	<td>
					   		${retireOfficial.nativePlace}
					   	</td>
					   	<td></td>
					   	<td></td>
					</tr>
					<Tr>
						<td align="right">入党时间：</td>
						<td>
							<s:date format="yyyy-MM-dd" name="retireOfficial.joinPartyTime"/>
						</td>
						<td align="right">参加工作时间：</td>
						<td>
							<s:date format="yyyy-MM-dd" name="retireOfficial.startWorkTime"/>
						</td>
					</Tr>
					<Tr>
						<td align="right">离退休时间：</td>
						<td>
							<s:date format="yyyy-MM-dd" name="retireOfficial.retireTime"/>
						</td>
						<td align="right">离退休文号：</td>
						<td>
							${retireOfficial.retireCode}
						</td>
					</Tr>
					<tr>
					    <Td align="right">享受待遇/级别：</Td>
					    <Td>
					    	<dictionary:text fieldCode="retireLevel" tableCode="retireOfficial" optionValue="${retireOfficial.retireLevel}" ></dictionary:text>
					    </Td>
					    <Td align="right">离退休时职务：</Td>
					    <Td>
					    	${retireOfficial.retireOrganDuty}
					    </Td>
					</tr>
					<tr>
					    <Td align="right">月收入总额：</Td>
					    <Td>
					    	${retireOfficial.monthEarning}
					    </Td>
					    <Td align="right">基本离退休金：</Td>
					    <Td>
					    	${retireOfficial.basicRetireMoney}
					    </Td>
					</tr>
					<Tr>
					    <td align="right">生活补贴：</td>
					    <td>${retireOfficial.lifeSubsidy}</td>
					    <td align="right">补贴2：</td>
					    <td>${retireOfficial.subsidy2}</td>
					</Tr>
					<Tr>
					    <td align="right">粮贴：</td>
					    <td>${retireOfficial.foodSubsidy}</td>
					    <td align="right">健康情况：</td>
					    <td>
					    	<dictionary:text fieldCode="health" tableCode="retireOfficial" optionValue="${retireOfficial.health}"></dictionary:text>
					    </td>
					</Tr>
					<tr>
					    <Td align="right">身份证号码：</Td>
					    <Td>
					    	${retireOfficial.idCard}
					    </Td>
					    <Td align="right">家庭住址：</Td>
					    <Td>
					    	${retireOfficial.address}
					    </Td>
					</tr>
					<tr>
					    <Td align="right">住房面积：</Td>
					    <Td>
					    	${retireOfficial.addressSize}
					    </Td>
					    <Td align="right">固定电话：</Td>
					    <Td>
					    	${retireOfficial.phone}
					    </Td>
					</tr>
					<tr>
					    <Td align="right">移动电话：</Td>
					    <Td>
					    	${retireOfficial.mobile}
					    </Td>
					    <Td align="right">配偶姓名：</Td>
					    <Td>
					    	${retireOfficial.spouseName}
					    </Td>
					</tr>
					<Tr>
						<td align="right">配偶出生年月：</td>
						<td>
							<s:date format="yyyy-MM-dd" name="retireOfficial.spouseBirthDate"/>
						</td>
						<td align="right">配偶身体状况：</td>
						<td><dictionary:text fieldCode="health" tableCode="retireOfficial" optionValue="${retireOfficial.health}"></dictionary:text></td>
					</Tr>
					<tr>
					    <Td align="right">配偶原单位及职务：</Td>
					    <Td>
					    	${retireOfficial.spouseOrganDuty}
					    </Td>
					    <Td align="right">配偶目前状况：</Td>
					    <Td>
					    	${retireOfficial.spouseInfo}
					    </Td>
					</tr>
					<tr>
						<td align="right">个人履历：</td>
						<td colspan="3">
							${retireOfficial.personalRecord}
						</td>
					</tr>
				</table>
				<div align="center" id="print_btn">
			    	<input name="print" type="button" value="打印" class="btn_23" onclick="SetPrintSettings('print')"/>
			    	<input name="printView" type="button" value="打印预览" class="btn_23" onclick="SetPrintSettings('view')"/>
			    	<input name="printHelp" type="button" value="打印帮助" class="btn_23" onclick="printHelp()"/>
			    	<input name="close" type="button" value="关闭" class="btn_23" onclick="doClose()"/>
			    </div>
			    <div align="center" style="font-size: 12pxl">
				 	<font color="red"><span id="SetTD" height="60"></span><br></font>
				</div>
		</div>
		<object id="factory" style="display:none" viewastext classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="<%=request.getContextPath()%>/bmp/print/ScriptX.cab#Version=6,2,433,14"></object>
	</body>
</html>
