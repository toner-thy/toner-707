<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>要害部位详情</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<!-- css -->
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadStyleSheet("${ctx}/platform/css/public/print/printPage.css");

			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/My97DatePicker/WdatePicker.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/SimpleUI.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js",function(){
				$ENV.onDomReady(function(){

				});
			});

			function preview(oper){
				if (oper < 10)
					{
					bdhtml=window.document.body.innerHTML;//获取当前页的html代码
					sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域
					eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域

					prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html

					prnhtmlprnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html

					window.document.body.innerHTML=prnhtmlprnhtml;
					window.print();
					window.document.body.innerHTML=bdhtml;
				} else {
					window.print();
				}
			}

			// 查看详情
			function doDetail(secrecyPersonId, partId){
				$ENV.dialog.open({
					url : '${ctx}/bmp/part/keyPart_detailPerson.action?partPerson.secrecyPerson.secrecyPersonId='+secrecyPersonId+'&part.partId=' + partId +'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '要害部位涉密人员详情'
				});
			}
		</script>

		<s:actionmessage theme="messages"/>

		<!--print 这个属性可以在打印时有效-->
		<style type="text/css" media="print">
			.no_print{display:none;}
			.PageNext{page-break-after: always;}
		</style>

	</head>

	<body style="overflow-y:auto; ">
		<div class="panel_header no_print" >
			<div class="panel_title panel_titleListIco no_print">
				<div style="width: 100%; font-size:22px; padding-top: 10px; padding-bottom: 8px; font-weight: bold; font-family: '楷体_gb2312';" align="center">
					保密要害部位【${part.partName}】详情
				</div>
			</div>
		</div>

		<div class="panel tMargin" style="margin-top: -1px;">
			<!-- 保密要害部位panel开始 -->
			<div class="panel tMargin" style="margin-top: -1px;">
				<div class="panel_header no_print">
					<div class="panel_title panel_titleListIco no_print">
						保密要害部位 - 基本信息
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table" cellspacing="0" cellpadding="0" width="100%;">
						<tr>
							<td class="tbLable fr">
								单位名称
							</td>
							<td class="tbValue fl" colspan="3">
								${part.organ.organName}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								部位名称
							</td>
							<td class="tbValue fl">
								${part.partName}
							</td>
							<td class="tbLable fr">
								主管部门
							</td>
							<td class="tbValue fl">
								${part.department.departmentName}
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								涉密等级
							</td>
							<td class="tbValue fl">
								<dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${part.secrecyLevel}"></dictionary:text>
							</td>
							<td class="tbLable fr">
								主要负责人
							</td>
							<td class="tbValue fl">
								${part.person.name}
							</td>
						</tr>
						<tr>
							<td class="fr">
								涉密人员数量
							</td>
							<td class="fl" colspan="3">
								<label title="涉密人员数量由当前系统自动统计">${partPersonSize}</label>
							</td>
						</tr>
						<tr>
							<td class="fr"  style="white-space: nowrap;">
								具体位置
							</td>
							<td class="fl" colspan="3">
								${part.location}
							</td>
						</tr>
						<tr>
							<td class="fr"   style="white-space: nowrap;">
								涉密工作事项及范围
							</td>
							<td class="fl" colspan="3">
								${part.secScope}
							</td>
						</tr>
						<tr>
							<td class="fr">
								技防措施
							</td>
							<td class="fl" colspan="3">
								${part.skillMeasure}
							</td>
						</tr>
					</table>
					<table class="content_table st no_print" width="100%;">
						<tr>
							<td class="tbLable fr">
								保密制度
							</td>
							<td class="fl" colspan="3">
								<attach:view uploadBehavior="bmpUploadBehavior" allowDownload="true" attachments="${attachments}" titleText="附件"></attach:view>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 保密要害部位panel结束 -->


			<!-- 保密要害部位panel开始 -->
			<s:if test="#request.keyPart_changelist.size>0">
				<div class="split_line"></div><!-- 分隔线 -->
				<div class="panel tMargin" style="margin-top: -1px;">
					<div class="panel_header no_print">
						<div class="panel_title panel_titleListIco no_print">
							保密要害部位 - 密级变更记录
						</div>
					</div>
					<div class="panel_content panel_content_table">
						<ec:table items="keyPart_changelist" var="keyPartChange" tableId="keyPart_changelist" border="0"
							action="${ctx}/bmp/part/keyPart_list_change.action" showPagination="false"
							imagePath="${ctx}/platform/template/borderlayout/skin/blue/img/ec/*.gif"
							width="100%" filterable="false" autoIncludeParameters="true" sortable="false">
							<ec:row>
								<ec:column property="changeDate" title="变更时间" width="10%"  cell="date" format="yyyy-MM-dd" />
								<ec:column property="null" title="原密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_section" optionValue="${keyPartChange.beforeLevel}"/>
								</ec:column>
								<ec:column property="null" title="变更后密级" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_section" optionValue="${keyPartChange.afterLevel}"/>
								</ec:column>
								<ec:column property="null" title="保密期限变更" width="10%">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_limit" optionValue="${keyPartChange.changeTimeState}"/>
								</ec:column>
								<ec:column property="changeReason" title="变更原因" width="40%" cell="text" alias="size=45"/>
							</ec:row>
						</ec:table>
					</div>
				</div>
			</s:if>
			<!-- 保密要害部位panel结束 -->



			<!-- 保密要害部位人员panel开始 -->
			<c:if test="${partPersonSize > 0}">
				<div class="split_line"></div><!-- 分隔线 -->
				<div class="panel tMargin">
					<div class="panel_header no_print">
						<div class="panel_title panel_titleListIco no_print">
							保密要害部位 - 人员列表
						</div>
						<div class="panel_btn_bar pop_button_bar no_print">
						</div>
					</div>
					<div class="panel_content panel_content_table">
						<div class="eXtremeTable">
							<table class="tableRegion" cellspacing="0" cellpadding="0" width="100%;">
								<thead>
									<tr>
										<td class="tableHeader" width="15%" >姓 名</td>
										<td class="tableHeader" width="20%" >行政职务</td>
										<td class="tableHeader" width="15%" >涉密等级</td>
										<td class="tableHeader" width="20%" >岗 位</td>
										<td class="tableHeader" width="25%" >联系电话</td>
									</tr>
								</thead>
								<tbody class="tableBody" >
									<c:forEach items="${partPersonList }" var="partPerson" varStatus="x">
										<tr class="${x.count % 2 != 0 ? 'odd' : 'even'}" onmouseover="this.className='highlight'" onmouseout="this.className='${x.count % 2 != 0 ? 'odd' : 'even' }'">
											<td>${partPerson.secrecyPerson.userInfo.name }</td>
											<td>${partPerson.secrecyPerson.officeDuty}</td>
											<td><dictionary:text tableCode="bmp" fieldCode="secrecy_level_person" optionValue="${partPerson.secrecyPerson.secrecyPersonLevel}"></dictionary:text></td>
											<td>${partPerson.secrecyPerson.post}</td>
											<td>${partPerson.secrecyPerson.officePhone}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</c:if>
			<!-- 保密要害部位人员panel结束 -->
		</div>
	</body>
</html>