<%-- 
    Document   : graficoCirculos
    Created on : 21/07/2014, 05:38:33 PM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="css/jquery/jquery-ui.css">
        <link rel="stylesheet" href="css/jquery/style.css">
        <script src="js/jquery/jquery-1.8.2.js"></script>
        <script src="js/jquery/jquery-ui.js"></script>
        <script src="js/modales/modales.js"></script>
        <script type="text/javascript" src="js/DataMining/Clustering/clustering.js"></script> 
        <title>VisualBiTool(Grafico de Burbujas)</title>
        <style>

circle {
  fill: rgb(31, 119, 180);
  fill-opacity: .25;
  stroke: rgb(31, 119, 180);
  stroke-width: 1px;
}

.leaf circle {
  fill: #ff7f0e;
  fill-opacity: 1;
}

text {
  font: 10px sans-serif;
}
.tooltip {
  position: absolute;
  width: auto;
  height: auto;
  background-color: #d0e9c6;
  border-color: black;
}

</style>
    </head>
    <body>
        <% 
            HttpSession sesion=request.getSession();
            out.println("<center><h2>Agrupamiento:"+(String)sesion.getAttribute("seTitle")+"</center></h2><hr>");
        %>
        <script src="js/d3/d3.v3.min.js"></script>
    <center>
<script>

var diameter = 600,
    format = d3.format(",d");

var pack = d3.layout.pack()
    .size([diameter - 4, diameter - 4])
    .value(function(d) { return d.size; });
    
    var tooltip = d3.select("body").append("div")
    .attr("class", "tooltip")
    .style("opacity", 20);
//------------------------------------------

var cValue = function(d) { return d.label;},
    color = d3.scale.category20c();
    
//-------------------------------------------
    
var svg = d3.select("body").append("svg")
    .attr("width", diameter)
    .attr("height", diameter)
    .style("border-color","black")
    .append("g")
    .attr("transform", "translate(2,2)");

d3.json("files/clusters.json", function(error, root) {
  var node = svg.datum(root).selectAll(".node")
      .data(pack.nodes)
      .enter().append("g")
      .attr("class", function(d) { return d.children ? "node" : "leaf node"; })
      .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; })
      .on("click", function(d) {funInfo(d.name)})
      .on("mouseover", function(d) {
          tooltip.transition()
               .duration(200)
               .style("opacity", .9)             
               tooltip.html("Indice: "+d.name+"<br/>Grupo: "+d.label+"<br/>Distancia: "+d.size+"") 
               //tooltip.html(funIndexes(d.name,d.label,d.size)) 
               .style("left",(d3.event.pageX+20) + "px")
               .style("top",(d3.event.pageY-50) + "px")})
      .on("mouseout", function(d) {
          tooltip.transition()
          .duration(10)
          .style("display:none", 1);     
          });
        //node.append("title")
        //.text(function(d) { return d.name + (d.children ? "" : ": " + format(d.size))+":"; })
        //.html(function(d) { return "<h1>Index: "+d.name+"</h1><br/><h1>Cluster: "+d.label+"</h1><br/><h1>Distance: "+d.size+"</h1>"; });

  node.append("circle")
      .attr("r", function(d) { return 4; })
      .style("fill", function(d) { return color(cValue(d));});

  node.filter(function(d) { return !d.children; }).append("text")
      .attr("dy", ".3em")
      .style("text-anchor", "middle");
      //.text(function(d) { return d.name.substring(0, d.r / 3); });
   

      
});
d3.select(self.frameElement).style("height", diameter + "px");
</script>

<div id="dialog-instance" title="VisualBiTool(Instancia)"></div>
<div id="dialog-message" title="VisualBiTool(Mensaje)">
</div>
<div id="divSelect" style ="position: absolute;right: 190px;top: 100px">
    <center>
    <h3 style="text-align: center;color: #ff7f0e">Grupos</h3>
    <%
        if(sesion.getAttribute("seSelect")!=null)
         {
            out.println((String)sesion.getAttribute("seSelect"));
         }
    %>
    </center>
</div>
<div id="divClusters"  style ="position: absolute;left: 790px;top: 180px;height: 500px;width: 400px;overflow: scroll;"></div>
<div id="divLoad"  style ="position: absolute;left: 600px;top: 100px;height: 200px"></div>
    </center>
    </body>
</html>
