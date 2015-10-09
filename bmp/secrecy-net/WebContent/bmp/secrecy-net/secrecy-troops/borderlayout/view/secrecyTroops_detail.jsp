<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.cdthgk.com/tags/dictionary" prefix="dictionary"%>
<div class="panel tMargin">
	<div class="panel_header">
		区、县（市）保密队伍建设情况统计表
	</div>
	<div class="panel_content panel_content_table">
		<table class="content_table" border="0">
			<tr>
				<td align="right">单位：</td>
				<td align="left">${secrecyTroops.reportOrgan.organName }</td>
				<td align="right">填表人：</td>
				<td align="left">${secrecyTroops.reportUser.userInfo.name}</td>
				<td align="right">填报日期：</td>
				<td align="left">
					<fmt:formatDate value="${secrecyTroops.reportDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</table>
		<table class="content_table">
			<tr>
				<td colspan="13" rowspan="4">保密队伍建设</td>
			</tr>
			<tr>
				<td rowspan="2">机关单位数量（个）</td>
				<td colspan="2" align="center">局</td>
				<td colspan="2" align="center">检查中心</td>
				<td colspan="4" align="center">学历情况</td>
				<td colspan="2" align="center">专业情况</td>
				<td colspan="2" align="center">年龄段</td>
			</tr>
			<tr>
				<td >编制人数</td>
				<td >实有人数</td>
				<td >编制人数</td>
				<td >实有人数</td>
				<td >博士生人数</td>
				<td >硕士生人数</td>
				<td >本科生人数</td>
				<td >大专及以下人数</td>
				<td >计算机及通信人数</td>
				<td >其他人数</td>
				<td >45岁以下人数</td>
				<td >45岁以上人数</td>
			</tr>
			<tr>
				<td>${secrecyTroops.smallOrganNum}</td>
				<td>${secrecyTroops.jupPlaitNum }</td>
				<td>${secrecyTroops.juExistNum }</td>
				<td>${secrecyTroops.zxPlaitNum }</td>
				<td>${secrecyTroops.zxExistNum }</td>
				<td>${secrecyTroops.degreeBoNum }</td>
				<td>${secrecyTroops.degreeSuoNum }</td>
				<td>${secrecyTroops.degreeBenNum }</td>
				<td>${secrecyTroops.degreeDazNum }</td>
				<td>${secrecyTroops.specialyComputerNum }</td>
				<td>${secrecyTroops.specialyOtherNum }</td>
				<td>${secrecyTroops.ageFortyfiveDown }</td>
				<td>${secrecyTroops.ageFortyfiveUp }</td>
			</tr>
		</table>
	</div>
</div>