<%@ tag body-content="scriptless" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@tag import="java.util.UUID"%>
<%@tag import="com.cdthgk.bmp.secrecycountryitem.qo.YearControlUtils"%>
<%@ attribute name="actionUrl" type="java.lang.String" required="true"%>
<%@ attribute name="yearStatProvider" type="com.cdthgk.bmp.secrecycountryitem.qo.YearControlDto" required="true"%>
<%@ attribute name="callBackFunction" type="java.lang.String" required="true"%>
<%
String id = UUID.randomUUID().toString();
int firstHalfFlag = 0;
if(YearControlUtils.isFirstHalf()){
	firstHalfFlag = 1;
}
%>
<style type="text/css">
	td{
		border-bottom:1px dashed #B3C0C8;
		padding-bottom:4px;
		padding-top:4px;
	}
</style>
<script type="text/javascript">
	$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/table/complexTbSustain.css");
	$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
	$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
	$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/resources/css/component.css");
	$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/component_blue.css");

	$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
	$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
	$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js", function(){
		$ENV.onDomReady(function(){
			var firstHalfFlag = '<%=firstHalfFlag%>';

		    if(firstHalfFlag=='1') {
				$('firstHalfFlag1_<%=id%>').style.display="";
				$('firstHalfFlag0_<%=id%>').style.display="none";
			}else {
				$('firstHalfFlag1_<%=id%>').style.display="none";
				$('firstHalfFlag0_<%=id%>').style.display="";
			}
			setChecked();//设置选项
			selectone();//设置显示
			//页面初始化

			//点击查询按钮
			function query(){
				var selectFlag = $('random_<%=id%>').checked;
				var beginday = $('beginDate_<%=id%>').value;
				var endday = $('endDate_<%=id%>').value;
				if(selectFlag==true) {
					if(beginday=="") {
						alert("请填写开始时间");
						return;
					}
					if(endday=="") {
						alert("请填写结束时间");
						return;
					}
					if(beginday>endday){
						alert("开始时间不能大于结束时间");
						return;
					}
				}


				var dateTypeValue = isSelectValue();
				var cbf = $('callBackFunction_<%=id%>');
				if(typeOf(${callBackFunction})=="function") {
					window.${callBackFunction}(beginday, endday, dateTypeValue);
				}
			}

			//单选 过滤项
			function selectone() {
				var dateTypes = document.getElementsByName('dateType_<%=id%>');
				var checkflag = false;//是否点击选项的标志

				for(var i=0; i<dateTypes.length; i++){
					if(dateTypes[i].checked==true){
						checkflag = true;//点击了选项
						var iValue = dateTypes[i].value;
						if(iValue=="random") {
							document.getElementById('random_div_<%=id%>').style.display="";
						}else{
							document.getElementById('random_div_<%=id%>').style.display="none";
						}
					}
				}

				if(checkflag==false) {
					document.getElementById('random_div_<%=id%>').style.display="none";
				}
			}

			//判断是否单选了任何一个过滤条件，选择了返回true  否则返回false
			function isSelect() {
				var dateTypes = document.getElementsByName('dateType_<%=id%>');
				var checkflag = false;//是否点击选项的标志

				for(var i=0; i<dateTypes.length; i++){
					if(dateTypes[i].checked==true){
						checkflag = true;//点击了选项
					}
				}
				return checkflag;
			}

			//得到选择的值
			function isSelectValue() {
				var dateTypes = document.getElementsByName('dateType_<%=id%>');
				var iValue = "";

				for(var i=0; i<dateTypes.length; i++){
					if(dateTypes[i].checked==true){
						iValue = dateTypes[i].value;
					}
				}
				return iValue;
			}

			//设置选择的项
			function setChecked(){
				var ty = $('yearStatProvider.dateType_<%=id%>').value;
				if(ty!="") {
					var dateTypes = document.getElementsByName('dateType_<%=id%>');
					for(var i=0; i<dateTypes.length; i++){
						if(dateTypes[i].value == ty ){
							dateTypes[i].checked=true;
						}
					}
				}
			}

			//查询事件
			$('btnQuery_<%=id%>').addEvent('click', function(){
				query();
			});

			//单击单选框事件
			$('year_<%=id%>').addEvent('click', function(){
				selectone();
			});
			$('firstHalf_<%=id%>').addEvent('click', function(){
				selectone();
			});
			$('secondHalf_<%=id%>').addEvent('click', function(){
				selectone();
			});
			$('quarter_<%=id%>').addEvent('click', function(){
				selectone();
			});
			$('month_<%=id%>').addEvent('click', function(){
				selectone();
			});
			$('random_<%=id%>').addEvent('click', function(){
				selectone();
			});

		});
	});

