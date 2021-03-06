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
		<title>编辑保密工作机构</title>

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
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="secrecyOrgan_detail_has.jsp" onclick=""><span>保 存</span></a>
					<a class="pop_button" href="secrecyOrgan_detail_has.jsp" onclick=""><span>返 回</span></a>
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
			<cp:start defaultTitle="保密工作机构简介" ctx="${ctx}" icoPath="/bmp/demo/secrecyOrgan/detail_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密工作机构简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密工作机构
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_secrecy_organ"> </cpc:tc>
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

			<!-- 内容panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑保密工作机构
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
								四川省教育局
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								机构类型：
							</td>
							<td class="tbValue fl">
								<select style="width: 130px;">
									<option selected="selected">保密工作领导小组</option>
									<option>保密委员会</option>
								</select>
							</td>
							<td class="tbLable fr">
								发文号：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="川教发[2012]18号"/> 例如：川XXX[2012]XX号
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								发文上传：
							</td>
							<td class="tbValue fl" colspan="3">
								<a href="###">《四川省教育局关于成立<保密工作机构>的通知》.doc</a> &nbsp; &nbsp; &nbsp; &nbsp;<a href="###">删除</a> &nbsp; &nbsp; &nbsp; &nbsp;
								<input type="file"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								成立(发文)日期：
							</td>
							<td class="tbValue fl">
								<input type="text" style="width: 130px;" class="Wdate validate['required']" value="2004-02-16" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
							</td>
							<td class="tbLable fr">
								保密办设在：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="秘书处"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								保密办负责人：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="王建新"/>
							</td>
							<td class="tbLable fr">
								行政职务：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="处长"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								电 话：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="028-2860398"/>
							</td>
							<td class="tbLable fr">
								地 址：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="新华路29号附2号"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								传 真：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="028-2842475"/>
							</td>
							<td class="tbLable fr">
								邮 编：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="625005"/>
							</td>
						</tr>
						<tr>
							<td class="tbLable fr">
								职责与成员分工：
							</td>
							<td class="tbValue fl" colspan="5">
								<textarea style="width: 95%;padding: 5px;" rows="3">四川省教育局保密领导小组成立于2004年......</textarea><br/>
								提示：请填写保密工作机构的职能职责，以及成员分工情况。
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 内容panel结束 -->

			<!-- 保密工作机构成员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密工作机构成员列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="###"><span>新 增</span></a>
						<a class="pop_button" href="###"><span>编 辑</span></a>
						<a class="pop_button" href="###"><span>删 除</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id=""  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion" width="100%" >
							<thead>
								<tr>
									<td class="tableHeader" width="30"><input type="checkbox"  name="secrecyLawCodeId_checkbox"  id="secrecyLawCodeId_checkbox"  class="class_secrecyLawCodeId_checkbox"  onclick="EcTable.checkAll('secrecyLawCodeId_checkbox')" /></td>
									<td class="tableHeader" width="20%" >机构职务</td>
									<td class="tableHeader" width="15%" >成员姓名</td>
									<td class="tableHeader" width="15%" >部 门</td>
									<td class="tableHeader" width="15%" >行政职务</td>
									<td class="tableHeader" width="15%" >办公室电话</td>
									<td class="tableHeader" width="2%" >备 注</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										组长
									</td>
									<td>
										郭东
									</td>
									<td>
										秘书处
									</td>
									<td>
										处长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										副组长
									</td>
									<td>
										刘颖
									</td>
									<td>
										教案处
									</td>
									<td>
										处长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										组员
									</td>
									<td>
										杨丽
									</td>
									<td>
										考试中心
									</td>
									<td>
										科长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										组员
									</td>
									<td>
										王连成
									</td>
									<td>
										考试中心
									</td>
									<td>
										副科长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
									</td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
			<!-- 保密工作机构成员panel结束 -->

			<!-- 办公室成员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密办成员列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="###"><span>新 增</span></a>
						<a class="pop_button" href="###"><span>编 辑</span></a>
						<a class="pop_button" href="###"><span>删 除</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id=""  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion" width="100%" >
							<thead>
								<tr>
									<td class="tableHeader" width="30"><input type="checkbox"  name="secrecyLawCodeId_checkbox"  id="secrecyLawCodeId_checkbox"  class="class_secrecyLawCodeId_checkbox"  onclick="EcTable.checkAll('secrecyLawCodeId_checkbox')" /></td>
									<td class="tableHeader" width="30%" >成员姓名</td>
									<td class="tableHeader" width="20%" >部 门</td>
									<td class="tableHeader" width="15%" >行政职务</td>
									<td class="tableHeader" width="15%" >办公室电话</td>
									<td class="tableHeader" width="2%" >备 注</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										王建新
									</td>
									<td>
										秘书处
									</td>
									<td>
										处长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >

									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										刘颖
									</td>
									<td>
										教案处
									</td>
									<td>
										处长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										杨丽
									</td>
									<td>
										考试中心
									</td>
									<td>
										科长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										王连成
									</td>
									<td>
										考试中心
									</td>
									<td>
										副科长
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="15%" >
									</td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
			<!-- 办公室成员panel结束 -->

		</div>
	</body>
</html>