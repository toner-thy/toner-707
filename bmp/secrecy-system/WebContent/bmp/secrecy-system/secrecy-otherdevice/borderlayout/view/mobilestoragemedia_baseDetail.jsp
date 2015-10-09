<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="dep"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/userinfo" prefix="ui"%>
<%@ taglib uri="http://www.cdthgk.com/tags/secrecyPerson/search" prefix="sp"%>
<%@ taglib uri="http://www.cdthgk.com/tags/keySection" prefix="section" %>
<%@ taglib uri="http://www.cdthgk.com/tags/keyPart" prefix="part" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

		<!-- css -->
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/formcheck/css/formcheck.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/page.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/platform/template/borderlayout/skin/blue/css/ecTable.css" type="text/css" rel="stylesheet" />

		<!-- 复杂表格CSS支持 -->
		<link href="${ctx}/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet"/>

		<!-- js -->
		<script src="${ctx}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-more-1.4.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/formcheck/1.4/formcheck.js");
			$ENV.loader.loadJavaScript("${ctx}/resources/js/notimoo/notimoo-1.2.1.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/layout/borderlayout/js/TabUtils.js");
			$ENV.loader.loadJavaScript("${ctx}/platform/theme/borderlayout/resources/js/ectable/EcTable.js", function(){
				$ENV.onDomReady(function(){
					// 页面加载完成，执行选择是否要害部门、部位
					showKeysectionpart('${secrecyMobilestoragemedia.isbelongKeydepartment}');
					var type = '${secrecyMobilestoragemedia.keySection.keySectionId}';
					if(type == ''){
						type = 2;
					} else {
						type = 1;
					}
					doChangeType(type);


				});
			});
			// 隐藏、显示是否属于要害部门、部位
			function showKeysectionpart(value){
				if(value == 1){
					$('keysectionpart_show').style.display = '';
				} else {
					$('keysectionpart_show').style.display = 'none';
				}
			}
			// 隐藏、显示要害部门、部位名称
			function doChangeType(value){
				if(value == 1){
					//显示要害部门, 隐藏要害部位信息及值
					$('keypart_show1').style.display = 'none';
					$('keypart_show2').style.display = 'none';
					$('keysection_show1').style.display = '';
					$('keysection_show2').style.display = '';
					$('keysection_show2').set("text","${not empty secrecyMobilestoragemedia.keySection.keySectionId ? secrecyMobilestoragemedia.keySection.department.departmentName : ''}");
					$('typeOfCute').set("text","要害部门");

				} else {
					//显示要害部位, 隐藏要害部门信息及值
					$('keypart_show1').style.display = '';
					$('keypart_show2').style.display = '';
					$('keypart_show2').set("text", "${not empty secrecyMobilestoragemedia.keyPart.partId ? secrecyMobilestoragemedia.keyPart.partName : ''}");
					$('typeOfCute').set("text","要害部位");
					$('keysection_show1').style.display = 'none';
					$('keysection_show2').style.display = 'none';
				}
			}
		</script>

			<!-- 涉密人员panel开始 -->
			<div class="panel tMargin bMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
							涉密移动存储介质
					</div>
				</div>

				<div class="panel_content panel_content_table">
					<form id="form_secrecyMobilestoragemedia_add" action="<s:url namespace='/secrecysystem/secrecymobilestoragemedia' action='mobilestoragemedia_editing' includeParams='true'/>" method="post">
						<!-- 隐藏域 -->
						<input type="hidden" id="secrecyMobilestoragemedia.secrecymobilestoragemediaId" name="secrecyMobilestoragemedia.secrecymobilestoragemediaId" value="${secrecyMobilestoragemedia.secrecymobilestoragemediaId }" />
						<table class="content_table" width="100%">
							<tr>
								<td class="tbLable fr">
									介质类型：
								</td>
								<td class="tbValue fl" >
									<dictionary:text tableCode="bmp" fieldCode="media_type" optionValue="${secrecyMobilestoragemedia.mediaType }"></dictionary:text>
								</td>
								<td class="tbLable fr">
									&nbsp;
								</td>
								<td class="tbValue fl" >
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									密级：
								</td>
								<td class="tbValue fl">
									<dictionary:text tableCode="bmp" fieldCode="secrecy_level_thing" optionValue="${secrecyMobilestoragemedia.secrecyLevel }" ></dictionary:text>
								</td>
								<td class="tbLable fr">
									责任人：
								</td>
								<td class="tbValue fl">
									${secrecyMobilestoragemedia.dutyPerson.name }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									编号：
								</td>
								<td class="tbValue fl">
									${secrecyMobilestoragemedia.mediaNo }
								</td>
								<td class="tbLable fr">
									序列号：
								</td>
								<td class="tbValue fl">
									${secrecyMobilestoragemedia.mediaSeq }
								</td>
							</tr>
							<tr>
								<td class="tbLable fr" nowrap="nowrap">
									是否属于要害部门、部位：
								</td>
								<td class="tbValue fl">
 									<dictionary:text tableCode="bmp" fieldCode="is_section_part" optionValue="${secrecyMobilestoragemedia.isbelongKeydepartment}"></dictionary:text>
								</td>
								<td class="tbLable fr">
									部门名称
								</td>
								<td class="tbValue fl">
									${secrecyMobilestoragemedia.department.departmentName }
								</td>
							</tr>
							<tr id="keysectionpart_show" style="display: none">
								<td class="tbLable fr">
									类型：
								</td>
								<td class="tbLable fl" id="typeOfCute">
								</td>
								<td class="tbLable fr" id="keysection_show1" style="display: ''">
									要害部门：
								</td>
								<td class="tbValue fl" id="keysection_show2" style="display: ''">

								</td>
								<td class="tbLable fr" id="keypart_show1" style="display: none">
									要害部位：
								</td>
								<td class="tbValue fl" id="keypart_show2" style="display: none">

								</td>
							</tr>
							<tr>
								<td class="tbLable fr">
									介质描述：
								</td>
								<td class="tbValue fl" colspan="3">
									${secrecyMobilestoragemedia.mediaDescription }
								</td>
							</tr>
						</table>

						<!-- 隐藏提交按钮 -->
						<div align="center">
							<input type="submit" id="sub" value="sub" style="display: none;" />
						</div>
					</form>
				</div>
			</div>
			<!-- 涉密人员panel结束 -->

