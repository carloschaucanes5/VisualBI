<%-- 
    Document   : dmClusterProcessKmeans
    Created on : Oct 8, 2014, 11:57:42 PM
    Author     : Carlitos
--%>

<%@page import="DataMining.Clustering.BuildFormatJsonCluster"%>
<%@page import="DataMining.Clustering.ListCentroides"%>
<%@page import="DataMining.Clustering.TableInstance"%>
<%@page import="File.Upload.WriteFile"%>
<%@page import="File.Upload.InstancesFile"%>
<%@page import="weka.core.Instance"%>
<%@page import="weka.clusterers.SimpleKMeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Vista de grupos)</title>
    </head>
    <body>
        <%
            HttpSession sesion=request.getSession();
            if(sesion.getAttribute("alKmean")!= null)
             {
               String path = (String)request.getParameter("path");
               String name = (String)request.getParameter("name");
               int  limitBottom = Integer.parseInt((String)request.getParameter("limitBottom"));
               int  limitTop = Integer.parseInt((String)request.getParameter("limitTop"));
               int  numInstances = Integer.parseInt((String)request.getParameter("numInstances"));
               SimpleKMeans kmeans = (SimpleKMeans)sesion.getAttribute("alKmean");
               InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
               if(inf.processingData()==true)
               {
                  //-----
                  if(sesion.getAttribute("alKmean")!= null)
                   {
                        ListCentroides lisCes = new ListCentroides(kmeans, inf.getData(),limitBottom,limitTop);
                        if(lisCes.processLists() ==true)
                        {
                            BuildFormatJsonCluster bfj = new BuildFormatJsonCluster(lisCes.getListIndexCen(),inf.getData(),kmeans , numInstances);
                            if(bfj.buildFormat() == true)
                             {
                                WriteFile wf = new WriteFile(path+"/files/clusters.json", bfj.getFormat());
                                if(wf.write() == true)
                                {
                                    Thread.sleep(2000);
                                    String selectClusters = "";
                                    selectClusters+="<select id='cluster' onchange='funDmSelectClusters()'>";
                                    selectClusters+="<option value='-1'>Select</option>";
                                    for(int j=0;j<kmeans.numberOfClusters();j++)
                                    {
                                        selectClusters+="<option value='"+j+"'>Cluster"+j+"</option>";
                                    }
                                    selectClusters+="</select>";
                                    sesion.setAttribute("seTitle",inf.getData().relationName());
                                    sesion.setAttribute("seSelect",selectClusters);
                                    sesion.setAttribute("sePath",path);
                                    sesion.setAttribute("seName",name);
                                    String html = "<br/>";
                                    html+="<table style='text-align:center'>";
                                    html+="<tr><th>Parametros</th><th>Grafico de Burbujas</th><th>Arbol de Mapa</th></tr>";
                                    html+="<tr>";
                                    html+="<td>";
                                    html+="<table>";
                                    html+="<tr><td>Limite inferior</td><td><input type='text' min='0' max='"+(inf.getData().numInstances()-1)+"'  value='0' id='limitBottom' name='limitBottom' class='form-control'/></td></tr>";
                                    html+="<tr><td>Limite superior</td><td><input type='text' min='1' max='"+inf.getData().numInstances()+"'  value='20'id='limitTop'  name='limitTop' class='form-control'/></td></tr>";
                                    html+="<tr><td>Num.Instances/Cluster</td><td><input type='text' min='1' max='"+inf.getData().numInstances()+"'  value='20'id='numInstances' name='numInstances'  class='form-control'/></td></tr>";
                                    html+="<tr><td><input type='button' value='Procesar Instancias' onclick='funDmProcessInstances()' class='btn btn-primary'/></td><td></td></tr>";
                                    html+="</table>";
                                    html+="</td>";
                                    html+="<td><img src='images/DataMining/Clustering/bubbleChart.jpg' onclick='funDmViewGraphics(1)'/></td>";
                                    html+="<td><img src='images/DataMining/Clustering/treeMapChart.png' onclick='funDmViewGraphics(2)' /></td>";
                                    html+="</tr>";
                                    html+="</table>";
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
                            out.println(lisCes.getExceptions());
                        }
                   }
                  else
                   {
                      out.println("There are not data...Try most later");
                   }
                  //------------
               }
               else
               {
                   out.println(inf.getException());
               }
             }
             else
             {
                out.println("Error:There is not object kmeans");
             }
        %>
    </body>
</html>
