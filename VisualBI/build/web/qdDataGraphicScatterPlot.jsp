<%-- 
    Document   : qdGraphicScatterPlot
    Created on : 10/12/2014, 10:22:15 PM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Gráfico de dispersión)</title>
        <link rel="stylesheet" href="css/jquery/jquery-ui.css">
        <link rel="stylesheet" href="css/jquery/style.css">
        <link rel="stylesheet" href="css/QualityData/Data/ScatterPlot.css">
        <script src="js/jquery/jquery-1.8.2.js"></script>
        <script src="js/jquery/jquery-ui.js"></script>
        <script type="text/javascript" src="js/QualityData/Data/axes.js"></script>
        <script type="text/javascript" src="js/QualityData/Data/JsData.js"></script>
        <script src="js/modales/modales.js"></script>
        <script src="js/d3/d3.v3.min.js"></script>
        <style>
body {
  font: 11px sans-serif;
}

.axis path,
.axis line {
  fill: none;
  stroke: #000;
  shape-rendering: crispEdges;
}

.dot {
  stroke: #000;
}

.tooltip {
  position: absolute;
  width: 200px;
  height: 28px;
  pointer-events: none;
}
</style>
    </head>
    <body>
        <%
        HttpSession sesion=request.getSession();
        out.println("<center><h3 color='blue'>"+(String)sesion.getAttribute("relation") +"</h3></center>");
        %>
        <div id="id_grafico">
          
<script>
  var margin = {top: 50, right: 20, bottom: 30, left: 200},
    width = 1100 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;
    /*var ejex=fun_ejex();
    var ejey=fun_ejey();
    var grupo=fun_ejez();*/
    
    var ejex=axisX();
    var ejey=axisY()
    var grupo=axisZ();
/* 
 * value accessor - returns the value to encode for a given data object.
 * scale - maps value to a visual display encoding, such as a pixel position.
 * map function - maps from data value to display value
 * axis - sets up axis
 */ 

// setup x 

var xValue = function(d) { return d[ejex];}, // data -> value
    xScale = d3.scale.linear().range([0, width]), // value -> display
    xMap = function(d) { return xScale(xValue(d));}, // data -> display
    xAxis = d3.svg.axis().scale(xScale).orient("bottom");

// setup y
var yValue = function(d) { return d[ejey];}, // data -> value
    yScale = d3.scale.linear().range([height, 0]), // value -> display
    yMap = function(d) { return yScale(yValue(d));}, // data -> display
    yAxis = d3.svg.axis().scale(yScale).orient("left");

// setup fill color
var cValue = function(d) { return d[grupo];},
    color = d3.scale.category10();
  
    
// add the graph canvas to the body of the webpage
var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right+60)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

// add the tooltip area to the webpage
var tooltip = d3.select("body").append("div")
    .attr("class", "tooltip")
    .style("opacity", 20);

// load data
d3.csv("files/cereal.csv", function(error, data) {

  // change string (from CSV) into number format
  data.forEach(function(d) {
    d[ejex] = +d[ejex];
    d[ejey] = +d[ejey];
//    console.log(d);
  });

  // don't want dots overlapping axis, so add in buffer to data domain
  xScale.domain([d3.min(data, xValue), d3.max(data, xValue)]);
  yScale.domain([d3.min(data, yValue), d3.max(data, yValue)]);

  // x-axis
  svg.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis)
    .append("text")
      .attr("class", "label")
      .attr("x", width)
      .attr("y", -6)
      .style("text-anchor", "end")
      .text(ejex);

  // y-axis
  svg.append("g")
      .attr("class", "y axis")
      .call(yAxis)
    .append("text")
      .attr("class", "label")
      .attr("transform", "rotate(-90)")
      .attr("y", 6)
      .attr("dy", ".71em")
      .style("text-anchor", "end")
      .text(ejey);
      
  // draw dots
  svg.selectAll(".dot")
      .data(data)
    .enter().append("circle")
      .attr("class", "dot")
      .attr("r", 3.0)
      .attr("cx", xMap)
      .attr("cy", yMap)
      .style("fill", function(d) { return color(cValue(d));}) 
      .on("click", function(d) {
               funQdViewInstance(d["index"])
      });

  // draw legend
  var legend = svg.selectAll(".legend")
      .data(color.domain())
    .enter().append("g")
      .attr("class", "legend")
      .attr("transform", function(d, i) { return "translate(0," + i * 50 + ")"; });

  // draw legend colored rectangles
  legend.append("rect")
      .attr("x", width+40)
      .attr("width", 20)
      .attr("dy", ".35em")
      .attr("height", 18)
      .style("fill", color);
     
  // draw legend text
  legend.append("text")
      .attr("x", width +40)
      .attr("y", 9)
      .attr("dy", ".35em")
      .style("text-anchor", "end")
      .text(function(d) { return d;});
      
 
});

</script>
        </div>
        
        <div id="divLoad"></div>
        <div id="divX">
            <%
               String axisX = (String)sesion.getAttribute("valuesAttribute1");
               out.println(axisX);
            %>
        </div>
        <div id="divY">
            <%
               String axisY = (String)sesion.getAttribute("valuesAttribute2");
               out.println(axisY);
            %>
        </div>
        <div id="divZ">
            <%
               String axisZ = (String)sesion.getAttribute("valuesAttribute3");
               out.println(axisZ);
            %>
        </div>
        <div id='dialog-instance' title="VisualBiTool(Instancia)"></div>
        <div id="divFile" style="display: none">
        <%
            out.println("<input type='text' id='filePath' value='"+(String)sesion.getAttribute("path")+"'>");
            out.println("<input type='text' id='fileName' value='"+(String)sesion.getAttribute("name")+"'>");
        %>
        </div>
    </body>
</html>
