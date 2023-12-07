<%-- 
    Document   : dwhConectar
    Created on : 10/09/2014, 12:45:44 PM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Parametros)</title>
                <script>
            function funEdit()
            {
                var check=document.getElementById("idEdit");
                var idHost=document.getElementById("idHost");
                var idPort=document.getElementById("idPort");
                var idDataBase=document.getElementById("idDataBase");
                var idUser=document.getElementById("idUser");
                var idPassword=document.getElementById("idPassword");
                if(check.checked==true)
                  {
                        idHost.readOnly = false;
                        idPort.readOnly = false;
                        idDataBase.readOnly = false;
                        idUser.readOnly = false;
                        idPassword.readOnly = false;
                  }
                  else
                  {
                        idHost.readOnly = true;
                        idPort.readOnly = true;
                        idDataBase.readOnly  = true;
                        idUser.readOnly = true;
                        idPassword.readOnly = true;
                  }
            }
        </script>
    </head>
    <body>
        <input type="button" class="btn btn-sm dark" value="Cerrar" style="position: absolute;right: 10px;top: 60px" onclick="closeTab(7)"/>
        <div>
            <center>
                <h4>PostgreSQL</h4>
                <hr/>
        <table  style="width: 400px" class='table table-striped table-hover table-bordered' id='sample_editable_1'>
 <%
        String name="",htmlTable="";
            try
            {
                name = (String)request.getParameter("name");
                htmlTable="<tr><th>Esquema</th><td>"+name+"</td></tr>";
                out.println(htmlTable);
            }
            catch(Exception error)
            {
                out.println(error.toString());
            }
%>
            <tr><th>Servidor</th><td><input type="text" id="idHost" value="127.0.0.1" class="spinner-input form-control" maxlength="100" readonly="true"/></td></tr>
            <tr><th>Puerto</th><td><input type="text" id="idPort" value="5432" class="spinner-input form-control" maxlength="10" readonly="true"/></td></tr>
            <tr><th>Base de Datos</th><td><input type="text" id="idDataBase" value="dwh" class="spinner-input form-control" maxlength="50" readonly="true"/></td></tr>
            <tr><th>Usuario</th><td><input type="text" id="idUser" value ="postgres" class="spinner-input form-control" maxlength="50" readonly="true"/></td></tr>
            <tr><th>Contraseña</th><td><input type="password" id="idPassword" value="postgres1" class="spinner-input form-control" maxlength="50" readonly="true"/></td></tr>
            <tr><td><input type="button" Value="Conectar" onclick="funDwhConnect()" class="btn btn-primary"/></td><td><input type="checkbox" id="idEdit" onchange="funEdit()" value="idConfidenceFactor">Editar</td></tr>
        </table>
            </center>
        </div>
            <div id="resultConnection"></div>
        </body>
</html>