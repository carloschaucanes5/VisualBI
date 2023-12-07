<%-- 
    Document   : BartChart1
    Created on : 31/08/2014, 04:44:48 PM
    Author     : Carlitos
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<title>VisualBiTool(Gráfico de Barras)</title>
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

	/*var barChartData = {
		labels : ["Januaryhjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjiuhiluiiiiiiiiiiiiiiiiihfuyfllllllllllllllllgliglil","February","March","April","May","June","July"],
		datasets : [
			{
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill: "rgba(220,220,220,0.75)",
				highlightStroke: "rgba(220,220,220,1)",
				data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
			},
			{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,0.8)",
				highlightFill : "rgba(151,187,205,0.75)",
				highlightStroke : "rgba(151,187,205,1)",
				data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
			}
		]

	}*/
	window.onload = function(){
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myBar = new Chart(ctx).Bar(lineChartData, {
			responsive : true
		});
	}

	</script>
	</body>
</html>

