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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>离退休档案列表</title>

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
			function doAdd(action){
				TabUtil.openAsTab({
					url : action,
					title : '离退休档案-新增',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在新增的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									document.getElementById('retireofficial_list_form').submit();
								}
							} else {
								return false;
							}
						}else{
								if(item.content.getContentWindow().needReload){
									document.getElementById('retireofficial_list_form').submit();
								}
						}
					}
				});
			}

			//编辑
			function doEdit(action){
				var items = EcTable.getCheckedItems();
	  			if(items.length==0) {
	  				alert("请选择一项记录。");
	  				return;
	  			} else if(items.length>1) {
	  				alert("最多只能选择一项记录。");
	  				return;
	  			}
	  			TabUtil.openAsTab({
					url : action+"?id="+items[0].value,
					title : '离退休档案-编辑',
					onClose : function(tab, item) {
						if(!item.content.getContentWindow().needReload2){
							if (window.confirm("您确定放弃正在编辑的内容吗？")) {
								if(item.content.getContentWindow().needReload){
									$ENV.page.refresh();
								}
							} else {
								return false;
							}
						} else{
							if(item.content.getContentWindow().needReload){
								$ENV.page.refresh();
							}
						}
					}
				});
			}

			function doDel(action){
				if(window.confirm("确定删除吗？")){
					var items = EcTable.getCheckedItems();
					if(items.length==0){
						alert("请选择一项。");
						return;
					}
					var ids = "?";
					items.each(function(item){
						ids += "ids=" + item.value + "&";
					});
					window.location.href=action+ids;
				}
			}
			function doDetail(action,retireOfficialId){
				environment.dialog.open({
					url : action+"?id="+retireOfficialId+"&date="+new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().x * 0.6,
					title : '离退休档案详情'
				});
			}

			function doPrint(action){
				var items = EcTable.getCheckedItems();
				if(items.length==0){
					alert("请选择一项。");
					return;
				}else if(items.length>1){
					alert("请选择一项。");
					return;
				}
				var date = new Date();
				window.showModalDialog(action+"?id="+items[0].value+"&date="+date.getTime(),
						window,"dialogWidth=650px;dialogHeight=620px;status=no;directories=no;menubar=no;resizable=yes;scrollbars=no;help=no");
			}

			function isCheckedOneClass(ckArr){
			    var pkValue = "";
			    var count = 0;
			    for(var i = 0; i < ckArr.length; i++){
			        var e = ckArr[i];
			        if(e.name != 'idall' && e.type.toLowerCase() == "checkbox"){
			            if(e.checked){
			                count++;
			                pkValue = e.value;
			                if(count > 1)
			                    break;
			            }
			        }
			    }
			    if(count == 1) {
			        return pkValue;
			    }
			    else if(count == 0){
			        alert(checkFirst);
			        return "";
			    }
			    else if(count > 1){
			        alert(onlyOne);
			        return "";
			    }
			}
		</script>
	</head>

	<body>
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

    	<div id="body_content" class="body_content">

			<!-- 复合面板开始 -->
			<cp:start defaultTitle="离退休档案简介" ctx="${ctx}" icoPath="/bmp/retireofficial/borderlayout/skin/blue/img/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp001','离退休档案简介');">关 于</div>
				<div id="cp002Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp002','离退休档案搜索');">查 询</div>
			</cp:start>
			<cp:msg show="false" divId="cp001">
				<!-- 模块简介 -->
				<div class="cpMsgTitle">
					关于离退休档案
				</div>
				<div class="cpMsgContext">
					<cpc:tc ctx="${ctx}" showId="bm_retire_offical"> </cpc:tc>
				</div>

				<!-- 上下之间的间隔，可以调节高度 -->
				<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

			</cp:msg>
			<cp:search show="true" divId="cp002">
				<s:form action="retireofficial_list" id="retireofficial_list_form" theme="simple">
					<table width="100%" class="st">
						<tr>
							<td class="tbLable fr">
								姓 名：
							</td>
							<td class="tbValue fl">
								<input name="retireOfficial.name" id="retireOfficial.name" type="text" value="${retireOfficial.name}">
							</td>
							<td class="tbLable fr">
								籍 贯：
							</td>
							<td class="tbValue fl">
								<input name="retireOfficial.nativePlace" id="retireOfficial.nativePlace" type="text" value="${retireOfficial.nativePlace}">
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								离退休时间：
							</td>
							<td class="tbValue fl" colspan="3">
								<input type="text" id="startTime" title="开始时间" name="startTime" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${startTime }'/>" class="Wdate data_input" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endTime\')||\'2030-10-01\'}'})" readonly="readonly" />
								到
								<input type="text" id="endTime" name="endTime" title="结束时间" name="endTime" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${endTime }'/>" class="Wdate data_input" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2030-10-01'})" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td colspan="4" class="fc" style="border: 0px;">
								<div class="stBtnBar">
									<a class="pop_button" href="javascript:document.getElementById('retireofficial_list_form').submit();"><span>查 询</span></a>
									<a class="pop_button" href="javascript:document.getElementById('retireofficial_list_form').reset();"><span>重 置</span></a>
								</div>
							</td>
						</tr>
					</table>
				</s:form>
			</cp:search>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						离退休档案列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<!-- 右侧按钮 -->
					</div>
				</div>
				<div class="panel_content">
					<s:if test="#request.retireOfficialList.size>0">
						<ec:table items="retireOfficialList" var="retireOfficial" tableId="retireOfficialList" border="0"
							action="${ctx}/bmp/retireofficial/retireofficial_list.action"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" retrieveRowsCallback="limit" sortRowsCallback="limit"
							filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="retireOfficialId" alias="retireOfficialId_checkbox" cell="checkbox" headerCell="checkbox"/>
								 <ec:column property="name" title="姓名"/>
								<ec:column property="null" title="性别">
									<dictionary:text tableCode="person" fieldCode="sex" optionValue="${retireOfficial.sex}"></dictionary:text>
								</ec:column>
								<ec:column property="nativePlace" title="籍贯"/>
								<ec:column property="birthDate" title="生日"  cell="date" format="yyyy-MM-dd"/>
								<ec:column property="startWorkTime" title="参加工作时间" cell="date" format="yyyy-MM-dd"/>
								<ec:column property="retireTime" title="离退休时间" cell="date" format="yyyy-MM-dd"/>
								<ec:column property="mobile" title="移动电话"/>
								<ec:column property="phone" title="固定电话"/>
								<ec:column property="null" title="显示详情">
									<a href="###" onclick="doDetail('${ctx}/bmp/retireofficial/retireofficial_detail.action','${retireOfficial.retireOfficialId}')">
										<img src="${ctx}/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/>
									</a>
								</ec:column>
							</ec:row>
						</ec:table>
					</s:if>
					<s:else>
						<u:noData text="暂无数据！"></u:noData>
					</s:else>
				</div>
			</div>
			<!-- 内容panel结束 -->
		</div>
	</body>
</html>

