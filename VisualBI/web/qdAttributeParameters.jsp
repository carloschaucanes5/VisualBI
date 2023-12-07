<%-- 
    Document   : qdAttribute
    Created on : Sep 29, 2014, 3:10:28 PM
    Author     : Carlitos
--%>

<%@page import="File.Upload.InstancesFile"%>
<%@page import="QualityData.Attribute.AttributeProcess"%>
<%@page import="File.Upload.WriteFile"%>
<%@page import="java.util.ListIterator"%>
<%@page import="weka.core.Attribute"%>
<%@page import="QualityData.Attribute.StatisticalValues"%>
<%@page import="java.util.Enumeration"%>
<%@page import="weka.core.Instances"%>
<%@page import="QualityData.Attribute.AttributeInformation"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Parametros)</title>
    </head>
        <body>
        <%     
           HttpSession sesion=request.getSession();
           String path = (String)request.getParameter("path");
           String name = (String)request.getParameter("name");
           String attributeName = (String)request.getParameter("attribute");
           sesion.setAttribute("seAttribute",attributeName);
           InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
           if(inf.processingData()==true)
            {
               Enumeration<Attribute> enA = inf.getData().enumerateAttributes();
               if(attributeName.compareTo("")!=0)
                {
                   StatisticalValues sv = new StatisticalValues(inf.getData(),attributeName.trim());
                   sv.calculateStatisticalValues();
                   int codGraphic = -1;

                        WriteFile wf = null;
                        wf = new WriteFile(path+"/js/QualityData/Attribute/LibraryChart/datosDistribucionNormal.js", sv.getNumericFormat());
                        wf.write();      
                        wf = new WriteFile(path+"/js/QualityData/Attribute/LibraryInfovis/datos.js", sv.getNominalFormat());
                        wf.write();
                        Thread.sleep(2000);
                        //class='table table-striped table-hover table-bordered' id='sample_editable_1'
                        out.println("<table class='table table-striped table-hover table-bordered'>");
                        out.println("<td colspan='4'><center><h4 style='color:blue;'>"+attributeName+"</h4></center></td>");
                        out.println("<tr>");
                        if(inf.getData().attribute(attributeName.trim()).isNominal() == true)
                        {
                            out.println("<tr><th>Tabla de datos</th><th>Gráfico de Barras</th><th>Gráfico de circulo</th></tr>");
                            out.println("<td><img  src='images/QualityData/tableChart.png' onclick='funViewGraphic(0)'></img></td>");
                            out.println("<td><img  src='images/QualityData/barChart.png' onclick='funViewGraphic(2)'></img></td>");
                            out.println("<td><img  src='images/QualityData/pieChart.png' onclick='funViewGraphic(3)'></img></td>");
                        }
                        else
                        {
                            out.println("<tr><th>Tabla de datos</th><th>Gráfico de área</th><th>Gráfico de Barras</th><th>Gráfico de circulo</th></tr>");
                            out.println("<td><img  src='images/QualityData/tableChart.png' onclick='funViewGraphic(0)'></img></td>");
                            out.println("<td><img  src='images/QualityData/lineChart.png' onclick='funViewGraphic(1)'></img></td>");
                            out.println("<td><img  src='images/QualityData/barChart.png' onclick='funViewGraphic(2)'></img></td>");
                            out.println("<td><img  src='images/QualityData/pieChart.png' onclick='funViewGraphic(3)'></img></td>");
                        }
                        //out.println("<td><img  src='images/QualityData/tableChart.png' onclick='funViewGraphic(0)'></img></td>");
                        //out.println("<td><img  src='images/QualityData/lineChart.png' onclick='funViewGraphic(1)'></img></td>");
                        //out.println("<td><img  src='images/QualityData/barChart.png' onclick='funViewGraphic(2)'></img></td>");
                        //out.println("<td><img  src='images/QualityData/pieChart.png' onclick='funViewGraphic(3)'></img></td>");
                        out.println("</tr>");
                        out.println("</table");
                        out.println("<img style='position:absolute;top:300px;left:200px' src='images/QualityData/pieChart.png' onclick='funViewGraphic(2)'></img><br/>");
                        //out.println("<div id='idResultAttribute'><center>"+sv.getHtmlResultsTable()+"</center></br></div>");  
                        sesion.setAttribute("reportAttribute", sv.getHtmlResultsTable());
               }
               else
                {
                   out.println("Nombre de atributo invalido");
                }                     
            }
            else
            {
                out.println(inf.getException());
            }
           
        %>
    </body>
</html>
