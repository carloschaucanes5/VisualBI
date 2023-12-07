<%-- 
    Document   : dmRulesParameters
    Created on : Oct 14, 2014, 6:22:58 PM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Parámatros)</title>
                <script>
            function funEditR()
            {
                var checkFactor=document.getElementById("idCheckFactor");
                var NumberRules=document.getElementById("idNumberRules");
                var MinMetric=document.getElementById("idMinMetric");
                var LowerBoundMinSupport=document.getElementById("idLowerBoundMinSupport");
                var UpperBoundMinSupport=document.getElementById("idUpperBoundMinSupport");
                if(checkFactor.checked==true)
                  {
                        NumberRules.readOnly=false;
                        MinMetric.readOnly=false;
                        LowerBoundMinSupport.readOnly=false;
                        UpperBoundMinSupport.readOnly=false;
                  }
                  else
                  {
                        NumberRules.readOnly=true;
                        MinMetric.readOnly=true;
                        LowerBoundMinSupport.readOnly=true;
                        UpperBoundMinSupport.readOnly=true;
                  }
            }
        </script>
    </head>
    <body>
        <input type="button" class="btn btn-sm dark" value="Close" style="position: absolute;right: 10px;top: 60px" onclick="closeTab(6)"/>
        <div class="caption"><i class="fa fa-edit"></i><a style="color: red;">Reglas de asociación</a><hr title="Parameters"></div>
        <table>
            <tr><td>Número de reglas</td><td><input type="text" id="idNumberRules" value="10"  class="spinner-input form-control" maxlength="4" readonly="true"/></td></tr>
            <tr><td>Metrica mínima</td><td><input type="text" id="idMinMetric" value="0.9" class="spinner-input form-control" maxlength="4" readonly="true"/></td></tr>
            <tr><td>Soporte limite inferior </td><td><input type="text" id="idLowerBoundMinSupport"   value="0.1" class="spinner-input form-control" maxlength="4" readonly="true"/></td></tr>
            <tr><td>Soporte limite superior </td><td><input type="text" id="idUpperBoundMinSupport"   value="1.0" class="spinner-input form-control" maxlength="4" readonly="true"/></td></tr>
            <tr><td><input type="button" Value="Ejecutar" onclick="funDmProcessApriori()" class="btn btn-primary"/></td><td><input type="checkbox" id="idCheckFactor" onchange="funEditR()" value="idConfidenceFactor">Editar</td></tr>
            <!---------------------------------------------------------------------------->
        </table>
        <br/>
        <div id="divResultApriori"></div>
    </body>
</html>

