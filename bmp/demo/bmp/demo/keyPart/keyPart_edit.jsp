<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib tagdir="/WEB-INF/tags/dictionary" prefix="dictionary"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑保密要害部位</title>

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
			});

			// 涉密人员详情
			function doSecrecyPersonDetail(id){
				$ENV.dialog.open({
					url : '${ctx}/bmp/demo/keyPart/secrecyPersonForKeyPartEidt_detail.jsp',
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					title : '保密要害部位【保密室】涉密人员详情'
				});
			}

			// 新增涉密人员
			function addKePartSecrecyPerson(){
				window.location.href = "${ctx}/bmp/demo/keyPart/keyPartSecrecyPerson_add.jsp";
			}

			// 编辑涉密人员
			function editKePartSecrecyPerson(){
				window.location.href = "${ctx}/bmp/demo/keyPart/keyPartSecrecyPerson_edit.jsp";
			}

			// 删除涉密人员
			function deleteKePartSecrecyPerson(){
				alert("demo不实现该效果");
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="keyPart_list.jsp" onclick=""><span>保 存</span></a>
					<a class="pop_button" href="keyPart_list.jsp" onclick=""><span>返回列表</span></a>
				</div>
			</div>
			<div class="right">
				<div class="pop_button_bar">
					<a class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
					<a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
				</div>
			</div>
		</div>

		<div class="body_content">
			<!-- 复合面板开始 -->
			<cp:start defaultTitle="保密要害部位简介" ctx="${ctx}" icoPath="/bmp/demo/keyPart/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密要害部位简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密要害部位
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_key_part"> </cpc:tc>
					</div>

					<!-- 上下之间的间隔，可以调节高度 -->
					<div class="cpMsgFg" style="height: 7px;overflow: hidden;"></div>

					<!-- 联系方式 -->
					<div class="cpMsgContactInfoTitle">
						业务指导
					</div>
					<div class="cpMsgContactInfoContext">
						联系人：四川省国家保密局督察处 XXX 联系电话：028-85229437
					</div>
				</cp:msg>
			<cp:end> </cp:end>
			<!-- 复合面板结束 -->

			<!-- 保密要害部位panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑保密要害部位
					</div>
					<div class="panel_btn_bar pop_button_bar">
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<table class="content_table st" width="100%">
						<tr>
							<td class="tbLable fr">
								单位名称：
							</td>
							<td class="tbValue fl" colspan="3">
								眉山市林业局
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								部位名称：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="保密室"/>
							</td>

							<td class="tbLable fr">
								主管部门：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="信息中心"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								涉密等级：
							</td>
							<td class="tbValue fl">
								<select style="width: 130px;">
									<option>一般</option>
									<option selected="selected">重要</option>
									<option>核心</option>
								</select>
							</td>
							<td class="tbLable fr">
								主要负责人：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="刘国友"/>
							</td>
						</tr>
						<tr>
							<td class="fr">
								涉密人员数量：
							</td>
							<td class="fl" colspan="3">
								<label title="涉密人员数量由当前系统自动统计">3</label>（开发说明：add页面没有，edit和detail有）
							</td>
						</tr>
						<tr>
							<td class="fr">
								具体位置：
							</td>
							<td class="fl" colspan="3">
								<input id="" name="" style="width: 80%;" value="16楼21层2103号"/>
							</td>
						</tr>
						<tr>
							<td class="fr">
								涉密工作事项及范围：
							</td>
							<td class="fl" colspan="3">
								<textarea style="width:100%; height: 100px;">......</textarea><br/>
								请填写涉密工作事项概况，不需要详细罗列具体事项。
							</td>
						</tr>
						<tr>
							<td class="fr">
								技防措施：
							</td>
							<td class="fl" colspan="3">
								<input id="" name="" style="width: 80%;" value="安装了电子密码文件柜、铁门、铁窗、监控视频"/>
							</td>
						</tr>
						<tr>
							<td class="fr">
								管理制度：
							</td>
							<td class="fl" colspan="3">
								是否建立并落实保密管理制度？<input id="ddd" name="" type="radio" checked="checked"/> 是 <input id="ddd" name="" type="radio"/> 否
							</td>
						</tr>
						<tr>
							<td class="fr" valign="top">
								本单位确定要害部位文件：
							</td>
							<td class="fl" colspan="3" valign="top">
								<a href="###">《关于确定xxx部门为保密要害部位的决定》.doc</a> &nbsp;  &nbsp;  &nbsp; <a href="###" title="删除【关于确定xxx部门为保密要害部位的决定.doc】">删除</a><br/>
								<input type="file"/> 请选择文件。（开发说明：仅可以上传一个文件）
							</td>
						</tr>
						<tr>
							<td class="fr" valign="top">
								本单位确定涉密人员文件：
							</td>
							<td class="fl" colspan="3" valign="top">
								<a href="###">《关于确定杨林川为涉密人员的决定》.doc</a> &nbsp;  &nbsp;  &nbsp; <a href="###" title="删除【关于确定杨林川为涉密人员的决定.doc】">删除</a><br/>
								<a href="###">《关于确定刘小鹏为涉密人员的决定》.doc</a> &nbsp;  &nbsp;  &nbsp; <a href="###" title="删除【关于确定刘小鹏为涉密人员的决定.doc】">删除</a><br/>
								<a href="###">《关于确定李建科为涉密人员的决定》.doc</a> &nbsp;  &nbsp;  &nbsp; <a href="###" title="删除【关于确定李建科为涉密人员的决定.doc】">删除</a><br/>
								<input type="file"/> 请选择文件。（开发说明：可以上传多个文件）
							</td>
						</tr>
						<tr>
							<td class="fr" valign="top">
								保密制度：
							</td>
							<td class="fl" colspan="3" valign="top">
								<a href="###">保密制度1.doc</a> &nbsp;  &nbsp;  &nbsp; <a href="###" title="删除【保密制度.doc】">删除</a><br/>
								<a href="###">保密制度2.doc</a> &nbsp;  &nbsp;  &nbsp; <a href="###" title="删除【保密制度.doc】">删除</a><br/>
								<a href="###">保密制度3.doc</a> &nbsp;  &nbsp;  &nbsp; <a href="###" title="删除【保密制度.doc】">删除</a><br/>
								<input type="file"/> 请选择保密制度文件。（开发说明：可以上传多个文件）
							</td>
						</tr>
						<tr>
							<td class="fl" colspan="4">
								<b>填表提示：</b><br/>
								部位名称： 填写可以参考“机要室”、“保密室”、“档案室”、“文印室”、“机房”、“资料室”等。<br/>
								具体位置： 填写样式如“×××楼×××层×××号”。<br/>
								技防措施： 电子密码文件柜、铁门、铁窗、监控视频、红外报警、电子门禁等。
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 保密要害部位panel结束 -->

			<!-- 保密要害部位人员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部位【保密室】涉密人员列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="###" onclick="addKePartSecrecyPerson()"><span>新 增</span></a>
						<a class="pop_button" href="###" onclick="editKePartSecrecyPerson()"><span>编 辑</span></a>
						<a class="pop_button" href="###" onclick="deleteKePartSecrecyPerson()"><span>删 除</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id=""  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<thead>
								<tr>
									<td class="tableHeader" ><input type="checkbox"  name="secrecyLawCodeId_checkbox"  id="secrecyLawCodeId_checkbox"  class="class_secrecyLawCodeId_checkbox"  onclick="EcTable.checkAll('secrecyLawCodeId_checkbox')" /></td>
									<td class="tableHeader"  width="20%" >姓 名</td>
									<td class="tableHeader"  width="15%" >行政职务</td>
									<td class="tableHeader"  width="15%" >涉密等级</td>
									<td class="tableHeader"  width="15%" >岗 位</td>
									<td class="tableHeader"  width="15%" >联系电话</td>
									<td class="tableHeader"  width="2%" >详 情</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value=""  class="row_checkbox " /></td>
									<td>
										刘云山
									</td>
									<td>
										处长
									</td>
									<td>
										重要
									</td>
									<td>
										处长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
										<a href="###" onclick="doSecrecyPersonDetail('')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value=""  class="row_checkbox " /></td>
									<td>
										刘颖
									</td>
									<td>
										副处长
									</td>
									<td>
										一般
									</td>
									<td>
										副处长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
										<a href="###" onclick="doSecrecyPersonDetail('')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
			<!-- 保密要害部位人员panel结束 -->

		</div>
	</body>
</html>