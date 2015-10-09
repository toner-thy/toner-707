<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
	<head>
		<title>主页我的日程统计</title>

  		<script type="text/javascript">
 			function doDetail(id){
 				/* environment.dialog.open({
 					url : '${ctx}/agenda/agenda_detail.action?id='+id+'&t_date=' + new Date().getTime(),
 					width : window.top.getSize().x * 0.7,
 					height : window.top.getSize().x * 0.5,
					icon : '${pageContext.request.contextPath}/platform/theme/default/images/main/display.gif',
 					title : '我的日程详情'
 				}); */
 				$ENV.dialog.open({
					title : '我的日程详情',
					url : '${ctx}/agenda/agenda/agenda_detail.action?id='+id+'&t_date=' + new Date().getTime(),
					width : window.top.getSize().x * 0.7,
					height : window.top.getSize().y * 0.5,
					params : {
						text : '${parameters.textEl}',
						hidden : '${parameters.valueEl}'
					}
				});
 			}
		</script>
  	</head>

  	<body>
		<table style="width: 95%;background-color: #F3FBFE;">
			<tr>
				<td align="center">
					<c:if test="${not empty agendaList}">
						<table style="width: 100%;">
							<tr>
								<td align="right" width="90" valign="top">
									<!-- 图标 -->
									<img src="${pageContext.request.contextPath}/bmp/images/index/agenda/ico.jpg">
								</td>
								<td align="left" width="*" valign="top">
									<!-- 列表内容 -->
									<table style="width: 100%;" cellpadding="0" cellspacing="0">
										<c:forEach items="${agendaList}" var="agenda">
											<tr>
												<td style="color: #666666; padding-left: 5px; border-bottom: 1px dotted #99CCE8;" width="*" align="left">
													<a href="javascript:doDetail('${agenda.agendaId}')" id="${agenda.agendaId}" title="点击查看【${agenda.agendaTitle}】" style="cursor: hand;">
														<div style="width:90px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">
															${agenda.agendaTitle}
														</div>
													</a>
												</td>
												<td style="color: #B0B0B0; padding-right: 5px; border-bottom: 1px dotted #99CCE8;" align="right" width="40">
													<c:if test="${agenda!=null}">
														<fmt:formatDate value="${agenda.endTime}" pattern="MM/dd"/>
													</c:if>
													<c:if test="${agenda==null}">
														&nbsp;
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
						</table>
					</c:if>

					<c:if test="${empty agendaList}">
						<div style="margin-top: 30px; margin-bottom: 15px;">
							<img src="${ctx}/bmp/images/index/agenda/ico.jpg"/><img src="${ctx}/bmp/images/index/agenda/info.jpg"/>
						</div>
					</c:if>
				</td>
			</tr>
		</table>
	</body>
</html>