</script>

<div class="panel tMargin">
	<div class="panel_header">
		<div class="panel_title panel_titleListIco">
			分时段统计
		</div>
	</div>
	<div class="panel_content panel_content_table">
		<form action="" method="post" id="queryform_<%=id%>" name="queryform_<%=id%>">
			<input type="hidden" name="actionUrl_<%=id%>" id="actionUrl_<%=id%>" value="${actionUrl}" /><!-- 路径 -->
			<input type="hidden" name="yearStatProvider.dateType_<%=id%>" id="yearStatProvider.dateType_<%=id%>" value="${yearStatProvider.dateType}" />
			<input type="hidden" name="callBackFunction_<%=id%>" id="callBackFunction_<%=id%>" value="${callBackFunction}">

			<table class="st"  style="width:100%;">
				<tr>
					<td class="tbLable fl"  colspan="4"  style="color:#6B6B6B;">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                                      本年度:<input type="radio" id="year_<%=id%>" name="dateType_<%=id%>" value="year"  /> &nbsp;&nbsp;
						<span id="firstHalfFlag1_<%=id%>" style="display: none;">
							  上半年:<input type="radio" id="firstHalf_<%=id%>" name="dateType_<%=id%>" value="firstHalf"  /> &nbsp;&nbsp;
						</span>
						<span id='firstHalfFlag0_<%=id%>' style="display: none;">
							  下半年:<input type="radio" id="secondHalf_<%=id%>" name="dateType_<%=id%>" value="secondHalf"  /> &nbsp;&nbsp;
						</span>
	                                                      本季度:<input type="radio" id="quarter_<%=id%>" name="dateType_<%=id%>" value="quarter"   /> &nbsp;&nbsp;
                                                                 本月:<input type="radio" id="month_<%=id%>" name="dateType_<%=id%>" value="month"  /> &nbsp;&nbsp;
                                                                 自定义时间段:<input type="radio" id="random_<%=id%>" name="dateType_<%=id%>" value="random"  /> &nbsp;&nbsp;
					</td>
				</tr>

				<tr id="random_div_<%=id%>">
					<td class="tbLable fr" style="color:#6B6B6B;">开始时间：</td>
					<td class="tbValue fl" >
						<input type="text" name="beginDate_<%=id%>" style="width:137px;"
						value="${yearStatProvider.beginDate}" class="Wdate" id="beginDate_<%=id%>"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
						<span style="color:red;">&nbsp;&nbsp;*</span>
					</td>
					<td class="tbLable fr" style="color:#6B6B6B;">结束时间：</td>
					<td class="tbValue fl" >
						<input type="text" name="endDate_<%=id%>" style="width:137px;"
						value="${yearStatProvider.endDate}" class="Wdate" id="endDate_<%=id%>"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
						<span style="color:red;">&nbsp;&nbsp;*</span>
					</td>
				</tr>

				<tr>
					<td colspan="4" class="fc" style="border: 0px;">
						<div class="stBtnBar">
							<a class="pop_button" id="btnQuery_<%=id%>"><span style="cursor:pointer;">查 询</span></a>
							<a class="pop_button" id="btnReset_<%=id%>" href="javascript:document.getElementById('queryform').reset();"><span>重 置</span></a>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<div class="split_line"></div><!-- 分隔线 -->

<script type="text/javascript">
</script>

