<%-- 
    Document   : qdDataRemoveAttribute
    Created on : 18/12/2014, 10:59:53 PM
    Author     : Carlitos
--%>

<%@page import="weka.core.Instances"%>
<%@page import="File.Upload.InstancesFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/QualityData/Data/removeAttribute.css">
        <script type="text/javascript" src="js/QualityData/Data/removeAttribute.js"></script> 
        <title>VisualBiTool(Eliminar Atributos)</title>
    </head>
    <body>
        <%
         String select1 = "",select2 = "", select3 = "",options1 = "",options2 = "";
         int numIns =0;
         Instances inss = null;
         String html = "";
         String path = (String)request.getParameter("path");
         String name = (String)request.getParameter("name");
         InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
         html="<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>";
         out.println("<h4 id='titleRemove'>Selección de atributos</h4>");
         html +="<div id = 'divRemove'>";
         html += "<table class='table table-striped table-bordered table-hover'>";
         if(inf.processingData() == true)
         {
            for(int j=0;j<inf.getData().numAttributes();j++)
            {
                html+="<tr><td><input type='checkbox' value='"+(j+1)+"' name = 'removeA' id='idR"+(j+1)+"'></td><td>"+inf.getData().attribute(j).name()+"</td></tr>";
            } 
             numIns = inf.getData().numInstances();
             inss = inf.getData();
         }
         html += "</table class='table table-striped table-bordered table-hover'>";
         html +="</div><br/><br/><br/>";
         html +="<input type = 'button' value='Todo' class='btn blue' onclick='funDataSelectAll("+inf.getData().numAttributes()+")'/>";
         html +="<input type = 'button' value='Ninguno' class='btn red' onclick='funDataSelectNone("+inf.getData().numAttributes()+")'/>";
         html +="<input type = 'button' value='Eliminar' class='btn green' onclick='funDataRemove("+inf.getData().numAttributes()+")'/>";
         out.println(html);
        //out.println("<img src='images/QualityData/ArrowData1.png' id='imageArrow1'/>");
        options1 += "<option value='-2'>Seleccionar</option>";
        options1 += "<option value='-1'>Num.Instancia</option>";
        options2 += "<option value='-2'>Seleccionar</option>"; 
        for(int j=0;j<inss.numAttributes();j++)
         {
            if(inss.attribute(j).isNominal() == true)
             {
                options2 += "<option value='"+j+"'>"+inss.attribute(j).name() +"</option>";
             }
            options1 += "<option value='"+j+"'>"+inss.attribute(j).name() +"</option>";
         }
        select1 ="<select id='idAttribute1' class='btn blue'>"+options1+"</select>";
        select2 ="<select id='idAttribute2' class='btn red'>"+options1+"</select>";
        select3 ="<select id='idAttribute3' class='btn green'>"+options2+"</select>";
        
        %>
        <div id="divParameters">
            <h4 id='titleRemove'>Distribucion de Datos</h4>
            <table>
                <tr><th>Limite Inferior</th><th><input type="number"   value="0" id="idBottom" min="0" max="<%=(numIns-1)%>" /></th></tr>
                <tr><th>Limite Superior</th><th><input type="number" value="<%=(numIns)%>" id="idTop"  min="1" max="<%=(numIns)%>" /></th></tr>
                <tr><th>Atributo1</th><th><%= select1 %></th></tr>
                <tr><th>Atributo2</th><th><%= select2 %></th></tr>
                <tr><th>Atributo3</th><th><%= select3 %></th></tr>
                <tr><th><input type="button" value="Procesar" class="btn yellow" onclick ="funQdProcessLimits()"></th><td></td></td></tr>
            </table>
        </div>
        <!--img src='images/QualityData/ArrowData1.png' id='imageArrow2'/-->
        <br/><br/>
        <div id="divScatterPlot"></div>
        
        
        
    </body>
</html>
