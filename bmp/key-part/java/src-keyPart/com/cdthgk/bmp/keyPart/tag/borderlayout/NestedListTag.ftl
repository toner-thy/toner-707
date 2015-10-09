<#assign uuid="com.cdthgk.view.web.freemarker.method.UUIDMethod"?new()>
<#assign id=uuid()>
<script type="text/javascript">
$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-core-1.4.js");
$ENV.loader.loadJavaScript("${contextPath}/resources/js/mootools/mootools-more-1.4.js");
$ENV.loader.loadJavaScript("${contextPath}/platform/layout/borderlayout/js/TabUtils.js");
$ENV.loader.loadJavaScript("${contextPath}/resources/js/SimpleUI/SimpleUI.js",function(){
	$ENV.onDomReady(function(){
		new SimpleUI.SimpleTab({
			el : 'tab_${id}',
			onActive : function(item, content, index) {
			},
			onFirstActive : function(item, content, index) {
				new IFrame({
					src : content.get('url'),
					frameborder : 0,
					styles : {
						width : content.getSize().x,
						height: content.getSize().y
					},
					events : {
						load : function() {
						}
					}
				}).inject(content);
			}
		});

	});
});

// 详 情
function doDetail(id){
	$ENV.dialog.open({
		url : '${ctx}/bmp/part/part_detail.action?part.partId='+id+'&t_date=' + new Date().getTime(),
		width : window.top.getSize().x * 0.8,
		height : window.top.getSize().y * 0.9,
		title : '要害部位详情'
	});
}
</script>

<div id="tab_${id}" class="panel tab_panel" style="overflow:hidden;">
	<div class="panel_header" style="width: 100%;">
		<div class="tab_bar">
			<div class="panel_title">保密要害部位列列表</div>
			<div class="panel_title">保密要害部位列密级变更历史列表</div>
			<div class="panel_title">保密要害部位解除信息列表</div>
		</div>

		<div style="position: absolute;right: 0;top: 0;">
			<div style="text-align: right;margin-top:3px;vertical-align: middle;">
			</div>
			<div class=""></div>
			<div class=""></div>
		</div>
	</div>

	<div class="tab_panel_content">

		<!-- 保密要害部位列表 -->
		<div class="panel_content" style="height:570px;" url="${ctx}/bmp/part/part_list_list.action?${(parameters.parameterText)}&nestedflag=1">
		</div>

         <!--保密要害部位列密级变更历史列表-->
		<div class="panel_content" style="height:570px;"  url="${ctx}/bmp/part/keyPart_list_change.action?${(parameters.parameterText)}">
        </div>

        <!--保密要害部位解除信息列表-->
		<div class="panel_content" style="height:570px;"  url="${ctx}/bmp/part/keyPart_list_clear.action?${(parameters.parameterText)}">
        </div>
    </div>
</div>
