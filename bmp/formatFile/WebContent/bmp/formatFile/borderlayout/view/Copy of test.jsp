<%@ page pageEncoding="UTF-8"%>
<html>
	<head>
		<link href="../resource/style.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="../resource/common/AIP_MAIN.js"></script>
		<!--该事件在AIP引擎初始化完毕之后触发-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyCtrlReady>
		<!--
		 //HWPostil1_NotifyCtrlReady()
		//-->
		</SCRIPT>
		<!--在执行以下操作的时候触发此事件（打印；删除页面；删除节点；删除印章；添加印章；用户登录）。-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=JSNotifyBeforeAction(lActionType,lType,strName,plContinue)>
		<!--
		 //HWPostil1_NotifyBeforeAction(lActionType,lType,strName,plContinue);
		//-->
		</SCRIPT>
		<!--在改变用户的时候触发此事件-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyChangeCurrUser>
		<!--
		 //HWPostil1_NotifyChangeCurrUser()
		//-->
		</SCRIPT>
		<!--该事件在改变当前页面的时候触发-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyChangePage>
		<!--
		 //HWPostil1_NotifyChangePage()
		//-->
		</SCRIPT>
		<!--该事件在改变当前用户颜色的时候触发-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyChangePenColor>
		<!--
		// HWPostil1_NotifyChangePenColor()
		//-->
		</SCRIPT>
		<!--该事件在改变当前用户笔的宽度的时候触发-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyChangePenWidth>
		<!--
		 //HWPostil1_NotifyChangePenWidth()
		//-->
		</SCRIPT>
		<!--在改变编辑框的内容时候触发此事件-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyChangeValue(pcName,pcNewValue)>
		<!--
		 //HWPostil1_NotifyChangeValue(pcName,pcNewValue)
		//-->
		</SCRIPT>
		<!--在点击节点的时候触发此事件-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyClick(pcName)>
		<!--
		 //HWPostil1_NotifyClick(pcName)
		//-->
		</SCRIPT>
		<!--在关闭文档的时候触发此事件-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyCloseDoc>
		<!--
		 //HWPostil1_NotifyCloseDoc()
		//-->
		</SCRIPT>
		<!--在打开文档的时候触发此事件-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyDocOpened(lOpenResult)>
		<!--
		// HWPostil1_NotifyDocOpened(lOpenResult)
		//-->
		</SCRIPT>
		<!--在全屏的时候触发此事件-->
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyFullScreen>
		<!--
		 //HWPostil1_NotifyFullScreen()
		//-->
		</SCRIPT>
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyMenuMsg(lCmd)>
		<!--
		 //HWPostil1_NotifyMenuMsg(lCmd)
		//-->
		</SCRIPT>
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyModifyStatus>
		<!--
		 //HWPostil1_NotifyModifyStatus()
		//-->
		</SCRIPT>
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyPosChange(pcNoteName)>
		<!--
		 //HWPostil1_NotifyPosChange(pcNoteName)
		//-->
		</SCRIPT>
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyReset(pcName)>
		<!--
		 HWPostil1_NotifyReset(pcName)
		//-->
		</SCRIPT>
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifySelect(pcName,lNoteType)>
		<!--
		 //HWPostil1_NotifySelect(pcName,lNoteType)
		//-->
		</SCRIPT>
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifyStatusChanged(lStatus)>
		<!--
		// HWPostil1_NotifyStatusChanged(lStatus)
		//-->
		</SCRIPT>
		<SCRIPT LANGUAGE=javascript FOR=HWPostil1 EVENT=NotifySumbit(pcName)>
		<!--
		 //HWPostil1_NotifySumbit(pcName)
		//-->
		</SCRIPT>

		<title>AIP接口演示</title>

		<SCRIPT LANGUAGE=javascript>
		function menuOnClick(id){
			var id=document.getElementById(id);
			var dis=id.style.display;
			if(dis!="none"){
				id.style.display="none";

			}else{
				id.style.display="block";
			}
		}
		</SCRIPT>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body style="background: #ccc;">

		<center>
			<div style="padding-right: 0px; padding-left: 0px; background: #fff; padding-bottom: 0px; margin: -10px 0px 0px; width: 1024px; padding-top: 10px; height: 750px" align=center>
				<form name="myform">
					<table class=tableblock width="90%">
						<tbody>
							<tr class=tableheader>
								<td colspan=2 valign=middle>
								<center>
								<font size=6> aip接口演示页面</font>
								</center>
								</td>
							</tr>
							<tr>
								<td width="100">
								事件列表：
								</td>
								<td >
								<textarea  name="commandtext" cols=100 rows=3></textarea><br>
								</td>
							</tr>
							<tr class="TableHeader">
								<td colspan="2">
									<span id="spnAIPInfo"></span>
								</td>
							</tr>
						</tbody>
					</table><br><br>
					<table class="TableBlock" width="90%">
						<tr>
							<td width="15%" valign="top" class="leftTableData">
								<div class="menuItem" onClick="menuOnClick('xuqiu')">
									需求接口
								</div>
								<div id="xuqiu">

									<input type="button" class="btn" onclick="return OpenFile('')" value="打开文件" />

								</div>
								<div class="menuItem" onClick="menuOnClick('test1')">
									常用接口
								</div>
								<div id="test1" style="display: none">

									<input type="button" class="btn" onclick="return OpenFile('')" value="打开文件" />
									<input type="button" class="btn" onclick="return AddSeal(0,0,'','')" value="手动盖章" />
									<input type="button" class="btn" onclick="return AddSeal(0,1,'','')" value="手写签名" />
									<input type="button" class="btn" onclick="return SaveToS(0,'C://test.aip','')" value="保存文件" />
									<input type="button" class="btn" onclick="return FilePrint(1)" value="打印文件" />

								</div>

								<div class="menuItem" onClick="menuOnClick('seal')">
									盖章方式
								</div>
								<div id="seal" style="display: none">

									<input type="button" class="btn" onclick="return AutoSeal(0,0,0,'10000:10000:0','','')" value="绝对坐标" />
									盖章文字：<input type="text" id="sealtxt" size="5" value="总则" />
									<input type="button" class="btn" onclick="AutoSeal(0,0,1,document.getElementById('sealtxt').value,'','')" value="查找文字盖章" />
									<input type="button" class="btn" onclick="return AutoSeal(0,1,0,'','','')" value="骑缝章" />
									<input type="button" class="btn" onclick="return AutoSeal(0,2,0,'','','')" value="对开骑缝章" />
									<input type="button" class="btn" onclick="return FileAddseal('C:/test.sel',0,'20000:20000:0','')" value="使用文件盖章" />
								</div>

								<div class="menuItem" onClick="menuOnClick('aip')">
									AIP文件操作
								</div>
								<div id="aip" style="display: none">

									<input type="button" class="btn" onclick="return OpenFile('')" value="打开文件" />
									<input type="button" class="btn" onclick="return SaveTo(0,'','pdf')" value="保存文件" />
									<input type="button" class="btn" onclick="return FileMerge('',0)" value="合并文件" />
									<input type="button" class="btn" onclick="return SaveTo(0,'','pdf')" value="关闭文件" />

								</div>
								<div class="menuItem" onClick="menuOnClick('doc')">
									WORD文件操作
								</div>
								<div id="doc" style="display: none">

									<input type="button" class="btn" onclick="" value="打开word" />
									书签：<input type="text" id="books" size="5" value="test" />
									<input type="button" class="btn" onclick="" value="书签赋值" />
									模版：<input type="text" id="books" size="8" value="C:/model.doc" />
									<input type="button" class="btn" onclick="" value="套红" />
									<input type="button" class="btn" onclick="" value="清稿" />
									<input type="button" class="btn" onclick="" value="保存word" />

								</div>
								<div class="menuItem" onClick="menuOnClick('pen')">
									手写设置
								</div>
								<div id="pen" style="display: none">

									<input type="button" class="btn" onclick="return SetPenwidth()" value="笔宽" />
									<input type="button" class="btn" onclick="return SetColor()" value="颜色" />
									<input type="button" class="btn" onclick="return SetPressurelevel()" value="压感" />

								</div>
								<div class="menuItem" onClick="menuOnClick('operator')">
									操作状态设置
								</div>
								<div id="operator" style="display: none">

									<input type="button" class="btn" onclick="return SetAction(1)" value="浏览" />
									<input type="button" class="btn" onclick="return SetAction(2)" value="文字选择" />

								</div>
								<div class="menuItem" onClick="menuOnClick('page')">
									页面视图设置
								</div>
								<div id="page" style="display: none">

									<input type="button" class="btn" onclick="return SetPageMode(1)" value="原始大小" />
									<input type="button" class="btn" onclick="return SetPageMode(2)" value="适应宽度" />
									<input type="button" class="btn" onclick="return SetPageMode(3)" value="窗口大小" />
									<input type="button" class="btn" onclick="return SetPageMode(4)" value="双页显示" />
									<input type="button" class="btn" onclick="return SetPageMode(5)" value="无边框" />

								</div>
								<div class="menuItem" onClick="menuOnClick('tool')">
									工具栏/菜单栏
								</div>
								<div id="tool" style="display: none">

									<input type="button" class="btn" onclick="return ShowToolBar(0)" value="隐藏工具栏" />
									<input type="button" class="btn" onclick="return ShowToolBar(1)" value="显示工具栏" />
									<input type="button" class="btn" onclick="return ShowDefMenu(0)" value="隐藏菜单" />
									<input type="button" class="btn" onclick="return ShowDefMenu(1)" value="显示菜单" />
									<input type="button" class="btn" onclick="return ShowScrollBarButton(2)" value="隐藏滚动条" />
									<input type="button" class="btn" onclick="return ShowScrollBarButton(1)" value="隐藏滚动条的工具栏" />
									<input type="button" class="btn" onclick="return ShowScrollBarButton(0)" value="显示滚动条" />

								</div>
								<div class="menuItem" onClick="menuOnClick('other')">
									其他
								</div>
								<div id="other" style="display: none">

									<input type="button" class="btn" onclick="return SetFullScreen(1)" value="全屏显示" />
									<input type="button" class="btn" onclick="return SearchText('总则',0,0)" value="搜索文字" />
									<input type="button" class="btn" onclick="return AddBarcode('测试内容',3000,3000,1)" value="插入二维条码" />

								</div>
							</td>
							<td width="85%" height="768" valign="top" >
								<!-- -----------------------------== 装载AIP控件 ==--------------------------------- -->
								<script src="../resource/common/LoadAip.js"></script>
								<!-- --------------------------------== 结束装载控件 ==----------------------------------- -->
							</td>
						</tr>
					</table>
				</form>
			</div>
		</center>
	</body>
</html>