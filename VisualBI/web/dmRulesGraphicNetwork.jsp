<%-- 
    Document   : rule3
    Created on : 1/06/2014, 03:14:48 PM
    Author     : carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Grafico de Red)</title>
        <link rel="stylesheet" href="css/jquery/jquery-ui.css">
        <link rel="stylesheet" href="css/jquery/style.css">
        <script src="js/jquery/jquery-1.8.2.js"></script>
        <script src="js/jquery/jquery-ui.js"></script>
        <script type="text/javascript" src="js/vis/vis.js"></script>
        <script type="text/javascript" src="js/DataMining/rule/data.js"></script>
        <script type="text/javascript" src="js/DataMining/rule/rulesDivisions.js"></script>
  <style type="text/css">
    #mygraph {
      width: 950px;
      height: 600px;
      border: 0.5px solid lightgray;
    }
  </style>
    </head>
    <body style="background-color: #cfc">
        <% 
            HttpSession sesion=request.getSession();
            out.println("<center><h2>Reglas:"+(String)sesion.getAttribute("seTituloR")+"</center></h2>");
        %>
        <input type="text" readonly="true" style="background-color: yellow" size="1"/>Antecedente<br/>
        <input type="text" readonly="true" style="background-color: cyan" size="1"/>Consecuente
        <div id="mygraph"></div>
<div id="info"></div>

<script type="text/javascript">
  // create an array with nodes
      // create nodes
      nodes=fun_nodes();
      // create edges
      edges=fun_edges();

  // create a graph
  var container = document.getElementById('mygraph');
  var data = {
    nodes: nodes,
    edges: edges
  };
  var options = {
    nodes: {
      shape: 'box',
      radiusMin: 20,
            radiusMax: 40,
            fontSize: 11,
            fontFace: "Tahoma"
    },
      tooltip: {
            delay: 200,
            fontSize: 12,
            color: {
                background: "#fff"
                }
            }
  };
  graph = new vis.Graph(container, data, options);

//add event listener
  graph.on('select', function(properties) {
  //document.getElementById('info').innerHTML += 'selection: ' + JSON.stringify(properti) + '<br>';
  });

// set initial selection (id's of some nodes);
 graph.setSelection([2, 2, 2]);
</script>
<div id="divReglas">
        <%
            
           if(sesion.getAttribute("seSelectR")!= null && sesion.getAttribute("seReglasR")!=null)
            {
                out.println("<div>");
                out.println((String)sesion.getAttribute("seReglasR"));
                out.println("</div>");
                out.println("<div style='position:absolute;top:30px;left:970px'>");
                out.println((String)sesion.getAttribute("seSelectR"));
                out.println("</div>");
            }
            else
            {
                return;
            }
        %>    
    
</div>

    </body>
</html>
