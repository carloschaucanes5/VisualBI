<%-- 
    Document   : dmClusterParameters
    Created on : Oct 8, 2014, 11:36:20 PM
    Author     : Carlitos
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Parametros-agrupamiento)</title>
    </head>
    <body>
    <input type="button" class="btn btn-sm dark" value="Cerrar" style="position: absolute;right: 10px;top: 60px" onclick="closeTab(5)"/>
    <center>
        <table>
            <tr><td>Numero de Grupos</td><td><input type="text" id="idNumClusters" class="form-control"/></td></tr>
            <tr><td><input type="button" Value="Ejecutar" onclick="funDmProcessKmean()" class="btn btn-primary"/></td><td></td></tr>
        </table>
        <hr/>
    </center>
    <div id="divTableClusters"></div>
    </body>
</html>
