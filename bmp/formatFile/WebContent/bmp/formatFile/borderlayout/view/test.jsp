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

		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body style="background: #ccc;">
		<center>
			<div style="padding-right: 0px; padding-left: 0px; background: #fff; padding-bottom: 0px; margin: -10px 0px 0px; width: 1024px; padding-top: 10px; height: 750px" align=center>
				<form name="myform">
					<table class="TableBlock" width="90%">
						<tr>
							<td height="768" valign="top" >
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
<script>
ShowToolBar(0);
ShowDefMenu(0);
OpenFile('../resource/luoyan.aip');
</script>