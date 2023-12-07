<%-- 
    Document   : dmRulesTable
    Created on : Oct 14, 2014, 7:02:01 PM
    Author     : Carlitos
--%>

<%@page import="File.Upload.InstancesFile"%>
<%@page import="DataMining.Rules.ClassApriori"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/DataMining/rule/rule.js"></script>
        <title>VisualBiTool (Tabla de Datos)</title>
    </head>
<body>
	<div>
		<div>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
                                                    <div class="caption"><i class="fa fa-edit"></i>Tabla de reglas</div>
							<div class="tools">
								<a href="javascript:;" class="remove"></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="btn-group">
								</div>
							</div>
<%
            HttpSession sesion=request.getSession();
            String path =  (String)request.getParameter("path");
            String name =  (String)request.getParameter("name");
            int numberRules = Integer.parseInt((String)request.getParameter("numberRules"));
            float minMetric = Float.parseFloat((String)request.getParameter("minMetric"));
            float lowerBoundMinSupport = Float.parseFloat((String)request.getParameter("lowerBoundMinSupport"));
            float upperBoundMinSupport = Float.parseFloat((String)request.getParameter("upperBoundMinSupport"));
            InstancesFile inf = new InstancesFile(path+"/uploads/"+name);
            if(inf.processingData()==true)
             {
                ClassApriori ca = new  ClassApriori(inf.getData(), numberRules, minMetric, lowerBoundMinSupport, upperBoundMinSupport);
                if(ca.buildRules()==true)
                 {
                    out.println(ca.getHtmlTableRules());
                    sesion.setAttribute("alApriori",ca.getApriori());
                    String html = "";
                    html += "<table>";
                    html += "<tr><th>Gráfico de Red</th></tr>";
                    html += "<tr><th><img src='images/DataMining/Rules/networkChart.JPG' onclick='funDmRulerGraphhic()'/></th></tr>";
                    html += "</table>";
                    out.println(html);
                    //out.println("<input type='button' value='View Graphic' onclick='funDmRulerGraphhic()'>");
                 }
                 else
                 {
                    out.println(ca.getExcepciones());
                 }
             }
             else
             {
                out.println(inf.getException());
             }      
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
        <div id="pruebas"></div>
</body>
</html>
