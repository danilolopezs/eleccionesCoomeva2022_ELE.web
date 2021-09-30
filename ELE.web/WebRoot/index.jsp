<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Administraci&#243;n de Planchas e inhabilidad de Asociados - Inicio de Sesi&#243;n de Usuarios</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta http-equiv="refresh" content="10; url=gui/inicioAdm.jspx">
  </head>
  
  <body>
    <script>
			<!--
				window.open("gui/inicioAdm.jspx","","toolbar=no,status=yes,scrollbars=yes,menubar=no,directories=no,width=950,height=550,left=50,top=0,resizable=0");
			-->
			<% response.sendRedirect("./gui/inicioAdm.jspx"); %>
			
	</script>
  </body>
</html>
