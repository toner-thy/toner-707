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
		<title>编辑保密要害部门</title>

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
			// 查看详情
			function doSecrecyPersonDetail(secrecyPersonId){
				$ENV.dialog.open({
					title : '涉密人员详情',
					url : '${ctx}/bmp/demo/keyDepartment/keySectionSecrecyPerson_detail.jsp',
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
			}

			function doSecrecyKeyPartDetail(secrecyPersonId){
				$ENV.dialog.open({
					title : '保密要害部位详情',
					url : '${ctx}/bmp/demo/keyDepartment/keySectionKeyPart_detail.jsp',
					width : window.top.getSize().x * 0.8,
					height : window.top.getSize().y * 0.9,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
			}

			// 新增涉密人员
			function addKePartSecrecyPerson(){
				window.location.href = "${ctx}/bmp/demo/keyDepartment/keySectionSecrecyPerson_add.jsp";
			}

			// 编辑涉密人员
			function editKePartSecrecyPerson(){
				window.location.href = "${ctx}/bmp/demo/keyDepartment/keySectionSecrecyPerson_edit.jsp";
			}

			// 删除涉密人员
			function deleteKePart(){
				alert("demo不实现该效果");
			}
		</script>

	</head>

	<body>
		<!-- 公共头部 -->
		<div class="button_bar">
			<div class="left">
				<div class="pop_button_bar">
					<a class="pop_button" href="keyDepartment_list.jsp" onclick=""><span>保 存</span></a>
					<a class="pop_button" href="keyDepartment_list.jsp" onclick=""><span>返回列表</span></a>
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
			<cp:start defaultTitle="保密要害部门简介" ctx="${ctx}" icoPath="/bmp/demo/keyDepartment/list_cpIco.gif">
				<div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(1,'cp001','保密要害部门简介');">关 于</div>
			</cp:start>
				<cp:msg show="true" divId="cp001">
					<!-- 模块简介 -->
					<div class="cpMsgTitle">
						关于保密要害部门
					</div>
					<div class="cpMsgContext">
						<cpc:tc ctx="${ctx}" showId="bm_key_department"> </cpc:tc>
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

			<!-- 保密要害部门panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						编辑保密要害部门
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
								部门名称：
							</td>
							<td class="tbValue fl">
								信息中心
							</td>
							<td class="tbLable fr">
								负责人：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="张三" />（开发信息：保存时，检查名称是否变化，如果变化，则提示是人名改变还是更换人员。）
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
								联系电话：
							</td>
							<td class="tbValue fl">
								<input id="" name="" value="028-82373356"/>
							</td>
						</tr>
						<tr>
							<td class="fr">
								在编人员数量：
							</td>
							<td class="fl">
								<input id="" name="" value="14"/>（开发说明：填数字）
							</td>
							<td class="fr">
								涉密人员数量：
							</td>
							<td class="fl">
								<label title="涉密人员数量由当前系统自动统计">3</label>（开发说明：add页面没有，edit和detail有）
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
							<td class="tbLable fr">
								保密制度：
							</td>
							<td class="fl" colspan="3">
								<script type="text/javascript">
								$ENV.loader.loadStyleSheet("/bmp/resources/theme/borderlayout/skin/blue/SimpleUI/css/FileUpload.css");
								$ENV.loader.loadJavaScript("${ctx}/resources/js/mootools/mootools-core-1.4.js");
								$ENV.loader.loadJavaScript("${ctx}/resources/js/SimpleUI/FileUpload.js",function(){
									$ENV.onDomReady(function(){
										funjtpgq = new FileUpload("upload_form_njtpgq", "file_id_njtpgq", {
											onIniFile: function(file){ file.value ? file.style.display = "none" : this.folder.removeChild(file); },
											onEmpty: function(){ alert("请选择一个文件"); },
											onLimite: function(){ alert("超过上传限制"); },
											limit: 1,
											onSame: function(){ alert("已经有相同文件"); },
											onNotExtIn:	function(){ alert("只允许上传" + this.extIn.join("，") + "文件"); },
											onExtOut : function(){alert("不允许上传" + this.extOut.join("，") + "文件");},
											onFail: function(file){ this.folder.removeChild(file);},
											onIni: function(){
												var arrRows = [];
												if(this.files.length){
													var oThis = this;
													this.files.each(function(o){
														var a = document.createElement("a"); a.innerHTML = "取消"; a.href = "javascript:void(0);";
														a.onclick = function(){ oThis.del(o); return false; };
														arrRows.push([o.value, a]);
													});
												} else { arrRows.push(["<font color='gray'>没有添加文件</font>", "&nbsp;"]); }
												addList(arrRows);
												document.getElementById("btn_upload_njtpgq").disabled = document.getElementById("btn_delete_njtpgq").disabled = this.files.length <= 0;
											}
										});

										document.getElementById("btn_upload_njtpgq").onclick = function(){
											var arrRows = [];
											funjtpgq.files.each(function(o){
												arrRows.push([o.value, "&nbsp;"]);
											});
											addList(arrRows);
											funjtpgq.folder.style.display = "none";
											document.getElementById("process_njtpgq").style.display = "";
											document.getElementById("msg_el_njtpgq").style.display = "";
											document.getElementById("upload_btn_bar_njtpgq").style.display = "none";

											funjtpgq.form.submit();
										}
										function addList(rows){
											var FileList = document.getElementById("file_list_njtpgq"), oFragment = document.createDocumentFragment();
											rows.each(function(cells){
												var row = document.createElement("tr");
												cells.each(function(o){
													var cell = document.createElement("td");
													if(typeof o == "string"){ cell.innerHTML = o; }else{if(o.tagName == 'a' || o.tagName == 'A') {cell.className = 'btnCell';}cell.appendChild(o);}
													row.appendChild(cell);
												});
												oFragment.appendChild(row);
											});
											while(FileList.hasChildNodes()){ FileList.removeChild(FileList.firstChild); }
											FileList.appendChild(oFragment);
										}
										document.getElementById("btn_delete_njtpgq").onclick = function(){ funjtpgq.clear(); }


									});
								});
								</script>
								<form id="upload_form_njtpgq" action="${ctx}/platform/attachment/attachment_uploading_b_bmpUploadBehavior.action">
								<input type="hidden" name="applyForm" value="add_keySection_form">
								<input type="hidden" name="applyName" value="secAttach">
								<input type="hidden" name="selectedFiles" value="file_id_njtpgq">
								<input type="hidden" name="processImg" value="process_njtpgq">
								<input type="hidden" name="msgEl" value="msg_el_njtpgq">
								<input type="hidden" name="responseFiles" value="response_filesnjtpgq">
								<input type="hidden" name="uploadBtn" value="upload_btn_bar_njtpgq">
								<input type="hidden" name="deleteBtn" value="btn_delete_njtpgq">
								<input type="hidden" name="fileName" value="funjtpgq">
								<div class="panel">
									<div class="panel_content panel_content_table">
										<table border="0" cellspacing="0" class="fu_list" style="margin-top:1px;">
											<tbody>
										  		<tr>
													<td style="width:120px;">
														<a href="javascript:void(0);" class="files" id="file_id_njtpgq"></a><img id="process_njtpgq" style="display:none;width: 160px;height: 18px;" src="/bmp/resources/theme/borderlayout/skin/blue/SimpleUI/image/FileUpload/loading.gif" />
													</td>
													<td style="color:gray">温馨提示：最多可同时上传 <b id="idLimit">1</b> 个文件


													</td>
										  		</tr>
										  		<tr>
													<td colspan="2">
														<div class="panel">
															<div class="panel_header">
																<div class="panel_title">
																	已选择文件
																</div>
																<div class="panel_titleBtnBar">
																</div>
															</div>
															<div class="panel_content panel_content_table">
																<table border="0" cellspacing="0">
																	<tbody id="file_list_njtpgq">
																	</tbody>
																</table>
															</div>
														</div>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<div class="panel">
															<div class="panel_header">
																<div class="panel_title">
																	附件列表
																</div>
																<div class="panel_titleBtnBar">
																</div>
															</div>
															<div class="panel_content panel_content_table">
																<table border="0" cellspacing="0">
																	<tbody id="response_filesnjtpgq">
																		<tr>
																			<td width="85%">
																				保密制度2.doc
																			</td>
																			<td>
																				下载&nbsp; 删除
																			</td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
													</td>
												</tr>
												<tr>
											        <td colspan="2" align="center" id="msg_el_njtpgq" style="display: none;">
											        	正在添加文件到您的网盘中，请稍候……<br />
											        	有可能因为网络问题，出现程序长时间无响应，请点击“<a href='?'><font color='red'>取消</font></a>”重新上传文件
											        </td>
												</tr>
												<tr>
													<td colspan="2" align="center" id="upload_btn_bar_njtpgq">
														<input type="button" value="开始上传" id="btn_upload_njtpgq" disabled="disabled" />&nbsp;&nbsp;&nbsp;
														<input type="button" value="全部取消" id="btn_delete_njtpgq" disabled="disabled" />
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								</form>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- 保密要害部门panel结束 -->

			<!-- 保密要害部位panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部门【信息中心】保密要害部位列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="keySectionKeyPart_add.jsp" onclick=""><span>新 增</span></a>
						<a class="pop_button" href="keySectionKeyPart_edit.jsp"><span>编 辑</span></a>
						<a class="pop_button" href="###" onclick="deleteKePart()"><span>删 除</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id=""  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<thead>
								<tr>
									<td class="tableHeader" ><input type="checkbox"  name="secrecyLawCodeId_checkbox"  id="secrecyLawCodeId_checkbox"  class="class_secrecyLawCodeId_checkbox"  onclick="EcTable.checkAll('secrecyLawCodeId_checkbox')" /></td>
									<td class="tableHeader"  width="20%" >部位名称</td>
									<td class="tableHeader"  width="15%" >涉密等级</td>
									<td class="tableHeader"  width="15%" >主要负责人</td>
									<td class="tableHeader"  width="15%" >联系电话</td>
									<td class="tableHeader"  width="2%" >详 情</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td width="35%" >
										文印室
									</td>
									<td width="20%" >
										一般
									</td>
									<td width="20%" >
										郭东
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doSecrecyKeyPartDetail('4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td width="35%" >
										机房
									</td>
									<td width="20%" >
										重要
									</td>
									<td width="20%" >
										王五
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doSecrecyKeyPartDetail('4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td width="35%" >
										档案室
									</td>
									<td width="20%" >
										重要
									</td>
									<td width="20%" >
										张三
									</td>
									<td width="15%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doSecrecyKeyPartDetail('4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
			<!-- 保密要害部位panel结束 -->

			<!-- 保密要害部门人员panel开始 -->
			<div class="panel tMargin">
				<div class="panel_header">
					<div class="panel_title panel_titleListIco">
						保密要害部门【信息中心】涉密人员列表
					</div>
					<div class="panel_btn_bar pop_button_bar">
						<a class="pop_button" href="###" onclick="addKePartSecrecyPerson()"><span>新 增</span></a>
						<a class="pop_button" href="###" onclick="editKePartSecrecyPerson()"><span>编 辑</span></a>
						<a class="pop_button" href="###" onclick="deleteKePart()"><span>删 除</span></a>
					</div>
				</div>
				<div class="panel_content panel_content_table">
					<div class="eXtremeTable" >
						<table id=""  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
							<thead>
								<tr>
									<td class="tableHeader" ><input type="checkbox"  name="secrecyLawCodeId_checkbox"  id="secrecyLawCodeId_checkbox"  class="class_secrecyLawCodeId_checkbox"  onclick="EcTable.checkAll('secrecyLawCodeId_checkbox')" /></td>
									<td class="tableHeader"  width="20%" >姓 名</td>
									<td class="tableHeader"  width="15%" >涉密等级</td>
									<td class="tableHeader"  width="20%" >行政职务</td>
									<td class="tableHeader"  width="15%" >岗 位</td>
									<td class="tableHeader"  width="20%" >联系电话</td>
									<td class="tableHeader"  width="5%" >详 情</td>
								</tr>
							</thead>
							<tbody class="tableBody" >
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										郭东
									</td>
									<td>
										重要
									</td>
									<td>
										处长
									</td>
									<td>
										处长
									</td>
									<td width="20%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doSecrecyPersonDetail('4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										刘颖
									</td>
									<td>
										一般
									</td>
									<td>
										副处长
									</td>
									<td>
										副处长
									</td>
									<td width="20%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doSecrecyPersonDetail('4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
								<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
									<td><input type="checkbox"  name="secrecyLawCodeId"  value="4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603"  class="row_checkbox " /></td>
									<td>
										杨丽
									</td>
									<td>
										一般
									</td>
									<td>
										科员
									</td>
									<td>
										科员
									</td>
									<td width="20%" >
										028-8729336
									</td>
									<td width="5%" >
										<a href="###" onclick="doSecrecyPersonDetail('4b4b1fdf-1eaf-102f-bec3-a8f6d85a3603')">
											<img src="${ctx}/platform/theme/default/images/main/display.gif" border="0" title="详细信息"/>
										</a>
									</td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
			<!-- 保密要害部门人员panel结束 -->

		</div>
	</body>
</html>