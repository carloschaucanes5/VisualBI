<%-- 
    Document   : qdDataProcessInstances
    Created on : 10/12/2014, 10:02:11 PM
    Author     : Carlitos
--%>

<%@page import="QualityData.Data.ValuesAttribute"%>
<%@page import="File.Upload.WriteFile"%>
<%@page import="File.Upload.InstancesFile"%>
<%@page import="QualityData.Data.FormatScatterPlot"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Instancias)</title>
    </head>
    <body>
        <%
        HttpSession sesion=request.getSession();
        String path = (String)request.getParameter("path");
        String name = (String)request.getParameter("name");
        int top = Integer.parseInt((String)request.getParameter("top"));
        int bottom = Integer.parseInt((String)request.getParameter("bottom"));
        int attribute1 = Integer.parseInt((String)request.getParameter("attribute1"));
        int attribute2 = Integer.parseInt((String)request.getParameter("attribute2"));
        int attribute3 = Integer.parseInt((String)request.getParameter("attribute3"));
        //out.println("atributo1=>"+attribute1+"atributo2=>"+attribute2+"atributo3=>"+attribute3);
        try
         {
            InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
            if(inf.processingData() == true)
             {
                FormatScatterPlot fsp = new FormatScatterPlot(inf.getData(), "");
                fsp.buildFormatScatterPlot(attribute1, attribute2, attribute3,top,bottom);
                //out.println(fsp.getFormatScatterPlot());
                WriteFile wf = new WriteFile(path+"/files/cereal.csv",fsp.getFormatScatterPlot() );
                WriteFile wfa = new WriteFile(path+"/js/QualityData/Data/axes.js",fsp.getAxes() );
                if(wf.write() == true && wfa.write() == true)
                 {
                   Thread.sleep(1000);
                   ValuesAttribute va1 = new ValuesAttribute(inf.getData(), attribute1,1);
                   ValuesAttribute va2 = new ValuesAttribute(inf.getData(), attribute2,2);
                   ValuesAttribute va3 = new ValuesAttribute(inf.getData(), attribute3,2);
                   if(va1.processValues() == true && va2.processValues() == true && va3.processValues() == true)
                     {
                       sesion.setAttribute("relation",inf.getData().relationName());
                       sesion.setAttribute("valuesAttribute1", va1.getValuesCodes());
                       sesion.setAttribute("valuesAttribute2", va2.getValuesCodes());
                       sesion.setAttribute("valuesAttribute3", va3.getValuesCodes());
                       sesion.setAttribute("path", path);
                       sesion.setAttribute("name", name);
                       //out.println("<input type='button' value ='Ver Grafico' onclick='funQdViewGraphic()'/>");
                       out.println("<div id='imageArrow3'>");
                       out.println("<h4 id='titleRemove'>Gráfico de Dispersión</h4>");
                       out.println("<img src='images/QualityData/ScatterPlotChart.jpg'  onclick='funQdViewGraphic()'/>");
                       out.println("</div>");
                       Thread.sleep(2000);  
                   }
                    else
                     {
                       out.println("Error al procesar los valores");
                     }
                 }
                else
                 {
                    out.println(wf.getExceptions());
                 }
             }
            else
             {
                out.println(inf.getException());
             }
         }
         catch(Exception err)
         {
             out.println(err.toString());
         }
        
        %>
    </body>
</html>
