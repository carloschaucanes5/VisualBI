<%-- 
    Document   : mdTreeProcesarParametros
    Created on : 15/08/2014, 11:58:35 AM
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
                var checkFactor=document.getElementById("checkConfidenceFactor");
                var editFactor=document.getElementById("idConfidenceFactor");
                var editMinObj=document.getElementById("idNumMinObj");
                var editFolds=document.getElementById("idNumFolds");
                var editSeed=document.getElementById("idSeed");
                if(checkFactor.checked==true)
                  {
                        editFactor.readOnly=false;
                        editFolds.readOnly=false;
                        editMinObj.readOnly=false;
                        editSeed.readOnly=false;
                  }
                  else
                  {
                        editFactor.readOnly=true;
                        editFolds.readOnly=true;
                        editMinObj.readOnly=true;
                        editSeed.readOnly=true;
                  }
            }
        </script>
    </head>
    <body>
       <input type="button" class="btn btn-sm dark" value="Cerrar" style="position: absolute;right: 10px;top: 60px" onclick="closeTab(4)"/>
       
        <div class="caption"><i class="fa fa-edit"></i><a style="color: red;">Árboles de decisión</a><hr title="Parameters" style="text-indent:'HOLA'"></div>
        <table>
            <tr><td>Factor de confianza</td><td><input type="text" id="idConfidenceFactor" value="0.25"  class="spinner-input form-control" maxlength="4" readonly="true"/></td></tr>
            <tr><td>Número mínimo de objetos</td><td><input type="text" id="idNumMinObj" value="2" class="spinner-input form-control" maxlength="4" readonly="true"/></td></tr>
            <tr><td>Número de pliegues</td><td><input type="text" id="idNumFolds"   value="3" class="spinner-input form-control" maxlength="4" readonly="true"/></td></tr>
             <tr><td>Semilla</td><td><input type="text" id="idSeed"   value="1" class="spinner-input form-control" maxlength="4" readonly="true"/></td></tr>
            <tr><td><input type="button" Value="Ejecutar" onclick="funDmProcessJ48()" class="btn btn-primary"/></td><td><input type="checkbox" id="checkConfidenceFactor" onchange="funEdit()" value="idConfidenceFactor">Editar</td></tr>
        </table> 
        <br/>
        <div id="divResultJ48"></div>
    </body>
</html>
