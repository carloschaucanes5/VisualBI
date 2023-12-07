<%-- 
    Document   : qdDataProcessRemove
    Created on : 19/12/2014, 08:15:51 PM
    Author     : Carlitos
--%>

<%@page import="File.Upload.WriteFile"%>
<%@page import="QualityData.Data.RemoveAttribute"%>
<%@page import="File.Upload.InstancesFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Seleccion Eliminar atributos)</title>
    </head>
    <body>
        <%
            String path = (String)request.getParameter("path");
            String name = (String)request.getParameter("name");
            String itemsRemove = (String)request.getParameter("itemsRemove");
            InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
            if(inf.processingData() == true)
             {
                RemoveAttribute ra = new RemoveAttribute(inf.getData(), itemsRemove);
                if(ra.processRemove() == true)
                 {
                    ra.getNewData().setRelationName(inf.getData().relationName());
                    WriteFile wf = new WriteFile(path+"/uploads/"+name,ra.getNewData().toString());
                    if(wf.write() == true)
                      {
                        Thread.sleep(2000);
                        out.println(itemsRemove.split(",").length+" Atributo(s) se ha(n) eliminado");
                      }
                     else
                      {
                        out.println(wf.getExceptions());
                      }
                 }
                else
                 { 
                    out.println(ra.getExceptions());
                 }
             }
            else
             {
                out.println(inf.getException());
             }
        %>
    </body>
</html>
