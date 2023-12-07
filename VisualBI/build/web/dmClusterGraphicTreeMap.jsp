<%-- 
    Document   : graficosCuadrosZoom
    Created on : 23/07/2014, 12:49:30 PM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/jquery/jquery-ui.css">
        <link rel="stylesheet" href="css/jquery/style.css">
        <script src="js/jquery/jquery-1.8.2.js"></script>
        <script src="js/jquery/jquery-ui.js"></script>
        <script src="js/modales/modales.js"></script>
        <script type="text/javascript" src="js/DataMining/Clustering/clustering.js"></script> 
        <script type="text/javascript" src="js/d3/d3.js"></script>
        <script type="text/javascript" src="js/d3/d3.layout.js"></script>

    <style type="text/css">

.chart {
  margin-left: 10px;
  margin-top: 40px;
}

text {
  font-size: 12px;
}

rect {
  fill: none;
}

    </style>
        <title>VisualBiTool(Mapa de Arbol)</title>
    </head>
    <body>
        
        <% 
            HttpSession sesion=request.getSession();
            out.println("<center><h2>Agrupamiento:"+(String)sesion.getAttribute("seTitle")+"</center></h2><hr>");
        %>
        <div id="body">
    </div>
    <script type="text/javascript">

var w = 1000-80 ,
    h = 470 ,
    x = d3.scale.linear().range([0, w]),
    y = d3.scale.linear().range([0, h]),
    color = d3.scale.category20c(),
    root,
    node;

var treemap = d3.layout.treemap()
    .round(false)
    .size([w, h])
    .sticky(true)
    .value(function(d) { return d.size; });
     //-------------------------------------- 
    var tooltip = d3.select("body").append("div")
    .attr("class", "tooltip")
    .style("opacity", 20);  
    //----------------------------------------    

var svg = d3.select("#body").append("div")
    .attr("class", "chart")
    .style("width", w + "px")
    .style("height", h + "px")
  .append("svg:svg")
    .attr("width", w)
    .attr("height", h)
  .append("svg:g")
    .attr("transform", "translate(.5,.5)");

d3.json("files/clusters.json", function(data) {
  node = root = data;

  var nodes = treemap.nodes(root)
      .filter(function(d) { return !d.children; });

  var cell = svg.selectAll("g")
      .data(nodes)
    .enter().append("svg:g")
      .attr("class", "cell")
      .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; })
      .on("dblclick", function(d) { return zoom(node == d.parent ? root : d.parent); });

  cell.append("svg:rect")
      .attr("width", function(d) { return d.dx - 1; })
      .attr("height", function(d) { return d.dy - 1; })
      .style("fill", function(d) { return color(d.parent.name); })
      .on("click", function(d) {funInfo(d.name)})
      .on("mouseover", function(d) {
          tooltip.transition()
          .duration(200)
          .style("opacity", .9);
          tooltip.html("Indice: "+d.name+"<br/>Grupo: "+d.label+"<br/>Distancia: "+d.size+"")
          .style("left",(d3.event.pageX + 5) + "px")
          .style("top",(d3.event.pageY - 30) + "px"); 
          })
       .on("mouseout", function(d) {
          tooltip.transition()
          .duration(500)
          .style("display:show", 1);     
          });

  cell.append("svg:text")
      .attr("x", function(d) { return d.dx / 2; })
      .attr("y", function(d) { return d.dy / 2; })
      .attr("dy", ".35em")
      .attr("text-anchor", "middle")
      .text(function(d) { return d.name; })
      .style("opacity", function(d) { d.w = this.getComputedTextLength(); return d.dx > d.w ? 1 : 0; });

  d3.select(window).on("dblclick", function() { zoom(root); });

  d3.select("select").on("change", function() {
    treemap.value(this.value == "size" ? size : count).nodes(root);
    zoom(node);
  });
});

function size(d) {
  return d.size;
}

function count(d) {
  return 1;
}

function zoom(d) {
  var kx = w / d.dx, ky = h / d.dy;
  x.domain([d.x, d.x + d.dx]);
  y.domain([d.y, d.y + d.dy]);

  var t = svg.selectAll("g.cell").transition()
      .duration(d3.event.altKey ? 7500 : 750)
      .attr("transform", function(d) { return "translate(" + x(d.x) + "," + y(d.y) + ")"; });

  t.select("rect")
      .attr("width", function(d) { return kx * d.dx - 1; })
      .attr("height", function(d) { return ky * d.dy - 1; })

  t.select("text")
      .attr("x", function(d) { return kx * d.dx / 2; })
      .attr("y", function(d) { return ky * d.dy / 2; })
      .style("opacity", function(d) { return kx * d.dx > d.w ? 1 : 0; });

  node = d;
  d3.event.stopPropagation();
}

    </script>
<div id="dialog-instance" title="VisualBiTools(Instancia)"></div>
<div id="dialog-message" title="VisualBiTool()">
</div>
<div id="divSelect" style ="position: absolute;right: 200px;top: 100px">
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
<div id="divClusters"  style ="position: absolute;left: 920px;top: 180px;height: 500px;width: 400px;overflow: scroll;"></div>
<div id="divLoad"  style ="position: absolute;left: 600px;top: 70px;height: 200px"></div>
    </body>
</html>
