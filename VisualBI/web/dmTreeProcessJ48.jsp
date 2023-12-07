<%-- 
    Document   : dmTreeProcessJ48
    Created on : Oct 12, 2014, 9:04:32 PM
    Author     : Carlitos
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="File.Upload.WriteFile"%>
<%@page import="DataMining.Tree.BuildFormatJson"%>
<%@page import="DataMining.Tree.ClassJ48"%>
<%@page import="File.Upload.InstancesFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/tree/tree.css"/>
        <title>VisualBiTool(J48)</title>
    </head>
    <body>
        <%
           HttpSession sesion=request.getSession();
           String path =  (String)request.getParameter("path");
           String name =  (String)request.getParameter("name");
           float confidenceFactor =  Float.parseFloat((String)request.getParameter("idConfidenceFactor"));
           int   numMinObj =  Integer.parseInt((String)request.getParameter("idNumMinObj"));
           int  numFolds =  Integer.parseInt((String)request.getParameter("idNumFolds"));
           int  seed =  Integer.parseInt((String)request.getParameter("idSeed"));
           InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
           if(inf.processingData()==true)
           {
               ClassJ48 cj48 = new ClassJ48(inf.getData(), confidenceFactor, numMinObj, numFolds, seed);
               if(cj48.processJ48()==true)
               {
                   BuildFormatJson bfj = new BuildFormatJson(cj48.getListObjetosJson(), cj48.getListRelations());
                   if(bfj.buildFormat()==true)
                   {
                       WriteFile wf = new WriteFile(path+"/files/flare.json", bfj.getJsonFormat());
                       if(wf.write()==true)
                        {
                            //out.println(cj48.getTableFeatures()+"<br/>");
                            sesion.setAttribute("titleTree", inf.getData().relationName());
                            String html="";
                            html+="<table>";
                            html+="<tr><th>Árbol Jerárquico</th><th>Árbol de Sangría</th></tr>";
                            html+="<tr><th><img src='images/DataMining/tree/hierarchyTreeChart.JPG' onclick='funDMViewTree(2)'/></th><th><img src='images/DataMining/tree/sangriaTreeChart.jpg' onclick='funDMViewTree(1)'/></th></tr>";
                            html+="</table>";
                            //html+="<div class='divTree'>";
                            //html+="<img src='images/DataMining/tree/hierarchyTreeChart.JPG' onclick='funDMViewTree(2)'/>";
                            out.println(html);
                        }
                       else
                        {
                           out.println(wf.getExceptions());
                        }
                   }
                   else
                   {
                       out.println(bfj.getExceptions());
                   }
               }
               else
               {
                   out.println("<center>"+inf.getException()+": Atributos no validos para operaciones de clasificación</center>");
               }
           }
           else
           {
               out.println(inf.getException());
           }
        %>
    </body>
</html>
