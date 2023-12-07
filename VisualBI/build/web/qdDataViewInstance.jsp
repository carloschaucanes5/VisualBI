<%-- 
    Document   : qdDataViewInstance
    Created on : 20/12/2014, 06:01:40 PM
    Author     : Carlitos
--%>

<%@page import="QualityData.Data.ViewInstance"%>
<%@page import="File.Upload.InstancesFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Instancia)</title>
    </head>
    <body>
        <%
        String path = (String)request.getParameter("path");
        String name = (String)request.getParameter("name");
        int indexInstance = Integer.parseInt((String)request.getParameter("indexInstance"));
        out.println("<center>Instancia No: "+ indexInstance+"</center><br/>");
        InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
        if(inf.processingData() == true)
        {
            ViewInstance vi = new ViewInstance(inf.getData(), indexInstance);
            if(vi.proccessInstances() == true)
            {
                out.println(vi.getTableInstance());
            }
            else
            {
                out.println(vi.getExceptions());
            }                  
        }
        else
        {
            out.println(inf.getException());
        }
        %>
    </body>
</html>
