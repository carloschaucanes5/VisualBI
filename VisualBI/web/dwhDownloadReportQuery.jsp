<%-- 
    Document   : dwhDownloadReports
    Created on : 1/12/2014, 10:05:02 PM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
             response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.
             response.setHeader ("Content-Disposition", "attachment;filename=\"result.xls\"");
             HttpSession sesion=request.getSession();
             
             out.println((String)sesion.getAttribute("report"));
             
        %>
    </body>
</html>
