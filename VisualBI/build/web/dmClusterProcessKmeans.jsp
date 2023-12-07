<%-- 
    Document   : dmClusterProcessKmeans
    Created on : 2/01/2015, 03:46:21 PM
    Author     : Carlitos
--%>

<%@page import="DataMining.Clustering.TableCentroides"%>
<%@page import="DataMining.Clustering.KMean"%>
<%@page import="weka.core.Instances"%>
<%@page import="QualityData.Data.ProcessRankTable"%>
<%@page import="File.Upload.InstancesFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/DataMining/Clustering/clustering.js"></script>
        <title>VisualBiTool(Kmeans)</title>
         <script>
            function funCheck()
            {
                var check=document.getElementById("checkTable");

                if(check.checked==true)
                  {
                      $('#table-clusters').show();
                  }
                  else
                  {
                      $('#table-clusters').hide();
                  }
            }
        </script>
    </head>
<body>
    <%
        HttpSession sesion=request.getSession();
    try
    {
        int numClusters = Integer.parseInt((String)request.getParameter("numClusters"));
        String path = (String)request.getParameter("path");
        String name = (String)request.getParameter("name");
        InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
        if(inf.processingData()==true)
        {
            KMean km = new KMean(numClusters, inf.getData());
            if(km.processKmeans()==true)
            {
                sesion.setAttribute("alKmean",km.getKmeans());
                TableCentroides ce = new TableCentroides(inf.getData(), km.getKmeans());
                if(ce.processTableCentroides()==true)
                 {
                    sesion.setAttribute("reportTableClusters", ce.getTableCentroides());
                    out.println("<center><h3 style='color:blue'>Visualización de  Grupos</h3></center>");
                    //---------------------------------------------------------
                          String html = "<div id='divGraphicImages'>";
                           html+= "<br/>";
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
                           html+="<td><img src='images/DataMining/Clustering/bubbleChart.jpg' onclick='funDmViewWarning()'/></td>";
                           html+="<td><img src='images/DataMining/Clustering/treeMapChart.png' onclick='funDmViewWarning()' /></td>";
                           html+="</tr>";
                           html+="</table>";
                           html+="</div>";
                           html+="<center><br/><input type='checkbox' id='checkTable' onchange='funCheck()'/><b>Ver Tabla de Grupos</b></center><hr/>";
                           out.println(html);
                    //---------------------------------------------------------

    %>
    <div id='table-clusters' style='display: none'>
	<div>
		<div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
                                                    <div class="caption"><i class="fa fa-edit"></i>Tabla de grupos</div>
							<div class="tools">
								<!--a href="javascript:;" class="remove"></a-->
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="btn-group">
								</div>
							</div>
                                                    
<%

                                                            out.println(ce.getTableCentroides());
                                                            out.println("<center><a href='dmClusterDownloadTable.jsp' class='btn green btn-xs' >Descargar</a></center>");
                                                            
%>

						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			<!-- END PAGE CONTENT -->
		</div>
		<!-- END PAGE -->
	</div>
    </div>
    <%
                     }
                 else
                 {
                    out.println(ce.getExceptions());
                 }
            }
            else
            {
                out.println(km.getExceptions());
            }
        }
        else
        {
            out.println(inf.getException());
        }
    }
    catch(Exception err)
    {
        out.println(err.getMessage());
    }
    %>
        <script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
	<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="assets/plugins/data-tables/DT_bootstrap.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="assets/scripts/app.js"></script>
	<script src="assets/scripts/table-editable.js"></script>    
	<script>
		jQuery(document).ready(function() {      
		   TableEditable.init();
		});
	</script>
</body>
</html>