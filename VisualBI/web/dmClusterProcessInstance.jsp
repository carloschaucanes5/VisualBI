<%-- 
    Document   : dmClusterProcessInstance
    Created on : Oct 9, 2014, 5:22:55 PM
    Author     : Carlitos
--%>

<%@page import="weka.clusterers.SimpleKMeans"%>
<%@page import="DataMining.Clustering.TableInstance"%>
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
            HttpSession sesion=request.getSession();
            if(sesion.getAttribute("seName")!= null && sesion.getAttribute("sePath")!= null && sesion.getAttribute("alKmean")!= null)
            {
                SimpleKMeans kmeans = (SimpleKMeans)sesion.getAttribute("alKmean");
                String path = (String)sesion.getAttribute("sePath");
                String name = (String)sesion.getAttribute("seName");
                int indexInstance = Integer.parseInt((String)request.getParameter("indexInstance"));
                InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
                if(inf.processingData()==true)
                {
                    TableInstance ti = new TableInstance(inf.getData(), indexInstance,kmeans);
                    if(ti.proccessTable()==true)
                    {
                        out.println(ti.getTable());
                    }
                    else
                    {
                        out.println(ti.getExceptions());
                    }
                }
                else
                {
                    out.println(inf.getException());
                }
            }
            else
            {
                out.println("Error:No se ha especificado el directorio del archivo. Intenta nuevamente");
            }
        %>
    </body>
</html>
