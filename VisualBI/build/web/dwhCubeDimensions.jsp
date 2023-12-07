<%-- 
    Document   : dwhTestConnection
    Created on : Oct 21, 2014, 9:34:01 PM
    Author     : Carlitos
--%>

<%@page import="org.olap4j.metadata.NamedList"%>
<%@page import="org.olap4j.metadata.Dimension"%>
<%@page import="Dwh.Cube.StructureCube"%>
<%@page import="org.olap4j.OlapConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Dimensiones)</title>
    </head>
    <body>
        <input type="button" class="btn btn-sm dark" value="Cerrar" style="position: absolute;right: 10px;top: 60px" onclick="closeTab(8)"/>
        <div style="width: 300px">
            <center>
                <h5 style="text-transform: uppercase;color:green ">Analisís Olap</h5>
            <hr style="color: greenyellow"/>
            </center>
        <%
        
          String html = "",htmlTable = "";
          try
          {
              HttpSession sesion=request.getSession();
              if(sesion.getAttribute("seConnectionOlap")!= null)
              {
                  OlapConnection conOlap= (OlapConnection)sesion.getAttribute("seConnectionOlap");
                  StructureCube sc = new StructureCube(conOlap);
                  if(sc.processCube() == true)
                   {
                        htmlTable = "<table  style='width:200px;height:350px;'>";
                        htmlTable+="<tr><th>Esquema</th><td>"+sc.getNameSchema()+"</td></tr>";
                        htmlTable+="<tr><th>Cubo</th><td>"+sc.getNameCube()+"</td></tr>";
                        for(int j=1;j<=3;j++)
                        {
                            htmlTable+="<tr><th>Dimensión"+j+"</th><td>"+sc.getSelectHtmlDimension(j) +"</td></tr>";
                            htmlTable+="<tr><th>Nivel"+j+"</th><td>"+sc.setDivHtmlLevel(j) +"</td></tr>"; 
                        }
                        htmlTable+="<tr><th>Medidas</th><td>"+sc.getCheckBoxHtmlMeasures()+"</td></tr>";
                        htmlTable+="</table>";
                        out.println(htmlTable);
                        out.println("<div style='position:absolute;left:550px;top:100px;'><img src='logo/dimensi3.gif' onclick='funDwhTableData()'></div>");
                        
                        //------------------------------------------------------
                        out.println("<div id='divC1' style='position:absolute;left:880px;top:250px;'></div>");
                        out.println("<div id='divC2' style='position:absolute;left:540px;top:380px;' ></div>");
                        out.println("<div id='divC3' style='position:absolute;left:545px;top:105px;'></div>");
                        
                        out.println("<input type='text' id='input1' value='0' style='display:none'/>");
                        out.println("<input type='text' id='input2' value='0' style='display:none'/>");
                        out.println("<input type='text' id='input3' value='0' style='display:none'/>");
                        
                        out.println("<input type='text' id='stateFilter1' value='0' style='display:none' />");
                        out.println("<input type='text' id='stateFilter2' value='0' style='display:none'/>");
                        out.println("<input type='text' id='stateFilter3' value='0' style='display:none' />");
                        
                        out.println("<div id='divError' style='color:red;' width='200px' ></div>");
                        //------------------------------------------------------
                   }
                  else
                   {
                      out.println(sc.getExceptions());
                   }
              }
              else
              {
                  out.println("No hay conexión");
              }
          }
          catch(Exception error)
          {
              out.println(error.toString());
          }
        %>
        </div>
    </body>
</html>
