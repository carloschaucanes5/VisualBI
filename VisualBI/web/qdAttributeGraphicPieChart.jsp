<%-- 
    Document   : qdGraphicPieChart
    Created on : 5/12/2014, 03:47:14 PM
    Author     : Carlitos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>VisualBiTool(Gráfico de circulo)</title>

<!-- CSS Files -->
<link type="text/css" href="css/LibraryInfovis/base.css" rel="stylesheet" />
<link type="text/css" href="css/LibraryInfovis/PieChart.css" rel="stylesheet" />

<!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

<!-- JIT Library File -->
<script language="javascript" type="text/javascript" src="js/QualityData/Attribute/LibraryInfovis/jit.js"></script>
<script language="javascript" type="text/javascript" src="js/QualityData/Attribute/LibraryInfovis/datos.js"></script>
<!-- Example File -->
<script language="javascript" type="text/javascript" src="js/QualityData/Attribute/LibraryInfovis/qdPieChart.js"></script>
</head>

<body onload="init();" style="border-color: chartreuse;background-color:#cde69c;">
        <center>
            <%
                HttpSession sesion=request.getSession();
                String nameAttribute = (String)sesion.getAttribute("seAttribute");
                out.println("<center><h1 style='color:white;'>X="+nameAttribute+"</h1></center>");
            %>
        </center>
<div id="container">


<div id="center-container">
    <div id="infovis"></div>    
</div>

<div id="right-container">

<div id="inner-details"></div>

</div>

<div id="log"></div>
</div>
</body>
</html>
