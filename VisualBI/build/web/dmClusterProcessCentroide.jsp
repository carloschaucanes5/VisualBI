<%-- 
    Document   : dmClusterProcessCentroide
    Created on : Oct 20, 2014, 7:58:30 PM
    Author     : Carlitos
--%>

<%@page import="DataMining.Clustering.TableInstance"%>
<%@page import="weka.clusterers.SimpleKMeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Centroide)</title>
    </head>
    <body>
        <%
            HttpSession sesion=request.getSession();
            if(sesion.getAttribute("alKmean")!= null)
            {
                SimpleKMeans kmeans = (SimpleKMeans)sesion.getAttribute("alKmean");
                int indexInstance = Integer.parseInt((String)request.getParameter("indexInstance"));
                TableInstance ti = new TableInstance(kmeans.getClusterCentroids(), indexInstance, null);
                if(ti.proccessTableCentroide()==true)
                 {
                    out.println(ti.getTable());
                 }
                 else
                 {
                    out.println(ti.getExceptions());
                 }
            }
            else
            {
                out.println("Error:No hay datos");
            }
        %>
    </body>
</html>
