<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>日程安排</title>

		<script src="${ctx}/agenda/borderlayout/skin/blue/js/calendar.js" type="text/javascript"></script>
		<script src="${ctx}/agenda/borderlayout/skin/blue/js/common.js" type="text/javascript"></script>
		<script src="${ctx}/agenda/borderlayout/skin/blue/js/popwindow.js" type="text/javascript"></script>
		<script src="${ctx}/agenda/borderlayout/skin/blue/js/UI_focusInput.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/SimpleUI/SimpleUI.js" type="text/javascript"></script>

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/template/borderlayout/skin/blue/css/page.css");
			$ENV.loader.loadStyleSheet("${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
			$ENV.loader.loadStyleSheet("${ctx}/agenda/borderlayout/skin/blue/css/agenda.css");
			$ENV.loader.loadStyleSheet("${ctx}/agenda/borderlayout/skin/blue/css/layout.css");
			$ENV.loader.loadStyleSheet("${ctx}/agenda/borderlayout/skin/blue/css/main.css");
			$ENV.loader.loadStyleSheet("${ctx}/agenda/borderlayout/skin/blue/css/paging.css");
			$ENV.loader.loadStyleSheet("${ctx}/agenda/borderlayout/skin/blue/css/function.css");
			$ENV.loader.loadStyleSheet("${ctx}/agenda/borderlayout/skin/blue/css/tab.css");
			$ENV.loader.loadStyleSheet("${ctx}/agenda/borderlayout/skin/blue/css/tooltips.css");
			$ENV.loader.loadStyleSheet("${ctx}/agenda/borderlayout/skin/blue/css/UI_focusInput.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js", function(){
				$ENV.onDomReady(function(){
					setTableToPick("pickme");
					stripeTables;
					highlightRows;
					/*domReady(lockRow);*/
					lockRowUsingCheckbox;
				});
			});
			var festivalPath = "${ctx}/agenda/borderlayout/skin/blue/js/festival.xml";
			var agendas = [];
			<c:forEach items="${agendaList}" var="agenda">
				agendas.push({
					id : '${agenda.agendaId}',
					title : '<c:out value="${agenda.agendaTitle}" escapeXml="true" />',
					startDate : <fmt:formatDate value="${agenda.startTime}" pattern="yyyyMMdd"/>,
					endDate : <fmt:formatDate value="${agenda.endTime}" pattern="yyyyMMdd"/>
				});
			</c:forEach>

			var cld;
			function doDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/agenda/agenda/agenda_detail.action?id='+id+'&t_date=' + new Date().getTime(),
					width : 0.8,
					height : 0.9,
					title : '我的日程安排详情'
				});
			}

			function getDateLink(year, month, day) {
				var dayLink = '<p style="display:none">'
							+ (day)
							+ '</p><table border="0"  width="100%" bordercolor="#CCCCCC" bordercolordark="#FFFFFF" style="table-layout:fixed"><tr><td class="dateHandTd"> '
							+ '<a class="epx12hei5" target="_self" href="'
							+ "<s:url action="agenda_list" namespace="/agenda/agenda"/>"
							+ "?agenda.startTime=" + year + "-" + month + "-" + day + " 00:00:00"
							+ "&agenda.endTime=" + year + "-" + month + "-" + day + " 23:59:59"
							+ '">'
							+ (month) + '月' + (day) + '日'
							+ '</a></td></tr><tr height="60"><td nowrap width=100% valign="top" align="left" '
							+ 'style="overflow:hidden; text-overflow:ellipsis;">';

				var date = "" + year + (month < 10 ? "0"+month : month) + (day < 10 ? "0" + day : day);
				date = parseInt(date);
				for(var i=0;i<agendas.length;i++){
					var agenda = agendas[i];
					if(agenda.startDate <= date && date <= agenda.endDate){
						dayLink += '<a class="cpx12lan" href="javascript:doDetail(\''+agenda.id+'\')">'+agenda.title+'</a><br/>';
					}
				}


				dayLink +='</td></tr></table>';
				return dayLink;
			}

			function drawCld(SY, SM) {

				var i, sD, s, size;
				cld = new calendar(SY, SM);

				if (SY > 1874 && SY < 1909)
					yDisplay = '光绪' + (((SY - 1874) == 1) ? '元' : SY - 1874);
				if (SY > 1908 && SY < 1912)
					yDisplay = '宣统' + (((SY - 1908) == 1) ? '元' : SY - 1908);
				if (SY > 1911 && SY < 1950)
					yDisplay = '民国' + (((SY - 1911) == 1) ? '元' : SY - 1911);
				if (SY > 1949)
					yDisplay = '建国' + (((SY - 1949) == 1) ? '元' : SY - 1949);

				for (i = 0; i < 42; i++) {

					gObj = document.getElementById('GD' + i);
					sObj = document.getElementById('SD' + i);
					lObj = 1;

					gObj.className = '';

					sD = i - cld.firstWeek;

					if (sD > -1 && sD < cld.length) { //日期內

						//！！！！！！！！！！！！！！！！！！！在这里确定是新建日程提醒，还是看已有的日程提醒！！！！！！！！！！！！！！！！
						//window.alert(SM);
						var dayLink = getDateLink(SY, SM + 1, sD + 1);
						sObj.innerHTML = dayLink;

						if (cld[sD].isToday)
							gObj.className = 'todayColor'; //今日顏色

						sObj.style.color = cld[sD].color; //國定假日顏色

						if (cld[sD].lDay == 1) //顯示農曆月
							if (cld[sD].isLeap) //閏月
								lObj.innerHTML = '<b>闰' + cld[sD].lMonth + '月'
										+ (leapDays(cld[sD].lYear) == 29 ? '小' : '大')
										+ '</b>';
							else
								//非閏月
								lObj.innerHTML = '<b>'
										+ cld[sD].lMonth
										+ '月'
										+ (monthDays(cld[sD].lYear, cld[sD].lMonth) == 29 ? '小'
												: '大') + '</b>';
						else
							//顯示農曆日
							lObj.innerHTML = cDay(cld[sD].lDay);

						s = cld[sD].lunarFestival;
						if (s.length > 0) { //農曆節日
							s = s.fontcolor('red');
						} else { //國曆節日
							s = cld[sD].solarFestival;
							if (s.length > 0) {
								s = (s == uFtvH.BlackFriday) ? s.fontcolor('black') : s
										.fontcolor('navy');
							} else { //廿四節氣
								s = cld[sD].solarTerms;
								if (s.length > 0)
									s = s.fontcolor('chocolate');
							}
						}

						if (s.length > 0)
							lObj.innerHTML = s;

					} else { //非日期
						sObj.innerHTML = '';
						lObj.innerHTML = '';
					}
				}
			}

			function changeCld() {
				var y, m;
				var cLD = document.getElementById("CLD");
				var sY = document.getElementById("SY");
				var sM = document.getElementById("SM");
				y = sY.selectedIndex + 1900;
				m = sM.selectedIndex + 1;

				window.location.href = "<s:url action="agenda_calendar" namespace="/agenda/agenda"/>?year="+y+"&month="+m;
				//drawCld(y, m);
			}

			function pushBtm(K) {
				var cLD = document.getElementById("CLD");
				var sY = document.getElementById("SY");
				var sM = document.getElementById("SM");

				switch (K) {

				case 'YU':
					if (sY.selectedIndex > 0)
						sY.selectedIndex--;
					break;
				case 'YD':
					if (sY.selectedIndex < 200)
						sY.selectedIndex++;
					break;
				case 'MU':
					if (sM.selectedIndex > 0) {
						sM.selectedIndex--;
					} else {
						sM.selectedIndex = 11;
						if (sY.selectedIndex > 0)
							sY.selectedIndex--;
					}
					break;
				case 'MD':
					if (sM.selectedIndex < 11) {
						sM.selectedIndex++;
					} else {
						sM.selectedIndex = 0;
						if (sY.selectedIndex < 200)
							sY.selectedIndex++;
					}
					break;
				default:
					sY.selectedIndex = tY - 1900;
					sM.selectedIndex = tM;
				}
				changeCld();

				return false;

			}

			var Today = new Date();
			var tY = Today.getFullYear();
			var tM = Today.getMonth();
			var tD = Today.getDate();
			//////////////////////////////////////////////////////////////////////////////

			var width = "130";
			var offsetx = 5;
			var offsety = 20; //农历提示信息DIV的显示偏移量。

			var x = 0;
			var y = 0;
			var showNow = 0;
			var sw = 0;
			var cnt = 0;

			var detail, dStyle;
			//document.onmousemove = mEvn;

			//顯示詳細日期資料
			function mOvr(event,v) {
				var s, festival, spcday;
				var sObj = document.getElementById('SD' + v);
				//window.alert(sObj.childNodes[0].innerHTML);
				var d;
				try {
					d = sObj.childNodes[0].innerText - 1;
				} catch (e) {
					return;
				}
				//window.alert(sObj.id);

				//sYear,sMonth,sDay,week,
				//lYear,lMonth,lDay,isLeap,
				//cYear,cMonth,cDay

				if (sObj.innerHTML != '') {
					sObj.style.cursor = 'help';
					//window.alert(cld[d]);
					spcday = cld[d].sMonth == 3 && cld[d].sDay == 3 * 7 ? unescape('Seamoon\'s birthday')
							: '';
					//spcday = "";

					if (cld[d].solarTerms == '' && cld[d].solarFestival == ''
							&& cld[d].lunarFestival == '' && spcday == '')
						festival = '';
					else
						festival = '<TABLE WIDTH=100% BORDER=0 CELLPADDING=2 CELLSPACING=0 BGCOLOR="#CCFFCC"><TR><TD>'
								+ '<FONT COLOR="#000000" STYLE="font-size:9pt;">'
								+ cld[d].solarTerms
								+ ' '
								+ cld[d].solarFestival
								+ ' '
								+ cld[d].lunarFestival
								+ ' '
								+ spcday
								+ '</FONT></TD>'
								+ '</TR></TABLE>';

					s = '<TABLE WIDTH="120" BORDER=0 CELLPADDING="0" CELLSPACING=0 BGCOLOR="#cc3300" style="opacity:0.8; -moz-opacity:0.8; filter:Alpha(opacity=80)"><TR><TD>'
							+ '<TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0><TR><TD ALIGN="right"><FONT COLOR="#ffffff" STYLE="font-size:9pt;">'
							+ cld[d].sYear
							+ ' 年 '
							+ cld[d].sMonth
							+ ' 月 '
							+ cld[d].sDay
							+ ' 日<br>星期'
							+ cld[d].week
							+ '<br>'
							+ '<font color="violet">农历'
							+ (cld[d].isLeap ? '闰 ' : ' ')
							+ cld[d].lMonth
							+ ' 月 '
							+ cld[d].lDay
							+ ' 日</font><br>'
							+ '<font color="yellow">'
							+ cld[d].cYear
							+ '年 '
							+ cld[d].cMonth
							+ '月 '
							+ cld[d].cDay
							+ '日</font>'
							+ '</FONT></TD></TR></TABLE>'
							+ festival
							+ '</TD></TR></TABLE>';

					detail.innerHTML = s;

					var posX, posY;
					//var x =0 , y=0;
					//window.alert(x + "," + y);
					if (showNow == 0) {
						posX = x + offsetx - (width / 2);
						posY = y + offsety;
						//if(posY>280) posY = y-16 -detail.offsetHeight;
						//if(posX<8) posX = 8;
						dStyle.left = posX;
						dStyle.top = posY;
						dStyle.visibility = "visible";
						showNow = 1;
					}
				}
			}

			//清除詳細日期資料
			function mOut() {
				if (cnt >= 1) {
					sw = 0;
				}
				if (sw == 0) {
					showNow = 0;
					dStyle.visibility = "hidden";
				} else
					cnt++;
			}

			function mEvn(event) {
				x = event.x;
				y = event.y;
				if (document.documentElement.scrollLeft)
					x += document.documentElement.scrollLeft;
				if (document.documentElement.scrollTop)
					//y += document.documentElement.scrollTop;

				var posX, posY;

				if (showNow) {
					posX = x + offsetx - (width / 2);
					posx1 = x + offsetx;
					posY = y + offsety;
					/* if (posY<55) posY=60;
					 if(posY>90) posY = y-16-detail.offsetHeight;
					/ if(y>75) posY=2;
					if(posX<4) posX = 4;
					if(posx1>90) posX = 35;*/
					dStyle.left = posX;
					dStyle.top = posY;
				}
			}

			///////////////////////////////////////////////////////////////////////////

			function initialize() {
				var key;

				//月曆

				detail = document.getElementById("detail");
				dStyle = detail.style;
				//alert();
				var cLD = document.getElementById("CLD");
				var sY = document.getElementById("SY");
				var sM = document.getElementById("SM");

				var targetYear = null;
				<c:if test="${year != null}">
				targetYear = ${year};
				</c:if>
				var targetMonth = null;
				<c:if test="${month != null}">
				targetMonth = ${month};
				</c:if>
				sY.selectedIndex = targetYear!=null? targetYear - 1900 : tY - 1900;
				sM.selectedIndex = targetMonth!=null? targetMonth - 1 : tM;
				drawCld(sY.selectedIndex + 1900, sM.selectedIndex);

			}

			addLoadEvent(initialize);

			if(document.body != null){
				document.body.onmousemove = function() {
					mEvn(event);
				};
			}

			suckerfish(sfHover, "INPUT");
			suckerfish(sfFocus, "INPUT");
			suckerfish(sfHover, "TEXTAREA");
			suckerfish(sfFocus, "TEXTAREA");

			// 显示鼠标翻转图片
			function showButOv(id){
				document.getElementById(id).className = id + "Mv";
			}

			// 显示鼠标正常图片
			function showButOu(id){
				document.getElementById(id).className = id + "Mu";
			}

		</script>
	</head>

	<body class="twoColElsLtHdr" id="template" onmouseover="mEvn(event)">
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<ap:operationbutton />
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 复合面板 -->
			<cp:start defaultTitle="日程安排简介" ctx="${ctx}" icoPath="/agenda/borderlayout/skin/blue/img/calendar_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','日程安排简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','日程安排搜索');">查 询</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle" style="text-align: left;">
						关于日程安排
					</div>
					<div class="cpMsgContext" style="text-align: left;">
						<cpc:tc ctx="${ctx}" showId="agenda_calender"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 5px;overflow: hidden;"></div>

					<!-- 联系方式 -->
					<div class="cpMsgContactInfoTitle" style="text-align: left;">
						业务联系方式
					</div>
					<div class="cpMsgContactInfoContext" style="text-align: left;">

					</div>
				</cp:msg>
				<cp:search show="false" divId="cp002">
					<table class="st" width="100%">
	   					<tr>
							<td class="tbLable fr">
				   				时间选择：
				   			</td>
				   			<td class="tbValue fl">
				   				<select style="height:21px;float: left;margin-right: 10px;" onchange="changeCld()" id="SY" name="SY">
									<script language="JavaScript" type="text/javascript">
										for (i = 1900; i < 2101; i++)
										document.write('<option>' + i + '年');
									</script>
								</select>
								<!-- 左箭头 -->
								<img src="${ctx}/agenda/borderlayout/skin/blue/img/mLeft.gif" style="float: left;width: 14px; height: 21px;cursor: hand;" title="点击查看上一月日程" onclick="pushBtm('MU')"/>
								<!-- 月选择 -->
								<select style="height:21px;float: left;" onchange="changeCld()" id="SM" name="SM">
									<script language="JavaScript" type="text/javascript">
										for (i = 1; i < 13; i++)
										document.write('<option>' + i + '月');
									</script>
								</select>
								<!-- 右箭头 -->
								<img src="${ctx}/agenda/borderlayout/skin/blue/img/mRight.gif" style="float: left;width: 14px; height: 21px;cursor: hand;" title="点击查看下一月日程" onclick="pushBtm('MD')"/>
	   						</td>
	   					</tr>
			   		</table>
				</cp:search>
			<cp:end> </cp:end>

			<div class="panel tMargin bMargin">
				<!-- 头部 -->
				<div class="panel_header">
					<!-- 标题 -->
					<div class="panel_title panel_titleListIco">日程安排</div>
					<!-- 右侧按钮 -->
					<div class="panel_btn_bar pop_button_bar"></div>
				</div>

				<div class="panel_content panel_content_table">
					<!-- 日历开始 -->
					<!-- 这里是月历的开始部份 -->
					<div id="detail" style="Z-INDEX: 3; FILTER: shadow(color = #333333, direction = 135); POSITION: absolute;"></div>

						<table border="0" width="100%" cellpadding="0" cellspacing="0" align="center">
							<tbody>
								<tr>
									<form id="CLD" name="CLD" onsubmit="return false;">
										<td width="100%" align="middle">
											<!-- 日历开始 -->
											<table width="100%" border="0" cellpadding="0" cellspacing="0" class="pickme">
												<tbody>
													<tr bgcolor="#e1f6ff">
														<td width="14%" height="35" align="middle" bgcolor="#e1f6ff" class="cpx12red">
															<div class="xq07"></div>
														</td>
														<td width="14%" height="35" align="middle" bgcolor="#e1f6ff">
															<div class="xq01"></div>
														</td>
														<td width="14%" height="35" align="middle" bgcolor="#e1f6ff">
															<div class="xq02"></div>
														</td>
														<td width="14%" height="35" align="middle" bgcolor="#e1f6ff">
															<div class="xq03"></div>
														</td>
														<td width="14%" height="35" align="middle" bgcolor="#e1f6ff">
															<div class="xq04"></div>
														</td>
														<td width="14%" height="35" align="middle" bgcolor="#e1f6ff">
															<div class="xq05"></div>
														</td>
														<td width="14%" height="35" align="middle" bgcolor="#e1f6ff" class="cpx12red">
															<div class="xq06"></div>
														</td>
													</tr>
													<script language="JavaScript" type="text/javascript">
														var gNum, color1, color2;

														// 星期六顏色
														switch (conWeekend) {
														case 1:
															color1 = 'black';
															color2 = color1;
															break;
														case 2:
															color1 = 'green';
															color2 = color1;
															break;
														case 3:
															color1 = 'red';
															color2 = color1;
															break;
														default:
															color1 = 'green';
															color2 = 'red';
														}

														for (i = 0; i < 6; i++) {
															document.write('<tr align=center>');
															for (j = 0; j < 7; j++) {
																gNum = i * 7 + j;
																document.write('<td id="GD' + gNum + '" onMouseOver="mOvr(event,' + gNum + ')" onMouseOut="mOut(event)" bgColor=#EAEEEE><font id="SD' + gNum + '" size=12 ');
																if (j == 0)
																	document.write(' color=red');
																if (j == 6) {
																	if (i % 2 == 1)
																		document.write(' color=' + color2);
																	else
																		document.write(' color=' + color1);
																}
																document.write(' TITLE=""> </font></td>');
															}
															document.write('</tr>');
														}
													</script>
												</tbody>
											</table>
										</td>
									</form>
								</tr>
							</tbody>
						</table>

						<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
							<tr>
								<td align="center">
									<span class="cpx12red">注</span><span>：点击具体某天可新增日程安排/点击已有日程安排可查看</span>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 这个用于清除浮动的元素应当紧跟 #mainContent div 之后，以便强制 #container div 包含所有的子浮动 -->
				<br class="clearfloat" />
			<!-- 日历结束 -->
			</div>
		</div>
	</body>
</html>