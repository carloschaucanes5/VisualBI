<%-- 
    Document   : cdProcesarAtributosCaracteristicas
    Created on : 9/08/2014, 01:12:21 PM
    Author     : Carlitos
--%>

<%@page import="File.Upload.InstancesFile"%>
<%@page import="QualityData.Attribute.AttributeProcess"%>
<%@page import="File.Upload.WriteFile"%>
<%@page import="QualityData.Attribute.AttributeInformation"%>
<%@page import="java.util.ListIterator"%>
<%@page import="java.util.List"%>
<%@page import="QualityData.Attribute.StatisticalValues"%>
<%@page import="weka.core.Attribute"%>
<%@page import="java.util.Enumeration"%>
<%@page import="weka.core.Instances"%>
<%@page import="weka.core.Instance"%>
<%@ page import="java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attributes</title>
         <!--script type="text/javascript" src="js/QualityData/JsQualityData.js"></script-->
        <style type="text/css">
        #fig {
                width: 800px;
                height: 800px;
             }
        </style>
    </head>
    <body>
        <input type="button" class="btn btn-sm dark" value="Cerrar" style="position: absolute;right: 10px;top: 60px" onclick="closeTab(2)"/>
        <%             
           HttpSession sesion=request.getSession();
           
           String path = (String)request.getParameter("path");
           String name = (String)request.getParameter("name");
           String htmlLA="",table="",format="";
           Attribute attrG=null;
           InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
           if(inf.processingData()==true)
            {
                //data = DataFile.getData();------------------------------------
               htmlLA+="<center><select size='8' style='width:600px' id='idSelectAttribute' onchange='funSelectAttribute()'>";
               Enumeration<Attribute> enA = inf.getData().enumerateAttributes();
               int n=0;
               while(enA.hasMoreElements()==true)
               {  
                   Attribute attr = enA.nextElement();
                   String nameAttribute=attr.name();
                   if(n==0)
                   { 
                       sesion.setAttribute("seAttribute",nameAttribute);
                       attrG = attr;
                       htmlLA+="<option value='"+nameAttribute+"' selected>"+nameAttribute;
                    }
                    else
                    {
                       htmlLA+="<option value='"+nameAttribute+"'>"+nameAttribute;
                    }
                   n++;
                }
               if(attrG!=null)
                {
                   StatisticalValues sv = new StatisticalValues(inf.getData(),attrG.name());
                   sv.calculateStatisticalValues();
                   //---------------------------------------
                   htmlLA+="</select></center>";
                   out.println("<center><img src='images/QualityData/titleAttribute.png'/></center>");
                   out.println(htmlLA+"");
                   WriteFile wf = null;
                   wf = new WriteFile(path+"/js/QualityData/Attribute/LibraryChart/datosDistribucionNormal.js", sv.getNumericFormat());
                   wf.write();      
                   wf = new WriteFile(path+"/js/QualityData/Attribute/LibraryInfovis/datos.js", sv.getNominalFormat());
                   wf.write();
                   Thread.sleep(1000);
                    out.println("<div id='idAttributeResult'>");
                    out.println("<table class='table table-striped table-hover table-bordered'>");
                    out.println("<td colspan='4'><center><h4 style='color:blue;'>"+attrG.name()+"</h4></center></td>");
                    out.println("<tr>");
                    if(attrG.isNominal() == true)
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
                    sesion.setAttribute("reportAttribute", sv.getHtmlResultsTable());
                    //out.println("<br/><br/><br/><br/><br><br><br><div id='idResultAttribute'><center>"+sv.getHtmlResultsTable()+"</center></br></div>");
                    out.println("</div>");
                }
               else
                {
                   out.println("Invalid Attribute name");
                }                     
            }
            else
            {
                out.println(inf.getException());
            }
        %>
    </body>
</html>
