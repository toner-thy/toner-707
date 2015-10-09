<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.cdthgk.com/tags/application" prefix="ap"%>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags/styles" prefix="styles"%>
<%@ taglib uri="http://www.cdthgk.com/tags/cp" prefix="cp" %>
<%@ taglib uri="http://www.cdthgk.com/tags/cpc" prefix="cpc"%>
<%@ taglib prefix="attach" uri="http://www.cdthgk.com/tags/attachment"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Q&A</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<!-- 复杂表格CSS支持 -->
<link href="/bmp/platform/css/public/table/complexTbSustain.css" type="text/css" rel="stylesheet" />
<link href="/bmp/platform/tag/compositePanel/cp.css" type="text/css" rel="stylesheet" />

<script src="/bmp/resources/js/environment/environment.js" type="text/javascript"></script>
<script src="/bmp/platform/tag/compositePanel/cp.js" type="text/javascript"></script>

<script type="text/javascript">
  $ENV.loader.loadStyleSheet("/bmp/platform/template/borderlayout/skin/blue/css/page.css");
  $ENV.loader.loadStyleSheet("/bmp/platform/template/borderlayout/skin/blue/css/ecTable.css");
  $ENV.loader.loadStyleSheet("/bmp/resources/theme/borderlayout/skin/blue/notimoo/notimoo-1.2.1.css");

  $ENV.loader.loadJavaScript("/bmp/resources/js/mootools/mootools-core-1.4.js");
  $ENV.loader.loadJavaScript("/bmp/resources/js/mootools/mootools-more-1.4.js");
  $ENV.loader.loadJavaScript("/bmp/resources/js/notimoo/notimoo-1.2.1.js");
  $ENV.loader.loadJavaScript("/bmp/platform/layout/borderlayout/js/TabUtils.js");
  $ENV.loader.loadJavaScript("/bmp/platform/theme/borderlayout/resources/js/ectable/EcTable.js");
  $ENV.loader.loadJavaScript("/bmp/resources/js/My97DatePicker/WdatePicker.js");
  $ENV.loader.loadJavaScript("/bmp/resources/js/SimpleUI/SimpleUI.js",
      function() {
        $ENV.onDomReady(function() {

        });
      });

  // 新 增
  function doAdd(action) {
    window.location.href = action+"?_ts="+ new Date().getTime();
  }
  // 新 增
  function doView(action) {
     window.location.href = action+"?_ts="+ new Date().getTime();
  }

  // 编 辑
  function doEdit(action) {
    var items = EcTable.getCheckedItems();
    if(items.length!=1) {
      alert("请选择一项记录");
      return;
    }
    window.location.href = action+"?technologyDefence.questionId=" + items[0].value + "&_ts="+ new Date().getTime();
  }

  // 删除方法
  function doDel(action) {
    var items = EcTable.getCheckedItems();
    if(items.length==0) {
      alert("请至少选择一项记录");
      return;
    }
    if(window.confirm('确定删除所选记录吗？')){
      var ids = "";
      items.each(function(item){
      ids += item.value + ",";
      });
      $('deleteIds').value = ids;
      var forms = $('delete_form');
      forms.jsp = action;
      forms.submit();
    }
  }

  //引用
  function doQuote(action){
    var items = EcTable.getCheckedItems();
    if(items.length!=1) {
      alert("请选择一项记录");
      return;
    }
    window.location.href = action + "?technologyDefence.questionId=" + items[0].value + "&_ts="+ new Date().getTime();
  }

  // 详 情
  function doDetail(id) {
    if( id!=null && id!="" ){
        $ENV.dialog.open({
          url : "/bmp/bmp/technologyDefence/technologyDefence_detail.jsp?technologyDefence.questionId=" + id + "&_ts=" + new Date().getTime(),
          width : 0.8,
          height : 0.9,
          title : '详情'
        });
    }else{
      alert("系统获取id错误，请重试");
      return;
    }
  }
</script>
</head>

