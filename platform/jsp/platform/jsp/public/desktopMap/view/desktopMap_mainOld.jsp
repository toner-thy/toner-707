<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="ui" uri="http://www.cdthgk.com/tags/ui"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>${name}</title>

		<script src="${contextPath}/resources/js/environment/environment.js" type="text/javascript"></script>
		<script type="text/javascript">
		$ENV.onDomReady(function(){
			$ENV.setContext('${contextPath}');
			$ENV.setContainer(window['home_container']);

			window.status = '用户 ：${_.login.actor.user.userInfo.name}  单位：${_.login.actor.user.organ.name}';
		});

		$ENV.loader.loadStyleSheet("${contextPath}/platform/template/borderlayout/skin/blue/css/pageNoButtonBar.css");
		$ENV.loader.loadStyleSheet("${contextPath}/resources/theme/borderlayout/skin/blue/SimpleUI/css/SimpleUI.css");
		$ENV.loader.loadStyleSheet("${contextPath}/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");
		$ENV.loader.loadStyleSheet("${contextPath}/resources/theme/borderlayout/skin/blue/AscribeDialog/main.css");

		$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-core-1.4.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-more-1.4.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/SimpleUI/SimpleUI.js");
		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/platform/resources/js/platform.js");
		$ENV.loader.loadJavaScript("${contextPath}/resources/js/notimoo/notimoo-1.2.1.js", function(){
			$ENV.onDomReady(function(){
				$ENV.registComponent('alert.messager', {
					notimoo : new Notimoo(),
					show : function(messages) {
						var notimoo = this.notimoo;
						if (messages) {
							var tp = $type(messages);
							if (tp == 'string') {
								notimoo.show({
									sticky: false,
									title: '消息提示',
									message: messages
								});
							} else {
								$A(messages).each(function(msg,index){
									(function(){
										notimoo.show({
											sticky: false,
											title: '消息提示',
											message: msg
										});
									}).delay(index*1000);
								});
							}
						}
					}
				});
			});
		});

		$ENV.loader.loadJavaScript("${pageContext.request.contextPath}/resources/js/AscribeDialog/dialog.js", function(){
			// 判断每次弹出时，需要关闭前一个弹出框,故做了一个全局变量
	    	var ascDialog = new AscDialog({
				isModal: false,
				useArrows: false,
				addCloseBtn: true,
				popOpacity: .9,
				classPrefix: 'Asc',
				place: {
					'ss': { target:'window', io:1, align:'se', offset:0, margin: -25 }, // show start
					'se': { trans:'fly', target:'window', io:-1, align:'se', offset: 8, margin:0 }, // show end
					'he': { trans:'fly', target:'window', io:-1, align:'se', offset: 25, margin:-25 } // hide end
				}
			});
			$ENV.onDomReady(function(){
				var parseMessages = function(messages) {
					var content = '';
					var tp = $type(messages);
					if (tp == 'string') {
						content += '<a href="###">' + messages + '</a></br>';
					} else if (tp == 'object') {
						var url = messages.visitTarget;
						if (url.indexOf('/') != 0) {
							url = '/' + url;
						}
						url = $ENV.getContext() + url;
						content += '<li><a href="###" url="' + url + '">' + messages.content + '</a>';
					} else {
						$A(messages).each(function(msg,index){
							content += parseMessages(msg);
						});
					}
					return content;
				};

				$ENV.registComponent('system.notifier', {
					notifier : function() {
						return ascDialog;
					},
					show : function(messages) {
						var content = '';
						if (messages) {
							var id = $ENV.generateId();
							content = parseMessages(messages);
							if (!content) return;
							content = "<ul id='" + id + "'>" + content + "</ul>";
							var notifier = this.notifier();
							<%-- notifier.set_contents(msg, cls, width); --%>
							notifier.set_contents(content, 'close');
							notifier.show();
							var length = $A(messages).length;
							$(id).getElements('a').addEvent('click', function() {
								var url = this.get('url');
								platform.open(url);
								this.getParent().destroy();
								if (--length == 0) {
									notifier.hide();
								}
							});
						}
					}
				});

				var loadMessages = function() {
					new Request.JSON({
					    url: "${contextPath}/platform/notification/messager/person/newly.action",
					    onSuccess: function(messages, text){
					    	$ENV.getComponent('system.notifier').show(messages);
					    }
					}).get();
				};
				loadMessages.delay(${_.config.messagerConfiguration.delay} * 1000);
				loadMessages.periodical(${_.config.messagerConfiguration.periodical} * 60 * 1000);
			});
		});

		window.onbeforeunload = function(event) {
			var e = event;
			if (window.event) {
				e = window.event;
			}
	        var n = e.screenX - window.screenLeft;
	        var b = n > document.documentElement.scrollWidth-20;
	        if(b && e.clientY < 0 || e.altKey) {
				if(confirm("您确定关闭吗？")) {
					var href = "${contextPath}/platform/login/logouting.action";
					if ($ENV.getContainer()) {
						$ENV.getContainer().location.href = href;
					} else {
						window.top.location.href = href;
					}
				} else {
					return false;
				}
			}
		}

		</script>

		<style type="text/css">
		html,body {
			margin: 0;padding: 0;overflow:hidden;
		}
		#home_container {
			position:absolute;
			_position:static;
			top:0;
			bottom:0;
			left:0;
			right:0;
			overflow: hidden;
			width: 100%;
			height: 100%;
		}
		</style>
	</head>
	<body>
		<!-- 老地址：暂时不读数据库（/platform/desktop/virtual.action） -->
		<iframe id="home_container" name="home_container" frameborder="0" scrolling="no" src="${ctx}/platform/desktop/virtual.action"></iframe>
	</body>
</html>