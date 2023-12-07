<%-- 
    Document   : dwhLevels
    Created on : Oct 22, 2014, 4:55:44 PM
    Author     : Carlitos
--%>

<%@page import="Dwh.Cube.StructureCube"%>
<%@page import="org.olap4j.OlapConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Niveles)</title>
    </head>
    <body>
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
                      String nameDimension = (String)request.getParameter("nameDimension");
                      int indexOrd = Integer.parseInt((String)request.getParameter("indexOrdinal"));
                      out.println(sc.getSelectHtmlLevelFull(nameDimension,indexOrd));
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
    </body>
</html>