<body>
  <!-- 公共头部 -->
  <div class="button_bar">
    <div class="left">
      <div class="pop_button_bar"><a href="###"  onclick="doAdd('/bmp/bmp/technologyDefence/technologyDefence_add.jsp');this.blur();" class="pop_button"><span>新增</span></a>
      <a href="###"  onclick="doEdit('/bmp/bmp/technologyDefence/technologyDefence_edit.jsp');this.blur();" class="pop_button"><span>编辑</span></a>
      <a href="###"  onclick="doDel('/bmp/bmp/technologyDefence/technologyDefence_delete.jsp');this.blur();" class="pop_button"><span>删除</span></a>
      <a href="###"  onclick="doAdd('/bmp/bmp/technologyDefence/technologyDefence_view.jsp');this.blur();" class="pop_button"><span>展示全部</span></a>
      </div>
    </div>
    <div class="right">
      <div class="pop_button_bar">
         <a	class="pop_button pop_button_refresh" href="###" onclick="javascript:window.refresh();"><span>刷新本页</span></a>
         <a class="pop_button pop_button_close" href="###" onclick="javascript:TabUtil.closeTab();"><span>退出本页</span></a>
      </div>
    </div>
  </div>

  <div class="body_content">
    <!-- 复合面板开始 -->
    <div class="panel">
      <div id="cpPanel" style="width: 100%;">
        <!-- 切换按钮 -->
        <div class="cpBtnBar">
          <div id="cp001Btn" class="cpBtn_ov" href="###" onmouseover="javascript:showCp(2,'cp001','年度工作计划简介');">关 于</div>
          <div id="cp002Btn" class="cpBtn" href="###" onmouseover="javascript:showCp(2,'cp002','年度工作计划查询');">查 询</div>
        </div>
        <!-- 切换面板 -->
        <div id="cpMainDiv" class="cpMainDiv">
          <table align="center" class="content_table" style="width: 100%;">
            <tr>
              <td width="100" valign="top"><img src="##" /></td>
              <td width="1" style="background-color: #ddd;"></td>
              <td width="15"></td>
              <td width="*" valign="top">
                <div id="cp001"
                  style="width: 100%; padding-bottom: 1px; padding-left: 1px; padding-right: 1px;">
                  <!-- 模块简介 -->
                  <div class="cpMsgTitle">关于技术防范</div>
                  <div class="cpMsgContext">
                    未找到相应的可配置文本，请检查。
                  </div>
                  <!-- 上下之间的间隔，可以调节高度 -->
                  <div class="cpMsgFg" style="height: 7px; overflow: hidden;"></div>
                </div>
                <div id="cp002"
                  style="display: none; width: 100%; padding-bottom: 1px; padding-left: 1px; padding-right: 1px;">
                  <form method="post" id="queryform" action="/bmp/bmp/technologyDefence/technologyDefence_list.jsp">
                    <table class="st" width="100%">
                      <tr>
                        <td class="tbLable fr">问题内容：</td>
                        <td class="tbValue fl"><input name="technologyDefence.questionTitle" value="" type="text"/></td>
                        <td class="tbLable fr">问题类型：</td>
                        <td class="tbValue fl">
                          <select>
                          <option>物理安全</option>
                          <option>电子防范技术</option>
                          <option>应用安全</option>
                          </select>
                        </td>
                      </tr>
                      <tr>
                        <td colspan="4" class="fc" style="border: 0px;">
                          <div class="stBtnBar">
                            <a class="pop_button" href="###"><span>查	询</span></a> <a class="pop_button"
                              href="javascript:document.getElementById('queryform').reset();"><span>重	置</span></a>
                          </div>
                        </td>
                      </tr>
                    </table>
                  </form>
                </div>
              <td width="15"></td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <!-- 复合面板结束 -->

    <!-- 内容panel开始 -->
    <div class="panel tMargin">
      <div class="panel_header">
        <div class="panel_title panel_titleListIco">技术防范列表</div>
        <div class="panel_btn_bar pop_button_bar"></div>
      </div>
      <div class="panel_content panel_content_table">
