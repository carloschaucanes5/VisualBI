<%-- 
    Document   : qdGraphicTableChart
    Created on : 6/12/2014, 03:31:04 PM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/QualityData/Attribute/JsAttribute.js"></script>
        <script type="text/javascript" src="js/QualityData/Attribute/JsAttribute.js"></script>
        <title>VisualBiTool(Tabla de Datos)</title>
    </head>
    <body style="border-color: chartreuse;background-color:#cde69c;">
        <%
            HttpSession sesion = request.getSession();
            out.println("<center>");
            out.println((String)sesion.getAttribute("reportAttribute"));
            out.println("<input type='button'  value = 'Descargar' onclick='funQdDownloadReport()'>");
            out.println("</center>");
        %>
    </body>
</html>
