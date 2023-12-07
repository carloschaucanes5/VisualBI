<%-- 
    Document   : dwhFilterLevel
    Created on : 2/11/2014, 09:06:15 PM
    Author     : Carlitos
--%>

<%@page import="Dwh.Connection.ClassConnection"%>
<%@page import="Dwh.Cube.SqlFilterLevel"%>
<%@page import="Dwh.Cube.GeneralQuery"%>
<%@page import="Dwh.Table.QueryTable"%>
<%@page import="Dwh.Cube.StructureCube"%>
<%@page import="org.olap4j.OlapConnection"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VisualBiTool(Filtros)</title>
    </head>
    <body>
        
        <%
         String html = "",htmlTable = "", level = "", measures = "",sql="";
         String[] vecDim = null;
          try
          {
              HttpSession sesion=request.getSession();
            String cadCon = (String)request.getParameter("cadCon");
            String[] vecCon = cadCon.split("=>");
            ClassConnection cc = new ClassConnection(vecCon[0].trim(), vecCon[1].trim(),vecCon[2].trim() , vecCon[3].trim(), vecCon[4].trim(), vecCon[5].trim());
                if(cc.processConnection() == true)
                {
                  OlapConnection conOlap= (OlapConnection)sesion.getAttribute("seConnectionOlap");
                  StructureCube sc = new StructureCube(conOlap);
                  if(sc.processCube() == true)
                   { 
                      level = (String)request.getParameter("level");
                      int indexLevel = Integer.parseInt((String)request.getParameter("indexLevel"));
                      measures = (String)request.getParameter("measures");
                      vecDim = level.split("=>");
                      SqlFilterLevel sfl = new SqlFilterLevel(vecDim[0], vecDim[1], sc.getNameCube(), measures);
                      sql=sfl.getDistinctValuesSql();
                      //out.println(sql);
                      QueryTable pt = new QueryTable(conOlap,sql,measures);
                      if(pt.processQuery()==true)
                      {
                          out.println(pt.getTableLevelFilter(indexLevel));
                      }
                      else
                      {
                          out.println(pt.getExceptions());
                      }
                      //materia=>dim_materia.nombre
                   }
                  else
                   {
                      out.println(sc.getExceptions());
                   }
              }
              else
              {
                  out.println(cc.getExceptions());
              }
          }
          catch(Exception error)
          {
              out.println(error.toString());
          }
        %>
    </body>
</html>