<form id="questionList"  action="/bmp/bmp/technologyDefence/technologyDefence_list.jsp"  method="post" >
<div>
<input type="hidden"  name="ec_i"  value="questionList" />
<input type="hidden"  name="questionList_crd"  value="10" />
<input type="hidden"  name="questionList_p"  value="1" />
<input type="hidden"  name="questionList_a_questionId_checkbox"  value="questionId" />
<input type="hidden"  name="questionList_a_size=18"  value="questionTitle" />
<input type="hidden"  name="questionList_a_size=28"  value="questionContent" />
<input type="hidden"  name="questionList_a_size=18"  value="createperson.userInfo.name" />
</div>
<div class="eXtremeTable" >
<table id="questionList_table"  border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%" >
  <thead>
  <tr>
    <td class="tableHeader"  width="5%" ><input type="checkbox"  name="questionId_checkbox"  id="questionId_checkbox"  class="class_questionId_checkbox"  onclick="EcTable.checkAll('questionId_checkbox')" /></td>
    <td class="tableHeader"  width="45%" >问题内容</td>
    <td class="tableHeader" >答案</td>
    <td class="tableHeader"  width="10%" >问题类型</td>
    <td class="tableHeader"  width="10%" >创建时间</td>
    <td class="tableHeader"  width="10%" >创建人</td>
    <td class="tableHeader"  width="10%" >详 情</td>
  </tr>
  </thead>
  <tbody class="tableBody" >
  <tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
    <td width="5%" ><input type="checkbox"  name="questionId"  value="ca82cae646b6ea110146b71db9d40000"  class="row_checkbox " /></td>
    <td width="45%" >是否安装门、窗、柜、锁</td>
    <td><p>是</p></td>
    <td width="15%" >物理安全</td>
    <td width="10%" >2014-06-20</td>
    <td width="15%" >总系统管理员</td>
    <td width="10%" >
    <a href="###" onclick="doDetail('ca82cae646b6ea110146b71db9d40000');"><img src="/bmp/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
    </td>
  </tr>
  <tr class="even"  onmouseover="this.className='highlight'"  onmouseout="this.className='even'" >
    <td width="5%" ><input type="checkbox"  name="questionId"  value="ca82cae646b6ea110146b811ce820006"  class="row_checkbox " /></td>
    <td width="45%" >是否安装视频监控</td>
    <td><p>是</p></td>
    <td><p>电子防范技术</p></td>
    <td width="10%" >2014-06-20</td>
    <td width="15%" >总系统管理员</td>
    <td width="10%" >
                <a href="###" onclick="doDetail('ca82cae646b6ea110146b811ce820006');"><img src="/bmp/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
              </td>
  </tr>
  <tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >
    <td width="5%" ><input type="checkbox"  name="questionId"  value="ca82cae646b6ea110146b71db9d40000"  class="row_checkbox " /></td>
    <td width="45%" >计算机是否安装杀毒软件</td>
    <td><p>是</p></td>
    <td width="15%" >应用安全</td>
    <td width="10%" >2014-06-20</td>
    <td width="15%" >总系统管理员</td>
    <td width="10%" >
    <a href="###" onclick="doDetail('ca82cae646b6ea110146b71db9d40000');"><img src="/bmp/platform/template/borderlayout/skin/blue/img/btn/display.gif" border="0" title="显示详情"/></a>
    </td>
  </tr>
  </tbody>
</table>
        <table class="page_control" >
  <tr style="padding: 0px;" >
    <td colspan="6" >
    <table border="0"  cellpadding="0"  cellspacing="0"  width="100%" >
      <tr>
        <td class="compactToolbar"  align="right" >
        <table border="0"  cellpadding="1"  cellspacing="2" >
          <tr>
        <td class="statusBar"  align="right" >共找到<font color=red>2</font>条记录,分<font color=red>1</font>页显示&#160;</td>
          <td><span>第一页</span></td>
          <td><span>上一页</span></td>
          <td><span>下一页</span></td>
          <td><span>最后页</span></td>
          <td><img src="/bmp/platform/template/borderlayout/skin/blue/img/ec/separator.gif"  style="border:0"  alt="Separator" /></td>
          <td>每页显示<select name="questionList_rd"  class="page_control"  onchange="" >
        <option value="10"  selected="selected">10</option><option value="30" >30</option><option value="100" >100</option>
        </select>条</td>
          <td><img src="/bmp/platform/template/borderlayout/skin/blue/img/ec/separator.gif"  style="border:0"  alt="Separator" /></td>
          <td class="statusBar" >转到<input type="text"  name="questionList_cpn"  class="page_control"  value="1"  size="1"  onkeypress="javascript111:if(event.keyCode==13){var re = /^[0-9]*[1-9][0-9]*$/;if(this.value < 1 || this.value > 1|| !re.test(this.value)){alert('页码不正确，请重新输入!');return false;}document.forms.questionList.questionList_p.value=this.value;document.forms.questionList.setAttribute('action','/bmp/bmp/technologyDefence/technologyDefence_list.jsp');document.forms.questionList.setAttribute('method','post');document.forms.questionList.submit()}" >页</td>
          </tr>
        </table></td>
      </tr>
    </table>
    </td>
  </tr>

        </table>
</div>
</form>



      </div>
    </div>
    <!-- 内容panel结束 -->
  </div>

  <!-- 删除用隐藏表单 -->
  <form id="delete_form" name="delete_form" method="post">
    <input id="deleteIds" name="deleteIds" type="hidden"/>
  </form>

</body>
</html>





<form id="form_1403486994515" action="/bmp/bmp/technologyDefence/technologyDefence_list.jsp" method="GET">

</form>
<script type="text/javascript">
<!--
window.refresh = function() {
  document.getElementById('form_1403486994515').submit();
}
if(window.$ENV) {
  $ENV.page.refresh = window.refresh;
  //$ENV.getRegion();
}
//-->
</script>


<script type="text/javascript">
<!--
if(window.$ENV) {
  if (window.getOpener) {
    $ENV.page.exit = window.close;
  } else if (window.dialogArguments != "undefined") {
    $ENV.page.exit = window.close;
  } else if (window.opener){
    $ENV.page.exit = window.close;
  } else if (window.parent) {
    alert("未实现");
  }
}
//-->
</script>



<script type="text/javascript">
<!--
var messages = [];

if (window.$ENV && $ENV.getComponent('alert.messager')) {
  $ENV.getComponent('alert.messager').show(messages);
}
//-->
</script>



