<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="department" %>
<%@ taglib uri="http://www.cdthgk.com/tags/theme" prefix="theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="panel">
	<!-- 头部 -->
	<div class="panel_header">
		<!-- 标题 -->
		<div class="panel_title panel_titleListIco">
		技术手段情况统计表
		</div>
	</div>
	<!-- 内容 -->
	<div class="panel_content panel_content_table">
		<table border="0" style="width: 100%;">
			<tr>
				<td align="left" style="padding-left: 20px;">单位：（盖章）&nbsp;&nbsp; ${tecToolInfo.reportOrgan.name}</td>
				<td align="center">填表人：&nbsp;&nbsp; ${tecToolInfo.reportUser.userInfo.name}</td>
				<td align="right" style="padding-right: 20px;">填报日期：&nbsp;&nbsp;<fmt:formatDate value="${tecToolInfo.reportDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</table>
		<table class="content_table">
			<tbody>
				<tr>
					<td width="5%" align="center" rowspan="3">
						技术<br/>手段
					</td>
					<td width="15%" align="center">
						涉密计算机违规外联监控平台（个）
					</td>
					<td width="15%" align="center">
						重要单位接入口保密检查平台纳入监管单位数量（个）
					</td>
					<td width="15%" align="center">
						机关单位网站保密检查平台纳入监管门户网站数量（个）
					</td>
					<td width="50%" align="center">
						保密技术检查工具
					</td>
				</tr>
				<tr>
					<td align="center" rowspan="2">
						<input type="text" value="${tecToolInfo.internetConnectPlatNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly">
					</td>
					<td align="center" rowspan="2">
						<input type="text" value="${tecToolInfo.impOrgCheckNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly">
					</td>
					<td align="center" rowspan="2">
						<input type="text" value="${tecToolInfo.orgWebsiteCheckNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly">
					</td>
					<td align="center">
						<table style="width: 100%;" class="content_table">
							<tr>
								<td align="left" style="border: 0;text-align: left:">
									<input type="checkbox" id="toolComputer" name="tecToolInfo.toolComputer" value="true" onclick="return false;" <c:if test="${tecToolInfo.toolComputer}">checked="checked"</c:if>>
									<label for="toolComputer">计算机检查工具</label>
								</td>
								<td align="left" style="border: 0;text-align: left:">
									<input type="checkbox" id="toolNet" name="tecToolInfo.toolNet" value="true" onclick="return false;" <c:if test="${tecToolInfo.toolNet}">checked="checked"</c:if>>
									<label for="toolNet">网络检查工具</label>
								</td>
								<td align="left" style="border: 0;text-align: left:">
									<input type="checkbox" id="toolCockhorse" name="tecToolInfo.toolCockhorse" value="true" onclick="return false;" <c:if test="${tecToolInfo.toolCockhorse}">checked="checked"</c:if>>
									<label for="toolCockhorse">木马检查工具</label>
								</td>
							</tr>
							<tr>
								<td align="left" style="border: 0;text-align: left:">
									<input type="checkbox" id="toolNetTest" name="tecToolInfo.toolNetTest" value="true" onclick="return false;" <c:if test="${tecToolInfo.toolNetTest}">checked="checked"</c:if>>
									<label for="toolNetTest">网络评测工具</label>
								</td>
								<td align="left" style="border: 0;text-align: left:">
									<input type="checkbox" id="toolSignal" name="tecToolInfo.toolSignal" value="true" onclick="return false;" <c:if test="${tecToolInfo.toolSignal}">checked="checked"</c:if>>
									<label for="toolSignal">电磁信号检查工具</label>
								</td>
								<td align="left" style="border: 0;text-align: left:">
									<input type="checkbox" id="toolOther" name="tecToolInfo.toolOther" value="true" onclick="return false;" <c:if test="${tecToolInfo.toolOther}">checked="checked"</c:if>>
									<label for="toolOther">其他工具：</label><input type="text" name="tecToolInfo.toolOtherDesc" value="${tecToolInfo.toolOtherDesc}" style="border: 0;border-bottom: 1px solid #000;width: 80%;" readonly="readonly">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="left">
						配备计算机检查工具<input type="text" name="tecToolInfo.toolComputerNum" value="${tecToolInfo.toolComputerNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly">套，
						网络检查工具<input type="text" name="tecToolInfo.toolNetNum" value="${tecToolInfo.toolNetNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly">套，
						木马检查工具<input type="text" name="tecToolInfo.toolCockhorseNum" value="${tecToolInfo.toolCockhorseNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly">套，
						网络评测工具<input type="text" name="tecToolInfo.toolNetTestNum" value="${tecToolInfo.toolNetTestNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly">套，
						电磁信号检查工具<input type="text" name="tecToolInfo.toolSignalNum" value="${tecToolInfo.toolSignalNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly">套
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>