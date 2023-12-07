<%-- 
    Document   : dwhEstablishConnection
    Created on : Oct 21, 2014, 7:16:33 PM
    Author     : Carlitos
--%>

<%@page import="Dwh.Connection.ClassConnection"%>
<%@page import="org.olap4j.OlapConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Conexión)</title>
    </head>
    <body>
       <%
            String cadCon = "";
            try
            {
                HttpSession sesion=request.getSession();
                cadCon = (String)request.getParameter("cadCon");
                String[] vecCon = cadCon.split("=>");
                ClassConnection cc = new ClassConnection(vecCon[0].trim(), vecCon[1].trim(),vecCon[2].trim() , vecCon[3].trim(), vecCon[4].trim(), vecCon[5].trim());
                if(cc.processConnection() == true)
                {
                   OlapConnection con = cc.getOlapConnection();
                   sesion.setAttribute("seConnectionOlap",con);
                   out.println("Conexion exitosa");
                }
                else
                {
                    out.println(cc.getExceptions());
                }
            }
            catch(Exception error)
            {
                out.println(error.toString());
            }
        %>
    </body>
</html>
