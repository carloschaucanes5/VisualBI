<%-- 
    Document   : tree1
    Created on : 1/05/2014, 11:44:37 AM
    Author     : carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <script type="text/javascript" src="js/d3/d3.js"></script>
        <script type="text/javascript" src="js/d3/d3.layout.js"></script>
        <title>VisualBiTool(Árbol jerárquico)</title>
        <style type="text/css">
.node circle {
cursor: pointer;
fill: #fcf;
stroke: steelblue;
stroke-width: 15px;
}

.node text {
position: absolute;
width: auto;
height: auto;
font-size: 13px;
background-color: #3cc051;
}

path.link {
fill: none;
stroke: #cfc;
stroke-width: 2px;
}
.tooltip {
  position: absolute;
  width: 200px;
  height: auto;
  border-color: gold;
  background-color: greenyellow;
}

</style>

    </head>
    <body style="background-color: #8cc657">
    <% 
        HttpSession sesion=request.getSession();
        out.println("<h4>Árboles de decisión :"+(String)sesion.getAttribute("titleTree")+"</h4>");
    %>


        <div style="display: table-row">
<script src="js/d3/d3.v3.min.js"></script>
<script>
 
var margin = {top: 20, right: 120, bottom: 20, left: 200},
    width = 1000000 - margin.right - margin.left,
    height =1000 - margin.top - margin.bottom;
    
var i = 0,
    duration = 750,
    root;

var tree = d3.layout.tree()
    .size([height, width]);

var diagonal = d3.svg.diagonal()
    .projection(function(d) { return [d.y, d.x]; });

var svg = d3.select("body").append("svg")
    .attr("width", width + margin.right + margin.left)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

var tooltip = d3.select("body").append("div")
    .attr("class", "tooltip")
    .style("opacity", 20);

d3.json("files/flare.json", function(error, flare) {
  root = flare;
  root.x0 = height;
  root.y0 = 0;

  function collapse(d) {
    if (d.children) {
      d._children = d.children;
      d._children.forEach(collapse);
      d.children = null;
    }
  }

  root.children.forEach(collapse);
  update(root);
});

d3.select(self.frameElement).style("height", "800px");

function update(source) {

  // Compute the new tree layout.
  var nodes = tree.nodes(root).reverse(),
      links = tree.links(nodes);

  // Normalize for fixed-depth.
  nodes.forEach(function(d) { d.y = d.depth * 180; });
 

  // Update the nodes…
  var node = svg.selectAll("g.node")
      .data(nodes, function(d) { return d.id || (d.id = ++i); });

  // Enter any new nodes at the parent's previous position.
  var nodeEnter = node.enter().append("g")
      .attr("class", "node")
      .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
      .on("click", click)
                  .on("mouseover", function(d) {
          tooltip.transition()
               .duration(200)
               .style("opacity", .9)
               tooltip.html(d.label)  
               .style("left",(d3.event.pageX -250) + "px")
               .style("top",(d3.event.pageY -45) + "px")});

  nodeEnter.append("circle")
      .attr("r", 1e-8)
      .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });
  nodeEnter.append("text")
      .attr("x", function(d) { return d.children || d._children ? -20 : 20; })
      .attr("dy", ".50em")
      .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
      .text(function(d) { return d.name.split("->")[d.name.split("->").length-1]; })
      .style("fill-opacity", 1e-8);

  
 

  // Transition nodes to their new position.
  var nodeUpdate = node.transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

  nodeUpdate.select("circle")
      .attr("r", 4.5)
      .style("fill", function(d) { return d._children ? "red" : "#fff"; });

  nodeUpdate.select("text")
      .style("fill-opacity", 1);

  // Transition exiting nodes to the parent's new position.
  var nodeExit = node.exit().transition()
      .duration(duration)
      .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
      .remove();

  nodeExit.select("circle")
      .attr("r", 1e-6);

  nodeExit.select("text")
      .style("fill-opacity", 1e-6);

  // Update the links…
  var link = svg.selectAll("path.link")
      .data(links, function(d) { return d.target.id; });

  // Enter any new links at the parent's previous position.
  link.enter().insert("path", "g")
      .attr("class", "link")
      .attr("d", function(d) {
        var o = {x: source.x0, y: source.y0};
        return diagonal({source: o, target: o});
      })
      ;

  // Transition links to their new position.
  link.transition()
      .duration(duration)
      .attr("d", diagonal);
//d3.select("body").append("p").text("hola mundito");

 
  // Transition exiting nodes to the parent's new position.
  link.exit().transition()
      .duration(duration)
      .attr("d", function(d) {
        var o = {x: source.x, y: source.y};
        return diagonal({source: o, target: o});
      })
      .remove();

  // Stash the old positions for transition.
  nodes.forEach(function(d) {
    d.x0 = d.x;
    d.y0 = d.y;
  });
  
}

// Toggle children on click.
function click(d) {
  if (d.children) {
    d._children = d.children;
    d.children = null;
    
  } else {
    d.children = d._children;
    d._children = null;
  }
  update(d);
  //alert(d.name);
  
}
</script>
        </div>
</body>
</html>
