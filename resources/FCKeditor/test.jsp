<%@ page language="java" import="com.fredck.FCKeditor.*" %>
<%@ taglib uri="/FCKeditor" prefix="FCK" %>
<script type="text/javascript" src="/FCKeditor/fckeditor.js"></script>

<%--
<form action="show.jsp" method="post" target="_blank">
 <FCK:editor id="content" basePath="/FCKeditor/"
       width="700"
       height="500"
       skinPath="/FCKeditor/editor/skins/silver/"
       toolbarSet = "Default"
 >
 input
        </FCK:editor>
<input type="submit" value="Submit">
</form>
--%>

<form action="show.jsp" method="post" target="_blank">
<table border="0" width="700"><tr><td>
<textarea id="content" name="content" style="WIDTH: 100%; HEIGHT: 400px">input</textarea>
<script type="text/javascript">
 var oFCKeditor = new FCKeditor('content') ;
 oFCKeditor.BasePath = "/FCKeditor/" ;
 oFCKeditor.Height = 400;
 oFCKeditor.ToolbarSet = "Default" ; 
 oFCKeditor.ReplaceTextarea();
</script>
<input type="submit" value="Submit">
</td></tr></table>
</form>

<%--
<form action="show.jsp" method="post" target="_blank">
<%
FCKeditor oFCKeditor ;
oFCKeditor = new FCKeditor( request, "content" ) ;
oFCKeditor.setBasePath( "/FCKeditor/" ) ;
oFCKeditor.setValue( "input" );
out.println( oFCKeditor.create() ) ;
%>
<br>
<input type="submit" value="Submit">
</form>
--%>