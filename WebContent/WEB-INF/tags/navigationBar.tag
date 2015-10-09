<%@ tag body-content="scriptless" pageEncoding="utf-8" %>
<%@tag import="com.cdthgk.platform.district.domain.District"%>
<%@tag import="java.util.List"%>
<%@tag import="java.util.ArrayList"%>
<%@tag import="java.util.Collections"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ attribute name="district" type="com.cdthgk.platform.district.domain.District" required="true"%>
<%@ attribute name="topDistrict" type="com.cdthgk.platform.district.domain.District" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%!
	private List<District> findDistrictList() {
		List<District> districtList = new ArrayList<District>();
		findDistrict(district, topDistrict, districtList);
		Collections.reverse(districtList);
		return districtList;
	}

	private void findDistrict(District district, District top, List<District> districtList) {
		if (district.getDistrictCode().equals(top.getDistrictCode())) {
			districtList.add(district);
		} else {
			districtList.add(district);
			findDistrict(district.getPid(), top, districtList);
		}
	}
%>
<%
	request.setAttribute("districtList", findDistrictList());
	request.setAttribute("currentDistrictCode", district.getDistrictCode());
	if (url.contains("?")) {
		url += "&district.districtCode=";
	} else {
		url += "?district.districtCode=";
	}
%>
<style type="text/css">
	a.districthref:active{
		text-decoration: underline;
		font-weight: 700;
	}
	a.districthref:hover{
		text-decoration: underline;
		font-weight: 700;
	}
</style>

<div id="null" class="districtCodeMapAddressBar" style="height: 23px;width: 100%;">
	&nbsp;&nbsp;综合统计分析 >
	<c:forEach items="${districtList}" var="d" varStatus="sta">
		<c:if test="${currentDistrictCode == d.districtCode}">
			<a href="###">${d.districtName}综合统计</a>
		</c:if>
		<c:if test="${currentDistrictCode != d.districtCode}">
			<a href="<%=url%>${d.districtCode}" class="districthref" >${d.districtName}综合统计</a>
		</c:if>
		<c:if test="${!sta.last}">
			>
		</c:if>
	</c:forEach>
</div>