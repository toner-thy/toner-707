<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	<%
			// 得到展示样式（标准样式和小型样式两种）
			String showType = (String)request.getParameter("showType");
			if(showType!=null && showType.equals("small")){
				// 小型样式
				request.setAttribute("showType", "small");
			}else{
				// 标准样式（默认样式）
				request.setAttribute("showType", "standard");
			}

			// 得到显示信息
			request.setAttribute("showMsg", request.getParameter("showMsg"));

			// 得到按钮1
			String btn01Name = (String)request.getParameter("btn01Name");
			if(btn01Name!=null && !btn01Name.equals("")){
				request.setAttribute("showBtn01", true);
				request.setAttribute("btn01Name", request.getParameter("btn01Name"));
				request.setAttribute("btn01Url", request.getParameter("btn01Url"));
			}else{
				request.setAttribute("showBtn01", false);
			}

			// 得到按钮2
			String btn02Name = (String)request.getParameter("btn02Name");
			if(btn02Name!=null && !btn02Name.equals("")){
				request.setAttribute("showBtn02", true);
				request.setAttribute("btn02Name", request.getParameter("btn02Name"));
				request.setAttribute("btn02Url", request.getParameter("btn02Url"));
			}else{
				request.setAttribute("showBtn02", false);
			}

			// 得到按钮3
			String btn03Name = (String)request.getParameter("btn03Name");
			if(btn03Name!=null && !btn03Name.equals("")){
				request.setAttribute("showBtn03", true);
				request.setAttribute("btn03Name", request.getParameter("btn03Name"));
				request.setAttribute("btn03Url", request.getParameter("btn03Url"));
			}else{
				request.setAttribute("showBtn03", false);
			}

			// 得到按钮4
			String btn04Name = (String)request.getParameter("btn04Name");
			if(btn04Name!=null && !btn04Name.equals("")){
				request.setAttribute("showBtn04", true);
				request.setAttribute("btn04Name", request.getParameter("btn04Name"));
				request.setAttribute("btn04Url", request.getParameter("btn04Url"));
			}else{
				request.setAttribute("showBtn04", false);
			}
	%>

						<s:if test="#request.showType=='standard'">
							<!-- 温馨提示标准样式 -->
							<table align="center" class="t1" border="0" cellpadding="0" cellspacing="0">
                               <tr>
                                   <td class="odd" align="center">
										<div style="width: 700px; height: 300px;background: url('${pageContext.request.contextPath}/platform/jsp/public/noDate/infoPic_b.gif') 0px 0px no-repeat;background-color: #F2F6F7;">
											<div style="float: left;padding-left:170px;padding-top:145px;text-align: left;font: 12px;width: 680px;height: 200px;color: #333;line-height: 30px; color: #404040;">
												${showMsg}
											</div>
											<div style="float: left;padding-left:225px;padding-top:30px;text-align: center;font: 12px;width: 680px;height: 50px;">
												<s:if test="#attr.showBtn01">
													<input type="button" id="noDateBtn01" value="${btn01Name}" class="btn_submit" onclick="doGo('${btn01Url}')"/>
												</s:if>

												<s:if test="#attr.showBtn02">
													<input type="button" id="noDateBtn02" value="${btn02Name}" class="btn_submit" onclick="doGo('${btn02Url}')"/>
												</s:if>

												<s:if test="#attr.showBtn03">
													<input type="button" id="noDateBtn03" value="${btn03Name}" class="btn_submit" onclick="doGo('${btn03Url}')"/>
												</s:if>

												<s:if test="#attr.showBtn04">
													<input type="button" id="noDateBtn04" value="${btn04Name}" class="btn_submit" onclick="doGo('${btn04Url}')"/>
												</s:if>
											</div>
										</div>
                                   </td>
                               </tr>
							</table>
						</s:if>
						<s:elseif test="#request.showType=='small'">
							<!-- 温馨提示小型样式 -->
							<table align="center" class="t1" border="0" cellpadding="0" cellspacing="0">
                               <tr>
                                   <td class="odd" align="center">
										<div style="width: 720px; height: 160px;background: url('${pageContext.request.contextPath}/platform/jsp/public/noDate/infoPic_s.gif') 0px 0px no-repeat;">
											<div style="float: left;padding-left:160px;padding-top:63px;text-align: left;font: 12px;width: 680px;height: 110px;color: #333;line-height: 25px;">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${showMsg}
											</div>
											<div style="float: left;padding-left:160px;text-align: center;font: 12px;width: 680px;height: 30px;">
												<s:if test="#attr.showBtn01">
													<input type="button" id="noDateBtn01" value="${btn01Name}" class="btn_submit" onclick="doGo('${btn01Url}')"/>
												</s:if>

												<s:if test="#attr.showBtn02">
													<input type="button" id="noDateBtn02" value="${btn02Name}" class="btn_submit" onclick="doGo('${btn02Url}')"/>
												</s:if>

												<s:if test="#attr.showBtn03">
													<input type="button" id="noDateBtn03" value="${btn03Name}" class="btn_submit" onclick="doGo('${btn03Url}')"/>
												</s:if>

												<s:if test="#attr.showBtn04">
													<input type="button" id="noDateBtn04" value="${btn04Name}" class="btn_submit" onclick="doGo('${btn04Url}')"/>
												</s:if>
											</div>
										</div>
                                   </td>
                               </tr>
							</table>
						</s:elseif>

