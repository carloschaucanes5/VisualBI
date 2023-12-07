<%-- 
    Document   : ChartJS
    Created on : 12/09/2014, 05:42:38 PM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<title>VisualBiTool(Gráfico de área)</title>
		<script src="js/QualityData/Attribute/LibraryChart/Chart.js"></script>
                <script src="js/QualityData/Attribute/LibraryChart/datosDistribucionNormal.js"></script>
	</head>
        <body style="border-color: chartreuse;background-color:#cde69c;" >
        <center>
            <%
                HttpSession sesion=request.getSession();
                String nameAttribute = (String)sesion.getAttribute("seAttribute");
                out.println("<center><h1 style='color:white;'>X="+nameAttribute+"</h1></center><br>");
            %>
        </center>
       
		<div style="width:80%;height: 60%;position: absolute;left: 100px;">
			<div>
				<canvas id="canvas" height="160" width="300"></canvas>
			</div>
		</div>
      
	<script>
		var randomScalingFactor = function(){ return Math.round(Math.random()*100)};

	window.onload = function(){
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myLine = new Chart(ctx).Line(lineChartData, {
			responsive: true
		});
	}


	</script>
	</body>
</html>
