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
function doDetailSecPerson(id){
	$ENV.dialog.open({
		url : '${ctx}/bmp/secrecyperson/keySectionSecrecyPerson_detail.action?secrecyPerson.secrecyPersonId='+id+'&t_date=' + new Date().getTime(),
		width : window.top.getSize().x * 0.8,
		height : window.top.getSize().y * 0.9,
		title : '涉密人员详情'
	});
}

</script>
<div id="tab_${id}" class="panel tab_panel" style="overflow:hidden;">
    <!--面板头开始-->
	<div class="panel_header" style="width: 100%;">
		<div class="tab_bar">
			<div class="panel_title">涉密人员列表</div>
			<div class="panel_title">涉密人员密级变更历史列表</div>
			<div class="panel_title">涉密人员脱密信息列表</div>
		</div>
		<div style="position: absolute;right: 0;top: 0;">
			<div style="text-align: right;margin-top:3px;vertical-align: middle;">
			</div>
			<div class=""></div>
			<div class=""></div>
		</div>
	</div>
	<!--面板头结束-->

	<!--3个面板开始-->
	<div class="tab_panel_content">

		<!--机关涉密人员列表-->
		<div id="secrecyPerson_list_list_div"
			class="panel_content" style="height:570px;" url="${ctx }/bmp/secrecyperson/secrecyPerson_list_list.action?${(parameters.parameterText)}&nestedflag=1">
        </div>

        <!--涉密人员密级变更历史列表-->
		<div id="secrecyPerson_secrecyLevelChangeHistory_div"
			class="panel_content" style="height:570px;" url="${ctx }/bmp/secrecyperson/secrecyPerson_secrecyLevelChangeHistory.action?${(parameters.parameterText)}">
        </div>

        <!--涉密人员脱密信息列表-->
		<div id="secrecyPerson_decryptionHistory_div"
			class="panel_content" style="height:570px;" url="${ctx }/bmp/secrecyperson/secrecyPerson_decryptionHistory.action?${(parameters.parameterText)}">
        </div>

	</div>
	<!--3个面板结束-->

</div>
