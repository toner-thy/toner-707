<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://www.cdthgk.com/tags/organization/department" prefix="department" %>
<%@ taglib uri="http://www.cdthgk.com/tags/theme" prefix="theme" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="panel">
	<!-- 头部 -->
	<div class="panel_header">
		<!-- 标题 -->
		<div class="panel_title panel_titleListIco">
		非涉密网络基本情况统计表
		</div>
	</div>
	<!-- 内容 -->
	<div class="panel_content panel_content_table">
		<table border="0" style="width: 100%;">
			<tr>
				<td align="left" style="padding-left: 20px;">单位：（盖章）&nbsp;&nbsp; ${noSecNet.reportOrgan.name}</td>
				<td align="center">填表人：&nbsp;&nbsp; ${noSecNet.reportUser.userInfo.name}</td>
				<td align="right" style="padding-right: 20px;">填报日期：&nbsp;&nbsp;<fmt:formatDate value="${noSecNet.reportDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</table>
		<table class="content_table">
			<tbody>
				<tr>
					<td width="30%" align="center" rowspan="3">
						与互联网连接的网络数量
						<br/>（<input type="text" name="noSecNet.internetNum" value="${noSecNet.internetNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly"> 个）
					</td>
					<td width="70%" align="left" colspan="4">
						<input type="checkbox" name="noSecNet.internetOa" id="noSecNet.internetOa" value="true" onclick="return false;" <c:if test="${noSecNet.internetOa}">checked="checked"</c:if>>
						&nbsp;&nbsp;<label for="noSecNet.internetOa">机关OA网</label>
					</td>
				</tr>
				<tr>
					<td width="70%" align="left" colspan="4">
						<input type="checkbox" name="noSecNet.internetParty" id="noSecNet.internetParty" value="true" onclick="return false;" <c:if test="${noSecNet.internetParty}">checked="checked"</c:if>>
						&nbsp;&nbsp;<label for="noSecNet.internetParty">党政外网</label>
					</td>
				</tr>
				<tr>
					<td width="70%" align="left" colspan="4">
						<input type="checkbox" name="noSecNet.internetOther" id="noSecNet.internetOther" value="true" onclick="return false;" <c:if test="${noSecNet.internetOther}">checked="checked"</c:if>>
						&nbsp;&nbsp;<label for="noSecNet.internetOther">其他</label> <input type="text" readonly="readonly" name="noSecNet.internetOtherDesc" value="${noSecNet.internetOtherDesc}" style="border: 0;border-bottom: 1px solid #000;width: 80%;">
					</td>
				</tr>
				<tr>
					<td id="intranet_td_first" width="30%" align="center" rowspan="${fn:length(noSecNet.noSecNetIntranets) + 1}">
						与互联网物理隔绝的网络数量
						<br/>（<input type="text" readonly="readonly" id="intranetNum" name="noSecNet.intranetNum" value="${empty noSecNet.intranetNum ? 0 : noSecNet.intranetNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly"> 个）
					</td>
					<td width="20%" align="center">
						网络名称
					</td>
					<td width="10%" align="center">
						用户数量
					</td>
					<td width="20%" align="center">
						使用范围
					</td>
					<td width="20%" align="center">
						用途
					</td>
				</tr>
				<c:forEach var="i" items="${noSecNet.noSecNetIntranets}">
				<tr>
					<td width="20%" align="center">
						${i.name}
					</td>
					<td width="10%" align="center">
						${i.userNum}
					</td>
					<td width="20%" align="center">
						${i.useRange}
					</td>
					<td width="20%" align="center">
						${i.purpose}
					</td>
				</tr>
				</c:forEach>
				<tr>
					<td width="30%" align="center">
						连接互联网计算机数量
						<br/>（<input type="text" name="noSecNet.internetComputerNum" value="${noSecNet.internetComputerNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly"> 台）
					</td>
					<td width="70%" align="left" colspan="4">

					</td>
				</tr>
				<tr>
					<td width="30%" align="center" rowspan="3">
						互联网门户网站数量
						<br/>（<input type="text" name="noSecNet.internetWebsiteNum" value="${noSecNet.internetWebsiteNum}" style="border: 0;border-bottom: 1px solid #000;width: 30px;" readonly="readonly"> 个）
					</td>
					<td align="left" colspan="2">
						是否存在互联网门户网址
					</td>
					<td align="left" colspan="2">
						<input type="radio" id="noSecNet.internetWebsiteExist.true" value="true" onclick="return false;" <c:if test="${noSecNet.internetWebsiteExist}">checked="checked"</c:if>
							class="validate['required']">
						&nbsp;&nbsp;<label for="noSecNet.internetWebsiteExist.true" style="margin-right: 40px;">是</label>
						<input type="radio" id="noSecNet.internetWebsiteExist.false" value="false" onclick="return false;" <c:if test="${noSecNet.internetWebsiteExist == false}">checked="checked"</c:if>
							class="validate['required']">
						&nbsp;&nbsp;<label for="noSecNet.internetWebsiteExist.false">否</label>
					</td>
				</tr>
				<tr>
					<td width="70%" align="left" colspan="4">
						<table style="width: 100%;" class="content_table">
							<tr>
								<td align="right" style="border: 0;width: 60px;">
									网址：
								</td>
								<td style="border: 0;">
									<c:forEach var="dn" items="${fn:split(noSecNet.internetWebsiteDn, ',')}">
									${dn} &nbsp;&nbsp;
									</c:forEach>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="70%" align="left" colspan="4">
						<table style="width: 100%;" class="content_table">
							<tr>
								<td align="right" style="border: 0;width: 60px;">
									IP&nbsp;地址：
								</td>
								<td style="border: 0;">
									<c:forEach var="ip" items="${fn:split(noSecNet.internetWebsiteIp, ',')}">
									${ip} &nbsp;&nbsp;
									</c:forEach>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>