<%-- 
    Document   : qdAttributeReport
    Created on : 6/12/2014, 06:19:11 PM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Reporte)</title>
    </head>
    <body>
       <%
             response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.
             response.setHeader ("Content-Disposition", "attachment;filename=\"result.xls\"");
             HttpSession sesion=request.getSession();
             
             out.println((String)sesion.getAttribute("reportAttribute"));
             
        %>
    </body>
</html>
