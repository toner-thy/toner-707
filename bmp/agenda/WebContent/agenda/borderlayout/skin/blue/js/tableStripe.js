// JavaScript Document
var tableToPickWithClassName = "pickme";  //��Ҫ��׽�ı����ʽ��

function setTableToPick(className)
{
	tableToPickWithClassName = className;
	
	domReady(stripeTables);
	domReady(highlightRows);
}


function stripeTables() {
	var tables = document.getElementsByTagName("table");
	for (var m=0; m<tables.length; m++) {
		if (tables[m].className == tableToPickWithClassName) {
			var tbodies = tables[m].getElementsByTagName("tbody");
			for (var i=0; i<tbodies.length; i++) {
				var odd = true;
				var rows = tbodies[i].getElementsByTagName("tr");
				for (var j=0; j<rows.length; j++) {
					if (odd == false) {
						odd = true;
					} else {
						addClass(rows[j],"odd");
						odd = false;
					}
				}
			}
		}
	}
}

function highlightRows() {
  if(!document.getElementsByTagName) return false;
  	var tables = document.getElementsByTagName("table");
	for (var m=0; m<tables.length; m++) {
		if (tables[m].className == tableToPickWithClassName) {
			  var tbodies = tables[m].getElementsByTagName("tbody");
			  for (var j=0; j<tbodies.length; j++) {
				 var rows = tbodies[j].getElementsByTagName("tr");
				 for (var i=0; i<rows.length; i++) {
					   rows[i].oldClassName = rows[i].className
					   rows[i].onmouseover = function() {
						  if( this.className.indexOf("selected") == -1)
							 addClass(this,"highlight");
					   }
					   rows[i].onmouseout = function() {
						  if( this.className.indexOf("selected") == -1)
							 this.className = this.oldClassName
					   }
				 }
			  }
		}
	}
}

function selectRowCheckbox(row) {
	var checkbox = row.getElementsByTagName("input")[0];
	if (checkbox.checked == true) {
		checkbox.checked = false;
	} else
	if (checkbox.checked == false) {
		checkbox.checked = true;
	}
}

//�����һ�м���ʾΪSelected״̬
function lockRow() {
  	var tables = document.getElementsByTagName("table");
	for (var m=0; m<tables.length; m++) {
		if (tables[m].className == tableToPickWithClassName) {
				var tbodies = tables[m].getElementsByTagName("tbody");
				for (var j=0; j<tbodies.length; j++) {
					var rows = tbodies[j].getElementsByTagName("tr");
					for (var i=0; i<rows.length; i++) {
						rows[i].oldClassName = rows[i].className;
						rows[i].onclick = function() {
							if (this.className.indexOf("selected") != -1) {
								this.className = this.oldClassName;
							} else {
								addClass(this,"selected");
							}
							selectRowCheckbox(this);
						}
					}
				}
		}
	}
}

//���CheckBOX�󣬵��м���ʾΪSelected״̬
function lockRowUsingCheckbox() {
	var tables = document.getElementsByTagName("table");
	for (var m=0; m<tables.length; m++) {
		if (tables[m].className == tableToPickWithClassName) {
			var tbodies = tables[m].getElementsByTagName("tbody");
			for (var j=0; j<tbodies.length; j++) {
				var checkboxes = tbodies[j].getElementsByTagName("input");
				for (var i=0; i<checkboxes.length; i++) {
					checkboxes[i].onclick = function(evt) {
						if (this.parentNode.parentNode.className.indexOf("selected") != -1){
							this.parentNode.parentNode.className = this.parentNode.parentNode.oldClassName;
						} else {
							addClass(this.parentNode.parentNode,"selected");
						}
						if (window.event && !window.event.cancelBubble) {
							window.event.cancelBubble = "true";
						} else {
							evt.stopPropagation();
						}
					}
				}
			}
		}
	}
}

var splashDelay = 600;
var splashRowID = new Array();
var splashCssName = new Array();
var totalSplashTimes = new Array();
var splashOldCssName = new Array();
var splashCssState = new Array();
var splashFirstTime = new Array(); 
var splashObjCount = 0;


//����splash
function resetSplash( foundIndex)
{
	splashFirstTime[foundIndex] = true;
	var datarow = GE( splashRowID[foundIndex] );
	
	if( datarow != null )
	{
		datarow.className = splashOldCssName[foundIndex];
	}

}

var splashObjArray = new Array(); //�������� ��������ΪarrayHolder
//ͻ����ʾid="newdatarow" �� id="lastvisitedrow" �� ���� �� һ��������˸���tr�С�����֧�ֶ����ͬʱ��˸����
function splashObject( objId , highLightCssName , times )
{
	//alert( countxx ++ );
	var objToSplash = GE( objId );
	
	var foundIndex  = storedUniqueObjToArray( objToSplash , splashObjArray);
	//alert( objToSplash +  foundIndex);
	
	 if (splashFirstTime[foundIndex] == null)
	 {
		 splashFirstTime[foundIndex] = true;
		 splashRowID[foundIndex] = objId
		 splashCssName[foundIndex] = highLightCssName
		 totalSplashTimes[foundIndex] = times
		 splashOldCssName[foundIndex] = objToSplash.className;
	 }
	
	if( objToSplash != null && totalSplashTimes[foundIndex] > 0)
	{
		
		
		if( splashFirstTime[foundIndex] )
		{		
			splashRowID[foundIndex] = objId
			splashCssName[foundIndex] = highLightCssName
			totalSplashTimes[foundIndex] = times
			splashFirstTime[foundIndex] = false;
			splashOldCssName[foundIndex] = objToSplash.className;
			//alert(splashOldCssName[foundIndex]);
		}

		totalSplashTimes[foundIndex] -- ;
			
		
		if( splashCssState[foundIndex] == 0 )
		{
			
			objToSplash.className = splashCssName[foundIndex];
			splashCssState[foundIndex] = 1;
			window.setTimeout("splashObject( splashRowID[" + foundIndex + "] , splashCssName[" + foundIndex + "] , totalSplashTimes[" + foundIndex + "] )", splashDelay );
			
		}
		else
		{
			objToSplash.className = splashOldCssName[foundIndex];

			splashCssState[foundIndex] = 0;
			window.setTimeout("splashObject( splashRowID[" + foundIndex + "] , splashCssName[" + foundIndex + "] , totalSplashTimes[" + foundIndex + "] )", splashDelay );
			
		}
	}
	else
	{
		resetSplash( foundIndex );			
	}

}

//addLoadEvent( function() {  splashObject( "newdatarow" , "newdatarow" , 16 ) ; } );
//addLoadEvent( function() {  splashObject( "lastvisitedrow" , "lastvisitedrow" , 16 ) ; } );