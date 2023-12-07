<%-- 
    Document   : dmClusterDownloadTable
    Created on : 3/01/2015, 11:14:58 AM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Descargas)</title>
    </head>
    <body>
        <%
            HttpSession sesion=request.getSession();
            if(sesion.getAttribute("reportTableClusters")!=null)
              {
                response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.
                response.setHeader ("Content-Disposition", "attachment;filename=\"tableClusters.xls\"");
                out.println((String)sesion.getAttribute("reportTableClusters"));
              }
             else
              {
                out.println("Opcion invalida..Intenta nuevamente el proceso");
                return;
              }      
        %>
    </body>
</html>
